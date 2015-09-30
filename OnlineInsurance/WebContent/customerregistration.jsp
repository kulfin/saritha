<%@ page contentType="text/html; charset=" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<form name="form1" method="post" action="">
  <p>&nbsp; </p>
  <p align="center"><strong><font color="#9900FF" size="7" face="Monotype Corsiva">Customer 
    Registration</font></strong></p>
  <table width="118" border="0" cellspacing="2" cellpadding="2">
    <tr> 
      <td width="44"><div align="center">Date</div></td>
      <td width="60">&nbsp;</td>
    </tr>
  </table>
  <p align="center">&nbsp;</p>
  <table width="573" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="122"> Customer Name</td>
      <td width="149"><input type="text" name="textfield"></td>
      <td width="118">Father's Name</td>
      <td width="144"><input type="text" name="textfield5"></td>
    </tr>
    <tr> 
      <td>Age</td>
      <td><input type="text" name="textfield2"></td>
      <td>Sex</td>
      <td><select name="select">
          <option value="M">Male</option>
          <option value="F">Female</option>
        </select></td>
    </tr>
    <tr> 
      <td>Qualification</td>
      <td><input type="text" name="textfield3"></td>
      <td>Address</td>
      <td><input type="text" name="textfield7"></td>
    </tr>
    <tr> 
      <td>Occupation</td>
      <td><input type="text" name="textfield4"></td>
	  <td>Pass word</td>
	  <td><input type="text" name="textfield6"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="184" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="70"><input type="submit" name="Submit" value="Submit"></td>
      <td width="90"><div align="center"> 
          <input type="reset" name="Submit2" value="Reset">
        </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
