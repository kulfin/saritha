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

function validateAddOperation(cityName)
{   
	//alert(cityName.value);
	var cityNameVal=cityName.value;
            	
	if(cityNameVal.trim()=="")
    {
        alert("Please Enter City Name");
        cityName.focus();
        return false;
	}
/*	var n = cityNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(cityNameVal.charAt(i))){
            alert("Enter only Alphabets");
            cityName.focus();
            return false;             
        }
    }*/

    setCity(cityNameVal);   	
 }



function validateUpdateOperation(cityId,cityName)
{   
	//alert("it is updateValidate");
	var cityNameVal=cityName.value;

	var cityIdVal=cityId.value;
	 
            	
		if(cityNameVal=="")
	    {
	        alert("Please Enter City Name");
	        cityName.focus();
	        return false;
		}
		
	/*	if(!cityNameVal.match(/^[a-zA-Z]+$/)){
			alert("Numbers and Special Characters are not allowed");
			cityName.focus();
			return false;
		}*/
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
           
      updateCity(cityIdVal,cityNameVal);   	
         
 }


 

function showUpdateDiv(cityId,cityName){
	 document.getElementById("city_add").style.display="none";
	 document.getElementById("city_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	document.getElementById("cityName").value=cityName;
	document.getElementById("cityId").value=cityId;
}

function showAddDiv(){
	document.getElementById("city_update").style.display="none";
	document.getElementById("city_add").style.display="block";
	


	 

	
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
	xmlhttp.open("POST","ManageCityController.jsp",true);
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
	document.getElementById("city_div").style.display="none";	
	var e = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=e.selectedIndex;
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCountryIdForFilterOperation=="select"){
		  alert("Please select Country");
		  document.getElementById("city_div").style.display="none";
		  document.getElementById("filter_select_region").innerHTML="Region: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_state").innerHTML="State: <select style=\"width:110px;\"><option>select</option></select>";
		 

		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_country_select");
	    selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCountryIdForUpdateOperation=="select"){
			  alert("Please select Country");
			  //document.getElementById("update_city_div").style.display="none";
			  document.getElementById("update_select_region").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
			 
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
		
		  
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		 
		 
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
       
	xmlhttp.open("POST","ManageCityController.jsp",true);
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
	document.getElementById("city_div").style.display="none";		
	var e = document.getElementById("filter_region_select");
    selectedRegionIndexForFilterOperation=e.selectedIndex;
	selectedRegionIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedRegionIdForFilterOperation=="select"){
		  alert("Please select Region");
		  document.getElementById("city_div").style.display="none";
		  document.getElementById("filter_select_state").innerHTML="State: <select style=\"width:110px;\"><option>select</option></select>";
		 
		  
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
			 // document.getElementById("update_city_div").style.display="none";
			  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
			  
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
		 
		
	    }

	  else if(subFlag=="updateOperation"){
		  //alert("it is update state");
		  document.getElementById("update_select_state").innerHTML=xmlhttp.responseText;
		  
		 
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
       
	xmlhttp.open("POST","ManageCityController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


var selectedStateIdForFilterOperation;
var selectedStateIndexForFilterOperation;
var selectedStateIdForUpdateOperation;
var selectedStateIndexForUpdateOperation;
function getCity(subFlag)
{
	document.getElementById("addCityName").value = "";
	if(subFlag=="filterOperation"){
	var e = document.getElementById("filter_state_select");
	selectedStateIndexForFilterOperation=e.selectedIndex;
	selectedStateIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedStateIdForFilterOperation=="select"){
		alert("Please select State");
		document.getElementById("city_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_state_select");
		selectedStateIndexForUpdateOperation=e.selectedIndex;
		selectedStateIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedStateIdForUpdateOperation=="select"){
			alert("Please select State");
			//document.getElementById("city_div").style.display="none";
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
		  document.getElementById("city_div").style.display="block";
		  document.getElementById("city_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_city&stateId="+selectedStateIdForFilterOperation;
	xmlhttp.open("POST","ManageCityController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setCity(cityName)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		  {
			  alert("Data Already Exists");
			  document.getElementById("addCityName").value = "";
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
		  {
			  alert("Problem Occured Please try again later.");
		  }
		  getCity();
	    //window.location.reload();
		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_city&countryId="+selectedCountryIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation+"&stateId="+selectedStateIdForFilterOperation+"&cityName="+cityName;
	xmlhttp.open("POST","ManageCityController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteCity(tableID) {
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
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
        		  {
        			  alert("Data deleted Successfully");
        		  }
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
        		  {
        			  alert("Problem Occured Please try again later.");
        		  }
        		  getCity();
        	    //window.location.reload();
        		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  }
        	var outData="flag=delete_city&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageCityController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateCity(cityId,cityName){
	//alert("script update city calling"+cityId+cityName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" state "+selectedStateId+" update "+updateSelectedStateId+" city "+selectedCityId+" update "+updateSelectedCityId+" city "+selectedCityId+" update "+updateSelectedCityId);
		 if((selectedCountryIdForFilterOperation==selectedCountryIdForUpdateOperation)&&(selectedRegionIdForFilterOperation==selectedRegionIdForUpdateOperation)&&(selectedStateIdForFilterOperation==selectedStateIdForUpdateOperation)){

			 // alert("it is if");
			  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
	   		  {
	   			  alert("Data updated Successfully");
	   		  }
	   		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
	   		  {
	   			  alert("Problem Occured Please try again later.");
	   		  }
			 document.getElementById("city_add").style.display="block";
			 document.getElementById("city_update").style.display="none";
         			 getCity('filterOperation');
		  
		  }
		 
		 else{

			//  alert("it is else");
			 window.location.reload();
		 }
	}
	}


	flag="update_city";
	
	var outData="flag="+flag+"&countryId="+selectedCountryIdForUpdateOperation+"&regionId="+selectedRegionIdForUpdateOperation+"&stateId="+selectedStateIdForUpdateOperation+"&cityId="+cityId+"&cityName="+cityName; 
	//alert("script update city calling out data is "+outData);
	xmlhttp.open("POST","ManageCityController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    