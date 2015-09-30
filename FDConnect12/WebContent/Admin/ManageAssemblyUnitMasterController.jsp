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
							inData = scr.getAssemblyUnit(outData);
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
					<table id="assembly_detail_table" cellspacing="0">

						<tr>
							<th>Select</th>
							<th>Edit</th>
							<th>Outsource Factory</th>
						</tr>

						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

									    String countryId = columnData[0];
										String countryName = columnData[1];
										out.println("<tr><td><input name=\"chk\" value=\"" + countryId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ countryId + "','" + countryName + "'" + ")\"></td><td>"
														+ countryName + "</td></tr>");
									} else {

										out.println("Data is Unsufficient to display");
										return;
									}
								}
								out.println("</table>");

							}else
							if (ajaxFlag.equals("set_country")) {

							
				                String countryName = request.getParameter("countryName");
							
								//System.out.println("it is set material sub group calling "+CountryName+CountryId);

								outData =countryName;
									
								status = scr.setAssemblyUnit(outData);
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

							if (ajaxFlag.equals("delete_country")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteAssemblyUnit(outData);
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

							}else

							if (ajaxFlag.equals("update_country")) {
								System.out.println("it is calling");
								outData = "";
								
								String countryId = request.getParameter("countryId");
								String countryName = request.getParameter("countryName");
								
								System.out.println("This update country data is country id is "
										+ countryId + " countryId " + countryId + " countryId "
										+ countryId + " countryId " + countryId + " countryId " + countryId
										+ " countryId " + countryId + " countryName " + countryName);
								
								outData = countryId + rowSeperator + null + columnSeperator
										 + countryName ;
									

								status = scr.updateAssemblyUnit(outData);
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

							}else
								if (ajaxFlag.equals("get_client")) {

									outData = "";
									inData = scr.getClientData();
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
							<table id="assembly_detail_table" cellspacing="0">

								<tr>
									<th>Select</th>
									<th>Edit</th>
									<th>Outsource Factory</th>
								</tr>

								<%
									for (int i = 0; i < rowData.length; i++) {
											columnData = rowData[i].split(columnSeperator);
											if (columnData.length == 2) {

											    String countryId = columnData[0];
												String countryName = columnData[1];
												out.println("<tr><td><input name=\"chk\" value=\"" + countryId
																+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
																+ countryId + "','" + countryName + "'" + ")\"></td><td>"
																+ countryName + "</td></tr>");
											} else {

												out.println("Data is Unsufficient to display");
												return;
											}
										}
										out.println("</table>");

									}

						
											%>
										
