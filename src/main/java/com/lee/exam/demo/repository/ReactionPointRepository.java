package com.lee.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReactionPointRepository {

	public int getSumReactionPointByMemberId(int memberId, String relTypeCode, int relId);

	public void addGoodReactionPoint(int memberId, String relTypeCode, int relId);

	public void addBadReactionPoint(int memberId, String relTypeCode, int relId);

}
