package Cheiron.Pipeline;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;

public abstract class Pipeline {

	protected AnalysisEngine engine = null;

	public Pipeline() {
		try {
			XMLInputSource xml = new XMLInputSource(new File("desc/pipeline.xml"));
			ResourceSpecifier spec = UIMAFramework.getXMLParser().parseResourceSpecifier(xml);
			engine = UIMAFramework.produceAnalysisEngine(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, JCas> convert(Map<String, Map<String, String>> data) throws Exception {
		Map<String, JCas> cases = new HashMap<String, JCas>();

		for (Entry<String, Map<String, String>> entry : data.entrySet()) {
			JCas cas = engine.newJCas();
			cas.setSofaDataURI(entry.getKey(), "text/prometheus-id");

			for (Entry<String, String> sofa : entry.getValue().entrySet())
				cas.createView(sofa.getKey()).setDocumentText(sofa.getValue());

			cases.put(entry.getKey(), cas);
		}

		return cases;
	}

	public abstract Map<String, JCas> process(Map<String, JCas> cases) throws Exception;

	public Object get(String field) throws Exception {
		return this.getClass().getDeclaredField(field).get(this);
	}

	public void set(String field, Object value) throws Exception {
		this.getClass().getDeclaredField(field).set(this, value);
	}

}
