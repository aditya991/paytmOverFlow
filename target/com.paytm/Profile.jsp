<%@ page import="java.util.List" %><%--
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

<%= request.getAttribute("listofinterest")%>


<%
    int i = 1;
    List<String> list = (List) request.getAttribute("listofinterest");
%>

<%
    for (String d : list) {
%>

    <%=i++%></td>
    <%=d%>
<%
    }
%>





<form action="profile" method="get">
    <input type="submit" value="show my interests"/>
</form>

</body>
</html>