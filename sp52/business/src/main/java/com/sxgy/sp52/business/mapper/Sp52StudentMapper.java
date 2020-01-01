package com.sxgy.sp52.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgy.sp52.core.pojo.Sp52Student;

import java.util.List;

public interface Sp52StudentMapper extends BaseMapper<Sp52Student> {
    List<Sp52Student> findAll();
}