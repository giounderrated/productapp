package com.gcode.productapp.users.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.gcode.productapp.users.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
	boolean userExistsWithUsername(String username);
	boolean userExistsWithEmail(String email);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	boolean insertUser(User user);
	boolean deleteUser(long userId);
	UserDetails loadUserByUsername(String username);
}
