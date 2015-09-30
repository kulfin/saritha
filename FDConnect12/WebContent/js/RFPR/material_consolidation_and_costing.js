function getXMLHttpRequestObject() {
	  var xmlHttpReq = false;
	  if (window.XMLHttpRequest) {
	    xmlHttpReq = new XMLHttpRequest();
	  } else if (window.ActiveXObject) {
	    try {
	      
	      xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	    } catch (exp1) {
	      try {
	        
	        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	      } catch (exp2) {
	        xmlHttpReq = false;
	      }
	    }
	  }
	  return xmlHttpReq;
	}
	

function updateMaterialRate(bomId,materialName,rate){
	//alert("material name and rate is "+materialName+rate);
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  //document.getElementById("select_bom").style.display="block";
		 //document.getElementById("select_bom").innerHTML=xmlhttp.responseText;
		 // getBomElementData(bomId);
		 // addRowForCosting();
		  window.location.href = 'MaterialConsolidationAndCostingView.jsp?bomId='+bomId;
		  
	    }
	  };
	var outData="flag=update_material_rate&bomId="+bomId+"&materialName="+materialName+"&rate="+rate;
	//alert(outData);
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}
//validate
function validateAddOperation(countryName)
{   
	var countryNameVal=countryName.value;
	
	 
            	
            	if(countryNameVal=="")
                {
                    alert("Please Enter Country Name");
                    countryName.focus();
                    return false;
                    
            	}
            	
            	
            	
      setCountry(countryNameVal);   	
         
 }



function validateUpdateOperation(countryId,countryName)
{   
	//alert("it is updateValidate");
	var countryNameVal=countryName.value;

	var countryIdVal=countryId.value;
	 
            	
            	if(countryNameVal=="")
                {
                    alert("Please Enter Country Name");
                    countryName.focus();
                    return false;
                    
            	}
            	
            	
            
            	
            
            	
            
           
            
            	
      updateCountry(countryIdVal,countryNameVal);   	
         
 }


 

