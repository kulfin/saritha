
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

<link rel="stylesheet" type="text/css"
	href="../css/Admin/manage_material.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>

<script type="text/javascript" src="../js/Admin/manage_material.js">
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
<body onload="getMaterialGroup('filterOperation')">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Material"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin: 8px 0 0 10px; font-size: 15px; color: #A90B0B;">

<span> Material Group: <select id="filter_material_group_select"
	onchange="getMaterialSubGroup('filterOperation')" style="width: 200px;">
	<option>select</option>
</select> </span> <span style="margin: 0 0 0 200px;"> Material Sub Group: <select
	id="filter_material_sub_group_select" onchange="getMaterial()"
	style="width: 200px;">
	<option>select</option>
</select> </span></div>
</div>

<div id="material_div" style="position: relative; display: none;">
<div id="material_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteMaterial('material_detail_table')"></div>
<div id="add_button"><input style="width: 140px;" type="button"
	value="Add" onclick="showAddDiv()"></div>

<div id="material_add" style="margin-left: 140px;">

<form name="addForm" action="#">
<div style="position: relative; margin-top: 10px; width: 100%; text-align: center; font-weight: bold; font-size: 17px;">
ADD MATERIAL</div>

<table id="material_add_table" cellspacing="4" border="0">
	<tr>
		<td>Group:</td>
		<td><input id="add_material_group_select" type="text" style="width: 150px;height:19px;"></input></td>
		<!--<td><select id="add_material_group_select"
			onchange="getMaterialSubGroup('addOperation')" name="type"
			style="width: 150px; height: 19px;">
			<option>select</option>
		</select></td>
		--><td>Sub Group:</td>
		<td><input type="text" id="add_material_sub_group_select" style="width: 150px;height: 19px;"></input>
		</td>
		<!--<td><select id="add_material_sub_group_select"
			onchange="getMaterialCode('addOperation')" name="type"
			style="width: 150px; height: 19px;">
			<option>select</option>
		</select></td>
	--></tr>

	<tr>
		<td>Serial Number:</td>
		<td><input id="add_material_serial_number_input"
			onchange="validateSerialNumber('addOperation')"
			style="height: 13px; width: 145px;" /></td>
		<td>Material Code:</td>
		<td><input id="add_material_code_input" readonly
			style="height: 13px; width: 145px;" type="text" /></td>
	</tr>


	<tr>
		<td>Thickness:</td>
		<td><input id="add_material_thickness_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="add_material_thickness_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>



	<tr>
		<td>Height:</td>
		<td><input id="add_material_height_input"
			style="height: 13px; width: 100px;" type="text" /><!--
	 Unit:
	 <select id="add_material_height_unit_select" style="width:100px;height:19px;"><option>select</option></select>
	 --></td>
		<td>Width:</td>
		<td><input id="add_material_width_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="add_material_width_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>




	<tr>
		<td>Capacity:</td>
		<td><input id="add_material_capacity_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="add_material_capacity_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>

	<tr>
		<td>Std Ordering Size:</td>
		<td><input id="add_material_std_ordering_size_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="add_material_std_ordering_size_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
		<td>Conversion Size:</td>
		<td><input id="add_material_conversion_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="add_material_conversion_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>
	

	<tr>
		
	</tr>

	<tr>
		<td>Color:</td>
		<td><input id="add_material_color_input"
			style="height: 13px; width: 195px;" type="text" /></td>
		<td>Material Rate:</td>
		<td><input id="add_material_material_rate_input"
			style="height: 13px; width: 195px;" type="text" /></td>
	</tr>

	<br>

	<tr>
		<td>Material Type:</td>
		<td><select id="add_material_material_type_select"
			style="width: 200px; height: 19px;" />
			<option>select</option>
		</select></td>
		
		<td>Material Name:</td>
		<td colspan="3"><input id="add_material_material_name_input"
			readonly style="height: 13px; width: 195px;" type="text" /></td>
		<td><input type="button" value="Generate"
			onclick="getMaterialName('addOperation')"></td>
	</tr>



	<tr>
		<td style="vertical-align: middle;">Storage Specs:</td>
		<td colspan="3"><textarea id="add_material_storage_specs_input"
			rows="2" style="width: 350px; resize: none;"></textarea></td>
	</tr>

	<tr>
		<td style="vertical-align: middle;">Expiry Specs:</td>
		<td colspan="3"><textarea id="add_material_expiry_specs_input"
			rows="2" style="width: 350px; resize: none;"></textarea></td>
	</tr>
</table>



<div style="position: relative; margin: 5px 0 0 0;"><span
	class="material_content" style="margin: 0px 0 0 143px;"><input
	style="width: 200px;" type="button" onclick="return setMaterial()"
	value="SUBMIT"></span> <span class="material_content"
	style="margin: 0px 0 0 30px;"><input style="width: 200px;"
	type="button" onclick="showViewDiv()" value="CANCEL"></span></div>

</form>
</div>


