<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.paytm.entity.Dept" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>paytmOverFlow</title>
</head>
<body style="margin:0px;">
<table style="width:100%; border-collapse:collapse; font:14px Arial,sans-serif;">
    <tr>
        <td colspan="2" style="padding:10px 20px; background-color:#ADD8E6;">
            <h1 style="font-size:24px;">Aap apni profile dekh rahe</h1>
        </td>
    </tr>
    <%
        List listDept = (List) request.getAttribute("listofdepartments");
        List listInterest = (List) request.getAttribute("listofinterest");
        request.setAttribute("listinterest", listInterest);
        request.setAttribute("listdept",listDept);
        String S =  (String) request.getAttribute("message");
        if(S == null)
            S = "";
    %>
    <tr style="height:170px;">
        <td style="width:50%; padding:20px; background-color:#d4d7dc; vertical-align: top;">
            <ul style="list-style:none; padding:0px; line-height:24px;">


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
                <form action="answerfeed" method="post">
                    <input type="submit"  value="Show my given Answers">
                </form>
                <br/>
            </ul>
        </td>
        <td style="padding:50px; background-color:#f2f2f2; vertical-align:top;">
            <h2>You interests are</h2>



            <c:forEach items="${listinterest}" var="s">
                <font color="blue"><c:out value="${s}"/></font><br/>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="padding:5px; background-color:#acb3b9; text-align:center;">
        </td>
    </tr>
</table>
</body>
</html>