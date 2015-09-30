<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

<head>
 <link rel="stylesheet" style="text/css" href="cascadess.css"/>

</head><body background="images/bg6.jpg"><center><BR><BR><FONT>

<%
Connection con=null;
Statement stmt=null;
ResultSet rs=null;
try
{
	 con=DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
		 stmt= con.createStatement(); 
	String Query = "SELECT sum(bill_amount) from o_bill where bill_status=1";
	System.out.println(Query);
	rs = stmt.executeQuery(Query);
}
catch(Exception e)
{
	System.out.println("Exception"+e);
}
if(rs.next())
{
	
	
%>
                             	<script>
	                         	for(i=1;i<=3;i++) document.write("<br>");
	                            </script>
		                        <H3 align="center">Total Amount Transaction per Day<BR>Rs:&nbsp;&nbsp;&nbsp;<%=rs.getString(1)%></H3>
	                            <BR>
	                           
<%
}
%>


</BODY>
</HTML>




