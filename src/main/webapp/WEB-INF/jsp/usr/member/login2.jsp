<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>####-로그인</title>
<link rel="stylesheet" href="/resource/common.css" />

</head>
<body>
<form class="container mx-auto px-3" method="POST" action="../member/doLogin">
  <div class="page-container">
    <div class="login-form-container shadow">
      <div class="login-form-right-side">
        <div class="top-logo-wrap"></div>
        <h1>로그인창</h1>
        <p>로그인 해보고 싶다</p>
      </div>
      <div class="login-form-left-side">
        <div class="login-top-wrap">
          <span>잊진 않았겠지?</span>
        </div>
        <div class="login-input-container">
          <div class="login-input-wrap input-id">
            <i class="far fa-envelope"></i> <input type="text" name="loginId">
          </div>
          <div class="login-input-wrap input-password">
            <i class="fas fa-key"></i> <input type="password" name="loginPw">
          </div>
        </div>
        <div class="btns">
          <button type="submit">로그인</button>
          <button class="btn-text-link " type="button" onclick="history.back()">뒤로가기</button>
        </div>
      </div>
    </div>
  </div>
  </form>
</body>
</html>