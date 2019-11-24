package com.sxgy.sp52.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgy.sp52.core.pojo.Sp52Role;

import java.util.List;

public interface Sp52RoleMapper extends BaseMapper<Sp52Role> {
    List<Sp52Role> findByAdminId(Long adminId);
}