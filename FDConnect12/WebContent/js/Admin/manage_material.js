//Get Material Group	
var getMaterialGroup = function(flag) {
	//alert("getMaterialGroup"+flag);
	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'get_material_group'
		},
		success : function(data) {
			if(flag=="filterOperation")
			 $('#filter_material_group_select').html(data);
			if(flag=="addOperation")
			 $('#add_material_group_select').html(data);
			if(flag=="updateOperation")
				 $('#update_material_group_select').html(data);
			
		},
	  async:   false
	});
};


//Get Material Sub Group
var filterMaterialGroupId;
var filterMaterialGroupName;
var getMaterialSubGroup = function(flag) {
	
	var materialGroupId;
	if(flag=="filterOperation"){
	  materialGroupId= $('#filter_material_group_select').val();
	  filterMaterialGroupName = $('#filter_material_group_select option:selected').html();
	  filterMaterialGroupId=materialGroupId;
	  $('#material_div').css('display', 'none');
	  if(materialGroupId=="select"){
	    $('#filter_material_sub_group_select').html('<option>select</option>');
	    return;
	  }
	 }
	if(flag=="addOperation"){
		  materialGroupId= $('#add_material_group_select').val();
	      if(materialGroupId=="select"){
		    $('#add_material_sub_group_select').html('<option>select</option>');
		    return;
		  }
	  }
	if(flag=="updateOperation"){
		  materialGroupId= $('#update_material_group_select').val();
	      if(materialGroupId=="select"){
		    $('#update_material_sub_group_select').html('<option>select</option>');
		    return;
		  }
	  }
	
	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'get_material_sub_group',
			materialGroupId:materialGroupId
		},
		success : function(data) {
			
			if(flag=="filterOperation")
			$('#filter_material_sub_group_select').html(data);
			if(flag=="addOperation")
				$('#add_material_sub_group_select').html(data);
			if(flag=="updateOperation")
				$('#update_material_sub_group_select').html(data);
		},
		 async:   false
	});
};

//Get Material 	
var filterMaterialSubGroupId;
var filterMaterialSubGroupName;
var getMaterial = function() {
	var materialSubGroupId;
	 materialSubGroupId = $('#filter_material_sub_group_select').val();
	 filterMaterialSubGroupName = $('#filter_material_sub_group_select option:selected').html();
//	 alert(filterMaterialSubGroupName);
	 filterMaterialSubGroupId=materialSubGroupId;
	$('#material_div').css('display', 'none');
	if(materialSubGroupId=="select"){
	return;
	}
	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'get_material',
			materialSubGroupId:materialSubGroupId
		},
		success : function(data) {
			
			$('#material_div').css('display', 'block');
			showViewDiv();
		    $('#material_detail').html(data);
		}
	});
};

//Get Material Type
var getMaterialType = function(flag) {

		$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'get_material_type'
		
		},
		success : function(data) {
		
		if(flag=="addOperation")	
		$('#add_material_material_type_select').html(data);
		
	    if(flag=="updateOperation")	
			$('#update_material_material_type_select').html(data);
	    
	},
	async:   false
 });
};


//Get Material Unit
var getMaterialUnit= function(flag,subFlag) {

		$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'get_material_unit'
		
		},
		async:false,
		success : function(data) {
			
		if(flag=="addOperation"){
		if(subFlag=="height")	
		$('#add_material_height_unit_select').html(data);
		else if(subFlag=="width")	
			$('#add_material_width_unit_select').html(data);
		else if(subFlag=="thickness")	
			$('#add_material_thickness_unit_select').html(data);
		else if(subFlag=="capacity")	
			$('#add_material_capacity_unit_select').html(data);
		else if(subFlag=="std_ordering_size")	
			$('#add_material_std_ordering_size_unit_select').html(data);
		else if(subFlag=="conversion")	
			$('#add_material_conversion_unit_select').html(data);
		}
		
		if(flag=="updateOperation"){
			if(subFlag=="height")	
			$('#update_material_height_unit_select').html(data);
			else if(subFlag=="width")	
				$('#update_material_width_unit_select').html(data);
			else if(subFlag=="thickness")	
				$('#update_material_thickness_unit_select').html(data);
			else if(subFlag=="capacity")	
				$('#update_material_capacity_unit_select').html(data);
			else if(subFlag=="std_ordering_size")	
				$('#update_material_std_ordering_size_unit_select').html(data);
			else if(subFlag=="conversion")	
				$('#update_material_conversion_unit_select').html(data);
			}
		
		}
	});
};

//Get Material Code	
var getMaterialCode = function(flag) {
	var materialSubGroupId;
	//alert('subgroup filter id'+filterMaterialSubGroupId);
	/*if(flag=="addOperation"){
		  materialSubGroupId= $('#add_material_sub_group_select').val();
	      if(materialSubGroupId=="select"){
		  
		    return;
		  }
	  }
	
	if(flag=="updateOperation"){
		  materialSubGroupId= $('#update_material_sub_group_select').val();
	      if(materialSubGroupId=="select"){
		  
		    return;
		  }
	  }*/

	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'get_material_code',
			materialSubGroupId:filterMaterialSubGroupId	
		},
		success : function(data) {
		if(flag=="addOperation"){
			
			 $('#add_material_serial_number_input').val((data.trim()).slice(-3));
			 $('#add_material_code_input').val(data.trim());
		}
		if(flag=="updateOperation"){
			
			 $('#update_material_serial_number_input').val((data.trim()).slice(-3));
			 $('#update_material_code_input').val(data.trim());
		}
		}
	});
};

