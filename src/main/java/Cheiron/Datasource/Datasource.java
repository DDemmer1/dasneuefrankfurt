package Cheiron.Datasource;

import java.util.Map;

public abstract class Datasource {

	public abstract Map<String, Map<String, String>> getData() throws Exception;

	public Object get(String field) throws Exception {
		return this.getClass().getDeclaredField(field).get(this);
	}

	public void set(String field, Object value) throws Exception {
		this.getClass().getDeclaredField(field).set(this, value);
	}

}
