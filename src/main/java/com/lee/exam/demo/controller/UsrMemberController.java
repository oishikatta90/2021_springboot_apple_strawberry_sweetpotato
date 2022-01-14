package com.lee.exam.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.MemberService;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Member;
import com.lee.exam.demo.vo.ResultData;
import com.lee.exam.demo.vo.Rq;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요.");
		}
		if (Ut.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해주세요.");
		}
		if (Ut.empty(nickName)) {
			return ResultData.from("F-4", "nickName(을)를 입력해주세요.");
		}
		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해주세요.");
		}
		if (Ut.empty(email)) {
			return ResultData.from("F-6", "email(을)를 입력해주세요.");
		}
		
		//joinRd 안에는
		//S-1, 회원가입완료메세지, id(ex7(번호))
		ResultData<Integer> joinRd = memberService.join(loginId,loginPw,name,nickName,cellphoneNo,email);
		
		if (joinRd.isFail()) {
			return (ResultData)joinRd;
		}
		
		Member member = memberService.getMemberById(joinRd.getData1());
		
		return ResultData.newData(joinRd,"member", member);
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpSession httpSession) {
		return "/usr/member/login";
	}
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
		Rq rq = (Rq)req.getAttribute("rq");
		//로그인을 하면 세션에 loginedMemberId 이름으로
		//로그인 아이디가 저장된다. if문으로 저장된 값이 있냐
		//물었을 때 있으면 isLogined를 true로 바꿔줘서 로그인 중으로 표시
		
		if (rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그인 되셨습니다.");
		}
		
		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("loginId(을)를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		//joinRd 안에는
		//S-1, 회원가입완료메세지, id(ex7(번호))
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Ut.jsHistoryBack("없는 아이디입니다.");
		}
		
		if (!member.getLoginPw().equals(loginPw)) {
			return Ut.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		rq.login(member);
		
		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickName()),"/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		if (rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그아웃 하셨습니다.");
		}
		
		rq.logout();
		
		return Ut.jsReplace("로그아웃 되셨습니다.","/");
	}
}