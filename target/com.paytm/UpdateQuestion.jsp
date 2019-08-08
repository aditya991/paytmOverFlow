<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>

<HTML>
<HEAD>
    <TITLE>Select element drop down box</TITLE>
</HEAD>

<BODY BGCOLOR=##f89ggh>

<%
    try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection connection =
                DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/paytmDB?user=root&password=root");

        Statement statement = connection.createStatement() ;

        resultset =statement.executeQuery("select Question from Question") ;
%>
<form action="UpdateQuesServlet" method="post">

    <center>
        <h1> Drop down box or select element</h1>
        <select name="item">
            <%  while(resultset.next()){ %>
            <option><%= resultset.getString("Question")%></option>
            <% } %>

        </>
        <br>
        <br>   <input type="submit" value="submit">
    </center>

</form>
<%
        //**Should I input the codes here?**
    }
    catch(Exception e)
    {
        System.out.println("wrong entry"+e);
    }
%>

</BODY>
</HTML>