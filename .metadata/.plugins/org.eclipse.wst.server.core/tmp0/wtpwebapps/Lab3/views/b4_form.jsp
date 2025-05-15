<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>Title: ${fn:toUpperCase(item.title)}</li>
		<li>Content: 
			<c:choose>
				<c:when test="${fn:length(item.content) > 3}"> 
    					${fn:substring(item.content, 0, 3)}...
   				</c:when>
				<c:otherwise>${item.content}</c:otherwise>
			</c:choose>
		</li>
	</ul>
</body>
</html>