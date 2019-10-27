package com.sxgy.sp33.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxgy.sp33.bean.Clz;
import com.sxgy.sp33.bean.Stu;
import com.sxgy.sp33.service.SpecificationSvc;
import com.sxgy.sp33.vo.PageData;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
	@Resource
	private SpecificationSvc specificationSvc;
	@RequestMapping("/save")
	public String save() {
		Clz clz1=new Clz("201901");
		Clz clz2=new Clz("201902");
		List<Clz> clzs=new ArrayList<>();
		clzs.add(clz1);
		clzs.add(clz2);
		specificationSvc.saveClzAll(clzs);
		Stu stu1=new Stu("孙悟空", "花果山", 700, '男', clz1);
		Stu stu2=new Stu("蜘蛛精", "广州", 700, '女', clz1);
		Stu stu3=new Stu("牛魔王", "广州", 500, '男', clz2);
		Stu stu4=new Stu("紫霞仙子","盘丝洞",500,'女',clz1);
		Stu stu5=new Stu("至尊宝","广州",500,'男',clz1);
		Stu stu6=new Stu("铁扇公主","火焰山",500,'女',clz2);
		List<Stu> stus=new ArrayList<>();
		stus.add(stu1);
		stus.add(stu2);
		stus.add(stu3);
		stus.add(stu4);
		stus.add(stu5);
		stus.add(stu6);
		specificationSvc.saveStuAll(stus);
		return "保存学生对象成功";
	}
	@RequestMapping("/getBySex")
	public List<Map<String,Object>> getBySex(char sex){
		return specificationSvc.getStusBySex(sex);
	}
	@RequestMapping("/getByDynamic")
	public List<Map<String,Object>> getByDynamic(Stu stu){
		return specificationSvc.getStusByDynamic(stu);
	}
	@RequestMapping("/getByPages")
	public PageData getByPages(String clzName,int pageIndex,int pageSize) {
		Page<Stu> page=specificationSvc.getStusByPage(clzName, pageIndex, pageSize);
		List<Stu> stus=page.getContent();
		List<Map<String,Object>> data=new ArrayList<>();
		for(Stu stu:stus) {
			Map<String,Object> map=new HashMap<>();
			map.put("name", stu.getName());
			map.put("age", stu.getAge());
			map.put("sex",stu.getSex());
			map.put("address", stu.getAddress());
			map.put("id", stu.getId());
			map.put("clzName", stu.getClz().getName());
			data.add(map);
		}
		PageData pgd=new PageData();
		pgd.setData(data);
		pgd.setPageIndex(page.getNumber()+1);
		pgd.setPageSize(page.getTotalPages());
		pgd.setTotalCount(page.getTotalElements());
		pgd.setPageNum(page.getSize());
		return pgd;
	}
}
