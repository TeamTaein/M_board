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
 table a:link { color: black; text-decoration: none;}
 table a:visited { color: gray; text-decoration: none;}
 table a:hover { color: blue; text-decoration: none;}
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
      
#search_btn{
	border: none;
	background-color:rgba(0,0,0,0);
}
#search_select{
	border: none;
	background-color:rgba(0,0,0,0);
	border-radius: 10px;
}
#search_text{
	border: none;
} 
#search_group{
	border: none;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;	
	align-items: center;
} 

.chart {
	font-family: 'Nanum Pen Script', cursive;	
	font-size: 30px;
	float: right;	
}

img {
	display: block;
	margin: 0 auto;	
}

.welcome{
	font-family: 'Nanum Pen Script', cursive;
	font-size: 20px;
	float: right;
}

</style>

<title>게시글목록</title>
</head>
<body>
<div class="welcome">

Welcome ${authUser.id }!!
</div>
<a href="${ctxPath }/index.jsp"><img src="${ctxPath }/img/yummy.png" alt="" /></a>


<div class="container">

<div class="chart">
		<a href="${ctxPath }/index.jsp"><i class="fa fa-home"></i></a>
		<a href="write.do" ><i class="fa fa-comment"></i></a>
		<u:isLogin><a href="${ctxPath }/logout.do" class="text-danger"><i class="fa fa-rocket"></i>Logout</a></u:isLogin>
	</div>
<!-- <div class="title"> 맛집 게시판</div> -->
	
<div class="h-100 d-flex" id="search_group">
<form action="search.do" method="get" class="serach-model-form">
	<select name="search_key" id="search_select">
		<option value="title">제목</option>
		<option value="writer_id">작성자</option>
		<option value="local_name">지역</option>
	</select>
	<input type="text" name="search_rs" placeholder="Search here....." id="search_text"/>
	<button type="submit" id="search_btn"><i class="fa fa-search"></i></button>
</form>
</div>


<table class="type09">
<thead>		
 
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
				<a href="list.do?pageNo=${articlePage.startPage - 5 }">&lt;</a>
			</c:if>		
		</li>			
			<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
			<li>	<a href="list.do?pageNo=${pNo}">${pNo }</a></li>
			</c:forEach>		
		<li>	
			<c:if test="${articlePage.endPage < articlePage.totalPages }">
				<a href="list.do?pageNo=${articlePage.startPage + 5 }">&gt;</a>
			</c:if>			
		</li>
		</c:if>
	</ul>
</div>


</div>
</body>
</html>