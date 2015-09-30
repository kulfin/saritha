<html>
<body>

<% String uname = (String)session.getAttribute("uname");%>

<table align="center">
<tr><td>
  <img border="0" src="images/strategyconsulting_main.jpg" width="425" height="349"></td></tr>
  <tr><td><b><font color="red" size="5">Welcome Mr./Miss :</font></b><font color="blue" size="4"><center><%out.println(uname);%></center></font></td></tr>
</body>
</html>