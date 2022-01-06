package com.lee.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.ArticleRepository;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Article;
import com.lee.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}



	public ResultData<Integer> writeArticle(String title, String body, int memberId) {
		articleRepository.writeArticle(title, body, memberId);
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), id);
	}


	public List<Article> getArticles() {
		List<Article> article =  articleRepository.getArticles();
		return article;
	}

	
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}


	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
		
	}


	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
}
