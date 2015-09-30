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
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}

function validateAddOperation(roleName)
{   
		//alert("in Add Role");
	var roleNameVal=roleName.value;
            	
	if(roleNameVal == "")
	{
	    alert("Please Enter role Name");
	    roleName.focus();
	    return false;
	    
	}
/*	var n = roleNameVal.toString().length;
	
	for(var i = 0; i < n; i++){
	    if (! isChar(roleNameVal.charAt(i))){
	        alert("Enter only Alphabets");
	        roleName.focus();
	    return false;
	   }
	}
*/
      setRole(roleNameVal);   	
         
 }

function validateUpdateOperation(roleId,roleName)
{   
	//alert("it is updateValidate");
	var roleNameVal=roleName.value;

	var roleIdVal=roleId.value;
            	
            	if(roleNameVal=="")
                {
                    alert("Please Enter EmployeeType Name");
                    roleName.focus();
                    return false;
                    
            	}
            	
      updateRole(roleIdVal,roleNameVal);   	
         
 }


function showUpdateDiv(roleId,roleName){
	 document.getElementById("role_add").style.display="none";
	 document.getElementById("role_update").style.display="block";

	 var flag=0;
	 
	 getCountry('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	document.getElementById("roleName").value=roleName;
	
	document.getElementById("roleId").value=roleId;
	 
}

function showAddDiv(){
	document.getElementById("role_update").style.display="none";
	document.getElementById("role_add").style.display="block";
	
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
		  document.getElementById("role_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_country";
	xmlhttp.open("POST","ManageFdOrgRoleController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setRole(employeeTypeNameVal)
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
			  document.getElementById("addroleName").Value = employeeTypeNameVal;
			  document.getElementById("addroleName").focus();
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5 )
		  {
			  alert(PROBLEM_OCCURED);
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
		  {
			  document.getElementById("addroleName").value = "";
			  document.getElementById("addroleName").focus();
		  }
		  
		  getCountry();
	    //window.location.reload();
		  //document.getElementById("employee_type_detail").innerHTML=xmlhttp.responseText;
	    }
	  };
	var outData="flag=set_employee_type&roleName="+employeeTypeNameVal;
	xmlhttp.open("POST","ManageFdOrgRoleController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete EmployeeType	
function deleteRole(tableID) {
//	alert("it is calling and table id is "+tableID);
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
                //alert(e);
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
        		  //document.getElementById("employee_type_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  };
        	var outData="flag=delete_role&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageFdOrgRoleController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }

function updateRole(roleId,roleName){
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
		  document.getElementById("roleName").value = "";
		  document.getElementById("role_add").style.display="block";
		  document.getElementById("role_update").style.display="none";
          getCountry('filterOperation');
	}
	};

	flag="update_role";
	
	var outData="flag="+flag+"&roleId="+roleId+"&roleName="+roleName; 
	//alert("script update country calling out data is "+outData);
	xmlhttp.open("POST","ManageFdOrgRoleController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    