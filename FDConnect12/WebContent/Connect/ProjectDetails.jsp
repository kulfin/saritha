<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%! String id=null; %>
<%! String ProjectStartDate=null; %>
<%! String ProjectEndDate=null; %>
<%! String ProjectPODate=null; %>
<%! String ProjectStatusDate=null; %>

<%
	String dd = "";
	String mm = "";
	String yy = "";
	System.out.println("Project Details JSp");
	id=(String)session.getAttribute("project_id");
	System.out.println("Project ID in projectDetails.jsp-->"+id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	
	//Pass project ID for CRM Details
	String Project_header=services.Project_header_data_select(Integer.parseInt(id));
	System.out.println("Project_header- "+Project_header);
	String Project_header_array[]=Project_header.split(Constants.columnSeperator);
	for(int count=0;count<Project_header_array.length;count++){
		System.out.println("value===="+count+"-----------------"+Project_header_array[count]);
	}
	
	String client_id=Project_header_array[2];
	//System.out.println("client_id "+client_id);
	
	String fd_division=Project_header_array[3];
	//System.out.println("fd_division "+fd_division); 
	
	//System.out.println("fd hub- "+Project_header_array[19]);
	
	// Check For Blank
	if(Project_header_array[14].equalsIgnoreCase("empty")){
		Project_header_array[14]="";
	}
	if(Project_header_array[15].equalsIgnoreCase("empty")){
		Project_header_array[15]="";
	}
	if(Project_header_array[16].equalsIgnoreCase("empty")){
		Project_header_array[16]="";
	}
	if(Project_header_array[17].equalsIgnoreCase("empty")){
		Project_header_array[17]="";
	}
	//date
	/*if(Project_header_array[18].substring(0,10).equalsIgnoreCase("1970-01-01")|| Project_header_array[18].equalsIgnoreCase("null")){
		ProjectPODate="";
	}
	else{
		Project_header_array[18]=Project_header_array[18].substring(0,10);	
		dd = Project_header_array[18].substring(8, 10);
		mm = Project_header_array[18].substring(5, 7);
		yy = Project_header_array[18].substring(2, 4);
		ProjectPODate = dd + "/" + mm + "/" + yy;
		
	}
	if(Project_header_array[5].substring(0,10).equalsIgnoreCase("1970-01-01")|| Project_header_array[5].equalsIgnoreCase("null")){
		ProjectEndDate="";
	}
	else{
		Project_header_array[5]=Project_header_array[5].substring(0,10);
		dd = Project_header_array[5].substring(8, 10);
		mm = Project_header_array[5].substring(5, 7);
		yy = Project_header_array[5].substring(2, 4);
		ProjectEndDate = dd + "/" + mm + "/" + yy;
	} 
	if(Project_header_array[4].substring(0,10).equalsIgnoreCase("1970-01-01") || Project_header_array[4].equalsIgnoreCase("null")){
		ProjectStartDate="";
	}
	else{
		Project_header_array[4]=Project_header_array[4].substring(0,10);	
		dd = Project_header_array[4].substring(8, 10);
		System.out.println("val of dd===========" + dd);

		mm = Project_header_array[4].substring(5, 7);

		yy = Project_header_array[4].substring(2, 4);
		ProjectStartDate = dd + "/" + mm + "/" + yy;
	} 
	*/
	ProjectPODate=Project_header_array[18];
	ProjectStartDate=Project_header_array[4];
	ProjectEndDate=Project_header_array[5];
	
	if(Project_header_array[10].equalsIgnoreCase("0")){
		Project_header_array[10]="";
	}
	if(Project_header_array[13].equalsIgnoreCase("0")){
		Project_header_array[13]="";
	}
	if(Project_header_array[1].equalsIgnoreCase("empty")){
		Project_header_array[1]="";
	} 
	if(Project_header_array[7].equalsIgnoreCase("empty")){
		Project_header_array[7]="";
	}
	if(Project_header_array[8].equalsIgnoreCase("empty")){
		Project_header_array[8]="";
	}
	if(Project_header_array[9].equalsIgnoreCase("empty")){
		Project_header_array[9]="";
	}
	if(Project_header_array[11].trim().equalsIgnoreCase("empty")){
		Project_header_array[11]="";
	}
	if(Project_header_array[12].trim().equalsIgnoreCase("empty")){
		Project_header_array[12]="";
	}
	if(Project_header_array[6].trim().equalsIgnoreCase("empty")){
		Project_header_array[6]="";
	}
	/*if(Project_header_array[22].substring(0,10).equalsIgnoreCase("1970-01-01")|| Project_header_array[22].equalsIgnoreCase("null")){
		ProjectStatusDate="";
	}
	else{
		Project_header_array[22]=Project_header_array[22].substring(0,10);	
		dd = Project_header_array[22].substring(8, 10);
		mm = Project_header_array[22].substring(5, 7);
		yy = Project_header_array[22].substring(2, 4);
		ProjectStatusDate = dd + "/" + mm + "/" + yy;
	}
*/
ProjectStatusDate=Project_header_array[22];
int fdManager=Integer.parseInt(Project_header_array[23]);
%>

<%@page import="com.fd.Connect.FilterSevices"%><html>
<head>
<style type="text/css">
#data{
color:#000000;
text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<link rel="stylesheet" type="text/css" media="all" href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/Connect/connet_js_entire.js"></script>
</head>
<body  onload="getFDMgr_Name();"  style="overflow: auto; height: 600px;font: calibri;">
						
	<div id="Projectdata" style="background-color: rgba(204, 204, 204, 0.66);height: 95px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	font-size:15px;
	box-shadow: 0px 0px 4px 3px #cccccc;
	width: 1300px;
	margin-left: 5px;">
		<h2  style="font-weight: bold;color:white;">PROJECT HEADER</h2>
		<div style="display: inline-table;position: absolute;
			margin-top:-23px;left:100px;
		 color: darkgray;
		 width:400px;height:50px;">
			<table style="width:100%;text-align: left;">
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
		<div style="left: 970px;position: absolute;display: inline-table;
		margin-top:-23px;color: darkgray;width:400px;height:50px;">
			<table style="width:100%;text-align:left;">
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
		
<div id= "subMenu" style="margin-top: 20px; font-size: 11px;">
					
<ul class="menu" id="menu">
  	<li><a href="#" onclick="project_details(<%=id%>);" class="submenulink">Project Header</a></li>
      <li><a href="DocumentLibrary.jsp?projectId=<%=id%>" 
		class="submenulink">Document Library</a></li>
    <!--  <li><a href="#" onclick="project_accountablity(<%=id%>);" class="submenulink">Accountability</a></li>  --> 
	<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element & Scope</a></li>
	<!--  <li><a href="#" class="submenulink">Date Plans</a></li>
	<li><a href="#" class="submenulink">Rate Card</a></li>  -->
	<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a></li>
	<!--  <li><a href="#" class="submenulink">Quantities</a></li>  -->
	<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
	<!-- <li><a href="#" onclick="project_approval(<%=id%>);" class="submenulink">Approval</a></li> 
	<li><a href="#" class="submenulink" onclick="#">Closure</a></li>  -->
</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>	
</div>	

<div id="sub_menu">
</div>	
	
<div id="updateform">

<form name="myForm">

<div id="setbackground" 
	style="border:#778 2px solid;
	background:#CFD6D5;
	margin:60px 165px 50px 243px;
	background: #e0e0e0;
	/* margin: 60px 165px 50px 154px; */
	border-radius: 10px;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;">
	
	<div style="float: left;padding-left: 10px;">
	<table style="text-align: left;word-spacing: 1px;padding-top: 15px;">
	<tr>
	<td>Client Name	</td>
	<td>	
	<select name="client_id" id="client_id" style="width: 150px;">
					<option value="SELECT">SELECT</option>
					<%
					services=new ProjectServices();
					String dropvalue_fddiv=services.drop_down_client();
					String[] result_fddiv=dropvalue_fddiv.split(Constants.rowSeperator);
					for(int i=0;i<result_fddiv.length;i++)
					{
						String[] res_fd_div=result_fddiv[i].split(Constants.columnSeperator);
						
						if(res_fd_div[1].equals(client_id))
						{	%> <option value="<%=res_fd_div[1]%>" SELECTED><%=res_fd_div[0]%></option> <%
							
						}
						else
							%> <option value="<%=res_fd_div[1]%>"><%=res_fd_div[0]%></option> <%
					}
					%>
	</select>
	</td>
	</tr>
	
	
	<tr>
	<td>Manager Name</td>
	<td> <input type="text" name="client_manager_name" id="client_manager_name"
	value="<%=Project_header_array[14]%>" size="20" maxlength="25"></td>
	</tr>
	<tr>
	<td>Manager Phone</td>
	<td> <input type="text" name="client_manager_phone" id="client_manager_phone"
	value="<%=Project_header_array[15]%>" size="14" maxlength="25"></td>
	</tr>
	<tr>
	<td>Manager Email</td>
	<td> <input type="text" name="client_manager_email" id="client_manager_email"
	value="<%=Project_header_array[16]%>" size="25" maxlength="100"></td>
	</tr>
	<tr>
	<td>PO Reference</td>
	<td> <input type="text" name="client_manager_po" id="client_manager_po"
	 size="20" maxlength="50" value="<%=Project_header_array[17]%>" ></td>
	</tr>
	<tr>
	<td>PO Date</td>
	<td> <input type="text" name="po_date" id="po_date"
	 size="10" onmouseover="date(po_date);" value="<%=ProjectPODate%>" ></td>
	</tr>
	<tr>
		<td>PO Amount</td>
		<td>
			<input type="text" name="po_amount" id="po_amount"  value="<%=Project_header_array[20] %>" size="10" >
		</td>	
	</tr>
	
	<tr>
	<td>Estimated Budget</td>
	<td> <input type="text" name="estimated_budget" id="estimated_budget"
	 size="20" maxlength="20" value="<%=Project_header_array[10]%>"></td>
	</tr>
	<tr>
	<td>Total Stores</td>
	<td> <input type="text" name="total_stores" id="total_stores" size="11" maxlength="10" 
	value="<%=Project_header_array[13]%>" ></td>
	</tr>	
	</table>
	</div>
	
	
	
	<div>
	<table style="text-align: left;word-spacing: 1px;padding-top: 15px;">
	
	<tr>
	<td>Project	Code</td>
	<td><input type="text" name="Project_id" id="Project_id" 
	size="15" maxlength="50" value="<%=Project_header_array[1]%>"> 
	</td>
	<td>FD Hub</td>
	<td>	<select name="fd_hub" id="fd_hub" style="width: 150px;" onchange="return getDivisionOnHub();">
			<option value="SELECT">SELECT</option>
	<%		services=new ProjectServices();
		String dropdownhub=services.drop_down_fdhubID();
		String[] resulthub=dropdownhub.split(Constants.rowSeperator);
		for(int i=0;i<resulthub.length;i++)
		{	
			String columndata[]=resulthub[i].split(Constants.columnSeperator);
			
			if(Project_header_array[19].equalsIgnoreCase(columndata[1])){
				
				%>
					<option value="<%=columndata[1]%>" SELECTED><%=columndata[0]%></option>
				<%
				}
				else
				%>	<option value="<%=columndata[1]%>"><%=columndata[0]%></option>
				<%
			
		}
	 %>
	 </select>
	 </td>
	</tr>
	
	<tr>
	<td>Project	Name</td>
	<td><input type="text" name="Project_name" id="Project_name"
	 size="20" maxlength="25" value="<%=Project_header_array[0]%>"> 
	</td>
	<td>FD Division</td>
	<td>
	<div id="fd_div_replace">
	 <select name="fd_div" id="fd_div" style="width: 150px;">
			<option value="SELECT">SELECT</option>		
					<%
					services=new ProjectServices();
					String dropvalue=services.drop_down_fddiv();
					String[] result=dropvalue.split(Constants.rowSeperator);
					for(int i=0;i<result.length;i++)
					{	
						String columndata[]=result[i].split(Constants.columnSeperator);
						if(fd_division.equalsIgnoreCase(columndata[1])){
						
						%>
							<option value="<%=columndata[1]%>" SELECTED><%=columndata[0]%></option>
						<%
						}
						else
						%>	<option value="<%=columndata[1]%>"><%=columndata[0]%></option>
						<%
					}
					%>
		</select> 
		</div>
	</td>	
	</tr>
	
	<tr>
	<td>CRM Name</td>
	<td><input type="text" name="crm_name" id="crm_name" size="20"
	 maxlength="25" value="<%=Project_header_array[7]%>" > 
	</td>
	
	<td>FD Approvals Mgr</td>
		<td>
			<div id="fd_div_FDMgr">
				<select name="fd_FDMgr" id="fd_FDMgr" style="width:150px;" >
					<option value="NODATA">Select</option>
						<%
				FilterSevices services1=new FilterSevices();
					String rowData[]=(services1.getFDApprovalMgrName()).split(Constants.rowSeperator);
				
					for(int i=0;i<rowData.length;i++)
					{	
						String columndata[]=rowData[i].split(Constants.columnSeperator);
						if(fdManager==Integer.parseInt(columndata[0])){
						
						%>
							<option value="<%=columndata[0]%>" SELECTED><%=columndata[1]%></option>
						<%
						}
						else
						%>	<option value="<%=columndata[0]%>"><%=columndata[1]%></option>
						<%
					}
					%>
				</select>
			</div>
		</td>	
	
	
	</tr>
	
	<tr>
	<td>CRM Phone</td>
	<td><input type="text" name="crm_phone" id="crm_phone"
	 size="14" maxlength="25" value="<%=Project_header_array[8]%>" > 
	</td>
	
	
	<td>Start Date</td>
	<td><input type="text" name="fd_start_date" id="fd_start_date"
	 size="15" onmouseover="date(fd_start_date);" value="<%=ProjectStartDate%>">
	
	
	</tr>
	
	
	<tr>
	<td>CRM Email</td>
	<td colspan="1"><input type="text" name="crm_email" id="crm_email"
	 size="25" maxlength="100" value="<%=Project_header_array[9]%>" > 
	</td>
	
	<td>End Date</td>
	<td><input type="text" name="fd_end_date"
	 id="fd_end_date" size="15" onmouseover="date(fd_end_date);" value="<%=ProjectEndDate%>">
	 </td>
	
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
		<input type="text" name="pro_status_date" id="pro_status_date" size="15"   value="<%=ProjectStatusDate%>"  onmouseover="date(pro_status_date);">
		</td>
	</tr>
	
	<tr>
	<td>Billing Narration</td>
	<td colspan="3">
	<textarea rows="2" cols="50" id="bill_naration" name="bill_naration" maxlength="250" 
	style="resize:verical;">
	<%=Project_header_array[11].trim()%>
	</textarea>
	</td>
	</tr>
	
	<tr>
	<td>Special Instruction</td>
	<td colspan="3">
	<textarea rows="2" cols="50" id="special_instruction" name="special_instruction" style="resize:verical;"
	maxlength="250">
	<%=Project_header_array[12].trim()%>
	</textarea>
	</td>
	</tr>
	
	<tr>
	<td>Notes</td>
	<td colspan="3">
	<textarea rows="2" cols="50" id="notes" name="notes" style="resize:verical;"
	maxlength="250" >
	<%=Project_header_array[6].trim()%>
	</textarea>
	</td>
	</tr>
	
	<tr>
	<td colspan="3"> </td>
	<td>
	<input type="button" onclick="return update_Project_header(<%=id%>);" value="Save Project">
	</td>
	</tr>
</table>
</div>
	
	</div>	
</form>	
	
	
	</div>
</body>
</html>