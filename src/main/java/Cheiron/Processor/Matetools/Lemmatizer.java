package Cheiron.Processor.Matetools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;
import de.julielab.jcore.types.Lemma;
import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import is2.data.SentenceData09;

public class Lemmatizer extends Processor {

	private Map<String, is2.lemmatizer2.Lemmatizer> detectors = new HashMap<String, is2.lemmatizer2.Lemmatizer>();

	protected void processView(JCas view) throws Exception {
		if (!detectors.containsKey(view.getDocumentLanguage()))
			return;

		for (Sentence sentence : JCasUtil.select(view, Sentence.class)) {
			List<Token> tokens = JCasUtil.selectCovered(Token.class, sentence);
			List<String> strings = new ArrayList<String>();

			strings.add("<root>");
			tokens.forEach(e -> strings.add(e.getCoveredText()));

			SentenceData09 data = new SentenceData09();
			data.init(strings.toArray(new String[strings.size()]));
			data = detectors.get(view.getDocumentLanguage()).apply(data);

			for (int i = 0; i < data.length(); i++) {
				Token token = tokens.get(i);

				Lemma lemma = new Lemma(view);
				lemma.setComponentId("Matetools.Lemmatizer");
				lemma.setBegin(token.getBegin());
				lemma.setEnd(token.getEnd());
				lemma.setValue(data.plemmas[i]);
				lemma.addToIndexes();
				token.setLemma(lemma);

				System.out.println("Matetools.Lemmatizer: " + token.getCoveredText() + "/" + lemma.getValue());
			}
		}
	}

	protected void loadDetector(String lang) {
		if (detectors.containsKey(lang))
			return;

		File model = new File("resources/matetools/" + lang + "-lemma.mdl");

		if (!model.exists())
			System.out.println("Matetools.Lemmatizer: No model for language '" + lang + "' found, aborting!");
		else
			detectors.put(lang, new is2.lemmatizer2.Lemmatizer(model.getPath()));
	}

}
