package Cheiron.Processor.Stanford;

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
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Postagger extends Processor {

	private Map<String, MaxentTagger> detectors = new HashMap<String, MaxentTagger>();

	@Override
	protected void processView(JCas view) throws Exception {
		if (!detectors.containsKey(view.getDocumentLanguage()))
			return;

		Token token = null;
		List<Word> tokens = null;
		List<Token> tokenList = null;
		POSTag postag = null;
		List<TaggedWord> postags = null;
		FSArray tokenArray = null;

		for (Sentence sentence : JCasUtil.select(view, Sentence.class)) {
			tokenList = new ArrayList<Token>();
			tokens = new ArrayList<Word>();

			for (Token t : JCasUtil.select(view, Token.class))
				if (sentence.getBegin() <= t.getBegin() && sentence.getEnd() >= t.getEnd())
					tokenList.add(t);

			for (Token t : tokenList)
				tokens.add(new Word(t.getCoveredText()));

			postags = detectors.get(view.getDocumentLanguage()).tagSentence(tokens);

			for (int i = 0; i < postags.size(); i++) {
				token = (Token) tokenList.get(i);

				postag = new POSTag(view);
				postag.setComponentId("Stanford.Postagger");
				postag.setBegin(token.getBegin());
				postag.setEnd(token.getEnd());
				postag.setValue(postags.get(i).tag());
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

				System.out.println("Stanford.Postagger: " + token.getCoveredText() + "/" + postag.getValue());
			}
		}
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		if (detectors.containsKey(lang))
			return;

		File model = new File("resources/stanford/" + lang + ".tagger");

		if (!model.exists())
			System.out.println("Stanford.Postagger: No model for language '" + lang + "' found, aborting!");
		else
			detectors.put(lang, new MaxentTagger(model.getPath()));
	}

}
