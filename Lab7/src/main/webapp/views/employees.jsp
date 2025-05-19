<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Employee Management</title>
</head>
<body>
    <c:url var="path" value="/employee" />
    <form method="post" enctype="multipart/form-data">
<!--     <form method="post" > -->
        <label>Id:</label><br> <input name="id" value="${item.id}"><br>
        <label>Fullname:</label><br> <input name="fullname" value="${item.fullname}"><br>
        <label>Password:</label><br> <input type="password" name="password" value="${item.password}"><br>
        <label>Gender:</label><br>
        <input type="radio" name="gender" value="true" <c:if test="${item.gender == true}">checked</c:if> /> Male
        <input type="radio" name="gender" value="false" <c:if test="${item.gender == false}">checked</c:if> /> Female<br>
      	<input type="date" name="birthday"value="${item.birthday != null ? item.birthday : ''}"/>

        <label>Salary:</label><br> 
<%--         <input type="number" step="0.01" name="salary" value="${item.salary != null ? item.salary : ''}"><br><br> --%>
       <input type="number" step="0.01" name="salary"
       value="<fmt:formatNumber value='${item.salary}' type='number' groupingUsed='false' maxFractionDigits='2' minFractionDigits='0'/>" />

        
        <label>Department:</label><br>
        <select name="departmentId">
            <c:forEach var="d" items="${departmentList}">
                <option value="${d.id}" 
                	<c:if test="${item.departmentId == d.id}">selected</c:if>>${d.name}
                </option>
            </c:forEach>
        </select><br>
        <label>Photo:</label><br>
<c:if test="${not empty item.photo}">
    <img src="${pageContext.request.contextPath}/uploads/${item.photo}" alt="Photo" width="120" />
</c:if>
<input type="file" name="photo"><br><br>

        <button formaction="${path}/create">Create</button>
        <button formaction="${path}/update">Update</button>
        <button formaction="${path}/delete">Delete</button>
        <button formaction="${path}/reset">Reset</button>
    </form>
    <hr>
    
    
    <table border="1" style="width: 100%">
        <thead>
            <tr>
                <th>No.</th>
                <th>Id</th>
                <th>Fullname</th>
                <th>Department</th>
                <th>Gender</th>
                <th>Birthday</th>
                <th>Salary</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${list}" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${e.id}</td>
                    <td>${e.fullname}</td>
                    <td>${e.departmentId}</td>
                    <td>
                    	<c:if test="${e.gender == true}">Male</c:if>
                    	<c:if test="${e.gender == false}">Female</c:if>
                    </td>
                    <td>${e.birthday}</td>
<%--                     <td>${e.salary}</td> --%>
<td><fmt:formatNumber value="${e.salary}" type="number" maxFractionDigits="2" minFractionDigits="0"/></td>

                    <td><a href="${path}/edit/${e.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
