package com.sxgy.sp34.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxgy.sp34.bean.User;
import com.sxgy.sp34.service.UserSvc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mybatis")
@Api(tags = "mybatis测试")
public class UserController2 {
	@Resource
	private UserSvc userSvc;

	@GetMapping("/insert")
	@ApiOperation(value="插入一条记录")
	public String insert(User user) {
		int num = userSvc.insert(user);
		return "插入" + num + "条记录";
	}

	@GetMapping("/getKey")
	public void getKey(User user) {
		userSvc.insertGetKey(user);
	}

	@GetMapping("/findAll")
	@ApiOperation(value="查询所有记录",response=User.class)
	public List<User> findAll() {
		return userSvc.findAll();
	}

	@GetMapping("/update")
	public void update(User user) {
		userSvc.update(user);
	}

	@GetMapping("/delete")
	public void delete(Integer id) {
		userSvc.delete(id);
	}

}
