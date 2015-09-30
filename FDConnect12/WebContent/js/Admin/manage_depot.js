function showUpdateDiv(depotId,depotName,contactName,contactPhone,notes,
		landMarkDetails,address,countryId,regionId,stateId,
		cityId,townId,areaId){
	
	document.getElementById("content_add_div").style.display="none";
	document.getElementById("content_update_div").style.display="block";
	
	// Country
    getCountry('update');
	var typeDdl = document.getElementById('update_operation_country_select');
	for (i = 0; i < typeDdl.options.length; i++) {
          //alert(countryId+"  "+typeDdl.options[i].value );
		if (countryId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
	// Region
    getRegion('update');
	var typeDdl = document.getElementById('update_operation_region_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (regionId == (typeDdl.options[i].value)) {
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
	
	// Town
    getTown('update');
	var typeDdl = document.getElementById('update_operation_town_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (townId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
	// Area
    getArea('update');
	var typeDdl = document.getElementById('update_operation_area_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (areaId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
     document.getElementById("update_operation_depot_id_input").value=depotId;
     document.getElementById("update_operation_depot_name_input").value=depotName;
     document.getElementById("update_operation_contact_name_input").value=contactName;
     document.getElementById("update_operation_contact_phone_input").value=contactPhone;
     document.getElementById("update_operation_land_mark_details_input").value=landMarkDetails;
     document.getElementById("update_operation_notes_input").value=notes;
     document.getElementById("update_operation_address_input").value=address;
     
     
     
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}

var validateAddOperation=function(){

	

    var clientId = $('#filter_operation_client_select').val();
	if (document.getElementById("filter_operation_client_select").selectedIndex == 0) {
		alert("Please Select Client");
        return;
	}
	
    var countryId = $('#filter_operation_country_select').val();
	if (document.getElementById("filter_operation_country_select").selectedIndex == 0) {
		alert("Please Select Country");
        return;
	}
	
    var regionId = $('#filter_operation_region_select').val();
	if (document.getElementById("filter_operation_region_select").selectedIndex == 0) {
		alert("Please Select Region");
        return;
	}
	
    var stateId = $('#filter_operation_state_select').val();
	if (document.getElementById("filter_operation_state_select").selectedIndex == 0) {
		alert("Please Select State");
        return;
	}
	
    var cityId = $('#filter_operation_city_select').val();
	if (document.getElementById("filter_operation_city_select").selectedIndex == 0) {
		alert("Please Select City");
        return;
	}
	
    var townId = $('#filter_operation_town_select').val();
	if (document.getElementById("filter_operation_town_select").selectedIndex == 0) {
		alert("Please Select Town ");
        return;
	}
	
    var areaId = $('#filter_operation_area_select').val();
	if (document.getElementById("filter_operation_area_select").selectedIndex == 0) {
		alert("Please Select Area ");
        return;
	}
	
	var depotName = $('#add_operation_depot_name_input');
	if (depotName.val() == "") {
		alert("Please Enter Depot Name");
		depotName.focus();
		return;
	}
	
	var contactName = $('#add_operation_contact_name_input');
	if (contactName.val() == "") {
		alert("Please Enter Contact Name");
		contactName.focus();
		return;
	}
	
	var contactPhone = $('#add_operation_contact_phone_input');
	if (contactPhone.val() == "") {
		alert("Please Enter Contact Phone");
		contactPhone.focus();
		return;
	}
	
	var landMarkDetails = $('#add_operation_land_mark_details_input');
	if (landMarkDetails.val() == "") {
		alert("Please Enter Land Mark Detail");
		landMarkDetails.focus();
		return;
	}
	
	var notes = $('#add_operation_notes_input');
	if (notes.val() == "") {
		alert("Please Enter Notes");
		notes.focus();
		return;
	}

	var address = $('#add_operation_address_input');
	if (address.val() == "") {
		alert("Please Enter Address");
		address.focus();
		return;
	}
	setDepot(countryId,regionId,stateId,cityId,townId,areaId,depotName.val(),
	 contactName.val(),contactPhone.val(),landMarkDetails.val(),
	 notes.val(),address.val(),clientId);
};

var validateUpdateOperation=function(){
	
	

    var clientId = $('#filter_operation_client_select').val();
	if (document.getElementById("filter_operation_client_select").selectedIndex == 0) {
		alert("Please Select Client");
        return;
	}
	
	
	var depotId = $('#update_operation_depot_id_input').val();
	var countryId = $('#update_operation_country_select').val();
	if (document.getElementById("update_operation_country_select").selectedIndex == 0) {
		alert("Please Select Country");
        return;

	}
	
    var regionId = $('#update_operation_region_select').val();
	if (document.getElementById("update_operation_region_select").selectedIndex == 0) {
		alert("Please Select Region");
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
	
    var townId = $('#update_operation_town_select').val();
	if (document.getElementById("update_operation_town_select").selectedIndex == 0) {
		alert("Please Select Town ");
        return;

	}
	
    var areaId = $('#update_operation_area_select').val();
	if (document.getElementById("update_operation_area_select").selectedIndex == 0) {
		alert("Please Select Area ");
        return;

	}
	
	var depotName = $('#update_operation_depot_name_input');
	if (depotName.val() == "") {
		alert("Please Enter Depot Name");
		depotName.focus();
		return;

	}
	
	var contactName = $('#update_operation_contact_name_input');
	if (contactName.val() == "") {
		alert("Please Enter Contact Name");
		contactName.focus();
		return;

	}
	
	var contactPhone = $('#update_operation_contact_phone_input');
	if (contactPhone.val() == "") {
		alert("Please Enter Contact Phone");
		contactPhone.focus();
		return;

	}
	
	var landMarkDetails = $('#update_operation_land_mark_details_input');
	if (landMarkDetails.val() == "") {
		alert("Please Enter Land Mark Detail");
		landMarkDetails.focus();
		return;

	}
	
	var notes = $('#update_operation_notes_input');
	if (notes.val() == "") {
		alert("Please Enter Notes");
		notes.focus();
		return;

	}

	var address = $('#update_operation_address_input');
	if (address.val() == "") {
		alert("Please Enter Address");
		address.focus();
		return;

	}
	
	
updateDepot(countryId,regionId,stateId,cityId,townId,areaId,depotId,depotName.val(),
	 contactName.val(),contactPhone.val(),landMarkDetails.val(),
	 notes.val(),address.val(),clientId);
};

//Get Depot
var getDepot = function() {
	var clientId;
   clientId=$('#filter_operation_client_select').val();
  // alert(clientId);
     if(document.getElementById("filter_operation_client_select").selectedIndex == 0){
     $('#content_operation_div').css('display','none');
     return;
     }

	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_depot",
                    clientId:clientId
				},
				success : function(data) {
			   $('#content_operation_div').css('display','block');
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Depot Name</th>'+
					'<th>Contact Name</th>'+
					'<th>Contact Phone</th>'+
					'<th>Notes</th>'+
					'<th>Land Mark Details</th>'+
					'<th>Address</th>'+
					'<th>Area Name</th>'+
					'<th>Town Name</th>'+
					'<th>City Name</th>'+
					'<th>State Name</th>'+
					'<th>Region Name</th>'+
					'<th>Country Name</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var depotId = data[i].depotId;
						var depotName = data[i].depotName;
						var contactName = data[i].contactName;
						var contactPhone = data[i].contactPhone;
						var notes = data[i].notes;
						var landMarkDetails = data[i].landMarkDetails;
						var address = data[i].address;
						var countryId = data[i].countryId;
						var countryName = data[i].countryName;
						var regionId = data[i].regionId;
						var regionName = data[i].regionName;
						var stateId = data[i].stateId;
						var stateName = data[i].stateName;
						var cityId = data[i].cityId;
						var cityName = data[i].cityName;
						var townId = data[i].townId;
						var townName = data[i].townName;
						var areaId = data[i].areaId;
						var areaName = data[i].areaName;
						
					
					
						
						$('#content_view_table').append("<tr>"+
								"<td><input type=\"checkbox\" value=\""+depotId+"\"></td>"+
								"<td><input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showUpdateDiv('"+depotId+"','"+depotName+"'," +
								"'"+contactName+"','"+contactPhone+"','"+notes+"'," +
								"'"+landMarkDetails+"','"+address+"','"+countryId+"'," +
								"'"+regionId+"','"+stateId+"'," +
								"'"+cityId+"','"+townId+"','"+areaId+"')\"></td>"+
								"<td>"+ depotName + "</td>"+
								"<td>"+ contactName + "</td>"+
								"<td>"+ contactPhone + "</td>"+
								"<td>"+ notes + "</td>"+
								"<td>"+ landMarkDetails + "</td>"+
								"<td>"+ address + "</td>"+
								"<td>"+ areaName + "</td>"+
								"<td>"+ townName + "</td>"+
								"<td>"+ cityName + "</td>"+
								"<td>"+ stateName + "</td>"+
								"<td>"+ regionName + "</td>"+
								"<td>"+ countryName + "</td>"+
								
								
							    "</tr>");
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Depot
var setDepot = function(countryId,regionId,stateId,cityId,townId,areaId,depotName,
		 contactName,contactPhone,landMarkDetails,
		 notes,address,clientId) {
	//alert('clientid ' + clientId);
	$.ajax( {
		type : 'POST',
		url : '../ManageDepot',
		data : {
			userAction : 'set_depot',
			countryId:countryId,
			regionId:regionId,
			stateId:stateId,
			cityId:cityId,
			townId:townId,
			areaId:areaId,
			depotName:depotName,
			contactName:contactName,
			contactPhone:contactPhone,
			landMarkDetails:landMarkDetails,
			notes:notes,
	     	address:address,
	     	clientId:clientId
			

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Depot is Added Succesfully');
				getDepot();
				$('#add_operation_country_select').prop('selectedIndex', 0);
				$('#add_operation_region_select').html('<option>select</option>');
				$('#add_operation_state_select').html('<option>select</option>');
				$('#add_operation_city_select').html('<option>select</option>');
				$('#add_operation_town_select').html('<option>select</option>');
				$('#add_operation_area_select').html('<option>select</option>');
				$('#add_operation_depot_name_input').val('');
				$('#add_operation_contact_name_input').val('');
				$('#add_operation_contact_phone_input').val('');
				$('#add_operation_land_mark_details_input').val('');
				$('#add_operation_notes_input').val('');
				$('#add_operation_address_input').val('');
			} else {
				alert('Depot Addition Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Depot
var deleteDepot = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var depotId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				depotId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}
 
	$.ajax( {
		type : 'POST',
		url : '../ManageDepot',
		data : {
			userAction : "delete_depot",
			depotId : depotId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Depot is Deleted Succesfully');
				getDepot();
			} else {
				alert('Depot Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update Depot
var updateDepot = function(countryId,regionId,stateId,cityId,townId,areaId,depotId,depotName,
		 contactName,contactPhone,landMarkDetails,
		 notes,address,clientId) {

	$.ajax( {
		type : 'POST',
		url : '../ManageDepot',
		data : {
			userAction : 'update_depot',
			countryId:countryId,
			regionId:regionId,
			stateId:stateId,
			cityId:cityId,
			townId:townId,
			areaId:areaId,
			depotId:depotId,
			depotName:depotName,
			contactName:contactName,
			contactPhone:contactPhone,
			landMarkDetails:landMarkDetails,
			notes:notes,
	     	address:address,
	     	clientId:clientId
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Depot is Updated Succesfully');
				getDepot();
				showAddDiv();
			} else {
				alert('Depot Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};




//Get CLIENT
var getClient = function(operation) {

	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
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







//Get Country
var getCountry = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_country"
                  
				},
				success : function(data) {
					
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_country_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var countryId = data[i].countryId;
						var countryName = data[i].countryName;
						$('#filter_operation_country_select').
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

//Get Region
var getRegion= function(operation) {
	var countryId;
	if(operation=="filter"){
     countryId=$('#filter_operation_country_select').val();
     //$('#content_operation_div').css('display','none');
	 $('#filter_operation_region_select').html('<option>select</option>');
	 $('#filter_operation_state_select').html('<option>select</option>');
	 $('#filter_operation_city_select').html('<option>select</option>');
	 $('#filter_operation_town_select').html('<option>select</option>');
	 $('#filter_operation_area_select').html('<option>select</option>');
     if(document.getElementById("filter_operation_country_select").selectedIndex == 0){
    	
    	 return;
     }
	}
	
	if(operation=="update"){
	     countryId=$('#update_operation_country_select').val();
	   	 $('#update_operation_region_select').html('<option>select</option>');
    	 $('#update_operation_state_select').html('<option>select</option>');
    	 $('#update_operation_city_select').html('<option>select</option>');
    	 $('#update_operation_town_select').html('<option>select</option>');
    	 $('#update_operation_area_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_country_select").selectedIndex == 0){
	    
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_region",
					countryId:countryId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_region_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var regionId = data[i].regionId;
						var regionName = data[i].regionName;
					    $('#filter_operation_region_select').
					    append("<option value=\""+regionId+"\">"+regionName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_region_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var regionId = data[i].regionId;
							var regionName = data[i].regionName;
						    $('#update_operation_region_select').
						    append("<option value=\""+regionId+"\">"+regionName+"</option>");
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
var getState= function(operation) {
	var regionId;
	if(operation=="filter"){
     regionId=$('#filter_operation_region_select').val();
    // $('#content_operation_div').css('display','none');
     $('#filter_operation_state_select').html('<option>select</option>');
	 $('#filter_operation_city_select').html('<option>select</option>');
	 $('#filter_operation_town_select').html('<option>select</option>');
	 $('#filter_operation_area_select').html('<option>select</option>');
     if(document.getElementById("filter_operation_region_select").selectedIndex == 0){
    	
    	 return;
     }
	}
	
	if(operation=="update"){
	     regionId=$('#update_operation_region_select').val();
	  	 $('#update_operation_state_select').html('<option>select</option>');
    	 $('#update_operation_city_select').html('<option>select</option>');
    	 $('#update_operation_town_select').html('<option>select</option>');
    	 $('#update_operation_area_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_region_select").selectedIndex == 0){
	     
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_state",
					regionId:regionId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_state_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var stateId = data[i].stateId;
						var stateName = data[i].stateName;
					    $('#filter_operation_state_select').
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

//Get City
var getCity= function(operation) {
	var stateId;
	if(operation=="filter"){
     stateId=$('#filter_operation_state_select').val();
    // $('#content_operation_div').css('display','none');
     $('#filter_operation_city_select').html('<option>select</option>');
	 $('#filter_operation_town_select').html('<option>select</option>');
	 $('#filter_operation_area_select').html('<option>select</option>');
     if(document.getElementById("filter_operation_state_select").selectedIndex == 0){
    	
    	 return;
     }
	}
	
	if(operation=="update"){
	     stateId=$('#update_operation_state_select').val();
	     $('#update_operation_city_select').html('<option>select</option>');
    	 $('#update_operation_town_select').html('<option>select</option>');
    	 $('#update_operation_area_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_state_select").selectedIndex == 0){
	      
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_city",
					stateId:stateId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_city_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var cityId = data[i].cityId;
						var cityName = data[i].cityName;
					    $('#filter_operation_city_select').
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


//Get Town
var getTown= function(operation) {
	var cityId;
	if(operation=="filter"){
     cityId=$('#filter_operation_city_select').val();
   //  $('#content_operation_div').css('display','none');
     $('#filter_operation_town_select').html('<option>select</option>');
	 $('#filter_operation_area_select').html('<option>select</option>');
     if(document.getElementById("filter_operation_city_select").selectedIndex == 0){
      
    	 return;
     }
	}
	
	if(operation=="update"){
	     cityId=$('#update_operation_city_select').val();
	     $('#update_operation_town_select').html('<option>select</option>');
	     $('#update_operation_area_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_city_select").selectedIndex == 0){
	  
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_town",
					cityId:cityId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_town_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var townId = data[i].townId;
						var townName = data[i].townName;
					    $('#filter_operation_town_select').
					    append("<option value=\""+townId+"\">"+townName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_town_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var townId = data[i].townId;
							var townName = data[i].townName;
						    $('#update_operation_town_select').
						    append("<option value=\""+townId+"\">"+townName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			});
};


//Get Area
var getArea= function(operation) {
	var townId;
	if(operation=="filter"){
     townId=$('#filter_operation_town_select').val();
    // $('#content_operation_div').css('display','none');
     $('#filter_operation_area_select').html('<option>select</option>');
     if(document.getElementById("filter_operation_town_select").selectedIndex == 0){
       
    	 return;
     }
	}
	
	if(operation=="update"){
	     townId=$('#update_operation_town_select').val();
	     $('#update_operation_area_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_town_select").selectedIndex == 0){
	  
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageDepot",
				data : {
					userAction : "get_area",
					townId:townId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_area_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
					
						var areaId = data[i].areaId;
						var areaName = data[i].areaName;
					    $('#filter_operation_area_select').
					    append("<option value=\""+areaId+"\">"+areaName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_area_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var areaId = data[i].areaId;
							var areaName = data[i].areaName;
						    $('#update_operation_area_select').
						    append("<option value=\""+areaId+"\">"+areaName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			});
};
