package com.sxgy.sp52.business.controller;

import com.sxgy.sp52.business.service.StuService;
import com.sxgy.sp52.core.aop.Log;
import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.core.pojo.Sp52Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author SXGY_09
 * @description 业务模块：学生管理
 * @date 2020-01-01 20:08
 */
@RestController
@RequestMapping("/business/api/v1/student")
@Api(tags = "业务模块：学生管理")
public class StudentController {
    @Resource
    private StuService stuService;

    /**
     * 添加学生
     *
     * @param student 学生信息
     * @return Integer
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加学生")
    @Log(name = "业务模块：学生管理", value = "添加学生")
    public ApiRp addStu(@RequestBody Sp52Student student) {
        int records = stuService.addStudent(student);
        if (records == 1) {
            return ApiRp.success();
        } else {
            return ApiRp.fail();
        }
    }

    /**
     * 修改学生
     *
     * @param student 学生信息
     * @return Integer
     */
    @PutMapping("/edit")
    @ApiOperation(value = "修改学生")
    @Log(name = "业务模块：学生管理", value = "修改学生")
    public ApiRp updateStu(@RequestBody Sp52Student student) {
        int records = stuService.updateStudent(student);
        if (records == 1) {
            return ApiRp.success();
        } else {
            return ApiRp.fail();
        }
    }

    /**
     * 删除学生
     *
     * @param id 学号
     * @return Integer
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除学生")
    @Log(name = "业务模块：学生管理", value = "删除学生")
    public ApiRp deleteStu(@PathVariable(value = "id") Long id) {
        int records = stuService.deleteStudent(id);
        if (records == 1) {
            return ApiRp.success();
        } else {
            return ApiRp.fail();
        }
    }

    /**
     * 查询学生信息
     *
     * @param id 学号
     * @return com.sxgy.sp52.core.pojo.Sp52Student
     */
    @GetMapping("/find/{id}")
    @ApiOperation(value = "查询学生信息", response = Sp52Student.class)
    @Log(name = "业务模块：学生管理", value = "查询学生信息")
    public ApiRp findById(@PathVariable(value = "id") Long id) {
        Sp52Student student = stuService.findById(id);
        if (student == null) {
            return ApiRp.fail();
        }
        return ApiRp.success("查询成功", student);
    }

    /**
     * 查询所有学生
     *
     * @return com.sxgy.sp52.core.pojo.Sp52Student
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有学生", response = Sp52Student.class)
    @Log(name = "业务模块：学生管理", value = "查询所有学生")
    public ApiRp findAll() {
        return ApiRp.success("查询成功", stuService.findAll());
    }
}
