package com.gcode.productapp.brands.controller;

import java.util.List;

import com.gcode.productapp.api.JSend;
import com.gcode.productapp.api.Success;
import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BrandController {
	private static final String PATH = "/brands";
	private static final String PATH_WITH_ID = "/brands/{id}";
    
    @Autowired
	private UseCase<Void, List<Brand>> getAllBrands;

    @Autowired
	private UseCase<Brand,String> createBrand;

	@Autowired
	private UseCase<Long,Brand> getBrand;

	@Autowired
	private UseCase<Brand,String> updateBrand;

	@Autowired
	@Qualifier("Brands")
	private UseCase<String,String> deleteBrand;

    @Autowired
	private Mapper<String,Brand> brandMapper;

    @GetMapping(PATH)
	public final JSend<List<Brand>> getAllBrands(final HttpEntity<String> entity){
		final List<Brand> products = getAllBrands.execute(null);
		return Success.<List<Brand>>builder()
			.data(products)
			.build();
	}

	@PostMapping(PATH)
	public final JSend<Brand> createBrand(final HttpEntity<String> entity){
		System.out.println("Creating brand");
		final String json = entity.getBody();
		final Brand brand = brandMapper.from(json);
		final String result = createBrand.execute(brand);
		return Success.<Brand>builder()
				.message(result)
				.data(brand)
				.build();
	}

	@GetMapping(PATH_WITH_ID)
	public final JSend<Brand> getBrand(@PathVariable("id") final long id){
		final Brand brand = getBrand.execute(id);
		return Success.<Brand>builder()
				.data(brand)
				.build();
	}

	@PutMapping(PATH)
	public final JSend<Brand> updateBrand(final HttpEntity<String> entity){
		final String json = entity.getBody();
		Brand brand = brandMapper.from(json);
		final String result = updateBrand.execute(brand);
		return Success.<Brand>builder()
				.message(result)
				.data(brand)
				.build();
	}

	@DeleteMapping(PATH_WITH_ID)
	public final JSend<String> deleteBrand(@PathVariable("id") final String id){
		final String result = deleteBrand.execute(id);
		return Success.<String>builder()
		.message(result)
		.build();
	}

}
