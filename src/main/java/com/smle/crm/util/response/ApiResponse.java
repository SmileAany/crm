package com.smle.crm.util.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smile
 * @Notes 统一api接口响应
 * @date 2022/4/2
 * @time 3:53 PM
 * @example
 * @link
 */
public class ApiResponse<T> {
    @Setter
    @Getter
    private int code;

    @Setter @Getter
    private String message;

    @Setter @Getter
    private T data;

    private ApiResponse() {}

    public ApiResponse(T data,int code,String message)
    {
        this.data = data;

        this.code = code;

        this.message = message;
    }

    public static <T> ApiResponse<T> success()
    {
        return new ApiResponse<T>(null,ResponseCode.Code.HttpCode.SUCCESS.getCode(), ResponseCode.Code.HttpCode.SUCCESS.getMessage());
    }

    public static <T> ApiResponse<T> success(String message)
    {
        return new ApiResponse<T>(null,ResponseCode.Code.HttpCode.SUCCESS.getCode(), message);
    }

    public static <T> ApiResponse<T> success(String message,int code)
    {
        return new ApiResponse<T>(null,code, message);
    }

    public static <T> ApiResponse<T> success(String message,int code,T data)
    {
        return new ApiResponse<T>(data,code, message);
    }

    public static <T> ApiResponse<T> error()
    {
        return new ApiResponse<T>(null,ResponseCode.Code.HttpCode.ERROR.getCode(), ResponseCode.Code.HttpCode.ERROR.getMessage());
    }

    public static <T> ApiResponse<T> error(String message)
    {
        return new ApiResponse<T>(null,ResponseCode.Code.HttpCode.ERROR.getCode(), message);
    }

    public static <T> ApiResponse<T> error(String message,int code)
    {
        return new ApiResponse<T>(null,code, message);
    }

    public static <T> ApiResponse<T> error(String message,int code,T data)
    {
        return new ApiResponse<T>(data,code, message);
    }
}
