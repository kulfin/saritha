
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_project_scope.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_project_scope.js">
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
<body onload="getCountry('filterOperation',1)">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Scope View"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_country"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
Department: <select style="width: 110px;">
	<option>Select</option>
	<option>Iron</option>
	<option>Steel</option>
	<option>Gold</option>
</select> </span>    </div>
</div>
<div id="region_div" style="position: relative; display: none;">
<div id="region_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteScope('region_detail_table')"></div>

<div id="region_add">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 185px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD SCOPE</div>
<br />
<br />
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Scope Name:<span style="margin: 0px 0 0px 20px;"><input id="addRegionName" name="regionName" type="text" size="40"></span></div>
<div style="position: relative; margin: 60px 0 0 170px;">
	<input style="width: 140px;" type="button" onclick="return validateAddOperation(regionName)" value="SUBMIT" /></div>
</form>





</div>

<div id="region_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 170px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
SCOPE</div>
<br />
<br />
<br />

<span
	style="position: relative; display: block; margin: 0px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Department:<span
	id="update_select_country" style="margin: 0px 0 0px 28px;"><select
	style="width: 305px;" id="update_country_select">
	<option>select</option>
</select></span> </span> 



  <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Scope Name:<span style="margin: 0px 0 0px 20px;"><input id="regionName"	name="regionName" type="text" size="45"></span> 
	<span style="margin: 0px 0 0px 0px;"><input id="regionId" name="regionId" type="hidden"></span> 
  </span> 
	
	
	<span
	style="position: relative; display: block; margin: 55px 0 0 20px;">
<input style="width: 140px;" type="button" 	onclick="return validateUpdateOperation(regionId,regionName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>