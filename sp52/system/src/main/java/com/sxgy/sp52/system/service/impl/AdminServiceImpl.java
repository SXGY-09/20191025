package com.sxgy.sp52.system.service.impl;

import com.google.gson.Gson;
import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.core.domain.AuthModulars;
import com.sxgy.sp52.core.domain.AuthRoleModulars;
import com.sxgy.sp52.core.domain.MemoryData;
import com.sxgy.sp52.core.exception.ApiException;
import com.sxgy.sp52.core.pojo.Sp52Admin;
import com.sxgy.sp52.core.pojo.Sp52Role;
import com.sxgy.sp52.core.util.security.JwtUtil;
import com.sxgy.sp52.core.util.security.Md5Util;
import com.sxgy.sp52.system.mapper.Sp52AdminMapper;
import com.sxgy.sp52.system.mapper.Sp52RoleMapper;
import com.sxgy.sp52.system.service.AdminService;
import com.sxgy.sp52.system.vo.LoginResponseVo;
import com.sxgy.sp52.system.vo.LoginVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author SXGY_09
 * @description 登录业务接口实现类
 * @date 2019-11-23 18:48
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    Gson gson=new Gson();
    @Resource
    private Sp52AdminMapper adminMapper;
    @Resource
    private Sp52RoleMapper roleMapper;

    @Override
    public LoginResponseVo ifLogin(LoginVo loginVo, HttpServletRequest request) {
        Sp52Admin admin=new Sp52Admin();
        admin.setUsername(loginVo.getUsername());
        admin.setPassword(loginVo.getPassword());
        Sp52Admin oldAdmin=adminMapper.findByUsername(admin);
        long time=System.currentTimeMillis();
        if(oldAdmin==null){
            throw new ApiException("账号不存在");
        }else if(!oldAdmin.getPassword().equals(Md5Util.MD5Encode(oldAdmin.getId()+admin.getPassword()+oldAdmin.getSaltValue(),"UTF-8",true))){
            throw new ApiException("账号不存在或密码错误");
        }
        Long adminId=oldAdmin.getId();
        String username=oldAdmin.getUsername();
        //查询账号角色信息
        List<Sp52Role> roles=roleMapper.findByAdminId(adminId);
        //生成token
        String token = JwtUtil.getToken(adminId,username,roles);
        LoginResponseVo loginResponseVo=new LoginResponseVo();
        loginResponseVo.setToken(token);
        loginResponseVo.setIssuedAt(new Date(time));
        loginResponseVo.setAdmin(oldAdmin);
        loginResponseVo.setRoles(roles);
        loginResponseVo.setAuthModulars(findModularsByRoles(roles));

        HttpSession session=request.getSession();
        session.setAttribute("id",adminId);
        session.setAttribute("name", username);
        session.setAttribute("roles",roles);
        return loginResponseVo;
    }

    @Override
    public ApiRp ifLogout(HttpServletRequest request) {
        Long adminId= (Long) request.getSession().getAttribute("id");
        String tokenKey="i"+adminId;
        if(MemoryData.getTokenMap().containsKey(tokenKey)){
            MemoryData.getTokenMap().removeToken(tokenKey);
        }
        return ApiRp.success("注销成功");
    }

    @Override
    public AuthModulars findModularsByRoles(List<Sp52Role> roles) {
        List<AuthRoleModulars> authRoleModulars= (List<AuthRoleModulars>) MemoryData.getRoleModularMap().get("authRoleModulars");
        Long finalRoleId=roles.get(0).getId();
        Optional<AuthRoleModulars> authRoleModulars1=authRoleModulars.stream()
                .filter(authRoleModulars2 -> finalRoleId.equals(authRoleModulars2.getId()))
                .findFirst();
        if(authRoleModulars1.isPresent()){
            return authRoleModulars1.get().getModulars();
        }
        return null;
    }
}
