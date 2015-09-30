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
	String outData="";
	Service scr = new Service();

	String ajaxFlag = request.getParameter("flag");

	// Get Material Type
	if (ajaxFlag.equals("get_material_type")) {
		inData = scr.getMaterialType(outData);
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			System.out.println("this is viw indata" + inData);
			rowData = data.split(rowSeperator);

		} else {
			out.println(data);
			return;
		}
%>
<table id="material_type_detail_table" cellspacing="0">

	<tr>
		<th>Select</th>
		<th>Edit</th>
		<th>Material Type</th>
	</tr>
	<%
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {
					String materialTypeId = columnData[0];
					String materialTypeName = columnData[1];
					out
							.println("<tr><td><input name=\"chk\" value=\""
									+ materialTypeId
									+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
									+ materialTypeId + "','" + materialTypeName + "'"
									+ ")\"></td><td>" + materialTypeName
									+ "</td></tr>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</table>");

		}

	//Set Material Type
		if (ajaxFlag.equals("set_material_type")) {

			String materialTypeName = request.getParameter("materialTypeName");
            outData = materialTypeName;
			status = scr.setMaterialType(outData);
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
 //Delete Material Type
		if (ajaxFlag.equals("delete_material_type")) {
			outData = "";
			String selectedValues[] = request
					.getParameter("selectedValues").split(",");

			for (int i = 0; i < selectedValues.length; i++) {
				outData = outData + selectedValues[i] + rowSeperator;
				//System.out.println("it is calling and selected values are "+selectedValues[i]);	
			}

			status = scr.deleteMaterialType(outData);
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
//Update Material Type
		if (ajaxFlag.equals("update_material_type")) {
			System.out.println("it is calling");
			outData = "";

			String materialTypeId = request.getParameter("materialTypeId");
			String materialTypeName = request.getParameter("materialTypeName");


			outData = materialTypeId + rowSeperator + null + columnSeperator
					+ materialTypeName;

			status = scr.updateMaterialType(outData);
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

