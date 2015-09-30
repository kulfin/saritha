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
    cell2.innerHTML ="<input id=\"element_section_input"+rowCount+"\"  name=\"percentage\" type=\"text\" size=\"27\">";

    var cell3 = row.insertCell(2);
    cell3.innerHTML="<span id=\"select_material"+rowCount+"\"><select id=\"material_select"+rowCount+"\" name=\"material\"  style=\"width: 250px;\" >"+
    "<option>select</option></select></span>";
    
    //
    var cell4 = row.insertCell(3);
    cell4.innerHTML ="<input id=\"height_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
    var cell5 = row.insertCell(4);
    cell5.innerHTML="<span id=\"select_height_unit"+rowCount+"\"><select id=\"height_unit_select"+rowCount+"\" name=\"heightUnit\"  style=\"width: 50px;\" >"+
    "<option>select</option></select></span>";
    
  //
    var cell6 = row.insertCell(5);
    cell6.innerHTML ="<input id=\"width_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\" />";
    
    var cell7 = row.insertCell(6);
    cell7.innerHTML="<span id=\"select_width_unit"+rowCount+"\"><select id=\"width_unit_select"+rowCount+"\" name=\"heightUnit\"  style=\"width: 50px;\" >"+
    "<option>select</option></select></span>";
    
  
    //
    
    var cell8 = row.insertCell(7);
    cell8.innerHTML ="<input id=\"thickness_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
    var cell9 = row.insertCell(8);
    cell9.innerHTML="<span id=\"select_thickness_unit"+rowCount+"\"><select id=\"thickness_unit_select"+rowCount+"\" name=\"heightUnit\"  style=\"width: 50px;\" >"+
    "<option>select</option></select></span>";
    
  
    //
    
    var cell10 = row.insertCell(9);
    cell10.innerHTML ="<input id=\"capacity_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
    var cell11 = row.insertCell(10);
    cell11.innerHTML="<span id=\"select_capacity_unit"+rowCount+"\"><select id=\"capacity_unit_select"+rowCount+"\" name=\"heightUnit\"  style=\"width: 50px;\" >"+
    "<option>select</option></select></span>";
    
  /*
    var cell12 = row.insertCell(11);
    cell12.innerHTML ="<input id=\"heightMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
    var cell13 = row.insertCell(12);
    cell13.innerHTML ="<input id=\"widthMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
    var cell14 = row.insertCell(13);
    cell14.innerHTML ="<input id=\"depthMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
    var cell15 = row.insertCell(14);
    cell15.innerHTML ="<input id=\"capacityMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">";
    
  */
    
    var cell12 = row.insertCell(11);
    cell12.innerHTML ="<input id=\"bom_quantity_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">"
    
    var cell13 = row.insertCell(12);
    cell13.innerHTML ="<input id=\"order_quantity_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">"
    
  /*  var cell14 = row.insertCell(13);
    cell14.innerHTML="<span id=\"select_process"+rowCount+"\"><select id=\"process_select"+rowCount+"\" name=\"heightUnit\"  style=\"width: 150px;\" >"+
    "<option>select</option></select></span>";*/
    
    var cell14 = row.insertCell(13);
	cell14.innerHTML = "<span id=\"select_order_quantity_unit" + rowCount
			+ "\"><select id=\"order_quantity_unit_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 50px;\" >"
			+ "<option>select</option></select></span>";
	
	var cell15 = row.insertCell(14);
	cell15.innerHTML = "<span id=\"select_process" + rowCount
			+ "\"><select id=\"process_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 150px;\" >"
			+ "<option>select</option></select></span>";
   
    
    getMaterial(rowCount,flag);
    getProcess(rowCount,flag);
    getHeightUnit(rowCount,flag);
    getWidthUnit(rowCount,flag);
    getThicknessUnit(rowCount,flag);
    getOrderQuantityUnit(rowCount,flag);
    getCapacityUnit(rowCount,flag);
    
    if(flag==1){
    fillFieldsForBomEdit(rowCount);	
    	
    }
    

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



 



