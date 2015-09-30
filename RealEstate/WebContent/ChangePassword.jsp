 <!--
    File : ChangePassword.jsp
	Purpose :This is the Change Password JSP allows users to change their password
			 
-->

<HTML>

<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>
<head>
<LINK href="styles.css" type="text/css" rel="stylesheet">
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>

<body Class=SC>
<center>

<BR><BR>
<FONT FACE="Century Gothic">

<!--Declaration of varaibles-->

<%! String errormsg ;%>
<%! String disluserid ;%>
<%! String dislpwd ;%>
<%! String dislnewpwd ;%>
    

<% 
   /*Retreiving user id and password*/

    disluserid = request.getParameter("uid");
    if(disluserid == null)
    disluserid = "";
    dislpwd = request.getParameter("pwd");
    if(dislpwd == null)
    dislpwd = "";
	dislnewpwd = request.getParameter("newpwd");
	if(dislnewpwd == null)
	dislnewpwd="";
	//System.out.println(disluserid+dislnewpwd+dislpwd);

%>

<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String Userid,Password;

try
{

	/*Getting the connection variable from session*/

	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Query = "SELECT * from login where Userid = \'"+disluserid+
		"\' and Password=\'"+dislpwd+"\'";
	//System.out.println(Query);
	rs = stmt.executeQuery(Query);
		
}
catch(Exception e)
{
	System.out.println("Exception"+e);
}
/* If user provides valid username & password then update the new password to database*/
if(rs!=null)
{
String UpdateQuery = 
"Update login set Password = \'"+dislnewpwd+"\' where Userid= \'"+disluserid+"\' and Password=\'"+dislpwd+"\'";
//System.out.println(UpdateQuery);
	int rowsAffected=stmt.executeUpdate(UpdateQuery);		
	//System.out.println("Rows Affected = " + rowsAffected);
if(rowsAffected==1)
	{%>
	
	<script>
		for(i=1;i<=10;i++) document.write("<br>");
	</script>
		<H3 align="center">Password Updated Successfully </H3>
	<BR>
	<center>
		<A href="Report.jsp"> Back </A>
	</center>
	<%}
/* If user provides invalid password or username*/
else{%>
	<script>
		for(i=1;i<=6;i++) document.write("<br>");
	</script>
		<H3 align="center">UserName/Password Invalid Please Try again </H3>
	<BR>
	<center>
		<A href="Report.jsp"> Back </A>
	</center>
<%
}
}
%>


<FONT size="2" color="blue" FACE="Century Gothic">

</BODY>
</HTML>




