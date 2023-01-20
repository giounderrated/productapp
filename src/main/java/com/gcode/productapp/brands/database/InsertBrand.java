package com.gcode.productapp.brands.database;

import com.gcode.productapp.brands.domain.Brand;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertBrand {
    private static final String QUERY = "INSERT INTO crud.brands (name) VALUES(?)";
	private final JdbcTemplate template;

    public static InsertBrand create(final JdbcTemplate template){
        return new InsertBrand(template);
    }

    private InsertBrand(final JdbcTemplate template){
        this.template = template;
    }

    public boolean insert(final Brand brand){
        final int results = template.update(
            QUERY,
            brand.getName()
        );
        return results>=1;
    }
}
