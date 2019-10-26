package com.sxgy.sp22.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@RequestMapping("/")
	public String index(Model model) {
		System.out.println("index方法");
		return "index";
	}
	@RequestMapping("/main")
	public String tomain() {
		System.out.println("tomain方法");
		return "main";
	}
	@RequestMapping("login")
	public ModelAndView login(@RequestParam String username,
			@RequestParam String passwd,
			ModelAndView mv) {
		System.out.println("login方法");
		System.out.println("登录名："+username+"，密码："+passwd);
		mv.setViewName("redirect:/main");
		return mv;
	}
}
