function showUpdateDiv(clientId,clientName,localCurrency,baseCurrency,
		tinNumber,cstNumber,countryId,stateId,cityId,pinCode,address){
	
	
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
	 
	// Country
    getCountry('update');
	var typeDdl = document.getElementById('update_operation_country_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (countryId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
	// State
    getState('update');
	var typeDdl = document.getElementById('update_operation_state_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (stateId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
	// City
    getCity('update');
	var typeDdl = document.getElementById('update_operation_city_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (cityId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	

     document.getElementById("update_operation_client_id_input").value=clientId;
     document.getElementById("update_operation_client_name_input").value=clientName;
     document.getElementById("update_operation_local_currency_input").value=localCurrency;
     document.getElementById("update_operation_base_currency_input").value=baseCurrency;
     document.getElementById("update_operation_tin_number_input").value=tinNumber;
     document.getElementById("update_operation_cst_number_input").value=cstNumber;
     document.getElementById("update_operation_pin_code_input").value=pinCode;
     document.getElementById("update_operation_address_input").value=address;
     
     
     
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}

function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}
function isdigit(c){
	return(c >= '0' && c<='9');
	}
var validateAddOperation=function(){

    var countryId = $('#add_operation_country_select').val();
	if (document.getElementById("add_operation_country_select").selectedIndex == 0) {
		alert("Please Select Country");
        return;
	}
    
	var stateId = $('#add_operation_state_select').val();
	if (document.getElementById("add_operation_state_select").selectedIndex == 0) {
		alert("Please Select State");
        return;
	}
	
	var cityId = $('#add_operation_city_select').val();
	if (document.getElementById("add_operation_city_select").selectedIndex == 0) {
		alert("Please Select City");
        return;
	}
	
	var clientName = $('#add_operation_client_name_input');
	if (clientName.val().trim() == "") {
		alert("Please Enter Client Name");
		clientName.focus();
		return;
	}
	var n = clientName.val().toString().length;

    /*for(var i = 0; i < n; i++){
        if (! isChar(clientName.val().charAt(i))){
            alert("Enter only Alphabets for Client Name");
            clientName.focus();
            return false;             
        }
    }*/
	
	var localCurrency = $('#add_operation_local_currency_input');
	if (localCurrency.val() == "") {
		alert("Please Enter Local Currency");
		localCurrency.focus();
		return;
	}
	
	var baseCurrency = $('#add_operation_base_currency_input');
	if (baseCurrency.val() == "") {
		alert("Please Enter Base Currency");
		baseCurrency.focus();
		return;
	}
	
	var tinNumber = $('#add_operation_tin_number_input');
	if (tinNumber.val() == "") {
		alert("Please Enter TIN Number");
		tinNumber.focus();
		return;
	}
	
	var cstNumber = $('#add_operation_cst_number_input');
	if (cstNumber.val() == "") {
		alert("Please Enter CST Number");
		cstNumber.focus();
		return;
	}
	
	var pinCode = $('#add_operation_pin_code_input');
	if (pinCode.val().trim() == "") {
		alert("Please Enter Pin Code");
		pinCode.focus();
		return;
	}
	var n = pinCode.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isdigit(pinCode.val().charAt(i))){
            alert("Enter only Numerics for Zip Code");
            pinCode.focus();
    return false;
}
    }
	var address = $('#add_operation_address_input');
	if (address.val() == "") {
		alert("Please Enter Address");
		address.focus();
		return;

	}
	setClient(countryId,stateId,cityId,clientName.val(),
	 localCurrency.val(),baseCurrency.val(),tinNumber.val(),
	 cstNumber.val(),pinCode.val(),address.val());
};

