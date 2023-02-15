package com.gcode.productapp.users.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserNotAllDetails {
	private final long id;
	private final String username;
	private final String email;
	private final String avatar;
	private final Role role;
}
