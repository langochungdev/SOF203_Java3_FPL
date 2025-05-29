<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/b4_BeanServlet" method="post"> 
	<div>Fullname:</div>
	<input value="${user.fullname}"  ${editable ? 'readonly="readonly"' : ''}><br>
	<div>Gender:</div>
	<input type="radio" name="gender" ${user.gender ? 'checked="checked"':''}>Male<br>
	<input type="radio" name="gender" ${!user.gender ? 'checked="checked"':''}>Female<br>
	<div>Country: ${user.country}</div>
	<select name="country">
		<option value="VN" ${user.country=='VN' ? 'selected="t"' : ''}>Viet Nam</option> 
		<option value="US" ${user.country=='US' ? 'selected="selected"' : ''}>United States</option>
		<option value="CN" ${user.country=='CN' ? 'selected="selected"' : ''}>China</option>
	</select>
	<button ${editable ? 'disabled="disabled"' : ''}>Create</button>
	<button>Update</button>
	</form>
</body>
</html>