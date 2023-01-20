package com.gcode.productapp.categories.controller;

import java.util.List;

import com.gcode.productapp.api.JSend;
import com.gcode.productapp.api.Success;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CategoryController {
    private static final String PATH = "/categories";
	private static final String PATH_WITH_ID = "/categories/{id}";

    @Autowired
    private UseCase<Void,List<Category>> getCategories;

    @Autowired
    private UseCase<Category,String> createCategory;

    @Autowired
    private UseCase<Long,Category> getCategoryById;

    @Autowired
    private UseCase<Category,String> updateCategory;

    @Autowired
    private UseCase<Long,String> deleteCategory;

    @Autowired
    private Mapper<String,Category> categoryMapper;

    @GetMapping(PATH)
    public final JSend<List<Category>> getAllCategories(HttpEntity<String> entity){
        final List<Category> categories = getCategories.execute(null);
        return Success.<List<Category>>builder()
			.data(categories)
			.build();
    }

    @PostMapping(PATH)
    public final JSend<Category> createCategory(HttpEntity<String> entity){
		final String json = entity.getBody();
        final Category category = categoryMapper.from(json);
        final String result = createCategory.execute(category);
        return Success.<Category>builder()
                .message(result)
                .data(category)
                .build();
    }

    @GetMapping(PATH_WITH_ID)
    public final JSend<Category> getCategoryById(@PathVariable final long id){
        final Category category = getCategoryById.execute(id);
        return Success.<Category>builder()
                .data(category)
                .build();
    }

    @PutMapping(PATH)
    public final JSend<Category> updateCategory(final HttpEntity<String> entity){
		final String json = entity.getBody();
        final Category category = categoryMapper.from(json);
        final String result = updateCategory.execute(category);
        return Success.<Category>builder()
				.message(result)
				.data(category)
				.build(); 
    }

    @DeleteMapping(PATH_WITH_ID)
    public final JSend<String> deleteCategory(@PathVariable final long id){
        final String result = deleteCategory.execute(id);
        return Success.<String>builder()
		.message(result)
		.build();
    }

}