function showUpdateDiv(countryId,countryName){
	 document.getElementById("country_add").style.display="none";
	 document.getElementById("country_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	 
	 
	document.getElementById("countryName").value=countryName;
	
	document.getElementById("countryId").value=countryId;
	 

	
}

function showAddDiv(){
	document.getElementById("country_update").style.display="none";
	document.getElementById("country_add").style.display="block";
	


	 

	
}




// Get Material Sub Group	








function getBomDropDownData()
{



	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  //document.getElementById("select_bom").style.display="block";
		 document.getElementById("select_bom").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_bom_drop_down_data";
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function getBomGeneralData()
{



	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  //document.getElementById("select_bom").style.display="block";
		 document.getElementById("bom_detail_div").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_bom_general_data";
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

var bomId;
function getBomElementData(bomIdIn)
{
bomId=bomIdIn;
  

	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(bomId);
		 
		  //document.getElementById("select_bom").style.display="block";
		 document.getElementById("material_consolidation_and_costing_div").innerHTML=xmlhttp.responseText;
		  addRowForCosting();
	    }
	  }
	//alert(bomId);
	var outData="flag=get_bom_element_data&bomId="+bomId;
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

var numberOfRows;
function addRowForCosting(){
	//alert("addRow");
var i;	
 numberOfRows=Number(document.getElementById("number_of_costing_rows").value);
for( i=0;i<numberOfRows;i++){
	//alert("inside loop"+numberOfRows);
addRow('material_costing_table',1);
}


//calculateDiscountPercentage();
if(numberOfRows==0){
	var discountAmount=document.getElementById("discount_amount").value;
	var discountPercentage=document.getElementById("discount_percentage").value;
	
	if((discountPercentage==0)||((discountPercentage==0)&&(discountAmount==0))){
		document.getElementById("discount_amount_input").value=discountAmount;
		validate('discount_calculation');
		calculateDiscountPercentage(rowCount);
		enableDisableOperation('discount_amount_input','discount_percentage_input');
		}

		else if(discountAmount==0){
		document.getElementById("discount_percentage_input").value=discountPercentage;
		validate('discount_calculation');
		calculateDiscountAmount(rowCount);
		enableDisableOperation('discount_percentage_input','discount_amount_input');
		}

	
	 validate('calculate');
}

}



function fillFieldsForCosting1(rowCount,flag){
//alert("fill fields1");	
if(document.getElementById("costItemAmount"+rowCount)!=null){
var amount=Number(document.getElementById("costItemAmount"+rowCount).value);
var amountPercentage=Number(document.getElementById("costItemPercentage"+rowCount).value);

if((amountPercentage==0)||((amountPercentage==0)&&(amount==0))){
document.getElementById("amount"+rowCount).value=amount;
calculatePercentage(rowCount);
enableDisableOperation("amount"+rowCount+"","percentage"+rowCount+"");
}

else if(amount==0){
document.getElementById("percentage"+rowCount).value=amountPercentage;
calculateAmount(rowCount);
enableDisableOperation("percentage"+rowCount+"","amount"+rowCount+"");
}

var costTypeId=document.getElementById("costTypeId"+rowCount).value;
//alert("cost type id is "+costTypeId);

var typeDdl = document.getElementById("cost_type_select"+rowCount);
for (k = 0; k <typeDdl.options.length; k++) {
   
	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
   if(costTypeId==(typeDdl .options[k].value)){
   	typeDdl.options[k].selected= true;
   	//alert("inside if "+costTypeId);
   }
}
getMaterialCostItem(rowCount,flag);




	 
}
/*var discountAmount=document.getElementById("discountValue").value;
document.getElementById("discountAmount").value=discountAmount;
validate('calculate');*/

}

var rowCountFlag=0;
function fillFieldsForCosting2(rowCount,flag){
	
	
	if(document.getElementById("costItemAmount"+rowCount)!=null){
	



	var costItemId=document.getElementById("costItemId"+rowCount).value;

	var itemDdl = document.getElementById("cost_item_select"+rowCount);
	for (k = 0; k <itemDdl.options.length; k++) {
	   
		//alert("inside for comp cost item id is "+costItemId+" cost Item Select id is "+itemDdl .options[k].value);
	   if(costItemId==(itemDdl .options[k].value)){
	   	itemDdl.options[k].selected= true;
	   	rowCountFlag++;
	   }
	}
		
	}

	if((Number(rowCountFlag))==(Number(numberOfRows))){
		
		var discountAmount=document.getElementById("discount_amount").value;
		var discountPercentage=document.getElementById("discount_percentage").value;
		
		if((discountPercentage==0)||((discountPercentage==0)&&(discountAmount==0))){
			document.getElementById("discount_amount_input").value=discountAmount;
			validate('discount_calculation');
			calculateDiscountPercentage(rowCount);
			enableDisableOperation('discount_amount_input','discount_percentage_input');
			}

			else if(discountAmount==0){
			document.getElementById("discount_percentage_input").value=discountPercentage;
			validate('discount_calculation');
			calculateDiscountAmount(rowCount);
			enableDisableOperation('discount_percentage_input','discount_amount_input');
			}
	
	
		 validate('calculate');
	}

	}

function getMaterialCostType(rowCount,flag)
{



	//var e = document.getElementById("bom_select");

	//var bomId=e.options[e.selectedIndex].value;
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  //document.getElementById("select_bom").style.display="block";
		 document.getElementById("select_cost_type"+rowCount+"").innerHTML=xmlhttp.responseText;
		 if(flag==1){
		 fillFieldsForCosting1(rowCount,flag);
		 }
	    }
	  }
	//alert(bomId);
	var outData="flag=get_material_cost_type&rowCount="+rowCount;
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function getMaterialCostItem(rowCount,flag)
{



	var e = document.getElementById("cost_type_select"+rowCount);

	var costTypeId=e.options[e.selectedIndex].value;
	
	if(costTypeId=="select"){
     document.getElementById("select_cost_item"+rowCount).innerHTML="<select id=\"cost_item_select"+rowCount+"\" name=\"costItem\" id=\"bom_select\" style=\"width: 150px;\"onchange=\"getBomElementData()\">"+
    "<option>select</option></select>";
     
     return;
		
	}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  //document.getElementById("select_bom").style.display="block";
		 document.getElementById("select_cost_item"+rowCount+"").innerHTML=xmlhttp.responseText;
		 if(flag==1)
		 fillFieldsForCosting2(rowCount,flag);
	    }
	  };
	//alert(bomId);
	var outData="flag=get_material_cost_item&rowCount="+rowCount+"&costTypeId="+costTypeId;
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