//Validate Serial Number	
var validateSerialNumber = function(flag) {
	var  materialSerialNumber;
	var  materialSubGroupId;
	if(flag=="addOperation"){
		 var materialCode=$('#add_material_code_input').val();
		 materialSubGroupId= $('#add_material_sub_group_select').val();
	      if(materialSubGroupId=="select"){
		    alert("Please Select Sub Group");
		    $('#add_material_serial_number_input').val(materialCode.slice(-3));
		    return;
		  }
		  materialSerialNumber= $('#add_material_serial_number_input').val();
	      if(materialSerialNumber==""){
		     alert("Serial Number Can't be Blank ");
		     $('#add_material_serial_number_input').val(materialCode.slice(-3));
	    	 return;
	    	 
		  }
	      
	      if(isNaN(materialSerialNumber)){
			     alert("Please Enter Numeric Value for Serial Number ");
			     $('#add_material_serial_number_input').val(materialCode.slice(-3));
		    	 return;
		  }
	      if(materialSerialNumber.length!=3){
			     alert("Please Enter Three Digit Serial Number");
			     $('#add_material_serial_number_input').val(materialCode.slice(-3));
		    	 return;
		  }
	  }
	
	if(flag=="updateOperation"){
		 var materialCode=$('#update_material_code_input').val();
		 materialSubGroupId= $('#update_material_sub_group_select').val();
	      if(materialSubGroupId=="select"){
		    alert("Please Select Sub Group");
		    $('#update_material_serial_number_input').val(materialCode.slice(-3));
		    return;
		  }
		  materialSerialNumber= $('#update_material_serial_number_input').val();
	      if(materialSerialNumber==""){
		     alert("Serial Number Can't be Blank ");
		     $('#update_material_serial_number_input').val(materialCode.slice(-3));
	    	 return;
	    	 
		  }
	      
	      if(isNaN(materialSerialNumber)){
			     alert("Please Enter Numeric Value for Serial Number ");
			     $('#update_material_serial_number_input').val(materialCode.slice(-3));
		    	 return;
		  }
	      if(materialSerialNumber.length!=3){
			     alert("Please Enter Three Digit Serial Number");
			     $('#update_material_serial_number_input').val(materialCode.slice(-3));
		    	 return;
		  }
	  }

	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'validate_serial_number',
			materialSubGroupId:materialSubGroupId,
			materialSerialNumber:materialSerialNumber
			
		},
		success : function(data) {
		if(flag=="addOperation"){
			
			if(data.trim()=="true"){
			 
			 var materialCode=$('#add_material_code_input').val();
			 $('#add_material_code_input').val((materialCode.slice(0,-3))+materialSerialNumber);
			}
			else{
				 alert("Serial Number Already Exist");
				 var materialCode=$('#add_material_code_input').val();
				 $('#add_material_serial_number_input').val(materialCode.slice(-3));
				 
				
			}
		}
		
	if(flag=="updateOperation"){
			
			if(data.trim()=="true"){
			 
			 var materialCode=$('#update_material_code_input').val();
			 $('#update_material_code_input').val((materialCode.slice(0,-3))+materialSerialNumber);
			}
			else{
				 alert("Serial Number Already Exist");
				 var materialCode=$('#update_material_code_input').val();
				 $('#update_material_serial_number_input').val(materialCode.slice(-3));
				 
				
			}
		}
		}
	});
};

