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

function validateAddOperation(townName,userName,password,designation,contactAddress,contactPhone,empType,role)
{   
	var townNameVal=townName.value;
	var userNameVal=userName.value;
	var passwordVal=password.value;
	var designationVal=designation.value;
	var contactAddressVal=contactAddress.value;
	var contactPhoneVal =contactPhone.value;
	var empTypeVal = empType.value;
	var roleVal = role.value;
	//alert('contactAddress is'+ contactAddress);
	if(townNameVal.trim()== "")
    {
        alert("Please Enter Employee Name");
        townName.focus();
        return false;
	}
	var n = townNameVal.toString().length;

  /*  for ( var i = 0; i < n; i++) {
		if (!isChar(townNameVal.charAt(i))) {
			alert("Enter only Alphabets  for Employee Name");
			townName.focus();
			return false;
		}
	}*/

	if(userNameVal.trim()=="")
    {
        alert("Please Enter User Name");
        userName.focus();
        return false;
	}
	if(passwordVal.trim()=="")
    {
        alert("Please Enter Password");
        password.focus();
        return false;
	}
	/* for ( var i = 0; i < n; i++) {
		if (!isChar(passwordVal.charAt(i))) {
			alert("Enter only Alphabets  for User Password");
			password.focus();
			return false;
		}
	}*/

	if(designationVal.trim()=="")
    {
        alert("Please Enter Designation");
        designation.focus();
        return false;
	}
/*	var n = designationVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(designationVal.charAt(i))){
            alert("Enter only Alphabets for Designation");
            designation.focus();
    return false;
   }
    }*/

	if(contactAddressVal.trim()=="")
    {
        alert("Please Enter Contact Address");
        contactAddress.focus();
        return false;
	}
	
	if(contactPhoneVal.trim()=="")
    {
        alert("Please Enter Phone Number");
        contactPhone.focus();
        return false;
	}
	if(contactPhoneVal.length<10){
		alert("Phone Number cannot be less than 10 digits");
		contactPhone.focus();
		return false;
	}
	

	if(empTypeVal=="Select")
    {
        alert("Please Select Employee Type");
        empType.focus();
        return false;
	}
	if(roleVal=="Select")
    {
        alert("Please Select Employee Role");
        role.focus();
        return false;
	}
	
      //alert("Before setEmployeeDetails"); 
//      setTown(townNameVal,userNameVal,passwordVal,designationVal,contactAddressVal,contactPhoneVal,empTypeVal,roleVal);   	
      setEmployeeDetails(townNameVal,userNameVal,passwordVal,designationVal,contactAddressVal,contactPhoneVal,empTypeVal,roleVal);
         
 }



function validateUpdateOperation(townId,townName,userName,password,designation,contactAddress,empType,role)
{   
	//alert("it is updateValidate");
	var empName = document.getElementById("townName").value;
	if(empName == "")
	{
		alert('Please Enter EmployeeName');
		townName.focus();
		return false;
	}
	var empId= document.getElementById("townId").value;
	
	var userName = document.getElementById("userName").value;
	if(userName == "")
	{
		alert('Please Enter EmployeeName');
		userName.focus();
		return false;
	}
	var password = document.getElementById("password").value;
	if(password == "")
	{
		alert('Please Enter password');
		password.focus();
		return false;
	}
	var designation = document.getElementById("designation").value;
	if(password == "")
	{
		alert('Please Enter Employee Designation');
		designation.focus();
		return false;
	}
	var contactAddress = document.getElementById("contactAddress").value;
	if(password == "")
	{
		alert('Please Enter Contact Address');
		contactAddress.focus();
		return false;
	}
	var contactPhone = document.getElementById("contactPhone").value;
	if(password == "")
	{
		alert('Please Enter Contact Phone');
		contactPhone.focus();
		return false;
	}
	
	if(empType == "select")
	{
		alert('Please Enter Employee Type');
		empType.focus();
		return false;
	}
	
	if(role == "select" || role == "")
	{
		alert('Please Enter Employee Role');
		role.focus();
		return false;
	}

	/*alert('here1'+empName);
	alert('here2'+userName);
	alert('here3'+password);
	alert('here4'+designation);
	alert('here5'+contactAddress);
	alert('here6'+contactPhone);
	alert('here7'+empType.value);
	alert('here8'+role.value);*/
	updateTown(empId,empName,userName,password,designation,contactAddress,contactPhone,empType.value,role.value);
	//updateTown(townIdVal,townNameVal,userNameVal,passwordVal,designationVal,contactAddressVal,empTypeVal,roleVal);   	
         
 }


 

