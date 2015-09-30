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

function validateAddOperation(fd_divisionName)
{   
	var fd_divisionNameVal=fd_divisionName.value;
            	
	if(fd_divisionNameVal.trim()=="")
    {
        alert("Please Enter FdDivision Name");
        fd_divisionName.focus();
        return false;
	}
	/*var n = fd_divisionNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(fd_divisionNameVal.charAt(i))){
            alert("Enter only Alphabets");
            fd_divisionName.focus();
	    return false;
	   }
    }*/

    setFdDivision(fd_divisionNameVal);   	
 }



function validateUpdateOperation(fd_divisionId,fd_divisionName)
{   
	//alert("it is updateValidate");
	var fd_divisionNameVal=fd_divisionName.value;

	var fd_divisionIdVal=fd_divisionId.value;
            	
	if(fd_divisionNameVal=="")
    {
        alert("Please Enter FdDivision Name");
        fd_divisionName.focus();
        return false;
	}
	
	if(document.getElementById("update_fd_org_select").selectedIndex==0){
		alert("Please Select FdOrg");
        //zipCode.focus();
        return false;	
	}
	
/*	if(document.getElementById("update_fd_hub_select").selectedIndex==0){
		alert("Please Select FdHub");
        //zipCode.focus();
        return false;	
	}*/
            	
      updateFdDivision(fd_divisionIdVal,fd_divisionNameVal);   	
         
 }


 

function showUpdateDiv(fd_divisionId,fd_divisionName){
	 document.getElementById("fd_division_add").style.display="none";
	 document.getElementById("fd_division_update").style.display="block";

	 var flag=0;
	 
	 getFdOrg('updateOperation',0);
	// var e = document.getElementById("update_fd_org_select");
	// e.selectedIndex=selectedFdOrgIndexForFilterOperation;
	document.getElementById("fd_divisionName").value=fd_divisionName;
	document.getElementById("fd_divisionId").value=fd_divisionId;
	 
}

