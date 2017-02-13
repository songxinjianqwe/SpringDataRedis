package me.newsong.exceptions.domain;

import java.util.List;

import org.springframework.http.HttpStatus;

public class RestError {
	private HttpStatus status;
	private int code;
	private List<RestFieldError> fieldErrors;
	private String moreinfoURL;
	
	public RestError() {
	}

	public RestError(HttpStatus status, int code, List<RestFieldError> fieldErrors, String moreinfoURL
			) {
		this.status = status;
		this.code = code;
		this.fieldErrors = fieldErrors;
		this.moreinfoURL = moreinfoURL;
	}

	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<RestFieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<RestFieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getMoreinfoURL() {
		return moreinfoURL;
	}

	public void setMoreinfoURL(String moreinfoURL) {
		this.moreinfoURL = moreinfoURL;
	}

	@Override
	public String toString() {
		return "RestError [status=" + status + ", code=" + code + ", fieldErrors=" + fieldErrors + ", moreinfoURL="
				+ moreinfoURL + "]";
	}
}
