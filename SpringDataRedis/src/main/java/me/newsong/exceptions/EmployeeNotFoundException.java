package me.newsong.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import me.newsong.exceptions.base.BaseRestException;
import me.newsong.exceptions.domain.RestFieldError;

public class EmployeeNotFoundException extends BaseRestException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5121572687508712305L;
	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40402;

	public EmployeeNotFoundException(Locale locale) {
		super(STATUS, CODE, locale, "employee", "empty");
	}
	
}

