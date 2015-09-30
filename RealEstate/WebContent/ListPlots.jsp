
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>

<HEAD>

	<LINK href="styles.css" type="text/css" rel="stylesheet">

<title>ConsolidatedReport</title>
<SCRIPT LANGUAGE="JavaScript">
<!--
function sendInfo(txtVal)
{
		var txt = window.opener.document.forms(0).PlotNo;
		txt.value = txtVal;
		window.close();
}

//-->
</SCRIPT>
</HEAD>

<body class="SC">

<h2 align=center>Plots List</h2>
<form name=cons>


<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String orderby="PlotNo";
String PlotNo,SurveyNo,Status;
int PhoneNo;
System.out.println("--1---");
orderby=((String)request.getParameter("orderby")==null) ? "PlotNo":(String)request.getParameter("orderby");
System.out.println("--2--"+orderby);

try
{

	/*Getting the connection variable from session*/

	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Query = "SELECT PlotNo,SurveyNo,Status FROM plotdetails where Status='Reserved' order by "+orderby;


	System.out.println("--1--"+Query);
	rs = stmt.executeQuery(Query);
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
	%><%=e%><%
}

%>


<%
if(rs!=null)
{
//System.out.println("Resultset is not null");

%>
	<!--Displaying the table header-->

	<br>
<!--	<DIV STYLE="overflow:scroll;width:650px; height:400px;">-->

	<table class=notebook align=center>

	<tr class=row_title>
        <th class=row_title><a class=title href="ListPlots.jsp?orderby=PlotNo">PlotNo</a></th>
	<th class=row_title><a class=title href="ListPlots.jsp?orderby=SurveyNo">SurveyNo</a></th>
	<th class=row_title><a class=title href="ListPlots.jsp?orderby=Status">Status</a></th>
<%
int DisRow=0;
	/*Getting the values from the database*/

	while(rs.next())
	{
	  PlotNo=rs.getString(1);
	  SurveyNo=rs.getString(2);
	  Status=rs.getString(3);
	DisRow++;
	%>
	<tr class= <%=(DisRow%2!=0)? "row_even" : "row_odd"%>>
		<td><a href="#" onclick="sendInfo('<%=PlotNo%>')"><%=PlotNo%></a></td>
		<td><%=SurveyNo%></td>
		<td><%=Status%></td>
	<%
	}


	rs.close();
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

