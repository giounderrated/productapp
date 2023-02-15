package com.gcode.productapp.users.config;

import com.gcode.productapp.users.repository.UserJDBCRepository;
import com.gcode.productapp.users.repository.UserRepository;
import com.gcode.productapp.users.services.UserService;
import com.gcode.productapp.users.services.UserServiceImpl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class UserConfig {
	private UserRepository repository;
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void postConstruct(){
		final JdbcTemplate template = new JdbcTemplate(dataSource);
		repository = UserJDBCRepository.create(template);
	}

	@Bean
	public UserService getUserService(){
		return UserServiceImpl.create(repository);
	}

}
