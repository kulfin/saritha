<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fd.Rfpr.*"%>

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

	if (ajaxFlag.equals("get_client")) {
		

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
			out.println("Client: <select id=\"client_select\" style=\"width:250px;margin:0 0 0 36px;\" onchange=\"getProject()\"><option value=\"select\">select</option></select>");
			return;
		}
		
	
	out.println("Client: <select id=\"client_select\" style=\"width:250px;margin:0 0 0 36px;\" onchange=\"getProject()\"><option value=\"select\">select</option>");	

	
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
			out.println("Project: <select id=\"Project_select\" style=\"width:250px;margin:0 0 0 33px;\" onchange=\"getElement()\"><option value=\"select\">select</option></select>");	
			return;
		}
		
	
	out.println("Project: <select id=\"Project_select\" style=\"width:250px;margin:0 0 0 33px;\" onchange=\"getElement()\"><option value=\"select\">select</option>");	

	
	
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
				out.println("Element: <select id=\"element_select\" style=\"width:250px;margin:0 0 0 13px;\" ><option value=\"select\">select</option></select>");	
				return;
			}
			
		
		out.println("Element: <select id=\"element_select\" onchange=\"getElementNameAndQuantity()\" style=\"width:250px;margin:0 0 0 13px;\" ><option value=\"select\">select</option>");	
		
		
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 2) {
						String projectId = columnData[0];
						String projectName = columnData[1];

						out.println("<option value=\"" + projectId + "\">"
								+ projectName + "</option>");

					} else {

						out.println("Data is Unsufficient to display");
						return;
					}

				}
				out.println("</select>");
	}	
	
	

	if (ajaxFlag.equals("get_element_name_and_quantity")) {
		
	       String ProjectElementId=request.getParameter("ProjectElementId");
	    
			outData = ProjectElementId;
			inData = scr.getElementNameAndQuantity(outData);
			statusAndData = inData.split(statusSeperator);
			if (statusAndData.length == 2) {
				status = Integer.parseInt(statusAndData[0]);
				data = statusAndData[1];
			} else {
				out.println("Data is Insufficient to display");
				return;
			}
			if (status == 0) {

				out.println(data);
				System.out.println(data);

			} else {
				
				return;
			}
			
		
		
		
		
				

			
	}	
	
	if (ajaxFlag.equals("get_brand")) {
		
	       //String ProjectId=request.getParameter("ProjectId");
	     
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
				out.println("Brand: <select id=\"brand_select\" style=\"width:150px\" ><option value=\"select\">select</option></select>");	
				return;
			}
			
	
		out.println("Brand: <select id=\"brand_select\" style=\"width:150px\" ><option value=\"select\">select</option>");	
		
		
			for (int i = 0; i < rowData.length; i++) {
					columnData = rowData[i].split(columnSeperator);
					if (columnData.length == 2) {
						String projectId = columnData[0];
						String projectName = columnData[1];

						out.println("<option value=\"" + projectId + "\">"
								+ projectName + "</option>");

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
	   out.println("<select id=\"order_quantity_unit_select"+rowCount+"\" name=\"costType\"  style=\"width:50px;\" >"+
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
	
	

	
	if (ajaxFlag.equals("create_bom")) {

		outData="";
      
		
		
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

        outData=clientId+rowSeperator+
       
        ProjectId+rowSeperator+ProjectElementId+rowSeperator+
        brandId+rowSeperator+bomCode+rowSeperator+
        bomVersionNumber+rowSeperator+bomDate+rowSeperator+elementSectionData+rowSeperator+
        materialIdData+rowSeperator+heightData+rowSeperator+
        heightUnitIdData+rowSeperator+widthData+rowSeperator+
        widthUnitIdData+rowSeperator+thicknessData+rowSeperator+
       thicknessUnitIdData+rowSeperator+orderQuantityUnitIdData+rowSeperator+capacityData+rowSeperator+
        capacityUnitIdData+rowSeperator+bomQuantityData+rowSeperator+
        orderQuantityData+rowSeperator+processIdData;
       
       System.out.println("this is create bom "+outData);
       

        status = scr.setBom(outData);
		 
		
		if (status == 0) {

			System.out.println(" successfull");

		} else {
			System.out.println(" fail");
			return;
		}
  
  
   
  







		}
	

			
											%>
										
