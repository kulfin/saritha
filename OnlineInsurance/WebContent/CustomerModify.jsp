<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./CustModify">
  <p> 
    <%! Connection con;
ResultSet rs,rs1;
int ccid;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

ccid=Integer.parseInt(request.getParameter("cid").trim());


con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select * from customer where custid=" + ccid );
while(rs.next())
{
%>
  </p>
  <p align="center"><font color="#3333FF" size="7" face="Monotype Corsiva"><strong><em><font color="#66FF66">Customer 
    Modification</font></em></strong></font></p>
  <div align="left"> 
    <p><font color="#66FF66"><strong><em>Date:</em></strong> 
      <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
      </font> </p>
    <p> 
      <label></label>
    </p>
    <table width="291" border="3" align="center" cellpadding="3">
      <tr> 
        <td width="190"><div align="center"><strong><font size="4"><em>Customer 
            ID </em></font></strong></div></td>
        <td width="179"><input name="bid" type="text" id="bid" value="<%=ccid%>" readonly="true"></td>
      </tr>
    </table>
  </div>
  <p>&nbsp;</p>
  <table width="572" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="122"> <div align="center"><em><strong>Customer Name</strong></em></div></td>
      <td width="149"><input name="cname" type="text" id="cname" value="<%=rs.getString(2)%>"></td>
      <td width="118"><div align="center"><em><strong>Father Name</strong></em></div></td>
      <td width="143"><input name="cfname" type="text" id="cfname" value="<%=rs.getString(3)%>"></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Age</strong></em></div></td>
      <td><input name="cage" type="text" id="cage2" value="<%=rs.getInt(4)%>"></td>
      <td><div align="center"><em><strong>Address</strong></em></div></td>
      <td><input name="caddr" type="text" id="caddr" value="<%=rs.getString(7)%>"></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Qualification</strong></em></div></td>
      <td><input name="cqual" type="text" id="cqual" value="<%=rs.getString(6)%>"></td>
      <td><div align="center"><em><strong>Occuptaion</strong></em></div></td>
      <td><input name="coccup" type="text" id="coccup" value="<%=rs.getString(8)%>"></td>
    </tr>
	<%}%>
    <%
    con=DBConn.getConnection();
     st=con.createStatement();

    rs=st.executeQuery("select password from login where userid="+ccid );
			   if(rs1.next())
			   {%>
	<tr>
	      <td><div align="center"><em><strong>Password</strong></em></div></td>
      <td><input name="cpwd" type="password" id="cpwd" value="<%=rs1.getString(1)%>"></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
	<%}%>
  </table>
  <p>&nbsp;</p>
  <table width="260" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="97"><input type="submit" name="Submit" value="    Submit     "></td>
      <td width="149"><div align="center"> 
          <input type="reset" name="Submit2" value="     Reset    ">
        </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
