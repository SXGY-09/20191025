package com.sxgy.sp52.system.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SXGY_09
 * @description 后台管理员模块
 * @date 2019-11-16 09:16
 */
@RestController
@RequestMapping(value = "/system/api/v1/admin",method = {RequestMethod.POST,RequestMethod.GET})
@Api(tags = "后台管理员模块")
public class AdminController {
}
