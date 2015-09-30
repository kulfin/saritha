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
	xmlhttp.open("POST","ViewBomController.jsp",true);
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
	  };

   
	var outData="flag=get_Project&subFlag=filterOperation&clientId="+selectedClientIdForFilterOperation;
  
       
	xmlhttp.open("POST","ViewBomController.jsp",true);
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
	  };

   
	var outData="flag=get_element&subFlag=filterOperation&ProjectId="+selectedProjectIdForFilterOperation;
  
       
	xmlhttp.open("POST","ViewBomController.jsp",true);
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
		  
		return;
	}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  document.getElementById("bom_div").style.display="block";
		  document.getElementById("bom_detail_div_label").style.display="none";
		  document.getElementById("bom_detail_div").style.display="none";
		  
		  document.getElementById("consolidation_div_label").style.display="none";
		  document.getElementById("consolidation_div").style.display="none";
		  
		  
		  document.getElementById("bom_general_detail_div").innerHTML=xmlhttp.responseText;
		 
	  }
	  };

	document.getElementById("view_bom_by_bom_code").checked=true;
	document.viewBomForm.viewBomByBomCode.disabled=false;
	document.viewBomForm.viewBomByBomCode.value="";
	document.viewBomForm.viewBomByProjectName.value="";
	document.viewBomForm.viewBomByProjectName.disabled=true;
	
	var outData="flag=get_bom_by_filter&ProjectElementId="+selectedProjectElementIdForFilterOperation;
  
       
	xmlhttp.open("POST","ViewBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getBomByViewOperation()
{
	
	var radioButtonValue = document.getElementById("view_bom_by_bom_code").checked;
	var subFlag;
	var viewText;
	viewText= document.viewBomForm.viewBomByProjectName.value;
	if(radioButtonValue==true){
		subFlag="view_bom_by_bom_code";
	    viewText= document.viewBomForm.viewBomByBomCode.value;
	    if(viewText==""){
	    	alert("Please enter some text for view");
	    	document.viewBomForm.viewBomByBomCode.focus();
	    	return;
	    }
	}
	else if(radioButtonValue==false){
		subFlag="view_bom_by_Project_name";
		viewText= document.viewBomForm.viewBomByProjectName.value;
		
		  if(viewText==""){
		    	alert("Please enter some text for view");
		    	document.viewBomForm.viewBomByProjectName.focus();
		    	return;
		    }
	}

	
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  document.getElementById("bom_div").style.display="block";
		  
		  document.getElementById("bom_detail_div_label").style.display="none";
		  document.getElementById("bom_detail_div").style.display="none";
		  
		  document.getElementById("consolidation_div_label").style.display="none";
		  document.getElementById("consolidation_div").style.display="none";
		  
		  document.getElementById("bom_general_detail_div").innerHTML=xmlhttp.responseText;
		 
		
		 
	    
	  }
	  }
	document.getElementById("filter_client_select").selectedIndex=0;
    document.getElementById("filter_select_Project").innerHTML=""+
	 "Project: <select id=\"filter_Project_select\"  style=\"width:250px;\"><option>select</option></select>";
	 
	 document.getElementById("filter_select_element").innerHTML=""+
	 "Element: <select id=\"filter_element_select\"  style=\"width:250px;\"><option>select</option></select>";	

   
	var outData="flag=get_bom_by_view&subFlag="+subFlag+"&viewText="+viewText;
  
       
	xmlhttp.open("POST","ViewBomController.jsp",true);
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
		  
		  document.getElementById("bom_detail_div_label").style.display="block";
		  document.getElementById("bom_detail_div").style.display="block";
		  document.getElementById("bom_detail_div").innerHTML=xmlhttp.responseText;
	        
		  consolidateBom(bomId);
	  }
	  }

   
	var outData="flag=edit_bom&bomId="+bomId;
  
       
	xmlhttp.open("POST","ViewBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);


}



var bomIdForUpdate;
var clientIdForUpdate;
var ProjectIdForUpdate;
var ProjectElementIdForUpdate;
var brandIdForUpdate;
function consolidateBom(bomId){

	
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		
		  
		  document.getElementById("consolidation_div_label").style.display="block";
		  document.getElementById("consolidation_div").style.display="block";
		  document.getElementById("consolidation_div").innerHTML=xmlhttp.responseText;
	
	  }
	  }

   
	var outData="flag=get_bom_element_data&bomId="+bomId;
  
       
	xmlhttp.open("POST","ViewBomController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);


}


