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
	String organisationId = null;

	String ajaxFlag = request.getParameter("flag");
	//System.out.println("This is ajax flag "+ajaxFlag);
	if (ajaxFlag.equals("get_country")) {
		String ajaxSubFlag=request.getParameter("subFlag");

		outData = "";
		inData = scr.getOrganisation(outData);
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
	out.println("Organisation: <select id=\"filter_country_select\"  onchange=\"getRegion('filterOperation',1)\"><option value=\"select\">select</option>");	
    }
	
	else if(ajaxSubFlag.equals("updateOperation")){
	out.println("<select id=\"update_country_select\"  onchange=\"getRegion('updateOperation',1)\"><option value=\"select\">select</option>");		
		
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
			System.out.println("Inside get_region");
			String ajaxSubFlag=request.getParameter("subFlag");
			outData = "";
			String countryId = request.getParameter("countryId");
			outData = countryId;
			organisationId = countryId;
			
			inData = scr.getHub(outData);
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
				//out.println("Hub: <select id=\"filter_region_select\" style=\"width:110px\" onchange=\"getState('filterOperation',1)\"><option value=\"select\">select</option>");	
				out.println("Hub: <select id=\"filter_region_select\" style=\"width:110px\" onchange=\"getCity('filterOperation',1)\"><option value=\"select\">select</option>");
			    }
				
				else if(ajaxSubFlag.equals("updateOperation")){
				//out.println("<select id=\"update_region_select\" style=\"width:120px\" onchange=\"getState('updateOperation',1)\"><option value=\"select\">select</option>");
				out.println("<select id=\"update_region_select\" style=\"width:120px\" onchange=\"getCity('updateOperation',1)\"><option value=\"select\">select</option>");
					
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
				String orgId = request.getParameter("orgId");
				//String countryId = request.getParameter("countryId");
				System.out.println("this is region id "+regionId);
				System.out.println("this is organisation id "+orgId);
				outData = regionId+columnSeperator+orgId;
				
				inData = scr.getDivisoin(outData);
				
				System.out.println("this is service data "+inData);
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
				out.println("Division: <select id=\"filter_state_select\" style=\"width:110px\" onchange=\"getCity('filterOperation',1)\"><option value=\"select\">select</option>");	
			    }
				
				else if(ajaxSubFlag.equals("updateOperation")){
				out.println("<select id=\"update_state_select\" style=\"width:120px\" onchange=\"getCity('updateOperation',1)\"><option value=\"select\">select</option>");		
					
				}
				for (int i = 0; i < rowData.length; i++) {
					
						columnData = rowData[i].split(columnSeperator);
						System.out.println("ColumnData length is" +columnData.length);
						if (columnData.length == 4) {
							String stateId = columnData[2];
							String stateName = columnData[3];
							System.out.println("Division ID :" + stateId);
							System.out.println("Division Name :" + stateName);
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
					System.out.println("Inside get City");
					String ajaxSubFlag=request.getParameter("subFlag");
					outData = "";
					//String stateId = request.getParameter("stateId");
					String divisionId = request.getParameter("divisionId");
					String hubId = request.getParameter("hubId");
					outData = divisionId+columnSeperator+hubId;
					//department
					inData = scr.getDepartment(outData);
					
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
						out.println("Department: <select id=\"filter_city_select\"  style=\"width:150px;\" onchange=\"getTown('filterOperation',1)\"><option value=\"select\">select</option>");	
					    }
						
						else if(ajaxSubFlag.equals("updateOperation")){
						out.println("<select id=\"update_city_select\" style=\"width:150px; ><option value=\"select\">select</option>");		
							
						}
					for (int i = 0; i < rowData.length; i++) {
							columnData = rowData[i].split(columnSeperator);
							if (columnData.length == 4) {
								String cityId = columnData[2];
								String cityName = columnData[3];

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

							String deptId = request.getParameter("deptId");
							//String divisionId = request.getParameter("divisionId");
							String hubId = request.getParameter("hubId");
							String orgId = request.getParameter("orgId");
							//System.out.println("cityId:"+cityId+"divisionId:"+divisionId+"hubId:"+hubId+"orgId:"+orgId);
							System.out.println("cityId:"+deptId+"hubId:"+hubId+"orgId:"+orgId);
                            System.out.println("controller city id is "+deptId);
							//outData = cityId+columnSeperator+divisionId+columnSeperator+hubId+columnSeperator+orgId;
							outData = deptId+columnSeperator+hubId+columnSeperator+orgId;

							inData = scr.getManageEmployee(outData);
							
							statusAndData = inData.split(statusSeperator);
							if (statusAndData.length == 2) {
								status = Integer.parseInt(statusAndData[0]);
								data = statusAndData[1];
							} else {
								out.println("Insufficient Data to display");
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
							<th>Employee Name</th>
							<th>UserName</th>
							<th>Password</th>
							<th>Designation</th>
							<th>Contact Address</th>
							<th>Contact Phone</th>
							<th>Employee Type</th>
							<th>Role</th>
						</tr>

						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 9) {

										String countryName = columnData[0];
										String regionName = columnData[1];
										String stateName = columnData[2];
										String cityName = columnData[3];
										String townId = columnData[4];
										String townName = columnData[5];
										
										String employeeId = columnData[0];
										String employeeName = columnData[1];
										String userName = columnData[2];
										String password = columnData[3];
										String designation = columnData[4];
										String contactAddress = columnData[5];
										String contactPhone = columnData[6];
										String empTypeName = columnData[7];
										String roleName = columnData[8];
										

										out.println("<tr><td><input name=\"chk\" value=\""+ employeeId + " \" type=\"checkbox\"></td>"
												+ "<td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('" + employeeId + "','" + employeeName + "','"+userName + "','"+password + "','"+designation+"','" + contactAddress +"','"+contactPhone +"','" + empTypeName + "','" + roleName +"')\"></td>"
														+"<td>"	+ employeeName + "</td> <td>"+ userName + "</td>"
														+"<td>" + password	+ "</td>" + "<td>" + designation + "</td>"
														+"<td>"	+ contactAddress + "</td>"+ "<td>" + contactPhone + "</td>"
														+"<td>" + empTypeName + "</td>"+"<td>"	+ roleName + "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_employee")) {

								String countryId = request.getParameter("countryId");
								String regionId = request.getParameter("regionId");
								//String stateId = request.getParameter("stateId");
								String cityId = request.getParameter("cityId");
							    String townName = request.getParameter("townName");
							    String userName = request.getParameter("userName");
							    String password = request.getParameter("password");
							    String designation = request.getParameter("designation");
							    String contactAddress = request.getParameter("contactAddress");
							    String contactPhone = request.getParameter("contactPhone");
							    String empType = request.getParameter("empType");
							    String role = request.getParameter("role");
							
								//System.out.println("it is set material sub group calling "+StateName+CountryId);

								/*outData = countryId + columnSeperator + regionId + columnSeperator + stateId + columnSeperator + cityId
										+ columnSeperator + townName + columnSeperator + userName + columnSeperator + password + columnSeperator 
										+ designation + columnSeperator + contactAddress + columnSeperator + contactPhone + columnSeperator 
										+ empType + columnSeperator + role;*/
								outData = countryId + columnSeperator + regionId + columnSeperator + cityId
								+ columnSeperator + townName + columnSeperator + userName + columnSeperator + password + columnSeperator 
								+ designation + columnSeperator + contactAddress + columnSeperator + contactPhone + columnSeperator 
								+ empType + columnSeperator + role;
									
								status = scr.setManageEmployee(outData);
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
								String selectedValues[] = request.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteManageEmployee(outData);
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
								
								String townId = request.getParameter("townId");
								String townName = request.getParameter("townName");
								String userName = request.getParameter("userName");
								String password = request.getParameter("password");
								String designation = request.getParameter("designation");
								String contactAddress = request.getParameter("contactAddress");
								String contactPhone = request.getParameter("contactPhone");
								String empType = request.getParameter("empType");
								String role = request.getParameter("role");
								
								String cityId = request.getParameter("cityId");
								//String stateId = request.getParameter("stateId");
								String regionId = request.getParameter("regionId");
								//String countryId = request.getParameter("countryId");

								
								System.out.println("This update town data is country id is "
										+ "" +  " regionId "
										+ regionId + " cityId " + cityId + " townId " + townId
										+ " townId " + townId + " townName " + townName);
								
								outData = townId + rowSeperator + null + columnSeperator + townName + columnSeperator + userName + columnSeperator 
										+ password  + columnSeperator + designation + columnSeperator
										+ contactAddress + columnSeperator + contactPhone + columnSeperator + empType + columnSeperator + role + columnSeperator
										+ regionId + columnSeperator + cityId;
								System.out.println("Parameters before passing" + outData);
								status = scr.updateManageEmployee(outData);
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

						
											%>
										
