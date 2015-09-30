<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
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

<link rel="stylesheet" type="text/css" href="../css/Connect/document_library.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Connect/document_library.js">
</script>

<script>
$(document).ready(function() {
	 // getProject();
	 // getCountry('add');
	});
</script>



</head>
<!------JSP Scripting ------>
<% /*
String username=(String)session.getAttribute("username");
if(username==null){
	{
		response.sendRedirect("login.jsp");
	}
}*/

String id = request.getParameter("projectId");
System.out.println(" Project id is "+id);
//session.setAttribute("project_id",id);
System.out.println("Project ID in ProjectScopeOfWork.jsp---------->"+id);
ProjectServices services = new ProjectServices();
String header_details = services.Project_header_details(Integer.parseInt(id));
String[] row_data = header_details.split(Constants.columnSeperator);
%>

<!-----   Body   ----->
<body>

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Client"/>
	</jsp:include>
</div>
<!-- body /main content-->
<div id="main_content_div">

<!-- Content Operation Div -->
<div id="content_operation_div">

			<div id="Projectdata">				
				<div style="background-color: #CCCCCC; color: darkgray; width: 33%; height: 50px; float: left; margin-top: 3px; margin-bottom: 3px; 
							-webkit-border-top-left-radius: 6px; -webkit-border-bottom-left-radius: 6px; -moz-border-radius-topleft: 6px; -moz-border-radius-bottomleft: 6px; border-top-left-radius: 6px; border-bottom-left-radius: 6px;">
					<table style="font-size: 15px;">
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
					</table>
				</div>
				<div style="background-color: #CCCCCC; color: darkgray;text-align: left; height: 50px; width: 33%; float: left; margin-top: 3px; margin-bottom: 3px;">
					<table style="font-size: 15px;">
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
		
					</table>
				</div>
				<div style="background-color: #CCCCCC; color: darkgray; width: 34%; text-align: left; height: 50px; float: left; margin-top: 3px; margin-bottom: 3px; 
							-webkit-border-top-right-radius: 6px; -webkit-border-bottom-right-radius: 6px; -moz-border-radius-topright: 6px; -moz-border-radius-bottomright: 6px; border-top-right-radius: 6px; border-bottom-right-radius: 6px;" >
					<table style="font-size: 15px;">
						<tr id="client_name">
							<td>Client Name</td>
							<td>:</td>
							<td><%=row_data[2]%></td>
						</tr>
						<tr>
							<td>End Date</td>
							<td>:</td>
							<td id="data"><%=row_data[5]%></td>
						</tr>
					</table>
				</div>
				<div style="clear:left"> </div>
			</div>
			<div id="subMenu" style="margin: 5px 0px 5px 0px; font-size: 11px;height:25px;">
				<ul class="menu" id="menu">
					<li><a href="#" onclick="project_details(<%=id%>);" class="submenulink">Project Header</a></li>			
					      <li><a href="DocumentLibrary.jsp?projectId=<%=id%>" 
		class="submenulink">Document Library</a></li>		
					<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element And Scope</a></li>					
					<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a> </li>
					<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
				</ul>
			</div>
			
			
<!-- File Upload Div -->	

<div id="file_upload_div">
<div style="margin:10px  0 0 0">
<form action="../UploadDocument" method="post" enctype="multipart/form-data" onsubmit="return validateFile()">
<!--<span style="margin:0 0 0 10px">
Project:
<select style="margin:0 0 0 5px;width:200px" id="project_select" name="project_select"  onchange="getBrand()">
<option>
select
</option>
</select>
</span>

-->
<input name="project_select" type="hidden" value="<%=id%>" />

<span style="margin:0 0 0 10px">
Brand:
<select style="margin:0 0 0 5px;width:220px" onchange="getElement()" id="brand_select" name="brand_select">
<option>
select
</option>
</select>
</span>

<span style="margin:0 0 0 20px">
Element:
<select style="margin:0 0 0 5px;width:220px"  id="element_select" name="element_select" >
<option>
select
</option>
</select>
</span>

<span style="margin:0 0 0 20px">
Hub:
<select style="margin:0 0 0 5px;width:175px"  id="hub_select" name="hub_select">
<option>
select
</option>
</select>
</span>

<span style="margin:0 0 0 20px">
Scope Of Work:
<select style="margin:0 0 0 5px;width:220px"  id="sow_select" name="sow_select">
<option>
select
</option>
</select>
</span>

<br/><br/>

<span style="margin:0 0 0 10px">
Document Type:
<select style="margin:0 0 0 5px;width:180px"  id="dt_select" name="dt_select">
<option>
select
</option>
</select>
</span>

<!--<span style="margin:0 0 0 10px">
Division:
<input readonly type="text" style="margin:0 0 0 5px;width:150px;" id="division_input" />
</span>

--><span style="margin:0 0 0 20px">
Comments:
<input  type="text" style="margin:0 0 0 5px;width:300px;" name="comments_input" id="comments_input" />
</span>

<label style="font-weight: bold; margin: 10px 0 0 20px;">
Upload Document</label>  
<input type="file" name="file" size="40" id="file" /> 
<input type="submit" value="Upload File" /></form>
</div>
</div>
<!-- /File Upload Div -->	


<!-- Content View Div -->	
<div id="content_view_div">
<table id="content_view_table" cellspacing="0">

</table>
</div>
<!-- /Content View Div -->

</div>
<!-- /Content Operation Div -->

</div>




<!-- End body /main content-->
<% 
//String projectId=request.getParameter("projectId");

System.out.println("project id is "+id);
out.println("<script>getDocumentLibrary('"+id+"');getBrand('"+id+"');getHub();getSow();getDt();</script>");

%>
</body>
</html>