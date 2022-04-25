package com.smile.crm.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smile
 * @Notes jwt 配置文件
 * @date 2022/4/20
 * @time 8:34 PM
 * @example
 * @link
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class JwtProperty {
    private String key;

    private int expire;

    private String header;

    private String prefix;
}
