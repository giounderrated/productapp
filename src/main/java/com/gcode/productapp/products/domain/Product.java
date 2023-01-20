package com.gcode.productapp.products.domain;

public interface Product {
	long getId();
	String getTitle();
	String getDescription();
	long getPrice(); //700 = 70000
	int getDiscountPercentage(); //12 = 12%
	int getRating(); // 600 = 6
	int getStock(); // 20 = 20
	String getThumbnail();
	long getBrandId();
	long getCategoryId();
}
