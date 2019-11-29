package com.sxgy.sp52.core.service.impl;

import com.sxgy.sp52.core.mapper.Sp52LogMapper;
import com.sxgy.sp52.core.pojo.Sp52Log;
import com.sxgy.sp52.core.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author SXGY_09
 * @description 日志业务接口实现类
 * @date 2019-11-29 19:06
 */
@Service
public class LogServiceImpl implements LogService {
    @Resource
    private Sp52LogMapper logMapper;

    @Override
    public int insertLog(Sp52Log log) {
        return logMapper.insert(log);
    }
}
