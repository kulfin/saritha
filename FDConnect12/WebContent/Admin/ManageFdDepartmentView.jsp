
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_fd_department.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_fd_department.js">
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
<body onload="getFdOrg('filterOperation')">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage FD Department View"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_fd_org"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
FdOrg: <select style="width: 110px;">
	<option>Select</option>
	<option>Iron</option>
	<option>Steel</option>
	<option>Gold</option>
</select> </span> <span id="filter_select_fd_hub"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 280px; font-size: 14px;">
FdHub: <select style="width: 110px;">
	<option>Select</option>

</select> </span> 

<!--<span id="filter_select_fd_division"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 545px; font-size: 14px;">
FdDivision: <select style="width: 110px;">
	<option>Select</option>

</select> </span>  --></div>
</div>
<div id="fd_department_div" style="position: relative; display: none;">
<div id="fd_department_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteFdDepartment('fd_department_detail_table')"></div>

<div id="fd_department_add">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 160px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
DEPARTMENT</div>
<br />
<br />
<br />

<div
	style="position: relative; margin: 10px 0 0px 10px; font-size: 15px; color: rgb(70, 66, 66);">FD Department
Name:<span style="margin: 0px 0 0px 20px;"><input id="add_fd_department_name_input" name="fd_departmentName" style="margin: 0 0 0 0;"
	type="text" size="25" maxlength="25"></span>
</div>
<!--<div style="position: absolute; margin: 10px 0 0px 10px; font-size: 15px; color: rgb(70, 66, 66);">
FD Department Division:
<span id="filter_select_fd_division" style="margin: 10px 0 0 4px;">
 <select style="width: 185px; margin: 0 0 0 4px; ">
	<option>Select</option>

</select> </span>
</div>
	
--><div style="position: relative; margin: 60px 0 0 170px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(fd_departmentName)" value="SUBMIT" /></div>
</form>





</div>

<div id="fd_department_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 145px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
DEPARTMENT</div>
<br />
<br />


<span style="position: relative; display: block; margin: 0px 0 0px 160px; font-size: 15px; color: rgb(70, 66, 66);">
	FdOrg:<span	id="update_select_fd_org" style="margin: 0px 0 0px 10px;">
		<select id="update_fd_org_select">
			<option>select</option>
		</select></span>
<span style="position: relative; display: block; margin: 15px 0 0px 0px; font-size: 15px; color: rgb(70, 66, 66);">
	FdHub:<span id="update_select_fd_hub" style="margin: 0px 0 0px 10px;">
	<select id="update_fd_hub_select">
		<option>select</option>
	</select></span></span></span> 

<!--<span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">FdDivision:<span
	id="update_select_fd_division" style="margin: 0px 0 0px 17px;"><select
	style="width: 120px;" id="update_fd_division_select">
	<option>select</option>
</select></span> </span> 

  --><span
	style="position: relative; display: block; margin: 15px 0 0px 68px; font-size: 15px; color: rgb(70, 66, 66);">FdDepartment
Name:<span style="margin: 0px 0 0px 10px;"><input id="fd_departmentName"
	name="fd_departmentName" type="text" size="25"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="fd_departmentId" name="fd_departmentId"
	type="hidden"></span> </span> 
	
	
	<span
	style="position: relative; display: block; margin: 45px 0 0 90px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(fd_departmentId,fd_departmentName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 35px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>