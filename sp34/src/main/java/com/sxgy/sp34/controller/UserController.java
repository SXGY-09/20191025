package com.sxgy.sp34.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxgy.sp34.bean.User;
import com.sxgy.sp34.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/jdbcTemplate")
@Api(tags="jdbcTemplate测试")
public class UserController {
	@Resource
	private UserService userService;
	@GetMapping("/insert")
	public String insert() {
		int num=userService.insertUser();
		return "插入"+num+"条记录";
	}
	@GetMapping("/getKey")
	public User getKey(User user) {
		return userService.insertGetKey(user);
	}
	@GetMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	@GetMapping("/update")
	public void update(User user) {
		userService.update(user);
	}
	@GetMapping("/delete")
	public void delete(Integer id) {
		userService.delete(id);
	}
}