var rowCount=0;
function addRow(tableID,flag) {
	rowCount=rowCount+1; 
	//alert(rowCount);
	//alert("this is rowCount"+rowCount);
    var table = document.getElementById(tableID);

    var rowCount1 = table.rows.length;
    var row = table.insertRow(rowCount1);

    var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    cell1.appendChild(element1);

    var cell2 = row.insertCell(1);
    cell2.innerHTML ="<span id=\"select_cost_type"+rowCount+"\"><select id=\"cost_type_select"+rowCount+"\" name=\"costType\" id=\"bom_select\" style=\"width: 150px;\"onchange=\"getBomElementData()\">"+
    "<option>select</option></select></span>";

    var cell3 = row.insertCell(2);
   
    cell3.innerHTML="<span id=\"select_cost_item"+rowCount+"\"><select id=\"cost_item_select"+rowCount+"\" name=\"costItem\" id=\"bom_select\" style=\"width: 150px;\"onchange=\"getBomElementData()\">"+
    "<option>select</option></select></span>";
    
    var cell4 = row.insertCell(3);
    
    cell4.innerHTML="<div style=\"display:inline-block; position:relative;\">" +
    "<div onclick=\"enableDisableOperation('percentage"+rowCount+"','amount"+rowCount+"')\" style=\"position:absolute; left:0; right:0; top:0; bottom:0;\">" +
    "</div>" +
    "<input style=\"text-align:right;\" id=\"percentage"+rowCount+"\"  onchange=\"calculateAmount("+rowCount+")\"  name=\"percentage\" type=\"text\" size=\"15\">" +
    "</div>";
    
    var cell5 = row.insertCell(4);
    cell5.innerHTML="<div style=\"display:inline-block; position:relative;\">" +
    "<div onclick=\"enableDisableOperation('amount"+rowCount+"','percentage"+rowCount+"')\" style=\"position:absolute; left:0; right:0; top:0; bottom:0;\">" +
    "</div>" +
    "<input  style=\"text-align:right;\"  id=\"amount"+rowCount+"\"  onchange=\"calculatePercentage("+rowCount+")\"   name=\"amount\" type=\"text\" size=\"15\">" +
    "</div>";
   
    //alert("before calling getmaterialCostType");
    getMaterialCostType(rowCount,flag);

}

function deleteRow(tableID) {
    try {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;

    for(var i=1; i<rowCount; i++) {
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked) {
            table.deleteRow(i);
            rowCount--;
            i--;
        }


    }
    }catch(e) {
        alert(e);
    }
}

function enableDisableOperation(enableInputFieldId,disableInputFieldId){
	//alert();
document.getElementById(enableInputFieldId).disabled=false;
document.getElementById(enableInputFieldId).focus();

//document.getElementById(disableInputFieldId).value="";
document.getElementById(disableInputFieldId).disabled=true;

//alert(document.getElementById(disableInputFieldId).disable);
	
}



