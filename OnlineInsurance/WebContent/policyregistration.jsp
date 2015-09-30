<%@ page  import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Apna Life Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" action="./policyreginsert">

<%! Connection con;
ResultSet rs;
%>
<%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);
%>

  <p align="center"><strong><font color="#9900FF" size="7" face="Monotype Corsiva">Policy 
    Registration</font></strong></p>
	
  Date: 
  <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  <p align="center">&nbsp;</p>
  <table width="539" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr>
      <td width="96">Policy Name</td>
      <td width="145"><input name="pname" type="text" id="pname"></td>
      <td width="100">Policy Term</td>
      <td width="158"><input name="pterm" type="text" id="pterm"></td>
    </tr>
    <tr>
      <td>Policy Amount</td>
      <td><input name="pamt" type="text" id="pamt"></td>
      <td>Face Amount</td>
      <td><input name="pfaceamt" type="text" id="pfaceamt"></td>
    </tr>
    <tr>
      <td>Interest</td>
      <td><input name="pinterest" type="text" id="pinterest"></td>
      <td>Bonus Period</td>
      <td><input name="pbonusperiod" type="text" id="pbonusperiod"></td>
    </tr>
    <tr>
      <td>Bonus Rate</td>
      <td><input name="pbonusrate" type="text" id="pbonusrate"></td>
     
      </tr>
  </table>
  <p>&nbsp;</p>
  <table width="214" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr>
      <td width="87"><input type="submit" name="Submit" value="  Submit  "></td>
      <td width="103"><div align="center">
          <input type="reset" name="Submit2" value="  Reset  ">
        </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
