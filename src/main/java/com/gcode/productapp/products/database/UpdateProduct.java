package com.gcode.productapp.products.database;

import com.gcode.productapp.products.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateProduct {
	private static final String QUERY = "UPDATE crud.products SET title= ?,"+
	" description=?, price=?, discountPercentage=?, rating=?, stock=?, thumbnail=? "+
	"WHERE id = ?";

	private final JdbcTemplate template;

	public static UpdateProduct create(final JdbcTemplate template){
		return new UpdateProduct(template);
	}

	private UpdateProduct(final JdbcTemplate template){
		this.template = template;
	}

	public boolean update(final Product product){
		final int results = template.update(
			QUERY,
			product.getTitle(),
			product.getDescription(),
			product.getPrice(),
			product.getDiscountPercentage(),
			product.getRating(),
			product.getStock(),
			product.getThumbnail(),
			product.getId()
		);
		return results>=1;
	}
}
