package com.gcode.productapp.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.gcode.productapp.config.ApiPaths.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers(AUTH_PATH + "/**")
				.permitAll()
				.requestMatchers(HttpMethod.POST, BRANDS_PATH + "/**", PRODUCTS_PATH + "/**", CATEGORIES_PATH + "/**")
				.hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, BRANDS_PATH + "/**", PRODUCTS_PATH + "/**", CATEGORIES_PATH + "/**")
				.hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, BRANDS_PATH + "/**", PRODUCTS_PATH + "/**", CATEGORIES_PATH + "/**")
				.hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, BRANDS_PATH + "/**",CATEGORIES_PATH + "/**", PRODUCTS_PATH + "/**")
				.permitAll()
				.requestMatchers(HttpMethod.GET, USERS_PATH + "/**")
				.authenticated()
				.requestMatchers(HttpMethod.GET,ADMIN_PATH+"/**" )
				.hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
				.accessDeniedHandler(accessDeniedHandler());
		return http.build();

		// http
		// .csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200", "http://localhost:4200/", "http://localhost:3000")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}
}
