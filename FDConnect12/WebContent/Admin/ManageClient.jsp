
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_client.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Admin/manage_client.js">
</script>
<title><%=ApplicationConstants.TITLE %></title>
<script>
$(document).ready(function() {
	  getClient();
	  getCountry('add');
	});
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
<body>

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Client"/>
	</jsp:include>
</div>
<!-- body /main content-->
<div id="main_content_div">

<!-- Content Operation Div -->
<div id="content_operation_div">

<!-- Content Operation Button Div -->
<div id="content_operation_button_div"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteClient('content_view_table')"></div>
<!-- /Content Operation Button Div -->

<!-- Content View Div -->	
<div id="content_view_div">
<table id="content_view_table" cellspacing="0">

</table>
</div>
<!-- /Content View Div -->

<!-- Content Add Div -->
<div id="content_add_div">
<form action="#">
<div id="content_add_label_div"><label id="content_add_label">Add Client</label></div>


<div id="content_add_fields_div">
<table cellspacing="1" id="content_add_fields_table">
<tr>
<td>Country:</td>
<td><select id="add_operation_country_select" onchange="getState('add');" style="width:215px;" ><option>select</option></select></td>
</tr>

<tr>
<td>State:</td>
<td><select id="add_operation_state_select" onchange="getCity('add');" style="width:215px;" ><option>select</option></select></td>
</tr>

<tr>
<td>City:</td>
<td><select id="add_operation_city_select" style="width:215px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Client Name:</td>
<td><input id="add_operation_client_name_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Local Currency:</td>
<td><input id="add_operation_local_currency_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Base Currency:</td>
<td><input id="add_operation_base_currency_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>TIN Number:</td>
<td><input id="add_operation_tin_number_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>CST Number:</td>
<td><input id="add_operation_cst_number_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Pin Code:</td>
<td><input id="add_operation_pin_code_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Address:</td>
<td><textarea id="add_operation_address_input"  rows="2"   style="width:210px;  resize: none;" ></textarea></td>
</tr>



</table>
</div>

<div id="content_add_button_div">
<input style="margin:0 0 0 0px;width:150px;" type="button" value="Submit" onclick="return validateAddOperation()">
</div>
</form>

</div>
<!-- /Content Add Div -->



<!-- Content Update Div -->
<div id="content_update_div">
<form action="#">
<div id="content_update_label_div">Update Client</div>


<div id="content_update_fields_div">
<table cellspacing="1" id="content_update_fields_table">
<tr>
<td>Country:</td>
<td><select id="update_operation_country_select" onchange="getState('update');" style="width:215px;" ><option>select</option></select></td>
</tr>

<tr>
<td>State:</td>
<td><select id="update_operation_state_select" onchange="getCity('update');" style="width:215px;" ><option>select</option></select></td>
</tr>

<tr>
<td>City:</td>
<td><select id="update_operation_city_select" style="width:215px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Client Name:</td>
<td>
<input id="update_operation_client_name_input" type="text" style="width:210px;" >
<input id="update_operation_client_id_input" type="hidden" />
</td>
</tr>

<tr>
<td>Local Currency:</td>
<td><input id="update_operation_local_currency_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Base Currency:</td>
<td><input id="update_operation_base_currency_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>TIN Number:</td>
<td><input id="update_operation_tin_number_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>CST Number:</td>
<td><input id="update_operation_cst_number_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Pin Code:</td>
<td><input id="update_operation_pin_code_input" type="text" style="width:210px;" ></td>
</tr>

<tr>
<td>Address:</td>
<td><textarea id="update_operation_address_input"  rows="2"   style="width:210px;  resize: none;" ></textarea></td>
</tr>



</table>
</div>

<div id="content_update_button_div">
<input style="margin:0 0 0 10px;width:150px;" type="button" value="Submit" onclick="return validateUpdateOperation()">
<input style="margin:0 0 0 60px;width:150px;" type="button" value="Cancel" onclick="return showAddDiv()">
</div>
</form>

</div>
<!-- /Content Update Div -->

</div>
<!-- /Content Operation Div -->

</div>




<!-- End body /main content-->


</body>
</html>