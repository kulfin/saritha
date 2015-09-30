<%!HttpSession session2=null; %>
<%
	/**response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);**/
	System.out.println("----home.jsp");
	
	
	
	 session2 = request.getSession();
	if(session2==null){
		System.out.println("inside if block");
		response.sendRedirect("../Login.jsp");
	}
	String employee_Id=(String)session2.getAttribute("employee_Id");
	
	String Role =(String)session2.getAttribute("Role");
	
	
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >

<%@page import="com.fd.login.RolesConstants"%>
<%@page import="com.fd.App.*"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/Common/style.css" />
<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Connect/jquery.dataTables.min.js"></script>
<!-- <script type="text/javascript" src="../js/RfprOnLoad.js"></script> -->
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />

<link rel="logo" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<!-- CSS for Drop Down Tabs Menu #2 -->

<script>
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

else if(pageName=="../Admin/ManageMaterialTypeView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Type";

else if(pageName=="../Admin/ManageMaterialElementView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Element";

else if(pageName=="../Admin/ManageMaterialElementStatusView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Element Status";

else if(pageName=="../Admin/ManageMaterialComponentView.jsp")
	document.getElementById('header_label').innerHTML="Manage Material Component";
	
else if(pageName=="../Admin/ManageCostType.jsp")
	document.getElementById('header_label').innerHTML="Manage Cost Type";

else if(pageName=="../Admin/ManageCostItem.jsp")
	document.getElementById('header_label').innerHTML="Manage Cost Item";


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
	
/* Manage Client*/
if(pageName=="../Admin/ManageClient.jsp")
	document.getElementById('header_label').innerHTML="Manage Client";

else if(pageName=="../Admin/ManageBrandCategory.jsp")
	document.getElementById('header_label').innerHTML="Manage Brand Category";

else if(pageName=="../Admin/ManageTrade.jsp")
	document.getElementById('header_label').innerHTML="Manage Trade";
	
else if(pageName=="../Admin/ManageChain.jsp")
	document.getElementById('header_label').innerHTML="Manage Chain";

else if(pageName=="../Admin/ManageRetailClient.jsp")
	document.getElementById('header_label').innerHTML="Manage Retail Client";

else if(pageName=="../Admin/ManageBrand.jsp")
	document.getElementById('header_label').innerHTML="Manage Brand";
	
else if(pageName=="../Admin/ManageDepot.jsp")
	document.getElementById('header_label').innerHTML="Manage Depot";

else if(pageName=="../Admin/ManageStoreStatus.jsp")
	document.getElementById('header_label').innerHTML="Manage Store Status";

else if(pageName=="../Admin/ManageStore.jsp")
	document.getElementById('header_label').innerHTML="Manage Store";
	
/* Misc Master  */
   
 if(pageName=="../Admin/ManageUnitView.jsp")
	 document.getElementById('header_label').innerHTML="Manage Units";

 else if(pageName=="../Admin/ManageCourierView.jsp")
 	document.getElementById('header_label').innerHTML="Manage Courier";

/* else if(pageName=="../Admin/ManageAssemblyUnitMasterView.jsp")
 	document.getElementById('header_label').innerHTML="Manage Outsource Factory ";*/
 else if(pageName=="../Admin/manage_associate.jsp")
	 	document.getElementById('header_label').innerHTML="Manage Associate";


/* Manage Project Aspects */
 
   
 if(pageName=="../Admin/ManageMeasurementStatus.jsp")
	 document.getElementById('header_label').innerHTML="Manage Measurement Status";

 else if(pageName=="../Admin/ManageJobCardStatus.jsp")
 	document.getElementById('header_label').innerHTML="Manage Job Card Status";

 else if(pageName=="../Admin/ManageProjectScopeView.jsp")
 	document.getElementById('header_label').innerHTML="Project Scope Master";
 	
 else if(pageName=="../Admin/ManageProjectStatusView.jsp")
	 	document.getElementById('header_label').innerHTML="Project Status Master";


/* Connect Files */
if(pageName=="../Connect/CreateProject.jsp")
document.getElementById('header_label').innerHTML="Create Project";

else if(pageName=="../Connect/Connect.jsp")
document.getElementById('header_label').innerHTML="Search Project";

else if(pageName=="../Connect/OpenProject.jsp")
document.getElementById('header_label').innerHTML="Recent Project";


/* RFPR Files */


document.getElementById('frame').src=pageName;



	
}
</script>
</head>
<!------JSP Scripting ------>
<%-- <%
 String username=(String)session.getAttribute("username");
if(username==null){
	{
		response.sendRedirect("login.jsp");
	}
} 
%> --%>

<!-----   Body   ----->
<body>
<!-- Header -->
<div id="container">

	<!-- Header -->
<div id="header">

	<font id="header_label" style="color: white; font-size:15px;font-weight:bold;text-align:center ">
		<%
			String title = request.getParameter("title");			
			if (title != null)
				out.println(title);	
			else
				out.println ("FD Connect");
				
		%>
	</font>
	
</div>
	<!-- End Header -->

	<!-- Vertical Menu -->
<div id="menu_div">

<ul class="menu" id="menu">

<%if(Role==null){
	response.sendRedirect("../Login.jsp");
}
else if(Role.equals(RolesConstants.GROUP_HEAD)){
		%>
		<li><a href="../Common/Home.jsp" class="menulink">Home</a></li>
		<li> <a href="#" class="menulink">Connect</a>
			<ul>
				<li> <a href="#" class="sub">Project</a>
					<ul>
						<li class="topline">
						<a href="../Connect/CreateProject.jsp">Create Project</a></li>
						<li><a href="../Connect/Connect.jsp">Search Projects</a></li>
						<li><a href="../Connect/OpenProject.jsp" >Recent Projects</a></li>
					</ul>
				</li>
			
			
			</ul>
		</li>
		<li><a href="#" class="menulink">Cost Estimation</a>
			<ul>
			
			   <!-- <li><a href="../RFPR/CreateBomView.jsp" >Create BOM</a></li> -->
			    <li><a href="../RFPR/CreateBomView.jsp" >Create Cost Estimation</a></li>
			   <!-- <li><a href="../RFPR/SearchBomView.jsp" >Consolidate BOM</a></li>  -->
			
			   			
			</ul>	
		</li>
		
		<li><a href="../Logout.jsp" class="menulink">Logout</a></li>	
		<%
	}else if(Role.equals(RolesConstants.CENTRAL_CO_ORDINATOR)){
		%>
		<li><a href="../Common/Home.jsp" class="menulink">Home</a></li>
		<li>	<a href="#" class="menulink">Admin</a>
		<ul>
			<li>
				<a href="#" class="sub">Manage Locations</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageCountryView.jsp">Country</a></li>
					<li><a href="../Admin/ManageRegionView.jsp">Region</a></li>
					<li><a href="../Admin/ManageStateView.jsp">State</a></li>
					<li><a href="../Admin/ManageCityView.jsp">City</a></li>
					<li><a href="../Admin/ManageTownView.jsp">Town</a></li>
					<li><a href="../Admin/ManageAreaView.jsp">Area</a></li>
					
				</ul>
			</li>
			
			<li>
				<a href="#" class="sub">Manage Material</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageMaterialGroupView.jsp"">Material Group</a></li>
					<li><a href="../Admin/ManageMaterialSubGroupView.jsp">Material Sub Group</a></li>
					<li><a href="../Admin/ManageMaterialTypeView.jsp" >Material Type</a></li>
					<li><a href="../Admin/ManageMaterialView.jsp">Material</a></li>
					<li><a href="../Admin/ManageMaterialElementView.jsp">Element Type</a></li>
					<li><a href="../Admin/ManageMaterialElementStatusView.jsp">Element Type Status</a></li>
             <li><a href="../Admin/ManageMaterialComponentView.jsp">Component</a></li> 
                    <li><a href="../Admin/ManageCostType.jsp">Cost Type</a></li>
                    <li><a href="../Admin/ManageCostItem.jsp">Cost Item</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage FD</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageFdOrgView.jsp">FD Organisation</a></li>
					<li><a href="../Admin/ManageFdHubView.jsp">FD Hub</a></li>
					<li><a href="../Admin/ManageFdDivisionView.jsp" >FD Division</a></li>
					<li><a href="../Admin/ManageFdDepartmentView.jsp" >FD Department</a></li>
					<li><a href="../Admin/ManageFdOrgEmployeeView.jsp" >FD Employee</a></li>
                    <li><a href="../Admin/ManageFdOrgEmployeeTypeView.jsp" >FD Employee Type</a></li>
                    <li><a href="../Admin/ManageFdOrgRoleView.jsp" >FD Role</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Client</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageClient.jsp" >Client</a></li>
					<li><a href="../Admin/ManageBrandCategory.jsp">Brand Category</a></li>
					<li><a href="../Admin/ManageTrade.jsp">Trade</a></li>
					<!--  <li><a href="../Admin/ManageRetailClient.jsp">Retail Client</a></li>-->
					<li><a href="../Admin/ManageChain.jsp">Chain</a></li>
					<li><a href="../Admin/ManageBrand.jsp">Brand</a></li>
					<li><a href="../Admin/ManageDepot.jsp">Depot</a></li>
					<li><a href="../Admin/ManageStoreStatus.jsp">Store Status</a></li>
					<li><a href="../Admin/ManageStore.jsp">Store</a></li>
					<!--<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Depot</a></li>
					<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Store</a></li>
					
					
					
				--></ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Document</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageDocumentType.jsp" >Document Type</a></li>
				<!--	<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')">Document Status</a></li>
				
					
					
					
				--></ul>
			</li>
			<li>

                   <a href="#" class="sub">Misc Masters</a>
                   <ul>
                            <li><a href="../Admin/ManageUnitView.jsp">Manage Units</a></li>
                            <li class="topline"><a href="../Admin/ManageCourierView.jsp" >Manage Courier</a></li>
                            <!-- <li><a href="../Admin/ManageAssemblyUnitMasterView.jsp">Manage Outsource Factory</a></li> -->
                            <li><a href="../Admin/manage_associate.jsp">Manage Associate</a></li>
                   </ul>
            </li>
       
            <li>
                   <a href="#" class="sub">Manage Project Aspects</a>
                   <ul>
                           <li><a href="../Admin/ManageMeasurementStatus.jsp">Measurement Status </a></li>
                           <li><a href="../Admin/ManageJobCardStatus.jsp">Job Card Status </a></li>
                           <li><a href="../Admin/ManageProjectScopeView.jsp">Project Scope Master </a></li>
                           <li class="topline"><a href="../Admin/ManageProjectStatusView.jsp" >Project Status Master</a></li>
                   </ul>
            </li>
			
		</ul>
	</li>
		<li> <a href="#" class="menulink">Connect</a>
			<ul>
				<li> <a href="#" class="sub">Project</a>
					<ul>
						<li class="topline">
						<a href="../Connect/CreateProject.jsp">Create Project</a></li>
						<li><a href="../Connect/Connect.jsp">Search Projects</a></li>
						<li><a href="../Connect/OpenProject.jsp" >Recent Projects</a></li>
					</ul>
				</li>
			
		
			
			
		
		
		<li><a href="../Logout.jsp" class="menulink">Logout</a></li>	
		<%
	}else if(Role.equals(RolesConstants.IMG_CO_ORDINATOR)){
		%>
		<li><a href="../Common/Home.jsp" class="menulink">Home</a></li>
		<li>	<a href="#" class="menulink">Admin</a>
		<ul>
			<li>
				<a href="#" class="sub">Manage Locations</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageCountryView.jsp">Country</a></li>
					<li><a href="../Admin/ManageRegionView.jsp">Region</a></li>
					<li><a href="../Admin/ManageStateView.jsp">State</a></li>
					<li><a href="../Admin/ManageCityView.jsp">City</a></li>
					<li><a href="../Admin/ManageTownView.jsp">Town</a></li>
					<li><a href="../Admin/ManageAreaView.jsp">Area</a></li>
					
				</ul>
			</li>
			
			<li>
				<a href="#" class="sub">Manage Material</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageMaterialGroupView.jsp"">Material Group</a></li>
					<li><a href="../Admin/ManageMaterialSubGroupView.jsp">Material Sub Group</a></li>
					<li><a href="../Admin/ManageMaterialTypeView.jsp" >Material Type</a></li>
					<li><a href="../Admin/ManageMaterialView.jsp">Material</a></li>
					<li><a href="../Admin/ManageMaterialElementView.jsp">Element Type</a></li>
					<li><a href="../Admin/ManageMaterialElementStatusView.jsp">Element Type Status</a></li>
             <li><a href="../Admin/ManageMaterialComponentView.jsp">Component</a></li> 
                    <li><a href="../Admin/ManageCostType.jsp">Cost Type</a></li>
                    <li><a href="../Admin/ManageCostItem.jsp">Cost Item</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage FD</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageFdOrgView.jsp">FD Organisation</a></li>
					<li><a href="../Admin/ManageFdHubView.jsp">FD Hub</a></li>
					<li><a href="../Admin/ManageFdDivisionView.jsp" >FD Division</a></li>
					<li><a href="../Admin/ManageFdDepartmentView.jsp" >FD Department</a></li>
					<li><a href="../Admin/ManageFdOrgEmployeeView.jsp" >FD Employee</a></li>
                    <li><a href="../Admin/ManageFdOrgEmployeeTypeView.jsp" >FD Employee Type</a></li>
                    <li><a href="../Admin/ManageFdOrgRoleView.jsp" >FD Role</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Client</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageClient.jsp" >Client</a></li>
					<li><a href="../Admin/ManageBrandCategory.jsp">Brand Category</a></li>
					<li><a href="../Admin/ManageTrade.jsp">Trade</a></li>
					<!--  <li><a href="../Admin/ManageRetailClient.jsp">Retail Client</a></li>-->
					<li><a href="../Admin/ManageChain.jsp">Chain</a></li>
					<li><a href="../Admin/ManageBrand.jsp">Brand</a></li>
					<li><a href="../Admin/ManageDepot.jsp">Depot</a></li>
					<li><a href="../Admin/ManageStoreStatus.jsp">Store Status</a></li>
					<li><a href="../Admin/ManageStore.jsp">Store</a></li>
					<!--<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Depot</a></li>
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
                            <li><a href="../Admin/ManageUnitView.jsp">Manage Units</a></li>
                            <li class="topline"><a href="../Admin/ManageCourierView.jsp" >Manage Courier</a></li>
                            <!-- <li><a href="../Admin/ManageAssemblyUnitMasterView.jsp">Manage Outsource Factory</a></li> -->
                            <li><a href="../Admin/manage_associate.jsp">Manage Associate</a></li>
                   </ul>
            </li>
       
            <li>
                   <a href="#" class="sub">Manage Project Aspects</a>
                   <ul>
                           <li><a href="../Admin/ManageMeasurementStatus.jsp">Measurement Status </a></li>
                           <li><a href="../Admin/ManageJobCardStatus.jsp">Job Card Status </a></li>
                           <li><a href="../Admin/ManageProjectScopeView.jsp">Project Scope Master </a></li>
                           <li class="topline"><a href="../Admin/ManageProjectStatusView.jsp" >Project Status Master</a></li>
                   </ul>
            </li>
			
		</ul>
	</li>
		<li> <a href="#" class="menulink">Connect</a>
			<ul>
				<li> <a href="#" class="sub">Project</a>
					<ul>
						<li class="topline">
						<a href="../Connect/CreateProject.jsp">Create Project</a></li>
						<li><a href="../Connect/Connect.jsp">Search Projects</a></li>
						<li><a href="../Connect/OpenProject.jsp" >Recent Projects</a></li>
					</ul>
				</li>
			
		
		<li><a href="#" class="menulink">Cost Estimation</a>
			<ul>
			   <li><a href="../RFPR/CreateBomView.jsp" >Create Cost Estimation</a></li>
			  
		</li>
		
		<li><a href="../Logout.jsp" class="menulink">Logout</a></li>	
		<%
	}else if(Role.equals(RolesConstants.MMMP_CO_ORDINATOR)){
		%>
		<li><a href="../Common/Home.jsp" class="menulink">Home</a></li>
		<li>	<a href="#" class="menulink">Admin</a>
		<ul>
			<li>
				<a href="#" class="sub">Manage Locations</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageCountryView.jsp">Country</a></li>
					<li><a href="../Admin/ManageRegionView.jsp">Region</a></li>
					<li><a href="../Admin/ManageStateView.jsp">State</a></li>
					<li><a href="../Admin/ManageCityView.jsp">City</a></li>
					<li><a href="../Admin/ManageTownView.jsp">Town</a></li>
					<li><a href="../Admin/ManageAreaView.jsp">Area</a></li>
					
				</ul>
			</li>
			
			<li>
				<a href="#" class="sub">Manage Material</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageMaterialGroupView.jsp"">Material Group</a></li>
					<li><a href="../Admin/ManageMaterialSubGroupView.jsp">Material Sub Group</a></li>
					<li><a href="../Admin/ManageMaterialTypeView.jsp" >Material Type</a></li>
					<li><a href="../Admin/ManageMaterialView.jsp">Material</a></li>
					<li><a href="../Admin/ManageMaterialElementView.jsp">Element Type</a></li>
					<li><a href="../Admin/ManageMaterialElementStatusView.jsp">Element Type Status</a></li>
             <li><a href="../Admin/ManageMaterialComponentView.jsp">Component</a></li> 
                    <li><a href="../Admin/ManageCostType.jsp">Cost Type</a></li>
                    <li><a href="../Admin/ManageCostItem.jsp">Cost Item</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage FD</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageFdOrgView.jsp">FD Organisation</a></li>
					<li><a href="../Admin/ManageFdHubView.jsp">FD Hub</a></li>
					<li><a href="../Admin/ManageFdDivisionView.jsp" >FD Division</a></li>
					<li><a href="../Admin/ManageFdDepartmentView.jsp" >FD Department</a></li>
					<li><a href="../Admin/ManageFdOrgEmployeeView.jsp" >FD Employee</a></li>
                    <li><a href="../Admin/ManageFdOrgEmployeeTypeView.jsp" >FD Employee Type</a></li>
                    <li><a href="../Admin/ManageFdOrgRoleView.jsp" >FD Role</a></li>
				</ul>
			</li>
			
				<li>
				<a href="#" class="sub">Manage Client</a>
				<ul>
					<li class="topline"><a href="../Admin/ManageClient.jsp" >Client</a></li>
					<li><a href="../Admin/ManageBrandCategory.jsp">Brand Category</a></li>
					<li><a href="../Admin/ManageTrade.jsp">Trade</a></li>
					<!--  <li><a href="../Admin/ManageRetailClient.jsp">Retail Client</a></li>-->
					<li><a href="../Admin/ManageChain.jsp">Chain</a></li>
					<li><a href="../Admin/ManageBrand.jsp">Brand</a></li>
					<li><a href="../Admin/ManageDepot.jsp">Depot</a></li>
					<li><a href="../Admin/ManageStoreStatus.jsp">Store Status</a></li>
					<li><a href="../Admin/ManageStore.jsp">Store</a></li>
					<!--<li><a href="#" onclick="frameCall('../Admin/ManageRegionView.jsp')" >Depot</a></li>
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
                            <li><a href="../Admin/ManageUnitView.jsp">Manage Units</a></li>
                            <li class="topline"><a href="../Admin/ManageCourierView.jsp" >Manage Courier</a></li>
                            <!-- <li><a href="../Admin/ManageAssemblyUnitMasterView.jsp">Manage Outsource Factory</a></li> -->
                            <li><a href="../Admin/manage_associate.jsp">Manage Associate</a></li>
                   </ul>
            </li>
       
            <li>
                   <a href="#" class="sub">Manage Project Aspects</a>
                   <ul>
                           <li><a href="../Admin/ManageMeasurementStatus.jsp">Measurement Status </a></li>
                           <li><a href="../Admin/ManageJobCardStatus.jsp">Job Card Status </a></li>
                           <li><a href="../Admin/ManageProjectScopeView.jsp">Project Scope Master </a></li>
                           <li class="topline"><a href="../Admin/ManageProjectStatusView.jsp" >Project Status Master</a></li>
                   </ul>
            </li>
			
		</ul>
	</li>
		<li> <a href="#" class="menulink">Connect</a>
			<ul>
				<li> <a href="#" class="sub">Project</a>
					<ul>
						<li class="topline">
						<a href="../Connect/CreateProject.jsp">Create Project</a></li>
						<li><a href="../Connect/Connect.jsp">Search Projects</a></li>
						<li><a href="../Connect/OpenProject.jsp" >Recent Projects</a></li>
					</ul>
				</li>
			
	
		
		
		<li><a href="../Logout.jsp" class="menulink">Logout</a></li>	
		<%
	}else if(Role.equalsIgnoreCase(RolesConstants.REGIONAL_CO_ORDINATOR)){
		%>
		<li><a href="../Common/Home.jsp" class="menulink">Home</a></li>
		<li> <a href="#" class="menulink">Connect</a>
			<ul>
			
		</ul>
		</li>
		
		<li><a href="../Logout.jsp" class="menulink">Logout</a></li>	
		<%
	}else if(Role.equalsIgnoreCase(RolesConstants.FIELD_CO_ORDINATOR)){
		%>
		<li><a href="../Common/Home.jsp" class="menulink">Home</a></li>
		<li> <a href="#" class="menulink">Connect</a>
			
		</li>
		<li><a href="../Logout.jsp" class="menulink">Logout</a></li>	
		<%
	}
%>		
	


</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>

	</div>
<div class="clearfix"></div>

	
<!-- End Menu -->

	<!-- body /main content-->
	 <div id="main_content" >
	
<div id="frame_div">
	
<!--<iframe id="frame" src="../Admin/Blank.jsp" name="iframe_a" height="780" width="1380" frameborder="0"></iframe>-->

</div>
	
		
	</div> 
	
	
</div>
</body>
</html>