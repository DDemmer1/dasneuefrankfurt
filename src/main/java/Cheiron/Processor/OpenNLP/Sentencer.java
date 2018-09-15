package Cheiron.Processor.OpenNLP;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;
import de.julielab.jcore.types.Sentence;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class Sentencer extends Processor {

	private Map<String, SentenceDetectorME> detectors = new HashMap<String, SentenceDetectorME>();

	@Override
	protected void processView(JCas view) throws Exception {
		if (!detectors.containsKey(view.getDocumentLanguage()))
			return;

		Sentence sentence = null;

		for (Span span : detectors.get(view.getDocumentLanguage()).sentPosDetect(view.getDocumentText())) {
			sentence = new Sentence(view);
			sentence.setComponentId("OpenNLP.Sentencer");
			sentence.setBegin(span.getStart());
			sentence.setEnd(span.getEnd());
			sentence.addToIndexes();

			System.out.println("OpenNLP.Sentencer: " + sentence.getCoveredText());
		}
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		if (detectors.containsKey(lang))
			return;

		File model = new File("resources/opennlp/" + lang + "-sent.bin");

		if (!model.exists())
			System.out.println("OpenNLP.Sentencer: No model for language '" + lang + "' found, aborting!");
		else
			detectors.put(lang, new SentenceDetectorME(new SentenceModel(model)));
	}
}
