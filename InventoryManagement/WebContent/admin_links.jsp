<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<frameset rows="13%,80%,7%" border="0">

<frame src="info.jsp" name="info" >
<frameset cols="20%,*" border="0">
<frame src="a_links.jsp" name="links">
<frame src="blank.html" name="display" >
</frameset>
<frame src="projectbottom.jsp">
</frameset>
</html>