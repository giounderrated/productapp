package com.gcode.productapp.products.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class Count {
	private static final String QUERY = "Select COUNT(*) FROM crud.products";

	private final JdbcTemplate template;

	public static Count create(final JdbcTemplate template) {
		return new Count(template);
	}

	private Count(final JdbcTemplate template) {
		this.template = template;
	}

	public int count() {
		final Integer results = template.queryForObject(
				QUERY,
				Integer.class
				);
		return results;
	}


}