function showUpdateDiv(townId,townName,userName,password,designation,contactAddress,contactPhone,empType,role)
{
	//alert("town Name"+townName);
	//alert("town id" + townId);
	 document.getElementById("town_add").style.display="none";
	 document.getElementById("town_update").style.display="block";
	 //alert("UserName is "+userName);
	 var flag=0;
	 
	 //getCountry('updateOperation',0);
	 getRegion('updateOperation',0);
	// var e = document.getElementById("update_country_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	document.getElementById("townName").value = townName;
	document.getElementById("userName").value = userName;
	document.getElementById("password").value = password;
	document.getElementById("designation").value = designation;
	document.getElementById("contactAddress").value = contactAddress;
	document.getElementById("contactPhone").value = contactPhone;
	document.getElementById("empType").value = empType;
	document.getElementById("role").value = role;
	document.getElementById("townId").value = townId;
}

function showAddDiv(){
	document.getElementById("town_update").style.display="none";
	document.getElementById("town_add").style.display="block";
}


//Get Material Group	
function getCountry(subFlag,status)
{
	//alert("Inside Country");
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
			 //getRegion(subFlag,0);
			 getCity(subFlag,0);
		  }
		  }
	    }
	  };
	var outData="flag=get_country&subFlag="+subFlag;
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
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
	//alert("Inside Region");
	if(subFlag=="filterOperation"){
	document.getElementById("town_div").style.display="none";	
	var e = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=e.selectedIndex;
    //alert("selected hub id is" + selectedCountryIndexForFilterOperation);
	selectedCountryIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedCountryIdForFilterOperation=="select"){
		  alert("Please select Organisation");
		  document.getElementById("town_div").style.display="none";
		  document.getElementById("filter_select_region").innerHTML="Hub: <select><option>select</option></select>";
		  //document.getElementById("filter_select_state").innerHTML="Division: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="Department: <select><option>select</option></select>";

		return;
	}
	}


	else if(subFlag=="updateOperation"){
		//alert('selected country' + selectedCountryIdForFilterOperation);
		/*var e = document.getElementById("filter_country_select");
	    selectedCountryIndexForUpdateOperation=e.selectedIndex;
		selectedCountryIdForUpdateOperation=e.options[e.selectedIndex].value;*/
		selectedCountryIdForUpdateOperation = selectedCountryIdForFilterOperation;
		if(selectedCountryIdForUpdateOperation=="select"){
			  alert("Please select Organisation");
			  //document.getElementById("update_town_div").style.display="none";
			  document.getElementById("update_select_region").innerHTML="<select><option>select</option></select>";
			  //document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_city").innerHTML="<select><option>select</option></select>";
			 
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
		  //document.getElementById("filter_select_state").innerHTML="Division: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="Department: <select style=\"width:110px;\"><option>select</option></select>";
		  
	    }

	  else if(subFlag=="updateOperation"){
		  
		  document.getElementById("update_select_region").innerHTML=xmlhttp.responseText;
		  //document.getElementById("update_select_state").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		  //document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		 
		  if(status==0){
			  //alert('before');
					 var e = document.getElementById("update_region_select");
					 e.selectedIndex=selectedCountryIndexForFilterOperation;
//					 getTown(subFlag,0);
					 getCity('updateOperation',0);
				 
				 /*var e = document.getElementById("update_region_select");
				 e.selectedIndex=selectedRegionIndexForFilterOperation;
//				 getState(subFlag,0);
//				 getCity(subFlag,0);
				 getTown(subFlag,0);*/
			  }
	    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_region&subFlag="+subFlag+"&countryId="+selectedCountryIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




var selectedRegionIdForFilterOperation;
var selectedRegionIndexForFilterOperation;
var selectedRegionIdForUpdateOperation;
var selectedRegionIndexForUpdateOperation;
var selectedOrgaForFilter;

function getState(subFlag,status)
{
	//alert("inside getState");
	if(subFlag=="filterOperation"){
	document.getElementById("town_div").style.display="none";		
	var e = document.getElementById("filter_region_select");
    selectedRegionIndexForFilterOperation=e.selectedIndex;
    selectedRegionIdForFilterOperation=e.options[e.selectedIndex].value;
    
    var f = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=f.selectedIndex;
    selectedOrgaForFilter = f.options[f.selectedIndex].value;
    
    //alert("selected country id is " + selectedOrgaForFilter);
	if(selectedRegionIdForFilterOperation=="select"){
		  alert("Please select Hub");
		  document.getElementById("town_div").style.display="none";
		  document.getElementById("filter_select_state").innerHTML="Division: <select style=\"width:110px;\"><option>select</option></select>";
		  document.getElementById("filter_select_city").innerHTML="Department: <select style=\"width:110px;\"><option>select</option></select>";
		  
		return;
	}
	}

	else if(subFlag=="updateOperation"){
		//alert("it is update state up");
		var e = document.getElementById("update_region_select");
	    selectedRegionIndexForUpdateOperation=e.selectedIndex;
		selectedRegionIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedRegionIdForUpdateOperation=="select"){
			  alert("Please select Hub");
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
		  document.getElementById("filter_select_city").innerHTML="Department: <select style=\"width:110px;\"><option>select</option></select>";
		
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
    	var outData="flag=get_state&subFlag="+subFlag+"&regionId="+selectedRegionIdForFilterOperation+"&orgId="+selectedOrgaForFilter;
    else if(subFlag=="updateOperation")
    	var outData="flag=get_state&subFlag="+subFlag+"&regionId="+selectedRegionIdForUpdateOperation+"&orgId="+selectedOrgaForFilter; 
       
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}



var selectedStateIdForFilterOperation;
var selectedStateIndexForFilterOperation;
var selectedStateIdForUpdateOperation;
var selectedStateIndexForUpdateOperation;

function getCity(subFlag,status)
{
	//alert('in getCity');
		if(subFlag=="filterOperation"){
	    document.getElementById("town_div").style.display="none";		
		/*var e = document.getElementById("filter_state_select");
	    selectedStateIndexForFilterOperation=e.selectedIndex;
		selectedStateIdForFilterOperation=e.options[e.selectedIndex].value;*/
		
		var g = document.getElementById("filter_region_select");
	    selectedRegionIndexForFilterOperation=g.selectedIndex;
	    selectedRegionIdForFilterOperation=g.options[g.selectedIndex].value;
	    
	    var f = document.getElementById("filter_country_select");
	    selectedCountryIndexForFilterOperation=f.selectedIndex;
	    selectedOrgaForFilter = f.options[f.selectedIndex].value;
	    
	    if(selectedRegionIdForFilterOperation=="select"){
			  alert("Please select Hub");
			  document.getElementById("town_div").style.display="none";
			  document.getElementById("filter_select_city").innerHTML="Department: <select style=\"width:110px;\"><option>select</option></select>";
			
			return;
		}
	    /*if(selectedStateIdForFilterOperation=="select"){
			  alert("Please select Division");
			  document.getElementById("town_div").style.display="none";
			  document.getElementById("filter_select_city").innerHTML="Department: <select style=\"width:110px;\"><option>select</option></select>";
			
			return;
		}*/
	}
	else if(subFlag=="updateOperation"){
		//alert('inside getCity');
		var e = document.getElementById("update_region_select");
	    selectedRegionIndexForUpdateOperation=e.selectedIndex;
		selectedRegionIdForUpdateOperation=e.options[e.selectedIndex].value;
		//alert('selected region value is ' + selectedRegionIdForUpdateOperation);
		if(selectedRegionIndexForUpdateOperation == "select"){
			  alert("Please select Hub");
			 // document.getElementById("update_town_div").style.display="none";
			  //document.getElementById("update_select_city").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
		//alert('initial' + xmlhttp.responseText);
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		  document.getElementById("filter_select_city").innerHTML=xmlhttp.responseText;
		 
	    }

	  else if(subFlag=="updateOperation"){
		  //alert('2255' + xmlhttp.responseText);
		  document.getElementById("update_select_city").innerHTML=xmlhttp.responseText;
		 
		  if(status==0){
				 var e = document.getElementById("update_city_select");
				 e.selectedIndex=selectedCityIndexForFilterOperation;
				 //getTown(subFlag,0);
			  }
		    }
    
	  }
	  };

    if(subFlag=="filterOperation")
    	
    	//var outData="flag=get_city&subFlag="+subFlag+"&stateId="+selectedStateIdForFilterOperation+"&divisionId="+selectedRegionIdForFilterOperation+"&hubId="+selectedOrgaForFilter;
    	var outData="flag=get_city&subFlag="+subFlag+"&divisionId="+selectedRegionIdForFilterOperation+"&hubId="+selectedOrgaForFilter;
    else if(subFlag=="updateOperation")
    	//var outData="flag=get_city&subFlag="+subFlag+"&stateId="+selectedStateIdForUpdateOperation+"&divisionId="+selectedRegionIdForFilterOperation+"&hubId="+selectedOrgaForFilter;;
    	var outData="flag=get_city&subFlag="+subFlag+"&divisionId="+selectedRegionIdForUpdateOperation+"&hubId="+selectedCountryIdForUpdateOperation;
       
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}






var selectedCityIdForFilterOperation;
var selectedCityIndexForFilterOperation;
var selectedCityIdForUpdateOperation;
var selectedCityIndexForUpdateOperation;

function getTown(subFlag)
{
	//alert("Inside getTown");
	if(subFlag=="filterOperation"){
	var e = document.getElementById("filter_city_select");
	selectedCityIndexForFilterOperation=e.selectedIndex;
	selectedCityIdForFilterOperation=e.options[e.selectedIndex].value;
	
	/*var h = document.getElementById("filter_state_select");
    selectedStateIndexForFilterOperation=h.selectedIndex;
	selectedStateIdForFilterOperation = h.options[h.selectedIndex].value;*/
	
	var g = document.getElementById("filter_region_select");
    selectedRegionIndexForFilterOperation=g.selectedIndex;
    selectedRegionIdForFilterOperation=g.options[g.selectedIndex].value;
    
    var f = document.getElementById("filter_country_select");
    selectedCountryIndexForFilterOperation=f.selectedIndex;
    selectedOrgaForFilter = f.options[f.selectedIndex].value;
	
	
	if(selectedCityIdForFilterOperation=="select"){
		alert("Please select Department");
		document.getElementById("town_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_city_select");
		selectedCityIndexForUpdateOperation=e.selectedIndex;
		selectedCityIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedCityIdForUpdateOperation=="select"){
			alert("Please select Department");
			//document.getElementById("town_div").style.display="none";
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
		  document.getElementById("town_div").style.display="block";
		  document.getElementById("town_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  };
	//var outData="flag=get_town&cityId="+selectedCityIdForFilterOperation+"&divisionId="+selectedStateIdForFilterOperation+"&hubId="+selectedRegionIdForFilterOperation+"&orgId="+selectedOrgaForFilter;
	var outData="flag=get_town&deptId="+selectedCityIdForFilterOperation+"&hubId="+selectedRegionIdForFilterOperation+"&orgId="+selectedOrgaForFilter;
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


function setEmployeeDetails(townName,userName,password,designation,contactAddress,contactPhone,empType,role)
{
	//alert("inside setEmployeeDetails");
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -3)
		  {
			  alert(DATA_EXISTS);
			  document.getElementById("addtownName").focus();
		  }
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
		  {
			  alert(PROBLEM_OCCURED);
		  }
		  	document.getElementById("addtownName").value = "";
			document.getElementById("adduserName").value = "";
			document.getElementById("addpassword").value = "";
			document.getElementById("adddesignation").value = "";
			document.getElementById("addcontactAddress").value = "";
			document.getElementById("addcontactPhone").value = "";
			document.getElementById("empType").value = "";
			document.getElementById("role").value = "";
			alert("Employee Added Successfully");
		  getTown();
	    //window.location.reload();
		  //document.getElementById("State_detail").innerHTML = xmlhttp.responseText;
	    }
	  };
	/*var outData="flag=set_employee&countryId="+selectedCountryIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation
				+"&stateId="+selectedStateIdForFilterOperation+"&cityId="+selectedCityIdForFilterOperation+"&townName="+townName
				+"&userName="+userName+"&password="+password+"&designation="+designation+"&contactAddress="+contactAddress
				+"&contactPhone="+contactPhone+"&empType="+empType+"&role="+role;*/
	  var outData="flag=set_employee&countryId="+selectedCountryIdForFilterOperation+"&regionId="+selectedRegionIdForFilterOperation
		+"&cityId="+selectedCityIdForFilterOperation+"&townName="+townName
		+"&userName="+userName+"&password="+password+"&designation="+designation+"&contactAddress="+contactAddress
		+"&contactPhone="+contactPhone+"&empType="+empType+"&role="+role;
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
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
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
        		  {
        			  alert(PROBLEM_OCCURED);
        		  }
        		  getTown();
        	    //window.location.reload();
        		  //document.getElementById("State_detail").innerHTML=xmlhttp.responseText;
        	    }
        	  };
        	var outData="flag=delete_town&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }


//empId,empName,userName,password,designation,contactAddress,contactPhone,empType,role
function updateTown(empId,empName,userName,password,designation,contactAddress,contactPhone,empType,role){
	//alert('empType id is' + empType);
	//alert('role id is ' + role);
	var deptId = document.getElementById("update_city_select").value;
	//alert('selected department is' + deptId);
	
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if country "+selectedCountryId+" update "+updateSelectedCountryId+" region "+selectedRegionId+" update "+updateSelectedRegionId+" state "+selectedStateId+" update "+updateSelectedStateId+" city "+selectedCityId+" update "+updateSelectedCityId+" town "+selectedTownId+" update "+updateSelectedTownId);
		 if(selectedRegionIdForFilterOperation==selectedRegionIdForUpdateOperation){
			 // alert("it is if");
			 /*if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == 0)
			  {
				  alert(DATA_EXISTS);
			  }*/
			  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -4 || xmlhttp.responseText.replace(/^\s+|\s+$/g, '') == -5)
			  {
				  alert(PROBLEM_OCCURED);
			  }
			  document.getElementById("town_update").style.display="none";
			  document.getElementById("town_add").style.display="block";
			  getTown('filterOperation');
		  }
		 
		 else{
			//  alert("it is else");
			 window.location.reload();
		 }
	}
	};

	flag="update_town";
							
	var outData="flag="+flag+"&regionId="+selectedRegionIdForUpdateOperation+"&cityId="+deptId
	+"&townId="+empId+"&townName="+empName+"&userName="+userName+"&password="+password+"&designation="+designation+"&contactAddress="+contactAddress
	+"&contactPhone="+contactPhone+"&empType="+empType+"&role="+role; 
	//alert("script update town calling out data is "+outData);
	xmlhttp.open("POST","ManageFdOrgEmployeeController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    