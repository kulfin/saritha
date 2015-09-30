
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_material_employee_status.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_material_employee_type_status.js">
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
	<jsp:param name="title" value="Manage FD Org Employee Type"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">

<div id="country_div" style="position: relative; display: none;">
<div id="role_detail">

</div>

<div id="delete_button"><input style="width: 140px;" type="button" value="Delete" onclick="deleteEmployeeType('employeeType_detail_table')"/>

<div id="role_add">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 150px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD EMPLOYEE TYPE</div>
<br />
<br />
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Employee Type Name:<span style="margin: 0px 0 0px 20px;"> 
	<input id="addemployeeTypeName" name="employeeTypeName" type="text" size="25" maxlength="25"></span></div>
<div style="position: relative; margin: 30px 0 0 175px;">
<input 	style="width: 140px;" type="button" onclick="return validateAddOperation(addemployeeTypeName)" value="SUBMIT" /></div>
</form>





</div>

<div id="role_update">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 145px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE EMPLOYEE TYPE</div>
<br />
<br />
<br />
 
 <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Employee Type Name:
 	<span style="margin: 0px 0 0px 20px;"><input id="employeeTypeName" name="employeeTypeName" type="text" size="35"></span> 
 	<span style="margin: 0px 0 0px 0px;"><input id="employeeTypeId" name="employeeTypeId" type="hidden"></span> 
 </span> 
	
	
	<span style="position: relative; display: block; margin: 65px 0 0 20px;">
		<input style="width: 140px;" type="button" onclick="return validateUpdateOperation(employeeTypeId,employeeTypeName)" value="UPDATE" />
	<span style="display: inline; margin: 0px 0 0 140px;"> 
		<input style="width: 140px;" type="button" onclick="showUpdateDiv()"	value="CANCEL" /></span></span>
</form>

</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>