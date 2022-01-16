<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인" />
<%@ include file="../common/head.jspf"%>
<section class="mt-5">
  <form class="container mx-auto px-3" method="POST" action="../member/doLogin">
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>아이디</th>
            <td><input name="loginId" class="w-96" type="text" placeholder="로그인 아이디" /></td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td><input name="loginPw" class="w-96" type="text" placeholder="비밀번호"></td>
          </tr>
          <tr>
            <th>로그인</th>
            <td>
              <button type="submit" class="btn btn-accent">로그인</button>
              <button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>


    <div class="btns">
      <button class="btn-text-link " type="button" onclick="history.back()">뒤로가기</button>
      <c:if test="${article.extra__actorCanDelete}">
        <a class="btn-text-link" href="../article/modify?id=${article.id }">게시물 수정</a>
        <a class="btn-text-link" onclick="if ( confirm('정말 삭제하시겠습니까?') == false  ) return false;"
          href="../article/doDelete?id=${article.id }">게시물 삭제</a>
      </c:if>
    </div>
  </form>
</section>
<%@ include file="../common/foot.jspf"%>