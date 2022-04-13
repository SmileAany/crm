package com.smle.crm.util.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smile
 * @Notes http响应接口
 * @date 2022/4/3
 * @time 4:05 PM
 * @example
 * @link
 */
public class HttpResponse {
    public static void response(HttpServletResponse response, String string) throws IOException {
        response.setStatus(ResponseCode.Code.HttpCode.SUCCESS.getCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter()
                .print(string);
    }
}
