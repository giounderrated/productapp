package com.gcode.productapp.brands.database;

import com.gcode.productapp.brands.domain.Brand;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateBrand{

    private static final String QUERY = "UPDATE crud.brands SET name=? WHERE id = ?";
	private final JdbcTemplate template;

    public static final UpdateBrand create(final JdbcTemplate template){
        return new UpdateBrand(template);
    }

    private UpdateBrand(final JdbcTemplate template){
        this.template = template;
    }
    
    public boolean update(final Brand brand){
		final int results = template.update(
			QUERY,
            brand.getName(),
			brand.getId()
		);
		return results>=1;
	}
}
