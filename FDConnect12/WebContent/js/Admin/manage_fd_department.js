var selectedFdHubIdForFilterOperation;
var selectedFdHubIndexForFilterOperation;
var selectedFdHubIdForUpdateOperation;
var selectedFdHubIndexForUpdateOperation;

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

function validateAddOperation(fd_departmentName)
{   
	var fd_departmentNameVal=fd_departmentName.value;
	
	if(fd_departmentNameVal.trim()=="")
    {
        alert("Please Enter FdDepartment Name");
        fd_departmentName.focus();
        return false;
	}
	/*var n = fd_departmentNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(fd_departmentNameVal.charAt(i))){
            //alert("Enter only Alphabets");
            fd_departmentName.focus();
	    return false;
	   }
    }*/

	/*var e = document.getElementById("filter_fd_division_select");
    selectedFdDivisionIndexForFilterOperation=e.selectedIndex;
	selectedFdDivisionIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdDivisionIdForFilterOperation=="select"){
		  alert("Please select FdDivision");
		  return false;
		  }*/
		//alert('division id is' +  selectedFdHubIdForFilterOperation);  
      setFdDepartment(fd_departmentNameVal);   	
 }


	
function validateUpdateOperation(fd_departmentId,fd_departmentName)
{   
	//alert("it is updateValidate");
	var fd_departmentNameVal=fd_departmentName.value;

	var fd_departmentIdVal=fd_departmentId.value;
	
	if(fd_departmentNameVal=="")
    {
        alert("Please Enter FdDepartment Name");
        fd_departmentName.focus();
        return false;
	}
            	
	if(document.getElementById("update_fd_org_select").selectedIndex==0){
		alert("Please Select FdOrg");
        //zipCode.focus();
        return false;	
	}
            	
	if(document.getElementById("update_fd_hub_select").selectedIndex==0){
		alert("Please Select FdHub");
        return false;	
		
	}
            	
	
    /*selectedFdDivisionIndexForUpdateOperation=e.selectedIndex;
	selectedFdDivisionIdForUpdateOperation=e.options[e.selectedIndex].value;
	if(selectedFdDivisionIdForUpdateOperation=="select"){
		  alert("Please select FdDivision");
		  return false;
		  }*/
	/*if(document.getElementById("update_fd_division_select").selectedIndex==0){
		alert("Please Select FdDivision");
        return false;	
	}*/
            	
      updateFdDepartment(fd_departmentIdVal,fd_departmentNameVal);   	
 }
 

function showUpdateDiv(fd_departmentId,fd_departmentName){
	 document.getElementById("fd_department_add").style.display="none";
	 document.getElementById("fd_department_update").style.display="block";

	 var flag=0;
	 
	 getFdOrg('updateOperation',0);
	// var e = document.getElementById("update_fd_org_select");
	// e.selectedIndex=selectedFdOrgIndexForFilterOperation;
	 
	 
	 
	document.getElementById("fd_departmentName").value=fd_departmentName;
	document.getElementById("fd_departmentName").focus();
	document.getElementById("fd_departmentId").value=fd_departmentId;
}

function showAddDiv(){
	document.getElementById("fd_department_update").style.display="none";
	document.getElementById("fd_department_add").style.display="block";
}


