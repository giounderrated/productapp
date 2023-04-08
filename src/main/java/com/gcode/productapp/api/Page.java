package com.gcode.productapp.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page<T> {

	private Info info;
	private T results;

	@Data
	@Builder
	public static class Info {
		private long count;
		private long pages;
	}
}
