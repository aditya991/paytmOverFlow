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
    List listInterest = (List) request.getAttribute("listofinterest");
    request.setAttribute("listinterest", listInterest);

%>

<c:forEach items="${listinterest}" var="s">
    <font color="blue"><c:out value="${s}"/></font><br/>
</c:forEach>

</br>
</br>

<form action="askUserQuestion.jsp" method="post">
    Select any department of your interests.
    <select name="Department">
        <c:forEach items="${listinterest}" var="s">
            <option value="${s}">${s}</option>
        </c:forEach>
    </select>
    <br/><br/>
    Question: <input type="text" name="Question">
    <input type="submit" value="Submit" />
</form>


</body>
</html>