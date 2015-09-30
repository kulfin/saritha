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
		String ajaxSubFlag=request.getParameter("subFlag");

		outData = "";
		inData = scr.getDeptForProjectStatus(outData);
		System.out.println("OutData in view is\t" + inData);
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
	out.println("Department: <select id=\"filter_country_select\" style=\"width:110px\" onchange=\"getRegion('filterOperation',1)\"><option value=\"select\">select</option>");	
    }
	
	else if(ajaxSubFlag.equals("updateOperation")){
	out.println("<select id=\"update_country_select\" style=\"width:305px\" onchange=\"getRegion('updateOperation',1)\"><option value=\"select\">select</option>");		
		
	}
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {
					String CountryId = columnData[0];
					String countryName = columnData[1];

					out.println("<option value=\"" + CountryId + "\">"
							+ countryName + "</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");
}
			

		
			

						if (ajaxFlag.equals("get_region")) {

							outData = "";

							String countryId = request.getParameter("countryId");
                             //System.out.println("controller region id is "+regionId);
							outData = countryId;

							inData = scr.getProjectScope(outData);
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
					<table id="region_detail_table" cellspacing="0">

						<tr>
							<th>Select</th>
							<th>Edit</th>
							<!--<th>Country</th>-->
							<th>Scope</th>
						</tr>

						<%
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

										//String countryName = columnData[0];
									    String regionId = columnData[0];
										String regionName = columnData[1];
										

										out
												.println("<tr>"
														+"<td><input name=\"chk\" value=\""	+ regionId	+ " \" type=\"checkbox\"></td>"
														+"<td><img src=\"../images//edit.png \" onclick=\"showUpdateDiv('"+ regionId + "','" + regionName + "'"+ ")\"></td>"
														+"<td>"	+ regionName + "</td></tr>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</table>");

							}

							if (ajaxFlag.equals("set_scope")) {

								String countryId = request.getParameter("countryId");
				                String regionName = request.getParameter("scopeName");
				                //String departmentId = request.getParameter("departmentId");
							
								//System.out.println("it is set material sub group calling "+RegionName+CountryId);

								outData = countryId + columnSeperator +  regionName ;
									
								status = scr.setProjectScope(outData);
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

							if (ajaxFlag.equals("delete_region")) {
								outData = "";
								String selectedValues[] = request
										.getParameter("selectedValues").split(",");

								for (int i = 0; i < selectedValues.length; i++) {
									outData = outData + selectedValues[i] + rowSeperator;
									//System.out.println("it is calling and selected values are "+selectedValues[i]);	
								}

								status = scr.deleteProjectScope(outData);
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

							if (ajaxFlag.equals("update_region")) {
								System.out.println("it is calling");
								outData = "";
								String countryId = request.getParameter("countryId");

								String regionId = request.getParameter("regionId");
								String regionName = request.getParameter("regionName");
								
								System.out.println("This update region data is country id is "
										+ countryId + " regionId " + regionId + " regionId "
										+ regionId + " regionId " + regionId + " regionId " + regionId
										+ " regionId " + regionId + " regionName " + regionName);
								
								outData = regionId + rowSeperator + null + columnSeperator
										 + countryId + columnSeperator
										 +regionName;

								status = scr.updateProjectScope(outData);
								System.out.println("Welcome 111111" + status);
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
		