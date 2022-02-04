package com.lee.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lee.exam.demo.interceptor.BeforeActionInterceptor;
import com.lee.exam.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
    // beforeActionInterceptor 인터셉터 불러오기
    @Autowired
    BeforeActionInterceptor beforeActionInterceptor;
    @Autowired
    // NeedLoginInterceptor 인터셉터 불러오기
    NeedLoginInterceptor needLoginInterceptor;


    // 이 함수는 인터셉터를 적용하는 역할을 합니다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
        registry.addInterceptor(beforeActionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/resource/**")
                .excludePathPatterns("/error");
        
        
        registry.addInterceptor(needLoginInterceptor)
        		.addPathPatterns("/usr/article/write")
        		.addPathPatterns("/usr/article/doWrite")
        		.addPathPatterns("/usr/article/doDelete")
        		.addPathPatterns("/usr/article/modify")
        		.addPathPatterns("/usr/article/doModify")
        		.addPathPatterns("/usr/reactionPoint/doGoodReaction")
        		.addPathPatterns("/usr/reactionPoint/doBadReaction")
        		.addPathPatterns("/usr/reactionPoint/doCancleGoodReaction")
        		.addPathPatterns("/usr/reactionPoint/doCancleBadReaction")
        		.addPathPatterns("/usr/reply/doWrite")
        		.addPathPatterns("/usr/reply/doDelete")
        		.addPathPatterns("/usr/reply/doDeleteAjax")
        		.addPathPatterns("/usr/reply/modify")
        		.addPathPatterns("/usr/reply/doModify")
        		.addPathPatterns("/usr/member/modify")
        		.addPathPatterns("/usr/member/doModify")
        		.addPathPatterns("/usr/member/checkPassword")
        		.addPathPatterns("/usr/member/doCheckPassword")
        		.addPathPatterns("/usr/member/myPage");
    }
}
