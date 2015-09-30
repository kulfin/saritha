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
	//System.out.println("This is ajax flag "+ajaxFlag);
	if (ajaxFlag.equals("get_country")) {
		String ajaxSubFlag=request.getParameter("subFlag");

		outData = "";
		inData = scr.getCountry(outData);
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
		
	if(ajaxSubFlag.equals("filterOperation")){	
	out.println("<select id=\"filter_country_select\" style=\"width:110px\" onchange=\"getRegion('filterOperation',1)\"><option value=\"select\">select</option>");	
    }
	
	else if(ajaxSubFlag.equals("updateOperation")){
	out.println("<select id=\"update_country_select\" style=\"width:120px\" onchange=\"getRegion('updateOperation',1)\"><option value=\"select\">select</option>");		
		
	}
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {
					String CountryId = columnData[0];
					String countryName = columnData[1];

					out.println("<option value=\"" + CountryId + "\">"
							+ countryName + "</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");
}
			

		if (ajaxFlag.equals("get_region")) {
			String ajaxSubFlag=request.getParameter("subFlag");
			outData = "";
			String countryId = request.getParameter("countryId");
			outData = countryId;
			
			inData = scr.getRegion(outData);
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
			if(ajaxSubFlag.equals("filterOperation")){	
				out.println("<select id=\"filter_region_select\" style=\"width:110px\" onchange=\"getState('filterOperation',1)\"><option value=\"select\">select</option>");	
			    }
				
			else if(ajaxSubFlag.equals("updateOperation")){
				out.println("<select id=\"update_region_select\" style=\"width:120px\" onchange=\"getState('updateOperation',1)\"><option value=\"select\">select</option>");		
					
				}
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 3) {
						String regionId = columnData[1];
						String regionName = columnData[2];

						out.println("<option value=\"" + regionId + "\">"
								+ regionName + "</option>");

					} else {

						out.println("Data is Unsufficient to display");
						return;
					}

				}
				out.println("</select>");
			}

			if (ajaxFlag.equals("get_state")) {
				
				String ajaxSubFlag=request.getParameter("subFlag");
				System.out.println("this is start of controller "+ajaxSubFlag);

				outData = "";
				String regionId = request.getParameter("regionId");
				System.out.println("this is region id "+regionId);
				outData = regionId;
				inData = scr.getState(outData);
				//System.out.println("this is service data "+inData);
				statusAndData = inData.split(statusSeperator);
				if (statusAndData.length == 2) {
					status = Integer.parseInt(statusAndData[0]);
					data = statusAndData[1];
				} else {
					out.println("Data is Insufficient to display");
					return;
				}
				if (status == 0) {

					System.out.println("this is viw indata"+inData+ajaxSubFlag);
					rowData = data.split(rowSeperator);

				} else {
					out.println(data);
					return;
				}
		if(ajaxSubFlag.equals("filterOperation")){	
				out.println("<select id=\"filter_state_select\" style=\"width:110px\" onchange=\"getCity('filterOperation',1)\"><option value=\"select\">select</option>");	
			    }
				
				else if(ajaxSubFlag.equals("updateOperation")){
				out.println("<select id=\"update_state_select\" style=\"width:120px\" onchange=\"getCity('updateOperation',1)\"><option value=\"select\">select</option>");		
					
				}
				for (int i = 0; i < rowData.length; i++) {
						columnData = rowData[i].split(columnSeperator);
						if (columnData.length == 4) {
							String stateId = columnData[2];
							String stateName = columnData[3];

							out.println("<option value=\"" + stateId + "\">"
									+ stateName + "</option>");

						} else {

							out.println("Data is Unsufficient to display");
							return;
						}

					}
					out.println("</select>");
				}

				if (ajaxFlag.equals("get_city")) {
					String ajaxSubFlag=request.getParameter("subFlag");
					outData = "";
					String stateId = request.getParameter("stateId");
					outData = stateId;
					inData = scr.getCity(outData);
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
					if(ajaxSubFlag.equals("filterOperation")){	
						out.println("<select id=\"filter_city_select\" style=\"width:110px\" ><option value=\"select\">select</option>");	
					    }
						
						else if(ajaxSubFlag.equals("updateOperation")){
						out.println("<select id=\"update_city_select\" style=\"width:120px\" ><option value=\"select\">select</option>");		
							
						}
					for (int i = 0; i < rowData.length; i++) {
							columnData = rowData[i].split(columnSeperator);
							if (columnData.length == 5) {
								String cityId = columnData[3];
								String cityName = columnData[4];

								out.println("<option value=\"" + cityId + "\">"
										+ cityName + "</option>");

							} else {

								out.println("Data is Unsufficient to display");
								return;
							}

						}
						out.println("</select>");
					}
			

						if (ajaxFlag.equals("get_town")) {

							outData = "";
							/*String cityId = request.getParameter("cityId");
							String stateId = request.getParameter("stateId");
							String regionId = request.getParameter("regionId");
							String countryId = request.getParameter("countryId");
                             System.out.println("controller city id is "+cityId);
							outData = cityId + columnSeperator + stateId + columnSeperator + regionId + columnSeperator + countryId;
							*/
							inData = scr.getCourier(outData);
							
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
					<table id="town_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>Courier Name</th>
							<th>Courier Address</th>
							<th>Pin Code</th>
							<th>Tel Number</th>
							<th>FAX Number</th>
							<th>TIN Number</th>
							<th>PAN Number</th>

						</tr>
						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 8) {

										String courierId = columnData[0];
										String courierName = columnData[1];
										String courierAddress = columnData[2];
										String pinCode = columnData[3];
										String telPhone = columnData[4];
										String faxNumber = columnData[5];
										String tinNumber = columnData[6];
										String panNumber = columnData[7];
	
										out	.println("<tr><td><input name=\"chk\" value=\""	+ courierId	+ " \" type=\"checkbox\"></td>"
												+"<td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"+ courierId + "','" + courierName + "','"+courierAddress + "','" + pinCode + "','" + telPhone + "','" + faxNumber
												+"','"+ tinNumber +"','"+ panNumber + "')\"></td>"
												+"<td>"+ courierName + "</td>"
												+"<td>"	+ courierAddress+ "</td>"
												+"<td>" + pinCode+ "</td>"
												+"<td>" + telPhone+ "</td>"
												+"<td>" + faxNumber + "</td>"
												+"<td>" + tinNumber + "</td>"
												+"<td>" + panNumber + "</td>"+"</tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_town")) {
							
								String countryId = request.getParameter("countryId");
								String regionId = request.getParameter("regionId");
								String stateId = request.getParameter("stateId");
								String cityId = request.getParameter("cityId");
							    String townName = request.getParameter("townName");
							    String courierAddress = request.getParameter("courierAddress");
							    String pinCode = request.getParameter("pinCode");
							    String telNumber = request.getParameter("telNumber");
							    String faxNumber = request.getParameter("faxNumber");
							    String tinNumber = request.getParameter("tinNumber");
							    String panNumber = request.getParameter("panNumber");
							
								//System.out.println("it is set material sub group calling "+StateName+CountryId);

								outData = countryId + columnSeperator + regionId + columnSeperator + stateId + columnSeperator + cityId + columnSeperator 
										+ townName + columnSeperator + courierAddress + columnSeperator + pinCode + columnSeperator + telNumber + columnSeperator
										+ faxNumber + columnSeperator + tinNumber + columnSeperator + panNumber;
									
								status = scr.setCourier(outData);
								out.println(status);
								/*
								if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}
								*/

							}

							if (ajaxFlag.equals("delete_town")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteCourier(outData);
								out.println(status);
								/*
								if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}
								*/
							}

							if (ajaxFlag.equals("update_town")) {
								System.out.println("it is calling");
								outData = "";
								String countryId = request.getParameter("countryId");
								String regionId = request.getParameter("regionId");
								String stateId = request.getParameter("stateId");
								String cityId = request.getParameter("cityId");
								String courierId = request.getParameter("courierId");
								String courierName = request.getParameter("courierName");
								String courierAddress = request.getParameter("courierAddress");
								String pinCode = request.getParameter("pinCode");
								String telNumber = request.getParameter("telNumber");
								String faxNumber = request.getParameter("faxNumber");
								String tinNumber = request.getParameter("tinNumber");
								String panNumber = request.getParameter("panNumber");
								
								System.out.println("This update town data is country id is "
										+ countryId + " stateId " + stateId + " regionId "
										+ regionId + " cityId " + cityId + " townId " + courierId
										+ " townId " + courierId + " townName " + courierName);
								
								outData = courierId + rowSeperator + null +columnSeperator + courierName + columnSeperator + courierAddress+ columnSeperator + pinCode + columnSeperator 
										 + telNumber+ columnSeperator + faxNumber + columnSeperator + tinNumber + columnSeperator + panNumber + columnSeperator 
										 + countryId + columnSeperator + regionId + columnSeperator + stateId + columnSeperator + cityId + columnSeperator;
								System.out.println("Input Data to updateCourier\t"+ outData);
								status = scr.updateCourier(outData);
								out.println(status);
								/*
								if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}
								*/

							}
							else if(ajaxFlag.equals("getAll")){
								
								outData = "";
						
								inData = scr.getCourier();
								
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
						<table id="town_detail_table" cellspacing="0">

							<tr>

								<th>Select</th>
								<th>Edit</th>
								<th>Courier Name</th>
								<th>Courier Address</th>
								<th>Pin Code</th>
								<th>Tel Number</th>
								<th>FAX Number</th>
								<th>TIN Number</th>
								<th>PAN Number</th>
								<th>City Name</th>
								<th>State Name</th>
								<th>Region Name</th>
								<th>Country Name</th>

							</tr>
							<%
								for (int i = 0; i < rowData.length; i++) {
										columnData = rowData[i].split(columnSeperator);
										if (columnData.length == 16) {

											String courierId = columnData[0];
											String courierName = columnData[1];
											String courierAddress = columnData[2];
											String pinCode = columnData[3];
											String telPhone = columnData[4];
											String faxNumber = columnData[5];
											String tinNumber = columnData[6];
											String panNumber = columnData[7];
											int cityId = Integer.parseInt(columnData[8]);
											int StateId = Integer.parseInt(columnData[9]);
											int RegionId = Integer.parseInt(columnData[10]);
											int CountryId = Integer.parseInt(columnData[11]);
											String cityName = columnData[12];
											String StateName = columnData[13];
											String RegionName = columnData[14];
											String CountryName = columnData[15];
											
											
											
		
											out	.println("<tr><td><input name=\"chk\" value=\""	+ courierId	+ " \" type=\"checkbox\"></td>"
													+"<td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"+ courierId + "','" + courierName + "','"+courierAddress + "','" + pinCode + "','" + telPhone + "','" + faxNumber
													+"','"+ tinNumber +"','"+ panNumber + "','"+ cityId + "','"+ StateId + "','"+ RegionId + "','"+ CountryId + "')\"></td>"
													+"<td>"+ courierName + "</td>"
													+"<td>"	+ courierAddress+ "</td>"
													+"<td>" + pinCode+ "</td>"
													+"<td>" + telPhone+ "</td>"
													+"<td>" + faxNumber + "</td>"
													+"<td>" + tinNumber + "</td>"
													+"<td>" + panNumber + "</td>"
													+"<td>" + cityName + "</td>"
													+"<td>" + StateName + "</td>"
													+"<td>" + RegionName + "</td>"
													+"<td>" + CountryName + "</td>"
													+"</tr>");

										} else {

											out.println("Data is Unsufficient to display");
											return;
										}

									}
									out.println("</table>");
								
								
								
							}

						
											%>
										
