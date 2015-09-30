var DATA_EXISTS = "Data Already Exists";
var PROBLEM_OCCURED = "Problem Occured Please try again later....!";
var DELETED = "Data deleted successfully";
var UPDATED = "Data Updated successfully";

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
	






function validateUpdateOperation(townId,townName,courierAddress,pinCode,telNumber,faxNumber,tinNumber,panNumber)
{   
	//alert("it is updateValidate");
	var townNameVal=townName.value;
	var courierAddressVal = courierAddress.value;
	var pinCodeVal = pinCode.value;
	var telNumberVal = telNumber.value;
	var faxNumberVal = faxNumber.value;
	var tinNumberVal = tinNumber.value;
	var panNumberVal = panNumber.value;
	var townIdVal=townId.value;
            	
    	if(townNameVal=="")
        {
            alert("Please Enter Courier Name");
            townName.focus();
            return false;
    	}
    	if(courierAddressVal=="")
        {
            alert("Please Enter Courier Address");
            courierAddress.focus();
            return false;
    	}
    	if(pinCodeVal=="")
        {
            alert("Please Enter Pin Code");
            pinCode.focus();
            return false;
    	}
    	if(telNumberVal=="")
        {
            alert("Please Enter Telephone Number");
            telNumber.focus();
            return false;
    	}
    	if(faxNumberVal=="")
        {
            alert("Please Enter Fax Number");
            faxNumber.focus();
            return false;
    	}
    	if(tinNumberVal=="")
        {
            alert("Please Enter Tin Number");
            tinNumber.focus();
            return false;
    	}
    	if(panNumberVal=="")
        {
            alert("Please Enter Pan Number");
            panNumber.focus();
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
    	//townId,townName,courierAddress,pinCode,telNumber,faxNumber,tinNumber,panNumber
      updateTown(townIdVal,townNameVal,courierAddressVal,pinCodeVal,telNumberVal,faxNumberVal,tinNumberVal,panNumberVal);   	
         
 }

function getUpdateCountry(countryId,regionId,stateId,cityId){
	
	//alert('getUpdateCountry');
	var subFlag="updateOperation";
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  document.getElementById("update_select_country").innerHTML=xmlhttp.responseText;  
		  var element_u = document.getElementById('update_country_select');
			 for (i = 0; i <element_u.options.length; i++) {
				//alert(element_u.options[i].value.trim());
			    if(countryId.trim().toUpperCase()==(element_u.options[i].value.trim())){
				    	element_u.options[i].selected= true;
			    }
		}
		 
			 getUpdateRegion(regionId,stateId,cityId);	 
			 
			 
	    }
	  };
	var outData="flag=get_country&subFlag="+subFlag;
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function getUpdateRegion(regionId,stateId,cityId){
	
	//alert('getUpdateCountry');
	var subFlag="updateOperation";
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  alert(xmlhttp.responseText);
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;  
		  
		  var element_u = document.getElementById('update_region_select');
			 for (i = 0; i <element_u.options.length; i++) {
				//alert(element_u.options[i].value.trim());
			    if(regionId.trim().toUpperCase()==(element_u.options[i].value.trim())){
				    	element_u.options[i].selected= true;
			    }
		}
			 
			 getUpdateState(stateId,cityId); 
		  
	    }
	  };
	  
	
	var outData="flag=get_region&subFlag="+subFlag+"&countryId="+document.getElementById("update_country_select").value;
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getUpdateState(stateId,cityId){
	
	alert('getUpdateState');
	var subFlag="updateOperation";
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  alert(xmlhttp.responseText);
		  document.getElementById("update_select_state").innerHTML=xmlhttp.responseText;  
		  
		  var element_u = document.getElementById('update_state_select');
			 for (i = 0; i <element_u.options.length; i++) {
				alert(element_u.options[i].value.trim());
			    if(stateId.trim().toUpperCase()==(element_u.options[i].value.trim())){
				    	element_u.options[i].selected= true;
			    }
		}
			 
		getUpdateCity(cityId);
		  
	    }
	  };
	  
	  var outData="flag=get_state&subFlag="+subFlag+"&countryId="+document.getElementById("update_country_select").value+
		"&regionId="+document.getElementById("update_region_select").value;
		xmlhttp.open("POST","ManageCourierController.jsp",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(outData);
}	  
	  
 function getUpdateCity(cityId){
			
			//alert('getUpdateCountry');
			var subFlag="updateOperation";
			var xmlhttp=getXMLHttpRequestObject();

			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
				  alert(xmlhttp.responseText);
				  document.getElementById("update_select_city").innerHTML=xmlhttp.responseText;  
				  
				  var element_u = document.getElementById('update_city_select');
					 for (i = 0; i <element_u.options.length; i++) {
						
					    if(cityId.trim().toUpperCase()==(element_u.options[i].value.trim())){
						    	element_u.options[i].selected= true;
					    }
				}
			    }
};
	  
	
	var outData="flag=get_city&subFlag="+subFlag+"&countryId="+document.getElementById("update_country_select").value+
	"&regionId="+document.getElementById("update_region_select").value+"&stateId="+document.getElementById("update_state_select").value;
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function showUpdateDiv(townId,townName,courierAddress,pinCode,telPhone,faxNumber,tinNumber,panNumber,
		cityId,stateId,regionId,countryId){
	 document.getElementById("town_add").style.display="none";
	 document.getElementById("town_update").style.display="block";
	
	 getUpdateCountry(countryId,regionId,stateId,cityId);
	
	 //getUpdateState(stateId);
	 //getUpdateCity(cityId);
	 
	 
	 var flag=0;
	 //getCountry('filterOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	document.getElementById("townName").value=townName;
	document.getElementById("townId").value=townId;
	document.getElementById("upcourierAddress").value=courierAddress;
	document.getElementById("uppinCode").value=pinCode;
	document.getElementById("uptelNumber").value=telPhone;
	document.getElementById("upfaxNumber").value=faxNumber;
	document.getElementById("uptinNumber").value=tinNumber;
	document.getElementById("uppanNumber").value=panNumber;
		
}

