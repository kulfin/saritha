<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fd.Admin.*"%>

<%
	String statusSeperator = "@&@";
	String rowSeperator = "!&!";
	String columnSeperator = "#&#";
	String fieldSeperator = "$&$";
	String statusAndData[];
	int status;
	String data;
	String rowData[];
	String columnData[];
	String inData;
	String outData;
	Service scr = new Service();

	String ajaxFlag = request.getParameter("flag");
	System.out.println("This is ajax flag "+ajaxFlag);
	
	if (ajaxFlag.equals("get_material_group")) {
		out.println("<option value=\"select\">select</option>");
		outData = "";
		inData = scr.getMaterialGroup(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];

			if (status == 0) {

				rowData = data.split(rowSeperator);
				for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 3) {
						String materialGroupId = columnData[0];
						String materialGroupName = columnData[1];

						out.println("<option value=\""
								+ materialGroupId + "\">"
								+ materialGroupName + "</option>");

					}

				}

			}

		}
	}
	
	if (ajaxFlag.equals("get_material_sub_group")) {
		String materialGroupId = request.getParameter("materialGroupId");
        out.println("<option value=\"select\">select</option>");
		outData = materialGroupId;
		inData = scr.getMaterialSubGroup(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];

			if (status == 0) {

				rowData = data.split(rowSeperator);
				for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 4) {
						String materialSubGroupId = columnData[1];
						String materialSubGroupName = columnData[2];

						out.println("<option value=\""
								+ materialSubGroupId + "\">"
								+ materialSubGroupName + "</option>");

					}

				}

			}

		}
	}

	if (ajaxFlag.equals("get_material_type")) {
	
        out.println("<option value=\"select\">select</option>");
		outData = "";
		inData = scr.getMaterialType(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];

			if (status == 0) {

				rowData = data.split(rowSeperator);
				for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 2) {
						String materialTypeId = columnData[0];
						String materialTypeName = columnData[1];

						out.println("<option value=\""
								+ materialTypeId + "\">"
								+ materialTypeName + "</option>");

					}

				}

			}

		}
	}
	
	if (ajaxFlag.equals("get_material_unit")) {
		
        out.println("<option value=\"select\">select</option>");
		outData = "";
		inData = scr.getMaterialUnit(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];

			if (status == 0) {

				rowData = data.split(rowSeperator);
				for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 2) {
						String materialUnitId = columnData[0];
						String materialUnitName = columnData[1];

						out.println("<option value=\""
								+ materialUnitId + "\">"
								+ materialUnitName + "</option>");

					}

				}

			}

		}
	}
	
if (ajaxFlag.equals("get_material_code")) {
		
        String materialSubGroupId=request.getParameter("materialSubGroupId");
		outData = materialSubGroupId;
		inData = scr.getMaterialCode(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];

			if (status == 0) {

				out.println(data);

			}

		}
	}

