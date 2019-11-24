package com.sxgy.sp52.system.vo;

import com.sxgy.sp52.core.domain.AuthModulars;
import com.sxgy.sp52.core.pojo.Sp52Admin;
import com.sxgy.sp52.core.pojo.Sp52Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author SXGY_09
 * @description 登录返回类
 * @date 2019-11-23 18:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LoginResponseVo登录返回类")
public class LoginResponseVo {
    @ApiModelProperty(name = "token", value = "token", dataType = "String")
    private String token;
    @ApiModelProperty(name = "issuedAt", value = "token签发时间", dataType = "java.util.Date")
    private Date issuedAt;
    @ApiModelProperty(name = "roles", value = "用户角色集合", dataType = "java.util.List")
    private List<Sp52Role> roles;
    @ApiModelProperty(name = "authModulars", value = "用户权限集合", dataType = "com.sxgy.sp52.core.domain.AuthModulars")
    private AuthModulars authModulars;
    @ApiModelProperty(name = "admin", value = "管理员信息", dataType = "com.sxgy.sp52.core.pojo.Sp52Admin")
    private Sp52Admin admin;
}
