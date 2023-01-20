package com.gcode.productapp.auth;

import com.gcode.productapp.api.JSend;
import com.gcode.productapp.api.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService service;

	@PostMapping("/register")
	public final JSend<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		AuthenticationResponse data = service.register(request);
		return Success.<AuthenticationResponse>builder()
				.data(data)
				.build();
	}

	@PostMapping("/authenticate")
	public JSend<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		AuthenticationResponse data = service.authenticate(request);
		return Success.<AuthenticationResponse>builder()
				.data(data)
				.build();
	}
}
