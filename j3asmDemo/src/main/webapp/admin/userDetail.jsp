<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container mt-3">
	<c:url var="root" value="/admin/user" />
	<c:if test="${sessionScope.currUser.id!=item.id}">
	<div class="mb-4">
		<a href="${root}/list" class="btn btn-secondary">Trở về
			danh sách</a>
	</div>
	</c:if>
	<div class="card p-4">
		<h2 class="text-center mb-4">Thông Tin Người Dùng</h2>
		<form method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label">Hình ảnh</label>
				<div>
					<img alt="${item.avatar}" src="${pageContext.request.contextPath}${item.avatarPath}"
						 class="img-fluid mb-2" id="preview" width="200px" height="300px"> <br> <input
						type="file" name="avatar" id="file" accept="image/*"
						onchange="previewImage(event)" ${sessionScope.currUser.id==item.id?'':'hidden'}> <br>
				</div>
			</div>
			<div class="mb-3">
				<label for="id" class="form-label">Id:</label> <input id="id"
					name="repId" value="${item.repId}" readonly class="form-control">
			</div>
			<div class="mb-3">
				<label for="username" class="form-label">Bút ký:</label> <input
					id="username" name="username" value="${item.username}"
					class="form-control">
			</div>
			<div class="mb-3">
				<label for="fullname" class="form-label">Họ tên:</label> <input
					id="fullname" name="fullname" value="${item.fullname}"
					class="form-control">
			</div>
			<div class="mb-3">
				<label for="birthday" class="form-label">Ngày sinh:</label> <input
					type="date" id="birthday" name="birthday" value="${item.birthday}"
					class="form-control">
			</div>
			<div class="mb-3">
				<label class="form-label">Giới tính:</label>
				<div>
					<input type="radio" name="gender" value="true" id="male"
						${item.gender==true?'checked':''}> <label for="male"
						class="form-label me-2">Nam</label> <input type="radio"
						name="gender" value="false" id="female"
						${item.gender==false?'checked':''}> <label for="female"
						class="form-label">Nữ</label>
				</div>
			</div>
			<div class="mb-3">
				<label for="mobile" class="form-label">Số điện thoại:</label> <input
					id="mobile" name="mobile" value="${item.mobile}"
					class="form-control">
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email:</label> <input
					id="email" name="email" value="${item.email}" class="form-control" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">Vai trò:</label>
				<div>
					<input type="radio" name="role" value="true" id="admin"
						${item.role==true?'checked':''} ${sessionScope.currUser.id==item.id?'disabled':''}> <label for="admin"
						class="form-label me-2">Admin</label> <input type="radio"
						name="role" value="false" id="reporter"
						${item.role==false?'checked':''} ${sessionScope.currUser.id==item.id?'disabled':''}> <label for="reporter"
						class="form-label">Ký giả</label>
				</div>
			</div>
			<hr>
			<div class="text-center">
				<button formaction="${root}/create" ${(action=='edit'||sessionScope.currUser.id==item.id)?'hidden':''}
					class="btn btn-success">Tạo</button>
				<button formaction="${root}/update" ${action=='create'?'hidden':''}
					class="btn btn-warning">Sửa</button>
				<button formaction="${root}/delete" ${(action=='create'||sessionScope.currUser.id==item.id)?'hidden':''}
					class="btn btn-danger">Xóa</button>
				<button formaction="${root}/reset" class="btn btn-secondary" ${sessionScope.currUser.id==item.id?'hidden':''}>Làm
					mới</button>
			</div>
		</form>
	</div>
</div>
<script>
	function previewImage(event) {
		const preview = document.getElementById('preview');
		const file = event.target.files[0];
		const reader = new FileReader();

		reader.onload = function () {
			preview.src = reader.result;
		}

		if (file) {
			reader.readAsDataURL(file);
		}
	}
</script>