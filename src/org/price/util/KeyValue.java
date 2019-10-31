package org.price.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KeyValue {
	
	private static List<String> keys;
	private static List<Object> values;
	
	public KeyValue(String json) {
		processJson(json);
	}
	
	
	public static void processJson(String json) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
		
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
		
		for (Map.Entry<String, JsonElement> entry: entries) {
		    System.out.println(entry.getKey());
		    keys = new ArrayList<>();
		    values= new ArrayList<>();
		    keys.add(entry.getKey());
		    values.add(entry.getValue());
		}
	}
	
	
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}

}
