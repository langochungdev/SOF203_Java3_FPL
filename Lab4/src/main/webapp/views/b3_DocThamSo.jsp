<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="b3_DocThamSo" method="post">
        <label>Tên đăng nhập: <input type="text" name="username" value="hung" /></label><br>
        <label>Mật khẩu: <input type="password" name="password" value="123" /></label><br>

        Giới tính:
        <label><input type="radio" name="gender" value="Nam" checked />Nam</label>
        <label><input type="radio" name="gender" value="Nữ" />Nữ</label><br>

        <label><input type="checkbox" name="married" value="yes" />Đã có gia đình?</label><br>

        Quốc tịch:
        <select name="nationality">
            <option>Vietnam</option>
            <option selected>United States</option>
            <option>Japan</option>
        </select><br>

        Sở thích:
        <label><input type="checkbox" name="hobby" value="Đọc sách" />Đọc sách</label>
        <label><input type="checkbox" name="hobby" value="Du lịch" checked />Du lịch</label>
        <label><input type="checkbox" name="hobby" value="Âm nhạc" checked />Âm nhạc</label>
        <label><input type="checkbox" name="hobby" value="Khác" />Khác</label><br>

        <label>Ghi chú:</label><br>
        <textarea name="note" rows="3" cols="40">Đang tìm bạn gái</textarea><br>

        <button>Đăng ký</button>
    </form>
</body>
</html>