//Get Material Group	
function getClientForFilterOperation()
{
	
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		  document.getElementById("filter_select_client").innerHTML=xmlhttp.responseText;
		 
		  
		
		  }
	    }
	 
	var outData="flag=get_client&subFlag=filterOperation";
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
var selectedClientIdForFilterOperation;
function getProjectForFilterOperation()
{
	
	var e = document.getElementById("filter_client_select");
	
	selectedClientIdForFilterOperation=e.options[e.selectedIndex].value;
		
	if(selectedClientIdForFilterOperation=="select"){
		  //alert("Please select Country");
		  document.getElementById("bom_div").style.display="none";
		  document.getElementById("filter_select_Project").innerHTML="Project: <select id=\"filter_Project_select\" style=\"width:250px;\"><option>select</option></select>";
		  document.getElementById("filter_select_element").innerHTML="Element: <select id=\"filter_element_select\" style=\"width:250px;\"><option>select</option></select>";
		 

		return;
	}
	


	
		
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  document.getElementById("bom_div").style.display="none";
		  document.getElementById("filter_select_Project").innerHTML=xmlhttp.responseText;
		  document.getElementById("filter_select_element").innerHTML="Element: <select id=\"filter_element_select\"  style=\"width:250px;\"><option>select</option></select>";
		
		 
	    
	  }
	  }

   
	var outData="flag=get_Project&subFlag=filterOperation&clientId="+selectedClientIdForFilterOperation;
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




var selectedProjectIdForFilterOperation;
function getElementForFilterOperation()
{
		
	var e = document.getElementById("filter_Project_select");
   
	selectedProjectIdForFilterOperation=e.options[e.selectedIndex].value;
	//alert("it is calling "+selectedProjectIdForFilterOperation);
	if(selectedProjectIdForFilterOperation=="select"){
		  //alert("Please select Country");
		  document.getElementById("bom_div").style.display="none";
		  document.getElementById("filter_select_element").innerHTML="Element: <select id=\"filter_element_select\"  style=\"width:250px;\"><option>select</option></select>";
		 

		return;
	}
	


	
		
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  document.getElementById("bom_div").style.display="none";
		  document.getElementById("filter_select_element").innerHTML=xmlhttp.responseText;
		 
		
		 
	    
	  }
	  }

   
	var outData="flag=get_element&subFlag=filterOperation&ProjectId="+selectedProjectIdForFilterOperation;
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




