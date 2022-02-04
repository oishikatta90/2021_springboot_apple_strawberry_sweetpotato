package com.lee.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.lee.exam.demo.service.MemberService;
import com.lee.exam.demo.util.Ut;

import lombok.Getter;

@Component
@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter 
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession httpSession;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		
		this.httpSession = req.getSession();
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}
		
		
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);

}
	
	public void printReplaceJs(String msg, String url) {
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsReplace(msg, url));
	} 

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsHistoryBack(msg));
	}
	
	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isNotLogined() {
		return !isLogined;
	}

	public void println(String str) {
		print(str + "\n");
	}

	public void login(Member member) {
		httpSession.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		httpSession.removeAttribute("loginedMemberId");
		
	}

	public String historyBackJsOnView(String msg) {
		req.setAttribute("msg",msg);
		req.setAttribute("historyBack",true);
		return "common/js";
	}

	public String jsHistoryBack(String msg) {
		if (msg == null) {
			msg = "";
		}
		return Ut.f("<script>\n" +
					"const msg = '%s'.trim();\n" +
					"if (msg.length > 0) {\n" +
					"alert(msg)\n" +
					"} \n" + 
					"history.back(); \n" + 
					"</script>\n"
								, msg);
	}

	public String jsReplace(String msg, String uri) {
		if (msg == null) {
			msg = "";
		}
		
		if (uri == null) {
			uri = "";
		}
	
		return Ut.f("<script> \n" +
				"const msg = '%s'.trim(); \n" +
				"if (msg.length > 0) { \n" +
				"alert(msg); \n" +
				"} \n" + 
				"location.replace('%s'); \n" + 
				"</script> \n"
				, msg, uri);
	}
	
	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            currentUri += "?" + queryString;
        }

        return currentUri;
	}

	public String getEncodedCurrentUri() {
		return Ut.getUriEncoded(getCurrentUri());
	}
	//이 메서드는 Rq객체가 자연스럽게 생성 되도록 유도하는 역할을 한다.
	//지우면 X, 편의를 위해 BeforActionInterceptor에서
	//꼭 호출하도록 해야한다. 
	//이 메서드는 그냥 아무 의미가 없고 Rq자체를 깨우는 역할을 한다.
	public void initOnBeforeActionInterceptor() {
		// TODO Auto-generated method stub
		
	}


}
