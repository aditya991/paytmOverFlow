<%--
  Created by IntelliJ IDEA.
  User: adityakumar
  Date: 07/08/19
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>paytmOverFlow</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
</head>
<body style="margin:0px;">
<table style="width:100%; border-collapse:collapse; font:14px Arial,sans-serif;">
    <tr>
        <td colspan="2" style="padding:10px 20px; background-color:#acb3b9;">
            <h1 style="font-size:24px;">Successfully logged in..</h1>
        </td>
    </tr>
    <tr style="height:170px;">
        <td style="width:50%; padding:20px; background-color:#d4d7dc; vertical-align: top;">
            <ul style="list-style:none; padding:0px; line-height:24px;">

                <form action="profile" method="post">
                    <input type="submit"  value="Profile">
                </form>

                <br>
                <form action="userFeedHome.jsp" method="post">
                    <input type="submit"  value="Feed">
                </form>

                <br>
                <form action="logout" method="post">
                    <input type="submit"  value="logout">
                </form>
            </ul>
        </td>
        <td style="padding:20px; background-color:#f2f2f2; vertical-align:top;">
            <p> Welcome <%= request.getAttribute("email")%> </p>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="padding:5px; background-color:#0000FF; text-align:center;">
        </td>
    </tr>
</table>
</body>
<script type="text/javascript">
    $(window).unload(function(){ alert('do unload stuff here'); });
</script>
</html>