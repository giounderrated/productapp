package com.gcode.productapp.products.domain;

import java.util.List;

import com.gcode.productapp.brands.domain.Brand;
import com.gcode.productapp.categories.domain.Category;

public interface ProductInfo{
    long getId();
	String getTitle();
	String getDescription();
	long getPrice();
	int getDiscountPercentage();
	int getRating();
	int getStock();
	Brand getBrand();
	Category getCategory();
	String getThumbnail();
	List<String> getImages();
}


// https://roytuts.com/select-example-using-spring-jdbctemplate/
// https://stackoverflow.com/questions/16461735/jdbc-template-one-to-many
// https://www.easycodeforall.com/retrieve-data-from-multiple-table.html#google_vignette