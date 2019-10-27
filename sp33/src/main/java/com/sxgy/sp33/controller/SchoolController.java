package com.sxgy.sp33.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxgy.sp33.bean.Clz;
import com.sxgy.sp33.bean.Stu;
import com.sxgy.sp33.service.SchoolSvc;

@RestController
@RequestMapping("/school")
public class SchoolController {
	@Resource
	private SchoolSvc schoolSvc;
	@RequestMapping("/save")
	public String save() {
		Clz clz1=new Clz("201901");
		Clz clz2=new Clz("201902");
		List<Clz> clzs=new ArrayList<>();
		clzs.add(clz1);
		clzs.add(clz2);
		schoolSvc.saveClzAll(clzs);
		Stu stu1=new Stu("孙悟空", "广州", 700, '男', clz1);
		Stu stu2=new Stu("蜘蛛精", "广州", 700, '女', clz1);
		Stu stu3=new Stu("牛魔王", "广州", 500, '男', clz2);
		List<Stu> stus=new ArrayList<>();
		stus.add(stu1);
		stus.add(stu2);
		stus.add(stu3);
		schoolSvc.saveStuAll(stus);
		return "保存对象成功";
	}
	@RequestMapping("/getClzStus")
	public List<Map<String,Object>> getCzStus(String clzName){
		return schoolSvc.getStusByClzName(clzName);
	}
	@RequestMapping("/findNameAndSex")
	public List<Map<String,Object>> getNameAndSex(String clzName){
		return schoolSvc.findNameAndSexByClzzName(clzName);
	}
	@RequestMapping("/findName")
	public List<String> findName(String clzName,Character sex){
		return schoolSvc.findNameByClzNameAndSex(clzName, sex);
	}
	@RequestMapping("/findClzName")
	public String findClzName(String stuName) {
		return schoolSvc.findClzNameByStuName(stuName);
	}
	@RequestMapping("/deleteStu")
	public String deleteStu(String stuName) {
		return "删除数据："+schoolSvc.deleteByStuName(stuName);
	}
	@RequestMapping("/namedQuery")
	public List<Stu> customFind(String clzName){
		return schoolSvc.customFind(clzName);
	}
}
