function showUpdateDiv(costTypeId,costItemId,costItemName){
	
	// CostType
    getCostType('update');
	var typeDdl = document.getElementById('update_operation_cost_type_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (costTypeId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
     document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_cost_item_id_input").value=costItemId;
     document.getElementById("update_operation_cost_item_name_input").value=costItemName;
   
     
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return((c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ' );
}

var validateAddOperation=function(){
    var costTypeId = $('#filter_operation_cost_type_select').val();
		if (document.getElementById("filter_operation_cost_type_select").selectedIndex == 0) {
			alert("Please Select Cost Type");
	        return;
		}
	
	var costItemName = $('#add_operation_cost_item_name_input');
	if (costItemName.val().trim() == "") {
		alert("Please Enter Cost Item Name");
		costItemName.focus();
		return;
	}
	
/*	var n = costItemName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(costItemName.val().charAt(i))){
            alert("Enter only Alphabets");
            costItemName.focus();
	    return false;
	   }
    }*/
	
	 setCostItem(costTypeId,costItemName.val());
};

var validateUpdateOperation=function(){
	
	var costItemId = $('#update_operation_cost_item_id_input').val();

	   var costTypeId = $('#update_operation_cost_type_select').val();
		if (document.getElementById("update_operation_cost_type_select").selectedIndex == 0) {
			alert("Please Select Cost Type");
	        return;

		}
	
   	
	var costItemName = $('#update_operation_cost_item_name_input');
	if (costItemName.val() == "") {
		alert("Please Enter Cost Item Name");
		costItemName.focus();
		return;

	}
	

	
	 updateCostItem(costTypeId,costItemId,costItemName.val());
};

//Get CostItem
var getCostItem = function() {
	costTypeId= $('#filter_operation_cost_type_select').val();
	if(document.getElementById("filter_operation_cost_type_select").selectedIndex == 0){
		$('#content_operation_div').css('display','none');
		return;
	}
	$.ajax( {
				type : "POST",
				url : "../ManageCostItem",
				data : {
					userAction : "get_cost_item",
					costTypeId:costTypeId
                  
				},
				success : function(data) {
				var len = data.length;
				if(len>0){
				$('#content_operation_div').css('display','block');
				}
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
				    '<th>CostItem</th>'+
					
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var costTypeId= data[i].costTypeId;
						var costItemId = data[i].costItemId;
						var costItemName = data[i].costItemName;
						
					
						
						$('#content_view_table').append("<tr>"+
								"<td><input type=\"checkbox\" value=\""+costItemId+"\"></td>"+
								"<td><input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showUpdateDiv('"+costTypeId+"',"+
								"'"+costItemId+"','"+costItemName+"'"+
								")\"></td>"+
							    "<td>"+ costItemName + "</td>"+
							    "</tr>");
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set CostItem
var setCostItem = function(costTypeId,costItemName) {
 
	$.ajax( {
		type : 'POST',
		url : '../ManageCostItem',
		data : {
			userAction : 'set_cost_item',
			costTypeId:costTypeId,
	        costItemName:costItemName
	    
			

		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Cost Item is Added Succesfully');
				getCostItem();
				$('#add_operation_retail_client_select').prop('selectedIndex', 0);
			    $('#add_operation_cost_item_name_input').val('');
				$('#add_operation_notes_input').val('');
				$('#add_operation_cost_item_name_input').focus();
			}
			else if(status == -3){
				alert("Cost Item Already Exists");
				$('#add_operation_cost_item_name_input').focus();
				return false;
			}
			else {
				alert('Cost Item Addition Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete CostItem
var deleteCostItem = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var costItemId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				costItemId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}
 
	$.ajax( {
		type : 'POST',
		url : '../ManageCostItem',
		data : {
			userAction : "delete_cost_item",
			costItemId : costItemId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Cost Item is Deleted Succesfully');
				getCostItem();
			} else {
				alert('Cost Item Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update CostItem
var updateCostItem = function(costTypeId,costItemId,costItemName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageCostItem',
		data : {
			userAction : 'update_cost_item',
			costTypeId:costTypeId,
			costItemId:costItemId,
	        costItemName:costItemName
	       
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Cost Item is Updated Succesfully');
				getCostItem();
				showAddDiv();
			} else {
				alert('Cost Item Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Get CostType
var getCostType = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageCostItem",
				data : {
					userAction : "get_cost_type"
                  
				},
				success : function(data) {
					 
					
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_cost_type_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var costTypeId = data[i].costTypeId;
						var costTypeName = data[i].costTypeName;
						$('#filter_operation_cost_type_select').
					    append("<option value=\""+costTypeId+"\">"+costTypeName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_cost_type_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var costTypeId = data[i].costTypeId;
							var costTypeName = data[i].costTypeName;
						    $('#update_operation_cost_type_select').
						    append("<option value=\""+costTypeId+"\">"+costTypeName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};


