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

	//Get Division
	if (ajaxFlag.equals("get_division")) {
		String ProjectId = request.getParameter("ProjectId");
		outData = ProjectId;

		inData = scr.getDivision(outData);
		rowData = inData.split(rowSeperator);
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String divisionId = columnData[0];
				String divisionName = columnData[1];
				out.println(divisionName);
			}
		}

	}
	 //Get Job Card Bulk Entry Mode
	if (ajaxFlag.equals("get_job_card_by_Project_bulk_entry_mode")) {
		String ProjectId = request.getParameter("ProjectId");
		

		inData = scr.getJobCardByProjectBulkEntryMode(ProjectId);
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String jobCardId = columnData[0];
				String jobCardNumber = columnData[1];
				out.println("<option value=\"" + jobCardId + "\">"
						+ jobCardNumber + "</option>");
			}
		}

	}
	 
	 //Get Store By Project Id Bulk Entry Mode
	if (ajaxFlag.equals("get_store_by_Project_bulk_entry_mode")) {
		String ProjectId = request.getParameter("ProjectId");
		

		inData = scr.getStoreByProjectBulkEntryMode(ProjectId);
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String storeId = columnData[0];
				String storeName = columnData[1];
				out.println("<option value=\"" + storeId + "\">"
						+ storeName + "</option>");
			}
		}

	}
	//Get Job Card By Project Select Mode
	if (ajaxFlag.equals("get_job_card_by_Project_select_mode")) {
		String ProjectId = request.getParameter("ProjectId");
		String jobCardNo;
		String jobCardId;
		String storeName;
		String cityName;
		String address;
		String elementName = null;
		String componentName = null;
		String materialName = null;
		String quantity = null;
		String elementDetailRowData[];
		String elementDetailColumnData[];
		String fieldColumnSeperator = "!#&!";
		String fieldRowSeperator = "!@!";

		inData = scr.getJobCardByProjectSelectMode(Integer.parseInt(ProjectId));
		System.out.println("This is indata " + inData);
		rowData = inData.split(rowSeperator);
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 6) {
				elementDetailRowData = (columnData[0]
						.split(fieldRowSeperator));
				jobCardId = columnData[1];
				jobCardNo = columnData[2];
				storeName = columnData[3];
				address = columnData[4];
				cityName = columnData[5];
			} else {
				out.println("data is unsufficient to display");
				return;
			}
%>
<div id="jc_summary<%=i%>" class="jc_summary"><span
	id="jc_checkbox"> <input type="checkbox" name="checkJobCard"
	value="<%=jobCardId%>"></input> </span> <span class="jc_summary_fields"
	onclick="toggleOperation('jc_detail<%=i%>')"> <span
	style="margin: 0 0 0 10px;  color: #000000;">JC
Number:<span
	style="color: rgb(70, 66, 66); margin: 0 0 0 8px; font-weight: normal;"><%=jobCardNo%></span></span>
<span style="margin: 0 0 0 27px; color: #000000; ">
Store Name:<span
	style="color: rgb(70, 66, 66); margin: 0 0 0 8px; font-weight: normal;"><%=storeName%>
</span></span> <span style="margin: 0 0 0 27px; color: #000000; ">Address:<span
	style="color: rgb(70, 66, 66); margin: 0 0 0 8px; "><%=address%>
</span></span> <span style="margin: 0 0 0 27px; color: #000000; ">City:<span
	style="color: rgb(70, 66, 66); margin: 0 0 0 8px; font-weight: normal;"><%=cityName%></span></span>
</span></div>

<div id="jc_detail<%=i%>" class="jc_detail">
<table class="jc_detail_table" cellspacing="0">
	<tr>
		<th>Element Type</th>
		<th>Component</th>
		<th>Material</th>
		<th>Quantity</th>
	</tr>
	<%
		for (int j = 0; j < elementDetailRowData.length; j++) {
					System.out.println("this is element row data "
							+ elementDetailRowData[j]);
					elementDetailColumnData = ((elementDetailRowData[j])
							.split(fieldColumnSeperator));
					if (elementDetailColumnData.length == 4) {
						elementName = elementDetailColumnData[0];
						componentName = elementDetailColumnData[1];
						materialName = elementDetailColumnData[2];
						quantity = elementDetailColumnData[3];
					} else {
						continue;
					}
	%>
	<tr>
		<td><%=elementName%></td>
		<td><%=componentName%></td>
		<td><%=materialName%></td>
		<td><%=quantity%></td>
	</tr>
	<%
		}
	%>
</table>
</div>