<!-- Material Update Div  -->
<div id="material_update">
<form name="updateForm" action="#">
<div style="position: relative; margin-top: 10px; width: 100%;  text-align: center; font-weight: bold; font-size: 17px;">
	UPDATE MATERIAL
</div>
<input type="hidden" id="update_material_id">
<table id="material_update_table" cellspacing="4" border="0" style="margin-top: 20px;">
	<tr>
		<td>Group:</td>
		<td><select id="update_material_group_select"
			onchange="getMaterialSubGroup('updateOperation')" name="type"
			style="width: 200px; height: 19px;">
			<option>select</option>
		</select></td>
		<td>Sub Group:</td>
		<td><select id="update_material_sub_group_select"
			onchange="getMaterialCode('updateOperation')" name="type"
			style="width: 200px; height: 19px;">
			<option>select</option>
		</select></td>
		
	</tr>

	<!--<tr>
		<td>Sub Group:</td>
		<td><select id="update_material_sub_group_select"
			onchange="getMaterialCode('updateOperation')" name="type"
			style="width: 200px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>

	--><tr>
		<td>Serial Number:</td>
			<td><input id="update_material_serial_number_input" onchange="validateSerialNumber('updateOperation')"
			style="height: 13px; width: 195px;" /></td>
		<td>Material Code:</td>
			<td><input id="update_material_code_input" readonly style="height: 13px; width: 195px;" type="text" /></td>
	</tr>



	<!--<tr>
		<td>Material Code:</td>
		<td><input id="update_material_code_input" readonly
			style="height: 13px; width: 195px;" type="text" /></td>
	</tr>


	--><tr>
		<td>Thickness:</td>
		<td><input id="update_material_thickness_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_thickness_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>



	<tr>
		<td>Height:</td>
		<td><input id="update_material_height_input"
			style="height: 13px; width: 100px;" type="text" /><!--
	 Unit:
	 <select id="update_material_height_unit_select" style="width:100px;height:19px;"><option>select</option></select>
	 --></td>
	 <td>Width:</td>
		<td><input id="update_material_width_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_width_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>

	<!--<tr>
		<td>Width:</td>
		<td><input id="update_material_width_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_width_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>







	--><tr>
		<td>Capacity:</td>
		<td><input id="update_material_capacity_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_capacity_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>

	<tr>
		<td>Std Ordering Size:</td>
		<td><input id="update_material_std_ordering_size_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_std_ordering_size_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
		<td>Conversion Size:</td>
		<td><input id="update_material_conversion_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_conversion_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>

	<!--<tr>
		<td>Conversion Size:</td>
		<td><input id="update_material_conversion_input"
			style="height: 13px; width: 100px;" type="text" /> Unit: <select
			id="update_material_conversion_unit_select"
			style="width: 100px; height: 19px;">
			<option>select</option>
		</select></td>
	</tr>

	--><tr>
		<td>Color:</td>
		<td><input id="update_material_color_input"
			style="height: 13px; width: 195px;" type="text" /></td>
			<td>Material Rate:</td>
		<td><input id="update_material_material_rate_input"
			style="height: 13px; width: 195px;" type="text" /></td>
	</tr>

	<!--<tr>
		<td>Material Rate:</td>
		<td><input id="update_material_material_rate_input"
			style="height: 13px; width: 195px;" type="text" /></td>
	</tr>

	--><tr>
		<td>Material Type:</td>
		<td><select id="update_material_material_type_select"
			style="width: 200px; height: 19px;" />
			<option>select</option>
		</select></td>
		<td>Material Name:</td>
		<td colspan="3"><input id="update_material_material_name_input"
			readonly style="height: 13px; width: 195px;" type="text" /></td>
		<td><input type="button" value="Generate"
			onclick="getMaterialName('updateOperation')"></td>
	</tr>

	<!--<tr>
		<td>Material Name:</td>
		<td colspan="3"><input id="update_material_material_name_input"
			readonly style="height: 13px; width: 350px;" type="text" /></td>
		<td><input type="button" value="Generate"
			onclick="getMaterialName('updateOperation')"></td>
	</tr>



	--><tr>
		<td style="vertical-align: middle;">Storage Specs:</td>
		<td colspan="3"><textarea
			id="update_material_storage_specs_input" rows="2"
			style="width: 350px; resize: none;"></textarea></td>
	</tr>

	<tr>
		<td style="vertical-align: middle;">Expiry Specs:</td>
		<td colspan="3"><textarea id="update_material_expiry_specs_input"
			rows="2" style="width: 350px; resize: none;"></textarea></td>
	</tr>
</table>



<div style="position: relative; margin: 13px 0 0 0;"><span
	class="material_content" style="margin: 0px 0 0 25px;"><input
	style="width: 200px;" type="button" onclick="return updateMaterial()"
	value="UPDATE"></span> <span class="material_content"
	style="margin: 0px 0 0 20px;"><input style="width: 200px;"
	type="button" onclick="showViewDiv()" value="CANCEL"></span></div>

</form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>