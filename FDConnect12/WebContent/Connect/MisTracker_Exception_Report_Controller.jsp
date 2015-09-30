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
				String strState = "";
				String strCityName = "";
				String strOutletName = "";
				String strAddress = "";
				String strTsi_so = "";
				String strTsi_phone = "";
				String strFd_Supervisor = "";
				String strTentative_Implementation_Date = ""; // client date (new column)
				String strTentative_Measurement_Date = ""; // client date (new column)
				String strVisitedButNotMeasured = ""; 
				String strRevisitedButNotMeasured = "";
				String strActual_Date_of_Measurement = ""; //client date (new column)
				String strPlanned_Measurement_date = "";
				String strPlanned_Implementation_Date = ""; //FD date (new column)
				String strReason_for_not_measuring = "";	//new column
				String strNumber_of_Visits = "";			//new column
				String strJobCardNo = "";
				String strElementName = "";
				String strPermission_Received = "";		//Yes/No
				String strMeasurement_No_Permission_Date = "NA"; 		//new column
				String strMeasurementCancelled = "";
				String strCancelled = "";
				//String strMEasurement_No_Permission_Date = "";
				String strJobCard_No_Permission_Date = "";
				String strElementDamaged = "";
				String strElementMissing = "";
				String strMaterialResent = "";
				String tempState = "";
				String tempCity = "";
				String tempStore = "";
				
				
				int elecount = 0;
				int stateCount =0 ;
				int cityCount = 0;
				int outletCount = 0;
				int tent_imp_date_Count = 0;
				int visited_but_not_measured_Count = 0;
				int revisited_but_not_measured_Count = 0;
				int no_of_visits = 0;
				int sum_of_no_of_visits = 0;
				int meas_no_per_Count = 0;
				int meas_cancelled_Count = 0;
				int jobCardNo_Count = 0;
				int jc_no_perm_Count = 0;
				int element_damaged_Count = 0;
				int element_missing_Count = 0;
				int cancelled_Count = 0;
				int material_resent_Count = 0;
				int jobCardId = 0;
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				if( !ProjectId.equalsIgnoreCase("select")){
					inData = scr.getJobCardByProjectForExceptionReport(Integer.parseInt(ProjectId));
					System.out.println("This is indata Job Card Report " + inData);
					rowData = inData.split(rowSeperator);
				if(inData.contains("NO DATA")){
						out.println("<br><br><center><b>Insufficient Data Available<b><center>");
						return;
				}
				else{
					%>
<!-- <input type="text" class="filter" />  -->
<table id="misReport" style="word-spacing: .5px; width: 100%; font-weight: normal; text-align: center; background-color: rgb(255, 255, 255)"
							class="display">
<thead>
<tr>
			<th><input type="hidden" ></input> </th>
			<th><input type="text" name="search_state" value="Search States" class="search_init" /></th>
			<th><input type="text" name="search_cities" value="Search Cities" class="search_init" /></th>
			<th><input type="text" name="search_storename" value="Search Storenames" class="search_init" /></th>
			<th><input type="text" name="search_address" value="Search Address" class="search_init" /></th>
			<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th>
			<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th>
			<th></th><th></th><th></th>
			
		</tr>

<tr style="color: #39939C">
<th>Sl.No</th><th>State</th><th>City</th> <th>Store Name</th><th>Address</th><th>CDO Name</th> <th>CDO Contact</th> <th>FD Supervisor</th>
<th>Contact No</th><th>Tentative Measured Date</th> <th>Tentative Implement Date</th> <th>Visited But Not Measured</th><th>Revisited But Not Measured</th> 
<th>Planned Measurement Date</th><th>Planned Implementation Date</th> <th>Reason For not Measuring</th><th>No_of Visits Made</th>
<th>Measurement No Permission</th><th>Measurement Cancelled</th><th>JobCard No</th><th>Element Name</th><th>Implementation No Permission </th>
<th>Element Damaged</th><th>Element Missing</th><th>Cancelled</th><th>Material Resent</th></tr></thead><tbody>
					<%
					//System.out.println("Length of data is\t:" + rowData.length);
					for (int i = 0; i < rowData.length; i++) {
						columnData = rowData[i].split(columnSeperator);
						//System.out.println("columnData of data is\t:" + columnData.length);
							intSerialNo++;
							if(i==0){
								tempState= columnData[0];
								strState = columnData[0];
								if(!strState.equals("NA")){
									stateCount ++;
								}
								tempCity = columnData[1];
								strCityName = columnData[1];
								if(!strCityName.equals("NA")){
									cityCount ++;
								}			
								tempStore = columnData[2];
								strOutletName = columnData[2];
								if(!strOutletName.equals("") || !strOutletName.equals("NA")){
									outletCount ++;
								}
								strAddress = columnData[3];
								if(strAddress == null || strAddress.equals("null"))
								{
									strAddress = "NA";
								}
								strTsi_so = columnData[4];
								strTsi_phone = columnData[5];
								strFd_Supervisor = columnData[6];
								strTsi_phone = columnData[7];
								strTentative_Measurement_Date = columnData[8];
								if(!strTentative_Measurement_Date.equals("NA")){
									tent_imp_date_Count ++;
								}
								strTentative_Implementation_Date = columnData[9];
								if(!strTentative_Implementation_Date.equals("NA")){
									tent_imp_date_Count ++;
								}
								strVisitedButNotMeasured = columnData[10];
								if(!strVisitedButNotMeasured.equals("NA")){
									visited_but_not_measured_Count ++;
								}
								strRevisitedButNotMeasured = columnData[11];
								if(!strRevisitedButNotMeasured.equals("NA")){
									revisited_but_not_measured_Count ++;
								}
								strPlanned_Measurement_date  = columnData[12];
								strPlanned_Implementation_Date = columnData[13];
								strReason_for_not_measuring = columnData[14];
								strNumber_of_Visits = columnData[15];
								if(strNumber_of_Visits != "NA"){
									sum_of_no_of_visits = sum_of_no_of_visits + Integer.parseInt(strNumber_of_Visits);
								}
								strMeasurement_No_Permission_Date = columnData[16]; //Measurement No Permission
								if(!strMeasurement_No_Permission_Date.equals("NA")){
									meas_no_per_Count++;
								}
								strMeasurementCancelled = columnData[17];
								if(!strMeasurementCancelled.equals("NA")){
									meas_cancelled_Count ++;
								}
								strJobCardNo = columnData[18];
								if(!strJobCardNo.equals("NA")){
									jobCardNo_Count ++;
								}
								strElementName = columnData[19];
								
								strJobCard_No_Permission_Date = columnData[20];
								if(!strJobCard_No_Permission_Date.equals("NA")){
									jc_no_perm_Count++;
								}
								
								strElementDamaged = columnData[21];
								if(!strElementDamaged.equals("NA")){
									element_damaged_Count ++;
								}
								strElementMissing = columnData[22];
								strCancelled = columnData[23];
								if(!strCancelled.equals("NA")){
									cancelled_Count ++;
								}
								strMaterialResent = columnData[24];
								if(!strMaterialResent.equals("NA")){
									material_resent_Count ++;
								}
							}else{
								strState = columnData[0];
								if(!tempState.equalsIgnoreCase(strState))
								{
									stateCount++;
									tempState = strState;
								}
								strCityName = columnData[1];
								if(!strCityName.equals("NA")){
									if(!tempCity.equalsIgnoreCase(strCityName))
									{
										cityCount++;
										tempCity = strCityName;
									}
								}
								strOutletName = columnData[2];
								if(!tempStore.equalsIgnoreCase(strOutletName))
								{
									outletCount ++;
									tempStore = strOutletName;
								}
								strAddress = columnData[3];
								if(strAddress.equals(null) || strAddress.equals("null") || strAddress.equals(""))
								{
									strAddress = "NA";
								}
								strTsi_so = columnData[4];
								strTsi_phone = columnData[5];
								strFd_Supervisor = columnData[6];
								strTsi_phone = columnData[7];
								strTentative_Measurement_Date = columnData[8];
								strTentative_Implementation_Date = columnData[9];
								if(!strTentative_Implementation_Date.equals("NA")){
									tent_imp_date_Count ++;
								}
								strVisitedButNotMeasured = columnData[10];
								if(!strVisitedButNotMeasured.equals("NA")){
									visited_but_not_measured_Count ++;
								}
								strRevisitedButNotMeasured = columnData[11];
								if(!strRevisitedButNotMeasured.equals("NA")){
									revisited_but_not_measured_Count ++;
								}
								strPlanned_Measurement_date = columnData[12];
								strPlanned_Implementation_Date = columnData[13];
								strReason_for_not_measuring = columnData[14];
								strNumber_of_Visits = columnData[15];
								if(strNumber_of_Visits != "NA"){
									sum_of_no_of_visits = sum_of_no_of_visits + Integer.parseInt(strNumber_of_Visits);
								}
								strMeasurement_No_Permission_Date = columnData[16]; //Measurement No Permission
								if(!strMeasurement_No_Permission_Date.equals("NA")){
									meas_no_per_Count++;
								}
								strMeasurementCancelled = columnData[17];
								if(!strMeasurementCancelled.equals("NA")){
									meas_cancelled_Count ++;
								}
								strJobCardNo = columnData[18];
								if(!strJobCardNo.equals("NA")){
									jobCardNo_Count ++;
								}
								strElementName = columnData[19];
								
								strJobCard_No_Permission_Date = columnData[20];
								if(!strJobCard_No_Permission_Date.equals("NA")){
									jc_no_perm_Count++;
								}
								strElementDamaged = columnData[21];
								if(!strElementDamaged.equals("NA")){
									element_damaged_Count ++;
								}
								strElementMissing = columnData[22];
								strCancelled = columnData[23];
								if(!strCancelled.equals("NA")){
									cancelled_Count ++;
								}
								strMaterialResent = columnData[24];
								if(!strMaterialResent.equals("NA")){
									material_resent_Count ++;
								}
							}
%>

<tr>
	<td><%=intSerialNo %></td>
	<td><%=strState %></td>
	<td><%=strCityName %></td>
	<td><%=strOutletName %></td>
	<td><%=strAddress %></td>
	<td><%=strTsi_so %></td>
	<td><%=strTsi_phone %></td>
	<td><%=strFd_Supervisor %></td>
	<td><%=strTsi_phone %></td>
	<td><%=strTentative_Measurement_Date%></td>
	<td><%=strTentative_Implementation_Date %></td>
	<td><%=strVisitedButNotMeasured%></td>
	<td><%=strRevisitedButNotMeasured%></td>
	<td><%=strPlanned_Measurement_date %></td>
	<td><%=strPlanned_Implementation_Date %></td>
	<td><%=strReason_for_not_measuring %></td>
	<td><%=strNumber_of_Visits %></td>
	<td><%=strMeasurement_No_Permission_Date %></td>
	<td><%=strMeasurementCancelled %></td>
	<td><%=strJobCardNo %></td>
	<td><%=strElementName %></td>
	<td><%=strJobCard_No_Permission_Date%></td>
	<td><%=strElementDamaged %></td>
	<td><%=strElementMissing %></td>
	<td><%=strCancelled %></td>
	<td><%=strMaterialResent %></td>
</tr>


<%

}

%>
<tr>
	<td><label>Total</label></td>
	<td align="right"><%=stateCount %></td>
	<td align="right"><%=cityCount %></td>
	<td align="right"><%=outletCount %></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td align="right"><%=visited_but_not_measured_Count%></td>
	<td align="right"><%=revisited_but_not_measured_Count%></td>
	<td></td>
	<td></td>
	<td></td>
	<td align="right"><%=sum_of_no_of_visits%></td>
	<td align="right"><%=meas_no_per_Count%></td>
	<td align="right"><%=meas_cancelled_Count%></td>
	<td align="right"><%=jobCardNo_Count %></td>
	<td align="right"><%=jobCardNo_Count %></td>
	<td align="right"><%=jc_no_perm_Count%></td>
	<td align="right"><%=element_damaged_Count %></td>
	<td align="right"><%=element_missing_Count %></td>
	<td align="right"><%=cancelled_Count %></td>
	<td align="right"><%=material_resent_Count %></td>
</tr>
</tbody>
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
