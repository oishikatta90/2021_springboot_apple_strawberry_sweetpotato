<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="게시물 작성" />
<%@ include file="../common/head.jspf"%>
<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="post" action="../article/doWrite">
      <table>
        <colgroup>
          <col width="200"/>
        </colgroup>
         
        <tbody>
          <tr>
            <th>작성자</th>
            <td>${rq.loginedMember.nickName}</td>
          </tr>
          <tr>
            <th>게시판</th>
            <td>
              <select class="select select-bordered" name="boardId">
                <option selected disabled>게시판 선택</option>              
                <option value="1">공지사항</option>              
                <option value="2">자유게시판</option>              
              </select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input required="required" class="w-full" name="title" type="text" placeholder="제목">
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea required="required" class="w-full" name="body" rows="10" placeholder="내용" ></textarea>
            </td>
          </tr>
           <tr>
            <th>작성</th>
            <td>
              <input type="submit" value="작성하기">
              <button type="button" onclick="history.back();">뒤로가기</button>
            </td>
           </tr>
        </tbody>
      </table>
    </form>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>