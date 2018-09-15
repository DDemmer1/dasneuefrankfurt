package Cheiron.Processor.OpenNLP;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;
import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class Tokenizer extends Processor {

	private Map<String, TokenizerME> detectors = new HashMap<String, TokenizerME>();

	@Override
	protected void processView(JCas view) throws Exception {
		if (!detectors.containsKey(view.getDocumentLanguage()))
			return;

		Token token = null;

		for (Sentence sentence : JCasUtil.select(view, Sentence.class)) {
			for (Span span : detectors.get(view.getDocumentLanguage()).tokenizePos(sentence.getCoveredText())) {
				token = new Token(view);
				token.setComponentId("OpenNLP.Tokenizer");
				token.setBegin(sentence.getBegin() + span.getStart());
				token.setEnd(sentence.getBegin() + span.getEnd());
				token.addToIndexes();

				System.out.println("OpenNLP.Tokenizer: " + token.getCoveredText());
			}
		}
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		if (detectors.containsKey(lang))
			return;

		File model = new File("resources/opennlp/" + lang + "-token.bin");

		if (!model.exists())
			System.out.println("OpenNLP.Tokenizer: No model for language '" + lang + "' found, aborting!");
		else
			detectors.put(lang, new TokenizerME(new TokenizerModel(model)));
	}
}
