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

function validateAddOperation(hubName, contactPerson, telNo, address) {
	var hubNameVal = hubName.value;
	var contactPersonVal = contactPerson.value;
	var telNoVal = telNo.value;
	var addressVal = address.value;

	if (hubNameVal.trim() == "") {
		alert("Please Enter Hub Name");
		hubName.focus();
		return false;

	}
/*	var n = hubNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(hubNameVal.charAt(i))){
            alert("Enter only Alphabets for Hub Name");
            hubName.focus();
    return false;
   }
    }*/

	if (contactPersonVal.trim() == "") {
		alert("Please Enter Contact Person Name");
		contactPerson.focus();
		return false;

	}
/*	var n = contactPersonVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(contactPersonVal.charAt(i))){
            alert("Enter only Alphabets for Contact Person");
            contactPerson.focus();
    return false;
   }
    }*/


	if (telNoVal.trim() == "") {
		alert("Please Enter Tel No.");
		telNo.focus();
		return false;

	}
    if(telNoVal.length < 10){
		alert("Tel No cannot be less than 10 digits");
		telNo.focus();
		return false;
	}

	if (addressVal.trim() == "") {
		alert("Please Enter Address");
		address.focus();
		return false;

	}

	if (document.getElementById("add_country_select").selectedIndex == 0) {
		alert("Please Select Country");
		//zipCode.focus();
		return false;

	}

	if (document.getElementById("add_region_select").selectedIndex == 0) {
		alert("Please Select Region");
		//zipCode.focus();
		return false;

	}

	/*if (document.getElementById("add_state_select").selectedIndex == 0) {
		alert("Please Select State");
		//zipCode.focus();
		return false;

	}
	if (document.getElementById("add_city_select").selectedIndex == 0) {
		alert("Please Select City");
		//zipCode.focus();
		return false;

	}

	if (document.getElementById("add_town_select").selectedIndex == 0) {
		alert("Please Select Town");
		//zipCode.focus();
		return false;

	}

	if (document.getElementById("add_area_select").selectedIndex == 0) {
		alert("Please Select Area");
		//zipCode.focus();
		return false;

	}*/

	setFdHub(hubNameVal, contactPersonVal, telNoVal, addressVal);

}

function validateUpdateOperation(hubId,hubName, contactPerson, telNo, address) {
	var hubIdVal=hubId.value;
	var hubNameVal = hubName.value;
	var contactPersonVal = contactPerson.value;
	var telNoVal = telNo.value;
	var addressVal = address.value;


	
	if (document.getElementById("update_fd_org_select").selectedIndex == 0) {
		alert("Please Select Fd Organisation");
		//zipCode.focus();
		return false;

	}
	
	if (document.getElementById("update_country_select").selectedIndex == 0) {
		alert("Please Select Country");
		//zipCode.focus();
		return false;

	}

	if (document.getElementById("update_region_select").selectedIndex == 0) {
		alert("Please Select Region");
		//zipCode.focus();
		return false;

	}

	/*if (document.getElementById("update_state_select").selectedIndex == 0) {
		alert("Please Select State");
		//zipCode.focus();
		return false;

	}
	if (document.getElementById("update_city_select").selectedIndex == 0) {
		alert("Please Select City");
		//zipCode.focus();
		return false;

	}

	if (document.getElementById("update_town_select").selectedIndex == 0) {
		alert("Please Select Town");
		//zipCode.focus();
		return false;

	}

	if (document.getElementById("update_area_select").selectedIndex == 0) {
		alert("Please Select Area");
		//zipCode.focus();
		return false;

	}*/
	
	if (hubNameVal == "") {
		alert("Please Enter Hub Name");
		hubName.focus();
		return false;

	}
	if (contactPersonVal == "") {
		alert("Please Enter Contact Person Name");
		contactPerson.focus();
		return false;

	}

	if (telNoVal == "") {
		alert("Please Enter Tel No.");
		telNo.focus();
		return false;

	}

	if (addressVal == "") {
		alert("Please Enter Address");
		address.focus();
		return false;

	}

	updateFdHub(hubIdVal,hubNameVal, contactPersonVal, telNoVal, addressVal);


}
var countryIdVar;
var regionIdVar;
var stateIdVar;
var cityIdVar;
var townIdVar;
var areaIdVar;

