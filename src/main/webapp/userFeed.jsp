<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 07/08/19
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
</head>
<body>
<h1>PaytmOverFlow mein Aapka Swagat hai</h1>

<h2>Do you wanna answer or question?</h2>
<%
List listInterest = (List) request.getAttribute("listofinterest");
request.setAttribute("listinterest", listInterest);
%>

<form action="askQuestionfeed" method="post">

    <select name="deptName">
        <c:forEach items="${listinterest}" var="s">
            <option value="${s}">${s}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit"  value="Ask Question" />
</form>

</body>
</html>