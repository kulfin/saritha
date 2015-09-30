<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>
<%@ page import="com.DBConnection"%>


<html>
<HEAD>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

</HEAD>
<script language="JavaScript" src="Images/date-picker.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function Validate()
{
	var PlotNum=0,SaleValue=0,SalesDate=0,Advance=0,Balance=0;
	var InstOpt="",SoldTo="";
	var Obj = document.SalesForm;
	PlotNum = Obj.PlotNum.value;
	SaleValue = Obj.SaleValue.value;
	SoldTo = Obj.SoldTo.value;
	SalesDate = Obj.SalesDate.value;
	Advance = Obj.Advance.value;
	Balance = Obj.Balance.value;
	InstOpt = Obj.InstOpt.options[Obj.InstOpt.options.selectedIndex].text;
	if(PlotNum=="" || PlotNum==null) 
	{
		alert("Plot number is mandatory");
		Obj.PlotNum.focus();
		return false;
	}
	if(SaleValue=="" || SaleValue==null) 
	{
		alert("Sale Value is mandatory");
		Obj.SaleValue.focus();
		return false;
	}
	if(SalesDate=="" || SalesDate==null) 
	{
		alert("Date is set to Current date");
		dt = new Date();
		y = dt.getFullYear();
		m = dt.getMonth()+1;
		d = dt.getDate();
		m = (m<10)?"0"+m:m;
		d = (d<10)?"0"+d:d;
		Obj.SalesDate.value = y+"-"+m+"-"+d;
	}
	if(SoldTo=="" || SoldTo==null) 
	{
		alert("Sold to is mandatory");
		Obj.SoldTo.focus();
		return false;
	}
	if(Advance=="" || Advance==null) 
	{
		Obj.Advance.value = 0;
	}
	if(Balance=="" || Balance==null) 
	{
		Obj.Balance.value=SaleValue-Advance
	}
	if(InstOpt=="" || InstOpt==null) 
	{
		Obj.InstOpt.value = "No";
	}

return true;
}
function compBalance()
{
	var Obj = document.SalesForm;
	SaleValue = Obj.SaleValue.value;
	Advance = Obj.Advance.value;
	Obj.Balance1.value=SaleValue-Advance;
	Obj.Balance.value=SaleValue-Advance;
}
//-->
</SCRIPT>
<h3 align=Center>Modify Sales Entry</h3>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>

<BODY CLASS=SC>
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;

ResultSet rs = null;

int PlotNum=0;
float SaleValue=0,Advance=0,Balance=0;
String sPlotNum="",sSaleValue="",sAdvance="",sBalance="";
String InstOpt="",SoldTo="",SalesDate="";

int errCode=0;

Balance = SaleValue - Advance;

String prevStatus="";
int prevPlotNum=0;
sPlotNum = (String)request.getParameter("PlotNum");
prevPlotNum = Integer.parseInt(sPlotNum);


try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry ="Select * from salesmaster where PlotNo="+prevPlotNum ;
	rs = stmt.executeQuery(Qry);
	if(rs.next())
	{
		PlotNum = rs.getInt(1);
		SaleValue = rs.getFloat(2);
		SalesDate = rs.getString(3);
		SoldTo = rs.getString(4);
		Advance = rs.getFloat(5);
		Balance = rs.getFloat(6);
		InstOpt = rs.getString(7);
	}
	else
	{
		errCode = 2;//Plot not found

	}
  	stmt.close();
}
catch(Exception e)
{
	System.out.println("Exception"+e);
	%>Exception --- <%=e%><%
}
if(errCode!=2)
{
%>
 <form method=post name="SalesForm" action="AlterSalesEntry.jsp" onsubmit = "return Validate();">
		<table align=center>
		<tr>
			<td>Plot Number </td>
			<td>
			<input type="text" name="PlotNum1" DISABLED VALUE="<%=PlotNum%>">
			<input type="HIDDEN" name="PlotNum" VALUE="<%=PlotNum%>">
			</td>
		</tr>
		<tr>
			<td>Sale Value </td>
			<td><input type="text" name="SaleValue"  VALUE="<%=SaleValue%>" onchange="compBalance()"></td>
		</tr>
		<tr>
			<td>Date of Sale </td>
			<td>
				<input type="text" name="SalesDate"  VALUE="<%=SalesDate%>">
				<a href="javascript:show_calendar('SalesForm.SalesDate');" onmouseover="window.status='Date Picker';return true;" onmouseout="window.status='';return true;" VALUE="SalesForm.datebox.value">
				<img src="Images/show-calendar.gif" width=24 height=22 border=0></a>
			(YYYY-MM-DD)
			</td>
		</tr>
		<tr>
			<td>Sold To </td>
			<td><input type="text" name="SoldTo"  VALUE="<%=SoldTo%>"></td>
		</tr>
		<tr>
			<td>Advance</td>
			<td><input type="text" name="Advance"  VALUE="<%=Advance%>" onchange="compBalance()"></td>
		</tr>
		<tr>
			<td>Balance</td>
			<td>
				<input type="text" name="Balance1"  VALUE="<%=Balance%>" disabled >
				<input type="HIDDEN" name="Balance"  VALUE="<%=Balance%>" >
			</td>
		</tr>
		<tr>
			<td>Installment Option</td>
			<td>
				<select name="InstOpt">
					<option value="<%=InstOpt%>"><%=InstOpt%></option>
					<option value="Yes">Yes</option>
					<option value="No">No</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" Value="Modify Data"></td>
			<td><input type="reset" Value="Clear"></td>
		</tr>
		
		</table>
		
		</form>
<br><br><br>
		<center>
			<A href="AlterSalesEntry.html"> Back </A>
		</center>

<%

}
else 
{
%>
		<script>
			for(i=1;i<=10;i++) document.write("<br>");
		</script>
			<H3 align="center">Plot details does not exist in Sales book </H3>
		<BR>
		<center>
			<A href="AlterSalesEntry.html"> Back </A>
		</center>
<%
}%>
</BODY>

</html>
