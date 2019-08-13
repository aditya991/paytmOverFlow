<html>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<body>
<form action="login" method="post">
    Email ID: <input id="one" type="text" placeholder="enter your email address" name="email" required/>
    </br>
    Password: <input id="two" type="password" placeholder="enter your password" name="password" required/>
    </br>
    <input id="primaryButton" type="submit" name="action" value="login"/>
</form>
<form action="login" method="post" >
    <input id="three" type="text" placeholder="enter your email address" name="email" style="display:none" required/>
    </br>
    <input id="four" type="password" placeholder="enter your password" name="password" style="display:none" required/>
    </br>
    <input type="button"
           id="secondaryButton" style="display:none"
           onclick="document.getElementById('primaryButton').click()" />
</form>
<script>
    var one = document.getElementById("one");
    var two = document.getElementById("two");
    var three = document.getElementById("three");
    var four = document.getElementById("four");

    //Get the value of textOne textbox input
    var textOne = one.value;
    var texttwo= two.value;

    three.value=textOne;
    four.value=texttwo;

</script>
<br>
<br>
<br>

<h2> New User Signup here</h2>

<br>
<br>

<form action="signup" method="post">

    Name  : <input type="text" name="name" required/>  <br>
    Email Id: <input type="text" name="email" required/> <br>
    Phone : <input type="text" name="phone"/> <br>
    Department: <input type="text" name="dept" required/> <br>
    Password: <input type="password" name="password" required/> <br>

    <input type="submit"  name="action" value="signup"/> <br>
</form>

</body>
</html>