//Get Material Name	
var getMaterialName = function(flag) {
	var materialGroupName;
	var materialSubGroupName;
	var materialThickness;
	var materialThicknessUnitName;
	
	var materialHeight;
	var materialHeightUnitName;
	
	var materialWidth;
	var materialWidthUnitName;
	
	var materialCapacity;
	var materialCapacityUnitName;
	
	var materialColor;
	var materialName;
	
	   //Group
	  if(flag=="addOperation"){
		  if($('#add_material_group_select').val()=="select"){
			alert("Please Select Material Group");
			return;
		}
	   else{
//		materialGroupName=$('#add_material_group_select option:selected').text();
		   materialGroupName= filterMaterialGroupName;
		}
		
	   //Sub Group
		if($('#add_material_sub_group_select').val()=="select"){
			alert("Please Select Material Sub Group");
			return;
		}
	   else{
//		materialSubGroupName=$('#add_material_sub_group_select option:selected').text();
		materialSubGroupName= filterMaterialSubGroupName;
		}
		
		//Thickness Input
		if(isNaN($('#add_material_thickness_input').val())){
			alert("Please Enter Numeric Value For Thickness");
			return;
		}
		else{
			materialThickness=(Number($('#add_material_thickness_input').val()));
		}
		
		//Thickness Unit
		if((materialThickness>0)&&($('#add_material_thickness_unit_select').val()=="select")){
			alert("Please Select Thickness Unit");
			return;
		}
		else{
			materialThicknessUnitName=$('#add_material_thickness_unit_select option:selected').text();
		}
		//materialName = materialName + materialThicknessUnitName;
		
		//Height Input
		if(isNaN($('#add_material_height_input').val())){
			alert("Please Enter Numeric Value For Height");
			return;
		}
		else{
			materialHeight=(Number($('#add_material_height_input').val()));
		}
		
		//Height Unit
	/*	if((materialHeight>0)&&($('#add_material_height_unit_select').val()=="select")){
			alert("Please Select Height Unit");
			return;
		}
		else{
			materialHeightUnitName=$('#add_material_height_unit_select option:selected').text();
		}*/
		
		//Width Input
		if(isNaN($('#add_material_width_input').val())){
			alert("Please Enter Numeric Value For Width");
			return;
		}
		else{
			materialWidth=(Number($('#add_material_width_input').val()));
		}
		
		//Width Unit
		if(((materialWidth>0)||(materialHeight>0))&&($('#add_material_width_unit_select').val()=="select")){
			alert("Please Select Height Width Unit");
			return;
		}
		else{
			materialWidthUnitName=$('#add_material_width_unit_select option:selected').text();
		}
		
		
		//Capacity Input
		if(isNaN($('#add_material_capacity_input').val())){
			alert("Please Enter Numeric Value For Capacity");
			return;
		}
		else{
			materialCapacity=(Number($('#add_material_capacity_input').val()));
		}
		
		//Capacity Unit
		if((materialCapacity>0)&&($('#add_material_capacity_unit_select').val()=="select")){
			alert("Please Select Capacity Unit");
			return;
		}
		else{
			materialCapacityUnitName=$('#add_material_capacity_unit_select option:selected').text();
		}
		
		//Color Input
		materialColor=$('#add_material_color_input').val();
	    
		//Material Name Creation
		//materialName=materialGroupName+" "+materialSubGroupName;
		materialName = materialSubGroupName;
		if(Number(materialThickness)>0){
       	 materialName=materialName+" "+materialThickness+materialThicknessUnitName;	
		// materialName = materialName+" "+materialThickness;
		}
	
		if((Number(materialHeight)>0)||(Number(materialWidth)>0)){
		 materialName=materialName+" "+materialHeight+"X"+materialWidth+materialWidthUnitName;
		// materialName = materialName+" " + materialHeight + "X" + materialWidth;
		}
		/*else if(Number(materialWidth)>0){
	     materialName=materialName+" "+materialHeight+"X"+materialWidth+materialWidthUnitName;	
		}*/
		
		if(Number(materialCapacity)>0){
	       	 materialName=materialName+" "+materialCapacity+materialCapacityUnitName;	
			// materialName = materialName+" "+materialThickness;
			}
		
		materialName=materialName+" "+materialColor;
		
		
		$('#add_material_material_name_input').val(materialName);
	/*	alert(materialGroupName+materialSubGroupName+materialThickness+materialThicknessUnitName
				+materialHeight+materialHeightUnitName+materialWidth+materialWidthUnitName+materialColor);*/
		
	  }
	  
	  if(flag=="updateOperation"){
			if($('#update_material_group_select').val()=="select"){
				alert("Please Select Material Group");
				return;
			}
		   else{
			materialGroupName=$('#update_material_group_select option:selected').text();
			}
			
		   //Sub Group
			if($('#update_material_sub_group_select').val()=="select"){
				alert("Please Select Material Sub Group");
				return;
			}
		   else{
			materialSubGroupName=$('#update_material_sub_group_select option:selected').text();
			}
			
			//Thickness Input
			if(isNaN($('#update_material_thickness_input').val())){
				alert("Please Enter Numeric Value For Thickness");
				return;
			}
			else{
				materialThickness=(Number($('#update_material_thickness_input').val()));
			}
			
			//Thickness Unit
			if((materialThickness>0)&&($('#update_material_thickness_unit_select').val()=="select")){
				alert("Please Select Thickness Unit");
				return;
			}
			else{
				materialThicknessUnitName=$('#update_material_thickness_unit_select option:selected').text();
			}
			
			//Height Input
			if(isNaN($('#update_material_height_input').val())){
				alert("Please Enter Numeric Value For Height");
				return;
			}
			else{
				materialHeight=(Number($('#update_material_height_input').val()));
			}
			
			//Height Unit
		/*	if((materialHeight>0)&&($('#update_material_height_unit_select').val()=="select")){
				alert("Please Select Height Unit");
				return;
			}
			else{
				materialHeightUnitName=$('#update_material_height_unit_select option:selected').text();
			}*/
			
			//Width Input
			if(isNaN($('#update_material_width_input').val())){
				alert("Please Enter Numeric Value For Width");
				return;
			}
			else{
				materialWidth=(Number($('#update_material_width_input').val()));
			}
			
			//Width Unit
			if(((materialWidth>0)||(materialHeight>0))&&($('#update_material_width_unit_select').val()=="select")){
				alert("Please Select Height Width Unit");
				return;
			}
			else{
				materialWidthUnitName=$('#update_material_width_unit_select option:selected').text();
			}
			
			//Capacity Input
			if(isNaN($('#update_material_capacity_input').val())){
				alert("Please Enter Numeric Value For Capacity");
				return;
			}
			else{
				materialCapacity=(Number($('#update_material_capacity_input').val()));
			}
			
			//Capacity Unit
			if((materialCapacity>0)&&($('#update_material_capacity_unit_select').val()=="select")){
				alert("Please Select Capacity Unit");
				return;
			}
			else{
				materialCapacityUnitName=$('#update_material_capacity_unit_select option:selected').text();
			}
			
			//Color Input
			materialColor=$('#update_material_color_input').val();
		    
			//Material Name Creation
			materialName=materialSubGroupName;
			if(Number(materialThickness)>0){
//			 materialName=materialName+" "+materialThickness+materialThicknessUnitName;
				materialName=materialName+" "+materialThickness+materialThicknessUnitName ;
			}
		
			if((Number(materialHeight)>0)||(Number(materialWidth)>0)){
//			 materialName=materialName+" "+materialHeight+"X"+materialWidth+materialWidthUnitName;
			 materialName=materialName+" "+materialHeight+"X"+materialWidth+materialWidthUnitName;
			}
			/*else if(Number(materialWidth)>0){
		     materialName=materialName+" "+materialHeight+"X"+materialWidth+materialWidthUnitName;	
			}*/
			
			if(Number(materialCapacity)>0){
		       	 materialName=materialName+" "+materialCapacity+materialCapacityUnitName;	
				// materialName = materialName+" "+materialThickness;
				}
			
			materialName=materialName+" "+materialColor;
			
			
			$('#update_material_material_name_input').val(materialName);
		/*	alert(materialGroupName+materialSubGroupName+materialThickness+materialThicknessUnitName
					+materialHeight+materialHeightUnitName+materialWidth+materialWidthUnitName+materialColor);*/
			
		  }

	
};

