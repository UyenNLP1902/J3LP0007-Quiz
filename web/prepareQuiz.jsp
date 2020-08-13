<%-- 
    Document   : prepareQuiz
    Created on : May 28, 2020, 7:46:09 AM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prepare Quiz</title>
    </head>
    <body>
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${not empty sessionScope}">
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <br><br>

            <h1>Take Quiz</h1>
            <c:set var="subject" value="${requestScope.INFO}"/>
            <font color="red">Subject:</font> ${subject.id} - ${subject.name} <br>
            <font color="red">Number of questions:</font> ${subject.noOfQuestion} <br>
            <font color="red">Duration:</font> ${subject.timer} minutes <br><br>

            <form action="DispatcherController">
                <input type="hidden" name="txtSubjectId" value="${subject.id}" />
                <input type="submit" value="Start" name="btnAction" />
            </form>
            <br>
            <a href="mainPage.jsp">Return To Main Page</a>
        </c:if>
    </body>
</html>
