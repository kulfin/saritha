<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<%!  Connection con;
ResultSet rs;
%>

<body bgcolor="#A3A3D1">
<%
int id=Integer.parseInt(request.getParameter("bid").trim());
con=DBConn.getConnection();
Statement st=con.createStatement();

st.executeUpdate("delete from branchmgr where branchmgrid="+id);
st.executeUpdate("delete from login where userid="+id);


	 %>
</body>
</html>
