package com.sxgy.sp52.core.security.dto;

import com.sxgy.sp52.core.util.internet.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author SXGY_09
 * @description 权限切点
 * @date 2019-11-23 15:19
 */
@Slf4j
@Service
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Commences an authentication scheme.
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error(authException.getMessage());
        HttpUtil.doReturnStr(response,authException.getMessage());
    }
}
