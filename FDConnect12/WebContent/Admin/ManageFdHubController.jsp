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
		
	if(ajaxSubFlag.equals("addOperation")){	
	out.println("<select id=\"add_country_select\" style=\"width:120px\" onchange=\"getRegion('addOperation',1)\"><option value=\"select\">select</option>");	
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
			if(ajaxSubFlag.equals("addOperation")){	
				out.println(" <select id=\"add_region_select\" style=\"width:120px\" onchange=\"getState('addOperation',1)\"><option value=\"select\">select</option>");	
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
		if(ajaxSubFlag.equals("addOperation")){	
				out.println(" <select id=\"add_state_select\" style=\"width:120px\" onchange=\"getCity('addOperation',1)\"><option value=\"select\">select</option>");	
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
					if(ajaxSubFlag.equals("addOperation")){	
						out.println("<select id=\"add_city_select\" style=\"width:120px\" onchange=\"getTown('addOperation',1)\"><option value=\"select\">select</option>");	
					    }
						
						else if(ajaxSubFlag.equals("updateOperation")){
						out.println("<select id=\"update_city_select\" style=\"width:120px\" onchange=\"getTown('updateOperation',1)\"><option value=\"select\">select</option>");		
							
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
						String ajaxSubFlag=request.getParameter("subFlag");
						outData = "";
						String cityId = request.getParameter("cityId");
						outData = cityId;
						inData = scr.getTown(outData);
						statusAndData = inData.split(statusSeperator);
						if (statusAndData.length == 2) {
							status = Integer.parseInt(statusAndData[0]);
							data = statusAndData[1];
						} else {
							out.println("Data is Insufficient to display");
							return;
						}
						if (status == 0) {

							//System.out.println("this is town indata"+inData);
							rowData = data.split(rowSeperator);

						} else {
							out.println(data);
							return;
						}
						if(ajaxSubFlag.equals("addOperation")){	
							out.println("<select id=\"add_town_select\" style=\"width:120px\" onchange=\"getArea('addOperation')\"><option value=\"select\">select</option>");	
						    }
							
							else if(ajaxSubFlag.equals("updateOperation")){
							out.println("<select id=\"update_town_select\" style=\"width:120px\" onchange=\"getArea('updateOperation')\"><option value=\"select\">select</option>");		
								
							}
						for (int i = 0; i < rowData.length; i++) {
								columnData = rowData[i].split(columnSeperator);
								if (columnData.length == 6) {
									String townId = columnData[4];
									String townName = columnData[5];

									out.println("<option value=\"" + townId + "\">"
											+ townName + "</option>");

								} else {

									out.println("Data is Unsufficient to display");
									return;
								}

							}
							out.println("</select>");
						}
					
					
					

					if (ajaxFlag.equals("get_area")) {
						String ajaxSubFlag=request.getParameter("subFlag");
						outData = "";
						String townId = request.getParameter("townId");
						outData = townId;
						inData = scr.getArea(outData);
						statusAndData = inData.split(statusSeperator);
						if (statusAndData.length == 2) {
							status = Integer.parseInt(statusAndData[0]);
							data = statusAndData[1];
						} else {
							out.println("Data is Insufficient to display");
							return;
						}
						if (status == 0) {

							//System.out.println("this is area indata"+inData);
							rowData = data.split(rowSeperator);

						} else {
							out.println(data);
							return;
						}
						if(ajaxSubFlag.equals("addOperation")){	
							out.println("<select id=\"add_area_select\" style=\"width:120px\" onchange=\"getAreaId('addOperation')\"><option value=\"select\">select</option>");	
						    }
							
							else if(ajaxSubFlag.equals("updateOperation")){
							out.println("<select id=\"update_area_select\" style=\"width:120px\" onchange=\"getAreaId('updateOperation')\"><option value=\"select\">select</option>");		
								
							}
						for (int i = 0; i < rowData.length; i++) {
								columnData = rowData[i].split(columnSeperator);
								if (columnData.length == 8) {
									String areaId = columnData[5];
									String areaName = columnData[6];

									out.println("<option value=\"" + areaId + "\">"
											+ areaName + "</option>");

								} else {

									out.println("Data is Unsufficient to display");
									return;
								}

							}
							out.println("</select>");
						}
	//System.out.println("This is ajax flag "+ajaxFlag);
	if (ajaxFlag.equals("get_fd_org")) {
		//System.out.println("it is controller");
		String ajaxSubFlag=request.getParameter("subFlag");

		outData = "";
		inData = scr.getFdOrg(outData);
		System.out.println("it is controller"+inData);
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
		
	if(ajaxSubFlag.equals("addOperation")){	
	out.println("FD Org: <select id=\"add_fd_org_select\"  onchange=\"getFdHub('addOperation',1)\"><option value=\"select\">select</option>");	
    }
	
	else if(ajaxSubFlag.equals("updateOperation")){
	out.println("<select id=\"update_fd_org_select\" style=\"width:380px\" onchange=\"getFdHub('updateOperation',1)\"><option value=\"select\">select</option>");		
		
	}
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 3) {
					String FdOrgId = columnData[0];
					String fd_orgName = columnData[1];

					out.println("<option value=\"" + FdOrgId + "\">"
							+ fd_orgName + "</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");
}
			

		
			

						if (ajaxFlag.equals("get_fd_hub")) {

							outData = "";

							String fd_orgId = request.getParameter("fd_orgId");
                             //System.out.println("controller fd_hub id is "+fd_hubId);
							outData = fd_orgId;

							inData = scr.getFdHub(outData);
							statusAndData = inData.split(statusSeperator);
							if (statusAndData.length == 2) {
								status = Integer.parseInt(statusAndData[0]);
								data = statusAndData[1];
							} else {
								out.println("Data is Insufficient to display");
								return;
							}
							if (status == 0) {

								System.out.println("this is viw indata"+inData);
								rowData = data.split(rowSeperator);

							} else {
								out.println(data);
								return;
							}
					%>
					<table id="fd_hub_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>FD Hub</th>
							<th>Address</th>
							<th>Contact Name</th>
							<th>Phone</th>
							<th>Country</th>
							<th>Region</th>
							
						</tr>





						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 9) {

									String	fdHubId = columnData[0];
									String	hubName = columnData[1];
									String	address = columnData[2];
									String	contactName = columnData[3];
									String	contactPhone=columnData[4];
									String	countryId  = columnData[5];
									String	countryName = columnData[6];
									String	regionId  = columnData[7];
									String	regionName =columnData[8];
									/*String	stateId  =columnData[9];
									String	stateName = columnData[10];
									String  cityId  = columnData[11];
									String	cityName = columnData[12];
									String	townId  = columnData[13];
									String	townName = columnData[14];
									String	areaId  = columnData[9];
									String	areaName = columnData[10];*/
										
										/*out.println("<tr><td><input name=\"chk\" value=\""
														+ fdHubId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ fdHubId + "','" + hubName + "','"
														+ address + "','" + contactName + "','"
														+ contactPhone + "','" + countryId + "','"
														+ regionId + "','" + stateId + "','"
														+ cityId + "','" + townId + "','"
														+ areaId+"'" 
														+ ")\"></td><td>"
														+ hubName + "</td><td>"
														+ address + "</td><td>"+contactName+"</td><td>"+contactPhone+"</td>"
														+"<td>"+countryName+"</td><td>"+regionName+"</td>"
														+"<td>"+stateName+"</td><td>"+cityName+"</td>"
														+"<td>"+townName+"</td><td>"+areaName+"</td>"
														+"</tr>");*/
									out.println("<tr><td><input name=\"chk\" value=\""
											+ fdHubId
											+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
											+ fdHubId + "','" + hubName + "','"
											+ address + "','" + contactName + "','"
											+ contactPhone + "','" + countryId + "','"
											+ regionId + "'" 
											+ ")\"></td><td>"
											+ hubName + "</td><td>"
											+ address + "</td><td>"+contactName+"</td><td>"+contactPhone+"</td>"
											+"<td>"+countryName+"</td><td>"+regionName+"</td>"
											
											+"</tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_fd_hub")) {

								String fdOrgId = request.getParameter("fd_orgId");
				                String hubName = request.getParameter("hubName");
				                String address = request.getParameter("address");
				                String telNo = request.getParameter("telNo");
				                String contactPerson = request.getParameter("contactPerson");
				                String countryId = request.getParameter("countryId");
				                String regionId = request.getParameter("regionId");
				                /*String stateId = request.getParameter("stateId");
				                String cityId = request.getParameter("cityId");
				                String townId = request.getParameter("townId");
				                String areaId = request.getParameter("areaId");*/
				                
							
								//System.out.println("it is set material sub group calling "+FdHubName+FdOrgId);

								//outData = countryId + columnSeperator +  regionId + columnSeperator
								//+stateId + columnSeperator +  cityId + columnSeperator
								//+townId + columnSeperator +  areaId + columnSeperator
								//+fdOrgId + columnSeperator +  hubName + columnSeperator
								//+address + columnSeperator +  contactPerson + columnSeperator
								//+telNo;
								outData = countryId + columnSeperator +  regionId + columnSeperator
								+fdOrgId + columnSeperator +  hubName + columnSeperator
								+address + columnSeperator +  contactPerson + columnSeperator
								+telNo;
								System.out.println("OutData to setFdHub is " + outData);
								status = scr.setFdHub(outData);
								//System.out.println("Status from setFdHub us" + status);
								out.println(status);

							/*	if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}
							*/

							}

							if (ajaxFlag.equals("delete_fd_hub")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteFdHub(outData);
								out.println(status);

								/*if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}
								*/

							}

							if (ajaxFlag.equals("update_fd_hub")) {

								String fdOrgId = request.getParameter("fd_orgId");
								String hubId = request.getParameter("hubId");
				                String hubName = request.getParameter("hubName");
				                String address = request.getParameter("address");
				                String telNo = request.getParameter("telNo");
				                String contactPerson = request.getParameter("contactPerson");
				                String countryId = request.getParameter("countryId");
				                String regionId = request.getParameter("regionId");
				               /* String stateId = request.getParameter("stateId");
				                String cityId = request.getParameter("cityId");
				                String townId = request.getParameter("townId");
				                String areaId = request.getParameter("areaId");*/
				                
							
								//System.out.println("it is set material sub group calling "+FdHubName+FdOrgId);

								outData = hubId + rowSeperator + null + columnSeperator + hubName + columnSeperator
								+address + columnSeperator +  contactPerson + columnSeperator
								+telNo+ columnSeperator + regionId + columnSeperator
								+  countryId + columnSeperator	+fdOrgId ;
								
								status = scr.updateFdHub(outData);
								out.println(status);

								/*if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}
								*/

							}

						
											%>
