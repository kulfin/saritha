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

							inData = scr.getManageElementStatus(outData);
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
					<table id="element_detail_table" cellspacing="0">

						<tr>
							<th>Select</th>
							<th>Edit</th>
							<th>Element Type Status</th>
						
						</tr>

						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {
									
									    String elementId = columnData[0];
										String elementName = columnData[1];

										out.println("<tr><td><input name=\"chk\" value=\""
														+ elementId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ elementId + "','" + elementName + "'"
														+ ")\"></td><td>"
														+ elementName + "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_element")) {

							
				                String elementName = request.getParameter("elementName");
							
								System.out.println("it is set Element calling "+elementName);

								outData =elementName;
									
								status = scr.setManageElementStatus(outData);

								out.println(status);
								/*if (status == 0) {

									System.out.println("Success");
									return;
								} else {
									System.out.println(status + "Failure");
									return;
								}*/

							}

							if (ajaxFlag.equals("delete_element")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteManageElementStatus(outData);

								out.println(status);
								/*if (status == 0) {

									System.out.println("Success");
									return;
								} else {
									System.out.println(status + "Failure");
									return;
								}*/
							}

							if (ajaxFlag.equals("update_element")) {
								System.out.println("it is calling");
								outData = "";
								String elementId = request.getParameter("elementId");
								String elementName = request.getParameter("elementName");
								
								System.out.println("This update country data is country id is "
										+ elementId + " countryId " + elementId + " countryId "
										+ elementId + " countryId " + elementId + " countryId " + elementId
										+ " countryId " + elementId + " countryName " + elementName);
								
								outData = elementId + rowSeperator + null + columnSeperator
										 + elementName ;
									

								status = scr.updateManageElementStatus(outData);
								out.println(status);
								/*if (status == 0) {

									System.out.println("Success");
									return;
								} else {
									System.out.println(status + "Failure");
									return;
								}*/

							}

						
											%>
										
