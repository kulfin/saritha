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
	href="../css/Connect/job_card_status_for_report.css" />
<link rel="stylesheet" href="../css/Common/chosen.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<script type="text/javascript" src="../js/Connect/job_card_status_for_report.js"></script>
<!-- <script type="text/javascript" src="../js/Common/jquery.min.js"></script> -->
<script src="../js/Connect/jquery.min.js"></script>
<script src="../js/Common/chosen.jquery.js" type="text/javascript"></script>
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

		getClient();
	});
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
	<jsp:param name="title" value="MIS TRACKER"/>
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

<!-- Loading Image -->
<div id="loading-image" style="display: none;position: absolute;width: 100%;top: 175px;" align="center">
	<img alt="Loading" src="../images/loading.gif">
</div>
<!-- Loading Image -->

<!--/filter div --> <!--job card div -->
<div id="jc_div" style="position: relative; margin: 10px 0 0 0; ">




</div>
<!--/job card div --></div>
<!--/main content div --> <!--footer div -->
<div id="footer"></div>
<!--/footer div --></div>
<!--/container div -->

<!-- End body /main content-->

<script type="text/javascript">
	function comboInit() {
		$(".chzn-select").chosen();
		$(".chzn-select-deselect").chosen( {
			allow_single_deselect : true
		});
	};
</script>
</body>
</html>