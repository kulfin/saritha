
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	
	HttpSession session1 = request.getSession(false);// don't create if it doesn't exist
	if(session1 == null && session1.isNew()) {
	    response.sendRedirect("/Login.jsp");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_country.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<script type="text/javascript" src="../js/Admin/manage_country.js">
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
<body onload="getCountry('filterOperation')">

<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Manage Country"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">

<div id="country_div" style="position: relative; display: none;">
<div id="country_detail">

</div>

<div id="delete_button"><input style="width: 140px;" type="button" value="Delete" onclick="deleteCountry('country_detail_table')"/>

<div id="country_add">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 178px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD COUNTRY</div>
<br />
<br />
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Country Name:<span style="margin: 0px 0 0px 20px;"><input id="countryName1" name="countryName" type="text" size="40" maxlength="25"></span></div>
<div style="position: relative; margin: 60px 0 0 170px;"><input
	style="width: 140px;" type="button"
	onclick="return validateAddOperation(countryName1)" value="SUBMIT" onkeypress="HandleKeyPress(event,this.form);"/></div>
</form>





</div>

<div id="country_update">

<form action="#">
<div
	style="position: relative; margin: 5px 0 0px 163px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE
COUNTRY</div>
<br />
<br />
<br />

 <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
 Country Name:<span style="margin: 0px 0 0px 20px;"><input id="countryName" name="countryName" type="text" size="44"></span> 
 <span style="margin: 0px 0 0px 0px;"><input id="countryId" name="countryId" type="hidden"></span> </span> 
	
	
<span style="position: relative; display: block; margin: 65px 0 0 20px;">
<input style="width: 140px;" type="button" 	onclick="return validateUpdateOperation(countryId,countryName)" value="UPDATE" />
<span style="display: inline; margin: 0px 0 0 140px;"> <input style="width: 140px;" type="button" onclick="showAddDiv()" value="CANCEL" />
</span></span></form>

</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>