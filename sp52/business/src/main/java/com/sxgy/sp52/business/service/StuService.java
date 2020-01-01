package com.sxgy.sp52.business.service;

import com.sxgy.sp52.core.pojo.Sp52Student;

import java.util.List;

/**
 * @author SXGY_09
 * @description 学生业务接口
 * @date 2020-01-01 20:14
 */
public interface StuService {
    /**
     * 添加一个学生
     *
     * @param student 学生信息
     * @return 记录数
     */
    int addStudent(Sp52Student student);

    /**
     * 修改学生信息
     *
     * @param student 学生信息
     * @return 记录数
     */
    int updateStudent(Sp52Student student);

    /**
     * 删除一个学生
     *
     * @param id 学号
     * @return 记录数
     */
    int deleteStudent(Long id);

    /**
     * 查询学生信息
     *
     * @param id 学号
     * @return 学生信息
     */
    Sp52Student findById(Long id);

    /**
     * 查询所有学生信息
     *
     * @return 学生信息
     */
    List<Sp52Student> findAll();
}

