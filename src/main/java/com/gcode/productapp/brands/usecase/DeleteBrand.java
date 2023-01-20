package com.gcode.productapp.brands.usecase;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.brands.database.BrandRepository;

public class DeleteBrand implements UseCase<String,String> {

    private final BrandRepository repository;
    private long brandId;

    public static final DeleteBrand create(final BrandRepository repository){
        return new DeleteBrand(repository);
    }

    private DeleteBrand(final BrandRepository repository){
        this.repository = repository;
    }

    @Override
    public String execute(final String id) {
        long brandId = Long.valueOf(id);
        setBrandID(brandId);
        verifyIfBrandExists();
        tryToDeleteBrandFromDB();
        return getSuccessMessage();
    }

    private void tryToDeleteBrandFromDB() {
        boolean success = repository.deleteBrand(brandId);
        if(!success){
			throw new IllegalArgumentException(getFailureMessage());
		}
    }

    private void verifyIfBrandExists() {
        boolean exists = repository.brandExistsWithId(brandId);
        if(!exists){
			throw new IllegalArgumentException(getNonExistentProductMessage());
		}
    }

    private void setBrandID(long brandId) {
        this.brandId = brandId;
    }
    private String getFailureMessage() {
		return String.format("Delete operation for brand with id %d FAILED!", brandId);
	}
	private String getSuccessMessage() {
		return String.format("Brand with id %d was deleted successfully", brandId);
	}
	private String getNonExistentProductMessage() {
		return String.format("Brand with id %d does not exists", brandId);
	}
    
}
