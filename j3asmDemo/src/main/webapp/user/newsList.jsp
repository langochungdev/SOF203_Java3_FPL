<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container my-4">
    <h1 class="mb-4">${category} </h1>
        <c:forEach var="article" items="${articleList}">
            <div class="row mb-3">
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/user/detail/${article.id}">
                    <img src="${pageContext.request.contextPath}${article.imagePath}" class="img-fluid rounded" alt="Ảnh"
                         style="max-width: 100%;">
                </a>
            </div>
            <div class="col-md-10">
                <h5>
                    <a href="${pageContext.request.contextPath}/user/detail/${article.id}" style="text-decoration: none;">${article.title}</a>
                </h5>
                <p class="text-muted">${article.excerpt}</p>
                <small class="text-muted"> Ngày đăng: ${article.postedDate} | Tác giả: ${article.authorName} </small>
            </div>
        </div>
    </c:forEach>

    <hr>
</div>


