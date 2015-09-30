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

rs=st.executeQuery("select * from  custpolicies order by custid asc");

%>

  <p align="center"><strong><font color="#66FF66" size="5"><em>CUSTOMERS POLICIES 
    DETAILS</em></font></strong></p>
  <p><strong><em> Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <table width="1151" height="59" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="101"><div align="center">
          <p><strong><em>CUST</em></strong><strong><em> POLICY ID</em></strong></p>
          </div></td>
      <td width="82"><div align="center"><strong><em>CUST ID</em></strong></div></td>
      <td width="87"><div align="center"><strong><em>POLICY ID</em></strong></div></td>
      <td width="122"><div align="center"><strong><em>POLICY DATE</em></strong></div></td>
      <td width="133"><div align="center"><strong><em>PREMIUM TYPE</em></strong></div></td>
      <td width="112"><div align="center"><strong><em>PREMIUM AMOUNT</em></strong></div></td>
      <td width="112"><div align="center"><strong><em>NOMINEE</em></strong></div></td>
      <td width="87"><div align="center"><strong><em>RELATION</em></strong></div></td>
      <td width="82"><div align="center"><strong><em>AGENTID </em></strong></div></td>
      <td width="127"><div align="center"><strong><em>POLICY EXPIRY DATE</em></strong></div></td>
    </tr>
  </table>
  <table width="1150" border="0" align="left" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="117" height="33"><%=rs.getInt(1)%> <div align="center"></div></td>
      <td width="88"><%=rs.getInt(2)%> <div align="center"></div></td>
      <td width="86"><%=rs.getInt(3)%> <div align="center"></div></td>
      <td width="126"><%=rs.getDate(4)%> <div align="center"></div></td>
      <td width="142"><%=rs.getString(5)%> <div align="center"></div></td>
      <td width="117"><%=rs.getInt(6)%> <div align="center"></div></td>
      <td width="101"><%=rs.getString(7)%> <div align="center"></div></td>
      <td width="93"><%=rs.getString(8)%> <div align="center"></div></td>
      <td width="84"><%=rs.getInt(9)%> <div align="center"></div></td>
      <td width="114"><%=rs.getDate(10)%></td>
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
