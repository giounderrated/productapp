package com.gcode.productapp.brands.usecase;

import java.util.Comparator;
import java.util.List;

import com.gcode.productapp.api.UseCase;
import com.gcode.productapp.brands.database.BrandRepository;
import com.gcode.productapp.brands.domain.Brand;

public class GetAllBrands implements UseCase<Void,List<Brand>>{

    private final BrandRepository repository;

    public static final UseCase<Void,List<Brand>> create(BrandRepository repository){
        return new GetAllBrands(repository);
    }

    private GetAllBrands(BrandRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Brand> execute(Void param) {
        final List<Brand>  brands = repository.getAllBrands();
        brands.sort(Comparator.comparing(Brand::getName));
        return brands;
    }
    
}
