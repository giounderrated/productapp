package com.gcode.productapp.brands.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.brands.database.BrandRepository;
import com.gcode.productapp.brands.domain.Brand;

public class CreateBrand implements UseCase<Brand,String> {

	private final BrandRepository repository;
    private Brand brand;

    public static UseCase<Brand,String> create(final BrandRepository repository){
        return new CreateBrand(repository);
    }
    
    private CreateBrand(BrandRepository repository) {
        this.repository = repository;
    }

    public String execute(Brand brand) {
        setBrand(brand);
        tryToAddBrand();
        return getSuccessMessage();
    }

    private String getSuccessMessage() {
		return String.format("Brand %s has been created correctly",brand.getName());
	}

    private void tryToAddBrand() {
        final boolean success = repository.insertBrand(brand);
        if (!success) {
			throw new RuntimeException("Error at inserting brand in database");
		}
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    
}
