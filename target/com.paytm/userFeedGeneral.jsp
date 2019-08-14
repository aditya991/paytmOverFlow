<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.paytm.entity.Dept" %>
<%@ page import="com.paytm.entity.Question" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: ekanshgupta
  Date: 2019-08-14
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PaytmOverFlow</title>
</head>
<body>
<h1>You are Looking at your feed</h1>
<%
    List listDept = (List) request.getAttribute("listdepartments");
    //request.setAttribute("listdept",listDept);
    String S =  (String) request.getAttribute("message");
    if(S == null)
        S = "";
%>

<form action ="manageFeed" method = "post">
    <%
        Iterator<Dept> iterator=listDept.iterator();
        while (iterator.hasNext()) {
            Dept d= (Dept) iterator.next();
            System.out.println(d.getDept_name());
            List<Question> questionsList=d.getQuestions();
            request.setAttribute("askedQuestions",questionsList);
    %>

    <c:forEach items="${askedQuestions}" var="ques">

        <div style="display:flex;align-items:center;">
                <input name="selectedQuestion" type="radio" value="${ques.question}" />
                <textarea placeholder="${ques.question}" style="font-size:18pt;height:80px;width:300px;border:1px dashed blue;" readonly="true"></textarea>
        </div>

        <br/>

    </c:forEach>

    <br/><br/>

    <%
        }
    %>

    <input type = "submit"  name = "option" value = "Select">

</form>

<br/>
<font color="#a52a2a">
    <%=S%>
</font>

</body>
</html>