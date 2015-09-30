<%@page import="com.fd.Connect.DropDown"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>


<%! String jobCardNo=null;  %>
<%! String jobCardDate=null;  %>
<%! String id=null; %>
<%! int i=0; %>
<%! int project_element_mapping_id=0; %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>

<%
	System.out.println("Store_Project_List");
	String employee_Id=(String)session.getAttribute("employee_Id");
	if(employee_Id==null){
		response.sendRedirect("../Login.jsp");
	}
	 id = request.getParameter("id");
	//System.out.println("id --->" + id);
	session.setAttribute("project_id", id);
	System.out.println("Project ID --->  " + id);
	ProjectServices services = new ProjectServices();
	DropDown drop = new DropDown();
	String[] trade_list = drop.drop_down_trade().split(
			Constants.columnSeperator);
	String[] chain_list = drop.drop_down_chain().split(
			Constants.columnSeperator);
	String[] region_list = drop.drop_down_region().split(
			Constants.columnSeperator);
	String[] state_list = drop.drop_down_state().split(
			Constants.rowSeperator);
	String[] city_list = drop.drop_down_city().split(
			Constants.columnSeperator);
	String[] town_list = drop.drop_down_town().split(
			Constants.columnSeperator);
	String[] area_list = drop.drop_down_area().split(
			Constants.columnSeperator);
	String[] country_list = drop.drop_down_country().split(
			Constants.rowSeperator);
	String[] fd_fub_list = drop.drop_down_fdhub().split(
			Constants.columnSeperator);
	String header_details = services.Project_header_details(Integer
			.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	
	int projID=Integer.parseInt(id);
	String[]project_list=drop.drop_down_project_list(projID).split(
			Constants.rowSeperator);
	String[] fd_Store_status_list = drop.drop_down_store_status_list().split(
			Constants.rowSeperator);
	
%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#data {
	color: #000000;
	text-align: left;
}

#td_green {
	color: #39939C;
}

.reduce_inputTag {
	width: 100px;
}

.reduce_comboBox {
	width: 70px;
}

/**.dataTables_filter {
	position: absolute;
	width: 18%;
	left:1150px;
	top: -25px;
}**/
</style>


<script type="text/javascript">
function headerSet(){	
	parent.window.document.getElementById('header_label').innerHTML="Manage Stores";
}	
</script>

<link rel="stylesheet" type="text/css"
	href="../css/Connect/details_project.css" />

<link rel="stylesheet" type="text/css"
	href="../css/Connect/project_scope_display.css" />


<script type="text/javascript" src="../js/Connect/storeProjectList.js"></script>

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css"
	href="../css/Common/dropdowntabfiles/bluetabs.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
<script type="text/javascript"
	src="../css/Common/dropdowntabfiles/dropdowntabs.js">
</script>

<!--  For new tab menu -->
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Connect/style.css" />


<!-- Side Menu
<link rel="stylesheet" type="text/css" 	href="../css/Connect/horizontal_menu.css" /> -->

<!-- CheckBox Select -->
<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script>
<script type="text/javascript" src="../js/Connect/drop_downs.js">
</script>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript"
	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js">
</script>

<!-- Drop Do0wn -->
<!-- <script type="text/javascript" src="../js/Common/jquery.min.js"></script> -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/Common/script.js"></script>

<script type="text/javascript" src="../js/Connect/connect.js"></script>


<!--Data Table-->
<link rel="stylesheet" type="text/css" href="test/demo_table.css" />


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


<!-----   Body   ----->
<%-- <body onload="listStore(<%=id%>);hideDivs();"> --%>
<body onload="headerSet();loadStore(<%=id %>);"
	style="width: 0px; margin-left: 5px; background-color: white; overflow: ">

<!-- div to include header -->
<div><jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Stores" />
</jsp:include></div>

<!-- body /main content-->
<div id="maincontent"
	style="font: bold 12px Calibri; height: 630px; width: 1375px;">

<div id="maincontent_element"
	style="height: 100px; width: 1375px; font-family: calibri;">

<div id="Projectdata"
	style="position: relative; background-color: rgba(204, 204, 204, 0.66); height: 95px; -moz-border-radius: 6px; -webkit-border-radius: 6px; border-radius: 6px; font-size: 15px; box-shadow: 0px 0px 4px 3px #cccccc; width: 1300px; margin-left: 5px; font-weight: normal;">
<h2 style="font-weight: bold;">PROJECT HEADER</h2>
<div
	style="display: inline-table; position: absolute; margin-top: -23px; left: 100px; color: darkgray; width: 400px; height: 50px;">
<table style="width: 100%; text-align: left;">
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
<div
	style="left: 970px; position: absolute; display: inline-table; margin-top: -23px; color: darkgray; width: 400px; height: 50px;">
<table style="width: 100%; text-align: left;">
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

<div id="subMenu" style="margin-top: 20px; font-size: 11px;">

<ul class="menu" id="menu">
	<li><a href="#" onclick="project_details(<%=id%>);"
		class="submenulink">Project Header</a></li>
      <li><a href="DocumentLibrary.jsp?projectId=<%=id%>" 
		class="submenulink">Document Library</a></li>
	<!--
					<li><a href="#" onclick="project_accountablity(<%=id%>);"
						class="submenulink">Accountability</a>
					</li>
					-->
	<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>"
		class="submenulink">Element & Scope</a></li>
	<!--
					<li><a href="#" class="submenulink">Date Plans</a>
					</li>
					<li><a href="#" class="submenulink">Rate Card</a>
					</li>
					-->
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a>
	</li>
	<!--  <li><a href="#" class="submenulink">Quantities</a></li> -->
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>"
		class="submenulink">Financial</a></li>
	<!--<li><a href="#" onclick="project_approval(<%=id%>);"
						class="submenulink">Approval</a>
					</li>
					 <li><a href="#" class="submenulink" onclick="#">Closure</a>  </li>  -->
