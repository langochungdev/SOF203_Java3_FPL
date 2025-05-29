<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<input name="a" value="${a}"><br> 
		<input name="b" value="${b}"><br>
		<button formaction="./cong">+</button>
		<button formaction="./tru">-</button>
		<button formaction="./nhan">*</button>
		<button formaction="./chia">/</button>
		<button formaction="./reset">reset</button>
	</form>
	${mess}
</body>
</html>