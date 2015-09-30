 <!--
    File : MadePayment.jsp : Initiates Making payments
		 
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
function Validate()
{
	var PlotNum=0,Ammount=0,PayeeName="";
	var ChequeNo=0;
	var Obj = document.PaymentsForm;
	Ammount = Obj.Ammount.value;
	ChequeNo = Obj.ChequeNo.value;
	PayeeName = Obj.PayeeName.value;
	if(Ammount=="" || Ammount==null) 
	{
		alert("Ammount is mandatory");
		Obj.Ammount.focus();
		return false;
	}
	if(ChequeNo=="" || ChequeNo==null) 
	{
		Obj.ChequeNo.value = 0;
	}
	if(PayeeName=="" || PayeeName==null) 
	{
		alert("Payee Name is mandatory");
		Obj.PayeeName.focus();
		return false;
	}
return true;
}


//-->
</SCRIPT>
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC>
<FORM METHOD=POST ACTION="PayNow.jsp" NAME="PaymentsForm" onsubmit="return Validate()">
<center>
<h3 align=center> Payments</h3>
<BR><BR>
<FONT FACE="Century Gothic">
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
String plNum = request.getParameter("PlotNum")==null ? "0" : request.getParameter("PlotNum");
	PlotNum = Integer.parseInt(plNum);
String sValue = request.getParameter("SaleValue")==null ? "0" : request.getParameter("SaleValue");
	SaleValue = Float.parseFloat(sValue);
String balance = request.getParameter("Balance")==null ? "0" : request.getParameter("Balance");
	Balance = Float.parseFloat(balance);
	
// Retrieving data from html page


try
{
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
	%>Exception --- <%=e%><%
}
%>
<TABLE width=30% align=center>
<title>Details</title>
<TR><TH class=row_title>Plot Number</TH><TH class=row_even><%=PlotNum%></TH></TR>
<TR><TH class=row_title>Sale Value</TH><TH class=row_even ><%=SaleValue%></TH></TR>
<TR><TH class=row_title>Balance</TH><TH  class=row_even><%=Balance%></TH></TR>
		<tr>
			<TH class=row_title>Ammount </TH>
			<td><input type="text" name="Ammount" size=30></td>
		</tr>
		<tr>
			<TH class=row_title>Cheque No. </TH>
			<td><input type="text" name="ChequeNo"  size=30></td>
		</tr>
		<tr>
			<TH class=row_title>Payee Name </TH>
			<td><input type="text" name="PayeeName"  size=30></td>
		</tr>
<TR>
	<TH><input type="submit" Value="Pay Now"></TH>
	<TH><input type="reset" Value="Clear"></TH>
</TR>
<INPUT TYPE=HIDDEN NAME="PlotNum" value="<%=PlotNum%>">
<INPUT TYPE=HIDDEN NAME="SaleValue" value="<%=SaleValue%>">
<INPUT TYPE=HIDDEN NAME="Balance" value="<%=Balance%>">
</TABLE>
</FORM>
</BODY>
</HTML>



<%%>