var selectedProjectElementIdForFilterOperation;
function getBomByFilterOperation()
{
		
	var e = document.getElementById("filter_element_select");
   
	selectedProjectElementIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedProjectElementIdForFilterOperation=="select"){
		  //alert("Please select Country");
		  document.getElementById("bom_div").style.display="none";
		return;
	}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  document.getElementById("bom_div").style.display="block";
		  document.getElementById("bom_div").innerHTML=xmlhttp.responseText;
	  }
	  };

	document.getElementById("search_bom_by_bom_code").checked=true;
	document.searchBomForm.searchBomByBomCode.disabled=false;
	document.searchBomForm.searchBomByBomCode.value="";
	document.searchBomForm.searchBomByProjectName.value="";
	document.searchBomForm.searchBomByProjectName.disabled=true;
	var outData="flag=get_bom_by_filter&ProjectElementId="+selectedProjectElementIdForFilterOperation;
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function getBomBySearchOperation()
{
	
	var radioButtonValue = document.getElementById("search_bom_by_bom_code").checked;
	var subFlag;
	var searchText;
	searchText= document.searchBomForm.searchBomByProjectName.value;
	if(radioButtonValue==true){
		subFlag="search_bom_by_bom_code";
	    searchText= document.searchBomForm.searchBomByBomCode.value;
	    if(searchText==""){
	    	alert("Please enter some text for search");
	    	document.searchBomForm.searchBomByBomCode.focus();
	    	return;
	    }
	}
	else if(radioButtonValue==false){
		subFlag="search_bom_by_Project_name";
		searchText= document.searchBomForm.searchBomByProjectName.value;
		
		  if(searchText==""){
		    	alert("Please enter some text for search");
		    	document.searchBomForm.searchBomByProjectName.focus();
		    	return;
		    }
	}

	
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  document.getElementById("bom_div").style.display="block";
		  document.getElementById("bom_div").innerHTML=xmlhttp.responseText;
		 
		
		 
	    
	  }
	  }
	document.getElementById("filter_client_select").selectedIndex=0;
    document.getElementById("filter_select_Project").innerHTML=""+
	 "Project: <select id=\"filter_Project_select\"  style=\"width:250px;\"><option>select</option></select>";
	 
	 document.getElementById("filter_select_element").innerHTML=""+
	 "Element: <select id=\"filter_element_select\"  style=\"width:250px;\"><option>select</option></select>";	

   
	var outData="flag=get_bom_by_search&subFlag="+subFlag+"&searchText="+searchText;
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}
var bomIdForUpdate;
var clientIdForUpdate;
var ProjectIdForUpdate;
var ProjectElementIdForUpdate;
var brandIdForUpdate;
function editBom(bomId,bomCode,bomVersionNumber,ProjectId,clientId,elementCode,ProjectId,ProjectElementId,brandId,bomDate,elementName){
	clientIdForUpdate=clientId;
	ProjectIdForUpdate=ProjectId;
	ProjectElementIdForUpdate=ProjectElementId;
	brandIdForUpdate=brandId;
	bomIdForUpdate=bomId;
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 // document.getElementById("bom_div").style.display="block";
		  document.getElementById("maincontent").innerHTML=xmlhttp.responseText;
		  getClientForUpdateOperation(1);
		  document.getElementById("element_name_input").innerHTML=elementName;
		  if(bomCode=="null"){
		   document.getElementById("bom_code_input").value="";
		  }
		  else{
		  document.getElementById("bom_code_input").value=bomCode;
		  }
		  if(bomVersionNumber=="null"){
			document.getElementById("bom_version_input").value="";
		  }
		  else{
			document.getElementById("bom_version_input").value=bomVersionNumber;
		  }
		  
		  document.getElementById("bom_date_input").innerHTML=bomDate;
		  if(document.getElementById("number_of_rows")!=null){
		  var numberOfRows=document.getElementById("number_of_rows").value;
		  for(var i=0;i<numberOfRows;i++){
			  addRow('bom_element_detail_table',1);
			 // alert("edit bom");
		  }
		  }
		  
		 
		
		 
	    
	  }
	  }

   
	var outData="flag=edit_bom&bomId="+bomId;
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);


}



function fillFieldsForBomEdit(rowCount){
if(document.getElementById("element_section"+rowCount)!=null){
	
var elementSection=document.getElementById("element_section"+rowCount).value;
document.getElementById("element_section_input"+rowCount).value=elementSection;

var height=document.getElementById("height"+rowCount).value;
if(height=="0.0"){
document.getElementById("height_input"+rowCount).value="";	
}
else{
document.getElementById("height_input"+rowCount).value=height;
}

var width=document.getElementById("width"+rowCount).value;
if(width=="0.0"){
	document.getElementById("width_input"+rowCount).value="";	
	}
	else{
	document.getElementById("width_input"+rowCount).value=width;
	}


var thickness=document.getElementById("thickness"+rowCount).value;
if(thickness=="0.0"){
	document.getElementById("thickness_input"+rowCount).value="";	
	}
	else{
	document.getElementById("thickness_input"+rowCount).value=thickness;
	}



var capacity=document.getElementById("capacity"+rowCount).value;
var capacity=document.getElementById("capacity"+rowCount).value;
if(capacity=="0.0"){
	document.getElementById("capacity_input"+rowCount).value="";	
	}
	else{
	document.getElementById("capacity_input"+rowCount).value=capacity;
	}

var bomQuantity=document.getElementById("bom_quantity"+rowCount).value;
document.getElementById("bom_quantity_input"+rowCount).value=bomQuantity;

var orderQuantity=document.getElementById("order_quantity"+rowCount).value;
document.getElementById("order_quantity_input"+rowCount).value=orderQuantity;


}
}

