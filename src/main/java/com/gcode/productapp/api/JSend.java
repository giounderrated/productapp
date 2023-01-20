package com.gcode.productapp.api;

import java.util.List;

public interface JSend<T> {
	String getStatus();
	int getCode();
	T getData();
	String getMessage();
	List<String> getStack();
}