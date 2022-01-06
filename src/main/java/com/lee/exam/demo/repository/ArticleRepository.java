package com.lee.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lee.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	public void writeArticle(@Param("title") String title, @Param("body") String body, @Param("memberId")int memberId);

	public Article getArticle(@Param("id") int id);

	public List<Article> getArticles();

	public int getLastInsertId();

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(int id, String title, String body);
}
