 <!--
    File : AddPlot.jsp
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
function computeTotal()
{
	var FrmObj = document.EPlotForm;
	var CostSqYard = FrmObj.CostSqYard.value;
	var Extent=FrmObj.Extent.value;
	var OtherExp=FrmObj.OtherExp.value;
	var TotalCost = (CostSqYard * Extent) + parseInt(OtherExp);
	FrmObj.TotalCost.value=TotalCost;
}
//-->
</SCRIPT>
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>

<body Class=SC>
<FORM METHOD=POST ACTION="UpdatePlot.jsp" NAME="EPlotForm">
<center>
<h3 align=Center>Edit Plot Information</h3>
<BR><BR>
<TABLE CLASS=notebook>
<!--Declaration of varaibles-->
<%
/*Declaration of variables*/
Connection con=null;
Statement stmt=null;
ResultSet rs = null;

int PlotNum=0,OtherExp=0,RoadNum=0,SurNum=0,Extent=0,CostSqYard=0,TotalCost=0;
String sPlotNum="",sOtherExp="",sRoadNum="",sSurNum="",sExtent="",sCostSqYard="";
String Boundaries="",Facing="",Status="";
int noRows=0;

// Retrieving data from html page
sPlotNum=request.getParameter("PlotNum");
PlotNum = Integer.parseInt(sPlotNum);




try
{
	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();
	String Qry = "Select * from plotdetails where PlotNo = "+PlotNum;
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
	if(rs.next())
	{
		PlotNum = rs.getInt(1);
		RoadNum = rs.getInt(2);
		SurNum = rs.getInt(3);
		Extent = rs.getInt(4);
		CostSqYard = rs.getInt(5);
		OtherExp = rs.getInt(6);
		Boundaries = rs.getString(7);
		Status = rs.getString(8);
		Facing = rs.getString(9);
		TotalCost  = ((CostSqYard * Extent) + OtherExp);
		%>
		<TR><TH class=row_title>Plot Number</TH>
		<td class="row_even">
			<input type="text" name="PlotNum1" DISABLED value= "<%=PlotNum%>">
			<input type="hidden" name="PlotNum"  value= "<%=PlotNum%>">
		</td></TR>
		<TR><TH class=row_title>Road Number</TH>
		<td class="row_even">
			<input type="text" name="RoadNum" value= "<%=RoadNum%>">
		</td></TR>
		<TR><TH class=row_title>Survey Number</TH>
		<td class="row_even">
			<input type="text" name="SurNum" value= "<%=SurNum%>">
		</td></TR>
		<TR><TH class=row_title>Extent</TH>
		<td class="row_even">
			<input type="text" name="Extent" value= "<%=Extent%>" onchange="computeTotal()" >
		</td></TR>
		<TR><TH class=row_title>Cost </TH>
		<td class="row_even">
			<input type="text" name="CostSqYard" value= "<%=CostSqYard%>"  onchange="computeTotal()" >per Sq yard</td></TR>
		<TR><TH class=row_title>Other Expences</TH>
		<td class="row_even">
			<input type="text" name="OtherExp" value= "<%=OtherExp%>"  onchange="computeTotal()" >
		</td></TR>
		<TR><TH class=row_title>Boundaries</TH>
		<td class="row_even">
			<input type="text" name="Boundaries" value= "<%=Boundaries%>">
		</td></TR>
		<TR>
		<TH class=row_title>Facing</TH>
		<td class="row_even">
				<select name="Facing">
					<option value="<%=Facing%>"><%=Facing%></option>
					<option value="East">East</option>
					<option value="West">West</option>
				</select>
		</td>
		</TR>
		<TR><TH class=row_title>Status</TH>
		<td class="row_even">
				<select name="Status">
					<option value="<%=Status%>"><%=Status%></option>
					<option value="Vacant">Vacant</option>
					<option value="Reserved">Reserved</option>
					<option value="Sold">Sold</option>
				</select>
		</td>
		</TR>
		<TR><TH class=row_title>Total Cost</TH>
		<td class="row_even">
			<input type="text" name="TotalCost" DISABLED value=<%=TotalCost%>>
		</td></TR>
		</TR>
		<TR>
		<TH class="row_even"><input type="submit" VALUE="Update"></TH><TH class="row_even"><input type="reset" value="Reset"></TH>
		</TR>
		<%
		noRows++;
	}
	else{
		%>
			<tr><th align=Center>Plot does not exists</th></tr>

		<%	
	}
		
}
/* If user provides invalid password or username*/
else
{%>
	<tr><th align=Center>Plot does not exists</th></tr>
<%}%>

<%%>
</TABLE>
<BR><BR>
<center><A class=title HREF="EditPlot.html"> &lt;&lt; BACK </A></center>
</FORM>
</BODY>
</HTML>




