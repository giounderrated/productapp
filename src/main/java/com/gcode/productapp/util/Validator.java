package com.gcode.productapp.util;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Validator<T> {

	private final T object;

	private Validator(final T object) {
		this.object = object;
	}

	public static <T> Validator<T> of(final T object) {
		return new Validator<>(
				Objects.requireNonNull(object, "The object cannot be null")
		);
	}

	public Validator<T> validate(final Predicate<T> validation, final String message) {
		if (validation.test(object)) {
			throw new IllegalArgumentException(message);
		}
		return this;
	}

	public <U> Validator<T> validate(
			final Function<T, U> prediction,
			final Predicate<U> validation,
			final String message) {

		return validate(prediction.andThen(validation::test)::apply, message);
	}

	public T get() {
		return object;
	}
}