package com.gcode.productapp.categories.database;

import java.util.Map;

import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.categories.domain.CategoryImpl;
import org.springframework.jdbc.core.JdbcTemplate;


public class GetCategoryWithId {
    private static final String QUERY = "SELECT * FROM crud.categories where id=?";
	private final JdbcTemplate template;

    public static final GetCategoryWithId create(JdbcTemplate template){
        return new GetCategoryWithId(template);
    }

    private GetCategoryWithId(JdbcTemplate template){
        this.template = template;
    }

    public Category withId(long id){
        final Map<String, Object> row = template.queryForMap(
			QUERY,
			id
		);
		return getCategory(row);
    } 

    private Category getCategory(Map<String, Object> row){
        final long id = Long.valueOf((int) row.get("id"));
        final String name = (String) row.get("name");
        final String description = (String) row.get("description");
        return CategoryImpl.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }
}
