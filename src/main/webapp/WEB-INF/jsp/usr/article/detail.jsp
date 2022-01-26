<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="게시물 내용" />
<%@ include file="../common/head.jspf"%>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
</script>

<script>
	function ArticleDetail__increaseHitCount() {
		const localStorageKey = 'article__${params.id}__viewDone';

		if (localStorage.getItem(localStorageKey)) {
			return;
		}

		localStorage.setItem(localStorageKey, true);
		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}

	$(function() {
		ArticleDetail__increaseHitCount();
	});
</script>

<script>
	//댓글작성 관련
  let ReplyWrite__submitFormDone = false;
  function ReplyWrite__submitForm(form) {
	if (ReplyWrite__submitFormDone) {
		return;
	}
    //좌우 공백 제거
	form.body.value = form.body.value.trim();
    
    if (form.body.value.length == 0) {
    	alert('댓글을 입력해주세요.');
    	form.body.focus();
    	return;
    }
    if (form.body.value.length < 2) {
    	alert('댓글 내용을 2자 이상 입력해주세요.');
    	form.body.focus();
    	return;
    }
    
    ReplyWrite__submitFormDone = true;
    form.submit();
    
  }
</script>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200" />
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
            <td>
              <div class="flex items-center">
                <span class="badge badge-primary article-detail__hit-count">${article.goodReactionPoint}</span> <span>&nbsp;</span>

                <c:if test="${actorCanMakeReactionPoint }">
                  <div class="tooltip" data-tip="이 제품을 좋아합니다.">
                    <a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-primary btn-outline">좋아요 👍</a>
                  </div>
                  <div class="tooltip" data-tip="이 제품을 싫어합니다.">
                    <a href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-secondary btn-outline">싫어요 👎</a>
                  </div>
                </c:if>
              
                <c:if test="${actorCanMakeCancleGoodReactionPoint }">
                  <div class="tooltip" data-tip="이 제품을 좋아합니다.">
                    <a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-primary">좋아요 👍</a>
                  </div>
                  <div class="tooltip" data-tip="이 제품을 싫어합니다.">
                    <a href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-secondary btn-outline btn-disabled>">싫어요 👎</a>
                  </div>
                </c:if>
               
              </div>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${article.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${article.body}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="btns">
      <button class="btn-text-link" type="button" onclick="history.back()">뒤로가기</button>
      <c:if test="${article.extra__actorCanDelete}">
        <a class="btn-text-link" href="../article/modify?id=${article.id }">게시물 수정</a>
        <a class="btn-text-link" onclick="if ( confirm('정말 삭제하시겠습니까?') == false  ) return false;"
          href="../article/doDelete?id=${article.id }">게시물 삭제</a>
      </c:if>
    </div>
  </div>
</section>


<section class="mt-5 mb-5">
  <div class="container mx-auto px-3">
    <h1 class="container mx-auto px-3 mt-5">댓글작성</h1>

    <c:if test="${rq.logined}">
     <form class="table-box-type-1" method="post" action="../reply/doWrite" onsubmit="ReplyWrite__submitForm(this); return false;">
      <input type="hidden" name="relTypeCode" value="article" />
      <input type="hidden" name="relId" value="${article.id}" />
      <table class="">
        <colgroup>
          <col width="200"/>
        </colgroup>
         
        <tbody>
          <tr>
            <th>${rq.loginedMember.nickName}</th>
            <td>
              <textarea class="w-full" name="body" rows="3" placeholder="내용" ></textarea>
              <input type="submit" value="작성하기">
            </td>
          </tr>
        </tbody>
      </table>
    </form>
   </c:if>
   <c:if test="${rq.notLogined }">
    <h1 class="container mx-auto px-3 mt-5"><a href="/usr/member/login" class="link link-primary">로그인</a> 후 이용 가능합니다.</h1>
   </c:if>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>