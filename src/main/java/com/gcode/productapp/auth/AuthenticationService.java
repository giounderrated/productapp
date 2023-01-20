package com.gcode.productapp.auth;

import com.gcode.productapp.config.security.JwtService;
import com.gcode.productapp.users.domain.Role;
import com.gcode.productapp.users.domain.User;
import com.gcode.productapp.users.domain.UserNotAllDetails;
import com.gcode.productapp.users.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = setUser(request);
		userService.insertUser(user);
		UserNotAllDetails details = UserNotAllDetails.builder()
			.username(user.getUsername())
			.email(user.getEmail())
			.avatar(user.getAvatar())
			.Role(user.getRole())
			.build();
		return new AuthenticationResponse(jwtService.generateToken(user),details);
	}

	private User setUser(RegisterRequest request) {
		return User.builder()
				.id(0)
				.username(request.getUsername())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.ROLE_USER)
				.avatar("-")
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getUsername(),
				request.getPassword()
			)
		);
		var user = userService.getUserByUsername(request.getUsername());
		UserNotAllDetails details = UserNotAllDetails.builder()
			.username(user.getUsername())
			.email(user.getEmail())
			.avatar(user.getAvatar())
			.Role(user.getRole())
			.build();

		return new AuthenticationResponse(jwtService.generateToken(user),details);
	}

}
