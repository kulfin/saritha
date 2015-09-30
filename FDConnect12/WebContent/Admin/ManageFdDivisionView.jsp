
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_fd_division.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_fd_division.js">
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
<body onload="getFdOrg('filterOperation',1)">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage FD Division"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_fd_org"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
FD Org: <select style="width: 110px;">
	<option>Select</option>
	<option>Iron</option>
	<option>Steel</option>
	<option>Gold</option>
</select> </span><!--

 <span id="filter_select_fd_hub"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 280px; font-size: 14px;">
FD Hub: <select style="width: 110px;">
	<option>Select</option>

</select> </span>   --></div>
</div>
<div id="fd_division_div" style="position: relative; display: none;">
<div id="fd_division_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteFdDivision('fd_division_detail_table')"></div>

<div id="fd_division_add">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 183px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
FD Division</div>
<br />
<br />
<br />
<br />

<div
	style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Division
Name:<span style="margin: 0px 0 0px 20px;"><input id="add_fd_division_name_input" name="fd_divisionName"
	type="text" size="30" maxlength="25"></span></div>
<div style="position: relative; margin: 60px 0 0 170px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(fd_divisionName)" value="SUBMIT" /></div>
</form>





</div>

<div id="fd_division_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 168px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
FD DIVISION</div>
<br />
<br />
<br />

<span	style="position: relative; display: block; margin: 0px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
FD Org:<span id="update_select_fd_org" style="margin: 0px 0 0px 60px;">
		<select style="width: 120px;" id="update_fd_org_select">
			<option>select</option>
		</select>
	</span> 

<!--<span
	style="position: relative; display: inline; margin: 0px 0 0px 60px; font-size: 15px; color: rgb(70, 66, 66);">FD Hub:<span
	id="update_select_fd_hub" style="margin: 0px 0 0px 11px;"><select
	style="width: 120px;" id="update_fd_hub_select">
	<option>select</option>
</select></span></span>
--></span> 



  <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">Division
Name:<span style="margin: 0px 0 0px 20px;"><input id="fd_divisionName"
	name="fd_divisionName" type="text" size="21"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="fd_divisionId" name="fd_divisionId"
	type="hidden"></span> </span> 
	
	
	<span
	style="position: relative; display: block; margin: 55px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(fd_divisionId,fd_divisionName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>