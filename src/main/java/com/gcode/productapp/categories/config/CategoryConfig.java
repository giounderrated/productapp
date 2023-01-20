package com.gcode.productapp.categories.config;

import java.util.List;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.util.Mapper;
import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcode.productapp.categories.database.CategoryJDBCRepository;
import com.gcode.productapp.categories.database.CategoryRepository;
import com.gcode.productapp.categories.domain.Category;
import com.gcode.productapp.categories.domain.CategoryMapper;
import com.gcode.productapp.categories.usecase.CreateCategory;
import com.gcode.productapp.categories.usecase.DeleteCategory;
import com.gcode.productapp.categories.usecase.GetAllCategories;
import com.gcode.productapp.categories.usecase.GetCategoryWithId;
import com.gcode.productapp.categories.usecase.UpdateCategory;



@Configuration
public class CategoryConfig {
    private CategoryRepository repository;
    @Autowired
	private DataSource dataSource;

    @PostConstruct
	public void postConstruct(){
		final JdbcTemplate template = new JdbcTemplate(dataSource);
		repository = CategoryJDBCRepository.create(template);
	}

    @Bean
    public UseCase<Void,List<Category>> getCategories(){
        return GetAllCategories.create(repository);
    }

    @Bean
    public UseCase<Category,String> createCategory(){
        return CreateCategory.create(repository);
    }

    @Bean
    public UseCase<Long,Category> getCategoryById(){
        return GetCategoryWithId.create(repository);
    }

    @Bean 
    public UseCase<Category,String> updateCategory(){
        return UpdateCategory.create(repository);
    }

    @Bean 
    public UseCase<Long,String> deleteCategory(){
        return DeleteCategory.create(repository);
    }

    @Bean
    public Mapper<String,Category> categoryMapper(){
        return CategoryMapper.create();
    }
}
