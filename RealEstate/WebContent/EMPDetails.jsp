<!--
	File : EmpDetails.jsp
	Purpose : This jsp is used to display the details of all the employees and also 
			  provide link to view or edit the details.

-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="com.DBConnection"%>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>

<HEAD>
<TITLE> Employee Details </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
	<LINK href="styles.css" type="text/css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript">
<!--

function editDetails(Label , strEmpNo){
	
	
var Loc = "EditDetails.jsp?txtEmpNo="+strEmpNo;

	if(Label=="Close")	{ window.close(); }
	else if(Label == "Edit") 
	{
location.replace(Loc);
	}


}
//-->
</SCRIPT>
</HEAD>


<BODY class=SC >



<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String filedata="";
String filestr="";
File file=null;
FileWriter fr=null;
Random rand = new Random(100000l);

String EmpName,EmpMailId,EmpBaseCity,Role;
String PrimarySkill,Remarks;
String strEmpNo = request.getParameter("txtEmpNo");
//System.out.println(strEmpBaseCity);
int EmpNo= Integer.parseInt(strEmpNo);
Date JoiningDate;
int Auth = 1;
try
{

	/*Getting the connection variable from session*/

	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Query = "SELECT * from empmaster where EMPNO="+EmpNo;
	//System.out.println(Query);
	rs = stmt.executeQuery(Query);
		
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}

if(rs!=null)
{
	/*Getting the values from the database*/

	while(rs.next())
	{
	  //System.out.println("----------");
	  EmpName=rs.getString(1);
	  EmpNo=rs.getInt(2);
	  EmpMailId=rs.getString(3);
  	  EmpBaseCity=rs.getString(4);
	  JoiningDate=rs.getDate(5);
	  Role=rs.getString(6);
	  PrimarySkill=rs.getString(7);
	  Remarks=rs.getString(8);

%>
	<!--Displaying the values from database-->
	<H3 CLASS=Top> <%=EmpName%>(<%=EmpNo%>)</H3>
	<TABLE>
	<TR class=row_odd><TD>EMPName</TD><TD><%if(EmpName==null)EmpName="-";%><%=EmpName%></TD>			
	<TR class=row_even><TD>Emp Number</TD><TD><%=EmpNo%></TD>
	<TR class=row_odd><TD>Email ID</TD><TD><%if(EmpMailId==null)EmpMailId="-";%><%=EmpMailId%></TD>
	<TR class=row_even><TD>CurrentLocation</TD><TD><%if(EmpBaseCity==null)EmpBaseCity="-";%><%=EmpBaseCity%></TD>
  	<TR class=row_odd><TD>JoiningDate</TD><TD><%=JoiningDate%></TD>
  	<TR class=row_even><TD>Role</TD><TD><%if(Role==null)Role="-";%><%=Role%></TD>
  	<TR class=row_odd><TD>Primary Skill</TD><TD><%if(PrimarySkill==null)PrimarySkill="-";%><%=PrimarySkill%></TD>
  	<TR class=row_odd><TD>Remarks</TD><TD><%if(Remarks==null)Remarks="-";%><%=Remarks%></TD>
	</TABLE>
<%
	}
	rs.close();
}
else
{
	/*To write to the server if the resultset is null*/
	//System.out.println("Result set is null\n");
}
/* Verifying wether the user is authorised to edit emp details
	if user is authorised he can edit the details if not he can
	only view the details */
Integer EmpAuth = (Integer)session.getAttribute("auth");
	Auth = EmpAuth.intValue();
	//System.out.println(Auth);
		String path="";
		String label="";
		/*If authorised show Edit and Close Buttons */
					label ="Edit";%>
				<TR><TD><INPUT TYPE="button" value="<%=label%>" onclick='editDetails("<%=label%>","<%=EmpNo%>");'></TD>
				<TD><INPUT TYPE="button" value="Close" onclick='window.close()'></TD></TR>

       <%
		
	   
			label = "Close"; %>
<TR><TD><INPUT TYPE="button" value="<%=label%>" onclick='editDetails("<%=label%>","<%=EmpNo%>");'></TD></TR>
			<%
        
     %>
</BODY>
</HTML>

