package com.smile.crm.security.exception;

/**
 * @author smile
 * @Notes 登陆异常
 * @date 2022/4/20
 * @time 10:52 PM
 * @example
 * @link
 */
public class AuthBadException extends RuntimeException{
    public AuthBadException(String message) {
        super(message);
    }
}
