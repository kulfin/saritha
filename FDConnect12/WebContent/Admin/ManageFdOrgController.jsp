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

								System.out.println("this is viw indata"+inData);
								rowData = data.split(rowSeperator);

							} else {
								out.println(data);
								return;
							}
					%>
					<table id="fd_org_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>FD Organisation</th>
							<th>Detail</th>
						
						
						
							
							

						</tr>





						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length ==3) {

									
									    String fd_orgId = columnData[0];
										String fd_orgName = columnData[1];
										String fd_orgDetail = columnData[2];
										

										out
												.println("<tr><td><input name=\"chk\" value=\""
														+ fd_orgId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ fd_orgId + "','" + fd_orgName + "','" + fd_orgDetail + "' "
														+ ")\"></td><td>"
														+ fd_orgName + "</td><td>"+fd_orgDetail+"</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_fd_org")) {

							
				                String fd_orgName = request.getParameter("fd_orgName");
				                String fd_orgDetail = request.getParameter("fd_orgDetail");
							
								//System.out.println("it is set material sub group calling "+FdOrgName+FdOrgId);

								outData =fd_orgName+columnSeperator+fd_orgDetail;
									
								status = scr.setFdOrg(outData);
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

							if (ajaxFlag.equals("delete_fd_org")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteFdOrg(outData);
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

							if (ajaxFlag.equals("update_fd_org")) {
								System.out.println("it is calling");
								outData = "";
								
								
								
								
								

								String fd_orgId = request.getParameter("fd_orgId");
								String fd_orgName = request.getParameter("fd_orgName");
								String fd_orgDetail = request.getParameter("fd_orgDetail");
								
								System.out.println("This update fd_org data is fd_org id is "
										+ fd_orgId + " fd_orgId " + fd_orgId + " fd_orgId "
										+ fd_orgId + " fd_orgId " + fd_orgId + " fd_orgId " + fd_orgId
										+ " fd_orgId " + fd_orgId + " fd_orgName " + fd_orgName);
								
								outData = fd_orgId + rowSeperator + null + columnSeperator
										 + fd_orgName+ columnSeperator
										 + fd_orgDetail ;
									

								status = scr.updateFdOrg(outData);
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
										
