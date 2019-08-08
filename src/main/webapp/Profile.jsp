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

<h1>Aap Apni Profile Dekh Rahe Hai</h1></body></br>
<h2>You are interested in</h2></br>

<%
    int i = 1;
    List<String> listInterest = (List <String>) request.getAttribute("listofinterest");
    List<Dept> listDept = (List <Dept>) request.getAttribute("listofdepartments");
    Iterator<String> iterator = listInterest.iterator();
        while (iterator.hasNext()) {
            System.out.println(i+iterator.next());
            i++;
        }
%>

<form action="/addinterest" method="get">
    Select a department to add into your interests.
    <select name="deptName">
        <c:forEach items="${deptSet}" var="dept">
            <option value="${dept.dep_name}">${dept.dept_name}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>