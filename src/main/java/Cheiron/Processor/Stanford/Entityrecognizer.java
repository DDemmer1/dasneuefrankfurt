package Cheiron.Processor.Stanford;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;
import de.julielab.jcore.types.EntityMention;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

public class Entityrecognizer extends Processor {

	private Map<String, AbstractSequenceClassifier<CoreLabel>> detectors = new HashMap<String, AbstractSequenceClassifier<CoreLabel>>();

	@Override
	protected void processView(JCas view) throws Exception {
		if (!detectors.containsKey(view.getDocumentLanguage()))
			return;

		AbstractSequenceClassifier<CoreLabel> classifier = detectors.get(view.getDocumentLanguage());

		for (Triple<String, Integer, Integer> item : classifier.classifyToCharacterOffsets(view.getDocumentText())) {
			EntityMention entity = new EntityMention(view);
			entity.setBegin(item.second());
			entity.setEnd(item.third());
			entity.setComponentId("Stanford.Entityrecognizer");
			entity.setTextualRepresentation(item.first());
			entity.addToIndexes();

			System.out.println("Stanford.Entityrecognizer: " + entity.getCoveredText() + "/" + item.first());
		}
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		if (detectors.containsKey(lang))
			return;

		File model = new File("resources/stanford/" + lang + "-ner.crf.ser.gz");

		if (!model.exists())
			System.out.println("Stanford.Entityrecognizr: No model for language '" + lang + "' found, aborting!");
		else
			detectors.put(lang, CRFClassifier.getClassifier(model));
	}

}
