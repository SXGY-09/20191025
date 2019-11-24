package com.sxgy.sp52.core.security.dto;

/**
 * @author SXGY_09
 * @description 常量
 * @date 2019-11-23 15:03
 */
public class Constances {
    /**
     * 安全白名单
     */
    public static final String[] AUTH_WHITELIST = {
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
            "/system/api/v1/account/ifLogin"
    };
    /**
     * 模块白名单
     */
    public static final String[] AUTH_WHITE_URIS={};
}
