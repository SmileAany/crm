package com.smile.crm.security.filter;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.jwt.JWTException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.smile.crm.property.JwtProperty;
import com.smile.crm.util.jwt.Jwt;
import com.smile.crm.util.response.ApiResponse;
import com.smile.crm.util.response.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smile
 * @Notes jwt认证过滤器
 * @date 2022/4/20
 * @time 8:30 PM
 * @example
 * @link
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /***
     * jwt 自定义配置值
     **/
    @Resource
    private JwtProperty jwtProperty;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request,response);
            return;
        }

        String accessToken = getAccessToken(request);

        try {
            validateAccessToken(accessToken,request);

            setAuthenticationContext(accessToken,request);

            filterChain.doFilter(request,response);
        }catch (JWTException exception) {
            HttpResponse.response(response,
                    JSON.toJSONString(ApiResponse.error(exception.getMessage(),401), SerializerFeature.WriteMapNullValue));
        }
    }

    /***
     * @Notes 将信息存放到 ThreadLocal 中
     * @param accessToken 方法token
     * @param request request
     * @author smile
     * @date 2022/4/21
     * @time 11:05 AM
     **/
    public void setAuthenticationContext(String accessToken,HttpServletRequest request) {
        //将用户信息和权限填充到用户身份token对象中
        UserDetails userDetails = getUserDetails(accessToken);
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails,null,null);

        //将authenticationToken填充到安全上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /***
     * @Notes 获取到用户信息
     * @param accessToken token值
     * @return UserDetails
     * @author smile
     * @date 2022/4/21
     * @time 11:09 AM
     **/
    public UserDetails getUserDetails(String accessToken) {
        return null;
    }

    /***
     * @Notes 判断header中是否包含 Authorization
     * @Notes 判断 token中是否包含 Bearer
     * @param request request
     * @return boolean
     * @author smile
     * @date 2022/4/20
     * @time 10:34 PM
     **/
    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader(jwtProperty.getHeader());

        return StringUtils.isNotEmpty(header) && header.startsWith(jwtProperty.getPrefix() + StringUtils.SPACE);
    }

    /***
     * @Notes 获取访问请求的token值
     * @param request request
     * @return java.lang.String
     * @author smile
     * @date 2022/4/20
     * @time 10:38 PM
     **/
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(jwtProperty.getHeader());

        return header.split(StringUtils.SPACE)[1].trim();
    }

    /***
     * @Notes 验证token是否正常
     * @Notes 记录请求的数据
     * @param accessToken token
     * @param request 请求值
     * @author smile
     * @date 2022/4/25
     * @time 9:31 PM
     **/
    private void validateAccessToken(String accessToken,HttpServletRequest request) throws JWTException{
        try{
            Jwt.verify(accessToken);
        }catch (SignatureVerificationException exception){
            log.debug("token 签名无效 ip地址：" + ServletUtil.getClientIP(request));
            throw new JWTException("token 签名无效");
        } catch (TokenExpiredException exception){
            log.debug("token 过期 ip地址：" + ServletUtil.getClientIP(request));
            throw new JWTException("token 过期");
        } catch (AlgorithmMismatchException exception){
            log.debug("token 签名算法不一致 ip地址：" + ServletUtil.getClientIP(request));
            throw new JWTException("token 签名算法不一致");
        } catch (Exception exception){
            log.debug("token 异常 ip地址：" + ServletUtil.getClientIP(request));
            throw new JWTException("token 异常");
        }
    }
}
