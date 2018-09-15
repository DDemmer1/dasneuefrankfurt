package Cheiron.Datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.vfs2.FileNotFolderException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Filesystem extends Datasource {

	protected String path;
	protected String filters;

	@Override
	public Map<String, Map<String, String>> getData() throws Exception {
		File directory = new File(path);
		Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

		if (!directory.exists())
			throw new FileNotFoundException(directory.getAbsolutePath());

		if (!directory.isDirectory())
			throw new FileNotFolderException(directory.getAbsolutePath());

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		for (File file : directory.listFiles()) {
			System.out.println("Datasource.Filesystem: " + file.getAbsolutePath());

			Document document = builder.parse(file);
			NodeList results = document.getElementsByTagName("result");

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
		}

		return data;
	}
}
