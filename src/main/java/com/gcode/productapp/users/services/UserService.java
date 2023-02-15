package com.gcode.productapp.users.services;

import java.util.List;

import com.gcode.productapp.users.domain.UserNotAllDetails;
import org.springframework.security.core.userdetails.UserDetails;

import com.gcode.productapp.users.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<UserNotAllDetails> getAllUsers();
	boolean userExistsWithUsername(String username);
	boolean userExistsWithEmail(String email);
	boolean userExistsWithId(long id);
	User getUserById(long id);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	boolean insertUser(User user);
	boolean deleteUser(long userId);
	UserDetails loadUserByUsername(String username);
}
