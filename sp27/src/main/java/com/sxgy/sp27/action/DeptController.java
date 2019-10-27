package com.sxgy.sp27.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeptController {
	@RequestMapping("/add")
	public String add(String dept) throws Exception{
		System.out.println("add()......");
		if(dept==null) {
			throw new NullPointerException("部门名称不能为空!");
		}
		return "success";
	}
}
