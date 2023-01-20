package com.gcode.productapp.brands.database;

import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.brands.domain.BrandImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public class GetBrandWithId{
    private static final String QUERY = "SELECT * FROM crud.brands where id=?";
	private final JdbcTemplate template;

    public static final GetBrandWithId create(JdbcTemplate template){
       return new GetBrandWithId(template);
    }

    private GetBrandWithId(JdbcTemplate template){
        this.template = template;
    }

    public Brand withId(long id){
        final Map<String, Object> row = template.queryForMap(
			QUERY,
			id
		);
		return getBrand(row);
    } 
    
    private Brand getBrand(Map<String, Object> row){
        final long id = Long.valueOf((int) row.get("id"));
        final String name = (String) row.get("name");

        return BrandImpl.builder()
                        .name(name)
                        .id(id)
                        .build();
    }
    
}  