</ul>

</div>
<script type="text/javascript">
function check()
{
	//alert('inside validate');
	var fileName = document.getElementById("file").value;
	//alert("file is" + fileName);
	if(fileName == "")
	{
		alert("Please Select File to Upload");
		file.focus();
		return false;
	}

	var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
    if(ext == "xls" || ext == "xlsx" )
    {
        
   		return true;
    } 
    else
    {
	    alert("Upload Excel files only");
	    file.focus();
	    return false;
    }
}
</script></div>
<!-- /Import From file -->
<div id="import_div"
	style="position: absolute; top: 230px; left: 300px; z-index: 1;">
<form action="../Upload" method="post" enctype="multipart/form-data"
	onsubmit="return check()"><label
	style="font-weight: bold; margin: 10px 0 0 0;">Import Store
Wise Element Type List From File</label> <input type="hidden" value=<%=id %>
	name="ProjectIdForStore" id="ProjectId"></input> <input type="file"
	name="file" size="40" id="file" accept="application/excel" /> <input
	type="submit" value="Upload File" /></form>
</div>


<div id="area_div" style="position: relative; margin-top: 50px;">
<%
					ProjectServices projectServices = new ProjectServices();
					String list_services = projectServices.getProjectStores(id);
					System.out.println("list_stores :: " + list_services);
				%>
<form action="" name="myDisplayForm"><!-- Data Display -->

<div  id="add_button" style="margin-left: -1080px; margin-top: 15px;">
<% 	
							if (!list_services.equalsIgnoreCase(Constants.NO_DATA)) {
							%> <input style="width: 110px;" type="button" value="Add Store"
	onclick="$(this).addStoreDisplay(<%=id%>);"> <% }
							else{
							%> <input style="width: 110px;" type="button" value="Add Store"
	onclick="$(this).newaddDisplay(<%=id%>);"> <% 
							}
							%> <!-- <input style="width: 117px;margin-left: 20px;" type="button" value="Delete Store"
								onclick="deleteStore()"> -->
