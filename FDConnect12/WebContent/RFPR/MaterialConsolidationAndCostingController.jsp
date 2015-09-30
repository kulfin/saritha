<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fd.Rfpr.*"%>
<%@ page import="java.util.*"%>
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
	String fieldSeperator = "!&#!";
	String statusAndData[];
	int status=0;
	String data;
	String rowData[];
	String columnData[];
	String inData=null;
	String outData;
	Service scr = new Service();

	String ajaxFlag = request.getParameter("flag");
	//System.out.println("This is ajax flag "+ajaxFlag);
	

		
			

						if (ajaxFlag.equals("get_bom_drop_down_data")) {

							outData = "";

						
                           

							inData = scr.getBomDropDownData(outData);
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
								 out.println("<select id=\"bom_select\" style=\"width: 150px;\" onchange=\"getBomElementData()\"><option>select</option></select>");
								return;
							}
					
 out.println("<select id=\"bom_select\" style=\"width: 150px;\" onchange=\"getBomElementData()\"><option>select</option>");





	
							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

									
									    String bomId = columnData[0];
										String bomCode = columnData[1];
										

										out
												.println("<option value=\""+bomId+"\">"+bomCode+"</option>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</select>");

							}
						
	

						if (ajaxFlag.equals("get_material_cost_type")) {

							outData = "";
                           String rowCount= request.getParameter("rowCount");
						
                           

							inData = scr.getMaterialCostType(outData);
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
								out.println("<select id=\"cost_type_select"+rowCount+"\" name=\"costType\"  style=\"width: 150px;\" onchange=\"getMaterialCostItem('"+rowCount+"')\">"+
							    "<option value=\"-1\">select</option></select>");
								return;
							}
				
