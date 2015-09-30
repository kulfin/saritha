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
System.out.println("I am here and safe");
// Retrieving data from html page
sPlotNum=request.getParameter("PlotNum");
sRoadNum =request.getParameter("RoadNum");
sOtherExp=request.getParameter("OtherExp");
sSurNum=request.getParameter("SurNum");
sExtent=request.getParameter("Extent");
sCostSqYard=request.getParameter("CostSqYard");
Boundaries = request.getParameter("Boundaries");
Status = request.getParameter("Status");
Facing = request.getParameter("Facing");

System.out.println(sPlotNum+sRoadNum+sOtherExp);
System.out.println(sSurNum+sExtent+sCostSqYard);
System.out.println(Boundaries+Facing+Status);
PlotNum = Integer.parseInt(sPlotNum);
System.out.println(PlotNum);
RoadNum = Integer.parseInt(sRoadNum);
System.out.println(RoadNum);
OtherExp = Integer.parseInt(sOtherExp);
System.out.println(OtherExp);
SurNum = Integer.parseInt(sSurNum);
System.out.println(SurNum);
Extent = Integer.parseInt(sExtent);
System.out.println(Extent);
CostSqYard = Integer.parseInt(sCostSqYard);
System.out.println(CostSqYard);


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Update plotdetails set RoadNo="+RoadNum+",SurveyNo="+SurNum+",Extent="+Extent+",SqYardCost="+CostSqYard+",OtherExpences="+OtherExp+",Boundaries=\'"+Boundaries+"\',Status=\'"+Status+"\',Facing=\'"+Facing+"\' where PlotNo ="+PlotNum;
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
			<H3 align="center">Plot details updated successfully </H3>
		<BR>
		<center>
			<A href="EditPlot.html"> Back </A>
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
		<A href="EditPlot.html"> Back </A>
	</center>
<%}%>
</BODY>
</HTML>




