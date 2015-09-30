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
boolean stat;
int i;

%>
    <%
	
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

pyid=Integer.parseInt(request.getParameter("pid").trim());


con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select * from custbonustx where custbonustxid=(select max(custbonustxid) from custbonustx where custpolicyid="+pyid +")");
System.out.println("stattus ::"+stat);

System.out.println("stattus ::after  "+stat);

i=rs.getFetchSize();
if(!rs.next())
{
System.out.println("stattus ::if statmtmmt :  "+stat);
%>

<p align="center"><strong><font color="#66FF66" size="6"><em> CUSTOMER POLICY 
    BONUS IS NIL</em></font></strong></p>

<%
}
else 
{
%>

  <p align="left"><strong><em><font color="#66FF66">Date:</font></em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p align="center"><strong><font color="#66FF66" size="5"><em> CUSTOMER POLICY 
    BONUS DETAILS</em></font></strong></p>
  <table width="732" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="190"><div align="center"><strong><em>CUST BONUX TXID</em></strong></div></td>
      <td width="165"><div align="center"><strong><em>CUST POLICY ID</em></strong></div></td>
      <td width="160"><div align="center"><strong><em>BONUS</em></strong></div></td>
      <td width="171"><div align="center"><strong><em>BONUS DATE</em></strong></div></td>
    </tr>
  </table>
  <table width="684" border="0" align="center" cellpadding="3">
    
    <tr> 
      <td width="198" height="36"><%=rs.getInt(1)%> <div align="center"></div></td>
      <td width="173"><%=rs.getInt(2)%> <div align="center"></div></td>
      <td width="155"><%=rs.getInt(4)%> <div align="center"></div></td>
      <td width="124"><%=rs.getDate(3)%> <div align="center"></div></td>
    </tr>
 
  </table>
  <% }%>
  <p><strong></strong></p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