// Validate Set Material

var setMaterial=function(){
//	alert('in');
	var groupId;
	var subGroupId;
	var serialNumber;
	var code;
	
	var thickness;
	var thicknessUnitId;
	
	var height;
	var heightUnitId;
	
	var width;
	var widthUnitId;
	
	var capacity;
	var capacityUnitId;
	
	var stdOrderingSize;
	var stdOrderingSizeUnitId;
	
	var conversion;
	var conversionUnitId;
	
	var color = null;
	var rate = null;
	var type = null;
	var name = null;
	var storageSpecs = null;
	var expirySpecs = null;
	
	//groupId=$('#add_material_group_select').val();
	groupId= filterMaterialGroupId;
	
 /*   if(groupId=="select"){
		alert("Please Select Material Group");
		return false;
    }*/	
	
   //subGroupId=$('#add_material_sub_group_select').val();
    subGroupId= filterMaterialSubGroupId;
    
  /*  if(subGroupId=="select"){
		alert("Please Select Material Sub Group");
		return false;
    }*/

    
    serialNumber = $('#add_material_serial_number_input').val();
    code=$('#add_material_code_input').val();
    
	//Thickness Input
	if(isNaN($('#add_material_thickness_input').val())){
		alert("Please Enter Numeric Value For Thickness");
		$('#add_material_thickness_input').focus();
		return;
	}
	else if(Number($('#add_material_thickness_input').val())<0){
		alert("Negative Value is Not Allowed For Thickness");
		$('#add_material_thickness_input').focus();
		return;
	}
	else{
		thickness=(Number($('#add_material_thickness_input').val()));
	}
	
	//Thickness Unit
	if((thickness>0)&&($('#add_material_thickness_unit_select').val()=="select")){
		alert("Please Select Thickness Unit");
		return;
	}
	
	else if((thickness>0)&&($('#add_material_thickness_unit_select').val()!="select")){
		thicknessUnitId=$('#add_material_thickness_unit_select').val();
	}
	
	else{
		thicknessUnitId="null";
	}
	
	//Height Input
	if(isNaN($('#add_material_height_input').val())){
		alert("Please Enter Numeric Value For Height");
		$('#add_material_height_input').focus();
		return;
	}
	else if(Number($('#add_material_height_input').val())<0){
		alert("Negative Value is Not Allowed For Height");
		$('#add_material_height_input').focus();
		return;
	}
	else{
		height=(Number($('#add_material_height_input').val()));
	}
	
	//Height Unit
/*	if((height>0)&&($('#add_material_height_unit_select').val()=="select")){
		alert("Please Select Height Unit");
		return;
	}
	
	else if((height>0)&&($('#add_material_height_unit_select').val()!="select")){
		heightUnitId=$('#add_material_height_unit_select').val();
	}
	
	else{
		heightUnitId=null;
	}*/
	
	//Width Input
	if(isNaN($('#add_material_width_input').val())){
		alert("Please Enter Numeric Value For Width");
		$('#add_material_width_input').focus();
		return;
	}
	else if(Number($('#add_material_width_input').val())<0){
		alert("Negative Value is Not Allowed For Width");
		$('#add_material_width_input').focus();
		return;
	}
	else{
		width=(Number($('#add_material_width_input').val()));
	}
	
	//Width Unit
	if(((width>0)||(height>0))&&($('#add_material_width_unit_select').val()=="select")){
		alert("Please Select Height Width Unit");
		return;
	}
	
	else if(((width>0)||(height>0))&&($('#add_material_width_unit_select').val()!="select")){
		heightUnitId=$('#add_material_width_unit_select').val();
		widthUnitId=$('#add_material_width_unit_select').val();
	}
	
	else{
		heightUnitId="null";
		widthUnitId="null";
	}
	
	//Capacity Input
	if(isNaN($('#add_material_capacity_input').val())){
		alert("Please Enter Numeric Value For Capacity");
		$('#add_material_capacity_input').focus();
		return;
	}
	else if(Number($('#add_material_capacity_input').val())<0){
		alert("Negative Value is Not Allowed For Capacity");
		$('#add_material_capacity_input').focus();
		return;
	}
	else{
		capacity=(Number($('#add_material_capacity_input').val()));
	}
	
	//Capacity Unit
	if((capacity>0)&&($('#add_material_capacity_unit_select').val()=="select")){
		alert("Please Select Capacity Unit");
		return;
	}
	
	else if((capacity>0)&&($('#add_material_capacity_unit_select').val()!="select")){
		capacityUnitId=$('#add_material_capacity_unit_select').val();
	}
	
	else{
		capacityUnitId="null";
	}
	
	//Standard Ordering Size Input
	if(isNaN($('#add_material_std_ordering_size_input').val())){
		alert("Please Enter Numeric Value For Standard Ordering Size");
		$('#add_material_std_ordering_size_input').focus();
		return;
	}
	else if(Number($('#add_material_std_ordering_size_input').val())<0){
		alert("Negative Value is Not Allowed For Standard Ordering Size");
		$('#add_material_std_ordering_size_input').focus();
		return;
	}
	else{
		stdOrderingSize=(Number($('#add_material_std_ordering_size_input').val()));
	}
	
	//Standard Ordering Size Unit
	if((stdOrderingSize>0)&&($('#add_material_std_ordering_size_unit_select').val()=="select")){
		alert("Please Select Standard Ordering Size Unit");
		return;
	}
	
	else if((stdOrderingSize>0)&&($('#add_material_std_ordering_size_unit_select').val()!="select")){
		stdOrderingSizeUnitId=$('#add_material_std_ordering_size_unit_select').val();
	}
	
	else{
		stdOrderingSizeUnitId="null";
	}
	
	//Conversion Input
	if(isNaN($('#add_material_conversion_input').val())){
		alert("Please Enter Numeric Value For Conversion");
		$('#add_material_conversion_input').focus();
		return;
	}
	else if(Number($('#add_material_conversion_input').val())<0){
		alert("Negative Value is Not Allowed For Conversion");
		$('#add_material_conversion_input').focus();
		return;
	}
	else{
		conversion=(Number($('#add_material_conversion_input').val()));
	}
	
	//Conversion Unit
	if((conversion>0)&&($('#add_material_conversion_unit_select').val()=="select")){
		alert("Please Select Conversion Unit");
		return;
	}
	
	else if((conversion>0)&&($('#add_material_conversion_unit_select').val()!="select")){
		conversionUnitId=$('#add_material_conversion_unit_select').val();
	}
	
	else{
		conversionUnitId="null";
	}
	
	if($('#add_material_color_input').val()==""){
	color="null";	
	}
	else{
	color=$('#add_material_color_input').val();
	}
	//Material Rate
	
	if ($('#add_material_material_rate_input').val() == "") {
		//alert("Please Enter Material Rate");
		//$('#add_material_material_rate_input').focus();
		rate = 0;
		//return false;
	} else if (isNaN($('#add_material_material_rate_input').val())) {
		alert("Please Enter Numeric Value For Material Rate");
		$('#add_material_material_rate_input').focus();
		return false;
	}

	else if (Number($('#add_material_material_rate_input').val()) < 0) {
		alert("Negative Value Not Allowed  For Material Rate");
		$('#add_material_material_rate_input').focus();
		return false;
	} else {
		rate = $('#add_material_material_rate_input').val();
	}
	
	//Material Type
  if(($('#add_material_material_type_select').val()!="select")){
		type=$('#add_material_material_type_select').val();
	}
	
	else{
		type="null";
	}
  getMaterialName('addOperation');
  name=$('#add_material_material_name_input').val();
  
  if($('#add_material_storage_specs_input').val()=="")
  storageSpecs="null";
  else
  storageSpecs=$('#add_material_storage_specs_input').val();
  
  if($('#add_material_expiry_specs_input').val()=="")
  expirySpecs="null";
  else
  expirySpecs=$('#add_material_expiry_specs_input').val();
  
 // alert(storageSpecs);
 // alert(expirySpecs);
//  alert('material type is' + type);
	$.ajax( {
		
		type : 'POST',
		url : 'ManageMaterialController.jsp',
		data : {
			flag : 'set_material',
			subGroupId:subGroupId,serialNumber:serialNumber,code:code,thickness:thickness,thicknessUnitId:thicknessUnitId,
			height:height,heightUnitId:heightUnitId,width:width,widthUnitId:widthUnitId,capacity:capacity,
			capacityUnitId:capacityUnitId,stdOrderingSize:stdOrderingSize,stdOrderingSizeUnitId:stdOrderingSizeUnitId,
			conversion:conversion,conversionUnitId:conversionUnitId,color:color,rate:rate,type:type,name:name,
			storageSpecs:storageSpecs,expirySpecs:expirySpecs
		},
		success : function(data) {
	       getMaterial('filterOperation');
	       showViewDiv();
		}
	});
  
};

