package com.gcode.productapp.categories.domain;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CategoryImpl implements Category {

    private long id;
    private String name;
    private String description;
}
