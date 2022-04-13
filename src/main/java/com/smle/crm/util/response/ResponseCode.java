package com.smle.crm.util.response;

/**
 * @author smile
 * @Notes 统一接口响应数据
 * @date 2022/4/2
 * @time 3:38 PM
 * @example
 * @link
 */
public class ResponseCode {
    private interface ICode {
        int getCode();

        String getMessage();
    }

    public interface Code {
        //http状态码
        enum HttpCode implements ICode {
            SUCCESS(200,"成功"),

            ERROR(400,"失败"),

            UNAUTHORIZED(401,"身份验证失败"),

            VALIDATE_FAILED(402,"参数校验异常"),

            FORBIDDEN(403,"无权限访问"),

            NOT_FOUND(404,"路由未找到"),

            METHOD_NOT_ALLOWED (405,"请求方法不支持"),

            TOO_MANY_REQUESTS(429,"接口频率限制"),

            INTERNAL_SERVER_ERROR(500,"系统错误");

            private int code;

            private String message;

            HttpCode(int code,String message)
            {
                this.code = code;
                this.message = message;
            }

            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        }

        //自定义状态码
        enum CustomCode implements ICode
        {
            ;
            private int code;

            private String message;

            CustomCode(int code,String message)
            {
                this.code = code;
                this.message = message;
            }

            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        }
    }
}
