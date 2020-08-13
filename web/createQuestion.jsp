<%-- 
    Document   : search
    Created on : May 21, 2020, 9:17:37 AM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.NAME}
        </font>
        <h1>Create Question</h1>

        <form action="DispatcherController">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            <c:set var="subjectList" value="${sessionScope.LIST_SUBJECT}"/>
            Subject <select name="cboSubject">
                <c:forEach var="s" items="${subjectList}">
                    <option value="${s.id}" ${param.cboSubject == s.id ? 'selected' : ''}>${s.id}</option>
                </c:forEach>
            </select><br>
            Question: <input type="text" name="txtQuestion" value="${param.txtQuestion}" /><br>
            <c:if test="${not empty errors.questionIsEmpty}">
                <font color="red">
                ${errors.questionIsEmpty}
                </font>
            </c:if><br>
            Answer A: <input type="text" name="txtAnswerA" value="${param.txtAnswerA}" /><br>
            Answer B: <input type="text" name="txtAnswerB" value="${param.txtAnswerB}" /><br>
            Answer C: <input type="text" name="txtAnswerC" value="${param.txtAnswerC}" /><br>
            Answer D: <input type="text" name="txtAnswerD" value="${param.txtAnswerD}" /><br>
            <c:if test="${not empty errors.answerIsEmpty}">
                <font color="red">
                ${errors.answerIsEmpty}
                </font>
            </c:if>
            <br>            

            Correct answer: 
            A: <input type="radio" name="btnCorrect" value="A" /> ;
            B: <input type="radio" name="btnCorrect" value="B" /> ;
            C: <input type="radio" name="btnCorrect" value="C" /> ;
            D: <input type="radio" name="btnCorrect" value="D" /> ;
            <br>            
            <c:if test="${not empty errors.noCorrectAnswer}">
                <font color="red">
                ${errors.noCorrectAnswer}
                </font><br>
            </c:if>
            <br>
            <input type="submit" value="Create" name="btnAction" />
        </form>
    </body>
</html>
