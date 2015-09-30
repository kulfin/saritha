<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./PremiumTX">
  <p> 
    <%! Connection con;
ResultSet rs;
CallableStatement cstmt;
%>
    <%
	System.out.println("Premium Payment jspform  ");
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

int id=Integer.parseInt(request.getParameter("bid").trim());

con=DBConn.getConnection();

cstmt=con.prepareCall("{call dues(?,?,?,?,?)}");
cstmt.registerOutParameter(2,Types.INTEGER);
cstmt.registerOutParameter(3,Types.INTEGER);
cstmt.registerOutParameter(4,Types.INTEGER);
cstmt.registerOutParameter(5,Types.DATE);
cstmt.setInt(1,id);
cstmt.execute();
System.out.println(" cstmtttttt values ");
System.out.println( "after cstmtttttt");
System.out.println(cstmt.getInt(2));
System.out.println(cstmt.getInt(3));
System.out.println(cstmt.getInt(4));
System.out.println(cstmt.getDate(5));
System.out.println("after printf statement");

rs=cstmt.executeQuery("select custid,policyid,premiumtype,premiumamount,expirydate from custpolicies where custpolicyid=" + id);

%>
  <p align="left"> <strong>Date:</strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p align="center"><font color="#CC0099" size="6" face="Monotype Corsiva"><em><strong>Customer 
    Premium Amount Payment Form</strong></em></font></p>
  <table width="749" height="179" border="0" align="center" cellpadding="3">
    <tr>
      <td width="99">CustId</td>
      <td width="153"><input type="text" name="textfield"></td>
      <td width="122">PolicyId</td>
      <td width="185"><input type="text" name="textfield6"></td>
    </tr>
    <tr>
      <td>PremiumType</td>
      <td><input type="text" name="textfield2"></td>
      <td>Premium Amount</td>
      <td><input type="text" name="textfield7"></td>
    </tr>
    <tr>
      <td height="31">Due</td>
      <td><input type="text" name="textfield3"></td>
      <td>Total PremiumAmount</td>
      <td><input type="text" name="textfield8"></td>
    </tr>
    <tr>
      <td height="31">Net Amount</td>
      <td><input type="text" name="textfield4"></td>
      <td>Next Premium Date</td>
      <td><input type="text" name="textfield9"></td>
    </tr>
    <tr>
      <td height="37">Policy ExprieDate</td>
      <td><input type="text" name="textfield5"></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
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
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
