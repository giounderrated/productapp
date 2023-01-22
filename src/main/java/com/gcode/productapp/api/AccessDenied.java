package com.gcode.productapp.api;

import java.util.Collections;
import java.util.List;

public class AccessDenied<T> implements JSend<T> {
	private final String status = "denied";
	private final int code = 403;
	private final String message;
	private final T data  = null;

	public static <T> Builder<T> builder() {
		return new Builder<>();
	}

	private AccessDenied(final Builder<T> builder) {
		message = builder.message;
	}

	public static final class Builder<T> {

		private String message;
		private T data;

		private Builder() {
		}

		public Builder<T> message(final String message) {
			this.message = message;
			return this;
		}

		public Builder<T> data(final T data) {
			this.data = data;
			return this;
		}

		public JSend<T> build() {
			return new AccessDenied<>(this);
		}
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public List<String> getStack() {
		return Collections.emptyList();
	}


}
