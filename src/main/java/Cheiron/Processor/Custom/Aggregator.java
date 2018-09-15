package Cheiron.Processor.Custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;
import de.uk.spinfo.types.EntityFrequency;
import de.uk.spinfo.types.LemmaFrequency;
import de.uk.spinfo.types.TokenFrequency;

public class Aggregator extends Processor {

	private String language;

	public Map<String, List<String>> pids = new HashMap<String, List<String>>();
	public Map<String, Map<String, Double>> tokenFreq = new HashMap<String, Map<String, Double>>();
	public Map<String, Map<String, Double>> lemmaFreq = new HashMap<String, Map<String, Double>>();
	public Map<String, Map<String, Double>> entityFreq = new HashMap<String, Map<String, Double>>();

	private void aggregateToken(JCas view, String pid) {
		for (TokenFrequency t : JCasUtil.select(view, TokenFrequency.class)) {
			if (!tokenFreq.containsKey(t.getValue()))
				tokenFreq.put(t.getValue(), new HashMap<String, Double>());

			Map<String, Double> nestedMap = tokenFreq.get(t.getValue());
			nestedMap.put(pid, t.getRelativeFreq());

			tokenFreq.put(t.getValue(), nestedMap);
		}
	}

	private void aggregateLemma(JCas view, String pid) {
		for (LemmaFrequency l : JCasUtil.select(view, LemmaFrequency.class)) {
			if (!lemmaFreq.containsKey(l.getValue()))
				lemmaFreq.put(l.getValue(), new HashMap<String, Double>());

			Map<String, Double> nestedMap = lemmaFreq.get(l.getValue());
			nestedMap.put(pid, l.getRelativeFreq());

			tokenFreq.put(l.getValue(), nestedMap);
		}
	}

	private void aggregateEntity(JCas view, String pid) {
		for (EntityFrequency f : JCasUtil.select(view, EntityFrequency.class)) {
			if (!entityFreq.containsKey(f.getValue()))
				entityFreq.put(f.getValue(), new HashMap<String, Double>());

			Map<String, Double> nestedMap = entityFreq.get(f.getValue());
			nestedMap.put(pid, f.getRelativeFreq());

			entityFreq.put(f.getValue(), nestedMap);
		}
	}

	@Override
	protected void processView(JCas view) throws Exception {
		String pid = view.getView("_InitialView").getSofaDataURI();

		aggregateToken(view, pid);
		aggregateLemma(view, pid);
		aggregateEntity(view, pid);

		pids.get(language).add(pid);
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		language = lang;
		
		if (!pids.containsKey(lang))
			pids.put(lang, new ArrayList<String>());
	}

}
