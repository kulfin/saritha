function showUpdateDiv(brandCategoryId,brandCategoryName){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_brand_category_id_input").value=brandCategoryId;
     document.getElementById("update_operation_brand_category_name_input").value=brandCategoryName;	
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}
var validateAddOperation=function(){
	var brandCategoryName = $('#add_operation_brand_category_name_input');
	if (brandCategoryName.val().trim() == "") {
		alert("Please Enter Brand Category Name");
		brandCategoryName.focus();
		return;

	}
/*	var n =brandCategoryName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(brandCategoryName.val().charAt(i))){
            alert("Enter only Alphabets");
            brandCategoryName.focus();
            return false;             
        }
    }*/
	setBrandCategory(brandCategoryName.val());
};

var validateUpdateOperation=function(){
	var brandCategoryId = $('#update_operation_brand_category_id_input');
	var brandCategoryName = $('#update_operation_brand_category_name_input');
	if (brandCategoryName.val() == "") {
		alert("Please Enter Brand Category Name");
		brandCategoryName.focus();
		return;

	}
	updateBrandCategory(brandCategoryId.val(),brandCategoryName.val());
};

//Get Brand Category
var getBrandCategory = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageBrandCategory",
				data : {
					userAction : "get_brand_category"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Brand Category Name</th></tr>');
					for ( var i = 0; i < len; ++i) {
						var brandCategoryId = data[i].brandCategoryId;
						var brandCategoryName = data[i].brandCategoryName;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+brandCategoryId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+brandCategoryId+"','"+brandCategoryName+"')\"></td>"+
								'<td>'+ brandCategoryName + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Brand Category
var setBrandCategory = function(brandCategoryName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageBrandCategory',
		data : {
			userAction : 'set_brand_category',
			brandCategoryName : brandCategoryName

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Brand Category is Added Succesfully');
				$('#add_operation_brand_category_name_input').val('');
				$('#add_operation_brand_category_name_input').focus();
				getBrandCategory();
				$('#add_operation_brand_category_name_input').val('');
			}else if(status == -3){
				alert("Brand Category exists with the name: " + brandCategoryName );
				$('#add_operation_brand_category_name_input').focus();
			}
			else {
				alert('Brand Category Addition Failed Please try again');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Brand Category
var deleteBrandCategory = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var brandCategoryId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				brandCategoryId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageBrandCategory',
		data : {
			userAction : "delete_brand_category",
			brandCategoryId : brandCategoryId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Brand Category is Deleted Succesfully');
				getBrandCategory();
			} else {
				alert('Brand Category Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Brand Category
var updateBrandCategory = function(brandCategoryId, brandCategoryName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageBrandCategory',
		data : {
			userAction : 'update_brand_category',
			brandCategoryId : brandCategoryId,
			brandCategoryName : brandCategoryName
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Brand Category is Updated Succesfully');
				getBrandCategory();
				showAddDiv();
			} else {
				alert('Brand Category Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   