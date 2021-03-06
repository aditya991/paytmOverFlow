<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.paytm.entity.Dept" %>
<%@ page import="com.paytm.entity.Question" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 2019-08-14
  Time: 14:13

  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.html"%>



<html>
<head>
    <title>PaytmOverFlow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        input:focus, textarea:focus, select:focus
        {
            outline: none;
        }
        .navbar-custom {
            background-color: #0AA9D8;
        }
        .navbar-dark {
            margin-bottom: 0;
            border-radius: 0;
        }
        body {
            background-color: #eeeeee;
        }

        .gedf-wrapper {
            margin-top: 0.97rem;
        }

        @media (min-width: 992px) {
            .gedf-main {
                padding-left: 4rem;
                padding-right: 4rem;
            }
            .gedf-card {
                margin-bottom: 2.77rem;
            }
        }

        /**Reset Bootstrap*/
        .dropdown-toggle::after {
            content: none;
            display: none;
        }
        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

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
            .row.content {height:auto;}
        }
        #cardTop
        {
            margin-top: 100px;
            margin-left: 250px;
        }
    </style>
</head>

<body>

<div class="container-fluid text-center">
    <div class="row">
        <div class="col-md-6 gedf-main" style="margin:0 auto;">
            <h1>You are Looking at your feed</h1>
            <%
                List listDept = (List) request.getAttribute("listdepartments");
                //request.setAttribute("listdept",listDept);
                String viewerName = (String) request.getAttribute("viewer");
                request.setAttribute("viewerName",viewerName);
                String S =  (String) request.getAttribute("message");
                if(S == null)
                    S = "";
            %>
            <%
                Iterator<Dept> iterator=listDept.iterator();
                while (iterator.hasNext())
                {
                    Dept d= (Dept) iterator.next();
//                    LOG.info(d.getDept_name());
                    List<Question> questionsList=d.getQuestions();
                    request.setAttribute("askedQuestions",questionsList);
            %>

            <c:forEach items="${askedQuestions}" var="ques">
                <form action ="manageFeed" method = "post" id="${ques.question_Id}">

                    <c:set var="name" value="${ques.user.u_name}" />
                    <c:if test="${name eq viewerName}">
                        <c:set var="name" value="you" />
                    </c:if>

                        <div class="card gedf-card">
                            <div class="card-header">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="mr-2">
                                            <img class="rounded-circle" width="45" src="https://picsum.photos/50/50" alt="">
                                        </div>
                                        <div class="ml-2">
                                            <div class="h5 m-0">
                                                    ${ques.user.email}
                                            </div>
                                            <div class="h7 text-muted">
                                                    ${name}
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div>
                                            ${ques.dept.dept_name}
                                        </div>
                                        <div>
                                            #${ques.question_Id}
                                        </div>
                                        <div class="dropdown">
                                            <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="fa fa-ellipsis-h"></i>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                                <div class="h6 dropdown-header">Configuration</div>
                                                <a class="dropdown-item" href="#">Save</a>
                                                <a class="dropdown-item" href="#">Hide</a>
                                                <a class="dropdown-item" href="#">Report</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="card-body">
                                <div class="text-muted h5 mb-2"> <i class="fa fa-clock-o"></i>Asked On: ${ques.created}</div>
                                    <p style="font-size:20px;">
                                        ${ques.question}
                                    </p>
                                    <input type="text" style="display:none;" class="cardText" name="ques" value="${ques.question}" id="selectQues" onclick="submitByTextbox(${ques.question_Id})" style="font-size:18pt;border:1px dashed blue;" readonly="true"/>
    <%--                                </p>--%>
                                </div>

                            <c:set var="zero" value="0" />
                            <c:set var="name" value="${ques.answersCount}" />
                            <c:set var="numberOfAnswers" value="${ques.answersCount} answers" />
                            <c:if test="${name eq zero}">
                                <c:set var="numberOfAnswers" value="unanswered" />
                            </c:if>


                            <div class="card-footer">
                                <a href="#" class="card-link"><i class="fa fa-gittip"></i> Like</a>
                                <a href="#" onclick="submitByTextbox(${ques.question_Id})" class="card-link"><i class="fa fa-comment"></i> Answer</a>
                                <a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Share</a>
                                <div style="text-align: right;color: blueviolet">
                                <c:out value="${numberOfAnswers}" escapeXml="false" />
                                </div>
                            </div>
                        </div>

                        <br/>
                </form>
            </c:forEach>

            <%
                }
            %>

            <br/>
            <font color="#a52a2a">
                <%=S%>
            </font>
        </div>
</div>
</div>
<footer class="container-fluid text-center">
    <p>© 2019 PaytmOverFlow.com. All rights reserved</p>
</footer>
<script type="text/javascript">
    function submitByTextbox(id)
    {
        document.getElementById(id).submit();
    }
</script>
</body>
</html>