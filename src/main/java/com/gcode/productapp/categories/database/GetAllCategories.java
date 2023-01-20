package com.gcode.productapp.categories.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.categories.domain.CategoryImpl;
import org.springframework.jdbc.core.JdbcTemplate;



public class GetAllCategories {
    private static final String QUERY = "SELECT * FROM crud.categories";
	private final JdbcTemplate template;

    public static final GetAllCategories create(JdbcTemplate template){
        return new GetAllCategories(template);
    }

    private GetAllCategories(JdbcTemplate template){
        this.template = template;
    }

    List<Category> execute(){
        final List<Category> categories = new ArrayList<>();
        final List<Map<String,Object>> rows = template.queryForList(
            QUERY
        );
    for (final Map<String, Object> row : rows ){
        final Category category = getCategory(row);
        categories.add(category);
    }
    return categories;
    }

    private Category getCategory(Map<String, Object> row) {
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
