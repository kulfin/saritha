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

							inData = scr.getUnit(outData);
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
					<table id="unit_detail_table" cellspacing="0">

						<tr>
							<th>Select</th>
							<th>Edit</th>
							<th>Unit</th>
						</tr>
						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {
									
									    String unitId = columnData[0];
										String unitName = columnData[1];

										out.println("<tr><td><input name=\"chk\" value=\""
														+ unitId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ unitId + "','" + unitName + "'"
														+ ")\"></td><td>"
														+ unitName+ "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_unit")) {

				                String unitName = request.getParameter("unitName");
							
								System.out.println("it is set material sub group calling "+ unitName);

								outData =unitName;
									
								status = scr.setUnit(outData);
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

							if (ajaxFlag.equals("delete_unit")) {
								outData = "";
								String selectedValues[] = request.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteUnit(outData);
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
									

								status = scr.updateUnit(outData);
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


