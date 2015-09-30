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

function validateAddOperation(material_groupName,material_groupCode)
{   
	var material_groupNameVal=material_groupName.value;
	var material_groupCodeVal=material_groupCode.value;
	

	 
            	
            	if(material_groupNameVal.trim()=="")
                {
                    alert("Please Enter Material Group Name");
                    material_groupName.focus();
                    return false;
                    
            	}
            /*	var n = material_groupNameVal.toString().length;

                for(var i = 0; i < n; i++){
                    if (! isChar(material_groupNameVal.charAt(i))){
                        alert("Enter only Alphabets for Group Name");
                        material_groupName.focus();
                return false;
               }
                }*/

            	
            	if(material_groupCodeVal.trim()==""){
            		alert("Please Enter Material Group Code");
            		material_groupCode.focus();
            		return false;
            	}
            	if(isNaN(material_groupCodeVal)){
            		alert("Please Enter Numeric Value For Material Group Code");
            		material_groupCode.focus();
            		return false;
            	}
            	if(material_groupCodeVal.length>2){
            		alert("Maximum Two Digit is Allowed For Material Group Code");
            		material_groupCode.focus();
            		return false;
            	}

            	var ajaxResponse;
            	var outData="flag=validate_material_group_code&materialGroupCode="+material_groupCodeVal;
            		$.ajax( {
            			type : 'POST',
            			url : 'ManageMaterialGroupController.jsp',
            			data : outData,
            			success : function(data) {
            			ajaxResponse=data;
            				
            			},
            		  async:   false
            		});
            		
        if (ajaxResponse.replace(/^\s+|\s+$/g, '') == "-3") {
		alert('Material Group Code Already Exist');
		material_groupCode.focus();
		return false;
	    } 		
            	
      setMaterialGroup(material_groupNameVal,material_groupCodeVal);  
   	
         
 }



function validateUpdateOperation(material_groupId,material_groupName,material_groupCode)
{   
	//alert("it is updateValidate");
	

	var material_groupIdVal=material_groupId.value;
	
	var material_groupNameVal=material_groupName.value;
	var material_groupCodeVal=material_groupCode.value;
	

	 
            	
            	if(material_groupNameVal=="")
                {
                    alert("Please Enter Material Group Name");
                    material_groupName.focus();
                    return false;
                    
            	}
            	
            	if(material_groupCodeVal!=materialGroupCode){
            	
            	
            	
            	if(material_groupCodeVal==""){
            		alert("Please Enter Material Group Code");
            		material_groupCode.focus();
            		return false;
            	}
            	if(isNaN(material_groupCodeVal)){
            		alert("Please Enter Numeric Value For Material Group Code");
            		material_groupCode.focus();
            		return false;
            	}
            	if(material_groupCodeVal.length>2){
            		alert("Maximum Two Digit is Allowed For Material Group Code");
            		material_groupCode.focus();
            		return false;
            	}

            	var ajaxResponse;
            	var outData="flag=validate_material_group_code&materialGroupCode="+material_groupCodeVal;
            		$.ajax( {
            			type : 'POST',
            			url : 'ManageMaterialGroupController.jsp',
            			data : outData,
            			success : function(data) {
            			ajaxResponse=data;
            				
            			},
            		  async:   false
            		});
            		
        if (ajaxResponse.replace(/^\s+|\s+$/g, '') == "-3") {
		alert('Material Group Code Already Exist');
		material_groupCode.focus();
		return false;
	    } 		
            	
        }  
            	
      updateMaterialGroup(material_groupIdVal,material_groupNameVal,material_groupCodeVal);   	
         
 }


 
var materialGroupCode;
function showUpdateDiv(material_groupId,material_groupName,material_groupCode){
	 document.getElementById("material_group_add").style.display="none";
	 document.getElementById("material_group_update").style.display="block";
     materialGroupCode=material_groupCode;
	// var flag=0;
	 
	 //getMaterialGroup('updateOperation',0);
	// var e = document.getElementById("update_material_group_select");
	// e.selectedIndex=selectedMaterialGroupIndexForFilterOperation;
	 
	 
	 
	document.getElementById("update_material_group_name_input").value=material_groupName;
	
	document.getElementById("update_material_group_id_input").value=material_groupId;
	 
	document.getElementById("update_material_group_code_input").value=material_groupCode;

	
}

function showAddDiv(){
	document.getElementById("material_group_update").style.display="none";
	document.getElementById("material_group_add").style.display="block";
	


	 

	
}




// Get Material Sub Group	








function getMaterialGroup(subFlag)
{



	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  //alert(xmlhttp.responseText);
		 
		  document.getElementById("material_group_div").style.display="block";
		  document.getElementById("material_group_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_material_group";
	xmlhttp.open("POST","ManageMaterialGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

// validate material Group Code
function validateMaterialGroupCode(materialGroupCode)
{
var materialGroupCodeVal=materialGroupCode.value;

if(materialGroupCodeVal==""){
	alert("Please Enter Material Group Code");
	materialGroupCode.focus();
	return false;
}
if(isNaN(materialGroupCodeVal)){
	alert("Please Enter Numeric Value For Material Group Code");
	materialGroupCode.focus();
	return false;
}


	
    var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
				 
			
			 
			 }
			  else  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
			  alert('Material Group Code Already Exist');
			  materialGroupCode.focus();
			  return false;
			  }
			
		  
	    }
	  }
	var outData="flag=validate_material_group_code&materialGroupCode="+materialGroupCodeVal;
	xmlhttp.open("POST","ManageMaterialGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}

//Set Material Group	
function setMaterialGroup(material_groupName,material_groupCode)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
		 
		  getMaterialGroup();
		  document.getElementById("add_material_group_name_input").value="";
		  document.getElementById("add_material_group_code_input").value="";
		 
		  }
		  else  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
		  alert('Material Group Already Exist');
		  }
		  else{
		  alert('Material Group Addition Failed');
		  }
	
	    }
	  }
	var outData="flag=set_material_group&material_groupName="+material_groupName+"&material_groupCode="+material_groupCode;
	xmlhttp.open("POST","ManageMaterialGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteMaterialGroup(tableID) {
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
        				 
        			  getMaterialGroup();
        			 
        			 
        			  }
        			
        			  else{
        			  alert('Material Group Deletion Failed');
        			  }
        	    }
        	  }
        	var outData="flag=delete_material_group&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageMaterialGroupController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateMaterialGroup(material_groupId,material_groupName,material_groupCode){
	//alert("script update material_group calling"+material_groupId+material_groupName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if material_group "+selectedMaterialGroupId+" update "+updateSelectedMaterialGroupId+" material_group "+selectedMaterialGroupId+" update "+updateSelectedMaterialGroupId+" material_group "+selectedMaterialGroupId+" update "+updateSelectedMaterialGroupId+" material_group "+selectedMaterialGroupId+" update "+updateSelectedMaterialGroupId+" material_group "+selectedMaterialGroupId+" update "+updateSelectedMaterialGroupId);
	
		 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
			 
			  getMaterialGroup();
			  showAddDiv();
			  }
			
			  else{
			  alert('Material Group Updation Failed');
			  }
         			 
		  
		
	
	}
	}


	flag="update_material_group";
	
	var outData="flag="+flag+"&material_groupId="+material_groupId+"&material_groupName="+material_groupName+
	"&material_groupCode="+material_groupCode; 
	//alert("script update material_group calling out data is "+outData);
	xmlhttp.open("POST","ManageMaterialGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    