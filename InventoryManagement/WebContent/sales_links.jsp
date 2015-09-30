<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<head><link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<body  background="images/bg6.jpg"><br><br><br>
<a href="change1.jsp" target="display">Change Password</a><br><br>
<a href="status_inventory.html" target="display">Status_Inventory</A><br><br>
<a href="search_item.html" target="display">Searching_item</a><br><br>
<!--<a href="contact_to_inventory.jsp" target="display">Contact_to_inventory</A><br><br>-->
<a href="items_details.jsp" target="display">produce_bill</A><br><br>
<a href="cancel_produced_bill.jsp" target="display">Cancel_produced_bill</A><br><br>
<a href="Logout.jsp" target="_top">logout</a><br><br>