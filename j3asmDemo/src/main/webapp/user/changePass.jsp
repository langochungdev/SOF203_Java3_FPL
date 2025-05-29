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
	<div class="d-flex justify-content-center align-items-center vh-100">
		<div class="card p-4" style="width: 25rem;">
			<h2 class="text-center"></h2>
			<c:url var="url" value="/user" />
			<form action="${url}/changePass" method="post">
				<div class="mb-3">
					<label for="currPass" class="form-label">Mật khẩu hiện tại</label> <input
						type="password" class="form-control" id="currPass" name="currPass" required>
				</div>
				<div class="mb-3">
					<label for="newPass" class="form-label">Mật khẩu mới</label> <input
						type="password" class="form-control" id="newPass" name="newPass"
						required>
				</div>
				<div class="mb-3">
					<label for="confirmPass" class="form-label">Xác nhận mật khẩu</label> <input
						type="password" class="form-control" id="confirmPass" name="confirmPass"
						required>
				</div>
				<button type="submit" class="btn btn-primary w-100">Đổi mật khẩu</button>
				<p style="color: red;">${error}</p>
			</form>
			<div class="mt-3 text-center">
				<p>
					Quên mật khẩu? <a href="${url}/forgetPass">Khôi phục ngay</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>