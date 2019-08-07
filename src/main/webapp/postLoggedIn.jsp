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

<<<<<<< HEAD
    Wanna See your profile? Click on Profile button otherwise you can logout.

    <form action="Profile.jsp">
        <input type="submit"  value="Profile">
    </form>

</br>

    <form action="${pageContext.request.contextPath}/logout">
=======
    <form action="value=${pageContext.request.contextPath}/logout" method="post">
>>>>>>> 178d14c312b9d1e600973efc542f629ed302c1d3
        <input type="submit"  value="logout">
    </form>
</body>
</html>
