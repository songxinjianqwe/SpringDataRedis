package me.newsong.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import me.newsong.exceptions.base.BaseRestException;

public class ValidationException extends BaseRestException {
	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
	private static final int CODE = 40001;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5495053837578511264L;

	public ValidationException(List<FieldError> errors) {
		super(STATUS, CODE,  toRestFieldErrorList(errors));
	}

}
