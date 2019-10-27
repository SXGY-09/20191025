package com.sxgy.sp32.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sxgy.sp32.bean.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

}
