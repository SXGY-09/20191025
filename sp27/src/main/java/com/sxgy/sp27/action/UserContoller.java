package com.sxgy.sp27.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//抛出异常时，会使用父类定义的异常处理方法
@Controller
public class UserContoller extends BaseController{
	@RequestMapping("/login")
	public String login(String username) throws Exception{
		System.out.println("login()......");
		if(username==null) {
			throw new NullPointerException("用户名不存在！");
		}
		return "success";
	}
	@RequestMapping("/find")
	public String find() {
		System.out.println("find()......");
		int i=5/0;
		System.out.println(i);
		return "success";
	}
}
