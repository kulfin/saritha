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
	System.out.println ("Entered ManageMaterialComponentController.jsp");
	
	if (ajaxFlag.equals("get_component")) {
		outData = "";
		
		inData = scr.getManageComponent();
		System.out.println("Received Data:" + inData);
		
		statusAndData = inData.split(statusSeperator);		
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		
		if (status == 0) {			
			rowData = data.split(rowSeperator);

		} else {
			out.println(data);
			return;
		}
%>
<table id="region_detail_table" cellspacing="0">
	<tr>
		<th>Select</th>
		<th>Edit</th>
		<th>Component</th>
	</tr>
<%
		for (int i = 0; i < rowData.length; i++) {
			columnData = rowData[i].split(columnSeperator);
			if (columnData.length == 2) {			
			    String regionId = columnData[0];
				String regionName = columnData[1];			
	
				out.println("<tr><td><input name=\"chk\" value=\""
								+ regionId
								+ " \" type=\"checkbox\"></td><td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"
								+ regionId + "','" + regionName + "'"
								+ ")\"></td><td>"
								+ regionName + "</td></tr>");
			} else {
				out.println("Data is Unsufficient to display");
				return;
			}
		}
		out.println("</table>");
	}

	if (ajaxFlag.equals("set_component")) {
		System.out.println("inside set_Component");		
        String componentName = request.getParameter("componentName");		
		System.out.println("it is set component calling  componentName"+componentName);
		
		outData = componentName + columnSeperator;
			
		status = scr.setManageComponent(outData);
		out.println(status);		
	}
	
	if (ajaxFlag.equals("delete_region")) {
		outData = "";
		String selectedValues[] = request.getParameter("selectedValues").split(",");

		for (int i = 0; i < selectedValues.length; i++) {
			outData = outData + selectedValues[i] + rowSeperator;				
		}

		status = scr.deleteManageComponent(outData);
		out.println(status);
	}

	if (ajaxFlag.equals("update_component")) {		
		outData = "";
		
		String componentId = request.getParameter("componentId");
		String componentName = request.getParameter("componentName");
		
		System.out.println("This update component data is:" +
				" componentId " + componentId + " componentName "
				+ componentName);
		
		outData = componentId + columnSeperator + componentName + rowSeperator;
		status = scr.updateManageComponent(outData);

		out.println(status);
	}
	System.out.println ("Exited ManageMaterialComponentController.jsp");						
%>
										
