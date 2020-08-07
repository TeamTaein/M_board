<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
table.type09 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;

}
table.type09 thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
table.type09 tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
table.type09 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
</style>
<title>게시글목록</title>
</head>
<body>

<div class="container">
<table class="type09">
	<tr class="header"><h1>게시판</h1></tr>
	<tr>
		<td colspan="5"><a href="write.do">[게시글쓰기]</a>
		<u:isLogin><a href="${ctxPath }logout.do" class="text-danger">[로그아웃하기]</a></u:isLogin>
		</td>		
	</tr>	
	 
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>지역</th>
		<th>작성자</th>
		<th>조회수</th>
	</tr>
	
<c:if test="${articlePage.hasNoArticles() }">
	<tr>
		<td colspan="4">게시글이 없습니다.</td>
	</tr>	
</c:if>

<c:forEach var="article" items="${articlePage.content }">
	<tr>
		<td>${article.number }</td>
		<td>
			<a href="read.do?no=${article.number }&pageNo=${articlePage.currentPage}">
				<c:out value="${article.title }"></c:out>
			</a>
		</td>
		
		<td>${article.localName }</td>		
		<td>${article.writer.id }</td>
		<td>${article.readCount }</td>
	</tr>
</c:forEach>
</table>

<br />
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
	<c:if test="${articlePage.hasArticles() }">
		<li class="page-item">
			<c:if test="${articlePage.startPage > 5 }">
				<a class="page-link" href="list.do?pageNo=${articlePage.startPage - 5 }">이전</a>
			</c:if>		
		</li>			
			<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
			<li class="page-item">	<a class="page-link" href="list.do?pageNo=${pNo }">${pNo }</a></li>
			</c:forEach>		
		<li class="page-item">	
			<c:if test="${articlePage.endPage < articlePage.totalPages }">
				<a class="page-link" href="list.do?pageNo=${articlePage.startPage + 5 }">다음</a>
			</c:if>			
		</li>
		</c:if>
	</ul>
</nav>


</div>
</body>
</html>