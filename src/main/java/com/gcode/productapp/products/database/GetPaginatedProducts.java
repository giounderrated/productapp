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

public class GetPaginatedProducts {
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
			group by p.id,b.id,c.id
				""";

	private final JdbcTemplate template;

	public static GetPaginatedProducts create(final JdbcTemplate template) {
		return new GetPaginatedProducts(template);
	}

	private GetPaginatedProducts(final JdbcTemplate template) {
		this.template = template;
	}

	public List<ProductInfo> execute(int limit, int offset) {
		String query = QUERY + "limit " + limit + " offset " + offset;
		List<ProductInfo> list = template.query(query, new ResultSetExtractor<>() {
			@Override
			public List<ProductInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ProductInfo> products = new ArrayList<>(10);

				while (rs.next()) {
					final long id = rs.getLong("id");
					final String title = rs.getString("title");
					final String description = rs.getString("product_description");
					final long price = rs.getLong("price");
					final int discountPercentage = rs.getInt("discountPercentage");
					final int rating = rs.getInt("rating");
					final int stock = rs.getInt("stock");
					final String thumbnail = rs.getString("thumbnail");
					final Brand brand = BrandImpl.builder().
							id(rs.getLong("brand_id")).
							name(rs.getString("brand_name"))
							.build();
					final Category category = CategoryImpl.builder()
							.id(rs.getLong("category_id"))
							.name(rs.getString("category_name"))
							.description(rs.getString("category_description"))
							.build();
					final List<String> images = getImages(rs.getString("images"));
					ProductInfo product = ProductInfoImpl.builder()
							.id(id)
							.title(title)
							.description(description)
							.price(price)
							.discountPercentage(discountPercentage)
							.rating(rating)
							.stock(stock)
							.thumbnail(thumbnail)
							.brand(brand)
							.category(category)
							.images(images)
							.build();
					products.add(product);
				}
				return products;
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
		});
		return list;
	}


}
