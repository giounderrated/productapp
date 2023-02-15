package com.gcode.productapp.users.repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserExistsWidhId {
	private static final String QUERY = "SELECT COUNT(*) FROM crud.users where id = ?";
	private final JdbcTemplate template;

	public static UserExistsWidhId create(final JdbcTemplate template){
		return new UserExistsWidhId(template);
	}

	private UserExistsWidhId(final JdbcTemplate template){
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
