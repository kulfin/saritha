
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html>

<%@page import="com.fd.Admin.Service"%><html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../css/Admin/manage_fd_employee.css" />
<%@page import="com.fd.App.*" %>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />

<script type="text/javascript" src="../js/Admin/manage_fd_employee.js">
</script>
<script type="text/javascript">
function numeric_only( e )
{
// deal with unicode character sets
var unicode = e.charCode ? e.charCode : e.keyCode;

// if the key is backspace, tab, or numeric
	if( unicode == 8 || unicode == 9 || ( unicode >= 48 && unicode <= 57 ) )
	{
		// we allow the key press
		return false;
	}
	else
	{
		// otherwise we don't
		return true;
	}
}
function numeric_only1( e )
{
// deal with unicode character sets
var unicode = e.charCode ? e.charCode : e.keyCode;

// if the key is backspace, tab, or numeric
	if( unicode == 8 || unicode == 9 || ( unicode >= 48 && unicode <= 57 ) )
	{
		// we allow the key press
		return true;
	}
	else
	{
		// otherwise we don't
		return false;
	}
}

function onlyAlphabets(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}


</script>
<title><%=ApplicationConstants.TITLE %></title>


<%
	Service src = new Service();
	String rowSeperator = "!&!";
	String columnSeperator = "#&#";
	String statusSeperator = "@&@";
	String resForEmpType = src.getEmployeeType("get");
	String result[] = resForEmpType.split(statusSeperator);
	String rowData [] = result[1].split(rowSeperator);
	
	String resForRole = src.getRole("get");
	String role_result[] = resForRole.split(statusSeperator);
	String role_rowData[] = role_result[1].split(rowSeperator); 
%>

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
	<jsp:param name="title" value="Manage FD Org Employee"/>
	</jsp:include>
</div>

<!-- body /main content-->
<div id="maincontent">
<div id="filt_div">
<div style="margin-left: 45px;"><span id="filter_select_country"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 5px; font-size: 14px;">
Organisation: <select style="width: 110px;">
	<option>Select</option>
	<option>Iron</option>
	<option>Steel</option>
	<option>Gold</option>
</select> </span> <span id="filter_select_region"
	style="color: #A90B0B; position: absolute; margin: 8px 0 0 300px; font-size: 14px;">
Hub: <select>
	<option>Select</option>

</select> </span> 

<!--<span id="filter_select_state" style="color: #A90B0B; position: absolute; margin: 8px 0 0 545px; font-size: 14px;">
	Division: <select style="width: 110px;">
				<option>Select</option>
			</select> 
</span> 

--><span id="filter_select_city" style="color: #A90B0B; position: absolute; margin: 8px 0 0 480px; font-size: 14px;">
Department: <select style="width: 110px;"> 
	<option>Select</option>

</select> </span> </div>
</div>
<div id="town_div" style="position: relative; display: none;">
<div id="town_detail"></div>

<div id="delete_button"><input style="width: 140px;" type="button"
	value="Delete" onclick="deleteTown('town_detail_table')"></div>

<div id="town_add">

<form action="#">
<div 	style="position: relative; text-align:center; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">ADD EMPLOYEE</div>
<br />
<br />

<div style="position: relative; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Employee Name:<span style="margin: 0px 0 0px 20px;"><input id="addtownName" name="townName" type="text" size="20" maxlength="25" ></span><br/>
	User Name:<span style="margin: 0px 0 0px 59px;"><input id="adduserName" name="userName" type="text" size="20" maxlength="25" ></span><br/>
	User Password:<span style="margin: 0px 0 0px 31px;"><input id="addpassword" name="password" type="password" size="20" maxlength="25"></span><br/>
	Designation:<span style="margin: 0px 0 0px 56px;"><input id="adddesignation" name="designation" type="text" size="20" maxlength="25"></span><br/>
	<!--
	Contact Address:<span style="margin: 0px 0 0px 19px;"><input id="addcontactAddress" name="contactAddress" type="text" size="20"></span><br/>-->
	<label style="vertical-align: top;">Contact Address:</label><span style="margin: 0px 0 0px 19px;width: 148px;"><textarea id="addcontactAddress" name="contactAddress" rows="2" cols="16"></textarea></span><br/>
	Contact Phone:<span style="margin: 0px 0 0px 31px;"><input id="addcontactPhone" name="contactPhone" type="text" size="20" onkeypress="return numeric_only1(event);" maxlength="14"></span><br/>
	Employee Type:<span style="margin: 0px 0 0px 25px;"><!--<input name="empType" type="text" size="30">
	-->
	<select id="empType" style="width: 150px">
	<option value="Select">select</option>
	<%
		for(int i=0;i<rowData.length;i++)
		{
		String colData [] = rowData[i].split(columnSeperator);
		%>
			<option value=<%=colData[0] %>><%=colData[1]%></option>
		<%
		}
	%>
		
	</select>
	</span><br/>
	Role:<span style="margin: 0 0 0 107px;">
	<select id="role" style="width: 150px">
		<option value="Select">select</option>
	<%
		for(int i=0;i<role_rowData.length;i++)
		{
		String colData [] = role_rowData[i].split(columnSeperator);
		%>
			<option value=<%=colData[0] %>><%=colData[1]%></option>
		<%
		}
	%>
	</select>
	</span><br/>
