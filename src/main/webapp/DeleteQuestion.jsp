<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.paytm.entity.Dept" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 07/08/19
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
</head>
<body>

<%
    List listofQuestion = (List) request.getAttribute("listofQuestion");
    request.setAttribute("listofQuestion", listofQuestion);

%>

</br>
</br>

<form action="DeleteServlet" method="GET">
    Select Question <br>
    <select name="question">
        <c:forEach items="${listofQuestion}" var="ques">
            <option value="${ques.question}">${ques.question}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Delete"/>
</form>

</body>
</html>