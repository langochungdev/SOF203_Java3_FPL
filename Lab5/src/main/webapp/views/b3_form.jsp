<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<input name="username" value="${username}"> <br> <input
			name="password" value="${password}"> <br> <input
			type="checkbox" name="remember-me"> Remember me?
		<hr>
		<button>Login</button>
	</form>
	<p style="color: red;">${message}</p>
</body>
</html>