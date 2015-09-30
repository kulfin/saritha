<%@ page  import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<frameset rows="209,*" cols="*" framespacing="3" frameborder="yes" border="16" bordercolor="#6633CC">
  <frame src="agenthead.htm" name="topFrame" scrolling="NO" noresize >
  <frameset rows="*" cols="216,*" framespacing="6" frameborder="yes" border="3" bordercolor="#006666">
    <frame src="agentleft.htm" name="leftFrame" scrolling="NO" noresize>
    <frame src="agentwork.jsp" name="mainFrame">
  </frameset>
</frameset>
<noframes><body>

<%
String id=request.getParameter("aid").trim();
System.out.println(" agentidddddd  :::  "+id);
session.setAttribute("AID",id);

%>

</body></noframes>
</html>
