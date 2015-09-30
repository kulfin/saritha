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

function validateAddOperation(elementName)
{   
	var elementNameVal  = elementName.value;
	
	if(elementNameVal.trim()=="")
    {
        alert("Please Enter Element Name");
        elementName.focus();
        return false;
	}
/*	var n = elementNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(elementNameVal .charAt(i))){
            alert("Enter only Alphabets");
            elementName.focus();
            return false;
        	}
    } */    	
      
    setElement(elementNameVal.trim());   	
 }



function validateUpdateOperation(elementId,elementName)
{   
	//alert("it is updateValidate");
	var elementNameVal=elementName.value;

	var elementIdVal=elementId.value;
            	
	if(elementNameVal=="")
    {
        alert("Please Enter Element Name");
        elementName.focus();
        return false;
	}
            	
      updateCountry(elementIdVal,elementNameVal.trim());   	
         
 }
 

function showUpdateDiv(elementId,elementName){
	 document.getElementById("country_add").style.display="none";
	 document.getElementById("country_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	 
	 
	document.getElementById("elementName").value=elementName;
	
	document.getElementById("elementId").value=elementId;
	
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
	xmlhttp.open("POST","ManageMaterialElementController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setElement(elementName)
{
	//alert("it is calling  add in setElement" + elementName);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		  {
			  alert("Element Already Exists");
			  document.getElementById("elementName").focus();
			  return false;
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
		  {
			  alert("Problem occured Please try again later");
		  }
		  document.getElementById("elementName").value=" ";
		  getCountry();
		 
	    //window.location.reload();
		  //document.getElementById("Country_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_element&elementName="+elementName;
	xmlhttp.open("POST","ManageMaterialElementController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteElement(tableID) {
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
        		  getCountry();
        	    //window.location.reload();
        		  //document.getElementById("Country_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  }
        	var outData="flag=delete_element&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageMaterialElementController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateCountry(elementId,elementName){
	//alert("script update country calling"+countryId+countryName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId);
	
			 // alert("it is if");
         			 getCountry('filterOperation');
	}
	};

	flag="update_element";
	
	var outData="flag="+flag+"&elementId="+elementId+"&elementName="+elementName; 
	//alert("script update country calling out data is "+outData);
	xmlhttp.open("POST","ManageMaterialElementController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    