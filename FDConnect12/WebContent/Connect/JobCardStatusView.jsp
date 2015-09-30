
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	
	String employee_Id=(String)session.getAttribute("employee_Id");
	if(employee_Id==null){
		response.sendRedirect("../Login.jsp");
	}
%>
<!DOCTYPE html>

<%@page import="com.fd.App.ApplicationConstants"%><html>
<head>
<meta charset="ISO-8859-1">
<!-- DataTable Css File -->
<!--<link rel="stylesheet" type="text/css" href="../css/Connect/demo_table_old.css"/>-->
<link rel="stylesheet" type="text/css" href="../Connect/test/demo_table.css"/>

<!-- DataTable Css File -->

<link rel="stylesheet" type="text/css" href="../css/Connect/job_card_status.css" />
<link rel="stylesheet" href="../css/Common/chosen.css" />
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<script type="text/javascript" src="../js/Connect/job_card_status.js"></script>
<script type="text/javascript" src="../js/Common/jquery.min.js"></script> 
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->
<script type="text/javascript" src="../js/Connect/jquery.dataTables.min.js"></script>
<script src="../js/Common/chosen.jquery.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript"	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>

<script>
	function toggleOperation(jcDetailDivId) {
		//alert("it is calling"+jcDetailDivId);
		$("#" + jcDetailDivId + "").toggle(1000);

	}

	$(document).ready(function() {
	
		getClient('NA');
		//showGridForJobCardSelection();
	});	
</script>

</head>


<!-----   Body   ----->
<body >
<!--container div -->
<div id="container">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Job Card Status Update"/>
	</jsp:include>
</div>

<!--/header div -->

<!--main content div -->
<div id="main_content">

<!--tab div -->
<div id="tab_div">
<div style="margin:4px 0 0 0;">
<span id="radio_select_mode" style="margin: 0 0 0 10px;">
<input Type="radio"  checked name="updateType" onclick="tabToggle('select_mode_radio')"  id="select_mode_radio" value="select_mode" style="margin:0 0 0 0px;">
Select Mode 
</span> 

<span id="radio_select_mode" style="margin: 0 0 0 450px;">
<input Type="radio" name="updateType" id="bulk_entry_mode_radio" onclick="tabToggle('bulk_entry_mode_radio')"  value="bulk_entry_mode" style="margin:0 0 0 0px;">
Bulk Entry Mode 
</span> 

<span id="radio_select_mode" style="margin: 0 0 0 450px;">
<input Type="radio" name="updateType" id="element_mode_radio" onclick="tabToggle('element_mode_radio')"  value="element_mode" style="margin:0 0 0 0px;">
Element Type Mode 
</span> 
</div>
</div>
<!--/tab div -->

<!--filter div -->
<div id="filt_div">
<div style="margin:4px 0 0 0;">

<span id="filter_select_client">
<label for="filter_client_select" style="margin:0 0 0 10px;">Client: </label>
<select name="filter_client_select" onchange="getProject();"  id="filter_client_select"   style="width:300px;" tabindex="2">
<option value="select">select</option> 
</select>
</span>

<span id="filter_select_Project">
<label for="filter_Project_select" style="margin:0 0 0 160px;">Project: </label>
<select  name="filter_Project_select" onchange="getDivision();"  id="filter_Project_select" style="width:300px;" tabindex="2" >
<option value="select">select</option> 
</select> 
</span>

<span id="division_name_input">
<label for="division_name" style="margin:0 0 0 160px;">Division: </label>
<input id="division_name" name="division_name" type ="text" size="34" style="height:17px;">
</span>
 
</div>
</div>
<!--/filter div -->

<!-- filter job card div  -->
<div id="element_mode_filter">
<div style="margin:4px 0 0 0;">
 <span id="element_mode_filter_select_job_card">
<label for="element_mode_filter_job_card_select" style="margin:0 0 0 10px;">Job Card: </label>
<select name="element_mode_filter_job_card_select" onchange="getJobCardByJobCardNumberElementMode();"  id="element_mode_filter_job_card_select"   style="width:200px;" >
<option value="select">select</option> 
</select>
</span>

