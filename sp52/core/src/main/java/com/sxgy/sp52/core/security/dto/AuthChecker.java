package com.sxgy.sp52.core.security.dto;

import com.google.gson.Gson;
import com.sxgy.sp52.core.domain.*;
import com.sxgy.sp52.core.util.security.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author SXGY_09
 * @description 授权检查
 * @date 2019-11-23 15:26
 */
@Slf4j
public class AuthChecker {
    static AntPathMatcher matcher = new AntPathMatcher();

    Gson gson = new Gson();


    public ApiRp check(HttpServletRequest request) {
        ApiRp apiRp = new ApiRp(ApiStatus.CODE_200);
        //拦截请求uri
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("?") != -1) {
            requestURI = requestURI.substring(0, requestURI.indexOf("?"));
        }
        //默认需要校验token
        boolean isToken = true;
        String finalRequestURI = requestURI;
        boolean b1 = Arrays.stream(Constances.AUTH_WHITELIST).anyMatch(uri -> matcher.match(uri, finalRequestURI));
        if (b1) isToken = false;
        if (isToken) {
            //用户所拥有的角色集合
            Set<String> userRoles = new HashSet<>();
            //访问此url所需要的角色集合
            Set<String> urlRoles = new HashSet<>();
            String str = checkToken(request, userRoles);
            if (!"OK".equals(str)) {
                log.info("token无效");
                apiRp = new ApiRp(ApiStatus.CODE_401);
                apiRp.setMsg(str);
                return apiRp;
            }
            //默认需要校验权限
            boolean isPower = true;
            boolean b2 = Arrays.stream(Constances.AUTH_WHITE_URIS).anyMatch(uri -> matcher.match(uri, finalRequestURI));
            if (b2) isPower = false;
            if (isPower) {
                if (!checkPower(request, userRoles, urlRoles, requestURI)) {
                    log.info("无权访问");
                    apiRp = new ApiRp(ApiStatus.CODE_403);
                }
            }
        }

        return apiRp;
    }

    Map tokenMap = MemoryData.getTokenMap();

    public String checkToken(HttpServletRequest request, Set<String> userRoles) {
        HttpSession session = request.getSession();
        //前端提交的token
        ApiRp apiRp = null;
        //获取用户提交的token
        String token = request.getHeader("token");

        //用户未上传token
        if (token == null || token.trim().length() == 0) {
            return "用户未提交token,URI: " + request.getRequestURI();
        }
        log.debug("用户提交的token:" + token);
        //解析用户token
        Claims claims = JwtUtil.getClaims(token);
        //token解析失败
        if (claims == null) {
            return "无效的token,URI: " + request.getRequestURI();
        }
        Customer customer = new Customer();
        customer.setCustomerId(new Long((Integer) claims.get("id")));
        customer.setCustomerName((String) claims.get("name"));
        List<Map> roles = (List<Map>) claims.get("roles");
        session.setAttribute("id",customer.getCustomerId());
        session.setAttribute("name",customer.getCustomerName());
        session.setAttribute("roles",roles);
        String iKey = "i" + customer.getCustomerId();
        log.debug("tokenKey:"+iKey);
        String oldToken = (String) tokenMap.get(iKey);
        log.debug("系统储存的token:" + oldToken);
        if (!token.equals(oldToken)) {
            return "token已失效,URI: " + request.getRequestURI();
        }
        for (Map map :roles){
            userRoles.add((String) map.get("roleName"));
        }
        return "OK";
    }


    List<AuthRoleModulars>  authRoleModulars = null;

    public boolean checkPower(HttpServletRequest request,
                              Set<String> userRoles,
                              Set<String> urlRoles,
                              String requestURI) {


        authRoleModulars = (List<AuthRoleModulars>) MemoryData.getRoleModularMap().get("modulars");
        //拦截请求方法
        String requestMethod = request.getMethod();

        String finalRequestURI = requestURI;

        authRoleModulars.forEach(authRoleModulars1 -> {
            authRoleModulars1.getModulars().getChildren().forEach(authModulars2 -> {
                authModulars2.getChildren().forEach(authModulars3 -> {
                    authModulars3.getChildren().forEach(authModulars0 -> {
                        String authUrl = authModulars0.getPageUrl();
                        String authMethod = authModulars0.getPageMethod();
                        if (restFulUrlMatch(requestMethod, authMethod, finalRequestURI, authUrl)) {
                            log.debug("访问此url所需的角色:"+authRoleModulars1.getRoleName() );
                            urlRoles.add(authRoleModulars1.getRoleName());
                        }
                    });
                });
            });
        });

        if (urlRoles.size() == 0) return false;
        boolean b = false;
        b = urlRoles.stream().anyMatch(
                urlRoleName -> userRoles.stream().anyMatch(
                        userRoleName -> userRoleName.equals(urlRoleName))
        );

        return b;
    }

    private static boolean restFulUrlMatch(String reqMethod, String authMethod, String reqUrl, String authUrl) {
        boolean methodMatches="ALL".equals(authMethod)||reqMethod.equals(authMethod);
        boolean urlMatches = matcher.match(authUrl, reqUrl);
        return methodMatches & urlMatches;
    }
}
