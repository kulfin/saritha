function showUpdateDiv(retailClientId,chainId,chainName){
	
	// Trade
//    getTrade('update');
//	getRetailClient('update');
	/*var typeDdl = document.getElementById('update_operation_trade_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (tradeId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}*/
	
	// Chain Category
    getRetailClient('update');
	var typeDdl = document.getElementById('update_operation_retail_client_select');
	for (i = 0; i < typeDdl.options.length; i++) {

		if (retailClientId == (typeDdl.options[i].value)) {
			typeDdl.options[i].selected = true;
		}
	}
	
     document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_chain_id_input").value=chainId;
     document.getElementById("update_operation_chain_name_input").value=chainName;
   
     
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}
var validateAddOperation=function(){
//	alert('in Add');
    var tradeId = $('#filter_operation_trade_select').val();
		if (document.getElementById("filter_operation_trade_select").selectedIndex == 0) {
			alert("Please Select Trade");
	        return;
		}
	
    var retailClientId = $('#add_operation_retail_client_select').val();
	if (document.getElementById("add_operation_retail_client_select").selectedIndex == 0) {
		alert("Please Select Retail Client");
        return;
  
	}
	
	var chainName = $('#add_operation_chain_name_input');
	if (chainName.val().trim() == "") {
		alert("Please Enter Chain Name");
		chainName.focus();
		return;

	}
/*	var n =chainName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(chainName.val().charAt(i))){
            alert("Enter only Alphabets");
            chainName.focus();
            return false;             
        }
    }*/
//	alert('tradeId' + tradeId + 'and chainName' + chainName.val() + "retail client id" + retailClientId);
//	 setChain(tradeId,chainName.val());
	 setChain(tradeId,retailClientId,chainName.val());
};

var validateUpdateOperation=function(){
	
	var chainId = $('#update_operation_chain_id_input').val();

	  /* var tradeId = $('#update_operation_trade_select').val();
		if (document.getElementById("filter_operation_trade_select").selectedIndex == 0) {
			alert("Please Select Trade");
	        return;
		}*/
	var tradeId = $('#filter_operation_trade_select').val();
	if (document.getElementById("filter_operation_trade_select").selectedIndex == 0) {
		alert("Please Select Trade");
        return;

	}
	
   var retailClientId = $('#update_operation_retail_client_select').val();
	if (document.getElementById("update_operation_retail_client_select").selectedIndex == 0) {
		alert("Please Select Retail Client");
       return;
 
	}
	
	var chainName = $('#update_operation_chain_name_input');
	if (chainName.val() == "") {
		alert("Please Enter Chain Name");
		chainName.focus();
		return;
	}
	
	 updateChain(tradeId,retailClientId,chainId,chainName.val());
};

//Get Chain
var getChain = function() {
	tradeId= $('#filter_operation_trade_select').val();
	if(document.getElementById("filter_operation_trade_select").selectedIndex == 0){
		$('#content_operation_div').css('display','none');
		return;
	}
	$.ajax( {
				type : "POST",
				url : "../ManageChain",
				data : {
					userAction : "get_chain",
					tradeId:tradeId
                  
				},
				success : function(data) {
					//alert('response data is' + data);
				var len = data.length;
//				alert('data length' + len);
				if(len>0){
					$('#content_operation_div').css('display','block');
					//$('#content_view_div').css('display','block');
					}
					$('#content_view_table').html(
					'<tr align=center><th>Select</th><th>Edit</th>'+
					'<th>RetailClientName</th>'+
					'<th>Chain</th>'+'</tr>');
					for ( var i = 0; i < len; ++i) {
						var tradeId= data[i].tradeId;
						var chainId = data[i].chainId;
						var chainName = data[i].chainName;
						var retailClientId = data[i].retailClientId;
						var retailClientName = data[i].retailClientName;
						/*alert('chainId' + chainId);
						alert('chainName' + chainName);
						alert('retailClientId'+ retailClientId);
						alert('retailClientName' + retailClientName);*/
						
						$('#content_view_table').append("<tr align=center>"+
								"<td><input type=\"checkbox\" value=\""+chainId+"\"></td>"+
								"<td><input type=\"image\" src=\"../images/edit.png\" " +
								//" onclick=\"showUpdateDiv('"+tradeId+"',"+"'"+retailClientId+"','" +chainId+"','"+chainName+"'"+ ")\"></td>"+
								" onclick=\"showUpdateDiv('"+retailClientId+"',"+"'" +chainId+"','"+chainName+"'"+ ")\"></td>"+
								"<td>"+ retailClientName +"</td>"+
								"<td>"+ chainName + "</td>"+
							    "</tr>");
					}
					//alert('At end');
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Chain
var setChain = function(tradeId,retailClientId,chainName) 
{
//	alert('inside setChain' + tradeId +":" + chainName+"retail client id" + retailClientId);
	$.ajax( {
		type : 'POST',
		url : '../ManageChain',
		data : {
			userAction : 'set_chain',
			tradeId:tradeId,
			retailClientId:retailClientId,
			chainName:chainName
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
//				alert('Chain is Added Succesfully');
				getChain();
				$('#add_operation_retail_client_select').prop('selectedIndex', 0);
			    $('#add_operation_chain_name_input').val('');
				$('#add_operation_notes_input').val('');
				$('#add_operation_chain_name_input').focus();
				
			} else if(status == -3){
				alert("Chain Name exists with");
				$('#add_operation_chain_name_input').focus();
			}
			else {
				alert('Chain Addition Failed');
				$('#add_operation_chain_name_input').focus();
			}
		},
		error : function(error) {
			alert("Error"+error.status);
		}
	});
};

// Delete Chain
var deleteChain = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var chainId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				chainId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}
 
	$.ajax( {
		type : 'POST',
		url : '../ManageChain',
		data : {
			userAction : "delete_chain",
			chainId : chainId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Chain is Deleted Succesfully');
				getChain();
			} else {
				alert('Chain Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update Chain
var updateChain = function(tradeId,retailClientId,chainId,chainName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageChain',
		data : {
			userAction : 'update_chain',
			tradeId:tradeId,
			retailClientId:retailClientId,
			chainId:chainId,
			chainName:chainName
	       
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Chain is Updated Succesfully');
				getChain();
				showAddDiv();
			} else {
				alert('Chain Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Get Trade
var getTrade = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageChain",
				data : {
					userAction : "get_trade"
                  
				},
				success : function(data) {
					 
					
				var len = data.length;
				if(operation=="filter"){
				$('#filter_operation_trade_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var tradeId = data[i].tradeId;
						var tradeName = data[i].tradeName;
						$('#filter_operation_trade_select').
					    append("<option value=\""+tradeId+"\">"+tradeName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_trade_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var tradeId = data[i].tradeId;
							var tradeName = data[i].tradeName;
						    $('#update_operation_trade_select').
						    append("<option value=\""+tradeId+"\">"+tradeName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};


//Get Chain Category
var getRetailClient = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageChain",
				data : {
					userAction : "get_retail_client"
                  
				},
				success : function(data) {
					
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_retail_client_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var retailClientId = data[i].retailClientId;
						var retailClientName = data[i].retailClientName;
						$('#add_operation_retail_client_select').
					    append("<option value=\""+retailClientId+"\">"+retailClientName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_retail_client_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var retailClientId = data[i].retailClientId;
							var retailClientName = data[i].retailClientName;
							//alert('retailClientId is ' + retailClientId);
						    $('#update_operation_retail_client_select').
						    append("<option value=\""+retailClientId+"\">"+retailClientName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};
