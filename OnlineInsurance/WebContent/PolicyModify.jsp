<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Apna Life Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" action="./PolicyModify">
  <p> 
    <%! Connection con;
ResultSet rs,rs1,rs2;
int pid;
%>
    <%
	System.out.println("PolicyModify.jsp");
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

pid=Integer.parseInt(request.getParameter("bid").trim());

Statement st=null;

con=DBConn.getConnection();
st=con.createStatement();
rs=st.executeQuery("select * from policies where policyid=" + pid );
while(rs.next())
{
%>
  </p>
  <p align="center"><em><strong><font color="#993399" size="7" face="Monotype Corsiva"> 
    </font></strong></em> <strong><font color="#993399" size="7" face="Monotype Corsiva"><em>Policy 
    Modification</em></font></strong></p>
  <label>Date: 
  <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </label>
  <table width="291" border="3" align="center" cellpadding="3">
    <tr> 
      <td width="190"><div align="center"><strong><font size="4"><em>Policy ID 
          </em></font></strong></div></td>
      <td width="179"><input name="bid" type="text" id="bid" value="<%=pid%>" readonly="true"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="531" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="100"> Policy Name</td>
      <td width="149"><input name="pname" type="text" id="pname2" value="<%=rs.getString(2)%>"></td>
      <td width="98">Policy Term</td>
      <td width="144"><input name="pterm" type="text" id="pterm2" value="<%=rs.getInt(3)%>"></td>
    </tr>
    <tr> 
      <td>Policy Amount</td>
      <td><input name="pamt" type="text" id="pamt" value="<%=rs.getInt(4)%>"></td>
      <td>Face Amount</td>
      <td><input name="pfamt" type="text" id="pfamt" value="<%=rs.getInt(5)%>"></td>
    </tr>
    <tr> 
      <td>Interest</td>
      <td><input name="pint" type="text" id="pint" value="<%=rs.getInt(6)%>"></td>
      <td>Bonus Period</td>
      <td><input name="pbonperiod" type="text" id="pbonperiod" value="<%=rs.getInt(8)%>"></td>
    </tr>
    <tr> 
    <td>Bonus Rate</td>
      <td><input name="pbonrate" type="text" id="pbonrate" value="<%=rs.getInt(9)%>"></td>
    
	<%}%>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
   
    </tr>
  </table>
  <p>&nbsp; </p>
  <table width="231" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="109"><input type="submit" name="Submit" value="   Submit   "></td>
      <td width="164"><div align="center"> 
          <input type="reset" name="Submit2" value="   Reset   ">
        </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
<p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
