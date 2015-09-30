
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_depot.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Admin/manage_depot.js">
</script>
<title><%=ApplicationConstants.TITLE %></title>
<script>
$(document).ready(function() {
	
 getClient('filter');
 getCountry('filter');
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
	<jsp:param name="title" value="Manage Depo"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="main_content_div">

<!-- Filter Div -->
<div id="filter_div">
<div style="margin:8px 0 0 0;">

<span style="margin:0 0 0 5px;">
Client:<select id="filter_operation_client_select"  style="width:155px; margin:0 0 0 5px;" onchange="getDepot();" >
<option>select</option>
</select>
</span>


<!-- 
<span style="margin:0 0 0 5px;">
Country:<select id="filter_operation_country_select"  style="width:155px; margin:0 0 0 5px;" onchange="getRegion('filter');" >
<option>select</option>
</select>
</span>

<span style="margin:0 0 0 5px;">
Region:<select id="filter_operation_region_select"  style="width:160px; margin:0 0 0 5px;" onchange="getState('filter');" >
<option>select</option>
</select>
</span>

<span style="margin:0 0 0 5px;">
State:<select id="filter_operation_state_select"  style="width:160px; margin:0 0 0 5px;" onchange="getCity('filter');" >
<option>select</option>
</select>
</span>

<span style="margin:0 0 0 5px;">
City:<select id="filter_operation_city_select"  style="width:160px; margin:0 0 0 5px;" onchange="getTown('filter');" >
<option>select</option>
</select>
</span>

<span style="margin:0 0 0 5px;">
Town:<select id="filter_operation_town_select"  style="width:160px; margin:0 0 0 5px;" onchange="getArea('filter');" >
<option>select</option>
</select>
</span>

<span style="margin:0 0 0 5px;">
Area:<select id="filter_operation_area_select"  style="width:160px; margin:0 0 0 5px;" onchange="getDepot();" >
<option>select</option>
</select>
</span> -->

</div>
</div>
<!-- /Filter Div -->

<!-- Content Operation Div -->
<div id="content_operation_div">

<!-- Content Operation Button Div -->
<div id="content_operation_button_div"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteDepot('content_view_table')"></div>
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
<div id="content_add_label_div"><label id="content_add_label">Add Depot</label></div>


<div id="content_add_fields_div">
<table cellspacing="1" id="content_add_fields_table">



<tr>
<td>Country:</td>
<td><select id="filter_operation_country_select"  style="width:155px; margin:0 0 0 5px;" onchange="getRegion('filter');" >
<option>select</option>
</select>
</td>
</tr>

<tr>
<td>Region:</td>
<td><select id="filter_operation_region_select"  style="width:160px; margin:0 0 0 5px;" onchange="getState('filter');" >
<option>select</option>
</select>
</td>
</tr>

<tr>
<td>State:</td>
<td><select id="filter_operation_state_select"  style="width:160px; margin:0 0 0 5px;" onchange="getCity('filter');" >
<option>select</option>
</select>
</td>
</tr>

<tr>
<td>City:</td>
<td><select id="filter_operation_city_select"  style="width:160px; margin:0 0 0 5px;" onchange="getTown('filter');" >
<option>select</option>
</select>
</td>
</tr>

<tr>
<td>Town:</td>
<td><select id="filter_operation_town_select"  style="width:160px; margin:0 0 0 5px;" onchange="getArea('filter');" >
<option>select</option>
</select>
</td>
</tr>

<tr>
<td>Area:</td>
<td><select id="filter_operation_area_select"  style="width:160px; margin:0 0 0 5px;" >
<option>select</option>
</select>
</td>
</tr>



<tr>
<td>Depot Name:</td>
<td><input id="add_operation_depot_name_input" type="text" style="width:225px;" ></td>
</tr>

<tr>
<td>Contact Name:</td>
<td><input id="add_operation_contact_name_input" type="text" style="width:225px;" ></td>
</tr>

<tr>
<td>Contact Phone:</td>
<td><input id="add_operation_contact_phone_input" type="text" style="width:225px;" ></td>
</tr>

<tr>
<td>Land Mark:</td>
<td><textarea  id="add_operation_land_mark_details_input" rows="2" style="width:225px; resize: none;" ></textarea></td>
</tr>

<tr>
<td>Notes:</td>
<td><textarea  id="add_operation_notes_input" rows="2" style="width:225px; resize: none;" ></textarea></td>
</tr>

<tr>
<td>Address:</td>
<td><textarea id="add_operation_address_input"  rows="2"   style="width:225px;  resize: none;" ></textarea></td>
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
<div id="content_update_label_div">Update Depot</div>


<div id="content_update_fields_div">
<table cellspacing="1" id="content_update_fields_table">
<tr>
<td>Country:</td>
<td><select id="update_operation_country_select" onchange="getRegion('update');" style="width:230px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Region:</td>
<td><select id="update_operation_region_select" onchange="getState('update');"  style="width:230px;" ><option>select</option></select></td>
</tr>

<tr>
<td>State:</td>
<td><select id="update_operation_state_select" onchange="getCity('update');" style="width:230px;" ><option>select</option></select></td>
</tr>

<tr>
<td>City:</td>
<td><select id="update_operation_city_select" onchange="getTown('update');"  style="width:230px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Town:</td>
<td><select id="update_operation_town_select" onchange="getArea('update');"  style="width:230px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Area:</td>
<td><select id="update_operation_area_select"  style="width:230px;" ><option>select</option></select></td>
</tr>

<tr>
<td>Depot Name:</td>
<td>
<input id="update_operation_depot_name_input" type="text" style="width:225px;" >
<input type="hidden" id="update_operation_depot_id_input" />
</td>
</tr>

<tr>
<td>Contact Name:</td>
<td><input id="update_operation_contact_name_input" type="text" style="width:225px;" ></td>
</tr>

<tr>
<td>Contact Phone:</td>
<td><input id="update_operation_contact_phone_input" type="text" style="width:225px;" ></td>
</tr>

<tr>
<td>Land Mark:</td>
<td><textarea  id="update_operation_land_mark_details_input" rows="2" style="width:225px; resize: none;" ></textarea></td>
</tr>

<tr>
<td>Notes:</td>
<td><textarea  id="update_operation_notes_input" rows="2" style="width:225px; resize: none;" ></textarea></td>
</tr>

<tr>
<td>Address:</td>
<td><textarea id="update_operation_address_input"  rows="2"   style="width:225px;  resize: none;" ></textarea></td>
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