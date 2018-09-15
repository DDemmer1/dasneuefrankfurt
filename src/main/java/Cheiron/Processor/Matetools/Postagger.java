package Cheiron.Processor.Matetools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import Cheiron.Processor.Processor;
import de.julielab.jcore.types.POSTag;
import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import is2.data.SentenceData09;
import is2.lemmatizer2.Lemmatizer;
import is2.transitionS2a.Parser;

public class Postagger extends Processor {

	private Map<String, Lemmatizer> ldetectors = new HashMap<String, Lemmatizer>();
	private Map<String, Parser> pdetectors = new HashMap<String, Parser>();

	protected void processView(JCas view) throws Exception {
		if (!ldetectors.containsKey(view.getDocumentLanguage()) || !pdetectors.containsKey(view.getDocumentLanguage()))
			return;

		for (Sentence sentence : JCasUtil.select(view, Sentence.class)) {
			List<Token> tokens = JCasUtil.selectCovered(Token.class, sentence);
			List<String> strings = new ArrayList<String>();

			strings.add("<root>");
			tokens.forEach(e -> strings.add(e.getCoveredText()));

			SentenceData09 data = new SentenceData09();
			data.init(strings.toArray(new String[strings.size()]));
			data = ldetectors.get(view.getDocumentLanguage()).apply(data);
			data = pdetectors.get(view.getDocumentLanguage()).apply(data);

			for (int i = 0; i < data.length(); i++) {
				FSArray feats;
				Token token = tokens.get(i);

				POSTag postag = new POSTag(view);
				postag.setComponentId("Matetools.Postagger");
				postag.setBegin(token.getBegin());
				postag.setEnd(token.getEnd());
				postag.setValue(data.plabels[i]);

				if (token.getPosTag() == null) {
					feats = new FSArray(view, 1);
					feats.set(0, postag);
				} else {
					feats = new FSArray(view, token.getPosTag().size() + 1);
					feats.copyFromArray(token.getPosTag().toArray(), 0, 0, token.getPosTag().size());
					feats.set(token.getPosTag().size(), postag);
				}

				token.setPosTag(feats);
				postag.addToIndexes();

				System.out.println("Matetools.Postagger: " + token.getCoveredText() + "/" + postag.getValue());
			}
		}
	}

	protected void loadDetector(String lang) throws Exception {
		if (ldetectors.containsKey(lang) && pdetectors.containsKey(lang))
			return;

		File lmodel = new File("resources/matetools/" + lang + "-lemma.mdl");
		File pmodel = new File("resources/matetools/" + lang + "-tagging.mdl");

		if (!lmodel.exists() || !pmodel.exists())
			System.out.println("Matetools.Postagger: No model for language '" + lang + "' found, aborting!");
		else {
			ldetectors.put(lang, new Lemmatizer(lmodel.getPath()));
			pdetectors.put(lang, new Parser(pmodel.getPath()));
		}
	}
}
