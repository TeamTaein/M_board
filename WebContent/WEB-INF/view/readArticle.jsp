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
<style>
table.type03 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
  margin : 20px 10px;
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
    width: 349px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<table class="type03">
<tr>
   <th scope="row">작성자</th>
   <td>${articleData.article.writer.id }</td>
</tr>

<tr>
   <th scope="row">제목</th>
   <td><c:out value="${articleData.article.title }"></c:out></td>
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
      <c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"></c:set>
      <a href="list.do?pageNo=${pageNo }">[목록]</a>
      <c:if test="${authUser.name == articleData.article.writer.id }">
         <a href="modify.do?no=${articleData.article.number }">[게시글 수정]</a>
         <a href="delete.do?no=${articleData.article.number }">[게시글 삭제]</a>      
      </c:if>   
   </td>   
</tr>
</table>

</div>
</body>
</html>