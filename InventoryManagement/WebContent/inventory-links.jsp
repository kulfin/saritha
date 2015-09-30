<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%> 
 <html>
 <head><link rel="sytlesheet" style="text/css" href="cascadess.css"/></head><body background="images/bg6.jpg">
  <a href="change1.jsp" target="display">CHANGE PASSWORD</A><br><br><br>
  <a href="checkstatusinventory.jsp" target="display">CHECK STATUS OF INVENTORY</a><br><br><br>
  <a href="generatereports.jsp" target="display">GENERATE REPORTS</a><br><br><br>
  <a href="simplebar.jsp" target="display">GRAPHS FOR ITEMS</a><br><br><br>
  <A HREF="Logout.jsp" target="_top">LOGOUT</A>
  </body></html>
 