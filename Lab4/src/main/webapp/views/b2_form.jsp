<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/calculate" var="cal" />
	<form method="post">
		<input name="x"><br> 
		<input name="y"><br>
		<button formaction="${cal}/add">+</button>
		<button formaction="${cal}/sub">-</button>
	</form>
	${message}
</body>
</html>