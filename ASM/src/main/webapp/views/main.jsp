<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

nav {
	text-align: center;
	padding: 2vh 0;
	background-color: #f0f0f0;
}

.header, .footer {
	background-color: #4682b4;
	color: white;
	text-align: center;
	height: 10vh;
	line-height: 6;
}

.container {
	display: flex;
	min-height: 74vh;
}

.main {
	flex: 2;
	padding: 20px;
	text-align: center;
}

.sidebar {
	flex: 1;
	background-color: #f4e4bc;
}

.sidebar-section {
	padding: 20px 10px;
}

.yellow {
	background-color: #ffd700;
}

.gray {
	background-color: #808080;
}

.green {
	background-color: #90ee90;
}

.sidebar input {
	width: 100%;
	box-sizing: border-box;
}

a {
	color: black;
	text-decoration: none;
}
/*         trang chu */
.item {
	display: flex;
	margin-bottom: 16px;
}

/* .item img { */
/* 	flex: 2; */
/* 	padding-right: 16px; */
/* } */
.item .noidung {
	flex: 8;
	text-align: left;
}

a {
	color: black;
	text-decoration: none;
}

.subtext {
	font-size: 10px;
}

.item .img {
	flex: 2;
	padding-right: 16px;
	background-color: blue;
	color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 16px;
	text-align: center;
	min-height: 100px; /* Adjust as needed */
}

/* chitiet  */
.b1 {
	display: flex;
	gap: 10px;
	margin-bottom: 10px;
}

.b1 .img {
	flex: 1;
}

.b1 p {
	flex: 4;
}

.b2 div {
	text-align: right;
}
</style>
<link rel="stylesheet" href="./style.css">
</head>

<body>
	<div class="header">Header: Thiết kế thử đồ</div>
	<nav>
		<a href="navigate?page=trangchu">Trang chủ</a> <a
			href="navigate?page=vanhoa">Văn hóa</a> <a
			href="navigate?page=phapluat">Pháp luật</a> <a
			href="navigate?page=thethao">Thể thao</a>
	</nav>
	<div class="container">
		<div class="main">
			<jsp:include page="${includePage}" />

		</div>
		<div class="sidebar">
			<a href='navigate?page=tintucchitiet'><div
					class="sidebar-section yellow">5 bản tin được xem nhiều</div></a> <a
				href='navigate?page=tintucchitiet'><div
					class="sidebar-section yellow">5 bản tin được xem nhiều</div></a> <a
				href='navigate?page=tintucchitiet'><div
					class="sidebar-section yellow">5 bản tin được xem nhiều</div></a> <input
				type="text" placeholder="newsletter" class="sidebar-section">
		</div>
	</div>
	<div class="footer">Footer: Thiết kế thử đồ</div>
</body>

</html>