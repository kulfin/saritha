
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
	
	<link rel="stylesheet" type="text/css" href="../css/Admin/manage_material_component.css" />
	<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
	
	<script type="text/javascript" src="../js/Admin/manage_material_component.js">
	</script>
	<title><%=ApplicationConstants.TITLE %></title>
</head>

<body onload="getComponent()">

<!-- div to include header -->
	<div>	
		<jsp:include page="../Common/Home.jsp">
			<jsp:param name="title" value="Manage Material Component View"/>
		</jsp:include>
	</div>

	<div id="maincontent">
		<div id="component_div" style="position: relative; display: block;">
			<div id="component_detail">
			</div>

			<div id="delete_button"><input style="width: 140px;" type="button"
				value="Delete" onclick="deleteComponent('region_detail_table')">
			</div>

			<div id="component_add">
				<form action="#">
					<div style="position: relative; margin: 5px 0 0px 185px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">
						ADD COMPONENT
					</div>
					
					<br />
					<br />
					<br />
					<br />
		
					<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
						Component Name:
						<span style="margin: 0px 0 0px 20px;">
							<input name="componentName" id="componentName" type="text" size="40" maxlength="25">
						</span>
					</div> 
					<div style="position: relative; margin: 60px 0 0 170px;">
						<input style="width: 140px;" type="button" onclick="return validateAddOperation(componentName)" value="SUBMIT" />
					</div>
				</form>
			</div>

			<div id="component_update">			
				<form action="#">
					<div style="position: relative; margin: 5px 0 0px 170px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">
						UPDATE COMPONENT
					</div>
					<br/>
					<br/>
					<br/>
					
					<span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
						Component Name:
						<span style="margin: 0px 0 0px 20px;">
							<input id="componentName1" name="componentName" type="text" size="40">
						</span> 
					<span style="margin: 0px 0 0px 0px;"><input id="componentId" name="componentId" type="hidden"></span> 
				  </span> 
					
					
					<span style="position: relative; display: block; margin: 55px 0 0 20px;">
						<input style="width: 140px;" type="button" onclick="return validateUpdateOperation(componentId,componentName1)" value="UPDATE" />
						<span style="display: inline; margin: 0px 0 0 140px;"> 
							<input 	style="width: 140px;" type="button" onclick="showAddDiv()" 	value="CANCEL" />
						</span>
					</span>
				</form>
			</div>
		</div>
	</div>
</body>
</html>