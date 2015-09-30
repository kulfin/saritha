<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../css/Connect/mis_tracker_exception_report.css" />
<link rel="stylesheet" href="../css/Common/chosen.css" />
<link rel="icon" href="<%=ApplicationConstants.IMAGEPATH %>" type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<style type="text/css">
#data{
color:#000000;
text-align: left;
}

#td_green{
	color: #39939C;
}

.reduce_inputTag {
	width: 100px;
}

.reduce_comboBox{
	width:100px;
}
</style>
	

<link rel="stylesheet" type="text/css" 	href="../css/Connect/project_scope_display.css" />
	
<!--Data Table-->
<link rel="stylesheet" type="text/css" href="../css/Connect/demo_table_old.css"/>
<link rel="stylesheet" type="text/css" href="../Connect/test/demo_page.css"/>

<!-- <script type="text/javascript" src="../js/Connect/jquery.js"></script>  -->


<script type="text/javascript" src="../js/Connect/mis_tracker_exception_report.js"></script>
<script type="text/javascript">

	/*$(".filter").on('keyup', function() {
		var rex = new RegExp($(this).val(), "i");
		console.log(rex);
		alert(rex);
		$(".jobCardStatusTable tbody tr").hide();
		$(".jobCardStatusTable tbody tr").filter(function() {
		    return rex.test($(this).text());
		}).show();
	});*/
	
		
</script>
<script type="text/javascript" charset="utf-8">

function getAll(){

			getClient();	

		function toggleOperation(jcDetailDivId) {
			//alert("it is calling"+jcDetailDivId);
			$("#" + jcDetailDivId + "").toggle(1000);
		
		}
		function comboInit() {
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen( {
				allow_single_deselect : true
			});
		};
	
//		getClient();

			
	
}
</script>


</head>
<!------JSP Scripting ------>
<%-- <%
	String username = (String) session.getAttribute("username");
	if (username == null) {
		{
			response.sendRedirect("../Login.jsp");
			return;
		}
	}
%> --%>

<!-----   Body   ----->
<body onload="getAll();">


<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="MIS TRACKER EXCEPTION REPORT"/>
	</jsp:include>
</div>
<!--/header div -->

<!--container div -->
<div id="container"><!--header div -->
<!-- <div id="header"></div>  -->
<!--/header div --> <!--main content div -->
<div id="main_content"><!--filter div -->
<div id="filt_div">
<div style="margin: 4px 0 0 0;"><span id="filter_select_client">
<label for="filter_client_select" style="margin: 0 0 0 10px;">Client:
</label> <select name="filter_client_select" onchange="getProject();"
	id="filter_client_select" style="width: 300px;" tabindex="2">
	<option value="select">select</option>
</select> </span> <span id="filter_select_Project"> <label
	for="filter_Project_select" style="margin: 0 0 0 160px;">Project:
</label> <select name="filter_Project_select"
	onchange="getJobCardByProjectForReport();" id="filter_Project_select"
	style="width: 300px;" tabindex="2">
	<option value="select">select</option>
</select> </span></div>
</div>
<!--/filter div --> <!--job card div -->

<!-- Loading Image -->
<div id="loading-image" style="display: none;position: absolute;width: 100%;top: 175px;" align="center">
	<img alt="Loading" src="../images/loading.gif">
</div>
<!-- Loading Image -->

<div id="jc_div" style="position: relative; margin: 10px 0 0 0; ">
<table id="misReport" style="word-spacing: .5px; width: 100%; font-weight: normal; text-align: center; background-color: rgb(255, 255, 255)" class="display">
<thead>
<tr>
	<th></th>
	<th><input type="text" name="search_engine" value="Search states" class="search_init" /></th>
	<th><input type="text" name="search_browser" value="Search cities" class="search_init" /></th>
	<th><input type="text" name="search_platform" value="Search storenames" class="search_init" /></th>
	<th><input type="text" name="search_version" value="Search address" class="search_init" /></th>
</tr>
<tr style="color: #39939C">
	<th>Sl.No</th><th>State</th><th>City</th> <th>Store Name</th><th>Address</th><th>CDO Name</th> <th>CDO Contact</th> <th>FD Supervisor</th>
	<th>Contact No</th><th>Tentative Measured Date</th> <th>Tentative Implement Date</th> <th>Visited But Not Measured</th><th>Revisited But Not Measured</th> 
	<th>Planned Measurement Date</th><th>Planned Implementation Date</th> <th>Reason For not Measuring</th><th>No_of Visits Made</th>
	<th>Measurement No Permission</th><th>Measurement Cancelled</th><th>JobCard No</th><th>Implementation No Permission </th>
	<th>Element Damaged</th><th>Element Missing</th><th>Cancelled</th><th>Material Resent</th>
</tr>
</thead>
</table>



</div>
<!--/job card div --></div>
<!--/main content div --> <!--footer div -->
<div id="footer"></div>
<!--/footer div --></div>
<!--/container div -->

<!-- End body /main content-->


</body>
</html>