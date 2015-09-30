<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<%

Connection con=null; Statement stmt=null;   ResultSet rs=null;
con=DBConnection.getConnection();
//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
//		test","root","root"); 
 stmt= con.createStatement(); 
String Query = "select category_code from o_item_category ";
System.out.println(Query);
rs = stmt.executeQuery(Query);
%>
<html>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/>
<title>change price of item</title>
</head>
<body background="images/bg6.jpg"><center>  <h2>Change Price of Item</h2><br>
<form name="f1" action="changeprice.jsp" method="post">
<table border="0" >
<tr><td>Category</td><td><select name="cat" >
<option default>none </option>
<%
		while(rs.next()){
%>
`	<option value='<%=rs.getString(1)%>'><%=rs.getString(1)%></option>
                                            
	<% } %></select></td></tr>
	<%
String Query1 = "select item_code from o_item ";
System.out.println(Query1);
rs = stmt.executeQuery(Query1);%>

<tr><td>Item Code </td><td><select name="item"><option default>none </option>

<%
		while(rs.next()){
%>
`		<option value='<%=rs.getString(1)%>'><%=rs.getString(1)%></option>
                                            
	<% } %></select></td></tr>  
<tr><td>Enter New Price</td><td><input type="text" name="price" size=5></td></tr>
<tr><td><input type="submit" value="update"></td><td><input type="reset" value="clear" ></td></tr>
</table>

</center>
</body>
</html>