<%--
  Created by IntelliJ IDEA.
  User: adityakumar
  Date: 2019-09-02
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page session = "false" %>
<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 07/08/19
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.html"%>

<html>
<style>


    body {
        font-family: "Lato", sans-serif;
    }

    .sidenav {
        height: 99%;
        width: 350px;
        position: fixed;
        z-index: 1;
        text-align: center;
        color: #818181;
        background-color: #1dcad8;
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
    <title>User Profile</title>
</head>
<body>




<div class="sidenav" style="align-content: center ;font-: ">

    <br>
    <h1> Welcome</h1> <br>
    <h2> <%=request.getAttribute("username")%></h2>
    <br>
    <h2> <%=request.getAttribute("email")%></h2>
    <br>
    <br>
    <h1> Department :</h1>
    <br>
    <h2> <%=request.getAttribute("myDept")%></h2>
    <br>

</div>

<div class="main">

    <h2>You interests are</h2>

    <%
        List listDept = (List) request.getAttribute("listofdepartments");
        List listInterest = (List) request.getAttribute("listofinterest");
        request.setAttribute("listinterest", listInterest);
        request.setAttribute("listdept",listDept);
        String S =  (String) request.getAttribute("message");
        if(S == null)
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
        <input type="submit" value="Add" />
    </form>

    <br/>

    <form action="removeinterest" method="post">
        Select any department to remove from your interests.
        <select name="deptName">
            <c:forEach items="${listinterest}" var="s">
                <option value="${s}">${s}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Remove" />
    </form>

    <br/>
    <form action="questionfeed" method="post">
        <input type="submit"  value="Show my asked Questions">
    </form>
    <form action="showAllAnswer" method="post">
        <input type="submit"  value="Show my given Answers">
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