<span id="element_mode_filter_select_store">
<label for="element_mode_filter_store_select" style="margin:0 0 0 110px;">Store: </label>
<select  name="element_mode_filter_store_select" onchange="getJobCardByStoreElementMode();"  id="element_mode_filter_store_select" style="width:200px;" tabindex="2" >
<option value="select">select</option> 
</select> 
</span>

<span id="element_mode_input_address">
<label for="element_mode_address_input" style="margin:0 0 0 110px;">Address: </label>
<input id="element_mode_address_input" readonly name="element_mode_address_input" type ="text" size="34" style="height:17px;">
</span>   

<span id="element_mode_input_city">
<label for="element_mode_city_input" style="margin:0 0 0 100px;">City: </label>
<input id="element_mode_city_input" readonly name="element_mode_city_input" type ="text" size="24" style="height:17px;">
</span> 
</div>  
 
</div>
<!-- /filter job card div -->

<div id="element_div" style="position: relative; margin: 5px 0 0 0;display:none">

<input type="Button" value="Update" onclick="return updateElementStatus();">
<!--Elment select label div -->
<div id="element_select_label" style="font-family: calibri;"><span id="element_mode_select_all_checkbox"
	style="float: left;"><input id="selectAllElements" onchange="elementSelection()" type="checkbox" /></span>Select Elements
</div>
<!--/Elment select label div -->

<form id="element_status_update_form">

<!--Element select  div -->
<div id="element_select_div">

</div>
<!--/Element select  div -->

</form>
</div>
<!--Element div -->
<div id="jc_div" style="position: relative; margin: 10px 0 0 0;display:none">

<!--job card update label div -->
<div id="jc_update_label" style="font-family: calibri;">Update Job
Card
</div>
<!--/job card update label div -->

<!--job card update  div -->
<div id="jc_update_div">
<form name="updateForm" id="updateForm">
<div style="position: relative; margin: 20px 0 0 10px;">Status:<span
	id="select_jc_status" style="margin: 0 0 0 76px;"><select
	id="jc_status_select" onchange="getRequiredFildsForJCUpdate()" style="width: 200px">
	<option>select</option>
</select></span></div>
<div  style="position: relative; margin: 20px 0 0 10px;">Status
Date:<span style="margin: 0 0 0 50px;"><input name="statusDate" id="jc_status_date_input" onmouseover="datePicker('jc_status_date_input')" type="text"	size="27"></span></div>
<div style="position: relative; margin: 20px 0 0 10px;"><span>Comments:</span><span
	style="margin: 0 0 0 55px;"><textarea rows="3" cols="30" name="comments" style="width: 192px;"></textarea></span></div>
<div id="required_filds_for_jc_update"></div>
<div style="position: relative; margin: 30px 0 0 150px;">
<input type="button" id="updateDetails"  Value="UPDATE"
	style="width: 150px;"></div>
	
</form>	
</div>
<!--/job card update  div -->

<!--job card select label div -->
<div id="jc_select_label" style="font-family: calibri;">
<!--  <span id="select_mode_select_all_checkbox"
	style="float: left;"><input id="selectAll" onchange="jobCardSelection()" type="checkbox" /></span>  -->
Select Job Card
</div>
<!--/job card select label div -->

<form name="jobCardSelect" id="jobCardSelect">

<!--job card select  div -->
<div id="jc_select_div">
	<table id="jcUpdate" class="display"
		style="word-spacing: .5px; width: 100%; text-align: center; font-weight: normal; background-color: rgb(255, 255, 255); border: none;">
		<thead>
			<tr style="color: #39939C">
				<th><input type="checkbox" id="checkall"/></th> 
				<!-- <th>Select</th> -->
				<th>Job Card</th>
				<th>Element Name</th>
				<th>Brand</th>
				<th>Component</th>
				<th>Material</th>
				<th>Qty</th>
				<th>StoreName</th>
				<th>Store Address</th>
				<th>City</th>
				
			</tr>
		</thead>
	</table>

</div>
<!--/job card select  div -->

</form>


</div>
<!--/job card div -->

</div>
<!--/main content div -->

<!--footer div -->
<div id="footer">
</div>
<!--/footer div -->

</div>
<!--/container div -->

<!-- End body /main content-->

<script type="text/javascript">function comboInit(){$(".chzn-select").chosen(); $(".chzn-select-deselect").chosen({allow_single_deselect:true});};
</script>
</body>
</html>