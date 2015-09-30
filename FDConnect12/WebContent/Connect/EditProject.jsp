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
System.out.println("EditProject Jsp");
String id=(String)session.getAttribute("project_id");
System.out.println("Project ID"+id);
ProjectServices services = new ProjectServices();
//String header_details = services.project_header_details(Integer.parseInt(id));
//String[] row_data = header_details.split(Constants.columnSeperator);



%>
<!DOCTYPE html>
<html>
<head>
<!-- Side menu CSS  -->
<link rel="stylesheet" type="text/css" 	href="../css/Connect/horizontal_menu.css" />

<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Connect/project_admin.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

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
% --%>>

<!-----   Body   ----->
<body  style="position: absolute; height: 110px;">
	<!-- Header -->
	<div id="admindiv">
		<!-- Header -->
		<div id="header-bar">
			<img alt="FD INDIA" src="../images/fd.gif" width="20%"> <img
				alt="FD INDIA" src="../images/fd_logo.gif" width="60%"
				style="position: absolute;"> <img alt="FD INDIA"
				src="../images/fd.gif" align="right" width="20%">
		</div>
		<!-- End Header -->

		<!-- Vertical Menu -->
	 	<div id="bluemenu" class="bluetabs">
			<ul>
				<li><a href="Home.jsp"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Home</span>
				</a>
				</li>
				<li><a href="#" rel="dropmenu_material"
					style="text-decoration: none; color: rgb(70, 66, 66);"><span>Admin Module</span>
				</a>
				</li>
				<li><a href="Connect.jsp" rel="dropmenu_place"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Connect Module</span>
				</a>
				</li>
				<li><a href="../managebom.jsp" rel="dropmenu_company"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>RFPR Module</span>
				</a>
				</li>
				<li><a href="../Common/Logout.jsp"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Logout</span>
				</a>
				</li>
			</ul>
		</div>


		<!-- End Menu -->

		<!-- body /main content-->
		<div id="maincontent">
			<table style="width: 100%; text-align: center;">
				<tr>
					<td width="16%">
								
						<div id="navContainer">
	<ul>
		<li> <span><a href="OpenProject.jsp">Open Project</a></span></li>
		<br/>
		<li> <span><a href="CreateProject.jsp">New Project</a></span></li>
		<br/>
		<li> <span><a href="OpenProject.jsp">Edit Project</a></span></li>
		<br/>
		<li> <span><a href="#">Recent Projects  </a></span>
		
  		
  		<ul>
		<li> <a href="#" onclick="#">Kolkatta Sweeets</a></li>
		<li> <a href="#" onclick="#">Horlicks India</a></li>
		<li> <a href="#" onclick="#">Cadbury Milk Ltd</a></li>
		</ul> 
		</li>
	</ul>

					</div>
					</td>
					
			<td width="72%">
								<div id="maincontent_element">
				
	<form name="myForm">
	<h2 style="color: #39939C;">Open Project</h2>
	
	<table id="table_id" align="center" style="width: 75%;text-align: center;" >
		<tr>
			<td width="50" id="td_id">
			<font  size="2">Project No.</font>	
			</td>
			
			<td width="50" id="td_id">
			<font  size="2">Project Name</font>			
			</td>
			
			<td width="50" id="td_id">
			<font  size="2">Client Name</font>	
			</td>
			
			<td width="50" id="td_id">
			<font  size="2">FD Division</font>	
			</td>			
		</tr>
		<%
		System.out.println("open_project");
	
		String result=services.Project_open();
		if(result==null){
		%>
		
		<tr>
			<td></td>		
		</tr>
		<tr>
			<td>NO DATA</td>		
		</tr>
		<%
		}else {
			
			String[] row_data=result.split(Constants.rowSeperator);
			for(int i=0;i<row_data.length;i++){
				String[] column_data=row_data[i].split(Constants.columnSeperator);
		%>
		
		<tr>
				
		</tr>
		<tr>
		<td width="50"><a href="#" type="hidden" onclick="open_details(<%=column_data[0]%>)">
			<font  size="2"><%=column_data[0]%></font>	
		</a>
		</td>	
		
		<td width="50">
			<font  size="2"><%=column_data[1]%></font>	
		</td>	
			
		<td width="50">
			<font  size="2"><%=column_data[2]%></font>	
		</td>
		
		<td width="50">
			<font  size="2"><%=column_data[3]%></font>	
		</td>	
						
		</tr>
		<%
			}
		}
		%>
		
	</table>
	
</form>

		</div>
					</td>
					<td width="12%">
		<!-- 				<div id="task">
						<font style="color: #39939C;font: bold;">Tasks :-</font>
									<br> 
						- Create New Project
						</div> -->
						
											<div id="navContainer">
		<ul>
		<li> <span><a href="#">Tasks </a></span>
			
		</li>
		</ul>	
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