package com.sxgy.sp52.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgy.sp52.core.domain.AuthRoleModulars;
import com.sxgy.sp52.core.pojo.Sp52RoleModular;

import java.util.List;

public interface Sp52RoleModularMapper extends BaseMapper<Sp52RoleModular> {
    /**
     * 查询角色-权限信息，树形结构封装
     * @return AuthRoleModulars
     */
    List<AuthRoleModulars> findRoleModular();
}