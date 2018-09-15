package Cheiron.Processor;

import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

public abstract class Processor extends JCasAnnotator_ImplBase {

	protected abstract void processView(JCas view) throws Exception;

	protected abstract void loadDetector(String lang) throws Exception;

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		JCas view = null;
		Iterator<JCas> it = null;

		try {
			it = cas.getViewIterator();
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (it.hasNext()) {
			view = it.next();

			if (view.getViewName().equals("_InitialView"))
				continue;

			try {
				loadDetector(view.getDocumentLanguage());
				processView(view);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Object get(String field) throws Exception {
		return this.getClass().getDeclaredField(field).get(this);
	}

	public void set(String field, Object value) throws Exception {
		this.getClass().getDeclaredField(field).setAccessible(true);
		this.getClass().getDeclaredField(field).set(this, value);
	}

}
