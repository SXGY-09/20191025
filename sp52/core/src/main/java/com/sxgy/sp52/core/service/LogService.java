package com.sxgy.sp52.core.service;

import com.sxgy.sp52.core.pojo.Sp52Log;

/**
 * @author SXGY_09
 * @description 日志业务接口
 * @date 2019-11-29 19:05
 */
public interface LogService {
    /**
     * 插入一条日志
     *
     * @param log 日志
     * @return affected rows
     */
    int insertLog(Sp52Log log);
}
