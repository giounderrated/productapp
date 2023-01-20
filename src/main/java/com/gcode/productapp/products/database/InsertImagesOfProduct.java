package com.gcode.productapp.products.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class InsertImagesOfProduct  {

    private static final String QUERY = "INSERT INTO crud.product_images (image_source, product_id) VALUES(?, ?)";
    private final JdbcTemplate template;

    public static final InsertImagesOfProduct create(JdbcTemplate template){
        return new InsertImagesOfProduct(template);
    }

    private InsertImagesOfProduct(JdbcTemplate template){
        this.template = template;
    }
    
    public boolean insert(long id, List<String> images){
        List<Object[]> batchArgsList = new ArrayList<Object[]>();
        for(String image:images){
            Object[] objectArray = { image,id};
            batchArgsList.add(objectArray);
        }
        final int results = template.batchUpdate(
			QUERY,
            batchArgsList
		).length;

        return results>=1;
    }
}
