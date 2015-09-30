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
	
function isNumeric(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}

//validate
function validateAddOperation(countryName)
{   
	
	var countryNameVal=countryName.value;
	
	if(countryNameVal.trim()=="")
    {
        alert("Please Enter Country Name");
        countryName.focus();
        return false;
	}
	
	 /*if (isNumeric(countryNameVal))
	 {
		 	alert('name can be only aplhabets');
		 	return false;
	 }*/
	 if (!countryNameVal.match(/^[a-zA-Z].+$/))
     {
		 countryNameVal.value="";
		 countryName1.focus(); 
          alert("Please Enter only alphabets in text");
          return false;
     }
    
    setCountry(countryNameVal);   	
         
 }



function validateUpdateOperation(countryId,countryName)
{   
	//alert("it is updateValidate");
	var countryNameVal=countryName.value;
	var countryIdVal=countryId.value;
	 
    	if(countryNameVal=="")
        {
            alert("Please Enter Country Name");
            countryName.focus();
            return false;
    	}
            	
      updateCountry(countryIdVal,countryNameVal);   	
         
 }


 

function showUpdateDiv(countryId,countryName){
	 document.getElementById("country_add").style.display="none";
	 document.getElementById("country_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	document.getElementById("countryName").value=countryName;
	document.getElementById("countryId").value=countryId;
	 
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
		 // alert(xmlhttp.responseText);
		 
		  document.getElementById("country_div").style.display="block";
		  document.getElementById("country_detail").innerHTML=xmlhttp.responseText;
		  document.getElementById("countryName1").value = "" ;
		  
	    }
	  };
	var outData="flag=get_country";
	xmlhttp.open("POST","ManageCountryController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setCountry(countryName)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	  {
		  //alert(xmlhttp.responseText.replace(/^\s+|\s+$/g, ''));
		  //alert(xmlhttp.responseText);
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		 {
			alert("Data Already Exists."); 
			document.getElementById("countryName1").focus();
		 }else if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
		 {
			 alert("Data not added Please try again later");
		 }	
		 else{
			 document.getElementById("countryName1").focus();
		 }
		  getCountry();
	    //window.location.reload();
		  //document.getElementById("Country_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_country&countryName="+countryName;
	xmlhttp.open("POST","ManageCountryController.jsp",true);
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
        		  //alert(xmlhttp.responseText.replace(/^\s+|\s+$/g, ''));
        		  
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
        		  {
        			 alert("Data Deleted SuccessFully");
        		  }
        		  else
        		  {
        			  alert("Deletion Failed.Please try again later"); 
        		  }
        		  //alert(xmlhttp.responseText);
        		  getCountry();
        	    //window.location.reload();
        		  //document.getElementById("Country_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  };
        	var outData="flag=delete_country&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageCountryController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateCountry(countryId,countryName){
	//alert("script update country calling"+countryId+countryName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
  		   //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId+" country "+selectedCountryId+" update "+updateSelectedCountryId);
		  // alert("it is if");
		 //alert(xmlhttp.responseText);
		 //alert(xmlhttp.responseText.replace(/^\s+|\s+$/g, ''));
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
		 {
			 alert("Successfully Updated");
			 document.getElementById("countryName").Value = "";
		 }else if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3) {
			 alert('Trying to add duplicate value');
			 document.getElementById("countryName").focus();
		 }else {
			alert("Update to DB Failed.Please try again later"); 
		 }
		 //document.getElementById("country_update").style.display="block";
		 //document.getElementById("country_add").style.display="none";
		 
		 document.getElementById("country_update").style.display="none";
		 document.getElementById("country_add").style.display="block";
		 
		 getCountry('filterOperation');
	
	}
	};

	flag="update_country";
	
	var outData="flag="+flag+"&countryId="+countryId+"&countryName="+countryName; 
	//alert("script update country calling out data is "+outData);
	xmlhttp.open("POST","ManageCountryController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    