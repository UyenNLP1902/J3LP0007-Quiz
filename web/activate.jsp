<%-- 
    Document   : activate
    Created on : May 25, 2020, 9:27:35 AM
    Author     : SE140355
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activate Page</title>
    </head>
    <body>       
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>

        <c:if test="${not empty sessionScope}">
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <h1>PLEASE ACTIVATE YOUR ACCOUNT FIRST</h1>
            
            <c:url var="urlRewritting" value="DispatcherController">
                <c:param name="btnAction" value="Logout"/>
            </c:url>
            <a href="${urlRewritting}">Back to login page</a>
        </c:if>
    </body>
</html>
