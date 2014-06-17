<!DOCTYPE html>
<%@page import="com.common.web.RequestUtils"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>404
<%
	System.out.println(RequestUtils.getLocation(request));
%>
</h1>
</body>
</html>