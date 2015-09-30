<%@ page import="java.sql.*,database.DBConn" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#A3A3D1">
<form name="form1"  action="./ManagerModify">
  <p>
    <%! Connection con,con1,con2;
ResultSet rs,rs1,rs2;
int mgrid;
String branchname;
%>
    <%
	System.out.println("Managermodify.jsp");
	
java.util.Date d=new java.util.Date();
java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
System.out.println("sys date is"+d2);

mgrid=Integer.parseInt(request.getParameter("bid").trim());

con=DBConn.getConnection();

Statement st=con.createStatement();

rs=st.executeQuery("select * from branchmgr where branchmgrid=" + mgrid );
if(rs.next())
{

%>
  </p>
  <p align="center"><strong><font color="#993399" size="7" face="Monotype Corsiva"><em>Branch 
    Manager Modification</em></font></strong></p>
  <label>Date: 
  <input name="bdate" type="text" id="bdate" value="<%=d2%>" readonly="true">
  </label>
  <table width="291" border="3" align="center" cellpadding="3">
    <tr> 
      <td width="190"><div align="center"><strong><font size="4"><em>Manager ID 
          </em></font></strong></div></td>
      <td width="179"><input name="bid" type="text" id="bid" value="<%=mgrid%>" readonly="true"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="539" border="3" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="96"> Manager Name</td>
      <td width="145"><input name="mname" type="text" id="mname2" value="<%=rs.getString(2)%>"></td>
      <td width="100">Father Name</td>
      <td width="158"><input name="mfname" type="text" id="mfname2" value="<%=rs.getString(3)%>"></td>
    </tr>
    <tr> 
      <td>Age</td>
      <td><input name="mage" type="text" id="mage2" value="<%=rs.getInt(4)%>"></td>
      <td>Sex</td>
      <td><input name="msex" type="text" id="msex" value="<%=rs.getString(5)%>"></td>
    </tr>
    <tr>
      <td>Qualification</td>
      <td><input name="mqual" type="text" id="mqual" value="<%=rs.getString(6)%>"></td>
      <td>Address</td>
      <td><input name="maddr" type="text" id="maddr" value="<%=rs.getString(7)%>"></td>
    </tr>
    <tr>
	
	<% branchname= rs.getString(8);
	System.out.println("before subquery");
	%>
      <td>Branch Name</td>
      <td><select name="mbname" id="mbname">
               <option><%=branchname%></option>
			  <%}%>
			  
			  <%
			  con=DBConn.getConnection();
			   st=con.createStatement();

			  rs1=st.executeQuery("select branchname from branch where branchid!=(select branchid from branch where branchname='"+branchname +"')");
			System.out.println("after subquery");
			
			   while(rs1.next())
			   {%>
			   <option><%=rs1.getString(1)%></option>
			   
			   <% System.out.println("no of rows");%>
			   		<%}%>						   
        </select></td>

		  <%  
		  con=DBConn.getConnection();
		  st=con.createStatement();

		  rs2=st.executeQuery("select password from login where userid="+ mgrid );
			   if(rs2.next())
			   {%>
      <td>Password</td>
      <td><input name="mpwd" type="password" id="mpwd" value="<%=rs2.getString(1)%>"></td>
	  <%}%>
    </tr>
   
  </table>
  <p>&nbsp; </p>
  <table width="287" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr> 
      <td width="123"><input type="submit" name="Submit" value="   Submit   "></td>
      <td width="150"><div align="center"> 
          <input type="reset" name="Submit2" value="   Reset   ">
        </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>

     
