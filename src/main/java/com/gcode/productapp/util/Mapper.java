package com.gcode.productapp.util;

@FunctionalInterface
public interface Mapper <I,O>{
	O from(I param);
}