</div>
<div id="area_detail">
<%
							if (list_services.equalsIgnoreCase(Constants.NO_DATA)) {
								System.out.println("DATA EMPTY");
						%>
<table id="area_detail_table1"
	style="word-spacing: .5px; width: 100%; font-weight: normal; text-align: center; background-color: rgb(255, 255, 255)"
	class="display">
				<thead>
					<tr style="color: #39939C">
						<th>Select</th>
						<th>Edit</th>
						<th>Store</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>JobCard No</th>
						<th>JobCard Date</th>
						<th>Element Name</th>
						<th>Qty / Store</th>
						<th>Store Status</th>
						<th>Hub</th>
						<th>Handled By</th>
						<th>TSI Name</th>
						<th>TSI Phone</th>
					
					
					</tr>
				<thead>
</table>

<%
							} else {
						%>
<table id="example" class="display"
	style="word-spacing: .5px; width: 100%; text-align: left; font-weight: normal; background-color: rgb(255, 255, 255); border: none;">
				<thead>
					<tr style="color: #39939C">
						<th>Select</th>
						<th>Edit</th>
						<th>Store</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>JobCard No</th>
						<th>JobCard Date</th>
						<th>Element Name</th>
						<th>Qty/Store</th>
						<th>Status</th>
						<th>Hub</th>
						<th>Handled By</th>
						<th>TSI Name</th>
						<th>TSI Phone</th>
			
					</tr>
				</thead>
	<tbody>
		<%
									String[] rowStore = list_services.split(Constants.rowSeperator);
									
										for (i = 0; i < rowStore.length; i++) {
											String[] columnSeperator = rowStore[i]
													.split(Constants.columnSeperator);
											if((columnSeperator[1]).equalsIgnoreCase("null") ||(columnSeperator[1]).equalsIgnoreCase("") ){
												columnSeperator[1]="NA";
											}
											if((columnSeperator[2]).equalsIgnoreCase("null") ||(columnSeperator[2]).equalsIgnoreCase("") ){
												columnSeperator[2]="NA";
											}
											if((columnSeperator[3]).equalsIgnoreCase("null") ||(columnSeperator[3]).equalsIgnoreCase("") ){
												columnSeperator[3]="NA";
											}
											if (columnSeperator[4].equalsIgnoreCase("empty") ||(columnSeperator[4]).equalsIgnoreCase("null") ) {
												columnSeperator[4] = "NA";
											}
											if (columnSeperator[5].equalsIgnoreCase("empty") ||(columnSeperator[5]).equalsIgnoreCase("null")) {
												columnSeperator[5] = "NA";
											}
											if (columnSeperator[6].equalsIgnoreCase("0000000000") || columnSeperator[6].equalsIgnoreCase("null")) {
												columnSeperator[6] = "NA";
											}
											if((columnSeperator[7]).equalsIgnoreCase("null") ||(columnSeperator[7]).equalsIgnoreCase("") ){
												columnSeperator[7]="NA";
											}
											if((columnSeperator[9]).equalsIgnoreCase("null") ||(columnSeperator[9]).equalsIgnoreCase("") ){
												columnSeperator[9]="NA";
											}
											if((columnSeperator[10]).equalsIgnoreCase("null") ||(columnSeperator[10]).equalsIgnoreCase("") ){
												columnSeperator[10]="NA";
											}
											if((columnSeperator[11]).equalsIgnoreCase("null") ||(columnSeperator[11]).equalsIgnoreCase("") ){
												columnSeperator[11]="NA";
											}
											if((columnSeperator[12]).equalsIgnoreCase("null") ||(columnSeperator[12]).equalsIgnoreCase("") ){
												columnSeperator[12]="NA";
											}
											if((columnSeperator[13]).equalsIgnoreCase("null") ||(columnSeperator[13]).equalsIgnoreCase("") ){
												columnSeperator[13]="NA";
											}
											if((columnSeperator[14]).equalsIgnoreCase("null") ||(columnSeperator[14]).equalsIgnoreCase("") ){
												columnSeperator[14]="NA";
											}
											if((columnSeperator[15]).equalsIgnoreCase("null") ||(columnSeperator[15]).equalsIgnoreCase("") ){
												columnSeperator[15]="NA";
											}
											if((columnSeperator[16]).equalsIgnoreCase("null") ||(columnSeperator[16]).equalsIgnoreCase("") ){
												columnSeperator[16]="NA";
											}
											
										//	if((columnSeperator[19]).equalsIgnoreCase("null") ||(columnSeperator[19]).equalsIgnoreCase("") ){
										//		columnSeperator[19]="NA";
										//	}
											if((columnSeperator[19]).equalsIgnoreCase("null") ||(columnSeperator[19]).equalsIgnoreCase("") ){
												columnSeperator[19]="NA";
											}
											if((columnSeperator[20]).equalsIgnoreCase("null") ||(columnSeperator[20]).equalsIgnoreCase("") ){
												columnSeperator[20]="NA";
											}
											
											 jobCardNo=columnSeperator[17];
											 jobCardDate=columnSeperator[18];
											 if(jobCardDate.equals("NA")){
												 jobCardDate="NA";
											 }else{
											 	jobCardDate=jobCardDate.substring(0,10);
											 }
											// System.out.println("columnSeperator[21]::::::::::::::::::::::::::::::"+columnSeperator[21]);
											// project_element_mapping_id=Integer.parseInt(columnSeperator[21]);
								%>

		<tr id="<%=columnSeperator[8]%>" class="gradeA">
			<td><input name="chkbox" id=chkbox
				value="<%=columnSeperator[8]%>" type="checkbox"></td>
			<td><a class="edit" href=""
				onclick='return editRecordNew(
			&apos;"+<%=columnSeperator[0]%>+"&apos;,
			&apos;"+<%=columnSeperator[1]%>+"&apos;,&apos;"+<%=columnSeperator[2]%>+"&apos;,
			&apos;"+<%=columnSeperator[3]%>+"&apos;,&apos;"+<%=columnSeperator[4]%>+"&apos;,
			&apos;"+<%=columnSeperator[5]%>+"&apos;,&apos;"+<%=columnSeperator[6]%>+"&apos;,
			&apos;"+<%=columnSeperator[7]%>+"&apos;,&apos;"+<%=columnSeperator[8]%>+"&apos;,
			&apos;"+<%=columnSeperator[9]%>+"&apos;,&apos;"+<%=columnSeperator[10]%>+"&apos;,
			&apos;"+<%=columnSeperator[11]%>+"&apos;,&apos;"+<%=columnSeperator[12]%>+"&apos;,
			&apos;"+<%=columnSeperator[13]%>+"&apos;,&apos;"+<%=columnSeperator[14]%>+"&apos;,
			&apos;"+<%=columnSeperator[15]%>+"&apos;,&apos;"+<%=columnSeperator[16]%>+"&apos;,
			&apos;"+<%=columnSeperator[17]%>+"&apos;,&apos;"+<%=jobCardDate%>+"&apos;,
			&apos;"+<%=columnSeperator[21]%>+");'>
			<img alt="edit" src="../images/edit.png"></img></a></td>
			<%-- <td><%=row_list[0] %></td> --%>
								<td><%=columnSeperator[1]%></td><%--store name--%>
								<td><%=columnSeperator[2]%></td><%--address --%>
								<td><%=columnSeperator[11]%></td><%--state name --%>
								<td><%=columnSeperator[12]%></td><%-- city name--%>
								<td><%=columnSeperator[17]%></td><%--job card no --%>
								<td><%=jobCardDate%></td><%--job card date --%>
								<td><%=columnSeperator[19]%></td><%--project name--%>
								<td><%=columnSeperator[20]%></td><%--quantity --%>
								<td><%=columnSeperator[7]%></td><%--status --%>
								<td><%=columnSeperator[3]%></td><%--fd hub --%>
								<td><%=columnSeperator[4]%></td><%-- handle by--%>
								<td><%=columnSeperator[5]%></td><%--tsi name --%>
								<td><%=columnSeperator[6]%></td><%-- tsi phone--%>
								
			</tr>

		<%
		}
	%>
	</tbody>
</table>
<%
					
							}
						
						%>
</div>
</form>
<!-- Delete Button --> <!-- 	<div id="delete_button"
					style="position: absolute; margin: 10px 0px 0px 0px;"> --> <%-- <input style="width: 140px;" type="button" value="View Store"
						onclick="viewStore(<%=id%>)">   --%> <%--
				<% 	
					if (!list_services.equalsIgnoreCase(Constants.NO_DATA)) {
					%>	
					<input style="width: 140px;"
						type="button" value="Add Store" onclick="$(this).addStoreDisplay(<%=id%>);">
					<% }
					else{
					%>	
						<input style="width: 140px;"
						type="button" value="Add Store" onclick="$(this).newaddDisplay(<%=id%>);">
					<% 
					}
					%>	--%> <!-- </div>  -->


