
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_material_group.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_material_group.js">
</script>
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
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
	<jsp:param name="title" value="Manage Material Group"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">

<div id="material_group_div" style="position: relative; display: none;">
<div id="material_group_detail" style="overflow: auto;">

</div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteMaterialGroup('material_group_detail_table')"></div>

<div id="material_group_add">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 130px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
MATERIAL GROUP</div>
<br />
<br />
<br />
<br />

<div
	style="position: relative; margin: 10px 0 0px 15px; font-size: 15px; color: rgb(70, 66, 66);"> Group
Name:<span style="margin: 0px 0 0px 20px;"><input id="add_material_group_name_input" name="material_groupName"
	type="text" size="25" maxlength="40"></span></div>
	
<div
	style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);"> Group
Code:<span style="margin: 0px 0 0px 20px;"><input id="add_material_group_code_input"  name="material_groupCode"
	type="text" size="25" maxlength="40"></span></div>	
<div style="position: relative; margin: 50px 0 0 160px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(material_groupName,material_groupCode)" value="SUBMIT" /></div>
</form>





</div>

<div id="material_group_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 115px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
MATERIAL GROUP</div>
<br />
<br />
<br />
<div
	style="position: relative; margin: 10px 0 0px 90px; font-size: 15px; color: rgb(70, 66, 66);"> Group
Name:<span style="margin: 0px 0 0px 20	px;"><input id="update_material_group_name_input" name="material_groupName"
	type="text" size="25" maxlength="40"></span></div>
	<input id="update_material_group_id_input" name="material_groupId"
	type="hidden">
<div
	style="position: relative; margin: 10px 0 0px 95px; font-size: 15px; color: rgb(70, 66, 66);"> Group
Code:<span style="margin: 0px 0 0px 0px;"><input id="update_material_group_code_input"  name="material_groupCode"
	type="text" size="25"></span></div>	
<div style="position: relative; margin: 40px 0 0 30px;"><input
	style="width: 140px;margin:0 0 0 55px;" type="button"
	onclick="return validateUpdateOperation(material_groupId,material_groupName,material_groupCode)" value="SUBMIT" />
	<span style="display: inline; margin: 0px 0 0 25px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></div>
<!--






 <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">MaterialGroup
Name:<span style="margin: 0px 0 0px 20px;"><input id="update_material_group_name_input"
	name="material_groupName" type="text" size="44"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="material_groupId" name="material_groupId"
	type="hidden"></span> </span> 
	
	
	
	
	<span
	style="position: relative; display: block; margin: 65px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(material_groupId,material_groupName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





--></div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>