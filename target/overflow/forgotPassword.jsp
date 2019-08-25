<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 2019-08-22
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>paytmOverFlow</title>
    <link rel   = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style type = "text/css">
        .login-form {
            width: 340px;
            margin: 50px auto;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
    </style>

</head>
<body>

<%
    String S =  (String) request.getAttribute("message");
    if(S == null)
        S = "";
%>

<div id = "forgotPasswordDiv">
    <div class = "login-form">

        <form name="forgotForm" action = "forgot" method = "post" >
            <div style = "text-align: center;color: blueviolet">Forgot Password</div><br/>
            <div class = "form-group">
                <div style  = "text-align: center">
                    Enter your e-mail address registered with us where the link to reset your password can be sent
                </div><br/>
                <input type = "text" class = "form-control" placeholder = "Enter email address here" name = "email"/>
            </div>

            <br/>

            <input class = "btn btn-primary btn-block" type = "submit" value = "Send recovery link" />
            <br/><br/>
            <font color = "#a52a2a">
                <%=S%>
            </font>
        </form>

        <br/>
        <label class = "pull-right checkbox-inline"><a href = "index.jsp">Go Back</a></label>

    </div>
</div>
</body>
</html>