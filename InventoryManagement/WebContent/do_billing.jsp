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
<%!double bill_amt;%>
<%! double price;%>
<%
Connection con=null;
 Statement stmt=null;
 
 ResultSet rs=null;

String billid=request.getParameter("bill_id");
String[] items=request.getParameterValues("item_code");

session.setAttribute("billid",billid);
session.setAttribute("items",items);
 try
{

   int j=0;
	
	

   con=DBConnection.getConnection();
	 
	 stmt= con.createStatement(); 
	

	
	String Query = "insert into o_bill values(\'"+billid+"\',"+0+","+1+",'2015-03-03')";
	
	
         
		stmt.executeUpdate(Query);
		
		
		
	
	
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
<a href="Validate.jsp">go back here</a>
</body>
</html>