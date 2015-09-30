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
function validateAddOperation(regionName)
{   
	//alert(regionName.value);
	var regionNameVal=regionName.value;
            	
	if(regionNameVal.trim() == "")
    {
        alert("Please Enter Region Name");
        regionName.focus();
        return false;
	}
/*	var n = regionNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(regionNameVal.charAt(i))){
            alert("Enter only Alphabets");
            regionName.focus();
            return false;             
        }
    }*/

    setRegion(regionNameVal);
 }



function validateUpdateOperation(regionId,regionName)
{   
	//alert("it is updateValidate");
	var regionNameVal=regionName.value;

	var regionIdVal=regionId.value;
            	
	if(regionNameVal=="" )
    {
        alert("Please Enter Region Name");
        regionName.focus();
        return false;
	}
/*	if(!regionNameVal.match(/^[a-zA-Z]+$/)){
		alert("Numbers and Special Characters are not allowed");
		regionName.focus();
		return false;
	}*/
	if(document.getElementById("update_country_select").selectedIndex==0){
		alert("Please Select Country");
        //zipCode.focus();
        return false;	
	}
         updateRegion(regionIdVal, regionNameVal);
 }


 

function showUpdateDiv(regionId,regionName){
	 document.getElementById("region_add").style.display="none";
	 document.getElementById("region_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	document.getElementById("regionName").value=regionName;
	document.getElementById("regionId").value=regionId;
	
}

function showAddDiv(){
	document.getElementById("region_update").style.display="none";
	document.getElementById("region_add").style.display="block";
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
	  };
	var outData="flag=get_country&subFlag="+subFlag;
	xmlhttp.open("POST","ManageRegionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


var selectedCountryIdForFilterOperation;
var selectedCountryIndexForFilterOperation;
var selectedCountryIdForUpdateOperation;
var selectedCountryIndexForUpdateOperation;
function getRegion(subFlag)
{
	//alert("calling 1");
	if(subFlag=="filterOperation"){
		
	var e = document.getElementById("filter_country_select");

	selectedCountryIndexForFilterOperation=e.selectedIndex;
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	
	if(selectedCountryIdForFilterOperation=="select"){
		alert("Please select Country");
		document.getElementById("region_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_country_select");
		selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCountryIdForUpdateOperation=="select"){
			alert("Please select Country");
			//document.getElementById("country_div").style.display="none";
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
		 
		  document.getElementById("region_div").style.display="block";
		  document.getElementById("region_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  };
	var outData="flag=get_region&countryId="+selectedCountryIdForFilterOperation;
	xmlhttp.open("POST","ManageRegionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setRegion(regionName)
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
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
		  {
			  alert("problem occured Please try again later");
		  }
		  document.getElementById("addRegionName").value = "";
		  getRegion();
	    //window.location.reload();
		  //document.getElementById("Region_detail").innerHTML = xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_region&countryId="+selectedCountryIdForFilterOperation+"&regionName="+regionName;
	xmlhttp.open("POST","ManageRegionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteRegion(tableID) {
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
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
        		  {
        			  alert("problem occured Please try again later");
        		  }
        		  
        		  getRegion();
        	    //window.location.reload();
        		  //document.getElementById("Region_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  };
        	var outData="flag=delete_region&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageRegionController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateRegion(regionId,regionName){
	//alert("script update region calling"+regionId+regionName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" region "+selectedRegionId+" update "+updateSelectedRegionId);
		 if((selectedCountryIdForFilterOperation==selectedCountryIdForUpdateOperation)){

			 // alert("it is if");
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
			  {
				  alert("Data updated successfully");
			  }
			  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
			  {
				  alert("problem occured Please try again later");
			  }
			  document.getElementById("region_add").style.display="block";
				 document.getElementById("region_update").style.display="none";
			  getRegion('filterOperation');
		  }
		 
		 else{

			//  alert("it is else");
			 window.location.reload();
		 }
	}
	};


	flag="update_region";
	
	var outData="flag="+flag+"&countryId="+selectedCountryIdForUpdateOperation+"&regionId="+regionId+"&regionName="+regionName; 
	//alert("script update region calling out data is "+outData);
	xmlhttp.open("POST","ManageRegionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    