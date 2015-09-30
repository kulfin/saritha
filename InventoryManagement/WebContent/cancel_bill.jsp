  <html>
  <head><link rel="stylesheet" style="text/css" href="cascadess.css"/><title>Do billing jsp</title>
<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
</head>
<body background="images/bg6.jpg">
<%
Connection con=null;
 Statement stmt1=null;
 Statement stmt2=null;
 Statement stmt3=null;
 Statement stmt4=null;
 Statement stmt5=null;
 ResultSet rs1=null;
 ResultSet rs2=null;
 
String billid=request.getParameter("bill_id");
System.out.println("billid is"+billid);
try
{
 
	


	 con=DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
		 stmt1= con.createStatement(); 

    
	//String selq1="select item_code from o_item_backup where bill_id=\'"+billid+"\'";
	
	

	
    String delq4="delete from o_bill where bill_id=\'"+billid+"\'";

	
	
	
	
		
		stmt1.executeUpdate(delq4);
		%>
	
	<H3 align="center">UR Entered Bill is Cancelled</H3>
	<BR>
	<center>
		<A href="cancel_produced_bill.jsp"> Back </A>
	</center>
<%
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}
finally
{   
	stmt1.close();
	con.close();
	
	}%>

</body>
</html>