<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String id=request.getParameter("id");
System.out.println("project_id"+id);
%>
<%
	System.out.println("Project Document Jsp");
	session.setAttribute("project_id",id);
	System.out.println("Project ID"+id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	//Pass project ID for CRM Details
	String crm_data=services.check_CRM_details(Integer.parseInt(id));
	System.out.println("crm_data---->"+crm_data);
%>
<html>
<head>
<style type="text/css">
#data{
color:#000000;
text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
</head>
<body style="overflow: auto; height: 600px;font: calibri;">

	<div id="Projectdata" style="background-color: rgba(204, 204, 204, 0.66);height: 95px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	font-size:15px;
	box-shadow: 0px 0px 4px 3px #cccccc;
	width: 1300px;
	margin-left: 5px;">
		<h2  style="font-weight: bold;color:white;">PROJECT HEADER</h2>
		<div style="display: inline-table;position: absolute;
			margin-top:-23px;left:100px;
		 color: darkgray;
		 width:400px;height:50px;">
			<table style="width:100%;text-align: left;">
				<tr>
					<td width="110px">Project No</td>
					<td>:</td>
					<td id="data"><%=row_data[0]%></td>
				</tr>
				<tr>
					<td>Project Name</td>
					<td>:</td>
					<td id="data"><%=row_data[1]%></td>
				</tr>
				<tr>
					<td>Client Name</td>
					<td>:</td>
					<td id="data"><%=row_data[2]%></td>
				</tr>
			</table>
		</div>
		<div style="left: 970px;position: absolute;display: inline-table;
		margin-top:-23px;color: darkgray;width:400px;height:50px;">
			<table style="width:100%;text-align:left;">
				<tr>
					<td width="110px">FD Division</td>
					<td>:</td>
					<td id="data"><%=row_data[3]%></td>
				</tr>
				<tr>
					<td>Start Date</td>
					<td>:</td>
					<td id="data"><%=row_data[4]%></td>
				</tr>
				<tr>
					<td>End Date</td>
					<td>:</td>
					<td id="data"><%=row_data[5]%></td>
				</tr>
			</table>
		</div>
			
	</div>		
							
<div id= "subMenu" style="margin-top: 20px; font-size: 11px;">
					
<ul class="menu" id="menu">
	<li><a href="#" onclick="project_details(<%=id%>);" class="submenulink">Project Header</a></li>
    <li><a href="#" onclick="project_document(<%=id%>);" class="submenulink">Document Library</a></li>
    <!--  <li><a href="#" onclick="project_accountablity(<%=id%>);" class="submenulink">Accountability</a></li>  --> 
	<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element & Scope</a></li>
	<!--  <li><a href="#" class="submenulink">Date Plans</a></li>  
	<li><a href="#" class="submenulink">Rate Card</a></li> -->
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a></li>
	<!-- <li><a href="#" class="submenulink">Quantities</a></li>  -->
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
	<!-- <li><a href="#" onclick="project_approval(<%=id%>);" class="submenulink">Approval</a></li> 
	<li><a href="#" class="submenulink" onclick="#">Closure</a></li>  -->
</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>	
</div>	


<form name="myForm" id="myCrmForm">
			<h3 style="color: #39939C;">Project Document</h3>
				<table align="center" style="width: 60%; text-align: center; table-layout: auto;">
									<tr height="8px" style="color: #39939C;">
									
										<td>Document
										</td>
										<td>Expected Date
										</td>
										<td>Project Document Status
										</td>
										<td>Project Reference
										</td>
									</tr>
									
									<tr height="12px">
										<td>CONTACT DETAILS
										</td>
										<td><input type="text" name="crm_name" id="crm_name"
											size="20">
										</td>
										<td>AVAILABLE/UNAVAILABLE
										</td>
										<td><input type="text" name="crm_name" id="crm_name"
											size="20">
										</td>
									</tr>
									
									<tr height="12px">
									<td>DESIGN MANUAL
										</td>
										<td><input type="text" name="crm_name" id="crm_name"
											size="20">
										</td>
										<td>AVAILABLE/UNAVAILABLE
										</td>
										<td><input type="text" name="crm_name" id="crm_name"
											size="20">
										</td>
									</tr>
									<tr height="12px">
									<td>CREATIVE OPEN FILES
										</td>
										<td><input type="text" name="crm_name" id="crm_name"
											size="20">
										</td>
										<td>AVAILABLE/UNAVAILABLE
										</td>
										<td><input type="text" name="crm_name" id="crm_name"
											size="20">
										</td>
									</tr>
				</table>
		</form>

</body>
</html>