<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body  bgcolor="#A3A3D1"> 
<form name="form1" method="post" action="">

<%! Connection con;
ResultSet rs;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

//branchid=Integer.parseInt(request.getParameter("bid").trim());
Statement st=null;

con=DBConn.getConnection();
st=con.createStatement();
rs=st.executeQuery("select * from  policies order by policyid asc");

%>

  <p align="center"><strong><font color="#CC33CC" size="5"><em>POLICIES DETAILS</em></font></strong></p>
  <p><strong><em> Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <table width="1122" height="59" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="77"><div align="center"><strong><em>POLICYID</em></strong></div></td>
      <td width="221"><div align="center"><strong><em>POLICY NAME</em></strong></div></td>
      <td width="68"><div align="center"><strong><em>POLICY TERM</em></strong></div></td>
      <td width="155"><div align="center"><strong><em>AMOUNT</em></strong></div></td>
      <td width="156"><div align="center"><strong><em>FACE AMOUNT</em></strong></div></td>
      <td width="80"><div align="center"><strong><em>INTEREST</em></strong></div></td>
      <td width="144"><div align="center"><strong><em>REG DATE</em></strong></div></td>
      <td width="119"><div align="center"><strong><em>BONUS PERIOD</em></strong></div></td>
      <td width="129"><div align="center"><strong><em>BONUS RATE</em></strong></div></td>
    </tr>
  </table>
  <table width="1117" border="0" align="center" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="90" height="33"><%=rs.getInt(1)%> <div align="center"></div></td>
      <td width="229"><%=rs.getString(2)%> <div align="center"></div></td>
      <td width="70"><%=rs.getInt(3)%> <div align="center"></div></td>
      <td width="162"><%=rs.getInt(4)%> <div align="center"></div></td>
      <td width="158"><%=rs.getInt(5)%> <div align="center"></div></td>
      <td width="87"><%=rs.getInt(6)%> <div align="center"></div></td>
      <td width="150"><%=rs.getDate(7)%> <div align="center"></div></td>
      <td width="111"><%=rs.getInt(8)%> <div align="center"></div></td>
      <td width="91"><%=rs.getInt(9)%> <div align="center"></div></td>
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
