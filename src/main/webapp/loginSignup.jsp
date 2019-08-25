<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: navalkishore
  Date: 2019-08-12
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style type="text/css">
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
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
    </style>

    <script type="text/javascript">

        function SignupFun() {
            var x= document.getElementById("signupDiv");
            var y= document.getElementById("loginDiv");

            if (x.style.display === "none") {
                x.style.display = "block";
                y.style.display="none";
            }
        }

        function loginFun() {
            var x= document.getElementById("loginDiv");
            var y= document.getElementById("signupDiv");

            if (x.style.display === "none") {
                x.style.display = "block";
                y.style.display="none";
            }
        }

        window.onload =function () {
            var x= document.getElementById("loginDiv");
            var y= document.getElementById("signupDiv");

            x.style.display = "block";
            y.style.display="none";
        }

    </script>
</head>
<body>

<%
    List deptList =(List) request.getAttribute("deptList");
    request.setAttribute("deptList",deptList);
%>

<div id="loginDiv">
    <div class="login-form">
        <form action="login" method="post" >

            <div class="form-group">
                Email ID: <input type="text" class="form-control"placeholder="enter your email address" name="email" required/>
            </div><br/>

            <div class="form-group">
                Password: <input type="password" class="form-control" placeholder="enter your password" name="password" required/>
            </div><br/>

            <input class="btn btn-primary btn-block" type="submit" value="login" name="action"/><br/>

            <a href="forgotPassword.jsp">Forgot Password?</a>
            <label class="pull-right checkbox-inline"><button class="text-center"  id="new signup"  onclick="SignupFun()">Create Account</button></label>

        </form>
    </div>
</div>

<div id="signupDiv">
    <div class="login-form">
        <form action="signup" method="post">

            <div class="form-group">
                Name  : <input class="form-control" type="text" name="name" placeholder="Enter your name" required/>
            </div><br/>

            <div class="form-group">
                Email Id: <input class="form-control" type="text" name="email" placeholder="Enter your email" required/>
            </div><br/>

            <div class="form-group">
                Phone : <input class="form-control" type="text" name="phone" placeholder="Enter your phone number(Optional)"/>
            </div><br/>

            <div class="form-group">
                Department :
                <select class="form-control" name="dept">
                    <c:forEach items="${deptList}" var="s">
                        <option value="${s}">${s}</option>
                    </c:forEach>
                </select>
            </div><br/>

            <div class="form-group">
                Password: <input class="form-control" type="password" name="password" placeholder="********************" required/>
            </div><br/>

            <input class="btn btn-primary btn-block" type="submit" value="Sign Up"/>
            <label class="pull-right checkbox-inline"><button id="old login" onclick="loginFun()">Already a User </button></label>
        </form>
    </div>
</div>
<footer class="container-fluid text-center">
    <p>© 2019 PaytmOverFlow.com. All rights reserved</p></footer>
</body>
</html>