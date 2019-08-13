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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
</head>
<body>
    Successfully logged in...
    Welcome <%= session.getAttribute("email")%> !!;

    <br/>

    Wanna See your profile? Click on Profile button otherwise you can logout.

    <form action="profile" method="post">
        <input type="submit"  name="action" value="Profile">
    </form>
</br>
    <form action="logout" method="post">
        <input type="submit" name="action" value="logout">
    </form>
</body>


<script type="text/javascript">
    $(window).unload(function(){ alert('do unload stuff here'); });
</script>
</html>