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
<FORM METHOD=POST ACTION="" NAME="SPlotsForm">
<center>
<P align=right><a class=title onclick="javascipt:window.print()" onmouseover="this.style.cursor='hand'" ><img src="Images/printer.gif" width="37" height="38" border=0 alt="Print"></a></P>
<h3 align=center> Sold Plots </h3>
<BR><BR>
<FONT FACE="Century Gothic">
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


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Select * from plotdetails where Status = 'Sold' order by PlotNo";
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
	while(rs.next())
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
		if(noRows==0){
		%>
		<TR class=row_title>
			<TH class=row_title>Plot Number</TH>
			<TH class=row_title>Road Number</TH>
			<TH class=row_title>Survey Number</TH>
			<TH class=row_title>Extent</TH>
			<TH class=row_title>Cost per Sq yard</TH>
			<TH class=row_title>Other Expences</TH>
			<TH class=row_title>Boundaries</TH>
			<TH class=row_title>Status</TH>
			<TH class=row_title>Facing</TH>
			<TH class=row_title>Total Cost</TH> <!-- (CostSqYard * Extent) + OtherExp -->
		</TR>
		<%
		}
		%>
		<TR class="<%=((noRows%2)==0 ? "row_odd" : "row_even")%>">
			<TH ><%=PlotNum%></TH>
			<TH ><%=RoadNum%></TH>
			<TH ><%=SurNum%></TH>
			<TH ><%=Extent%></TH>
			<TH ><%=CostSqYard%></TH>
			<TH ><%=OtherExp%></TH>
			<TH ><%=Boundaries%></TH>
			<TH ><%=Status%></TH>
			<TH ><%=Facing%></TH>
			<TH ><%=((CostSqYard * Extent) + OtherExp)%></TH> <!-- (CostSqYard * Extent) + OtherExp -->
		</TR>
		<%
		noRows++;
	}

System.out.println("==="+noRows);		
}
/* If user provides invalid password or username*/
else
{%>
	No records found
<%}%>
</TABLE>
</FORM>
</BODY>
</HTML>




