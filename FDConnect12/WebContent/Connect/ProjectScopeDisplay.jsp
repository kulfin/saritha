<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>


<!-- Retrieve all display Data -->
<%
String id = request.getParameter("id");
session.setAttribute("project_id",id);
System.out.println("Project ID in ProjectScopeDisplay.jsp---------->"+id);
ProjectServices services = new ProjectServices();
String header_details = services.Project_header_details(Integer
		.parseInt(id));
String[] row_data = header_details.split(Constants.columnSeperator);

//Pass project ID for CRM Details
String crm_data=services.check_CRM_details(Integer.parseInt(id));
//Scope List
String scope_data=services.scope_list();
String[] scope_list_data=scope_data.split(Constants.rowSeperator);
//Brand on Client
String[] brand_list_data=services.drop_down_brandOnClient(row_data[2]).split(Constants.columnSeperator);

/* String[] brand_list_data=services.drop_down_brand().split(Constants.columnSeperator); */
//Element
String[] element_list_data=services.drop_down_element().split(Constants.columnSeperator);
//Component
String[] component_list_data=services.drop_down_component().split(Constants.columnSeperator);
//Material
String[] material_list_data=services.drop_down_material().split(Constants.columnSeperator);
//Depot 
String[] depot_list_data=services.drop_down_depot().split(Constants.columnSeperator);
//Print Mode
String[] print_mode_list_data=services.drop_down_print_mode_master().split(Constants.columnSeperator);
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#data{
color:#000000;
text-align: left;
}
</style>
<meta charset="ISO-8859-1">
<script type="text/javascript">

function headerSet(){	
	parent.window.document.getElementById('header_label').innerHTML="Project Elements";
}
	
</script>

<script type="text/javascript">

var ajaxRequest;  

function ajaxcheck(){
	
	 try
	 {
	   // Opera 8.0+, Firefox, Safari
	   ajaxRequest = new XMLHttpRequest();
	 }
	 catch(e)
	 {
	   // Internet Explorer Browsers
	   try
	   {
	      ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
	   }
	   catch(e)
	   {
	      try
	      {
	         ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
	      }
	      catch(e)
	      {
	         // Something went wrong
	         alert("Your browser broke!");
	         return false;
	      }
	   }
	 }
}

function open_details(num) {
	//indow.location.href = 'DetailsProject.jsp?id=' + num;
	
	parent.window.document.getElementById('header_label').innerHTML = "Project Page";
	
	ajaxcheck();
	ajaxRequest.onreadystatechange = function(){
  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		document.getElementById('main_content_element').innerHTML = ajaxRequest.responseText;
	  }
};

	ajaxRequest.open("GET","DetailsProject.jsp?id="+ num,true);	
	ajaxRequest.send(null); 	
}
</script>


<!--  For new tab menu -->
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Connect/style.css" />


<!-- Side Menu -->
<link rel="stylesheet" type="text/css" 	href="../css/Connect/horizontal_menu.css" />

<!-- CheckBox Select -->
<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script>
<script type="text/javascript" src="../js/Connect/drop_downs.js">
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"	href="../css/Connect/project_scope_display.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css" href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript" src="../css/Common/dropdowntabfiles/dropdowntabs.js">
</script>

<!-- Drop Do0wn -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />
<script type="text/javascript" src="../js/Connect/connect.js"></script>

</head>
<!-----   Body   ----->
<body style="width:1375px;" onload="headerSet();">
	<!-- Header -->
	<div id="admindiv">
<!-- Header -->
<!-- <div id="header">
<font id="header_label" style="color: white; font-size:15px;font-weight:bold;text-align:center " >Recent Project</font>
</div>
 -->
		
<!-- 
<div id="menu_div">

