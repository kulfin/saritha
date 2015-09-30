 
<HTML><head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>

<%@ page language="java" %>
<%@ page import="com.DBConnection" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

 <body background="images/bg6.jpg"><center><BR><BR>

<!--Declaration of varaibles-->

<%! String disluserid ; String dislpwd ; String dislnewpwd ;%>
    

<% 
   /*Retreiving user id and password*/

    disluserid = request.getParameter("uid"); 
    dislpwd = request.getParameter("pwd");
    dislnewpwd = request.getParameter("npwd");
	
	System.out.println(disluserid+dislnewpwd+dislpwd);

%>

<%

/*Declaration of variables*/

Connection con=null; Statement stmt=null;   ResultSet rs=null;

try
{
	/*Getting the connection variable from session*/

 con=DBConnection.getConnection();
	//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
	//		test","root","root"); 
	 stmt= con.createStatement(); 
String Query = "SELECT * from o_login where Userid = \'"+disluserid+"\' and Password =\'"+dislpwd+"\'";

	System.out.println(Query);

	rs = stmt.executeQuery(Query);
		

/* If user provides valid username & password then update the new password to database*/

if(rs!=null)
{
String UpdateQuery = 
"Update o_login set Password = \'"+dislnewpwd+"\' where Userid= \'"+disluserid+"\' and Password=\'"+dislpwd+"\'";
//System.out.println(UpdateQuery);
	int rowsAffected=stmt.executeUpdate(UpdateQuery);	
	
	System.out.println("Rows Affected = " + rowsAffected);

if(rowsAffected==1)
	{

%>
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">Password Updated Successfully </H3>
	<BR>
	<%
		}



/* If user provides invalid password or username*/
else{
	
%>
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">UserName/Password Invalid Please Try again </H3>
	<BR>
	<center>
		<A href="change1.jsp"> Back </A>
	</center>
<%
}
}
}
catch(Exception e)
{
	e.printStackTrace();
}

%>
</BODY>
</HTML>




