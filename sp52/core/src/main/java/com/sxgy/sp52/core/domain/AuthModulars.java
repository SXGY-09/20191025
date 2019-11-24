package com.sxgy.sp52.core.domain;

import com.sxgy.sp52.core.pojo.Sp52Modular;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SXGY_09
 * @description Sp52Modular的扩展类
 * @date 2019-11-23 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AuthModular模块扩展类")
public class AuthModulars extends Sp52Modular {
    /**
     * 子模块
     */
    @ApiModelProperty(name = "children", value = "子模块节点", dataType = "java.util.List")
    private List<AuthModulars> children;
}
