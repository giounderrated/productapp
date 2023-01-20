package com.gcode.productapp.categories.database;

import java.util.List;

import com.gcode.productapp.categories.domain.Category;
import org.springframework.jdbc.core.JdbcTemplate;

public class CategoryJDBCRepository implements CategoryRepository{

    private final JdbcTemplate template;

    public static final CategoryJDBCRepository create(JdbcTemplate template){
        return new CategoryJDBCRepository(template);
    }

    private CategoryJDBCRepository(JdbcTemplate template) {
        this.template =template;
    }

    

    @Override
    public List<Category> getAllCategories() {
        GetAllCategories getAllCategories = GetAllCategories.create(template);
        return getAllCategories.execute();
    }

    @Override
    public boolean insertCategory(Category category) {
        InsertCategory insert = InsertCategory.create(template);
        return insert.insert(category);
    }

    @Override
    public Category getCategoryById(long id) {
        GetCategoryWithId getCategory = GetCategoryWithId.create(template);
        return getCategory.withId(id);
    }

    @Override
    public boolean categoryExists(long id) {
        CategoryExists exists = CategoryExists.create(template);
        return exists.withId(id);
    }

    @Override
    public boolean updateCategory(Category category) {
        UpdateCategory updateCategory = UpdateCategory.create(template);
        return updateCategory.update(category);
    }

    @Override
    public boolean deleteCategory(long id) {
        DeleteCategory delete = DeleteCategory.create(template);
        return delete.withId(id);
    }

    
}
