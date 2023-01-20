package com.gcode.productapp.brands.domain;

import com.gcode.productapp.util.Mapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.gcode.productapp.util.JsonUtils.*;


public class BrandMapper implements Mapper<String,Brand> {

    private static final String[] COLUMNS = {
        "name"
    };

    public static Mapper<String, Brand> create(){
		return new BrandMapper();
	}

    @Override
    public Brand from(String json) { 
        if (json.isBlank()) {
			throw new IllegalArgumentException(getEmptyMessage());
		}
        final JsonElement parser = JsonParser.parseString(json);
		final JsonObject jsonObject = parser.getAsJsonObject();
		checkFieldsInJson(jsonObject, COLUMNS);
        final String name = getStringFromJsonObject(jsonObject, "name");
        final long id = getId(jsonObject);

        return BrandImpl.builder()
                .id(id)
                .name(name)
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
