function showUpdateDiv(measurementStatusId,measurementStatusName){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_measurement_status_id_input").value=measurementStatusId;
     document.getElementById("update_operation_measurement_status_name_input").value=measurementStatusName;	
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}

var validateAddOperation=function(){
	var measurementStatusName = $('#add_operation_measurement_status_name_input');
	if (measurementStatusName.val() == "") {
		alert("Please Enter Measurement Status Name");
		measurementStatusName.focus();
		return;

	}
	setMeasurementStatus(measurementStatusName.val());
};

var validateUpdateOperation=function(){
	var measurementStatusId = $('#update_operation_measurement_status_id_input');
	var measurementStatusName = $('#update_operation_measurement_status_name_input');
	if (measurementStatusName.val() == "") {
		alert("Please Enter Measurement Status Name");
		measurementStatusName.focus();
		return;

	}
	updateMeasurementStatus(measurementStatusId.val(),measurementStatusName.val());
};

//Get Measurement Status
var getMeasurementStatus = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageMeasurementStatus",
				data : {
					userAction : "get_measurement_status"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Measurement Status Name</th></tr>');
					for ( var i = 0; i < len; ++i) {
						var measurementStatusId = data[i].measurementStatusId;
						var measurementStatusName = data[i].measurementStatusName;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+measurementStatusId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+measurementStatusId+"','"+measurementStatusName+"')\"></td>"+
								'<td>'+ measurementStatusName + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Measurement Status
var setMeasurementStatus = function(measurementStatusName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageMeasurementStatus',
		data : {
			userAction : 'set_measurement_status',
			measurementStatusName : measurementStatusName

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Measurement Status is Added Succesfully');
				getMeasurementStatus();
				$('#add_operation_measurement_status_name_input').val('');
				$('#add_operation_measurement_status_name_input').focus();
			} else if(status == -3){
				alert('Data already Exists');
				$('#add_operation_measurement_status_name_input').focus();
			}else {
				$('#add_operation_measurement_status_name_input').val('');
				$('#add_operation_measurement_status_name_input').focus();
				alert('Measurement Status Addition Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Measurement Status
var deleteMeasurementStatus = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var measurementStatusId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				measurementStatusId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageMeasurementStatus',
		data : {
			userAction : "delete_measurement_status",
			measurementStatusId : measurementStatusId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Measurement Status is Deleted Succesfully');
				getMeasurementStatus();
			} else {
				alert('Measurement Status Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Measurement Status
var updateMeasurementStatus = function(measurementStatusId, measurementStatusName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageMeasurementStatus',
		data : {
			userAction : 'update_measurement_status',
			measurementStatusId : measurementStatusId,
			measurementStatusName : measurementStatusName
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Measurement Status is Updated Succesfully');
				getMeasurementStatus();
				showAddDiv();
			} else {
				alert('Measurement Status Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   