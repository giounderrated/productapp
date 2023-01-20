package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.Role;
import com.gcode.productapp.users.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class GetUserByEmail {
	private static final String QUERY = "SELECT * FROM crud.users where username = ?";
	private final JdbcTemplate template;

	public static final GetUserByEmail create(final JdbcTemplate template) {
		return new GetUserByEmail(template);
	}

	private GetUserByEmail(final JdbcTemplate template) {
		this.template = template;
	}

	public User withEmail(String email) {
		final Map<String, Object> row = template.queryForMap(
				QUERY,
				email
		);
		return getUser(row);
	}

	private User getUser(Map<String, Object> row) {
		final long id = (long) (int) row.get("id");
		final String username = (String) row.get("username");
		final String email = (String) row.get("email");
		final String password = (String) row.get("password");
		final String avatar = (String) row.get("avatar_image");
		final String role = (String) row.get("role");
		return User.builder()
				.id(id)
				.username(username)
				.email(email)
				.password(password)
				.role(Role.valueOf(role))
				.avatar(avatar)
				.build();
	}
}

