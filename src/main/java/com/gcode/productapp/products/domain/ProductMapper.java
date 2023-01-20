package com.gcode.productapp.products.domain;

import com.gcode.productapp.util.Mapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.gcode.productapp.util.JsonUtils.*;

public class ProductMapper implements Mapper<String, Product> {
	private static final String[] COLUMNS = {
			"title",
			"description",
			"price",
			"discountPercentage",
			"rating",
			"stock",
			"brand_id",
			"category_id",
			"thumbnail"
	};

	public static Mapper<String,Product> create(){
		return new ProductMapper();
	}

	public Product from(String json) {
		if (json.isBlank()) {
			throw new IllegalArgumentException(getEmptyMessage());
		}
		final JsonElement parser = JsonParser.parseString(json);
		final JsonObject jsonObject = parser.getAsJsonObject();
		checkFieldsInJson(jsonObject, COLUMNS);
		final long id = getId(jsonObject);
		final String title = getStringFromJsonObject(jsonObject, "title");
		final String description = getStringFromJsonObject(jsonObject, "description");
		final long price = getLongFromJsonObject(jsonObject, "price");
		final int discountPercentage = getIntFromJsonObject(jsonObject, "discountPercentage");
		final int rating = getIntFromJsonObject(jsonObject, "rating");
		final int stock = getIntFromJsonObject(jsonObject, "stock");
		final String thumbnail = getStringFromJsonObject(jsonObject, "thumbnail");
		final long brand_id = getLongFromJsonObject(jsonObject,"brand_id");
		final long category_id = getLongFromJsonObject(jsonObject,"category_id");
		return ProductImpl.builder()
				.id(id)
				.title(title)
				.description(description)
				.price(price)
				.discountPercentage(discountPercentage)
				.rating(rating)
				.stock(stock)
				.thumbnail(thumbnail)
				.brandId(brand_id)
				.categoryId(category_id)
				.build();
	}

	private long getId(JsonObject jsonObject) {
		long id;
		try {
			id = getLongFromJsonObject(jsonObject, "id");
		} catch (final NullPointerException e) {
			id = 0;
		}
		return id;
	}

	private String getEmptyMessage() {
		return "Body cannnot be empty";
	}
}
