<%@ page  import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<frameset rows="107,*" cols="*" framespacing="3" frameborder="yes" border="3" bordercolor="#9966CC">
  <frame src="custhead.jsp" name="topFrame" scrolling="NO" noresize >
  <frameset rows="*" cols="319,*" framespacing="3" frameborder="yes" border="3" bordercolor="#CC33CC">
    <frame src="custleft.jsp" name="leftFrame" scrolling="NO" noresize>
    <frame src="custwork.jsp" name="mainFrame">
  </frameset>
</frameset>
<noframes><body>

<% 
String id=request.getParameter("uid").trim();
System.out.println(" useridddddd  :::  "+id);
session.setAttribute("UID",id);


%>
</body></noframes>
</html>
