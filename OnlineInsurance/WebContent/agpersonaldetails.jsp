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

String obj=(String)session.getAttribute("AID");
int aid=Integer.parseInt(obj);
con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select * from  agents where agentid="+ aid);

%>
  <p align="center"><strong><font color="#CC33CC" size="5"><em><font color="#66FF66">AGENT 
    PERSONAL DETAILS</font></em></font></strong></p>
  <p><font color="#66FF66"><strong><em> Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
    </font> </p>
  <table width="1134" height="59" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="80"><div align="center"><strong><em>AGENTID</em></strong></div></td>
      <td width="136"><div align="center"><strong><em>AGENT NAME</em></strong></div></td>
      <td width="135"><div align="center"><strong><em>FATHER NAME</em></strong></div></td>
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
  <table width="1136" border="0" align="right" cellpadding="3">
    <%
while(rs.next())
{ %>
    <tr> 
      <td width="109" height="31"><%=rs.getInt(1)%> 
        <div align="center"></div></td>
      <td width="116"><%=rs.getString(2)%> <div align="center"></div></td>
      <td width="122"><%=rs.getString(3)%> <div align="center"></div></td>
      <td width="36"><%=rs.getInt(4)%> <div align="center"></div></td>
      <td width="49"><%=rs.getString(5)%> <div align="center"></div></td>
      <td width="124"><%=rs.getString(6)%> <div align="center"></div></td>
      <td width="112"><%=rs.getString(7)%> <div align="center"></div></td>
      <td width="133"><%=rs.getString(8)%> <div align="center"></div></td>
      <td width="82"><%=rs.getDate(9)%> <div align="center"></div></td>
      <td width="98"><%=rs.getInt(10)%>  <div align="center"></div></td>
      <td width="65"><%=rs.getInt(11)%>  <div align="center"></div></td>
    </tr>
    <% }%>
  </table>
  <p>&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
