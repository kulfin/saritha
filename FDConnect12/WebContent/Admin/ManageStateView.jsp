
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_state.css" />
<%@page import="com.fd.App.*" %>

 <link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>


<script type="text/javascript" src="../js/Admin/manage_state.js">
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
<body onload="getCountry('filterOperation')">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage State"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_country"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
Country: <select style="width: 110px;">
	<option>NO DATA</option>
	
</select> </span> <span id="filter_select_region"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 280px; font-size: 14px;">
Region: <select style="width: 110px;">
	<option>Select</option>

</select> </span>   </div>
</div>
<div id="state_div" style="position: relative; display: none;">
<div id="state_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteState('state_detail_table')"></div>

<div id="state_add">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 183px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
STATE</div>
<br />
<br />
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	State Name:<span style="margin: 0px 0 0px 20px;"><input id="adstateName" name="stateName" type="text" size="40" maxlength="25"></span>
</div>
<div style="position: relative; margin: 60px 0 0 170px;">
	<input style="width: 140px;" type="button" onclick="return validateAddOperation(stateName)" value="SUBMIT" /></div>
</form>





</div>

<div id="state_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 168px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
STATE</div>
<br />
<br />
<br />

<span
	style="position: relative; display: block; margin: 0px 0 0px 80px; font-size: 15px; color: rgb(70, 66, 66);">Country:<span
	id="update_select_country" style="margin: 0px 0 0px 0px;"><select style="width: 120px;" id="update_country_select">
	<option>select</option>
</select></span> <br/><span
	style="position: relative; display: inline; margin: 0px 0 0px 10px; font-size: 15px; color: rgb(70, 66, 66);">Region:<span
	id="update_select_region" style="margin: 0px 0 0px 0px;"><select style="width: 120px;" id="update_region_select">
	<option>select</option>
</select></span></span></span> 



  <span
	style="position: relative; display: block; margin: 15px 0 0px 50px; font-size: 15px; color: rgb(70, 66, 66);">State
Name:<span style="margin: 0px 0 0px 4px;"><input id="stateName" name="stateName" type="text" size="20"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="stateId" name="stateId"
	type="hidden"></span> </span> 
	
	
	<span
	style="position: relative; display: block; margin: 51px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(stateId,stateName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 25px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>