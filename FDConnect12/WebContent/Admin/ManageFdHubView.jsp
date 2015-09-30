
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_fd_hub.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_fd_hub.js">

</script>
<script type="text/javascript" >
function numeric_only( e ) { // deal with unicode character sets 
	  var unicode = e.charCode ? e.charCode : e.keyCode;

//if the key is backspace, tab, or numeric 
if( unicode == 8 || unicode == 9 || ( unicode >= 48 && unicode <= 57 ) ) { // we allow the key press
	return true; } 
else { // otherwise we don't 
	return false; 
	} 
	}
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
<body onload="getFdOrg('addOperation','1')">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage FD Hub"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="add_select_fd_org"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
FD Organisation: <select style="width: 175px;">
	<option>Select</option>
	<option>FDI</option>

</select> </span>    </div>
</div>
<div id="fd_hub_div" style="position: relative; display: none;">
<div id="fd_hub_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteFdHub('fd_hub_detail_table')"></div>

<div id="fd_hub_add">

<form name="addForm" action="#">
<div style="position: relative; margin: 5px 0 0px 175px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
HUB</div>
<br />
<br />

<span 	style="position: relative; display: block; margin: 0px 0 0px 15px; font-size: 15px; color: rgb(70, 66, 66);">
Country:<span id="add_select_country" style="margin: 0px 0 0px 20px;"><select
	style="width: 120px;" id="add_country_select">
	<option>select</option>
</select></span> <span
	style="position: relative; display: inline; margin: 0px 0 0px 30px; font-size: 15px; color: rgb(70, 66, 66);">
	Region:<span id="add_select_region" style="margin: 0px 0 0px 7px;"><select
	style="width: 125px;" id="add_region_select">
	<option>select</option>
</select></span></span></span> 

<!--<span style="position: relative; display: block; margin: 15px 0 0px 21px; font-size: 15px; color: rgb(70, 66, 66);">
	State:<span id="add_select_state" style="margin: 0px 0 0px 20px;">
	<select style="width: 120px;" id="add_state_select">
		<option>select</option>
	</select></span> 
<span
	style="position: relative; display: inline; margin: 0px 0 0px 60px; font-size: 15px; color: rgb(70, 66, 66);">City:<span
	id="add_select_city" style="margin: 0px 0 0px 33px;"><select
	style="width: 120px;" id="add_city_select">
	<option>select</option>
</select></span></span></span> <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Town:<span
	id="add_select_town" style="margin: 0px 0 0px 19px;"><select
	style="width: 120px;" id="add_town_select">
	<option>select</option>
</select></span> <span
	style="position: relative; margin: 0px 0 0px 59px; font-size: 15px; color: rgb(70, 66, 66);">Area:
	<span id="add_select_area" style="margin: 0px 0 0px 25px;"><select
	style="width: 120px;" id="add_area_select">
	<option>select</option>
</select></span></span></span>
-->
 <span
	style="position: relative; display: block; margin: 15px 0 0px 15px; font-size: 15px; color: rgb(70, 66, 66);">
	Hub Name: <span style="margin: 0px 0 0px 1px;"><input id="add_hub_name_input" name="hubName" type="text" size="14" maxlength="25">
</span>
	
<span style="margin:0 0 0 30px">
Tel No.:<span style="margin: 0px 0 0px 5px;"><input id="add_hub_tel_no_input" style="width: 125px;"
	name="telNo" type="text" onkeypress=" return numeric_only( event )"; maxlength="14"></span>
</span></span>


 <span
	style="position: relative; display: block; margin: 15px 0 0px 15px; font-size: 15px; color: rgb(70, 66, 66);">
	Contact Person: <span style="margin: 0px 0 0px 0px;"><input id="add_hub_contact_person_input"
	name="contactPerson" type="text" size="20" maxlength="25"></span></span>
	
 <span
	style="position: relative; display: block; margin: 15px 0 0px 15px; font-size: 15px; color: rgb(70, 66, 66);">Address:
		<span style="margin: 0px 0 0px 56px;">
		<textarea  id="add_hub_address_input" name="address" rows="2" cols="8" style="margin: 2px;height: 32px;width: 148px; resize:none;" maxlenght="255"></textarea>
		</span>
	</span>

	

	
	
	<span
	style="position: relative; display: block; margin: 10px 0 0 170px;">
<input style="width: 140px;" type="button"
	onclick="return validateAddOperation(hubName,contactPerson,telNo,address)" value="SUBMIT" />
</span></form>







</div>

<div id="fd_hub_update">

<form name="updateForm" action="#">
<div style="position: relative; margin: 5px 0 0px 162px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">
UPDATE HUB</div>
<br />
<input type="hidden" name="hubId" />
<span style="position: relative; display: block; margin: 0px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
Fd Org:<span id="update_select_fd_org" style="margin: 0px 0 0px 0px;">
		<select style="width: 120px;" id="update_country_select">
			<option>select</option>
		</select>
	</span>
</span>
<span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Country:<span
	id="update_select_country" style="margin: 0px 0 0px 0px;"><select
	style="width: 120px;" id="update_country_select">
	<option>select</option>
</select></span> <span
	style="position: relative; display: inline; margin: 0px 0 0px 60px; font-size: 15px; color: rgb(70, 66, 66);">Region:<span
	id="update_select_region" style="margin: 0px 0 0px 11px;"><select
	style="width: 120px;" id="update_region_select">
	<option>select</option>
</select></span></span></span> 

<!--<span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">State:<span
	id="update_select_state" style="margin: 0px 0 0px 17px;"><select
	style="width: 120px;" id="update_state_select">
	<option>select</option>
</select></span> <span
	style="position: relative; display: inline; margin: 0px 0 0px 58px; font-size: 15px; color: rgb(70, 66, 66);">City:<span
	id="update_select_city" style="margin: 0px 0 0px 34px;"><select
	style="width: 120px;" id="update_city_select">
	<option>select</option>
</select></span></span></span> <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Town:<span
	id="update_select_town" style="margin: 0px 0 0px 17px;"><select
	style="width: 120px;" id="update_town_select">
	<option>select</option>
</select></span> <span
	style="position: relative; margin: 0px 0 0px 60px; font-size: 15px; color: rgb(70, 66, 66);">Area:
	<span
	id="update_select_area" style="margin: 0px 0 0px 30px;"><select
	style="width: 120px;" id="update_area_select">
	<option>select</option>
</select></span></span></span>

 --><span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
 		Name:<span style="margin: 0px 0 0px 10px;"><input id="hubName" name="hubName" type="text" size="14"></span>
	
<span style="margin:0 0 0 60px">Tel No.:<span style="margin: 0px 0 0px 10px;">
<input id="telNo"	name="telNo" type="text" size="14"></span>
</span></span>


 <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
 		Contact Person:<span style="margin: 0px 0 0px 0px;"><input id="contactPerson" name="contactPerson" type="text" size="47">
 	</span></span>
	
 <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
 Address: <span style="margin: 0px 0 0px 47px;"><input id="address"
	name="address" type="text" size="47"></span></span>

	<span
	style="position: relative; display: block; margin: 25px 0 0 80px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(hubId,hubName,contactPerson,telNo,address)" value="SUBMIT" />
		<span
	style="margin: 0px 0 0 60px;">
<input style="width: 140px;" type="button"
	onclick="showAddDiv()" value="CANCEL" />
</span>
</span></form>







</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>