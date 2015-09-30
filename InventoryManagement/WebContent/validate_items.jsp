<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/><title>change price of item</title>
</head>
<body background="images/bg6.jpg"><center>  <h2>Change Quantity of Item</h2>
<%! String iid;%>
<%
iid=request.getParameter("info");
	Connection con=null;
	ResultSet rs=null;
	Statement stmt=null;
		
	 con=DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
		 stmt= con.createStatement(); 
			String Query = "Select * from o_item where item_code='"+iid+"'";
			rs = stmt.executeQuery(Query);
			if(rs.next())
		{
%>
<form name="f1" action="updateitems_qua.jsp" method="post">
<table border="0" >
<tr><td>Item Code</td><td><input type="text" name="iid" value='<%=rs.getString(1)%>' readonly>
<tr><td>Item Name</td><td><input type="text" name="iname" value='<%=rs.getString(2)%>' readonly>
<tr><td>Item Quantity </td><td><input TYPE="TEXT" name="iq" value='<%=rs.getString(4)%>' readonly></td></tr>
<tr><td>Add Item Quantity </td><td><input TYPE="TEXT" name="iq1"></td></tr>
<tr><td><input type="submit" value="Add Quantity"></td><td><input type="reset" value="clear" ></td></tr>
</table> 
<%}
%>
</center>
</body>
</html>