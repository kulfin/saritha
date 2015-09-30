 <!--
    File : MadePayment.jsp : Initiates Making payments
		 
-->

<HTML>

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="com.DBConnection"%>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>
<%
int[] PlotNum= new int[500];
float[] SaleValue=new float[500];
float[] Advance=new float[500];
float[] Balance=new float[500];
String InstOpt="",SoldTo="",SalesDate="";
int noRows=0;
%>
<head>
<LINK href="styles.css" type="text/css" rel="stylesheet">
<SCRIPT LANGUAGE="JavaScript">
<!--
function GetValues(RowNum,PlotNum,SaleValue,Balance)
{
	//alert(RowNum+"--"+PlotNum+"--"+SaleValue+"--"+Balance);
	var Frm = document.PaymentsForm;
	Frm.PlotNum.value=PlotNum;
	Frm.SaleValue.value=SaleValue;
	Frm.Balance.value=Balance;
}

//-->
</SCRIPT>
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC>
<FORM METHOD=POST ACTION="PaymentsEntry.jsp" NAME="PaymentsForm">
<center>
<h3 align=center> Payments</h3>
<BR><BR>
<FONT FACE="Century Gothic">
<TABLE CLASS=notebook>
<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
ResultSet rs = null;


// Retrieving data from html page


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Select * from salesmaster where Not (Balance=0) order by PlotNo";
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
		PlotNum[noRows] = rs.getInt(1);
		SaleValue[noRows] = rs.getFloat(2);
		SalesDate = rs.getString(3);
		SoldTo = rs.getString(4);
		Advance[noRows] = rs.getFloat(5);
		Balance[noRows] = rs.getFloat(6);
		InstOpt = rs.getString(7);
		if(noRows==0){
		%>
		<TR class=row_title>
			<TH class=row_title>Select to Pay</TH>
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
			<TH><Input type="Radio" Name="PayNow" onclick="GetValues(<%=noRows%>,<%=PlotNum[noRows]%>,<%=SaleValue[noRows]%>,<%=Balance[noRows]%>)"> </TH>
			<TH ><%=PlotNum[noRows]%></TH>
			<TH ><%=SaleValue[noRows]%></TH>
			<TH ><%=((SalesDate.length()<10)?SalesDate:SalesDate.substring(0,10))%></TH>
			<TH ><%=SoldTo%></TH>
			<TH ><%=Advance[noRows]%></TH>
			<TH ><%=Balance[noRows]%></TH>
			<TH ><%=InstOpt%></TH>
		</TR>
		<%
		noRows++;
	}

System.out.println("==="+noRows);		
}
/* If user provides invalid password or username*/
if(noRows==0)
{
	%><TR><TH>No records found</TH></TR><%
}
else
{
	%><TR><TH><input type="submit" Value="Make Payment"></TH></TR><%
}
%>
</TABLE>
<INPUT TYPE=HIDDEN NAME="PlotNum" value="">
<INPUT TYPE=HIDDEN NAME="SaleValue" value="">
<INPUT TYPE=HIDDEN NAME="Balance" value="">
</FORM>
</BODY>
</HTML>




