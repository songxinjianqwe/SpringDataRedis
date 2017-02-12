package me.newsong.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import me.newsong.exceptions.base.BaseRestException;

public class UsernameNotExistedException extends BaseRestException {
	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
	private static final int CODE = 40002;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 480762216653449470L;

	public UsernameNotExistedException(String lastName, Locale locale) {
		super(STATUS, CODE, locale,"lastName", lastName);
	}
}
