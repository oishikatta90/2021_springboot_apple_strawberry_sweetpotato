<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../common/head.jspf"%>

<script>
let ArticleModify__submitDone = false;
function ArticleModify__submit(form) {
  if (ArticleModify__submitDone) {
    return;
  }
  
  form.body.value = form.body.value.trim();
  
  if (form.body.value.length == 0) {
    alert('내용을 입력해주세요.')
    form.body.focus();
    
    return;
  }
  ArticleModify__submitDone = true;
  form.submit();
}
</script>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="post" action="../article/doModify" onsubmit="Modify__submit(this); return false; ">
      <input type="hidden" name="id" value="${article.id }" />
      <table>
        <colgroup>
          <col width="200"/>
        </colgroup>
         
        <tbody>
          <tr>
            <th>번호</th>
            <td>${article.id }</td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${article.forPrintType2RegDate}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.forPrintType2UpdateDate}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra__writerName}</td>
          </tr>
          <tr>
            <th>조회수</th>
            <td><span class="badge badge-primary article-detail__hit-count">${article.hitCount}</span></td>
          </tr>
          <tr>
          <tr>
            <th>추천</th>
            <td><span class="badge badge-primary article-detail__hit-count">${article.goodReactionPoint}</span></td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input class="w-full" name="title" type="text" placeholder="제목" value="${article.title}">
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea class="w-full" name="body" rows="10">${article.body}</textarea>
            </td>
          </tr>
           <tr>
            <th>수정</th>
            <td>
              <input type="submit" value="수정하기">
              <button type="button" onclick="history.back();">뒤로가기</button>
            </td>
           </tr>
        </tbody>
      </table>
    </form>
    
    <div class="btns">
      <button class="btn-text-link" type="button" onclick="history.back()">뒤로가기</button>
      <c:if test="${article.extra__actorCanDelete}">
      <a class="btn-text-link" href="../article/modify?id=${article.id }">게시물 수정</a>
      <a class="btn-text-link" onclick="if ( confirm('정말 삭제하시겠습니까?') == false  ) return false;" href="../article/doDelete?id=${article.id }">게시물 삭제</a>
      </c:if>
    </div>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>