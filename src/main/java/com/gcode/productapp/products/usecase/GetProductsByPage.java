package com.gcode.productapp.products.usecase;

import com.gcode.productapp.api.Page;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.database.ProductRepository;
import com.gcode.productapp.products.domain.ProductInfo;

import java.util.List;

public class GetProductsByPage implements UseCase<Integer, Page<List<ProductInfo>>> {

	private final ProductRepository repository;
	private final int PAGE_SIZE = 5;

	public static final UseCase<Integer, Page<List<ProductInfo>>> create(final ProductRepository repository) {
		return new GetProductsByPage(repository);
	}

	private GetProductsByPage(final ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public Page<List<ProductInfo>> execute(Integer page) {
		final int offset = (page - 1) * PAGE_SIZE;
		final List<ProductInfo> products = repository.getProductsByPage(PAGE_SIZE, offset);
		final int count = repository.count();
		final double totalPages = Math.ceil((double) count / (double) PAGE_SIZE);
		final Page.Info info = Page.Info.builder().count(count).pages((int) totalPages).build();
		return Page.<List<ProductInfo>>builder().info(info).results(products).build();
	}
}
