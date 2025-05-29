<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhập mã xác nhận</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
<c:url var="root" value="/user"/>
	<form action="${pageContext.request.contextPath}${formAction}">
		<label>Mã xác nhận đã được gửi, hãy nhập mã vào ô trống bên
			dưới</label><br> <input type="text" required="required" name="confirm"><br>
		<button>Xác nhận</button><br>
		<p style="color: red;">${errorMess}</p>
	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>