
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

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_fd_org.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_fd_org.js">
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
	<jsp:param name="title" value="Manage FD Org"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">

<div id="fd_org_div" style="position: relative; display: none;">
<div id="fd_org_detail">

</div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteFdOrg('fd_org_detail_table')"></div>

<div id="fd_org_add">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 126px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD
FD ORGANISATION</div>
<br />
<br />
<br />
<br />

<div
	style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
Name:<span style="margin: 0px 0 0px 20px;"><input id="add_fd_org_name_input" name="fd_orgName"
	type="text" size="40" maxlength="25"></span></div>
	
	<div
	style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
Detail:<span style="margin: 0px 0 0px 20px;"><input id="add_fd_org_detail_input"  name="fd_orgDetail"
	type="text" size="40" maxlength="100"></span></div>
	
<div style="position: relative; margin: 60px 0 0 170px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(fd_orgName,fd_orgDetail)" value="SUBMIT" /></div>
</form>





</div>

<div id="fd_org_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 113px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
FD ORGANISATION</div>
<br />
<br />
<br />





 <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
Name:<span style="margin: 0px 0 0px 20px;"><input id="fd_orgName"
	name="fd_orgName" type="text" size="44"></span> <span
	style="margin: 0px 0 0px 0px;"><input id="fd_orgId" name="fd_orgId"
	type="hidden"></span> </span> 
	
	 <span
	style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
Detail:<span style="margin: 0px 0 0px 20px;"><input id="fd_orgDetail"
	name="fd_orgDetail" type="text" size="44"></span>  </span> 
	
	
	<span
	style="position: relative; display: block; margin: 65px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return validateUpdateOperation(fd_orgId,fd_orgName,fd_orgDetail)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button" onclick="showAddDiv()"
	value="CANCEL" /></span></span></form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>