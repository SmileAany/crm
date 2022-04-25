package com.smile.crm.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smile.crm.util.response.ApiResponse;
import com.smile.crm.util.response.HttpResponse;
import com.smile.crm.util.response.ResponseCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smile
 * @Notes
 * @date 2022/4/21
 * @time 6:44 PM
 * @example
 * @link
 */
@Component
public class SecurityAuthenticationHandler implements AuthenticationEntryPoint {
    /***
     * 用户被冻结
     **/
    private final static String EMAIL_IS_LOCK = "邮箱被冻结";

    /***
     * 用户名或密码错误
     **/
    private final static String PASSWORD_ERROR = "用户名或密码错误";

    /***
     * token 未提供
     **/
    private final static String TOKEN_NOT_PROVIDER = "token 未提供";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof UsernameNotFoundException) {
            HttpResponse.response(response,
                    JSON.toJSONString(ApiResponse.error(authException.getMessage(), ResponseCode.Code.HttpCode.UNAUTHORIZED.getCode()),SerializerFeature.WriteMapNullValue)
            );
        } else if (authException instanceof LockedException) {
            HttpResponse.response(response,
                    JSON.toJSONString(ApiResponse.error(EMAIL_IS_LOCK, ResponseCode.Code.HttpCode.UNAUTHORIZED.getCode()),SerializerFeature.WriteMapNullValue)
            );
        } else if (authException instanceof BadCredentialsException) {
            HttpResponse.response(response,
                    JSON.toJSONString(ApiResponse.error(PASSWORD_ERROR, ResponseCode.Code.HttpCode.UNAUTHORIZED.getCode()),SerializerFeature.WriteMapNullValue)
            );
        } else {
            HttpResponse.response(response,
                    JSON.toJSONString(ApiResponse.error(TOKEN_NOT_PROVIDER, ResponseCode.Code.HttpCode.UNAUTHORIZED.getCode()),SerializerFeature.WriteMapNullValue)
            );
        }
    }
}
