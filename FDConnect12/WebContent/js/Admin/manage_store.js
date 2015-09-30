function showUpdateDiv(chainId,storeFlag,storeId,storeName,contactName,contactPhone,notes,
		landMarkDetails,address){
	
	document.getElementById("content_add_div").style.display="none";
	document.getElementById("content_update_div").style.display="block";
	
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
	
	// Chain
    getChain('update');
	var typeDdl = document.getElementById('update_operation_chain_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (chainId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
	// Store Flag
    getStoreFlag('update');
	var typeDdl = document.getElementById('update_operation_store_flag_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (storeFlag == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
     document.getElementById("update_operation_store_id_input").value=storeId;
     document.getElementById("update_operation_store_name_input").value=storeName;
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
function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}

var validateAddOperation=function(){


    var countryName = $('#filter_operation_country_select option:selected').text();
	if (document.getElementById("filter_operation_country_select").selectedIndex == 0) {
		alert("Please Select Country");
        return;

	}
	
    var regionName = $('#filter_operation_region_select option:selected').text();
	if (document.getElementById("filter_operation_region_select").selectedIndex == 0) {
		alert("Please Select Region");
        return;

	}
	
    var stateName = $('#filter_operation_state_select option:selected').text();
	if (document.getElementById("filter_operation_state_select").selectedIndex == 0) {
		alert("Please Select State");
        return;

	}
	
    var cityName = $('#filter_operation_city_select option:selected').text();
	if (document.getElementById("filter_operation_city_select").selectedIndex == 0) {
		alert("Please Select City");
        return;

	}
	
    var townName = $('#filter_operation_town_select option:selected').text();
	if (document.getElementById("filter_operation_town_select").selectedIndex == 0) {
		alert("Please Select Town ");
        return;

	}
	
    var areaName = $('#filter_operation_area_select option:selected').text();
	if (document.getElementById("filter_operation_area_select").selectedIndex == 0) {
		alert("Please Select Area ");
        return;

	}
	
    var chainName = $('#add_operation_chain_select option:selected').text();
	if (document.getElementById("add_operation_chain_select").selectedIndex == 0) {
		alert("Please Select Chain ");
        return;

	}
	
	var storeFlag = $('#add_operation_store_flag_select option:selected').text();
	if (document.getElementById("add_operation_store_flag_select").selectedIndex == 0) {
	 alert("Please Select Store Flag ");
	    return;
    }
	
	var storeName = $('#add_operation_store_name_input').val();
	if (storeName.trim() == "") {
		alert("Please Enter Store Name");
		storeName.focus();
		return;

	}
/*	var n =storeName.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(storeName.charAt(i))){
            alert("Enter only Alphabets for Store Name");
            storeName.focus();
            return false;             
        }
    }*/
	var contactName = $('#add_operation_contact_name_input').val();
	if (contactName.trim() == "") {
		alert("Please Enter Contact Name");
		contactName.focus();
		return;

	}
/*	var n = contactName.toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(contactName.charAt(i))){
            alert("Enter only Alphabets for Contact Name");
            contactName.focus();
            return false;             
        }
    }*/
	var contactPhone = $('#add_operation_contact_phone_input').val();
	if (contactPhone.trim() == "") {
		contactPhone = "";
	}
	/*if (contactPhone.val().trim() == "") {
		alert("Please Enter Contact Phone");
		contactPhone.focus();
		return;

	}*/
    
	/*if( $('#add_operation_contact_phone_input').val().length < 14 ){
		alert("Phone Number cannot be less than 10 digits");
		contactPhone.focus();
		return false;
	}*/
	var landMarkDetails = $('#add_operation_land_mark_details_input').val();
	if (landMarkDetails.trim() == "") {
		alert("Please Enter Land Mark Detail");
		landMarkDetails.focus();
		return;

	}
	
	var notes = $('#add_operation_notes_input').val();
	if (notes.trim() == "") {
		alert("Please Enter Notes");
		notes.focus();
		return;

	}

	var address = $('#add_operation_address_input').val();
	if (address.trim() == "") {
		alert("Please Enter Address");
		address.focus();
		return;

	}
	setStore(countryName,regionName,stateName,cityName,townName,areaName,chainName,storeFlag,storeName,
	 contactName,contactPhone,landMarkDetails,
	 notes,address);
};

var validateUpdateOperation=function(){
	
	var storeId= $('#update_operation_store_id_input').val();
	
    var countryName = $('#update_operation_country_select option:selected').text();
	if (document.getElementById("update_operation_country_select").selectedIndex == 0) {
		alert("Please Select Country");
        return;

	}
	
    var regionName = $('#update_operation_region_select option:selected').text();
	if (document.getElementById("update_operation_region_select").selectedIndex == 0) {
		alert("Please Select Region");
        return;

	}
	
    var stateName = $('#update_operation_state_select option:selected').text();
	if (document.getElementById("update_operation_state_select").selectedIndex == 0) {
		alert("Please Select State");
        return;

	}
	
    var cityName = $('#update_operation_city_select option:selected').text();
	if (document.getElementById("update_operation_city_select").selectedIndex == 0) {
		alert("Please Select City");
        return;

	}
	
    var townName = $('#update_operation_town_select option:selected').text();
	if (document.getElementById("update_operation_town_select").selectedIndex == 0) {
		alert("Please Select Town ");
        return;

	}
	
    var areaName = $('#update_operation_area_select option:selected').text();
	if (document.getElementById("update_operation_area_select").selectedIndex == 0) {
		alert("Please Select Area ");
        return;

	}
	
    var chainName = $('#update_operation_chain_select option:selected').text();
	if (document.getElementById("update_operation_chain_select").selectedIndex == 0) {
		alert("Please Select Chain ");
        return;

	}
	
	var storeFlag = $('#update_operation_store_flag_select option:selected').text();
	if (document.getElementById("update_operation_store_flag_select").selectedIndex == 0) {
	 alert("Please Select Store Flag ");
	    return;
    }
	
	var storeName = $('#update_operation_store_name_input');
	if (storeName.val() == "") {
		alert("Please Enter Store Name");
		storeName.focus();
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
	updateStore(countryName,regionName,stateName,cityName,townName,areaName,chainName,storeFlag,storeId,storeName.val(),
	 contactName.val(),contactPhone.val(),landMarkDetails.val(),
	 notes.val(),address.val());
};

//Get Store
var getStore = function() {
	
	var areaName;
   areaName=$('#filter_operation_area_select option:selected').text();
   
     if(document.getElementById("filter_operation_area_select").selectedIndex == 0){
     $('#content_operation_div').css('display','none');
     return;
     }
  

	$.ajax( {
				type : "POST",
				url : "../ManageStore",
				data : {
					userAction : "get_store",
                    areaName:areaName
				},
				success : function(data) {
			   $('#content_operation_div').css('display','block');
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Chain Name</th>'+
					'<th>Store Flag</th>'+
					'<th>Store Name</th>'+
					'<th>Contact Name</th>'+
					'<th>Contact Phone</th>'+
					'<th>Notes</th>'+
					'<th>Land Mark Details</th>'+
					'<th>Address</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var storeId = data[i].storeId;
						var storeName = data[i].storeName;
						var contactName = data[i].contactName;
						var contactPhone = data[i].contactPhone;
						var notes = data[i].notes;
						var landMarkDetails = data[i].landMarkDetails;
						var address = data[i].address;
						var chainName = data[i].chainName;
						var storeFlag = data[i].storeFlag;
						var countryName = data[i].countryName;
						var regionName = data[i].regionName;
					    var stateName = data[i].stateName;
					    var cityName = data[i].cityName;
					    var townName = data[i].townName;
						var areaName = data[i].areaName;
						
					
					
						
						$('#content_view_table').append("<tr>"+
								"<td><input type=\"checkbox\" value=\""+storeId+"\"></td>"+
								"<td><input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showUpdateDiv('"+chainName+"','"+storeFlag+"'," +
								"'"+storeId+"','"+storeName+"'," +
								"'"+contactName+"','"+contactPhone+"','"+notes+"'," +
								"'"+landMarkDetails+"','"+address+"')\"></td>"+
								"<td>"+ chainName + "</td>"+
								"<td>"+ storeFlag + "</td>"+
								"<td>"+ storeName + "</td>"+
								"<td>"+ contactName + "</td>"+
								"<td>"+ contactPhone + "</td>"+
								"<td>"+ notes + "</td>"+
								"<td>"+ landMarkDetails + "</td>"+
								"<td>"+ address + "</td>"+
								
							    "</tr>");
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Store
var setStore = function(countryName,regionName,stateName,
		cityName,townName,areaName,chainName,storeFlag,storeName,
		 contactName,contactPhone,landMarkDetails,
		 notes,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageStore',
		data : {
			userAction : 'set_store',
			countryName:countryName,
			regionName:regionName,
			stateName:stateName,
			cityName:cityName,
			townName:townName,
			areaName:areaName,
			storeName:storeName,
			chainName:chainName,
			storeFlag:storeFlag,
			contactName:contactName,
			contactPhone:contactPhone,
			landMarkDetails:landMarkDetails,
			notes:notes,
	     	address:address
			

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Store is Added Succesfully');
				getStore();
		        $('#add_operation_chain_select').val('select');
		        $('#add_operation_chain_select').val('select');
				$('#add_operation_store_flag_select').val('select');
				$('#add_operation_store_name_input').val('');
				$('#add_operation_contact_name_input').val('');
				$('#add_operation_contact_phone_input').val('');
				$('#add_operation_land_mark_details_input').val('');
				$('#add_operation_notes_input').val('');
				$('#add_operation_address_input').val('');
			}else if(status == -3){
				alert('Store Name Exists');
				$('#add_operation_store_name_input').focus();
			}else {
				alert('Store Addition Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Store
var deleteStore = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var storeId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				storeId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageStore',
		data : {
			userAction : "delete_store",
			storeId : storeId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Store is Deleted Succesfully');
				getStore();
			} else {
				alert('Store Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update Store
var updateStore = function(countryName,regionName,stateName,cityName,townName,areaName,chainName,storeFlag,storeId,storeName,
		 contactName,contactPhone,landMarkDetails,
		 notes,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageStore',
		data : {
			userAction : 'update_store',
			countryName:countryName,
			regionName:regionName,
			stateName:stateName,
			cityName:cityName,
			townName:townName,
			areaName:areaName,
			chainName:chainName,
			storeFlag:storeFlag,
			storeId:storeId,
			storeName:storeName,
			contactName:contactName,
			contactPhone:contactPhone,
			landMarkDetails:landMarkDetails,
			notes:notes,
	     	address:address
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Store is Updated Succesfully');
				getStore();
				showAddDiv();
			} else {
				alert('Store Updation Failed');

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
				url : "../ManageStore",
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
							var countryId= data[i].countryId;
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
     $('#content_operation_div').css('display','none');
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
				url : "../ManageStore",
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
     $('#content_operation_div').css('display','none');
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
				url : "../ManageStore",
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
     $('#content_operation_div').css('display','none');
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
				url : "../ManageStore",
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
     $('#content_operation_div').css('display','none');
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
				url : "../ManageStore",
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
     $('#content_operation_div').css('display','none');
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
				url : "../ManageStore",
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

//Get Chain
var getChain= function(operation) {

	$.ajax( {
			type : "POST",
			url : "../ManageStore",
			data : {
				userAction : "get_chain"
			},
			success : function(data) {
			var len = data.length;
			if(operation=="add"){
			$('#add_operation_chain_select').html('<option>select</option>');
				for ( var i = 0; i < len; ++i) {
				
					var chainId = data[i].chainId;
					var chainName = data[i].chainName;
				    $('#add_operation_chain_select').
				    append("<option value=\""+chainName+"\">"+chainName+"</option>");
				}
			 }
			
			else if(operation=="update"){
				$('#update_operation_chain_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var chainId = data[i].chainId;
						var chainName = data[i].chainName;
					    $('#update_operation_chain_select').
					    append("<option value=\""+chainName+"\">"+chainName+"</option>");
					}
				 }
			},
			error : function(error) {
				alert("Error");
			},
			async: false
		});
};

//Get Chain
var getStoreFlag= function(operation) {

	            var data=new Array();
	            data= ["OPERATING","CLOSED","MOVED_OUT","ADDRESS_CHANGED"];
	            var len=data.length;
				if(operation=="add"){
				$('#add_operation_store_flag_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
					
					    var storeFlag  = data[i];
					    $('#add_operation_store_flag_select').
					    append("<option value=\""+storeFlag +"\">"+storeFlag +"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_store_flag_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
					      var storeFlag  = data[i];
						    $('#update_operation_store_flag_select').
						    append("<option value=\""+storeFlag +"\">"+storeFlag +"</option>");
						}
					 }
				
};