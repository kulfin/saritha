var getAssociates = function() {

	$.ajax( {
		type : "GET",
		url : "../ManageAssociates",
		data : {
			userAction : "get_associates"

		},
		success : function(data) {
			// alert("got response "+data);
			var len = data.length;
			$('#content_view_table')
					.html(
							'<tr><th>Select</th><th>Edit</th>' + '<th>Associate Name</th><th>Address</th><th>City</th><th>Email</th><th>Contact Name</th>' + '<th>Contact</th><th>TIN</th><th>CST</th><th>Excisable</th></tr>');
			for ( var i = 0; i < len; ++i) {
				$('#content_view_table')
				.append(
						'<tr>'
								+ '<td><input type=\'checkbox\' value=\''
								+ data[i].associateId
								+ '\'></td>'
								+ "<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"
								+ data[i].associateId + "','" + data[i].associateName + "','"
								+ data[i].associateAddress + "','" + data[i].associateCity + "','"
								+ data[i].associateEmailId +  "','" + data[i].associateContactName + "','"
								+ data[i].associateContactPhone + "','" + data[i].associateTinNo + "','"
								+ data[i].associateCstNo + "','" + data[i].associateExcisable
								+ "')\"></td>" 
								+ '<td>' + data[i].associateName
								+ '</td>' + '<td>'
								+ data[i].associateAddress
								+ '</td>' + '<td>'
								+ data[i].associateCity
								+ '</td>' + '<td>'
								+ data[i].associateEmailId
								+ '</td>' + '<td>'
								+ data[i].associateContactName
								+ '</td>' + '<td>'
								+ data[i].associateContactPhone
								+ '</td>' + '<td>'
								+ data[i].associateTinNo
								+ '</td>' + '<td>'
								+ data[i].associateCstNo
								+ '</td>' + '<td>'
								+ data[i].associateExcisable
								+ '</td>' + '</tr>');
	}
				/*$('#content_view_table')
						.append(
								'<tr>'
										+ '<td><input type=\'checkbox\' value=\''
										+ data[i].associateId
										+ '\'></td>'
										+ "<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"
										+ data[i].associateId + "','" + data[i].associateName + "','"
										+ data[i].associateAddress + "','" + data[i].associateCity + "','"
										+ data[i].associateEmailId +  "','" + data[i].associateContactName + "','"
										+ data[i].associateContactPhone + "','" + data[i].associateTinNo + "','"
										+ data[i].associateCstNo + "','" + data[i].associateExcisable
										+ "')\"></td>" + "<td><input type=\"image\" src=\"../images/info.png\" onclick=\"showCapacityDetails('"
										+ data[i].associateId + "')\"></td>"
										+ '<td>' + data[i].associateName
										+ '</td>' + '<td>'
										+ data[i].associateAddress
										+ '</td>' + '<td>'
										+ data[i].associateCity
										+ '</td>' + '<td>'
										+ data[i].associateEmailId
										+ '</td>' + '<td>'
										+ data[i].associateContactName
										+ '</td>' + '<td>'
										+ data[i].associateContactPhone
										+ '</td>' + '<td>'
										+ data[i].associateTinNo
										+ '</td>' + '<td>'
										+ data[i].associateCstNo
										+ '</td>' + '<td>'
										+ data[i].associateExcisable
										+ '</td>' + '</tr>');
			}*/
		},
		async : false,
		error : function(xhr, ajaxOptions, thrownError) {
			alert('here' + xhr.status);
			alert(thrownError);
		}
	});
};

function emailValidate(email) {
	var x = $('#emailId').val();
	var emailRegexStr = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	var isvalid = emailRegexStr.test(x);

	if (!isvalid) {
		return false;
	}
	else
	{
		return true;
	}
}

