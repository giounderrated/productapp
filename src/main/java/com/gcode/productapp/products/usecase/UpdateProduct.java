package com.gcode.productapp.products.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductRepository;
import com.gcode.productapp.products.domain.Product;

public class UpdateProduct implements UseCase<Product,String> {

	private final ProductRepository repository;
	private Product product;
	public static final UpdateProduct create(ProductRepository repository){
		return new UpdateProduct(repository);
	}

	private UpdateProduct(ProductRepository repository){
		this.repository = repository;
	}

	@Override
	public String execute(Product product) {
		setProduct(product);
		verifyIfProductExists();
		tryToUpdateProductFromDB();
		return getSuccessMessage();
	}

	private void setProduct(Product product) {
		this.product = product;
	}
	private void verifyIfProductExists() {
		final long id = product.getId();
		final boolean exists = repository.productExistsWithId(id);
		if(!exists){
			throw new IllegalArgumentException(getNonExistentProductMessage());
		}
	}

	private void tryToUpdateProductFromDB() {
		final boolean result = repository.updateProduct(product);
		if(!result){
			throw new IllegalArgumentException(getFailureMessage());
		};
	}

	private String getSuccessMessage() {
		return String.format("Product %s was updated successfully", product.getTitle());
	}

	private String getNonExistentProductMessage() {
		return String.format("Product with id %s does not exists", product.getId());
	}

	private String getFailureMessage() {
		return String.format("Update operation for product with id %s FAILED!", product.getId());
	}
}
