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
				
				String strScope_Region = "NA";
				String strState = "NA";
				String strCity = "NA";
				String strStore_Name = "NA";
				String strStore_Status = "NA";
				String strCDO_Name = "NA";
				String strCDO_Contact ="NA";
				String strFD_Supervisor = "NA";
				String strContact_No = "NA";
				String strFD_Hub = "NA";
				String strMSheet_Received = "NA";
				String strJobCard_No = "NA";
				String strJc_Date = "NA";
				String strElement_Name = "NA";
				String strElement_Qty = "NA";
				String strTent_Meas_Date = "NA";
				String strTent_Impl_Date = "NA";
				String strPlanned_Impl_Date = "NA";
				String strNo_of_Visits = "NA";
				String strReson_For_Not_Measuring = "NA";
				String strCurrent_Meas_Sheet = "NA";
				String strAcutual_Date_Measurement = "NA";
				String strRe_Measured = "NA";
				String strMeasred_By = "NA";
				String strAW_Sent_For_Approval = "NA";
				String strAW_Approved = "NA";
				String strAW_Received = "NA";
				String strPlanned_Dispatched_Start_Date = "NA";
				String strPlanned_Dispatched_End_Date = "NA";
				String strActual_Dispatch_Start_Date = "NA";
				String strActual_Dispatch_Date = "NA";
				String strMaterial_Resent_Date = "NA";
				String strMaterial_Recd_Date = "NA";
				String strImpl_Perm_Recd = "NA";
				String strVisited_With_Material = "NA";
				String strImplemented_Date = "NA";
				String strCancelled_Date = "NA";
				String strImplemented_By = "NA";
				String strImpl_Sheet_Dc_Sent = "NA";
				String strPhotos_DC_Sent ="NA";
				String strPhotos_DC_Recd = "NA";
				String strBilled = "NA";
				String tempStoreName = "";
				String tempJobCardNo = "";
				
				int intSerialNo = 0;
				int elecount = 0;
				int region_Count = 0;
				int state_Count = 0;
				int city_Count = 0;
				int jobCardId = 0;
				int outlet_Count = 0;
				int jobCard_Count = 0;
				int sum_of_element_qty = 0;
				int no_of_visits_Count = 0;
				int actual_date_of_measuremen_Count = 0;
				int re_measured_date_Count = 0;
				int aw_approval_Count = 0;
				int aw_approved_date_Count = 0;
				int aw_received_date_Count = 0;
				int actual_dispatched_Count = 0;
				int material_resent_Count = 0;
				int material_received_date_Count = 0;
				int impl_perm_recd_date_Count = 0;
				int visited_with_material_Count = 0;
				int implementation_Count = 0;
				int implemented_by_Count = 0;
				int cancelled_Count = 0;
				int impl_dc_sheet_Count = 0;
				int photos_dc_sent_Count = 0;
				int photos_dc_recd_Count = 0;
				int billing_Count = 0;
				int sum_of_elements = 0;
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				if( !ProjectId.equalsIgnoreCase("select")){
					inData = scr.getJobCardByProjectForReport(Integer.parseInt(ProjectId));
					System.out.println("This is indata Job Card Report " + inData);
					rowData = inData.split(rowSeperator);
				if(inData.contains("NO DATA")){
						out.println("<br><br><center><b>Insufficient Data Available<b><center>");
						return;
				}
				else{
					%>
<table class="jobCardStatusTable" cellspacing=0>
<thead>
<tr><th colspan="16" align="center">Store Details</th><th colspan="9" align="center">Measurement Status</th><th colspan="2" align="center">Art Works Status</th>
<th colspan="6">Production Status</th>
<th colspan="7" align="center">Implementation Status</th><th colspan="2" align="center">Audit Status</th><th align="center">Billing Status</th></tr>
<tr>
<th>S.No</th><th>Region</th><th>State</th><th>City</th> <th>Store Name & Address</th><th>Store Status</th><th>CDO Name</th> <th>CDO Contact</th> <th>FD Supervisor</th>
<th>Contact No</th><th>FD Hub</th><th>MSheet Received Date</th><th>JobCard No</th><th>JobCard Date</th><th>Element Name</th><th>Element Qty</th>
<th>Tentative Measurement Client Target End Date</th><th>Tentative Implementation Client Target End Date</th>
<th>Planned Implementation Fd Target End Date</th><th>No of Visits Made</th><th>Reason For not Measuring</th><th>Current measurement status</th>
<th>Actual Date of Measurement</th><th>Re-Measured Date</th><th>Measured By</th><th>AW Sent For Approval Date</th><th>AW Approved Date</th>
<th>AW Received Date</th><th>Planned Dispatch Start Date</th><th>Planned Dispatch End Date</th><th>Actual Dispatch Start Date</th><th>Actual Dispatch Date</th>
<th>Material Resent Date</th><th>Material Received Date</th><th>Impl Permission Received Date</th><th>Visited with Material Date</th><th>Implemented Date</th>
<th>Cancelled Date</th><th>Implemented By</th><th>Impl Sheet/DC sent Date</th><th>Photos/DC sent Date</th><th>Photos/DC Received Date</th>
<th>Billed Date</th></thead></tr>
					<%
					for (int i = 0; i < rowData.length; i++) {
						columnData = rowData[i].split(columnSeperator);
							intSerialNo++;
							if(i==0)
							{
								if(!columnData[0].equals("NA"))
								{
									strScope_Region = columnData[0];
									region_Count ++;
								}
								if(!columnData[1].equals("NA"))
								{
									strState = columnData[1];
									state_Count ++;
								}
								if(!columnData[2].equals("NA"))
								{
									strCity = columnData[2];
									city_Count ++;
								}
								strStore_Name = columnData[3];
								if(strStore_Name != "NA")
								{
									tempStoreName = strStore_Name;
									outlet_Count ++;
								}
								strStore_Status = columnData[4];
								strCDO_Name = columnData[5];
								strCDO_Contact = columnData[6];
								strFD_Supervisor = columnData[7];
								strContact_No = columnData[8];
								if(!columnData[9].equalsIgnoreCase(null) || !columnData[9].equalsIgnoreCase("NA")){
									strFD_Hub = columnData[9];
								}
								strMSheet_Received = columnData[10];
								strJobCard_No = columnData[11];
								if(!strJobCard_No.equalsIgnoreCase("NA")){
									tempJobCardNo = strJobCard_No;
									jobCard_Count ++;
								}
								strJc_Date = columnData[12];
								strElement_Name = columnData[13];
								strElement_Qty = columnData[14];
								if(!strElement_Qty.equals("NA")){
									sum_of_element_qty = sum_of_element_qty + Integer.parseInt(strElement_Qty);
								}
								strTent_Meas_Date = columnData[15];
								strTent_Impl_Date = columnData[16];
								strPlanned_Impl_Date = columnData[17];
								strNo_of_Visits = columnData[18];
								if(!strNo_of_Visits.equals("NA")){
									no_of_visits_Count = no_of_visits_Count + Integer.parseInt(strNo_of_Visits);
								}
								strReson_For_Not_Measuring = columnData[19];
								strCurrent_Meas_Sheet = columnData[20];
								strAcutual_Date_Measurement = columnData[21];
								if(!strAcutual_Date_Measurement.equals("NA")){
									actual_date_of_measuremen_Count ++;
								}
								strRe_Measured = columnData[22];
								if(!strRe_Measured.equals("NA")){
									re_measured_date_Count ++;
								}
								strMeasred_By = columnData[23];
								strAW_Sent_For_Approval = columnData[24];
								if(!strAW_Sent_For_Approval.equals("NA")){
									aw_approval_Count ++;
								}
								strAW_Approved = columnData[25];
								if(!strAW_Approved.equals("NA")){
									aw_approved_date_Count ++;
								}
								strAW_Received = columnData[26];
								if(!strAW_Received.equals("NA")){
									aw_received_date_Count ++;
								}
								strPlanned_Dispatched_Start_Date = columnData[27];
								strPlanned_Dispatched_End_Date = columnData[28];
								strActual_Dispatch_Start_Date = columnData[29];
								strActual_Dispatch_Date = columnData[30];
								if(!strActual_Dispatch_Date.equals("NA")){
									actual_dispatched_Count ++;
								}
								strMaterial_Resent_Date = columnData[31];
								if(!strMaterial_Resent_Date.equals("NA")){
									material_resent_Count ++;
								}
								strMaterial_Recd_Date = columnData[32];
								if(!strMaterial_Recd_Date.equals("NA")){
									material_received_date_Count ++;
								}
								strImpl_Perm_Recd = columnData[33];
								if(!strImpl_Perm_Recd.equals("NA")){
									impl_perm_recd_date_Count ++;
								}
								strVisited_With_Material = columnData[34];
								if(!strVisited_With_Material.equals("NA")){
									visited_with_material_Count ++;
								}
								strImplemented_Date = columnData[35];
								if(!strImplemented_Date.equals("NA")){
									implementation_Count ++;
								}
								strCancelled_Date = columnData[36];
								if(!strCancelled_Date.equals("NA")){
									cancelled_Count ++;
								}
								strImplemented_By = columnData[37];
								if(!strImplemented_By.equals("NA")){
									implemented_by_Count ++;
								}
								strImpl_Sheet_Dc_Sent = columnData[38];
								if(!strImpl_Sheet_Dc_Sent.equals("NA")){
									impl_dc_sheet_Count ++;
								}
								strPhotos_DC_Sent = columnData[39];
								if(!strPhotos_DC_Sent.equals("NA")){
									photos_dc_sent_Count ++;
								}
								strPhotos_DC_Recd = columnData[40];
								if(!strPhotos_DC_Recd.equals("NA")){
									photos_dc_recd_Count ++;
								}
								strBilled = columnData[41];
								if(!strBilled.equals("NA")){
									billing_Count ++;
								}
								
							}else{
								
								if(!columnData[0].equals("NA"))
								{
									strScope_Region = columnData[0];
									region_Count ++;
								}
								if(!columnData[1].equals("NA"))
								{
									strState = columnData[1];
									state_Count ++;
								}
								if(!columnData[2].equals("NA"))
								{
									strCity = columnData[2];
									city_Count ++;
								}
								strStore_Name = columnData[3];
								
								if(!tempStoreName.equalsIgnoreCase(strStore_Name))
								{
									outlet_Count ++;
									tempStoreName = strStore_Name;
								}
								strStore_Status = columnData[4];
								strCDO_Name = columnData[5];
								strCDO_Contact = columnData[6];
								strFD_Supervisor = columnData[7];
								strContact_No = columnData[8];
								if(columnData[9] != null || !columnData[9].equalsIgnoreCase("null")){
									strFD_Hub = columnData[9];
								}
								strMSheet_Received = columnData[10];
								strJobCard_No = columnData[11];
								if(!strJobCard_No.equalsIgnoreCase(tempJobCardNo) && !strJobCard_No.equalsIgnoreCase("NA"))
								{
									jobCard_Count ++;
									tempJobCardNo = strJobCard_No;
								}
								strJc_Date = columnData[12];
								strElement_Name = columnData[13];
								strElement_Qty = columnData[14];
								if(!strElement_Qty.equals("NA")){
									sum_of_element_qty = sum_of_element_qty + Integer.parseInt(strElement_Qty);
								}
								strTent_Meas_Date = columnData[15];
								strTent_Impl_Date = columnData[16];
								strPlanned_Impl_Date = columnData[17];
								strNo_of_Visits = columnData[18];
								if(!strNo_of_Visits.equals("NA")){
									no_of_visits_Count = no_of_visits_Count + Integer.parseInt(strNo_of_Visits);
								}
								strReson_For_Not_Measuring = columnData[19];
								strCurrent_Meas_Sheet = columnData[20];
								strAcutual_Date_Measurement = columnData[21];
								if(!strAcutual_Date_Measurement.equals("NA")){
									actual_date_of_measuremen_Count ++;
								}
								strRe_Measured = columnData[22];
								if(!strRe_Measured.equals("NA")){
									re_measured_date_Count ++;
								}
								strMeasred_By = columnData[23];
								strAW_Sent_For_Approval = columnData[24];
								if(!strAW_Sent_For_Approval.equals("NA")){
									aw_approval_Count ++;
								}
								strAW_Approved = columnData[25];
								if(!strAW_Approved.equals("NA")){
									aw_approved_date_Count ++;
								}
								strAW_Received = columnData[26];
								if(!strAW_Received.equals("NA")){
									aw_received_date_Count ++;
								}
								strPlanned_Dispatched_Start_Date = columnData[27];
								strPlanned_Dispatched_End_Date = columnData[28];
								strActual_Dispatch_Start_Date = columnData[29];
								strActual_Dispatch_Date = columnData[30];
								if(!strActual_Dispatch_Date.equals("NA")){
									actual_dispatched_Count ++;
								}
								strMaterial_Resent_Date = columnData[31];
								if(!strMaterial_Resent_Date.equals("NA")){
									material_resent_Count ++;
								}
								strMaterial_Recd_Date = columnData[32];
								if(!strMaterial_Recd_Date.equals("NA")){
									material_received_date_Count ++;
								}
								strImpl_Perm_Recd = columnData[33];
								if(!strImpl_Perm_Recd.equals("NA")){
									impl_perm_recd_date_Count ++;
								}
								strVisited_With_Material = columnData[34];
								if(!strVisited_With_Material.equals("NA")){
									visited_with_material_Count ++;
								}
								strImplemented_Date = columnData[35];
								if(!strImplemented_Date.equals("NA")){
									implementation_Count ++;
								}
								strCancelled_Date = columnData[36];
								if(!strCancelled_Date.equals("NA")){
									cancelled_Count ++;
								}
								strImplemented_By = columnData[37];
								if(!strImplemented_By.equals("NA")){
									implemented_by_Count ++;
								}
								strImpl_Sheet_Dc_Sent = columnData[38];
								if(!strImpl_Sheet_Dc_Sent.equals("NA")){
									impl_dc_sheet_Count ++;
								}
								strPhotos_DC_Sent = columnData[39];
								if(!strPhotos_DC_Sent.equals("NA")){
									photos_dc_sent_Count ++;
								}
								strPhotos_DC_Recd = columnData[40];
								if(!strPhotos_DC_Recd.equals("NA")){
									photos_dc_recd_Count ++;
								}
								strBilled = columnData[41];
								if(!strBilled.equals("NA")){
									billing_Count ++;
								}
							}
							
%>
<tbody>
<tr>
	<td><%=intSerialNo %></td>
	<td><%=strScope_Region%></td>
	<td><%=strState %></td>
	<td><%=strCity %></td>
	<td><%=strStore_Name %></td>
	<td><%=strStore_Status %></td>
	<td><%=strCDO_Name %></td>
	<td><%=strCDO_Contact %></td>
	<td><%=strFD_Supervisor %></td>
	<td><%=strContact_No %></td>
	<td><%=strFD_Hub %></td>
	<td><%=strMSheet_Received %></td>
	<td><%=strJobCard_No %></td>
	<td><%=strJc_Date %></td>
	<td><%=strElement_Name %></td>
	<td><%=strElement_Qty %></td>
	<td><%=strTent_Meas_Date %></td>
	<td><%=strTent_Impl_Date %></td>
	<td><%=strPlanned_Impl_Date %></td>
	<td><%=strNo_of_Visits %></td>
	<td><%=strReson_For_Not_Measuring %></td>
	<td><%=strCurrent_Meas_Sheet %></td>
	<td><%=strAcutual_Date_Measurement %></td>
	<td><%=strRe_Measured %></td>
	<td><%=strMeasred_By %></td>
	<td><%=strAW_Sent_For_Approval %></td>
	<td><%=strAW_Approved %></td>
	<td><%=strAW_Received %></td>
	<td><%=strPlanned_Dispatched_Start_Date %></td>
	<td><%=strPlanned_Dispatched_End_Date %></td>
	<td><%=strActual_Dispatch_Start_Date %></td>
	<td><%=strActual_Dispatch_Date %></td>
	<td><%=strMaterial_Resent_Date %></td>
	<td><%=strMaterial_Recd_Date %></td>
	<td><%=strImpl_Perm_Recd %></td>
	<td><%=strVisited_With_Material %></td>
	<td><%=strImplemented_Date %></td>
	<td><%=strCancelled_Date %></td>
	<td><%=strImplemented_By %></td>
	<td><%=strImpl_Sheet_Dc_Sent %></td>
	<td><%=strPhotos_DC_Sent %></td>
	<td><%=strPhotos_DC_Recd %></td>
	<td><%=strBilled %></td>
</tr>
</tbody>
<%
}
	%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td align="right"><%=outlet_Count %></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td align="right"></td>
		<td align="right"><%=jobCard_Count %></td>
		<td></td>
		<td></td>
		<td align="right"><%=sum_of_element_qty %></td>
		<td align="right"></td>
		<td align="right"></td>
		<td align="right"></td>
		<td align="right"><%=no_of_visits_Count %></td>
		<td align="right"></td>
		<td align="right"></td>
		<td align="right"><%=actual_date_of_measuremen_Count %></td>
		<td align="right"><%=re_measured_date_Count %></td>
		<td></td>
		<td><%= aw_approval_Count %></td>
		<td><%= aw_approved_date_Count %></td>
		<td><%= aw_received_date_Count %></td>
		<td></td>
		<td></td>
		<td></td>
		<td><%=actual_dispatched_Count %></td>
		<td><%=material_resent_Count %></td>
		<td><%=material_received_date_Count %></td>
		<td><%=impl_perm_recd_date_Count %></td>
		<td><%=visited_with_material_Count %></td>
		<td><%=implementation_Count %></td>
		<td><%=cancelled_Count %></td>
		<td><%=implemented_by_Count %></td>
		<td><%=impl_dc_sheet_Count %></td>
		<td><%=photos_dc_sent_Count %></td>
		<td><%=photos_dc_recd_Count %></td>
		<td><%=billing_Count %></td>
	</tr>
	<%	
%>
</table>
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