<div id="area_add"
	style="position: absolute; margin-top: 25px; width: 100%; visibility: hidden;">

<div id="insert_div">
<form action="#" name="myForm">

<div
	style="width: 80%; border: 2px solid rgb(119, 119, 136); background-color: rgb(224, 224, 224); margin: 20px 0px 50px 120px; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border-bottom-left-radius: 10px;">
<div
	style="position: relative; margin: 20px 0 0px 30px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">
ADD STORE</div>
<br />
<table
	style="font-size: 12px; text-align: left; width: 97%; margin-left: 15px;">
	<tbody>
		<tr>
			<td style="position: relative; color: rgb(70, 66, 66);">Store
			Name</td>
			<td colspan="2"><input type="text" id="store_name"
				name="store_name" style="width: 220px;" /></td>


			<td
				style="position: relative; color: rgb(70, 66, 66); padding-left: 38px;">
			Store Address</td>
			<td colspan="2"><input type="text" id="store_addr"
				name="store_addr" style="width: 240px; margin-left: -26px;" /></td>


			<td style="position: relative; color: rgb(70, 66, 66);">Status</td>
			
			<td><!-- <input type="text" id="store_status" name="store_status" /> -->
			<div id="store_status_div">
			<select style="width: 100px;" id="store_status">
					
				<%
					for (i = 0; i < fd_Store_status_list.length; i++) {
						String[] statusid_name = fd_Store_status_list[i]
						.split(Constants.columnSeperator);
				%>
				<option value="<%=statusid_name[0]%>"><%=statusid_name[1]%></option>
				<%
						}
				%>
				</select>
			</div>
			</td>

		</tr>

		<tr>

			<td style="position: relative; color: rgb(70, 66, 66);">FD Hub</td>
			<td>
			
			<div id="fd_hub_add">
					<select style="width: 100px;"
						id="fd_hub" name="fd_hub">
						<%
							for (i = 0; i < fd_fub_list.length; i++) {
						%>
						<option value="<%=fd_fub_list[i]%>"><%=fd_fub_list[i]%></option>
						<%
						}
					%>
					</select>
			</div>
			
			</td>


			<td style="position: relative; color: rgb(70, 66, 66);">Handle
			By</td>
			<td><input type="text" id="handle_by" name="handle_by"
				style="width: 140px;" /></td>

			<td style="position: relative; color: rgb(70, 66, 66);">TSI Name</td>
			<td><input type="text" id="tsi_name" name="tsi_name" /></td>


			<td style="position: relative; color: rgb(70, 66, 66);">TSI
			Phone</td>
			<td><input type="text" id="tsi_phone" name="tsi_phone"
				maxlength="12" /></td>

		</tr>

		<tr>
			<td style="position: relative; color: rgb(70, 66, 66);">Chain
			Name</td>
			<td><!-- <input type="text" id="chain_name" name="chain_name" /> -->
			<div id="chain_name_div"><select style="width: 100px;"
				id="chain_name">
				<option value="SELECT">SELECT</option>
				<%
														for (i = 0; i < chain_list.length; i++) {
													%>
				<option value="<%=chain_list[i]%>"><%=chain_list[i]%></option>
				<%
														}
													%>
			</select></div>

			</td>


			<td style="position: relative; color: rgb(70, 66, 66);">TradeName</td>
			<td><!-- <input type="text" id="trade_name" name="trade_name" /> -->
			<div id="trade_name_div"><select style="width: 100px;"
				id="trade_name">
				<option value="SELECT">SELECT</option>
				<%
														for (i = 0; i < trade_list.length; i++) {
													%>
				<option value="<%=trade_list[i]%>"><%=trade_list[i]%></option>
				<%
														}
													%>
			</select></div>
			</td>
			<!-- </tr>
			<tr> -->
			<td style="position: relative; color: rgb(70, 66, 66);">Country</td>
			<td>
			<div id="country_Name_Add">
			<select style="width: 100px;"
				id="country_name" onchange="getRegionOnCountryAdd();">
				<option value="SELECT">SELECT</option>
				<%
											for (i = 0; i < country_list.length; i++) {
												String[] countryid = country_list[i]
												.split(Constants.columnSeperator);
												%>
											<option value="<%=countryid[1]%>"><%=countryid[0]%></option>
				<%
														}
													%>
			</select>
			</div>
			</td>
			<td>
			<div id="projectListDiv"><select style="width: 100px;"
				id="projName" onchange="">
				<!-- getProjCode_Qty(); -->
				<option value="SELECT">SELECT</option>
				<% 
														
							for ( i = 0; i < project_list.length; i++) {
							if(!project_list[i].equals(Constants.NO_DATA)){
							String[] projectName = project_list[i]
							.split(Constants.columnSeperator);
						
				%>
				<option value="<%=projectName[1]%>"><%=projectName[0]%></option>
				<%
						}
					else{
				%>
				<option value="NO DATA">NO Data</option>
				<%
			}
		}
	%>
			</select></div>
			</td>

			<td>
			<div id="quantityDiv"><input type="text" id="qty"
				class="reduce_inputTag"></div>
			</td>


			<td style="position: relative; color: rgb(70, 66, 66);">Region</td>
			<td>
			<div id="region_add"><select style="width: 100px;"
				id="region_name">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td style="position: relative; color: rgb(70, 66, 66);">State</td>

			<td>
			<div id="state_add">
			<select style="width: 100px;" 	id="state_name" onchange="getCityOnStateAdd();">
				<option value="SELECT">SELECT</option>
				<% 
					for ( i = 0; i < state_list.length; i++) {
							if(!state_list[i].equals(Constants.NO_DATA)){
							String[] stateName = state_list[i]
							.split(Constants.columnSeperator);
							
				%>
				<option value="<%=stateName[1]%>"><%=stateName[0]%></option>
				<%
						}
					else{
				%>
				<option value="NO DATA">NO Data</option>
				<%
			}
		}
	%>
				
			</select></div>
			</td>

			<td style="position: relative; color: rgb(70, 66, 66);">City</td>
			<td>
			<div id="city_add"><select style="width: 100px;" id="city_name">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>

			<!-- </tr>

					<tr> -->
			<td style="position: relative; color: rgb(70, 66, 66);">Town</td>
			<td>
			<div id="town_add"><select style="width: 100px;" id="town_name">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>

			<td style="position: relative; color: rgb(70, 66, 66);">Area</td>
			<td>
			<div id="area_add"><select style="width: 100px;" id="area_name">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>
		</tr>

	</tbody>
