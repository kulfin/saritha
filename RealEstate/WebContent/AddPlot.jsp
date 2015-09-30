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
<center>
<h3 align=Center>Add New Plot Information</h3>
<BR><BR>
<FONT FACE="Century Gothic">

<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;

int PlotNum=0,OtherExp=0,RoadNum=0,SurNum=0,Extent=0,CostSqYard=0;
String sPlotNum="",sOtherExp="",sRoadNum="",sSurNum="",sExtent="",sCostSqYard="";
String Boundaries="",Facing="",Status="";
int Res=0;

// Retrieving data from html page
sPlotNum=request.getParameter("PlotNum");
sRoadNum =request.getParameter("RoadNum");
sOtherExp=request.getParameter("OtherExp");
sSurNum=request.getParameter("SurNum");
sExtent=request.getParameter("Extent");
sCostSqYard=request.getParameter("CostSqYard");
Boundaries = request.getParameter("Boundaries");
Facing = request.getParameter("Facing");
Status = request.getParameter("Status");

PlotNum = Integer.parseInt(sPlotNum);
RoadNum = Integer.parseInt(sRoadNum);
OtherExp = Integer.parseInt(sOtherExp);
SurNum = Integer.parseInt(sSurNum);
Extent = Integer.parseInt(sExtent);
CostSqYard = Integer.parseInt(sCostSqYard);




try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Insert into plotdetails values ("+PlotNum+","+RoadNum+","+SurNum+","+Extent+","+CostSqYard+","+OtherExp+",\'"+Boundaries+"\',\'"+Status+"\',\'"+Facing+"\')";
	System.out.println("Qry-->"+Qry);
	Res = stmt.executeUpdate(Qry);
}
catch(Exception e)
{
	System.out.println("Exception"+e);
	%>Exception --- <%=e%><%
}
if(Res==1)
{%>
		<script>
			for(i=1;i<=10;i++) document.write("<br>");
		</script>
			<H3 align="center">Plot details added successfully </H3>
		<BR>
		<center>
			<A href="AddPlot.html"> Back </A>
		</center>
<%}
/* If user provides invalid password or username*/
else
{%>
	<script>
		for(i=1;i<=6;i++) document.write("<br>");
	</script>
		<H3 align="center">Error in updating..... </H3>
	<BR>
	<center>
		<A href="AddPlot.html"> Back </A>
	</center>
<%}%>
</BODY>
</HTML>




