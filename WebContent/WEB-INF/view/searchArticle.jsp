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
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/korea-font.css" type="text/css">

<style type="text/css">
 a:link { color: black; text-decoration: none;}
 a:visited { color: gray; text-decoration: none;}
 a:hover { color: blue; text-decoration: none;}
</style>

<style>
table.type09 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    font-family: 'Nanum Pen Script', cursive;
    font-size: 40px;

} 
table.type09 thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
	font-family: 'Nanum Pen Script', cursive;
	font-siz: 30px;    
}
table.type09 tbody td {
    width: 500px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid ;
    /* background: #f3f6f7; */ 
	font-family: 'Nanum Pen Script', cursive;
	font-siz: 30px;
}
table.type09 td {
    width: 500px;
    padding: 10px;
    vertical-align: middle;
    border-bottom: 1px solid #ccc;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;    
}
 .title {
	font-family: 'Nanum Pen Script', cursive;
	font-size: 75px;
	text-align: center;
	color: olive;
}

.block-27 ul {
  padding: 0;
  margin: 0;
  font-family: 'Nanum Pen Script', cursive;
  font-size: 25px;
  text-align: center; 
   }
  .block-27 ul li {
    display: inline-block;
    margin-bottom: 4px;
    font-weight: 400;
    }
    .block-27 ul li a, .block-27 ul li span {
      border: 1px solid #1eafed;      
      text-align: center;
      display: inline-block;
      width: 40px;
      height: 40px;
      line-height: 40px;
      border-radius: 50%; 

      }

</style>
<title>게시글목록</title>
</head>
<body>

<div class="container">
<div class="title"> 맛집 게시판</div>

<table class="type09">

<thead>		
	<tr>
		<td colspan="5"><a href="write.do">[게시글쓰기]</a>
		<u:isLogin><a href="${ctxPath }/logout.do" class="text-danger">[로그아웃하기]</a></u:isLogin>
				<!-- 검색 기능 -->
<form action="search.do" method="get">
	<select name="search_key" id="">
		<option value="title">제목</option>
		<option value="writer_id">작성자</option>
		<option value="local_name">지역</option>
	</select>
	<input type="text" name="search_rs" />
	<input type="submit" value="검색" />
</form>
		</td>	

	</tr>	
 
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>지역</th>
		<th>작성자</th>
		<th>조회수</th>
	</tr>
</thead>

<tbody>
<c:if test="${articlePage.hasNoArticles() }">
   <tr>
      <td colspan="4">게시글이 없습니다.</td>
   </tr>   
</c:if>

<c:forEach var="article" items="${articlePage.content }">

	<tr onmouseover="this.style.background='lightblue'" onmouseout="this.style.background='white'">
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
</tbody>
</table>

<br />

<div class="block-27 justify-content-center">
	<ul>
	<c:if test="${articlePage.hasArticles() }">
		<li>
			<c:if test="${articlePage.startPage > 5 }">
				<a href="search.do.do?pageNo=${articlePage.startPage - 5 }">&lt;</a>
			</c:if>		
		</li>			
			<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
			<li>	<a href="search.do?pageNo=${pNo}">${pNo }</a></li>
			</c:forEach>		
		<li>	
			<c:if test="${articlePage.endPage < articlePage.totalPages }">
				<a href="search.do?pageNo=${articlePage.startPage + 5 }">&gt;</a>
			</c:if>			
		</li>
		</c:if>
	</ul>
</div>


</div>
</body>
</html>