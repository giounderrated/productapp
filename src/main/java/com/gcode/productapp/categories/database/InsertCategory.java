package com.gcode.productapp.categories.database;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gcode.productapp.categories.domain.Category;

public class InsertCategory {
    private static final String QUERY = "INSERT INTO crud.categories (name,description) VALUES(?,?)";
	private final JdbcTemplate template;

    public static final InsertCategory create(JdbcTemplate template){
        return new InsertCategory(template);
    }

    private InsertCategory(JdbcTemplate template){
        this.template =template;
    }

    public boolean insert(final Category category){
        final int results = template.update(
            QUERY,
            category.getName(),
            category.getDescription()
        );
        return results>=1;
    }

}
