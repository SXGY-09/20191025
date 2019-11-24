package com.sxgy.sp52.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgy.sp52.core.domain.AuthModulars;
import com.sxgy.sp52.core.pojo.Sp52Modular;
public interface Sp52ModularMapper extends BaseMapper<Sp52Modular> {
    AuthModulars findAllAuthModulars();
}