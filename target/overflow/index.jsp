<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        window.onload =function () {
            var button= document.getElementById('submitButton') ;
            button.form.submit();
        }
    </script>
</head>
<body>
<form action="indexPage" method="get">
    <input id="submitButton" class="button"  type="submit" value="Send" style="display: none;"mvn/>
</form>
</body>
</html>