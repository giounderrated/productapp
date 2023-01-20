package com.gcode.productapp.auth;

import com.gcode.productapp.users.domain.UserNotAllDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {
    private final String token;
    private final UserNotAllDetails user;
}
