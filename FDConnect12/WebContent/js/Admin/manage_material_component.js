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
	


//validate
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}

function validateAddOperation(componentName)
{   
	var componentNameVal=componentName.value;
	 
	if(componentNameVal.trim()=="")
    {
        alert("Please Enter Component Name");
        componentName.focus();
        return false;
	}
	
	var n =componentNameVal.toString().length;    
	setComponent(componentNameVal);
}


function validateUpdateOperation(componentId,componentName)
{   	
	var componentNameVal=componentName.value;
	var componentIdVal=componentId.value;
            	
	if(componentNameVal=="")
    {
        alert("Please Enter Component Name");
        componentName.focus();
        return false;
	}
	
	updateComponent(componentIdVal, componentNameVal);         
}

function showUpdateDiv(componentId,componentName){
	 document.getElementById("component_add").style.display="none";
	 document.getElementById("component_update").style.display="block";

	 var flag=0;
	 
	// getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	document.getElementById("componentName1").value=componentName;	
	document.getElementById("componentId").value=componentId;
	
}

function showAddDiv(){
	document.getElementById("component_update").style.display="none";
	document.getElementById("component_add").style.display="block";
}


//Get Material Group	
/*
function getCountry(subFlag,status)
{
	//alert('');
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation")
		  document.getElementById("filter_select_country").innerHTML=xmlhttp.responseText;
		  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_country").innerHTML=xmlhttp.responseText;
		  if(status==0){
			 var e = document.getElementById("update_country_select");
			 e.selectedIndex=selectedCountryIndexForFilterOperation;
			 getRegion(subFlag,0);
		  }
		  }
	    }
	  };
	var outData="flag=get_country&subFlag="+subFlag;
	xmlhttp.open("POST","ManageMaterialComponentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
var selectedCountryIdForFilterOperation;
var selectedCountryIndexForFilterOperation;
var selectedCountryIdForUpdateOperation;
var selectedCountryIndexForUpdateOperation;
*/
function getComponent()
{
	/*if(subFlag=="updateOperation"){		
		var e = document.getElementById("update_country_select");
		selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCountryIdForUpdateOperation=="select"){
			alert("Please select Country");
			//document.getElementById("country_div").style.display="none";
			return;
		}
		return;
		}*/
	
    var xmlhttp=getXMLHttpRequestObject();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    { 
		  document.getElementById("component_div").style.display="block";
		  document.getElementById("component_detail").innerHTML=xmlhttp.responseText;
		  
		  //document.getElementById("update_country_select").value="";
		  document.getElementById("componentName").value = "" ;
		  document.getElementById("componentName1").value = "" ;
		  
	    }
	};
	
	var outData="flag=get_component";
	xmlhttp.open("POST","ManageMaterialComponentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setComponent(componentName)
{
//	alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();
	xmlhttp.onreadystatechange=function(){
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	  {
//		 alert('status' + xmlhttp.responseText.replace(/^\s+|\s+$/g, ''));
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		 {
			alert("Data Already Exists.");
			document.getElementById("componentName").focus();
			return false;
		 }
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
		 {
//			alert("Component Added successfully"); 
		 }
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -1)
		 {
			alert("Problem occured.Please try again later."); 
		 }
		 document.getElementById("componentName").value = '';
		 document.getElementById("componentName").focus();
		 getComponent();	    
	   }	  
	  };
	var outData="flag=set_component&componentName="+componentName;
	xmlhttp.open("POST","ManageMaterialComponentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteComponent(tableID) {
	//alert("it is calling and table id is "+tableID);
	try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var selectedValues=new Array();
        var j=0;
        for(var i=1; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            
            if(null != chkbox && true == chkbox.checked) {
            	 selectedValues[j]=chkbox.value;
            	 j++;
            }
        }
           
    }catch(e) {
        alert(e);
    }
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			getComponent();	    
		}
	};
	
	var outData="flag=delete_region&selectedValues="+selectedValues;
	xmlhttp.open("POST","ManageMaterialComponentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}



function updateComponent(componentId,componentName){
	//alert('in update Region');
	//alert("script update region calling"+regionId+regionName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){		 
			getComponent();
		}
	};

	flag="update_component";
	
	var outData="flag="+flag+"&componentId="+componentId+"&componentName="+componentName; 
	
	xmlhttp.open("POST","ManageMaterialComponentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);	
}
        
    