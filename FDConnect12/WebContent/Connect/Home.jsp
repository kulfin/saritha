<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	System.out.println("Home jsp"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Connect/home.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css"
	href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript" src="../css/Common/dropdowntabfiles/dropdowntabs.js">
</script>


<!-- Horizontal Menu -->
<link rel="stylesheet" type="text/css" 	href="../css/Connect/horizontal_menu.css" />

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
<body style="position: absolute;">
	<!-- Header -->
	<div id="admindiv">
		
			<!-- Vertical Menu -->
	 	<div id="bluemenu" class="bluetabs">
			<ul>
				<li><a href="Home.jsp"
					style="color: rgb(70, 66, 66); text-decoration: none;"><span>Home</span>
				</a>
				</li>
				<li><a href="../admin.jsp" rel="dropmenu_material"
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


		<div id="dropmenu_material" class="dropmenudiv_b">
			<a href="manage_materialgroup.jsp">Material Group</a> <a
				href="manage_submaterialgroup.jsp">Sub-Material Group</a> <a
				href="manage_materials.jsp">Materials</a>
		</div>

		<div id="dropmenu_place" class="dropmenudiv_b">
			<a href="manage_country.jsp">Manage Country</a> <a
				href="manage_region.jsp">Manage Region</a> <a
				href="manage_state.jsp">Manage State</a> <a href="manage_city.jsp">Manage
				City</a> <a href="manage_area.jsp">Manage Area</a>
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

		
 </div>
 <!-- End Menu -->

		<!-- body /main content-->
		<div id="maincontent">
			<table style="width: 100%; text-align: center;">
				<tr>
					<td width="20%">
									<div id="navContainer">
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>			
			
	<ul>
		<li> <span><a href="OpenProject.jsp">Search Project</a></span></li>
		<br/>
		<li> <span><a href="CreateProject.jsp">New Project</a></span></li>
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
					<td width="65%">
						<div id="maincontent_element">
						<br>
						<br>
						<br>
						<br>
							<font style="padding-left: 10px;"> <b><font style="">Welcome
										to FOURTH DIMENSION !!</font>
							</b> <br> <br>This tool is made for making work easy for
								Project Managment. <br>Please Use it and give your valuable
								feed-back to us. </font>
						</div>
					</td>
					<td width="15%">
					
		<div id="navContainer">
		<ul>
		<li> <span><a href="#">Tasks </a></span></li>
		</ul>	
		</div>		
					
					
					
					<!-- 	<div id="task">
						<font style="">
						Tasks :-
									<br> 
						Create New Project
						</font>
						</div> -->
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
</html>>