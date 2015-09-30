<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form  action="./AgentCommission.jsp" name="form1" target="mainFrame">
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
rs=st.executeQuery("select agentid from agents");
 System.out.println(rs);
%>
  </p>
  <p><font color="#66FF66">Date: 
    <input name="bdate" type="text" id="bdate2" value="<%=d2%>" readonly="true">
    </font></p>
  <p align="left"><font color="#66FF66"><em><strong><font size="7" face="Monotype Corsiva">Agents 
    </font></strong></em></font><em><strong><font color="#FFFFFF" size="7" face="Monotype Corsiva"> 
    </font></strong></em></p>
  <p align="left">&nbsp;</p>
  <div align="left">
    <table width="208" height="66" border="3" align="left" cellpadding="3">
      <tr> 
        <td width="97" height="62"> <div align="left"><font size="5" face="Times New Roman, Times, serif"><em><strong>Select 
            Agent ID</strong></em></font><strong> </strong><strong> </strong></div>
          <strong>
<label> </label>
          </strong> <div align="center"><strong><font size="5" face="Times New Roman, Times, serif"></font></strong></div></td>
        <td width="83"><select name="bid" id="bid">
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
  <div align="center">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </div>
  <table width="178" border="0" align="left" cellpadding="3">
    <tr> 
      <td width="127"><div align="center">
          <input type="submit" name="Submit" value="Submit">
        </div></td>
      <td width="109"> <div align="center">
          <input type="reset" name="Submit2" value="Reset">
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
