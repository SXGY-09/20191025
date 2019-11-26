package com.sxgy.sp52.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SXGY_09
 * @description 用于存储带有效期的token
 * @date 2019-11-26 21:55
 */
public class ExpiryMap {
    /**
     * token允许最短有效时间：1 min
     */
    private static final long MIN_EXPIRY_TIME = 1000 * 60;
    /**
     * token允许最长有效时间：1 hour
     */
    private static final long MAX_EXPIRY_TIME = 1000 * 60 * 60;
    /**
     * token默认有效时间：10 min
     */
    private static final long DEFAULT_EXPIRY_TIME = 1000 * 60 * 10;
    /**
     * 有效期，默认10min
     */
    @Getter
    @Setter
    private long expiry;
    /**
     * 存储tokens
     */
    private Map<String, TokenModel> map;

    public ExpiryMap() {
        this.expiry = DEFAULT_EXPIRY_TIME;
        map = new HashMap<>();
    }

    public ExpiryMap(long expiry) {
        if (expiry < MIN_EXPIRY_TIME) {
            expiry = MIN_EXPIRY_TIME;
        } else if (expiry > MAX_EXPIRY_TIME) {
            expiry = MAX_EXPIRY_TIME;
        }
        this.expiry = expiry;
        map = new HashMap<>();
    }

    /**
     * 添加一个key-token，覆盖已存在
     *
     * @param key   tokenKey
     * @param token tokenValue
     */
    public void addToken(String key, String token) {
        map.put(key, new TokenModel(token));
    }

    /**
     * 判断是否存在token
     *
     * @param key tokenKey
     * @return true if contains
     */
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * 获取token
     *
     * @param key tokenKey
     * @return token
     */
    public String get(String key) {
        return map.get(key).token;
    }

    /**
     * 判断是否过期
     *
     * @param key tokenKey
     * @return true if expired
     */
    public boolean isExpired(String key) {
        return !containsKey(key) || map.get(key).isExpired();
    }

    /**
     * 判断token是否有效
     *
     * @param key   tokenKey
     * @param token tokenValue
     * @return true if effective
     */
    public boolean validate(String key, String token) {
        return !isExpired(key) && map.get(key).validate(token);
    }

    /**
     * 删除token
     *
     * @param key tokenKey
     */
    public void removeToken(String key) {
        map.remove(key);
    }

    /**
     * 清空tokens
     */
    public void clear() {
        map.clear();
    }

    /**
     * Token模型类
     */
    private class TokenModel {
        /**
         * token值
         */
        private String token;
        /**
         * token到期时间
         */
        private long expiryTime;

        private TokenModel(String token) {
            this.token = token;
            this.update();
        }

        /**
         * 刷新到期时间
         */
        private void update() {
            this.expiryTime = System.currentTimeMillis() + expiry;
        }

        /**
         * 验证token
         *
         * @param token token
         * @return true:有效, false:无效
         */
        private boolean validate(String token) {
            if (isExpired() || !token.equals(this.token)) {
                return false;
            }
            this.update();
            return true;
        }

        /**
         * 判断是否过期
         *
         * @return true:过期, false:未过期
         */
        private boolean isExpired() {
            return System.currentTimeMillis() > this.expiryTime;
        }
    }
}
