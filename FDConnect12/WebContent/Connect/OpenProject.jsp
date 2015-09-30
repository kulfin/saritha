<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	System.out.println("OPEN PROJECT");
	
	String employee_Id=(String)session.getAttribute("employee_Id");
	if(employee_Id==null){
		response.sendRedirect("../Login.jsp");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- <link rel="stylesheet" type="text/css" href="../css/Connect/project_admin.css" /> -->

<!-- Side menu CSS  -->
<!-- <link rel="stylesheet" type="text/css" 	href="../css/Connect/horizontal_menu.css" /> -->
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<!-- CSS for Drop Down Tabs Menu #2 -->
<!-- <link rel="stylesheet" type="text/css"	href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript" src="../css/Common/dropdowntabfiles/dropdowntabs.js">
</script> -->
<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script>

<!-- Drop Do0wn -->
<link rel="stylesheet" type="text/css" href="../css/Common/style.css" />

<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>-->
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />
<script type="text/javascript" src="../js/Connect/connect.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>




<style type="text/css" media="screen">
@import "test/demo_page.css";

@import "test/demo_table.css";
</style>
<script type="text/javascript" src="../js/Connect/jquery.js"></script>
<script type="text/javascript"
	src="../js/Connect/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').dataTable();
			} );
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

</script>

<script type="text/javascript">
		
	function open_details(num) {
		//alert(num+"===num");
		parent.window.document.getElementById('header_label').innerHTML = "Project Page";
		
		ajaxcheck();
		ajaxRequest.onreadystatechange = function(){
	  		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				document.getElementById('main_content_element').innerHTML = ajaxRequest.responseText;
			}
		};

		ajaxRequest.open("GET","DetailsProject.jsp?id="+ num+"&flag=false",true);	
		ajaxRequest.send(null);		
	}
</script>


</head>


<body onload="getFDMgr_Name();" style="width: 100%; height: 100%;">

<!-- div to include header -->
<div><jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Recent Projects" />
</jsp:include></div>

<!-- Header -->
<div id="admindiv" style="position: absolute; height: 97%; width: 98%;">

<!-- body /main content-->
<div id="main_content_element">
<table style="width: 100%; text-align: center;">
	<tr>
		<td width="100%">

		<div style="margin: 50px 218px 0px 155px;" align="center">
		<h2 style="color: #FFFFFF; background-color: #e0e0e0;">RECENT
		PROJECTS</h2>
		<table class="display" id="example">
			<thead>
				<tr>
					<th align="left">Project No.</th>
					<th align="left" >Project Name</th>
					<th align="left" >Client Name</th>
					<th align="left" >FD Division</th>

				</tr>
			</thead>
			<%
				ProjectServices services = new ProjectServices();
				String result = services.Project_open();
				if (result == null) {
			%>
			<tbody>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>NO DATA</td>
				</tr>
			</tbody>
			<%
				} else {
			%>

			<tbody>

				<%
					String[] row_data = result.split(Constants.rowSeperator);
						for (int i = 0; i < row_data.length; i++) {
							String[] column_data = row_data[i]
									.split(Constants.columnSeperator);
							
				%>
				<tr class="gradeA" onclick="open_details(<%=column_data[4]%>)">

					<td><%=column_data[0]%></td>
					<td><%=column_data[1]%></td>
					<td><%=column_data[2]%></td>
					<td><%=column_data[3]%></td>
				</tr>

				<%
					}
				%>

			</tbody>


			<%
				}
			%>

		</table>

		<%-- <table id="table_id"  style="width: 100%;text-align: left;border: 1px solid;
	border-collapse:separate; border-spacing: 3px;">
		<tr>
			<td width="22%" id="td_id">
			<font  size="2">Project No.</font>	
			</td>
			
			<td width="30%" id="td_id">
			<font  size="2">Project Name</font>			
			</td>
			
			<td width="27%" id="td_id">
			<font  size="2">Client Name</font>	
			</td>
			
			<td width="30%" id="td_id">
			<font  size="2">FD Division</font>	
			</td>			
		</tr>
		<%
		
		
		ProjectServices services =new ProjectServices();
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
		
		<tr></tr>
		<tr>
		<td><a href="#" type="hidden" onclick="open_details(<%=column_data[4]%>)">
			<font  size="2"><%=column_data[0]%></font>	
		</a>
		</td>	
		
		<td>
			<font  size="2"><%=column_data[1]%></font>	
		</td>	
			
		<td>
			<font  size="2"><%=column_data[2]%></font>	
		</td>
		
		<td >
			<font  size="2"><%=column_data[3]%></font>	
		</td>	
						
		</tr>
		<%
			}
		}
		%>
		
	</table>
	 --%></div>

		</td>
		<!-- <td width="15%">
					</td> -->
	</tr>
</table>
</div>

</div>
<!-- End body /main content-->
<!-- admindiv -->
</body>
</html>