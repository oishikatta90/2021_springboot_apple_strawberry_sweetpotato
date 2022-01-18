package com.lee.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lee.exam.demo.service.MemberService;
import com.lee.exam.demo.vo.Rq;


@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Override
	    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
			
		  return HandlerInterceptor.super.preHandle(req, resp, handler);
	    }
}
