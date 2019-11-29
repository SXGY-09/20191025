package com.sxgy.sp52.core.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SXGY_09
 * @description log_name log_value
 * @date 2019-11-29 18:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleLog {
    /**
     * 模块
     */
    private String name;
    /**
     * 内容
     */
    private String value;
}
