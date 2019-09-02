<%@ page import="com.paytm.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 2019-08-14
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.html"%>


<html>
<head>
    <title>PaytmOverFlow</title>
</head>
<body>
<%
    Question q = (Question) request.getAttribute("ques");
    String questionName=q.getQuestion();
%>
<%= questionName %>
</body>
</html>

