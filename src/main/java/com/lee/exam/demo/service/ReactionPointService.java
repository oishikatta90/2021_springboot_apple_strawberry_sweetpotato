package com.lee.exam.demo.service;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.ReactionPointRepository;
import com.lee.exam.demo.vo.ResultData;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;

	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService aritlcArticleService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = aritlcArticleService;
	}

	public boolean actorCanMakeReactionPoint(int memberId, String relTypeCode, int relId) {
			return reactionPointRepository.getSumReactionPointByMemberId(memberId, relTypeCode, relId) == 0;
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
