package com.gcode.productapp.categories.usecase;

import java.util.Comparator;
import java.util.List;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.categories.database.CategoryRepository;

public class GetAllCategories implements UseCase<Void,List<Category>> {

    private final CategoryRepository repository;

    public static final UseCase<Void,List<Category>> create(CategoryRepository repository){
        return new GetAllCategories(repository);
    }

    private GetAllCategories(CategoryRepository repository){
        this.repository =repository;
    }

    @Override
    public List<Category> execute(Void param) {
        final List<Category> categories = repository.getAllCategories();
        categories.sort(Comparator.comparing(Category::getName));
        return categories;
    }

    
}
