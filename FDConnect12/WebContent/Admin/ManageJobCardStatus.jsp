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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_job_card_status.css" />


<link rel="icon" href="../images/fd_title_logo.jpg" type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Admin/manage_job_card_status.js">
</script>
<!-- <link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />  -->
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
<body onload="getJobCardStatus();getRoles();">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Job Card Status"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="main_content_div">

<!-- Content Operation Div -->
<div id="content_operation_div">

<!-- Content Operation Button Div -->
<div id="content_operation_button_div"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteJobCardStatus('content_view_table')"></div>
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

<div id="content_add_label_div" style="position: relative; margin: 5px 0 0px 178px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">
	<label id="content_add_label">Add Job Card Status</label>
</div>


<div id="content_add_fields_div">
<table cellspacing="0" id="content_add_fields_table">
<tr>
	<td>Status Name:</td>
	<td><input id="add_operation_job_card_status_name_input" type="text" size="20"></td>
</tr>
<tr>
<td>RoleName:</td>
<td>
	<select style="width: 155px;" id="select_role_name">
		<option>select</option>
	</select>
</td>
	
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
<div id="content_update_label_div">Update Job Card Status</div>


<div id="content_update_fields_div">
<table cellspacing="0" id="content_update_fields_table">
<tr>
	<td>Status Name:</td>
	<td>
		<input id="update_operation_job_card_status_name_input" type="text" size="20">
		<input id="update_operation_job_card_status_id_input" type="hidden" >
	</td>
</tr>
<tr>
	<td>RoleName:</td>
	<td>
		<select style="width: 155px;" id="update_operation_job_card_role_id_select">
			<option>select</option>
		</select>
	</td>
</tr>
</table>
</div>

<div id="content_update_button_div">
<input style="margin:0 0 0 10px;width:150px;" type="button" value="Submit" onclick="return validateUpdateOperation()">
<input style="margin:0 0 0 40px;width:150px;" type="button" value="Cancel" onclick="return showAddDiv()">
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