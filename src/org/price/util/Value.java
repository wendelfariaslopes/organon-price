package org.price.util;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Value {
	
	public static String getValue(String json, String key) {
		JsonObject jo = new Gson().fromJson(json, JsonObject.class);
		
		return jo.get(key).getAsString();
	}
	
	public static String getValue2(String json, String key) {
		JSONObject jo = new JSONObject(json);
		
		return (String) jo.get(key);
	}

}
