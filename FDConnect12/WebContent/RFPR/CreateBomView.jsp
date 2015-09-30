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

<link rel="stylesheet" type="text/css" href="../css/RFPR/create_bom.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
<script type="text/javascript" src="../js/RFPR/create_bom.js">
</script>
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />

<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>

<script type="text/javascript" src="../js/Connect/jquery.min.detail.js"></script>

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
<body onload="getClient()">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Create BOM"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div style="margin-left:5px;">
<div id="bom_detail_div">
<div style="position:relative; margin:5px 0 0 0;">
<span id="select_client" style="color: #000000;margin: 0 0 0 10px; font-size: 14px;font-weight:normal;">
Client: <select style="width: 250px; margin:0 0 0 36px;">
<option>Select</option>
</select>
</span> 

<span id="select_Project" style="color: #000000; margin: 0 0 0 135px; font-size: 14px; font-weight:normal;">
Project: <select style="width: 250px; margin:0 0 0 33px;">
<option>Select</option>
</select> 
</span> 

<span id="select_element" style="color: #000000;  margin: 0 0 0 135px; font-size: 14px;font-weight:normal;">
Element: <select style="width: 250px; margin:0 0 0 13px;">
<option>Select</option>
</select>
</span> 

<!--  <span id="select_brand" style="color: #000000;  margin: 0 0 0 150px; font-size: 14px;font-weight:normal;">
Brand: <select style="width: 151px;">
<option>Select</option>
</select>
</span> 

-->
</div>
<div style="position:relative; margin:8px 0 0 0;">


<span id="select_brand" style="color: #000000;  margin: 0 0 0 10px; font-size: 14px;font-weight:normal;">
Bom Code: 
<input type="text" size="36" id="bom_code_input" />
</span> 

<span id="select_brand" style="color: #000000;  margin: 0 0 0 135px; font-size: 14px;font-weight:normal;">
Bom Version: 
<input style=" margin:0 0 0 3px;" type="text" size="36" id="bom_version_input" />
</span> 

<!--<span id="select_brand" style="color: #000000;  margin: 0 0 0 135px; font-size: 14px;font-weight:normal;">
Quantity: 
<input style=" margin:0 0 0 3px;" type="text" size="36" readonly id="quantity_input" />
</span> 
--></div>

<div style="position:relative; margin:8px 0 0 0;">
<span id="select_brand" style="color: #000000;  margin: 0 0 0 10px; font-size: 14px;font-weight:normal;">
Element Type Name: 
<input style=" margin:0 0 0 0px;" type="text" size="32" readonly id="element_name_input" />
</span> 



</div>
</div>
<div id="bom_buttons_div">
<span><INPUT type="button" value="Add Row" onclick="addRow('bom_element_detail_table')" style="width:120px;margin:0 0 0 0;" /></span>
<span><INPUT type="button" value="Delete Row" onclick="deleteRow('bom_element_detail_table')" style="width:120px;margin:0 0 0 20px;" /></span>
<span><INPUT type="button" value="Submit" onclick=" return validate()" style="width:120px;margin:0 0 0 20px;" /></span>
</div>
<div id="bom_element_detail_div">
<table id="bom_element_detail_table" cellspacing="0">

	<tr>

		<th>Select</th>
		<th>Element Section</th>
		<th>Material</th>
		<th>Height</th>
		<th>Unit</th>
		<th>Width</th>
		<th>Unit</th>
		<th>Thickness</th>
		<th>Unit</th>
		<th>Capacity</th>
		<th>Unit</th>
		<th>Quantity</th>
		<th>Order Quantity</th>
		<th>Unit</th>
		<th>Process</th>
		</tr>
		</table>



</div>

</div>
</div>



<!-- End body /main content-->


</body>
</html>