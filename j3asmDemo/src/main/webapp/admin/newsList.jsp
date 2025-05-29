<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<!-- Search Bar -->
	<div style="margin: auto; margin-bottom: 15px; margin-top: 15px" class="border-solid col-md-4 text-center">
		<form action="${pageContext.request.contextPath}/admin/news/search"
			class="d-inline-block w-100">
			<div class="input-group">
				<input type="text" class="form-control"
					placeholder="Tìm theo tiêu đề, nội dung, thể loại, tác giả"
					name="search">
				<button class="btn btn-outline-light" type="submit">
					<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="25"
						height="25" viewBox="0 0 50 50">
								<path
							d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z"></path>
							</svg>
				</button>
			</div>
		</form>
	</div>
	<table class="table table-bordered">
		<thead class="table-light">
			<tr>
				<c:forTokens var="col"
					items="Id,Tác giả,Loại tin,Tiêu đề,Ngày đăng tải,Lượt xem,Trang chủ"
					delims=",">
					<th>${col}</th>
				</c:forTokens>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${list}">
				<tr>

					<td>${article.repId}</td>
					<td>${article.authorName}</td>
					<td>${article.categoryName}</td>
					<td>${article.title}</td>
					<td>${article.postedDate}</td>
					<td>${article.viewCount}</td>
					<td>${article.home}</td>
					<td><a href="${pageContext.request.contextPath}/admin/news/edit/${article.id}">Xem
							chi tiết...</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/admin/news/blank" class="btn btn-primary" ${sessionScope.currUser.role==true?'hidden':''}>Tạo
		tin mới</a>
</div>
