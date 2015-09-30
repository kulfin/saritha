function showUpdateDiv(areaId,areaName,zipCode){
	 document.getElementById("area_add").style.display="none";
	 document.getElementById("area_update").style.display="block";

	 var flag=0;
	 
	 updateGetCountry(flag);
	 
	document.getElementById("areaName").value=areaName;
	document.getElementById("zipCode").value=zipCode;
	document.getElementById("areaId").value=areaId;
	 

	
}

function showAddDiv(){
	document.getElementById("area_update").style.display="none";
	document.getElementById("area_add").style.display="block";
	


	 

	
}


function updateValidate(areaId,areaName,zipCode)
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


function updateGetCountry(flag)
{
	
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
	    
		  document.getElementById("update_select_country").innerHTML=xmlhttp.responseText;
		  
		  if(flag==0){
			  var e = document.getElementById("update_country_select");
				 e.selectedIndex=selectedCountryIndex;
				 
				 updateSelectedCountryId=selectedCountryId;
				 updateGetRegion(flag);
			  
		  }
		  

		 
		  
		 // document.getElementById("Country_select").selectedIndex="1";
	    }
	  }
	var outData="flag=update_get_country";
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
var updateSelectedCountryId ;
function updateGetRegion(flag)
{
	//updateSelectedRegionId="select";
	var e = document.getElementById("update_country_select");
    updateSelectedCountryId=e.options[e.selectedIndex].value;
	if(updateSelectedCountryId=="select"){
		alert("Please select Country");
		  //document.getElementById("area_div").style.display="none";
		  document.getElementById("update_select_region").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		return;
	}
	//document.getElementById("area_div").style.display="none";
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  //document.getElementById("state_div").style.display="block";
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;
          document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  
		  if(flag==0){
			  var e = document.getElementById("update_region_select");
				 e.selectedIndex=selectedRegionIndex;
				 
				 updateSelectedRegionId=selectedRegionId;
				 updateGetState(flag);
			  
		  }
	    }
	  }
	var outData="flag=update_get_region&countryId="+updateSelectedCountryId;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


var updateSelectedRegionId ;
function updateGetState(flag)
{
	
	var e = document.getElementById("update_region_select");

	updateSelectedRegionId=e.options[e.selectedIndex].value;
	if(updateSelectedRegionId=="select"){
		alert("Please select Region");
		//document.getElementById("area_div").style.display="none";

		  document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		return;
	}
//	document.getElementById("area_div").style.display="none";
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  //document.getElementById("state_div").style.display="block";
		  document.getElementById("update_select_state").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  
		  if(flag==0){
			  var e = document.getElementById("update_state_select");
				 e.selectedIndex=selectedStateIndex;
				 
				 updateSelectedStateId=selectedStateId;
				 updateGetCity(flag);
			  
		  }
	    }
	  }
	var outData="flag=update_get_state&regionId="+updateSelectedRegionId;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



var updateSelectedStateId ;
function updateGetCity(flag)
{
	
	var e = document.getElementById("update_state_select");

	updateSelectedStateId=e.options[e.selectedIndex].value;
	if(updateSelectedStateId=="select"){
		alert("Please select State");
		 //document.getElementById("area_div").style.display="none";

		  document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		return;
	}
	//document.getElementById("area_div").style.display="none";
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  //document.getElementById("state_div").style.display="block";
		  document.getElementById("update_select_city").innerHTML=xmlhttp.responseText;
		  document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  
		  if(flag==0){
			  var e = document.getElementById("update_city_select");
				 e.selectedIndex=selectedCityIndex;
				 
				 updateSelectedCityId=selectedCityId;
				 updateGetTown(flag);
			  
		  }
	    }
	  }
	var outData="flag=update_get_city&stateId="+updateSelectedStateId;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



var updateSelectedCityId ;
function updateGetTown(flag)
{
	
	var e = document.getElementById("update_city_select");

	updateSelectedCityId=e.options[e.selectedIndex].value;
	if(updateSelectedCityId=="select"){
		alert("Please select City update");
		document.getElementById("update_select_town").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		//document.getElementById("area_div").style.display="none";
		return;
	}
	//document.getElementById("area_div").style.display="none";
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  //document.getElementById("state_div").style.display="block";
		  document.getElementById("update_select_town").innerHTML=xmlhttp.responseText;
		  
		  if(flag==0){
			  var e = document.getElementById("update_town_select");
				 e.selectedIndex=selectedTownIndex;
				 
				updateSelectedTownId=selectedTownId;
				// updateGetTown(flag);
			  
		  }
	    }
	  }
	var outData="flag=update_get_town&cityId="+updateSelectedCityId;
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

var updateSelectedTownId ;
function updateGetArea(flag)
{
	
	var e = document.getElementById("update_town_select");

	updateSelectedTownId=e.options[e.selectedIndex].value;
	if(updateSelectedTownId=="select"){
		alert("Please select Town update");

		//document.getElementById("area_div").style.display="none";
		return;
	}
	
	
}

function updateArea(areaId,areaName,zipCode){
	//alert("script update area calling"+areaId+areaName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" state "+selectedStateId+" update "+updateSelectedStateId+" city "+selectedCityId+" update "+updateSelectedCityId+" town "+selectedTownId+" update "+updateSelectedTownId);
		 if((selectedCountryId==updateSelectedCountryId)&&(selectedRegionId==updateSelectedRegionId)&&(selectedStateId==updateSelectedStateId)&&(selectedCityId==updateSelectedCityId)&&(selectedTownId==updateSelectedTownId)){
		  
         			 getArea();
		  
		  }
		 
		 else{
			
			// window.location.reload();
		 }
	}
	}


	flag="update_area";
	
	var outData="flag="+flag+"&countryId="+updateSelectedCountryId+"&regionId="+updateSelectedRegionId+"&stateId="+updateSelectedStateId+"&cityId="+updateSelectedCityId+"&townId="+updateSelectedTownId+"&areaId="+areaId+"&areaName="+areaName+"&zipCode="+zipCode; 
	//alert("script update area calling out data is "+outData);
	xmlhttp.open("POST","ManageAreaController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
	
	
	
	