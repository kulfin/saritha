<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fd.Rfpr.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.tools.DiagnosticCollector"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String statusSeperator = "@&@";
	String rowSeperator = "!&!";
	String columnSeperator = "#&#";

	String fieldSeperator = "!&#!";
	String statusAndData[];
	int status;
	String data;
	String rowData[];
	String columnData[];
	String inData;
	String outData;
	Service scr = new Service();

	String ajaxFlag = request.getParameter("flag");

	//System.out.println("This is ajax flag "+ajaxFlag);
	if (ajaxFlag.equals("get_client")) {
		String subFlag = request.getParameter("subFlag");

		outData = "";
		inData = scr.getClient(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			//System.out.println("this is viw indata"+inData);
			rowData = data.split(rowSeperator);

		} else {

			if (subFlag.equals("filterOperation")) {
				out
						.println("Client: <select id=\"filter_client_select\" style=\"width:250px\" onchange=\"getProjectForFilterOperation()\"><option value=\"select\">select</option></select>");
			}

			else if (subFlag.equals("updateOperation")) {
				System.out
						.println("update operation get client controller");
				out
						.println("Client: <select id=\"update_client_select\" style=\"width:250px;margin:0 0 0 36px;\" onchange=\"getProjectForUpdateOperation()\"><option value=\"select\">select</option></select>");
			}

			return;
		}

		if (subFlag.equals("filterOperation")) {
			out
					.println("Client: <select id=\"filter_client_select\" style=\"width:250px\" onchange=\"getProjectForFilterOperation()\"><option value=\"select\">select</option>");
		} else if (subFlag.equals("updateOperation")) {
			System.out
					.println("update operation get client controller");
			out
					.println("Client: <select id=\"update_client_select\" style=\"width:250px;margin:0 0 0 36px;\" onchange=\"getProjectForUpdateOperation()\"><option value=\"select\">select</option>");
		}

		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {
				String clientId = columnData[0];
				String clientName = columnData[1];
				System.out.println("client id is " + clientId);
				out.println("<option value=\"" + clientId + "\">"
						+ clientName + "</option>");

			} else {

				out.println("Data is Unsufficient to display");
				return;
			}

		}
		out.println("</select>");
	}

	if (ajaxFlag.equals("get_Project")) {

		String clientId = request.getParameter("clientId");
		String subFlag = request.getParameter("subFlag");
		outData = clientId;
		inData = scr.getProject(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			//System.out.println("this is viw indata"+inData);
			rowData = data.split(rowSeperator);

		} else {
			if (subFlag.equals("filterOperation"))
				out
						.println("Project: <select id=\"filter_Project_select\" style=\"width:250px\" onchange=\"getElementForFilterOperation()\"><option value=\"select\">select</option></select>");
			else if (subFlag.equals("updateOperation"))
				out
						.println("Project: <select id=\"update_Project_select\" style=\"width:250px;margin:0 0 0 33px;\" onchange=\"getElementForUpdateOperation()\"><option value=\"select\">select</option></select>");
			return;
		}

		if (subFlag.equals("filterOperation"))
			out
					.println("Project: <select id=\"filter_Project_select\" style=\"width:250px\" onchange=\"getElementForFilterOperation()\"><option value=\"select\">select</option>");
		else if (subFlag.equals("updateOperation"))
			out
					.println("Project: <select id=\"update_Project_select\" style=\"width:250px;margin:0 0 0 33px;\" onchange=\"getElementForUpdateOperation()\"><option value=\"select\">select</option>");

		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {
				String ProjectId = columnData[0];
				String ProjectName = columnData[1];
				System.out.println("Project id is " + ProjectId);
				out.println("<option value=\"" + ProjectId + "\">"
						+ ProjectName + "</option>");

			} else {

				out.println("Data is Unsufficient to display");
				return;
			}

		}
		out.println("</select>");
	}

	if (ajaxFlag.equals("get_element")) {

		String ProjectId = request.getParameter("ProjectId");
		String subFlag = request.getParameter("subFlag");
		outData = ProjectId;
		inData = scr.getElement(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			//System.out.println("this is viw indata"+inData);
			rowData = data.split(rowSeperator);

		} else {
			if (subFlag.equals("filterOperation"))
				out
						.println("Element: <select id=\"filter_element_select\" style=\"width:250px\" onchange=\"getBomByFilterOperation()\"><option value=\"select\">select</option></select>");
			else if (subFlag.equals("updateOperation"))
				out
						.println("Element: <select id=\"update_element_select\" style=\"width:250px\" onchange=\"getBomByUpdateOperation()\"><option value=\"select\">select</option></select>");

			return;
		}

		if (subFlag.equals("filterOperation"))
			out
					.println("Element: <select id=\"filter_element_select\" style=\"width:250px\" onchange=\"getBomByFilterOperation()\"><option value=\"select\">select</option>");
		else if (subFlag.equals("updateOperation"))
			out
					.println("Element: <select id=\"update_element_select\" style=\"width:250px\" onchange=\"getBomByUpdateOperation()\"><option value=\"select\">select</option>");

		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {
				String elementId = columnData[0];
				String elementName = columnData[1];

				out.println("<option value=\"" + elementId + "\">"
						+ elementName + "</option>");

			} else {

				out.println("Data is Unsufficient to display");
				return;
			}

		}
		out.println("</select>");
	}

	if (ajaxFlag.equals("get_bom_by_filter")) {

		outData = "";

		String ProjectElementId = request
				.getParameter("ProjectElementId");
		//System.out.println("controller city id is "+cityId);
		outData = ProjectElementId;

		inData = scr.getBomByProjectElementId(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			//System.out.println("this is viw indata"+inData);
			rowData = data.split(rowSeperator);

		} else {
			out.println(data);
			return;
		}
%>
<table id="bom_detail_table" cellspacing="0">

	<tr>

		<th>Client</th>
		<th>Project</th>
		<th>Project</th>
		<th>Bom Code</th>
		<th>Bom Version Number</th>
		<th>Brand</th>
		<th>Element Type</th>
		<th>Bom Date</th>
		<!-- <th>Cost Amount</th>  -->
		<th>Operations</th>
	</tr>

	<%
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 13) {

					String bomId = columnData[0];
					String bomCode = columnData[1];
					String bomVersionNumber = columnData[2];
					String ProjectId = columnData[3];
					String ProjectName = columnData[4];
					String clientId = columnData[5];
					String clientName = columnData[6];
					ProjectElementId = columnData[7];
					String elementCode = columnData[8];
					String elementName = columnData[9];
					String brandId = columnData[10];
					String brandName = columnData[11];
					String bomDate = columnData[12];
					/*String costAmount = columnData[13];*/

					String editImagepath = "../images/edit.png";
					String deleteImagepath = "../images/delete.png";
					String printImagepath = "../images/printer.png";
					String consolidationImagepath = "../images/consolidation.png";

					out.println("<tr>");
					out.println("<td align=\"center\">" + clientName
							+ "</td>");
					out.println("<td align=\"center\">" + ProjectName
							+ "</td>");
					out.println("<td align=\"center\">" + elementCode
							+ "</td>");
					out
							.println("<td align=\"center\">" + bomCode
									+ "</td>");
					out.println("<td align=\"center\">" + bomVersionNumber
							+ "</td>");
					out.println("<td align=\"center\">" + brandName
							+ "</td>");
					out.println("<td align=\"center\">" + elementName
							+ "</td>");
					out
							.println("<td align=\"center\">" + bomDate
									+ "</td>");
					/*out.println("<td align=\"center\">"+costAmount+"</td>");*/
					out
							.println("<td align=\"center\"><img src="
									+ editImagepath
									+ " title=\"View Bom Detail\" onclick=\" editBom('"
									+ bomId + "','" + bomCode + "','"
									+ bomVersionNumber + "','" + ProjectId
									+ "','" + clientId + "','"
									+ elementCode + "','" + ProjectId
									+ "','" + ProjectElementId + "','"
									+ brandId + "','" + bomDate + "','"
									+ elementName + "')\" />" +
									/*out.println("<td align=\"center\"><img src="+editImagepath+" title=\"View Bom Detail\" onclick=\" editBom('"+bomId+"','"+bomCode+"','"+bomVersionNumber+"','"+ProjectId+"','"+clientId+"','"+elementCode+"','"+ProjectId+"','"+ProjectElementId+"','"+brandId+"','"+bomDate+"','"+costAmount+"','"+elementName+"')\" />"+*/
									"</td>");
					out.println("</tr>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</table>");

		}

		if (ajaxFlag.equals("get_bom_by_view")) {

			outData = "";
			inData = "";

			String subFlag = request.getParameter("subFlag");
			String viewText = request.getParameter("viewText");
			System.out.println("this is controller by view and  " + subFlag
					+ viewText);

			outData = viewText;
			if (subFlag.equals("view_bom_by_bom_code")) {
				inData = scr.getBomByBomCode(outData);
			} else if (subFlag.equals("view_bom_by_Project_name")) {
				inData = scr.getBomByProjectName(outData);
			}

			statusAndData = inData.split(statusSeperator);
			if (statusAndData.length == 2) {
				status = Integer.parseInt(statusAndData[0]);
				data = statusAndData[1];
			} else {
				out.println("Data is Insufficient to display");
				return;
			}
			if (status == 0) {

				//System.out.println("this is viw indata"+inData);
				rowData = data.split(rowSeperator);

			} else {
				out.println(data);
				return;
			}
	%>
	<table id="bom_detail_table" cellspacing="0">

		<tr>

			<th>Client</th>
			<th>Project</th>
			<th>Project</th>
			<th>Bom Code</th>
			<th>Bom Version Number</th>
			<th>Brand</th>
			<th>Element Type</th>
			<th>Bom Date</th>
			<th>Operations</th>




		</tr>





		<%
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 13) {

						String bomId = columnData[0];
						String bomCode = columnData[1];
						String bomVersionNumber = columnData[2];
						String ProjectId = columnData[3];
						String ProjectName = columnData[4];
						String clientId = columnData[5];
						String clientName = columnData[6];
						String ProjectElementId = columnData[7];
						String elementCode = columnData[8];
						String elementName = columnData[9];
						String brandId = columnData[10];
						String brandName = columnData[11];
						String bomDate = columnData[12];

						String editImagepath = "../images/edit.png";
						String deleteImagepath = "../images/delete.png";
						String printImagepath = "../images/printer.png";
						String consolidationImagepath = "../images/consolidation.png";

						out.println("<tr>");
						out.println("<td align=\"center\">" + clientName
								+ "</td>");
						out.println("<td align=\"center\">" + ProjectName
								+ "</td>");
						out.println("<td align=\"center\">" + elementCode
								+ "</td>");
						out
								.println("<td align=\"center\">" + bomCode
										+ "</td>");
						out.println("<td align=\"center\">" + bomVersionNumber
								+ "</td>");
						out.println("<td align=\"center\">" + brandName
								+ "</td>");
						out.println("<td align=\"center\">" + elementName
								+ "</td>");
						out
								.println("<td align=\"center\">" + bomDate
										+ "</td>");
						out
								.println("<td align=\"center\"><img src="
										+ editImagepath
										+ " title=\"View Bom Detail\" onclick=\" editBom('"
										+ bomId + "','" + bomCode + "','"
										+ bomVersionNumber + "','" + ProjectId
										+ "','" + clientId + "','"
										+ elementCode + "','" + ProjectId
										+ "','" + ProjectElementId + "','"
										+ brandId + "','" + bomDate + "','"
										+ elementName + "')\" />" + "</td>");
						out.println("</tr>");
					} else {

						out.println("Data is Unsufficient to display");
						return;
					}

				}
				out.println("</table>");

			}

			if (ajaxFlag.equals("edit_bom")) {

				outData = "";
				inData = "";

				String bomId = request.getParameter("bomId");
				System.out.println("this is controller edit bom and bomId is "
						+ bomId);

				outData = bomId;

				inData = scr.getBomElements(outData);

				int rowDataLength;

				statusAndData = inData.split(statusSeperator);
				if (statusAndData.length == 2) {
					status = Integer.parseInt(statusAndData[0]);
					data = statusAndData[1];
				} else {
					out.println("Data is Insufficient to display");
					return;
				}
				if (status == 0) {

					System.out.println("this is controller get elemens indata---"
							+ inData);
					rowData = data.split(rowSeperator);

				} else {

					rowData = null;
					//out.println(data);
					//return;
				}
		%>

		<div id="bom_element_detail_div">
		<table id="bom_element_detail_table" cellspacing="0">

			<tr>


				<th>Element Section</th>
				<th>Material</th>
				<th>Height</th>
				<th>Unit</th>
				<th>Width</th>
				<th>Unit</th>
				<th>Thickness</th>
				<th>Unit</th>
				<th>Capacity</th>
				<th>Unit</th>
				<th>Quantity</th>
				<th>Order Quantity</th>
				<th>Process</th>
			</tr>






			<%
				if (rowData != null) {
						out.println("<input type=\"hidden\" id=\"number_of_rows\" value=\""+ rowData.length + "\" >");
						for (int i = 0; i < rowData.length; i++) {
							System.out.println("*******************************111111111111111111111111");
							columnData = rowData[i].split(columnSeperator);
						//	if (columnData.length == 20) {
								System.out.println("*******************************222222222222222");
								int j = i + 1;
								bomId = columnData[0];
								String elementSection = columnData[1];
								String materialId = columnData[2];
								String materialName = columnData[3];
								String height = columnData[4];
								String heightUnit = columnData[5];
								String heightUnitName = columnData[6];
								String width = columnData[7];
								String widthUnit = columnData[8];
								String widthUnitName = columnData[9];
								String thickness = columnData[10];
								String thicknessUnit = columnData[11];
								String thicknessUnitName = columnData[12];
								String capacity = columnData[13];
								String capacityUnit = columnData[14];
								String capacityUnitName = columnData[15];
								String bomQuantity = columnData[16];
								String orderQuantity = columnData[17];
								String processId = columnData[20];
								String processName = columnData[21];

								out.println("<tr>");
								out.println("<td align=\"center\">"
										+ elementSection + "</td>");
								out.println("<td align=\"center\">" + materialName
										+ "</td>");
								out
										.println("<td style=\"padding-right:3px;\" align=\"right\">"
												+ height + "</td>");
								out.println("<td align=\"center\">"
										+ heightUnitName + "</td>");
								out
										.println("<td style=\"padding-right:3px;\" align=\"right\">"
												+ width + "</td>");
								out.println("<td align=\"center\">" + widthUnitName
										+ "</td>");
								out
										.println("<td style=\"padding-right:3px;\" align=\"right\">"
												+ thickness + "</td>");
								out.println("<td align=\"center\">"
										+ thicknessUnitName + "</td>");
								out
										.println("<td style=\"padding-right:3px;\" align=\"right\">"
												+ capacity + "</td>");
								out.println("<td align=\"center\">"
										+ capacityUnitName + "</td>");
								out
										.println("<td style=\"padding-right:3px;\" align=\"right\">"
												+ bomQuantity + "</td>");
								out
										.println("<td style=\"padding-right:3px;\" align=\"right\">"
												+ orderQuantity + "</td>");
								out.println("<td align=\"center\">" + processName
										+ "</td>");
								out.println("</tr>");

						//	}
						}
					}
					out.println("</table></div>");

				}

				if (ajaxFlag.equals("get_bom_element_data")) {
					int rowDataForCostingLength;
					outData = "";
					String rowDataForConsolidation[];
					String rowDataForCosting[];
					String rowDataForDiscount[];

					String bomId = request.getParameter("bomId");
					outData = bomId;
					System.out.println("this is controller bomId is " + bomId);
					inData = scr.getBomElementDataForViewOperation(outData);
					statusAndData = inData.split(statusSeperator);
					if (statusAndData.length == 2) {
						status = Integer.parseInt(statusAndData[0]);
						data = statusAndData[1];
					} else {
						out.println("Data is Insufficient to after status display");
						return;
					}
					if (status == 0) {

						System.out.println("this is viw indata" + inData);
						rowData = data.split(fieldSeperator);
                         System.out.println("row data length"+rowData.length);
						if (rowData.length == 3) {

							rowDataForConsolidation = rowData[0]
									.split(rowSeperator);
							System.out.println("this is  check1 "+rowData[0]);
							if (!rowData[1].equals(" ")) {

								rowDataForCosting = rowData[1].split(rowSeperator);
								rowDataForCostingLength = rowDataForCosting.length;
							} else {
								rowDataForCosting = null;
								rowDataForCostingLength = 0;
								System.out.println("inside rowData[1] equals if ");
							}
							rowDataForDiscount = rowData[2].split(columnSeperator);
							
							System.out.println("this is  check2 ");
						} else {
							rowDataForConsolidation = null;
							rowDataForCosting = null;
							rowDataForDiscount = null;
							rowDataForCostingLength = 0;
						}

					} else {
						out.println(data);
						return;
					}
					System.out.println("this is  check3 ");
					String materialName[] = new String[rowDataForConsolidation.length];
					int orderQuantity[] = new int[rowDataForConsolidation.length];
					float rate[] = new float[rowDataForConsolidation.length];
					
					System.out.println("this is  check4 ");
					for (int k = 0; k < rowDataForConsolidation.length; k++) {
						columnData = rowDataForConsolidation[k]
								.split(columnSeperator);
						if (columnData.length == 3) {

							materialName[k] = columnData[0];
							rate[k] = Float.parseFloat(columnData[1]);
							orderQuantity[k] = Integer.parseInt(columnData[2]);
						} else {

							out.println("Data is Unsufficient 2 to display");
							return;
						}
					}
			
					List<String> consolidatedMaterialName = new ArrayList<String>();
					List<Integer> consolidatedOrderQuantity = new ArrayList<Integer>();
					List<Float> consolidatedMaterialRate = new ArrayList<Float>();

					boolean check;
					int consolidatedMaterialNameIndex = 0;
					int consolidatedOrderQuantityElement;
					int requiredMaterialQuantity[];
					int consolidatedOrderQuantityCal;
					String materialDimension[] = null;

					for (int i = 0; i < materialName.length; i++) {
						check = false;

						for (int j = 0; j < consolidatedMaterialName.size(); j++)//intial condition is false for Zero
						{
							if ((materialName[i]).equals(consolidatedMaterialName
									.get(j))) {
								check = true;
								consolidatedMaterialNameIndex = j;
								break;
							}
						}
						if (check) {
							consolidatedOrderQuantityElement = consolidatedOrderQuantity
									.get(consolidatedMaterialNameIndex);
							consolidatedOrderQuantityCal = consolidatedOrderQuantityElement
									+ orderQuantity[i];
							consolidatedOrderQuantity
									.remove(consolidatedMaterialNameIndex);
							consolidatedOrderQuantity.add(
									consolidatedMaterialNameIndex,
									consolidatedOrderQuantityCal);
						}

						else {
							consolidatedMaterialName.add((materialName[i]));
							consolidatedOrderQuantity.add(orderQuantity[i]);
							consolidatedMaterialRate.add(rate[i]);
						}
					}

					System.out.println("consolidated material Name= "
							+ consolidatedMaterialName);
					System.out.println("consolidated Order Quantity= "
							+ consolidatedOrderQuantity);
					System.out.println("consolidated Order Quantity= "
							+ consolidatedMaterialRate);
			%>
			<div id="material_consolidation_div" >
			<table cellspacing="0">
				<tr>
					<th>Material Name</th>
					<th style="width: 100px;">Order Quantity</th>
					<th style="width: 100px;">Rate</th>
					<th style="width: 100px;">Amount</th>
				</tr>

				<%
					double totalAmount = 0.0;
						for (int l = 0; l < consolidatedMaterialName.size(); l++) {

							double amount = ((consolidatedMaterialRate.get(l)) * (consolidatedOrderQuantity
									.get(l)));
							amount = ((int) Math.round(amount * 100)) / 100.0;

							totalAmount = totalAmount + amount;
							out.println("<tr><td>" + consolidatedMaterialName.get(l)
									+ "</td><td align=\"right\">"
									+ consolidatedOrderQuantity.get(l)
									+ "</td><td align=\"right\">"
									+ consolidatedMaterialRate.get(l)
									+ "</td><td align=\"right\">" + amount
									+ "</td></tr>");

						}

						totalAmount = ((int) Math.round(totalAmount * 100)) / 100.0;

						out.println("</table>");
						out
								.println("<div id=\"total_amount_display_div\"><span style=\"margin:0 0 0 15px\">Material Cost:</span><span  style=\"color: rgb(70, 66, 66); float:right; \">"
										+ totalAmount + "</span></div>");
						out.println("</div>");
				%>

			

				<div id="material_costing_div" >
				<table id="material_costing_table" cellspacing="0">
					<tr>
					
						<th>Cost Type</th>
						<th>Cost Item</th>
						<th style="width: 125px;">Percentage</th>
						<th style="width: 125px;">Amount</th>
					</tr>

					<tr>
				
						<td>Material Cost</td>
						<td>Material Cost</td>
						<td></td>
						<td align="right"><%=totalAmount%></td>
					</tr>
					<%
					Double grossAmount=totalAmount;
						if (rowDataForCosting != null) {
							
								for (int i = 0; i < rowDataForCosting.length; i++) {
									columnData = rowDataForCosting[i]
											.split(columnSeperator);
									if (columnData.length == 4) {
										String costTypeName = columnData[0].trim();
										String costItemName = columnData[1];
										Double amount = Double.parseDouble(columnData[2]);
										Double amountPercentage = Double.parseDouble(columnData[3]);
										if(amount==0.0){
											amount=(amountPercentage/100)*totalAmount;
											amount = ((int) Math.round(amount*100))/100.0;
											
										}
										else if(amountPercentage==0.0){
											amountPercentage=(amount/totalAmount)*100;
											amountPercentage = ((int) Math.round(amountPercentage*100))/100.0;
										}
										
										grossAmount+=amount;
										int j = i + 1;
										out.println("<tr><td>" + costTypeName
												+ "</td><td>" + costItemName + "</td><td align=\"right\">"
												+ amountPercentage + "</td><td align=\"right\">" + amount
												+ "</td></tr>");

									}
								}
							}
							out.println("</table>");
							
							double discountAmount = 0;
							double discountPercentage = 0;
							if ((rowDataForDiscount != null)
									&& (rowDataForDiscount.length == 2)) {
								discountAmount = Double.parseDouble(rowDataForDiscount[0]);
								discountPercentage = Double
										.parseDouble(rowDataForDiscount[1]);
								
								if(discountAmount==0.0){
									discountAmount=(discountPercentage/100)*grossAmount;
									discountAmount = ((int) Math.round(discountAmount*100))/100.0;
									
								}
								else if(discountPercentage==0.0){
									discountPercentage=(discountAmount/grossAmount)*100;
									discountPercentage = ((int) Math.round(discountPercentage*100))/100.0;
								}
								
						      
							}
							 double netAmount=grossAmount-discountAmount;
					%>

				
					
					<div id="gross_cost_display_div"><span style="margin:0 0 0 15px">Gross Amount:</span>
						<span  style="color: rgb(70, 66, 66); float:right; "><%=grossAmount%></span>
					</div>

					<div id="discount_entry_div">
						<span style="margin: 0 0 0 5px">Discount (Percentage):<span	style="color: rgb(70, 66, 66); margin: 0 0 0 0;"> 
							<input value="<%=discountPercentage %>" style="height: 12px; text-align: right;" type="text" size="12">
						</span></span> 
						<span style="margin: 0 0 0 40px"> Discount Amount:<span style="color: rgb(70, 66, 66); margin: 0 0 0 0;">
							 <input value="<%=discountAmount %>"  style="height: 12px; text-align: right;" type="text" size="12">
						</span></span>
					</div>




			
					
					<div id="net_cost_display_div">
								<span style="margin:0 0 0 15px">Net Amount:</span>
								<span  style="color: rgb(70, 66, 66); float:right; "><%=netAmount %></span>
					</div>

					</div>







					<%
						}
					%>
				
</body>
</html>