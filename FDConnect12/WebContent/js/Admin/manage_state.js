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


function validateAddOperation(stateName)
{   
	var stateNameVal=stateName.value;
            	
	if(stateNameVal.trim()=="")
    {
        alert("Please Enter State Name");
        stateName.focus();
        return false;
	}
	/*var n = stateNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(stateNameVal.charAt(i))){
            alert("Enter only Alphabets");
            stateName.focus();
            return false;             
        }
    }*/

      setState(stateNameVal);   	
 }



function validateUpdateOperation(stateId,stateName)
{   
	//alert("it is updateValidate");
	var stateNameVal=stateName.value;
	var stateIdVal=stateId.value;
            	
	if(stateNameVal=="")
    {
        alert("Please Enter State Name");
        stateName.focus();
        return false;
	}
/*	if(!stateNameVal.match(/^[a-zA-Z]+$/)){
		alert("Numbers and Special Characters are not allowed");
		stateName.focus();
		return false;
	}
	*/
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
      updateState(stateIdVal,stateNameVal);   	
         
 }


 

function showUpdateDiv(stateId,stateName){
	 document.getElementById("state_add").style.display="none";
	 document.getElementById("state_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	 
	 
	document.getElementById("stateName").value=stateName;
	
	document.getElementById("stateId").value=stateId;
	 

	
}

function showAddDiv(){
	document.getElementById("state_update").style.display="none";
	document.getElementById("state_add").style.display="block";
	


	 

	
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
	xmlhttp.open("POST","ManageStateController.jsp",true);
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
	document.getElementById("state_div").style.display="none";	
	var e = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=e.selectedIndex;
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCountryIdForFilterOperation=="select"){
		  alert("Please select Country");
		  document.getElementById("state_div").style.display="none";
		  document.getElementById("filter_select_region").innerHTML="Region: <select style=\"width:110px;\"><option>select</option></select>";
		  
		 

		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_country_select");
	    selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCountryIdForUpdateOperation=="select"){
			  alert("Please select Country");
			  //document.getElementById("update_state_div").style.display="none";
			  document.getElementById("update_select_region").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  
			 
			 
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
		 
		
		  
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;
		 
		 
		 
		  if(status==0){
			//  alert("it is region 1");
				 var e = document.getElementById("update_region_select");
				 e.selectedIndex=selectedRegionIndexForFilterOperation;
				 getState(subFlag);
				// alert("it is region 2");
			  }
	    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageStateController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



var selectedRegionIdForFilterOperation;
var selectedRegionIndexForFilterOperation;
var selectedRegionIdForUpdateOperation;
var selectedRegionIndexForUpdateOperation;
function getState(subFlag)
{
	//alert("calling 1");
	if(subFlag=="filterOperation"){
		
	var e = document.getElementById("filter_region_select");

	selectedRegionIndexForFilterOperation=e.selectedIndex;
	selectedRegionIdForFilterOperation=e.options[e.selectedIndex].value;
	
	if(selectedRegionIdForFilterOperation=="select"){
		alert("Please select Region");
		document.getElementById("state_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_region_select");
		selectedRegionIndexForUpdateOperation=e.selectedIndex;
		selectedRegionIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedRegionIdForUpdateOperation=="select"){
			alert("Please select Region");
			//document.getElementById("state_div").style.display="none";
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
		 
		  document.getElementById("state_div").style.display="block";
		  document.getElementById("state_detail").innerHTML=xmlhttp.responseText;
		  
		  //clear add stateName onclick of submit
		  document.getElementById("adstateName").value = "";
		  
	    }
	  };
	var outData="flag=get_state&regionId="+selectedRegionIdForFilterOperation;
	xmlhttp.open("POST","ManageStateController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setState(stateName)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText.replace(/^\s+|\s+$/g, ''));
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		  {
			  alert("Data Already Exists");
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4)
		  {
			  alert("There was an Error Please try again later");
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
		  {
			  alert("Please try again later");
		  }
		  getState();
	    //window.location.reload();
		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_state&countryId="+selectedCountryIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation+"&stateName="+stateName;
	xmlhttp.open("POST","ManageStateController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteState(tableID) {
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
        			  alert("Data deleted successfully.");
        		  }
        		  getState();
        	    //window.location.reload();
        		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  }
        	var outData="flag=delete_state&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageStateController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateState(stateId,stateName){
	//alert("script update state calling"+stateId+stateName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" state "+selectedStateId+" update "+updateSelectedStateId+" state "+selectedStateId+" update "+updateSelectedStateId+" state "+selectedStateId+" update "+updateSelectedStateId);
		 if((selectedCountryIdForFilterOperation==selectedCountryIdForUpdateOperation)&&(selectedRegionIdForFilterOperation==selectedRegionIdForUpdateOperation)){

			 // alert("it is if");
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
   		  	{
				 alert("Data updated successfully.");
				 document.getElementById("stateName").value = "";
				 document.getElementById("state_add").style.display="block";
				 document.getElementById("state_update").style.display="none";
   		  	}
         			 getState('filterOperation');
		  
		  }
		 
		 else{

			//  alert("it is else");
			 window.location.reload();
		 }
	}
	}


	flag="update_state";
	
	var outData="flag="+flag+"&countryId="+selectedCountryIdForUpdateOperation+"&regionId="+selectedRegionIdForUpdateOperation+"&stateId="+stateId+"&stateName="+stateName; 
	//alert("script update state calling out data is "+outData);
	xmlhttp.open("POST","ManageStateController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    