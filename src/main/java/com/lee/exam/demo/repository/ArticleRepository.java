package com.lee.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lee.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	@Insert("insert into article set regDate=now(), updateDate=now(), title= #{title}, `body`= #{body}")
	public Article writeArticle(String title, String body);
	
	@Select("select * from article where id = #{id}")
	public Article getArticle(@Param("id") int id);

	@Select("select * from article order by id desc")
	public List<Article> getArticles();
	
	@Delete("delete from article where id = #{id}")
	public void deleteArticle(@Param("id")int id);
	
	@Update("update article set title = #{title}, `body` =#{body} where id = #{id}")
	public void modifyArticle(int id, String title, String body);
}
