package com.lee.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.ArticleService;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Article;
import com.lee.exam.demo.vo.ResultData;


@Controller
public class UsrArticleController {
	//인스턴스 변수 시작
	@Autowired
	private ArticleService articleService;

	
	//ActionMethod 시작점
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-1", "내용을 입력해주세요");
		}
		
		ResultData writeArticleRd = articleService.writeArticle(title, body);
		int id = (int) writeArticleRd.getData1();
		
		Article article = articleService.getArticle(id);
		
		return ResultData.from(writeArticleRd.getResultCode(),writeArticleRd.getMsg(),writeArticleRd.getData1());
	}
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles(String title, String body) {
		return articleService.getArticles();
		
	}
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id));
		
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) { 
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		articleService.deleteArticle(id);
		return id + "번 게시물이 삭제되었습니다.";
		
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		articleService.modifyArticle(id, title, body);
		return id + "번 게시물이 수정되었습니다.";
		
	}
	//ActionMethod 끝
}
