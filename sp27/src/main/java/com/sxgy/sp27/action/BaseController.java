package com.sxgy.sp27.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e) throws Exception{
		System.out.println("BaseController defaultErrorHandler()......");
		ModelAndView mv=new ModelAndView();
		mv.addObject("exception", e);
		mv.addObject("url",request.getRequestURL());
		mv.setViewName("error1");
		return mv;
	}
}
