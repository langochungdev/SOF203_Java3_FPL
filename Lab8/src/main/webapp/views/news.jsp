<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.LANG}"/>
	<fmt:setBundle basename="i18n.lang"/>
	<h1>FPL</h1>
	<hr>
	<p>
	<a href="./home"><fmt:message>menu.home</fmt:message></a>|
	<a href="./news"><fmt:message>menu.news</fmt:message></a>|
	<a href="./contact"><fmt:message>menu.contact</fmt:message></a>|
	<a href="?lang=en_US"><fmt:message>menu.english</fmt:message></a>|
	<a href="?lang=vi_VN"><fmt:message>menu.vietnamese</fmt:message></a>
	</p>
	<h2><fmt:message>news.content</fmt:message></h2>
</body>
</html>