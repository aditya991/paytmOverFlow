<%--
  Created by IntelliJ IDEA.
  User: adityakumar
  Date: 07/08/19
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome !!</title>
</head>
<body>
    Successfully logged in...
    Welcome <%= request.getAttribute("email")%> !!;

    <br/>

    <form action="logout" method="post">
        <input type="submit"  value="logout">
    </form>
</body>
</html>
