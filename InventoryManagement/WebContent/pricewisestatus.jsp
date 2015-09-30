<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html><head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head><body background="images/bg6.jpg">
<%ResultSet rs=null;
Connection con=null;
Statement stmt=null;
con=DBConnection.getConnection();
//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
//		test","root","root"); 
 stmt= con.createStatement(); 
String str="select *from o_item order by item_price";
stmt=con.createStatement();
rs=stmt.executeQuery(str);
%>
<br><br>
<center><h2>PriceWise Item Status</h2></center><br>
<table align="center" border="2">
<tr align="center"><th >ITEM_CODE</th><th>ITEM_NAME </th><th>ITEM_PRICE</th><th>ITEM_QUANTITY</th><th>ITEM_QUALITY</th><th>CATEGORY</th>
</tr>

<%		while(rs.next()){					%>
					
<tr align="center"><td>
<%=rs.getString(1)%></td><td><%=rs.getString(2)%></td><td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td>
   <TD><%=rs.getString(5)%></td><td><%=rs.getString(7)%></td></tr>

<%   	}       %>


<table><br><br>
<center><a href="generatereports.jsp"> BACK</a>
</body>
</html>