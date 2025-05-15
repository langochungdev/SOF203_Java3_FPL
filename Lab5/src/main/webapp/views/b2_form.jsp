<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Gửi Email</h2>
	<form action="b2_Servlet" method="post">
		From: <input type="email" name="from" required><br>
		<br> To: <input type="email" name="to" required><br>
		<br> Subject: <input type="text" name="subject" required><br>
		<br> Body:<br>
		<textarea name="body" rows="5" cols="40" required></textarea>
		<br>
		<br>
		<button type="submit">Gửi Email</button>
	</form>
</body>
</html>