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

function validateAddOperation(fd_orgName,fd_orgDetail)
{   
	var fd_orgNameVal=fd_orgName.value;
	var fd_orgDetailVal=fd_orgDetail.value;
            	
	if(fd_orgNameVal.trim()=="")
    {
        alert("Please Enter FdOrg Name");
        fd_orgName.focus();
        return false;
        
	}
/*	var n = fd_orgNameVal.toString().length;

    for(var i = 0; i < n; i++){
        if (!isChar(fd_orgNameVal.charAt(i))){
            alert("Enter only Alphabets for Name");
            fd_orgName.focus();
        return false;
       }
    }*/
	
	if(fd_orgDetailVal.trim()=="")
    {
        alert("Please Enter FdOrg Name");
        fd_orgDetail.focus();
        return false;
	}
/*	var n =fd_orgDetailVal.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(fd_orgDetailVal.charAt(i))){
            alert("Enter only Alphabets for Detail");
            fd_orgDetail.focus();
        return false;
       }
    }
*/
            	
     setFdOrg(fd_orgNameVal,fd_orgDetailVal);   	
         
 }



function validateUpdateOperation(fd_orgId,fd_orgName,fd_orgDetail)
{   
	//alert("it is updateValidate");
	var fd_orgNameVal=fd_orgName.value;

	var fd_orgIdVal=fd_orgId.value;
	var fd_orgDetailVal=fd_orgDetail.value;
	 
            	
            	if(fd_orgNameVal=="")
                {
                    alert("Please Enter FdOrg Name");
                    fd_orgName.focus();
                    return false;
                    
            	}
            	
             	
            	if(fd_orgDetailVal=="")
                {
                    alert("Please Enter FdOrg Detail");
                    fd_orgDetail.focus();
                    return false;
                    
            	}
            	
            	
            
            	
            
            	
            
           
            
            	
      updateFdOrg(fd_orgIdVal,fd_orgNameVal,fd_orgDetailVal);   	
         
 }


 

function showUpdateDiv(fd_orgId,fd_orgName,fd_orgDetail){
	 document.getElementById("fd_org_add").style.display="none";
	 document.getElementById("fd_org_update").style.display="block";

	 var flag=0;
	 
	 getFdOrg('updateOperation',0);
	// var e = document.getElementById("update_fd_org_select");
	// e.selectedIndex=selectedFdOrgIndexForFilterOperation;
	 
	 
	 
	document.getElementById("fd_orgName").value=fd_orgName;
	document.getElementById("fd_orgDetail").value=fd_orgDetail;
	
	document.getElementById("fd_orgId").value=fd_orgId;
	 

	
}

function showAddDiv(){
	document.getElementById("fd_org_update").style.display="none";
	document.getElementById("fd_org_add").style.display="block";
	


	 

	
}




// Get Material Sub Group	








function getFdOrg(subFlag)
{



	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  document.getElementById("fd_org_div").style.display="block";
		  document.getElementById("fd_org_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_fd_org";
	xmlhttp.open("POST","ManageFdOrgController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setFdOrg(fd_orgName,fd_orgDetail)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
			  getFdOrg();
			  document.getElementById("add_fd_org_name_input").value="";
			  document.getElementById("add_fd_org_name_input").focus();
			  document.getElementById("add_fd_org_detail_input").value="";
            }
		   else  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
			  alert('FD Organisation Already Exist');
			  document.getElementById("add_fd_org_name_input").focus();
			  return false;
		    }
		   else{
			  alert('FD Organisation Addition Failed');
		    }
	    }
	  };
	var outData="flag=set_fd_org&fd_orgName="+fd_orgName+"&fd_orgDetail="+fd_orgDetail;
	xmlhttp.open("POST","ManageFdOrgController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteFdOrg(tableID) {
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
      				 
        			  getFdOrg();
        			 
        			 
        			  }
        			
        			  else{
        			  alert('FD Organisation Deletion Failed');
        			  }
        	    }
        	  }
        	var outData="flag=delete_fd_org&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageFdOrgController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateFdOrg(fd_orgId,fd_orgName,fd_orgDetail){
	//alert("script update fd_org calling"+fd_orgId+fd_orgName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId+" fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId+" fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId+" fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId+" fd_org "+selectedFdOrgId+" update "+updateSelectedFdOrgId);
	
			 // alert("it is if");
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
			 
			  getFdOrg();
			  showAddDiv();
			  }
			
			  else{
			  alert('FD Organisation Updation Failed');
			  }
		  
		
	
	}
	}


	flag="update_fd_org";
	
	var outData="flag="+flag+"&fd_orgId="+fd_orgId+"&fd_orgName="+fd_orgName+"&fd_orgDetail="+fd_orgDetail; 
	//alert("script update fd_org calling out data is "+outData);
	xmlhttp.open("POST","ManageFdOrgController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    