package com.czhappy.commonindexdemo.jsonparse;

/**
 * @author py
 * @date 2012-7-27
 * @comment
 */
public class JSONUtil {
	public static boolean isJsonObject(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
		} catch (JSONException e) {
			return false;
		}
		return true;
	}

	public static boolean isJsonArray(String jsonString) {
		try {
			JSONArray json = new JSONArray(jsonString);
		} catch (JSONException e) {
			return false;
		}
		return true;
	}
}
