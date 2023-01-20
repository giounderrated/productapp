package com.gcode.productapp.categories.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.categories.database.CategoryRepository;

public class DeleteCategory implements UseCase<Long,String> {

    private final CategoryRepository repository;
    private long categoryId;

    public static final DeleteCategory create(CategoryRepository repository){
        return new DeleteCategory(repository);
    }

    private DeleteCategory(CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public String execute(Long id ) {
        setCategoryId(id);
        verifyIfCategoryExists();
        tryToDeleteCategoryFromDB();
        return getSuccessMessage();
    }

    private void tryToDeleteCategoryFromDB() {
        final boolean result = repository.deleteCategory(categoryId);
        if(!result){
			throw new IllegalArgumentException(getFailureMessage());
		}
    }

    private String getFailureMessage() {
        return String.format("Delete operation for category with id %d FAILED!",categoryId);
    }

    private void setCategoryId(Long id) {
        this.categoryId = id;
    }

    private void verifyIfCategoryExists(){
        final boolean exists = repository.categoryExists(categoryId);
        if(!exists){
            throw new IllegalArgumentException(getNonExistentCategoryMessage());
        }
    }

    private String getNonExistentCategoryMessage() {
        return String.format("Category with id %d does not exists",categoryId);
    }

    private String getSuccessMessage() {
        return String.format("Category with id %d was updated successfully",categoryId);
    }

    
}
