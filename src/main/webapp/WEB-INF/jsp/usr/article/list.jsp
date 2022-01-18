<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="${board.name } 게시물 리스트" />
<%@ include file="../common/head.jspf"%>
<section class="mt-5">
  <div class="container mx-auto px-3">
      <div>게시물 개수 : ${articlesCount } 건</div>
    <div class="mt-2 overflow-x-auto">
      <table class="table table-fixed w-full">
        <colgroup>
          <col width="50" />
          <col width="150" />
          <col width="150" />
          <col width="150" />
          <col />
        </colgroup>
        <thead>
          <tr class="active">
            <th>번호</th>
            <th>작성날짜</th>
            <th>수정날짜</th>
            <th>작성자</th>
            <th>제목</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="article" items="${articles}">
            <tr>
              <td>${article.id }</td>
              <td>${article.regDate.substring(2, 16)}</td>
              <td>${article.updateDate.substring(2, 16)}</td>
              <td>${article.extra__writerName}</td>
              <td><a class="btn-text-link block truncate" href="../article/detail?id=${article.id}">${article.title}</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="page-menu mt-3 ">
      <div class="btn-group justify-center">
        <c:set var="pageMenuArmLen" value="5"/>
        <c:set var="startPage" value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1}"/>
        <c:set var="endPage" value="${page + pageMenuArmLen <= pagesCount ? page + pageMenuArmLen : pagesCount }"/>
        
        <c:set var="pageBaseUri" value="?boardId=${boardId}" />
        <c:set var="pageBaseUri" value="${pageBaseUri}&searchKeywordTypeCode=${param.searchKeywordTypeCode}" />
        <c:set var="pageBaseUri" value="${pageBaseUri}&searchKeyword=${param.searchKeyword}" />
        
        <c:if test="${page > 1 }">
          <a class="btn btn-xs" href="${pageBaseUri}&page=1"><<</a>          
        </c:if>
       
        <c:forEach begin="${startPage}" end="${endPage}" var="i">
          <a class="btn btn-xs ${page == i ? 'btn-active' : ''}" href="${pageBaseUri}&page=${i}">${i}</a>          
        </c:forEach>
        <c:if test="${page != pagesCount }">
          <a class="btn btn-xs" href="${pageBaseUri }&page=${pagesCount}">>></a>          
        </c:if>
      </div>
    </div>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>