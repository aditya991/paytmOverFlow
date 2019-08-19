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
    List listInterest = (List) request.getAttribute("listofDept");
    request.setAttribute("listinterest", listInterest);
    String S =  (String) request.getAttribute("message");
    if(S == null)
        S = "";
%>

<form action="saveQuestion" method="post">
    Select any department of your interests
    <select name="Department">
        <c:forEach items="${listinterest}" var="s">
            <option value="${s}">${s}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <textarea placeholder="Type your question here" name='Question' style="font-size:18pt;height:80px;width:300px;text-align:center;"></textarea>
    <br/>
    <input type="submit" value="Submit" />
</form>

<br/><br/>

<font color="#a52a2a">
    <%=S%>
</font>


</body>
</html>