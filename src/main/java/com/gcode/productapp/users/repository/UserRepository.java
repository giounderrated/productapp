package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.User;
import com.gcode.productapp.users.domain.UserNotAllDetails;

import java.util.List;

public interface UserRepository {
	List<UserNotAllDetails> getAllUsers();
	boolean userExistsWithEmail(String email);
	boolean userExistsWithUsername(String username);
	boolean userExistsWithId(long id);
	User getUserById(long id);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	boolean insertUser(User user);
	boolean deleteUser(long userId);
}
