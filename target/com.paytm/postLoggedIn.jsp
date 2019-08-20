<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>paytmOverFlow</title>
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
</html>