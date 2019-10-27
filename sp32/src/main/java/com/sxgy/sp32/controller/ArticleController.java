package com.sxgy.sp32.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxgy.sp32.bean.Article;
import com.sxgy.sp32.service.ArticleSvc;

@RestController
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleSvc articleSvc;
	@RequestMapping("/sort")
	public Iterable<Article> sortArticle(){
		//指定排序参数对象，id，降序
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Iterable<Article> articles=articleSvc.findAllSort(sort);
		return articles;
	}
	@RequestMapping("/pager")
	public List<Article> sortPagerArticle(int pageIndex){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		/**
		 * 1.current page
		 * 2.records each page
		 * 3.sort
		 */
		Pageable page=PageRequest.of(pageIndex-1, 2,sort);
		Page<Article> articles=articleSvc.findAll(page);
		System.out.println("查询总页数："+articles.getTotalPages());
		System.out.println("查询总记录数："+articles.getTotalElements());
		System.out.println("查询当前第几页："+(articles.getNumber()+1));
		System.out.println("查询当前页面的记录数："+articles.getNumberOfElements());
		List<Article> articleList=articles.getContent();
		System.out.println("查询当前页面的集合："+articleList);
		return articleList;
	}
}
