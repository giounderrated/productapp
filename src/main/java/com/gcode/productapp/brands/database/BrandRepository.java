package com.gcode.productapp.brands.database;

import java.util.List;

import com.gcode.productapp.brands.domain.Brand;

public interface BrandRepository {
    boolean insertBrand(Brand brand);
    List<Brand> getAllBrands();
    boolean brandExistsWithId(long brandId);
    Brand getBrand(long brandId);
    boolean updateBrand(Brand brand);
    boolean deleteBrand(long brandId);
}
