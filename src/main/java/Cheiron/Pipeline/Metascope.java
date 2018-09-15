package Cheiron.Pipeline;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Custom.Aggregator;
import Cheiron.Processor.Custom.Inverter;
import Cheiron.Processor.Tika.Langdetector;

public class Metascope extends Pipeline {

	protected String pos = null;

	private String hash(String in) throws Exception {
		Formatter formatter = new Formatter();
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

		for (byte b : sha1.digest(in.getBytes("UTF-8")))
			formatter.format("%02x", b);

		String out = formatter.toString();
		formatter.close();

		return out;
	}

	@Override
	public Map<String, JCas> process(Map<String, JCas> cases) throws Exception {
		JCas metacas = engine.newJCas();
		Langdetector langdetector = new Langdetector();
		Aggregator aggregator = new Aggregator();
		Inverter inverter = new Inverter();
		String clear = cases.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> e.getKey())
				.collect(Collectors.joining("/"));
		String hash = "metacas-" + hash(clear);

		metacas.setSofaDataURI(hash, "text/metacas-id");

		for (Entry<String, JCas> entry : cases.entrySet()) {
			langdetector.process(entry.getValue());
			aggregator.process(entry.getValue());
		}

		inverter.set("metacas", metacas);
		inverter.set("pids", aggregator.get("pids"));
		inverter.set("tokenFreq", aggregator.get("tokenFreq"));
		inverter.set("lemmaFreq", aggregator.get("lemmaFreq"));
		inverter.set("entityFreq", aggregator.get("entityFreq"));

		for (Entry<String, JCas> entry : cases.entrySet())
			inverter.process(entry.getValue());

		cases.put(hash, metacas);

		return cases;
	}

}
