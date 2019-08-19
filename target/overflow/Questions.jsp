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
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script>
        $(document).ready(function() {
            $(function() {
                console.log('false');
                $( "#dialog" ).dialog({
                    autoOpen: false,
                    title: 'Test'
                });
            });

            $("button").click(function(){
                console.log("click");
                $(this).hide();
                $( "#dialog" ).dialog('open');
            });
        });
    </script>
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

<form action ="manageQuestion" method = "post">
    <c:forEach items="${askedQuestions}" var="ques">
        <div style="display:flex;align-items:center;">

            <input name="selectedQuestion" type="radio" value="${ques.question}" />
            <textarea placeholder="${ques.question}" style="font-size:18pt;height:80px;width:300px;border:1px dashed blue;" readonly="true"></textarea>
            <button id="open">Update</button>
            <div id="dialog" title="Basic dialog">

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