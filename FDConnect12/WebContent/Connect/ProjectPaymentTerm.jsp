
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1);

String id=request.getParameter("id");
System.out.println("Project ID in Payment Term Days -- "+id);
%>
<%
	session.setAttribute("project_id",id);
	System.out.println("Project ID"+id);
	ProjectServices services = new ProjectServices();
	//Pass project Details
	String header_details = services.Project_header_details(Integer.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	System.out.println("Project_data---->"+header_details);
	
	//Pass project ID for Payment Details
	String payment_term_data=services.check_TermDays_details(Integer.parseInt(id));
	System.out.println("Payment_Term data---->"+payment_term_data);
	String[] payment_term =payment_term_data.split(Constants.columnSeperator);
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
<script type="text/javascript">

function headerSet(){	
	parent.window.document.getElementById('header_label').innerHTML="Project Term Days";
}
	
</script>
<script type="text/javascript" src="../js/Connect/details_project.js">
</script>
<!--  For new tab menu -->
<link rel="stylesheet" type="text/css" href="../css/Connect/style.css" />


<link rel="stylesheet" type="text/css" href="../css/Connect/details_project.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>


<script type="text/javascript" src="../js/Connect/connet_js_entire.js"></script>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<!-- Drop Do0wn -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />
<script type="text/javascript" src="../js/Connect/connect.js"></script>



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



</head>
<body>

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Project Payment Term"/>
	</jsp:include>
</div>

<div id="main_content_element">
<!-- body /main content-->
		<div id="maincontent">
			<table style="width: 100%; text-align: center;">
<tr>


<td width="100%">
					
<div id="maincontent_element" style="font: calibri;">
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
 <!--    <li><a href="#" onclick="#" class="submenulink">Document Library</a></li>
    	<li><a href="#" onclick="#" class="submenulink">Accountability</a></li> 
   -->
    
       <li><a href="DocumentLibrary.jsp?projectId=<%=id%>" 
		class="submenulink">Document Library</a></li><!--
    <li><a href="#" onclick="project_accountablity(<%=id%>);" class="submenulink">Accountability</a></li> 

	--><li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element & Scope</a></li>
	<!--<li><a href="#" class="submenulink">Date Plans</a></li>
	<li><a href="#" class="submenulink">Rate Card</a></li>-->
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a></li>
	<!-- <li><a href="#" class="submenulink">Quantities</a></li> -->
<!-- 	<li><a href="#" class="submenulink">Financial</a></li>
	<li><a href="#" onclick="#" class="submenulink">Approval</a></li> -->
	
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
	<!--  <li><a href="#" onclick="project_approval(<%=id%>);" class="submenulink">Approval</a></li>  --> 
	
	<!-- <li><a href="#" class="submenulink" onclick="#">Closure</a></li>  -->
</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>	
</div>				
	
	
<br>
<br>
	<% if(payment_term_data.equals("NO DATA")){
		System.out.println("IN NO DATA FORM");
		%>
	<form name="mytermForm" id="mytermForm" >
			<h3 style="color: #39939C;">Payment Term Details</h3>
				<table align="center" style="width: 80%; text-align: center;">
									<tr>
										<td width="20%">From the Date of Project Approval </td>
										<td>
										<input type="text" id="prjct_approval"	name="prjct_approval"
										size="12" onmouseover="date_prjct_approval()">
										</td>
										
										<td width="30%">From the Date of Implementation </td>
										<td><input type="text" name="prjct_implt" id="prjct_implt"
											size="12" onmouseover="date_prjct_implt()"></td>
									</tr>
									<tr>		
										<td width="30%">From the Date of Dispatch </td>
										<td ><input type="text" name="prjct_dispatch" id="prjct_dispatch"
											size="12" onmouseover="date_prjct_dispatch()"></td>
											
										<td width="30%">From the Date of Docking </td>
										<td><input type="text" name="prjct_docking" id="prjct_docking"
											size="12" onmouseover="date_prjct_docking()"></td>
									</tr>
								</table>
					<table align="center" style="width: 80%; text-align: center;">
									<tr>
									<td width="80%">
									</td>
									<td width="20%">
			<input type="button" value="Save Details" onclick="return save_payment_term(<%=id%>);"  class="btn">
									</td>
									</tr>
			</table>
	</form>
	<%}else{
		System.out.println("IN DATA FORM");
		
		%>
		<form name="mytermForm" id="mytermForm">
				<h3 style="color: #39939C;">Payment Term Details</h3>
				<table align="center" style="width: 90%; text-align: center;">
									<tr>
										<td width="20%">From the Date of Project Approval </td>
										<td><input type="text" name="prjct_approval" id="prjct_approval" value="<%=payment_term[0]%>"
											size="12" onmouseover="date_prjct_approval()" disabled="disabled"></td>
									
										<td width="30%">From the Date of Implementation </td>
										<td><input type="text" name="prjct_implt" id="prjct_implt" value="<%=payment_term[1] %>"
											size="12" onmouseover="date_prjct_implt()" disabled="disabled"></td>
									</tr>
									<tr>			
										<td width="30%">From the Date of Dispatch </td>
										<td ><input type="text" name="prjct_dispatch" id="prjct_dispatch" value="<%=payment_term[2] %>"
											size="12" onmouseover="date_prjct_dispatch()" disabled="disabled"></td>
											
										<td width="30%">From the Date of Docking </td>
										<td><input type="text" name="prjct_docking" id="prjct_docking" value="<%=payment_term[3] %>"
											size="12" onmouseover="date_prjct_docking()" disabled="disabled"></td>
											
									</tr>
									</table>
									<table align="center" style="width: 90%; text-align: center;">
									<tr>
									<td width="80%">
									</td>
									<td width="20%">
									<input type="button" id="edit_buttons" name="edit_buttons" value="Edit Details" onclick="editPayment();" class="btn"/>
									<input type="button" id="update_buttons" value="Update Details"
									 onclick="return update_payment_term(<%=id%>)" style="visibility: hidden;">
									</td>
									</tr>
			</table>
	</form>
	<% } %>
			
					
	
	</div>
</td>
					
				</tr>
			</table>
		</div>
		</div>
		<!-- End body /main content-->
		<!-- Footer -->
		<!-- <div id="footer">
			<font style="font-size: 12px" color="#000">Powered by</font>&nbsp;&nbsp;&nbsp;
			<img alt="Aseema Softnet Technologies" src="../images/a_logo.png"
				height="20px" width="40px" draggable="false" /> <a
				href="http://www.aseema.in"><font color="#A0A0A0"
				style="font-size: 13px; font-weight: bold;"> Aseema Softnet
					Technologies Private Limited</font> </a>
		</div> -->
	</div>
	<!-- admindiv -->
</body>
</html>