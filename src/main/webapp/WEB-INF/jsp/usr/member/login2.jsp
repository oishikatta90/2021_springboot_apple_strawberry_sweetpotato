<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>####-로그인</title>
<link rel="stylesheet" href="/resource/common.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" />
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.11.1/dist/full.css" rel="stylesheet" type="text/css" />

</head>
<body>
  <form class="mx-auto px-3" method="POST" action="../member/doLogin">
    <input type="hidden" name="afterLoginUri" value="${param.afterLoginUri}" />
    <div class="page-container">
      <div class="rounded login-form-container shadow-2xl pt-20 pl-20 pr-20">
        <div class="login-form-right-side">
          <div class="top-logo-wrap"></div>
          <h1 class="text-center">로그인</h1>
        </div>
        <div class="form-control">
          <label class="label"> <span class="label-text">아이디</span>
          </label> <input type="text" placeholder="아이디" class="input input-bordered" name="loginId"
            onkeyup="checkLoginIdDup(this);" autocomplete="off" />
        </div>
        <div class="form-control"> 
          <label class="label"> <span class="label-text">비밀번호</span>
          </label> <input type="password" placeholder="비밀번호" class="input input-bordered" name="loginPw">
        </div>
        <div class="btns mb-5">
          <button class="btn btn-warning mt-5" type="submit">로그인</button> 
            <button class="btn btn-warning mt-5" type="button" onclick="history.back()">뒤로가기</button>
        </div>
      </div>
    </div>
  </form>
</body>
</html>