function validateAddOperation() {
	var name;
	var address;
	var city;
	var email;
	var contact_name;
	var contact_phone;
	var tin_no;
	var cst_no;
	var excisable;
	name = $('#AnameId').val();
	if (name == "") {
		alert('AssociateName Filed Cannot be empty');
		$('#AnameId').focus();
		return false;
	}
	address = $('#addressId').val();
	if (address == "" || address.length == 0) {
		alert('Address Filed Cannot be empty');
		$('#addressId').focus();
		return false;
	}
	city = $('#cityId').val();
	if (city == "") {
		alert('CityName Filed Cannot be empty');
		$('#cityId').focus();
		return false;
	}
	email = $('#emailId').val();
	if (email == "") {
		alert('EmailAddress Filed Cannot be empty');
		$('#emailId').focus();
		return false;
	}
	
	if (email != "") {
		if (!emailValidate(email)) {
			alert("Not a valid e-mail address");
			$('#emailId').focus();
			return false;
		}
	}
	contact_name = $('#contactName').val();
	if (contact_name == "") {
		alert('ContactName Filed Cannot be empty');
		$('#contactName').focus();
		return false;
	}
	contact_phone = $('#contactPhone').val();
	/*if (contact_phone == "") {
		alert('ContactPhone Filed Cannot be empty');
		$('#contactPhone').focus();
		return false;
	}*/
	/*if(contact_phone.length < 10)
	{
		alert('Contact number cannot be less than 10');
		$('#contactPhone').focus();
		return false;
	}*/
	tin_no = $('#tinId').val();
	/*if (tin_no == "") {
		alert('TIN Filed Cannot be empty');
		$('#tinId').focus();
		return false;
	}*/
	cst_no = $('#cstId').val();
	/*if (cst_no == "") {
		alert('CST Filed Cannot be empty');
		$('#cstId').focus();
		return false;
	}*/
	excisable = $('#excisable option:selected').val();
	if (excisable == "select") {
		alert('Please select Excisable');
		$('#excisable').focus();
		return false;
	}
	//alert('selcted excisable is ' + excisable);
	setAssociate(name,address,city,email,contact_name,contact_phone,tin_no,cst_no,excisable);
}


var setAssociate =function(name,address,city,email,contact_name,contact_phone,tin_no,cst_no,excisable) {
	//alert('in setAssociate');
	//alert('selcted excisable is ' + excisable);
		$.ajax( {
			type : 'POST',
			url : '../ManageAssociates',
			data : {
				userAction : 'set_associate',
				name:name,
				address:address,
				city:city,
				email:email,
		        contact_name:contact_name,
		        contact_phone:contact_phone,
		        tin_no:tin_no,
		        cst_no : cst_no,
		        excisable:excisable
			},
			success : function(data) {
				var status = data.status;
				if (status == 0) {
					alert('Associate Details Added Succesfully');
					$('#AnameId').val('');
					$('#addressId').val('');
					$('#cityId').val('');
					$('#emailId').val('');
					$('#contactName').val('');
					$('#contactPhone').val('');
					$('#tinId').val('');
					$('#cstId').val('');
					$('#excisable').val('');
					$('#AnameId').focus();
					getAssociates();
										
				} else if(status == -3){
					alert('Data Already Exists');
					$('#AnameId').focus();
				}else {
					alert('Associate Addition Failed');
					$('#AnameId').val('');
					$('#addressId').val('');
					$('#cityId').val('');
					$('#emailId').val('');
					$('#contactName').val('');
					$('#contactPhone').val('');
					$('#tinId').val('');
					$('#cstId').val('');
					$('#excisable').val('');
					$('#AnameId').focus();
				}
			},
			error : function(error) {
				alert("Error");
			}
		});
	};


	var deleteAssociate = function(tableID) {
		//alert('in delete');
		try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var associateId = new Array();
			var j = 0;
			for ( var i = 1; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					associateId[j] = chkbox.value;
					j++;
				}

			}
		} catch (e) {
			alert(e);
		}
	 
		$.ajax( {
			type : 'POST',
			url : '../ManageAssociates',
			data : {
				userAction : "delete_associate",
				associateId : associateId
			},
			success : function(data) {
				var status = data.status;
				if (status == 0) {
					alert('Associate is Deleted Succesfully');
					getAssociates();
				} else {
					alert('Associate Deletion Failed');
				}
			},
			error : function(error) {
				alert("Error");
			}
		});
	};
	
function showUpdateDiv(associateId,associateName,associateAddress,associateCity,associateEmail,associateContact_name,associateContact_phone,associateTin_no,associateCst_no,associateExcisable) {
	//alert('update');
	$('#associate_add').hide();
	$('#associate_update').show();
	
	$('#upassociateId').val(associateId);
	$('#upAnameId').val(associateName);
	$('#upaddressId').val(associateAddress);
	$('#upcityId').val(associateCity);
	$('#upemailId').val(associateEmail);
	$('#upcontactName').val(associateContact_name);
	$('#upcontactPhone').val(associateContact_phone);
	$('#uptinNo').val(associateTin_no);
	$('#upcstNo').val(associateCst_no);
	$('#upexcisable').val(associateExcisable);
}

