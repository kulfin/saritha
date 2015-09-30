<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" action="./CustomerModify.jsp">
  <p>
    <%! Connection con;
ResultSet rs;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select custid from customer");
 System.out.println(rs);
%>
  </p>
  <p align="center"><font color="#CC0099" size="7" face="Monotype Corsiva"> <em><strong> 
    <font color="#66FF66">Customer Modification</font></strong></em></font></p>
  <p align="left"><font color="#66FF66">Date: 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
    </font> </p>
  <p>&nbsp;</p>
  <table width="363" height="66" border="3" align="center" cellpadding="3">
    <tr> 
      <td width="193"><div align="right"><font size="5" face="Times New Roman, Times, serif"><em><strong>Select 
          Customer ID</strong></em></font><strong> </strong></div>
        <strong>
        <label> </label>
        </strong> <div align="center"><strong><font size="5" face="Times New Roman, Times, serif"></font></strong></div>
        </td>
      <td width="142"><select name="cid" id="cid">
          <% while(rs.next())
	  {%>
          <option><%=rs.getInt(1)%></option>
          <% }	  %>
        </select></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <div align="center"></div>
  <table width="273" border="0" align="center" cellpadding="3">
    <tr> 
      <td width="152"><input type="submit" name="Submit" value="     Submit   "></td>
      <td width="143"><input type="reset" name="Submit2" value="       Reset     "></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>
