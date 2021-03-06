<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.lee.exam.demo.util.Ut" %>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="150" />
        </colgroup>
        <tbody>
          <tr>
            <th>로그인아이디</th>
            <td>
              ${rq.loginedMember.loginId}
            </td>
          </tr>
          <tr>
            <th>이름</th>
            <td>
              ${rq.loginedMember.name}
            </td>
          </tr>
          <tr>
            <th>별명</th>
            <td>
              ${rq.loginedMember.nickName}
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              ${rq.loginedMember.email}
            </td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td>
              ${rq.loginedMember.cellphoneNo}
            </td>
          </tr>
        </tbody>
      </table>
              <a href="../member/checkPassword?replaceUri=${Ut.getUriEncoded('../member/modify')}" class="btn btn-primary">회원정보수정</a>
              <button type="button" class="btn btn-outline " onclick="history.back();">뒤로가기</button>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>