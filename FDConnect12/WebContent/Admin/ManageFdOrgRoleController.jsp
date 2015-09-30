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

							outData = "";

						
                           

							inData = scr.getRole(outData);
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
					<table id="employeeType_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>Role</th>
						
						</tr>

						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

									    String roleId = columnData[0];
										String roleName = columnData[1];
										
										out.println("<tr><td><input name=\"chk\" value=\""
														+ roleId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ roleId + "','" + roleName + "'"
														+ ")\"></td><td>"
														+ roleName + "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_employee_type")) {

							
				                String roleName = request.getParameter("roleName");
							
								//System.out.println("it is set material sub group calling "+CountryName+CountryId);
								System.out.println("it is set material sub group calling " + roleName );

								outData =roleName;
									
								status = scr.setRole(outData);
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

							if (ajaxFlag.equals("delete_role")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteRole(outData);
								out.println(status);

								/*
								if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}*/

							}

							if (ajaxFlag.equals("update_role")) {
								System.out.println("it is calling in update_role");
								outData = "";

								String roleId = request.getParameter("roleId");
								String roleName = request.getParameter("roleName");
								
								System.out.println("This update country data is country id is "
										+ roleId + " countryId " + roleId + " countryId "
										+ roleId + " countryId " + roleId + " countryId " + roleId
										+ " countryId " + roleId + " countryName " + roleName);
								
								outData = roleId + rowSeperator + null + columnSeperator
										 + roleName ;
									

								status = scr.updateRole(outData);
								out.println(status);
								/*								
								if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println(status + "Failure");
									return;
								}*/

							}

						
											%>
										
