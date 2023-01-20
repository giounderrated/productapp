package com.gcode.productapp.brands.database;

import com.gcode.productapp.brands.domain.Brand;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class BrandJDBCRepository implements BrandRepository{
    
    private final JdbcTemplate template;

    public static final BrandJDBCRepository create(JdbcTemplate template){
        return new BrandJDBCRepository(template);
    }

    private BrandJDBCRepository(JdbcTemplate template){
        this.template = template;
    }

    @Override
    public boolean insertBrand(Brand brand) {
        final InsertBrand insertBrand = InsertBrand.create(template);
        return insertBrand.insert(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        GetAllBrands getAllBrands = GetAllBrands.create(template);
        return getAllBrands.execute();
    }

    @Override
    public boolean brandExistsWithId(long id) {
        BrandExistsWithId brand = BrandExistsWithId.create(template);
        return brand.exists(id);
    }

    @Override
    public Brand getBrand(long brandId) {
        GetBrandWithId getBrand = GetBrandWithId.create(template);
        return getBrand.withId(brandId);
    }

    @Override
    public boolean updateBrand(Brand brand) {
        UpdateBrand update = UpdateBrand.create(template);
        return update.update(brand);
    }

    @Override
    public boolean deleteBrand(long brandId) {
        DeleteBrand deleteBrand = DeleteBrand.create(template);
        return deleteBrand.withId(brandId);
    }
    
}
