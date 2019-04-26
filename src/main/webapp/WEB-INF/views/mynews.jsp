<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="hxy.inspec.admin.po.AdminUser"%>
<%
	AdminUser user = (AdminUser) request.getSession().getAttribute("user");
	if (user == null) {
		request.getRequestDispatcher("/lose").forward(request, response);
	} else {
	
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>