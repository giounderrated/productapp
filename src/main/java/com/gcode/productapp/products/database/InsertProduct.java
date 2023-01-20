package com.gcode.productapp.products.database;

import com.gcode.productapp.products.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertProduct {
	private static final String QUERY = "INSERT INTO crud.products (title, description, thumbnail, price, discountpercentage, rating, stock, brand_id, category_id)"
	+" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final JdbcTemplate template;

	public static InsertProduct create(final JdbcTemplate template){
		return new InsertProduct(template);
	}

	private InsertProduct(final JdbcTemplate template){
		this.template = template;
	}

	boolean insert(final Product product){
		final int results = template.update(
			QUERY,
			product.getTitle(),
			product.getDescription(),
			product.getThumbnail(),
			product.getPrice(),
			product.getDiscountPercentage(),
			product.getRating(),
			product.getStock(),
			product.getBrandId(),
			product.getCategoryId()
		);
		
		return results>=1;
	}
}
