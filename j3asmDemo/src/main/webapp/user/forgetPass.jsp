<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url var="root" value="/user"/>
	<div class="d-flex justify-content-center align-items-center vh-100">
		<div class="card p-4" style="width: 25rem;">
			<form action="${root}/forgetPass" method="post">
				<c:if test="${sessionScope.isConfirm==false}">
					<h2 class="text-center">Hãy nhập email của bạn</h2>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"
							required>
					</div>
				</c:if>
				<c:if test="${sessionScope.isConfirm==true}">
					<h2 class="text-center">Tạo mật khẩu mới</h2>
					<div class="mb-3">
						<label for="newPassword" class="form-label">Mật khẩu mới</label> <input
							type="password" class="form-control" id="newPassword"
							name="newPassword" required>
					</div>
					<div class="mb-3">
						<label for="confirmPassword" class="form-label">Xác nhận
							mật khẩu mới</label> <input type="password" class="form-control"
							id="confirmPassword" name="confirmPassword" required>
					</div>
				</c:if>
				<h3 style="color: red;">${error}</h3>
				<button type="submit" class="btn btn-primary w-100">Tiếp
					tục</button>
			</form>
		</div>
	</div>
</body>
</html>