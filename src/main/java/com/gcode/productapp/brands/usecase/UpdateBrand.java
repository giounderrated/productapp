package com.gcode.productapp.brands.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.brands.database.BrandRepository;
import com.gcode.productapp.brands.domain.Brand;

public class UpdateBrand implements UseCase<Brand,String> {
    private final BrandRepository repository;
    private Brand brand;

    public static final UpdateBrand create(BrandRepository repository){
        return new UpdateBrand(repository);
    }

    private UpdateBrand(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(Brand brand) {
        setBrand(brand);
        veryfyIfBrandExists();
        tryToUpdateBrandFromDB();
        return getSuccessMessage();
    }

    private void setBrand(final Brand brand) {
        this.brand = brand;
    }
    
    private void veryfyIfBrandExists() {
        final long id = brand.getId();
        final boolean exists = repository.brandExistsWithId(id);
        if(!exists){
            throw new IllegalArgumentException(getNonExistentBrandMessage());
		}
    }
    
    private void tryToUpdateBrandFromDB() {
        final boolean result = repository.updateBrand(brand);
        if(!result){
			throw new IllegalArgumentException(getFailureMessage());
		};
    }
    private String getSuccessMessage() {
		return String.format("Brand %s was updated successfully", brand.getName());
	}

	private String getNonExistentBrandMessage() {
		return String.format("Brand with id %s does not exists", brand.getId());
	}

	private String getFailureMessage() {
		return String.format("Update operation for brand with id %s FAILED!", brand.getId());
	}

}
