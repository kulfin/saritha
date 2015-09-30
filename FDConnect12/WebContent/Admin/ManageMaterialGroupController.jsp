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
	

		
			

						if (ajaxFlag.equals("get_material_group")) {

							outData = "";

						
                           

							inData = scr.getMaterialGroup(outData);
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
					<table id="material_group_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>Material Group</th>
							<th>Group Code</th>
						
						
						
							
							

						</tr>





						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 3) {

									
									    String material_groupId = columnData[0];
										String material_groupName = columnData[1];
										String material_groupCode = columnData[2];
										

										out
												.println("<tr><td><input name=\"chk\" value=\""
														+ material_groupId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ material_groupId + "','"+material_groupName+"','" + material_groupCode + "'"
														+ ")\"></td><td>"
														+ material_groupName + "</td><td>"+material_groupCode+"</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_material_group")) {

							
				                String material_groupName = request.getParameter("material_groupName");
				                String material_groupCode = request.getParameter("material_groupCode");
							
								//System.out.println("it is set material sub group calling "+MaterialGroupName+MaterialGroupId);

								outData =material_groupName+columnSeperator+material_groupCode;
									
								status = scr.setMaterialGroup(outData);
								out.println(status);

								/*if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}*/

							}
							
							if (ajaxFlag.equals("validate_material_group_code")) {

								
				                
				                String materialGroupCode = request.getParameter("materialGroupCode");
							
								//System.out.println("it is set material sub group calling "+MaterialGroupName+MaterialGroupId);

								outData =materialGroupCode;
									
								status = scr.validateMaterialGroupCode(outData);
								out.println(status);

								/*if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}*/

							}

							if (ajaxFlag.equals("delete_material_group")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteMaterialGroup(outData);
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

							if (ajaxFlag.equals("update_material_group")) {
								System.out.println("it is calling");
								outData = "";
								
								
								
								
								

								String material_groupId = request.getParameter("material_groupId");
								String material_groupName = request.getParameter("material_groupName");
								String material_groupCode = request.getParameter("material_groupCode");
								
								System.out.println("This update material_group data is material_group id is "
										+ material_groupId + " material_groupId " + material_groupId + " material_groupId "
										+ material_groupId + " material_groupId " + material_groupId + " material_groupId " + material_groupId
										+ " material_groupId " + material_groupId + " material_groupName " + material_groupName);
								
								outData = material_groupId + rowSeperator + null + columnSeperator
										 + material_groupName + columnSeperator + material_groupCode;
									

								status = scr.updateMaterialGroup(outData);
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

						
											%>
