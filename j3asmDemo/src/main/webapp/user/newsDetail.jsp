<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container my-4">
    <h1 class="mb-4 text-center"></h1>
    <div class="row">
        <div class="col-md-4">
            <img src="${pageContext.request.contextPath}${article.imagePath}" class="img-fluid rounded"
                 alt="Ảnh chi tiết" style="max-width: 100%; height: auto;">
        </div>
        <div class="col-md-8">
            <h5 class="text-primary" style="text-decoration: none;">${article.title}</h5>
            <p class="text-muted">${article.content}</p>
            <small class="text-muted">
                <strong>Ngày đăng:</strong> ${article.postedDate} | <strong>Tác giả:</strong> ${article.getAuthorName()}
            </small>
        </div>
    </div>

    <!-- Related News Section -->
    <div class="mt-4">
        <c:if test="${not empty relatedNewsList}">
            <h4>Các tin tức liên quan</h4>
        </c:if>
        <div class="row">
            <c:forEach var="relatedNews" items="${relatedNewsList}">
                <div class="col-md-4">
                    <div class="card mb-3">
                        <img src="${pageContext.request.contextPath}${relatedNews.imagePath}" class="card-img-top"
                             alt="Tin tức liên quan" style="width: 100%; height: auto;">
                        <div class="card-body">
                            <h5 class="card-title">${relatedNews.title}</h5>
                            <p class="card-text">${relatedNews.excerpt}</p>
                            <a href="${pageContext.request.contextPath}/user/detail/${relatedNews.id}"
                               class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/user/home"
           class="btn btn-primary position-fixed bottom-0 end-0 m-4">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list"
                 viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
            </svg>
        </a>
    </div>
</div>

