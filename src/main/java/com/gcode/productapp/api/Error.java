package com.gcode.productapp.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Error<T extends Exception> implements JSend<T> {

	private static final String PACKAGE = "com.gcode.productApp";

	private final String status = "error";
	private final int code = 500;
	private final String message;
	private final T data;
	private final List<String> stack;

	private Error(final T data) {
		this.data = data;
		message = data.getLocalizedMessage();
		stack = setStack(data);
	}

	static <T extends Exception> JSend<T> create(final T data) {
		return new Error<>(data);
	}

	private static List<String> setStack(final Exception e) {
		return Arrays.stream(e.getStackTrace())
				.filter(filterStack())
				.flatMap(concatStackInfo())
				.collect(Collectors.toList());
	}

	private static Predicate<StackTraceElement> filterStack() {
		return e -> {
			final String className = e.getClassName();
			return className.startsWith(PACKAGE);
		};
	}

	private static Function<StackTraceElement, Stream<String>> concatStackInfo() {
		return e -> {
			final String className = e.getClassName();
			final String methodName = e.getMethodName();
			final int lineNumber = e.getLineNumber();
			return Stream.of(String.format("%s:%s:%d", className, methodName, lineNumber));
		};
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
		return Collections.unmodifiableList(stack);
	}
}