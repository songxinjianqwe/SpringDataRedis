package me.newsong.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import me.newsong.exceptions.base.BaseRestException;

public class UsernameExistedException extends BaseRestException {
	private static final HttpStatus STATUS = HttpStatus.CONFLICT;
	private static final int CODE = 42201;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6915629262486503046L;

	public UsernameExistedException(String lastName, Locale locale) {
		super(STATUS, CODE, locale, "lastName", lastName);
	}

}
