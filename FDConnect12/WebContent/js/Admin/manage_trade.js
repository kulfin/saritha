function showUpdateDiv(tradeId,tradeName){
	 document.getElementById("content_add_div").style.display="none";
	 document.getElementById("content_update_div").style.display="block";
     document.getElementById("update_operation_trade_id_input").value=tradeId;
     document.getElementById("update_operation_trade_name_input").value=tradeName;	
}

function showAddDiv(){
	document.getElementById("content_update_div").style.display="none";
	document.getElementById("content_add_div").style.display="block";
}
function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}
var validateAddOperation=function(){
	var tradeName = $('#add_operation_trade_name_input');
	if (tradeName.val().trim() == "") {
		alert("Please Enter Trade Name");
		tradeName.focus();
		return;

	}
/*	var n =tradeName.val().toString().length;

    for(var i = 0; i < n; i++){
        if (! isChar(tradeName.val().charAt(i))){
            alert("Enter only Alphabets");
            tradeName.focus();
            return false;             
        }
    }*/
	setTrade(tradeName.val());
};

var validateUpdateOperation=function(){
	var tradeId = $('#update_operation_trade_id_input');
	var tradeName = $('#update_operation_trade_name_input');
	if (tradeName.val() == "") {
		alert("Please Enter Trade Name");
		tradeName.focus();
		return;

	}
	updateTrade(tradeId.val(),tradeName.val());
};

//Get Trade
var getTrade = function() {
 
	$.ajax( {
				type : "POST",
				url : "../ManageTrade",
				data : {
					userAction : "get_trade"
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th>Select</th><th>Edit</th>'+
					'<th>Trade Name</th></tr>');
					for ( var i = 0; i < len; ++i) {
						var tradeId = data[i].tradeId;
						var tradeName = data[i].tradeName;
						$('#content_view_table').append('<tr>'+
								'<td><input type=\'checkbox\' value=\''+tradeId+'\'></td>'+
								"<td><input type=\"image\" src=\"../images/edit.png\" onclick=\"showUpdateDiv('"+tradeId+"','"+tradeName+"')\"></td>"+
								'<td>'+ tradeName + '</td>'+
								'</tr>');
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};

// Set Trade
var setTrade = function(tradeName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageTrade',
		data : {
			userAction : 'set_trade',
			tradeName : tradeName

		},
		success : function(data) {
			var status = data.status;
			if(status == -3){
				alert("Trade Exists with the name :  " + tradeName);
				$('#add_operation_trade_name_input').focus();
			}
			if (status == 0) {
//				alert('Trade is Added Succesfully');
				getTrade();
				$('#add_operation_trade_name_input').val('');
				$('#add_operation_trade_name_input').focus();
			} 
			if(status == -1){
				alert('Trade Addition Failed');
				$('#add_operation_trade_name_input').val('');
			}
			
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Delete Trade
var deleteTrade = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var tradeId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				tradeId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}

	$.ajax( {
		type : 'POST',
		url : '../ManageTrade',
		data : {
			userAction : "delete_trade",
			tradeId : tradeId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Trade is Deleted Succesfully');
				getTrade();
			} else {
				alert('Trade Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error"+error.responseText);
		}
	});
};

// Update Trade
var updateTrade = function(tradeId, tradeName) {

	$.ajax( {
		type : 'POST',
		url : '../ManageTrade',
		data : {
			userAction : 'update_trade',
			tradeId : tradeId,
			tradeName : tradeName
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Trade is Updated Succesfully');
				getTrade();
				showAddDiv();
			} else {
				alert('Trade Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};


   