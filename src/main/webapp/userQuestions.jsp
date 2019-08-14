<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: ekanshgupta
Date: 2019-08-13
Time: 14:16
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<h1>You have asked following questions :-</h1>

<%
    List listQuestions = (List) request.getAttribute("listquestions");
    request.setAttribute("askedQuestions",listQuestions);
    String S =  (String) request.getAttribute("message");
    if(S == null)
        S = "";
%>

<br/>

<%
    boolean isChecked = true;
%>

<form action ="manageQuestion" method = "post">
        <c:forEach items="${askedQuestions}" var="ques">
            <div style="display:flex;align-items:center;">

                <input name="selectedQuestion" type="radio" value="${ques.question}" <%= (isChecked )?"checked":"" %>/>
                <textarea placeholder="${ques.question}" style="font-size:18pt;height:80px;width:300px;border:1px dashed blue;" readonly="true"></textarea>

                <!-- Using Bootstrap for popup window -->
                <!-- Trigger the modal with a button -->

                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Update</button>

                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title" style="text-align: center">Update Your Question Here</h4>
                            </div>
                            <div class="modal-body">
                                <textarea name="updatedQuestion" style="font-size:18pt;height:80px;width:850px;border:1px dashed blue;">${ques.question}</textarea>
                            </div>

                            <div style="align-items: center">
                            <input type = "submit"  name = "option" value = "Update">
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <br/>
        </c:forEach>
<br/><br/>
    <input type = "submit"  name = "option" value = "Delete">
</form>

<br/>
<font color="#a52a2a">
    <%=S%>
</font>

</body>
</html>