<ul class="menu" id="menu">
    <li><a href="Home.jsp" class="menulink">Home</a></li>
    
    <li>
		<a href="#" class="menulink">Admin</a>
		<ul>
			<li>
				<a href="#" class="sub">Manage Locations</a>
				<ul>
					<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageCountryView.jsp')" >Country</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')">Region</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageStateView.jsp')" >State</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageCityView.jsp')" >City</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageTownView.jsp')" >Town</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageAreaView.jsp')" >Area</a></li>
					
				</ul>
			</li>
			
			<li>
				<a href="#" class="sub">Manage Material</a>
				<ul>
					<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageMaterialGroupView.jsp')" >Material Group</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageMaterialSubGroupView.jsp')">Material Sub Group</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageMaterialView.jsp')" >Material</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageMaterialTypeView.jsp')" >Material Type</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageMaterialElementView.jsp')">Element</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageMaterialElementStatusView.jsp')">Element Status</a></li>
                    <li><a href="#" onclick="frameCall('../Admin/ManageMaterialComponentView.jsp')">Component</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage FD</a>
				<ul>
					<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageFdOrgView.jsp')" >FD Organisation</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageFdHubView.jsp')">FD Hub</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageFdDivisionView.jsp')" >FD Division</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageFdDepartmentView.jsp')" >FD Department</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageFdOrgEmployeeView.jsp')" >FD Employee</a></li>
                    <li><a href="#" onclick="frameCall('../Admin/ManageFdOrgEmployeeTypeView.jsp')" >FD Employee Type</a></li>
                    <li><a href="#" onclick="frameCall('../Admin/ManageFdOrgRoleView.jsp')" >FD Role</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Client</a>
				<ul>
					<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Client</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')">Brand</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Depot</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Store</a></li>
					
					
					
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Document</a>
				<ul>
					<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Document</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')">Document Status</a></li>
				
					
					
					
				</ul>
			</li>
			<li>

                   <a href="#" class="sub">Misc Masters</a>
                   <ul>
                            <li><a href="#" onclick="frameCall('../Admin/ManageUnitView.jsp')">Manage Units</a></li>
                            <li class="topline"><a href="#" onclick="frameCall('../Admin/ManageCourierView.jsp')" >Manage Courier</a></li>
                            <li><a href="#" onclick="frameCall('../Admin/ManageAssemblyUnitMasterView.jsp')">Manage Outsource Factory</a></li>
                   </ul>
            </li>
       
            <li>
                   <a href="#" class="sub">Manage Project Aspects</a>
                   <ul>
                           <li><a href="#" onclick="frameCall('../Admin/ManageProjectScopeView.jsp')">Project Scope Master </a></li>
                           <li class="topline"><a href="#" onclick="frameCall('../Admin/ManageProjectStatusView.jsp')" >Project State Master</a></li>
                   </ul>
            </li>
			
		</ul>
	</li>
    
    
	 <li>
		<a href="#" class="menulink">Connect</a>
		<ul>
			<li>
				<a href="#" class="sub">Project</a>
				<ul>
					<li class="topline">
					<a href="#" onclick="frameCall('../Connect/CreateProject.jsp')">Create Project</a></li>
					<li><a href="#" onclick="frameCall('../Connect/Connect.jsp')" >Search Projects</a></li>
					<li><a href="#" onclick="frameCall('../Connect/OpenProject.jsp')" >Recent Projects</a></li>
					
					
				</ul>
			</li>
			
			
			<li><a href="#" onclick="frameCall('../Connect/MeasurementSheet.jsp')">Measurement Sheet</a></li>	
			
			
			<li>
				<a href="#" class="sub">Job Card</a>
				<ul>
					<li class="topline"><a href="#" onclick="frameCall('../Connect/JobCardAssignment.jsp')" >Job Card Assignment</a></li>
					<li><a href="#" onclick="frameCall('../Connect/JobCardStatusView.jsp')">Job Card Status Update</a></li>
					
					
					
				</ul>
			</li>
			
			<li><a href="#" onclick="frameCall('../Connect/Jobcard_Report.jsp')">MIS Tracker</a></li>	
			
		</ul>
	</li>
	
		<li><a href="#" class="menulink">RFPR</a>
		<ul>
		
		   <li><a href="#" onclick="frameCall('../RFPR/CreateBomView.jsp')" >Create BOM</a></li>
			<li><a href="#" onclick="frameCall('../RFPR/SearchBomView.jsp')" >Consolidate BOM</a></li>
			
		
			
		</ul>	
			
	</li>
	
	
	<li><a href="../Logout.jsp" class="menulink">Logout</a></li>


