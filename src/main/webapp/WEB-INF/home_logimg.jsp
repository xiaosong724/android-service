<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/16
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log</title>
    <link rel="stylesheet" type="text/css" href="assets/css/demo.css" />
</head>
<body>
<!-- coidea:loader START -->
<div class="loader">
    <div class="lds-ripple">
        <div></div>
        <div></div>
    </div>
</div>
<!-- coidea:loader END -->

<!-- coidea:header START -->
<header>
    <div class="header-inner">
        <div class="logo">
            <a href="/home"><span></span>开启日志</a>
        </div>
        <nav class="header-navigation">
            <li><a href=""><span>发送自定义通知</span></a></li>

        </nav>
    </div>
</header>
<!-- coidea:header END -->

<!-- slideshow START -->
<section class="slideshow">

    <!-- slideshow:navigation START -->
    <ul class="navigation">
        <!-- slideshow:navigation:item START -->
        <c:forEach var="logList" items="${logimgList}" varStatus="cou">
            <li class="navigation-item ${cou.index==0?'active':''}">
                <span class="rotate-holder"></span>
                <span class="background-holder" style="background-image: url(img/mini/${logList.imgpath});"></span>
            </li>
        </c:forEach>
        <!-- slideshow:navigation:item END -->
    </ul>
    <!-- slideshow:navigation END -->

    <!-- slideshow:details START -->
    <div class="detail">
        <!-- slideshow:details START -->
        <c:forEach var="logList2" items="${logimgList}" varStatus="cou">
        <div class="detail-item ${cou.index==0?'active':''}">
            <div class="headline">${logList2.title}</div>
            <div class="background" style="background-image: url(img/mini/${logList2.imgpath})"></div>
        </div>
        </c:forEach>
        <!-- slideshow:details END -->

    </div>
    <!-- slideshow:details END -->

</section>
<!-- slideshow END -->
<script src="assets/js/jquery-1.11.0.min.js"></script>
<script src="assets/js/TweenMax.min.js"></script>
<script src="assets/js/imagesloaded.pkgd.min.js"></script>
<script src="assets/js/CSSPlugin.min.js"></script>
<script src="assets/js/TextPlugin.min.js"></script>
<script src="assets/js/demo.js"></script>
</body>
</html>