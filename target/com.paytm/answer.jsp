<%--
  Created by IntelliJ IDEA.
  User: navalkishore
  Date: 9/8/19
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
<form action="qwertyui">
    <button type="submit" id="Add">Click to add textbox</button>
<div id="textboxDiv"></div>
<script>
    $(document).ready(
        function() {
        $("#Add").on("click", function() {
            $("#textboxDiv").append("<div><br><input type='text'/><br></div>");
        });

        //
        //
        // $("#Remove").on("click", function() {
        //     $("#textboxDiv").children().last().remove();
        // });
    });
</script>
</form>
</body>

</html>