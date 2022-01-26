package com.lee.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.ReplyRepository;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Member;
import com.lee.exam.demo.vo.Reply;
import com.lee.exam.demo.vo.ResultData;
import com.lee.exam.demo.vo.Rq;

@Service
public class ReplyService {
	private ReplyRepository replyRepository;
	private Rq rq;

	public ReplyService(Rq rq, ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
		this.rq = rq;
	}

	public ResultData<Integer> writeReply(String relTypeCode, int relId, String body, int memberId) {
		replyRepository.writeReply(relTypeCode, relId, body, memberId);
		int id = replyRepository.getLastInsertId();
		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다.", id), "id", id);
	}

	public List<Reply> getForPrintReplies(Member loginedMember, String relTypeCode, int relId) {
		return replyRepository.getForPrintReplies(loginedMember, relTypeCode, relId);
	}
}
