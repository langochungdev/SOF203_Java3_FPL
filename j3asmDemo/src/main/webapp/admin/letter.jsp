<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h2 class="text-center mb-4">Danh sách Newsletter</h2>
	<table class="table table-bordered">
		<thead class="table-light">
			<tr>
				<c:forTokens var="col" items="No.,Email,Enabled" delims=",">
					<th>${col}</th>
				</c:forTokens>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${item.email}</td>
					<td>${item.enabled}</td>
					<td><a href="${pageContext.request.contextPath}/letter/edit/${vs.count}">Xem
							chi tiết...</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="card p-4">
	<c:url value="/letter" var="root"/>
		<h2 class="text-center mb-4">Newsletter chi tiết</h2>
		<form method="post">
			<div class="mb-3">
				<label for="email" class="form-label">Email:</label> <input
					id="email" name="email" value="${item.email}" class="form-control"
					readonly>
			</div>
			<div class="mb-3">
				<input id="enabled" name="enabled" type="checkbox"
					${item.enabled==true?'checked':''} class="" > <label
					for="enabled" class="form-label">Enabled ?</label>
			</div>
			<div class="text-center">
				<button formaction="${root}/update"	class="btn btn-warning">Sửa</button>
				<button formaction="${root}/delete"	class="btn btn-danger">Xóa</button>
			</div>
		</form>
	</div>
</div>