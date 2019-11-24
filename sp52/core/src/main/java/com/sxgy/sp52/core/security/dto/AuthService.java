package com.sxgy.sp52.core.security.dto;

import com.google.gson.Gson;
import com.sxgy.sp52.core.domain.ApiRp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SXGY_09
 * @description 请求权限过滤
 * @date 2019-11-23 15:03
 */
@Component
@Slf4j
public class AuthService {
    private AuthChecker checker=new AuthChecker();
    private Gson gson = new Gson();

    /**
     * 判断一个请求是否有权限
     * @param request
     * @param authentication
     * @return
     */
    public boolean canAccess(HttpServletRequest request, Authentication authentication){
        long t1 = System.currentTimeMillis();
        ApiRp apiRp = checker.check(request);
        if (apiRp.getCode() !=200) {
            throw new CustomAuthenticationException(gson.toJson(apiRp));
        }
        long t2 = System.currentTimeMillis();
        log.debug("鉴权耗时: " + (t2 - t1) + "ms");

        return true;
    }
}
