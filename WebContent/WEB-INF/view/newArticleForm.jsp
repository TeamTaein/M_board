<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

        
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

<title>게시글 쓰기</title>
</head>
<body>
<!-- enctyped은 method가 post일때만 동작 / web.xml에 multipart-config 추가 -->
<form action="write.do" method="post" enctype="multipart/form-data">

<div class="form-group">
	제목: <br /><textarea name="title" rows="1" cols="80"  >${param.title }</textarea>
	<c:if test="${errors.title }">제목을 입력하세요</c:if>
	<br />
	</div>
		
	<div class="form-group">
	지역 :
	<select name="local_name">
			<option value="일산">일산</option>
			<option value="신촌">신촌</option>
			<option value="이대">이대</option>
	</select>  
	</div>	

<div class="form-group">
	내용: <br />
	<textarea name="content" rows="10" cols="80">${param.content }</textarea>
</div>
<div class="form-group">
파일: <br />
<input type="file" name="file1" accept="image/*" />
</div>
<div class="form-group">
<button type="submit" class="btn btn-primary" value="새 글 등록" >새 글 등록</button>
</div>
</form>
</body>
</html>