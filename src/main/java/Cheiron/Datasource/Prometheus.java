package Cheiron.Datasource;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Prometheus extends Datasource {

	protected String api, usr, key;
	protected String field, param, limit;
	protected String filters, sources, logical, orderby;

	@Override
	public Map<String, Map<String, String>> getData() throws Exception {
		Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

		String clearAuth = usr + ":" + key;
		String basicAuth = "Basic " + new String(Base64.getEncoder().encodeToString(clearAuth.getBytes()));

		String f = "?field=" + URLEncoder.encode(field, "UTF-8");
		String p = "&term=" + URLEncoder.encode(param, "UTF-8");
		String l = "&per_page=" + URLEncoder.encode(limit, "UTF-8");

		URL url = new URL(api + f + p + l);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("authorization", basicAuth);

		System.out.println("Datasource.Prometheus: " + url.toString());

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(connection.getInputStream());
		NodeList results = doc.getElementsByTagName("result");

		for (int i = 0; i < results.getLength(); i++) {
			String pid = UUID.randomUUID().toString();
			Map<String, String> record = new HashMap<String, String>();
			NodeList result = results.item(i).getChildNodes();

			for (int j = 0; j < result.getLength(); j++) {
				String key = result.item(j).getNodeName();
				String val = result.item(j).getTextContent();

				if (val.isEmpty()) {
					continue;
				} else if (key.equals("pid")) {
					pid = val;
				} else if (!filters.isEmpty() && !filters.contains(key)) {
					continue;
				} else {
					record.put(key, val);
				}
			}

			if (record.isEmpty())
				continue;

			data.put(pid, record);
		}

		return data;
	}

}
