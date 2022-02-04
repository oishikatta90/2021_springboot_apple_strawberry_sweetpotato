<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="회원정보수정" />
<%@ include file="../common/head.jspf"%>

<script>
  let MemberModify__submitDone = false;
  function MemberModify__submit(form) {
    if (MemberModify__submitDone) {
      return;
    }
    
    form.loginPw.value = form.loginPw.value.trim();
    
    if (form.loginPw.value.length > 0) {
      form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
      
      if (form.loginPwConfirm.value.length == 0) {
        alert('비밀번호확인을 입력해주세요.')
        form.loginPwConfirm.focus();
        
        return;
      }
      
      if (form.loginPw.value != form.loginPwConfirm.value) {
        alert('비밀번호확인이 일치하지 않습니다.');
        form.loginPwConfirm.focus();
        
        return;
      }
    }
    
    form.name.value = form.name.value.trim();
    
    if (form.name.value.length == 0) {
      alert('이름을 입력해주세요.')
      form.name.focus();
      
      return;
    }
    
    form.nickName.value = form.nickName.value.trim();
    
    if (form.nickName.value.length == 0) {
      alert('별명을 입력해주세요.')
      form.nickName.focus();
      
      return;
    }
    
    form.email.value = form.email.value.trim();
    
    if (form.email.value.length == 0) {
      alert('이메일을 입력해주세요.')
      form.email.focus();
      
      return;
    }
    
    form.cellphoneNo.value = form.cellphoneNo.value.trim();
    
    if (form.cellphoneNo.value.length == 0) {
      alert('휴대전화번호를 입력해주세요.')
      form.cellphoneNo.focus();
      
      return;
    }
    
    MemberModify__submitDone = true;
    form.submit();
  }
</script>

<section class="mt-5">
  <form class="container mx-auto px-3" method="POST" action="../member/doModify" onsubmit="MemberModify__submit(this); return false;">
   <input type="hidden" name="id" value="${rq.loginedMember.id }" />
   <input type="hidden" name="memberModifyAuthKey" value="${param.memberModifyAuthKey}" />
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
         <tr>
            <th>로그인 아이디</th>
            <td>
              ${rq.loginedMember.loginId}
            </td>
          </tr>
         <tr>
            <th>변경 할 비밀번호</th>
            <td>
              <input class="input input-bordered" name="loginPw" placeholder="비밀번호를 입력해주세요." type="password"  />
            </td>
          </tr>
         <tr>
            <th>변경 할 비밀번호 확인</th>
            <td>
              <input class="input input-bordered" name="loginPwConfirm" placeholder="비밀번호를 입력해주세요." type="password"  />
            </td>
          </tr>
          <tr>
            <th>이름</th>
            <td>
              <input class="input input-bordered" name="name" placeholder="이름을 입력해주세요." type="text" value="${rq.loginedMember.name }" />
            </td>
          </tr>
          <tr>
            <th>별명</th>
            <td>
              <input class="input input-bordered" name="nickName" placeholder="별명을 입력해주세요." type="text" value="${rq.loginedMember.nickName }" />
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              <input class="input input-bordered" name="email" placeholder="이메일을 입력해주세요." type="text" value=" ${rq.loginedMember.email}" />
            </td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td>
              <input class="input input-bordered" name="cellphoneNo" placeholder="전화번호를 입력해주세요." type="tel" value=" ${rq.loginedMember.cellphoneNo}" />
            </td>
          </tr>
            <th>회원정보수정</th>
            <td>
              <button type="submit" class="btn btn-accent">수정</button>
              <button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="btns">
      <button class="btn-text-link " type="button" onclick="history.back()">뒤로가기</button>
    </div>
  </form>
</section>
