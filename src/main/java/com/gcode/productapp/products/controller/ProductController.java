package com.gcode.productapp.products.controller;

import com.gcode.productapp.api.JSend;
import com.gcode.productapp.api.Page;
import com.gcode.productapp.api.Success;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.products.domain.Product;
import com.gcode.productapp.products.domain.ProductInfo;
import com.gcode.productapp.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProductController {
	private static final String PATH = "/products";
	private static final String PATH_WITH_ID = "/products/{id}";
	private static final String PATH_TO_IMAGES = "/products/images";

	@Autowired
	private UseCase<Product, String> createProduct;
	@Autowired
	private UseCase<Void, List<ProductInfo>> getAllProducts;
	@Autowired
	private UseCase<Integer, Page<List<ProductInfo>>> getProductsByPage;
	@Autowired
	private UseCase<String, ProductInfo> getProductWithID;
	@Autowired
	private UseCase<Product, String> updateProduct;
	@Autowired
	@Qualifier("Products")
	private UseCase<String, String> deleteProduct;
	//	@Autowired
//	private UseCase<ProductImages,String> insertImagesOfProduct;
	@Autowired
	private Mapper<String, ProductInfo> ProductInfoMapper;
	@Autowired
	private Mapper<String, Product> ProductMapper;
//	@Autowired
//	private Mapper<String,ProductImages> getProductImagesMapper;

	@PostMapping(PATH)
	public final JSend<Product> createProduct(final HttpEntity<String> entity) {
		final String json = entity.getBody();
		final Product product = ProductMapper.from(json);
		final String result = createProduct.execute(product);
		return Success.<Product>builder()
				.message(result)
				.data(product)
				.build();
	}
//	@GetMapping(PATH)
//	public final JSend<List<ProductInfo>> getAllProducts(final HttpEntity<String> entity){
//		final List<ProductInfo> products = getAllProducts.execute(null);
//		return Success.<List<ProductInfo>>builder()
//			.data(products)
//			.build();
//	}

	@GetMapping(PATH)
	public final JSend<Page<List<ProductInfo>>> getProductsByPage(@RequestParam String page) {
//		final List<ProductInfo> products = getAllProducts.execute(null);
//		Page.Info info = Page.Info.builder().pages(10).count(100).build();
//		System.out.println(page);
//		final Page<List<ProductInfo>> paginated = Page.<List<ProductInfo>>builder().info(info).results(products).build();
//		return Success.<Page<List<ProductInfo>>>builder().data(paginated).build();
		Page<List<ProductInfo>> result = getProductsByPage.execute(Integer.valueOf(page));
		return Success.<Page<List<ProductInfo>>>builder().data(result).build();
	}

	@GetMapping(PATH_WITH_ID)
	public final JSend<ProductInfo> getProduct(@PathVariable("id") final String id) {
		final ProductInfo product = getProductWithID.execute(id);
		return Success.<ProductInfo>builder()
				.data(product)
				.build();
	}

	@PutMapping(PATH)
	public final JSend<Product> updateProduct(final HttpEntity<String> entity) {
		final String json = entity.getBody();
		final Product product = ProductMapper.from(json);
		final String result = updateProduct.execute(product);
		return Success.<Product>builder()
				.message(result)
				.data(product)
				.build();
	}

	@DeleteMapping(PATH_WITH_ID)
	public final JSend<String> deleteProduct(@PathVariable("id") final String id) {
		final String result = deleteProduct.execute(id);
		return Success.<String>builder()
				.message(result)
				.build();
	}
//	@PostMapping(PATH_TO_IMAGES)
//	public final JSend<ProductImages> insertImagesOfProduct(final HttpEntity<String> entity){
//		final String json = entity.getBody();
//		final ProductImages images = getProductImagesMapper.from(json);
//		final String result = insertImagesOfProduct.execute(images);
//		return Success.<ProductImages>builder()
//				.data(images)
//				.message(result)
//				.build();
//	}
}
