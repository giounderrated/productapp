package com.gcode.productapp.brands.config;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.util.Mapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcode.productapp.brands.database.BrandJDBCRepository;
import com.gcode.productapp.brands.database.BrandRepository;
import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.brands.domain.BrandMapper;
import com.gcode.productapp.brands.usecase.CreateBrand;
import com.gcode.productapp.brands.usecase.GetAllBrands;
import com.gcode.productapp.brands.usecase.GetBrandWithId;
import com.gcode.productapp.brands.usecase.UpdateBrand;
import com.gcode.productapp.brands.usecase.DeleteBrand;

import java.util.List;

import javax.sql.DataSource;

@Configuration
public class BrandConfiguration {
    
    private BrandRepository repository;

    @Autowired
	private DataSource dataSource;

    @PostConstruct
	public void postConstruct(){
		final JdbcTemplate template = new JdbcTemplate(dataSource);
		repository = BrandJDBCRepository.create(template);
	}

    @Bean
	public UseCase<Void, List<Brand>> getAllBrands(){
		return GetAllBrands.create(repository);
	}

	@Bean
	public UseCase<Brand,String> createBrand(){
		return CreateBrand.create(repository);
	}

	@Bean
	public UseCase<Long,Brand> getBrand(){
		return GetBrandWithId.create(repository);
	}

	@Bean
	public UseCase<Brand,String> updateBrand(){
		return UpdateBrand.create(repository);
	}

	@Bean(name = "Brands")
	public UseCase<String,String> deleteBrand(){
		return DeleteBrand.create(repository);
	}

	@Bean
	public static Mapper<String,Brand> getBrandMapper(){
		return BrandMapper.create();
	}

}
