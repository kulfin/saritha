<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Admin/manage_associate.css" />
<script type="text/javascript" src="../js/Admin/manage_associate.js"></script>
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<title><%=ApplicationConstants.TITLE %></title>
<!-- Menu Div -->

	<jsp:include page="../Common/Home.jsp">
		<jsp:param name="title" value="Manage Associate" />
	</jsp:include>

<!-- Menu Div -->
<script type="text/javascript">
  // function to only allow numeric key presses in a textbox
// this doesn't stop pasting of non numeric values
function numeric_only( e )
{
// deal with unicode character sets
var unicode = e.charCode ? e.charCode : e.keyCode;

// if the key is backspace, tab, or numeric
if( unicode == 8 || unicode == 9 || ( unicode >= 48 && unicode <= 57 ) )
{
// we allow the key press
return true;
}
else
{
// otherwise we don't
return false;
}
}
    </script>
</head>
<body onload="getAssociates();">
<!-- content View -->
<div id="associate_detail">
	<table id="content_view_table" cellspacing="0"></table>

</div>

<div id="delete_button">
<input style="width: 140px;" type="button"
	value="Delete" onclick="deleteAssociate('content_view_table')" />
</div>

<div id="associate_add">
	<form action="#" id="addAssociate">
		<div style="position: relative; margin: 5px 0 0px 110px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD ASSOCIATE</div>
		<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
			<table id="addTable">
				<tr>
					<td>Associate Name</td>
					<td><input id="AnameId" type="text" maxlength="25"></input></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><textarea id="addressId" cols="16" rows="2"></textarea></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input id="cityId" type="text" maxlength="50"></input></td>
				</tr>
				<tr>
					<td>Email Id</td>
					<td><input id="emailId" type="text" maxlength="50"></input></td>
				</tr>
				<tr>
					<td>Contact Name</td>
					<td><input id="contactName" type="text" maxlength="50"></input></td>
				</tr>
				<tr>
					<td>Contact Phone</td>
					<td><input id="contactPhone" type="text" maxlength="14" onkeypress="return numeric_only(event);"></input></td>
				</tr>
				<tr>
					<td>TIN Number</td>
					<td><input id="tinId" type="text" maxlength="25"></input></td>
				</tr>
				<tr>
					<td>CST Number</td>
					<td><input id="cstId" type="text" maxlength="25"></input></td>
				</tr>
				<tr>
					<td>Excisable</td>
					<td><select id="excisable" style="width: 100px;"><option value="select">select</option><option value="yes">Yes</option><option value="no">No</option></select></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td><input style="width: 140px;" type="button" onclick="return validateAddOperation()" value="SUBMIT" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>

<div id="associate_update">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 110px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE ASSOCIATES</div>
<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
			<table>
				<tr>
					<td>Associate Name</td>
					<td><input id="upAnameId" type="text" maxlength="25"></input></td>
					<td style="visibility: hidden;"><input id="upassociateId" type="text"></input></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><textarea id="upaddressId" cols="16" rows="2"></textarea></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input id="upcityId" type="text" maxlength="50"></input></td>
				</tr>
				<tr>
					<td>Email Id</td>
					<td><input id="upemailId" type="text"></input></td>
				</tr>
				<tr>
					<td>Contact Name</td>
					<td><input id="upcontactName" type="text" maxlength="25"></input></td>
				</tr>
				<tr>
					<td>Contact Phone</td>
					<td><input id="upcontactPhone" type="text" maxlength="14"></input></td>
				</tr>
				<tr>
					<td>TIN Number</td>
					<td><input id="uptinNo" type="text" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>CST Number</td>
					<td><input id="upcstNo" type="text" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>Excisable</td>
					<td><select id="upexcisable" style="width: 100px;">
					<option value="yes">Yes</option>
					<option value="no">No</option>
					</select></td>
				</tr>
				
				<tr>
					<td><input style="width: 140px;" type="button" onclick="return validateUpdateOperation()" value="UPDATE" /></td>
					<td><input style="width: 140px;" type="button" onclick="showAddDiv()" value="CANCEL" /></td>
				</tr>
			</table>
		</div>
</form>
</div>

<!--<div id="capacityDetails" style="margin-top: 200px;">
	<table id="capacity_view_table" cellspacing="0"></table>
</div>
--></body>
</html>