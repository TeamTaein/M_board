<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>암호 변경</title>
<style>
.pwForm {
	border: 1px solid;
	padding: 20px;
	display: block;
	margin: 20px auto;
	border-radius: 10px;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;	
}
.pwForm legend {
	font-size: 50px;
}
a {
	float: right;
}

</style>
</head>
<body>
<div class="pwForm container">
	<form action="changePwd.do" method="post">
	<fieldset>
	<legend>암호 변경 입력사항<a href="${ctxPath }/index.jsp" class="text-danger"><i class="fa fa-times"></i></a></legend>
	<p>
		현재암호: <br /> <input type="password" name="curPwd" placeholder="현재 암호" />
		<c:if test="${errors.curPwd}"> <b style="color:red">현재 암호를 입력하세요</b></c:if>
		<c:if test="${errors.badCurPwd}"><b style="color:red">현재 암호와 일치하지 않습니다.</b></c:if>		
	</p>
	<p>
		새 암호: <br /> <input type="password" name="newPwd" placeholder="변경 할 암호" />
		<c:if test="${errors.newPwd}"><b style="color:red">암호를 입력하세요</b></c:if>
	</p>
	<input type="submit" value="암호변경" />
	<input type="reset" value="재입력" />
	</fieldset>
	</form>
	
</div>
</body>
</html>