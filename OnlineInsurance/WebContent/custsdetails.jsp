<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="">

<%! Connection con=null;
ResultSet rs=null;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

//branchid=Integer.parseInt(request.getParameter("bid").trim());
con=DBConn.getConnection();
Statement st=null;
 st=con.createStatement();

rs=st.executeQuery("select * from  customer order by custid asc");

%>

  <p align="center"><strong><font color="#CC33CC" size="5"><em>CUSTOMERS PERSONAL 
    DETAILS</em></font></strong></p>
  <p><strong><em> Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <table width="1032" height="59" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="71"><div align="center"><strong><em>CUSTID</em></strong></div></td>
      <td width="178"><div align="center"><strong><em>CUSTOMER NAME</em></strong></div></td>
      <td width="140"><div align="center"><strong><em>FATHERS NAME</em></strong></div></td>
      <td width="55"><div align="center"><strong><em>AGE</em></strong></div></td>
      <td width="31"><div align="center"><strong><em>SEX</em></strong></div></td>
      <td width="130"><div align="center"><strong><em>QUALIFICATION</em></strong></div></td>
      <td width="132"><div align="center"><strong><em>ADDRESS</em></strong></div></td>
      <td width="107"><div align="center"><strong><em>OCCUPATION</em></strong></div></td>
      <td width="92"><div align="center"><strong><em>REGDATE</em></strong></div></td>
    </tr>
  </table>
  <table width="1031" border="0" align="center" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="103" height="33"><%=rs.getInt(1)%> <div align="center"></div></td>
      <td width="182"><%=rs.getString(2)%> <div align="center"></div></td>
      <td width="134"><%=rs.getString(3)%> <div align="center"></div></td>
      <td width="50"><%=rs.getInt(4)%> <div align="center"></div></td>
      <td width="48"><%=rs.getString(5)%> <div align="center"></div></td>
      <td width="128"><%=rs.getString(6)%> <div align="center"></div></td>
      <td width="124"><%=rs.getString(7)%> <div align="center"></div></td>
      <td width="106"><%=rs.getString(8)%> <div align="center"></div></td>
      <td width="82"><%=rs.getDate(9)%> <div align="center"></div></td>
    </tr>
    <% }%>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
