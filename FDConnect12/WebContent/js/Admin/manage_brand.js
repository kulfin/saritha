function showUpdateDiv(clientId,brandCategoryId,brandId,brandName,notes){
	
	// Client
    getClient('update');
	var typeDdl = document.getElementById('update_operation_client_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (clientId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
	// Brand Category
    getBrandCategory('update');
	var typeDdl = document.getElementById('update_operation_brand_category_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (brandCategoryId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
     document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_brand_id_input").value=brandId;
     document.getElementById("update_operation_brand_name_input").value=brandName;
     document.getElementById("update_operation_notes_input").value=notes;
     
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}

var validateAddOperation=function(){
	
    var clientId = $('#filter_operation_client_select').val();
		if (document.getElementById("filter_operation_client_select").selectedIndex == 0) {
			alert("Please Select Client");
	        return;

		}
	
    var brandCategoryId = $('#add_operation_brand_category_select').val();
	if (document.getElementById("add_operation_brand_category_select").selectedIndex == 0) {
		alert("Please Select Brand Category");
        return;

	}
	
	var brandName = $('#add_operation_brand_name_input');
	if (brandName.val().trim() == "") {
		alert("Please Enter Brand Name");
		brandName.focus();
		return;

	}
	/*var n = brandName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(brandName.val().charAt(i))){
            alert("Enter only Alphabets for Brand Name");
            brandName.focus();
            return false;             
        }
    }*/
	
	var notes = $('#add_operation_notes_input');
	/*if (notes.val() == "") {
		notes.val ="";
		alert("Please Enter Notes");
		notes.focus();
		return;

	}*/
	
	 setBrand(clientId,brandCategoryId,brandName.val(),notes.val());
};

var validateUpdateOperation=function(){
	
	var brandId = $('#update_operation_brand_id_input').val();

	var clientId = $('#update_operation_client_select').val();
	if (document.getElementById("update_operation_client_select").selectedIndex == 0) {
		alert("Please Select Client");
		return;

	}

	var brandCategoryId = $('#update_operation_brand_category_select').val();
	if (document.getElementById("update_operation_brand_category_select").selectedIndex == 0) {
		alert("Please Select Brand Category");
		return;

	}

	var brandName = $('#update_operation_brand_name_input');
	if (brandName.val() == "") {
		alert("Please Enter Brand Name");
		brandName.focus();
		return;

	}

	var notes = $('#update_operation_notes_input');
	if (notes.val() == "") {
		alert("Please Enter Notes Currency");
		notes.focus();
		return;

     }

 updateBrand(clientId,brandCategoryId,brandId,brandName.val(),notes.val());
};

//Get Brand
var getBrand = function() {
	clientId= $('#filter_operation_client_select').val();
	if(document.getElementById("filter_operation_client_select").selectedIndex == 0){
		$('#content_operation_div').css('display','none');
		return;
	}
	$.ajax( {
				type : "POST",
				url : "../ManageBrand",
				data : {
					userAction : "get_brand",
					clientId:clientId
                  
				},
				success : function(data) {
				var len = data.length;
				//if(len>0){
				$('#content_operation_div').css('display','block');
				//}
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Brand Category</th>'+
					'<th>Brand</th>'+
					'<th>Notes</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var clientId= data[i].clientId;
						var brandCategoryId= data[i].brandCategoryId;
						var brandCategoryName= data[i].brandCategoryName;
						var brandId = data[i].brandId;
						var brandName = data[i].brandName;
						var notes = data[i].notes;
					
						
						$('#content_view_table').append("<tr>"+
								"<td><input type=\"checkbox\" value=\""+brandId+"\"></td>"+
								"<td><input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showUpdateDiv('"+clientId+"','"+brandCategoryId+"'," +
								"'"+brandId+"','"+brandName+"','"+notes+"'" +
								")\"></td>"+
								"<td>"+ brandCategoryName + "</td>"+
								"<td>"+ brandName + "</td>"+
								"<td>"+ notes + "</td>"+
							    "</tr>");
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Brand
var setBrand = function(clientId,brandCategoryId,brandName,notes) {
 
	$.ajax( {
		type : 'POST',
		url : '../ManageBrand',
		data : {
			userAction : 'set_brand',
			clientId:clientId,
			brandCategoryId:brandCategoryId,
			brandName:brandName,
	        notes:notes
			

		},
		success : function(data) {
//			alert('status is' + data.status);
			var status = data.status;
			
			if (status == 0) {
				alert('Brand is Added Succesfully');
				getBrand();
				$('#add_operation_brand_category_select').prop('selectedIndex', 0);
			    $('#add_operation_brand_name_input').val('');
				$('#add_operation_notes_input').val('');
				$('#add_operation_brand_name_input').focus();
				
			}
			if(status == -3){
				alert("Brand Exists With Name  : " + brandName);
				$('#add_operation_brand_name_input').focus();
				return false;
			}
			if(status == -1){
				alert('Brand Addition Failed Please try again ');
				$('#add_operation_brand_name_input').val('');
				$('#add_operation_notes_input').val('');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Brand
var deleteBrand = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var brandId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				brandId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}
 
	$.ajax( {
		type : 'POST',
		url : '../ManageBrand',
		data : {
			userAction : "delete_brand",
			brandId : brandId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Brand is Deleted Succesfully');
				getBrand();
			} else {
				alert('Brand Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update Brand
var updateBrand = function(clientId,brandCategoryId,brandId,brandName,notes) {

	$.ajax( {
		type : 'POST',
		url : '../ManageBrand',
		data : {
			userAction : 'update_brand',
			clientId:clientId,
			brandCategoryId:brandCategoryId,
			brandId:brandId,
			brandName:brandName,
	        notes:notes
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Brand is Updated Succesfully');
				getBrand();
				showAddDiv();
			} else {
				alert('Brand Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Get Client
var getClient = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageBrand",
				data : {
					userAction : "get_client"
                  
				},
				success : function(data) {
					
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_client_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var clientId = data[i].clientId;
						var clientName = data[i].clientName;
						$('#filter_operation_client_select').
					    append("<option value=\""+clientId+"\">"+clientName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_client_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var clientId = data[i].clientId;
							var clientName = data[i].clientName;
						    $('#update_operation_client_select').
						    append("<option value=\""+clientId+"\">"+clientName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};


//Get Brand Category
var getBrandCategory = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageBrand",
				data : {
					userAction : "get_brand_category"
                  
				},
				success : function(data) {
					
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_brand_category_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var brandCategoryId = data[i].brandCategoryId;
						var brandCategoryName = data[i].brandCategoryName;
						$('#add_operation_brand_category_select').
					    append("<option value=\""+brandCategoryId+"\">"+brandCategoryName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_brand_category_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var brandCategoryId = data[i].brandCategoryId;
							var brandCategoryName = data[i].brandCategoryName;
						    $('#update_operation_brand_category_select').
						    append("<option value=\""+brandCategoryId+"\">"+brandCategoryName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};
