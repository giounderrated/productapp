package com.gcode.productapp.categories.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class CategoryExists {
    private static final String QUERY = "SELECT COUNT(*) FROM crud.categories where id = ?";
	private final JdbcTemplate template;

    public static final CategoryExists create(JdbcTemplate template){
        return new CategoryExists(template);
    }

    private CategoryExists(JdbcTemplate template){
        this.template =template;
    }
    public boolean withId(final long id){
		final Integer results = template.queryForObject(
			QUERY,
			Integer.class,
			id
		);
		return results>=1;
	}

}