</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>

	</div> -->
<!-- End Header -->		

		<!-- body /main content-->
<div id="main_content_element">

		<div id="maincontent">
			<!-- <table style="width: 100%; text-align: center;">
				<tr>


			<td width="100%"> -->

		<div id="maincontent_element" style="overflow:auto;height: 600px;font: calibri;" >
						
		
							
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
		<div style="margin-left: 970px;position: absolute;display: inline-table;
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

<div id= "subMenu" style="margin-top:20px; font-size:11px;">
					
<ul class="menu" id="menu">
	  	<li><a href="#" onclick="project_details(<%=id%>);" class="submenulink">Project Header</a></li>
 <!--    <li><a href="#" onclick="#" class="submenulink">Document Library</a></li>
    	<li><a href="#" onclick="#" class="submenulink">Accountability</a></li> 
   -->
    
    <li><a href="#" onclick="project_document(<%=id%>);" class="submenulink">Document Library</a></li>
    <li><a href="#" onclick="project_accountablity(<%=id%>);" class="submenulink">Accountability</a></li> 

	<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element & Scope</a></li>
	<li><a href="#" class="submenulink">Date Plans</a></li>
	<li><a href="#" class="submenulink">Rate Card</a></li>
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a></li>
	<li><a href="#" class="submenulink">Quantities</a></li>
<!-- 	<li><a href="#" class="submenulink">Financial</a></li>
	<li><a href="#" onclick="#" class="submenulink">Approval</a></li> -->
	
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
	<li><a href="#" onclick="project_approval(<%=id%>);" class="submenulink">Approval</a></li> 
	
	<li><a href="#" class="submenulink" onclick="#">Closure</a></li>
