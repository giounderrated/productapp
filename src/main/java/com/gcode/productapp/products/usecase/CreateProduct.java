package com.gcode.productapp.products.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductRepository;
import com.gcode.productapp.products.domain.Product;

public class CreateProduct implements UseCase<Product,String> {
	private final ProductRepository repository;
	private Product product;

	public static UseCase<Product,String> create(final ProductRepository repository){
		return new CreateProduct(repository);
	}
	private CreateProduct(final ProductRepository repository){
		this.repository = repository;
	}
	public String execute(Product product) {
		setProduct(product);
		tryToAddProduct();
		return getSuccessMessage();
	}

	private String getSuccessMessage() {
		return String.format("Product %s has been created correctly",product.getTitle());
	}

	private void tryToAddProduct() {
		final boolean success = repository.insertProduct(product);
		if (!success) {
			throw new RuntimeException("Error at inserting product in database");
		}
	}

	private void setProduct(Product product) {
		this.product = product;
	}
}
