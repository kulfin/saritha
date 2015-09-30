<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./custpbonus.jsp" target="mainFrame">

  <p align="left"> 
    <%! Connection con;
ResultSet rs;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select custpolicyid from custpolicies");
 System.out.println(rs);
%>
    <strong><font color="#66FF66"><em>Date: </em></font></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p align="left"><font color="#66FF66" size="6" face="Monotype Corsiva"> <em><strong> 
    Customer Policy </strong></em></font></p>
  <p align="left">&nbsp;</p>
  <div align="left">
<table width="234" height="60" border="3" cellpadding="3">
      <tr> 
        <td width="170" height="50"> 
          <div align="center"><font size="5" face="Times New Roman, Times, serif"><em><strong><font size="3">Select 
            Customer PolicyID</font></strong></em></font><font size="3"><strong> 
            </strong><strong> </strong></font></div>
          <strong>
<label> </label>
          </strong> <div align="center"><strong><font size="5" face="Times New Roman, Times, serif"></font></strong></div></td>
        <td width="97"><select name="pid" id="pid">
            <% while(rs.next())
	  {%>
            <option><%=rs.getInt(1)%></option>
            <% }
	  %>
          </select></td>
      </tr>
    </table>
  </div>
  <p>&nbsp;</p>
  <div align="center"></div>
  <table width="181" border="0" align="left" cellpadding="3">
    <tr> 
      <td width="127"> <div align="center">
          <input type="submit" name="Submit" value="Submit">
        </div></td>
      <td width="144"> <div align="center">
          <input type="reset" name="Submit2" value="Reset">
        </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>
