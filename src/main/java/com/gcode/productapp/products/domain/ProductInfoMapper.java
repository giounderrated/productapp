package com.gcode.productapp.products.domain;

import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.brands.domain.BrandImpl;
import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.categories.domain.CategoryImpl;
import com.gcode.productapp.util.Mapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gcode.productapp.util.JsonUtils.*;


public class ProductInfoMapper implements Mapper<String, ProductInfo> {
	private static final String[] COLUMNS = {
			"title",
			"description",
			"price",
			"discountPercentage",
			"rating",
			"stock",
			"brand_id",
			"category_id",
			"thumbnail",
			"images"
	};

	public static Mapper<String, ProductInfo> create() {
		return new ProductInfoMapper();
	}

	private ProductInfoMapper() {
	}

	@Override
	public ProductInfo from(String json) {
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
		final Brand brand = getBrand(getLongFromJsonObject(jsonObject, "brand_id"));
		final Category category = getCategory(getLongFromJsonObject(jsonObject, "category_id"));
		final List<String> images = getImages(jsonObject);
		return ProductInfoImpl.builder()
				.id(id)
				.title(title)
				.description(description)
				.price(price)
				.discountPercentage(discountPercentage)
				.rating(rating)
				.stock(stock)
				.brand(brand)
				.category(category)
				.thumbnail(thumbnail)
				.images(images)
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

	private Brand getBrand(long brand_id) {
		return BrandImpl.builder()
				.id(brand_id)
				.name("")
				.build();
	}

	private Category getCategory(long category_id) {
		return CategoryImpl.builder()
				.id(category_id)
				.name("")
				.description("")
				.build();
	}

	private static List<String> getImages(final JsonObject jsonObject) {
		List<String> images = new ArrayList<>();

		try {
			JsonArray imagesObject = getJsonArrayFromJsonObject(jsonObject, "images");
			for (JsonElement jsonElement : imagesObject) {
				images.add(jsonElement.getAsString());
			}
		} catch (final NullPointerException e) {
			images = Collections.emptyList();
		}
		return images;
	}

	private String getEmptyMessage() {
		return "Request body cannot be empty or null";
	}
	// private static Brand getBrand(final JsonObject jsonObject){
	// JsonObject brandObject= jsonObject.getAsJsonObject("brand");
	// long id = getLongFromJsonObject(brandObject, "id");
	// String name = getStringFromJsonObject(brandObject,"name");

	// return BrandImpl.builder()
	// .id(id)
	// .name(name)
	// .build();
	// }
}
