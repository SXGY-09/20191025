package com.sxgy.sp27.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TestController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/hello")
	public String hello() throws Exception{
		throw new Exception();
	}
	@RequestMapping("/test")
	public String test() throws Exception{
		System.out.println("test()......");
		int i=5/0;
		System.out.println(i);
		return "success";
	}
	@ExceptionHandler(value=Exception.class)
	public String errorHandler() {
		System.out.println("TestController errorHandler()...");
		return "系统故障";
	}
}
