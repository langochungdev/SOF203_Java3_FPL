<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="card p-4" style="width: 25rem;">
            <h2 class="text-center"></h2>
            <c:url var="url" value="/user"/>
            <form action="${url}/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <input type="checkbox" id="rememberMe" name="rememberMe">
                    <label for="rememberMe" class="form-label">Ghi nhớ đăng nhập cho lần sau</label>
                </div>
                <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
              	<p style="color: red;">${error}</p>
            </form>
            <div class="mt-3 text-center">
                <p>Chưa có tài khoản? <a href="${url}/register">Đăng ký ngay</a></p>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