</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>	
</div>	


					
					<form name="myForm" id="myScopeForm">
						
					<table width="99%">
					<tr>
					<td width="55%">
					<div><h4 style="color:#39939C ;">Add Element</h4></div>
					
					
					<div>
					<font id="td_id">Project Number</font> <input type="text" id='prjct_id' name='prjct_id' 
					size='25' maxlength='25'>
					<font id="td_id">Project Name</font> <input type="text" id='prjct_name' name='prjct_name' 
					size='35' maxlength='50'>
					
					</div>
					<br>
					<!-- <table align="center" style="width: 100%; text-align: center;" border="1 px"> -->
					<!-- <table align="center" style="width: 55%; text-align: center;"> -->
						<table align="center" style="width: 73%; text-align: center;">
						
						
									<tr>
										<td id="td_id" width="">STATUS</td>
										<td id="td_id">BRAND</td>
										<td id="td_id" colspan="2">ELEMENT TYPE</td>
										<td id="td_id">COMPONENT</td>
										<td id="td_id">MATERIAL</td>
										<!-- <td id="td_id">COMPANY DEPOT</td> -->
										<td id="td_id">Print Mode</td>
										<td id="td_id">Quantity</td>
										
									</tr>
									<tr>
									
										<td id="td_scope_list">
										<input type="checkbox" name="chck_hold"	id="chck_hold" value="">
										</td>
										<td id="td_brand_list">
										<select name="brand_id"
											id="brand_id">
												<%
										
										for(int i=0;i<brand_list_data.length;i++){
											
											%>
												<option value="<%=brand_list_data[i]%>"><%=brand_list_data[i] %></option>
												<%
										}
										%>
										</select></td>
										

										<td id="td_element_list" colspan="2">
										<select name="element_id" id="element_id">
												<%
										
										for(int i=0;i<element_list_data.length;i++){
											
											%>
												<option value="<%=element_list_data[i]%>"><%=element_list_data[i]%></option>
												<%
										}
										%>
										</select></td>

										
										<td id="td_component_list">
										<select name="component_id"
											id="component_id">
												<%
										
										for(int i=0;i<component_list_data.length;i++){
											
											%>
												<option value="<%=component_list_data[i]%>"><%=component_list_data[i]%></option>
												<%
										}
										%>
										</select>
										</td>


									<!-- </tr>
									
									<tr>
									<td id="td_id">MATERIAL</td>
									<td id="td_id">COMPANY DEPOT</td>
									<td id="td_id">Print Mode</td>
									<td id="td_id">Quantity</td>
									</tr>
									<tr>  -->
										
										<td id="td_material_list">
										<select name="material_id" id="material_id">
												<%
										
										for(int i=0;i<material_list_data.length;i++){
											
											%>
												<option value="<%=material_list_data[i]%>"><%=material_list_data[i]%></option>
												<%
										}
										%>
										</select></td>
										
										
								<%-- 		
										 <td id="td_depot_list">
										<select name="depot_id"	id="depot_id">
												<%
																						
										for(int i=0;i<depot_list_data.length;i++){
											
											%>
												<option value="<%=depot_list_data[i]%>"><%=depot_list_data[i]%></option>
												<%
										}
										%>
										</select></td>  --%>

 										
										
										<td id="td_id">
										<select name="print_mode_id" id="print_mode_id">
												<%
										
										for(int i=0;i<print_mode_list_data.length;i++){
											
											%>
												<option value="<%=print_mode_list_data[i]%>"><%=print_mode_list_data[i]%></option>
												<%
										}
										%>
										</select>
										</td>
										
										<td id="td_id">
										<input type="text" name="qty" id="qty"
											size="10">
										</td>
								</tr>
								<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
			<input type="button" value="Insert Element" onclick="return save_Elementdetails(<%=id%>);" class="btn" />
									</td>
								<td>
			<input type="button" value="Cancel" onclick="cancelElement(<%=id%>);" class="btn">					
								</td>		
									</tr>
									
								</table>
					
					
					
					
					
					
					<%-- <table align="left" style="width: 100%; text-align: center;" border="1">
					<tr>
																		
										
										<td id="td_id" width="16%">BRAND</td>
										<td id="td_brand_list" width="16%">
										<select name="brand_id"
											id="brand_id">
												<%
										
										for(int i=0;i<brand_list_data.length;i++){
											
											%>
												<option value="<%=brand_list_data[i]%>"><%=brand_list_data[i] %></option>
												<%
										}
										%>
										</select></td>
										
										<td id="td_id" width="16%">ELEMENT</td>
										<td id="td_element_list" width="16%" colspan="3">
										<select name="element_id" id="element_id" style="width: 100%">
												<%
										
										for(int i=0;i<element_list_data.length;i++){
											
											%>
												<option value="<%=element_list_data[i]%>"><%=element_list_data[i]%></option>
												<%
										}
										%>
										</select></td>
										<td id="td_id" width="16%">COMPONENT</td>
										<td id="td_component_list" width="16%"><select name="component_id"
											id="component_id">
												<%
										
										for(int i=0;i<component_list_data.length;i++){
											
											%>
												<option value="<%=component_list_data[i]%>"><%=component_list_data[i]%></option>
												<%
										}
										%>
										</select></td>
					</tr>	
					
					<tr>
										
										<td id="td_id">MATERIAL</td>
										<td id="td_material_list"><select name="material_id"
											id="material_id">
												<%
										
										for(int i=0;i<material_list_data.length;i++){
											
											%>
												<option value="<%=material_list_data[i]%>"><%=material_list_data[i]%></option>
												<%
										}
										%>
										</select></td>
										<td id="td_id">QUANTITY</td>
										<td id="td_id">
										<input type="text" name="qty" id="qty"
											size="10"></td>
										<td id="td_id">COMPANY DEPOT</td>
										<td id="td_depot_list"><select name="depot_id"
											id="depot_id">
												<%
																						
										for(int i=0;i<depot_list_data.length;i++){
											
											%>
												<option value="<%=depot_list_data[i]%>"><%=depot_list_data[i]%></option>
												<%
										}
										%>
										</select></td>
										
											<td id="td_id">Print Mode</td>
										<td id="td_id">
										<select name="print_mode_id" id="print_mode_id">
												<%
										
										for(int i=0;i<print_mode_list_data.length;i++){
											
											%>
												<option value="<%=print_mode_list_data[i]%>"><%=print_mode_list_data[i]%></option>
												<%
										}
										%>
										</select>
										</td>
					</tr>
					<tr>
				
					<td id="td_id" width="11%">Hold</td>
										<td id="td_scope_list" width="11%"><input type="checkbox" name="chck_hold"
											value="">
					</td>
					<td>
					
					</td>
					<td>
					
					</td>
					</tr>
							
					</table>	 --%>		
					
					
					
					
							
					</td>
					</tr>
					<%-- <tr>
					<td>
						<div id="add_element-decide_scope" style="overflow: auto; height: 432px;" align="center">	
						<table width="47%" border="1">
						<tr>
						<h4 style="color: #39939C;">Scope of Work</h4>
						</tr>
						<tr>
						<td id="td_id">Reqeuired</td>
						<td id="td_id">Scope List</td>
						<td id="td_id">FD Hub</td>
						<td id="td_id">Responsible Person</td>
						</tr>
						
						<%
										for(int i=0;i<scope_list_data.length;i++){
											String[] scope_list_row= scope_list_data[i].split(Constants.columnSeperator);
											
						%>
									<tr>	
										
										<td id="td_scope_list"><input type="checkbox" name="chckbox"
											value="<%=scope_list_row[0]%>"></td>
											
										<td id="td_scope_list"><%=scope_list_row[1]%></td>
										
										
										<td>
										<div id="td_fd_hub_list<%=i%>" align="center">
										<input type="text" name="td_fd_hub_list" value="" onclick="drop_down_fdhub(<%=i%>);">
										</div>
										</td>
										
										<td>
										<div id="td_resopnsible_list<%=i%>" align="center">
										<input type="text" name="td_fd_hub_list" value="" onclick="drop_down_fdemployee(<%=i%>);">
										</div>
										</td>
										
										
						</tr>
						<% } %>
						</table>
					</div>
					
					
					</td>
					
						
					
					
					</tr> --%>
				
					</table>
					</form>
					
					<br><br>
	<!-- 				<table width="100%" border="1">
					<tr>
									<td colspan="7" id="td_id">Scope List</td>
									<td colspan="2" id="td_id">Planned</td>
									<td colspan="2" id="td_id">Actual</td>
									<td colspan="2" id="td_id">Planned </td>
									<td colspan="2" id="td_id">Actual</td>
									</tr>
						
									<tr>
									<td width="7%" id="td_id">Scope</td>
									<td width="7%" id="td_id">Region</td>
									<td width="7%" id="td_id">State</td>
									<td width="7%" id="td_id">Trade</td>
									<td width="7%" id="td_id">Chain</td>
									<td width="7%" id="td_id">City</td>
									<td width="7%" id="td_id">No. Of Store</td>
																	
									<td width="7%" id="td_id">Start Date</td>
									<td width="7%" id="td_id">End Date</td>
									
									<td width="7%" id="td_id">Start Date</td>
									<td width="7%" id="td_id">End Date</td> 
									
									<td width="7%" id="td_id">Start Date</td>
									<td width="7%" id="td_id">End Date</td>
									<td width="7%" id="td_id">Start Date</td>
									<td width="7%" id="td_id">End Date</td> 
									
									</tr>
									
					<tr>
									
									<td width="7%">
									Proof Approval
									</td>
									<td width="7%">
									<div id="Region_id">
									<input type="text"  name="Region_id" style="width: 95%" onclick="drop_region();">
									</div>
									</td>
									
									<td width="7%">
									<div id="State_id">
									<input type="text" name="State_id" style="width: 95%" onclick="drop_state();">
									</div>
									</td>
									<td width="7%">
									<div id="Trade_id">
									<input type="text" name="Trade_id" style="width: 95%" onclick="drop_trade();">
									</div>
									</td>
									
									<td width="7%">
									<div id="Chain_id">
									<input type="text" name="Chain_id" style="width: 95%" onclick="drop_chain();">
									</div>
									</td>
									<td width="7%">
									<div id="City_id">
									<input type="text" name="City_id" style="width: 95% " onclick="drop_city();">
									</div>
									</td>
									<td width="7%">
									<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
																	
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
									
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td> 
									
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td>
									<td width="7%">
										<input type="text" id="No_of_Store" name="No_of_Store" style="width: 95%">
									</td> 
								
					
					
					</tr>
					</table> -->
				
					
							<%-- <form name="myForm" id="myScopeForm">
								<h3 style="color: #39939C;">Add Element of Work</h3>
								<table align="center" style="width: 100%; text-align: center;">
									<tr>
										<td id="td_id">BRAND</td>
										<td id="td_id">ELEMENT</td>
										<td id="td_id">COMPONENT</td>
										<td id="td_id">MATERIAL</td>
										<td id="td_id">QTY</td>
										<td id="td_id">COMPANY DEPOT</td>
										<td id="td_id">Print Mode</td>
									</tr>
									<tr>
										<td id="td_brand_list">
										<select name="brand_id"
											id="brand_id">
												<%
										
										for(int i=0;i<brand_list_data.length;i++){
											
											%>
												<option value="<%=brand_list_data[i]%>"><%=brand_list_data[i] %></option>
												<%
										}
										%>
										</select></td>
										

										<td id="td_element_list">
										<select name="element_id" id="element_id">
												<%
										
										for(int i=0;i<element_list_data.length;i++){
											
											%>
												<option value="<%=element_list_data[i]%>"><%=element_list_data[i]%></option>
												<%
										}
										%>
										</select></td>

										
										<td id="td_component_list"><select name="component_id"
											id="component_id">
												<%
										
										for(int i=0;i<component_list_data.length;i++){
											
											%>
												<option value="<%=component_list_data[i]%>"><%=component_list_data[i]%></option>
												<%
										}
										%>
										</select></td>

										
										<td id="td_material_list"><select name="material_id"
											id="material_id">
												<%
										
										for(int i=0;i<material_list_data.length;i++){
											
											%>
												<option value="<%=material_list_data[i]%>"><%=material_list_data[i]%></option>
												<%
										}
										%>
										</select></td>
										
										
										
										
										
										<td id="td_id">
										<input type="text" name="qty" id="qty"
											size="10"></td>
										
										<td id="td_depot_list"><select name="depot_id"
											id="depot_id">
												<%
																						
										for(int i=0;i<depot_list_data.length;i++){
											
											%>
												<option value="<%=depot_list_data[i]%>"><%=depot_list_data[i]%></option>
												<%
										}
										%>
										</select></td>

 										
										
										<td id="td_id">
										<select name="print_mode_id" id="print_mode_id">
												<%
										
										for(int i=0;i<print_mode_list_data.length;i++){
											
											%>
												<option value="<%=print_mode_list_data[i]%>"><%=print_mode_list_data[i]%></option>
												<%
										}
										%>
										</select>
										</td>


									</tr>
								</table>
								<table align="center" style="width: 60%; text-align: center;">

									<tr>
										
									</tr>

								</table>
								<br>
								
								<h3 style="color: #39939C;">Element Scope Work</h3>
							
						<table align="center" style="width: 100%; text-align: center;" border="1">
									<tr>
									<td width="21%"></td>
									<td width="37%">Client </td>
									<td width="37%">Fourth Division</td>
									</tr>
					</table>
								
						<table align="center" style="width: 100%; text-align: center;" border="1">
									<tr>
									<td  colspan="3">Scope List</td>
									<td colspan="2">Planned</td>
									<td colspan="2">Actual</td>
									<td colspan="2">Planned </td>
									<td colspan="2">Actual</td>
									</tr>
						
									<tr>
									<td width="5%">Required</td>
									<td width="10%">Scope List</td>
									<td width="12%">Set As Hold</td>
									
									<td  width="9.25%">Start Date</td>
									<td width="9.25%">End Date</td>
									
									<td width="9.25%">Start Date</td>
									<td width="9.25%">End Date</td> 
									
									<td width="9.25%">Start Date</td>
									<td width="9.25%">End Date</td>
									<td width="9.25%">Start Date</td>
									<td width="9.25%">End Date</td> 
									
									</tr>
									
									
										<%
										for(int i=0;i<scope_list_data.length;i++){
											String[] scope_list_row= scope_list_data[i].split(Constants.columnSeperator);
											
											%>
											<tr>
										
										<td id="td_scope_list"><input type="checkbox" name="chckbox"
											value="<%=scope_list_row[0]%>"></td>
											
										<td id="td_scope_list"><%=scope_list_row[1]%></td>
										
										<td id="td_scope_list"><input type="checkbox" name="chck_hold"
											value="<%=scope_list_row[0]%>"></td>
										<td>
											<input type="text"  id="client_planned_start_date<%=scope_list_row[0]%>" size="12" 
											onclick="date_cl_pl_st(<%=scope_list_row[0]%>)">	  
										</td>
										<td>
												<input type="text" id="client_planned_end_date<%=scope_list_row[0]%>" size="12"
												onclick="date_cl_pl_en(<%=scope_list_row[0]%>)"> 
										</td>
										
										<td>
											<input type="text" id="client_actual_start_date<%=scope_list_row[0]%>" size="12"
											onclick="date_cl_ac_st(<%=scope_list_row[0]%>)"
											>	  
										</td>
										<td>
												<input type="text" id="client_actual_end_date<%=scope_list_row[0]%>" size="12"
												onclick="date_cl_ac_en(<%=scope_list_row[0]%>)"> 
										</td>
										
										<td>
											<input type="text" id="fd_planned_start_date<%=scope_list_row[0]%>" size="12"
											onclick="date_fd_pl_st(<%=scope_list_row[0]%>)">
												  
										</td>
										<td>
												<input type="text" id="fd_planned_end_date<%=scope_list_row[0]%>" size="12"
												onclick="date_fd_pl_en(<%=scope_list_row[0]%>)"> 
										</td>
										<td>
											<input type="text" id="fd_actual_start_date<%=scope_list_row[0]%>" size="12" 
											onclick="date_fd_ac_st(<%=scope_list_row[0]%>)" >	  
										</td>
										<td>
												<input type="text" id="fd_actual_end_date<%=scope_list_row[0]%>" size="12"
												onclick="date_fd_ac_en(<%=scope_list_row[0]%>)"> 
										</td>
											
											
										</tr>
										<%
										}
										%>
									
						</table>

					<fieldset style="border: none; float: right;">
					<input type="button" value="Save Details" onclick="save_details(<%=id%>);"
										class="btn" />
										onclick="save_details(<%=id%>);validation();" 
					</fieldset>

