<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body bgcolor="#A3A3D1">
<%

int id=Integer.parseInt(request.getParameter("bid").trim());
//con=DBConn.getConnection();
ResultSet rs=null;
Connection con=DBConn.getConnection();
Statement st=con.createStatement();

st.executeUpdate("delete from branch where branchid="+id);

	 %>
<br>

</body>
</html>
