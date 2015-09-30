function showUpdateDiv(documentTypeId,documentTypeName,description,owner){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_document_type_id_input").value=documentTypeId;
     document.getElementById("update_operation_document_type_name_input").value=documentTypeName;
     document.getElementById("update_operation_description_input").value=description;
     document.getElementById("update_operation_owner_input").value=owner;
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}
var validateAddOperation=function(){
	var documentTypeName = $('#add_operation_document_type_name_input');
	if (documentTypeName.val().trim() == "") {
		alert("Please Enter Store Status Name");
		documentTypeName.focus();
		return;

	}
	
	var owner = $('#add_operation_owner_input');
	if (owner.val().trim() == "") {
		alert("Please Enter Owner Name");
		documentTypeName.focus();
		return;

	}
/*	var n = documentTypeName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(documentTypeName.val().charAt(i))){
            alert("Enter only Alphabets");
            documentTypeName.focus();
            return false;             
        }
    }*/
	
	var description = $('#add_operation_description_input');
	if (description.val().trim() == "") {
		alert("Please Enter Description");
		description.focus();
		return;

	}
	setDocumentType(documentTypeName.val(),owner.val(),description.val());
};

var validateUpdateOperation=function(){
	var documentTypeId = $('#update_operation_document_type_id_input');
	var documentTypeName = $('#update_operation_document_type_name_input');
	if (documentTypeName.val() == "") {
		alert("Please Enter Store Status Name");
		documentTypeName.focus();
		return;

	}
	
	var owner = $('#update_operation_owner_input');
	if (owner.val().trim() == "") {
		alert("Please Enter Owner Name");
		documentTypeName.focus();
		return;

	}
	
	var description = $('#update_operation_description_input');
	if (description.val() == "") {
		alert("Please Enter Description");
		description.focus();
		return;

	}
	updateDocumentType(documentTypeId.val(),documentTypeName.val(),owner.val(),description.val());
};

//Get Store Status
var getDocumentType = function() {
 
	//alert("get document Type");
	$.ajax( {
				type : "POST",
				url : "../ManageDocumentType",
				data : {
					userAction : "get_document_type"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Document Type</th>'+
					'<th>Description</th>'+
					'<th>Owner</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var documentTypeId = data[i].documentTypeId;
						var documentTypeName = data[i].documentTypeName;
						var description = data[i].description;
						var owner = data[i].owner;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+documentTypeId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+documentTypeId+"','"+documentTypeName+"','"+description+"','"+owner+"')\"></td>"+
								'<td>'+ documentTypeName + '</td>'+
								'<td>'+ description + '</td>'+
								'<td>'+ owner + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Store Status
var setDocumentType = function(documentTypeName,owner,description,owner) {

	$.ajax( {
		type : 'POST',
		url : '../ManageDocumentType',
		data : {
			userAction : 'set_document_type',
			documentTypeName : documentTypeName,
			owner : owner,
			description : description

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Store Status is Added Succesfully');
				getDocumentType();
				$('#add_operation_document_type_name_input').val('');
				$('#add_operation_description_input').val('');
				$('#add_operation_owner_input').val('');
				$('#add_operation_document_type_name_input').focus();
				
			} else if(status == -3){
				alert('Store Status name already Exists');
				$('#add_operation_document_type_name_input').focus();
			}else {
				alert('Store Status Addition Failed');
				$('#add_operation_document_type_name_input').focus();
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Store Status
var deleteDocumentType = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var documentTypeId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				documentTypeId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageDocumentType',
		data : {
			userAction : "delete_document_type",
			documentTypeId : documentTypeId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Store Status is Deleted Succesfully');
				getDocumentType();
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
var updateDocumentType = function(documentTypeId, documentTypeName,owner,description) {

	$.ajax( {
		type : 'POST',
		url : '../ManageDocumentType',
		data : {
			userAction : 'update_document_type',
			documentTypeId : documentTypeId,
			documentTypeName : documentTypeName,
			owner : owner,
			description : description
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Store Status is Updated Succesfully');
				getDocumentType();
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


   