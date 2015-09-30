<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Apna Life Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1" >
<form name="form1"  action="./ManagerDeletion.jsp">
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
 rs=st.executeQuery("select branchmgrid from branchmgr");
 System.out.println(rs);
%>
  </p>
  <p align="center"><font color="#CC0099" size="7" face="Monotype Corsiva"> <em><strong>Manager 
    Deletion</strong></em></font></p>
  <p align="left">Date: 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p>&nbsp;</p>
  <table width="401" border="3" align="center" cellpadding="3">
    <tr> 
      <td width="215"><div align="center"><font size="5" face="Times New Roman, Times, serif"><em><strong>Select 
          ManagerID</strong></em></font><strong> 
          <label> </label>
          </strong>
          <div align="center"><strong><font size="5" face="Times New Roman, Times, serif"></font></strong></div>
        </div></td>
      <td width="201"><select name="bid" id="bid">
         <% while(rs.next())
	  {%>
          <option><%=rs.getInt(1)%></option>
          <% }
	  %>
        </select></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <div align="center"></div>
  <table width="289" border="0" align="center" cellpadding="3">
    <tr> 
      <td width="127"><input type="submit" name="Submit" value="     Submit   "></td>
      <td width="144"><input type="reset" name="Submit2" value="       Reset     "></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<p>&nbsp;</p>
</form>
</body>
</html>
