package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class InsertUser {
	private static final String QUERY = "INSERT INTO crud.users (username, email, password, avatar_image, role) VALUES(?,?,?,?,?)";
	private final JdbcTemplate template;

	public static InsertUser create(final JdbcTemplate template) {
		return new InsertUser(template);
	}

	private InsertUser(final JdbcTemplate template) {
		this.template = template;
	}

	public boolean insert(final User user) {
		final int results = template.update(
				QUERY,
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				user.getAvatar(),
				user.getRole().toString()
				);

		return results >= 1;
	}

}
