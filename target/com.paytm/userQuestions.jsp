<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 2019-08-13
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
</head>
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



<form action ="" method = "post">
    <input type = "submit"  name = "Update" value = "Update">
    <input type = "submit"  name = "Delete" value = "Delete">
</form>
<br/>

<font color="#a52a2a">
    <%=S%>
</font>

</body>
</html>
