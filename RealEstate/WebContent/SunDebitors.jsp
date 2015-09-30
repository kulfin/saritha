 <!--
    File : SunCreditors.jsp : Displays the details of creditors
		 
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
<h3 align=center> Sales Book (Sundry Debitors)</h3>
<BR><BR>
<FONT FACE="Century Gothic">
<TABLE CLASS=notebook>
<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
ResultSet rs = null;

int PlotNum=0;
float SaleValue=0,Advance=0,Balance=0;
String InstOpt="",SoldTo="",SalesDate="";
int noRows=0;

// Retrieving data from html page


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "SELECT * from salesmaster where Balance = 0 order by PlotNo";
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
		SaleValue = rs.getFloat(2);
		SalesDate = rs.getString(3);
		SoldTo = rs.getString(4);
		Advance = rs.getFloat(5);
		Balance = rs.getFloat(6);
		InstOpt = rs.getString(7);
		if(noRows==0){
		%>
		<TR class=row_title>
			<TH class=row_title>Plot Number</TH>
			<TH class=row_title>Sale Value</TH>
			<TH class=row_title>Sold On</TH>
			<TH class=row_title>Slod To</TH>
			<TH class=row_title>Advance</TH>
			<TH class=row_title>Balance</TH>
			<TH class=row_title>Installment Option</TH>
		</TR>
		<%
		}
		%>
		<TR class="<%=((noRows%2)==0 ? "row_odd" : "row_even")%>">
			<TH ><%=PlotNum%></TH>
			<TH ><%=SaleValue%></TH>
			<TH ><%=((SalesDate.length()<10)?SalesDate:SalesDate.substring(0,10))%></TH>
			<TH ><%=SoldTo%></TH>
			<TH ><%=Advance%></TH>
			<TH ><%=Balance%></TH>
			<TH ><%=InstOpt%></TH>
		</TR>
		<%
		noRows++;
	}

System.out.println("==="+noRows);		
}
if(noRows==0)
{%>
	<TR colspan=2><TH> No Records Found </TH><TR>
<%}%>
</TABLE>
</FORM>
</BODY>
</HTML>




