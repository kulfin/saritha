<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<SCRIPT LANGUAGE="JavaScript">          <!--history.go(+1);//--> 
function validate(){

 y = document.change.pwd;
 z = document.change.npwd;

 var pd=y.value;
 var ad=z.value;
 var pattern = /^([a-zA-Z0-9\_\. ]{4,8})$/;
  
   if(!(pattern.test(pd))){
	alert("Invalid password");
    return false;
	}else if(!(pattern.test(pd))){
	alert("Invalid password");
    return false;
	}
 else if(!(pattern.test(ad))){
	alert("Invalid newpassword--please provide minimum 4 characters");
	return false;
  }
 else
   return true;

}
//-->
</SCRIPT>
<body background="images/bg6.jpg">
<center>
<BR><BR><br>
<FORM NAME="change" ACTION="change.jsp" METHOD="POST" onsubmit="return validate()">
<center><h2>Enter new password</center>
<TABLE Border=0 align=center>

<TR><TD>Userid</TD>	<TD><input TYPE=text name=uid value='<%=session.getAttribute("user")%>' readonly ></TD></TR>
<TR><TD>Old Password</TD><TD><input TYPE=password name=pwd ></TD></TR>
<TR><TD>New Password </TD><TD><input TYPE=password name=npwd size="8"  maxlength="10"></TD></TR>
<TR><TD><INPUT  Type=submit name=submit value="Submit"></TD><TD><INPUT TYPE=reset name=resett value="Reset" ></TD></TR>
</TABLE>


</FORM>
</BODY>
</HTML>
