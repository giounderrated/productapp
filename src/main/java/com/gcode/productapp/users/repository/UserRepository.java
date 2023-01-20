package com.gcode.productapp.users.repository;

import com.gcode.productapp.users.domain.User;

import java.util.List;

public interface UserRepository {
	List<User> getAllUsers();
	boolean userExistsWithEmail(String email);
	boolean userExistsWithUsername(String username);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	boolean insertUser(User user);
	boolean deleteUser(long userId);
}
