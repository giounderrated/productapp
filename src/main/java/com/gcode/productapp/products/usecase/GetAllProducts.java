package com.gcode.productapp.products.usecase;


import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductRepository;

import com.gcode.productapp.products.domain.ProductInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class GetAllProducts implements UseCase<Void, List<ProductInfo>> {

	private final ProductRepository repository;

	public static UseCase<Void, List<ProductInfo>> create(ProductRepository repository) {
		return new GetAllProducts(repository);
	}

	public GetAllProducts(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ProductInfo> execute(Void param) {
		final List<ProductInfo> products = repository.getAllProducts();
		products.sort(Comparator.comparing(ProductInfo::getId).thenComparing(ProductInfo::getTitle));
		log.info("Getting a total of {} products.", products.size());
		return products;
	}
}
