function showUpdateDiv(materialTypeId,materialTypeName){
	 document.getElementById("material_type_add").style.display="none";
	 document.getElementById("material_type_update").style.display="block";
     document.getElementById("update_material_type_name").value=materialTypeName;
     document.getElementById("update_material_type_id").value=materialTypeId;	
}

function showAddDiv(){
	document.getElementById("material_type_update").style.display="none";
	document.getElementById("material_type_add").style.display="block";
}

//Get Material Type
var getMaterialType = function() {

	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialTypeController.jsp',
		data : {
			flag : 'get_material_type'
		},
		success : function(data) {
		
			$('#material_type_detail').html(data);
			
			
		},
	  async:   false
	});
};

//Set Material Type
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}

var setMaterialType = function() {

	var materialTypeName = $('#add_material_type_name');
	if (materialTypeName.val().trim() == "") {
		alert("Please Enter Material Type Name");
		materialTypeName.focus();
		return false;

	}
/*	var n = materialTypeName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(materialTypeName.val().charAt(i))){
            alert("Enter only Alphabets");
            materialTypeName.focus();
            return false;
   }

    }*/

	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialTypeController.jsp',
		data : {
			flag : 'set_material_type' ,
			materialTypeName:materialTypeName.val()	
		},
		success : function(data) {
		if($.trim(data)=="0"){	
		 getMaterialType();
		 $('#add_material_type_name').val("");
		}
		else if($.trim(data)=="-3"){
			alert('Material Type Already Exist');
		 	materialTypeName.focus();}
		else
		alert('Material Type Addition Failed');	
			
		}
		

	});
};

//Delete Material Type
var deleteMaterialType = function(tableID) {

	try { 
		  var table =
		  document.getElementById(tableID); 
		  var rowCount = table.rows.length; 
		  var selectedValues=new Array(); 
		  var j=0; 
		  for(var i=1; i<rowCount; i++) { 
		  var row =table.rows[i]; 
		  var chkbox = row.cells[0].childNodes[0];
		   if(null != chkbox && true == chkbox.checked) {
		  selectedValues[j]=chkbox.value; 
		   j++;
		   }
		 
		   }
		 }
	catch(e) { 
	alert(e);
		}
    var outData="flag=delete_material_type&selectedValues="+selectedValues;
	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialTypeController.jsp',
		data :outData,
		success : function(data) {
		if($.trim(data)=="0")	
			 getMaterialType();
		else
		alert('Material Type Deletion Failed');	
				
		
			

		}

	});
};

//Update Material Type
var updateMaterialType = function() {
    var materialTypeId=$('#update_material_type_id');
	var materialTypeName = $('#update_material_type_name');
	if (materialTypeName.val() == "") {
		alert("Please Enter Material Type Name");
		materialTypeName.focus();
		return;

	}

	$.ajax( {
		type : 'POST',
		url : 'ManageMaterialTypeController.jsp',
		data : {
			flag : 'update_material_type' ,
		    materialTypeId:materialTypeId.val(),
			materialTypeName:materialTypeName.val()	
		},
		success : function(data) {
			if($.trim(data)=="0"){	
				 getMaterialType();
				 showAddDiv();
			}
			else
			alert('Material Type Updation Failed');	

		}

	});
};
   