package com.gcode.productapp.products.config;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductJDBCRepository;
import com.gcode.productapp.products.database.ProductRepository;
import com.gcode.productapp.products.domain.Product;
import com.gcode.productapp.products.domain.ProductInfo;
import com.gcode.productapp.products.domain.ProductInfoMapper;
import com.gcode.productapp.products.domain.ProductMapper;

import com.gcode.productapp.products.usecase.*;
import com.gcode.productapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableTransactionManagement
public class ProductConfiguration {
	private ProductRepository repository;
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void postConstruct(){
		final JdbcTemplate template = new JdbcTemplate(dataSource);
		repository = ProductJDBCRepository.create(template);
	}
	@Bean
	public UseCase<Product,String> createProduct(){
		return CreateProduct.create(repository);
	}
	@Bean
	public UseCase<Void, List<ProductInfo>> getAllProducts(){
		return GetAllProducts.create(repository);
	}
	@Bean
	public static Mapper<String, ProductInfo> getProductInfoMapper() {return ProductInfoMapper.create();}
	@Bean
	public static Mapper<String,Product> getProductMapper(){
		return ProductMapper.create();
	}

//	@Bean
//	public static Mapper<String,ProductImages> getProductImagesMapper() {return ProductImagesMapper.create();
//	}
	@Bean
	public UseCase<String,ProductInfo> getProductWithId(){
		return GetProductWithId.create(repository);
	}
	@Bean("Products")
	public UseCase<String, String> deleteProductWithID(){
		return DeleteProduct.create(repository);
	}
	@Bean
	public UseCase<Product,String> updateProduct(){
		return UpdateProduct.create(repository);
	}
//	@Bean
//	public UseCase<ProductImages, String> insertImages(){
//		return InsertImagesOfProduct.create(repository);
//	}

}
