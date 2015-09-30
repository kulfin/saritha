
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
CallableStatement cstmt;
int stat;
int aid;
%>

<%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

int aid=Integer.parseInt(request.getParameter("bid").trim());

con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select * from agentcommtx where AGENTCOMMTXID=(select max(AGENTCOMMTXID) from agentcommtx where agentid="+ aid + ")" );
System.out.println(" rs:: "  +rs);

%>

  <p>Date: 
    <input name="bdate" type="text" id="bdate2" value="<%=d2%>" readonly="true">
  </p>
  <p align="center"><em><strong><font color="#66FF66" size="7" face="Monotype Corsiva">Agent 
    Commission </font></strong></em></p>
  <p>&nbsp;</p>
  <table width="777" border="2" align="center" cellpadding="3">
    <tr> 
      <td width="194" height="64"><div align="center"><font size="5" face="Times New Roman, Times, serif"><em><strong>Agent 
          CommTX ID</strong></em></font><strong> </strong></div></td>
           <td width="156"><div align="center"><font size="5" face="Times New Roman, Times, serif"><em><strong>Agent ID
      <td width="156"><div align="center"><font size="5" face="Times New Roman, Times, serif"><em><strong>Agent 
          Amount </strong></em></font></div></td>
      <td width="207"><div align="center"><font size="5" face="Times New Roman, Times, serif"><em><strong>Agent 
          Commission</strong></em></font></div></td>
      </tr>
  </table>
  <% while(rs.next())
  {
   %>
  <div align="center"></div>
  <table width="749" border="0" align="center" cellpadding="3">
    <tr>
      <td width="208" height="26"><%=rs.getInt(1)%></td>
       <td width="167"><%=rs.getInt(2)%></td>
      <td width="167"><%=rs.getInt(3)%></td>
      <td width="201"><%=rs.getInt(4)%></td>
    
    </tr>
  </table>
  
  <%
  }
   %>
 
   
    
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
