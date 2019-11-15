package com.sxgy.sp52.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author SXGY_09
 * @description security配置
 * @date 2019-11-15 21:17
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 安全白名单
     */
    private static final String[] AUTH_WHITELIST = {
            "/i18n/**",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/webjars/**",
            "/configuration/ui",
            "/configuration/security",

            "/*",
            "/api",
            "/api2",
            "/swagger-ui.html",
            "/docs.html",
            "/file/**",
            "/static/**",
            "/test/**",
            "/favicon.ico",
            "/*/api/v1/demo/**",
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
