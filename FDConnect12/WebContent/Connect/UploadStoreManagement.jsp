<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	System.out.println("UploadStoreManagement.jsp");
	
%>
<!DOCTYPE html>
<html>
<head>
<!-- Side menu CSS  -->
<link rel="stylesheet" type="text/css" 	href="../css/Connect/horizontal_menu.css" />

<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Connect/project_admin.css" />
<link rel="icon" href="../images/favicon.ico" type="image/x-icon" />
<title>ProTrac</title>

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css"	href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript" src="../css/Common/dropdowntabfiles/dropdowntabs.js">
</script>
<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js">
</script>

</head>
<!------JSP Scripting ------>
<%-- <%
	String username = (String) session.getAttribute("username");
	if (username == null) {
		{
			response.sendRedirect("../Login.jsp");
		}
	}
%>
 --%>
<!-----   Body   ----->
<body  style="position: absolute; height: 110px;">
	<!-- Header -->
	<div id="admindiv">
		<!-- Header -->
			<!--<div id="header-bar">
	 	<img alt="FD INDIA" src="../images/fd.gif" width="20%"> <img
				alt="FD INDIA" src="../images/fd_logo.gif" width="60%"
				style="position: absolute;"> <img alt="FD INDIA"
				src="../images/fd.gif" align="right" width="20%"> 
		</div> -->
		<!-- End Header -->

		<!-- Vertical Menu -->
	 	<div id="bluemenu" class="bluetabs">
			<ul>
				<li><a href="../Common/Home.jsp"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Home</span>
				</a>
				</li>
				<li><a href="#" rel="dropmenu_material"
					style="text-decoration: none; color: rgb(70, 66, 66);"><span>Admin</span>
				</a>
				</li>
				<li><a href="Connect.jsp" rel="dropmenu_place"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Connect</span>
				</a>
				</li>
				<li><a href="../managebom.jsp" rel="dropmenu_company"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>RFPR</span>
				</a>
				</li>
				<li><a href="../Common/Logout.jsp"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Logout</span>
				</a>
				</li>
			</ul>
		</div>
		
		<div id="dropmenu_material" class="dropmenudiv_b">
			<a href="manage_materialgroup.jsp">Material Group</a> 
			<a href="manage_submaterialgroup.jsp">Sub-Material Group</a> 
			<a href="manage_materials.jsp">Materials</a>
		</div>

		<div id="dropmenu_place" class="dropmenudiv_b">
			<a href="CreateProject.jsp">Create Project</a>
			<a href="Connect.jsp">Search Project</a>
			<a href="OpenProject.jsp">Recent Project</a>
				
								
				
		</div>

		<div id="dropmenu_company" class="dropmenudiv_b">
			<a href="manage_companies.jsp">Manage Company</a> <a
				href="manage_brand.jsp">Manage Brand</a> <a href="manage_depot.jsp">Manage
				Depot</a>
		</div>


		<script type="text/javascript">
			//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
			tabdropdown.init("bluemenu");
		</script>

		<!-- End Menu -->

		<!-- body /main content-->
		<div id="maincontent">
			<table style="width: 100%; text-align: center;">
				<tr>
					
			<td width="72%">
				<div id="maincontent_element">
				
	<!-- <form name="myForm"> -->
	
	<h3 style="color: #39939C;">Upload Store Sheet</h3>
	
	<!-- <table align="center" style="border: 1px;">
	<tr>
	<td> File</td> <td><input type="text" id="upload_file" name="upload_file"/></td>
	<td><input type="submit" value="Upload File"></td>
	</tr>
	</table> -->
	
	<form action="UploadStoreSupport.jsp" method="post" name="form1" enctype="multipart/form-data">
		<table align="center">
		
			<!-- <tr>

				<td colspan="2" align="center" style="color: 3399FF;"><B>UPLOAD
				THE FILE</B></td>

		
				<td colspan="2" align="center">&nbsp;</td>
			</tr> -->
			
			<tr>
				<td style="color: brown"><b>CHOOSE FILE TO UPLOAD:</b></td>
			
				<td><INPUT NAME="file" TYPE="file" style="color: #686868; font: bold;"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><INPUT TYPE="submit"
					VALUE="Upload File" style="color: #686868; font: bold;"></td>
			</tr>
			
		</table>
		</form>
		
	
	
	<!-- </form> -->

		</div>
					</td>

				</tr>
			</table>
		</div>
		<!-- End body /main content-->
		<!-- Footer -->
		<div id="footer">
			<font style="font-size: 12px" color="#000">Powered by</font>&nbsp;&nbsp;&nbsp;
			<img alt="Aseema Softnet Technologies" src="../images/a_logo.png"
				height="20px" width="40px" draggable="false" /> <a
				href="http://www.aseema.in"><font color="#A0A0A0"
				style="font-size: 13px; font-weight: bold;"> Aseema Softnet
					Technologies Private Limited</font> </a>
		</div>
	</div>
	<!-- admindiv -->
</body>
</html>