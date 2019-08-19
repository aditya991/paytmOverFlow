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

    <script type="text/javascript">

        function SignupFun() {

            var x= document.getElementById("signupDiv");
            var y= document.getElementById("loginDiv");

            if (x.style.display === "none") {
                x.style.display = "block";
                y.style.display="none";
            } else {
                x.style.display = "none";
                y.style.display = "block";

            }
        }

        function loginFun() {

            var x= document.getElementById("loginDiv");
            var y= document.getElementById("signupDiv");

            if (x.style.display === "none") {
                x.style.display = "block";
                y.style.display="none";
            } else {
                x.style.display = "none";
                y.style.display = "block";
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

    <form action="login" method="post" >

        Email ID : <input type="text" placeholder="enter your email address" name="email" required/></br>
        Password : <input type="password" placeholder="enter your password" name="password" required/></br>
        <input type="submit" value="login" name="action"/>

    </form>

    <form action="adminLogin" method="post">

        Admin Email ID : <input type="text" placeholder="enter your email address" name="email" required/></br>
        Password       : <input type="password" placeholder="enter your password" name="password" required/></br>
        <input type="submit" value="login" />

    </form>

    <br>

    <h2> New User Signup here</h2>
    <button id="new signup" onclick="SignupFun()">New User CLick here</button>

</div>

<br><br>

<div id="signupDiv">

    <form action="signup" method="post">

        Name       : <input type="text" name="name" required/>  <br>
        Email Id   : <input type="text" name="email" required/> <br>
        Phone      : <input type="text" name="phone"/> <br>

        Department : <select name="dept">
                     <c:forEach items="${deptList}" var="s">
                         <option value="${s}">${s}</option>
                     </c:forEach>
                     </select>

        <br>

        Password   : <input type="password" name="password" required/> <br>
                     <input type="submit" value="signup"/> <br>
    </form>

    <br><br>

    <button id="old login" onclick="loginFun()">Already a User </button>

</div>
</body>
</html>