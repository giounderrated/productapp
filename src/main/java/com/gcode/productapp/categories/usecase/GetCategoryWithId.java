package com.gcode.productapp.categories.usecase;

import com.gcode.productapp.categories.database.CategoryRepository;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.categories.domain.Category;

public class GetCategoryWithId implements UseCase<Long,Category> {

    private final CategoryRepository repository;
    private long categoryId;
    public static final UseCase<Long,Category> create(CategoryRepository repository){
        return new GetCategoryWithId(repository);
    }
    
    public GetCategoryWithId(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category execute(Long id) {
        setCategoryId(id);
        verifyIfCategoryExists();
        return tryToGetVategoryFromDB();
    }

    private void setCategoryId(Long id) {
        this.categoryId = id;
    }

    private void verifyIfCategoryExists() {
        final boolean exists = repository.categoryExists(categoryId);
        if(!exists){
			throw new IllegalArgumentException(getNonExistentCategoryMessage(categoryId));
		}
    }

    private Category tryToGetVategoryFromDB() {
        final Category category = repository.getCategoryById(categoryId);
        if(category==null){
            throw new IllegalArgumentException(getFailureMessage(categoryId));
        }
        return category;

    }

    private String getFailureMessage(long categoryId) {
		return String.format("Impossible to get category with id: %d", categoryId);
	}

    private String getNonExistentCategoryMessage(long categoryId) {
        return String.format("Category with id %d does not exists", categoryId);
    }



    
}
