 <!--
    File : AddNewUser.jsp
	Purpose :To add a new user
			 
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
<%! int auth ;%>
    

<% 
   /*Retreiving user id and password*/

    disluserid = request.getParameter("uid");
    if(disluserid == null)
    disluserid = "";
    dislpwd = request.getParameter("pwd");
    if(dislpwd == null)
    dislpwd = "";
	String sauth = request.getParameter("auth");
	if(sauth == null)
	     auth=0;

		auth = Integer.parseInt(sauth);
	//System.out.println(disluserid+dislnewpwd+dislpwd);

%>

<%
int Auth = ((Integer)session.getAttribute("auth")).intValue();
if(Auth!=0){
	%><HR><H3 align=center>You are not authorized to access this page..</H3><HR><%
}
else{
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

	String Query = "SELECT * from login where Userid = \'"+disluserid+"\'";
	System.out.println(Query);
	rs = stmt.executeQuery(Query);
		
}
catch(Exception e)
{
	System.out.println("Exception"+e);
}
/* If user provides valid username & password then update the new password to database*/
if(rs.next())
{%>
	<script>
		for(i=1;i<=6;i++) document.write("<br>");
	</script>
		<H3 align="center"> User already exists</H3>
	<BR>
	<center>
		<A href="Report.jsp"> Back </A>
	</center>
<%
	
}
else{
String UpdateQuery = 
"Insert into login values(\'"+disluserid+"\',\'"+dislpwd+"\',"+auth+")";
//System.out.println(UpdateQuery);
	int rowsAffected=stmt.executeUpdate(UpdateQuery);		
	//System.out.println("Rows Affected = " + rowsAffected);
if(rowsAffected==1)
	{%>
	
	<script>
		for(i=1;i<=10;i++) document.write("<br>");
	</script>
		<H3 align="center">User Created Successfully </H3>
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
		<H3 align="center">Unable to create user please try again </H3>
	<BR>
	<center>
		<A href="Report.jsp"> Back </A>
	</center>
<%
}
}
}
%>


<FONT size="2" color="blue" FACE="Century Gothic">

</BODY>
</HTML>




