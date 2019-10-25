package com.sxgy.sp21.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpController {
	@RequestMapping("/sp")
	public String sp() {
		return "welcome to site sp!";
	}
}
