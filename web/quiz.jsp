<%-- 
    Document   : quiz
    Created on : May 28, 2020, 10:22:58 AM
    Author     : SE140355
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${not empty sessionScope}">
            <c:if test="${empty sessionScope.QUESTION_LIST}">
                <c:redirect url="mainPage.jsp"/>
            </c:if>
            <c:if test="${not empty sessionScope.QUESTION_LIST}">
                <font color="red">
                Welcome, ${sessionScope.NAME}
                </font>
                <h1>Quiz Page</h1>

                <hr>
                <c:set var="index" value="${requestScope.INDEX}"/>
                <c:set var="question" value="${requestScope.QUESTION}"/>
                <c:set var="answers" value="${requestScope.ANSWERS}"/>
                <c:set var="noOfQuestion" value="${sessionScope.NoOfQuestion}"/>
                <c:set var="time" value="${sessionScope.TIME}"/>
                <c:set var="answer_sheet" value="${sessionScope.ANSWER_SHEET}"/>
                <c:set var="id" value="${question.id}"/>
                <form action="DispatcherController">
                    <p id="timer"></p><br>

                    <input type="hidden" name="txtQuestionId" value="${question.id}" />
                    <input type="hidden" name="index" value="${index}" />
                    Question ${index}: ${question.content} <br><br>
                    <c:forEach var="a" items="${answers}">
                        <input type="radio" name="chkAnswerId" value="${a.id}"
                               <c:if test="${answer_sheet.get(question.id) == a.id}">
                                   checked="checked"
                               </c:if>
                               /> 
                        ${a.content}<br>
                    </c:forEach><br>

                    <c:if test="${index != 1}" >
                        <c:url var="urlRewritting" value="DispatcherController">
                            <c:param name="btnAction" value="Start"/>
                            <c:param name="index" value="${index - 1}"/>
                        </c:url>
                        <a href="${urlRewritting}">Previous</a>
                    </c:if>
                    ${index}
                    <c:if test="${index < noOfQuestion}">
                        <c:url var="urlRewritting" value="DispatcherController">
                            <c:param name="btnAction" value="Start"/>
                            <c:param name="index" value="${index + 1}"/>
                        </c:url>
                        <a href="${urlRewritting}">Next</a>
                    </c:if>
                    <br><br>
                    <input type="submit" id="finish" value="Finish Attempt" name="btnAction" />
                </form>

                <script>
                    var distance = 0;
                    var testTime = ${time};
                    var end = new Date().setTime(new Date().getTime() + testTime);

                    var x = setInterval(function () {

                        // Get today's date and time
                        var now = new Date().getTime();

                        // Find the distance between now and the count down date
                        distance = end - now;

                        // Time calculations for days, hours, minutes and seconds
                        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                        // Output the result in an element with id="timer"
                        document.getElementById("timer").innerHTML = hours + "h " + minutes + "m " + seconds + "s ";

                        // If the count down is over, click Finish 
                        if (distance < 0) {
                            clearInterval(x);
                            $("#finish").click();
                        }
                    });

                    $(document).ready(function () {
                        var radioVal;
                        $("input[type='radio']").click(function () {
                            radioVal = $("[name=chkAnswerId]:checked").val();
                            $.post("DispatcherController?btnAction=Start&chkAnswerId="
                                    + radioVal, {"chkAnswerId": radioVal}
                            + "&txtQuestionId=${question.id}");
                        });
                        $("a").click(function () {
                            var href = $(this).attr("href");
                            href += "&countdown=" + distance;
                            $("a").attr("href", href);
                        });
                    });
                </script>
            </c:if>
        </c:if>
    </body>
</html>
