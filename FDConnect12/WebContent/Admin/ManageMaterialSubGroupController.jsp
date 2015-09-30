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
		String ajaxSubFlag=request.getParameter("subFlag");

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

			//System.out.println("this is viw indata"+inData);
			rowData = data.split(rowSeperator);

		} else {
			out.println(data);
			return;
		}
		
	if(ajaxSubFlag.equals("filterOperation")){	
	out.println("Material Group: <select id=\"filter_material_group_select\" style=\"width:110px\" onchange=\"getMaterialSubGroup('filterOperation',1)\"><option value=\"select\">select</option>");	
    }
	
	else if(ajaxSubFlag.equals("updateOperation")){
	out.println("<select id=\"update_material_group_select\" style=\"width:180px\" onchange=\"getMaterialSubGroup('updateOperation',1)\"><option value=\"select\">select</option>");		
		
	}
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 3) {
					String MaterialGroupId = columnData[0];
					String material_groupName = columnData[1];

					out.println("<option value=\"" + MaterialGroupId + "\">"
							+ material_groupName + "</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");
}
			

		
			

						if (ajaxFlag.equals("get_material_sub_group")) {

							outData = "";

							String material_groupId = request.getParameter("material_groupId");
                             //System.out.println("controller material_sub_group id is "+material_sub_groupId);
							outData = material_groupId;

							inData = scr.getMaterialSubGroup(outData);
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
					<table id="material_sub_group_detail_table" cellspacing="0">

						<tr>

							<th>Select</th>
							<th>Edit</th>
							<th>Material Group</th>
							<th>Material Sub Group</th>
							<th> Sub Group Code</th>
						
						
							
							

						</tr>





						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 4) {

										String material_groupName = columnData[0];
									    String material_sub_groupId = columnData[1];
										String material_sub_groupName = columnData[2];
										String material_sub_groupCode = columnData[3];
										

										out
												.println("<tr><td><input name=\"chk\" value=\""
														+ material_sub_groupId
														+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
														+ material_sub_groupId + "','" + material_sub_groupName + "','" + material_sub_groupCode + "'"
														+ ")\"></td><td>"
														+ material_groupName + "</td><td>"
														+ material_sub_groupName + "</td><td>"+material_sub_groupCode+"</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_material_sub_group")) {

								String material_groupId = request.getParameter("material_groupId");
				                String material_sub_groupName = request.getParameter("material_sub_groupName");
				                String material_sub_groupCode = request.getParameter("material_sub_groupCode");
							
								//System.out.println("it is set material sub group calling "+MaterialSubGroupName+MaterialGroupId);

								outData = material_groupId
										 + columnSeperator +  material_sub_groupName+columnSeperator+material_sub_groupCode;
									
								status = scr.setMaterialSubGroup(outData);
								out.println(status);

							/*	if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}
							*/

							}
							
                            if (ajaxFlag.equals("validate_material_sub_group_code")) {

								
				                
				                String materialSubGroupCode = request.getParameter("materialSubGroupCode");
				                String materialGroupId = request.getParameter("materialGroupId");
							
								//System.out.println("it is set material sub group calling "+MaterialGroupName+MaterialGroupId);

								outData =materialGroupId+columnSeperator+materialSubGroupCode;
									
								status = scr.validateMaterialSubGroupCode(outData);
								out.println(status);

								/*if (status == 0) {

									System.out.println("Success");
									return;

								} else {

									System.out.println("Failure" + status);
									return;
								}*/

							}

							if (ajaxFlag.equals("delete_material_sub_group")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteMaterialSubGroup(outData);
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

							if (ajaxFlag.equals("update_material_sub_group")) {
								System.out.println("it is calling update");
								outData = "";
								String material_groupId = request.getParameter("material_groupId");
								
								
								
								

								String material_sub_groupId = request.getParameter("material_sub_groupId");
								String material_sub_groupName = request.getParameter("material_sub_groupName");
								String material_sub_groupCode = request.getParameter("material_sub_groupCode");
								
								System.out.println("This update material_sub_group data is material_group id is "
										+ material_groupId + " material_sub_groupId " + material_sub_groupId +  " material_sub_groupName " + material_sub_groupName);
								
								outData = material_sub_groupId + rowSeperator + null + columnSeperator
										 + material_sub_groupName + columnSeperator
										 +material_groupId+columnSeperator+material_sub_groupCode;

								status = scr.updateMaterialSubGroup(outData);
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