//Delete Material 
var deleteMaterial=function(tableID) {
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
            var outData="flag=delete_material&selectedMaterialId="+selectedValues;
            
        	$.ajax( {
        		type : 'POST',
        		url : 'ManageMaterialController.jsp',
        		data :outData,
        		success : function(data) {
        			getMaterial('filterOperation');
        		}
        	});
       };

var updateMaterial=function(){
	var materialId;
	var groupId;
	var subGroupId;
	var serialNumber;
	var code;
	
	var thickness;
	var thicknessUnitId;
	
	var height;
	var heightUnitId;
	
	var width;
	var widthUnitId;
	
	var capacity;
	var capacityUnitId;
	
	var stdOrderingSize;
	var stdOrderingSizeUnitId;
	
	var conversion;
	var conversionUnitId;
	
	var color = null;
	var rate = null;
	var type = null;
	var name = null;
	var storageSpecs = null;
	var expirySpecs = null;
	
	materialId=$('#update_material_id').val();
	groupId=$('#update_material_group_select').val();
	//groupId= filterMaterialGroupId;
	
    if(groupId=="select"){
		alert("Please Select Material Group");
		return false;
    }	
	
   subGroupId=$('#update_material_sub_group_select').val();
    //subGroupId= filterMaterialSubGroupId;
    
  if(subGroupId=="select"){
		alert("Please Select Material Sub Group");
		return false;
    }
    //alert("group id is...."+groupId+" sub group id is...."+subGroupId);
    
    serialNumber = $('#update_material_serial_number_input').val();
    code=$('#update_material_code_input').val();
    
	//Thickness Input
	if(isNaN($('#update_material_thickness_input').val())){
		alert("Please Enter Numeric Value For Thickness");
		$('#update_material_thickness_input').focus();
		return;
	}
	else if(Number($('#update_material_thickness_input').val())<0){
		alert("Negative Value is Not Allowed For Thickness");
		$('#update_material_thickness_input').focus();
		return;
	}
	else{
		thickness=(Number($('#update_material_thickness_input').val()));
	}
	
	//Thickness Unit
	if((thickness>0)&&($('#update_material_thickness_unit_select').val()=="select")){
		alert("Please Select Thickness Unit");
		return;
	}
	
	else if((thickness>0)&&($('#update_material_thickness_unit_select').val()!="select")){
		thicknessUnitId=$('#update_material_thickness_unit_select').val();
	}
	
	else{
		thicknessUnitId="null";
	}
	
	//Height Input
	if(isNaN($('#update_material_height_input').val())){
		alert("Please Enter Numeric Value For Height");
		$('#update_material_height_input').focus();
		return;
	}
	else if(Number($('#update_material_height_input').val())<0){
		alert("Negative Value is Not Allowed For Height");
		$('#update_material_height_input').focus();
		return;
	}
	else{
		height=(Number($('#update_material_height_input').val()));
	}
	
	//Height Unit
/*	if((height>0)&&($('#update_material_height_unit_select').val()=="select")){
		alert("Please Select Height Unit");
		return;
	}
	
	else if((height>0)&&($('#update_material_height_unit_select').val()!="select")){
		heightUnitId=$('#update_material_height_unit_select').val();
	}
	
	else{
		heightUnitId=null;
	}*/
	
	//Width Input
	if(isNaN($('#update_material_width_input').val())){
		alert("Please Enter Numeric Value For Width");
		$('#update_material_width_input').focus();
		return;
	}
	else if(Number($('#update_material_width_input').val())<0){
		alert("Negative Value is Not Allowed For Width");
		$('#update_material_width_input').focus();
		return;
	}
	else{
		width=(Number($('#update_material_width_input').val()));
	}
	
	//Width Unit
	if(((width>0)||(height>0))&&($('#update_material_width_unit_select').val()=="select")){
		alert("Please Select Height Width Unit");
		return;
	}
	
	else if(((width>0)||(height>0))&&($('#update_material_width_unit_select').val()!="select")){
		heightUnitId=$('#update_material_width_unit_select').val();
		widthUnitId=$('#update_material_width_unit_select').val();
	}
	
	else{
		heightUnitId="null";
		widthUnitId="null";
	}
	
	//Capacity Input
	if(isNaN($('#update_material_capacity_input').val())){
		alert("Please Enter Numeric Value For Capacity");
		$('#update_material_capacity_input').focus();
		return;
	}
	else if(Number($('#update_material_capacity_input').val())<0){
		alert("Negative Value is Not Allowed For Capacity");
		$('#update_material_capacity_input').focus();
		return;
	}
	else{
		capacity=(Number($('#update_material_capacity_input').val()));
	}
	
	//Capacity Unit
	if((capacity>0)&&($('#update_material_capacity_unit_select').val()=="select")){
		alert("Please Select Capacity Unit");
		return;
	}
	
	else if((capacity>0)&&($('#update_material_capacity_unit_select').val()!="select")){
		capacityUnitId=$('#update_material_capacity_unit_select').val();
	}
	
	else{
		capacityUnitId="null";
	}
	
	//Standard Ordering Size Input
	if(isNaN($('#update_material_std_ordering_size_input').val())){
		alert("Please Enter Numeric Value For Standard Ordering Size");
		$('#update_material_std_ordering_size_input').focus();
		return;
	}
	else if(Number($('#update_material_std_ordering_size_input').val())<0){
		alert("Negative Value is Not Allowed For Standard Ordering Size");
		$('#update_material_std_ordering_size_input').focus();
		return;
	}
	else{
		stdOrderingSize=(Number($('#update_material_std_ordering_size_input').val()));
	}
	
	//Standard Ordering Size Unit
	if((stdOrderingSize>0)&&($('#update_material_std_ordering_size_unit_select').val()=="select")){
		alert("Please Select Standard Ordering Size Unit");
		return;
	}
	
	else if((stdOrderingSize>0)&&($('#update_material_std_ordering_size_unit_select').val()!="select")){
		stdOrderingSizeUnitId=$('#update_material_std_ordering_size_unit_select').val();
	}
	
	else{
		stdOrderingSizeUnitId="null";
	}
	
	//Conversion Input
	if(isNaN($('#update_material_conversion_input').val())){
		alert("Please Enter Numeric Value For Conversion");
		$('#update_material_conversion_input').focus();
		return;
	}
	else if(Number($('#update_material_conversion_input').val())<0){
		alert("Negative Value is Not Allowed For Conversion");
		$('#update_material_conversion_input').focus();
		return;
	}
	else{
		conversion=(Number($('#update_material_conversion_input').val()));
	}
	
	//Conversion Unit
	if((conversion>0)&&($('#update_material_conversion_unit_select').val()=="select")){
		alert("Please Select Conversion Unit");
		return;
	}
	
	else if((conversion>0)&&($('#update_material_conversion_unit_select').val()!="select")){
		conversionUnitId=$('#update_material_conversion_unit_select').val();
	}
	
	else{
		conversionUnitId="null";
	}
	
	if($('#update_material_color_input').val()==""){
	color="null";	
	}
	else{
	color=$('#update_material_color_input').val();
	}
	//Material Rate
	
	if ($('#update_material_material_rate_input').val() == "") {
		//alert("Please Enter Material Rate");
		//$('#update_material_material_rate_input').focus();
		rate = 0;
		//return false;
	} else if (isNaN($('#update_material_material_rate_input').val())) {
		alert("Please Enter Numeric Value For Material Rate");
		$('#update_material_material_rate_input').focus();
		return false;
	}

	else if (Number($('#update_material_material_rate_input').val()) < 0) {
		alert("Negative Value Not Allowed  For Material Rate");
		$('#update_material_material_rate_input').focus();
		return false;
	} else {
		rate = $('#update_material_material_rate_input').val();
	}
	
	//Material Type
  if(($('#update_material_material_type_select').val()!="select")){
		type=$('#update_material_material_type_select').val();
	}
	
	else{
		type="null";
	}
  getMaterialName('updateOperation');
  name=$('#update_material_material_name_input').val();
  
  if($('#update_material_storage_specs_input').val()=="")
  storageSpecs="null";
  else
  storageSpecs=$('#update_material_storage_specs_input').val();
  
  if($('#update_material_expiry_specs_input').val()=="")
  expirySpecs="null";
  else
  expirySpecs=$('#update_material_expiry_specs_input').val();
  
    		$.ajax( {
    			type : 'POST',
    			url : 'ManageMaterialController.jsp',
    			data : {
    				flag : 'update_material',materialId:materialId,
    				subGroupId:subGroupId,serialNumber:serialNumber,code:code,thickness:thickness,thicknessUnitId:thicknessUnitId,
    				height:height,heightUnitId:heightUnitId,width:width,widthUnitId:widthUnitId,capacity:capacity,
    				capacityUnitId:capacityUnitId,stdOrderingSize:stdOrderingSize,stdOrderingSizeUnitId:stdOrderingSizeUnitId,
    				conversion:conversion,conversionUnitId:conversionUnitId,color:color,rate:rate,type:type,name:name,
    				storageSpecs:storageSpecs,expirySpecs:expirySpecs
    			},
    			success : function(data) {
    		       getMaterial('filterOperation');
    		       showViewDiv();
    			}
    		});
    	  
    	};       

 