//Get Material Group	
function getFdOrg(subFlag,status)
{
	//var y=document.getElementById("FdOrg_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation")
		  document.getElementById("filter_select_fd_org").innerHTML=xmlhttp.responseText;
		  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_fd_org").innerHTML=xmlhttp.responseText;
		  if(status==0){
			 var e = document.getElementById("update_fd_org_select");
			 e.selectedIndex=selectedFdOrgIndexForFilterOperation;
			 getFdHub(subFlag,0);
		  }
		  }
	    }
	  };
	var outData="flag=get_fd_org&subFlag="+subFlag;
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
var selectedFdOrgIdForFilterOperation;
var selectedFdOrgIndexForFilterOperation;
var selectedFdOrgIdForUpdateOperation;
var selectedFdOrgIndexForUpdateOperation;
function getFdHub(subFlag,status)
{
	if(subFlag=="filterOperation"){
	document.getElementById("fd_department_div").style.display="none";	
	var e = document.getElementById("filter_fd_org_select");
    selectedFdOrgIndexForFilterOperation=e.selectedIndex;
	selectedFdOrgIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdOrgIdForFilterOperation=="select"){
		  alert("Please select FdOrg");
		  document.getElementById("fd_department_div").style.display="none";
		  document.getElementById("filter_select_fd_hub").innerHTML="FdHub: <select style=\"width:185px;\"><option>select</option></select>";
		  //document.getElementById("filter_select_fd_division").innerHTML="FdDivision: <select style=\"width:110px;\"><option>select</option></select>";
		 

		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_fd_org_select");
	    selectedFdOrgIndexForUpdateOperation=e.selectedIndex;
		selectedFdOrgIdForUpdateOperation=e.options[e.selectedIndex].value;
		
		if(selectedFdOrgIdForUpdateOperation=="select"){
			  alert("Please select FdOrg");
			  //document.getElementById("update_fd_department_div").style.display="none";
			  document.getElementById("update_select_fd_hub").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  document.getElementById("update_select_fd_division").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
			 
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		  document.getElementById("filter_select_fd_hub").innerHTML=xmlhttp.responseText;
		  //document.getElementById("filter_select_fd_division").innerHTML="<select style=\"width:185px;margin:0 0 0 4px;\"><option>select</option></select>";
		
		  
	    }

	  else if(subFlag=="updateOperation"){
		  //alert('fdhubId for update' + xmlhttp.responseText);
		  document.getElementById("update_select_fd_hub").innerHTML=xmlhttp.responseText;
		  //document.getElementById("update_select_fd_division").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
		 
		 
		  if(status==0){
				 var e = document.getElementById("update_fd_hub_select");
				 e.selectedIndex=selectedFdHubIndexForFilterOperation;
				 getFdDivision(subFlag,0);
			  }
	    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_fd_hub&subFlag="+subFlag+"&fd_orgId="+selectedFdOrgIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_fd_hub&subFlag="+subFlag+"&fd_orgId="+selectedFdOrgIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}




/*var selectedFdHubIdForFilterOperation;
var selectedFdHubIndexForFilterOperation;
var selectedFdHubIdForUpdateOperation;
var selectedFdHubIndexForUpdateOperation;
function getFdDivision(subFlag,status)
{
	
	if(subFlag=="filterOperation"){
	document.getElementById("fd_department_div").style.display="none";		
	var e = document.getElementById("filter_fd_hub_select");
    selectedFdHubIndexForFilterOperation=e.selectedIndex;
	selectedFdHubIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdHubIdForFilterOperation=="select"){
		  alert("Please select FdHub");
		  document.getElementById("fd_department_div").style.display="none";
		  document.getElementById("filter_select_fd_division").innerHTML="FdDivision: <select style=\"width:110px;\"><option>select</option></select>";
		 
		  
		return;
	}
	}


	else if(subFlag=="updateOperation"){
		//alert("it is update fd_division up");
		var e = document.getElementById("update_fd_hub_select");
	    selectedFdHubIndexForUpdateOperation=e.selectedIndex;
		selectedFdHubIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedFdHubIdForUpdateOperation=="select"){
			  alert("Please select FdHub");
			 // document.getElementById("update_fd_department_div").style.display="none";
			  document.getElementById("update_select_fd_division").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
			  
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
		  document.getElementById("filter_select_fd_division").innerHTML=xmlhttp.responseText;
		 
		
	    }

	  else if(subFlag=="updateOperation"){
		  //alert("it is update fd_division");
		  document.getElementById("update_select_fd_division").innerHTML=xmlhttp.responseText;
		  
		 
		  if(status==0){
				 var e = document.getElementById("update_fd_division_select");
				 e.selectedIndex=selectedFdDivisionIndexForFilterOperation;
				 getFdDepartment(subFlag,0);
			  }
		    }
    
	  }
	  }

    if(subFlag=="filterOperation")
	var outData="flag=get_fd_division&subFlag="+subFlag+"&fd_hubId="+selectedFdHubIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_fd_division&subFlag="+subFlag+"&fd_hubId="+selectedFdHubIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}*/


var selectedFdDivisionIdForFilterOperation;
var selectedFdDivisionIndexForFilterOperation;
var selectedFdDivisionIdForUpdateOperation;
var selectedFdDivisionIndexForUpdateOperation;
function getFdDepartment(subFlag)
{
	if(subFlag=="filterOperation"){
	var e = document.getElementById("filter_fd_division_select");
	selectedFdDivisionIndexForFilterOperation=e.selectedIndex;
	selectedFdDivisionIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdDivisionIdForFilterOperation=="select"){
		alert("Please select FdDivision");
		document.getElementById("fd_department_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_fd_division_select");
		selectedFdDivisionIndexForUpdateOperation=e.selectedIndex;
		selectedFdDivisionIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedFdDivisionIdForUpdateOperation=="select"){
			alert("Please select FdDivision");
			//document.getElementById("fd_department_div").style.display="none";
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
		  document.getElementById("fd_department_div").style.display="block";
		  document.getElementById("fd_department_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  };
	var outData="flag=get_fd_department&fd_divisionId="+selectedFdDivisionIdForFilterOperation;
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


/*
 * Set Material Group
 */	
function setFdDepartment(fd_departmentName,divisionId)
{
	//alert("it is calling  setFdDepartment add");
	//alert('divisionId is'+divisionId);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
				 
			  alert('FD Department Added Successfully');
			  getFdDepartmentDetails('filterOperation',1);
			  document.getElementById("add_fd_department_name_input").value="";
			  document.getElementById("add_fd_department_name_input").focus();
			 
            }
		   else  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
			  alert('FD Department Already Exist');
			  document.getElementById("add_fd_department_name_input").focus();
			  
		    }
		   else{
			  alert('FD Department Addition Failed');
		    }
	    }
	  };
	//var outData="flag=set_fd_department&fd_orgId="+selectedFdOrgIdForFilterOperation+"&fd_hubId="+selectedFdHubIdForFilterOperation+"&fd_divisionId="+selectedFdDivisionIdForFilterOperation+"&fd_departmentName="+fd_departmentName;
	  var outData="flag=set_fd_department&fd_orgId="+selectedFdOrgIdForFilterOperation+"&fd_hubId="+selectedFdHubIdForFilterOperation+"&fd_departmentName="+fd_departmentName;
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteFdDepartment(tableID) {
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
        		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
       				 
        			  //getFdDepartment();
        			  getFdDepartmentDetails();
        			  }
        			  else{
        				  alert('FD Department Deletion Failed');
        			  }
        	    }
        	  };
        	var outData="flag=delete_fd_department&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateFdDepartment(fd_departmentId,fd_departmentName){
	//alert("script update fd_department calling"+fd_departmentId+fd_departmentName);
	//alert('in updatedeparm' + selectedFdHubIdForUpdateOperation);
	var xmlhttp=getXMLHttpRequestObject();
   
	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		
		 //alert("it is caliing before if fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId+" fd_hub "+selectedFdHubId+" update "+updateSelectedFdHubId+" fd_division "+selectedFdDivisionId+" update "+updateSelectedFdDivisionId+" fd_department "+selectedFdDepartmentId+" update "+updateSelectedFdDepartmentId+" fd_department "+selectedFdDepartmentId+" update "+updateSelectedFdDepartmentId);
		 //if((selectedFdOrgIdForFilterOperation==selectedFdOrgIdForUpdateOperation)&&(selectedFdHubIdForFilterOperation==selectedFdHubIdForUpdateOperation)&&(selectedFdDivisionIdForFilterOperation==selectedFdDivisionIdForUpdateOperation)){
		 if((selectedFdOrgIdForFilterOperation==selectedFdOrgIdForUpdateOperation)&&(selectedFdHubIdForFilterOperation==selectedFdHubIdForUpdateOperation)){

			 // alert("it is if");
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0")
			 {
				 //  getFdDepartment();
				 getFdDepartmentDetails('filterOperation',1);
				  showAddDiv();
			 }
			 else{
				  alert('FD Department Updation Failed');
		  	  }
		 }
		 else{
			//  alert("it is else");
			 window.location.reload();
		 }
	}
	};


	flag="update_fd_department";
	//alert('FdDvision' + selectedFdDivisionIdForUpdateOperation);
	var outData="flag="+flag+"&fd_orgId="+selectedFdOrgIdForUpdateOperation+"&fd_hubId="+selectedFdHubIdForUpdateOperation+"&fd_departmentId="+fd_departmentId+"&fd_departmentName="+fd_departmentName; 
