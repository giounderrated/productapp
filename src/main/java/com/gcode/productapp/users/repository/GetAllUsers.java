package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.Role;
import com.gcode.productapp.users.domain.User;
import com.gcode.productapp.users.domain.UserNotAllDetails;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllUsers {
	public static final String QUERY = "SELECT * FROM crud.users";
	private final JdbcTemplate template;

	public static GetAllUsers create(final JdbcTemplate template){
		return new GetAllUsers(template);
	}

	private GetAllUsers(final JdbcTemplate template){
		this.template = template;
	}

	public List<UserNotAllDetails> execute(){
        final List<UserNotAllDetails> users = new ArrayList<>();
        final List<Map<String,Object>> rows = template.queryForList(
				QUERY
		);
        for (final Map<String, Object> row : rows ){
			final UserNotAllDetails user = getUser(row);
			users.add(user);
		}
        return users;
    }

	private UserNotAllDetails getUser(Map<String, Object> row) {
		 final long id = (long) (int) row.get("id");
		 final String username = (String) row.get("username");
		 final String email =  (String) row.get("email");
		 final String password =  (String) row.get("password");
		 final String avatar =  (String) row.get("avatar_image");
		 final String role = (String) row.get("role");
		 return UserNotAllDetails.builder().id(id).username(username).email(email).role(Role.valueOf(role)).avatar(avatar).build();
	}
}
