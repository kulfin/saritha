<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- new table add -->
	<style type="text/css" media="screen">
		@import "test/demo_page.css";
		@import "test/demo_table.css";
		
	</style>
	
	<script type="text/javascript" src="../js/Connect/jquery.js"></script>
	<script type="text/javascript"  src="../js/Connect/jquery.dataTables.min.js"></script>
	

<!-- Drop Do0wn -->
<link rel="stylesheet" type="text/css" href="../css/Common/style.css" />
<!-- <script type="text/javascript" src="../js/Common/jquery.min.js"></script> -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />
<script type="text/javascript" src="../js/Connect/connect.js"></script>


<!-- Combo box drop down -->
	<link rel="stylesheet" href="../css/Connect/jquery-ui.css" />
	<!-- <script src="../js/Connect/jquery-1.8.3.js"></script> -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<!-- <script src="../js/Connect/jquery-ui.js"></script> -->
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
	<!-- <link rel="stylesheet" href="../css/Common/style.css" /> -->

<title>ProTrac</title>

<!-- CSS for Drop Down Tabs Menu #2 -->

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

	

function frameCall(pageName){
//alert("it is calling");	

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

function changeHomeImage(img){
//	alert(img);
	document.getElementById("home").src=img;
}


</script>
</head>
<!------JSP Scripting ------>
<%
/* String username=(String)session.getAttribute("username");
if(username==null){
	{
		response.sendRedirect("login.jsp");
	}
} */
%>

<!-----   Body   ----->
<body style="width: 100%;height: 100%">
<!-- Header -->
<div id="container">

	<!-- Header -->
<div id="header">
<font id="header_label" style="color: white; font-size:15px;font-weight:bold;text-align:center " >Home</font>
</div>
	<!-- End Header -->

	<!-- Vertical Menu -->
<div id="menu_div">

<ul class="menu" id="menu">
    
    
	<li>	<a id="Homelink" onmouseover="changeHomeImage('images/HomeRO.png')" href="TestHomePage.jsp"  
				 onmouseout="changeHomeImage('images/HomeOff.png')" > 
			<img id="home" name="home" alt="Home" src="images/HomeOff.png" class="home">
		</a>
	</li>	
	 
   <!--  <li><a href="Home.jsp" class="menulink">Home</a></li> -->
    
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
					<!--<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Client</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')">Brand</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Depot</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Store</a></li>
					
					
					
				--></ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Document</a>
				<ul>
					<!--<li class="topline"><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Document</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')">Document Status</a></li>
				
					
					
					
				--></ul>
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

	</div>

	
<!-- End Menu -->

	<!-- body /main content-->
	 <div id="main_content_element">
	
		
	</div> 
	
	
</div>
</body>
</html>