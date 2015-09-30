 <!--
    File : SalesEntry.jsp
	Purpose :To add a new sales entry to the database
			 
-->

<HTML>

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="com.DBConnection"%>
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

String prevStatus="";
int prevPlotNum=0;


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();	
	stmt0 = con.createStatement();
	stmt1 =  con.createStatement();
	String Qry0 ="Select PlotNo,Status from plotdetails where Status Not Like 'Sold' and PlotNo="+PlotNum ;
	rs = stmt0.executeQuery(Qry0);
	if(rs.next())
	{
		prevPlotNum = rs.getInt(1);
		prevStatus = rs.getString(2);
	  if(!(prevStatus.equals("Sold")))
	  {	
		String Qry = "Insert into salesmaster values ("+PlotNum+","+SaleValue+",\'"+SalesDate+"\',\'"+SoldTo+"\',"+Advance+","+Balance+",\'"+InstOpt+"\')";
		System.out.println("Qry-->"+Qry);
		Res = stmt.executeUpdate(Qry);
		String Qry1 = "Update plotdetails set Status=\'Sold\' where PlotNo ="+PlotNum;
		System.out.println("Qry1-->"+Qry1);
		Res1 = stmt1.executeUpdate(Qry1);
	  }
	 }
	 else
	 {
		Res = 2;
	 }
		stmt.close();
		stmt0.close();
		stmt1.close();
	  
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
			<H3 align="center">Entry added successfully </H3>
		<BR>
		<center>
			<A href="SalesEntry.html"> Back </A>
		</center>
<%}
else if(Res==2)
{
%>
		<script>
			for(i=1;i<=10;i++) document.write("<br>");
		</script>
			<H3 align="center">Plot is already Sold</H3>
		<BR>
		<center>
			<A href="SalesEntry.html"> Back </A>
		</center>
<%	
}
else
{%>
	<script>
		for(i=1;i<=6;i++) document.write("<br>");
	</script>
		<H3 align="center">Error in updating..... </H3>
	<BR>
	<center>
		<A href="SalesEntry.html"> Back </A>
	</center>
<%}%>
</BODY>
</HTML>




