package Cheiron.Processor.HeidelTime;

import org.apache.uima.jcas.JCas;

import de.unihd.dbs.heideltime.standalone.components.ResultFormatter;

public class ResultBasket implements ResultFormatter {
	
	private JCas cas;

	@Override
	public String format(JCas jcas) throws Exception {
		cas = jcas;
		return null;
	}

	public JCas getCas() {
		return cas;
	}

}
