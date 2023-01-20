package com.gcode.productapp.api;

@FunctionalInterface
public interface UseCase <I, O>{
	O execute(I param);
}