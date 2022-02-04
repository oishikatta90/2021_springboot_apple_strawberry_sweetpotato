package com.lee.exam.demo.service;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.MemberRepository;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Member;
import com.lee.exam.demo.vo.ResultData;

@Service
public class MemberService {	
	private MemberRepository memberRepository;
	private AttrService attrService;
	
	public MemberService(MemberRepository memberRepository, AttrService attrService) {
		this.memberRepository = memberRepository;
		this.attrService = attrService;
	}
	

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickName, String cellphoneNo,
			String email) {
		//로그인 아이디 중복체크
		Member oldMember = getMemberByLoginId(loginId);
		
		if (oldMember != null) {
			return ResultData.from("F-7",Ut.f("이미 존재하는 아이디(%s)입니다.", loginId));
		}
		
		//이름 + 이메일 중복체크
		oldMember = getMemberByNameAndEmail(name, email);
		
		if (oldMember != null) {
			return ResultData.from("F-8",Ut.f("이미 존재하는 아이디(%s)와 이메일(%s)입니다.", loginId,email));
		}
		memberRepository.join(loginId, loginPw, name, nickName, cellphoneNo, email);
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.","id", id);
	}


	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}


	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}


	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}


	public void modifyMember(int id, String loginPw, String name, String nickName, String email, String cellphoneNo) {
		memberRepository.modifyMember(id, loginPw, name, nickName, email, cellphoneNo);
	}


	public String genMemberModifyAuthKey(int actorId) {
		String memberModifyAuthKey = Ut.getTempPassword(10);

		attrService.setValue("member", actorId, "extra", "memberModifyAuthKey", memberModifyAuthKey, Ut.getDateStrLater(60 * 5));

		return memberModifyAuthKey;
	}


	public ResultData checkMemberModifyAuthKey(int actorId, String memberModifyAuthKey) {
		String saved = attrService.getValue("member", actorId, "extra", "memberModifyAuthKey");

		if ( !saved.equals(memberModifyAuthKey) ) {
			return ResultData.from("F-1", "일치하지 않거나 만료되었습니다.");
		}

		return ResultData.from("S-1", "정상적인 코드입니다.");
	}

}
