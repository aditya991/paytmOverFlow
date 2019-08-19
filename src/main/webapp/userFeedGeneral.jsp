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
    String userName = (String) request.getAttribute("userName");
    request.setAttribute("username",userName);
    if(S == null)
        S = "";
%>

<form action ="answer" method = "get">

    <%
        Iterator<Dept> iterator=listDept.iterator();
        while (iterator.hasNext()) {
            Dept d= (Dept) iterator.next();
            String deptName = d.getDept_name();
            String isEmpty = "";
            System.out.println(d.getDept_name());
            List<Question> questionsList=d.getQuestions();

            if(questionsList.isEmpty())
                isEmpty = "No questions has been asked in this department till now.";

            request.setAttribute("askedQuestions",questionsList);
    %>

    <br/><br/>

    <font color="blue">
    <%="       "+deptName%>
    </font>

    <br/><br/>

    <font color="#a52a2a">
    <%=isEmpty%>
    </font>

    <br/><br/>

    <c:forEach items="${askedQuestions}" var="ques">

        <br/><br/>
        <div style="display:flex;align-items:center;">
                <input name="selectedQuestion" type="radio" value="${ques.question}" />
                <textarea placeholder="${ques.question}" style="font-size:18pt;height:80px;width:300px;border:1px dashed blue;" readonly="true"></textarea>
        </div>
        <br/>

        <c:set var="name" value="${ques.user.u_name}" />

        <c:if test="${name eq username}">
            <c:set var="name" value="you" />
        </c:if>

        <font color="green">asked ${ques.updated}<br/>by  <c:out value="${name}" escapeXml="false" /></font>
        <br/><br/>

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