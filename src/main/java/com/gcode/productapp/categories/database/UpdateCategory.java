package com.gcode.productapp.categories.database;

import com.gcode.productapp.categories.domain.Category;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateCategory {
    private static final String QUERY = "UPDATE crud.categories SET name=?, description=? WHERE id = ?";
	private final JdbcTemplate template;

    public static final UpdateCategory create(JdbcTemplate template){
        return new UpdateCategory(template);
    }

    private UpdateCategory(JdbcTemplate template) {
        this.template = template;
    }
    public boolean update(final Category category){
		final int results = template.update(
			QUERY,
            category.getName(),
            category.getDescription(),
			category.getId()
		);
		return results>=1;
	}
}
