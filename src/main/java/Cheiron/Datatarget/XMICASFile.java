package Cheiron.Datatarget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.vfs2.FileNotFolderException;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;

public class XMICASFile extends Datatarget {

	protected String ext;
	protected String path;

	@Override
	public void write(Map<String, JCas> data) throws Exception {
		File directory = new File(path);

		if (!directory.isDirectory())
			throw new FileNotFolderException(directory.getAbsolutePath());

		for (Entry<String, JCas> e : data.entrySet()) {
			OutputStream stream = new FileOutputStream(new File(directory, e.getKey() + "." + ext));
			XmiCasSerializer.serialize(e.getValue().getCas(), stream);
		}
	}

}
