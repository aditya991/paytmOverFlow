<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<%
    List listofQuestion = (List) request.getAttribute("listofQuestion");
    request.setAttribute("listofQuestion", listofQuestion);

%>


<form action="UpdateServlet" method="GET">
    Select Question<br>
    <select name="question">
        <c:forEach items="${listofQuestion}" var="ques">
            <option value="${ques.question}">${ques.question}</option>
        </c:forEach>
    </select>
    <br/><br/>

    New Question:<input type="text" name="UpdateQuestion">
    <br>
    <input type="submit" value="Update" />
</form>

</body>
</html>