//	var outData="flag="+flag+"&fd_orgId="+selectedFdOrgIdForUpdateOperation+"&fd_hubId="+selectedFdHubIdForUpdateOperation+"&fd_divisionId="+selectedFdDivisionIdForUpdateOperation+"&fd_departmentId="+fd_departmentId+"&fd_departmentName="+fd_departmentName;
	
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}



function getFdDepartmentDetails(subFlag)
{

	if(subFlag=="filterOperation"){
	var e = document.getElementById("filter_fd_hub_select");
	selectedFdHubIndexForFilterOperation=e.selectedIndex;
	selectedFdHubIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdHubIdForFilterOperation=="select"){
		alert("Please select FdHub");
		document.getElementById("filter_select_fd_division").innerHTML="FdDivision: <select style=\"width:185px;\"><option>select</option></select>";
		document.getElementById("fd_department_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_fd_hub_select");
		selectedFdHubIndexForUpdateOperation=e.selectedIndex;
		selectedFdHubIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedFdHubIdForUpdateOperation=="select"){
			alert("Please select FdHub");
			//document.getElementById("fd_department_div").style.display="none";
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
		  document.getElementById("fd_department_div").style.display="block";
		  document.getElementById("fd_department_detail").innerHTML=xmlhttp.responseText;
		  getFdDivision(subFlag,1);
	    }
	  };
	var outData="flag=get_fd_department_details&fd_hubId="+selectedFdHubIdForFilterOperation;
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

