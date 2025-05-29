<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>Name: ${item.name}</li>
		<li>Price: <fmt:formatNumber value="${item.price}" pattern="#,###.00"/>
		</li>
		<li>Date: <fmt:formatDate value="${item.date}" pattern="dd/MM/yyyy"/>
		</li>
	</ul>
</body>
</html>