function showAddDiv(){
	document.getElementById("town_update").style.display="none";
	document.getElementById("town_add").style.display="block";
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
		  { document.getElementById("filter_select_country").innerHTML=xmlhttp.responseText;
		  	//document.getElementById("update_select_country").innerHTML=xmlhttp.responseText;
		  } else if(subFlag=="updateOperation"){
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
	xmlhttp.open("POST","ManageCourierController.jsp",true);
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
	//document.getElementById("town_div").style.display="none";	
	var e = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=e.selectedIndex;
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCountryIdForFilterOperation=="select"){
		  alert("Please select Country");
		  //document.getElementById("town_div").style.display="none";
		  document.getElementById("filter_select_region").innerHTML="<select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_state").innerHTML="<select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="<select style=\"width:110px;\"><option>select</option></select>";

		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_country_select");
	    selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCountryIdForUpdateOperation=="select"){
			  alert("Please select Country");
			  //document.getElementById("update_town_div").style.display="none";
			  document.getElementById("update_select_region").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
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
		  document.getElementById("filter_select_state").innerHTML="<select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="<select style=\"width:110px;\"><option>select</option></select>";
		  
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		 
		  if(status==0){
				 var e = document.getElementById("update_region_select");
				 e.selectedIndex=selectedRegionIndexForFilterOperation;
				 getState(subFlag,0);
			  }
	    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageCourierController.jsp",true);
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
	//document.getElementById("town_div").style.display="none";		
	var e = document.getElementById("filter_region_select");
    selectedRegionIndexForFilterOperation=e.selectedIndex;
	selectedRegionIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedRegionIdForFilterOperation=="select"){
		  alert("Please select Region");
		  //document.getElementById("town_div").style.display="none";
		  document.getElementById("filter_select_state").innerHTML="State: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		  
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
			 // document.getElementById("update_town_div").style.display="none";
			  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  
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
		  document.getElementById("filter_select_city").innerHTML="<select style=\"width:110px;\"><option>select</option></select>";
		
	    }

	  else if(subFlag=="updateOperation"){
		  //alert("it is update state");
		  document.getElementById("update_select_state").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		 
		  if(status==0){
				 var e = document.getElementById("update_state_select");
				 e.selectedIndex=selectedStateIndexForFilterOperation;
				 getCity(subFlag,0);
			  }
		    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_state&subFlag="+subFlag+"&regionId="+selectedRegionIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_state&subFlag="+subFlag+"&regionId="+selectedRegionIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

//validate
function validateAddOperation(townName,courierAddress,pinCode,telNumber,faxNumber,tinNumber,panNumber)
{  // alert("in add");
	var townNameVal=townName.value;
	var courierAddressVal = courierAddress.value;
	var pinCodeVal = pinCode.value;
	var telNumberVal = telNumber.value;
	var faxNumberVal = faxNumber.value;
	var tinNumberVal = tinNumber.value;
	var panNumberVal = panNumber.value;
	if(townNameVal=="")
    {
        alert("Please Enter Courier Name");
        townName.focus();
        return false;
	}
	if(courierAddressVal=="")
    {
        alert("Please Enter Courier Address");
        courierAddress.focus();
        return false;
	}
	if(pinCodeVal=="")
    {
        alert("Please Enter PinCode");
        pinCode.focus();
        return false;
	}
	if(telNumberVal=="")
    {
        alert("Please Enter Telephone Number");
        telNumber.focus();
        return false;
	}
	if(faxNumberVal=="")
    {
        alert("Please Enter Fax Number");
        faxNumber.focus();
        return false;
	}
	if(tinNumberVal=="")
    {
        alert("Please Enter Tin Number");
        tinNumber.focus();
        return false;
	}
	if(panNumberVal=="")
    {
        alert("Please Enter Pan Number");
        panNumber.focus();
        return false;
	}
	
	var e = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=e.selectedIndex;
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCountryIdForFilterOperation=="select"){
		  alert("Please select Country");
		  return false;
	}
	
	var e = document.getElementById("filter_region_select");
    selectedRegionIndexForFilterOperation=e.selectedIndex;
	selectedRegionIdForFilterOperation=e.options[e.selectedIndex].value;
	
	if(selectedRegionIdForFilterOperation=="select"){
		  alert("Please select Region");
		  return false;
	}
	
	var e = document.getElementById("filter_state_select");
    selectedStateIndexForFilterOperation=e.selectedIndex;
	selectedStateIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedStateIdForFilterOperation=="select"){
		  alert("Please select State");
		  return false;
	}
	 
	var e = document.getElementById("filter_city_select");
	selectedCityIndexForFilterOperation=e.selectedIndex;
	selectedCityIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCityIdForFilterOperation=="select"){
		  alert("Please select City");
		  return false;
	}
	
	
	
      setTown(townNameVal,courierAddressVal,pinCodeVal,telNumberVal,faxNumberVal,tinNumberVal,panNumberVal);   	
         
 }




