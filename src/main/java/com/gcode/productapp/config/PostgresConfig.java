package com.gcode.productapp.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class PostgresConfig {

	final private String driverClassName = "org.postgresql.Driver";
	final private String url = "jdbc:postgresql://localhost:5432/gdb";//jdbc:postgresql://host:port/database
	final private String username = "postgres";
	final private String password = "Ghaexer610";
	final private String schema = "crud";
	final private int poolSize = 2;
	final private String testQuery = "SELECT 1";

	@Bean
	public DataSource getDataSource() {
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setSchema(schema);
		dataSource.setMaximumPoolSize(poolSize);
		dataSource.setPoolName(schema);
		dataSource.setConnectionTestQuery(testQuery);
		return dataSource;
	}

}
