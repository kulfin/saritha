<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*"%>
<%
	ProjectServices services = new ProjectServices();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
	<title><%=ApplicationConstants.TITLE %></title>
<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script> 	
	<!-- Drop Do0wn -->
	<link rel="stylesheet" type="text/css" href="../css/Common/style.css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/Common/script.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />
	<script type="text/javascript" src="../js/Connect/connect.js"></script>

	<!-- Combo box drop down -->
	<link rel="stylesheet" href="../css/Connect/jquery-ui.css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
	
	<style type="text/css" media="screen">
		@import "test/demo_page.css";
		@import "test/demo_table.css";
		
	</style>
	<script type="text/javascript"  src="../js/Connect/jquery.dataTables.min.js"></script>
	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').dataTable();
			} );
	</script> 
	
<script type="text/javascript">

var ajaxRequest;  

function ajaxcheck(){
	
	 try
	 {	   ajaxRequest = new XMLHttpRequest();
	 }
	 catch(e)
	 {  try
	   {	ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
	   }
	   catch(e)
	   {  try
	      {      ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
	      }
	      catch(e)
	      {  alert("Your browser broke!");
	         return false;
	      }
	   }
	 }
}

function frameCall(pageName){
/* Admin Files */

//Manage Locations

if(pageName=="../Admin/ManageCountryView.jsp")
document.getElementById('header_label').innerHTML="Manage Country";

else if(pageName=="../Admin/ManageRegionView.jsp")
document.getElementById('header_label').innerHTML="Manage Region";

else if(pageName=="../Admin/ManageStateView.jsp")
document.getElementById('header_label').innerHTML="Manage State";

else if(pageName=="../Admin/ManageCityView.jsp")
document.getElementById('header_label').innerHTML="Manage City";

else if(pageName=="../Admin/ManageTownView.jsp")
document.getElementById('header_label').innerHTML="Manage Town";

else if(pageName=="../Admin/ManageAreaView.jsp")
document.getElementById('header_label').innerHTML="Manage Area";


//Manage Material
if(pageName=="../Admin/ManageMaterialGroupView.jsp")
document.getElementById('header_label').innerHTML="Manage Material Group";

else if(pageName=="../Admin/ManageMaterialSubGroupView.jsp")
document.getElementById('header_label').innerHTML="Manage Material Sub Group";

else if(pageName=="../Admin/ManageMaterialView.jsp")
document.getElementById('header_label').innerHTML="Manage Material";

else if(pageName=="../Admin/ManageMaterialElementView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Element";
else if(pageName=="../Admin/ManageMaterialElementStatusView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Element Status";
else if(pageName=="../Admin/ManageMaterialComponentView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Component";


//Manage FD

if(pageName=="../Admin/ManageFdOrgView.jsp")
document.getElementById('header_label').innerHTML="Manage FD Organisation";

else if(pageName=="../Admin/ManageFdHubView.jsp")
document.getElementById('header_label').innerHTML="Manage FD Hub";

else if(pageName=="../Admin/ManageFdDivisionView.jsp")
document.getElementById('header_label').innerHTML="Manage FD Division";

else if(pageName=="../Admin/ManageFdDepartmentView.jsp")
document.getElementById('header_label').innerHTML="Manage FD Department";

else if(pageName=="../Admin/ManageFdOrgEmployeeView.jsp")
	document.getElementById('header_label').innerHTML="Manage FD Employee";

else if(pageName=="../Admin/ManageFdOrgEmployeeTypeView.jsp")
	document.getElementById('header_label').innerHTML="Manage FD Employee Type";

else if(pageName=="../Admin/ManageFdOrgRoleView.jsp")
	document.getElementById('header_label').innerHTML="Manage FD Role";



/* Connect Files */
if(pageName=="../Connect/CreateProject.jsp")
document.getElementById('header_label').innerHTML="Create Project";
else if(pageName=="../Connect/Connect.jsp")
{
	document.getElementById('header_label').innerHTML="Search Project";
	window.location.href='Connect.jsp';
	return;
}

else if(pageName=="../Connect/OpenProject.jsp"){
	document.getElementById('header_label').innerHTML="Recent Project";;
	window.location.href='OpenProject.jsp';
	return;
}

else if(pageName=="../Connect/JobCardAssignment.jsp")
document.getElementById('header_label').innerHTML="Job Card Assignment";

else if(pageName=="../Connect/JobCardStatusView.jsp")
document.getElementById('header_label').innerHTML="Job Card Status Update";

else if(pageName=="../Connect/MeasurementSheet.jsp")
document.getElementById('header_label').innerHTML="Measurement Sheet";

else if(pageName=="../Connect/Jobcard_Report.jsp")
document.getElementById('header_label').innerHTML="Job Card Report";


/* RFPR Files */


if(pageName=="../RFPR/SearchBomView.jsp")
	document.getElementById('header_label').innerHTML="Search Bom";

else if(pageName=="../RFPR/CreateBomView.jsp")
	document.getElementById('header_label').innerHTML="Create Bom";



/*Misc Master */
if(pageName=="../Admin/ManageUnitView.jsp")
document.getElementById('header_label').innerHTML = "Misc Master Unit";

else if(pageName=="../Admin/ManageCourierView.jsp")
	document.getElementById('header_label').innerHTML = "Misc Master Courier";

else if(pageName=="../Admin/ManageAssemblyUnitMasterView.jsp")
	document.getElementById('header_label').innerHTML = "Misc Master Unit Master";
	
/* Project Aspects*/
 
if(pageName=="../Admin/ManageProjectScopeView.jsp")
	document.getElementById('header_label').innerHTML="Manage Project Scope";
else if(pageName=="../Admin/ManageProjectStatusView.jsp")
	document.getElementById('header_label').innerHTML="Manage Project Status";



//document.getElementById('main_content').src=pageName;

ajaxcheck();
ajaxRequest.onreadystatechange = function(){
  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	//alert("response   "+ajaxRequest.responseText);
	  //parent.window.document.getElementById('header_label').innerHTML="Project Approval";
	document.getElementById('main_content_element').innerHTML = ajaxRequest.responseText;
	  
		      
  }
};

ajaxRequest.open("GET",pageName,true);	
ajaxRequest.send(null); 

	
}
</script>
	
	
	
	
	<script type="text/javascript">
		
	function open_details(num) {
		
		//indow.location.href = 'DetailsProject.jsp?id=' + num;
		
		parent.window.document.getElementById('header_label').innerHTML = "Project Page";
		
		ajaxcheck();
		ajaxRequest.onreadystatechange = function(){
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				//alert("response   "+ajaxRequest.responseText);
				  //parent.window.document.getElementById('header_label').innerHTML="Project Approval";
				document.getElementById('main_content_element').innerHTML = ajaxRequest.responseText;
				  
					      
			  }
			};

		//ajaxRequest.open("GET","DetailsProject.jsp?id="+ num,true);	
		ajaxRequest.open("GET","DetailsProject.jsp?id="+ num+"&flag="+false);
		ajaxRequest.send(null); 

		
	}
	</script>

			
</head>


<!-----   Body   ----->
<body style="position: absolute;width: 100%;height: 100%;font: 13px Calibri; " onload="disable_radio();" >

<!-- div to include header -->
<div>	
			<jsp:include page="../Common/Home.jsp">
			<jsp:param name="title" value="Search Project"/>
			</jsp:include>
		</div>


<div id="container">

	<!-- Header -->
<!-- 	
<div id="header">
<font id="header_label" style="color: white; font-size:15px;font-weight:bold;text-align:center " >Search Project</font>
</div> -->
	<!-- End Header -->

	<!-- Vertical Menu -->

<!-- <div id="menu_div">

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

	
<!-- End Menu -->

	<!-- body /main content-->
	 <div id="main_content_element">
	
	<table style="width: 100%; text-align: center;">
			<tr>
					<td width="14%"></td>

					<td width="85%">

<div id="maincontent_element">
	<form name="myform">
		<div id="search_project">
			<div style="margin: 50px 277px 39px 25px;
				border: #778 2px solid;
				background: #e0e0e0;
				border-radius: 10px;">
						<table style="width: 100%; text-align: left;">
											<tr>
												<td colspan="2">
													<h2 style="color: #ffffff;">SEARCH BY:</h2></td>
											</tr>
											<tr>
												<td><input type="radio" id="id_1" name="btn1"
													checked="checked" onclick="radio_test()"></td>
												<td>
												<font style="font-size: 16px;">Project Name</font>
												</td>

												<td><input id="search_project_name" size="15"
													name="search_project_name" onclick="project_search_name();" />
												</td>


												<td><input type="radio" id="id_2" name="btn1"
													onclick="radio_test()"></td>
												<td>
												<font style="font-size: 16px;">Client Name
												</font>
												</td>
												<td>
										<!-- 	<input id="search_client" size="15" name="search_client" onclick="project_search_by_client();"/>	 -->

													<!-- </td> --> 
				<select name="search_client" id="search_client" style="width: 170px;">
														<!-- <option value="ALL">ALL</option> -->
			<%
			String dropvalue_client = services.drop_down_client();
			String[] result_drop_client = dropvalue_client
					.split(Constants.rowSeperator);
			//System.out.println("result--->"+dropvalue_client);
			for (int i = 0; i < result_drop_client.length; i++) {
				System.out.println("result_drop_client--->"
						+ result_drop_client[i]);
				String[] columnData = result_drop_client[i]
						.split(Constants.columnSeperator);	%>
				<option value="<%=columnData[0]%>"><%=columnData[0]%></option>
			<%}%>
				</select></td>
												<!-- 	</tr>
									<tr> -->
			<td><input type="radio" id="id_3" name="btn1" onclick="radio_test()"></td>
												<td>
												<font style="font-size: 16px;">
												Division Name
												</font></td>
												<td>
													<!-- <input id="search_division" size="15" name="search_division" onclick="project_search_by_division();"/> -->
													<select name="search_division" id="search_division"
													style="width: 170px;">
														<!-- 	<option value="ALL">ALL</option> -->
														<%String dropvalue = services.drop_down_fddiv();
			String[] result = dropvalue.split(Constants.rowSeperator);
			for (int i = 0; i < result.length; i++) {
				if (result[i].equals("")) {
				} else {
					String[] columnData = result[i]
							.split(Constants.columnSeperator);%>
														<option value="<%=columnData[0]%>">
															<%=columnData[0]%>
														</option>
														<%
														}
			}
			%>
												</select></td>
											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td><input type="button" id="btnFilter"
													value="Search Project" onclick="return search_projects();">
												</td>
											</tr>


										</table> 
									</div>
								</div>
							</form>

		<div id="searched_result" style="margin: 50px 277px 39px 25px;">
		<h3 style="color:white;font-size: 17px;background-color:#e0e0e0;">Searched Result</h3>




		<table class="display" id="example">
											<thead>
												<tr>
													<th>Project No</th>
													<th>Project Name</th>
													<th>Client</th>
													<th>Division</th>
													
													
												</tr>
											</thead>
											<tbody>
																							
											</tbody>

										</table>

									
				</div>
						</div>
							</td>
				</tr>
			</table>
	
	
		
	</div> 
	
	
</div>
</body>
</body>
</html>