

var oTable;
var nRow;
var nEditing;
var aData;
var nNodes;

var project_element_mapping_u;
var project_elementId_u;
var storeId_u;
// Get Client
var getClient = function(divisionName) {
	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'get_client',
			divisionName : divisionName
		},
		success : function(data) {

			var len = data.length;
			for ( var i = 0; i < len; ++i) {
				var clientId = data[i].clientId;
				var clientName = data[i].clientName;

				$('#filter_operation_client_select').append(
						"<option value=\"" + clientId + "\">" + clientName
								+ "</option>");
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Get All Project for the client
var getProject = function(divisionName) {

	$('#grid_div').html('');
	gridRowCount = 0;
	tableRowCount = 0;

	var clientId = $('#filter_operation_client_select').val();

	$('#division_name_input').val('');
	$('#filter_operation_Project_select').html(
			'<option value="select">select</option>');
	if (document.getElementById("filter_operation_client_select").selectedIndex == 0) {

		return;
	}

	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'get_Project',
			clientId : clientId,
			divisionName : divisionName
		},
		success : function(data) {

			var len = data.length;
			for ( var i = 0; i < len; ++i) {
				var ProjectId = data[i].ProjectId;
				var ProjectName = data[i].ProjectName;

				$('#filter_operation_Project_select').append(
						"<option value=\"" + ProjectId + "\">" + ProjectName
								+ "</option>");
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Get the project Division
var getDivision = function() {
	ProjectStoreFlag = false;
	ProjectStoreStatusFlag = false;
	ProjectElementFlag = false;
	elementStatusFlag = false;

	$('#grid_div').html('');
	gridRowCount = 0;
	tableRowCount = 0;

	var ProjectId = $('#filter_operation_Project_select').val();
	$('#ProjectId').val(ProjectId);
	if (document.getElementById("filter_operation_Project_select").selectedIndex == 0) {
		$('#division_name_input').val('');
		return;
	}

	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'get_division',
			ProjectId : ProjectId
		},
		success : function(data) {
			var len = data.length;
			for ( var i = 0; i < len; ++i) {
				var divisionId = data[i].divisionId;
				var divisionName = data[i].divisionName;

				$('#division_name_input').val(divisionName);
			}
			getMappedProjectStore();

		},
		async : false,
		error : function(error) {
			alert("Error");
		}
	});

};

// Get Mapped Project Stores for the project with elements
var getMappedProjectStore = function() {
	$('#jcMnd tbody').remove();
	var ProjectId = $('#filter_operation_Project_select').val();

	if (document.getElementById("filter_operation_Project_select").selectedIndex == 0) {
		return;
	}

	// alert('calling '+ProjectId);
	$
			.ajax( {
				type : 'POST',
				url : '../MndJobCardAssignment',
				data : {
					userAction : 'get_mapped_Project_store',
					ProjectId : ProjectId
				},
				success : function(data) {

					$('#jcMnd').append("<tbody>");
					var len = data.length;
					for ( var i = 0; i < len; ++i) {

						var storeId = data[i].storeId;
						var storeName = data[i].storeName;
						var storeCode = data[i].storeCode;
						var stateName = data[i].stateName;
						var cityName = data[i].cityName;
						var address = data[i].address;
						var storeStatusId = data[i].storeStatusId;
						var storeStatusName = data[i].storeStatusName;
						var elementName = data[i].elementName;
						var fdhubName = data[i].fdhubName;
						var brandName = data[i].brandName;
						var elementStatusName = data[i].elementStatusName;
						var quantity = data[i].quantity;

						var elementId = data[i].elementId;
						var project_element_mappind_id = data[i].project_element_mapping_Id;
						// var element = data[i].element;
						var jcNumber = data[i].jcNumber;
						var jcDate = data[i].jcDate;
						var comment = data[i].comments;
						if (comment == null | comment == "") {
							comment = "NA";
						}
						// alert('comment is' + comment);
						$('#jcMnd tbody')
								.append(
										"<tr class='gradeA'><td><input name=chkbox id=chkbox"
												+ i
												+ " class=checkBox value="
												+ storeId
												+ " type=checkbox></td>"
												+ "<td><a class='edit' href=''><img alt=edit src=../images/edit.png></img></a></td>"
												+ "<td>"
												+ stateName
												+ "</td><td>"
												+ cityName
												+ "</td>"
												+ "<td>"
												+ storeName
												+ "</td><td>"
												+ address
												+ "</td><td>"
												+ storeCode
												+ "</td><td>"
												+ storeStatusName
												+ "</td>"
												+ "<td>"
												+ brandName
												+ "<input type='hidden' id=project_storeId"+ i + " value="+ storeId
												+ "></input></td><td>"
												+ elementName
												+ "<input type='hidden' id=\"project_elementId\" value="
												+ elementId
												+ "></input><input type='hidden' id=\"project_element_mapping_id\" value="
												+ project_element_mappind_id
												+ "></input><input type='hidden' id=project_storeId_u value="+ storeId
												+ "></input></td><td>"
												+ quantity
												+ "<td>"
												+ elementStatusName
												+ "</td>"
												+ "<td>"
												+ comment
												+ "</td><td>"
												+ fdhubName	
												+ "<input type='hidden' id=jcNumber" + i	+ " value=" + jcNumber + "></input>" 
												+ "<input type='hidden' id=jcDate" + i + " value=" + jcDate+ "></input> </td>" 
												+ "<td>" + jcNumber + "</td>"
												+ "<td>"
												+ jcDate + "</td></tr>");
					}

					$('#jcMnd').append("</tbody>");
					getDataTable();
				},
				async : false,
				error : function(error) {
					alert("Error");
				}
			});
};

function getDataTable() {
	(function($) {
		$.fn.dataTableExt.oApi.fnGetColumnData = function(oSettings, iColumn,
				bUnique, bFiltered, bIgnoreEmpty) {
			// check that we have a column id
			if (typeof iColumn == "undefined")
				return new Array();

			// by default we only want unique data
			if (typeof bUnique == "undefined")
				bUnique = true;

			// by default we do want to only look at filtered data
			if (typeof bFiltered == "undefined")
				bFiltered = true;

			// by default we do not want to include empty values
			if (typeof bIgnoreEmpty == "undefined")
				bIgnoreEmpty = true;

			// list of rows which we're going to loop through
			var aiRows;

			// use only filtered rows
			if (bFiltered == true)
				aiRows = oSettings.aiDisplay;
			// use all rows
			else
				aiRows = oSettings.aiDisplayMaster; // all row numbers

			// set up data array
			var asResultData = new Array();

			for ( var i = 0, c = aiRows.length; i < c; i++) {
				iRow = aiRows[i];
				var aData = this.fnGetData(iRow);
				var sValue = aData[iColumn];

				// ignore empty values?
				if (bIgnoreEmpty == true && sValue.length == 0)
					continue;

				// ignore unique values?
				else if (bUnique == true
						&& jQuery.inArray(sValue, asResultData) > -1)
					continue;

				// else push the value onto the result data array
				else
					asResultData.push(sValue);
			}

			return asResultData;
		};
	}(jQuery));

	$.fn.dataTableExt.oApi.fnGetTds = function(oSettings, mTr) {
		var anTds = [];
		var anVisibleTds = [];
		var iCorrector = 0;
		var nTd, iColumn, iColumns;

		/* Take either a TR node or aoData index as the mTr property */
		var iRow = (typeof mTr == 'object') ? oSettings.oApi
				._fnNodeToDataIndex(oSettings, mTr) : mTr;
		var nTr = oSettings.aoData[iRow].nTr;

		/* Get an array of the visible TD elements */
		for (iColumn = 0, iColumns = nTr.childNodes.length; iColumn < iColumns; iColumn++) {
			nTd = nTr.childNodes[iColumn];
			if (nTd.nodeName.toUpperCase() == "TD") {
				anVisibleTds.push(nTd);
			}
		}

		/* Construct and array of the combined elements */
		for (iColumn = 0, iColumns = oSettings.aoColumns.length; iColumn < iColumns; iColumn++) {
			if (oSettings.aoColumns[iColumn].bVisible) {
				anTds.push(anVisibleTds[iColumn - iCorrector]);
			} else {
				anTds.push(oSettings.aoData[iRow]._anHidden[iColumn]);
				iCorrector++;
			}
		}

		return anTds;
	};

	function fnCreateSelect(aData) {
		var r = '<select><option value=""></option>', i, iLen = aData.length;
		for (i = 0; i < iLen; i++) {
			r += '<option value="' + aData[i] + '">' + aData[i] + '</option>';
		}
		return r + '</select>';
	}

	// Initialise the DataTable

	oTable = $('#jcMnd').dataTable( {
		"oLanguage" : {
			"sSearch" : "Search all columns:"
		},
		"bDestroy" : true
	});

	// Add a select menu for each TH element in the table footer
	$("tfoot th").each(function(i) {
		if (i >= 2 && i <= 7)
			this.innerHTML = fnCreateSelect(oTable.fnGetColumnData(i));
		$('select', this).change(function() {
			oTable.fnFilter($(this).val(), i);
		});
	});

	$('#jcMnd a.edit').on(
			'click',
			function(e) {
				project_element_mapping_u = ($(this).parent().parent().find('#project_element_mapping_id').val());
				project_elementId_u = ($(this).parent().parent().find('#project_elementId').val());
				storeId_u = ($(this).parent().parent().find('#project_storeId_u').val());
				e.preventDefault();

				/* Get the row as a parent of the link that was clicked on */
				nRow = $(this).parents('tr')[0];

				if (nEditing !== null && nEditing != nRow) {
					/*
					 * A different row is being edited - the edit should be
					 * cancelled and this row edited
					 */
					restoreRow(oTable, nEditing);
					editRow(oTable, nRow);
					nEditing = nRow;
				} else if (nEditing == nRow && this.innerHTML == "Save") {
					/* This row is being edited and should be saved */
					saveRow(oTable, nEditing);
					nEditing = null;
				} else {
					/* No row currently being edited */
					editRow(oTable, nRow);
					nEditing = nRow;
				}
			});

	$('#generateJCNum').on('click', function(e) {
		
		var storeId;
		var storeIdTemp = new Array();
		
		console.log('inside button click');
			$.ajax( {
							type : 'POST',
							url : '../MndJobCardAssignment',
							data : {
								userAction : 'get_job_card_number'
							},
							success : function(data) {
								// return data;
							jobCardNumber = Number(data.jobCardNumber);
							console.log('jc no ' + jobCardNumber);
							var jcCreated = [];
							var nodes = oTable.fnGetNodes();
							if (nodes != null) {
								console.log("Nodes:" + nodes.length);
									for ( var n = 0; n < nodes.length; n++) {
										var aPos = oTable.fnGetPosition(nodes[n]);
										aData = oTable.fnGetData(aPos);
										if (n == 0) {
											storeId = $('#chkbox' + n).val();
											console.log('storeId' + storeId);
											jcCreated[n] = $('#chkbox' + n).val();
											if($('#jcNumber'+n).val() == "NA"){
												oTable.fnUpdate('Mnd'+jobCardNumber, nodes[n], 14); // For jobCard Number
//														$('#jcNumber'+n).val('Mnd'+jobCardNumber);
													$('#jcNumber'+n).val(jobCardNumber);
												var curDate = getDate();
												oTable.fnUpdate(curDate, nodes[n], 15); // For JobCard Date by default it will be the current date
													$('#jcDate'+n).val(curDate);
													jobCardNumber++;
											}
										} 
										console.log('storeId in else' + storeId + "and" + $('#chkbox' + n).val());
										
										console.log("Checkbox: " + aData[0] + "  Value:" + aData[14]);
										if ($('#chkbox' + n).is(":checked")) {
											console.log('checked');
//											if (storeId != $('#chkbox' + n).val()) {
												storeId = $('#chkbox' + n).val();
												//alert('length of storeId array' + jcCreated.length);
												console.log('length of storeId array' + jcCreated.length);
												for(var jc=0;jc<jcCreated.length;jc++){
													if(jcCreated[jc] != $('#chkbox' + n).val()){
														if($('#jcNumber'+n).val() == "NA"){
															oTable.fnUpdate('Mnd'+jobCardNumber, nodes[n], 14); // For jobCard Number
			//														$('#jcNumber'+n).val('Mnd'+jobCardNumber);
																$('#jcNumber'+n).val(jobCardNumber);
															var curDate = getDate();
															oTable.fnUpdate(curDate, nodes[n], 15); // For JobCard Date by default it will be the current date
																$('#jcDate'+n).val(curDate);
																jobCardNumber++;
																jcCreated[n] = $('#chkbox' + n).val();
														}
													}
												}
//											}
											
									/*if (n == 0) {
										storeId = $('#chkbox' + n).val();
										alert('storeId' + storeId);
									} else {
										alert('storeId in else' + storeId + "and" + $('#chkbox' + n).val());
										if (storeId != $('#chkbox' + n).val()) {
											jobCardNumber++;
											storeId = $('#chkbox' + n).val();
										}
									}*/
								}
							}
						}
					},
					async : false,
					error : function(error) {
						alert("Error");
					}
			});
});


$('#SaveJcDetails').on('click', function(e) {
	//alert('inside Save click');
	var projectId;
	var storeId = [];
	var jcNumber = [];
	var jcDate = [];
	projectId = $('#filter_operation_Project_select').val();
	//alert('inside jc_card' + projectId);
	if (document.getElementById("filter_operation_Project_select").selectedIndex == 0) {
			alert("Please select Project");
			return;
	}
		var nodes = oTable.fnGetNodes();
		if (nodes != null) {
			//alert("Nodes:" + nodes.length);
			for ( var n = 0; n < nodes.length; n++) {
				var aPos = oTable.fnGetPosition(nodes[n]);
				aData = oTable.fnGetData(aPos);
				
				//alert("Checkbox: " + aData[0] + "  Value:" + aData[14]);
				if ($('#chkbox' + n).is(":checked")) {
						console.log('checked in save');
					storeId[n] = $('#chkbox'+n).val();
						console.log('store_id is ' + storeId[n]);
					jcNumber[n] = $('#jcNumber'+n).val();
						console.log('jcNumber' + jcNumber[n]);
					jcDate[n] = $('#jcDate'+n).val();
						console.log('store_id is ' + jcDate[n]);	
				}
			}
		}
		console.log('length of array' + storeId.length);
		if(storeId.length>0){
			/*console.log('projectId' + projectId);
			console.log('storeId' + storeId);
			console.log('jcNumber' + jcNumber);
			console.log('jcDate' + jcDate);
			console.log('before setJobCard function');*/
			setJobCard(projectId,storeId,jcNumber,jcDate);
		}else{
			alert('No rows selected');
			return;
		}

	});
}

function editRow(oTable, nRow) {
	// alert('in editRow');
	aData = oTable.fnGetData(nRow);
	var jqTds = $('>td', nRow);
	jqTds[1].innerHTML = "<a class='edit' href='#' onclick='updateRow();'>Save</a>/<a class='cancel' href='#' onclick='getMappedProjectStore();' >Cancel</a>";
	// jqTds[2].innerHTML = "<input id=\"state\" type='text'
	// value='"+aData[2]+"' readonly>";
	// jqTds[3].innerHTML = "<input id=\"city\" type='text' value='"+aData[3]+"'
	// readonly>";
	// jqTds[4].innerHTML = '<input id=\"storename\" type="text"
	// value="'+aData[4]+'" readonly>';
	// jqTds[5].innerHTML = '<input id=\"address\" type="text"
	// value="'+aData[5]+'" readonly>';
	// jqTds[6].innerHTML = '<input id=\"storecode\" type="text"
	// value="'+aData[6]+'">';
	jqTds[7].innerHTML = '<select id=\"storestatus\"><option value=\"SELECT\">SELECT</option></select>';

	// jqTds[8].innerHTML = '<input id=\"brand\" type="text"
	// value="'+aData[8]+'">';
	// jqTds[9].innerHTML = '<input id=\"elementname\" type="text"
	// value="'+aData[9]+'">';
	jqTds[10].innerHTML = '<input id=\"qty\" type="text" value="' + aData[10] + '">';
	jqTds[11].innerHTML = '<select id=\"elementstatus\"><option value=\"SELECT\">SELECT</option></select>';
	jqTds[12].innerHTML = '<input id=\"comments\" type="text" value="' + aData[12] + '">';
	// jqTds[13].innerHTML = '<input id=\"fdhub\" type="text"
	// value="'+aData[13]+'">';
	jqTds[14].innerHTML = '<input id=\"jcno\" type="text" value="' + aData[14] + '">';
	jqTds[15].innerHTML ='<input type="text" id="jcdate" value="'+aData[15]+'" onclick=date(jcdate)>';
	// value="'+aData[15]+'">';
	getStoreStatus(aData);
	getElementStatus(aData);

}



function restoreRow(oTable, nRow) {
	var aData = oTable.fnGetData(nRow);
	var jqTds = $('>td', nRow);

	for ( var i = 0, iLen = jqTds.length; i < iLen; i++) {
		oTable.fnUpdate(aData[i], nRow, i, false);
	}
	oTable.fnDraw();
}


// Get Store Status from store_status_master
var getStoreStatus = function(aData) {

	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'get_store_status'
		},
		success : function(data) {
			// alert('storestatus' + data);
			var len = data.length;
			for ( var i = 0; i < len; ++i) {
				var store_statusId = data[i].storestatusId;
				var store_statusName = data[i].storestatusName;
				$('#storestatus').append(
						"<option value=\"" + store_statusId + "\">"
								+ store_statusName + "</option>");
			}
			var store_status = document.getElementById('storestatus');
			for (i = 0; i < store_status.options.length; i++) {
				if (aData[7].trim() == (store_status.options[i].text.trim())) {
					store_status.options[i].selected = true;
				}
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Get Store Status from store_status_master
var getElementStatus = function() {

	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'get_element_status'
		},
		success : function(data) {
			// alert('elementstatus' + data);
			var len = data.length;
			for ( var i = 0; i < len; ++i) {
				var element_statusId = data[i].elementId;
				var element_statusName = data[i].elementName;

				$('#elementstatus').append(
						"<option value=\"" + element_statusId + "\">"
								+ element_statusName + "</option>");
			}

			var store_status = document.getElementById('elementstatus');
			for (i = 0; i < store_status.options.length; i++) {
				if (aData[11].trim() == (store_status.options[i].text.trim())) {
					store_status.options[i].selected = true;
				}
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

var updateRow = function() {

	var storeStatus = $('#storestatus').val();
	var quantity = $('#qty').val();
	var elementStatus = $('#elementstatus').val();
	var comments = $('#comments').val();
	var ProjectId = $('#filter_operation_Project_select').val();
	// alert('projectId is' + ProjectId);
	
	var storeId = $('#project_storeId_u').val(); 
//		alert('storeId is' + storeId);
	var project_elementId = $('#project_elementId').val(); 
//		alert('project elementId is' + project_elementId);
	
	var project_element_mappingId = $('#project_element_mapping_id').val();
//		alert('project element_mappingId is' + project_element_mappingId);
	 
	console.log('element mapping_id_u is' + project_elementId_u);
	console.log('project element mapping_u is' + project_element_mapping_u);
	console.log('store Id is' + storeId_u);
	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'update_mnd_Data',
			ProjectId : ProjectId,
			StoreId : storeId_u,
			StoreStatusId : storeStatus,
			Quantity : quantity,
			ElementStatusId : elementStatus,
			Comments : comments,
			ElementId : project_elementId_u,
			Project_element_mappingId : project_element_mapping_u
		},
		success : function(data) {
			alert(data);
			getMappedProjectStore();
		},
		error : function(error) {
			alert("Error");
		}
	});
};

var jobCardNumber;
var counter = 0;
var generateJobCardNumbers = function() {

	var ProjectId = $('#filter_operation_Project_select').val();
//	alert('inside jc_card' + ProjectId);
	if (document.getElementById("filter_operation_Project_select").selectedIndex == 0) {
		return;
	}

	if (counter == 0) {
		$.ajax( {
			type : 'POST',
			url : '../MndJobCardAssignment',
			data : {
				userAction : 'get_job_card_number',
				ProjectId : ProjectId
			},
			success : function(data) {
				return data;
				jobCardNumber = Number(data.jobCardNumber);
				//alert('jc no ' + jobCardNumber);
				counter++;
			},
			async : false,
			error : function(error) {
				alert("Error");
			}
		});
	}

};

var setJobCard = function(ProjectId, storeId, jcNumber, jcDate) {
//	alert('inside setJobCard');
	
	console.log('storeId '+ storeId);
	console.log('ProjectId '+ ProjectId);
	console.log('jcNumber '+ jcNumber);
	console.log('jcDate '+ jcDate);
	$.ajax( {
		type : 'POST',
		url : '../MndJobCardAssignment',
		data : {
			userAction : 'set_job_card',
			ProjectId : ProjectId,
			storeId : storeId,
			jcNumber : jcNumber,
			jcDate : jcDate
		},
		success : function(data) {
			//alert('response' + data) ;
			if (data.status == 0) {
				alert("Job Cards Created Successfullly");
//				window.location.reload();
				getMappedProjectStore();
			}

			else {
				alert("Job Cards Creation Failed");
			}

		},
		async : false,
		error : function(error) {
			alert("Error");
		}
	});
};

var validateStoreAndElementData = function() {

	var ProjectId = $('#filter_operation_Project_select').val();
	alert('inside jc_card' + ProjectId);
	if (document.getElementById("filter_operation_Project_select").selectedIndex == 0) {
		return;
	}

	var storeId = new Array();
	var jcNumber = new Array();
	var jcDate = new Array();

	var elementId = new Array();
	var elementStatus = new Array();
	var quantity = new Array();
	var comments = new Array();

	var k = 0;
	for ( var i = 1; i <= gridRowCount; i++) {

		if (document.getElementById("store_select" + i) != null) {

			// if ((document.getElementById("selectStoreCheckbox" + i).checked)
			// == true) {

			if (document.getElementById("store_select" + i).selectedIndex == 0) {
				alert('Please Select The Store');
				return;
			}

			if ($("#jc_number_input" + i).val() == "") {
				alert("Please Enter JC Number");
				return;
			}

			if ($("#jc_date_input" + i).val() == "") {
				alert("Please Enter JC Date");
				return;
			}

			var tbl = document.getElementById("element_grid_table" + i);
			var rCount = tbl.rows.length;

			if (rCount <= 1) {

				alert("Please Add Atleast One element to Store");
				return;
			}

			for ( var l = 1; l < rCount; l++) {

				// Store Data

				storeId[k] = $("#store_select" + i).val();
				jcNumber[k] = $("#jc_number_input" + i).val();
				jcNumber[k] = (jcNumber[k].trim()).substring(3);
				jcDate[k] = $("#jc_date_input" + i).val();

				// Element Data

				elementId[k] = tbl.rows[l].cells[1].children[0].value;

				if (elementId[k] == "select") {
					alert('Please Select The Element');
					return;
				}

				elementStatus[k] = tbl.rows[l].cells[4].children[0].value;

				if (elementStatus[k] == "select") {
					alert("Please Select Element Status");
					return;
				}

				quantity[k] = tbl.rows[l].cells[5].children[0].value;

				if (quantity[k] == "") {
					alert("Please Enter Quantity");
					return;
				}

				comments[k] = tbl.rows[l].cells[6].children[0].value;

				k++;

			}

			// }
		}

	}

	var ProjectId = $("#filter_operation_Project_select").val();
	if (document.getElementById("filter_operation_Project_select").selectedIndex == 0) {
		alert("Please Select Project");
		return;
	}

	/*
	 * alert("store " + storeId + " jcNumber " + jcNumber + " jcDate " + "" +
	 * jcDate + "elementId " + elementId + " element status " + "" +
	 * elementStatus + " quantity" + +quantity + " comments " + comments);
	 */
	setJobCard(ProjectId, storeId, jcNumber, jcDate, elementId, elementStatus,
			quantity, comments);

};

function date(id)
{
document.getElementById(id.id).value="";
	new JsDatePick({
	useMode:2,
	target:""+id.id+"",
		dateFormat:"%d/%m/%y"
});
}

/*function scriptfile(){
	alert("date");
		var filename="../js/Connect/jsDatePick.jquery.min.1.3.js";
		var fileref=document.createElement('script');
		fileref.setAttribute("type","text/javascript");
		fileref.setAttribute("src", filename);
		document.getElementsByTagName("head")[0].appendChild(fileref);
}*/
	

function getDate()
{
	//alert("getDate");
	var currentDate = new Date();
	console.log(currentDate+"===currentDate");
	var day = currentDate.getDate();
	console.log("day="+day);
	var month = currentDate.getMonth()+1;
	console.log("month=="+month);
	if(month<10){
		month=('0'+month);
		console.log("inside if "+month);
	}
	var year = currentDate.getFullYear();
	console.log("year="+year);
	if(year<1000){
		year=year;
		//alert("year=="+year);
	}
	year=currentDate.getFullYear().toString().substring(2, 4);
	
		
	var monthNames = [ "January", "February", "March", "April", "May", "June",
	                   "July", "August", "September", "October", "November", "December" ];

	return  day +"/"+ month +"/" + year ;
	 
}
