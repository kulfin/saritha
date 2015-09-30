function showUpdateDiv(retailClientId,retailClientName,address){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_retail_client_id_input").value=retailClientId;
     document.getElementById("update_operation_retail_client_name_input").value=retailClientName;
     document.getElementById("update_operation_address_input").value=address;
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}
var validateAddOperation=function(){
	var retailClientName = $('#add_operation_retail_client_name_input');
	if (retailClientName.val().trim() == "") {
		alert("Please Enter Retail Client Name");
		retailClientName.focus();
		return;

	}
	var n =retailClientName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(retailClientName.val().charAt(i))){
            alert("Enter only Alphabets");
            retailClientName.focus();
            return false;             
        }
    }
	var address = $('#add_operation_address_input');
	if (address.val().trim() == "") {
		alert("Please Enter Address");
		address.focus();
		return;

	}
	setRetailClient(retailClientName.val(),address.val());
};

var validateUpdateOperation=function(){
	var retailClientId = $('#update_operation_retail_client_id_input');
	var retailClientName = $('#update_operation_retail_client_name_input');
	if (retailClientName.val() == "") {
		alert("Please Enter Retail Client Name");
		retailClientName.focus();
		return;

	}
	
	var address = $('#update_operation_address_input');
	if (address.val() == "") {
		alert("Please Enter Address");
		address.focus();
		return;

	}
	updateRetailClient(retailClientId.val(),retailClientName.val(),address.val());
};

//Get Retail Client
var getRetailClient = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageRetailClient",
				data : {
					userAction : "get_retail_client"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Retail Client Name</th>'+
					'<th>Address</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var retailClientId = data[i].retailClientId;
						var retailClientName = data[i].retailClientName;
						var address = data[i].address;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+retailClientId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+retailClientId+"','"+retailClientName+"','"+address+"')\"></td>"+
								'<td>'+ retailClientName + '</td>'+
								'<td>'+ address + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Retail Client
var setRetailClient = function(retailClientName,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageRetailClient',
		data : {
			userAction : 'set_retail_client',
			retailClientName : retailClientName,
			address : address

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Retail Client is Added Succesfully');
				getRetailClient();
				$('#add_operation_retail_client_name_input').val('');
				$('#add_operation_address_input').val('');
			} else {
				alert('Retail Client Addition Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Retail Client
var deleteRetailClient = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var retailClientId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				retailClientId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageRetailClient',
		data : {
			userAction : "delete_retail_client",
			retailClientId : retailClientId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Retail Client is Deleted Succesfully');
				getRetailClient();
			} else {
				alert('Retail Client Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Retail Client
var updateRetailClient = function(retailClientId, retailClientName,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageRetailClient',
		data : {
			userAction : 'update_retail_client',
			retailClientId : retailClientId,
			retailClientName : retailClientName,
			address : address
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Retail Client is Updated Succesfully');
				getRetailClient();
				showAddDiv();
			} else {
				alert('Retail Client Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   