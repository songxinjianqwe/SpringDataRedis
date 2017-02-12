package me.newsong.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import me.newsong.exceptions.base.BaseRestException;

public class DepartmentNotFoundException extends BaseRestException {
	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40401;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5639753594261954777L;

	public DepartmentNotFoundException(Locale locale) {
		super(STATUS, CODE, locale, "depts", "empty");
	}

}
