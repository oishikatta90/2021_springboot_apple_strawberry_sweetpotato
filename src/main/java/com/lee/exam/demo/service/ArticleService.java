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
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id),"id", id);
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


	public ResultData<Article> modifyArticle(int id, String title, String body) {
		
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물을 수정하였습니다.", id),"article", article);
	}



	public ResultData actorCanModify(int actorId, Article article) {
		if (article == null) {
			return ResultData.from("F-1", "권한이 없습니다.");
		}
		
		if (article.getMemberId() != actorId) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "수정 가능합니다.");
		
		
	}
}
