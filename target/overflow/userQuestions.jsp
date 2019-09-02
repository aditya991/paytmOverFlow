<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: ekanshgupta
Date: 2019-08-13
Time: 14:16
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.html"%>


<html>
<head>
    <title>PaytmOverFlow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<style>
    @media screen and (max-width: 767px)
    {
        .sidenav {
            height: auto;
            padding: 15px;
        }
        .row.content {height:auto;}
    }
    footer
    {
        background-color: #0AA9D8;
        color: white;
        padding: 15px;
    }
    #formTop
    {
        margin: 0 auto;
    }
</style>

<body>
<h1>You have asked following questions :-</h1>

    <%
        List listQuestions = (List) request.getAttribute("listquestions");
        request.setAttribute("askedQuestions",listQuestions);
        String S =  (String) request.getAttribute("message");
        if(S == null)
            S = "";
    %>
    <br/>

    <c:set var="emptyText" value="None" />

    <div class="container-fluid text-center">
        <div class="card" >
            <div class="card-body" id="formTop">
                <form action ="manageQuestion" method = "post">
                    <c:forEach items="${askedQuestions}" var="ques">
                        <c:set var="emptyText" value="" />
                        <div style="text-align: center;align-items:center;">
                            <input name="selectedQuestion" type="radio" value="${ques.question}" />
                            <input placeholder="${ques.question}" style="font-size:18pt;height:80px;width:500px;text-align:center;" readonly="true" />
                        </div>
                        <br/>
                     </c:forEach>
                    <font color="red" size="18"><c:out value="${emptyText}" escapeXml="false" /></font>
                    <br/><br/>
                    <input type = "submit"  name = "option" value = "Delete">
                </form>
            </div>
        </div>
        <br/>
        <font color="#a52a2a">
            <%=S%>
        </font>
    </div>
    <footer class="container-fluid text-center">
        <p>© 2019 PaytmOverFlow.com. All rights reserved</p>
    </footer>
</body>
</html>
