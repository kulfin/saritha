 <!--
    File : PayNow.jsp : Completes Making payments
		 
-->

<HTML>

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="com.DBConnection"%>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>
<head>
<LINK href="styles.css" type="text/css" rel="stylesheet">
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC>
<FORM METHOD=POST ACTION="" NAME="PayNowForm" >
<center>
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
float Ammount=0;
int ChequeNo=0;
String InstOpt="",SoldTo="",SalesDate="";
Date PaymentDate= new Date(System.currentTimeMillis());
int noRows=0;
//System.out.println("--0--");
//Reading Parameters from previous page
String plNum = request.getParameter("PlotNum")==null ? "0" : request.getParameter("PlotNum");
	PlotNum = Integer.parseInt(plNum);
String sValue = request.getParameter("SaleValue")==null ? "0" : request.getParameter("SaleValue");
	SaleValue = Float.parseFloat(sValue);
String balance = request.getParameter("Balance")==null ? "0" : request.getParameter("Balance");
	Balance = Float.parseFloat(balance);
String Ammt = request.getParameter("Ammount")==null ? "0" : request.getParameter("Ammount");
	Ammount = Float.parseFloat(Ammt);
String CheqNo = request.getParameter("ChequeNo")==null ? "0" : request.getParameter("ChequeNo");
	ChequeNo = Integer.parseInt(CheqNo);
String PayeeName = request.getParameter("PayeeName")==null ? "NA" : request.getParameter("PayeeName");
//Reading Current InstallmentNum for particular plot
String QrySelect = "SELECT Max(InstallmentNo) FROM installmentdetails WHERE installmentdetails.PlotNo="+PlotNum;
//System.out.println(QrySelect); 
int InstallMent=0;
String QryUpdate="";
String UpdateSales="";
int val=0,res=0;
// Retrieving data from html page
//System.out.println("--1--");
try
{
	/*Getting the connection variable from session*/
con=DBConnection.getConnection();
	stmt =  con.createStatement();
	rs = stmt.executeQuery(QrySelect);
	if(rs!=null)
	{
		if(rs.next())
		{
			InstallMent=rs.getInt(1);
		}
	}
	else
	{
		InstallMent=0;
	}
	//System.out.println("Installment-->"+InstallMent);
	//Generating New values
	InstallMent++;
	Balance = Balance - Ammount;
	QryUpdate="Insert into installmentdetails(PlotNo,InstallmentNo,Balance,PaymentMade,PaymentDate,ChequeNo,PaymentBy) values("+PlotNum+","+InstallMent+","+Balance+","+Ammount+",\'"+PaymentDate+"\',"+ChequeNo+",\'"+PayeeName+"\')";
	//System.out.println(QryUpdate);
	val = stmt.executeUpdate(QryUpdate);
	if(val==1)
	{
		UpdateSales="Update salesmaster set Balance = "+Balance+" where PlotNo="+PlotNum;
		res=stmt.executeUpdate(UpdateSales);
		//System.out.println("Updated salesmaster -->"+res);
	}
	stmt.close();
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
	%>Exception --- <%=e%><%
}
%>
<%
	if(val==1)
	{
		%>Details Updated Successfully<br><br><%
		%><a href="MadePayment.jsp">Pay Other</a><%
	}
	else
	{
		%>Error in updating...please try again<br><br><%
		%><a href="MadePayment.jsp">Try Again</a><%
	}
%>
</BODY>
</HTML>




