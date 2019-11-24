package com.sxgy.sp52.common.service.impl;

import com.sxgy.sp52.common.mapper.Sp52ModularMapper;
import com.sxgy.sp52.common.mapper.Sp52RoleModularMapper;
import com.sxgy.sp52.common.service.RoleModularService;
import com.sxgy.sp52.core.domain.AuthModulars;
import com.sxgy.sp52.core.domain.AuthRoleModulars;
import com.sxgy.sp52.core.domain.MemoryData;
import com.sxgy.sp52.core.util.clone.CloneUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author SXGY_09
 * @description 角色-权限业务接口实现
 * @date 2019-11-23 21:28
 */
@Service
@Slf4j
@Transactional
public class RoleModularServiceImpl implements RoleModularService {
    @Resource
    private Sp52ModularMapper modularMapper;
    @Resource
    private Sp52RoleModularMapper roleModularMapper;
    @Override
    public List<AuthRoleModulars> findRoleModular() {
        log.info("=====读取权限信息====");
        //查询系统所有权限
        AuthModulars authModulars = modularMapper.findAllAuthModulars();
        //查询系统所有角色的权限信息
        List<AuthRoleModulars> authRoleModulars = roleModularMapper.findRoleModular();
        MemoryData.getRoleModularMap().put("authModulars",authModulars);
        MemoryData.getRoleModularMap().put("authRoleModulars",authRoleModulars);

        //将系统权限添加到角色权限对象集合
        for (AuthRoleModulars authRoleModulars1 : authRoleModulars){
            AuthModulars authModulars1 = null;
            try {
                authModulars1 = CloneUtil.clone(authModulars);
            } catch (Exception e) {
                e.printStackTrace();
                log.debug("========clone失败============");
            }
            authRoleModulars1.setModulars(authModulars1);
        }

        //清理角色没有的权限
        Iterator<AuthRoleModulars> itr1 = authRoleModulars.iterator();
        while (itr1.hasNext()){
            AuthRoleModulars authRoleModulars1 = itr1.next();
            AuthModulars authModulars1 = new AuthModulars();
            //迭代获得3级目录
            Iterator<AuthModulars> itm2 = authRoleModulars1.getModulars().getChildren().iterator();
            while (itm2.hasNext()){
                AuthModulars authModulars2 = itm2.next();
                //迭代获得3级目录
                Iterator<AuthModulars> itm3 = authModulars2.getChildren().iterator();
                while (itm3.hasNext()){
                    AuthModulars authModulars3 = itm3.next();

                    //迭代获得权限列表
                    Iterator<AuthModulars> itm0 =  authModulars3.getChildren().iterator();
                    while (itm0.hasNext()){
                        AuthModulars authModulars00 = itm0.next();
                        //是否删除此元素
                        boolean b = true;
                        //遍历此角色权限集合
                        for (Long modularId : authRoleModulars1.getModularIds()){
                            if(authModulars00.getId().equals(modularId)){
                                b = false;
                            }
                        }
                        if(b){
                            itm0.remove();
                        }
                    }
                    //删除没有子节点的父节点
                    if(authModulars3.getChildren().size() == 0){
                        itm3.remove();
                    }
                }


                //删除没有子节点的父节点
                if(authModulars2.getChildren().size() == 0){
                    itm2.remove();
                }

            }

            //删除没有子节点的父节点
            if(authRoleModulars1.getModulars().getChildren().size() == 0){
                itr1.remove();
            }
        }
        MemoryData.getRoleModularMap().put("modulars",authRoleModulars);
        return authRoleModulars;
    }
}
