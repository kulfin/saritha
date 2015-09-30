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
	out.println("Country: <select id=\"filter_country_select\" style=\"width:110px\" onchange=\"getRegion('filterOperation',1)\"><option value=\"select\">select</option>");	
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
				out.println("Region: <select id=\"filter_region_select\" style=\"width:110px\" onchange=\"getState('filterOperation',1)\"><option value=\"select\">select</option>");	
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

							outData = "";

							String regionId = request.getParameter("regionId");
                             System.out.println("controller region id is "+regionId);
							outData = regionId;

							inData = scr.getState(outData);
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
					<table id="state_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>Country</th>
							<th>Region</th>
							<th>State</th>
						</tr>
						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 4) {

										String countryName = columnData[0];
										String regionName = columnData[1];
									    String stateId = columnData[2];
										String stateName = columnData[3];
										

										out
												.println("<tr><td><input name=\"chk\" value=\""
														+ stateId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ stateId + "','" + stateName + "'"
														+ ")\"></td><td>"
														+ countryName + "</td><td>"
														+ regionName + "</td><td>" + stateName
														+ "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_state")) {

								String countryId = request.getParameter("countryId");
								String regionId = request.getParameter("regionId");
						        String stateName = request.getParameter("stateName");
							
								//System.out.println("it is set material sub group calling "+StateName+CountryId);

								outData = countryId + columnSeperator + regionId + columnSeperator +  stateName;
									
								status = scr.setState(outData);
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

							if (ajaxFlag.equals("delete_state")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteState(outData);
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

							if (ajaxFlag.equals("update_state")) {
								System.out.println("it is calling");
								outData = "";
								String countryId = request.getParameter("countryId");
								String regionId = request.getParameter("regionId");
								
								
								

								String stateId = request.getParameter("stateId");
								String stateName = request.getParameter("stateName");
								
								System.out.println("This update state data is country id is "
										+ countryId + " stateId " + stateId + " regionId "
										+ regionId + " stateId " + stateId + " stateId " + stateId
										+ " stateId " + stateId + " stateName " + stateName);
								
								outData = stateId + rowSeperator + null + columnSeperator
										 + countryId + columnSeperator
										+ regionId + columnSeperator + stateName;

								status = scr.updateState(outData);
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
										
