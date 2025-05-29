<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <style>
        .dropdown:hover .dropdown-menu {
            display: block;
            min-width: auto;
            width: max-content;
            padding: 0;
        }

        .dropdown-item {
            display: block;
            width: 100%;
            padding: 8px 16px;
            text-align: left;
            white-space: nowrap;
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<c:url value="/user" var="path"/>
<div class="container row align-items-center">
    <!-- Newspaper Name -->
    <div class="col-md-4 text-start">
        <img src="${pageContext.request.contextPath}/photo/logo.png" width="60%" alt="Logo" />
    </div>

    <!-- Search Bar -->
    <div class="col-md-4 text-center">
        <form class="d-inline-block w-100" action="${path}/search" method="get">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Tìm kiếm" name="search">
                <button class="btn btn-outline-light" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="25"
                         height="25" viewBox="0 0 50 50">
                        <path d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z"></path>
                    </svg>
                </button>
            </div>
        </form>
    </div>

    <div class="col-md-4 text-end">
        <!-- User Icon -->
        <div class="dropdown d-inline-block mx-1">
            <button class="btn btn-light account-icon" type="button"
                    id="dropdownMenuButton" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                     fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                    <path
                            d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
                </svg>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">

                <c:if test="${sessionScope.currUser != null}">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/news">Trang
                        quản trị</a></li>
                    <li><a class="dropdown-item"
                           href="${pageContext.request.contextPath}/admin/user/edit/${sessionScope.currUser.id}">Trang
                        cá nhân</a></li>
                    <li><a class="dropdown-item"
                           href="${pageContext.request.contextPath}/user/changePass">Đổi mật khẩu</a></li>
                    <li><a class="dropdown-item" href="${path}/logout">Đăng
                        xuất</a></li>
                </c:if>
                <c:if test="${sessionScope.currUser == null}">
                    <li><a class="dropdown-item" href="${path}/login">Đăng nhập</a></li>
                    <li><a class="dropdown-item" href="${path}/register">Đăng ký</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<nav class="bg-light text-center py-2">
    <a href="${path}/home" class="text-decoration-none mx-2">Trang chủ</a>
    <a href="${path}/culture" class="text-decoration-none mx-2">Văn hóa</a>
    <a href="${path}/law" class="text-decoration-none mx-2">Pháp luật</a>
    <a href="${path}/sports" class="text-decoration-none mx-2">Thể thao</a>
    <a href="${path}/travel" class="text-decoration-none mx-2">Du lịch</a>
    <a href="${path}/tech" class="text-decoration-none mx-2">Công nghệ</a>
</nav>
<hr>
<main>
    <jsp:include page="${view}"/>
</main>
<div class="bg-dark text-white text-center py-3 mt-5 mt-auto">
    <p>&copy; 2024 Website Tin Tức. All rights reserved</p>
</div>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
