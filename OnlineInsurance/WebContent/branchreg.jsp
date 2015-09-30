<%@ page  import="java.sql.*,java.util.*" errorPage="" %>
<html>
<head>
<title>BRANCH REGISTRATION</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<p>&nbsp;</p><form name="form1"  action="./branchreginsert">
  <p> 
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);
%>
  </p>
  <p align="center"><strong><font color="#0000FF" size="7" face="Monotype Corsiva"><em><font color="#66FF66">Branch 
    Registration</font></em></font></strong></p>
  <font color="#66FF66">
  <label>Date: 
  <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </label>
  </font>
  <label> </label>
  <p>&nbsp;</p>
  <table width="593" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="135"><div align="center"><em><strong> Branch Name</strong></em></div></td>
      <td width="163"><input name="bname" type="text" id="bname2"></td>
      <td width="78"><div align="center"><strong><em>Location</em></strong></div></td>
      <td width="177"><input name="location" type="text" id="location2"></td>
    </tr>
    <tr> 
      <td><div align="center"><em><strong>Phone</strong></em></div></td>
      <td><input name="phone" type="text" id="phone2"></td>
      <td><div align="center"><strong><em>State</em></strong></div></td>
      <td><input name="state" type="text" id="state2"></td>
    </tr>
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
  <p>&nbsp;</p>
<p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
<em><strong><font color="#0000FF" size="6" face="Geneva, Arial, Helvetica, sans-serif"></font></strong></em> 
</body>
</html>
