<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1"  action="./AgentModify.jsp">
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
  <p align="center"><em><strong><font color="#66FF66" size="7" face="Monotype Corsiva">Agent</font></strong></em> 
    <font color="#66FF66" size="7" face="Monotype Corsiva"><em><strong>Modification</strong></em></font></p>
  <p align="left">Date: 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </p>
  <p>&nbsp;</p>
  <table width="351" height="66" border="3" align="center" cellpadding="3">
    <tr> 
      <td width="229" height="62"> <div align="right"><font size="5" face="Times New Roman, Times, serif"><em><strong>Select 
          Agent ID</strong></em></font><strong> </strong></div>
        <strong>
        <label> </label>
        </strong> <div align="center"><strong><font size="5" face="Times New Roman, Times, serif"></font></strong></div>
        </td>
      <td width="164"><select name="bid" id="bid">
          <% while(rs.next())
	  {%>
          <option><%=rs.getInt(1)%></option>
          <% }
	  %>
        </select></td>
    </tr>
  </table>
  <%
  rs.close();%>
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
</form>
</body>
</html>
