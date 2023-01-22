package com.gcode.productapp.config.security;

import com.gcode.productapp.api.AccessDenied;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(AccessDenied.<String>builder().message("Access denied").data("").build());
		response.getWriter().write(json);
	}
}
