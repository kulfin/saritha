<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>MANAGER REGISTRATION</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" action="./mgreginsert">
<%! Connection con;
ResultSet rs;
%>
<%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select branchname from branch");
 System.out.println(rs);
%>

  <p align="center"><font color="#66FF66" size="7" face="Monotype Corsiva">Manager 
    Registration</font></p>
  <div align="left"><strong><em><font color="#66FF66">Date:</font></em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </div>
  <p>&nbsp;</p>
  <table width="713" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="140"><div align="center"><font size="4"><em><strong>Name</strong></em></font></div></td>
      <td width="148"><input name="mname" type="text" id="mname"></td>
      <td width="175"><div align="center"><font size="4"><em><strong>Father Name</strong></em></font></div></td>
      <td width="210"><input name="mfname" type="text" id="mfname"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Age</strong></em></font></div></td>
      <td><input name="mage" type="text" id="mage"></td>
      <td><div align="center"><font size="4"><em><strong>Sex</strong></em></font></div></td>
      <td> <select name="msex" size="1" id="msex">
          <option value="M">Male</option>
          <option value="F">Female</option>
        </select></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Qualification</strong></em></font></div></td>
      <td><input name="mqual" type="text" id="mqual"></td>
      <td><div align="center"><font size="4"><em><strong>Address</strong></em></font></div></td>
      <td><input name="maddr" type="text" id="maddr"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Branch Name</strong></em></font></div></td>
      <td><select name="mbname" id="mbname">
          <% 
	 while(rs.next()){
   %>
          <option><%=rs.getString(1)%></option>
          <% } %>
        </select></td>
      <td><div align="center"><font size="4"><em><strong>Password</strong></em></font></div></td>
      <td><input name="mpwd" type="password" id="mpwd" > </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="319" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="147"><input type="submit" name="Submit" value="        Submit        "></td>
      <td width="158"><div align="center">
          <input type="reset" name="Submit2" value="        Reset       ">
        </div></table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  </form>
</body>
</html>
