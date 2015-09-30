<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" action="./AgentModify">
  <p> 
<%! Connection con;
ResultSet rs,rs1,rs2,rs3;
int agentid,branchmgrid;
String mgrname;
%>
    <%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

agentid=Integer.parseInt(request.getParameter("bid").trim());

con=DBConn.getConnection();

Statement st=con.createStatement();
rs=st.executeQuery("select * from agents where agentid="+ agentid);
 System.out.println(rs);
 while(rs.next())
 {
%>
  </p>
  <p align="center"><font color="#3333FF" size="7" face="Monotype Corsiva"><strong><em><font color="#66FF66">Agent 
    Modification</font></em></strong></font></p>
  <div align="left"> 
    <p><font color="#66FF66"><strong><em>Date:</em></strong> 
      <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
      </font> </p>
    <table width="291" border="3" align="center" cellpadding="3">
      <tr> 
        <td width="190"><div align="center"><strong><font size="4"><em>Agent ID 
            </em></font></strong></div></td>
        <td width="179"><input name="agentid" type="text" id="agentid" value="<%=agentid%>" readonly="true"></td>
      </tr>
    </table>
    
  </div>
  <p>&nbsp;</p>
  <table width="572" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="122"> <div align="center"><font size="4"><em><strong>Agent Name</strong></em></font></div></td>
      <td width="149"><input name="agentname" type="text" id="agentname" value="<%=rs.getString(2)%>"></td>
      <td width="118"><div align="center"><font size="4"><em><strong>Father Name</strong></em></font></div></td>
      <td width="143"><input name="agentfname" type="text" id="agentfname" value="<%=rs.getString(3)%>"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Age</strong></em></font></div></td>
      <td><input name="agentage" type="text" id="agentage" value="<%=rs.getInt(4)%>"></td>
      <td><div align="center"><font size="4"><em><strong>Sex</strong></em></font></div></td>
      <td><input name="asex" type="text" id="asex" value="<%=rs.getString(5)%>"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Qualification</strong></em></font></div></td>
      <td><input name="agentqual" type="text" id="agentqual" value="<%=rs.getString(6)%>"></td>
      <td><div align="center"><font size="4"><em><strong>Occuptaion</strong></em></font></div></td>
      <td><input name="agentoccup" type="text" id="agentoccup" value="<%=rs.getString(7)%>"></td>
    </tr>
    <tr> 
      <td><div align="center"><font size="4"><em><strong>Address</strong></em></font></div></td>
      <td><input name="agentaddr" type="text" id="agentaddr" value="<%=rs.getString(8)%>"></td>
      <td><div align="center"><font size="4"><em><strong>Security Deposit</strong></em></font></div></td>
      <td><input name="agentsecurity" type="text" id="agentsecurity" value="<%=rs.getInt(10)%>"></td>
    </tr>

	<% branchmgrid= rs.getInt(11);
	System.out.println("before subquery");
	rs1=st.executeQuery("select branchmgrname from branchmgr where branchmgrid="+ branchmgrid);
	if(rs1.next())
	{
	mgrname=rs1.getString(1);
	}
	%>
    <tr> 
      <td height="30"><div align="center"><font size="4"><em><strong>Manager Name</strong></em></font></div></td>
      <td height="30"><select name="mbname" id="select">
          <option><%=mgrname%></option>
          <% rs3=st.executeQuery("select branchmgrname from branchmgr where branchmgrid!="+ branchmgrid);
		while(rs.next())
		{
		%>
          <option><%=rs3.getString(1)%></option>
          <%}%>
        </select></td>
		
		
		  <%  
			   rs2=st.executeQuery("select password from login where userid="+ agentid );
			   if(rs2.next())
			   {%>
      <td height="30"><div align="center"><font size="4"><em><strong>Password</strong></em></font></div></td>
      <td height="30"><input name="agentpwd" type="password" id="agentpwd" value="<%=rs2.getString(1)%>"></td>
	  
	   <%}%>
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
	
	<% }%>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>
