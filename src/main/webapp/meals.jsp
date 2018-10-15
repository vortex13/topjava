<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<c:set var="meals" value="${requestScope.meals}"/>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <thead>
    <tr>
        <td>Описание</td>
        <td>Калории</td>
        <td>Дата и время</td>
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <c:choose>
            <c:when test="${meal.isExceed() eq true}">
                <tr style="color: red">
            </c:when>
            <c:otherwise>
                <tr style="color: green">
            </c:otherwise>
        </c:choose>
        <td><c:out value="${meal.getDescription()}"/></td>
        <td><c:out value="${meal.getCalories()}"/></td>
        <td><c:out value="${meal.getFormattedDateTime()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>