function getFdDivision(subFlag,status)
{
	
	if(subFlag=="filterOperation"){
	/*document.getElementById("fd_department_div").style.display="none";		
	var e = document.getElementById("filter_fd_hub_select");
    selectedFdHubIndexForFilterOperation=e.selectedIndex;
	selectedFdHubIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdHubIdForFilterOperation=="select"){
		  alert("Please select FdHub");
		  document.getElementById("fd_department_div").style.display="none";
		  document.getElementById("filter_select_fd_division").innerHTML="FdDivision: <select style=\"width:110px;\"><option>select</option></select>";
		 
		  
		return;
	}*/
	}


	else if(subFlag=="updateOperation"){
		//alert("it is update fd_division up");
		var e = document.getElementById("update_fd_hub_select");
	    selectedFdHubIndexForUpdateOperation=e.selectedIndex;
		selectedFdHubIdForUpdateOperation=e.options[e.selectedIndex].value;
		//alert('selected hubId for update' + selectedFdHubIdForUpdateOperation);
		if(selectedFdHubIdForUpdateOperation=="select"){
			  alert("Please select FdHub");
			 // document.getElementById("update_fd_department_div").style.display="none";
			  document.getElementById("update_select_fd_division").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			 
			  
			return;
		}
		}
	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  
		  if(subFlag=="filterOperation"){
//		  document.getElementById("filter_select_fd_division").innerHTML=xmlhttp.responseText;
			
	    }

	  else if(subFlag=="updateOperation"){
		  //alert("it is update fd_division");
		  document.getElementById("update_select_fd_division").innerHTML=xmlhttp.responseText;
		  
		 
		  if(status==0){
				 var e = document.getElementById("update_fd_division_select");
				 e.selectedIndex=selectedFdDivisionIndexForFilterOperation;
//				 getFdDepartment(subFlag,0);
				 getFdDepartmentDetails(subFlag,0);
			  }
		    }
    
	  }
	  };

	  //alert('selectedFdHubForFilterOperation' + selectedFdHubIdForFilterOperation);
	if(subFlag=="filterOperation")
	var outData="flag=get_fd_division1&subFlag="+subFlag+"&fd_hubId="+selectedFdHubIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_fd_division1&subFlag="+subFlag+"&fd_hubId="+selectedFdHubIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageFdDepartmentController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}
        
    