package com.gcode.productapp.products.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class ProductExistsWithId {

	private static final String QUERY = "SELECT COUNT(*) FROM crud.products where id = ?";
	private final JdbcTemplate template;

	public static final ProductExistsWithId create(JdbcTemplate template) {
		return new ProductExistsWithId(template);
	}
	private ProductExistsWithId(JdbcTemplate template){
		this.template = template;
	}

	public boolean exists(final long id){
		final Integer results = template.queryForObject(
			QUERY,
			Integer.class,
			id
		);
		return results>=1;
	}

}
