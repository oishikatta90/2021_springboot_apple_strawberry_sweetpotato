package com.lee.exam.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.exam.demo.service.MemberService;
import com.lee.exam.demo.util.Ut;
import com.lee.exam.demo.vo.Article;
import com.lee.exam.demo.vo.Member;
import com.lee.exam.demo.vo.ResultData;
import com.lee.exam.demo.vo.Rq;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	private Rq rq;
	
	public UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
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
	
	@RequestMapping("/usr/member/signUp")
	public String showSignUp(HttpSession httpSession) {
		return "/usr/member/signUp";
	}
	@RequestMapping("/usr/member/doSignUp")
	public String doSignUp(HttpSession httpSession) {
		return "/usr/member/signUp";
	}
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpSession httpSession) {
		return "/usr/member/login2";
	}
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		//로그인을 하면 세션에 loginedMemberId 이름으로
		//로그인 아이디가 저장된다. if문으로 저장된 값이 있냐
		//물었을 때 있으면 isLogined를 true로 바꿔줘서 로그인 중으로 표시
		if (Ut.empty(loginId)) {
			return rq.jsHistoryBack("loginId(을)를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		//joinRd 안에는
		//S-1, 회원가입완료메세지, id(ex7(번호))
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return rq.jsHistoryBack("없는 아이디입니다.");
		}
		
		if (!member.getLoginPw().equals(loginPw)) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		rq.login(member);
		
		return rq.jsReplace(Ut.f("%s님 환영합니다.", member.getNickName()),"/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		if (!rq.isLogined()) {
			return rq.jsHistoryBack("이미 로그아웃 하셨습니다.");
		}
		
		rq.logout();
		
		return rq.jsReplace("로그아웃 되셨습니다.","/");
	}
	
	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {
		return "usr/member/myPage";
	}
	
	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword() {
		return "usr/member/checkPassword";
	}
	
	@RequestMapping("/usr/member/doCheckPassword")
	@ResponseBody
	public String doCheckPassword(String loginPw, String replaceUri) {
		if (Ut.empty(loginPw)) {
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		if (!rq.getLoginedMember().getLoginPw().equals(loginPw)) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		return rq.jsReplace("",replaceUri);
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify() {
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(int id, String loginPw, String name, String nickName, String email, String cellphoneNo) {
		if (Ut.empty(loginPw)) {
			loginPw = null;
		}
		if (Ut.empty(name)) {
			return rq.jsHistoryBack("이름을 입력해주세요.");
		}
		if (Ut.empty(nickName)) {
			return rq.jsHistoryBack("별명을 입력해주세요.");
		}
		if (Ut.empty(email)) {
			return rq.jsHistoryBack("이메일을 입력해주세요.");
		}
		if (Ut.empty(cellphoneNo)) {
			return rq.jsHistoryBack("전화번호를 입력해주세요.");
		}
		
		memberService.modifyMember(rq.getLoginedMemberId(), loginPw, name, nickName, email, cellphoneNo);
		return rq.jsReplace("회원정보가 수정되었습니다.", "../member/myPage");

	}

	
}