var grossMaterialCost;
function validate(flag) {

	var amount = new Array();
	var amountPercentage = new Array();
	var costItem = new Array();

	var j = 0;
	var k = 0;

	for ( var i = 1; i <= rowCount; i++) {
		if (document.getElementById("cost_item_select" + i) != null) {

			if (document.getElementById("cost_item_select" + i).selectedIndex == 0) {
				alert("Please Select Cost Item at Row " + i);
				return false;

			} else {
				var e = document.getElementById("cost_item_select" + i);
				var costItemId = Number(e.options[e.selectedIndex].value);
				costItem[k] = costItemId;
				// alert("this is cost Item Id "+costItem[k]);
				k++;

			}

			if (document.getElementById("amount" + i).value == "") {

				alert("Please Enter Amount or Percentage");
				document.getElementById("amount" + i).focus();
				return false;
			}

			else if (isNaN(document.getElementById("amount" + i).value)) {
				alert("Please Enter Numeric Value For Amount or Percentage");
				document.getElementById("amount" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("amount" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Amount or Percentage");
				document.getElementById("amount" + i).focus();
				return false;
			}

			else {
				if (document.getElementById("amount" + i).disabled == false) {
					amount[j] = Math.round(Number(document
							.getElementById("amount" + i).value) * 100) / 100;
					amountPercentage[j] = 0;
					j++;
				}

				else {
					amountPercentage[j] = Math.round(Number(document
							.getElementById("percentage" + i).value) * 100) / 100;
					
					amount[j] = Math.round(Number(document
							.getElementById("amount" + i).value) * 100) / 100;
					j++;
				}
			}

		}
	}
	// Math.round(4.3564*100)/100 ;
	var totalMaterialCost = Math
			.round((Number(document.materialCostingForm.totalMaterialCost.value)) * 100) / 100;

	grossMaterialCost = 0;
	for ( var k = 0; k < amount.length; k++) {

		// alert("This is percentage "+percentage[k]+" and this is amount
		// "+amount[k]);
		var materialCostItemCost = amount[k];
		grossMaterialCost = grossMaterialCost + materialCostItemCost;

	}
	grossMaterialCost = Math.round((grossMaterialCost + totalMaterialCost)*100)/100;
	
	if(flag=="discount_calculation"){
		
		return;
	}

	document.getElementById("gross_cost_display_div").style.display = "block";
	document.getElementById("gross_cost").innerHTML = grossMaterialCost;
	document.getElementById("discount_entry_div").style.display = "block";
	var discountValue = Math
			.round((Number(document.materialCostingForm.discountAmount.value)) * 100) / 100;
	var discountPercentage;

	if (isNaN(discountValue)) {
		alert("Please Enter Numeric Value For Discount or Percentage");
		document.materialCostingForm.discountAmount.focus();
		return false;
	}

	else if ((Number(discountValue)) < 0) {
		alert("Negative Value Not Allowed For Discount or Percentage");
		document.materialCostingForm.discountAmount.focus();
		return false;
	}

	else {
		if (document.materialCostingForm.discountAmount.disabled == false) {
			discountValue = Math.round(Number(discountValue) * 100) / 100;
			if (discountValue == "") {
				discountValue = 0;
			}
			discountPercentage = 0;

		}

		else {
			discountPercentage = Math
					.round(Number(document.materialCostingForm.discountPercentage.value) * 100) / 100;
			if (discountPercentage == "") {
				discountPercentage = 0;
			}
			discountValue = Math.round(Number(discountValue) * 100) / 100;

		}

	}

    var netMaterialCost = Math.round((grossMaterialCost - discountValue) * 100) / 100;
	document.getElementById("net_cost_display_div").style.display = "block";
	document.getElementById("net_cost").innerHTML = netMaterialCost;

	if ((flag == "save") || (flag == "update")) {
		for(var i=0;i<amount.length;i++){
			if(Number(amountPercentage[i])!=0){
				
				amount[i]=0;
			}
		}
		
		if(discountPercentage!=0){
			discountValue=0;
		}
		setBomCost(flag, costItem, amount,amountPercentage,discountValue,discountPercentage);

	}

}

function setBomCost(flag, costItem, amount,amountPercentage,discountValue,discountPercentage)
{
	// alert("setbomCost");
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 alert("Bom Cost Items Updated Successfully");
		// window.location.href="../RFPR/SearchBomView.jsp";
	    }
	  };
	// alert(bomId);
	var outData="flag=set_bom_cost&subFlag="+flag+"&bomId="+bomId+"&costItem="+costItem+"&amount="+amount+"&amountPercentage="+amountPercentage+"&discountAmount="+discountValue+"&discountPercentage="+discountPercentage;
	xmlhttp.open("POST","MaterialConsolidationAndCostingController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function calculateAmount(rowCount){
// alert("field is changed");
var costItemPercentage = Number(document.getElementById("percentage"+rowCount).value);
var totalMaterialCost=Number(document.materialCostingForm.totalMaterialCost.value);
var totalAmount=Math.round((((costItemPercentage)*(totalMaterialCost))/100)*100)/100;
document.getElementById("amount"+rowCount).value=totalAmount;
/*if(document.getElementById("percentage"+rowCount).value==""){
	document.getElementById("percentage"+rowCount).disabled=false;
	document.getElementById("amount"+rowCount).disabled=true;
}*/
}

function calculatePercentage(rowCount){
	//alert("field is changed");
	 
	var totalMaterialCost=Number(document.materialCostingForm.totalMaterialCost.value);
	var totalAmount=document.getElementById("amount"+rowCount).value;
    var costItemPercentage=Math.round((((totalAmount)*(100))/totalMaterialCost)*100)/100;
	document.getElementById("percentage"+rowCount).value=costItemPercentage;
	
/*	if(document.getElementById("amount"+rowCount).value==""){
		document.getElementById("percentage"+rowCount).disabled=true;
		document.getElementById("amount"+rowCount).disabled=false;
	}*/
}

function calculateDiscountAmount(){
	//alert("field is changed");
	var discountPercentage=Number(document.materialCostingForm.discountPercentage.value);
	var discountAmount=Math.round((((discountPercentage)*(grossMaterialCost))/100)*100)/100;
	//alert(discountPercentage);
	document.materialCostingForm.discountAmount.value=discountAmount;
	//validate('flag');
}

function calculateDiscountPercentage(){
		//alert("field is changed");
	
	var discountAmount=Number(document.materialCostingForm.discountAmount.value);
	var discountPercentage=Math.round((((discountAmount)*(100))/grossMaterialCost)*100)/100;
	//alert(discountAmount);
	document.materialCostingForm.discountPercentage.value=discountPercentage;
	
	
}