<%
	}

	}
	
	//Get Job Card By Client
	if (ajaxFlag.equals("get_job_card_by_client")) {
		String clientId = request.getParameter("clientId");
		outData =clientId;
        inData = scr.getJobCardByClient(outData);
		rowData = inData.split(rowSeperator);
		
		out.println("</select><input type=\"button\" value=\"Search\" "+
	      "onclick=\"getJobCardByJobCardNumber();\" style=\"width:100px;margin:10 10 10 10px;\">  ");
		 out.println("<br>");
		out.println("<select data-placeholder=\"Select Job Cards\" "+ 
		"id=\"filter_job_card_select\" style=\"width:1250px;margin:20 0 0 0px;\" multiple class=\"chzn-select\"  tabindex=\"8\">");
		out.println("<option value=\"\"></option>");
		
		      
		
	    for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String jobCardId = columnData[0];
				String jobCardNumber = columnData[1];
				out.println("<option value=\"" + jobCardId + "\">"
						+ jobCardNumber + "</option>");
			}
		}
     
	}	
	
	//Get Job Card By Card Number
	if (ajaxFlag.equals("get_job_card_by_job_card_number")) {
		String jobCardId = request.getParameter("jobCardId");
		String jobCardNo;
		
		String storeId;
		String storeName;
		String cityName;
		String address;
		String ProjectName;
		String elementName = null;
		String componentName = null;
		String materialName = null;
		String quantity = null;
		String elementDetailRowData[]=null;
		String elementDetailColumnData[];
		String fieldColumnSeperator = "!#&!";
		String fieldRowSeperator = "!@!";
		
		System.out.println("This is job card id cnt " + jobCardId);
		
		inData = scr.getJobCardByJobCardNumber(jobCardId);
		System.out.println("This is indata controller " + inData);
		rowData = inData.split(rowSeperator);
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 8) {
				elementDetailRowData = (columnData[0]
						.split(fieldRowSeperator));
				storeId = columnData[1];
				jobCardId = columnData[2];
				jobCardNo = columnData[3];
				storeName = columnData[4];
				address = columnData[5];
				cityName = columnData[6];
				ProjectName = columnData[7];
				
				out.println(storeId+columnSeperator+address+columnSeperator+cityName+rowSeperator);
			} else {
				elementDetailRowData=null;
				//return;
			}


	}
	out.println("<table style=\"width:820px; border:solid 1px lightgray;\" class=\"component_detail\" cellspacing=\"0\">");
	out.println("<tr ><th style=\"color:red;background:#FFFFFF;border:solid 1px lightgray;\">"+
	"Element Type</th><th style=\"color:red;background:#FFFFFF;border:solid 1px lightgray;\">"+
	"Component</th><th style=\"color:red;background:#FFFFFF; border:solid 1px lightgray;\">"+
	"Material</th><th style=\"color:red;background:#FFFFFF;border:solid 1px lightgray;\">Qty</th></tr>");
	if(elementDetailRowData!=null)
	for (int j = 0; j < elementDetailRowData.length; j++) {
		System.out.println("this is element row data "
				+ elementDetailRowData[j]);
		elementDetailColumnData = ((elementDetailRowData[j])
				.split(fieldColumnSeperator));
		if (elementDetailColumnData.length == 4) {
			elementName = elementDetailColumnData[0];
			componentName = elementDetailColumnData[1];
			materialName = elementDetailColumnData[2];
			quantity = elementDetailColumnData[3];
		} else {
			continue;
		}
		

out.println("<tr>"+
//"<td style=\"width:39px;\"></td>"+
//"<td style=\"width:153px;\"></td>"+
//"<td style=\"width:153px;\"></td>"+
//"<td style=\"width:230px;\"></td>"+
//"<td style=\"width:162px;\"></td>"+
"<td style=\"width:200px;border:solid 1px lightgray;\"><input type=\"text\" style=\"width:190px;\"  readonly value=\""+elementName+"\"></td>"+
"<td style=\"width:250px;border:solid 1px lightgray;\"><input type=\"text\" style=\"width:240px;\"   readonly value=\""+componentName+"\"></td>"+
"<td style=\"width:300px;border:solid 1px lightgray;\"><input type=\"text\"  style=\"width:285px;\"  readonly value=\""+materialName+"\"></td>"+
"<td style=\"width:60px;border:solid 1px lightgray;\"><input type=\"text\"   size=\"4\" readonly value=\""+quantity+"\"></td>"+
"</tr>");




	
}
	out.println("</table>");
}	
	//Get Job Card By Card Number
	if (ajaxFlag.equals("get_job_card_by_store")) {
		String storeId = request.getParameter("storeId");
		String jobCardNo;
		
        String jobCardId;	
		String storeName;
		String cityName;
		String address;
		String ProjectName;
		String elementName = null;
		String componentName = null;
		String materialName = null;
		String quantity = null;
		String elementDetailRowData[]=null;
		String elementDetailColumnData[];
		String fieldColumnSeperator = "!#&!";
		String fieldRowSeperator = "!@!";
		
	//	System.out.println("This is job card id cnt " + jobCardId);
		
		inData = scr.getJobCardByStore(storeId);
		System.out.println("This is indata controller " + inData);
		rowData = inData.split(rowSeperator);
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 7) {
				elementDetailRowData = (columnData[0]
						.split(fieldRowSeperator));
				
				jobCardId = columnData[1];
				jobCardNo = columnData[2];
				storeName = columnData[3];
				address = columnData[4];
				cityName = columnData[5];
				ProjectName = columnData[6];
				
				out.println(jobCardId+columnSeperator+address+columnSeperator+cityName+rowSeperator);
			} else {
				out.println("data is unsufficient to display");
				return;
			}


	}
		out.println("<table style=\"width:820px; border:solid 1px lightgray;\" class=\"component_detail\" cellspacing=\"0\">");
		out.println("<tr ><th style=\"color:red;background:#FFFFFF;border:solid 1px lightgray;\">"+
		"Element Type</th><th style=\"color:red;background:#FFFFFF;border:solid 1px lightgray;\">"+
		"Component</th><th style=\"color:red;background:#FFFFFF; border:solid 1px lightgray;\">"+
		"Material</th><th style=\"color:red;background:#FFFFFF;border:solid 1px lightgray;\">Qty</th></tr>");
		if(elementDetailRowData!=null)
			for (int j = 0; j < elementDetailRowData.length; j++) {
				System.out.println("this is element row data "
						+ elementDetailRowData[j]);
				elementDetailColumnData = ((elementDetailRowData[j])
						.split(fieldColumnSeperator));
				if (elementDetailColumnData.length == 4) {
					elementName = elementDetailColumnData[0];
					componentName = elementDetailColumnData[1];
					materialName = elementDetailColumnData[2];
					quantity = elementDetailColumnData[3];
				} else {
					continue;
				}
				

				out.println("<tr>"+
						//"<td style=\"width:39px;\"></td>"+
						//"<td style=\"width:153px;\"></td>"+
						//"<td style=\"width:153px;\"></td>"+
						//"<td style=\"width:230px;\"></td>"+
						//"<td style=\"width:162px;\"></td>"+
						"<td style=\"width:200px;border:solid 1px lightgray;\"><input type=\"text\" style=\"width:190px;\"  readonly value=\""+elementName+"\"></td>"+
						"<td style=\"width:250px;border:solid 1px lightgray;\"><input type=\"text\" style=\"width:240px;\"   readonly value=\""+componentName+"\"></td>"+
						"<td style=\"width:300px;border:solid 1px lightgray;\"><input type=\"text\"  style=\"width:285px;\"  readonly value=\""+materialName+"\"></td>"+
						"<td style=\"width:60px;border:solid 1px lightgray;\"><input type=\"text\"   size=\"4\" readonly value=\""+quantity+"\"></td>"+
						"</tr>");




	
		}
	out.println("</table>");	
}	
	
	//Get Element
	if (ajaxFlag.equals("get_element")) {
		String storeId = request.getParameter("storeId");
		
		String elementId;
		String elementName = null;
		String componentName = null;
		String materialName = null;
		String quantity = null;
		String elementDetailRowData[];
		String elementDetailColumnData[];
		String fieldColumnSeperator = "!#&!";
		String fieldRowSeperator = "!@!";

		inData = scr.getElementByStore(Integer.parseInt(storeId));
		System.out.println("This is indata " + inData);
		rowData = inData.split(rowSeperator);
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 3) {
				elementDetailRowData = (columnData[0]
						.split(fieldRowSeperator));
				elementId = columnData[1];
				elementName = columnData[2];
			
			} else {
				out.println("data is unsufficient to display");
				return;
			}
