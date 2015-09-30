<!--
	 File: Consolidated.jsp
	 Purpose : This jsp reads the data from empmaster table and displays the
			   data on main frame in a specific format.Also opens a popup window if
			   user wants to see or edit the details of particular employee
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>

<HEAD>

	<LINK href="styles.css" type="text/css" rel="stylesheet">

<title>ConsolidatedReprot</title>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

/* Function to show popup window displaying details of Employee based on emp num */
function fnEmpPopUp(strEmpNo)
{ 

var intLeft=0;
var strTitle ='Details';
var strUrl = 'EMPDetails.jsp?txtEmpNo='+strEmpNo;
	intLeft = screen.availWidth/2;
var strOptions= 'menubar=no,toolbar=no,scrollbars=yes,resizable=Yes,height=300,width=500,left=' + (intLeft)  + ',top=0';
fnNewDetailsPopUp(strUrl,strTitle,strOptions);
}	
function fnNewDetailsPopUp(strUrl,strTitle,strOptions)
{
	popupWin = window.open(strUrl,strTitle,strOptions).focus();
}

//-->
</SCRIPT>
</HEAD>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>

<body class="SC">
<P align=right><a class=title onclick="javascipt:window.print()" onmouseover="this.style.cursor='hand'" ><img src="Images/printer.gif" width="37" height="38" border=0 alt="Print"></a></P>
<h2 align=center>Employee List</h2>
<form name=cons>


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
String Domain,PrimarySkill,Remarks;
int EmpNo;
Date JoiningDate;
String orderby=(String)request.getParameter("orderby");
if(orderby==null){
orderby="EmpName";
}
%>

<%

try
{

	/*Getting the connection variable from session*/

	con=DBConnection.getConnection();
//	stmt =  con.createStatement();
	stmt =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
	String Query = "Select * from empmaster order by "+orderby;

	//System.out.println(Query);
	rs = stmt.executeQuery(Query);
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
	%><%=e%><%
}

%>


<%
if(rs.first())
{
//System.out.println("Resultset is not null");
rs.beforeFirst();

%>
	<!--Displaying the table header-->

	<br>
<!--	<DIV STYLE="overflow:scroll;width:650px; height:400px;">-->

	<table class=notebook>

	<tr class=row_title>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=EMPName">EMPName</a></th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=EMPNO">EMPNO</a></th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=MailID">Mail ID</th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=CurrentLocation">CurrentLocation</a></th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=JoiningDate">JoiningDate</a></th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=Role">Role</a></th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=PrimarySkillset">Qualification</a></th>
	<th class=row_title><a class=title href="ConsolidatedSort.jsp?orderby=Remarks">Remarks</a></th>
	</tr>

<%
int DisRow=0;
	/*Getting the values from the database*/

	while(rs.next())
	{
	  EmpName=rs.getString(1);
	  EmpNo=rs.getInt(2);
	  EmpMailId=rs.getString(3);
  	  EmpBaseCity=rs.getString(4);
	  JoiningDate=rs.getDate(5);
	  Role=rs.getString(6);
	  PrimarySkill=rs.getString(7);
	  Remarks=rs.getString(8);
DisRow++;

%>
	<!--Displaying the values from database-->
<%
	if(DisRow%2!=0){
	%><tr class=row_odd><%
	}else{
	%><tr class=row_even><%
	}%>
	<td><%if(EmpName==null||(EmpName.trim()).equals(""))EmpName="-";%>
		<%=EmpName%></td>
	<td><a id="x" href="javascript:fnEmpPopUp(<%=EmpNo%>)" target="_self"><%=EmpNo%>  </a></td>
	<td><%if(EmpMailId==null||(EmpMailId.trim()).equals(""))EmpMailId="-";%><%=EmpMailId%></td>
 	<td><%if(EmpBaseCity==null||(EmpBaseCity.trim()).equals(""))EmpBaseCity="-";
		if((EmpBaseCity.trim()).equals("HYDERABAD"))EmpBaseCity="Hyderabad";
	    if((EmpBaseCity.trim()).equals("BANGALORE"))EmpBaseCity="Bangalore";
	    if((EmpBaseCity.trim()).equals("PUNE"))EmpBaseCity="Pune";%>
	 <%=EmpBaseCity%></td> 
  	 <%
		if(JoiningDate==null){
			%><td>-</td><%
		}else{
		 %><td><%=JoiningDate%></td><%
		}
	 %>	
  	<td>
	<%
		if(Role==null||(Role.trim()).equals(""))	Role="-";
	%><%=Role%></td>
  	<td><%if(PrimarySkill==null||(PrimarySkill.trim()).equals(""))PrimarySkill="-";%><%=PrimarySkill%></td>
	<td><%if(Remarks==null||Remarks.trim().equals(""))Remarks="-";%><%=Remarks%></td> 
</tr>


<%
	}


	rs.close();
%>
<%
}
else
{
	/*To write to the server if the resultset is null*/
	//System.out.println("Result set is null\n");
	%>
		<CENTER>
		<B>No Records found............<B><BR>
		<A href="Report.jsp"> Back </A>
		</CENTER>
	<%

}


%>


</table>
</form>
</BODY>
</HTML>

