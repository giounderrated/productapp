package com.gcode.productapp.brands.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.brands.domain.BrandImpl;
import org.springframework.jdbc.core.JdbcTemplate;

public class GetAllBrands{
    
    private static final String QUERY = "SELECT * FROM crud.brands";
    private final JdbcTemplate template;

    public static final GetAllBrands create(JdbcTemplate template){
        return new GetAllBrands(template);
    }

    private GetAllBrands(JdbcTemplate template){
        this.template = template;
    }

    public List<Brand> execute(){
        final List<Brand> brands = new ArrayList<>();
        final List<Map<String,Object>> rows = template.queryForList(
				QUERY
		);
        for (final Map<String, Object> row : rows ){
			final Brand brand = getBrand(row);
			brands.add(brand);
		}
        return brands;
    }

    private Brand getBrand(Map<String, Object> row) {
        final long id = Long.valueOf((int) row.get("id"));
		final String name = (String) row.get("name");
        return BrandImpl.builder()
                .id(id)
                .name(name)
                .build();
    }

}
