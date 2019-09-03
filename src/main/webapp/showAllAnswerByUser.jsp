<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session = "false" %>
<%@ page import="com.paytm.entity.Answer" %>
<%--
  Created by IntelliJ IDEA.
  User: adityakumar
  Date: 2019-09-02
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.html"%>
<html>
    <head>
        <title>PaytmOverflow</title>
        <style>
            .detailBox
            {
                width:800px;
                margin: 0 auto;
                border:1px solid #bbb;
                margin-top: 70px;
            }
            .titleBox {
            background-color:#5DADE2;
            padding:10px;
            }
            .titleBox label{
            color:#444;
            margin:0;
            display:inline-block;
            }

            .commentBox {
            padding:10px;
            border-top:1px dotted #666839;
            background-color:#AED6F1;
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
            max-height:500px;
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
            .actionBox
            {
                border-top:1px dotted #bbb;
                padding:10px;
            }
        </style>

    </head>
<body>

    <% List<Answer> Alist= (List<Answer>) request.getAttribute("answer");
        request.setAttribute("AnswerList", Alist);
        String viewerName = (String) request.getAttribute("viewer");
        request.setAttribute("viewerName",viewerName);
        int size=Alist.size();
    %>

    <c:set var="emptyText" value="None" />

    <c:forEach items="${AnswerList}" var = "i">
        <c:set var="emptyText" value="" />
        <div class="detailBox">

            <c:set var="name" value="${i.question.user.u_name}" />
            <c:if test="${name eq viewerName}">
                <c:set var="name" value="You" />
            </c:if>

            <div class="titleBox">
                <label>
                    Asked By : <span class="text">${name}</span>
                    <span class="sub-text"></span>
                    <span style="margin-left: 620px;" class="text">#${i.question.question_Id}</span>
                </label>
            </div>

            <div class="commentBox">
                <p class="taskDescription">${i.question.question}</p><span class="date sub-text">${i.question.created}</span>
            </div>

<%--            <div class="titleBox">--%>
<%--                <label>Your Response:</label>--%>
<%--            </div>--%>

            <div class="actionBox">
                <ul class="commentList">
                        <li>
                            <div class="commentText">
                                <p class="">${i.answer}</p>
                                <span class="date sub-text">on ${i.getCreated()}</span>
                            </div>
                        </li>
                </ul>
            </div>

            <div class="commentBox">
            <form action ="manageFeed" method = "post" id="${i.answer_id}">
                <input name="ques" type="text" value="${i.question.question}" style="display: none" />
                <p class="taskDescription">
                <a href="#" onclick="submitByTextbox(${i.answer_id})" class="card-link"><i class="fa fa-comment"></i>See Original Post</a>
                </p>
            </form>
            </div>

        </div>
        <br><br>
    </c:forEach>
    <center>
    <font color="red" size="18"><c:out value="${emptyText}" escapeXml="false" /></font>
    </center>
    <script type="text/javascript">
        function submitByTextbox(id)
        {
            document.getElementById(id).submit();
        }
    </script>
</body>
</html>
