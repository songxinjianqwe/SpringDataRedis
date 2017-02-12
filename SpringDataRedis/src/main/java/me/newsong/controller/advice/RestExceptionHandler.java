package me.newsong.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.newsong.exceptions.base.BaseRestException;
import me.newsong.exceptions.domain.RestError;
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(BaseRestException.class)
	public ResponseEntity<RestError> handle(BaseRestException e) {
		return new ResponseEntity<RestError>(new RestError(e.getStatus(), e.getCode(), e.getErrors(), ""), e.getStatus());
	}
	
}
