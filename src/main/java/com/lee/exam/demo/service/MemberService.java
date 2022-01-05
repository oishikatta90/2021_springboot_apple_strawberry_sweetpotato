package com.lee.exam.demo.service;

import org.springframework.stereotype.Service;

import com.lee.exam.demo.repository.MemberRepository;
import com.lee.exam.demo.vo.Member;

@Service
public class MemberService {

	//private MemberRepository memberRepository;

	public void join(String loginId, String loginPw, String name, String nickName, String cellphoneNo,
			String email) {
		//return memberRepository.join();
	}

}
