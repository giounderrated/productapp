package com.gcode.productapp.products.database;


import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.brands.domain.BrandImpl;
import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.categories.domain.CategoryImpl;
import com.gcode.productapp.products.domain.ProductInfo;
import com.gcode.productapp.products.domain.ProductInfoImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetProductWithId {
	private static final String QUERY = """
			select p.id,
			p.title,
			p.description as "product_description",
			p.thumbnail,
			p.price,
			p.discountpercentage,
			p.rating,
			p.stock,
			p.brand_id,
			p.category_id,
			b.id as "brand_id",
			b."name" as "brand_name",
			c.id as "category_id",
			c."name" as "category_name",
			c.description as "category_description",
			json_agg(pimg.*) as images
			from crud.products p 
			left join crud.brands b on b.id = p.brand_id
			left join crud.categories c on c.id = p.category_id 
			left join crud.product_images pimg on pimg.product_id = p.id
			where p.id = ?
			group by p.id,b.id,c.id
				""";
	private final JdbcTemplate template;

	public static final GetProductWithId create(JdbcTemplate template) {
		return new GetProductWithId(template);
	}

	private GetProductWithId(JdbcTemplate template) {
		this.template = template;
	}

	public ProductInfo withId(Long id) {
		final Map<String, Object> row = template.queryForMap(
				QUERY,
				id
		);
		return getProduct(row);
	}

	private ProductInfo getProduct(Map<String, Object> row) {
		final long id = Long.valueOf((int) row.get("id"));
		final String title = (String) row.get("title");
		final String description = (String) row.get("product_description");
		final long price = Long.valueOf((int)row.get("price"));
		final int discountPercentage = (int) row.get("discountPercentage");
		final int rating = (int) row.get("rating");
		final int stock = (int) row.get("stock");
		final String thumbnail = (String) row.get("thumbnail");

		final long brand_id = Long.valueOf((int) row.get("brand_id"));
		final String brand_name = String.valueOf(row.get("brand_name"));
		final long category_id = Long.valueOf((int) row.get("category_id"));
		final String category_name = String.valueOf(row.get("category_name"));
		final String category_description = String.valueOf(row.get("category_description"));
		final String imagesAsJson = String.valueOf(row.get("images"));
		List<String> images = getImages(imagesAsJson);

		final Brand brand = BrandImpl.builder()
				.id(brand_id)
				.name(brand_name)
				.build();
		final Category category = CategoryImpl.builder()
				.id(category_id)
				.name(category_name)
				.description(category_description)
				.build();

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

	private List<String> getImages(String json) {
		if (json.contains("null")) return Collections.emptyList();
		JsonElement elements = JsonParser.parseString(json);
		JsonArray array = elements.getAsJsonArray();
		List<String> images = new ArrayList<>(5);
		for (int i = 0; i < array.size(); i++) {
			JsonObject item = array.get(i).getAsJsonObject();
			String image = item.get("image_source").getAsString();
			images.add(image);
		}
		return images;
	}

}