</table>

<span style="position: relative; display: block; margin: 25px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return insertStore(<%=id%>);" value="Insert Store" /> <span
	style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button" onclick="clear_add_store(<%=id%>);"
	value="Cancel" /> </span> </span></div>
<br>
<br>
<br>
<!-- Import from File :			
						<input type="text" id="browse_id" name="browse_id">
						<input type="button" value="Upload"> --></form>
</div>

<div id="update_div">

<form action="#" name="myUpdateForm">
<div
	style="position: static; width: 80%; height: 100% border :   2px solid rgb(119, 119, 136); background-color: rgb(224, 224, 224); margin: 0px 0px 50px 120px; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border-bottom-left-radius: 10px;"">
<div
	style="position: relative; margin: -245px 0 0px 40px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">
UPDATE STORE</div>
<br />
<table
	style="font-size: 12px; text-align: left; width: 97%; margin-left: 15px;">
	<tbody>

		<tr>
			<td style="position: relative; color: rgb(70, 66, 66);">Store
			Name</td>
			<td colspan="2"><input type="text" id="store_name_u"
				name="store_name_u" style="width: 220px;" /></td>
			<!-- </tr>


			<tr> -->
			<td
				style="position: relative; color: rgb(70, 66, 66); padding-left: 38px;">
			Store Address</td>
			<td colspan="2"><input type="text" id="store_addr_u"
				name="store_addr_u" style="width: 240px; margin-left: -26px;" /></td>


											<td style="position: relative; color: rgb(70, 66, 66);">
												Status
											</td>
											
											<td>
												<div id="store_status_update">
												<select style="width: 100px;" id="store_status_u">
														
												<%
													for (i = 0; i < fd_Store_status_list.length; i++) {
														String[] statusid_name = fd_Store_status_list[i]
														.split(Constants.columnSeperator);
												%>
												<option value="<%=statusid_name[0]%>"><%=statusid_name[1]%></option>
												<%
														}
												%>
												</select>
											</div>
											</td>

			

		</tr>
		<tr>
			<td>
			<div id="projectListDiv_update"><select style="width: 100px;"
				id="projName_u" onchange="">
				<!-- getProjCode_Qty_update(); -->
				<option value="SELECT">SELECT</option>

				<% 
														
														for ( i = 0; i < project_list.length; i++) {
															
															if(!project_list[i].equals(Constants.NO_DATA)){
															String[] projectName = project_list[i]
																	.split(Constants.columnSeperator);
															
															System.out.println("project_list[i]::::"+project_list[i]);
													%>
				<option value="<%=projectName[1]%>"><%=projectName[0]%></option>
				<%
														}
													else{
														%>
				<option value="NO DATA">NO Data</option>
				<%
													}
												}
													%>

			</select></div>
			</td>
			<td style="position: relative; color: rgb(70, 66, 66);">FD Hub</td>
			<td><!-- <input type="text" id="fd_hub" name="fd_hub" /> -->

			<div id="fd_hub_div">
					<select style="width: 100px;"
						id="fd_hub_u" name="fd_hub_u">
						<%
							for (i = 0; i < fd_fub_list.length; i++) {
						%>
						<option value="<%=fd_fub_list[i]%>"><%=fd_fub_list[i]%></option>
						<%
						}
					%>
					</select>
			</div>
			</td>

			<td style="position: relative; color: rgb(70, 66, 66);">Handle
			By</td>
			<td><input type="text" id="handle_by_u" name="handle_by_u" /></td>

			<!-- </tr>
									
						<tr> -->

			<td style="position: relative; color: rgb(70, 66, 66);">TSI Name</td>
			<td><input type="text" id="tsi_name_u" name="tsi_name_u" /></td>


			<td style="position: relative; color: rgb(70, 66, 66);">TSI
			Phone</td>
			<td><input type="text" id="tsi_phone_u" name="tsi_phone_u" /></td>

		</tr>


		<tr>
			<td style="position: relative; color: rgb(70, 66, 66);">Chain
			Name</td>
			<td><!-- <input type="text" id="chain_name" name="chain_name" /> -->
			<div id="chain_name_div"><select style="width: 100px;"
				id="chain_name_u">
				<option value="SELECT">SELECT</option>
				<%
															for ( i = 0; i < chain_list.length; i++) {
																
																
													%>

				<option value="<%=chain_list[i]%>"><%=chain_list[i]%></option>
				<%
															}
														%>
			</select></div>
			</td>


			<td style="position: relative; color: rgb(70, 66, 66);">Trade
			Name</td>
			<td>
			<div id="trade_name_div"><select style="width: 100px;"
				id="trade_name_u">
				<option value="SELECT">SELECT</option>
				<%
														for ( i = 0; i < trade_list.length; i++) {
													%>
				<option value="<%=trade_list[i]%>"><%=trade_list[i]%></option>
				<%
														}
													%>
			</select></div>
			</td>
			<!-- 				</tr>
				<tr>
 -->
			<td style="position: relative; color: rgb(70, 66, 66);">Country</td>
			<td>
			<div id="country_name_div"><select style="width: 100px;"
				id="country_name_u" name="country_name_u"
				onchange="getRegionOnCountryUpdate();">
				<option value="SELECT">SELECT</option>
				<%
														for ( i = 0; i < country_list.length; i++) {
															String[] countryid = country_list[i]
																	.split(Constants.columnSeperator);
															
													%>
				<option value="<%=countryid[1]%>"><%=countryid[0]%></option>
				<%
														}
													%>
			</select></div>
			</td>



			<td style="position: relative; color: rgb(70, 66, 66);">Region</td>
			<td>
			<div id="region_update"><select style="width: 100px;"
				id="region_name_u">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>
		</tr>

		<tr>
			<td style="position: relative; color: rgb(70, 66, 66);">State</td>
			<td>
			<div id="state_update"><select style="width: 100px;" id="state_name_u" onchange="getCityOnStateUpdate();">
				<option value="SELECT">SELECT</option>
				<% 
					for ( i = 0; i < state_list.length; i++) {
							if(!state_list[i].equals(Constants.NO_DATA)){
							String[] stateName = state_list[i]
							.split(Constants.columnSeperator);
							
				%>
				<option value="<%=stateName[1]%>"><%=stateName[0]%></option>
				<%
						}
					else{
				%>
				<option value="NO DATA">NO Data</option>
				<%
			}
		}
	%>
			</select></div>
			</td>
			<td style="position: relative; color: rgb(70, 66, 66);">City</td>
			<td>
			<div id="city_update"><select style="width: 100px;"
				id="city_name_u">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>



			<td style="position: relative; color: rgb(70, 66, 66);">Town</td>
			<td>
			<div id="town_update"><select style="width: 100px;"
				id="town_name_u">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>
			<td style="position: relative; color: rgb(70, 66, 66);">Area</td>
			<td>
			<div id="area_update"><select style="width: 100px;"
				id="area_name_u">
				<option value="NODATA">NO DATA</option>
			</select></div>
			</td>
		</tr>
		<!-- Hidden Field Here for passing the ID -->
		<tr>
			<td><input type="hidden" value="" id="project_store_hidden"
				name="project_store_hidden"></td>
				
			<td><input type="hidden" id="project_element_mapping_id"></td>	
		</tr>
	</tbody>
