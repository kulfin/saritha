<!--
	File : ModifyDetails.jsp
	Purpose : This jsp is used to modify the user details by Authorised Users
-->
<html>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>

<head>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

<title>
Modifying Employee Details.......
</title>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
</head>
<body Class=SC>
<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String EmpName,EmpMailId,EmpBaseCity,Role;
String PrimarySkillRemarks;
String strEmpNo = request.getParameter("EmpNo");
String strEmpBaseCity=request.getParameter("EmpBaseCity");
int EmpNo= Integer.parseInt(strEmpNo);
Date JoiningDate;
String strPrimarySkill=request.getParameter("PrimarySkill");
String strRemarks=request.getParameter("Remarks");
String strRemarksBKUP2 = (String)session.getAttribute("RemarksBKUP2");
try
{
	strRemarks+=";"+strRemarksBKUP2;
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Query = "Update empmaster set PrimarySkillset=\'"+strPrimarySkill+"\',Remarks=\'"+strRemarks+"\' where EMPNO="+EmpNo;
	//System.out.println(Query);
	int rowsAffected=stmt.executeUpdate(Query);		
	//System.out.println("Rows Affected = " + rowsAffected);
	//If data is updated then an entry is added to Activity log 
	if(rowsAffected==1)
	{
		String AdminUser=(String)session.getAttribute("userr");
		Timestamp DateModified = new Timestamp(System.currentTimeMillis());
		String ModDate = DateModified.toString();
		String InsQuery="Insert into activitytracker(AdminUser,EmpModified,DateModified) values(\'"+AdminUser+"\',\'"+EmpNo+"\',\'"+ModDate+"\')";
		int LogEntry=stmt.executeUpdate(InsQuery);
		%>
			<script>
				for(i=1;i<=10;i++) document.write("<br>");
			</script>
				<H3 align="center">Details Updated Successfully </H3>
			<BR>
			<center>
			 <INPUT TYPE="button" value="Close" onclick='window.close();'></center>
			</center>
		<%
	}
}
catch(Exception e)
{
	System.out.println("Exception"+e);
}
%>

</body>
</html>