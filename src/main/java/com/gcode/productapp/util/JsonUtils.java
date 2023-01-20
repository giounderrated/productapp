package com.gcode.productapp.util;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public enum JsonUtils {
	;

	public static String getStringFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsString();
	}

	public static long getLongFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsLong();
	}

	public static float getFloatFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsFloat();
	}

	public static int getIntFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsInt();
	}

	public static byte getByteFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsByte();
	}

	public static boolean getBooleanFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsBoolean();
	}

	public static JsonArray getJsonArrayFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsJsonArray();
	}

	public static JsonObject getJsonObjectFromJsonObject(final JsonObject jsonObject, final String key) {
		return jsonObject.get(key).getAsJsonObject();
	}

	public static void checkFieldsInJson(final JsonObject jsonObject, final String... keys) {
		for (final String key : keys) {

			if (!jsonObject.has(key)) {
				throw new IllegalArgumentException(String.format("Json does not have value %s", key));
			}
		}
	}
}