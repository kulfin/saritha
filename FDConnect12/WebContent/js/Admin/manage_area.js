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
function isdigit(c){
	return(c >= '0' && c<='9');
	}

function validateAddOperation(areaName,zipCode)
{   
	var areaNameVal=areaName.value;
	var zipCodeVal=zipCode.value;
	 
            	
		if(areaNameVal.trim()=="")
		{
			alert("Please Enter Area Name");
			areaName.focus();
			return false;
			
		}
		var n = areaNameVal.toString().length;

		/*for(var i = 0; i < n; i++){
			if (! isChar(areaNameVal.charAt(i))){
				alert("Enter only Alphabets for Area Name.");
				areaName.focus();
				return false;
			}
		}*/
		

		if(zipCodeVal.trim()=="")
		{
			alert("Please Enter Zip Code");
			zipCode.focus();
			return false;
			
		}
		var n = zipCodeVal.toString().length;

		for(var i = 0; i < n; i++){
			if (! isdigit(zipCodeVal.charAt(i))){
				alert("Enter only Numerics for Zip Code");
				zipCode.focus();
				return false;
			}
		}
		setArea(areaNameVal,zipCodeVal);
}


function validateUpdateOperation(areaId,areaName,zipCode)
{   
	//alert("it is updateValidate");
	var areaNameVal=areaName.value;
	var zipCodeVal=zipCode.value;
	var areaIdVal=areaId.value;
            	
	if(areaNameVal=="")
    {
        alert("Please Enter Area Name");
        areaName.focus();
        return false;
	}
	
	if(zipCodeVal=="")
    {
        alert("Please Enter Zip Code");
        zipCode.focus();
        return false;
	}
	if(document.getElementById("update_country_select").selectedIndex==0){
		alert("Please Select Country");
        //zipCode.focus();
        return false;	
	}
	
	if(document.getElementById("update_region_select").selectedIndex==0){
		alert("Please Select Region");
        //zipCode.focus();
        return false;	
	}
	
	if(document.getElementById("update_state_select").selectedIndex==0){
		alert("Please Select State");
        //zipCode.focus();
        return false;	
	}
	if(document.getElementById("update_city_select").selectedIndex==0){
		alert("Please Select City");
        //zipCode.focus();
        return false;	
	}
	
	if(document.getElementById("update_town_select").selectedIndex==0){
		alert("Please Select Town");
        //zipCode.focus();
        return false;	
	}
            	
      updateArea(areaIdVal,areaNameVal,zipCodeVal);   	
         
 }


 

function showUpdateDiv(areaId,areaName,zipCode){
	 document.getElementById("area_add").style.display="none";
	 document.getElementById("area_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	 
	 
	document.getElementById("areaName").value=areaName;
	document.getElementById("zipCode").value=zipCode;
	document.getElementById("areaId").value=areaId;
	 

	
}

function showAddDiv(){
	document.getElementById("area_update").style.display="none";
	document.getElementById("area_add").style.display="block";
	


	 

	
}


//Get Material Group	
function getCountry(subFlag,status)
{
	
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
	  }
	var outData="flag=get_country&subFlag="+subFlag;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
var selectedCountryIdForFilterOperation;
var selectedCountryIndexForFilterOperation;
var selectedCountryIdForUpdateOperation;
var selectedCountryIndexForUpdateOperation;
function getRegion(subFlag,status)
{
	if(subFlag=="filterOperation"){
	document.getElementById("area_div").style.display="none";	
	var e = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=e.selectedIndex;
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCountryIdForFilterOperation=="select"){
		  alert("Please select Country");
		  document.getElementById("area_div").style.display="none";
		  document.getElementById("filter_select_region").innerHTML="Region: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_state").innerHTML="State: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_country_select");
	    selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCountryIdForUpdateOperation=="select"){
			  alert("Please select Country");
			  //document.getElementById("update_area_div").style.display="none";
			  document.getElementById("update_select_region").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		  document.getElementById("filter_select_region").innerHTML=xmlhttp.responseText;
		  document.getElementById("filter_select_state").innerHTML="State: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  if(status==0){
				 var e = document.getElementById("update_region_select");
				 e.selectedIndex=selectedRegionIndexForFilterOperation;
				 getState(subFlag,0);
			  }
	    }
    
	  }
	  }

    if(subFlag=="filterOperation")
	var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




