<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>

<%
	System.out.println("Details-Project");
	String id = request.getParameter("id");
	String flag = request.getParameter("flag");
	session.setAttribute("project_id",id);
	System.out.println("Project ID"+id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	

%>
<!DOCTYPE html>
<html>
<head>



<style type="text/css">
#data{
color:#000000;
text-align: left;
}
/*ul.menu .submenulink {
border: 1px solid #aaa;
padding: 5px 7px 7px;
font-weight: bold;
background: url(images/header.gif);
width: 99px;
}*/


</style>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Connect/ProjectHeader.css" />
<link rel="stylesheet" type="text/css" href="../css/Connect/details_project.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css" href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript" src="../css/Common/dropdowntabfiles/dropdowntabs.js"></script>


<script type="text/javascript" src="../js/Connect/connet_js_entire.js"></script>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="../js/Connect/jquery.min.detail.js"></script>

</head>
<!-----   Body   ----->
<body  style="position: absolute; height: 110px;">

<%
	if(flag.equals("true")){
	%>
	<!-- div to include header -->
		<div>	
			<jsp:include page="../Common/Home.jsp">
			<jsp:param name="title" value="Project Details"/>
			</jsp:include>
		</div>
<!-- div to include header -->
	
	
	<%	
	}
	
%>


		<div id="maincontent">
			<table cellspacing="0" cellpadding="0" style="width: 100%; text-align: center;">
				<tr>
						<td width="80%">
					
	<div id="maincontent_element" style="overflow: auto; font: calibri;" >
	
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
      <li><a href="DocumentLibrary.jsp?projectId=<%=id%>" 
		class="submenulink">Document Library</a></li>
    <!-- <li><a href="#" onclick="project_accountablity(<%=id%>);" class="submenulink">Accountability</a></li>  --> 
	<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element & Scope</a></li>
	<!--  <li><a href="ProjectDatePlans.jsp?id=<%=id%>" class="submenulink">Date Plans</a></li>
	<li><a href="#" class="submenulink">Rate Card</a></li>  -->
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a></li>
	<!-- <li><a href="#" class="submenulink">Quantities</a></li>  --> <!-- requirement refer Dev-Review-Report-06-06-13.xls in drop box  -->  
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
	<!-- <li><a href="#" onclick="project_approval(<%=id%>);" class="submenulink">Approval</a></li>  --> 
	
	<!-- <li><a href="#" class="submenulink" onclick="#">Closure</a></li>  -->
</ul> 


<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>	
</div>	
	</div>
</td>
					
				</tr>
			</table>
		</div>
		<!-- End body /main content-->

</body>
</html>