package com.gcode.productapp.products.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class GetImagesFromProduct {
    private static final String QUERY = "SELECT * FROM crud.product_images where product_id = ?";
	private final JdbcTemplate template;

    public static final GetImagesFromProduct create(JdbcTemplate template){
        return new GetImagesFromProduct(template);
    }

    private GetImagesFromProduct(JdbcTemplate template){
        this.template = template;
    }

    public List<String> withId(long id){
        final List<String> images = new ArrayList<>(5);
		final List<Map<String,Object>> rows = template.queryForList(
				QUERY,
                id
		);
        try {
            for (final Map<String, Object> row : rows ){
                final String image = row.get("image_source").toString();
                images.add(image);
            }
            return images;
        } catch (NullPointerException e) {
            return Collections.<String>emptyList();
        }
    }
}
