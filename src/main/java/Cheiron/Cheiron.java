package Cheiron;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import DnfWrapper.PrometheusXMLBuilder;
import org.apache.uima.jcas.JCas;

import Cheiron.Datasource.Datasource;
import Cheiron.Datatarget.Datatarget;
import Cheiron.Pipeline.Pipeline;

public class Cheiron {

	public static Properties properties = new Properties();
	public static String configfile = new String();
	public static String writelog = new String();

	public static List<Datasource> Datasource = new ArrayList<Datasource>();
	public static List<Pipeline> Pipeline = new ArrayList<Pipeline>();
	public static List<Datatarget> Datatarget = new ArrayList<Datatarget>();

	public static void main(String[] params) throws Exception {

		readConfig(params);
		readParams(params);
		loadConfig();

		if (writelog.equals("false")) {
			OutputStream devnull = new OutputStream() {
				@Override
				public void write(int b) {
				}
			};

			System.setOut(new PrintStream(devnull));
			System.setErr(new PrintStream(devnull));
		} else if (!writelog.equals("true")) {
			OutputStream logfile = new FileOutputStream(Paths.get(writelog).toFile());

			System.setOut(new PrintStream(logfile));
			System.setErr(new PrintStream(logfile));
		}

		run();

	}




	public static void findEntitys(String text) throws Exception{
		PrometheusXMLBuilder pxml = new PrometheusXMLBuilder();
		pxml.buildXML(text);

		String[] para={};
		readConfig(para);
		readParams(para);
		loadConfig();

		if (writelog.equals("false")) {
			OutputStream devnull = new OutputStream() {
				@Override
				public void write(int b) {
				}
			};

			System.setOut(new PrintStream(devnull));
			System.setErr(new PrintStream(devnull));
		} else if (!writelog.equals("true")) {
			OutputStream logfile = new FileOutputStream(Paths.get(writelog).toFile());

			System.setOut(new PrintStream(logfile));
			System.setErr(new PrintStream(logfile));
		}

		run();


	}



	private static void readConfig(String[] params) throws Exception {
		for (int i = 0; i < params.length; i++)
			if (params[i].contains("Cheiron.configfile="))
				configfile = params[i].split("=")[1];

		File file = configfile.isEmpty() ? Paths.get("cheiron.props").toFile() : new File(configfile);

		if (!file.exists())
			throw new FileNotFoundException(file.getAbsolutePath());

		configfile = file.getPath();
		properties.load(new FileInputStream(file));
	}

	private static void readParams(String[] params) throws Exception {
		for (int i = 0; i < params.length; i++) {
			if (!params[i].contains("="))
				continue;

			String[] param = params[i].split("=");
			properties.setProperty(param[0], param[1]);
		}
	}

	private static void loadConfig() throws Exception {
		System.out.println(properties.toString());
		Map<String, Map<String, String>> tree = new HashMap<String, Map<String, String>>();

		for (Entry<Object, Object> e : properties.entrySet()) {
			String obj = e.getKey().toString().substring(0, e.getKey().toString().lastIndexOf("."));
			String key = e.getKey().toString().substring(e.getKey().toString().lastIndexOf(".") + 1);
			String val = e.getValue().toString();

			if (!tree.containsKey(obj))
				tree.put(obj, new HashMap<String, String>());

			tree.get(obj).put(key, val);
		}

		for (Entry<String, String> leaf : tree.get(MethodHandles.lookup().lookupClass().getName()).entrySet()){
			set(leaf.getKey(), leaf.getValue());
		}

		for (Entry<String, Map<String, String>> branch : tree.entrySet()) {
			if (branch.getKey().equals(MethodHandles.lookup().lookupClass().getName()))
				continue;

			if (!knows(branch.getKey()))
				continue;

			Object obj = Class.forName(branch.getKey()).newInstance();
			String sup = branch.getKey().toString().substring(0, branch.getKey().toString().lastIndexOf("."));
			Method call = obj.getClass().getMethod("set", String.class, Object.class);
			String field = sup.substring(sup.lastIndexOf(".") + 1);

			for (Entry<String, String> leaf : branch.getValue().entrySet())
				if (!leaf.getKey().equals("run"))
					call.invoke(obj, leaf.getKey(), leaf.getValue());

			if (branch.getValue().containsKey("run") && branch.getValue().get("run").equals("true")) {
				if (!knowsField(MethodHandles.lookup().lookupClass().getName(), field))
					continue;

				Object list = get(field);
				((List<Object>) list).add(obj);
				set(field, list);
			}
		}
	}

	private static void run() throws Exception {
		if (Pipeline.isEmpty())
			return;

		if (Pipeline.size() > 1) {
			Pipeline.sort((p1, p2) -> {
				int one = 0;
				int two = 0;

				try {
					one = Integer.parseInt((String) p1.get("pos"));
					two = Integer.parseInt((String) p2.get("pos"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				return one - two;
			});
		}

		Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
		Map<String, JCas> cases = new HashMap<String, JCas>();

		for (Datasource source : Datasource)
			data.putAll(source.getData());

		cases = Pipeline.get(0).convert(data);

		for (Pipeline pipe : Pipeline)
			cases.putAll(pipe.process(cases));

		for (Datatarget target : Datatarget)
			target.write(cases);
	}

	protected static Object get(String field) throws Exception {
		Class<?> cheiron = MethodHandles.lookup().lookupClass();
		return cheiron.getDeclaredField(field).get(null);
	}

	protected static void set(String field, Object value) throws Exception {
		Class<?> cheiron = MethodHandles.lookup().lookupClass();
		cheiron.getDeclaredField(field).set(cheiron, value);
	}

	protected static boolean knows(String cname) {
		try {
			Class.forName(cname);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected static boolean knowsMethod(String cname, String method) {
		try {
			Class.forName(cname).getMethod(method);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected static boolean knowsField(String cname, String field) {
		try {
			Class.forName(cname).getDeclaredField(field);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
