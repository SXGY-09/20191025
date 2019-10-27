package com.sxgy.sp31.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxgy.sp31.bean.User;
import com.sxgy.sp31.service.UserSvc;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
	@Resource
	private UserSvc userSvc;

	@RequestMapping("/save")
	public String save() {
		User user = new User();
		user.setLoginName("dlei");
		user.setUsername("许雷");
		user.setSex('男');
		user.setAge(3);
		userSvc.save(user);
		return "保存数据成功";
	}

	@RequestMapping("/update")
	public String update() {
		// 修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
		User user = userSvc.getById(1);
		userSvc.update(user);
		return "修改成功";
	}

	@RequestMapping("/delete")
	public String delete() {
		userSvc.delete(1);
		return "删除数据成功";
	}

	@RequestMapping("/select")
	public Iterable<User> getAll() {
		return userSvc.getAll();
	}
}
