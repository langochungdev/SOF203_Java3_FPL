<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Department Management</title>
</head>
<body>
	<c:url var="path" value="/department" />
	<form method="post" >
		<label>Id:</label><br> <input name="id" value="${item.id}"><br>
		<label>Name:</label><br> <input name="name" value="${item.name}"><br>
		<label>Description:</label><br>
		<textarea name="description" rows="3">${item.description}</textarea>
		<hr>
		<button formaction="${path}/create">Create</button>
		<button formaction="${path}/update">Update</button>
		<button formaction="${path}/delete">Delete</button>
		<button formaction="${path}/reset">Reset</button>
	</form>
	<hr>

	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<th>No.</th>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${d.id}</td>
					<td>${d.name}</td>
					<td>${d.description}</td>
					<td><a href="${path}/edit/${d.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>