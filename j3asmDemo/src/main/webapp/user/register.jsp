<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
	  <style>
        /* Background image and blur effect */
        body {
            background: url('your-background-image.jpg') no-repeat center center fixed;
            background-size: cover;
        }

        /* Apply blur and semi-transparent background to the form container */
        .blur-background {
            background: rgba(255, 255, 255, 0.7); /* White with transparency */
            backdrop-filter: blur(10px); /* Blur effect */
            border-radius: 10px; /* Optional rounded corners */
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2); /* Optional shadow */
        }
    </style>
</head>
<body>
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="card p-4" style="width: 25rem;">
            <h2 class="text-center">Đăng ký tài khoản</h2>
            <form action="${pageContext.request.contextPath}/user/register" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>
                <div class="form-check mb-3">
				    <input class="form-check-input" type="checkbox" id="authorCheck" name="authorCheck">
					<label class="form-check-label" for="authorCheck">Đăng ký làm tác giả</label>
				</div>
				<div class="form-check mb-3">
				    <input class="form-check-input" type="checkbox" id="newsCheck" name="letterCheck">
					<label class="form-check-label" for="newsCheck">Đăng ký nhận tin mới nhất</label>
				</div>
                <h3 style="color: red;">${error}</h3>
                <button type="submit" class="btn btn-primary w-100">Xác thực</button>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
