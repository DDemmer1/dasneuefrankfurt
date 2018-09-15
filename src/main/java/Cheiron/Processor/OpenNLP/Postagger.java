package Cheiron.Processor.OpenNLP;

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
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class Postagger extends Processor {

	private Map<String, POSTaggerME> detectors = new HashMap<String, POSTaggerME>();

	@Override
	protected void processView(JCas view) throws Exception {
		if (!detectors.containsKey(view.getDocumentLanguage()))
			return;

		Token token = null;
		String[] tokens = null;
		List<Token> tokenList = null;
		POSTag postag = null;
		String[] postags = null;
		FSArray tokenArray = null;

		for (Sentence sentence : JCasUtil.select(view, Sentence.class)) {
			tokenList = new ArrayList<Token>();

			for (Token t : JCasUtil.select(view, Token.class))
				if (sentence.getBegin() <= t.getBegin() && sentence.getEnd() >= t.getEnd())
					tokenList.add(t);

			tokens = new String[tokenList.size()];
			for (int i = 0; i < tokens.length; i++)
				tokens[i] = tokenList.get(i).getCoveredText();

			postags = detectors.get(view.getDocumentLanguage()).tag(tokens);

			for (int i = 0; i < postags.length; i++) {
				token = (Token) tokenList.get(i);

				postag = new POSTag(view);
				postag.setComponentId("OpenNLP.Postagger");
				postag.setBegin(token.getBegin());
				postag.setEnd(token.getEnd());
				postag.setValue(postags[i]);
				postag.addToIndexes();

				if (token.getPosTag() == null) {
					tokenArray = new FSArray(view, 1);
					tokenArray.set(0, postag);
				} else {
					tokenArray = new FSArray(view, token.getPosTag().size() + 1);
					tokenArray.copyFromArray(token.getPosTag().toArray(), 0, 0, token.getPosTag().size());
					tokenArray.set(token.getPosTag().size(), postag);
				}

				token.setPosTag(tokenArray);

				System.out.println("OpenNLP.Postagger: " + token.getCoveredText() + "/" + postag.getValue());
			}
		}
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		if (detectors.containsKey(lang))
			return;

		File model = new File("resources/opennlp/" + lang + "-pos-maxent.bin");

		if (!model.exists())
			System.out.println("OpenNLP.Postagger: No model for language '" + lang + "' found, aborting!");
		else
			detectors.put(lang, new POSTaggerME(new POSModel(model)));
	}
}