function showAddDiv(){
	document.getElementById("fd_division_update").style.display="none";
	document.getElementById("fd_division_add").style.display="block";
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
			 //getFdHub(subFlag,0);
		  }
		  }
	    }
	  };
	var outData="flag=get_fd_org&subFlag="+subFlag;
	xmlhttp.open("POST","ManageFdDivisionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	
/*var selectedFdOrgIdForFilterOperation;
var selectedFdOrgIndexForFilterOperation;
var selectedFdOrgIdForUpdateOperation;
var selectedFdOrgIndexForUpdateOperation;
function getFdHub(subFlag,status)
{
	if(subFlag=="filterOperation"){
	document.getElementById("fd_division_div").style.display="none";	
	var e = document.getElementById("filter_fd_org_select");
    selectedFdOrgIndexForFilterOperation=e.selectedIndex;
	selectedFdOrgIdForFilterOperation=e.options[e.selectedIndex].value;
	if(selectedFdOrgIdForFilterOperation=="select"){
		  alert("Please select FdOrg");
		  document.getElementById("fd_division_div").style.display="none";
		  document.getElementById("filter_select_fd_hub").innerHTML="FdHub: <select style=\"width:110px;\"><option>select</option></select>";
		  
		 

		return;
	}
	}


	else if(subFlag=="updateOperation"){
		var e = document.getElementById("update_fd_org_select");
	    selectedFdOrgIndexForUpdateOperation=e.selectedIndex;
		selectedFdOrgIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedFdOrgIdForUpdateOperation=="select"){
			  alert("Please select FdOrg");
			  //document.getElementById("update_fd_division_div").style.display="none";
			  document.getElementById("update_select_fd_hub").innerHTML="<select style=\"width:120px;\"><option>select</option></select>";
			  
			 
			 
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
		 
		
		  
	    }

	  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_fd_hub").innerHTML=xmlhttp.responseText;
		 
		 
		 
		  if(status==0){
			//  alert("it is fd_hub 1");
				 var e = document.getElementById("update_fd_hub_select");
				 e.selectedIndex=selectedFdHubIndexForFilterOperation;
				 getFdDivision(subFlag);
				// alert("it is fd_hub 2");
			  }
	    }
    
	  }
	  };

    if(subFlag=="filterOperation")
	var outData="flag=get_fd_hub&subFlag="+subFlag+"&fd_orgId="+selectedFdOrgIdForFilterOperation;
    else if(subFlag=="updateOperation")
    var outData="flag=get_fd_hub&subFlag="+subFlag+"&fd_orgId="+selectedFdOrgIdForUpdateOperation; 
       
	xmlhttp.open("POST","ManageFdDivisionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}*/


var selectedFdOrgIdForFilterOperation;
var selectedFdOrgIndexForFilterOperation;
var selectedFdOrgIdForUpdateOperation;
var selectedFdOrgIndexForUpdateOperation;
function getFdDivision(subFlag)
{
	//alert("calling 1");
	if(subFlag=="filterOperation"){
		var e = document.getElementById("filter_fd_org_select");
	    selectedFdOrgIndexForFilterOperation=e.selectedIndex;
		selectedFdOrgIdForFilterOperation=e.options[e.selectedIndex].value;
		if(selectedFdOrgIdForFilterOperation=="select"){
		  alert("Please select FdOrg");
		  document.getElementById("fd_division_div").style.display="none";
		  //document.getElementById("filter_select_fd_hub").innerHTML="FdHub: <select style=\"width:110px;\"><option>select</option></select>";
		return;
		}
	
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_fd_org_select");
	    selectedFdOrgIndexForUpdateOperation=e.selectedIndex;
		selectedFdOrgIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedFdOrgIdForUpdateOperation=="select"){
			  alert("Please select FdOrg");
			  
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
		 
		  document.getElementById("fd_division_div").style.display="block";
		  document.getElementById("fd_division_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  };
	var outData="flag=get_fd_division&fd_orgId="+selectedFdOrgIdForFilterOperation;
	xmlhttp.open("POST","ManageFdDivisionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setFdDivision(fd_divisionName)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  //alert(xmlhttp.responseText);
			  getFdDivision();
			  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
			  getFdDivision();
			  document.getElementById("add_fd_division_name_input").value="";
			  document.getElementById("add_fd_division_name_input").focus();
			 
	        }
		   else  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
			  alert('FD Division Already Exist');
			  document.getElementById("add_fd_division_name_input").focus();
			  return false;
		    }
		   else{
			  alert('FD Division Addition Failed');
			  document.getElementById("add_fd_division_name_input").value="";
		    }
	    }
	  };
	//var outData="flag=set_fd_division&fd_orgId="+selectedFdOrgIdForFilterOperation+"&fd_hubId="+selectedFdHubIdForFilterOperation+"&fd_divisionName="+fd_divisionName;
	var outData="flag=set_fd_division&fd_orgId="+selectedFdOrgIdForFilterOperation+"&fd_divisionName="+fd_divisionName;
	xmlhttp.open("POST","ManageFdDivisionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteFdDivision(tableID) {
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
       				 
        			  getFdDivision();
        			 
        			 
        			  }
        			
        			  else{
        			  alert('FD Division Deletion Failed');
        			  }
        	    }
        	  }
        	var outData="flag=delete_fd_division&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageFdDivisionController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateFdDivision(fd_divisionId,fd_divisionName){
	//alert("script update fd_division calling"+fd_divisionId+fd_divisionName+zipCode);
	getFdDivision('updateOperation');
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId+" fd_hub "+selectedFdHubId+" update "+updateSelectedFdHubId+" fd_division "+selectedFdDivisionId+" update "+updateSelectedFdDivisionId+" fd_division "+selectedFdDivisionId+" update "+updateSelectedFdDivisionId+" fd_division "+selectedFdDivisionId+" update "+updateSelectedFdDivisionId);
//		 if((selectedFdOrgIdForFilterOperation==selectedFdOrgIdForUpdateOperation)&&(selectedFdHubIdForFilterOperation==selectedFdHubIdForUpdateOperation)){
		 if(selectedFdOrgIdForFilterOperation==selectedFdOrgIdForUpdateOperation){

			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
				 
				  getFdDivision();
				  showAddDiv();
				  }
				
				  else{
				  alert('FD Division Updation Failed');
				  }
		  
		  }
		 
		 else{

			//  alert("it is else");
			 window.location.reload();
		 }
	}
	}


	flag="update_fd_division";
	
//	var outData="flag="+flag+"&fd_orgId="+selectedFdOrgIdForUpdateOperation+"&fd_hubId="+selectedFdHubIdForUpdateOperation+"&fd_divisionId="+fd_divisionId+"&fd_divisionName="+fd_divisionName;
	var outData="flag="+flag+"&fd_orgId="+selectedFdOrgIdForUpdateOperation+"&fd_divisionId="+fd_divisionId+"&fd_divisionName="+fd_divisionName;
	//alert("script update fd_division calling out data is "+outData);
	xmlhttp.open("POST","ManageFdDivisionController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData.length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    