%>

<div id="element_summary<%=i%>" class="element_summary">
<span id="element_checkbox"> 
<input type="checkbox" name="elementSelectCheckbox" id="element_select<%=i%>" value="<%=elementId%>"></input> 
</span>
 
<span class="element_summary_fields"> 

<span style="margin: 0 0 0 10px;  color: #000000;">Element Type Name:
<span style=" margin: 0 0 0 8px;">
<input type="text" readonly size="30" style="height:15px;"   value="<%=elementName%>">
</span>
</span>

<span style="margin: 0 0 0 10px;  color: #000000;">Status:
<span style=" margin: 0 0 0 8px;">
<select style="width:190px;" id="element_status_select<%=i%>"><option>select</option></select>
</span>
</span>

<span style="margin: 0 0 0 10px;  color: #000000;">Date:
<span style=" margin: 0 0 0 8px;">
<input id="date<%=i%>" style="height:15px;"  onclick="datePicker('date<%=i%>')" type="text" size="16" />
</span>
</span>

<span style="margin: 0 0 0 10px;  color: #000000;">Comments:
<span style=" margin: 0 0 0 8px;">
<input style="height:15px;" id="comments<%=i%>" type="text" size="50">
</span>
</span>

</span>
</div>

<div id="element_detail<%=i%>" class="element_detail">
<table class="element_detail_table" cellspacing="0">
	<tr>
	
		<th>Component</th>
		<th>Material</th>
		<th>Quantity</th>
	</tr>
	<%
		for (int j = 0; j < elementDetailRowData.length; j++) {
					System.out.println("this is element row data "
							+ elementDetailRowData[j]);
					elementDetailColumnData = ((elementDetailRowData[j])
							.split(fieldColumnSeperator));
					if (elementDetailColumnData.length == 3) {
					
						componentName = elementDetailColumnData[0];
						materialName = elementDetailColumnData[1];
						quantity = elementDetailColumnData[2];
					} else {
						continue;
					}
	%>
	<tr>
	
		<td><%=componentName%></td>
		<td><%=materialName%></td>
		<td><%=quantity%></td>
	</tr>
	<%
		}
	%>
