package com.lee.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lee.exam.demo.vo.Member;
import com.lee.exam.demo.vo.Reply;
import com.lee.exam.demo.vo.ResultData;

@Mapper
public interface ReplyRepository {

	public void writeReply(String relTypeCode, int relId, String body, int memberId);

	public int getLastInsertId();

	public List<Reply> getForPrintReplies(Member loginedMember, String relTypeCode, int relId);

	public Reply getReply(int id);

	public Reply getForPrintReply(int memberId, int id);

	public void deleteReply(int id);

	public void modifyReplyRd(int id, String body);


}
