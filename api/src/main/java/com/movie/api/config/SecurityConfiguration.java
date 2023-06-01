package com.movie.api.config;

import com.movie.api.auth.AuthorizationFilter;
import com.movie.api.model.support.ResponseResult;
import com.movie.api.utils.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * SpringSecurity配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * HTTP验证规则
     *
     * @param http h
     * @throws Exception e
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //开启跨域
        http.csrf().disable().cors();

        //允许跨域使用iframe
        http.headers().frameOptions().disable();

        //禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //身份验证失败
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            ResponseUtil.writeJson(response, new ResponseResult<>(403, "身份认证失败, 请重新登录"));
        });

        http.addFilter(new AuthorizationFilter(authenticationManagerBean()));

    }

    /**
     * SpringSecurity有默认的跨域配置 会无法放行RequestHeader带有"Authorization"请求
     * 防止前端请求api报出cors error
     *
     * @return *
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // 创建一个 UrlBasedCorsConfigurationSource 对象
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建一个 CorsConfiguration 对象
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 添加允许的请求头信息，其中 "*" 表示允许所有请求头
        corsConfiguration.addAllowedHeader("*");
        // 添加允许的 DELETE 请求头信息
        corsConfiguration.addAllowedHeader("DELETE");
        // 添加允许的请求方法信息，其中 "*" 表示允许所有请求方法
        corsConfiguration.addAllowedMethod("*");
        // 添加允许的请求来源信息，其中 "*" 表示允许所有请求来源
        corsConfiguration.addAllowedOrigin("*");
        // 将 CorsConfiguration 对象注册到 UrlBasedCorsConfigurationSource 中，并指定拦截的路径为 "/*"
        source.registerCorsConfiguration("/**", corsConfiguration);
        // 返回 UrlBasedCorsConfigurationSource 对象
        return source;
    }

}
