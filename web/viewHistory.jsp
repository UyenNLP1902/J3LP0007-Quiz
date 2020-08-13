<%-- 
    Document   : viewHistory
    Created on : May 30, 2020, 2:11:11 PM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View History</title>
    </head>
    <body>
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not empty sessionScope}">
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <h1>View Quiz History</h1>

            <c:set var="subjectList" value="${sessionScope.LIST_SUBJECT}"/>
            <c:set var="noOfPages" value="${requestScope.noOfPages}"/>
            <c:set var="currentPage" value="${requestScope.currentPage}"/>
            <c:set var="history" value="${requestScope.VIEW_RESULT}"/>

            <form action="DispatcherController">
                Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /><br>
                Subject <select name="cboSubject">
                    <option value="All" ${param.cboSubject == 'All' ? 'selected' : ''}>All</option>
                    <c:forEach var="s" items="${subjectList}">
                        <option value="${s.id}" ${param.cboSubject == s.id ? 'selected' : ''}>${s.id}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="View Quiz History" name="btnAction" />
                <input type="hidden" name="txtCurrentPage" value="1" />
            </form>

            <c:set var="history" value="${requestScope.VIEW_RESULT}"/>
            <c:if test="${not empty history}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Email</th>
                            <th>Subject ID</th>
                            <th>Score</th>
                            <th>Correct Answers</th>
                            <th>Quiz Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${history}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                    <input type="hidden" name="txtId" value="${item.id}" />
                                </td>
                                <td>
                                    ${item.email}
                                </td>
                                <td>
                                    ${item.subjectId}
                                </td>
                                <td>
                                    ${item.score}
                                </td>
                                <td>
                                    ${item.numberOfCorrect}/
                                    <c:forEach var="s" items="${subjectList}">
                                        <c:if test="${s.id == item.subjectId}">
                                            ${s.noOfQuestion}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    ${item.dateQuiz}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${currentPage != 1}">
                    <c:url var="urlRewritting" value="DispatcherController">
                        <c:param name="btnAction" value="Search"/>
                        <c:param name="txtCurrentPage" value="${currentPage - 1}"/>
                        <c:param name="txtKeyword" value="${keyword}"/>
                        <c:param name="cboStatus" value="${status}"/>
                        <c:param name="cboSubject" value="${subject}"/>
                    </c:url>
                    <a href="${urlRewritting}">Previous</a>
                </c:if>
                Page ${currentPage}/${noOfPages}
                <c:if test="${currentPage < noOfPages}">
                    <c:url var="urlRewritting" value="DispatcherController">
                        <c:param name="btnAction" value="Search"/>
                        <c:param name="txtCurrentPage" value="${currentPage + 1}"/>
                        <c:param name="txtKeyword" value="${keyword}"/>
                        <c:param name="cboStatus" value="${status}"/>
                        <c:param name="cboSubject" value="${subject}"/>
                    </c:url>
                    <a href="${urlRewritting}">Next</a>
                </c:if>
            </c:if>
        </c:if>
    </body>
</html>
