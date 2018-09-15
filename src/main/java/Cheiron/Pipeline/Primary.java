package Cheiron.Pipeline;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Custom.Frequencer;
import Cheiron.Processor.HeidelTime.Timetagger;
import Cheiron.Processor.Matetools.Lemmatizer;
import Cheiron.Processor.OpenNLP.Sentencer;
import Cheiron.Processor.OpenNLP.Tokenizer;
import Cheiron.Processor.Stanford.Entityrecognizer;
import Cheiron.Processor.Stanford.Postagger;
import Cheiron.Processor.Tika.Langdetector;

public class Primary extends Pipeline {

	protected String pos = null;

	@Override
	public Map<String, JCas> process(Map<String, JCas> cases) throws Exception {
		Langdetector langdetector = new Langdetector();
		Sentencer sentencer = new Sentencer();
		Tokenizer tokenizer = new Tokenizer();
		Timetagger timetagger = new Timetagger();
		Lemmatizer lemmatizer = new Lemmatizer();
		Postagger postagger = new Postagger();
		Entityrecognizer entityrecognizer = new Entityrecognizer();
		Frequencer frequencer = new Frequencer();

		for (Entry<String, JCas> entry : cases.entrySet()) {
			langdetector.process(entry.getValue());
			sentencer.process(entry.getValue());
			tokenizer.process(entry.getValue());
			timetagger.process(entry.getValue());
			lemmatizer.process(entry.getValue());
			postagger.process(entry.getValue());
			entityrecognizer.process(entry.getValue());
			frequencer.process(entry.getValue());
		}

		return cases;
	}

}
