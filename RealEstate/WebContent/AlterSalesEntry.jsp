 <!--
    File : SalesEntry.jsp
	Purpose :To add a new sales entry to the database
			 
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
<h3 align=Center>Sales Entry</h3>
<BR><BR>
<FONT FACE="Century Gothic">

<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
Statement stmt0=null;
Statement stmt1=null;

ResultSet rs = null;

int PlotNum=0;
float SaleValue=0,Advance=0,Balance=0;
String sPlotNum="",sSaleValue="",sAdvance="",sBalance="";
String InstOpt="",SoldTo="",SalesDate="";


int Res=0,Res1=0;

// Retrieving data from html page
sPlotNum=request.getParameter("PlotNum");
sSaleValue =request.getParameter("SaleValue");
sAdvance=request.getParameter("Advance");
sBalance=request.getParameter("Balance");

SalesDate=request.getParameter("SalesDate");
InstOpt=request.getParameter("InstOpt");
SoldTo = request.getParameter("SoldTo");

PlotNum = Integer.parseInt(sPlotNum);
SaleValue = Float.parseFloat(sSaleValue);
Advance = Float.parseFloat(sAdvance);
Balance = SaleValue - Advance;

try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Update salesmaster set SaleValue="+SaleValue+",DateOfSale=\'"+SalesDate+"\',SoldTo=\'"+SoldTo+"\',Advance="+Advance+",Balance="+Balance+",InstallmentOption=\'"+InstOpt+"\' where PlotNo="+PlotNum;
	System.out.println("Qry-->"+Qry);
	Res = stmt.executeUpdate(Qry);
	stmt.close();
	  
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
			<H3 align="center">Entry Updated successfully </H3>
		<BR>
		<center>
			<A href="AlterSalesEntry.html"> Back </A>
		</center>
<%}
else
{%>
	<script>
		for(i=1;i<=6;i++) document.write("<br>");
	</script>
		<H3 align="center">Error in updating..... </H3>
	<BR>
	<center>
		<A href="AlterSalesEntry.html"> Back </A>
	</center>
<%}%>
</BODY>
</HTML>




