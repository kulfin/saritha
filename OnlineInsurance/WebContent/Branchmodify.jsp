<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Apna Life Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<p>&nbsp;</p>
<form name="form1" method="post" action="./BranchModify">
  <p>
  <%! Connection con;
ResultSet rs;
int branchid;

%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

branchid=Integer.parseInt(request.getParameter("bid").trim());

con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select * from branch where branchid=" + branchid );
while(rs.next())
{

%>
  </p>
  <p align="center"><strong><font color="#993399" size="7" face="Monotype Corsiva"><em><font color="#66FF66">Branch 
    Modification</font></em></font></strong></p>
  <font color="#66FF66">
  <label>Date: 
  <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </label>
  </font>
  <label> </label>
  <table width="291" border="3" align="center" cellpadding="3">
    <tr>
      <td width="190"><div align="center"><strong><font size="4"><em>Branch ID </em></font></strong></div></td>
      <td width="179"><input name="bid" type="text" id="bid" value="<%=branchid%>" readonly="true"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="539" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="96"><div align="center"><em><strong> Branch Name</strong></em></div></td>
      <td width="145"><input name="bname" type="text" id="bname2" value="<%=rs.getString(2)%>"></td>
      <td width="100"><div align="center"><strong><em>Location</em></strong></div></td>
      <td width="158"><input name="location" type="text" id="location2" value="<%=rs.getString(3)%>"></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Phone</strong></em></div></td>
      <td><input name="phone" type="text" id="phone2" value="<%=rs.getInt(5)%>"></td>
      <td><div align="center"><strong><em>State</em></strong></div></td>
      <td><input name="state" type="text" id="state2" value="<%=rs.getString(6)%>"></td>
    </tr>
    <%}%>
  </table>
  <p>&nbsp; </p>
  <table width="249" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="123"><input type="submit" name="Submit" value="   Submit   "></td>
      <td width="112"><div align="center"> 
          <input type="reset" name="Submit2" value="   Reset   ">
        </div></td>
    </tr>
  </table>
  </form>
<p><em><strong><font color="#CC0099" size="7" face="Monotype Corsiva"></font></strong></em></p>
</body>
</html>
