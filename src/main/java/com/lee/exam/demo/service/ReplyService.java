package com.lee.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.ReplyRepository;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Article;
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
		List<Reply> replies = replyRepository.getForPrintReplies(loginedMember, relTypeCode, relId);
		
		for (Reply reply : replies) {
			updateForPrintData(loginedMember, reply);
		}
		return replies;
	}

	private void updateForPrintData(Member loginedMember, Reply reply) {
		if (reply == null) {
			return;
		}
		
		ResultData actorCanDeleteRd = actorCanDelete(loginedMember, reply);
		reply.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
		
		ResultData actorCanModifyRd = actorCanModify(loginedMember, reply);
		reply.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
		
	}
	
	private ResultData actorCanDelete(Member loginedMember, Reply reply) {
		if (loginedMember == null) {
			return ResultData.from("F-1", "댓글이 존재하지 않습니다.");
		}
		
		if (reply.getMemberId() != loginedMember.getId()) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "댓글 삭제가 가능합니다.");
	}

	private ResultData actorCanModify(Member loginedMember, Reply reply) {
		if (loginedMember == null) {
			return ResultData.from("F-1", "댓글이 존재하지 않습니다.");
		}  
		
		if (reply.getMemberId() != loginedMember.getId()) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "댓글 수정이 가능합니다.");	}

	public Reply getForPrintReply(int memberId, int id) {
		return replyRepository.getForPrintReply(memberId, id);
	}

	public void deleteReply(int id) {
		replyRepository.deleteReply(id);
	}

	public Reply getReply(int id) {
		return replyRepository.getReply(id);  
	}

	public ResultData modifyReplyRd(int id, String body) {
		replyRepository.modifyReplyRd(id, body);
		return ResultData.from("S-1", Ut.f("%d번 댓글을 수정하였습니다.", id));
	}
}
