
<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.Random"%><html>
<head><link rel="stylesheet" style="text/css" href="cascadess.css"></head>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>login </title>
<script language="javascript">
function isValidate()
{
if(f1.uid.value==""||f1.uid.value==null){
alert("userID field value must be provide");

f1.uid.focus() }
else if(f1.pwd.value!=f1.cpwd.value){
alert("correct password provide");
f1.pwd.focus();}
else if(f1.pwd.value==""||f1.pwd.value==null){
alert("Password field value must be provide");
f1.pwd.focus()
	}
else{
document.f1.action="Validate.jsp";
document.f1.submit();
}

}
</script>

<body background="images/bg6.jpg" >
<center><h2>Online Sales And Inventory Management System</h2></center>
<br><br>
<center><h2><img src="images/key_login.jpg" width=35 height=35>Please Provide UserName And Password</h2></center>
<br>
<form method="post" name="f1">
<table border="0" align="center">
<tr><td>UserID</td><td><input type="text" name="uid" size="10" maxlength="10" tabindex=1></td></tr>
<tr><td>Pass Word</td><td><input type="password" name="pwd" size="10" maxlength="10" tabindex=2></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="cpwd" size="10" maxlength="10" tabindex=3></td></tr>

<tr align="center"><td ><input type="submit" value="login" onclick="isValidate()" tabindex=4></td><td><input type="reset" tabindex=5></td></tr>

</table>
</form>

</body>
</html>
