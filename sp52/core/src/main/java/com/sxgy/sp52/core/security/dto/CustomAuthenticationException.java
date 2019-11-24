package com.sxgy.sp52.core.security.dto;

import org.springframework.security.core.AuthenticationException;

/**
 * @author SXGY_09
 * @description 描述
 * @date 2019-11-23 15:24
 */
public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
