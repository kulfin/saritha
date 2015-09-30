<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	System.out.println("CreateProject Jsp");
	
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
select{font: 14px Calibri;}
input{font: 14px Calibri;}

textarea{
border-radius: 4px;
-moz-border-radius: 2px;
resize:vertical;
border: 1px inset;

}
</style>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/Connect/project_admin.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

</head>

<!-----   Body   ----->
<body onload="getDivisionOnHub();">

	<!-- div to include header -->
		<div>	
			<jsp:include page="../Common/Home.jsp">
			<jsp:param name="title" value="Create Project"/>
			</jsp:include>
		</div>

		<!-- body /main content-->
		<div id="maincontent" style="position: absolute;width: 94%;height: 97%;">
			<table style="width: 100%; text-align: center;">
				<tr>
					
			<td width="100%">
				<div id="maincontent_element">
		
			
	<form name="myForm">
<div id="setbackground" style="border:#778 2px solid; background:#e0e0e0;
						 margin:60px 165px 50px 154px;
						 border-radius: 10px;
						 -webkit-border-radius:10px;
						-moz-border-radius: 10px;">
	<h2 style="color: #ffffff;">CREATE PROJECT</h2>
	
	<div style="display:block;float: left;margin-left:25px;">
	
	<table style="text-align: left;word-spacing: 1px;">
	<tr>
	<td class="mandatory_field">Client Name	</td>
	<td>	<select name="client_id" id="client_id" style="width: 150px;">
					<option value="SELECT">SELECT</option>
					<%
					ProjectServices services=new ProjectServices();
					String dropvalue_fddiv=services.drop_down_client();
					String[] result_fddiv=dropvalue_fddiv.split(Constants.rowSeperator);
					for(int i=0;i<result_fddiv.length;i++)
					{
						String[] res_fd_div=result_fddiv[i].split(Constants.columnSeperator);
						
							%>
							<option value="<%=res_fd_div[1]%>"><%=res_fd_div[0]%></option>
					<%
					}
					%>
		</select>
	</td>
	</tr>
	<tr>
	<td>Manager Name</td>
	<td> <input type="text" name="client_manager_name" id="client_manager_name" size="20" maxlength="25"></td>
	</tr>
	<tr>
	<td>Manager Phone</td>
	<td> <input type="tel" name="client_manager_phone" id="client_manager_phone" size="14" maxlength="10"></td>
	</tr>
	<tr>
	<td>Manager Email</td>
	<td> <input type="email" name="client_manager_email" id="client_manager_email" size="25" maxlength="100"></td>
	</tr>
	<tr>
	<td>PO Reference</td>
	<td> <input type="text" name="client_manager_po" id="client_manager_po" size="20" maxlength="50"></td>
	</tr>
	<tr>
	<td>PO Date</td>
	<td> <input type="text" name="po_date" id="po_date" size="10" onmouseover="date(po_date);"></td>
	</tr>
	
	<tr>
		<td>PO Amount</td>
		<td>
			<input type="text" name="po_amount" id="po_amount" size="10" >
		</td>	
	</tr>
	
	<tr>
	<td>Estimated Budget</td>
	<td> <input type="text" name="estimated_budget" id="estimated_budget" size="20" maxlength="20"></td>
	</tr>
	<tr>
	<td>Total Stores</td>
	<td> <input type="text" name="total_stores" id="total_stores" size="11" maxlength="10"></td>
	</tr>	
	</table>
	</div>
	
	
	
	<div style="display: inline-block;">
	<table style="text-align: left;word-spacing: 1px;">
	
	<tr>
	<td class="mandatory_field">Project	Code</td>
	<td><input type="text" name="Project_id" id="Project_id" size="15" maxlength="50"> 
	</td>
	<td class="mandatory_field">FD Hub</td>
	<td>
	<!-- <select name="fd_hub" id="fd_hub" style="width: 150px;" onchange="return getDivisionOnHub();">  -->
	<select name="fd_hub" id="fd_hub" style="width: 150px;">
			<option value="SELECT">SELECT</option>
	<%		services=new ProjectServices();
		String dropdownhub=services.drop_down_fdhubID();
		String[] resulthub=dropdownhub.split(Constants.rowSeperator);
		for(int i=0;i<resulthub.length;i++)
		{	
			String columndata[]=resulthub[i].split(Constants.columnSeperator);
			%>
				<option value="<%=columndata[1]%>"><%=columndata[0]%></option>
			<%
			
		}
	 %>
	 </select>
	 </td>
	<!-- <input type="text" name="fd_hub" id="fd_hub" size="20"> -->
	</tr>
	
	<tr>
	<td class="mandatory_field">Project	Name</td>
	<td><input type="text" name="Project_name" id="Project_name" size="20" maxlength="25"> 
	</td>
	<td class="mandatory_field">FD Division</td>
	<td>
	<div id="fd_div_replace">
	<select name="fd_div" id="fd_div" style="width: 150px;">
			<option value="NODATA">NO DATA</option>	
		</select>
	</div>
	</td>
	</tr>
	
	<tr>
		<td>CRM Name</td>
		<td><input type="text" name="crm_name" id="crm_name" size="20" maxlength="25"></td>
	
		<td class="mandatory_field" >FD Approvals Mgr</td>
		<td>
			<div id="fd_div_FDMgr">
				<select name="fd_FDMgr" id="fd_FDMgr" style="width:150px;" onclick="getFDMgr_Name();">
					<option value="NODATA">NO DATA</option>
				</select>
			</div>
		</td>	
	</tr>
	
	<tr>
	<td>CRM Phone</td>
	<td><input type="text" name="crm_phone" id="crm_phone" size="20" maxlength="10"> 
	</td>
	<td>Start Date</td>
	<td><input type="text" name="fd_start_date" id="fd_start_date" size="15" onmouseover="date(fd_start_date);">
	</tr>
	
	<tr>
	<td>CRM Email</td>
	<td colspan="0"><input type="email" name="crm_email" id="crm_email" size="20" maxlength="100"> 
	</td>
	
	<td>End Date</td>
	<td><input type="text" name="fd_end_date" id="fd_end_date" size="15" onmouseover="date(fd_end_date);">
	</tr>
	
	<tr>
		<td>Project Status</td>
		<td>
		<select id="pro_status">
			<option value="CREATED">CREATED</option>
			<option value="APPROVED">APPROVED</option>
			<option value="CANCELLED">CANCELLED</option>
			<option value="CLOSED">CLOSED</option>
			<option value="WORK_IN_PROGRESS">WORK_IN_PROGRESS</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>Project Status Date</td>
		<td>
		<input type="text" name="pro_status_date" id="pro_status_date" size="15" onmouseover="date(pro_status_date);">
		</td>
	</tr>
	
	<tr>
	<td>Billing Narration</td>
	<td colspan="3">
	<textarea rows="2" cols="50" id="bill_naration" name="bill_naration" maxlength="250"></textarea>
	</td>
	</tr>
	
	<tr>
	<td>Special Instruction</td>
	<td colspan="3">
	<textarea rows="2" cols="50" id="special_instruction" name="special_instruction" maxlength="150"></textarea>
	</td>
	</tr>
	
	<tr>
	<td>Notes</td>
	<td colspan="3">
	<textarea rows="2" cols="50" id="notes" name="notes" maxlength="250" ></textarea>
	</td>
	</tr>
	
	<tr>
	<td colspan="3"> </td>
	<td>
	<input type="button" onclick="return create_Project();" value="Save Project">
	</td>
	</tr>
</table>
</div>
	
	</div>	
</form>

		</div>
					</td>
				</tr>
			</table>
		</div>
		<!-- End body /main content-->
</body>
</html>