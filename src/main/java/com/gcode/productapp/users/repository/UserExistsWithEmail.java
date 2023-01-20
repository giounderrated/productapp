package com.gcode.productapp.users.repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserExistsWithEmail {
	private static final String QUERY = "SELECT COUNT(*) FROM crud.users where email = ?";
	private final JdbcTemplate template;

	public static final UserExistsWithEmail create(final JdbcTemplate template){
		return new UserExistsWithEmail(template);
	}

	private UserExistsWithEmail(final JdbcTemplate template){
		this.template = template;
	}

	public boolean exists(final String email){
		final Integer results = template.queryForObject(
			QUERY,
			Integer.class,
			email
		);
		return results>=1;
	}
}
