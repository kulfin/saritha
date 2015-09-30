 <!--
    File : AddAppartment.jsp
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
<h3 align=Center>Add New Appartment Information</h3>
<BR><BR>
<FONT FACE="Century Gothic">

<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;



int Res=0;
int AppNo=0,NoFlats=0,PlotNo=0;
String sAppNo,sNoFlats,sPlotNo;
String Name="",Address="";
// Retrieving data from html page
sAppNo=request.getParameter("AppNo");
sPlotNo =request.getParameter("PlotNo");
sNoFlats=request.getParameter("NoFlats");
Name = request.getParameter("Name");
Address = request.getParameter("Address");

AppNo = Integer.parseInt(sAppNo);
PlotNo = Integer.parseInt(sPlotNo);
NoFlats = Integer.parseInt(sNoFlats);


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Insert into appartmentmaster values ("+AppNo+","+PlotNo+",\'"+Name+"\',\'"+Address+"\',"+NoFlats+")";
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
			<H3 align="center">Appartment details added successfully </H3>
		<BR>
		<center>
			<A href="AddAppartment.html"> Back </A>
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
		<A href="AddAppartment.html"> Back </A>
	</center>
<%}%>
</BODY>
</HTML>




