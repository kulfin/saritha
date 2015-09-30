<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	System.out.println("ProjectApproval Jsp");
	String id = request.getParameter("id");
	session.setAttribute("project_id",id);
	System.out.println("Project ID"+id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer
			.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	//Pass project ID for Project Approval Details
	String project_approval=services.check_Project_Approval(Integer.parseInt(id));
	System.out.println("check_Project_Approval ---->"+project_approval);
	
	
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
<body style="font: calibri;">
<div id="Projectdata" style="background-color: rgba(204, 204, 204, 0.66);height: 95px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	font-size:15px;
	box-shadow: 0px 0px 4px 3px #cccccc;
	width: 1300px;
	margin-left: 5px;">
		<h2  style="font-weight: bold;color:white;">PROJECT DETAILS</h2>
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


<% if(!project_approval.equals("NO DATA")){ 
	String [] project_approval_data= project_approval.split(Constants.columnSeperator);

%>
		<form name="myApprovalForm_u" id="myApprovalForm_u" style="margin-top: 60px;">
			<h3 style="color: #39939C;">Project Approval</h3>
				<table align="center" style="width: 63%; text-align: center; table-layout: auto;">
									<tr height="8px" style="color: #39939C;">
									
										<td>Project Approved
										</td>
										<td>Project Date
										</td>
										<td>Project Status
										</td>
										<td>Project Reference
										</td>
									</tr>
									
									<tr height="12px">
										<td>CLIENT MANAGER
										</td>
										<td><input type="text" name="client_approval_date" id="client_approval_date" 
										onclick='date_client_approval_date()' size="10" value='<%=project_approval_data[0]%>'
										readonly="readonly">
										</td>
										<td>
										<input type="text" name="status_client_u" value="<%=project_approval_data[4]%>" readonly="readonly">	
								<!-- <select id="status_client_u" name="status_client_u" style="width: 150px;">
									<option value="SELECT">SELECT</option>
									<option value="YES">YES</option>
									<option value="NO">NO</option>
								</select> -->
										</td>
										<td colspan="2">
										<input type="text" name="client_approval_comment_u" id="client_approval_comment_u" 
											size="50" maxlength="50" value="<%=project_approval_data[2]%>" readonly="readonly">
										</td>
									</tr>
									<tr height="12px">
									<td>FD MANAGER
										</td>
										<td><input type="text" name="fd_approval_date" id="fd_approval_date" 
										onclick='date_fd_approval_date()'size="10" value="<%=project_approval_data[1]%>" readonly="readonly">
										</td>
										<td>	
										<input type="text" name="status_fd_u" value="<%=project_approval_data[5]%>" readonly="readonly">
								<!-- <select id="status_fd_u" name="status_fd_u" style="width: 150px;" >
									<option value="SELECT">SELECT</option>
									<option value="YES">YES</option>
									<option value="NO">NO</option>
								</select> -->
										</td>
										<td colspan="2"><input type="text" name="fd_approval_comment_u" id="fd_approval_comment_u"
											size="50" maxlength="50" value="<%=project_approval_data[3]%>" readonly="readonly">
										</td>
									</tr>
									
									<tr>
									<td></td>
									<td></td>
									<td></td>
									
									<td><input type="button" name="Submit" value="Edit Record" onclick="editAppprovalRecord();"></td>
									<td><input type="button" name="Submit" value="Update Record"></td>
									</tr>
									
				</table>
			</form>
		<% }else
			{ 
			System.out.println("NO DATA In Project APPROVAL");
			%>
		<form name="myApprovalForm" id="myApprovalForm" style="margin-top: 60px;">
			<h3 style="color: #39939C;">Project Approval</h3>
				<table align="center" style="width: 63%; text-align: center; table-layout: auto;">
									<tr height="8px" style="color: #39939C;">
									
										<td>Project Approved
										</td>
										<td>Project Date
										</td>
										<td>Project Status
										</td>
										<td>Project Reference
										</td>
									</tr>
									
							<tr height="12px">
										<td>CLIENT MANAGER
										</td>
										<td>
										<input type="text" name="client_approval_date" id="client_approval_date" 
										onclick='date_client_approval_date()' size="20">
										</td>
										<td>	
								<select id="status_client" name="status_client" style="width: 150px;">
									<option value="SELECT">SELECT</option>
									<option value="YES">YES</option>
									<option value="NO">NO</option>
								</select>
										</td>
										<td>
										<input type="text" name="client_approval_comment" id="client_approval_comment" 
											size="50" maxlength="50">
										</td>
									</tr>
									<tr height="12px">
									<td>FD MANAGER
										</td>
										<td><input type="text" name="fd_approval_date" id="fd_approval_date" 
										onclick='date_fd_approval_date()'	size="20">
										</td>
										<td>	
								<select id="status_fd" name="status_fd" style="width: 150px;">
									<option value="SELECT">SELECT</option>
									<option value="YES">YES</option>
									<option value="NO">NO</option>
								</select>
										</td>
										<td><input type="text" name="fd_approval_comment" id="fd_approval_comment"
											size="50" maxlength="50">
										</td>
									</tr>
									
									<tr>
									<td>
									</td>
									<td>
									</td>
									<td>
									</td>
									<td>
									<button name='save_detils' onclick='return save_approvalDetails(<%=id%>);'>Save Details</button>
									</td>
									</tr>
				</table>
		</form>		
		<% } %>
</body>
</html>