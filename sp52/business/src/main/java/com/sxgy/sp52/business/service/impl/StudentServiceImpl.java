package com.sxgy.sp52.business.service.impl;

import com.sxgy.sp52.business.service.StudentService;
import com.sxgy.sp52.core.mapper.SpStudentMapper;
import com.sxgy.sp52.core.pojo.SpStudent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author SXGY_09
 * @description 学生业务接口实现类
 * @date 2019-11-15 22:22
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private SpStudentMapper studentMapper;

    /**
     * 插入记录返回主键
     *
     * @param student 学生信息
     * @return java.lang.Long
     */
    @Override
    public Long save(SpStudent student) {
        return studentMapper.insertReturnKey(student);
    }
}
