<%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 07/08/19
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar-custom {
            background-color: #0AA9D8;
        }
        .navbar-dark {
            margin-bottom: 0;
            border-radius: 0;
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

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row" id="cardTop">
        <div class="col-sm-6">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="https://longfordpc.com/images/person-clipart-question-mark-4.jpg" style="height:300px; width:300px;" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"> Got lots of Questions? </h5>
                <form action="askQuestion" method="post">
                    <input type="submit" class="btn btn-primary" value="Ask Question">
                </form>
            </div>
        </div>
        </div>

        <div class="col-sm-6">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="https://media.wired.com/photos/5a0a38c1d07f6824ff44221b/master/w_2400,c_limit/twitterlists-TA.jpg" style="height:300px; width:300px;" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"> Show Feed </h5>
                <form action="generalfeed" method="post">
                    <input type="submit" class="btn btn-primary" value="Go to feed">
                </form>
            </div>
        </div>
        </div>
    </div>
<%--    <div class="row content">--%>
<%--        <div class="col-sm-2 sidenav">--%>
<%--            <p><a href="#">Link</a></p>--%>
<%--            <p><a href="#">Link</a></p>--%>
<%--            <p><a href="#">Link</a></p>--%>
<%--        </div>--%>
<%--        <div class="col-sm-8 text-left">--%>
<%--            <h1>Welcome to Paytm Overflow !!</h1>--%>
<%--            <hr>--%>
<%--            <h3>Test</h3>--%>
<%--            <p>Lorem ipsum...</p>--%>
<%--        </div>--%>
<%--        <div class="col-sm-2 sidenav" >--%>
<%--            <form action="askQuestion" method="post">--%>
<%--                <input type="submit"  value="Ask Question">--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>

<footer class="container-fluid text-center">
    <p>© 2018 Paytm.com. All rights reserved</p></footer>
</body>
</html>
