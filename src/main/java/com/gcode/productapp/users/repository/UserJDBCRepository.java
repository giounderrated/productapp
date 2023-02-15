package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.User;
import com.gcode.productapp.users.domain.UserNotAllDetails;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserJDBCRepository implements UserRepository{
	private final JdbcTemplate template;

	public static UserRepository create(final JdbcTemplate template) {
		return new UserJDBCRepository(template);
	}

	private UserJDBCRepository(final JdbcTemplate template){
		this.template = template;
	}

	@Override
	public List<UserNotAllDetails> getAllUsers() {
		GetAllUsers getAll = GetAllUsers.create(template);
		return getAll.execute();
	}

	@Override
	public boolean userExistsWithEmail(String email) {
		UserExistsWithEmail userExistsWithEmail = UserExistsWithEmail.create(template);
		return userExistsWithEmail.exists(email);
	}

	@Override
	public User getUserByUsername(String username) {
		GetUserByUsername getUserByUsername = GetUserByUsername.create(template);
		return getUserByUsername.withUsername(username);
	}

	@Override
	public User getUserByEmail(String email) {
		GetUserByEmail getUserByEmail = GetUserByEmail.create(template);
		return getUserByEmail.withEmail(email);
	}

	@Override
	public boolean userExistsWithUsername(String username) {
		UserExistWithUsername exists = UserExistWithUsername.create(template);
		return exists.exists(username);
	}

	@Override
	public boolean userExistsWithId(long id) {
		UserExistsWidhId existsWidhId = UserExistsWidhId.create(template);
		return existsWidhId.exists(id);

	}

	@Override
	public User getUserById(long id) {
		GetUserById getUser = GetUserById.create(template);
		return getUser.withId(id);
	}

	@Override
	public boolean insertUser(User user) {
		InsertUser insert = InsertUser.create(template);
		return insert.insert(user);
	}

	@Override
	public boolean deleteUser(long userId) {
		DeleteUser delete = DeleteUser.create(template);
		return delete.withId(userId);
	}
}
