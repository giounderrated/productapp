package com.gcode.productapp.products.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductRepository;

public class DeleteProduct implements UseCase<String,String> {

	private final ProductRepository repository;

	private long productId;

	public static final DeleteProduct create(ProductRepository repository){
		return new DeleteProduct(repository);
	}

	private DeleteProduct(ProductRepository repository){
		this.repository = repository;
	}

	@Override
	public String execute(final String productId){
			long id = Long.valueOf(productId);
			setProductId(id);
			verifyIfProductExists();
			tryToDeleteProductFromDB();
			return getSuccessMessage();
	}

	private void setProductId(final long productId) {
		this.productId = productId;
	}

	private void verifyIfProductExists() {
		boolean exists = repository.productExistsWithId(productId);
		if(!exists){
			throw new IllegalArgumentException(getNonExistentProductMessage());
		}
	}

	private void tryToDeleteProductFromDB() {
		boolean success = repository.deleteProduct(productId);
		if(!success){
			throw new IllegalArgumentException(getFailureMessage());
		}
	}

	private String getFailureMessage() {
		return String.format("Delete operation for product with id %s FAILED!", productId);
	}
	private String getSuccessMessage() {
		return String.format("Product with id %s was deleted successfully", productId);
	}
	private String getNonExistentProductMessage() {
		return String.format("Product with id %s does not exists", productId);
	}


}
