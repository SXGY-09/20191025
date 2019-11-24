package com.sxgy.sp52.core.util.security;

import com.sxgy.sp52.core.domain.ExpiryMap;
import com.sxgy.sp52.core.domain.MemoryData;
import com.sxgy.sp52.core.pojo.Sp52Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * @author SXGY_09
 * @description Jwt工具类
 * @date 2019-11-16 12:20
 */
@Slf4j
public class JwtUtil {
    /**
     * 加密/解析密钥
     */
    private static final String key = "SXGY_09";
    /**
     * 加密/解析算法
     */
    private static final SignatureAlgorithm mode = SignatureAlgorithm.HS256;
    static ExpiryMap tokenMap = MemoryData.getTokenMap();

    public static String getToken(Long customerId, String customerName, List<Sp52Role> roles) {
        Long time = System.currentTimeMillis();
        String token = Jwts.builder()
                .claim("id", customerId)
                .claim("name", customerName)
                .claim("roles", roles)
                .setIssuedAt(new Date(time))
                .signWith(mode, key)
                //截止时间2h
                .setExpiration(new Date((time + 1000 * 3600 * 2)))
                .compact();
        String tokenKey = "i" + customerId;
        log.debug("tokenKey:" + tokenKey);
        //把token存入MemoryData
        tokenMap.put(tokenKey, token);
        return token;
    }

    /**
     * 解析token
     *
     * @param token 口令
     * @return 失败返回null
     */
    public static Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            log.error("token解析失败");
        }
        log.debug("====claims====");
        log.debug(claims.toString());
        log.debug("====claims=====");
        return claims;
    }
}
