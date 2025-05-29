<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<c:url value="b4_upfile" var="url"/> --%>
	<form action="./b4_upfile" method="post" enctype="multipart/form-data">
		<input name="photo" type="file"><br>
		<button>Upload</button>
	</form>
	${path} <br>
	<c:if test="${not empty filename}">
	<img src="${pageContext.request.contextPath}/imgs/${filename}" style="max-width:200px">
</c:if>

</body>
</html>