function showUpdateDiv(materialId ,materialTypeId,serialNumber,materialCode, materialName,height,
		 heightUnitId , width,widthUnitId ,thickness , thicknessUnitId,capacity ,capacityUnitId , 
		 stdOrderingSize,stdOrderingSizeUnitId,conversionSize ,conversionSizeUnitId,color, 
		 materialRate,storageSpecs ,expirySpecs){



	// Material Group;

	getMaterialGroup('updateOperation');

	var typeDdl = document.getElementById('update_material_group_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (filterMaterialGroupId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	// Material Sub Group

	getMaterialSubGroup('updateOperation');

	var typeDdl = document.getElementById('update_material_sub_group_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (filterMaterialSubGroupId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	// Material Type

	getMaterialType('updateOperation');

	var typeDdl = document
			.getElementById('update_material_material_type_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (materialTypeId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	// Material Unit

	getMaterialUnit('updateOperation', 'width');

	var typeDdl = document.getElementById('update_material_width_unit_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (widthUnitId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	
	getMaterialUnit('updateOperation', 'thickness');

	var typeDdl = document
			.getElementById('update_material_thickness_unit_select');
	for (i = 0; i < typeDdl.options.length; i++) {
     
		if (thicknessUnitId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}


	getMaterialUnit('updateOperation', 'capacity');

	var typeDdl = document
			.getElementById('update_material_capacity_unit_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (capacityUnitId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	getMaterialUnit('updateOperation', 'std_ordering_size');

	var typeDdl = document
			.getElementById('update_material_std_ordering_size_unit_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (stdOrderingSizeUnitId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	getMaterialUnit('updateOperation', 'conversion');

	var typeDdl = document
			.getElementById('update_material_conversion_unit_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (conversionSizeUnitId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}

	$('#update_material_id').val(materialId);
	serialNumber = materialCode.slice(-3);
	$('#update_material_serial_number_input').val(serialNumber);
	$('#update_material_code_input').val(materialCode);

	$('#update_material_thickness_input').val(thickness);
	$('#update_material_height_input').val(height);
	$('#update_material_width_input').val(width);
	$('#update_material_capacity_input').val(capacity);
	$('#update_material_std_ordering_size_input').val(stdOrderingSize);
	$('#update_material_conversion_input').val(conversionSize);

	$('#update_material_color_input').val(color);
	$('#update_material_material_rate_input').val(materialRate);
	$('#update_material_material_name_input').val(materialName);
	$('#update_material_storage_specs_input').val(storageSpecs);
	$('#update_material_expiry_specs_input').val(expirySpecs);

	document.getElementById("material_add").style.display = "none";
	document.getElementById("material_detail").style.display = "none";
	document.getElementById("delete_button").style.display = "none";
	document.getElementById("add_button").style.display = "none";
//	document.getElementById("add_button").style.margin = "10px 0 0 0px";
	document.getElementById("material_update").style.display = "block";
	
}

function showAddDiv(){

	document.getElementById("material_update").style.display="none";
	document.getElementById("material_detail").style.display="none";
	document.getElementById("delete_button").style.display="none";
	document.getElementById("add_button").style.display="none";
	document.getElementById("material_add").style.display="block";
//	getMaterialGroup('addOperation');
	//getMaterialSubGroup('addOperation');
	getMaterialType('addOperation');
	getMaterialUnit('addOperation','height');
	getMaterialUnit('addOperation','width');
	getMaterialUnit('addOperation','thickness');
	getMaterialUnit('addOperation','capacity');
	getMaterialUnit('addOperation','std_ordering_size');
	getMaterialUnit('addOperation','conversion');
	
//	alert('in ShowAdd' + filterMaterialGroupName);
//	alert('in ShowAdd' + filterMaterialSubGroupName);
//	$('#add_material_group_select').val("select");
	$('#add_material_group_select').val(filterMaterialGroupName);
//    $('#add_material_sub_group_select').val("select");
    $('#add_material_sub_group_select').val(filterMaterialSubGroupName);
    getMaterialCode('addOperation');
    $('#add_material_serial_number_input').val("");
    $('#add_material_code_input').val("");
    
	//Thickness Input
	$('#add_material_thickness_input').val("");
	
	
	//Thickness Unit
	$('#add_material_thickness_unit_select').val("select");
	
	//Height Input
	$('#add_material_height_input').val("") ;
	//Height Unit
		
	
	//Width Input
	$('#add_material_width_input').val("");
	
	
	//Width Unit
	$('#add_material_width_unit_select').val ("select");
	
	//Capacity Input
	$('#add_material_capacity_input').val("");
	
	//Capacity Unit
	$('#add_material_capacity_unit_select').val ("select");
	
	//Standard Ordering Size Input
	$('#add_material_std_ordering_size_input').val("");
	
	//Standard Ordering Size Unit
	$('#add_material_std_ordering_size_unit_select').val ("select");
	
	//Conversion Input
	$('#add_material_conversion_input').val ("");
	
	//Conversion Unit
	$('#add_material_conversion_unit_select').val("select");
	
	
	$('#add_material_color_input').val("");
   $('#add_material_material_rate_input').val("");
   $('#add_material_material_type_select').val("select");
   $('#add_material_material_name_input').val("");
   $('#add_material_storage_specs_input').val("");
   $('#add_material_expiry_specs_input').val("");
  

	

	
}

function showViewDiv(){
	document.getElementById("material_update").style.display="none";
	document.getElementById("material_add").style.display="none";
	document.getElementById("delete_button").style.display="block";
	document.getElementById("add_button").style.display="block";
	document.getElementById("add_button").style.margin="10px 0 0 150px";
	
	document.getElementById("material_detail").style.display="block";
	
	
}

           