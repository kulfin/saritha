<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<frameset rows="14%,79%,*" border=0>

<frame src="inventory-welcome.jsp" >
<frameset cols="20%,*">
<frame src="inventory-links.html">
<frame src="blank.html" name="display">
</frameset>
<frame src="projectbottom.jsp">
</html>