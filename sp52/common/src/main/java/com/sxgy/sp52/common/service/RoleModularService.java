package com.sxgy.sp52.common.service;

import com.sxgy.sp52.core.domain.AuthRoleModulars;

import java.util.List;

/**
 * @author SXGY_09
 * @description 角色-权限业务接口
 * @date 2019-11-23 21:26
 */
public interface RoleModularService {
    /**
     * 查询角色权限 树形结构封装
     * @return AuthModulars
     */
    List<AuthRoleModulars> findRoleModular();
}
