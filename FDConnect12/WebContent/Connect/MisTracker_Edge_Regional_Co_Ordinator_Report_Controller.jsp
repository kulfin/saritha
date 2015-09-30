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
				String strAllignedToMeasure = "";
				String strTsi_So_Name = "";
				String strMeasure_By = "NA";
				String strElements = "";
				String strMeasured_On = "NA";
				String strMaterial_Reached = "";
				String strPermission_Received = "";
				String strImplemented_By = "";
				String strImplemented_On = "";
				String strProduce_Cancel = "";
				String strAudit = "";
				String strAudit_Photos_Couriered = "";
				String strMaterialReceived ="NA";
				
				int elecount = 0;
				int jobCardNo_Count = 0;
				int outlet_Count = 0;
				int state_Count = 0;
				int element_Qty_count = 0;
				int aligned_to_measure = 0;
				int tsi_so_name =0;
				int measured_by = 0;
				int elements_Count = 0;
				int elements_sum = 0;
				int measured = 0;
				int not_measured = 0;
				int material_reached = 0;
				int permission_received = 0;
				int implemented_By = 0;
				int implemented_On = 0;
				int not_implemented = 0;
				int produce_cancel = 0;
				int audit = 0;
				int audit_photos_couriered = 0;
				int material_received = 0;
				int sum_of_elements_qty =0;
				int jobCardId = 0;
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				if( !ProjectId.equalsIgnoreCase("select")){
					inData = scr.getMIS_Report_For_Regional_Co_Ordinator(Integer.parseInt(ProjectId));
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
<th>Element Qty</th><th>Alligned to Measure</th><th>TSI/SO Name</th><th>Measured By</th><th>Elements</th><th>Measured On</th>
<th>Material Reached</th><th>Permission Received</th><th>Implemented By</th><th>Implemented On</th><th>Produce&Cancel</th>
<th>Audit</th><th>Audit/Photo's Couriered</th></tr></thead>
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
							if(!strOutletName.equals("NA")){
								outlet_Count ++;
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
								sum_of_elements_qty = sum_of_elements_qty + Integer.parseInt(strElement_qty);
								element_Qty_count ++;
							}
							strAllignedToMeasure = columnData[8];
							if(!strAllignedToMeasure.equals("NA")){
								aligned_to_measure ++;
							}
							strTsi_So_Name = columnData[9];
							if(!strTsi_So_Name.equals("NA")){
								tsi_so_name ++;
							}
							strMeasure_By = columnData[10];
							if(!strMeasure_By.equals("NA")){
								measured_by ++;
							}else{
								strMeasure_By = "NA";
							}
							strElements = columnData[11];
							if(!strElements.equals("NA")){
								elements_sum = elements_sum + Integer.parseInt(strElements);
								elements_Count ++;
							}
							strMeasured_On = columnData[12];
							if(!strMeasured_On.equals("NA")){
								measured ++;
							}else{
								strMeasured_On = "NA";
								not_measured ++;
							}
							strMaterial_Reached = columnData[13];
							if(!strMaterial_Reached.equals("NA")){
								material_reached ++;
							}
							strPermission_Received = columnData[14];
							if(!strPermission_Received.equals("NA")){
								permission_received ++;
							}
							strImplemented_By = columnData[15];
							if(!strImplemented_By.equals("NA")){
								implemented_By ++;
							}
							strImplemented_On = columnData[16];
							if(!strImplemented_On.equals("NA")){
								implemented_On ++;
							}else{
								not_implemented ++;
							}
							strProduce_Cancel = columnData[17];
							if(!strProduce_Cancel.equals("NA")){
								produce_cancel ++;
							}
							strAudit = columnData[18];
							if(!strAudit.equals("NA")){
								audit ++;
							}
							strAudit_Photos_Couriered = columnData[19];
							if(!strAudit_Photos_Couriered.equals("NA")){
								audit_photos_couriered ++;
							}
							strMaterialReceived = columnData[20];
							if(!strMaterialReceived.equals("NA")){
								material_received ++;
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
	<td><%=strAllignedToMeasure %></td>
	<td><%=strTsi_So_Name %></td>
	<td><%=strMeasure_By %></td>
	<td><%=strElements %></td>
	<td><%=strMeasured_On %></td>
	<td><%=strMaterial_Reached %></td>
	<td><%=strPermission_Received %></td>
	<td><%=strImplemented_By %></td>
	<td><%=strImplemented_On %></td>
	<td><%=strProduce_Cancel %></td>
	<td><%=strAudit %></td>
	<td><%=strAudit_Photos_Couriered %></td>
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
	<td align="right"><%=sum_of_elements_qty %></td>
	<td align="right"><%=aligned_to_measure %></td>
	<td align="right"><%=tsi_so_name %></td>
	<td align="right"><%=measured_by %></td>
	<td align="right"><%=elements_sum %></td>
	<td align="right"><%=measured %></td>
	<td align="right"><%=material_reached %></td>
	<td align="right"><%=permission_received %></td>
	<td align="right"><%=implemented_By %></td>
	<td align="right"><%=implemented_On %></td>
	<td align="right"><%=produce_cancel %></td>
	<td align="right"><%=audit %></td>
	<td align="right"><%=audit_photos_couriered %></td>
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
				<td><%=outlet_Count %></td>
			</tr>
			<tr>
				<td>Total Elements</td>
				<td><%=elements_Count %></td>
			</tr>
			<tr>
				<td>Alligned to Measure</td>
				<td><%=aligned_to_measure %></td>
			</tr>
			<tr>
				<td>Measured</td>
				<td><%=measured %></td>
			</tr>
			<tr>
				<td>Not Measured</td>
				<td><%=not_measured %></td>
			</tr>
			<tr>
				<td>Balance to Measure</td>
				<td><%=not_measured %></td>
			</tr>
			<tr>
				<td>Material Received</td>
				<td><%=material_received %></td>
			</tr>
			<tr>
				<td>Material Implemented</td>
				<td><%=implemented_On %></td>
			</tr>
			<tr>
				<td>Balance to Implement</td>
				<td><%=not_implemented %></td>
			</tr>
			<tr>
				<td>Awaiting Permissoin</td>
				<td><%="NA" %></td>
			</tr>
			<tr>
				<td>Cancelled</td>
				<td><%="NA" %></td>
			</tr>
			<tr>
				<td>Audited</td>
				<td><%=audit %></td>
			</tr>
			<tr>
				<td>Audit Photos Couriered</td>
				<td><%=audit_photos_couriered %></td>
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