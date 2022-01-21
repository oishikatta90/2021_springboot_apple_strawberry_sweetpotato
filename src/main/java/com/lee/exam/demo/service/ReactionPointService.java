package com.lee.exam.demo.service;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.ReactionPointRepository;

@Service
public class ReactionPointService {
	
	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public boolean actorCanMakeReactionPoint(int memberId, String relTypeCode, int relId) {
			return reactionPointRepository.getSumReactionPointByMemberId(memberId, relTypeCode, relId) == 0;
		}
}
