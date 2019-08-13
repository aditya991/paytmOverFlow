<%--
  Created by IntelliJ IDEA.
  User: renu
  Date: 06/08/19
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action ="AddServlet" method="post">
    Add Question
    <br>
    <input type="submit" name="submit">
    <br>
</form>
</form>
<form action="DeleteQuesServlet" method="post">

    Delete Question
    <br>
    <input type="submit" name="submit">
    <br>
</form>
<form action="UpdateQuesServlet" method="post">
    Update Question<br>
    <input type="submit" name="submit">
    <br>
</form>
</html>