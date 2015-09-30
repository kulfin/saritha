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

<link rel="stylesheet" type="text/css" href="../css/RFPR/material_consolidation_and_costing.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<script type="text/javascript" src="../js/RFPR/material_consolidation_and_costing.js">
</script>




</head>
<!------JSP Scripting ------>
<% /*
String username=(String)session.getAttribute("username");
if(username==null){
	{
		response.sendRedirect("login.jsp");
	}
}*/
%>
<!-----   Body   ----->
<body onload="getBomDropDownData()">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Search BOM"/>
	</jsp:include>
</div>
<!-- body /main content-->
<% 
String bomId=request.getParameter("bomId");
out.println("<script>getBomElementData("+bomId+");</script>");
%>
<div id="maincontent">

<div id="material_consolidation_and_costing_div" style="margin-left:5px;">










</div>

</div>




<!-- End body /main content-->


</body>
</html>