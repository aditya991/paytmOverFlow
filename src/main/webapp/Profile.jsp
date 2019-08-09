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

<h1>Aap Apni Profile Dekh Rahe Hai</h1></br>
<h2>You interests are</h2>

<%
    List listDept = (List) request.getAttribute("listofdepartments");
    List listInterest = (List) request.getAttribute("listofinterest");
    request.setAttribute("listinterest", listInterest);
    request.setAttribute("listdept",listDept);
%>

<c:forEach items="${listinterest}" var="s">
    <c:out value="${s}"/>
</c:forEach>

</br>
</br>

<form action="/addinterest" method="get">
    Select any department to add into your interests.
    <select name="deptName">
        <c:forEach items="${listdept}" var="dept">
            <option value="${dept.dept_name}">${dept.dept_name}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Add" />
</form>

<br/>

<form action="/addinterest" method="get">
    Select any department to remove from your interests.
    <select name="deptName">
        <c:forEach items="${listinterest}" var="s">
            <option value="${s}">${s}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Remove" />
</form>
</body>
</html>