package com.gcode.productapp.products.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class DeleteProduct {
	private static final String QUERY = "DELETE FROM crud.products WHERE id = ?";

	private final JdbcTemplate template;

	public static DeleteProduct create(final JdbcTemplate template){
		return new DeleteProduct(template);
	}

	private DeleteProduct(JdbcTemplate template){
		this.template = template;
	}

	public boolean withId(long id){
		final int results = template.update(
			QUERY,
			id
		);
		return results>=1;
	}
}
