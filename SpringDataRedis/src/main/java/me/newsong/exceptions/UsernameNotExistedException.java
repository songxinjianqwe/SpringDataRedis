package me.newsong.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE,reason="用户名不存在")
public class UsernameNotExistedException extends RuntimeException {
}
