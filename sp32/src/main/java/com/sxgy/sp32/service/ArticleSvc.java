package com.sxgy.sp32.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sxgy.sp32.bean.Article;
import com.sxgy.sp32.repository.ArticleRepository;
@Service
public class ArticleSvc {
	@Resource
	private ArticleRepository articleRepository;

	public Iterable<Article> findAllSort(Sort sort) {
		return articleRepository.findAll(sort);
	}

	public Page<Article> findAll(Pageable page) {
		return articleRepository.findAll(page);
	}
}
