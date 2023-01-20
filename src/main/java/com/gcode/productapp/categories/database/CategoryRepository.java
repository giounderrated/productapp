package com.gcode.productapp.categories.database;

import java.util.List;

import com.gcode.productapp.categories.domain.Category;

public interface CategoryRepository {
    List<Category> getAllCategories();
    boolean insertCategory(Category category);
    Category getCategoryById(long id);
    boolean categoryExists(long id);
    boolean updateCategory(Category category);
    boolean deleteCategory(long id);
}
