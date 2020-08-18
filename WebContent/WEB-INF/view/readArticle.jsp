<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<style>
table.type03 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
  	margin-top: 30px;
  	font-family: 'Nanum Pen Script', cursive;
  	font-size: 30px;
}
table.type03 th {
    width: 147px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;

}
table.type03 td {
    width: 800px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
table.type03 td a {
   font-family: 'Nanum Pen Script', cursive;
   font-size: 24px;
   
}

table.type09 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    font-family: 'Nanum Pen Script', cursive;
    font-size: 20px;
    margin-bottom: 30px;

} 
table.type09 th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
	font-family: 'Nanum Pen Script', cursive;
	font-siz: 30px;    
}
table.type09 td {
    width: 500px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid ;    
	font-family: 'Nanum Pen Script', cursive;
	font-siz: 25px;
}

table.type09 td a {
	font-family: 'Nanum Pen Script', cursive;
	font-siz: 25px;
	color: red;
}


.comment_form {
    background-color: #ffffff;
	padding-top: em;
    border-radius: 4px;
    border: 1px solid #ddd;
    padding: 10px;
    font-family: 'Nanum Pen Script', cursive;	
	display: block;
	margin: 0 auto;	 
	margin-top: 50px;
	margin-bottom: 30px;
	font-size: 30px;  
}

.submit_btn {
    background-color: #475d9f;
    border: 1px solid #323f6b;
    color: #ffffff;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 35px;
/*     display: block;
    margin: 0 auto; */
}

.input_field {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 4px;
    margin: 3px 0;
    font-size: 30px;
    width: 80%;
}

.review {
	margin-top: 20px;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 50px;	
	text-align: center;	
}

.review a{
	float: right;
}

</style>
<title>Insert title here</title>
</head>
<body>
<div class="review container">
<b>Review Lookup</b><a href="list.do" class="text-danger"><i class="fa fa-times"></i></a>
</div>
<div class="container">
<table class="type03">
<tr>

	<th scope="row">작성자</th>
	<td>${articleData.article.writer.id }</td>
</tr>

<tr>
	<th scope="row">제목</th>
	<td><c:out value="[${articleData.article.localName }] ${articleData.article.title }"></c:out></td>
</tr>

<tr>
	<th scope="row">내용</th>
	<td>${articleData.content }</td>
</tr>

<tr>
	<th scope="row">영수증 첨부</th>
		<td style="white-space: pre-wrap;">
		<c:if test="${not empty articleData.fileName }">
			<img class="rounded float-left" src="/images/${articleData.article.number }/${articleData.fileName }" alt="" />
		</c:if>
		</td>	

</tr>


<tr>

	<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}"></c:set>
		<a href="list.do?pageNo=1" class="text-info"><i class="fa fa-list"></i>List</a>
		<c:if test="${authUser.id == articleData.article.writer.id}">
			<a href="modify.do?no=${articleData.article.number }" class="text-dark"><i class="fa fa-pen"></i>Modify</a>
			<a href="delete.do?no=${articleData.article.number }" class="text-danger"><i class="fa fa-trash"></i>Delete</a>		
		</c:if>	
	</td>	

</tr>
</table>
</div>

<div class="comment_form container">

<table class="type09">
<tr>
<th>작성자</th>
<th>내용</th>
<th>작성시간</th>
</tr>
<c:if test="${commentPage.hasNoComments() }">
   <tr>
      <td colspan="4">댓글이 없습니다.</td>
   </tr>   
</c:if>
<c:forEach var="comment" items="${commentPage.content}">

	<tr>
		<td>${comment.commentWriter.name }</td>		
		<td>${comment.commentContent }</td>
		<td>${comment.regDate }</td>
		<c:if test="${authUser.id == comment.commentWriter.name }">
		<td><a href="commentdelete.do?comment_no=${comment.commentNo }">[댓글삭제]</a></td>	
		</c:if>	
	</tr>

</c:forEach>
</table>

<form action="commentwrite.do" method="post">
댓글: 
<input class="input_field" type="text" name="commentContent" >${param.commentContent}
<%-- <textarea name="commentContent" rows="5" cols="30">${param.commentContent}</textarea> --%>
<input class="submit_btn" type="submit" value="댓글 등록"/>
</form>


<nav aria-label="Page navigation example">

	<ul class="pagination justify-content-center">
	<c:if test="${commentPage.hasComments() }">
		<li class="page-item">
			<c:if test="${commentPage.startPage > 5 }">

				<a class="page-link" href="read.do?no=${articleData.article.number}&pageCno=${commentPage.startPage - 5 }">이전</a>
			</c:if>		
		</li>			
			<c:forEach var="cpNo" begin="${commentPage.startPage }" end="${commentPage.endPage }">
			<li class="page-item">	<a class="page-link" href="read.do?no=${articleData.article.number}&pageCno=${cpNo}">${cpNo }</a></li>
			</c:forEach>		
		<li class="page-item">	
			<c:if test="${commentPage.endPage < commentPage.totalPages }">
				<a class="page-link" href="read.do?no=${articleData.article.number}&pageCno=${commentPage.startPage + 5 }">다음</a>

			</c:if>			
		</li>
		</c:if>
	</ul>
</nav>


</div>

</body>
</html>





