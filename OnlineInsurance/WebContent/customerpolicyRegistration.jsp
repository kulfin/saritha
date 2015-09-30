<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1" method="post" action="./CustPolyRegInsert">

<%! Connection con;
ResultSet rs,rs1,rs2;
%>
<%
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

// con=DBConn.getConnection();
con=DBConn.getConnection();
Statement st=con.createStatement();

rs=st.executeQuery("select custid from customer");
 rs1=st.executeQuery("select policyid from policies");
  rs2=st.executeQuery("select agentid from agents");
 System.out.println(rs);
%>

  <p align="center"><font color="#3300FF" size="7" face="Monotype Corsiva"><strong><em><font color="#66FF66">Customer 
    Policy Registration</font></em></strong></font></p>
  <div align="left"></div>
  <p><font color="#66FF66"><strong><em>Date:</em></strong> 
    <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
    </font> </p>
  <table width="630" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="120"> <div align="center"><em><strong><font face="Times New Roman, Times, serif">Customer 
          ID</font></strong></em></div></td>
      <td width="164"><select name="cid" id="cid">
          <% 
	 while(rs.next()){
   %>
          <option><%=rs.getInt(1)%></option>
	 
	   <% } %>
        </select></td>
      <td width="150"><div align="center"><font face="Times New Roman, Times, serif"><strong><em>Policy 
          ID</em></strong></font></div></td>
      <td width="170"><select name="pid" id="pid">
          <% 
	 while(rs1.next()){
   %>
          <option><%=rs1.getInt(1)%></option>
	 
	   <% } %>
        </select></td>
    </tr>
    <tr> 
      <td><div align="center"><font face="Times New Roman, Times, serif"><strong><em>Premium 
          Type</em></strong></font></div></td>
      <td><select name="ptype" id="ptype">
          <option value="MONTHLY">MONTHLY</option>
          <option value="QUARTERLY">QUARTERLY</option>
          <option value="HALFYEARLY">HALFYEARLY</option>
          <option value="ANNUALLY">ANNUALLY</option>
        </select></td>
      <td><div align="center"><font face="Times New Roman, Times, serif"><strong><em>Agent 
          ID</em></strong></font></div></td>
      <td><select name="aid" id="aid">
          <% 
	 while(rs2.next()){
   %>
          <option><%=rs2.getInt(1)%></option>
	 
	   <% } %>
        </select></td>
    </tr>
    <tr> 
      <td><div align="center"><strong><em>Nominee</em></strong></div></td>
      <td><input name="nominee" type="text" id="nominee"></td>
      <td><div align="center"><strong><em>Relation</em></strong></div></td>
      <td><input name="relation" type="text" id="relation"></td>
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
</form>
</body>
</html>
