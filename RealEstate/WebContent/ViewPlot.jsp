 <!--
    File : AddPlot.jsp
	Purpose :To add a new plot to the database
			 
-->

<HTML>

<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>
<head>
<LINK href="styles.css" type="text/css" rel="stylesheet">
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC>
<FORM METHOD=POST ACTION="" NAME="VPlotForm">
<center>
<P align=right><a class=title onclick="javascipt:window.print()" onmouseover="this.style.cursor='hand'" ><img src="Images/printer.gif" width="37" height="38" border=0 alt="Print"></a></P>
<h3 align=Center>Plot Information</h3>
<BR><BR>
<TABLE CLASS=notebook>
<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
ResultSet rs = null;

int PlotNum=0,OtherExp=0,RoadNum=0,SurNum=0,Extent=0,CostSqYard=0;
String sPlotNum="",sOtherExp="",sRoadNum="",sSurNum="",sExtent="",sCostSqYard="";
String Boundaries="",Facing="",Status="";
int noRows=0;

// Retrieving data from html page
sPlotNum=request.getParameter("PlotNum");
PlotNum = Integer.parseInt(sPlotNum);


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Select * from plotdetails where PlotNo = "+PlotNum;
	System.out.println("Qry-->"+Qry);
	rs = stmt.executeQuery(Qry);
}
catch(Exception e)
{
	System.out.println("Exception"+e);
	%>Exception --- <%=e%><%
}
if(rs!=null)
{
	System.out.println("Result set not null");
	if(rs.next())
	{
		PlotNum = rs.getInt(1);
		RoadNum = rs.getInt(2);
		SurNum = rs.getInt(3);
		Extent = rs.getInt(4);
		CostSqYard = rs.getInt(5);
		OtherExp = rs.getInt(6);
		Boundaries = rs.getString(7);
		Status = rs.getString(8);
		Facing = rs.getString(9);	
		%>
		<TR><TH class=row_title>Plot Number</TH><TH class="row_even"><%=PlotNum%></TH></TR>
		<TR><TH class=row_title>Road Number</TH><TH class="row_even"><%=RoadNum%></TH></TR>
		<TR><TH class=row_title>Survey Number</TH><TH class="row_even"><%=SurNum%></TH></TR>
		<TR><TH class=row_title>Extent</TH><TH class="row_even"><%=Extent%></TH></TR>
		<TR><TH class=row_title>Cost per Sq yard</TH><TH class="row_even"><%=CostSqYard%></TH></TR>
		<TR><TH class=row_title>Other Expences</TH><TH class="row_even"><%=OtherExp%></TH></TR>
		<TR><TH class=row_title>Boundaries</TH><TH class="row_even"><%=Boundaries%></TH></TR>
		<TR><TH class=row_title>Facing</TH><TH class="row_even"><%=Facing%></TH></TR>
		<TR><TH class=row_title>Status</TH><TH class="row_even"><%=Status%></TH></TR>
			<!-- TotalCost = (CostSqYard * Extent) + OtherExp -->
		<TR><TH class=row_title>Total Cost</TH><TH class="row_even"><%=((CostSqYard * Extent) + OtherExp)%></TH></TR>
		</TR>
		<%
		noRows++;
	}
	else{
		%>
			<tr><th align=Center>Plot does not exists</th></tr>
		<%	
	}
		
}
/* If user provides invalid password or username*/
else
{%>
	<tr><th align=Center>Plot does not exists</th></tr>
<%}%>
</TABLE>
<BR><BR>
<center><A class=title HREF="ViewPlot.html"> &lt;&lt; BACK </A></center>
</FORM>
</BODY>
</HTML>




