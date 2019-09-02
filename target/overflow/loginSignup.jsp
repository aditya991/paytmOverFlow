<%@ page import="java.util.List" %>
<%@ page import="com.google.common.hash.Hashing" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>

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

        #loginDiv {
            margin-top: 200px;
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

    <script>
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    </script>

    <script type="text/javascript">

        function SignupFun() {
            var x = document.getElementById("signupDiv");
            var y = document.getElementById("loginDiv");


            if (x.style.display === "none") {
                x.style.display = "block";
                y.style.display = "none";
            } else {
                x.style.display = "none";
                y.style.display = "block";

            }
        }

        function loginFun() {
            var x = document.getElementById("loginDiv");
            var y = document.getElementById("signupDiv");

            if (x.style.display === "none") {
                x.style.display = "block";
                y.style.display = "none";
            } else {
                x.style.display = "none";
                y.style.display = "block";

            }
        }

        window.onload = function () {

            var x = document.getElementById("loginDiv");
            var y = document.getElementById("signupDiv");

            x.style.display = "block";
            y.style.display = "none";

        }

        function validateEmail(emailField) {
            var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

            emailField.value = emailField.value.toLowerCase();

            if (reg.test(emailField.value) === false) {
                alert('Invalid Email Address');

                emailField.value = "";
                return false;
            }
            return true;
        }

        function validatePhone(phoneField) {

            if (/^\d{10}$/.test(phoneField.value) || /^\d{0}$/.test(phoneField.value)) {
                // value is ok, use it
            } else {
                alert("Invalid number; must be ten digits");
                phoneField.value = "";
                return false;
            }
        }

        function validateName(nameField) {
            //returns true if matched, vaidates for a-z and A-Z and white space
            if (/^[A-Za-z\s]+$/.test(nameField.value)) {
                // value is ok, use it
            } else {
                alert("Invalid name");
                nameField.value = "";
                return false;
            }
        }

        function validatePasswordLogin()
        {
            var passwordField = document.getElementById("loginPassword");

            if (passwordField.value.length < 6)
            {
                passwordField.setAttribute("value","");
                alert("invalid password");
            }
            else
            {
                document.getElementById("loginForm").submit();
            }
        }

        function validatePasswordSignup()
        {

            var passwordField = document.getElementById("signupPassword");

            if (passwordField.value.length < 6)
            {
                alert("invalid password");
            }
            else
            {
                document.getElementById("signupForm").submit();
            }
        }
    </script>
</head>
<body>

<%
    List deptList = (List) request.getAttribute("deptList");
%>

<div id="loginDiv">
    <div class="login-form">
        <form id="loginForm" action="login" method="post">
            <div class="form-group">
                Email ID: <input type="text" class="form-control" onblur="validateEmail(this)"
                                 placeholder="enter your email address" name="email" required/>
            </div>

            </br>

            <div class="form-group">
                Password: <input type="password" class="form-control" placeholder="enter your password"
                                 id="loginPassword" name="password" required/>
            </div>

            </br>

            <input class="btn btn-primary btn-block" type="submit" value="login" onclick="validatePasswordLogin()"
                   name="action"/>

            <a href="forgotPassword.jsp">Forgot Password?</a>
            <label class="pull-right checkbox-inline">
                <button class="text-center" id="new signup" onclick="SignupFun()">Create Account</button>
            </label>
        </form>
    </div>
</div>

<div id="signupDiv">
    <div class="login-form">
        <form id="signupForm" action="signup" method="post">

            <div class="form-group">
                Name : <input class="form-control" type="text" name="name" onblur="validateName(this)"
                              placeholder="Enter your name" required/>
            </div>

            </br>

            <div class="form-group">
                Email Id: <input class="form-control" type="text" name="email" onblur="validateEmail(this)"
                                 placeholder="Enter your email" required/>
            </div>

            </br>

            <div class="form-group">
                Phone : <input class="form-control" type="text" name="phone" onblur="validatePhone(this)"
                               placeholder="Enter your phone number"/>
            </div>

            </br>

            <div class="form-group">
                Department :
                <select class="form-control" name="dept">
                    <c:forEach items="${deptList}" var="s">
                        <option value="${s}">${s}</option>
                    </c:forEach>
                </select>
            </div>

            </br>

            <div class="form-group">
                Password: <input class="form-control" type="password" name="password" id="signupPassword"
                                 placeholder="********************" required/>
            </div>

            </br>

            <input class="btn btn-primary btn-block" type="submit" onclick="validatePasswordSignup()" value="Sign Up"/>


            <label class="pull-right checkbox-inline">
                <button id="old login" onclick="loginFun()">Already a User</button>
            </label>
        </form>
    </div>
</div>

</body>
</html>