<%@page import="java.text.DecimalFormat"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<!--<link rel="stylesheet" type="text/css" href="test/demo_table.css"/>-->
<script type="text/javascript"  src="../js/Connect/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/Connect/jquery.js"></script>

</head>

<%
System.out.println("SelectMeasurementData Jsp");
   String visit_store_name=request.getParameter("visit_store_name");
   //String Project_select=request.getParameter("Project_select");
   
   ProjectServices services=new ProjectServices();
   String result=services.selectMeasurementDisplayOnProjectElementID_StoreID(visit_store_name);
   System.out.println("result from selectmeasurementdata.jsp---------------------> ");
%> 

<body>
		<% if(result.equals(Constants.NO_DATA)){	
			System.out.println("NO DATA");
			%>
		<table style="color: #39939C;width: 100%;font-size: 12px;" id="example" class="display">
				<thead>
				<tr>
					<th>Select</th>
					<th>Edit</th>
					<th>Brand</th>
<!--					<th>Project No</th>-->
					<th>Element Name</th>
					<th>Element Type</th>
					<th>Element Status</th>
					<th>Component Type</th>
					<th>Material</th>
					<th>Surface No</th>
					<th>Surface Detail</th>
					<th>Height</th>
					<th>Width</th>
					<th>Unit</th>
					<th>Depth</th>
					<th>Unit</th>
					<th>Quantity</th>
					<th>SFT</th>
					<!-- <td>Upload</td> -->
				</tr>
				</thead>
		</table>		
<%
		}else
		
		{
			System.out.println("DATA Available"+ result);
			%>
	<form id="myMeasureSheet" name="myMeasureSheet">		
		<table style="color: #39939C; width: 100%;font-size: 12px;" id="example" class="display">
			<thead>
				<tr>
					<th>Select</th>
					<th>Edit</th>
					<th>Brand</th>
<!--					<th>Project NO</th>-->
					<th>Element Name</th>
					<th>Element Type</th>
					<th>Element Status</th>
					<th>Component Type</th>
					<th>Material</th>
					<th>Surface No</th>
					<th>Surface Detail</th>
					<th>Height</th>
					<th>Width</th>
					<th>Unit</th>
					<th>Depth</th>
					<th>Unit</th>
					<th>Quantity</th>
					<th>SFT</th>
					
					<!-- <td>Upload</td> -->
				</tr>
			</thead>
			<tbody>
			<%
		String[] rowData=result.split(Constants.rowSeperator);
		double SFT=0.0;
		for(int i=0;i<rowData.length;i++){
			String[] colData=rowData[i].split(Constants.columnSeperator);
		
			String measurement_id=colData[0];
			if(colData[1].equalsIgnoreCase("empty") || colData[1].equalsIgnoreCase("null")){
				colData[1]="";
			}
			if(colData[2].equalsIgnoreCase("empty") || colData[2].equalsIgnoreCase("null")){
				colData[2]="";
			}
			String SurfaceNo =colData[1];
			String SurfaceDetail =colData[2];
			
			
			String Unit=colData[9]; 
			if(colData[5].equals("null")){
				colData[5]="";
			}
			
			String Height =colData[3];
		/*	if(Float.parseFloat(colData[3])==0){
				
				colData[3]="";
				colData[5]=" ";
			}*/
			
			String Width=colData[4]; 
			/**if(Float.parseFloat(colData[4])==0){
				
				colData[4]="";
				colData[5]=" ";
			}**/
			
			
			String Unit_d=colData[10];
			if(colData[7].equals("null")){
				colData[7]=" ";
			}
			
			String Depth=colData[6];
			if(Float.parseFloat(colData[6])==0){
				colData[6]="";
				colData[7]="";
			}
			  DecimalFormat f = new DecimalFormat("##.000");
			  String unit=null;
			  System.out.println("before parse====="+colData[3]+"##"+colData[4]); 
			double height=Double.parseDouble(colData[3]);
			double width=Double.parseDouble(colData[4]);
			System.out.println("after parsing"+height+"##"+width+"##"+(height*width));
			String unitVal=colData[5];
			System.out.println(unitVal+"====unitVal");
			double quant=Double.parseDouble(colData[8]);
			
			if(unitVal.trim().equalsIgnoreCase("FT")){
				SFT=height*width;
				SFT=quant*SFT;
			}
			else if(unitVal.trim().equalsIgnoreCase("INCH")){
				//SFT=(height*width)/144;
				unit=f.format((height*width)/144);
				SFT=Double.parseDouble(unit);
				SFT=quant*SFT;
				System.out.println(SFT+"====for INCH");
			}else if(unitVal.trim().equalsIgnoreCase("CM")){
				//SFT=Math.round(height*width)/900;
				unit=f.format((height*width)/900);
				SFT=Double.parseDouble(unit);
				SFT=quant*SFT;
				System.out.println(SFT+"====for cm");
			}else if(unitVal.trim().equalsIgnoreCase("MM")){
				//SFT=(height*width)/92903.04;
				unit=f.format((height*width)/92903.04);
				SFT=Double.parseDouble(unit);
				SFT=quant*SFT;
				System.out.println(SFT+"====for mm");
			}else if(unitVal.trim().equalsIgnoreCase("METER")){
				//SFT=(height*width)/10.76;
				unit=f.format((height*width)/10.76);
				SFT=Double.parseDouble(unit);
				SFT=quant*SFT;
				System.out.println(SFT+"====for m");
			}else if(unitVal.trim().equalsIgnoreCase("SFT")){
				unit=f.format((height*width));
				SFT=Double.parseDouble(unit);
				SFT=quant*SFT;
				System.out.println(SFT+"===sft");
			}else{
				SFT=0;
			}
			String SFTVal=String.valueOf(SFT);
			String Quantity=colData[8];
			String Brand= colData[11];
			String Element= colData[12];
			String Component= colData[13];
			String Material=colData[14];
			String Project_element_id=colData[15];
			String ElementStatus=colData[16];
			int element_id=Integer.parseInt(colData[17]);
			System.out.println("element_id====>"+element_id);
			if(colData[3].equalsIgnoreCase("0.0")&&(colData[4]).equalsIgnoreCase("0.0")){
				colData[5]="";
				colData[8]="";
				SFTVal="";
			}
			if( colData[3].equalsIgnoreCase("0.0")){
				colData[3]="";
			}
			if( colData[4].equalsIgnoreCase("0.0")){
				colData[4]="";
			}
			
			%>
			<tr class="gradeA">
					<td><input type="checkbox" name="checkbx[]" value="<%=colData[0]%>"> </td>
					<td><a class="edit"><img alt="edit" src="../images/edit.png" 
					onclick="editMeasurementData('<%=SurfaceNo%>','<%=SurfaceDetail%>','<%=Component%>',
					'<%=Height%>','<%=Width%>','<%=colData[5]%>','<%=Depth%>',
					'<%=colData[7]%>','<%=Quantity%>','<%=Project_element_id%>','<%=measurement_id%>','<%=ElementStatus%>','<%=element_id%>');" > </a> 
					</td>
					<td><%=Brand%><input type="hidden" id="pgm_element_id" name="pgm_element_id" value="<%=Project_element_id%>"> </td>
					<%-- <td><%=colData[19]%></td>--%>
					<td><%=colData[20]%></td>
					<td><%=Element%></td>
					<td><%=ElementStatus%></td>
					<td><%=Component%></td>
					<td><%=Material%></td>
					<td><%=colData[1]%></td>
					<td><%=colData[2]%></td>
					<td><%=colData[3]%></td>
					<td><%=colData[4]%></td>
					<td><%=colData[5]%><input type="hidden" id="measurement_id<%=i%>" name="w_unitid" value="<%=measurement_id%>"> </td>
					<td><%=colData[6]%></td>
					<td><%=colData[7]%><input type="hidden" id="d_unit_id" name="d_unit_id" value="<%=colData[10]%>"></td>
					<td><%=colData[8]%></td>
					<td><%=SFTVal%></td>
					
					<!-- <td>Upload</td> -->
			</tr>
			<%
		}
		%>
		</tbody>
		</table>
		</form>
		<%
		
  }   %>
  
  	

</body>
</html> 