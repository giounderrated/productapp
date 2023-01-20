package com.gcode.productapp.brands.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class BrandExistsWithId {
    private static final String QUERY = "SELECT COUNT(*) FROM crud.brands where id = ?";
	private final JdbcTemplate template;

    public static final BrandExistsWithId create(final JdbcTemplate template){
        return new BrandExistsWithId(template);
    }
    
    public BrandExistsWithId(JdbcTemplate template) {
        this.template = template;
    }
    
    public boolean exists(final long id){
		final Integer results = template.queryForObject(
			QUERY,
			Integer.class,
			id
		);
		return results>=1;
	}
}
