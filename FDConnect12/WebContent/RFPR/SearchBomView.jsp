<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../css/RFPR/search_bom.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />

<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>

<script type="text/javascript" src="../js/Connect/jquery.min.detail.js"></script>
<script type="text/javascript" src="../js/RFPR/search_bom.js">
</script>
</head>
<!------JSP Scripting ------>
<% /*
String username=(String)session.getAttribute("username");
if(username==null){
	{
		response.sendRedirect("login.jsp");
	}
}*/
%>
<!-----   Body   ----->
<body onload="getClientForFilterOperation()">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Search BOM"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div style="margin-left:5px;">
<div id="filt_div">
<div style="position:relative;margin:6px 0 0 0;">
<span id="filter_label"
style="color: #A90B0B; margin: 0px 0 0 5px; font-size: 14px;">
BOM Filter:  </span>
<span id="filter_select_client"
	style="color: #000000;margin: 0 0 0 30px; font-size: 14px;font-weight:normal;">
Client: <select id="filter_client_select"   style="width: 250px;">
	<option>Select</option>
	
</select> </span> <span id="filter_select_Project"
	style="color: #000000; margin: 0 0 0 130px; font-size: 14px; font-weight:normal;">
Project: <select id="filter_Project_select"  style="width: 250px; ">
	<option>Select</option>

</select> </span> <span id="filter_select_element"
	style="color: #000000;  margin: 0 0 0 130px; font-size: 14px;font-weight:normal;">
Element: <select id="filter_element_select" style="width: 250px;">
	<option>Select</option>

</select> </span> 
</div> 
</div>
<div id="search_div">
<form action="#" name="searchBomForm">
<div style="margin:6px 0 0 0; position:relative;">
<span id="filter_label"
style="color: #A90B0B;  margin: 0 0 0 5px; font-size: 14px;">
BOM Search:</span> 
<span 
style="color: #A90B0B;  margin: 0 0 0 21px; font-size: 14px;">
<input type="radio" checked onclick="enableDisableTextFieldsForBomSearch()" name="searchBom" id="search_bom_by_bom_code" value="searchBomByBomCode" />

<span 
style="color: #000000;  margin: 0 0 0 0px; font-size: 14px; font-weight:normal;">
By BOM Code:
</span>
<span 
style="color: #000000;  margin: 0 0 0 05px; font-size: 14px; font-weight:normal;">
<input type="text" name="searchBomByBomCode" size="22" />
</span>
</span>

<span 
style="color: #A90B0B;  margin: 0 0 0 135px; font-size: 14px;">
<input type="radio" onclick="enableDisableTextFieldsForBomSearch()"   name="searchBom" value="searchBomByProjectName" id="search_bom_by_Project_name" />

<span 
style="color: #000000;  margin: 0 0 0 0px; font-size: 14px;font-weight:normal;">
By Project Name:
</span>
<span 
style="color: #000000;  margin: 0 0 0 02px; font-size: 14px; font-weight:normal;">
<input type="text" disabled name="searchBomByProjectName"  size="21"/>
</span>
</span>
<span style="margin: 0 0 0 180px;">
<input type="button" onclick="return getBomBySearchOperation()" style="width:250px;" value="Search">
</span>
 
</div>
</form>
</div>
<div id="bom_div" style="position: relative; display: none;">




</div>

</div>
</div>



<!-- End body /main content-->


</body>
</html>