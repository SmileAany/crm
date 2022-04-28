package com.smile.crm.security.config;

import com.smile.crm.security.filter.JwtAuthenticationTokenFilter;
import com.smile.crm.security.handler.SecurityAuthenticationHandler;
import com.smile.crm.security.provider.AccountAuthenticationProvider;
import com.smile.crm.security.provider.EmailAuthenticationProvider;
import com.smile.crm.security.service.AccountUserService;
import com.smile.crm.security.service.EmailUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @author smile
 * @Notes security配置中心
 * @Notes 采用多种方式进行支付
 * @Notes 邮箱登陆+账号密码登陆
 * @date 2022/4/21
 * @time 2:59 PM
 * @example
 * @link
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 放行的路径
     */
    private final String[] PATH_RELEASE = {
            "/email/login",
            "/account/login"
    };

    /***
     * 登陆异常处理类
     **/
    @Resource
    private SecurityAuthenticationHandler securityAuthenticationHandler;

    /***
     *  email userDetails类
     **/
    @Resource
    private EmailUserService emailUserService;

    /***
     *  account userDetails类
     **/
    @Resource
    private AccountUserService accountUserService;

    /***
     *  jwt 过滤器
     **/
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /***
     * 暴露 authenticationManagerBean
     **/
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /***
     * 密码加密
     **/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /***
     * 登陆验证提供
     * 绑定 Provider
     * 绑定 UserService
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //email
        EmailAuthenticationProvider emailAuthenticationProvider
                = new EmailAuthenticationProvider();
        emailAuthenticationProvider.setUserDetailsService(emailUserService);

        authenticationManagerBuilder
                .authenticationProvider(emailAuthenticationProvider);

        //account
        AccountAuthenticationProvider accountAuthenticationProvider =
                new AccountAuthenticationProvider();
        accountAuthenticationProvider
                .setUserDetailsService(accountUserService);
        accountAuthenticationProvider
                .setPasswordEncoder(new BCryptPasswordEncoder());

        authenticationManagerBuilder
                .authenticationProvider(accountAuthenticationProvider);
    }

    /***
     * 配置中心
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated();

        //允许跨域
        http.cors();

        //禁止csrf验证
        http.csrf().disable();

        //禁止header头缓存
        http.headers().cacheControl();

        //禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //设置登陆异常和访问异常
        http.exceptionHandling()
                .authenticationEntryPoint(securityAuthenticationHandler);

        //添加jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /***
     * 设置跨域
     **/
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
