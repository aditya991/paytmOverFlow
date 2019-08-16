<%@ page import="com.paytm.entity.Answer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.paytm.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: adityakumar
  Date: 2019-08-14
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title></title>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <style>
        .detailBox {
            width:800px;
            margin: 0 auto;
            border:1px solid #bbb;
            margin-top: 150px;
        }
        .titleBox {
            background-color:#FFE933;
            padding:10px;
        }
        .titleBox label{
            color:#444;
            margin:0;
            display:inline-block;
        }

        .commentBox {
            padding:10px;
            border-top:1px dotted #bbb;
        }
        .commentBox .form-group:first-child, .actionBox .form-group:first-child {
            width:80%;
        }
        .commentBox .form-group:nth-child(2), .actionBox .form-group:nth-child(2) {
            width:18%;
        }
        .actionBox .form-group * {
            width:100%;
        }
        .taskDescription {
            margin-top:10px;
        }
        .commentList {
            padding:0;
            list-style:none;
            max-height:200px;
            overflow:auto;
        }
        .commentList li {
            margin:0;
            margin-top:10px;
        }
        .commentList li > div {
            display:table-cell;
        }
        .commenterImage {
            width:30px;
            margin-right:5px;
            height:100%;
            float:left;
        }
        .commenterImage img {
            width:100%;
            border-radius:50%;
        }
        .commentText p {
            margin:0;
        }
        .sub-text {
            color:#aaa;
            font-family:verdana;
            font-size:11px;
        }
        .actionBox {
            border-top:1px dotted #bbb;
            padding:10px;
        }
    </style>
</head>
<body>

    <div class="detailBox">
        <div class="titleBox">
            <label>Asked By: <%=request.getAttribute("questionOwnerName")%></label>
        </div>

        <div class="commentBox">

            <p class="taskDescription"> <%= request.getAttribute("ques")%> </p><span class="date sub-text">on <%= request.getAttribute("questionCreationTime")%></span>
        </div>
        <%
            List Alist= (List) request.getAttribute("Alist");
            String s=(String )request.getAttribute("ques");
            request.setAttribute("quesName",s);
        %>

        <div class="actionBox">
            <ul class="commentList">
                <c:forEach items="${Alist}" var="ans">
                    <li>
                        <div class="commentText">
                            <p class="">${ans.answer}</p> <span class="date sub-text">answered ${ans.created} <br/> By ${ans.user.u_name}</span>

                        </div>
                    </li>
                </c:forEach>
            </ul>

<%--            entering new answer--%>
            <form class="form-inline" role="form" action="giveAnswer" method="post">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Write your answer here..." name="answer" />
                    <input type="text" style="display:none" name="question" value="${quesName}"/>
                </div>
                <div class="form-group">
                    <input type="submit" value="Reply" name="action"/>
                </div>
            </form>
        </div>

    </div>
</body>
</html>