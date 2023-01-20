package com.gcode.productapp.users.repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class DeleteUser {
	private static final String QUERY = "DELETE FROM crud.users WHERE id = ?";
	private final JdbcTemplate template;

	public static final DeleteUser create(final JdbcTemplate template) {
		return new DeleteUser(template);
	}

	private DeleteUser(final JdbcTemplate template) {
		this.template = template;
	}

	public boolean withId(long id) {
		final int results = template.update(
				QUERY,
				id
		);
		return results >= 1;
	}
}
