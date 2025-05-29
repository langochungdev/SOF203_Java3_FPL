<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table class="table table-bordered">
		<thead class="table-light">
			<tr>
				<c:forTokens var="col"
					items="Id,Bút ký,Họ tên,Ngày sinh,Giới tính,Số điện thoại,Email,Vai trò"
					delims=",">
					<th>${col}</th>
				</c:forTokens>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${list}">
				<c:if test="${sessionScope.currUser.id!=user.id}">
					<tr>
						<td>${user.repId}</td>
						<td>${user.username}</td>
						<td>${user.fullname}</td>
						<td>${user.birthday}</td>
						<td>${user.gender==true?'Nam':'Nữ'}</td>
						<td>${user.mobile}</td>
						<td>${user.email}</td>
						<td>${user.role==true?'Admin':'Ký giả'}</td>
						<td><a href="${pageContext.request.contextPath}/admin/user/edit/${user.id}">Xem
								chi tiết...</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/admin/user/blank" class="btn btn-primary">Thêm
		user</a>
</div>