out.println("<select id=\"cost_type_select"+rowCount+"\" name=\"costType\"  style=\"width: 150px;\" onchange=\"getMaterialCostItem('"+rowCount+"')\">"+
    "<option value=\"-1\">select</option>");






							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

									
									    String costTypeId = columnData[0];
										String costTypeName = columnData[1];
										

										out
												.println("<option value=\""+costTypeId+"\">"+costTypeName+"</option>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</select>");

							}
						
			
						
						if (ajaxFlag.equals("get_material_cost_item")) {

							outData = "";
                           String rowCount= request.getParameter("rowCount");
                           String costTypeId= request.getParameter("costTypeId");
						 outData=costTypeId;
                           

							inData = scr.getMaterialCostItem(outData);
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
								out.println("<select id=\"cost_item_select"+rowCount+"\" name=\"costItem\"  style=\"width: 150px;\" onchange=\"getSelectedMaterialCostItemId('"+rowCount+"')\">"+
							    "<option>select</option></select>");
								return;
							}
				
                        out.println("<select id=\"cost_item_select"+rowCount+"\" name=\"costItem\"  style=\"width: 150px;\" onchange=\"getSelectedMaterialCostItemId('"+rowCount+"')\">"+
                        "<option>select</option>");






							for (int i = 0; i < rowData.length; i++) {
									columnData = rowData[i].split(columnSeperator);
									if (columnData.length == 2) {

									
									    String costItemId = columnData[0];
										String costItemName = columnData[1];
										

										out
												.println("<option value=\""+costItemId+"\">"+costItemName+"</option>");

									} else {

										out.println("Data is Unsufficient to display");
										return;
									}

								}
								out.println("</select>");

							}
						
						
						

						if (ajaxFlag.equals("set_bom_cost")) {

							outData = "";

						 String subFlag=request.getParameter("subFlag");
                         String bomId=request.getParameter("bomId");
                         String discountPercentage=request.getParameter("discountPercentage");
                         String discountAmount=request.getParameter("discountAmount");
                         String costItem[]=request.getParameter("costItem").split(",");
                         String amount[]=request.getParameter("amount").split(",");
                         String amountPercentage[]=request.getParameter("amountPercentage").split(",");
                          
                         String costItemData="";
                         String amountData="";
                         String amountPercentageData="";
                         for(int i=0;i<costItem.length;i++){
                         costItemData=costItemData+costItem[i]+columnSeperator; 
                         amountData=amountData+amount[i]+columnSeperator; 
                         amountPercentageData=amountPercentageData+amountPercentage[i]+columnSeperator; 
                         }

                         outData=bomId+rowSeperator+costItemData+rowSeperator+
                         amountData+rowSeperator+amountPercentageData+rowSeperator+discountAmount+rowSeperator+discountPercentage;
                         
                         System.out.println("this is out Data for setBome "+outData);
                         if(subFlag.equals("save")){
                         
                        	 status = scr.setBomCost(outData);
                         }
                         else if(subFlag.equals("update")){
                             
                        	 status = scr.updateBomCost(outData);
                         }
                         
							
							
							if (status == 0) {

								System.out.println("success");
								

							} else {
								System.out.println("failure "+status);
								return;
							}
					
							
									

								}
							
						
						

						if (ajaxFlag.equals("update_material_rate")) {

							outData = "";

                         String bomId=request.getParameter("bomId");
                         String materialName=request.getParameter("materialName");
                         String rate=request.getParameter("rate");
                     
                         outData=bomId+columnSeperator+materialName+columnSeperator+rate;
                         
                         System.out.println("this is out Data for setBome "+outData);
                         status=scr.updateMaterialRate(outData);
							
							
							if (status == 0) {

								System.out.println("success");
								

							} else {
								System.out.println("failure "+status);
								return;
							}
					
							
									

								}
							

						if (ajaxFlag.equals("get_bom_element_data")) {
                            int rowDataForCostingLength;
							outData = "";
							String rowDataForConsolidation[];
							String rowDataForCosting[];
							String rowDataForDiscount[];

						  String bomId=request.getParameter("bomId");
                           outData=bomId;
                           System.out.println("this is controller bomId is "+bomId);
							inData = scr.getBomElementData(outData);
							statusAndData = inData.split(statusSeperator);
							if (statusAndData.length == 2) {
								status = Integer.parseInt(statusAndData[0]);
								data = statusAndData[1];
							} else {
								out.println("Data is Insufficient to after status display");
								return;
							}
							if (status == 0) {

								System.out.println("this is viw indata"+inData);
								rowData=data.split(fieldSeperator);
								
								if(rowData.length==3){
								
								rowDataForConsolidation = rowData[0].split(rowSeperator);
								//System.out.println("this is consolidation data "+rowData[0]);
								if(!rowData[1].equals(" ")){
									
								 rowDataForCosting = rowData[1].split(rowSeperator);
								 rowDataForCostingLength=rowDataForCosting.length;
								}
								else{
									rowDataForCosting=null;
									rowDataForCostingLength=0;
									System.out.println("inside rowData[1] equals if ");
								}
								rowDataForDiscount = rowData[2].split(columnSeperator);
								}
								else{
									rowDataForConsolidation=null;
									rowDataForCosting=null;
									rowDataForDiscount=null;
									rowDataForCostingLength=0;
								}
								

							} else {
								out.println(data);
								return;
							}
			
							
							
						String materialName[]=new String[rowDataForConsolidation.length];
						int orderQuantity[]=new int[rowDataForConsolidation.length];
						float rate[]=  new float[rowDataForConsolidation.length];
							for (int k = 0; k < rowDataForConsolidation.length; k++) {
									columnData = rowDataForConsolidation[k].split(columnSeperator);
									if (columnData.length == 3) {

									
									     materialName[k] = columnData[0];
									     rate[k] = Float.parseFloat(columnData[1]);
										 orderQuantity[k] = Integer.parseInt(columnData[2]);
									} else {

										out.println("Data is Unsufficient  to display");
										return;
									}
							}
							
										 List<String>consolidatedMaterialName = new ArrayList<String>();
										 List<Integer>consolidatedOrderQuantity = new ArrayList<Integer>();
										 List<Float>consolidatedMaterialRate = new ArrayList<Float>();
										 
										 boolean check;
										 int consolidatedMaterialNameIndex=0;
										 int consolidatedOrderQuantityElement;
										 int requiredMaterialQuantity[];
										 int consolidatedOrderQuantityCal;
										 String materialDimension[]=null;

										 for(int i=0;i<materialName.length;i++)
										 {
										  check=false;	

										 	for(int j=0;j<consolidatedMaterialName.size();j++)//intial condition is false for Zero
										 	{
										 		if((materialName[i]).equals(consolidatedMaterialName.get(j)))
										 		{
										 			check=true;
										 		    consolidatedMaterialNameIndex=j;
										 		    break;
										 		}
										 	}
										 	if(check)
										 	{
										 		consolidatedOrderQuantityElement =consolidatedOrderQuantity.get(consolidatedMaterialNameIndex);
										 		consolidatedOrderQuantityCal=consolidatedOrderQuantityElement+orderQuantity[i]; 
										 		consolidatedOrderQuantity.remove(consolidatedMaterialNameIndex);
										 		consolidatedOrderQuantity.add(consolidatedMaterialNameIndex,consolidatedOrderQuantityCal);
										 	}
										 	
										 	else{
										 		consolidatedMaterialName.add((materialName[i]));
										 		consolidatedOrderQuantity.add(orderQuantity[i]);
										 		consolidatedMaterialRate.add(rate[i]);
										 	}
										 }	

									
							
							System.out.println("consolidated material Name= "+consolidatedMaterialName);
							System.out.println("consolidated Order Quantity= "+consolidatedOrderQuantity);
							System.out.println("consolidated Order Quantity= "+consolidatedMaterialRate);
							
				%>
<div id="material_consolidation_div">
<table cellspacing="0">
	<tr>
		<th>Material Name</th>
		<th style="width:100px;">Order Quantity</th>
		<th style="width:100px;">Rate</th>
		<th style="width:100px;">Amount</th>
	</tr>

	<%     
                       double totalAmount=0.0;
                       for(int l=0;l<consolidatedMaterialName.size();l++){
                        	   
                        	   double amount=((consolidatedMaterialRate.get(l))*(consolidatedOrderQuantity.get(l)));
                        	   amount = ((int) Math.round(amount*100))/100.0;
                              
                        	   totalAmount=totalAmount+amount;
                    	   out.println("<tr><td>"+consolidatedMaterialName.get(l)+"</td><td align=\"right\">"+
                    			   consolidatedOrderQuantity.get(l)+"</td><td align=\"right\">"+
                    			   "<input type=\"text\" value=\""+consolidatedMaterialRate.get(l)+"\" size=\"10\" style=\"text-align:right;\" onchange=\"updateMaterialRate('"+bomId+"','"+consolidatedMaterialName.get(l)+"',this.value)\">"+
                    			   "</td><td align=\"right\">"+
                    			   amount+"</td></tr>");	
                        	   
                        	   
                           }
                       
                      
                      
                         totalAmount = ((int) Math.round(totalAmount*100))/100.0;
                      
    
	
                          out.println("</table>");
                          out.println("<div id=\"total_amount_display_div\"><span style=\"margin:0 0 0 15px\">Material Cost:</span><span  style=\"color: rgb(70, 66, 66); float:right; \">"+totalAmount+"</span></div>");
                          out.println("</div>");
						%>

	<div id="add_delete_row_buttons"><span style="margin:0 0 0 0;"><INPUT
		type="button" value="Add Row" class="costing_table_buttons"
		onclick="addRow('material_costing_table')" /></span> <span style="margin:0 0 0 9px;"><INPUT
		type="button" value="Delete Row" class="costing_table_buttons"
		onclick="deleteRow('material_costing_table')" /></span> <span style="margin:0 0 0 9px;"><INPUT
		type="button" value="Calculate Quote" class="costing_table_buttons"
		onclick=" return validate('calculate_amount')" /></span> <%
Double discountAmount=0.0;
Double discountPercentage=0.0;		
if((rowDataForDiscount!=null)&&(rowDataForDiscount.length==2)){
discountAmount=Double.parseDouble(rowDataForDiscount[0]);
discountPercentage=Double.parseDouble(rowDataForDiscount[1]);
System.out.println("inside discount disAmount "+discountAmount+" percentage "+discountPercentage);
}		
if((rowDataForCostingLength==0)&&((discountAmount==0)&&(discountPercentage==0)))
out.println("<span style=\"margin:0 0 0 9px;\" ><INPUT class=\"costing_table_buttons\" type=\"button\" value=\"Save\" onclick=\" return validate('save')\" /></span>");
else
out.println("<span style=\"margin:0 0 0 9px;\" ><INPUT class=\"costing_table_buttons\" type=\"button\" value=\"Update\" onclick=\" return validate('update')\" /></span>");	
%>
	</div>
	<div id="material_costing_div">

	<form action="#" name="materialCostingForm">
	<table id="material_costing_table" cellspacing="0">
		<tr>
			<th>Select</th>
			<th>Cost Type</th>
			<th>Cost Item</th>
			<th style="width:125px;">Percentage</th>
			<th style="width:125px;">Amount</th>
		</tr>

		<tr>
			<td></td>
			<td>Material Cost</td>
			<td>Material Cost</td>
			<td></td>
			<td align="right"><%=totalAmount %></td>
		</tr>

	</table>
	<input type="hidden" id="number_of_costing_rows"
		value="<%=rowDataForCostingLength %>" /> <%

if(rowDataForCosting!=null){
for(int i=0;i<rowDataForCosting.length;i++){
columnData=rowDataForCosting[i].split(columnSeperator);
if(columnData.length==4){
String costTypeId=columnData[0].trim();
String costItemId=columnData[1];
String amount=columnData[2];
String amountPercentage=columnData[3];
int j=i+1;
out.println("<input type=\"hidden\" id=\"costTypeId"+j+"\" value=\""+costTypeId+"\" />");
out.println("<input type=\"hidden\" id=\"costItemId"+j+"\" value=\""+costItemId+"\" />");
out.println("<input type=\"hidden\" id=\"costItemAmount"+j+"\" value=\""+amount+"\" />");
out.println("<input type=\"hidden\" id=\"costItemPercentage"+j+"\" value=\""+amountPercentage+"\" />");
}


}
}
out.println("<input type=\"hidden\" id=\"discount_amount\" value=\""+discountAmount+"\" /> ");
out.println("<input type=\"hidden\" id=\"discount_percentage\" value=\""+discountPercentage+"\" />");
%>

	<div id="gross_cost_display_div"><span style="margin: 0 0 0 0px;">
	Gross Amount:</span><span id="gross_cost"
		style="color: rgb(70, 66, 66); margin: 0 0 0 5px;"> </span></div>

	<div id="discount_entry_div"><span style="margin: 0 0 0 5px">
	Enter Discount (Percentage):<span
		style="color: rgb(70, 66, 66); margin: 0 0 0 0;">
		<div style="display:inline-block; position:relative;">
	    <div onclick="enableDisableOperation('discount_percentage_input','discount_amount_input')" style="position:absolute; left:0; right:0; top:0; bottom:0;">
	    </div>
		 <input
		style="height: 12px;text-align:right;" type="text" onchange="calculateDiscountAmount()"
		size="12" name="discountPercentage" id="discount_percentage_input">
		</div>
		</span></span>
		
		 <span
		style="margin: 0 0 0 40px"> Enter Discount Amount:<span
		style="color: rgb(70, 66, 66); margin: 0 0 0 0;"> 
		<div style="display:inline-block; position:relative;">
	    <div onclick="enableDisableOperation('discount_amount_input','discount_percentage_input')" style="position:absolute; left:0; right:0; top:0; bottom:0;">
	    </div>
		<input
		style="height: 12px;text-align:right;" type="text" size="12"
		onchange="calculateDiscountPercentage()" name="discountAmount"
		id="discount_amount_input" ></span></span>
		</div>
		
		</div>
		

	<div id="net_cost_display_div"><span style="margin: 0 0 0 0px;">
	Minimum Quotable Price:</span><span id="net_cost"
		style="color: rgb(70, 66, 66); margin: 0 0 0 5px;"> </span></div>

	<input type="hidden" name="totalMaterialCost" value="<%=totalAmount %>" />

	</form>
	</div>







	<% 		

							}
							
						
											%>

</body>
</html>