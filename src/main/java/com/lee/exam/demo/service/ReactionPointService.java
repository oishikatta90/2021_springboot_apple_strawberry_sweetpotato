package com.lee.exam.demo.service;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.ReactionPointRepository;
import com.lee.exam.demo.vo.ResultData;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;

	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = articleService;
	}   

	public ResultData actorCanMakeReactionPoint(int memberId, String relTypeCode, int relId) {
		if (memberId == 0) {
			return ResultData.from("F-1", "로그인 후 이용해주세요.");
		}
		int  SumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(memberId, relTypeCode, relId);
		
		if (SumReactionPointByMemberId != 0) {
			return ResultData.from("F-2", "리액션이 불가능합니다.","sumReactionPointByMemberId",SumReactionPointByMemberId);
		}
		
		return ResultData.from("S-1", "리액션이 가능합니다.","sumReactionPointByMemberId",SumReactionPointByMemberId);
	}

	public ResultData addGoodReactionPoint(int memberId, String relTypeCode, int relId) {    
		reactionPointRepository.addGoodReactionPoint(memberId, relTypeCode, relId);
		
		switch(relTypeCode) {
		case "article": articleService.increaseGoodReactionPoint(relId);
			break;
		}
		
		return ResultData.from("S-1", "좋아요 처리되었습니다");
		
	}

	public ResultData addBadReactionPoint(int memberId, String relTypeCode, int relId) {
		reactionPointRepository.addBadReactionPoint(memberId, relTypeCode, relId);
		
	 	switch(relTypeCode) {
		case "article": articleService.increaseBadReactionPoint(relId);
			break;
		}
		
		return ResultData.from("S-1", "싫어요 처리되었습니다.");
	}
}