</div>
<div style="position: relative; margin: 20px 0 0 170px;">
<input style="width: 140px;" type="button"	onclick="return validateAddOperation(townName,userName,password,designation,contactAddress,contactPhone,empType,role)" value="SUBMIT" /></div>
</form>

</div>

<div id="town_update">

<form action="#">
<div style="position: relative; margin: 5px 0 0px 175px; color: #A90B0B; font-size: 17px; font-weight: bold; font-family: verdana;">UPDATE EMPLOYEE</div>
<br />
<span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	Employee Name:<span style="margin: 0px 0 0px 20px;"><input id="townName"	name="townName" type="text" size="20"></span> 
  	<span style="margin: 0px 0 0px 0px;"><input id="townId" name="townId" type="hidden"></span> 
  </span>
  
<span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	User Name:<span style="margin: 0px 0 0px 59px;"><input id="userName"	name="userName" type="text" size="20"></span> 
  </span>  
  
  <span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	User Password:<span style="margin: 0px 0 0px 31px;"><input id="password"	name="Upassword" type="password" size="20"></span> 
  </span>
  
  <span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	Designation:<span style="margin: 0px 0 0px 56px;"><input id="designation"	name="Udesignation" type="text" size="20"></span> 
  </span>
  
  <!--<span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	Contact Address:<span style="margin: 0px 0 0px 18px;"><input id="contactAddress"	name="contactAddress" type="text" size="20"></span> 
  </span>
  -->
  	<span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	<label style="vertical-align: top;">Contact Address:</label><span style="margin: 0px 0 0px 18px;"><textarea id="contactAddress"	name="contactAddress" rows="2" cols="16"></textarea></span> 
  </span>
  <span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	Contact Phone:<span style="margin: 0px 0 0px 30px;"><input id="contactPhone"	name="contactPhone" type="text" size="20"></span> 
  </span>
  
  <!--<span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	Employee Type:<span style="margin: 0px 0 0px 25px;"><input id="empType"	name="empType" type="text" size="30"></span> 
  </span>
  
  <span style="position: relative; display: block; margin: 15px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
  	Role:<span style="margin: 0px 0 0px 97px;"><input id="role"	name="role" type="text" size="30"></span> 
  </span><br/>
-->

<span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Employee Type:<span style="margin: 0px 0 0px 24px;">
		<select id="empType" style="width: 150px">
		<option value="select">select</option>
		<%
			for(int i=0;i<rowData.length;i++)
			{
			String colData [] = rowData[i].split(columnSeperator);
			%>
				<option value=<%=colData[0] %>><%=colData[1]%></option>
			<%
			}
		%>
		
	</select>
	</span>
</span>
<span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Role:<span style="margin: 0px 0 0px 105px;">
	<select id="role" style="width: 150px">
		<option value="select">select</option>
	<%
		for(int i=0;i<role_rowData.length;i++)
		{
		String colData [] = role_rowData[i].split(columnSeperator);
		%>
			<option value=<%=colData[0] %>><%=colData[1]%></option>
		<%
		}
	%>
	</select>
	</span>
</span>
	
	<span style="position: relative; display: block; margin: 10px 0 10px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Hub:<span id="update_select_region" style="margin: 0px 0 0px 107px;">
			<select style="width: 150px;" id="update_region_select">
				<option>select</option>
			</select>
		</span> 
		<!--<span style="position: relative; display: inline; margin: 0px 0 0px 4px; font-size: 15px; color: rgb(70, 66, 66);">
		Division:<span 	id="update_select_region" style="margin: 0px 0 0px 11px;">
					<select	style="width: 120px;" id="update_region_select">
						<option>select</option>
					</select>
				</span>
		</span>
--></span> 

<span style="position: relative; display: block; margin: 10px 0 0px 20px; font-size: 15px; color: rgb(70, 66, 66);">
	Department:<span id="update_select_city" style="margin: 0px 0 0px 45px;">
				<select style="width: 150px;" id="update_city_select">
					<option>select</option>
				</select>
				</span> 
				<!--<span style="position: relative; display: inline; margin: 0px 0 0px 12px; font-size: 15px; color: rgb(70, 66, 66);">
	City:<span id="update_select_city" style="margin: 0px 0 0px 29px;">
				<select style="width: 120px;" id="update_city_select">
					<option>select</option>
				</select>
		 </span>
		</span>
--></span> 

	
	<span style="position: relative; display: block; margin: 15px 0 0 65px;">
		<input style="width: 140px;" type="button" onclick="return validateUpdateOperation(townId,townName,userName,Upassword,Udesignation,contactAddress,empType,role)" value="UPDATE" />
	<span style="display: inline; margin: 0px 0 0 30px;"> 
		<input style="width: 140px;" type="button" onclick="showAddDiv()" value="CANCEL" />
	</span>
</span>
</form>





</div>
</div>

</div>




<!-- End body /main content-->


</body>
</html>