function showAddDiv(){
	$('#associate_add').show();
	$('#associate_update').hide();
}

var validateUpdateOperation=function(){
	var upassociateId;
	var upname;
	var upaddress;
	var upcity;
	var upemail;
	var upcontact_name;
	var upcontact_phone;
	var uptin_no;
	var upcst_no;
	var upexcisable;
	upassociateId = $('#upassociateId').val();
	upname = $('#upAnameId').val();
	if (upname == "") {
		alert('AssociateName Filed Cannot be empty');
		$('#upAnameId').focus();
		return false;
	}
	upaddress = $('#upaddressId').val();
	if (upaddress == "" || upaddress.length == 0) {
		alert('Address Filed Cannot be empty');
		$('#upaddressId').focus();
		return false;
	}
	upcity = $('#upcityId').val();
	if (upcity == "") {
		alert('CityName Filed Cannot be empty');
		$('#upcityId').focus();
		return false;
	}
	upemail = $('#upemailId').val();
	if (upemail == "") {
		alert('EmailAddress Filed Cannot be empty');
		$('#upemailId').focus();
		return false;
	}
	
	if (upemail != "") {
		if (emailValidate(upemail)) {
			alert("Not a valid e-mail address");
			$('#upemailId').focus();
			return false;
		}
	}
	upcontact_name = $('#upcontactName').val();
	if (upcontact_name == "") {
		alert('ContactName Filed Cannot be empty');
		$('#upcontactName').focus();
		return false;
	}
	upcontact_phone = $('#upcontactPhone').val();
	/*if (upcontact_phone == "") {
		alert('ContactPhone Filed Cannot be empty');
		$('#upcontactPhone').focus();
		return false;
	}
	if(upcontact_phone.length < 10)
	{
		alert('Contact number cannot be less than 10');
		$('#upcontactPhone').focus();
		return false;
	}*/
	uptin_no = $('#uptinNo').val();
	if (uptin_no == "") {
		alert('TIN Filed Cannot be empty');
		$('#uptinNo').focus();
		return false;
	}
	upcst_no = $('#upcstNo').val();
	if (upcst_no == "") {
		alert('CST Filed Cannot be empty');
		$('#upcstNo').focus();
		return false;
	}
	upexcisable = $('#upexcisable option:selected').val();
	if (upexcisable == "select") {
		alert('Please select Excisable');
		$('#upexcisable').focus();
		return false;
	}
	updateAssociate(upassociateId,upname,upaddress,upcity,upemail,upcontact_name,upcontact_phone,uptin_no,upcst_no,upexcisable);
};


var updateAssociate = function(associateId,associateName,associateAddress,associateCity,associateEmail,
		associateContact_name,associateContact_phone,associateTin_no,associateCst_no,associateExcisable){
	//alert('associate id is ' + associateId);
	$.ajax( {
		type : 'POST',
		url : '../ManageAssociates',
		data : {
			userAction : 'update_associate',
			associateId:associateId,
			associateName:associateName,
			associateAddress:associateAddress,
			associateCity:associateCity,
			associateEmail:associateEmail,
			associateContact_name:associateContact_name,
			associateContact_phone:associateContact_phone,
			associateTin_no:associateTin_no,
			associateCst_no:associateCst_no,
			associateExcisable:associateExcisable
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Associate is Updated Succesfully');
				getAssociates();
				showAddDiv();
			} else {
				alert('Associate Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};




var showCapacityDetails = function(associateId)
{
	alert('in capacity details');
	$('#associate_add').hide();
	$('#associateDetails').show();
	
	$.ajax( {
		type : 'POST',
		url : '../ManageAssociates',
		data : {
			userAction : 'associate_capacity_details',
			associateId:associateId
		},
		success : function(data) {
			var status = data.status;
			alert(data);
			$('#capacity_view_table')
			.html('<tr><th>AssociateName</th><th>ProcessName</th><th>Capacity</th>' + '</tr>');
			for ( var i = 0; i < len; ++i) {
				$('#capacity_view_table').append('<tr>'	+ '<td>'+ data[i].associateName+'</td>'
														+'<td>'+ data[i].processName + '</td>' 
														+ '<td>'+ data[i].capacity + '</td>'
												+ '</tr>');
				}
		},
		error : function(error) {
			alert("Error");
		}

	});
};