function getMaterial(rowCount,flag)
{     //alert("get Material1");
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert("get Material2");
		 
		  document.getElementById("select_material"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		         if(flag==1){
		        	 //alert("inside getMaterial flag");
				 var materialId=document.getElementById("material_id"+rowCount).value;
				 //alert("inside getMaterial flag material id is "+materialId);
				 var typeDdl = document.getElementById("material_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
					 //alert("inside getMaterial flag loop id is "+materialId+"select value is "+typeDdl .options[k].value);
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(materialId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_material&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function getProcess(rowCount,flag)
{    //alert("get Process1");
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert("get Process2");
		 
		  document.getElementById("select_process"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
			     var processId=document.getElementById("process_id"+rowCount).value;
				 var typeDdl = document.getElementById("process_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(processId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_process&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getHeightUnit(rowCount,flag)
{   //alert("get Height1");
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 // alert("get Height2")
		 
		  document.getElementById("select_height_unit"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
			    var heightUnitId=document.getElementById("height_unit"+rowCount).value;
				 var typeDdl = document.getElementById("height_unit_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(heightUnitId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_unit&subFlag=heightUnit&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getWidthUnit(rowCount,flag)
{
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		  document.getElementById("select_width_unit"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
			     var widthUnitId=document.getElementById("width_unit"+rowCount).value;
				 var typeDdl = document.getElementById("width_unit_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(widthUnitId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_unit&subFlag=widthUnit&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getThicknessUnit(rowCount,flag)
{
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		  document.getElementById("select_thickness_unit"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
			     var thicknessUnitId=document.getElementById("thickness_unit"+rowCount).value;
				 var typeDdl = document.getElementById("thickness_unit_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(thicknessUnitId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_unit&subFlag=thicknessUnit&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getOrderQuantityUnit(rowCount,flag)
{
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		  document.getElementById("select_order_quantity_unit"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
			     var orderQuantityUnitId=document.getElementById("order_quantity_unit"+rowCount).value;
			    // alert("order quantity unit "+orderQuantityUnitId);
				 var typeDdl = document.getElementById("order_quantity_unit_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(orderQuantityUnitId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_unit&subFlag=orderQuantityUnit&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getCapacityUnit(rowCount,flag)
{
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		  document.getElementById("select_capacity_unit"+rowCount).innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
			     var capacityUnitId=document.getElementById("capacity_unit"+rowCount).value;
				 var typeDdl = document.getElementById("capacity_unit_select"+rowCount);
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(capacityUnitId==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_unit&subFlag=capacityUnit&rowCount="+rowCount;
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



function getClientForUpdateOperation(flag)
{
	//alert("calling getClientFor Update");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		  document.getElementById("update_select_client").innerHTML=xmlhttp.responseText;
		  //alert("calling getClientFor Update1");
		 if(flag==1){
				 
				 var typeDdl = document.getElementById("update_client_select");
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(clientIdForUpdate==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				 getProjectForUpdateOperation(flag);
			  }
		  
		
		  }
	    }
	 
	var outData="flag=get_client&subFlag=updateOperation";
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
var selectedClientIdForUpdateOperation;
function getProjectForUpdateOperation(flag)
{
	
	var e = document.getElementById("update_client_select");
	
	selectedClientIdForUpdateOperation=e.options[e.selectedIndex].value;
		
	if(selectedClientIdForUpdateOperation=="select"){
		  //alert("Please select Country");
		  //document.getElementById("bom_div").style.display="none";
		  document.getElementById("update_select_Project").innerHTML="Project: <select style=\"width:250px;margin:0 0 0 33px;\"><option>select</option></select>";
		  document.getElementById("update_select_element").innerHTML="Element: <select style=\"width:250px;\"><option>select</option></select>";
		 

		return;
	}
	


	
		
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		
		  document.getElementById("update_select_Project").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_element").innerHTML="Element: <select style=\"width:250px;\"><option>select</option></select>";
	      if(flag==1){
				 
				 var typeDdl = document.getElementById("update_Project_select");
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(ProjectIdForUpdate==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				 getElementForUpdateOperation(flag);
			  }
		
		
		 
	    
	  }
	  }

   
	var outData="flag=get_Project&subFlag=updateOperation&clientId="+selectedClientIdForUpdateOperation;
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




var selectedProjectIdForUpdateOperation;
function getElementForUpdateOperation(flag)
{
		
	var e = document.getElementById("update_Project_select");
   
	selectedProjectIdForUpdateOperation=e.options[e.selectedIndex].value;
	//alert("it is calling "+selectedProjectIdForUpdateOperation);
	if(selectedProjectIdForUpdateOperation=="select"){
		  //alert("Please select Country");
		  //document.getElementById("bom_div").style.display="none";
		  document.getElementById("update_select_element").innerHTML="Element: <select style=\"width:250px;\"><option>select</option></select>";
		 

		return;
	}
	


	
		
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		
		  document.getElementById("update_select_element").innerHTML=xmlhttp.responseText;
		 
		  if(flag==1){
				 
				 var typeDdl = document.getElementById("update_element_select");
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(ProjectElementIdForUpdate==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				// getBrandForUpdateOperation(flag);
			  }
		 
	    
	  }
	  }

   
	var outData="flag=get_element&subFlag=updateOperation&ProjectId="+selectedProjectIdForUpdateOperation;
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

//var selectedProjectIdForUpdateOperation;
function getBrandForUpdateOperation(flag)
{
		
 var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		
		  document.getElementById("update_select_brand").innerHTML=xmlhttp.responseText;
		 
		  if(flag==1){
				 
				 var typeDdl = document.getElementById("update_brand_select");
				 for (k = 0; k <typeDdl.options.length; k++) {
				    
				 	//alert("inside for comp cost type id is "+costTypeId+" cost Type Select id is "+typeDdl .options[k].value);
				    if(brandIdForUpdate==(typeDdl .options[k].value)){
				    	typeDdl.options[k].selected= true;
				    	//alert("inside if "+costTypeId);
				    }
				 }
				 
				 //getProjectForUpdateOperation(flag);
			  }
		 
	    
	  }
	  }

   
	var outData="flag=get_brand&subFlag=updateOperation";
  
       
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



function validate()
{  
	      var selectedClientId;
	      var selectedProjectId;
	      var selectedProjectElementId;
	      var selectedBrandId;
	      var bomCode;
	      var bomVersionNumber;
	      var bomDate;
	      
	      
	       
	       
	       if(document.getElementById("update_client_select").selectedIndex==0){
		       alert("Please Select Client");
               return false;	
		
	         }
	       else{
	    	  var e=document.getElementById("update_client_select");
	    	  selectedClientId=e.options[e.selectedIndex].value; 
	    	  //alert(selectedClientId);
	       }
	       
	       
	       
	       if(document.getElementById("update_Project_select").selectedIndex==0){
		       alert("Please Select Project");
               return false;	
		
	         }
	       else{
	    	  var e=document.getElementById("update_Project_select");
	    	  selectedProjectId=e.options[e.selectedIndex].value; 
	    	  //alert(selectedProjectId);
	       }
	       
	       
	       
	       
	       if(document.getElementById("update_element_select").selectedIndex==0){
		       alert("Please Select Project");
               return false;	
		
	         }
	       else{
	    	  var e=document.getElementById("update_element_select");
	    	  selectedProjectElementId=e.options[e.selectedIndex].value; 
	    	  //alert(selectedProjectElementId);
	       }
	       
	       
	       
	      /* if(document.getElementById("update_brand_select").selectedIndex==0){
		       alert("Please Select Brand");
               return false;	
		
	         }
	       else{
	    	  var e=document.getElementById("update_brand_select");
	    	  selectedBrandId=e.options[e.selectedIndex].value; 
	    	 // alert(selectedBrandId);
	       }
	       */
	       
	   	if (document.getElementById("bom_code_input").value == "") {
			
			bomCode=null;

		} else {
			bomCode = document.getElementById("bom_code_input").value;
			//alert(bomCode);
		}

		if (document.getElementById("bom_version_input").value == "") {
			bomVersionNumber=null;

		} else {
			bomVersionNumber = document.getElementById("bom_version_input").value;
			//alert(bomVersionNumber);
		}
	       
	       /*if(document.getElementById("bom_date_input").value==""){
		       alert("Please Enter Bom Date");
		       document.getElementById("bom_date_input").focus();
               return false;	
		
	         }
	       else{
	    	 bomDate = document.getElementById("bom_date_input").value;
	    	  //alert(bomDate);
	       }*/
	       
	       
	       //bom elements validation
	       

	          var materialId=new Array();
	          var heightUnitId=new Array();
	          var widthUnitId=new Array();
	          var thicknessUnitId=new Array();
	      	  var orderQuantityUnitId = new Array();
	          var capacityUnitId=new Array();
	          var processId=new Array();
	          
	          var elementSection=new Array();
	          var height=new Array();
	          var width=new Array();
	          var thickness=new Array();
	          var capacity=new Array();
	          var bomQuantity=new Array();
	          var orderQuantity=new Array();
	          
		      var j=0;
		      
		       
	       
	         for(var i=1;i<=rowCount;i++){
	          if(document.getElementById("element_section_input"+i)!=null){  
	        	  
	        	  var dimensionFlag = 0;

	  			if (document.getElementById("element_section_input" + i).value == "") {
	  				alert("Please Enter Element Section");
	  				document.getElementById("element_section_input" + i).focus();
	  				return false;
	  			}

	  			else {
	  				elementSection[j] = document
	  						.getElementById("element_section_input" + i).value;

	  				//alert("element section is "+elementSection[j]);

	  			}

	  			if (document.getElementById("material_select" + i).selectedIndex == 0) {
	  				alert("Please Select Material");
	  				return false;

	  			} else {
	  				var e = document.getElementById("material_select" + i);
	  				materialId[j] = Number(e.options[e.selectedIndex].value);

	  				// alert("material Id is "+materialId[j]);

	  			}

	  			if (document.getElementById("height_input" + i).value == "") {

	  				dimensionFlag++;
	  				// alert("Please Enter Height");
	  				// document.getElementById("height_input"+i).focus();
	  				//return false;	
	  			}

	  			else if (isNaN(document.getElementById("height_input" + i).value)) {
	  				alert("Please Enter Numeric Value For Height");
	  				document.getElementById("height_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document.getElementById("height_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Height");
	  				document.getElementById("height_input" + i).focus();
	  				return false;
	  			}

	  			// else{
	  			height[j] = Number(document.getElementById("height_input" + i).value);

	  			// alert("height  is "+height[j]);

	  			// }

	  			if ((document.getElementById("height_unit_select" + i).selectedIndex == 0)
	  					&& (document.getElementById("height_input" + i).value != "")) {
	  				alert("Please Select Height Unit");
	  				return false;

	  			}

	  			else {
	  				if (document.getElementById("height_unit_select" + i).selectedIndex != 0) {
	  					var e = document.getElementById("height_unit_select" + i);
	  					heightUnitId[j] = e.options[e.selectedIndex].value;
	  				} else {
	  					heightUnitId[j] = " ";

	  				}

	  				//alert("height Id is "+heightUnitId[j]);

	  			}

	  			if (document.getElementById("width_input" + i).value == "") {

	  				dimensionFlag++;
	  				// alert("Please Enter Width");
	  				//document.getElementById("width_input"+i).focus();
	  				//return false;	
	  			}

	  			else if (isNaN(document.getElementById("width_input" + i).value)) {
	  				alert("Please Enter Numeric Value For Width");
	  				document.getElementById("width_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document.getElementById("width_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Width");
	  				document.getElementById("width_input" + i).focus();
	  				return false;
	  			}

	  			// else{
	  			width[j] = Number(document.getElementById("width_input" + i).value);

	  			// alert("width  is "+width[j]);

	  			//}

	  			if ((document.getElementById("width_unit_select" + i).selectedIndex == 0)
	  					&& (document.getElementById("width_input" + i).value != "")) {
	  				alert("Please Select width Unit");
	  				return false;

	  			}

	  			else {
	  				if (document.getElementById("width_unit_select" + i).selectedIndex != 0) {
	  					var e = document.getElementById("width_unit_select" + i);
	  					widthUnitId[j] = e.options[e.selectedIndex].value;
	  				} else {
	  					widthUnitId[j] = " ";

	  				}
	  				//alert("width Id is "+widthUnitId[j]);

	  			}

	  			if (document.getElementById("thickness_input" + i).value == "") {

	  				dimensionFlag++;
	  				//alert("Please Enter Thickness");
	  				//document.getElementById("thickness_input"+i).focus();
	  				//return false;	
	  			}

	  			else if (isNaN(document.getElementById("thickness_input" + i).value)) {
	  				alert("Please Enter Numeric Value For Thickness");
	  				document.getElementById("thickness_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document.getElementById("thickness_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Thickness");
	  				document.getElementById("thickness_input" + i).focus();
	  				return false;
	  			}

	  			// else{
	  			thickness[j] = Number(document
	  					.getElementById("thickness_input" + i).value);

	  			// alert("thickness  is "+thickness[j]);

	  			//}

	  			if ((document.getElementById("thickness_unit_select" + i).selectedIndex == 0)
	  					&& (document.getElementById("thickness_input" + i).value != "")) {
	  				alert("Please Select Thickness Unit");
	  				return false;

	  			}

	  			else {
	  				if (document.getElementById("thickness_unit_select" + i).selectedIndex != 0) {
	  					var e = document
	  							.getElementById("thickness_unit_select" + i);
	  					thicknessUnitId[j] = e.options[e.selectedIndex].value;
	  				} else {
	  					thicknessUnitId[j] = " ";

	  				}

	  			}

	  			if (document.getElementById("capacity_input" + i).value == "") {

	  				dimensionFlag++;
	  				//alert("Please Enter Capacity");
	  				// document.getElementById("capacity_input"+i).focus();
	  				//return false;	
	  			}

	  			else if (isNaN(document.getElementById("capacity_input" + i).value)) {
	  				alert("Please Enter Numeric Value For Capacity");
	  				document.getElementById("capacity_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document.getElementById("capacity_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Capacity");
	  				document.getElementById("capacity_input" + i).focus();
	  				return false;
	  			}

	  			// else{
	  			capacity[j] = Number(document.getElementById("capacity_input" + i).value);

	  			// alert("capacity  is "+capacity[j]);

	  			// }

	  			if ((document.getElementById("capacity_unit_select" + i).selectedIndex == 0)
	  					&& (document.getElementById("capacity_input" + i).value != "")) {
	  				alert("Please Select Capacity Unit");
	  				return false;

	  			}

	  			else {
	  				if (document.getElementById("capacity_unit_select" + i).selectedIndex != 0) {
	  					var e = document.getElementById("capacity_unit_select" + i);
	  					capacityUnitId[j] = e.options[e.selectedIndex].value;
	  				} else {
	  					capacityUnitId[j] = " ";

	  				}

	  				//alert("capacity Id is "+capacityUnitId[j]);

	  			}

	  			if (dimensionFlag == 4) {
	  				alert("Atleast Enter One Of Height , Width ,Thickness or Capacity");
	  				return false;
	  			}

	  			if (document.getElementById("bom_quantity_input" + i).value == "") {

	  				alert("Please Enter Bom Quantity");
	  				document.getElementById("bom_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else if (isNaN(document.getElementById("bom_quantity_input" + i).value)) {
	  				alert("Please Enter Numeric Value For Bom Quantity");
	  				document.getElementById("bom_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document.getElementById("bom_quantity_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Bom Quantity");
	  				document.getElementById("bom_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document.getElementById("bom_quantity_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Bom Quantity");
	  				document.getElementById("bom_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else {
	  				bomQuantity[j] = Number(document
	  						.getElementById("bom_quantity_input" + i).value);

	  				// alert("bom_quantity  is "+bom_quantity[j]);

	  			}

	  			if (document.getElementById("order_quantity_input" + i).value == "") {

	  				alert("Please Enter Order Quantity");
	  				document.getElementById("order_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else if (isNaN(document.getElementById("order_quantity_input" + i).value)) {
	  				alert("Please Enter Numeric Value For Order Quantity");
	  				document.getElementById("order_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else if ((document.getElementById("order_quantity_input" + i).value)
	  					.indexOf(".") != -1) {
	  				alert("Decimal Value Not Allowed For Order Quantity");
	  				document.getElementById("order_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else if ((Number(document
	  					.getElementById("order_quantity_input" + i).value)) < 0) {
	  				alert("Negative Value Not Allowed For Order Quantity");
	  				document.getElementById("order_quantity_input" + i).focus();
	  				return false;
	  			}

	  			else {
	  				orderQuantity[j] = Number(document
	  						.getElementById("order_quantity_input" + i).value);

	  				// alert("order_quantity  is "+order_quantity[j]);

	  			}
	  			
	  			if ((document.getElementById("order_quantity_unit_select" + i).selectedIndex == 0)
					&& (document.getElementById("order_quantity_input" + i).value != "")) {
				alert("Please Select order quantity Unit");
				return false;

			}

			else {
				if (document.getElementById("order_quantity_unit_select" + i).selectedIndex != 0) {
					var e = document
							.getElementById("order_quantity_unit_select" + i);
					orderQuantityUnitId[j] = e.options[e.selectedIndex].value;
				} else {
					orderQuantityUnitId[j] = " ";

				}

			}

	  			if (document.getElementById("process_select" + i).selectedIndex == 0) {
					processId[j]=" ";

				}

				else {
					var e = document.getElementById("process_select" + i);
					processId[j] = Number(e.options[e.selectedIndex].value);

					//alert("process Id is "+processId[j]);

				}

	          
	          
	          j=j+1;
	          }
	         
	         }                                                                              
	        
	         
	         
updateBom(selectedClientId,selectedProjectId,selectedProjectElementId,selectedBrandId,
bomCode,bomVersionNumber,bomDate,materialId,heightUnitId,widthUnitId,thicknessUnitId,orderQuantityUnitId,
capacityUnitId,processId,elementSection,height,width,thickness,capacity,bomQuantity,orderQuantity);

}


function updateBom(selectedClientId,selectedProjectId,selectedProjectElementId,selectedBrandId,
		bomCode,bomVersionNumber,bomDate,materialId,heightUnitId,widthUnitId,thicknessUnitId,orderQuantityUnitId,
		capacityUnitId,processId,elementSection,height,width,thickness,capacity,bomQuantity,orderQuantity){
	
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		 alert("bom is updated successfully");
		 //window.location.reload();
		 
		  
		
		  }
	    };
	 
	var outData="flag=update_bom&bomId="+bomIdForUpdate+"&selectedClientId="+selectedClientId+"&selectedProjectId="+selectedProjectId+
	"&selectedProjectElementId="+selectedProjectElementId+"&selectedBrandId="+selectedBrandId+
	"&bomCode="+bomCode+"&bomVersionNumber="+bomVersionNumber+
	"&bomDate="+bomDate+"&materialId="+materialId+
	"&heightUnitId="+heightUnitId+"&widthUnitId="+widthUnitId+
	"&thicknessUnitId="+thicknessUnitId+"&orderQuantityUnitId="+orderQuantityUnitId+"&capacityUnitId="+capacityUnitId+
	"&processId="+processId+"&elementSection="+elementSection+
	"&height="+height+"&width="+width+
	"&thickness="+thickness+"&capacity="+capacity+
	"&bomQuantity="+bomQuantity+"&orderQuantity="+orderQuantity;
	
	//alert(outData);
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function deleteBom(bomId){
	//alert();
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		 
		 alert("Bom is Deleted successfully");
		 //window.location.reload();
		 if(document.getElementById("filter_element_select").selectedIndex!=0){
			 getBomByFilterOperation();
		
		 }
		 else{
			 getBomBySearchOperation();
			 
		 }
		
		  }
	    }
	 
	var outData="flag=delete_bom&bomId="+bomId;
	
	
	//alert(outData);
	xmlhttp.open("POST","SearchBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function datePicker(){

    new JsDatePick({

          useMode:2,

          target:"bom_date_input",//Id specified -àOnclick

          dateFormat:"%Y-%m-%d"

    });  

}

function enableDisableTextFieldsForBomSearch(){
	
	if(document.getElementById("search_bom_by_bom_code").checked==true){
		
		document.searchBomForm.searchBomByBomCode.disabled=false;
		document.searchBomForm.searchBomByProjectName.disabled=true;
	}
	
	else if(document.getElementById("search_bom_by_Project_name").checked=true){
		
		document.searchBomForm.searchBomByBomCode.disabled=true;
		document.searchBomForm.searchBomByProjectName.disabled=false;
	}
	
}


