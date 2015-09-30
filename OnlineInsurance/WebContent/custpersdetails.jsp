<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="">
  <p align="left"> 
    <%! Connection con;
ResultSet rs;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

String obj=(String)session.getAttribute("UID");
int uid=Integer.parseInt(obj);
con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select * from  customer where custid="+uid);

%>
  </p>
  <p align="center"><em><strong><font color="#66FF66" size="5">CUSTOMER PERSONAL 
    </font></strong></em> <font color="#66FF66"><strong><font size="5"><em> DETAILS</em></font></strong></font></p>
  <p align="left"><font color="#66FF66"><strong><em>Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
    </font> </p>
  <table width="1140" height="59" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="72"><div align="center"><strong><em>CUSTID</em></strong></div></td>
      <td width="168"><div align="center"><strong><em>CUSTOMER NAME</em></strong></div></td>
      <td width="158"><div align="center"><em><strong>FATHER NAME</strong></em></div></td>
      <td width="34"><div align="center"><strong><em>AGE</em></strong></div></td>
      <td width="31"><div align="center"><strong><em>SEX</em></strong></div></td>
      <td width="130"><div align="center"><strong><em>QUALIFICATION</em></strong></div></td>
      <td width="221"><div align="center"><strong><em>ADDRESS</em></strong></div></td>
      <td width="107"><div align="center"><strong><em>OCCUPATION</em></strong></div></td>
      <td width="123"><div align="center"><strong><em>REG DATE</em></strong></div></td>
    </tr>
  </table>
  <table width="1131" border="0" align="center" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="84" height="33"><%=rs.getInt(1)%> <div align="center"></div></td>
      <td width="182"><%=rs.getString(2)%> <div align="center"></div></td>
      <td width="150"><%=rs.getString(3)%> <div align="center"></div></td>
      <td width="35"><%=rs.getInt(4)%> <div align="center"></div></td>
      <td width="52"><%=rs.getString(5)%> <div align="center"></div></td>
      <td width="147"><%=rs.getString(6)%> <div align="center"></div></td>
      <td width="199"><%=rs.getString(7)%> <div align="center"></div></td>
      <td width="113"><%=rs.getString(8)%> <div align="center"></div></td>
      <td width="95"><%=rs.getDate(9)%> <div align="center"></div></td>
    </tr>
    <% }%>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
