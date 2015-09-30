<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1"> 
<form name="form1" method="post" action="">
  <p> 
    <%! Connection con;
ResultSet rs;
int pyid;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

pyid=Integer.parseInt(request.getParameter("pid").trim());

String obj=(String)session.getAttribute("UID");
int uid=Integer.parseInt(obj);

Statement st=null;

con=DBConn.getConnection();
st=con.createStatement();
rs=st.executeQuery("select * from custpolicies where custid="+uid +" and policyid=" + pyid);


%>
  </p>
  <p align="left"><strong><em><font color="#66FF66">Date:</font></em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p align="center"><strong><font color="#66FF66" size="5"><em> CUSTOMER POLICY 
    DETAILS</em></font></strong> </p>
  <table width="982" height="54" border="2" align="left" cellpadding="3">
    <tr> 
      <td width="83" height="46">
<div align="center"><strong><em>CUST POLICY ID</em></strong></div></td>
      <td width="106"><div align="center"><strong><em>POLICY DATE</em></strong></div></td>
      <td width="145"><div align="center"><strong><em>PREMIUM TYPE</em></strong></div></td>
      <td width="131"><div align="center"><strong><em>PREMIUM AMOUNT</em></strong></div></td>
      <td width="121"><div align="center"><strong><em>NOMINEE</em></strong></div></td>
      <td width="113"><div align="center"><strong><em>RELATION</em></strong></div></td>
      <td width="80"><div align="center"><strong><em>AGENTID</em></strong></div></td>
      <td width="117"><div align="center"><strong><em>EXPIRY DATE</em></strong></div></td>
    </tr>
  </table>
   
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="982" border="0" align="left" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="99" height="36"><%=rs.getInt(1)%> 
        <div align="center"></div></td>
      <td width="109"><%=rs.getDate(4)%> <div align="center"></div></td>
      <td width="143"><%=rs.getString(5)%> <div align="center"></div></td>
      <td width="132"><%=rs.getInt(6)%> <div align="center"></div></td>
      <td width="128"><%=rs.getString(7)%> <div align="center"></div></td>
      <td width="109"><%=rs.getString(8)%> <div align="center"></div></td>
      <td width="81"><%=rs.getInt(9)%> <div align="center"></div></td>
      <td width="102"><%=rs.getDate(10)%> <div align="center"></div></td>
    </tr>
    <% }%>
  </table>
  <p>&nbsp;</p>
  <div align="left"></div>
  <p align="left">&nbsp;</p>
  <p align="left">&nbsp;</p>
</form>
</body>
</html>
