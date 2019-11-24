package com.sxgy.sp52.system.service;

import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.core.domain.AuthModulars;
import com.sxgy.sp52.core.pojo.Sp52Role;
import com.sxgy.sp52.system.vo.LoginResponseVo;
import com.sxgy.sp52.system.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author SXGY_09
 * @description 人员管理业务接口
 * @date 2019-11-23 18:43
 */
public interface AdminService {
    /**
     * 登录
     *
     * @param loginVo 账号密码
     * @param request 请求
     * @return token等
     */
    LoginResponseVo ifLogin(LoginVo loginVo, HttpServletRequest request);

    /**
     * 注销
     *
     * @param request 请求
     * @return ApiRp
     */
    ApiRp ifLogout(HttpServletRequest request);

    /**
     * 根据角色查询权限
     *
     * @param roles 角色
     * @return 权限
     */
    AuthModulars findModularsByRoles(List<Sp52Role> roles);
}
