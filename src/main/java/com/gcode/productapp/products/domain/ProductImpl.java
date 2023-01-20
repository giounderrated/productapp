package com.gcode.productapp.products.domain;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class ProductImpl implements Product {
    private long id;
	private String title;
	private String description;
	private long price;
	private int discountPercentage;
	private int rating;
	private int stock;
	private String thumbnail;
	private long brandId;
	private long categoryId;

}