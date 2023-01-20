package com.gcode.productapp.brands.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.brands.database.BrandRepository;
import com.gcode.productapp.brands.domain.Brand;

public class GetBrandWithId implements UseCase<Long, Brand> {
    private final BrandRepository repository;
    private  long brandId;

    public static final GetBrandWithId create(BrandRepository repository){
        return new GetBrandWithId(repository);
    }

    private GetBrandWithId(BrandRepository repository){
        this.repository = repository;
    }

    @Override
    public Brand execute(Long id) {
        setBrandId(id);
        verifyIfBrandExists();
        return tryToGetBrandFromDB();
    }

    private Brand tryToGetBrandFromDB() {
        final Brand brand = repository.getBrand(brandId);

        if(brand==null){
            throw new IllegalArgumentException(getFailureMessage(brandId));
        }
        return brand;
    }

    private void verifyIfBrandExists() {
        final boolean exists = repository.brandExistsWithId(brandId);
        if(!exists){
			throw new IllegalArgumentException(getNonExistentBrandMessage(brandId));
		}
    }

    private String getFailureMessage(long brandId) {
		return String.format("Impossible to get brand with id: %d", brandId);
	}

    private String getNonExistentBrandMessage(long brandId) {
        return String.format("Brand with id %d does not exists", brandId);
    }

    private void setBrandId(long id) {
        this.brandId = id;
    }
    
}
