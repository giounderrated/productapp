package com.gcode.productapp.users.repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserExistWithUsername {
	private static final String QUERY = "SELECT COUNT(*) FROM crud.users where username = ?";
	private final JdbcTemplate template;

	public static final UserExistWithUsername create(final JdbcTemplate template){
		return new UserExistWithUsername(template);
	}

	private UserExistWithUsername(final JdbcTemplate template ){
		this.template = template;
	}

	  public boolean exists(final String username){
		final Integer results = template.queryForObject(
			QUERY,
			Integer.class,
			username
		);
		return results>=1;
	}

}
