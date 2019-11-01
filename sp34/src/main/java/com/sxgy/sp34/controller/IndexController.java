package com.sxgy.sp34.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	@GetMapping("/api")
	public ModelAndView api() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/swagger-ui.html");
		return mv;
	}
}
