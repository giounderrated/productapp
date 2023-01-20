package com.gcode.productapp.categories.usecase;

import com.gcode.productapp.categories.database.CategoryRepository;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.categories.domain.Category;

public class UpdateCategory implements UseCase<Category, String>{
    private final CategoryRepository repository;
    private Category category;

    public static final UpdateCategory create(CategoryRepository repository){
        return new UpdateCategory(repository);
    }

    private UpdateCategory(CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public String execute(Category category) {
        setCategory(category);
        verifyIfCategoryExists();
        tryToUpdateCategoryFromDB();
        return getSuccessMessage();
    }

    private String getSuccessMessage() {
        return String.format("Category with id %d was updated successfully", category.getId());
    }

    private void tryToUpdateCategoryFromDB() {
        final boolean result = repository.updateCategory(category);
        if(!result){
			throw new IllegalArgumentException(getFailureMessage());
		};
    }

    private String getFailureMessage() {
        return String.format("Update operation for category with id %s FAILED!", category.getId());
    }

    private void setCategory(Category category) {
        this.category = category;
    }

    private void verifyIfCategoryExists(){
        final long id = category.getId();
        final boolean exists = repository.categoryExists(id);
        if(!exists){
            throw new IllegalArgumentException(getNonExistentCategoryMessage());
        }
    }

    private String getNonExistentCategoryMessage() {
        return String.format("Category with id %d does not exists",category.getId());
    }


    
}
