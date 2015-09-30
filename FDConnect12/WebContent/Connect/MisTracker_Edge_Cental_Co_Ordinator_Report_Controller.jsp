<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fd.Connect.*"%>




<%
	String statusSeperator = "@&@";
	String rowSeperator = "!&!";
	String columnSeperator = "#&#";
	String fieldSeperator = "$&$";
	String statusAndData[];
	int status = 0;
	String data = null;
	String rowData[];
	String columnData[];
	String inData;
	String outData;
	ProjectServices scr = new ProjectServices();

	String ajaxFlag = request.getParameter("flag");
	System.out.println("This is ajax flag " + ajaxFlag);
	response.setContentType("text/html;charset=UTF-8");
	
    //Get Client
	if (ajaxFlag.equals("get_client")) {

		outData = "";
		inData = scr.getClient();
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String clientId = columnData[0];
				String clientName = columnData[1];
				out.println("<option value=\"" + clientId + "\">"
						+ clientName + "</option>");
			}
		}

	}
	 //Get Project
	if (ajaxFlag.equals("get_Project")) {
		String clientId = request.getParameter("clientId");
		outData = clientId;

		inData = scr.getProject(outData);
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String ProjectId = columnData[0];
				String ProjectName = columnData[1];
				out.println("<option value=\"" + ProjectId + "\">"
						+ ProjectName + "</option>");
			}
		}

	}

	
	
	//Get Job Card By Project For Report
			if (ajaxFlag.equals("get_job_card_by_Project_for_report")) {
				String ProjectId = request.getParameter("ProjectId");
				//out.println("Project ID is\t------------" + ProjectId);
				int intSerialNo = 0;
				String strJobCardNo = "";
				String strProjectName = "";
				String strOutletName = "";
				String strAddress = "";
				String strCityName = "";
				String strState = "";
				String strFdHub = "";
				String strElement_qty = "";
				String strSheets_Received = "";
				String strCancel_Hold ="";
				String strAW_Sent_For_Approval ="";
				String strArtWorks_complete = "";
				String strReady_For_Dispatch = "";
				String strMaterial_Dispatched = "";
				String strAudit_Photo_Received = "";
				String strBilled = "";
				
				
				int elecount = 0;
				int outletCount = 0;
				int jobCardNo_Count = 0;
				int state_Count = 0;
				int element_Qty_count = 0;
				int sheets_Rec_Count = 0;
				int cancel_Hold_Count = 0;
				
				int awSent_for_Approval_Count = 0;
				int artworks_Complete_Count = 0;
				int ready_For_Dispatch_Count = 0;
				int material_dispatched_Count = 0;
				int total_materials = 0;
				int audit_photo_received = 0;
				int billed_Count = 0;
				int sum_of_elements =0;
				int jobCardId = 0;
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				if( !ProjectId.equalsIgnoreCase("select")){
					inData = scr.getMIS_Report_For_Central_Co_Ordinator(Integer.parseInt(ProjectId));
					System.out.println("This is indata Job Card Report " + inData);
					rowData = inData.split(rowSeperator);
				if(inData.contains("NO DATA")){
						out.println("<br><br><center><b>Insufficient Data Available<b><center>");
						return;
				}
				else{
					%>
<!-- <input type="text" class="filter" />  -->
<div id="report" style="height: 350px;overflow-y: scroll;">
<table class="jobCardStatusTable" cellspacing=0>
<thead>

<tr>
<th>Sl.No</th><th>Job Card No</th><th>Project Name</th><th>Outlet Name</th><th>Address</th><th>City</th><th>State</th><th>FD Hub</th>
<th>Element Qty</th><th>Sheets Received</th><th>Cancel/Hold</th> <th>AW Sent For Approval</th><th>Artworks Complete</th> 
<th>Ready For Dispatch</th><th>Material Dispatched</th><th>Audit/Photo Received</th><th>Billed</th></tr></thead>
					<%
					//System.out.println("Length of data is\t:" + rowData.length);
					for (int i = 0; i < rowData.length; i++) {
						columnData = rowData[i].split(columnSeperator);
						//System.out.println("columnData of data is\t:" + columnData.length);
							intSerialNo++;
							strJobCardNo = columnData[0];
							if(!strJobCardNo.equals("NA")){
								jobCardNo_Count ++;
							}
							strProjectName = columnData[1];
							strOutletName = columnData[2];
							if(!strOutletName.equals("") || !strOutletName.equals("NA")){
								outletCount ++;
							}
							strAddress = columnData[3];
							strCityName = columnData[4];
							strState = columnData[5];
							if(!strState.equals("NA")){
								state_Count ++;
							}
							strFdHub = columnData[6];
							strElement_qty = columnData[7];
							if(!strElement_qty.equals("NA")){
								sum_of_elements = sum_of_elements + Integer.parseInt(strElement_qty);
								element_Qty_count ++;
							}
							strSheets_Received = columnData[8];
							if(!strSheets_Received.equals("NA")){
								sheets_Rec_Count ++;
							}
							strCancel_Hold = columnData[9];
							if(!strCancel_Hold.equals("NA")){
								cancel_Hold_Count ++;
							}
							strAW_Sent_For_Approval = columnData[10];
							if(!strAW_Sent_For_Approval.equals("NA")){
								awSent_for_Approval_Count ++;
							}
							strArtWorks_complete = columnData[11];
							if(!strArtWorks_complete.equals("NA")){
								artworks_Complete_Count ++;
							}
							strReady_For_Dispatch = columnData[12];
							if(!strReady_For_Dispatch.equals("NA")){
								ready_For_Dispatch_Count ++;
							}
							strMaterial_Dispatched = columnData[13];
							total_materials ++;
							if(!strMaterial_Dispatched.equals("NA")){
								material_dispatched_Count ++;
							}
							strAudit_Photo_Received = columnData[14];
							if(!strAudit_Photo_Received.equals("NA")){
								audit_photo_received ++;
							}
							strBilled = columnData[15];
							if(!strBilled.equals("NA")){
								billed_Count ++;
							}
%>
<tbody>
<tr>
	<td><%=intSerialNo %></td>
	<td><%=strJobCardNo %></td>
	<td><%=strProjectName %></td>
	<td><%=strOutletName %></td>
	<td><%=strAddress %></td>
	<td><%=strCityName %></td>
	<td><%=strState %></td>
	<td><%=strFdHub %></td>
	<td><%=strElement_qty %></td>
	<td><%=strSheets_Received %></td>
	<td><%=strCancel_Hold %></td>
	<td><%=strAW_Sent_For_Approval %></td>
	<td><%=strArtWorks_complete %></td>
	<td><%=strReady_For_Dispatch%></td>
	<td><%=strMaterial_Dispatched %></td>
	<td><%=strAudit_Photo_Received%></td>
	<td><%=strBilled%></td>
</tr>

</tbody>
<%
}
%>
<tr>
	<td></td>
	<td align="right"><%=jobCardNo_Count %></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td align="right"><%=state_Count %></td>
	<td></td>
	<td align="right"><%=element_Qty_count %></td>
	<td align="right"><%=sheets_Rec_Count %></td>
	<td align="right"><%=cancel_Hold_Count %></td>
	<td align="right"><%=awSent_for_Approval_Count %></td>
	<td align="right"><%=artworks_Complete_Count %></td>
	<td align="right"><%=ready_For_Dispatch_Count %></td>
	<td align="right"><%=material_dispatched_Count %></td>
	<td align="right"><%=audit_photo_received %></td>
	<td align="right"><%=billed_Count %></td>
	
</tr>
<%				
%>
</table>
</div>
<div id="report_summary" style="margin-left: 50px;">
	<table id="summary" style="border: 2px;">
		<thead>
			<tr>
				<th>Summary</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>No of outlets</td>
				<td><%=outletCount %></td>
			</tr>
			<tr>
				<td>Total Element</td>
				<td><%=sum_of_elements %></td>
			</tr>
			<tr>
				<td>Sheets Received</td>
				<td><%=sheets_Rec_Count %></td>
			</tr>
			<tr>
				<td>Artworks Complete</td>
				<td><%=artworks_Complete_Count %></td>
			</tr>
			<tr>
				<td>Material Dispatched</td>
				<td><%=material_dispatched_Count %></td>
			</tr>
			<tr>
				<td>Balance to Dispatch</td>
				<td><%=total_materials - material_dispatched_Count %></td>
			</tr>
		</tbody>
	</table>
</div>

<% 

	}
				}
				else{
					%>
					<script type="text/javascript">
					alert("Select Project");
					</script>
					<%
				}
}
	//Get Job Card By Client
	
%>