package com.sxgy.sp52.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SXGY_09
 * @description 登录类
 * @date 2019-11-23 18:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LoginVo登录类")
public class LoginVo {
    /**
     * 账号
     */
    @ApiModelProperty(name = "username", value = "账号", dataType = "String")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码", dataType = "String")
    private String password;
}
