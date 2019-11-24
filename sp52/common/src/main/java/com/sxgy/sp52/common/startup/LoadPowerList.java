package com.sxgy.sp52.common.startup;

import com.sxgy.sp52.common.service.RoleModularService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author SXGY_09
 * @description 开机加载权限列表
 * @date 2019-11-23 21:23
 */
@Component
@Order
public class LoadPowerList implements CommandLineRunner {
    @Resource
    private RoleModularService roleModularService;

    @Override
    public void run(String... args) throws Exception {
        roleModularService.findRoleModular();
    }
}
