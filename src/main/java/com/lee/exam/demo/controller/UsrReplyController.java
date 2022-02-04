package com.lee.exam.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.ArticleService;
import com.lee.exam.demo.service.ReplyService;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Article;
import com.lee.exam.demo.vo.Reply;
import com.lee.exam.demo.vo.ResultData;
import com.lee.exam.demo.vo.Rq;

@Controller
public class UsrReplyController {
	private ReplyService replyService;
	private Rq rq;
	private ArticleService articleService;

	public UsrReplyController(Rq rq, ReplyService replyService, ArticleService articleService) {
		this.replyService = replyService;
		this.articleService = articleService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
		
		if (Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요");
		}
		if (Ut.empty(relId)) {
			return rq.jsHistoryBack("relId(을)를 입력해주세요");
		}
		if (Ut.empty(body)) {
			return rq.jsHistoryBack("내용을 입력해주세요");
		}
		

		ResultData<Integer> writeReplyRd = replyService.writeReply(relTypeCode, relId, body, rq.getLoginedMemberId());
		int id = writeReplyRd.getData1();

		if (Ut.empty(replaceUri)) {
			switch (relTypeCode) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", relId);
				break;
			}
		}
		return rq.jsReplace(Ut.f("%d번 댓글이 작성 되었습니다.", id), replaceUri);
	}
	
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, String replaceUri) {
		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);
		
		if (reply == null) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsHistoryBack("본인이 작성 한 댓글만 삭제 가능합니다");
		}
		
		if (Ut.empty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		} 
		replyService.deleteReply(id);
		return rq.jsReplace(Ut.f("%d번 댓글이 삭제되었습니다.", id), replaceUri);
		
	}
	
	@RequestMapping("/usr/reply/modify")
	public String modify(int id, String replaceUri, Model model) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("id(을)를 입력해주세요.");
		}
		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);
		
		if (reply == null) {
			return rq.historyBackJsOnView(Ut.f("%d번 댓글은 존재하지 않습니다.", id));
		}
		
		String relDataTitle = null;
		
		switch (reply.getRelTypeCode())	 {
		case "article":
			Article article = articleService.getArticle(reply.getRelId());
			relDataTitle = article.getTitle();
		}
		model.addAttribute("reply", reply);
		
		return "usr/reply/modify";
	}
	
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body, String replaceUri) {
		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);
		
		if (reply == null) {
			return rq.jsHistoryBack(Ut.f("%d번  댓글이 존재하지 않습니다.", id));
		}
		
		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsHistoryBack("본인이 작성 한 댓글만 수정 가능합니다");
		}
		
		ResultData modifyReplyRd = replyService.modifyReplyRd(id, body);
		
		if (Ut.empty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		} 
		return rq.jsReplace(modifyReplyRd.getMsg(), replaceUri);
	}
}