var selectedRegionIdForFilterOperation;
var selectedRegionIndexForFilterOperation;
var selectedRegionIdForUpdateOperation;
var selectedRegionIndexForUpdateOperation;
function getState(subFlag,status)
{
	
	if(subFlag=="filterOperation"){
	document.getElementById("area_div").style.display="none";		
	var e = document.getElementById("filter_region_select");
    selectedRegionIndexForFilterOperation=e.selectedIndex;
	selectedRegionIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedRegionIdForFilterOperation=="select"){
		  alert("Please select Region");
		  document.getElementById("area_div").style.display="none";
		  document.getElementById("filter_select_state").innerHTML="State: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
		return;
	}
	}


	else if(subFlag=="updateOperation"){
		//alert("it is update state up");
		var e = document.getElementById("update_region_select");
	    selectedRegionIndexForUpdateOperation=e.selectedIndex;
		selectedRegionIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedRegionIdForUpdateOperation=="select"){
			  alert("Please select Region");
			 // document.getElementById("update_area_div").style.display="none";
			  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		  document.getElementById("filter_select_state").innerHTML=xmlhttp.responseText;
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
	    }

	  else if(subFlag=="updateOperation"){
		  //alert("it is update state");
		  document.getElementById("update_select_state").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  if(status==0){
				 var e = document.getElementById("update_state_select");
				 e.selectedIndex=selectedStateIndexForFilterOperation;
				 getCity(subFlag,0);
			  }
		    }
    
	  }
	  }

    if(subFlag=="filterOperation")
	var outData="flag=get_state&subFlag="+subFlag+"&regionId="+selectedRegionIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_state&subFlag="+subFlag+"&regionId="+selectedRegionIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



var selectedStateIdForFilterOperation;
var selectedStateIndexForFilterOperation;
var selectedStateIdForUpdateOperation;
var selectedStateIndexForUpdateOperation;
function getCity(subFlag,status)
{
	if(subFlag=="filterOperation"){
    document.getElementById("area_div").style.display="none";		
	var e = document.getElementById("filter_state_select");
    selectedStateIndexForFilterOperation=e.selectedIndex;
	selectedStateIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedStateIdForFilterOperation=="select"){
		  alert("Please select State");
		  document.getElementById("area_div").style.display="none";
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_state_select");
	    selectedStateIndexForUpdateOperation=e.selectedIndex;
		selectedStateIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedStateIdForUpdateOperation=="select"){
			  alert("Please select State");
			 // document.getElementById("update_area_div").style.display="none";
			  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		  document.getElementById("filter_select_city").innerHTML=xmlhttp.responseText;
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_city").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  if(status==0){
				 var e = document.getElementById("update_city_select");
				 e.selectedIndex=selectedCityIndexForFilterOperation;
				 getTown(subFlag,0);
			  }
		    }
    
	  }
	  }

    if(subFlag=="filterOperation")
	var outData="flag=get_city&subFlag="+subFlag+"&stateId="+selectedStateIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_city&subFlag="+subFlag+"&stateId="+selectedStateIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




