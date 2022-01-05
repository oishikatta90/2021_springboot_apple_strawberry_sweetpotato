package com.lee.exam.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.MemberService;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String join(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		memberService.join(loginId,loginPw,name,nickName,cellphoneNo,email);
		return "성공!";
	}
}