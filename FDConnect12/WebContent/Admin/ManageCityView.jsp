
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_city.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_city.js">
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
<body onload="getCountry('filterOperation')">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage City"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_country"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
Country: <select style="width: 110px;">
	<option>Select</option>
	<option>Iron</option>
	<option>Steel</option>
	<option>Gold</option>
</select> </span> <span id="filter_select_region"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 280px; font-size: 14px;">
Region: <select style="width: 110px;">
	<option>Select</option>

</select> </span> <span id="filter_select_state"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 545px; font-size: 14px;">
State: <select style="width: 110px;">
	<option>Select</option>

</select> </span>  </div>
</div>
<div id="city_div" style="position: relative; display: none;">
<div id="city_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteCity('city_detail_table')"></div>

<div id="city_add">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 190px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
CITY</div>
<br />
<br />
<br />
<br />

<div
	style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">City
Name:<span style="margin: 0px 0 0px 20px;"><input id="addCityName" name="cityName"
	type="text" size="40" maxlength="25"></span></div>
<div style="position: relative; margin: 60px 0 0 170px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(cityName)" value="SUBMIT" /></div>
</form>





</div>

<div id="city_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 175px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
CITY</div>
<br />
<br />


<span
	style="position: relative; display: block; margin: 0px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Country:<span
	id="update_select_country" style="margin: 0px 0 0px 0px;"><select
	style="width: 120px;" id="update_country_select">
	<option>select</option>
</select></span> <span
	style="position: relative; display: inline; margin: 0px 0 0px 60px; font-size: 15px; color: rgb(70, 66, 66);">Region:<span
	id="update_select_region" style="margin: 0px 0 0px 11px;"><select
	style="width: 120px;" id="update_region_select">
	<option>select</option>
</select></span></span></span> 

<span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">State:<span
	id="update_select_state" style="margin: 0px 0 0px 17px;"><select
	style="width: 120px;" id="update_state_select">
	<option>select</option>
</select></span> </span> 

  <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">City
Name:<span style="margin: 0px 0 0px 20px;"><input id="cityName"
	name="cityName" type="text" size="49"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="cityId" name="cityId"
	type="hidden"></span> </span> 
	
	
	<span
	style="position: relative; display: block; margin: 45px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(cityId,cityName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>