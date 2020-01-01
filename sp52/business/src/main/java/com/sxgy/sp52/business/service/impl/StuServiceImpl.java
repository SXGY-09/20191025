package com.sxgy.sp52.business.service.impl;

import com.sxgy.sp52.business.mapper.Sp52StudentMapper;
import com.sxgy.sp52.business.service.StuService;
import com.sxgy.sp52.core.pojo.Sp52Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SXGY_09
 * @description 描述
 * @date 2020-01-01 20:19
 */
@Service
public class StuServiceImpl implements StuService {
    @Resource
    private Sp52StudentMapper studentMapper;
    @Override
    public int addStudent(Sp52Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int updateStudent(Sp52Student student) {
        return studentMapper.updateById(student);
    }

    @Override
    public int deleteStudent(Long id) {
        return studentMapper.deleteById(id);
    }

    @Override
    public Sp52Student findById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Sp52Student> findAll() {
        return studentMapper.findAll();
    }
}
