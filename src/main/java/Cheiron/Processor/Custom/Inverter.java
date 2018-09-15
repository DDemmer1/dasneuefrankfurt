package Cheiron.Processor.Custom;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;
import de.uk.spinfo.types.EntityFrequency;
import de.uk.spinfo.types.EntityTFIDF;
import de.uk.spinfo.types.LemmaFrequency;
import de.uk.spinfo.types.LemmaTFIDF;
import de.uk.spinfo.types.TokenFrequency;
import de.uk.spinfo.types.TokenTFIDF;

public class Inverter extends Processor {

	private String language;
	
	public JCas metacas;
	public Map<String, List<String>> pids = new HashMap<String, List<String>>();

	public Map<String, Map<String, Double>> tokenFreq;
	public Map<String, Map<String, Double>> lemmaFreq;
	public Map<String, Map<String, Double>> entityFreq;

	private void wieghtToken(JCas view, JCas metacas) throws Exception {
		Map<String, Double> idfWeight = new HashMap<String, Double>();
		Map<String, Entry<Double, Double>> tfidfWeight = new HashMap<String, Entry<Double, Double>>();

		for (Entry<String, Map<String, Double>> t : tokenFreq.entrySet())
			idfWeight.put(t.getKey(), (double) Math.log((double) pids.get(language).size() / t.getValue().size()));

		for (TokenFrequency t : JCasUtil.select(view, TokenFrequency.class))
			tfidfWeight.put(t.getValue(), new SimpleEntry<Double, Double>(t.getRelativeFreq(),
					t.getRelativeFreq() * idfWeight.get(t.getValue())));

		tfidfWeight = tfidfWeight.entrySet().stream()
				.sorted((k, v) -> Double.compare(v.getValue().getValue(), k.getValue().getValue()))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (x, y) -> {
					return null;
				}, LinkedHashMap::new));

		int counter = 0;
		for (Entry<String, Entry<Double, Double>> e : tfidfWeight.entrySet()) {
			TokenTFIDF tfidf = new TokenTFIDF(metacas);
			tfidf.setBegin(counter);
			tfidf.setEnd(counter++);
			tfidf.setValue(e.getKey());
			tfidf.setTfWeight(e.getValue().getKey());
			tfidf.setTfidfWeight(e.getValue().getValue());
			tfidf.setComponentId("Custom.Inverter");
			tfidf.addToIndexes();

			System.out.println("Custom.Inverter(Token): [" + metacas.getViewName() + "] " + e.getKey() + "/"
					+ e.getValue().getKey() + "/" + e.getValue().getValue());
		}
	}

	private void weightLemma(JCas view, JCas metacas) throws Exception {
		Map<String, Double> idfWeight = new HashMap<String, Double>();
		Map<String, Entry<Double, Double>> tfidfWeight = new HashMap<String, Entry<Double, Double>>();

		for (Entry<String, Map<String, Double>> t : lemmaFreq.entrySet())
			idfWeight.put(t.getKey(), (double) Math.log((double) pids.get(language).size() / t.getValue().size()));

		for (LemmaFrequency l : JCasUtil.select(view, LemmaFrequency.class))
			tfidfWeight.put(l.getValue(), new SimpleEntry<Double, Double>(l.getRelativeFreq(),
					l.getRelativeFreq() * idfWeight.get(l.getValue())));

		tfidfWeight = tfidfWeight.entrySet().stream()
				.sorted((k, v) -> Double.compare(v.getValue().getValue(), k.getValue().getValue()))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (x, y) -> {
					return null;
				}, LinkedHashMap::new));

		int counter = 0;
		for (Entry<String, Entry<Double, Double>> e : tfidfWeight.entrySet()) {
			LemmaTFIDF tfidf = new LemmaTFIDF(metacas);
			tfidf.setBegin(counter);
			tfidf.setEnd(counter++);
			tfidf.setValue(e.getKey());
			tfidf.setTfWeight(e.getValue().getKey());
			tfidf.setTfidfWeight(e.getValue().getValue());
			tfidf.setComponentId("Custom.Inverter");
			tfidf.addToIndexes();

			System.out.println("Custom.Inverter(Lemma): [" + metacas.getViewName() + "] " + e.getKey() + "/"
					+ e.getValue().getKey() + "/" + e.getValue().getValue());
		}
	}

	private void weightEntity(JCas view, JCas metacas) throws Exception {
		Map<String, Double> idfWeight = new HashMap<String, Double>();
		Map<String, Entry<Double, Double>> tfidfWeight = new HashMap<String, Entry<Double, Double>>();

		for (Entry<String, Map<String, Double>> t : entityFreq.entrySet())
			idfWeight.put(t.getKey(), (double) Math.log((double) pids.get(language).size() / t.getValue().size()));

		for (EntityFrequency e : JCasUtil.select(view, EntityFrequency.class))
			tfidfWeight.put(e.getValue(), new SimpleEntry<Double, Double>(e.getRelativeFreq(),
					e.getRelativeFreq() * idfWeight.get(e.getValue())));

		tfidfWeight = tfidfWeight.entrySet().stream()
				.sorted((k, v) -> Double.compare(v.getValue().getValue(), k.getValue().getValue()))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (x, y) -> {
					return null;
				}, LinkedHashMap::new));

		int counter = 0;
		for (Entry<String, Entry<Double, Double>> e : tfidfWeight.entrySet()) {
			EntityTFIDF tfidf = new EntityTFIDF(metacas);
			tfidf.setBegin(counter);
			tfidf.setEnd(counter++);
			tfidf.setValue(e.getKey());
			tfidf.setTfWeight(e.getValue().getKey());
			tfidf.setTfidfWeight(e.getValue().getValue());
			tfidf.setComponentId("Custom.Inverter");
			tfidf.addToIndexes();

			System.out.println("Custom.Inverter(Entity): [" + metacas.getViewName() + "] " + e.getKey() + "/"
					+ e.getValue().getKey() + "/" + e.getValue().getValue());
		}
	}

	@Override
	protected void processView(JCas view) throws Exception {
		String pid = view.getView("_InitialView").getSofaDataURI();

		wieghtToken(view, JCasUtil.getView(metacas, pid, true));
		weightLemma(view, JCasUtil.getView(metacas, pid, true));
		weightEntity(view, JCasUtil.getView(metacas, pid, true));
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		language = lang;
	}

}
