<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String id=request.getParameter("id");
	String username=request.getParameter("username");
	String title=request.getParameter("title");
	String logtype=request.getParameter("logtype");
	String message=request.getParameter("message");
	String viewcount=request.getParameter("viewcount");
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="stylesheet" href="css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css/style.css"> <!-- Resource style -->

	<script src="js/modernizr.js"></script> <!-- Modernizr -->
  	
	<title>3D Folding Panel</title>

</head>
<body onload="show_img()" >
	<div  class="cd-fold-content single-page" >


		<div>
			<h2><%=title%></h2>
			<em>日志专属ID:<%=id%> 浏览次数:<%=viewcount%></em>
			<em>作者:<%=username%>  类型:<%=logtype%></em>
			<p>
				<%=message%>
			</p>
		</div>
		<div>
			<div id="imgshow_log" class="htmleaf-container">
			<h3>加载中......</h3>
			</div>
		</div>
	</div>
</body>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->

</body>
<script>

</script>
</html>