package com.gcode.productapp.brands.domain;

import lombok.Builder;

@Builder
public class BrandImpl implements Brand {

    private long id;
    private String name;

    @Override
    public long getId() {
       return id;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
