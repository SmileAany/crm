package com.smile.crm.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smile.crm.util.response.ApiResponse;
import com.smile.crm.util.response.HttpResponse;
import com.smile.crm.util.response.ResponseCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smile
 * @Notes 无权限访问
 * @date 2022/4/21
 * @time 7:48 PM
 * @example
 * @link
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();

        HttpResponse.response(response, JSON.toJSONString(
                ApiResponse.error(ResponseCode.Code.HttpCode.FORBIDDEN), SerializerFeature.WriteMapNullValue));
    }
}
