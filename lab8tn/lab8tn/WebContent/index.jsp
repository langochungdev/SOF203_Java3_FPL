<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>






<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FPT POLYTECHNIC</h1>
	<hr>




	<%-- <a href="views/trangchu.jsp"><fmt:message key="menu.home"/></a>
<a href="/lab8tn/about"><fmt:message key="menu.about"/></a>
<a href="/lab8tn/contact"><fmt:message key="menu.contact"/> </a>
<a href="/lab8tn/nut">AnhTu</a>
<a href="?lang=vi"> Tiếng Việt </a>
<a href="?lang=en"> English </a>

--%>

	<fmt:setLocale value="${sessionScope.lang}" scope="request" />
	<fmt:setBundle basename="global" scope="request" />
	<c:url var="home" value="/home" />
	<a href="${home}/index"><fmt:message key="menu.home" /></a>
	<a href="${home}/about"><fmt:message key="menu.about" /></a>
	<a href="${home}/contact"><fmt:message key="menu.contact" /> </a> ||
	<a href="?lang=vi"> Tiếng Việt </a>
	<a href="?lang=en"> English </a>
	<hr>
	<jsp:include page="${view}" />
	<div>&copy 2021 design Anh TU</div>
</body>
</html>