package Cheiron.Processor.Tika;

import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.uima.jcas.JCas;

import Cheiron.Processor.Processor;

public class Langdetector extends Processor {

	private LanguageDetector detector = null;

	@Override
	protected void processView(JCas view) throws Exception {
		String lang = detector.detect(view.getDocumentText()).getLanguage();
		view.setDocumentLanguage(lang);

		System.out.println("Tika.Langdetector: " + view.getView("_InitialView").getSofaDataURI() + "/" + lang);
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		if (detector != null)
			return;

		detector = new OptimaizeLangDetector().loadModels();
	}
}
