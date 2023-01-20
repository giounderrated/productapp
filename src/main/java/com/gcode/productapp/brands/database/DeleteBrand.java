package com.gcode.productapp.brands.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class DeleteBrand {
	private static final String QUERY = "DELETE FROM crud.brands WHERE id = ?";
	private final JdbcTemplate template;

    public static DeleteBrand create(final JdbcTemplate template){
        return new DeleteBrand(template);
    }

    private DeleteBrand(final JdbcTemplate template){
        this.template =template;
    }

    public boolean withId(long id){
        final int results = template.update(
			QUERY,
			id
		);
		return results>=1;
    }
    
}
