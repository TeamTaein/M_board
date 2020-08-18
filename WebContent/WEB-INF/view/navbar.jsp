<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL사용하기 위한 태그 : 사용할 태그는 코어태그! -->
<!-- 태그는 대부분 'c'  -->
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL사용하기 위한 태그 : 사용할 태그는 함수태그! -->
<!-- 태그는 대부분 'fn'  -->
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %> 
	<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Yoga Studio Template">
    <meta name="keywords" content="Yoga, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Yummy | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
    <!-- Header Section Begin -->
    <header class="header-section">
        <div class="container">
            <div class="logo">
                <a href="${ctxPath }/index.jsp"><img src="/WEB-INF/img/logo.png" alt=""></a>
            </div>
            <div class="nav-menu">
                <nav class="main-menu mobile-menu">
                    <ul>                    
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">Pages</a>
                        <ul class="sub-menu">                                
                                <li><a href="recipe.html">Notice</a></li>
                                <li><a href="${ctxPath }/article/list.do">Review</a></li>
                                <li><a href="${ctxPath }/logout.do">Logout</a></li>
                            </ul> 
                        </li>
                        <li><a href="recipe.html">Notice</a></li>
                        <li><a href="${ctxPath }/article/list.do">Review</a></li>
                        <li><u:isLogin><a href="${ctxPath }/logout.do" class="text-danger">Logout</a></u:isLogin></li>
                    </ul>
                </nav>
                <div class="nav-right search-switch">
                    <i class="fa fa-search"></i>
                </div>
            </div>
            <div id="mobile-menu-wrap"></div>
        </div>
    </header>
    <!-- Header End -->
 
        <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>