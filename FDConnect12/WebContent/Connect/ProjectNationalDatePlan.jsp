<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1);

String id=request.getParameter("id");
System.out.println("Project ID in Payment Term Days -- "+id);
%>
<%
	session.setAttribute("project_id",id);
	System.out.println("Project ID"+id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	System.out.println("project_data---->"+header_details);
	//Pass project ID for Payment Details
	String payment_term_data=services.check_TermDays_details(Integer.parseInt(row_data[0]));
	System.out.println("Payment_Term data---->"+payment_term_data);
	String[] payment_term =payment_term_data.split(Constants.columnSeperator);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
#id{
color: #39939C;
}
</style>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
</head>
<body>
		<h3 style="color: #39939C;">Project Details</h3>
							<table id="table_id" align="center"
								style="width: 90%; text-align: center; table-layout: auto;">
								<tr>
									<td width="50" id="td_id">Project No.</td>
									<td width="50"><font size="2"><%=row_data[0]%></font></td>

									<td width="50" id="td_id">Project Name</td>
									<td width="50"><font size="2"><%=row_data[1]%></font></td>

									<td width="50" id="td_id">Client Name</td>
									<td width="50"><font size="2"><%=row_data[2]%></font></td>
								</tr>
								<tr>
									<td width="50" id="td_id">FD
											Division</td>
									<td width="50"><font size="2"><%=row_data[3]%></font></td>

									<td width="50" id="td_id">Start
											Date</td>
									<td width="50"><font size="2"><%=row_data[4]%></font></td>

									<td width="50" id="td_id">End
											Date</td>
									<td width="50"><font size="2"><%=row_data[5]%></font></td>
								</tr>
		</table>
		
<div id= "subMenu" style="margin-top: 20px; font-size: 9px;">
					
<ul class="menu" id="menu">
  	<li><a href="#" onclick="project_details(<%=id%>);" class="submenulink">Project Header</a></li>
    <li><a href="#" onclick="project_document(<%=id%>);" class="submenulink">Document Library</a></li>
    <li><a href="#" onclick="project_accountablity(<%=id%>);" class="submenulink">Accountability</a></li> 
	<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element & Scope</a></li>
	<li><a href="#" class="submenulink">Date Plans</a></li>
	<li><a href="#" class="submenulink">Rate Card</a></li>
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a></li>
	<li><a href="#" class="submenulink">Quantities</a></li>
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
	<li><a href="#" onclick="project_approval(<%=id%>);" class="submenulink">Approval</a></li> 	
	<li><a href="#" class="submenulink" onclick="#">Closure</a></li>
</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>	
</div>	
		
<br>		
		<form name="myForm" id="mytermForm">
			<h3 style="color: #39939C;">National Date Plans</h3>
				<table align="center" style="width: 90%; text-align: center;">
									<tr>
										<td width="10%" id="td_id">Region</td>
										<td><input type="text" name="prjct_approval" id="prjct_approval"
											size="12"></td>
										
										<td width="10%" id="td_id">State</td>
										<td><input type="text" name="prjct_implt" id="prjct_implt"
											size="12"></td>
											
										<td width="10%" id="td_id">Trade</td>
										<td ><input type="text" name="prjct_dispatch" id="prjct_dispatch"
											size="12"></td>
											
										<td width="10%" id="td_id">Chain</td>
										<td><input type="text" name="prjct_docking" id="prjct_docking"
											size="12"></td>
									</tr>
									
									<tr>
									
									<td width="10%" id="td_id">City</td>
										<td><input type="text" name="prjct_approval" id="prjct_approval"
											size="12"></td>
										
										<td width="10%" id="td_id">No of Stores</td>
										<td><input type="text" name="prjct_implt" id="prjct_implt"
											size="12"></td>
											
										<td width="10%" id="td_id">Activity</td>
										<td ><input type="text" name="prjct_dispatch" id="prjct_dispatch"
											size="12"></td>
									</tr>
									<tr></tr>
									<tr>			
										<td width="10%" id="td_id">Planned Date</td>
										<td><input type="text" name="prjct_docking" id="prjct_docking"
											size="12"></td>
											
										<td width="10%" id="td_id">Received Date</td>
										<td><input type="text" name="prjct_docking" id="prjct_docking"
											size="12"></td>
											
										<td width="10%" id="td_id">Planned Start Date</td>
										<td><input type="text" name="prjct_docking" id="prjct_docking"
											size="12"></td>
											
										<td width="10%" id="td_id">Planned End Date</td>
										<td><input type="text" name="prjct_docking" id="prjct_docking"
											size="12"></td>
											
									</tr>
			</table>
			<br>
			<table align="center">
			<tr>
				<td id="td_id">Region</td>
				<td id="td_id">State</td>
				<td id="td_id">Trade</td>
				<td id="td_id">Chain</td>
				<td id="td_id">City</td>
				<td id="td_id">No of Stores</td>
				<td id="td_id">National Date Plan</td>
			</tr>
			</table>
			
			
			
	</form>

</body>
</html>