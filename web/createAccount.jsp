<%-- 
    Document   : createAccount
    Created on : May 19, 2020, 8:34:38 PM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="DispatcherController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Email* <input type="text" name="txtEmail" 
                          value="${param.txtEmail}" /><br>
            <c:if test="${not empty errors.emailTemplateErr}">
                <font color="red">
                ${errors.emailTemplateErr}
                </font>
            </c:if>
            <br>
            Name* <input type="text" name="txtName" 
                         value="${param.txtName}" />(2-30 characters)<br>
            <c:if test="${not empty errors.nameLengthErr}">
                <font color="red">
                ${errors.nameLengthErr}
                </font>
            </c:if><br>
            Password* <input type="password" name="txtPassword" 
                             value="" />(6-20 characters)<br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font>
            </c:if><br>
            Confirm Password* <input type="password" name="txtConfirm" 
                                     value="" /><br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font>
            </c:if><br>
            <input type="submit" value="Create Account" name="btnAction" />
            <input type="reset" value="Reset" /><br>
            <c:if test="${not empty errors.emailIsExisted}">
                <font color="red">
                ${errors.emailIsExisted}
                </font>
            </c:if> 
        </form>

    </body>
</html>
