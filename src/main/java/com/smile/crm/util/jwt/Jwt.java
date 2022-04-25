package com.smile.crm.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

/**
 * @author smile
 * @Notes
 * @date 2022/4/20
 * @time 8:08 PM
 * @example
 * @link
 */
@Component
public class Jwt {
    /***
     * 密钥
     **/
    private static String key;

    /***
     * 过期时间
     **/
    private static int expire;

    @Value("${jwt.expire}")
    public void setExpire(int expire)
    {
        Jwt.expire = expire;
    }

    @Value("${jwt.key}")
    public void setKey(String key)
    {
        Jwt.key = key;
    }

    /***
     * @Notes 生成token
     * @param claims claims
     * @return java.lang.String
     * @author smile
     * @date 2022/4/20
     * @time 8:17 PM
     **/
    public static String token(Map<String,String> claims) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,expire);

        JWTCreator.Builder builder = JWT.create();

        if (!Objects.isNull(claims)) {
            claims.forEach(builder::withClaim);
        }

        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(key));
    }

    /***
     * @Notes 生成token
     * @param claims claims
     * @param calendar 过期时间
     * @return java.lang.String
     * @author smile
     * @date 2022/4/20
     * @time 8:17 PM
     **/
    public static String token(Map<String,String> claims, Calendar calendar){
        JWTCreator.Builder builder = JWT.create();

        if (!Objects.isNull(claims)) {
            claims.forEach(builder::withClaim);
        }

        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(key));
    }

    /***
     * @Notes 生成token
     * @param claims claims
     * @param calendar 过期时间
     * @param key 密钥
     * @return java.lang.String
     * @author smile
     * @date 2022/4/20
     * @time 8:24 PM
     **/
    public static String token(Map<String,String> claims,Calendar calendar,String key) {
        JWTCreator.Builder builder = JWT.create();

        if (!Objects.isNull(claims)) {
            claims.forEach(builder::withClaim);
        }

        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(key));
    }

    /***
     * @Notes 验证token
     * @param token token值
     * @author smile
     * @date 2022/4/20
     * @time 8:22 PM
     **/
    public static void verify(String token)
    {
        JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token);
    }

    /***
     * @Notes 验证token
     * @param token token值
     * @param key 密钥
     * @author smile
     * @date 2022/4/20
     * @time 8:22 PM
     **/
    public static void verify(String token,String key)
    {
        JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token);
    }

    /***
     * @Notes 解析token
     * @param token token的值
     * @return com.auth0.jwt.interfaces.DecodedJWT
     * @author smile
     * @date 2022/4/20
     * @time 8:23 PM
     **/
    public static DecodedJWT parse(String token)
    {
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token);
    }

    /***
     * @Notes 解析token
     * @param token token的值
     * @param key 密钥
     * @return com.auth0.jwt.interfaces.DecodedJWT
     * @author smile
     * @date 2022/4/20
     * @time 8:23 PM
     **/
    public static DecodedJWT parse(String token,String key)
    {
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token);
    }
}
