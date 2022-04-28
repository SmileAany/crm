package com.smile.crm.handler;

import com.smile.crm.util.response.ApiResponse;
import com.smile.crm.util.response.ResponseCode;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author smile
 * @Notes 异常处理中心
 * @date 2022/4/13
 * @time 5:58 PM
 * @example
 * @link
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse<String> handleException(Exception e) throws Exception {
//        if (e instanceof UsernameNotFoundException) {
//            throw(e);
//        } else if (e instanceof BadCredentialsException) {
//            throw(e);
//        }

        e.printStackTrace();

        return ApiResponse.error(ResponseCode.Code.HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 路由未找到
     **/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponse<String> handleNotFoundException(){
        return ApiResponse.error(ResponseCode.Code.HttpCode.NOT_FOUND);
    }

    /**
     * 数学错误
     **/
    @ExceptionHandler(ArithmeticException.class)
    public ApiResponse<String> ArithmeticException(ArithmeticException exception){
        return ApiResponse.error(exception.getMessage(),ResponseCode.Code.HttpCode.ERROR.getCode());
    }

    /**
     * 请求方式被拒绝
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<String> HttpRequestMethodNotSupportedException() {
        return ApiResponse.error(ResponseCode.Code.HttpCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 参数异常
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ApiResponse.error(ResponseCode.Code.HttpCode.VALIDATE_FAILED);
    }

    /**
     * 参数异常
     * 必须参数 未提供
     **/
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<String> MissingServletRequestParameterException() {
        return ApiResponse.error(ResponseCode.Code.HttpCode.VALIDATE_FAILED);
    }
}
