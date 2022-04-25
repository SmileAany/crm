package com.smile.crm.util;

import com.smile.crm.property.JwtProperty;
import com.smile.crm.util.jwt.Jwt;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author smile
 * @Notes
 * @date 2022/4/20
 * @time 8:39 PM
 * @example
 * @link
 */
@SpringBootTest
public class JwtTest {
    @Resource
    private JwtProperty jwtProperty;
}
