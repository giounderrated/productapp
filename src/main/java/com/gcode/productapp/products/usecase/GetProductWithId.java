package com.gcode.productapp.products.usecase;


import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductRepository;
import com.gcode.productapp.products.domain.ProductInfo;

public class GetProductWithId implements UseCase<String, ProductInfo> {
	private final ProductRepository repository;
	private long productId;

	public static final GetProductWithId create(ProductRepository repository) {
		return new GetProductWithId(repository);
	}

	public GetProductWithId(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public ProductInfo execute(String productId) {
		long id = Long.parseLong(productId);
		setIdProduct(id);
		verifyIfProductExists();
		return tryToGetProductFromDB();
	}

	private void setIdProduct(final long productId){
		this.productId = productId;
	}

	private ProductInfo tryToGetProductFromDB() {
		 ProductInfo product = repository.getProduct(productId);
		 if(product==null){
		 	throw new IllegalArgumentException(getFailureMessage(productId));
		 }
		 return product;
	}

	private String getFailureMessage(long productId) {
		return String.format("Impossible to get product with id: %d", productId);
	}


	private void verifyIfProductExists() {
		final boolean exists = repository.productExistsWithId(productId);
		if(!exists){
			throw new IllegalArgumentException(getNonExistentProductMessage(productId));
		}
	}

	private String getNonExistentProductMessage(long productId) {
		return String.format("Product with id %d does not exists", productId);
	}
}
