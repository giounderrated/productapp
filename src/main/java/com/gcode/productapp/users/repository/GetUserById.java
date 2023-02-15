package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.Role;
import com.gcode.productapp.users.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class GetUserById {
	private static final String QUERY = "SELECT * FROM crud.users where id = ?";
	private final JdbcTemplate template;

	public static GetUserById create(final JdbcTemplate template) {
		return new GetUserById(template);
	}

	private GetUserById(final JdbcTemplate template) {
		this.template = template;
	}

	public User withId(long id) {
		final Map<String, Object> row = template.queryForMap(
				QUERY,
				id
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
