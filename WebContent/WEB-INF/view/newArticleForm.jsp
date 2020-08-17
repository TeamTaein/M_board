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
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
 
<title>게시글 쓰기</title>

<style>
.form_box {
    background-color: #ffffff;
	padding-top: em;
    border-radius: 4px;
    border: 1px solid #ddd;
    padding: 10px;
    font-family: 'Nanum Pen Script', cursive;	
	display: block;
	margin: 0 auto;	 
	margin-top: 50px;
	font-size: 30px;   
}
.form_box a {
	float: right;
}
.input_field {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 4px;
    margin: 3px 0;
    font-size: 30px;
    width: 100%;
}
.textarea_field {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 4px;
    margin: 3px 0;
    font-size: 30px;
    width: 100%;
    height: 160px;
}

.submit_btn {
    background-color: #475d9f;
    border: 1px solid #323f6b;
    color: #ffffff;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 35px;
}

.local_field{
	background-color: white;
	border: 1px solid #323f6b;
	color: black;
    border-radius: 4px;
    padding: 2px 8px;
    font-size: 25px;	
    
}
.file_field label {
 	display: inline-block;
 	padding: .5em .75em;
 	color: black; 
 	font-size: 25px; 
 	line-height: normal; 
 	vertical-align: middle; 
 	background-color: #fdfdfd; 
 	cursor: pointer; 
 	border: 1px solid #ebebeb; 
 	border-bottom-color: #e2e2e2; 
 	border-radius: .25em;  	
 }
 
 .file_field input[type="file"]{ /* 파일 필드 숨기기 */
 	position: absolute;
 	width: 1px;
 	height: 1px;
 	padding: 0;
 	margin: -1px;
 	overflow: hidden;
 	clip: rect(0,0,0,0,);
 	border: 0;
 }
form b{
	font-size: 30px;
}

</style>
</head>

<body>
<!-- enctyped은 method가 post일때만 동작 / web.xml에 multipart-config 추가 -->
<div class="form_box container">
<h1><b>게시물 작성</b><a href="list.do" class="text-danger"><i class="fa fa-times"></i></a></h1>
<br />
<form action="write.do" method="post" enctype="multipart/form-data">

	<b> 제목: </b>
	<input class="input_field" type="text" name="title" value="${param.title }" />
	<c:if test="${errors.title }"><b style="color:red">제목을 입력하세요</b></c:if>
	<br />
	
		
		
	<b> 지역 : </b> <br />
	<select class="local_field" name="local_name">
			<option value="일산">일산</option>
			<option value="신촌">신촌</option>
			<option value="이대">이대</option>
	</select>  
	<%-- <br /><input type="text" name="local_name" value="${param.local_name }" /> --%>

	<br />
	<b> 내용: </b> 
	<textarea class="textarea_field" name="content" rows="5" cols="30">${param.content }</textarea>
	<c:if test="${errors.content }"><b style="color:red">내용을 입력하세요</b></c:if>


<div class="file_field">
<b> 파일: </b>
<label for="file_upload">업로드</label>
<br />
<input id="file_upload" type="file" name="file1" accept="image/*" />
</div>


<input class="submit_btn" type="submit" value="새 글 등록" />
<input class="submit_btn" type="reset" value="다시쓰기" />
</form>
</div>
</body>
</html>