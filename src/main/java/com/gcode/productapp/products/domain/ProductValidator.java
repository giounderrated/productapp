package com.gcode.productapp.products.domain;


import com.gcode.productapp.util.Validator;

public class ProductValidator {
	private static final int MAX_NAME_LENGTH = 40;
	private static final int MIN_NAME_LENGTH = 3;

	private final Validator<Product> validator;

	public static ProductValidator create(final Product product){
		return new ProductValidator(product);
	}

	private ProductValidator(final Product product){
		validator = Validator.of(product);
	}

	public Product validate(){
		validateName();

		return validator.get();
	}

	private void validateName() {
		validator
				.validate(p -> p.getTitle().isBlank(), "Name cannot be empty")
				.validate(p -> p.getTitle().length() > MAX_NAME_LENGTH, String.format("Product name cannot have more than %d characters",MAX_NAME_LENGTH))
				.validate(p -> p.getTitle().length() > MAX_NAME_LENGTH, String.format("Product name cannot have less than %d characters",MIN_NAME_LENGTH));
	}
}
