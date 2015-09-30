<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="../css/Connect/mnd_job_card_assignment.css" />
<link rel="stylesheet" type="text/css" href="test/demo_table.css"/>
<link rel="stylesheet" type="text/css" href="../Connect/test/demo_page.css"/>

<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>


<script type="text/javascript" src="../js/Common/jquery.min.js"></script>
<script type="text/javascript" src="../js/Connect/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/Connect/mnd_job_card_assignment.js"></script>  
<!-- <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script> -->

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript"
	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script>

	function toggleOperation(jcDetailDivId) {
		//alert("it is calling"+jcDetailDivId);
		$("#" + jcDetailDivId + "").toggle(1000);
	}	
		
	$(document).ready(function() {	
		getClient('Mnd');
		
	});	
		 
</script>

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


</script>

</head>


<!-----   Body   ----->
<body>
<script type="text/javascript"><!--
/*
(function(){
  var bsa = document.createElement('script');
     bsa.type = 'text/javascript';
     bsa.async = true;
     bsa.src = '//s3.buysellads.com/ac/bsa.js';
  (document.getElementsByTagName('head')[0]||document.getElementsByTagName('body')[0]).appendChild(bsa);
})();
*/

</script>


	<!-- div to include header -->

<div>
		<jsp:include page="../Common/Home.jsp">
			<jsp:param name="title" value="MnD Job Card Assignment"/>
			</jsp:include>
</div>
<!--container div -->
<div id="container">

<!--main content div -->
<div id="main_content">

<!--filter div -->
<div id="filt_div">
<div style="margin:4px 0 0 0;">

<span id="filter_operation_select_client_span">
<label for="filter_operation_client_select" style="margin:0 0 0 10px;">Client: </label>
<select  onchange="getProject('Mnd');"  id="filter_operation_client_select"   style="width:300px;">
<option value="select">select</option> 
</select>
</span>

<span id="filter_operation_select_Project_span">
<label for="filter_operation_Project_select" style="margin:0 0 0 160px;">Project: </label>
<select  name="filter_operation_Project_select" onchange="getDivision();"  id="filter_operation_Project_select" style="width:300px;">
<option value="select">select</option> 
</select> 
</span>

<span id="division_name_input_span">
<label for="division_name" style="margin:0 0 0 160px;">Division: </label>
<input id="division_name_input" name="division_name" type ="text" size="34" style="height:17px;">
</span>
 
</div>
</div>
<!--/filter div -->

<!-- /Import From file -->
<!-- <div id="import_div" style="margin: 10px 0 0 10px;">
	<form action="../Upload" method="post" enctype="multipart/form-data" onsubmit="return check()">
<label style="font-weight: bold;margin:10px 0 0 0;">Import Store Wise Element Type List From File</label>
		<input type="hidden" name="ProjectIdForStore" id="ProjectId"></input>
		<input type="file" name="file" size="40" id="file" accept="application/excel" /> 
		<input type="submit" value="Upload File" />
	</form>
</div>  -->
<!-- /Import From file -->

<div id="storeStatus" style="display: none;">
	<select id="storeStatus">
		<option value="NODATA">NODATA</option>
	</select>
</div>

<!--grid button div -->
<div id="grid_button_div">

<div style="margin:4px 20px 0 0;float: right;">
<!-- <input type="checkbox" onclick="selectUnselectAllStores()" id="selectAllStoresCheckbox"  style="margin:0 0 0 10px;" />
<input type="button" id="" value="Add Store" onclick="addGridRow('unmapped_Project_store')"  style="margin:0 0 0 10px;"  />
<input type="button" id="" value="Delete Store" onclick="deleteGridRow()"  style="margin:0 0 0 10px;"  />
<input type="button" id="" value="Maximize All" onclick="maximizeAllElementGrid();"  style="margin:0 0 0 10px;"  />
<input type="button" id="" onclick="minimizeAllElementGrid();"  value="Minimize All"  style="margin:0 0 0 10px;"  /> 
<input type="button" value="Save Store Status" onclick="setStoreStatus();" style="margin:0 0 0 300px" />
<input type="button" value="Save Element Type Status" onclick="validateStoreAndElementDataForSavingElementStatus();" style="margin:0 0 0 10px" /> -->
<input type="button" value="Generate Job Card Numbers" id="generateJCNum"  style="margin:0 0 0 10px" />
<input type="button" value="Save" id="SaveJcDetails" style="margin:0 0 0 10px" />  
</div>
<div style="clear: both;"></div>
</div>
<!--grid header div -->
<!-- <div id="grid_header_div">

 <div style="margin:4px 0 0 0;">
<span style="margin:0 0 0 138px;">
Store
</span>

<span style="margin:0 0 0 150px;">
Region
</span>

<span style="margin:0 0 0 123px;">
State
</span>

<span style="margin:0 0 0 130px;">
City
</span>

<span style="margin:0 0 0 165px;">
Address
</span>


<span style="margin:0 0 0 131px;">
JC Date
</span>





<span style="margin:0 0 0 35px;">
JC Number
</span>


<span style="margin:0 0 0 50px;">
Status
</span>

</div>  
</div> -->
<!--/grid header div -->

<!--grid div -->
<!-- <div id="grid_div">


</div>  --> 
<div id="dataTable_Content">
<table id="jcMnd" class="display"
	style="word-spacing: .5px; width: 100%; text-align: center; font-weight: normal; background-color: rgb(255, 255, 255); border: none;">
<thead>
	
	<tr style="color: #39939C">
		<th>Select</th>
		<th>Edit</th>
		<th>State</th>
		<th>City</th>
		<th>Store Name</th>
		<th>Address</th>
		<th>Store Code</th>
		<th>Store Status</th>
		<th>Brand</th>
		<th>Project/Element Name</th>
		<th>Qty</th>
		<th>Element Status</th>
		<th>Comments</th>
		<th>FD Hub</th>
		<th>JobCard No</th>
		<th>JobCard Date</th>
	
	</tr>
</thead>
<tfoot>
<tr>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
</tr>
</tfoot>
</table>

</div>

<!--/grid div -->
<!--<div id="buttons_div">
<input type="button" value="Generate Job Card Numbers" onclick="generateJobCardNumbers();" style="width:250px;margin:0 0 0 10px" />
<input type="button" value="Save" onclick="validateStoreAndElementData();" style="width:250px;margin:0 0 0 10px" />
</div>
--><!--/main content div -->

<!--footer div -->
<div id="footer">
</div>
<!--/footer div -->

</div>

<!--/ Main Content Div -->
</div>
<!--/container div -->

<!-- End body /main content-->

</body>
</html>