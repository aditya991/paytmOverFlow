<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 07/08/19
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="header.html" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style>
    .navbar-custom {
        background-color: #0AA9D8;
    }

    .navbar-dark {
        margin-bottom: 0;
        border-radius: 0;
    }

    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {
        height: 450px
    }

    /* Set gray background color and 100% height */
    .sidenav {
        padding-top: 20px;
        background-color: #5DD5F9;
        height: 100%;
    }

    /* Set black background color, white text and some padding */
    footer {
        background-color: #0AA9D8;
        color: white;
        padding: 15px;
    }

    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
        .sidenav {
            height: auto;
            padding: 15px;
        }

        .row.content {
            height: auto;
        }
    }

    #formTop {
        margin-top: 40px;
    }
</style>
<body>

<%
    List listInterest = (List) request.getAttribute("listofDept");
    request.setAttribute("listinterest", listInterest);
    String S = (String) request.getAttribute("message");
    if (S == null)
        S = "";
%>

<div class="container-fluid text-center">
    <div class="card">
        <div class="card-body" id="formTop">
            <form action="saveQuestion" method="post">
                Select any department of your interests
                <select name="Department">
                    <c:forEach items="${listinterest}" var="s">
                        <option value="${s}">${s}</option>
                    </c:forEach>
                </select>

                <br/><br/>

                <input type="text" placeholder="Type your question here" name='Question'
                       style="font-size:18pt;height:80px;width:500px;text-align:center;" required></input>
                <br/>
                <input type="submit" class="btn btn-primary" value="Submit" style="margin-top:10px;"/>
            </form>
        </div>
    </div>
    <br/><br/>
    <font color="#a52a2a">
        <%=S%>
    </font>
</div>
<footer class="container-fluid text-center">
    <p>© 2018 Paytm.com. All rights reserved</p>
</footer>
</body>
</html>