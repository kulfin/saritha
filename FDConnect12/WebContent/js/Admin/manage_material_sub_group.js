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

function validateAddOperation(material_sub_groupName,material_sub_groupCode)
{   
	var material_sub_groupNameVal=material_sub_groupName.value;
	var material_sub_groupCodeVal=material_sub_groupCode.value;
	//alert('validate');
    	if(material_sub_groupNameVal.trim()=="" )
        {
            alert("Please Enter MaterialSubGroup Name");
            material_sub_groupName.focus();
            return false;
    	}
    	if(material_sub_groupNameVal.match(/[-,._'"]{2}/i))
    	{
    		alert("Special Characters are not allowed ");
            material_sub_groupName.focus();
            return false;
    	}
    		
//        var n =  material_sub_groupNameVal.toString().length;

       /* for(var i = 0; i < n; i++){
            if (! isChar( material_sub_groupNameVal.charAt(i))){
                //alert("Enter only Alphabets for Sub Group Name");
                material_sub_groupName.focus();
                return false;
			}
         }*/

            	
    	if(material_sub_groupCodeVal.trim()==""){
    		alert("Please Enter Material Group Code");
    		material_sub_groupCode.focus();
    		return false;
    	}
    	if(isNaN(material_sub_groupCodeVal)){
    		alert("Please Enter Numeric Value For Material Group Code");
    		material_sub_groupCode.focus();
    		return false;
    	}
    	if(material_sub_groupCodeVal.length>2){
    		alert("Maximum Two Digit is Allowed For Material Group Code");
    		material_sub_groupCode.focus();
    		return false;
    	}

    	var ajaxResponse;
    	var outData="flag=validate_material_sub_group_code&materialGroupId="+selectedMaterialGroupIdForFilterOperation+"&materialSubGroupCode="+material_sub_groupCodeVal;
    		$.ajax( {
    			type : 'POST',
    			url : 'ManageMaterialSubGroupController.jsp',
    			data : outData,
    			success : function(data) {
    			ajaxResponse=data;
    			
    			},
    		  async:   false
    		});
            		
        if (ajaxResponse.replace(/^\s+|\s+$/g, '') == "-3") {
		alert('Material Sub Group Code Already Exist');
		material_sub_groupCode.focus();
		return false;
	    } 		
            	
      setMaterialSubGroup(material_sub_groupNameVal,material_sub_groupCodeVal);   	
         
 }



function validateUpdateOperation(material_sub_groupId,material_sub_groupName,material_sub_groupCode)
{   
	//alert("it is updateValidate");
	var material_sub_groupNameVal=material_sub_groupName.value;

	var material_sub_groupIdVal=material_sub_groupId.value;
	var material_sub_groupCodeVal=material_sub_groupCode.value;
            	
            	
	if(document.getElementById("update_material_group_select").selectedIndex==0){
		alert("Please Select Material Group");
        //zipCode.focus();
        return false;	
		
	}
   if(material_sub_groupNameVal=="")
    {
        alert("Please Enter MaterialSubGroup Name");
        material_sub_groupName.focus();
        return false;
        
	}
    if(material_sub_groupCodeVal!=materialSubGroupCode){
    	if(material_sub_groupCodeVal==""){
    		alert("Please Enter Material Group Code");
    		material_sub_groupCode.focus();
    		return false;
    	}
    	if(isNaN(material_sub_groupCodeVal)){
    		alert("Please Enter Numeric Value For Material Group Code");
    		material_sub_groupCode.focus();
    		return false;
    	}
    	if(material_sub_groupCodeVal.length>2){
    		alert("Maximum Two Digit is Allowed For Material Group Code");
    		material_sub_groupCode.focus();
    		return false;
    	}

    	var ajaxResponse;
    	var outData="flag=validate_material_sub_group_code&materialGroupId="
    		+selectedMaterialGroupIdForUpdateOperation+"&materialSubGroupCode="+material_sub_groupCodeVal;
    		$.ajax( {
    			type : 'POST',
    			url : 'ManageMaterialSubGroupController.jsp',
    			data : outData,
    			success : function(data) {
    			ajaxResponse=data;
    				//alert(data);
    			},
    		  async:   false
    		});
            		
        if (ajaxResponse.replace(/^\s+|\s+$/g, '') == "-3") {
        	alert('Material Sub Group Code Already Exist');
        	material_sub_groupCode.focus();
        	return false;
	    } 		
 }  
      updateMaterialSubGroup(material_sub_groupIdVal,material_sub_groupNameVal,material_sub_groupCodeVal);   	
 }


 
var materialSubGroupCode;
function showUpdateDiv(material_sub_groupId,material_sub_groupName,material_sub_groupCode){
	 document.getElementById("material_sub_group_add").style.display="none";
	 document.getElementById("material_sub_group_update").style.display="block";
    //alert(material_sub_groupCode);
	 var flag=0;
	 
	 getMaterialGroup('updateOperation',0);
	// var e = document.getElementById("update_material_group_select");
	// e.selectedIndex=selectedCountryIndexForFilterOperation;
	 
	 materialSubGroupCode=material_sub_groupCode;
	 
	document.getElementById("update_material_sub_group_name_input").value=material_sub_groupName;
	
	document.getElementById("update_material_sub_group_id_input").value=material_sub_groupId;
	 
	document.getElementById("update_material_sub_group_code_input").value=material_sub_groupCode;

	
}

function showAddDiv(){
	document.getElementById("material_sub_group_update").style.display="none";
	document.getElementById("material_sub_group_add").style.display="block";
	


	 

	
}


//Get Material Group	
function getMaterialGroup(subFlag,status)
{
	
	//var y=document.getElementById("MaterialGroup_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	
		  if(subFlag=="filterOperation")
		  document.getElementById("filter_select_material_group").innerHTML=xmlhttp.responseText;
		  else if(subFlag=="updateOperation"){
		  document.getElementById("update_select_material_group").innerHTML=xmlhttp.responseText;
		  if(status==0){
			 var e = document.getElementById("update_material_group_select");
			 e.selectedIndex=selectedMaterialGroupIndexForFilterOperation;
			 getMaterialSubGroup(subFlag,0);
		  }
		  }
	    }
	  }
	var outData="flag=get_material_group&subFlag="+subFlag;
	xmlhttp.open("POST","ManageMaterialSubGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


// Get Material Sub Group	







var selectedMaterialGroupIdForFilterOperation;
var selectedMaterialGroupIndexForFilterOperation;
var selectedMaterialGroupIdForUpdateOperation;
var selectedMaterialGroupIndexForUpdateOperation;
function getMaterialSubGroup(subFlag)
{
	//alert("calling 1");
	if(subFlag=="filterOperation"){
		
	var e = document.getElementById("filter_material_group_select");

	selectedMaterialGroupIndexForFilterOperation=e.selectedIndex;
	selectedMaterialGroupIdForFilterOperation=e.options[e.selectedIndex].value;
	
	if(selectedMaterialGroupIdForFilterOperation=="select"){
		alert("Please select MaterialGroup");
		document.getElementById("material_sub_group_div").style.display="none";
		return;
	}
	}

	if(subFlag=="updateOperation"){
		var e = document.getElementById("update_material_group_select");
		selectedMaterialGroupIndexForUpdateOperation=e.selectedIndex;
		selectedMaterialGroupIdForUpdateOperation=e.options[e.selectedIndex].value;
		if(selectedMaterialGroupIdForUpdateOperation=="select"){
			alert("Please select MaterialGroup");
			//document.getElementById("material_group_div").style.display="none";
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
		 
		  document.getElementById("material_sub_group_div").style.display="block";
		  document.getElementById("material_sub_group_detail").innerHTML=xmlhttp.responseText;
		  
	    }
	  }
	var outData="flag=get_material_sub_group&material_groupId="+selectedMaterialGroupIdForFilterOperation;
	xmlhttp.open("POST","ManageMaterialSubGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
	
}


//Set Material Group	
function setMaterialSubGroup(material_sub_groupName,material_sub_groupCode)
{
	//alert("it is calling  add");
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
				 
			  getMaterialSubGroup();
			  document.getElementById("add_material_sub_group_name_input").value="";
			  document.getElementById("add_material_sub_group_code_input").value="";
			 
			  }
			  else  if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="-3"){
			  alert('Material Group Already Exist');
			  }
			  else{
			  alert('Material Sub Group Addition Failed');
			  }
	    //window.location.reload();
		  //document.getElementById("MaterialSubGroup_detail").innerHTML=xmlhttp.responseText;
	    }
	  }
	var outData="flag=set_material_sub_group&material_groupId="+selectedMaterialGroupIdForFilterOperation+
	"&material_sub_groupName="+material_sub_groupName+"&material_sub_groupCode="+material_sub_groupCode;
	xmlhttp.open("POST","ManageMaterialSubGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(outData);
}


//Delete Material Group	
function deleteMaterialSubGroup(tableID) {
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
     				 
        			  getMaterialSubGroup();
        			 
        			 
        			  }
        			
        			  else{
        			  alert('Material Sub Group Deletion Failed');
        			  }
        	    }
        	  }
        	//alert(outData);
        	var outData="flag=delete_material_sub_group&selectedValues="+selectedValues;
        	xmlhttp.open("POST","ManageMaterialSubGroupController.jsp",true);
        	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	xmlhttp.send(outData);      
            
        }



