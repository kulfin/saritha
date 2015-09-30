
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_document_type.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Admin/manage_document_type.js">
</script>
<title><%=ApplicationConstants.TITLE %></title>




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
<body onload="getDocumentType();">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Document Type"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="main_content_div">

<!-- Content Operation Div -->
<div id="content_operation_div">

<!-- Content Operation Button Div -->
<div id="content_operation_button_div"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteDocumentType('content_view_table')"></div>
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
<div id="content_add_label_div"><label id="content_add_label">Add Document Type</label></div>


<div id="content_add_fields_div">
<table cellspacing="0" id="content_add_fields_table">
<tr>
<td>Document Type Name:</td>
<td><input id="add_operation_document_type_name_input" type="text" size="35" maxlength="25"></td>
</tr>

<tr>
<td>Owner:</td>
<td><input id="add_operation_owner_input" type="text" size="35" maxlength="25"></td>
</tr>

<tr>
<td>Description:</td>
<td><textarea id="add_operation_description_input" rows="2" style="width:240px;resize:none;" maxlength="255" ></textarea></td>
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
<div id="content_update_label_div">Update Document Type</div>


<div id="content_update_fields_div">
<table cellspacing="0" id="content_update_fields_table">
<tr>
<td>Document Type Name:</td>
<td>
<input id="update_operation_document_type_name_input" type="text" size="35">
<input id="update_operation_document_type_id_input" type="hidden" >
</td>
</tr>


<tr>
<td>Owner:</td>
<td>
<input id="update_operation_owner_input" type="text" size="35">

</td>
</tr>


<tr>
<td>Description:</td>
<td><textarea id="update_operation_description_input" rows="2" style="width:240px;resize:none;" ></textarea></td>
</tr>

</table>
</div>

<div id="content_update_button_div">
<input style="margin:0 0 0 10px;width:150px;" type="button" value="Submit" onclick="return validateUpdateOperation()">
<input style="margin:0 0 0 150px;width:150px;" type="button" value="Cancel" onclick="return showAddDiv()">
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