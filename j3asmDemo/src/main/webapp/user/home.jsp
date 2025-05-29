<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	/* Font và màu sắc chung */
	body {
		font-family: 'Arial', sans-serif;
		color: #333; /* Màu chữ xám đậm */
	}

	h5, h6, a {
		font-weight: bold;
		color: #0056b3; /* Màu xanh đậm */
	}

	a:hover {
		color: #dc1d1d; /* Màu xanh tối hơn khi hover */
		text-decoration: underline;
	}

	/* Carousel Slider */
	.carousel-caption h5 {
		background-color: rgba(0, 0, 0, 0.6); /* Nền đen trong suốt */
		color: #fff; /* Chữ màu trắng */
		padding: 10px;
		border-radius: 3px;
	}

	.carousel-caption a {
		color: #fff; /* Link màu trắng */
	}

	/* Thẻ tin bài */
	.card-header {
		background-color: #f8f9fa; /* Màu nền nhạt */
		color: #333; /* Màu chữ đậm */
		border-bottom: 1px solid #ddd;
	}

	.card-body {
		padding: 10px;
	}

	/* Ảnh bài viết */
	.img-fluid {
		border: 1px solid #ddd;
		border-radius: 4px;
		margin-bottom: 10px;
	}

	/* Đoạn tóm tắt bài viết */
	.text-muted {
		font-size: 0.9rem;
		color: #777; /* Màu xám nhạt */
	}

	/* Danh sách bài viết nổi bật */
	ul.list-group li {
		border-bottom: 1px solid #ddd;
		padding: 10px;
	}

	ul.list-group li a {
		font-size: 0.95rem;
		font-weight: bold;
	}

	/* Nút đăng ký nhận tin */
	form input[type="email"] {
		border: 1px solid #ccc;
		padding: 8px;
	}

	form button {
		background-color: #0056b3;
		color: #fff;
	}

	form button:hover {
		background-color: #003d7a;
	}

	/* Các bản tin đã xem */
	.row.mb-3 {
		border-bottom: 1px solid #eee;
		padding-bottom: 10px;
		margin-bottom: 10px;
	}

	h5 a {
		font-size: 1.1rem;
	}

	h5 a:hover {
		color: #0056b3;
		text-decoration: underline;
	}

	small.text-muted {
		font-size: 0.85rem;
	}

</style>
<div class="container my-4">
	<div class="row">
		<!-- Carousel Slider -->
		<div class="col-md-9">
			<div class="border bg-white p-5">
				<div id="articleSlider" class="carousel slide"
					data-bs-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<c:forEach var="slider" items="${homePageList}" varStatus="status">
							<li data-bs-target="#articleSlider" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
						</c:forEach>
					</ol>

					<div class="carousel-inner">
						<c:forEach var="slider" items="${homePageList}" varStatus="status">
							<div class="carousel-item ${status.index == 0 ? 'active' : ''}">
								<img src="${pageContext.request.contextPath}${slider.imagePath}" class="d-block w-100"
									alt="${slider.title}">
								<div class="carousel-caption d-none d-md-block">
									<h5>
										<a href="${pageContext.request.contextPath}/user/detail/${slider.id}"
											style="text-decoration: none;">${slider.title}</a>
									</h5>
								</div>
							</div>
						</c:forEach>
					</div>

					<!-- Controls -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#articleSlider" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#articleSlider" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>

			<!-- 5 Articles Already Viewed -->
			<div class="card mb-3">
				<div class="card-header bg-success text-white">5 bản tin bạn
					đã xem</div>
				<div class="card-body">
					<c:forEach var="viewed" items="${viewedList}">
						<div class="row mb-3">
							<div class="col-md-2">
								<a href="${pageContext.request.contextPath}/user/detail/${viewed.id}"> <img
										src="${pageContext.request.contextPath}${viewed.imagePath}" class="img-fluid rounded" alt="Ảnh"
										style="max-width: 60%;">
								</a>
							</div>
							<div class="col-md-10">
								<h5>
									<a href="${pageContext.request.contextPath}/user/detail/${viewed.id}">${viewed.title}</a>
								</h5>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<!-- Latest Articles -->
		<div class="col-md-3">
			<div class="card mb-4">
				<div class="card-header bg-secondary text-white">5 bản tin mới
					nhất</div>
				<ul class="list-group list-group-flush">
					<c:forEach var="lastest" items="${latestList}">
						<div class="row mb-3">
							<div class="col-md-12 text-center">
								<a href="${pageContext.request.contextPath}/user/detail/${lastest.id}"> <img
									src="${pageContext.request.contextPath}${lastest.imagePath}" class="img-fluid rounded" alt="Ảnh"
									style="max-width: 100%; height: auto;">
								</a>
								<h6 class="mt-2">
									<a href="${pageContext.request.contextPath}/user/detail/${lastest.id}"
										style="text-decoration: none; font-size: 0.9rem;">${lastest.title}
									</a>
								</h6>
							</div>
						</div>
					</c:forEach>
				</ul>
			</div>

			<!-- Newsletter Subscription -->
			<div class="card mb-3">
				<div class="card-header bg-secondary text-white">Đăng ký nhận tin</div>
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/letter/subscribe" method="post"
						class="d-flex">
						<input name="email" type="email"
							class="form-control form-control-sm me-2"
							placeholder="newsletter@example.com">
							<span class="${textColor}">${errorMess}</span>
						<button class="btn btn-primary btn-sm" type="submit">
							<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
								fill="currentColor" class="bi bi-newspaper" viewBox="0 0 16 16">
							  <path ="M0 2.5A1.5 1.5 0 0 1 1.5 1h11A1.5 1.5 0 0 1 14 2.5v10.528c0 .3-.05.654-.238.972h.738a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 1 1 0v9a1.5 1.5 0 0 1-1.5 1.5H1.497A1.497 1.497 0 0 1 0 13.5zM12 14c.37 0 .654-.211.853-.441.092-.106.147-.279.147-.531V2.5a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0-.5.5v11c0 .278.223.5.497.5z" />
							  <path d="M2 3h10v2H2zm0 3h4v3H2zm0 4h4v1H2zm0 2h4v1H2zm5-6h2v1H7zm3 0h2v1h-2zM7 8h2v1H7zm3 0h2v1h-2zm-3 2h2v1H7zm3 0h2v1h-2zm-3 2h2v1H7zm3 0h2v1h-2z" />
							</svg>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-12">
		<!-- Most Viewed Articles -->
		<div class="card mb-3">
			<div class="card-header bg-warning text-white">5 bản tin được
				xem nhiều</div>
			<div class="card-body">
				<c:forEach var="mostViewed" items="${mostViewedList}">
					<div class="row mb-3">
						<div class="col-md-2">
							<a href="${pageContext.request.contextPath}/user/detail/${mostViewed.id}"> <img
								src="${pageContext.request.contextPath}${mostViewed.imagePath}" class="img-fluid rounded" alt="Ảnh"
								style="max-width: 100%;">
							</a>
						</div>
						<div class="col-md-10">
							<h5>
								<a href="${pageContext.request.contextPath}/user/detail/${mostViewed.id}"
									style="text-decoration: none;">${mostViewed.title}</a>
							</h5>
							<p class="text-muted">${mostViewed.excerpt}</p>
							<small class="text-muted"> <svg
									xmlns="http://www.w3.org/2000/svg" width="20" height="20"
									fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 15 20">
									  <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0" />
									  <path
										d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7" />
									</svg> ${mostViewed.viewCount}
							</small>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

