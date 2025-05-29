<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<form>
		<table class="table table-bordered">
			<thead class="table-light">
				<tr>
					<c:forTokens var="col" items="Id,Loại tin,Action" delims=","
						varStatus="vs">
						<th ${vs.count==3?'colspan="2"':''}>${col}</th>
					</c:forTokens>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cate" items="${list}">
					<tr>
						<td>${cate.repId}</td>
						<td><input type="text" name="update${cate.id}"
							value="${cate.name}"></td>
						<td>
							<button
								formaction="${pageContext.request.contextPath}/admin/category/update/${cate.id}">Sửa
							</button>
						</td>
						<td>
							<button
								formaction="${pageContext.request.contextPath}/admin/category/delete/${cate.id}">Xóa
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

	<a class="btn btn-primary"
		href="${pageContext.request.contextPath}/admin/category/new">Thêm
		thể loại</a>
	<c:if test="${pageContext.request.servletPath=='/admin/category/new'}">
		<br>
		<form>
			<label for="newCate">Loại tin: </label> <input type="text"
				name="newCate" id="newCate"> <input type="button"
				formaction="${pageContext.request.contextPath}/admin/category/insert"
				value="Thêm">
		</form>
	</c:if>


</div>
