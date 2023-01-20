package com.gcode.productapp.categories.usecase;

import com.gcode.productapp.categories.database.CategoryRepository;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.categories.domain.Category;

public class CreateCategory implements UseCase<Category,String>{

    private final CategoryRepository repository;
    private Category category;
    
    public static final UseCase<Category,String> create(final CategoryRepository repository){
        return new CreateCategory(repository);
    }
    
    public CreateCategory(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(Category category) {
        setCategory(category);
        tryToAddCategory();
        return getSuccessMessage();
    }

    private void setCategory(Category category) {
        this.category = category;
    }

    private void tryToAddCategory() {
        final boolean success = repository.insertCategory(category);
        if (!success) {
			throw new RuntimeException("Error at inserting Category in database");
		}
    }

    private String getSuccessMessage() {
		return String.format("Category %s has been created correctly",category.getName());
	}
  

    
}
