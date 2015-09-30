 <!--
    File : ViewPayments.jsp : Displays the payments made 
		 
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
<h3 align=center> All Payments</h3>
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
int InstallmentNo=0,ChequeNo=0;
float Balance=0,Payment=0;
String PaymentBy="";
Date PaymentDate;
int noRows=0;

// Retrieving data from html page


try
{
	/*Getting the connection variable from session*/
con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Select * from installmentdetails order by PlotNo,InstallmentNo";
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
		InstallmentNo = rs.getInt(2);
		Balance = rs.getFloat(3);
		Payment = rs.getFloat(4);
		PaymentDate = rs.getDate(5);
		ChequeNo = rs.getInt(6);
		PaymentBy = rs.getString(7);
		if(noRows==0){
		%>
		<TR class=row_title>
			<TH class=row_title>Plot Number</TH>
			<TH class=row_title>Installment Number</TH>
			<TH class=row_title>Balance</TH>
			<TH class=row_title>Payment Made</TH>
			<TH class=row_title>Payment Date</TH>
			<TH class=row_title>Cheque Number</TH>
			<TH class=row_title>Payee Name</TH>
		</TR>
		<%
		}
		%>
		<TR class="<%=((noRows%2)==0 ? "row_odd" : "row_even")%>">
			<TH ><%=PlotNum%></TH>
			<TH ><%=InstallmentNo%></TH>
			<TH ><%=Balance%></TH>
			<TH ><%=Payment%></TH>
			<TH ><%=PaymentDate%></TH>
			<TH ><%=(ChequeNo==0) ? "<font color='#6633FF'>Cash Payment</font>" : ""+ChequeNo %></TH>
			<TH ><%=PaymentBy%></TH>
		</TR>
		<%
		noRows++;
	}

System.out.println("==="+noRows);		
}
/* If user provides invalid password or username*/
if(noRows==0)
{%>
	<TR><TH>No Records Found</TH></TR>
<%}%>
</TABLE>
</FORM>
</BODY>
</HTML>