var selectedCityIdForFilterOperation;
var selectedCityIndexForFilterOperation;
var selectedCityIdForUpdateOperation;
var selectedCityIndexForUpdateOperation;
function getTown(subFlag,status)
{
	
	if(subFlag=="filterOperation"){
	document.getElementById("area_div").style.display="none";		
	var e = document.getElementById("filter_city_select");
	selectedCityIndexForFilterOperation=e.selectedIndex;
	selectedCityIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCityIdForFilterOperation=="select"){
		  alert("Please select City");
		  document.getElementById("area_div").style.display="none";
		  document.getElementById("filter_select_town").innerHTML="Town: <select style=\"width:110px;\"><option>select</option></select>";
		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_city_select");
	    selectedCityIndexForUpdateOperation=e.selectedIndex;
		selectedCityIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCityIdForUpdateOperation=="select"){
			  alert("Please select City");
			  //document.getElementById("update_area_div").style.display="none";
			  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		   document.getElementById("filter_select_town").innerHTML=xmlhttp.responseText;
		
		 
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_town").innerHTML=xmlhttp.responseText;
		  if(status==0){
				 var e = document.getElementById("update_town_select");
				 e.selectedIndex=selectedTownIndexForFilterOperation;
				 getArea(subFlag);
			  }
	    }
    
	  }
	  }

    if(subFlag=="filterOperation"){
 
	var outData="flag=get_town&subFlag="+subFlag+"&cityId="+selectedCityIdForFilterOperation;}
    else if(subFlag=="updateOperation")
    var outData="flag=get_town&subFlag="+subFlag+"&cityId="+selectedCityIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


var selectedTownIdForFilterOperation;
var selectedTownIndexForFilterOperation;
var selectedTownIdForUpdateOperation;
var selectedTownIndexForUpdateOperation;
function getArea(subFlag)
{
	if(subFlag=="filterOperation"){
	var e = document.getElementById("filter_town_select");
	selectedTownIndexForFilterOperation=e.selectedIndex;
	selectedTownIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedTownIdForFilterOperation=="select"){
		alert("Please select Town");
		document.getElementById("area_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_town_select");
		selectedTownIndexForUpdateOperation=e.selectedIndex;
		selectedTownIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedTownIdForUpdateOperation=="select"){
			alert("Please select Town");
			//document.getElementById("area_div").style.display="none";
			return;
		}
		return;
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		  document.getElementById("area_div").style.display="block";
		  document.getElementById("area_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  };
	var outData="flag=get_area&townId="+selectedTownIdForFilterOperation;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setArea(areaName,zipCode)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		 {
			alert("Data Already Exists."); 
		 }
		 $('#addAreaName').val('');
		 $('#addZipCode').val('');
		 getArea();
	    //window.location.reload();
		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_area&countryId="+selectedCountryIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation+"&stateId="+selectedStateIdForFilterOperation+"&cityId="+selectedCityIdForFilterOperation+"&townId="+selectedTownIdForFilterOperation+"&areaName="+areaName+"&zipCode="+zipCode;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteArea(tableID) {
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

        	xmlhttp.onreadystatechange=function()
        	  {
        	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
        	    {
        		  getArea();
        	    //window.location.reload();
        		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  }
        	var outData="flag=delete_area&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageAreaController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateArea(areaId,areaName,zipCode){
	//alert("script update area calling"+areaId+areaName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" state "+selectedStateId+" update "+updateSelectedStateId+" city "+selectedCityId+" update "+updateSelectedCityId+" town "+selectedTownId+" update "+updateSelectedTownId);
		 
		 if((selectedCountryIdForFilterOperation==selectedCountryIdForUpdateOperation)&&(selectedRegionIdForFilterOperation==selectedRegionIdForUpdateOperation)&&(selectedStateIdForFilterOperation==selectedStateIdForUpdateOperation)&&(selectedCityIdForFilterOperation==selectedCityIdForUpdateOperation)&&(selectedTownIdForFilterOperation==selectedTownIdForUpdateOperation)){
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
			 {
				alert("Data Already Exists."); 
			 }
			 
			 // alert("it is if");
			 document.getElementById("area_add").style.display="block";
			 document.getElementById("area_update").style.display="none";
         	getArea('filterOperation');
		  
		  }
		 else{

			//  alert("it is else");
			 window.location.reload();
		 }
	}
	};
	flag="update_area";
	
	var outData="flag="+flag+"&countryId="+selectedCountryIdForUpdateOperation+"&regionId="+selectedRegionIdForUpdateOperation+"&stateId="+selectedStateIdForUpdateOperation+"&cityId="+selectedCityIdForUpdateOperation+"&townId="+selectedTownIdForUpdateOperation+"&areaId="+areaId+"&areaName="+areaName+"&zipCode="+zipCode; 
	//alert("script update area calling out data is "+outData);
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}
        
    