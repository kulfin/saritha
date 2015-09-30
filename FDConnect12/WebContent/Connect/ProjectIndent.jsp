<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	System.out.println("ProjectIndent Jsp");
	String id=request.getParameter("id");
	session.setAttribute("project_id",id);
	System.out.println("Project ID"+id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer
			.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	//Pass project ID for CRM Details
	String crm_data=services.check_CRM_details(Integer.parseInt(id));
	System.out.println("crm_data---->"+crm_data);
%>
<html>
<head>
<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js">
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<form name="myIndentForm" id="myIndentForm">
			<h3 style="color: #39939C;">Project Indent</h3>
				<table align="center" style="width: 52%; text-align: center; table-layout: auto;">
																	
									<tr height="12px">
										<td id="td_id">Project No.
										</td>
										<td><input type="text" name="indent_create_no" id="indent_create_no"
											size="20">
										</td>
										<td id="td_id">Created Date
										</td>
										<td><input type="text" name="indent_create_date" id="indent_create_date" 
										onclick="dateindent();" size="20">
										</td>
									</tr>
					</table>
				<table align="center" style="width: 57%; text-align: center; table-layout: auto;">
		<tr>
			<td id="td_id">Comments</td>
			<td><textarea cols="90" rows="4" id="crm_notes">
				</textarea>
			</td>	
		</tr>
		<tr>
		<td></td>
		<td><input type="button" onclick="" value="Submit" name="Submit"></td>
		</tr>
			</table>
		</form>

</body>
</html>