</form> --%>
</div>
					
					
					<!-- Display Table -->
					<%-- <%
					
					
					%>
					<div id="display_scope_of_work" style="overflow: auto;">
						Scope Of Work List
					<table>
					<tr>
					<td>Brand</td>
					</tr>
					
					<tr>
					<td>ELEMENT</td>
					</tr>
					
					<tr>
					<td>COMPONENT</td>
					</tr>			
					
					<tr>
					<td>MATERIAL</td>
					</tr>	
					
					<tr>
					<td>QUANTITY</td>
					</tr>
					
					<tr>
					<td>COMPANY DEPOT</td>
					</tr>	
					
					
					<tr>
					<td>PRINT MODE</td>
					</tr>
					
					</table>
  					
					</div>
					
					 --%>
<!-- 	
					</td>

</tr>
			</table> -->
		</div>
</div>
		<!-- End body /main content-->
		
		<!-- Footer -->
<!-- 		<div id="footer">
			<font style="font-size: 12px" color="#000">Powered by</font>&nbsp;&nbsp;&nbsp;
			<img alt="Aseema Softnet Technologies" src="../images/a_logo.png"
				height="20px" width="40px" draggable="false" /> <a
				href="http://www.aseema.in"><font color="#A0A0A0" style="font-size: 13px; font-weight: bold;"> Aseema Softnet
					Technologies Private Limited</font> </a>
		</div> -->
	</div>
	<!-- Scope of Work Table -->
</body>
</html>