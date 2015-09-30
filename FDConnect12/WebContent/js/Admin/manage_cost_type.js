function showUpdateDiv(costTypeId,costTypeName){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_cost_type_id_input").value=costTypeId;
     document.getElementById("update_operation_cost_type_name_input").value=costTypeName;	
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}

var validateAddOperation=function(){
	var costTypeName = $('#add_operation_cost_type_name_input');
	if (costTypeName.val().trim() == "") {
		alert("Please Enter Cost Type Name");
		costTypeName.focus();
		return;

	}
/*	var n = costTypeName.val().toString().length;

	for ( var i = 0; i < n; i++) {
		if (!isChar(costTypeName.val().charAt(i))) {
			alert("Enter only Alphabets");
			costTypeName.focus();
			return false;
		}
	}*/

	setCostType(costTypeName.val());
};

var validateUpdateOperation=function(){
	var costTypeId = $('#update_operation_cost_type_id_input');
	var costTypeName = $('#update_operation_cost_type_name_input');
	if (costTypeName.val() == "") {
		alert("Please Enter Cost Type Name");
		costTypeName.focus();
		return;

	}
	updateCostType(costTypeId.val(),costTypeName.val());
};

//Get Cost Type
var getCostType = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageCostType",
				data : {
					userAction : "get_cost_type"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Cost Type Name</th></tr>');
					for ( var i = 0; i < len; ++i) {
						var costTypeId = data[i].costTypeId;
						var costTypeName = data[i].costTypeName;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+costTypeId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+costTypeId+"','"+costTypeName+"')\"></td>"+
								'<td>'+ costTypeName + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Cost Type
var setCostType = function(costTypeName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageCostType',
		data : {
			userAction : 'set_cost_type',
			costTypeName : costTypeName

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Cost Type is Added Succesfully');
				document.getElementById("add_operation_cost_type_name_input").focus();
				$('#add_operation_cost_type_name_input').val('');
				getCostType();
			}else if(status == -3){
				alert("Cost Type Already Exists");
				document.getElementById("add_operation_cost_type_name_input").focus();
				return false;
			}
			else {
				alert('Cost Type Addition Failed');
				document.getElementById("add_operation_cost_type_name_input").focus();
			}
			
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Cost Type
var deleteCostType = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var costTypeId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				costTypeId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageCostType',
		data : {
			userAction : "delete_cost_type",
			costTypeId : costTypeId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Cost Type is Deleted Succesfully');
				getCostType();
			} else {
				alert('Cost Type Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Cost Type
var updateCostType = function(costTypeId, costTypeName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageCostType',
		data : {
			userAction : 'update_cost_type',
			costTypeId : costTypeId,
			costTypeName : costTypeName
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Cost Type is Updated Succesfully');
				getCostType();
				showAddDiv();
			} else {
				alert('Cost Type  Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   