if (ajaxFlag.equals("validate_serial_number")) {
	
    String materialSerialNumber=request.getParameter("materialSerialNumber");
    String materialSubGroupId=request.getParameter("materialSubGroupId");
	outData = materialSubGroupId+columnSeperator+materialSerialNumber;
	System.out.println(outData);
	status = scr.validateMaterialSerialNumber(outData);
	System.out.println("this is controller validate serial number"+status);
	if (status == 0) {

			out.println("true");
			

		}

	}

	
	if (ajaxFlag.equals("get_material")) {

		outData = "";

		String materialSubGroupId = request
				.getParameter("materialSubGroupId");
		System.out.println("controller material_sub_group id is "
				+ materialSubGroupId);
		outData = materialSubGroupId;

		inData = scr.getMaterial(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			System.out.println("this is viw indata" + inData);
			rowData = data.split(rowSeperator);

		} else {
			out.println(data);
			return;
		}
%>
					<table id="material_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>Type</th>
							<th>S.No.</th>
							<th>Code</th>
							<th>Name</th>
							<th>Height</th>
							<th>Unit</th>
							<th>Width</th>
							<th>Unit</th>
							<th>Thickness</th>
							<th>Unit</th>
							<th>Capacity</th>
							<th>Unit</th>
							<th>Std Ordering Size</th>
							<th>Unit</th>
							<th>Conversion Size</th>
							<th>Unit</th>
							<th>Color</th>
							<th>Rate</th>
							<th>Storage Specs</th>
							<th>Expiry Specs</th>
														
						
							
							

						</tr>





						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 28) {
										String materialId = columnData[0];
										String serialNumber = columnData[1];
										String materialCode = columnData[2];
										String materialTypeId = columnData[3];
										String materialTypeName = columnData[4];
										String materialName = columnData[5];
										String height = columnData[6];
										String heightUnitId = columnData[7];
										String heightUnitName = columnData[8];
										String width = columnData[9];
										String widthUnitId = columnData[10];
										String widthUnitName = columnData[11];
										String thickness = columnData[12];
										String thicknessUnitId = columnData[13];
										String thicknessUnitName = columnData[14];
					                    String capacity = columnData[15];
										String capacityUnitId = columnData[16];
										String capacityUnitName = columnData[17];
										String stdOrderingSize = columnData[18];
										String stdOrderingSizeUnitId = columnData[19];
										String stdOrderingSizeUnitName = columnData[20];
							            String conversionSize = columnData[21];
										String conversionSizeUnitId = columnData[22];
										String conversionSizeUnitName = columnData[23];
									    String color = columnData[24];
										String storageSpecs = columnData[25];
										String expirySpecs = columnData[26];
										String materialRate = columnData[27];
										
										if(materialTypeName.equals("null"))
										materialTypeName="";
										
										if(heightUnitName.equals("null"))
											heightUnitName="";	
										
										if(widthUnitName.equals("null"))
											widthUnitName="";
										
										if(thicknessUnitName.equals("null"))
											thicknessUnitName="";
										
										if(capacityUnitName.equals("null"))
											capacityUnitName="";
										
										if(stdOrderingSizeUnitName.equals("null"))
											stdOrderingSizeUnitName="";
										
										if(conversionSizeUnitName.equals("null"))
											conversionSizeUnitName="";
										
										if(color.equals("null"))
											color="";
										
										if(storageSpecs.equals("null"))
											storageSpecs="";
										
										if(expirySpecs.equals("null"))
											expirySpecs="";
										
										out
												.println("<tr><td><input name=\"chk\" value=\""
														+ materialId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" "
														+ "onclick= \"showUpdateDiv('"
														+ materialId + "','" + materialTypeId
														+ "','"+serialNumber+"','"+materialCode+"','" + materialName + "','" + height
														+ "','" + heightUnitId + "','" + width
														+ "','" + widthUnitId + "','"
														+ thickness + "','" + thicknessUnitId
														+ "','" + capacity + "','"
														+ capacityUnitId + "','" + stdOrderingSize
														+ "','"+stdOrderingSizeUnitId+"','" + conversionSize + "','"
														+ conversionSizeUnitId + "','"
														+ color + "','" + materialRate
														+ "','"
														+ storageSpecs + "','" + expirySpecs
														
														+ "') \"></td>" 
														+"<td>"+materialTypeName+"</td><td>"+serialNumber+"</td>"
														+"<td>"+materialCode+"</td><td>"+materialName+"</td>"
														+"<td>"+height+"</td><td>"+heightUnitName+"</td>"
														+"<td>"+width+"</td><td>"+widthUnitName+"</td>"
														+"<td>"+thickness+"</td><td>"+thicknessUnitName+"</td>"
														+"<td>"+capacity+"</td><td>"+capacityUnitName+"</td>"
														+"<td>"+stdOrderingSize+"</td><td>"+stdOrderingSizeUnitName+"</td>"
														+"<td>"+conversionSize+"</td><td>"+conversionSizeUnitName+"</td>"
														+"<td>"+color+"</td><td>"+materialRate+"</td>"
														+"<td>"+storageSpecs+"</td><td>"+expirySpecs+"</td>"
														
												+"</tr>");
									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}
	
	
							String paramValue = "";
							if (ajaxFlag.equals("set_material")) {

							  outData = request.getParameter("subGroupId") + columnSeperator +
							  request.getParameter("serialNumber") + columnSeperator +
							  request.getParameter("code") + columnSeperator +
							  request.getParameter("thickness") + columnSeperator +
							  request.getParameter("thicknessUnitId") + columnSeperator +
							  request.getParameter("height") + columnSeperator +
							  request.getParameter("heightUnitId") + columnSeperator +
							  request.getParameter("width") + columnSeperator +
							  request.getParameter("widthUnitId") + columnSeperator +
							  request.getParameter("capacity") + columnSeperator +
							  request.getParameter("capacityUnitId") + columnSeperator +
							  request.getParameter("stdOrderingSize") + columnSeperator +
							  request.getParameter("stdOrderingSizeUnitId") + columnSeperator +
							  request.getParameter("conversion") + columnSeperator +
							  request.getParameter("conversionUnitId") + columnSeperator +
							  request.getParameter("color") + columnSeperator +
							  request.getParameter("rate") + columnSeperator +
							  request.getParameter("type") + columnSeperator +
							  request.getParameter("name") + columnSeperator +
							  request.getParameter("storageSpecs") + columnSeperator +
							  request.getParameter("expirySpecs");
							  //"null" + columnSeperator + "null";
							  //(paramValue = request.getParameter("storageSpecs")) == null? "null" : paramValue + columnSeperator +
							  //(paramValue = request.getParameter("expirySpecs")) == null? "null" : paramValue ;	
							  //request.getParameter("expirySpecs") ;
							
							  System.out.println("Parameters--- " + outData);

								status = scr.setMaterial(outData);

								if (status == 0) {

									System.out.println("Success" + status);
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}

							}

							if (ajaxFlag.equals("delete_material")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedMaterialId").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									
								}

								status = scr.deleteMaterial(outData);

								if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}

							}

							if (ajaxFlag.equals("update_material")) {

								  outData = request.getParameter("materialId") + columnSeperator +
								  request.getParameter("subGroupId") + columnSeperator +
								  request.getParameter("serialNumber") + columnSeperator +
								  request.getParameter("code") + columnSeperator +
								  request.getParameter("thickness") + columnSeperator +
								  request.getParameter("thicknessUnitId") + columnSeperator +
								  request.getParameter("height") + columnSeperator +
								  request.getParameter("heightUnitId") + columnSeperator +
								  request.getParameter("width") + columnSeperator +
								  request.getParameter("widthUnitId") + columnSeperator +
								  request.getParameter("capacity") + columnSeperator +
								  request.getParameter("capacityUnitId") + columnSeperator +
								  request.getParameter("stdOrderingSize") + columnSeperator +
								  request.getParameter("stdOrderingSizeUnitId") + columnSeperator +
								  request.getParameter("conversion") + columnSeperator +
								  request.getParameter("conversionUnitId") + columnSeperator +
								  request.getParameter("color") + columnSeperator +
								  request.getParameter("rate") + columnSeperator +
								  request.getParameter("type") + columnSeperator +
								  request.getParameter("name") + columnSeperator +
								  request.getParameter("storageSpecs") + columnSeperator +
								  request.getParameter("expirySpecs") ;
								
								  System.out.println(outData);

									status = scr.updateMaterial(outData);

									if (status == 0) {

										System.out.println("Success" + status);
										return;

									} else {

										System.out.println("Failure" + status);
										return;
									}

								}
				%>	

							