package com.smle.crm.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smile
 * @Notes
 * @date 2022/4/13
 * @time 12:02 PM
 * @example
 * @link
 */
@Configuration
public class CrmWebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置fastjson输出格式
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();

        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);

        converter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig config = new FastJsonConfig();

        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(Charset.forName("UTF-8"));

        config.setSerializerFeatures(
                //json格式化
                SerializerFeature.PrettyFormat,
                //输出map中value为null的数据
                SerializerFeature.WriteMapNullValue,
                //输出空list为[]，而不是null
                SerializerFeature.WriteNullListAsEmpty,
                //输出空string为""，而不是null
                SerializerFeature.WriteNullStringAsEmpty,
                //对map进行排序
                SerializerFeature.MapSortField,
                //禁用循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }
}
