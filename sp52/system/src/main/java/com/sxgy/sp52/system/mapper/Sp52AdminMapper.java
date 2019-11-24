package com.sxgy.sp52.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgy.sp52.core.pojo.Sp52Admin;

public interface Sp52AdminMapper extends BaseMapper<Sp52Admin> {
    /**
     * 插入返回主键
     * @param record 记录
     * @return 主键
     */
    Long insertSelective(Sp52Admin record);

    /**
     * 通过用户名查询账号
     * @param admin 用户
     * @return 管理员信息
     */
    Sp52Admin findByUsername(Sp52Admin admin);
}