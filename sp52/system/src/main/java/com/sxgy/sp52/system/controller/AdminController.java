package com.sxgy.sp52.system.controller;

import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.system.service.AdminService;
import com.sxgy.sp52.system.vo.LoginResponseVo;
import com.sxgy.sp52.system.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SXGY_09
 * @description 系统模块：人员管理
 * @date 2019-11-23 18:16
 */
@RestController
@Api(tags = "系统模块：人员管理")
@RequestMapping("/system/api/v1/account")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param loginVo 账号密码
     * @param request 请求
     * @return 登录token等信息
     */
    @PostMapping("/ifLogin")
    @ApiOperation(value = "登录", response = LoginResponseVo.class)
    public ApiRp ifLogin(@RequestBody LoginVo loginVo, HttpServletRequest request){
        return ApiRp.success("登录成功", adminService.ifLogin(loginVo,request));
    }

    /**
     * 注销
     * @param request 请求
     * @return msg
     */
    @DeleteMapping("/ifLogout")
    @ApiOperation(value = "注销")
    public ApiRp ifLogout(HttpServletRequest request){
        return adminService.ifLogout(request);
    }
}
