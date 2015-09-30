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

<link rel="stylesheet" type="text/css"
	href="../css/Connect/MisTracker_edge_regional_co_ordinator_report.css" />
<link rel="stylesheet" href="../css/Common/chosen.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
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
	
<link rel="stylesheet" type="text/css"
	href="../css/Connect/details_project.css" />

<link rel="stylesheet" type="text/css"
	href="../css/Connect/project_scope_display.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<!--Data Table-->
<!-- <link rel="stylesheet" type="text/css" href="test/demo_table_old.css"/>  -->

<script type="text/javascript" src="../js/Connect/jquery-1.8.3.js"></script>
<!-- <script type="text/javascript"
		src="../js/Connect/jquery.dataTables.min.js"></script>-->
<!-- <script type="text/javascript" src="//datatables.net/download/build/jquery.dataTables.nightly.js"></script>  -->
<!-- <script type="text/javascript" src="../js/Common/jquery.min.js"></script> -->
<script src="../js/Common/chosen.jquery.js" type="text/javascript"></script>

<script type="text/javascript"
	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="../js/Connect/MisTracker_edge_central_co_ordinator_report.js"></script>
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
<body>


<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="MIS TRACKER EDGE CENTRAL CO-ORDINATOR REPORT"/>
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




</div>
<!--/job card div --></div>
<!--/main content div --> <!--footer div -->
<div id="footer"></div>
<!--/footer div --></div>
<!--/container div -->

<!-- End body /main content-->

<script type="text/javascript">
	$(document).ready(function() {
		getClient();
		/*$('#table1').dataTable({
			"iDisplayLength": 5,
			"aaSortingFixed": [[2, 'asc']],
			"bLengthChange": false,
			"bSortClasses": false
		});*/
	});
	
	
	
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
</script>
</body>
</html>