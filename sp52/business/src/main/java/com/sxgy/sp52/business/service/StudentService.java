package com.sxgy.sp52.business.service;

import com.sxgy.sp52.core.pojo.SpStudent;

/**
 * @author SXGY_09
 * @description 学生业务接口
 * @date 2019-11-15 22:21
 */
public interface StudentService {
    /**
     * 插入记录返回主键
     *
     * @param student 学生信息
     * @return java.lang.Long
     */
    Long save(SpStudent student);
}
