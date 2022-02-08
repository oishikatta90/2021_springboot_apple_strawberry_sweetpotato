<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>###--회원가입</title>
<link rel="stylesheet" href="/resource/common.css" />

</head>
<body>

<script type="text/javascript">
  let submitJoinFormDone = false;
  function submitJoinForm(form) {
  	if (submitJoinFormDone) {
			alert('처리중입니다.');
			return;
		}
  	
  	form.loginId.value = form.loginId.value.trim();
  	
  	if (form.loginId.value.length == 0) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
  	
  	form.loginPw.value = form.loginPw.value.trim();
  	
  	if (form.loginPw.value.length == 0) {
			alert('로그인 비밀번호를 입력해주세요.');
			form.loginPw.focus();
			return;
		}
  	
  	form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
  	
  	if (form.loginPwConfirm.value.length == 0) {
			alert('로그인 비밀번호 확인을 입력해주세요.');
			return;
		}
  	if (form.loginPw.value != form.loginPwConfirm.value) {
			alert('비밀번호 확인이 일치하지 않습니다.');
			form.loginPwConfirm.focus();
			return;
		}
  	
  	form.name.value = form.name.value.trim();
  	
  	if (form.name.value == 0) {
			alert('이름을 입력해주세요.');
			return;
		}
  	
  	form.nickName.value = form.nickName.value.trim();
  	
  	if (form.nickName.value == 0) {
			alert('닉네임을 입력해주세요');
			return;
		}
  	
  	form.email.value = form.email.value.trim();
  	
  	if (form.email.value == 0) {
			alert('이메일을 입력해주세요');
			return;
		}
  	
  	form.cellphoneNo.value = form.cellphoneNo.value.trim();
  	
  	if (form.cellphoneNo.value == 0) {
			alert('전화번호를 입력해주세요');
			return;
		}
    form.submit();
  }
</script>
  <form action="../member/doJoin" method="post" onsubmit="submitJoinForm(this); return false;">
    <input type="hidden" name="afterLoginUri" value="${param.afterLoginUri }" />
    <div class="page-container">
      <div class="login-form-container shadow">
        <div class="login-form-right-side">
          <div class="top-logo-wrap"></div>
          <h1>회원가입</h1>
        </div>
        <div class="login-form-left-side">
          <div class="login-top-wrap">
            <span>빨리 사고싶지?</span>
          </div>
          <div class="login-input-container">
            아이디 <input type="text" name="loginId">
          </div>
          <div class="login-input-wrap input-password">
            비밀번호 <input type="password" name="loginPw">
          </div>
          <div class="login-input-wrap input-passwordConfirm">
            비밀번호 확인 <input type="password" name="loginPwConfirm">
          </div>
          <div class="login-input-wrap input-name">
            이름 <input type="text" name="name">
          </div>
          <div class="login-input-wrap input-nickName">
            닉네임 <input type="text" name="nickName">
          </div>
          <div class="login-input-wrap input-email">
            이메일 <input type="email" name="email">
          </div>
          <div class="login-input-wrap input-cellphoneNo">
            전화번호 <input type="text" name="cellphoneNo">
          </div>
        </div>
        <div class="btns">
          <button type="submit"> 회원가입 </button>
          <button class="btn-text-link " type="button" onclick="history.back()">뒤로가기</button>
        </div>
      </div>
    </div>
  </form>
</body>
</html>