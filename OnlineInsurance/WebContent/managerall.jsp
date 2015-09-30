<%@ page  import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<frameset rows="120,*" cols="*" framespacing="3" frameborder="yes" border="3" bordercolor="#FF00FF">
  <frame src="managerhead.htm" name="topFrame" scrolling="NO" noresize >
  <frameset rows="*" cols="270,*" framespacing="3" frameborder="yes" border="3" bordercolor="#9933CC">
    <frame src="managerleft.htm" name="leftFrame" scrolling="NO" noresize>
    <frame src="managerwork.jsp" name="mainFrame">
  </frameset>
</frameset>
<noframes>

<body bgcolor="#A3A3D1">

<%
String id=request.getParameter("mid").trim();
System.out.println(" mgridddddd  :::  "+id);
session.setAttribute("MGRID",id);

%>

</body></noframes>
</html>
