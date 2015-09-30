
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_material_sub_group.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Admin/manage_material_sub_group.js">
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
<body onload="getMaterialGroup('filterOperation',1)">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Material Sub Group"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_material_group"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
Material Group: <select style="width: 110px;">
	<option>Select</option>
	<option>Iron</option>
	<option>Steel</option>
	<option>Gold</option>
</select> </span>    </div>
</div>
<div id="material_sub_group_div" style="position: relative; display: none;">
<div id="material_sub_group_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteMaterialSubGroup('material_sub_group_detail_table')"></div>

<div id="material_sub_group_add">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 110px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
MATERIAL SUB GROUP</div>
<br />
<br />
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 50px; font-size: 15px; color: rgb(70, 66, 66);">Sub Group
Name:<span style="margin: 0px 0 0px 15px;"><input id="add_material_sub_group_name_input" name="material_sub_groupName"
	type="text" size="25" maxlength="25"></span></div>

<div style="position: relative; margin: 10px 0 0px 50px; font-size: 15px; color: rgb(70, 66, 66);">Sub Group
Code:<span style="margin: 0px 0 0px 20px;"><input id="add_material_sub_group_code_input" name="material_sub_groupCode"
	type="text" size="25"></span></div>
		
<div style="position: relative; margin: 60px 0 0 170px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(material_sub_groupName,material_sub_groupCode)" value="SUBMIT" /></div>
</form>





</div>

<div id="material_sub_group_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 90px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
MATERIAL SUB GROUP</div>
<br />
<br />
<br />

<span
	style="position: relative; display: block; margin: 0px 0 0px 80px; font-size: 15px; color: rgb(70, 66, 66);">Material Group:<span
	id="update_select_material_group" style="margin: 0px 0 0px 20px;"><select
	style="width: 180px;" id="update_material-group_select">
	<option>select</option>
</select></span> </span> 



  <span
	style="position: relative; display: block; margin: 15px 0 0px 80px; font-size: 15px; color: rgb(70, 66, 66);">Sub Group
Name:<span style="margin: 0px 0 0px 0px;"><input id="update_material_sub_group_name_input" 
	name="material_sub_groupName" type="text" size="25"></span></span> 
	
  <span
	style="position: relative; display: block; margin: 15px 0 0px 80px; font-size: 15px; color: rgb(70, 66, 66);">Sub Group
Code:<span style="margin: 0px 0 0px 5px;"><input id="update_material_sub_group_code_input" 
	name="material_sub_groupCode" type="text" size="25"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="update_material_sub_group_id_input"  name="material_sub_groupId"
	type="hidden"></span> </span> 	
	
	<span
	style="position: relative; display: block; margin: 40px 0 0 80px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(material_sub_groupId,material_sub_groupName,material_sub_groupCode)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 40px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>