function showUpdateDiv(hubId,hubName,address,contactPerson,telNo,countryId,regionId,stateId,cityId,townId,areaId) {
	
	document.getElementById("fd_hub_add").style.display = "none";
	document.getElementById("fd_hub_update").style.display = "block";


	
	  getFdOrg('updateOperation', 0);
	  countryIdVar=countryId;
	  regionIdVar=regionId;
	  stateIdVar=stateId;
	  cityIdVar=cityId;
	  townIdVar=townId;
	  areaIdVar=areaId;
	  getCountry('updateOperation',0);
	  
	  document.updateForm.hubId.value=hubId;
	  document.updateForm.hubName.value=hubName;
	  document.updateForm.address.value=address;
	  document.updateForm.contactPerson.value=contactPerson;
	  document.updateForm.telNo.value=telNo;
	 
	// var e = document.getElementById("update_fd_org_select");
	// e.selectedIndex=selectedFdOrgIndexForAddOperation;

	//document.updateForm.

	//document.getElementById("fd_hubId").value = fd_hubId;

}

function showAddDiv() {
	document.getElementById("fd_hub_update").style.display = "none";
	document.getElementById("fd_hub_add").style.display = "block";

}

function getCountry(subFlag, status) {

	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			if (subFlag == "addOperation")
				document.getElementById("add_select_country").innerHTML = xmlhttp.responseText;
			else if (subFlag == "updateOperation") {
				document.getElementById("update_select_country").innerHTML = xmlhttp.responseText;
				if (status == 0) {
					//var e = document.getElementById("update_country_select");
					//e.selectedIndex = selectedCountryIndexForAddOperation;
					//getRegion(subFlag, 0);
					
					 var e = document.getElementById('update_country_select');
					 for (i = 0; i <e.options.length; i++) {
					    
					    if(countryIdVar==(e .options[i].value)){
					    	e.options[i].selected= true;
					    }
					 }
					 getRegion(subFlag, 0);
				}
			}
		}
	};
	var outData = "flag=get_country&subFlag=" + subFlag;
	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

// Get Material Sub Group	
var selectedCountryIdForAddOperation;
var selectedCountryIndexForAddOperation;
var selectedCountryIdForUpdateOperation;
var selectedCountryIndexForUpdateOperation;
function getRegion(subFlag, status) {
	if (subFlag == "addOperation") {

		var e = document.getElementById("add_country_select");
		selectedCountryIndexForAddOperation = e.selectedIndex;
		selectedCountryIdForAddOperation = e.options[e.selectedIndex].value;
		if (selectedCountryIdForAddOperation == "select") {
			

			document.getElementById("add_select_region").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_state").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	else if (subFlag == "updateOperation") {
		var e = document.getElementById("update_country_select");
		selectedCountryIndexForUpdateOperation = e.selectedIndex;
		selectedCountryIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedCountryIdForUpdateOperation == "select") {
			
			//document.getElementById("update_area_div").style.display="none";
			document.getElementById("update_select_region").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_state").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			if (subFlag == "addOperation") {
				document.getElementById("add_select_region").innerHTML = xmlhttp.responseText;
				//document.getElementById("add_select_state").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("add_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			}

			else if (subFlag == "updateOperation") {
				document.getElementById("update_select_region").innerHTML = xmlhttp.responseText;
				//document.getElementById("update_select_state").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("update_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				if (status == 0) {
					 var e = document.getElementById('update_region_select');
					 for (i = 0; i <e.options.length; i++) {
					    
					    if(regionIdVar==(e .options[i].value)){
					    	e.options[i].selected= true;
					    }
					 }
					 getState(subFlag, 0);
				}
			}

		}
	};

	if (subFlag == "addOperation")
		var outData = "flag=get_region&subFlag=" + subFlag + "&countryId="
				+ selectedCountryIdForAddOperation;
	else if (subFlag == "updateOperation")
		var outData = "flag=get_region&subFlag=" + subFlag + "&countryId="
				+ selectedCountryIdForUpdateOperation;

	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

