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

						
                           

							inData = scr.getEmployeeType(outData);
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
							<th>Employee Type</th>
						
						</tr>

						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

									    String employeeTypeId = columnData[0];
										String employeeTypeName = columnData[1];
										
										out.println("<tr><td><input name=\"chk\" value=\""
														+ employeeTypeId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ employeeTypeId + "','" + employeeTypeName + "'"
														+ ")\"></td><td>"
														+ employeeTypeName + "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_employee_type")) {

							
				                String employeeTypeName = request.getParameter("employeeTypeName");
							
								//System.out.println("it is set material sub group calling "+CountryName+CountryId);
								System.out.println("it is set material sub group calling " + employeeTypeName );

								outData =employeeTypeName;
									
								status = scr.setEmployeeType(outData);
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

							if (ajaxFlag.equals("delete_employee_type")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteEmployeeType(outData);
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

							if (ajaxFlag.equals("update_employee_type")) {
								System.out.println("it is calling in update_role");
								outData = "";

								String employeeTypeId = request.getParameter("employeeTypeId");
								String employeeTypeName = request.getParameter("employeeTypeName");
								
								System.out.println("This update country data is country id is "
										+ employeeTypeId + " employeeTypeId " + employeeTypeId + " employeeTypeId "
										+ employeeTypeId + " employeeTypeId " + employeeTypeId + " employeeTypeId " + employeeTypeId
										+ " employeeTypeId " + employeeTypeId + " employeeTypeName " + employeeTypeName);
								
								outData = employeeTypeId + rowSeperator + null + columnSeperator
										 + employeeTypeName ;
									

								status = scr.updateEmployeeType(outData);
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
										