</table>
<span style="position: relative; display: block; margin: 0px 0 0 20px;">
<input style="width: 140px;" type="button"
	onclick="return updateStore(<%=id%>)" value="Update Store" /> <span
	style="display: inline; margin: 0px 0 0 140px;"> <input
	style="width: 140px;" type="button"
	onclick="clear_update_store(<%=id%>);" value="Cancel" /> </span> </span></div>
</form>
</div>
</div>
</div>

<!-- End body /main content-->
</div>

<script type="text/javascript" src="../js/Connect/jquery.js"></script>
<script type="text/javascript"
	src="../js/Connect/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8">
		var oTable;
		var nRow;
		var nEditing;
		var oTable_new;
		var newRowData; 
		var newRow;
		 function getTableData(){
			 oTable=	$('#example').dataTable({
					//'iDisplayLength': 10,
					"aaSortingFixed": [[3,'asc']],//column number,ascending order
					"bPaginate": false,
					 'bLengthChange': false,
			         "bSortClasses": false,
			         "bProcessing" : true,
			         "bAutoWidth" : true,
		            "aoColumns": [ {"bSortable": false,"sWidth": "5px;"}, {"bSortable": false,"sWidth": "5px;"},
		                           null,null,null,null,null,null,null,null,null,null,null,null,null]
			        });
			// new FixedHeader(oTable);
				
				oTable_new=$('#area_detail_table1').dataTable({
					
					//'iDisplayLength': 10,
					"bFilter": false,
					 "bPaginate": false,
		            'bLengthChange': false,
		            "bSortClasses": false,
		            "bProcessing" : true
		           
		          //  "bProcessing" : true,
			          //  "bDestroy" : true,
			          //  "bAutoWidth" : true,
			        //    "sScrollY" : "400px",
			          //  "sScrollX" : "100%"
				            
					
				});
				nEditing = null;	

				
				$.fn.addStoreDisplay = function(proj_id) {

			     var aiNew = oTable.fnAddData( [ '<input type="checkbox" name="chkbox" id="chkbox">',
					                                '',
					  	                            '','','','','','','','','','','','',''] );
					 nRow = oTable.fnGetNodes( aiNew[0] );
					nEditing = nRow;
					displayInputBox(oTable, nRow,proj_id);
				};

				$.fn.newaddDisplay=function(proj_id){
				//alert("due to onclick");
					 newRowData=oTable_new.fnAddData([ '<input type="checkbox" name="chkbox" id="chkbox">',
					                                '',
					                                '','','','','','','','','','','','','']);
					newRow = oTable_new.fnGetNodes( newRowData[0] );
					nEditing = newRow;
					newAddedAtEmptyTable(oTable_new, newRow,proj_id);
					
			    };
			    
			    }
					
				 
		//new added for edit		
				$('#example a.edit').live('click', function (e) {
					console.log("a.edit");
						e.preventDefault();
						
						/* Get the row as a parent of the link that was clicked on */
						var nRow = $(this).parents('tr')[0];
						
						if ( nEditing !== null && nEditing != nRow ) {
						console.log("if ( nEditing !== null && nEditing != nRow ):::::::::::::");
							/* Currently editing - but not this row - restore the old before continuing to edit mode */
							restoreRow( oTable, nEditing );
							editRow( oTable, nRow );
							nEditing = nRow;
						}
						else if ( nEditing == nRow && this.innerHTML == "Save" ) {
						console.log("else if ( nEditing == nRow && this.innerHTML == Save )::::::::::::::::::::");
							/* Editing this row and want to save it */
						var project_id=<%=id%>;
						saveRow(oTable, nEditing,project_id);
						nEditing = null;
						}
						else if(nEditing == nRow && this.innerHTML == "Cancel" ){
						
							restoreRow( oTable, nEditing );
							nEditing=null;
							}
						else {
							/* No edit in progress - let's start one */
							console.log("inside else:::::::::::::");
							editRow( oTable, nRow );
							nEditing = nRow;
						}
					});

				function restoreRow ( oTable, nRow )
				{
				console.log("restoreRow");
					var aData = oTable.fnGetData(nRow);
					console.log("aData=="+aData);
					var jqTds = $('>td', nRow);
					console.log("restoreRow------------jqTds=="+jqTds);
					for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
						oTable.fnUpdate( aData[i], nRow, i, false );
					}
					
					oTable.fnDraw();
				}
				
				 function newAddedAtEmptyTable(oTable_new, newRow,proj_id){
			
			var aData = oTable_new.fnGetData(newRow);
			var jqInputs = $('input', newRow);
			var jqTds = $('>td', newRow);

			jqTds[1].innerHTML="<a class='' href='' onclick='return insertNewRow("+proj_id+")';>Save</a>/<br><a class='edit' href=''>Cancel</a>" ;
			//store
			jqTds[2].innerHTML = '<input type="text"  name="store_name" id="store_name" class="reduce_inputTag">';

			//Address
			jqTds[3].innerHTML = '<input type="text" name="store_addr" id="store_addr"  class="reduce_inputTag">';

			//state
			jqTds[4].innerHTML = document.getElementById('state_add').innerHTML.toString().trim();

			//city
			jqTds[5].innerHTML = '<div id="city_add"><select style="width: 100px;" id="city_name"><option value="SELECT">SELECT</option> </select></div>';


			//job card no jqTds[6].innerHTML;
			//job card date jqTds[7].innerHTML;
			
			//project Name
			jqTds[8].innerHTML=document.getElementById('projectListDiv').innerHTML.toString().trim();

			
			//quantity
			jqTds[9].innerHTML='<div id="quantityDiv"><input type="text" name="qty" id="qty" style="width:50px;"></div>';

			//status
			jqTds[10].innerHTML = document.getElementById('store_status_div').innerHTML;
			var statusDom = document.getElementById('store_status');
			   for (var i = 0; i <statusDom.options.length; i++) {
				    if("Newly Added"==(statusDom.options[i].text)){
				    	//alert(TradeName.options[i].text);
				    	statusDom.options[i].selected= true;
				    }
			   }
			
			//Hub
			jqTds[11].innerHTML=document.getElementById('fd_hub_add').innerHTML;
			//Handle by
			jqTds[12].innerHTML = '<input type="text" name="handle_by" id="handle_by" class="reduce_inputTag">';
			//TSI Name
			jqTds[13].innerHTML = '<input type="text"  name="tsi_name" id="tsi_name" class="reduce_inputTag">';
			//Tsi phone Number
			jqTds[14].innerHTML = '<input type="text"  name="tsi_phone" id="tsi_phone" maxlength="12" class="reduce_inputTag">';
			

		
			}

			
			function displayInputBox(oTable, nRow,proj_id){
				var aData = oTable.fnGetData(nRow);
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);

				jqTds[1].innerHTML="<a class='' href='' onclick='return insertSt("+proj_id+")';>Save</a>/<br><a class='edit' href=''>Cancel</a>" ;
				//store
				jqTds[2].innerHTML = '<input type="text"  name="store_name" id="store_name" class="reduce_inputTag">';

				//Address
				jqTds[3].innerHTML = '<input type="text" name="store_addr" id="store_addr"  class="reduce_inputTag">';

				//state
				jqTds[4].innerHTML = document.getElementById('state_add').innerHTML.toString().trim();

				//city
				jqTds[5].innerHTML = '<div id="city_add"><select style="width: 100px;" id="city_name"><option value="SELECT">SELECT</option> </select></div>';

				//job card no jqTds[6].innerHTML;
				//job card date jqTds[7].innerHTML;
				
				//project Name	
				jqTds[8].innerHTML=document.getElementById('projectListDiv').innerHTML.toString().trim();

				
				//quantity
				jqTds[9].innerHTML='<div id="quantityDiv"><input type="text" name="qty"  id="qty" style="width:50px;"></div>';

				//status
				jqTds[10].innerHTML = document.getElementById('store_status_div').innerHTML.toString().trim();

				var statusDom = document.getElementById('store_status');
				   for (var i = 0; i <statusDom.options.length; i++) {
					    if("Newly Added"==(statusDom.options[i].text)){
					    	//alert(TradeName.options[i].text);
					    	statusDom.options[i].selected= true;
					    }
				   }

				//Hub
				jqTds[11].innerHTML=document.getElementById('fd_hub_add').innerHTML;
				//Handle by
				jqTds[12].innerHTML = '<input type="text" name="handle_by" id="handle_by" class="reduce_inputTag">';
				//TSI Name
				jqTds[13].innerHTML = '<input type="text"  name="tsi_name" id="tsi_name" class="reduce_inputTag">';
				//Tsi phone Number
				jqTds[14].innerHTML = '<input type="text"  name="tsi_phone" id="tsi_phone" maxlength="12" class="reduce_inputTag">';
				

			/*
				//country
				//	jqTds[11].innerHTML = '<input type="text" id="country_name" class="reduce_inputTag">';
				//region
				//	jqTds[12].innerHTML ='<input type="text" id="region_name" class="reduce_inputTag">';
			
				//town
				jqTds[13].innerHTML = '<input type="text"  class="reduce_inputTag"  id="town_name">';
				//area
				jqTds[14].innerHTML = '<input type="text" class="reduce_inputTag"  id="area_name">';

				//trade		
				jqTds[15].innerHTML =document.getElementById('trade_name_div').innerHTML ;
				var TradeName = document.getElementById('trade_name');
				   for (var i = 0; i <TradeName.options.length; i++) {
					    if("NA"==(TradeName.options[i].text)){
					    	//alert(TradeName.options[i].text);
					    	TradeName.options[i].selected= true;
					    }
				   }
				
				//chain
				jqTds[16].innerHTML = document.getElementById('chain_name_div').innerHTML;
				 var ChainName = document.getElementById('chain_name');
				  for (var i = 0; i <ChainName.options.length; i++) {
					    if("NA"==(ChainName.options[i].text)){
					    //	alert(ChainName.options[i].text);
					    	ChainName.options[i].selected= true;
					    }
				   }
				   
				*/
			}
		
			
			
			
			function insertSt(proj_id){
				//alert("insertSt--"+proj_id);
				return insertStore(oTable,nRow,proj_id);
			}			
			
			function insertNewRow(proj_id){
					//alert("insertStore1--proj="+proj_id+"=otable="+oTable_new+"=newRow="+newRow);
				return insertStoreforEmptyTable(oTable_new,newRow,proj_id);
			}
			
			function editRow ( oTable, nRow )
			{
			
				var aData = oTable.fnGetData(nRow);
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);
				//alert("editRow called..");
				
				jqTds[1].innerHTML ="<a class='edit' href='' >Save</a>/<br><a class='edit' href=''>Cancel</a>";
				//store
				if(aData[2]=="NA"){
					aData[2]="";	
					}
				jqTds[2].innerHTML = '<input type="text" value="'+aData[2]+'" id="store_name_u" class="reduce_inputTag">';

				//Address
				if(aData[3]=="NA"){
					aData[3]="";	
					}
				jqTds[3].innerHTML = '<input type="text" value="'+aData[3]+'" id="store_addr_u" class="reduce_inputTag">';

				//state
				if(aData[4]=="NA"){
					aData[4]="";	
					}
				jqTds[4].innerHTML = document.getElementById('state_update').innerHTML.toString().trim();
				var state_u = document.getElementById('state_name_u');
				 for (i = 0; i <state_u.options.length; i++) {
				    if(aData[4].trim().toUpperCase()==(state_u.options[i].text.trim())){
				    	state_u.options[i].selected= true;
				    }
				} 
				

				//city
				if(aData[5]=="NA"){
					aData[5]="";	
					}
				jqTds[5].innerHTML = '<div id="city_update"><select style="width: 100px;" id="city_name_u"><option value="'+aData[5]+'">'+aData[5]+'</option> </select></div>';

				//jqTds[6] jobcard no
				//jqTds[7] jobcard date

				
				//Element name
				jqTds[8].innerHTML=document.getElementById('projectListDiv_update').innerHTML;
				var proj_name=document.getElementById('projName_u');
				
				for (i = 0; i <proj_name.options.length; i++) {
				    if(aData[8].trim()==(proj_name.options[i].text.trim())){
				    	proj_name.options[i].selected= true;
				    }
				} 	

				//quantity
				jqTds[9].innerHTML='<div id=quantityDiv_update ><input type="text" value="'+aData[9]+'"  id="qty_u"  style="width:50px;"></div>';
				
				//status
				jqTds[10].innerHTML = document.getElementById('store_status_update').innerHTML.toString().trim();
				var store_status_u=document.getElementById('store_status_u');
				for( i=0;i<store_status_u.options.length;i++){
					
					if(aData[10].trim()==(store_status_u.options[i].text.trim())){
						store_status_u.options[i].selected= true;
				    }
				}
				
				//Hub
				jqTds[11].innerHTML=document.getElementById('fd_hub_div').innerHTML;
				var HubVal=document.getElementById('fd_hub_u');
				console.log("HUBVAL==>"+HubVal);
				for (i = 0; i <HubVal.options.length; i++) {
					
				    if(aData[11].trim().toUpperCase()==(HubVal[i].value.trim())){
				    	HubVal.options[i].selected= true;
				    }
				 }						
				
				
				//handle by
				if(aData[12]=="NA"){
					aData[12]="";	
					}
				jqTds[12].innerHTML = '<input type="text" value="'+aData[12]+'" id="handle_by_u" class="reduce_inputTag">';
				
				//TSI Name
				if(aData[13]=="NA"){
					aData[13]="";	
					}
				jqTds[13].innerHTML = '<input type="text" value="'+aData[13]+'" id="tsi_name_u" class="reduce_inputTag">';
				
				//TSI pn number
				if(aData[14]=="NA"){
					aData[14]="";	
					}
				jqTds[14].innerHTML = '<input type="text" value="'+aData[14]+'" id="tsi_phone_u" maxlength="12" class="reduce_inputTag">';
			
				
			}
			
			
		</script>
</body>
</html>