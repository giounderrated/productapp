package com.gcode.productapp.products.domain;

import java.util.List;

import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.categories.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class ProductInfoImpl implements ProductInfo {
    private long id;
    private String title;
    private String description;
    private long price;
    private int discountPercentage;
    private int rating;
    private int stock;
    private Brand brand;
    private Category category;
    private String thumbnail;
    private List<String> images;
}
