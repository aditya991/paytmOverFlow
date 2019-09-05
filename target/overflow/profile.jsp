<%--
  Created by IntelliJ IDEA.
  User: adityakumar
  Date: 2019-09-02
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page session="false" %>
<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 07/08/19
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.html" %>

<html>
<style>


    body {
        font-family: "Lato", sans-serif;
    }

    .profile {
        margin: 20px 0;
    }

    /* Profile sidebar */
    .profile-sidebar {
        padding: 20px 0 10px 0;
        background: #fff;
    }

    .profile-userpic img {
        float: none;
        margin: 0 auto;
        width: 50%;
        height: 50%;
        -webkit-border-radius: 50% !important;
        -moz-border-radius: 50% !important;
        border-radius: 50% !important;
    }

    .profile-usertitle {
        text-align: center;
        margin-top: 20px;
    }

    .profile-usertitle-name {
        color: #5a7391;
        font-size: 16px;
        font-weight: 600;
        margin-bottom: 7px;
    }

    .profile-usertitle-job {
        text-transform: uppercase;
        color: #5b9bd1;
        font-size: 12px;
        font-weight: 600;
        margin-bottom: 15px;
    }

    .profile-userbuttons {
        text-align: center;
        margin-top: 10px;
    }

    .profile-userbuttons .btn {
        text-transform: uppercase;
        font-size: 11px;
        font-weight: 600;
        padding: 6px 15px;
        margin-right: 5px;
    }

    .profile-userbuttons .btn:last-child {
        margin-right: 0px;
    }

    .profile-usermenu {
        margin-top: 30px;
    }

    .profile-usermenu ul li {
        border-bottom: 1px solid #f0f4f7;
    }

    .profile-usermenu ul li:last-child {
        border-bottom: none;
    }

    .profile-usermenu ul li a {
        color: #93a3b5;
        font-size: 14px;
        font-weight: 400;
    }

    .profile-usermenu ul li a i {
        margin-right: 8px;
        font-size: 14px;
    }

    .profile-usermenu ul li a:hover {
        background-color: #fafcfd;
        color: #5b9bd1;
    }

    .profile-usermenu ul li.active {
        border-bottom: none;
    }

    .profile-usermenu ul li.active a {
        color: #5b9bd1;
        background-color: #f6f9fb;
        border-left: 2px solid #5b9bd1;
        margin-left: -2px;
    }

    /* Profile Content */
    .profile-content {
        padding: 20px;
        background: #fff;
        min-height: 460px;
    }

    .sidenav {
        height: 99%;
        width: 350px;
        position: fixed;
        z-index: 1;
        text-align: center;
        color: #818181;
        background-color: #22d4d8;
        overflow-x: hidden;
        padding-top: 20px;
    }

    .sidenav a {
        padding: 6px 8px 6px 16px;
        text-decoration: none;
        font-size: 25px;

        color: #818181;
        display: block;
    }

    .sidenav a:hover {
        color: #111;
    }

    .main {
        margin-left: 350px; /* Same as the width of the sidenav */
        font-size: 28px; /* Increased text to enable scrolling */
        padding: 0px 10px;
    }

    @media screen and (max-height: 450px) {
        .sidenav {padding-top: 15px;}
        .sidenav a {font-size: 18px;}
    }
</style>

<head>
    <title>PaytmOverflow - User Profile</title>
</head>
<body>

<div class="sidenav" style="align-content: center ;">
    <div class="container">
    <div class="row profile" style="background-color: #1dcad8">
        <div class="col-md-3" style="background-color: #1dcad8">
            <div class="profile-sidebar">
                <!-- SIDEBAR USERPIC -->
                <div class="profile-userpic">
                    <img src="http://keenthemes.com/preview/metronic/theme/assets/admin/pages/media/profile/profile_user.jpg" class="img-responsive" alt="">
                </div>
                <!-- END SIDEBAR USERPIC -->
                <!-- SIDEBAR USER TITLE -->
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                        <%=request.getAttribute("username")%>
                    </div>
                    <div class="profile-usertitle-job">
                        Developer
                    </div>
                    <div class="profile-usertitle-job">
                        Email : <%=request.getAttribute("email")%>
                    </div>
                    <div class="profile-usertitle-job">
                        Department : <%=request.getAttribute("myDept")%>
                    </div>
                </div>
                <!-- END SIDEBAR USER TITLE -->
                <!-- SIDEBAR BUTTONS -->
<%--                <div class="profile-userbuttons">--%>
<%--                    <button type="button" class="btn btn-success btn-sm">Follow</button>--%>
<%--                    <button type="button" class="btn btn-danger btn-sm">Message</button>--%>
<%--                </div>--%>
                <!-- END SIDEBAR BUTTONS -->
                <!-- SIDEBAR MENU -->
<%--                <div class="profile-usermenu">--%>
<%--                    <ul class="nav">--%>
<%--                        <li class="active">--%>
<%--                            <a href="#">--%>
<%--                                <i class="glyphicon glyphicon-home"></i>--%>
<%--                                Overview </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="#">--%>
<%--                                <i class="glyphicon glyphicon-user"></i>--%>
<%--                                Account Settings </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="#" target="_blank">--%>
<%--                                <i class="glyphicon glyphicon-ok"></i>--%>
<%--                                Tasks </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="#">--%>
<%--                                <i class="glyphicon glyphicon-flag"></i>--%>
<%--                                Help </a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
                <!-- END MENU -->
            </div>
        </div>
    </div>
</div>
</div>

<div class="main">

    <h2>You interests are</h2>

    <%
        List listDept = (List) request.getAttribute("listofdepartments");
        List listInterest = (List) request.getAttribute("listofinterest");
        request.setAttribute("listinterest", listInterest);
        request.setAttribute("listdept", listDept);
        String S = (String) request.getAttribute("message");
        if (S == null)
            S = "";
    %>

    <c:forEach items="${listinterest}" var="s">
        <font color="blue"><c:out value="${s}"/></font><br/>
    </c:forEach>

    </br>
    </br>

    <form action="addinterest" method="post">
        Select any department to add into your interests.
        <select name="deptName">
            <c:forEach items="${listdept}" var="dept">
                <option value="${dept.dept_name}">${dept.dept_name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Add"/>
    </form>

    <br/>

    <form action="removeinterest" method="post">
        Select any department to remove from your interests.
        <select name="deptName">
            <c:forEach items="${listinterest}" var="s">
                <option value="${s}">${s}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Remove"/>
    </form>

    <br/>
    <form action="questionfeed" method="post">
        <input type="submit" value="Show my asked Questions">
    </form>
    <form action="showAllAnswer" method="post">
        <input type="submit" value="Show my given Answers">
        <input type="text" style="display:none;" name="email" value='<%= request.getAttribute("email")%>'>
    </form>
    <br/>

    <font color="#a52a2a">
        <%=S%>
    </font>

    <br/>
    <br/>

</div>
</body>
</html>
