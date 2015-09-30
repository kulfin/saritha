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
	if (ajaxFlag.equals("get_fd_org")) {
		String ajaxSubFlag=request.getParameter("subFlag");

		outData = "";
		inData = scr.getFdOrg(outData);
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
	out.println("FD Org: <select id=\"filter_fd_org_select\"  onchange=\"getFdDivision('filterOperation',1)\"><option value=\"select\">select</option>");	
    }
	
	else if(ajaxSubFlag.equals("updateOperation")){
	out.println("<select id=\"update_fd_org_select\"  onchange=\"getFdDivision('updateOperation',1)\"><option value=\"select\">select</option>");		
		
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
			String ajaxSubFlag=request.getParameter("subFlag");
			outData = "";
			String fd_orgId = request.getParameter("fd_orgId");
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

				//System.out.println("this is viw indata"+inData);
				rowData = data.split(rowSeperator);

			} else {
				out.println(data);
				return;
			}
			if(ajaxSubFlag.equals("filterOperation")){	
				out.println("FD Hub: <select id=\"filter_fd_hub_select\" style=\"width:110px\" onchange=\"getFdDivision('filterOperation',1)\"><option value=\"select\">select</option>");	
			    }
				
				else if(ajaxSubFlag.equals("updateOperation")){
				out.println("<select id=\"update_fd_hub_select\" style=\"width:120px\" onchange=\"getFdDivision('updateOperation',1)\"><option value=\"select\">select</option>");		
					
				}
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 17) {
						String fd_hubId = columnData[0];
						String fd_hubName = columnData[1];

						out.println("<option value=\"" + fd_hubId + "\">"
								+ fd_hubName + "</option>");

					} else {

						out.println("Data is Unsufficient to display");
						return;
					}

				}
				out.println("</select>");
			}

		
			

			

						if (ajaxFlag.equals("get_fd_division")) {

							outData = "";

							String fd_orgId = request.getParameter("fd_orgId");
                             System.out.println("controller fd_hub id is "+fd_orgId);
							outData = fd_orgId;

							inData = scr.getFdDivision(outData);
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
					<table id="fd_division_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>FdOrg</th>
							<!-- <th>FdHub</th> -->
							<th>FdDivision</th>
						
							
							

						</tr>





						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 3) {

										String fd_orgName = columnData[0];
										//String fd_hubName = columnData[1];
									    String fd_divisionId = columnData[1];
										String fd_divisionName = columnData[2];
										

										/*out
												.println("<tr><td><input name=\"chk\" value=\""
														+ fd_divisionId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ fd_divisionId + "','" + fd_divisionName + "'"
														+ ")\"></td><td>"
														+ fd_orgName + "</td><td>"
														+ fd_hubName + "</td><td>" + fd_divisionName
														+ "</td></tr>");*/
										out.println("<tr><td><input name=\"chk\" value=\""
												+ fd_divisionId
												+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
												+ fd_divisionId + "','" + fd_divisionName + "'"
												+ ")\"></td><td>"
												+ fd_orgName + "</td><td>" + fd_divisionName
												+ "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_fd_division")) {

								String fd_orgId = request.getParameter("fd_orgId");
								//String fd_hubId = request.getParameter("fd_hubId");
						        String fd_divisionName = request.getParameter("fd_divisionName");
							
								//System.out.println("it is set material sub group calling "+FdDivisionName+FdOrgId);

								//outData = fd_orgId + columnSeperator + fd_hubId + columnSeperator +  fd_divisionName;
								outData = fd_orgId + columnSeperator +  fd_divisionName;
									
								status = scr.setFdDivision(outData);
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

							if (ajaxFlag.equals("delete_fd_division")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteFdDivision(outData);
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

							if (ajaxFlag.equals("update_fd_division")) {
								System.out.println("it is calling");
								outData = "";
								String fd_orgId = request.getParameter("fd_orgId");
								String fd_hubId = request.getParameter("fd_hubId");
								
								
								

								String fd_divisionId = request.getParameter("fd_divisionId");
								String fd_divisionName = request.getParameter("fd_divisionName");
								
								System.out.println("This update fd_division data is fd_org id is "
										+ fd_orgId + " fd_divisionId " + fd_divisionId + " fd_hubId "
										+ fd_hubId + " fd_divisionId " + fd_divisionId + " fd_divisionId " + fd_divisionId
										+ " fd_divisionId " + fd_divisionId + " fd_divisionName " + fd_divisionName);
								
								outData = fd_divisionId + rowSeperator + null + columnSeperator
										 + fd_divisionName + columnSeperator
										+ fd_orgId + columnSeperator + fd_hubId;

								status = scr.updateFdDivision(outData);
								out.println(status);

							/*	if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}
							*/

							}

						
											%>
										
