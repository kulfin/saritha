<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fd.Rfpr.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
	if (ajaxFlag.equals("get_client")) {
		String subFlag = request.getParameter("subFlag");

		outData = "";
		inData = scr.getClient(outData);
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
			
			if(subFlag.equals("filterOperation")){
				out.println("Client: <select id=\"filter_client_select\" style=\"width:250px\" onchange=\"getProjectForFilterOperation()\"><option value=\"select\">select</option></select>");	
			}
			
			else if(subFlag.equals("updateOperation")){
					System.out.println("update operation get client controller");
					out.println("Client: <select id=\"update_client_select\" style=\"width:250px;margin:0 0 0 36px;\" onchange=\"getProjectForUpdateOperation()\"><option value=\"select\">select</option></select>");	
			}
				
			return;
		}
		
	if(subFlag.equals("filterOperation")){
	out.println("Client: <select id=\"filter_client_select\" style=\"width:250px\" onchange=\"getProjectForFilterOperation()\"><option value=\"select\">select</option>");	
	}
	else if(subFlag.equals("updateOperation")){
		System.out.println("update operation get client controller");
		out.println("Client: <select id=\"update_client_select\" style=\"width:250px;margin:0 0 0 36px;\" onchange=\"getProjectForUpdateOperation()\"><option value=\"select\">select</option>");	
		}
	
	
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {
					String clientId = columnData[0];
					String clientName = columnData[1];
                    System.out.println("client id is "+clientId);
					out.println("<option value=\"" + clientId + "\">"
							+ clientName + "</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");
}
			

	if (ajaxFlag.equals("get_Project")) {
	
       String clientId=request.getParameter("clientId");
       String subFlag = request.getParameter("subFlag");
		outData = clientId;
		inData = scr.getProject(outData);
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
			if(subFlag.equals("filterOperation"))
			out.println("Project: <select id=\"filter_Project_select\" style=\"width:250px\" onchange=\"getElementForFilterOperation()\"><option value=\"select\">select</option></select>");	
			else if(subFlag.equals("updateOperation"))
			out.println("Project: <select id=\"update_Project_select\" style=\"width:250px;margin:0 0 0 33px;\" onchange=\"getElementForUpdateOperation()\"><option value=\"select\">select</option></select>");
			return;
		}
		
	if(subFlag.equals("filterOperation"))
	out.println("Project: <select id=\"filter_Project_select\" style=\"width:250px\" onchange=\"getElementForFilterOperation()\"><option value=\"select\">select</option>");	
	else if(subFlag.equals("updateOperation"))
	out.println("Project: <select id=\"update_Project_select\" style=\"width:250px;margin:0 0 0 33px;\" onchange=\"getElementForUpdateOperation()\"><option value=\"select\">select</option>");
	
	
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {
					String ProjectId = columnData[0];
					String ProjectName = columnData[1];
                    System.out.println("Project id is "+ProjectId);
					out.println("<option value=\"" + ProjectId + "\">"
							+ ProjectName + "</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");
}
	

	if (ajaxFlag.equals("get_element")) {
		
	       String ProjectId=request.getParameter("ProjectId");
	       String subFlag = request.getParameter("subFlag");
			outData = ProjectId;
			inData = scr.getElement(outData);
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
				if(subFlag.equals("filterOperation"))
				out.println("Element: <select id=\"filter_element_select\" style=\"width:250px\" onchange=\"getBomByFilterOperation()\"><option value=\"select\">select</option></select>");	
				else if(subFlag.equals("updateOperation"))
				out.println("Element: <select id=\"update_element_select\" style=\"width:250px\" onchange=\"getBomByUpdateOperation()\"><option value=\"select\">select</option></select>");	
					
				return;
			}
			
		if(subFlag.equals("filterOperation"))
		out.println("Element: <select id=\"filter_element_select\" style=\"width:250px\" onchange=\"getBomByFilterOperation()\"><option value=\"select\">select</option>");	
		else if(subFlag.equals("updateOperation"))
		out.println("Element: <select id=\"update_element_select\" style=\"width:250px\" onchange=\"getBomByUpdateOperation()\"><option value=\"select\">select</option>");	
		
		
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 2) {
						String elementId = columnData[0];
						String elementName = columnData[1];

						out.println("<option value=\"" + elementId + "\">"
								+ elementName + "</option>");

					} else {

						out.println("Data is Unsufficient to display");
						return;
					}

				}
				out.println("</select>");
	}	
	
	
	if (ajaxFlag.equals("get_brand")) {
		
	       //String ProjectId=request.getParameter("ProjectId");
	       String subFlag = request.getParameter("subFlag");
			outData = "";
			inData = scr.getBrand(outData);
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
				if(subFlag.equals("filterOperation"))
					out.println("Project: <select id=\"filter_brand_select\" style=\"width:150px\" onchange=\"getBomByFilterOperation()\"><option value=\"select\">select</option></select>");	
					else if(subFlag.equals("updateOperation"))
					out.println("Brand: <select id=\"update_brand_select\" style=\"width:150px\" onchange=\"getBomByUpdateOperation()\"><option value=\"select\">select</option></select>");	
				return;
			}
			
		if(subFlag.equals("filterOperation"))
		out.println("Project: <select id=\"filter_brand_select\" style=\"width:150px\" onchange=\"getBomByFilterOperation()\"><option value=\"select\">select</option>");	
		else if(subFlag.equals("updateOperation"))
		out.println("Brand: <select id=\"update_brand_select\" style=\"width:150px\" onchange=\"getBomByUpdateOperation()\"><option value=\"select\">select</option>");	
		
		
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 2) {
						String elementId = columnData[0];
						String elementName = columnData[1];

						out.println("<option value=\"" + elementId + "\">"
								+ elementName + "</option>");

					} else {

						out.println("Data is Unsufficient to display");
						return;
					}

				}
				out.println("</select>");
	}
	
	
	if (ajaxFlag.equals("get_material")) {

		outData = "";
       String rowCount= request.getParameter("rowCount");
	
       

		inData = scr.getMaterial(outData);
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
			  out.println("<select id=\"material_select"+rowCount+"\" name=\"costType\"  style=\"width: 250px;\" >"+
			   "<option value=\"select\">select</option></select>");
			return;
		}

   out.println("<select id=\"material_select"+rowCount+"\" name=\"costType\"  style=\"width: 250px;\" >"+
   "<option value=\"select\">select</option>");






		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {

				
				    String materialId = columnData[0];
					String materialName = columnData[1];
					

					out
							.println("<option value=\""+materialId+"\">"+materialName+"</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");

		}
	
	
	if (ajaxFlag.equals("get_unit")) {

		outData = "";
       String rowCount= request.getParameter("rowCount");
       String subFlag= request.getParameter("subFlag");
	
       

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
			 if(subFlag.equals("heightUnit"))
			out.println("<select id=\"height_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
			"<option value=\"select\">select</option></select>");
				   
		    if(subFlag.equals("widthUnit"))
			 out.println("<select id=\"width_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
			"<option value=\"select\">select</option></select>");
				   
			if(subFlag.equals("thicknessUnit"))
			 out.println("<select id=\"thickness_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
			 "<option value=\"select\">select</option></select>");
			
			if(subFlag.equals("orderQuantityUnit"))
				 out.println("<select id=\"order_quantity_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
				 "<option value=\"select\">select</option></select>");
				   
			if(subFlag.equals("capacityUnit"))
			 out.println("<select id=\"capacity_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
			 "<option value=\"select\">select</option></select>");
			return;
		}
   if(subFlag.equals("heightUnit"))
   out.println("<select id=\"height_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
   "<option value=\"select\">select</option>");
   
   if(subFlag.equals("widthUnit"))
	   out.println("<select id=\"width_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
	   "<option value=\"select\">select</option>");
   
   if(subFlag.equals("thicknessUnit"))
	   out.println("<select id=\"thickness_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
	   "<option value=\"select\">select</option>");
   
   if(subFlag.equals("orderQuantityUnit"))
	   out.println("<select id=\"order_quantity_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
	   "<option value=\"select\">select</option>");
   
   if(subFlag.equals("capacityUnit"))
	   out.println("<select id=\"capacity_unit_select"+rowCount+"\" name=\"costType\"  style=\"width: 50px;\" >"+
	   "<option value=\"select\">select</option>");






		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {

				
				    String materialId = columnData[0];
					String materialName = columnData[1];
					

					out
							.println("<option value=\""+materialId+"\">"+materialName+"</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");

		}
	
	
	if (ajaxFlag.equals("get_process")) {

		outData = "";
       String rowCount= request.getParameter("rowCount");
      // String subFlag= request.getParameter("subFlag");
	
       

		inData = scr.getProcess(outData);
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
			 out.println("<select id=\"process_select"+rowCount+"\" name=\"costType\"  style=\"width: 150px;\" >"+
			   "<option value=\"select\">select</option></select>");
			   
			return;
		}
  
   out.println("<select id=\"process_select"+rowCount+"\" name=\"costType\"  style=\"width: 150px;\" >"+
   "<option value=\"select\">select</option>");
   
  






		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 2) {

				
				    String processId = columnData[0];
					String processName = columnData[1];
					

					out
							.println("<option value=\""+processId+"\">"+processName+"</option>");

				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</select>");

		}
	
	

	if (ajaxFlag.equals("get_bom_by_filter")) {

		outData = "";

		String ProjectElementId = request.getParameter("ProjectElementId");
         //System.out.println("controller city id is "+cityId);
		outData = ProjectElementId;

		inData = scr.getBomByProjectElementId(outData);
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
%>
<table id="bom_detail_table" cellspacing="0">

	<tr>

		<th>Client</th>
		<th>Project</th>
		<th>Project</th>
		<th>Bom Code</th>
		<th>Bom Version Number</th>
		<th>Brand</th>
		<th>Element Type</th>
		<th>Bom Date</th>
		<th>Operations</th>
		
		
		

	</tr>





	<%
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 13) {

				String	bomId = columnData[0];
				String	bomCode = columnData[1];
				String	bomVersionNumber = columnData[2];
				String ProjectId = columnData[3];
				String ProjectName = columnData[4];
				String clientId = columnData[5];
				String clientName = columnData[6];
			    ProjectElementId = columnData[7];
				String elementCode = columnData[8];
				String elementName = columnData[9];
				String brandId = columnData[10];
				String brandName = columnData[11];
				String bomDate = columnData[12];
				
				String editImagepath="../images/edit.png";
				String deleteImagepath="../images/delete.png";
				String printImagepath="../images/printer.png";
				String consolidationImagepath="../images/consolidation.png";
					
				 out.println("<tr>");
					out.println("<td align=\"center\">"+clientName+"</td>");
					out.println("<td align=\"center\">"+ProjectName+"</td>");
					out.println("<td align=\"center\">"+elementCode+"</td>");
					out.println("<td align=\"center\">"+bomCode+"</td>");
					out.println("<td align=\"center\">"+bomVersionNumber+"</td>");
					out.println("<td align=\"center\">"+brandName+"</td>");
					out.println("<td align=\"center\">"+elementName+"</td>");
					out.println("<td align=\"center\">"+bomDate+"</td>");
					out.println("<td align=\"center\"><img src="+editImagepath+" title=\"Edit Bom\" onclick=\" editBom('"+bomId+"','"+bomCode+"','"+bomVersionNumber+"','"+ProjectId+"','"+clientId+"','"+elementCode+"','"+ProjectId+"','"+ProjectElementId+"','"+brandId+"','"+bomDate+"','"+elementName+"')\" />"+
					"<img style=\"margin:0 0 0 10px\" src="+deleteImagepath+" title=\"Delete Bom\"  onclick=\" deleteBom('"+bomId+"')\" /><a href=\"MaterialConsolidationAndCostingView.jsp?bomId="+bomId+"\"><img style=\"margin:0 0 0 10px\" title=\"Material Consolidation\"  src="+consolidationImagepath+" /></a><img style=\"margin:0 0 0 10px\" title=\"Print\"   src="+printImagepath+" /></td>");
					
					out.println("</tr>");
				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</table>");

		}
	
	if (ajaxFlag.equals("get_bom_by_search")) {

		outData = "";
		inData="";
        
		String subFlag =request.getParameter("subFlag");
		String searchText=request.getParameter("searchText");
        System.out.println("this is controller by search and  "+subFlag+searchText);
         
		outData = searchText;
		if(subFlag.equals("search_bom_by_bom_code")){
			inData = scr.getBomByBomCode(outData);
		}
		else if(subFlag.equals("search_bom_by_Project_name")){
			inData = scr.getBomByProjectName(outData);
		}

	
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
%>
<table id="bom_detail_table" cellspacing="0">

	<tr>

		<th>Client</th>
		<th>Project</th>
		<th>Project</th>
		<th>Bom Code</th>
		<th>Bom Version Number</th>
		<th>Brand</th>
		<th>Element Type</th>
		<th>Bom Date</th>
		<th>Operations</th>
		
		
		

	</tr>





	<%
		for (int i = 0; i < rowData.length; i++) {
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 13) {

				String	bomId = columnData[0];
				String	bomCode = columnData[1];
				String	bomVersionNumber = columnData[2];
				String ProjectId = columnData[3];
				String ProjectName = columnData[4];
				String clientId = columnData[5];
				String clientName = columnData[6];
			    String ProjectElementId = columnData[7];
				String elementCode = columnData[8];
				String elementName = columnData[9];
				String brandId = columnData[10];
				String brandName = columnData[11];
				String bomDate = columnData[12];
				
				String editImagepath="../images/edit.png";
				String deleteImagepath="../images/delete.png";
				String printImagepath="../images/printer.png";
				String consolidationImagepath="../images/consolidation.png";
					
				 out.println("<tr>");
					out.println("<td align=\"center\">"+clientName+"</td>");
					out.println("<td align=\"center\">"+ProjectName+"</td>");
					out.println("<td align=\"center\">"+elementCode+"</td>");
					out.println("<td align=\"center\">"+bomCode+"</td>");
					out.println("<td align=\"center\">"+bomVersionNumber+"</td>");
					out.println("<td align=\"center\">"+brandName+"</td>");
					out.println("<td align=\"center\">"+elementName+"</td>");
					out.println("<td align=\"center\">"+bomDate+"</td>");
					out.println("<td align=\"center\"><img src="+editImagepath+" title=\"Edit Bom\" onclick=\" editBom('"+bomId+"','"+bomCode+"','"+bomVersionNumber+"','"+ProjectId+"','"+clientId+"','"+elementCode+"','"+ProjectId+"','"+ProjectElementId+"','"+brandId+"','"+bomDate+"','"+elementName+"')\" />"+
					"<img style=\"margin:0 0 0 10px\" src="+deleteImagepath+" title=\"Delete Bom\"  onclick=\" deleteBom('"+bomId+"')\" /><a href=\"MaterialConsolidationAndCostingView.jsp?bomId="+bomId+"\"><img style=\"margin:0 0 0 10px\" title=\"Material Consolidation\"  src="+consolidationImagepath+" /></a><img style=\"margin:0 0 0 10px\" title=\"Print\"   src="+printImagepath+" /></td>");
					
					out.println("</tr>");
				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
			out.println("</table>");

		}

	
	if (ajaxFlag.equals("edit_bom")) {

		outData = "";
		inData="";
        
		
		String bomId=request.getParameter("bomId");
        System.out.println("this is controller edit bom and bomId is "+bomId);
         
		outData = bomId;
		
	    inData = scr.getBomElements(outData);
		
		int rowDataLength;

	
		statusAndData = inData.split(statusSeperator);
		if (statusAndData.length == 2) {
			status = Integer.parseInt(statusAndData[0]);
			data = statusAndData[1];
		} else {
			out.println("Data is Insufficient to display");
			return;
		}
		if (status == 0) {

			System.out.println("this is controller get elemens indata"+inData);
			rowData = data.split(rowSeperator);
			
		} else {
			
			rowData=null;
			//out.println(data);
			//return;
		}
%>
<div id="bom_detail_div">
<div style="position:relative; margin:5px 0 0 0;">
<span id="update_select_client" style="color: #000000;margin: 0 0 0 10px; font-size: 14px;font-weight:normal;">
Client: <select style="width: 151px;">
<option>select</option>
</select>
</span> 

<span id="update_select_Project" style="color: #000000; margin: 0 0 0 150px; font-size: 14px; font-weight:normal;">
Project: <select style="width: 151px;">
<option>select</option>
</select> 
</span> 

<span id="update_select_element" style="color: #000000;  margin: 0 0 0 150px; font-size: 14px;font-weight:normal;">
Project: <select style="width: 151px;">
<option>select</option>
</select>
</span> 

<!--  
<span id="update_select_brand" style="color: #000000;  margin: 0 0 0 150px; font-size: 14px;font-weight:normal;">
Brand: <select style="width: 151px;">
<option>select</option>
</select>
</span> 
-->
</div>
<div style="position:relative; margin:8px 0 0 0;">


<span id="update_select_brand" style="color: #000000;  margin: 0 0 0 10px; font-size: 14px;font-weight:normal;">
Bom Code: 
<input type="text" size="36" id="bom_code_input" />
</span> 

<span id="update_select_brand" style="color: #000000;  margin: 0 0 0 147px; font-size: 14px;font-weight:normal;">
Bom Version: 
<input type="text"  size="36" id="bom_version_input" />
</span> 

<span id="update_select_brand" style="color: #000000;  margin: 0 0 0 50px; font-size: 14px;font-weight:normal;">
Date: 
<!--  <input type="text" onclick="datePicker()" id="bom_date_input" /> -->
<span id="bom_date_input" style="color:#000000;" >

</span>
</span> 

<span id="update_select_element" style="color: #000000;  margin: 0 0 0 50px; font-size: 14px;font-weight:normal;">
Element Type Name: 
<span id="element_name_input" style="color:#000000;">

</span>
</span> 
</div>
</div>
<div id="bom_buttons_div">
<span><INPUT type="button" value="Add Row" onclick="addRow('bom_element_detail_table')" style="width:120px;margin:0 0 0 0;" /></span>
<span><INPUT type="button" value="Delete Row" onclick="deleteRow('bom_element_detail_table')" style="width:120px;margin:0 0 0 20px;" /></span>
<span><INPUT type="button" value="Update" onclick="return validate()" style="width:120px;margin:0 0 0 20px;" /></span>
</div>
<div id="bom_element_detail_div">
<table id="bom_element_detail_table" cellspacing="0">

	<tr>

		<th>Select</th>
		<th>Element Section</th>
		<th>Material</th>
		<th>Height</th>
		<th>Unit</th>
		<th>Width</th>
		<th>Unit</th>
		<th>Thickness</th>
		<th>Unit</th>
		<th>Capacity</th>
		<th>Unit</th>
		<th>Quantity</th>
		<th>Order Quantity</th>
		<th>Unit</th>
		<th>Process</th>
		</tr>
		</table>





	<%      if(rowData!=null){
	                 out.println("<input type=\"hidden\" id=\"number_of_rows\" value=\""+rowData.length+"\" >");
		for (int i = 0; i < rowData.length; i++) {
			
				columnData = rowData[i].split(columnSeperator);
				if (columnData.length == 22) {
                       int j=i+1;
					   bomId= columnData[0];
		        	   String elementSection=columnData[1];
		        	   String materialId= columnData[2];
		        	   String materialName=columnData[3];
		        	   String height= columnData[4];
		        	   String heightUnit= columnData[5];
		        	   String heightUnitName= columnData[6];
		        	   String width=columnData[7];
		        	   String widthUnit=  columnData[8];
		        	   String widthUnitName=  columnData[9];
		        	   String thickness =columnData[10];
		        	   String thicknessUnit= columnData[11];
		        	   String thicknessUnitName = columnData[12];
		        	   String capacity=columnData[13];
		        	   String capacityUnit= columnData[14];
		        	   String capacityUnitName= columnData[15];
		        	   String  bomQuantity=columnData[16];
		        	   String  orderQuantity= columnData[17];
		        	   String orderQuantityUnit= columnData[18];
		        	   String orderQuantityUnitName= columnData[19];
		        	   String  processId= columnData[20];
		        	   String processName= columnData[21];
		        	   
		        	   
		        	   out.println("<input type=\"hidden\" id=\"element_section"+j+"\" value=\""+elementSection+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"material_id"+j+"\" value=\""+materialId+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"height"+j+"\" value=\""+height+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"height_unit"+j+"\" value=\""+heightUnit+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"width"+j+"\" value=\""+width+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"width_unit"+j+"\" value=\""+widthUnit+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"thickness"+j+"\" value=\""+thickness+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"thickness_unit"+j+"\" value=\""+thicknessUnit+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"capacity"+j+"\" value=\""+capacity+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"capacity_unit"+j+"\" value=\""+capacityUnit+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"bom_quantity"+j+"\" value=\""+bomQuantity+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"order_quantity"+j+"\" value=\""+orderQuantity+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"order_quantity_unit"+j+"\" value=\""+orderQuantityUnit+"\" >");
		        	   out.println("<input type=\"hidden\" id=\"process_id"+j+"\" value=\""+processId+"\" >");
		        	  
		        	   
		        	   
			
					
					
					
				} else {

					out.println("Data is Unsufficient to display");
					return;
				}

			}
	}
			out.println("</div>");

		}	

	
	if (ajaxFlag.equals("update_bom")) {

		outData="";
      
		
		String bomId=request.getParameter("bomId");
		String clientId=request.getParameter("selectedClientId");
		String ProjectId=request.getParameter("selectedProjectId");
		String ProjectElementId=request.getParameter("selectedProjectElementId");
		String brandId=request.getParameter("selectedBrandId");
		
		String bomCode=request.getParameter("bomCode");
		String bomVersionNumber=request.getParameter("bomVersionNumber");
		String bomDate=request.getParameter("bomDate");
		
		String materialId[]=request.getParameter("materialId").split(",");
		String heightUnitId[]=request.getParameter("heightUnitId").split(",");
		String widthUnitId[]=request.getParameter("widthUnitId").split(",");
		String thicknessUnitId[]=request.getParameter("thicknessUnitId").split(",");
		String orderQuantityUnitId[]=request.getParameter("orderQuantityUnitId").split(",");
		String capacityUnitId[]=request.getParameter("capacityUnitId").split(",");
		String processId[]=request.getParameter("processId").split(",");
		String elementSection[]=request.getParameter("elementSection").split(",");
		String height[]=request.getParameter("height").split(",");
		String width[]=request.getParameter("width").split(",");
		String thickness[]=request.getParameter("thickness").split(",");
		String capacity[]=request.getParameter("capacity").split(",");
		String bomQuantity[]=request.getParameter("bomQuantity").split(",");
		String orderQuantity[]=request.getParameter("orderQuantity").split(",");
		
		
		
       
       
         
		String materialIdData="";
		String heightUnitIdData="";
		String widthUnitIdData="";
		String thicknessUnitIdData="";
		String orderQuantityUnitIdData="";
		String capacityUnitIdData="";
	    String processIdData="";
	    
		String elementSectionData="";
		String bomQuantityData="";
		String orderQuantityData="";
		
		String heightData="";
		String widthData="";
		String thicknessData="";
		String capacityData="";
		
        for(int i=0;i<materialId.length;i++){
        materialIdData=materialIdData+materialId[i]+columnSeperator;
        heightUnitIdData=heightUnitIdData+heightUnitId[i]+columnSeperator; 
        widthUnitIdData=widthUnitIdData+widthUnitId[i]+columnSeperator; 
        thicknessUnitIdData=thicknessUnitIdData+thicknessUnitId[i]+columnSeperator; 
        orderQuantityUnitIdData=orderQuantityUnitIdData+orderQuantityUnitId[i]+columnSeperator; 
        capacityUnitIdData=capacityUnitIdData+capacityUnitId[i]+columnSeperator; 
        processIdData=processIdData+processId[i]+columnSeperator; 
        
        elementSectionData=elementSectionData+elementSection[i]+columnSeperator; 
        bomQuantityData=bomQuantityData+bomQuantity[i]+columnSeperator; 
        orderQuantityData=orderQuantityData+orderQuantity[i]+columnSeperator; 
        
        heightData=heightData+height[i]+columnSeperator; 
        widthData=widthData+width[i]+columnSeperator; 
        thicknessData=thicknessData+thickness[i]+columnSeperator;
        capacityData=capacityData+capacity[i]+columnSeperator;
        
        
        }

        outData=bomId+rowSeperator+clientId+rowSeperator+
        ProjectId+rowSeperator+ProjectElementId+rowSeperator+
        brandId+rowSeperator+bomCode+rowSeperator+
        bomVersionNumber+rowSeperator+bomDate+rowSeperator+elementSectionData+rowSeperator+
        materialIdData+rowSeperator+heightData+rowSeperator+
        heightUnitIdData+rowSeperator+widthData+rowSeperator+
        widthUnitIdData+rowSeperator+thicknessData+rowSeperator+
        thicknessUnitIdData+rowSeperator+orderQuantityUnitIdData+rowSeperator+capacityData+rowSeperator+
        capacityUnitIdData+rowSeperator+bomQuantityData+rowSeperator+
        orderQuantityData+rowSeperator+processIdData;
       
       System.out.println(outData);
       

        status = scr.updateBom(outData);
		 
		
		if (status == 0) {

			System.out.println("update successfull");

		} else {
			System.out.println("update fail");
			return;
		}
  
  
   
  







		}
	
	if (ajaxFlag.equals("delete_bom")) {

		String bomId=request.getParameter("bomId");
		outData=bomId;
	    System.out.println(outData);
        status = scr.deleteBom(outData);
		 
		
		if (status == 0) {

			System.out.println("update successfull");

		} else {
			System.out.println("update fail");
			return;
		}
  
  
   
  







		}
			
											%>
										
</body>
</html>