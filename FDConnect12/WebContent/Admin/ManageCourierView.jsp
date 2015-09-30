
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_courier.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_courier.js">
</script>
<title><%=ApplicationConstants.TITLE %></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
</script>
<script type="text/javascript">

$(document).ready(function(){
	getCourierData();
	getCountry("filterOperation");
	
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
<body onload="">

<!-- div to include header -->

<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Courier View"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<!-- 
<div id="filt_div">
<div style="margin-left: 45px;">
<span id="filter_select_country"
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

</select> </span> <span id="filter_select_city"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 790px; font-size: 14px;">
City: <select style="width: 110px;">
	<option>Select</option>

</select> </span> </div>
</div> 
 -->

<div id="town_div" style="position: relative; display: block;">
<div id="town_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteTown('town_detail_table')"></div>

<div id="town_add" >

<form action="#">
<div style="position: relative; margin: 5px 0 0px 150px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD COURIER</div>
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 35px; font-size: 15px; color: rgb(70, 66, 66);">	Courier Name:
	<span style="margin: 0px 0 0px 14px;"><input id="addCourierName" name="townName"	type="text" size="25"></span>
  <span style="position: relative; display: block; margin: 15px 0 0px -20px; font-size: 15px; color: rgb(70, 66, 66);">Courier Address:
  	<span style="margin: 0px 0 0px 20px;"><textarea id="courierAddress" name="courierAddress" rows="3" cols="30" ></textarea></span> 
  </span>
  <span style="position: relative; display: block; margin: 15px 0 0px 26px; font-size: 15px; color: rgb(70, 66, 66);">Pin Code:
 		<span style="margin: 0px 0 0px 20px;"><input id="pinCode" name="pinCode" type="text" size="12"></span> 
  </span>
  <span style="position: relative; display: block; margin: 15px 0 0px 10px; font-size: 15px; color: rgb(70, 66, 66);">Tel Number:
  		<span style="margin: 0px 0 0px 20px;"><input id="telNumber" name="telNumber" type="text" size="12"></span> 
  </span>
  <span style="position: relative; display: block; margin: 15px 0 0px 6px; font-size: 15px; color: rgb(70, 66, 66);">FAX Number:
  		<span style="margin: 0px 0 0px 17px;"><input id="faxNumber" name="faxNumber" type="text" size="12"></span> 
  </span>  
  <span style="position: relative; display: block; margin: 15px 0 0px 10px; font-size: 15px; color: rgb(70, 66, 66);">TIN Number:
  		<span style="margin: 0px 0 0px 17px;"><input id="tinNumber" name="tinNumber" type="text" size="12"></span> 
  </span> 
  <span style="position: relative; display: block; margin: 15px 0 0px 4px; font-size: 15px; color: rgb(70, 66, 66);">PAN Number:
  		<span style="margin: 0px 0 0px 16px;"><input id="panNumber" name="panNumber" type="text" size="12"></span> 
  </span> 
  <span style="position: relative; display: block; margin: 15px 0 0px 4px; font-size: 15px; color: rgb(70, 66, 66);">Country:
  		<span style="margin: 0px 0 0px 52px;" id="filter_select_country"><select style="width: 110px;">
											<option>Select</option></select> </span>
  </span> 
   <span style="position: relative; display: block; margin: 15px 0 0px 4px; font-size: 15px; color: rgb(70, 66, 66);">Region:
  		<span style="margin: 0px 0 0px 56px;" id="filter_select_region"><select style="width: 110px;">
												<option>Select</option></select> </span>
  </span> 
   <span style="position: relative; display: block; margin: 15px 0 0px 4px; font-size: 15px; color: rgb(70, 66, 66);">State:
  		<span style="margin: 0px 0 0px 69px;" id="filter_select_state"><select style="width: 110px;"><option>Select</option></select> </span>
  </span> 
   <span style="position: relative; display: block; margin: 15px 0 0px 4px; font-size: 15px; color: rgb(70, 66, 66);">City:
  		<span style="margin: 0px 0 0px 79px;" id="filter_select_city"><select style="width: 110px;">
												<option>Select</option></select></span> 
  </span> 
  
  
</div>
<div style="position: relative; margin: 50px 0 0 140px;">
	<input	style="width: 140px;" type="button" onclick="return validateAddOperation(addCourierName,courierAddress,pinCode,telNumber,faxNumber,tinNumber,panNumber)" value="SUBMIT" /></div>
</form>





</div>

<div id="town_update">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 175px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE COURIER</div>
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

<span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">State:<span
	id="update_select_state" style="margin: 0px 0 0px 17px;"><select style="width: 120px;" id="update_state_select">
	<option>select</option>
</select></span> <span
	style="position: relative; display: inline; margin: 0px 0 0px 58px; font-size: 15px; color: rgb(70, 66, 66);">City:<span
	id="update_select_city" style="margin: 0px 0 0px 34px;"><select
	style="width: 120px;" id="update_city_select">
	<option>select</option>
</select></span></span></span> 

  <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Courier Name:
  		<span style="margin: 0px 0 0px 20px;"><input id="townName" name="townName" type="text" size="45"></span> 
  		<span style="margin: 0px 0 0px 0px;"><input id="townId" name="townId" type="hidden"></span>
  </span> 
  <span style="position: relative; display: block; margin: 15px 0 0px 6px; font-size: 15px; color: rgb(70, 66, 66);">Courier Address:
  		<span style="margin: 0px 0 0px 20px;"><textarea id="upcourierAddress" name="courierAddress" rows="3" cols="30" ></textarea></span> 
  </span>
  <span style="position: relative; display: block; margin: 15px 0 0px 51px; font-size: 15px; color: rgb(70, 66, 66);">Pin Code:
  		<span style="margin: 0px 0 0px 20px;"><input id="uppinCode" name="pinCode" type="text" size="15"></span> 
  </span>
  <span style="position: relative; display: block; margin: 15px 0 0px 34px; font-size: 15px; color: rgb(70, 66, 66);">Tel Number:
  		<span style="margin: 0px 0 0px 20px;"><input id="uptelNumber" name="telNumber" type="text" size="15"></span> 
  </span>
  <span style="position: relative; display: block; margin: 15px 0 0px 30px; font-size: 15px; color: rgb(70, 66, 66);">FAX Number:
  		<span style="margin: 0px 0 0px 17px;"><input id="upfaxNumber" name="faxNumber" type="text" size="15"></span> 
  </span>  
  <span style="position: relative; display: block; margin: 15px 0 0px 34px; font-size: 15px; color: rgb(70, 66, 66);">TIN Number:
  		<span style="margin: 0px 0 0px 18px;"><input id="uptinNumber" name="tinNumber" type="text" size="15"></span> 
  </span> 
  <span style="position: relative; display: block; margin: 15px 0 0px 33px; font-size: 15px; color: rgb(70, 66, 66);">PAN Number:
  		<span style="margin: 0px 0 0px 13px;"><input id="uppanNumber" name="panNumber" type="text" size="15"></span> 
  </span> 
  
	
	
	<span style="position: relative; display: block; margin: 45px 0 0 20px;">
		<input style="width: 140px;" type="button" onclick="return validateUpdateOperation(townId,townName,upcourierAddress,uppinCode,uptelNumber,upfaxNumber,uptinNumber,uppanNumber)" value="UPDATE" />
	<span style="display: inline; margin: 0px 0 0 140px;"> 
		<input style="width: 140px;" type="button" onclick="showAddDiv()" value="CANCEL" /></span></span></form>

</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>