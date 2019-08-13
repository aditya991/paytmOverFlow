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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
</head>
<body>

<h1>Aap Apni Profile Dekh Rahe Hai</h1></br>
<h2>Hello <%=request.getAttribute("username")%></h2>
<h2>You interests are</h2>

<%
    List listDept = (List) request.getAttribute("listofdepartments");
    List listInterest = (List) request.getAttribute("listofinterest");
    request.setAttribute("listinterest", listInterest);
    request.setAttribute("listdept",listDept);
%>

<c:forEach items="${listinterest}" var="s">
    <font color="blue"><c:out value="${s}"/></font><br/>
</c:forEach>

</br>
</br>

<form action="addinterest" method="get">
    Select any department to add into your interests.
    <select name="deptName">
        <c:forEach items="${listdept}" var="dept">
            <option value="${dept.dept_name}">${dept.dept_name}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" name="action" value="Add" />
</form>

<br/>

<form action="removeinterest" method="get">
    Select any department to remove from your interests.
    <select name="deptName">
        <c:forEach items="${listinterest}" var="s">
            <option value="${s}">${s}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" name="action" value="Remove" />
</form>
<br/>
<font color="#a52a2a"><%=request.getAttribute("message")%></font>

<form action="questionfeed" method="post">
    <input type="submit" name="action" value="Show my Questions">
</form>

<br/>
<br/>

<form action="answerfeed" method="post">
    <input type="submit"  name="action" value="Show my Answers">
</form>

<form action="logout" method="post">
    <input type="submit"  name="action" value="Log Out">
</form>
<form action="back" method="post">
    <input type="button"  onclick="window.location.href='http://localhost:8082/paytmOverFlow_war_exploded/postLoggedIn.jsp'" value="Go Back">
</form>
</body>
<script type="text/javascript">
    // $(document).ready(function() {
    //     function disableBack() { window.history.forward() }
    //
    //     window.onload = disableBack();
    //     window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
    // });
</script>
</html>