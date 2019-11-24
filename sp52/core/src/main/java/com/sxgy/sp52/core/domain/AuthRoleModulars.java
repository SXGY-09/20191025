package com.sxgy.sp52.core.domain;

import com.sxgy.sp52.core.pojo.Sp52Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SXGY_09
 * @description 角色扩展类
 * @date 2019-11-23 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AuthRoleModulars角色-权限")
public class AuthRoleModulars extends Sp52Role {
    /**
     * 角色下所有权限id
     */
    @ApiModelProperty(name = "modularIds", value = "所有权限id", dataType = "java.util.List")
    private List<Long> modularIds;
    /**
     * 角色下所有权限
     */
    @ApiModelProperty(name = "modulars", value = "所有权限", dataType = "com.sxgy.sp52.core.domain.AuthModulars")
    private AuthModulars modulars;
}