function getCourierData()
{

	var xmlhttp=getXMLHttpRequestObject();
	

	xmlhttp.onreadystatechange=function(){
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  document.getElementById("town_detail").innerHTML = xmlhttp.responseText;
		  document.getElementById("town_div").style.display = "block";
		 // document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
	    }
	  };
	  
	  xmlhttp.open("POST","ManageCourierController.jsp",true);
	  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xmlhttp.send("flag=getAll");
	
}





var selectedStateIdForFilterOperation;
var selectedStateIndexForFilterOperation;
var selectedStateIdForUpdateOperation;
var selectedStateIndexForUpdateOperation;
function getCity(subFlag,status)
{
	if(subFlag=="filterOperation"){
    //document.getElementById("town_div").style.display="none";		
	var e = document.getElementById("filter_state_select");
    selectedStateIndexForFilterOperation=e.selectedIndex;
	selectedStateIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedStateIdForFilterOperation=="select"){
		  alert("Please select State");
		  //document.getElementById("town_div").style.display="none";
		  document.getElementById("filter_select_city").innerHTML="City: <select style=\"width:110px;\"><option>select</option></select>";
		
		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_state_select");
	    selectedStateIndexForUpdateOperation=e.selectedIndex;
		selectedStateIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedStateIdForUpdateOperation=="select"){
			  alert("Please select State");
			 // document.getElementById("update_town_div").style.display="none";
			  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
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
		 
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_city").innerHTML=xmlhttp.responseText;
		 
		  if(status==0){
				 var e = document.getElementById("update_city_select");
				 e.selectedIndex=selectedCityIndexForFilterOperation;
				 getTown(subFlag,0);
			  }
		    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_city&subFlag="+subFlag+"&stateId="+selectedStateIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_city&subFlag="+subFlag+"&stateId="+selectedStateIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}






var selectedCityIdForFilterOperation;
var selectedCityIndexForFilterOperation;
var selectedCityIdForUpdateOperation;
var selectedCityIndexForUpdateOperation;

function getTown(subFlag)
{
	
	/*if(subFlag=="filterOperation"){
		var e = document.getElementById("filter_city_select");
		selectedCityIndexForFilterOperation=e.selectedIndex;
		selectedCityIdForFilterOperation=e.options[e.selectedIndex].value;
		
	alert("city"+ selectedCityIdForFilterOperation);
	alert("state"+ selectedStateIdForFilterOperation);
	alert("region" + selectedRegionIdForFilterOperation);
	alert("coutry"+ selectedCountryIdForFilterOperation);
	
	if(selectedCityIdForFilterOperation=="select"){
		alert("Please select City");
		document.getElementById("town_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_city_select");
		selectedCityIndexForUpdateOperation=e.selectedIndex;
		selectedCityIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCityIdForUpdateOperation=="select"){
			alert("Please select City");
			//document.getElementById("town_div").style.display="none";
			return;
		}
		return;
		}*/
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		  document.getElementById("town_div").style.display="block";
		  document.getElementById("town_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  };
	  
//	var outData="flag=get_town&cityId="+selectedCityIdForFilterOperation+"&stateId="+selectedStateIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation+"&countryId="+selectedCountryIdForFilterOperation;
	  var outData="flag=get_town";
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setTown(townName,courierAddress,pinCode,telNumber,faxNumber,tinNumber,panNumber)
{
	//alert("it is calling  add");
	
	 
	  
	
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		  {
			  alert(DATA_EXISTS);
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
		  {
			  alert(PROBLEM_OCCURED);
		  }
		  document.getElementById("addCourierName").value = "";
		  document.getElementById("courierAddress").value = "";
		  document.getElementById("pinCode").value = "";
		  document.getElementById("telNumber").value = "";
		  document.getElementById("faxNumber").value = "";
		  document.getElementById("tinNumber").value = "";
		  document.getElementById("panNumber").value = "";
		  getCourierData();
		  //getTown();
	    //window.location.reload();
		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	  
	  
	 
	var outData="flag=set_town&countryId="+selectedCountryIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation+"&stateId="+selectedStateIdForFilterOperation+"&cityId="+selectedCityIdForFilterOperation
				+"&townName="+townName+"&courierAddress="+courierAddress+"&pinCode="+pinCode+"&telNumber="+telNumber+"&faxNumber="+faxNumber+"&tinNumber="+tinNumber+"&panNumber="+panNumber;
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteTown(tableID) {
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
        			  alert(DELETED);
        		  }
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
        		  {
        			  alert(PROBLEM_OCCURED);
        		  }
        		  getTown();
        	    //window.location.reload();
        		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  };
        	  //alert(""+selectedValues);
        	var outData="flag=delete_town&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageCourierController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateTown(courierId,courierName,courierAddress,pinCode,telNumber,faxNumber,tinNumber,panNumber){
	//alert("script update town calling"+townId+townName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" state "+selectedStateId+" update "+updateSelectedStateId+" city "+selectedCityId+" update "+updateSelectedCityId+" town "+selectedTownId+" update "+updateSelectedTownId);
		 if((selectedCountryIdForFilterOperation==selectedCountryIdForUpdateOperation)&&(selectedRegionIdForFilterOperation==selectedRegionIdForUpdateOperation)&&(selectedStateIdForFilterOperation==selectedStateIdForUpdateOperation)&&(selectedCityIdForFilterOperation==selectedCityIdForUpdateOperation)){

			 // alert("it is if");
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
			  {
				  alert(UPDATED);
			  }
			  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
			  {
				  alert(PROBLEM_OCCURED);
			  }
			  document.getElementById("town_add").style.display="block";
			  document.getElementById("town_update").style.display="none";
         	 getTown('filterOperation');
		  }
		 else{
			//  alert("it is else");
			 window.location.reload();
		 }
	}
	};


	flag="update_town";
	
	var outData="flag="+flag+"&countryId="+selectedCountryIdForUpdateOperation+"&regionId="+selectedRegionIdForUpdateOperation+"&stateId="+selectedStateIdForUpdateOperation+"&cityId="+selectedCityIdForUpdateOperation
				+"&courierId="+courierId+"&courierName="+courierName+"&courierAddress="+courierAddress+"&pinCode="+pinCode+"&telNumber="+telNumber+"&faxNumber="+faxNumber
				+"&tinNumber="+tinNumber+"&panNumber="+panNumber; 
	//alert("script update town calling out data is "+outData);
	xmlhttp.open("POST","ManageCourierController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}  