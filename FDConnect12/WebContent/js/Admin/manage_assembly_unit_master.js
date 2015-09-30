var DATA_EXISTS = "Data Already Exists";
var PROBLEM_OCCURED = "Problem Occured Please try again later";
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


//validate
function validateAddOperation(assemblyName)
{   
	var countryNameVal=assemblyName.value;
            	if(countryNameVal=="")
                {
                    alert("Please Enter Assembly Unit Name");
                    assemblyName.focus();
                    return false;
            	}
      setCountry(countryNameVal);   	
 }

function validateUpdateOperation(uassemblyId,uassemblyName)
{   
	//alert("it is updateValidate");
	var assemblyNameVal=uassemblyName.value;

	var assemblyIdVal=uassemblyId.value;
	 
            	
            	if(assemblyNameVal=="")
                {
                    alert("Please Enter Country Name");
                    uassemblyName.focus();
                    return false;
                    
            	}
            	
      updateCountry(assemblyIdVal,assemblyNameVal);   	
         
 }



function showUpdateDiv(assemblyId,assemblyName){
	 document.getElementById("country_add").style.display="none";
	 document.getElementById("country_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	document.getElementById("uassemblyName").value=assemblyName;
	
	document.getElementById("uassemblyId").value=assemblyId;
}

function showAddDiv(){
	document.getElementById("country_update").style.display="none";
	document.getElementById("country_add").style.display="block";
}


function getCountry(subFlag)
{
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  document.getElementById("country_div").style.display="block";
		  document.getElementById("country_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_country";
	xmlhttp.open("POST","ManageAssemblyUnitMasterController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setCountry(assemblyName)
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
		  document.getElementById("addAssemblyName").value = "";
		  getCountry();
	    //window.location.reload();
		  //document.getElementById("Country_detail").innerHTML=xmlhttp.responseText;
	    }
	  }
	var outData="flag=set_country&countryName="+assemblyName;
	xmlhttp.open("POST","ManageAssemblyUnitMasterController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteCountry(tableID) {
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
        		  getCountry();
        	    //window.location.reload();
        		  //document.getElementById("Country_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  }
        	var outData="flag=delete_country&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageAssemblyUnitMasterController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }

function updateCountry(assemblyId,assemblyName){
	//alert("script update country calling"+countryId+countryName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId);
	
			 // alert("it is if");
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
		  {
			  alert(UPDATED);
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
		  {
			  alert(PROBLEM_OCCURED);
		  }
		  document.getElementById("country_add").style.display="block";
		  document.getElementById("country_update").style.display="none";
		  
          getCountry('filterOperation');
	}
	};

	flag="update_country";
	
	var outData="flag="+flag+"&countryId="+assemblyId+"&countryName="+assemblyName; 
	//alert("script update country calling out data is "+outData);
	xmlhttp.open("POST","ManageAssemblyUnitMasterController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    