<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" action="./AgentRegInsert">
  <%! Connection con;
ResultSet rs;
%>
  <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select branchmgrname from branchmgr");
 System.out.println(rs);
%>
  <p align="center"><font color="#66FF66" size="7" face="Monotype Corsiva"><strong><em>Agents 
    Registration</em></strong></font></p>
  <div align="left"><font color="#66FF66"><strong><em>Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
    </font></div>
  <p>&nbsp;</p>
  <table width="572" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="122"> <div align="center"><font size="4"><em><strong>Agent Name</strong></em></font></div></td>
      <td width="149"><input name="agentname" type="text" id="agentname"></td>
      <td width="118"><div align="center"><font size="4"><em><strong>Father Name</strong></em></font></div></td>
      <td width="143"><input name="agentfname" type="text" id="agentfname"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Age</strong></em></font></div></td>
      <td><input name="agentage" type="text" id="agentage"></td>
      <td><div align="center"><font size="4"><em><strong>Sex</strong></em></font></div></td>
      <td><select name="asex" size="1" id="asex">
          <option value="M">Male</option>
          <option value="F">Female</option>
        </select></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Qualification</strong></em></font></div></td>
      <td><input name="agentqual" type="text" id="agentqual"></td>
      <td><div align="center"><font size="4"><em><strong>Occuptaion</strong></em></font></div></td>
      <td><input name="agentoccup" type="text" id="agentoccup"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Address</strong></em></font></div></td>
      <td><input name="agentaddr" type="text" id="agentaddr"></td>
      <td><div align="center"><font size="4"><em><strong>Security Deposit</strong></em></font></div></td>
      <td><input name="agentsecurity" type="text" id="agentsecurity"></td>
    </tr>
    <tr> 
      <td height="30"><div align="center"><font size="4"><em><strong>Manager Name</strong></em></font></div></td>
      <td height="30"><select name="mbname" id="mbname">
          <% 
	 while(rs.next()){
   %>
          <option><%=rs.getString(1)%></option>
          <% } %>
        </select></td>
      <td height="30"><div align="center"><font size="4"><em><strong>Password</strong></em></font></div></td>
      <td height="30"><input name="agentpwd" type="password" id="agentpwd"></td>
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
</form>
</body>
</html>
