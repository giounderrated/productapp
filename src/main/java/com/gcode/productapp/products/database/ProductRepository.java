package com.gcode.productapp.products.database;

import com.gcode.productapp.products.domain.Product;
import com.gcode.productapp.products.domain.ProductInfo;

import java.util.List;

public interface ProductRepository {
	boolean insertProduct(Product product);
	List<ProductInfo> getAllProducts();
	boolean productExistsWithId(long productId);
	ProductInfo getProduct(long productId);
	boolean updateProduct(Product product);
	boolean deleteProduct(long productId);
	boolean insertImagesOfProduct(long id, List<String> images);
}
