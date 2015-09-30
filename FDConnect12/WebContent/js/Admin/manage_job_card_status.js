function showUpdateDiv(jobCardStatusId,jobCardStatusName,roleId,roleName){
	getRoleUpdate(roleId);
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_job_card_status_id_input").value=jobCardStatusId;
     document.getElementById("update_operation_job_card_status_name_input").value=jobCardStatusName;	
//     document.getElementById("update_operation_job_card_role_id_select").value=roleId;
     
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}

var validateAddOperation=function(){
	var jobCardStatusName = $('#add_operation_job_card_status_name_input');
	if (jobCardStatusName.val() == "") {
		alert("Please Enter Job Card Status Name");
		jobCardStatusName.focus();
		return;
	}
	var role = $('#select_role_name');
	if (role.val() == "select") {
			alert("Please Select Role");
		select_role_name.focus();
		return;
	}
	
	setJobCardStatus(jobCardStatusName.val(),role.val());
};

var validateUpdateOperation=function(){
	var jobCardStatusId = $('#update_operation_job_card_status_id_input');
	var jobCardStatusName = $('#update_operation_job_card_status_name_input');
	if (jobCardStatusName.val() == "") {
		alert("Please Enter Job Card Status Name");
		jobCardStatusName.focus();
		return;

	}
	var roleId = $('#update_operation_job_card_role_id_select');
	if (roleId.val() == "select") {
		alert("Please Select Role");
		update_operation_job_card_role_id_select.focus();
		return;

	}
	updateJobCardStatus(jobCardStatusId.val(),jobCardStatusName.val(),roleId.val());
};

//Get Job Card Status
var getJobCardStatus = function() {
 
	$.ajax( {
		type : "POST",
		url : "../ManageJobCardStatus",
		data : {
			userAction : "get_job_card_status"
          
		},
		success : function(data) {
		var len = data.length;
			$('#content_view_table').html(
			'<tr><th>Select</th><th>Edit</th>'+
			'<th>Role</th><th>Job Card Status Name</th></tr>');
			for ( var i = 0; i < len; ++i) {
				var jobCardStatusId = data[i].jobCardStatusId;
				var jobCardStatusName = data[i].jobCardStatusName;
				var roleId = data[i].roleId;
				var roleName = data[i].roleName;
				$('#content_view_table').append('<tr>'+
						'<td><input type=\'checkbox\' value=\''+jobCardStatusId+'\'></td>'+
						"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+jobCardStatusId+"','"+jobCardStatusName+"','"+ roleId +"','"+ roleName +"')\"></td>"+
						'<td>'+ roleName + '</td>'+
						'<td>'+ jobCardStatusName + '</td>'+
						'</tr>');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Set Job Card Status
var setJobCardStatus = function(jobCardStatusName,roleId) {

	$.ajax( {
		type : 'POST',
		url : '../ManageJobCardStatus',
		data : {
			userAction : 'set_job_card_status',
			jobCardStatusName : jobCardStatusName,
			roleId : roleId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Job Card Status is Added Succesfully');
				$('#select_role_name option:eq(0)').attr('selected','selected');
				getJobCardStatus();
				$('#add_operation_job_card_status_name_input').val('');
				$('#add_operation_job_card_status_name_input').focus();
			} else if(status == -3){
				alert('Data Already Exists');
				$('#add_operation_job_card_status_name_input').focus();
			}else {
				alert('Job Card Status Addition Failed');
				$('#add_operation_job_card_status_name_input').focus();
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Job Card Status
var deleteJobCardStatus = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var jobCardStatusId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				jobCardStatusId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageJobCardStatus',
		data : {
			userAction : "delete_job_card_status",
			jobCardStatusId : jobCardStatusId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Job Card Status is Deleted Succesfully');
				getJobCardStatus();
			} else {
				alert('Job Card Status Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Job Card Status
var updateJobCardStatus = function(jobCardStatusId, jobCardStatusName,roleId) {

	$.ajax( {
		type : 'POST',
		url : '../ManageJobCardStatus',
		data : {
			userAction : 'update_job_card_status',
			jobCardStatusId : jobCardStatusId,
			jobCardStatusName : jobCardStatusName,
			roleId : roleId
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Job Card Status is Updated Succesfully');
				getJobCardStatus();
				showAddDiv();
			} else {
				alert('Job Card Status Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Update Job Card Status
var getRoles = function() {

	$.ajax( {
		type : 'POST',
		url : '../ManageJobCardStatus',
		data : {
			userAction : 'get_role'
		},
		success : function(data) {
			var len = data.length; 
			for ( var i = 0; i < len; ++i) {
				var roleId = data[i].roleId;
				var roleName = data[i].roleName;
				$('#select_role_name').append('<option value='+roleId +'>'+roleName +'</option>');
			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Update Job Card Status
var getRoleUpdate = function(selectedRoleId) {

	$.ajax( {
		type : 'POST',
		url : '../ManageJobCardStatus',
		data : {
			userAction : 'get_role'
		},
		success : function(data) {
			var len = data.length; 
			for ( var i = 0; i < len; ++i) {
				var roleId = data[i].roleId;
				var roleName = data[i].roleName;
				$('#update_operation_job_card_role_id_select').append('<option value='+roleId +'>'+roleName +'</option>');
			}
			var role_list = document.getElementById('update_operation_job_card_role_id_select');
			for (i = 0; i < role_list.options.length; i++) {
				if (selectedRoleId == (role_list.options[i].value)) {
					role_list.options[i].selected = true;
					return;
				}
			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   