var selectedRegionIdForAddOperation;
var selectedRegionIndexForAddOperation;
var selectedRegionIdForUpdateOperation;
var selectedRegionIndexForUpdateOperation;
function getState(subFlag, status) {

	if (subFlag == "addOperation") {

		var e = document.getElementById("add_region_select");
		selectedRegionIndexForAddOperation = e.selectedIndex;
		selectedRegionIdForAddOperation = e.options[e.selectedIndex].value;
		if (selectedRegionIdForAddOperation == "select") {
		

			document.getElementById("add_select_state").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	else if (subFlag == "updateOperation") {
		//alert("it is update state up");
		var e = document.getElementById("update_region_select");
		selectedRegionIndexForUpdateOperation = e.selectedIndex;
		selectedRegionIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedRegionIdForUpdateOperation == "select") {
			
			// document.getElementById("update_area_div").style.display="none";
			document.getElementById("update_select_state").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			if (subFlag == "addOperation") {
//				document.getElementById("add_select_state").innerHTML = xmlhttp.responseText;
	//			document.getElementById("add_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
		//		document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			//	document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			}

			else if (subFlag == "updateOperation") {
				//alert("it is update state");
				//document.getElementById("update_select_state").innerHTML = xmlhttp.responseText;
				//document.getElementById("update_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				//document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				if (status == 0) {
					 var e = document.getElementById('update_state_select');
					 for (i = 0; i <e.options.length; i++) {
					    
					    if(stateIdVar==(e .options[i].value)){
					    	e.options[i].selected= true;
					    }
					 }
					 getCity(subFlag, 0);
				}
			}

		}
	};

	if (subFlag == "addOperation")
		var outData = "flag=get_state&subFlag=" + subFlag + "&regionId="
				+ selectedRegionIdForAddOperation;
	else if (subFlag == "updateOperation")
		var outData = "flag=get_state&subFlag=" + subFlag + "&regionId="
				+ selectedRegionIdForUpdateOperation;

	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

var selectedStateIdForAddOperation;
var selectedStateIndexForAddOperation;
var selectedStateIdForUpdateOperation;
var selectedStateIndexForUpdateOperation;
function getCity(subFlag, status) {
	if (subFlag == "addOperation") {

		var e = document.getElementById("add_state_select");
		selectedStateIndexForAddOperation = e.selectedIndex;
		selectedStateIdForAddOperation = e.options[e.selectedIndex].value;
		if (selectedStateIdForAddOperation == "select") {
			

			document.getElementById("add_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	else if (subFlag == "updateOperation") {
		var e = document.getElementById("update_state_select");
		selectedStateIndexForUpdateOperation = e.selectedIndex;
		selectedStateIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedStateIdForUpdateOperation == "select") {
		
			// document.getElementById("update_area_div").style.display="none";
			document.getElementById("update_select_city").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			if (subFlag == "addOperation") {
				document.getElementById("add_select_city").innerHTML = xmlhttp.responseText;
				document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			}

			else if (subFlag == "updateOperation") {
				document.getElementById("update_select_city").innerHTML = xmlhttp.responseText;
				document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				if (status == 0) {
					 var e = document.getElementById('update_city_select');
					 for (i = 0; i <e.options.length; i++) {
					    
					    if(cityIdVar==(e .options[i].value)){
					    	e.options[i].selected= true;
					    }
					 }
					 getTown(subFlag, 0);
				}
			}

		}
	};

	if (subFlag == "addOperation")
		var outData = "flag=get_city&subFlag=" + subFlag + "&stateId="
				+ selectedStateIdForAddOperation;
	else if (subFlag == "updateOperation")
		var outData = "flag=get_city&subFlag=" + subFlag + "&stateId="
				+ selectedStateIdForUpdateOperation;

	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

var selectedCityIdForAddOperation;
var selectedCityIndexForAddOperation;
var selectedCityIdForUpdateOperation;
var selectedCityIndexForUpdateOperation;
function getTown(subFlag, status) {

	if (subFlag == "addOperation") {

		var e = document.getElementById("add_city_select");
		selectedCityIndexForAddOperation = e.selectedIndex;
		selectedCityIdForAddOperation = e.options[e.selectedIndex].value;
		if (selectedCityIdForAddOperation == "select") {
			

			document.getElementById("add_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	else if (subFlag == "updateOperation") {
		var e = document.getElementById("update_city_select");
		selectedCityIndexForUpdateOperation = e.selectedIndex;
		selectedCityIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedCityIdForUpdateOperation == "select") {
			
			//document.getElementById("update_area_div").style.display="none";
			document.getElementById("update_select_town").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			if (subFlag == "addOperation") {
				document.getElementById("add_select_town").innerHTML = xmlhttp.responseText;
				document.getElementById("add_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";

			}

			else if (subFlag == "updateOperation") {
				document.getElementById("update_select_town").innerHTML = xmlhttp.responseText;
				document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
				if (status == 0) {
					 var e = document.getElementById('update_town_select');
					 for (i = 0; i <e.options.length; i++) {
					    
					    if(townIdVar==(e .options[i].value)){
					    	e.options[i].selected= true;
					    	//alert(townIdVar);
					    }
					 }
					 getArea(subFlag, 0);
				}
			}

		}
	};

	if (subFlag == "addOperation") {

		var outData = "flag=get_town&subFlag=" + subFlag + "&cityId="
				+ selectedCityIdForAddOperation;
	} else if (subFlag == "updateOperation")
		var outData = "flag=get_town&subFlag=" + subFlag + "&cityId="
				+ selectedCityIdForUpdateOperation;

	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

var selectedTownIdForAddOperation;
var selectedTownIndexForAddOperation;
var selectedTownIdForUpdateOperation;
var selectedTownIndexForUpdateOperation;
function getArea(subFlag, status) {

	if (subFlag == "addOperation") {

		var e = document.getElementById("add_town_select");
		selectedTownIndexForAddOperation = e.selectedIndex;
		selectedTownIdForAddOperation = e.options[e.selectedIndex].value;
		if (selectedTownIdForAddOperation == "select") {
			

			document.getElementById("add_select_area").innerHTML = "<select style=\"width:110px;\"><option>select</option></select>";
			return;
		}
	}

	else if (subFlag == "updateOperation") {
		var e = document.getElementById("update_town_select");
		selectedTownIndexForUpdateOperation = e.selectedIndex;
		selectedTownIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedTownIdForUpdateOperation == "select") {
		
			//document.getElementById("update_area_div").style.display="none";
			document.getElementById("update_select_area").innerHTML = "<select style=\"width:120px;\"><option>select</option></select>";
			return;
		}
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			if (subFlag == "addOperation") {
				document.getElementById("add_select_area").innerHTML = xmlhttp.responseText;

			}

			else if (subFlag == "updateOperation") {
				document.getElementById("update_select_area").innerHTML = xmlhttp.responseText;
				if (status == 0) {
					 var e = document.getElementById('update_area_select');
					 for (i = 0; i <e.options.length; i++) {
					    
					    if(areaIdVar==(e .options[i].value)){
					    	e.options[i].selected= true;
					    }
					 }
					 getAreaId(subFlag);
				}
			}

		}
	};

	if (subFlag == "addOperation") {

		var outData = "flag=get_area&subFlag=" + subFlag + "&townId="
				+ selectedTownIdForAddOperation;
	} else if (subFlag == "updateOperation"){
		var outData = "flag=get_area&subFlag=" + subFlag + "&townId="
				+ selectedTownIdForUpdateOperation;
		//alert("area script"+selectedTownIdForUpdateOperation);
	}

	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

var selectedAreaIdForAddOperation;
var selectedAreaIndexForAddOperation;
var selectedAreaIdForUpdateOperation;
var selectedAreaIndexForUpdateOperation;
function getAreaId(subFlag) {
	if (subFlag == "addOperation") {
		var e = document.getElementById("add_area_select");
		selectedAreaIndexForAddOperation = e.selectedIndex;
		selectedAreaIdForAddOperation = e.options[e.selectedIndex].value;
		if (selectedAreaIdForAddOperation == "select") {
			

			return;
		}
	}

	if (subFlag == "updateOperation") {
		var e = document.getElementById("update_area_select");
		selectedAreaIndexForUpdateOperation = e.selectedIndex;
		selectedAreaIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedAreaIdForUpdateOperation == "select") {
			
			//document.getElementById("area_div").style.display="none";
			return;
		}
		return;
	}

}

//Get Material Group	
function getFdOrg(subFlag,status) {
    //alert("it is calling");

		var xmlhttp=getXMLHttpRequestObject();

	 xmlhttp.onreadystatechange=function()
	 {
	 if (xmlhttp.readyState==4 && xmlhttp.status==200)
	 {
	
	 if(subFlag=="addOperation"){
	 document.getElementById("add_select_fd_org").innerHTML=xmlhttp.responseText;
	 getCountry('addOperation', 1);
	
	 }
	
	 else if(subFlag=="updateOperation"){
		 //alert("cal1");
	 document.getElementById("update_select_fd_org").innerHTML=xmlhttp.responseText;
	 if(status==0){
		// alert("cal1");
	 var e = document.getElementById("update_fd_org_select");
	 e.selectedIndex=selectedFdOrgIndexForAddOperation;
	 getFdHub(subFlag,0);
	 }
	 }
	 }
	 }
	 var outData="flag=get_fd_org&subFlag="+subFlag;
	 xmlhttp.open("POST","ManageFdHubController.jsp",true);
	 xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 xmlhttp.send(outData);
	 
}


// Get Material Sub Group	

var selectedFdOrgIdForAddOperation;
var selectedFdOrgIndexForAddOperation;
var selectedFdOrgIdForUpdateOperation;
var selectedFdOrgIndexForUpdateOperation;
function getFdHub(subFlag) {
	//alert("calling 1");
	if (subFlag == "addOperation") {

		var e = document.getElementById("add_fd_org_select");

		selectedFdOrgIndexForAddOperation = e.selectedIndex;
		selectedFdOrgIdForAddOperation = e.options[e.selectedIndex].value;

		if (selectedFdOrgIdForAddOperation == "select") {
			alert("Please select FdOrg");
			document.getElementById("fd_hub_div").style.display = "none";
			return;
		}
	}

	if (subFlag == "updateOperation") {
		var e = document.getElementById("update_fd_org_select");
		selectedFdOrgIndexForUpdateOperation = e.selectedIndex;
		selectedFdOrgIdForUpdateOperation = e.options[e.selectedIndex].value;
		if (selectedFdOrgIdForUpdateOperation == "select") {
			alert("Please select FdOrg");
			//document.getElementById("fd_org_div").style.display="none";
			return;
		}
		return;
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//alert(xmlhttp.responseText);

			document.getElementById("fd_hub_div").style.display = "block";
			document.getElementById("fd_hub_detail").innerHTML = xmlhttp.responseText;
			

		}
	};
	var outData = "flag=get_fd_hub&fd_orgId=" + selectedFdOrgIdForAddOperation;
	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

//Set Material Group	
function setFdHub(hubName, contactPerson, telNo, address)
{
	//alert("it is calling  add");
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				//alert('response is'+ xmlhttp.responseText);
			  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == "0"){
					 
				  getFdHub();
				  document.getElementById("add_hub_name_input").value="";
				  document.getElementById("add_hub_tel_no_input").value="";
				  document.getElementById("add_hub_contact_person_input").value="";
				  document.getElementById("add_hub_address_input").value="";
				  
				  document.getElementById("add_country_select").selectedIndex=0;
				  document.getElementById("add_region_select").selectedIndex=0;
				  /*document.getElementById("add_state_select").selectedIndex=0;
				  document.getElementById("add_city_select").selectedIndex=0;
				  document.getElementById("add_town_select").selectedIndex=0;
				  document.getElementById("add_area_select").selectedIndex=0;*/
				 
	            } else if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
				  alert('FD Hub Already Exist');
				  document.getElementById("add_hub_name_input").focus();
				  return false;
			    }
			   else{
				  alert('FD Hub Addition Failed');
			    }
		    }
		
	};
	var outData = "flag=set_fd_hub&fd_orgId=" + selectedFdOrgIdForAddOperation
			+ "&hubName=" + hubName + "&contactPerson=" + contactPerson
			+ "&telNo=" + telNo + "&address=" + address + "&countryId="
			+ selectedCountryIdForAddOperation + "&regionId="
			+ selectedRegionIdForAddOperation ;
	
	//alert(outData);
	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}

//Delete Material Group	
function deleteFdHub(tableID) {
	//alert("it is calling and table id is "+tableID);
	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var selectedValues = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];

			if (null != chkbox && true == chkbox.checked) {
				selectedValues[j] = chkbox.value;
				j++;

			}

		}

	} catch (e) {
		alert(e);
	}
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
  				 
   			  getFdHub();
   			 
   			 
   			  }
   			
   			  else{
   			  alert('FD Hub Deletion Failed');
   			  }
		}
	}
	var outData = "flag=delete_fd_hub&selectedValues=" + selectedValues;
	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}



function updateFdHub(hubId, hubName, contactPerson, telNo, address)
{
	//alert("it is calling  add");
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if ((selectedFdOrgIdForAddOperation == selectedFdOrgIdForUpdateOperation)) {

				if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
					 
					  getFdHub();
					  showAddDiv();
					  }
					
					  else{
					  alert('FD Hub Updation Failed');
					  }

			}

			else {

				//  alert("it is else");
				window.location.reload();
			}
		}
	};
	var outData = "flag=update_fd_hub&fd_orgId=" + selectedFdOrgIdForUpdateOperation+"&hubId="+hubId+""
			+ "&hubName=" + hubName + "&contactPerson=" + contactPerson
			+ "&telNo=" + telNo + "&address=" + address + "&countryId="
			+ selectedCountryIdForUpdateOperation + "&regionId="
			+ selectedRegionIdForUpdateOperation ;
	
	//alert(outData);
	xmlhttp.open("POST", "ManageFdHubController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