</table>
</div>


<%
	}
		out.println("<input type=\"hidden\" id=\"number_of_rows\" value=\""+rowData.length+"\">");
	}


	//Get Job Card Status
	if (ajaxFlag.equals("get_jc_status")) {
		inData = scr.getJCStatus();
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String jcStatusId = columnData[0];
				String jcStatusName = columnData[1];
				out.println("<option value=\"" + jcStatusId + "\">"
						+ jcStatusName + "</option>");
			}
		}

	}
	
	//Get Element Status
	if (ajaxFlag.equals("get_element_status")) {
		inData = scr.getElementStatus();
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String elementStatusId = columnData[0];
				String elementStatusName = columnData[1];
				out.println("<option value=\"" + elementStatusId + "\">"
						+ elementStatusName + "</option>");
			}
		}

	}

	//Get Courier Name
	if (ajaxFlag.equals("get_courier_name")) {
		inData = scr.getCourier();
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String courierId = columnData[0];
				String courierName = columnData[1];
				out.println("<option value=\"" + courierId + "\">"
						+ courierName + "</option>");
			}
		}

	}
	
	//Get Assembly Unit
	if (ajaxFlag.equals("get_assembly_unit")) {
		inData = scr.getAssemblyUnit();
		rowData = inData.split(rowSeperator);
		out.println("<option value=\"select\">select</option>");
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {

				String assemblyUnitId = columnData[0];
				String assemblyUnitName = columnData[1];
				out.println("<option value=\"" + assemblyUnitId + "\">"
						+ assemblyUnitName + "</option>");
			}
		}

	}
	
	//Update Job Card
	if (ajaxFlag.equals("update_job_card")) {
		outData = "";
		String selectedJcId = request.getParameter("selectedJcId");
		System.out.println("selectedJcId::"+selectedJcId);
		String jcStatusId = request.getParameter("jcStatusId");
		System.out.println("jcStatusId::"+jcStatusId);
		String statusDate = request.getParameter("statusDate");
		String comments = request.getParameter("comments");
		String dcNumber = request.getParameter("dcNumber");
		String dcDate = request.getParameter("dcDate");
		String courierId = request.getParameter("courierId");
		String assignedDate = request.getParameter("assignedDate");
		String completionDate = request.getParameter("completionDate");
		String assemblyUnitId = request.getParameter("assemblyUnitId");

		outData = selectedJcId + columnSeperator + jcStatusId
				+ columnSeperator + statusDate + columnSeperator
				+ comments + columnSeperator + assemblyUnitId
				+ columnSeperator + assignedDate + columnSeperator
				+ completionDate + columnSeperator + dcNumber
				+ columnSeperator + dcDate + columnSeperator
				+ courierId;
		System.out.println("this is controller" + outData);

		inData = scr.setJcStatus(outData);

		

	}
	
	//Update Element Status
	if (ajaxFlag.equals("update_element_status")) {

		outData = "";
		String elementId = request.getParameter("elementId");
		String elementStatusId = request.getParameter("elementStatusId");
		String elementStatusDate = request.getParameter("elementStatusDate");
		String elementStatusComments = request.getParameter("elementStatusComments");
		
        outData = elementId + columnSeperator + elementStatusId
				+ columnSeperator + elementStatusDate + columnSeperator
				+ elementStatusComments ;
		System.out.println("this is controller" + outData);

		inData = scr.setElementStatus(outData);

		

	}
%>




