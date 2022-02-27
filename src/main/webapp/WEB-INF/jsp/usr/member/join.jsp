  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>###--회원가입</title>
<link rel="stylesheet" href="/resource/common.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 사이트 공통 css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" />
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.11.1/dist/full.css" rel="stylesheet" type="text/css" />



</head>
<body>

  <script type="text/javascript">
			let submitJoinFormDone = false;
			let validLoginId = "";
			function submitJoinForm(form) {
				if (submitJoinFormDone) {
					alert('처리중입니다.');
					return;
				}
				form.loginId.value = form.loginId.value.trim();
				if (form.loginId.value.length == 0) {
					alert('로그인아이디를 입력해주세요.');
					form.loginId.focus();
					return;
				}
				/*
				 if (form.loginId.value != validLoginId) {
				   alert('해당 로그인아이디는 올바르지 않습니다. 다른 로그인아이디를 입력해주세요.');
				   form.loginId.focus();
				   return;
				 }
				 */
				form.loginPw.value = form.loginPw.value.trim();
				if (form.loginPw.value.length == 0) {
					alert('로그인비밀번호를 입력해주세요.');
					form.loginPw.focus();
					return;
				}
				form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
				if (form.loginPwConfirm.value.length == 0) {
					alert('로그인비밀번호 확인을 입력해주세요.');
					form.loginPwConfirm.focus();
					return;
				}
				if (form.loginPw.value != form.loginPwConfirm.value) {
					alert('로그인비밀번호 확인이 일치하지 않습니다.');
					form.loginPwConfirm.focus();
					return;
				}
				form.name.value = form.name.value.trim();
				if (form.name.value.length == 0) {
					alert('이름을 입력해주세요.');
					form.name.focus();
					return;
				}
				form.nickName.value = form.nickName.value.trim();
				if (form.nickName.value.length == 0) {
					alert('닉네임을 입력해주세요.');
					form.nickName.focus();
					return;
				}
				form.email.value = form.email.value.trim();
				if (form.email.value.length == 0) {
					alert('이메일을 입력해주세요.');
					form.email.focus();
					return;
				}
				form.cellphoneNo.value = form.cellphoneNo.value.trim();
				if (form.cellphoneNo.value.length == 0) {
					alert('휴대전화번호를 입력해주세요.');
					form.cellphoneNo.focus();
					return;
				}
				submitJoinFormDone = true;
				form.submit();
			}
			function checkLoginIdDup(el) {
				$('.loginId-message').empty();
				const form = $(el).closest('form').get(0);

				if (form.loginId.value.length == 0) {
					validLoginId = '';
					return;
				}

				$.get('../member/getLoginIdDup', {
					isAjax : 'Y',
					loginId : form.loginId.value
				}, function(data) {
					$('.loginId-message').html(
							'<div style="margin-top:7px; margin-bottom:7px; color:blue;">'
									+ data.msg + '</div>');
					if (data.success) {
						validLoginId = data.data1;
					} else {
						validLoginId = '';
					}
				}, 'json');
			}
		</script>
  <form action="../member/doJoin" method="post" onsubmit="submitJoinForm(this); return false;">
    <input type="hidden" name="afterLoginUri" value="${param.afterLoginUri }" />
    <div class="page-container">
      <div class="rounded join-form-container shadow-2xl pt-11 pl-11 pr-11 ">
        <div class="login-form-right-side">
          <div class="top-logo-wrap"></div>
          <h1 class="text-center">회원가입</h1>
        </div>
        <div class="login-form-left-side">
          <div class="form-control">
            <label class="label"> <span class="label-text">아이디</span>
            </label> <input type="text" placeholder="아이디" class="input input-bordered" name="loginId"
              onkeyup="checkLoginIdDup(this);" autocomplete="off" />
          </div>
          <div class="loginId-message"></div> 
          <div class="form-control"> 
            <label class="label"> <span class="label-text">비밀번호</span>
            </label> <input type="password" placeholder="비밀번호" class="input input-bordered" name="loginPw">
          </div>
          <div class="form-control"> 
            <label class="label"> <span class="label-text">비밀번호 확인</span>
            </label> <input type="password" placeholder="비밀번호 확인" class="input input-bordered" name="loginPwConfirm">
          </div>
          <div class="form-control">
            <label class="label"> <span class="label-text">이름</span>
            </label> <input type="text" placeholder="이름" class="input input-bordered" name="name">
          </div>
          <div class="form-control">
            <label class="label"> <span class="label-text">닉네임</span>
            </label> <input type="text" placeholder="닉네임" class="input input-bordered" name="nickName">
          </div> 
          <div class="form-control">
            <label class="label"> <span class="label-text">이메일</span>
            </label> <input type="text" placeholder="이메일" class="input input-bordered" name="email">
          </div>
          <div class="form-control">
            <label class="label"> <span class="label-text">전화번호</span>
            </label> <input type="text" placeholder="전화번호" class="input input-bordered" name="cellphoneNo">
          </div>
        </div>
        <div class="btns mb-5">
          <button class="btn btn-warning mt-5" type="submit">가입하기</button> 
          <button class="btn btn-warning mt-5" type="button" onclick="history.back()">뒤로가기</button>
        </div>
      </div>
     </div>
  </form>
</body>
</html>