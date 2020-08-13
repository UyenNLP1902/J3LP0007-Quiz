<%-- 
    Document   : search
    Created on : May 19, 2020, 11:03:07 AM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>

        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not empty sessionScope}">
            <form action="DispatcherController">
                <input type="submit" value="Logout" name="btnAction" />
            </form>
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <h1>Main Page</h1>

            <c:if test="${not empty sessionScope.ROLE}">
                <a href="createQuestion.jsp">Create Question</a><br>
                <a href="viewHistory.jsp">View Quiz History</a><br>
                <hr>
                <c:set var="subjectList" value="${sessionScope.LIST_SUBJECT}"/>

                <div>
                    <form action="DispatcherController">
                        Keyword <input type="text" name="txtKeyword" 
                                       value="${param.txtKeyword}" /><br>
                        Status <select name="cboStatus">
                            <option value="All" ${param.cboStatus == 'All' ? 'selected' : ''}>All</option>
                            <option value="Available" ${param.cboStatus == 'Available' ? 'selected' : ''}>Available</option>
                            <option value="Disable" ${param.cboStatus == 'Disable' ? 'selected' : ''}>Disable</option>
                        </select><br>

                        Subject <select name="cboSubject">
                            <option value="All" ${param.cboSubject == 'All' ? 'selected' : ''}>All</option>
                            <c:forEach var="s" items="${subjectList}">
                                <option value="${s.id}" ${param.cboSubject == s.id ? 'selected' : ''}>${s.id}</option>
                            </c:forEach>
                        </select><br>
                        <input type="submit" value="Search" name="btnAction" />
                        <input type="hidden" name="txtCurrentPage" value="1" />
                    </form>
                </div>
                <br>                


                <c:set var="currentPage" value="${requestScope.currentPage}"/>
                <c:set var="noOfPages" value="${requestScope.noOfPages}"/>
                <c:set var="keyword" value="${param.txtKeyword}"/>
                <c:set var="status" value="${param.cboStatus}"/>
                <c:set var="subject" value="${param.cboSubject}"/>
                <c:set var="bank" value="${requestScope.SEARCH_RESULTS}"/>

                <c:if test="${not empty bank}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Question</th>
                                <th>Subject ID</th>
                                <th>Available</th>
                                <th>Answer A</th>
                                <th>Answer B</th>
                                <th>Answer C</th>
                                <th>Answer D</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${bank}" varStatus="counter">
                            <form action="DispatcherController">
                                <c:set var="question" value="${item.key}"/>
                                <c:set var="answers" value="${item.value}"/>
                                <tr>
                                    <td>
                                        ${counter.count}
                                        <input type="hidden" name="txtQuestionId" value="${question.id}" />
                                    </td> 
                                    <td>
                                        <input type="text" name="txtQuestionContent" value="${question.content}" />
                                    </td>
                                    <td>    
                                        <c:set var="subjectList" value="${sessionScope.LIST_SUBJECT}"/>
                                        <select name="txtSubjectId">
                                            <c:forEach var="s" items="${subjectList}">
                                                <option value="${s.id}" ${s.id == question.subjectId ? 'selected' : ''}>${s.id}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        ${question.status}
                                    </td>

                                    <c:forEach var="a" items="${answers}" varStatus="counter1">
                                        <td>
                                            <c:set var="answerOrder" value="${counter1.count}"/>
                                            <input type="hidden" name="id${answerOrder}" value="${a.id}" />
                                            Correct: <input type="radio" name="rbtnCorrect" value="${a.id}" 
                                                            <c:if test="${a.isCorrect}">checked='checked'</c:if> 
                                                                /><br>
                                                            <input type="text" name="content${answerOrder}" value="${a.content}" />
                                        </td>
                                    </c:forEach>
                                    <td>
                                        <c:if test="${question.status == true}">
                                            <input type="hidden" name="txtQuestionId" value="${question.id}" />
                                            <input type="hidden" name="txtCurrentPage" value="${currentPage}" />
                                            <input type="hidden" name="txtKeyword" value="${keyword}" />
                                            <input type="hidden" name="cboStatus" value="${status}" />
                                            <input type="hidden" name="cboSubject" value="${subject}" />
                                            <input type="submit" value="Update" name="btnAction" />
                                        </c:if>
                                        <c:if test="${question.status == false}">
                                            Update
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:url var="urlRewritting" value="DispatcherController">
                                            <c:param name="btnAction" value="Delete"/>
                                            <c:param name="txtQuestionId" value="${question.id}"/>
                                            <c:param name="txtCurrentPage" value="${currentPage}"/>
                                            <c:param name="txtKeyword" value="${keyword}"/>
                                            <c:param name="cboStatus" value="${status}"/>
                                            <c:param name="cboSubject" value="${subject}"/>
                                        </c:url>
                                        <a href="${urlRewritting}">Delete</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table><br>

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

        <c:if test="${empty sessionScope.ROLE}">
            <c:set var="subjectList" value="${sessionScope.LIST_SUBJECT}"/>
            <form action="DispatcherController">           
                Subject <select name="cboSubject">
                    <c:forEach var="s" items="${subjectList}">
                        <option value="${s.id}">${s.id}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="Take A Quiz" name="btnAction" />
            </form>
            <br>
            <hr>
            View Quiz History
            <form action="DispatcherController">
                Subject <select name="cboSubject">
                    <c:forEach var="s" items="${subjectList}">
                        <option value="${s.id}" ${param.cboSubject == s.id ? 'selected' : ''}>${s.id}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="View History" name="btnAction" />
                <input type="hidden" name="txtCurrentPage" value="1" />
            </form>


            <c:set var="noOfPages" value="${requestScope.noOfPages}"/>
            <c:set var="currentPage" value="${requestScope.currentPage}"/>
            <c:set var="history" value="${requestScope.VIEW_RESULT}"/>
            <c:set var="emptyRes" value="${requestScope.EMPTY_RESULT}"/>

            <c:if test="${not empty emptyRes}">
                ${emptyRes}
            </c:if>
            <c:if test="${not empty history}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
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
    </c:if>
</body>
</html>

