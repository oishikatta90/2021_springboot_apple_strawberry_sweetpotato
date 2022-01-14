package com.lee.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.ArticleService;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Article;
import com.lee.exam.demo.vo.ResultData;
import com.lee.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	// 인스턴스 변수 시작
	@Autowired
	private ArticleService articleService;

	// ActionMethod 시작점
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");
		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "로그인부터 해주세요");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-1", "내용을 입력해주세요");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		int id = writeArticleRd.getData1();

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		return ResultData.newData(writeArticleRd,"article", article);
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, HttpServletRequest req) {
		Rq rq = (Rq) req.getAttribute("rq");


		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId());
		
		model.addAttribute("articles",articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model,HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");

		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		model.addAttribute("article",article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(),id);

		if (article == null) {

			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id));

	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");


		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("로그인부터 해주세요");
		}
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(),id);

		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("본인이 작성 한 게시물만 삭제 가능합니다");
		}

		articleService.deleteArticle(id);
		return Ut.jsReplace(Ut.f("%d번 게시물이 삭제되었습니다.", id), "../article/list");
		
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");


		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		
		   
		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		return articleService.modifyArticle(id, title, body);

	}
	// ActionMethod 끝
}
