<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="c" uri="http://jakarta.ee/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select>
		<c:forEach var="ct" items="${countries}">
			<option value="${ct.id}">${ct.name}</option>
		</c:forEach>
	</select>
</body>
</html>