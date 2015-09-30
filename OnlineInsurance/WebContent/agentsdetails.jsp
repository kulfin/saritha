<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="">
  <%! Connection con;
ResultSet rs;
%>
  <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

//branchid=Integer.parseInt(request.getParameter("bid").trim());

con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select * from  agents order by agentid asc");

%>
  <p align="center"><strong><font color="#66FF66" size="5"><em>AGENTS DETAILS</em></font></strong></p>
  <p><strong><em> Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <table width="1185" height="59" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="80"><div align="center"><strong><em>AGENTID</em></strong></div></td>
      <td width="136"><div align="center"><strong><em>AGENT NAME</em></strong></div></td>
     
      <td width="34"><div align="center"><strong><em>AGE</em></strong></div></td>
      <td width="31"><div align="center"><strong><em>SEX</em></strong></div></td>
      <td width="130"><div align="center"><strong><em>QUALIFICATION</em></strong></div></td>
      <td width="107"><div align="center"><strong><em>OCCUPATION</em></strong></div></td>
      <td width="148"><div align="center"><strong><em>ADDRESS</em></strong></div></td>
      <td width="99"><div align="center"><strong><em>REGDATE</em></strong></div></td>
      <td width="84"><div align="center"><strong><em>SECURITY DEPOSIT</em></strong></div></td>
      <td width="85"><div align="center"><strong><em>MANAGER ID</em></strong></div></td>
    </tr>
  </table>
  <table width="1206" border="0" align="right" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="117" height="31"><%=rs.getInt(1)%> 
        <div align="center"></div></td>
      <td width="155"><%=rs.getString(2)%> <div align="center"></div></td>
      <td width="101"><%=rs.getString(3)%> <div align="center"></div></td>
      <td width="38"><%=rs.getString(4)%> <div align="center"></div></td>
      <td width="61"><%=rs.getString(5)%> <div align="center"></div></td>
      <td width="131"><%=rs.getString(6)%> <div align="center"></div></td>
      <td width="114"><%=rs.getString(7)%> <div align="center"></div></td>
      <td width="134"><%=rs.getDate(8)%> <div align="center"></div></td>
      <td width="96"><%=rs.getInt(9)%> <div align="center"></div></td>
      <td width="81"><%=rs.getInt(10)%></td>
      
    </tr>
    <% }%>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
