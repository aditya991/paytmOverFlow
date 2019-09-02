<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 2019-08-24
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>paytmOverFlow</title>

    <link rel   = "stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

    <script type = "text/javascript">

        function showAlert(){
            if(document.resetForm.updatedPassword.value === document.resetForm.confirmedPassword.value){
                alert("Password reset successfully!");
            }
        }

        function change1(){

            var elem = document.getElementById("button1");

            if (elem.value === "Show"){
                elem.value = "Hide";
            } else {
                elem.value = "Show";
            }

            var x = document.getElementById("pswd1");

            if(x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }

        function change2(){

            var elem = document.getElementById("button2");

            if (elem.value === "Show"){
                elem.value = "Hide";
            } else {
                elem.value = "Show";
            }

            var x = document.getElementById("pswd2");

            if(x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
    </script>

</head>
<body>

<%
    String token =  (String) request.getAttribute("token");
    request.setAttribute("resetToken",token);
    String S =  (String) request.getAttribute("message");
    if(S == null)
        S = "";
%>

<div id = "resetPasswordDiv">
    <div class = "login-form">

        <form name = "resetForm" action = "reset" method = "post" >

            <!--<div class="form-group">-->

            <div style="text-align: center;color: blueviolet">Reset password</div><br/>

            <input type = "password" id = "pswd1"   style = "width: 80%" placeholder = "Enter your new password here" name = "updatedPassword"/>
            <input type = "button"   id = "button1" value = "Show" onclick = "change1()"><br/><br/>

            <input type = "password" id = "pswd2"   style = "width: 80%" placeholder = "Confirm new password here" name = "confirmedPassword"/>
            <input type = "button"   id = "button2" value = "Show" onclick = "change2()"><br/><br/>

            <!--
                <div class="input-group mb-3">
                    <input type="password" class="form-control" id = "pswd1" name = "updatedPassword" placeholder="Enter your new password here" aria-label="Enter your new password here" aria-describedby="button1">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button1" onclick = "change1()" >Show</button>
                    </div>
                </div>

                <br/>

                <div class="input-group mb-3">
                    <input type="password" class="form-control" id = "pswd2" name = "confirmedPassword" placeholder="Confirm new password here" aria-label="Confirm new password here" aria-describedby="button2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button2" onclick = "change2()">Show</button>
                    </div>
                </div>
            -->

            <input type = "text" name = "token" value = "${resetToken}" style = "display: none"/>

            <!-- </div>-->

            <br/>

            <input class = "btn btn-primary btn-block" type = "submit" value = "Update" onclick = "showAlert()"/>
            <br/><br/>
            <font color = "#a52a2a">
                <%=S%>
            </font>
        </form>

    </div>
</div>
<footer class="container-fluid text-center">
    <p>© 2019 PaytmOverFlow.com. All rights reserved</p></footer>
</body>
</html>