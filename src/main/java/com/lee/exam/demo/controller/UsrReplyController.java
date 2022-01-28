package com.lee.exam.demo.controller;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	public UsrReplyController(Rq rq, ReplyService replyService) {
		this.replyService = replyService;
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
		int relId;
		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);

		if (reply == null) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsHistoryBack("본인이 작성 한 댓글만 삭제 가능합니다");
		}

		replyService.deleteReply(id);
		replaceUri = Ut.f("../article/detail?id=%d", id);
		return rq.jsReplace(Ut.f("%d번 게시물이 삭제되었습니다.", id), replaceUri);

	}
	

}
