package com.lee.exam.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.MemberService;
import com.lee.exam.demo.vo.Member;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		if (loginId == null || loginId.trim().length() == 0) {
			return "loginId(을)를 입력해주세요.";
		}
		if (loginPw == null || loginPw.trim().length() == 0) {
			return "loginPw(을)를 입력해주세요.";
		}
		if (name == null || name.trim().length() == 0) {
			return "name(을)를 입력해주세요.";
		}
		if (nickName == null || nickName.trim().length() == 0) {
			return "nickName(을)를 입력해주세요.";
		}
		if (cellphoneNo == null || cellphoneNo.trim().length() == 0) {
			return "cellphoneNo(을)를 입력해주세요.";
		}
		if (email == null || email.trim().length() == 0) {
			return "email(을)를 입력해주세요.";
		}
		
		int id = memberService.join(loginId,loginPw,name,nickName,cellphoneNo,email);
		System.out.println(id);
		
		if (id == -1) {
			return "이미 등록된 회원 아이디입니다.";
		}
		Member member = memberService.getMemberById(id);
		
		return member;
	}
}
