
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_brand.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Admin/manage_brand.js">
</script>
<title><%=ApplicationConstants.TITLE %></title>
<script>
$(document).ready(function() {
	  getClient('filter');
	  getBrandCategory('add');
	
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
	<jsp:param name="title" value="Manage Brand"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="main_content_div">

<!-- Filter Div -->
<div id="filter_div">
<div style="margin:8px 0 0 0;">
<span style="margin:0 0 0 10px;">
Client:<select id="filter_operation_client_select"  style="width:215px; margin:0 0 0 10px;" onchange="getBrand();" >
<option>select</option>
</select>
</span>

</div>
</div>
<!-- /Filter Div -->

<!-- Content Operation Div -->
<div id="content_operation_div">

<!-- Content Operation Button Div -->
<div id="content_operation_button_div"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteBrand('content_view_table')"></div>
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
<div id="content_add_label_div"><label id="content_add_label">Add Brand</label></div>


<div id="content_add_fields_div">
<table cellspacing="2" id="content_add_fields_table">

<tr>
<td>Brand Category:</td>
<td><select id="add_operation_brand_category_select" style="width:250px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Brand Name:</td>
<td><input id="add_operation_brand_name_input" type="text" style="width:245px;" maxlength="25" ></td>
</tr>

<tr>
<td>Notes:</td>
<td><textarea  id="add_operation_notes_input" rows="3" style="width:245px;resize:none;" maxlength="255" ></textarea></td>
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
<div id="content_update_label_div">Update Brand</div>


<div id="content_update_fields_div">
<table cellspacing="1" id="content_update_fields_table">
<tr>
<td>Client:</td>
<td><select id="update_operation_client_select"  style="width:250px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Brand Category:</td>
<td><select id="update_operation_brand_category_select"  style="width:250px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Brand Name:</td>
<td>
<input id="update_operation_brand_name_input" type="text" style="width:245px;" >
<input id="update_operation_brand_id_input" type="hidden" />
</td>
</tr>

<tr>
<td>Notes:</td>
<td><textarea  id="update_operation_notes_input" rows="3" style="width:245px;resize:none;" ></textarea></td>
</tr>






</table>
</div>

<div id="content_update_button_div">
<input style="margin:0 0 0 10px;width:150px;" type="button" value="Submit" onclick="return validateUpdateOperation()">
<input style="margin:0 0 0 100px;width:150px;" type="button" value="Cancel" onclick="return showAddDiv()">
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