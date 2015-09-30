<!--
	File : StatusTracker.jsp
	Purpose : This jsp is used to generate reports to track the status of employee
-->
<html>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>
<%@page contentType="application/vnd.ms-excel"%>
<%response.setHeader("Content-Disposition","attachment;filename=Status.xls");%>


<head>
<title>
Modifying Employee Details.......
</title>
<SCRIPT LANGUAGE="JavaScript">

</SCRIPT>

</head>
<body Class=SC>
<form name="SForm"  METHOD="GET">
	<br>
	<table align=center BGCOLOR="white" border=1 cellspacing=0 cellpadding=1>
	<tr>
	<th>Admin UserID</th>
	<th>UpdatedUser EmpNo</th>
	<th>Date Updated</th>
	<th>Date Allocated</th>
	<th>Project Code</th>
	<th>From Status</th>
	<th>To Status</th>
	
	</tr>
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
ResultSet rs=null;
String AdminUser,DateUpdated,DateAllocated;
String Project,OldStatus,NewStatus;
int EmpModified;
String strStartDate = request.getParameter("strStartDate");
String strEndDate =  request.getParameter("strEndDate");

//System.out.println(strStartDate);
//System.out.println(strEndDate);


try
{

	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Query = "Select * from StatusChangeTracker  where DateAllocated between (\'"+strStartDate+"\') and (\'"+strEndDate+"\')"; 
	//System.out.println(Query);
	rs = stmt.executeQuery(Query);
	if(rs!=null)
	{
		while(rs.next()){
		//System.out.println("----------");
			AdminUser=rs.getString(2);
			EmpModified=rs.getInt(3);
			DateUpdated=rs.getString(4);
			DateAllocated=rs.getString(5);
			Project=rs.getString(6);
			OldStatus=rs.getString(7);
			NewStatus=rs.getString(8);
	 	//System.out.println(AdminUser+":"+EmpModified+":"+DateUpdated+":"+DateAllocated+":"+Project+":"+OldStatus+":"+NewStatus);
	
%>
<tr>
	<td><%=AdminUser%></td>
  	<td><%=EmpModified%></td>
  	<td><%=DateUpdated%></td>
  	<td><%=DateAllocated%></td>
  	<td><%=Project%></td>
  	<td><%=OldStatus%></td>
    <td><%=NewStatus%></td>
</tr>	
<%
		}
	}
	else
		System.out.println("No Records");
}
catch(Exception e)
{
	System.out.println("Exception"+e);
}
%>
</table>

</form>
</body>
</html>