package com.sxgy.sp52.business.controller;

import com.sxgy.sp52.business.service.StudentService;
import com.sxgy.sp52.core.pojo.SpStudent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SXGY_09
 * @description 学生管理
 * @date 2019-11-15 22:20
 */
@RestController
@RequestMapping(value = "business/api/v1/student",method = {RequestMethod.GET,RequestMethod.POST})
@Api(tags = "学生管理")
public class StuController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/save")
    @ApiOperation(value = "添加学生",response = Long.class)
    public Long save(SpStudent student){
        return studentService.save(student);
    }
}
