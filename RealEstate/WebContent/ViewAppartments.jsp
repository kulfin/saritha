 <!--
    File : ViewAppartments.jsp
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
<FORM METHOD=POST ACTION="" NAME="RPlotsForm">
<P align=right><a onclick="javascipt:window.print()" onmouseover="this.style.cursor='hand'" ><img src="Images/printer.gif" width="37" height="38" border=0 alt="Print"></a></P>
<center>
<h3 align=center> Consolidated report of Appartments  </h3>
<BR><BR>
<FONT FACE="Century Gothic">
<TABLE CLASS=notebook>
<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
ResultSet rs = null;

int Res=0;
int AppNo=0,NoFlats=0,PlotNo=0;
String Name="",Address="";
int noRows=0;

// Retrieving data from html page


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Select * from appartmentmaster order by AppNo,PlotNo";
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
		AppNo = rs.getInt(1);
		PlotNo = rs.getInt(2);
		Name = rs.getString(3);
		Address = rs.getString(4);
		NoFlats = rs.getInt(5);
		if(noRows==0){
		%>
		<TR class=row_title>
			<TH class=row_title>Appartment Number</TH>
			<TH class=row_title>Plot Number</TH>
			<TH class=row_title>Name</TH>
			<TH class=row_title>Address</TH>
			<TH class=row_title>Number of Flats</TH>
		</TR>
		<%
		}
		%>
		<TR class="<%=((noRows%2)==0 ? "row_odd" : "row_even")%>">
			<TH ><%=AppNo%></TH>
			<TH ><A HREF="ViewPlot.jsp?PlotNum=<%=PlotNo%>"><%=PlotNo%></A></TH>
			<TH ><%=Name%></TH>
			<TH ><%=Address%></TH>
			<TH ><%=NoFlats%></TH>
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




