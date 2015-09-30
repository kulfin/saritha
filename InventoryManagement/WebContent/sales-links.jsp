<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<head>
<title>Welcome to SALES PERSON</title>
</head>
<body background="images/bg6.jpg"><center><br><br><br><br>
<H2>WELCOME TO SALES PERSON</H2><table border=0 >
<tr><a href="change.html" >Change Password</a><br><br><br>
<tr><a href="status_inventory.jsp">Status_Inventory</A><br><br><br>
<tr><a href="search_item.jsp">Searching_item</a><br><br><br>
<tr><a href="contact_to_inventory.jsp">Contact_to_inventory</A><br><br><br>
<tr><a href="produce_bill.jsp">produce_bill</A><br><br><br>
<tr><a href="cancel_produced_bill.jsp">Cancel_produced_bill</A><br><br><br>
<tr><a href="error_produce_bill.jsp">error_produce_bill</A><br><br><br>
<tr><a href="logout.jsp">logout</a><br><br><br><br>
</table></center>

</body>
</html>