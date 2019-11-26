package com.sxgy.sp52.core.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SXGY_09
 * @description 内存数据
 * @date 2019-11-16 12:28
 */
@Data
public class MemoryData {
    //用于存储 用户-token，默认10分钟过期
    private static ExpiryMap tokenMap = new ExpiryMap();
    //用于存储 角色-权限
    private static Map roleModularMap = new HashMap();
    public static List<AuthRoleModulars> authRoleModulars;

    public static ExpiryMap getTokenMap() {
        return tokenMap;
    }

    public static Map getRoleModularMap() {
        return roleModularMap;
    }

    public static void setRoleModularMap(Map roleModularMap) {
        MemoryData.roleModularMap = roleModularMap;
    }
}
