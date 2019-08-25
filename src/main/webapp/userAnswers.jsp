<%--
Created by IntelliJ IDEA.
User: ekanshgupta
Date: 2019-08-13
Time: 14:16
To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<h1>You have asked following answers :-</h1>

<%
    List listAnswers = (List) request.getAttribute("listanswers");
    request.setAttribute("givenAnswers",listAnswers);
    String S =  (String) request.getAttribute("message");
    if(S == null)
        S = "";
%>

<br/>

<form action ="manageAnswer" method = "post">
    <c:forEach items="${givenAnswers}" var="ans">
        <div style="display:flex;align-items:center;">
            <input name="selectedAnswer" type="radio" value="${ans.answer}" />
                    <textarea placeholder="${ans.answer}" style="font-size:18pt;height:80px;width:300px;border:1px dashed blue;" readonly="true"></textarea>
        </div>
        <br/>
    </c:forEach>
    <br/><br/>
    <input type = "submit" name = "option" value = "Delete">
</form>

<br/>
<font color="#a52a2a">
    <%=S%>
</font>
<footer class="container-fluid text-center">
    <p>© 2019 PaytmOverFlow.com. All rights reserved</p></footer>
</body>
</html>