function updateMaterialSubGroup(material_sub_groupId,material_sub_groupName,material_sub_groupCode){
	//alert("script update material_sub_group calling"+material_sub_groupId+material_sub_groupName+zipCode);
	var xmlhttp=getXMLHttpRequestObject();

	xmlhttp.onreadystatechange=function(){
	 if(xmlhttp.readyState==4 && xmlhttp.status==200){
		 //alert("it is caliing before if material_group "+selectedMaterialGroupId+" update "+updateSelectedMaterialGroupId+" material_sub_group "+selectedMaterialSubGroupId+" update "+updateSelectedMaterialSubGroupId+" material_sub_group "+selectedMaterialSubGroupId+" update "+updateSelectedMaterialSubGroupId+" material_sub_group "+selectedMaterialSubGroupId+" update "+updateSelectedMaterialSubGroupId+" material_sub_group "+selectedMaterialSubGroupId+" update "+updateSelectedMaterialSubGroupId);
		 if((selectedMaterialGroupIdForFilterOperation==selectedMaterialGroupIdForUpdateOperation)){
			
			 if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '')=="0"){
				 
				  getMaterialSubGroup();
				  showAddDiv();
				  }
				
				  else{
				  alert('Material Sub Group Updation Failed');
				  }
		  
		  }
		 
		 else{

			//  alert("it is else");
			 window.location.reload();
		 }
	}
	}


	flag="update_material_sub_group";
	
	var outData="flag="+flag+"&material_groupId="+selectedMaterialGroupIdForUpdateOperation+"&material_sub_groupId="
	+material_sub_groupId+"&material_sub_groupName="+material_sub_groupName+"&material_sub_groupCode="+material_sub_groupCode; 
	//alert("script update material_sub_group calling out data is "+outData);
	xmlhttp.open("POST","ManageMaterialSubGroupController.jsp",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("Content-length", outData .length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(outData);
	
}
        
    