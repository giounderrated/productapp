package com.gcode.productapp.categories.domain;

import com.gcode.productapp.util.Mapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.gcode.productapp.util.JsonUtils.*;


public class CategoryMapper implements Mapper<String,Category> {

    private static final String[] COLUMNS = {
        "name",
        "description"
    };

    public static Mapper<String,Category> create(){
        return new CategoryMapper();
    }

    @Override
    public Category from(String json) {
        if (json.isBlank()) {
			throw new IllegalArgumentException(getEmptyMessage());
		}
        final JsonElement parser = JsonParser.parseString(json);
		final JsonObject jsonObject = parser.getAsJsonObject();
		checkFieldsInJson(jsonObject, COLUMNS);
        final long id = getId(jsonObject);
        final String name = getStringFromJsonObject(jsonObject, "name");
        final String description = getStringFromJsonObject(jsonObject, "description");
        return CategoryImpl.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

    private static long getId(final JsonObject jsonObject) {
		long id;
		try {
			id = getLongFromJsonObject(jsonObject, "id");
		} catch (final NullPointerException e) {
			id = 0;
		}
		return id;
	}

    private String getEmptyMessage() {
        return "Request body cannot be empty or null";
    }
    
}