var validateUpdateOperation=function(){
	
	var clientId = $('#update_operation_client_id_input').val();
	 var countryId = $('#update_operation_country_select').val();
		if (document.getElementById("update_operation_country_select").selectedIndex == 0) {
			alert("Please Select Country");
	        return;

		}
		
		var stateId = $('#update_operation_state_select').val();
		if (document.getElementById("update_operation_state_select").selectedIndex == 0) {
			alert("Please Select State");
	        return;

		}
		
		var cityId = $('#update_operation_city_select').val();
		if (document.getElementById("update_operation_city_select").selectedIndex == 0) {
			alert("Please Select City");
	        return;

		}
		
		var clientName = $('#update_operation_client_name_input');
		if (clientName.val() == "") {
			alert("Please Enter Client Name");
			clientName.focus();
			return;

		}
		
		var localCurrency = $('#update_operation_local_currency_input');
		if (localCurrency.val() == "") {
			alert("Please Enter Local Currency");
			localCurrency.focus();
			return;

		}
		
		var baseCurrency = $('#update_operation_base_currency_input');
		if (baseCurrency.val() == "") {
			alert("Please Enter Base Currency");
			baseCurrency.focus();
			return;

		}
		
		var tinNumber = $('#update_operation_tin_number_input');
		if (tinNumber.val() == "") {
			alert("Please Enter TIN Number");
			tinNumber.focus();
			return;

		}
		
		var cstNumber = $('#update_operation_cst_number_input');
		if (cstNumber.val() == "") {
			alert("Please Enter CST Number");
			cstNumber.focus();
			return;

		}
		
		var pinCode = $('#update_operation_pin_code_input');
		if (pinCode.val() == "") {
			alert("Please Enter Pin Code");
			pinCode.focus();
			return;

		}
		
		var address = $('#update_operation_address_input');
		if (address.val() == "") {
			alert("Please Enter Address");
			address.focus();
			return;

		}
		updateClient(countryId,stateId,cityId,clientId,clientName.val(),
		 localCurrency.val(),baseCurrency.val(),tinNumber.val(),
		 cstNumber.val(),pinCode.val(),address.val());
};

