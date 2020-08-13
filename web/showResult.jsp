<%-- 
    Document   : showResult
    Created on : May 29, 2020, 11:47:41 AM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Result Page</title>
    </head>
    <body>
    <c:if test="${empty sessionScope}">
        <c:redirect url="login.html"/>
    </c:if>

    <c:if test="${not empty sessionScope}">
        <font color="red">
        Welcome, ${sessionScope.NAME}
        </font>
        <h1>Result</h1>
        
        <c:set var="subject" value="${requestScope.SUBJECT}"/>
        <c:set var="correct" value="${requestScope.NO_OF_CORRECT}"/>
        <c:set var="score" value="${requestScope.SCORE}"/>
        
        <font color="red">Subject:</font> ${subject.id} - ${subject.name} <br>
        <font color="red">Correct questions:</font> ${correct}/${subject.noOfQuestion} <br>
        <font color="red">Score:</font> ${score} <br><br>

        <a href="mainPage.jsp">Back To Main Page</a>
    </c:if>
</body>
</html>
