package com.lee.exam.demo.service;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.MemberRepository;
import com.lee.exam.demo.vo.Member;

@Service
public class MemberService {	
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	

	public void join(String loginId, String loginPw, String name, String nickName, String cellphoneNo,
			String email) {
		memberRepository.join(loginId, loginPw, name, nickName, cellphoneNo, email);
	}

}
