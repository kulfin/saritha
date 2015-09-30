function showUpdateDiv(storeStatusId,storeStatusName,description){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_store_status_id_input").value=storeStatusId;
     document.getElementById("update_operation_store_status_name_input").value=storeStatusName;
     document.getElementById("update_operation_description_input").value=description;
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}
var validateAddOperation=function(){
	var storeStatusName = $('#add_operation_store_status_name_input');
	if (storeStatusName.val().trim() == "") {
		alert("Please Enter Store Status Name");
		storeStatusName.focus();
		return;

	}
/*	var n = storeStatusName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(storeStatusName.val().charAt(i))){
            alert("Enter only Alphabets");
            storeStatusName.focus();
            return false;             
        }
    }*/
	
	var description = $('#add_operation_description_input');
	if (description.val().trim() == "") {
		alert("Please Enter Description");
		description.focus();
		return;

	}
	setStoreStatus(storeStatusName.val(),description.val());
};

var validateUpdateOperation=function(){
	var storeStatusId = $('#update_operation_store_status_id_input');
	var storeStatusName = $('#update_operation_store_status_name_input');
	if (storeStatusName.val() == "") {
		alert("Please Enter Store Status Name");
		storeStatusName.focus();
		return;

	}
	
	var description = $('#update_operation_description_input');
	if (description.val() == "") {
		alert("Please Enter Description");
		description.focus();
		return;

	}
	updateStoreStatus(storeStatusId.val(),storeStatusName.val(),description.val());
};

//Get Store Status
var getStoreStatus = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageStoreStatus",
				data : {
					userAction : "get_store_status"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Store Status Name</th>'+
					'<th>Description</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var storeStatusId = data[i].storeStatusId;
						var storeStatusName = data[i].storeStatusName;
						var description = data[i].description;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+storeStatusId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+storeStatusId+"','"+storeStatusName+"','"+description+"')\"></td>"+
								'<td>'+ storeStatusName + '</td>'+
								'<td>'+ description + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Store Status
var setStoreStatus = function(storeStatusName,description) {

	$.ajax( {
		type : 'POST',
		url : '../ManageStoreStatus',
		data : {
			userAction : 'set_store_status',
			storeStatusName : storeStatusName,
			description : description

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Store Status is Added Succesfully');
				getStoreStatus();
				$('#add_operation_store_status_name_input').val('');
				$('#add_operation_description_input').val('');
				$('#add_operation_store_status_name_input').focus();
			} else if(status == -3){
				alert('Store Status name already Exists');
				$('#add_operation_store_status_name_input').focus();
			}else {
				alert('Store Status Addition Failed');
				$('#add_operation_store_status_name_input').focus();
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Store Status
var deleteStoreStatus = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var storeStatusId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				storeStatusId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageStoreStatus',
		data : {
			userAction : "delete_store_status",
			storeStatusId : storeStatusId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Store Status is Deleted Succesfully');
				getStoreStatus();
			} else {
				alert('Store Status Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Store Status
var updateStoreStatus = function(storeStatusId, storeStatusName,description) {

	$.ajax( {
		type : 'POST',
		url : '../ManageStoreStatus',
		data : {
			userAction : 'update_store_status',
			storeStatusId : storeStatusId,
			storeStatusName : storeStatusName,
			description : description
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Store Status is Updated Succesfully');
				getStoreStatus();
				showAddDiv();
			} else {
				alert('Store Status Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   