//Get Client
var getClient = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_client"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Client Name</th>'+
					'<th>Local Currency</th>'+
					'<th>Base Currency</th>'+
					'<th>TIN No.</th>'+
					'<th>CST No.</th>'+
					'<th>Country</th>'+
					'<th>State</th>'+
					'<th>City</th>'+
					'<th>Pin Code</th>'+
					'<th>Address</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var clientId = data[i].clientId;
						var clientName = data[i].clientName;
						var localCurrency = data[i].localCurrency;
						var baseCurrency = data[i].baseCurrency;
						var tinNumber = data[i].tinNumber;
						var cstNumber = data[i].cstNumber;
						var countryId = data[i].countryId;
						var countryName = data[i].countryName;
						var stateId = data[i].stateId;
						var stateName = data[i].stateName;
						var cityId = data[i].cityId;
						var cityName = data[i].cityName;
						var pinCode = data[i].pinCode;
						var address = data[i].address;
						
						$('#content_view_table').append("<tr>"+
								"<td><input type=\"checkbox\" value=\""+clientId+"\"></td>"+
								"<td><input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showUpdateDiv('"+clientId+"','"+clientName+"'," +
								"'"+localCurrency+"','"+baseCurrency+"','"+tinNumber+"'," +
								"'"+cstNumber+"','"+countryId+"','"+stateId+"'," +
								"'"+cityId+"','"+pinCode+"','"+address+"')\"></td>"+
								"<td>"+ clientName + "</td>"+
								"<td>"+ localCurrency + "</td>"+
								"<td>"+ baseCurrency + "</td>"+
								"<td>"+ tinNumber + "</td>"+
								"<td>"+ cstNumber + "</td>"+
								"<td>"+ countryName + "</td>"+
								"<td>"+ stateName + "</td>"+
								"<td>"+ cityName + "</td>"+
								"<td>"+ pinCode + "</td>"+
								"<td>"+ address + "</td>"+
								
							    "</tr>");
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Client
var setClient = function(countryId,stateId,cityId,clientName,
		 localCurrency,baseCurrency,tinNumber,
		 cstNumber,pinCode,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageClient',
		data : {
			userAction : 'set_client',
			countryId:countryId,
			stateId:stateId,
			cityId:cityId,
			clientName:clientName,
			localCurrency:localCurrency,
			baseCurrency:baseCurrency,
			tinNumber:tinNumber,
			cstNumber:cstNumber,
			pinCode:pinCode,
			address:address
			

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Client is Added Succesfully');
				getClient();
				$('#add_operation_country_select').prop('selectedIndex', 0);
				$('#add_operation_state_select').html('<option>select</option>');
				$('#add_operation_city_select').html('<option>select</option>');
				$('#add_operation_client_name_input').val('');
				$('#add_operation_local_currency_input').val('');
				$('#add_operation_base_currency_input').val('');
				$('#add_operation_tin_number_input').val('');
				$('#add_operation_cst_number_input').val('');
				$('#add_operation_pin_code_input').val('');
				$('#add_operation_address_input').val('');
			} else if(status == -3){
				alert("Client Exists with the name :" + clientName);
				document.getElementById("add_operation_client_name_input").focus();
				
			}else {
				alert('Client Addition Failed');
				document.getElementById("add_operation_client_name_input").focus();
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Client
var deleteClient = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var clientId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				clientId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}
 
	$.ajax( {
		type : 'POST',
		url : '../ManageClient',
		data : {
			userAction : "delete_client",
			clientId : clientId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Client is Deleted Succesfully');
				getClient();
			} else {
				alert('Client Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update Client
var updateClient = function(countryId,stateId,cityId,clientId,
		clientName,localCurrency,baseCurrency,tinNumber,
		 cstNumber,pinCode,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageClient',
		data : {
			userAction : 'update_client',
			countryId:countryId,
			stateId:stateId,
			cityId:cityId,
			clientId:clientId,
			clientName:clientName,
			localCurrency:localCurrency,
			baseCurrency:baseCurrency,
			tinNumber:tinNumber,
			cstNumber:cstNumber,
			pinCode:pinCode,
			address:address
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Client is Updated Succesfully');
				getClient();
				showAddDiv();
			} else {
				alert('Client Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Get Country
var getCountry = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_country"
                  
				},
				success : function(data) {
					
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_country_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var countryId = data[i].countryId;
						var countryName = data[i].countryName;
						$('#add_operation_country_select').
					    append("<option value=\""+countryId+"\">"+countryName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_country_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var countryId = data[i].countryId;
							var countryName = data[i].countryName;
						    $('#update_operation_country_select').
						    append("<option value=\""+countryId+"\">"+countryName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};

//Get State
var getState = function(operation) {
	var countryId;
	if(operation=="add"){
     countryId=$('#add_operation_country_select').val();
 	 $('#add_operation_state_select').html('<option>select</option>');
	 $('#add_operation_city_select').html('<option>select</option>');
     if(document.getElementById("add_operation_country_select").selectedIndex == 0){
   
    	 return;
     }
	}
	
	if(operation=="update"){
	     countryId=$('#update_operation_country_select').val();
		 $('#update_operation_state_select').html('<option>select</option>');
    	 $('#update_operation_city_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_country_select").selectedIndex == 0){
	    
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_state",
					countryId:countryId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_state_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var stateId = data[i].stateId;
						var stateName = data[i].stateName;
					    $('#add_operation_state_select').
					    append("<option value=\""+stateId+"\">"+stateName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_state_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var stateId = data[i].stateId;
							var stateName = data[i].stateName;
						    $('#update_operation_state_select').
						    append("<option value=\""+stateId+"\">"+stateName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			});
};

//Get State
var getCity = function(operation) {
	var stateId;
	if(operation=="add"){
      stateId=$('#add_operation_state_select').val();
      $('#add_operation_city_select').html('<option>select</option>');
     if(document.getElementById("add_operation_state_select").selectedIndex == 0){
    	
    	 return;
     }
    
	}
	
	if(operation=="update"){
	     stateId=$('#update_operation_state_select').val();
	     $('#update_operation_city_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_state_select").selectedIndex == 0){
	    	
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_city",
					stateId:stateId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_city_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var cityId = data[i].cityId;
						var cityName = data[i].cityName;
					    $('#add_operation_city_select').
					    append("<option value=\""+cityId+"\">"+cityName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_city_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var cityId = data[i].cityId;
							var cityName = data[i].cityName;
						    $('#update_operation_city_select').
						    append("<option value=\""+cityId+"\">"+cityName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			});
};

   