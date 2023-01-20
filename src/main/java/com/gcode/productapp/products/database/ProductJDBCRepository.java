package com.gcode.productapp.products.database;

import com.gcode.productapp.products.domain.Product;
import com.gcode.productapp.products.domain.ProductInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductJDBCRepository implements ProductRepository{
	private final JdbcTemplate template;

	public static ProductJDBCRepository create(final JdbcTemplate template){
		return new ProductJDBCRepository(template);
	}
	private ProductJDBCRepository(final JdbcTemplate template){
		this.template =template;
	}

	public boolean insertProduct(Product product) {
		final InsertProduct insertProduct = InsertProduct.create(template);
		return insertProduct.insert(product);
	}

	public List<ProductInfo> getAllProducts() {
		 final GetAllProducts products = GetAllProducts.create(template);
		return products.execute();
	}

	public boolean productExistsWithId(long productId) {
		final ProductExistsWithId exists = ProductExistsWithId.create(template);
		return exists.exists(productId);
	}

	public ProductInfo getProduct(long productId) {
		 final GetProductWithId getProductWithId = GetProductWithId.create(template);
		 return getProductWithId.withId(productId);
	}

	public boolean deleteProduct(long productId) {
		final DeleteProduct deleteProduct = DeleteProduct.create(template);
		return deleteProduct.withId(productId);
	}

	@Override
	public boolean updateProduct(Product product) {
		final UpdateProduct updateProduct = UpdateProduct.create(template);
		return updateProduct.update(product);
	}

	@Override
	public boolean insertImagesOfProduct(long id,List<String> images) {
		final InsertImagesOfProduct insert = InsertImagesOfProduct.create(template);
		return insert.insert(id,images);
	}
	


}
