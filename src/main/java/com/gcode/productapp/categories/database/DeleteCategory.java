package com.gcode.productapp.categories.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class DeleteCategory {
    private static final String QUERY = "DELETE FROM crud.categories WHERE id = ?";
	private final JdbcTemplate template;

    public static final DeleteCategory create(JdbcTemplate template){
        return new DeleteCategory(template);
    }
    
    private DeleteCategory(JdbcTemplate template) {
        this.template = template;
    }
    
    public boolean withId(long id){
        final int results = template.update(
			QUERY,
			id
		);
		return results>=1;
    }

}
