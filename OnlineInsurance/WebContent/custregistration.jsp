<%@ page  import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./CustRegInsert">


<%! Connection con;
ResultSet rs;
%>
<%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

%>


  <p align="center"><font color="#3333FF" size="7" face="Monotype Corsiva"><strong><em>Customer 
    Registration</em></strong></font></p>
  <div align="left"><strong><em>Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </div>
  <p>&nbsp;</p>
  <table width="572" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="122"> <div align="center"><em><strong>Customer Name</strong></em></div></td>
      <td width="149"><input name="cname" type="text" id="cname"></td>
      <td width="118"><div align="center"><em><strong>Father Name</strong></em></div></td>
      <td width="143"><input name="cfname" type="text" id="cfname"></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Age</strong></em></div></td>
      <td><input name="cage" type="text" id="cage"></td>
      <td><div align="center"><em><strong>Sex</strong></em></div></td>
      <td><select name="csex" size="1" id="csex">
          <option value="M">Male</option>
          <option value="F">Female</option>
        </select></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Qualification</strong></em></div></td>
      <td><input name="cqual" type="text" id="cqual"></td>
      <td><div align="center"><em><strong>Occuptaion</strong></em></div></td>
      <td><input name="coccup" type="text" id="coccup"></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Address</strong></em></div></td>
      <td><input name="caddr" type="text" id="caddr"></td>
      <td><div align="center"><em><strong>Password</strong></em></div></td>
      <td><input name="cpwd" type="password" id="cpwd"></td>
    </tr>
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
