package com.gcode.productapp.api;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.postgresql.util.PSQLException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.UncheckedIOException;

@ControllerAdvice
public final class SharedExceptionHandler {

	@ExceptionHandler({
			JsonMappingException.class,
			IllegalArgumentException.class,
			PSQLException.class,
			UncheckedIOException.class
	})
	public static ResponseEntity<JSend<Exception>> handleException(final Exception e) {
//		LOGGER.error(e.getLocalizedMessage(), e);
		final JSend<Exception> body = Error.create(e);
		final int code = body.getCode();
		return ResponseEntity
				.status(code)
				.body(body);
	}
}
