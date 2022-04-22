<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../common/head.jspf"%>
<section class="mt-5">
  <form class="container mx-auto px-3" method="POST" action="../member/doCheckPassword">
  <input type="hidden" name="replaceUri" value="${param.replaceUri }" />
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>아이디</th>
            <td>${rq.loginedMember.loginId }</td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td><input name="loginPw" class="w-96" type="password" placeholder="비밀번호" required="required"></td>
          </tr>
        </tbody>
      </table>
              <button type="submit" class="btn btn-primary">비밀번호 확인</button>
              <button type="button" class="btn btn-outline" onclick="history.back();">뒤로가기</button>
    </div>
    <!-- 
    <div class="btns">
      <button class="btn-text-link " type="button" onclick="history.back()">뒤로가기</button>
    </div>
     -->
  </form>
</section>
