var oTable;
var nRow;
var nEditing;
var aData;
var nNodes;
var selectedRadioButtonId="select_mode_radio";
var tabToggle=function(radioButtonId){
$('#jc_div').css('display', 'none');
$('#element_mode_filter').css('display', 'none');
$('#element_div').css('display', 'none');


$('#division_name').val('');
$('#filter_Project_select').html('<option value="select">select</option>');
document.getElementById('filter_client_select').selectedIndex=0;


selectedRadioButtonId=radioButtonId;
if($('#'+radioButtonId).val()=="bulk_entry_mode"){
//$('#filter_select_Project').css('display', 'none');	
//$('#division_name_input').css('display', 'none');	
}	

else if($('#'+radioButtonId).val()=="select_mode"){
	//$('#filter_select_Project').css('display', 'inline');	
	//$('#division_name_input').css('display', 'inline');	
}	
};

//Get Client	
var getClient = function() {
	 
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_client'
		},
		success : function(data) {
			$('#filter_client_select').html(data);
		}
	});
};



// Get Project
var getProject = function() {
	var clientId = $('#filter_client_select').val();
	$('#division_name').val('');
	if(clientId=="select"){
	 $('#filter_Project_select').html('<option value="select">select</option>');
	 return;
	}
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_Project',
			clientId : clientId
		},
		success : function(data) {
			$('#filter_Project_select').html(data);
		}
	});
};

// Get Division
var getDivision = function() {
	var ProjectId = $('#filter_Project_select').val();
	if(ProjectId=="select"){
		$('#division_name').val('');
		 return;
	  }
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_division',
			ProjectId : ProjectId
		},
		success : function(data) {
			$('#division_name').val($.trim(data));
			var fd_division_name=$.trim(data);
		//alert("fd_division_name_______"+fd_division_name);
			if($('#'+selectedRadioButtonId).val()=="bulk_entry_mode"){
				//alert("bulk entry mode");
				showGridForJobCardSelection();
				
				}	

				else if($('#'+selectedRadioButtonId).val()=="select_mode"){
					//alert("select mode");
					getJobCardByProjectSelectMode(fd_division_name);
					
				}
				else if($('#'+selectedRadioButtonId).val()=="element_mode"){
					//alert("select mode");
					showElmentModeFilter();
					
				}
		}
	});

};

// Get Job Card By Project  select Mode
var getJobCardByProjectSelectMode = function(fd_division_name) {
	$('#jcUpdate tbody').remove();
	//alert('inside get_job_card_by_Project_select_mode');
	var ProjectId = $('#filter_Project_select').val();
	$.ajax( {
		type : 'POST',
		url : '../JobCardStatusUpdate',
		data : {
		userAction : 'get_job_card_by_Project_select_mode',
			ProjectId : ProjectId,
			fd_division_name:fd_division_name
		},
		success : function(data) {
			//alert(data);
			$('#jcUpdate').append("<tbody>");
				var len = data.length;
			for ( var i = 0; i < len; i++) {

				var project_storeId = data[i].Project_store_id;
				var jobcardId = data[i].job_card_id;
				var jobcardNumber = data[i].job_card_number;
					console.log('jobCard number' + jobcardNumber);
				var storeName = data[i].storeName;
					//alert(storeName);
					console.log('store name' + storeName);
				var address = data[i].address;
					console.log('address' + address);
				var cityName = data[i].city_name;
					console.log('cityname' + cityName);
				var elementName = data[i].element_name;
					console.log('element name' + elementName);
				var componentName = data[i].component_name;
					console.log('component name' + componentName);
				var materialName = data[i].material_name;
					console.log('material name ' + materialName);
					//alert('material Name' + materialName);
				var quantity = data[i].quantity;
					console.log('quantity' + quantity);
					
				var brandName = data[i].brandName;
				// alert('comment is' + comment);

				$('#jcUpdate tbody').append("<tr class='gradeA'><td><input name=chkbox id=chkbox" + i + " class=case  value=" + jobcardId + " type=checkbox></td>"
										+ "<td>" + jobcardNumber + "</td>" 
										+ "<td>" + elementName + "</td>"
										+ "<td>" + brandName + "</td>"
										+ "<td>" + componentName + "</td>" 
										+ "<td>" + materialName + "</td>" 
										+ "<td>" + quantity + "</td>"
										+ "<td>" + storeName + "</td>"
										+ "<td>" + address + "</td>"
										+ "<td>" + cityName  + "</td></tr>"); 
										
										
									
										
			}
			$('#jcUpdate').append("</tbody>");
			$('#jc_div').css('display', 'block');
			getDataTable();
			getJCStatus();
			/*return;
			$('#jc_div').css('display', 'block');
			$('#select_mode_select_all_checkbox').html('<input id="selectAll" onchange="jobCardSelection()" type="checkbox" />');
			$('#jc_select_div').html(data);
			getJCStatus();*/
		}
	});
};

//Show Element Mode Filter
var showElmentModeFilter = function() {
	$('#element_mode_filter').css('display', 'block');
	getJobCardByProjectElementMode();
	getStoreByProjectElementMode();
};



//Show Grid For Job Card Selection
var showGridForJobCardSelection=function(){
$('#jc_div').css('display', 'block');
$('#jc_select_div').html("<input type=\"button\" value=\"Add Row\" onclick=\"addRow('job_card_selection_grid_table')\" />" +
		"<input type=\"button\" value=\"Delete Row\"  onclick=\"deleteRow('job_card_selection_grid_table')\" />" +
		"<div style=\"height:370px;overflow:auto;\"><table class=display id=\"job_card_selection_grid_table\" cellspacing=\"0\">" +
		"<tr>" +
		"<th style=\"width:45px;\"><input id=\"selectAll\" onchange=\"jobCardSelection()\" type=\"checkbox\" /></th>" +
		"<th style=\"width:150px;\">Enter Job Card </th>" +
		"<th style=\"width:160px;\">Enter Store </th>" +
	
		"<th style=\"width:285px;\">Address</th>" +
		"<th style=\"width:185px;\">City </th>" +
		
		/*"<th>Element</th>" +
		"<th>Component</th>" +
		"<th>Material</th>" +
		"<th>Quantity</th>" +*/
		
		"</tr>"+
		
		"</table></div>");	
        getJCStatus();
        $('#select_mode_select_all_checkbox').html('');
};

var rowCount=0;
function addRow(tableID) {
	rowCount = rowCount + 1;
	//alert(rowCount);
	//alert("this is rowCount"+rowCount);
	var table = document.getElementById(tableID);

	var rowCount1 = table.rows.length;
	var row = table.insertRow(rowCount1);
	row.id="store_detail";
	var cell1 = row.insertCell(0);
	
	cell1.innerHTML="<input type=\"checkbox\" name=\"checkJobCard\" id=\"grid_job_card_select_checkbox"+rowCount+"\" value=\"\" />";
	cell1.style="width:45px;";
	
	var cell2 = row.insertCell(1);
	cell2.innerHTML="<select style=\"width:120px;\" onchange=\"getJobCardByJobCardNumber("+rowCount+");\" id=\"grid_job_card_number_select"+rowCount+"\"><option>select</option>";
	cell2.style="width:150px;";
	
	var cell3 = row.insertCell(2);
	cell3.innerHTML="<select style=\"width:150px;\"   onchange=\"getJobCardByStore("+rowCount+");\"  id=\"grid_project_name_select"+rowCount+"\"><option>select</option>";
	cell3.style="width:160px;";
	
	var cell4 = row.insertCell(3);
	cell4.innerHTML="<input type=\"text\" style=\"width:275px;\"   readonly  id=\"grid_store_address_input"+rowCount+"\" />";
	cell4.style="width:285px;";
	
	var cell5 = row.insertCell(4);
	cell5.innerHTML="<input type=\"text\" style=\"width:175px;\" readonly  id=\"grid_store_city_input"+rowCount+"\" />";
	cell5.style="width:185px;";
	
	/*var cell6 = row.insertCell(5);
	cell6.innerHTML="<input type=\"text\"  readonly  id=\"grid_store_city_input"+rowCount+"\" />";
	cell6.style="width:150px;";
	
	var cell7 = row.insertCell(6);
	cell7.innerHTML="<input type=\"text\"  readonly  id=\"grid_store_city_input"+rowCount+"\" />";
	cell7.style="width:150px;";
	
	var cell8 = row.insertCell(7);
	cell8.innerHTML="<input type=\"text\"   readonly  id=\"grid_store_city_input"+rowCount+"\" />";
	cell8.style="width:150px;";
	
	var cell9 = row.insertCell(8);
	cell9.innerHTML="<input type=\"text\" size=\"10\"  readonly  id=\"grid_store_city_input"+rowCount+"\" />";
	cell6.style="width:100px;";*/
	
	var rowCount1 = table.rows.length;
	var row1 = table.insertRow(rowCount1);
	row1.id="component_detail"+rowCount;
	
    //row.innerHTML="<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>";
	
	var cellr = row1.insertCell(0);
	cellr.colSpan="9";
	cellr.id="component_detail_th"+rowCount;
	/*cell1.innerHTML="";
	var cell2 = row.insertCell(1);
	cell2.colspan="4";
	cell2.innerHTML="";*/
	
	getJobCardByProjectBulkEnteryMode(rowCount);
	getStoreByProjectBulkEnteryMode(rowCount);


}

function deleteRow(tableID) {
	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;

		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			
			if(row.id=="store_detail"){
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				table.deleteRow(i);
				rowCount--;
				i--;
				
				table.deleteRow(i+1);
				rowCount--;
				i--;
				
			}
		}
		}
	} catch (e) {
		alert(e);
	}
}

//Get Job Card For grid Selection
var getJobCardByProjectBulkEnteryMode = function(rowCount) {
	var ProjectId = $('#filter_Project_select').val();
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_job_card_by_Project_bulk_entry_mode',
			ProjectId : ProjectId
		},
		success : function(data) {
			
		$('#grid_job_card_number_select'+rowCount).html(data);
			//getJCStatus();
		}
	});
};



//Get Store by Project Bulk Entry Mode
var getStoreByProjectBulkEnteryMode = function(rowCount) {
	var ProjectId = $('#filter_Project_select').val();
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_store_by_Project_bulk_entry_mode',
			ProjectId : ProjectId
		},
		success : function(data) {
			
		$('#grid_project_name_select'+rowCount).html(data);
			//getJCStatus();
		}
	});
};

//Get Job Card By Project Select Mode
var getJobCardByProjectElementMode = function() {
	var ProjectId = $('#filter_Project_select').val();
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_job_card_by_Project_bulk_entry_mode',
			ProjectId : ProjectId
		},
		success : function(data) {
			
		$('#element_mode_filter_job_card_select').html(data);
			//getJCStatus();
		}
	});
};

//Get Store by Project Select Mode
var getStoreByProjectElementMode = function() {
	var ProjectId = $('#filter_Project_select').val();
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_store_by_Project_bulk_entry_mode',
			ProjectId : ProjectId
		},
		success : function(data) {
			
		$('#element_mode_filter_store_select').html(data);
			//getJCStatus();
		}
	});
};
//Get Job Card By Card Number
var getJobCardByJobCardNumber = function(rowCount) {
	var jobCardId=$('#grid_job_card_number_select'+rowCount).val();
	if(jobCardId=="select"){
	
	    return;
	  }
	var outData="flag=get_job_card_by_job_card_number&jobCardId="+jobCardId;
   
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : outData,
		success : function(data) {
	
		var columnSeperator = "#&#";
		var rowSeperator = "!&!";
		var rowData=data.split(rowSeperator);
		var columnData=rowData[0].split(columnSeperator);
		
		var typeDdl = document.getElementById('grid_project_name_select'+rowCount);
		for (i = 0; i < typeDdl.options.length; i++) {
             
			if (jQuery.trim(columnData[0]) == (typeDdl.options[i].value)) {
			
				typeDdl.options[i].selected = true;
			}
		}
		 $('#grid_job_card_select_checkbox'+rowCount).val(jobCardId);
		 $('#grid_store_address_input'+rowCount).val(columnData[1]);
		 $('#grid_store_city_input'+rowCount).val(columnData[2]);
		 $('#component_detail_th'+rowCount).html(rowData[1]);
		 
		}
	});
};



//Get Job Card By Store 
var getJobCardByStore = function(rowCount) {
	var storeId=$('#grid_project_name_select'+rowCount).val();
	if(storeId=="select"){
	
	    return;
	  }
	var outData="flag=get_job_card_by_store&storeId="+storeId;
   
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : outData,
		success : function(data) {
		
		var columnSeperator = "#&#";
		var rowSeperator = "!&!";
		var rowData=data.split(rowSeperator);
		var columnData=rowData[0].split(columnSeperator);
		
		var typeDdl = document.getElementById('grid_job_card_number_select'+rowCount);
		for (i = 0; i < typeDdl.options.length; i++) {
             
			if (jQuery.trim(columnData[0]) == (typeDdl.options[i].value)) {
			
				typeDdl.options[i].selected = true;
			}
		}
		 $('#grid_job_card_select_checkbox'+rowCount).val(columnData[0]);
		 $('#grid_store_address_input'+rowCount).val(columnData[1]);
		 $('#grid_store_city_input'+rowCount).val(columnData[2]);
		 $('#component_detail_th'+rowCount).html(rowData[1]);
		 
		}
	});
};


//Get Job Card By Card Number Select Mode
var getJobCardByJobCardNumberElementMode = function() {
	var jobCardId=$('#element_mode_filter_job_card_select').val();
	if(jobCardId=="select"){
	
	    return;
	  }
	var outData="flag=get_job_card_by_job_card_number&jobCardId="+jobCardId;
   
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : outData,
		success : function(data) {
	
		var columnSeperator = "#&#";
		var rowSeperator = "!&!";
		var rowData=data.split(rowSeperator);
		var columnData=rowData[0].split(columnSeperator);
		
		var typeDdl = document.getElementById('element_mode_filter_store_select');
		for (i = 0; i < typeDdl.options.length; i++) {
             
			if (jQuery.trim(columnData[0]) == (typeDdl.options[i].value)) {
			
				typeDdl.options[i].selected = true;
			}
		}
		// $('#grid_job_card_select_checkbox'+rowCount).val(jobCardId);
		 $('#element_mode_address_input').val(columnData[1]);
		 $('#element_mode_city_input').val(columnData[2]);
		// $('#component_detail_th'+rowCount).html(rowData[1]);
		 getElementDetail();
		 
		}
	});
};

//Get Job Card By Store  select mode
var getJobCardByStoreElementMode = function() {
	var storeId=$('#element_mode_filter_store_select').val();
	if(storeId=="select"){
	
	    return;
	  }
	var outData="flag=get_job_card_by_store&storeId="+storeId;
   
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : outData,
		success : function(data) {
		
		var columnSeperator = "#&#";
		var rowSeperator = "!&!";
		var rowData=data.split(rowSeperator);
		var columnData=rowData[0].split(columnSeperator);
		
		var typeDdl = document.getElementById('element_mode_filter_job_card_select');
		for (i = 0; i < typeDdl.options.length; i++) {
             
			if (jQuery.trim(columnData[0]) == (typeDdl.options[i].value)) {
			
				typeDdl.options[i].selected = true;
			}
		}
		// $('#grid_job_card_select_checkbox'+rowCount).val(columnData[0]);
		 $('#element_mode_address_input').val(columnData[1]);
		 $('#element_mode_city_input').val(columnData[2]);
		 //$('#component_detail_th'+rowCount).html(rowData[1]);
		 getElementDetail();
		}
	});
};
//Get Element Detail
var getElementDetail =function(){

	var storeId=$('#element_mode_filter_store_select').val();
	if(storeId=="select"){
	
	    return;
	  }
	//alert(storeId);
	var outData="flag=get_element&storeId="+storeId;
   
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : outData,
		success : function(data) {
		//alert(data);
		$('#element_div').css('display', 'block');
		$('#element_select_div').html(data);
		var rowCount=$('#number_of_rows').val();
		
		for(var i=0;i<rowCount;i++){
			getElementStatus(i);
		}
		
		}
	});
};
// Get Job Card Status
var getJCStatus = function() {
     $.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_jc_status'
		},
		success : function(data) {
			$('#jc_status_select').html(data);

		}
	});
};

//Get Job Card Status
var getElementStatus = function(rowCount) {
	//alert(rowCount);
     $.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_element_status'
		},
		success : function(data) {
			
			$('#element_status_select'+rowCount).html(data);

		}
	});
};


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

	document.getElementById("jc_status_date_input").value = day +"/"+ month +"/" + year ;
	 
}

// Get Required Fields For Jc Update
var getRequiredFildsForJCUpdate = function() {
//	alert('getRequiredFildsForJCUpdate');
	var jcStatus = $('#jc_status_select option:selected').text();
//	alert(jcStatus);
	 getDate();
	 
	if(jcStatus == "Dispatched") {
		document.getElementById("required_filds_for_jc_update").innerHTML = "<div style=\"position:relative;margin:20px 0 0 10px;\">"
				+ "DC No.:<span style=\"margin:0 0 0 82px;\"><input name=\"dcNumber\" type=\"text\" size=\"27\"></span></div>"
				+ "<div style=\"position:relative;margin:20px 0 0 10px;\">DC Date:<span style=\"margin:0 0 0 75px;\">"
				+ "<input name=\"dcDate\" id=\"dc_date_input\" onmouseover=\"datePicker('dc_date_input')\" type=\"text\" size=\"27\"></span></div>"
				+ "<div style=\"position:relative;margin:20px 0 0 10px;\">Courier Name:<span id=\"select_courier_name\" style=\"margin:0 0 0 36px;\">"
				+ "<select id=\"courier_name_select\" style=\"width:200px\"><option>select</option></select></span></div>";
		getCourierName();
	}

	else if (jcStatus == "Implemented")  {
		document.getElementById("required_filds_for_jc_update").innerHTML = ""
				+ "<div style=\"position:relative;margin:20px 0 0 10px;\">"
				+ "Assembly Unit:<span id=\"select_assembly_unit\" style=\"margin:0 0 0 32px;\">"
				+ "<select id=\"assembly_unit_select\" style=\"width:200px\">"
				+ "<option>select</option></select></span></div>"
				+ "<div style=\"position:relative;margin:20px 0 0 10px;\">"
				+ "Assigned Date:<span style= \"margin:0 0 0 31px;\">"
				+ "<input name=\"assignedDate\" onmouseover=\"datePicker('assigned_date_input')\" id=\"assigned_date_input\" type=\"text\" size=\"27\"></span></div>"
				+ "<div style=\"position:relative;margin:20px 0 0 10px;\">"
				+ "Completion Date:<span style=\"margin:0 0 0 12px;\">"
				+ "<input name=\"completionDate\" onmouseover=\"datePicker('completion_date_input')\" id=\"completion_date_input\"  type=\"text\" size=\"27\"></span></div>";
		getAssemblyUnit();
	}
	else{
		document.getElementById("required_filds_for_jc_update").innerHTML = "";
	}
};

// Get Courier Name
var getCourierName = function() {
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_courier_name'
		},
		success : function(data) {
			$('#courier_name_select').html(data);

		}
	});
};

// Get Assembly Unit
var getAssemblyUnit = function() {
	$.ajax( {
		type : 'POST',
		url : 'JobCardStatusController.jsp',
		data : {
			flag : 'get_assembly_unit'
		},
		success : function(data) {
			$('#assembly_unit_select').html(data);

		}
	});
};

// Job Card Selection
var jobCardSelection= function(){
	
    var checkbox = new Array();
	checkbox=$('#jobCardSelect input[name=checkJobCard]');

	if ($('#selectAll').is(':checked')==true) {
         
		for ( var i = 0; i < checkbox.length; i++)
			checkbox[i].checked = true;

	}

	if ($('#selectAll').is(':checked')==false)  {

		for ( var i = 0; i < checkbox.length; i++)
			checkbox[i].checked = false;

	}
};

//Element Selection
var elementSelection= function(){
	
    var checkbox = new Array();
	checkbox=$('#element_status_update_form input[name=elementSelectCheckbox]');

	if ($('#selectAllElements').is(':checked')==true) {
       
		for ( var i = 0; i < checkbox.length; i++)
			checkbox[i].checked = true;

	}

	if ($('#selectAllElements').is(':checked')==false)  {
	
		for ( var i = 0; i < checkbox.length; i++)
			checkbox[i].checked = false;

	}
};

//Update Job Card

function updateJobCard(){

	console.log("updateJobCard");
	var dcNumber="null";
	var dcDate="null";
	var courierId='null';
	var assignedDate="null";
	var completionDate="null";
	var assemblyUnitId='null';
	var checkBoxArray=new Array();
	checkBoxArray=document.getElementsByName("checkJobCard");
	var selectedJcId=new Array();
	var j=0;
	 for (var i = 0; i < checkBoxArray.length; i++)
	    if (checkBoxArray[i].checked){
	       //alert(checkBoxArray[i].value);
	    	selectedJcId[j]=checkBoxArray[i].value;
	       if(selectedJcId[j]==""){
	    	   alert("Please Enter  Job Card");
	    	   return;
	       }
	    	   
	       j++;
	      }

if(selectedJcId.length<1){
alert("Please select job cards");
return;
}	 

var e = document.getElementById("jc_status_select");
var jcStatusId=e.options[e.selectedIndex].value;


if(jcStatusId=="select"){
	alert("Please Select Status");
	return;
}



var dateStr=document.updateForm.statusDate.value;
if(dateStr==""){
    alert("Please Enter Status Date");
    dateStr.focus();
    return false;	

  }

var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

var matchArray = dateStr.match(datePat); // is the format ok?
if (matchArray == null) {
 alert("Date must be in DD/MM/YY format");
 return false;
}

day = matchArray[1]; // parse date into variables
month = matchArray[3];
year = matchArray[4];
if (month < 1 || month > 12) { // check month range
 alert("Month must be between 1 and 12");
 return false;
}
if (day < 1 || day > 31) {
 alert("Day must be between 1 and 31");
 return false;
}
if ((month==4 || month==6 || month==9 || month==11) && day==31) {
 alert("Month "+month+" doesn't have 31 days!")
 return false;
}
if (month == 2) { // check for february 29th
 var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
 if (day>29 || (day==29 && !isleap)) {
  alert("February " + year + " doesn't have " + day + " days!");
  return false;
   }
}
// date is valid


var statusDate = dateStr;
	
var comments=document.updateForm.comments.value;
/*if(comments==""){
	alert("Please Enter Comments");
	document.updateForm.comments.focus();
	return;
}*/

if(jcStatusId=="1"){
	 assemblyUnitId="null";
	 dcNumber=document.updateForm.dcNumber.value;
	if(dcNumber==""){
		alert("Please Enter DC Number");
		document.updateForm.dcNumber.focus();
		return;
	}
	
	 var dateStr=document.updateForm.dcDate.value;;
	 if(dateStr==""){
	     alert("Please Enter DC Date");
	     dateStr.focus();
	     return false;	
	   }

	 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

	 var matchArray = dateStr.match(datePat); // is the format ok?
	 if (matchArray == null) {
	  alert("Date must be in DD/MM/YY format");
	  return false;
	 }

	 day = matchArray[1]; // parse date into variables
	 month = matchArray[3];
	 year = matchArray[4];
	 if (month < 1 || month > 12) { // check month range
	  alert("Month must be between 1 and 12");
	  return false;
	 }
	 if (day < 1 || day > 31) {
	  alert("Day must be between 1 and 31");
	  return false;
	 }
	 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
	  alert("Month "+month+" doesn't have 31 days!")
	  return false;
	 }
	 if (month == 2) { // check for february 29th
	  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	  if (day>29 || (day==29 && !isleap)) {
	   alert("February " + year + " doesn't have " + day + " days!");
	   return false;
	    }
	 }
	 // date is valid

	 dcDate = dateStr;
	
	var e = document.getElementById("courier_name_select");
	 courierId=e.options[e.selectedIndex].value;
	if(courierId=="select"){
		alert("Please select Courier Name");
		return;
	}
}
	 
else if(jcStatusId=="2"){
	courierId="null";
	  var dateStr=document.updateForm.assignedDate.value;
		 if(dateStr==""){
		     alert("Please Enter Assigned Date");
		     dateStr.focus();
		     return false;	
		   }

		 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

		 var matchArray = dateStr.match(datePat); // is the format ok?
		 if (matchArray == null) {
		  alert("Date must be in DD/MM/YY format");
		  return false;
		 }

		 day = matchArray[1]; // parse date into variables
		 month = matchArray[3];
		 year = matchArray[4];
		 if (month < 1 || month > 12) { // check month range
		  alert("Month must be between 1 and 12");
		  return false;
		 }
		 if (day < 1 || day > 31) {
		  alert("Day must be between 1 and 31");
		  return false;
		 }
		 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
		  alert("Month "+month+" doesn't have 31 days!")
		  return false;
		 }
		 if (month == 2) { // check for february 29th
		  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		  if (day>29 || (day==29 && !isleap)) {
		   alert("February " + year + " doesn't have " + day + " days!");
		   return false;
		    }
		 }
		 // date is valid
		 assignedDate = dateStr;
    var dateStr=document.updateForm.completionDate.value;
	 if(dateStr==""){
	     alert("Please Enter Completion Date");
	     dateStr.focus();
	     return false;	

	   }

	 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

	 var matchArray = dateStr.match(datePat); // is the format ok?
	 if (matchArray == null) {
	  alert("Date must be in DD/MM/YY format");
	  return false;
	 }

	 day = matchArray[1]; // parse date into variables
	 month = matchArray[3];
	 year = matchArray[4];
	 if (month < 1 || month > 12) { // check month range
	  alert("Month must be between 1 and 12");
	  return false;
	 }
	 if (day < 1 || day > 31) {
	  alert("Day must be between 1 and 31");
	  return false;
	 }
	 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
	  alert("Month "+month+" doesn't have 31 days!")
	  return false;
	 }
	 if (month == 2) { // check for february 29th
	  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	  if (day>29 || (day==29 && !isleap)) {
	   alert("February " + year + " doesn't have " + day + " days!");
	   return false;
	    }
	 }
	 // date is valid

	 completionDate = dateStr;
	
	var e = document.getElementById("assembly_unit_select");
	 assemblyUnitId=e.options[e.selectedIndex].value;
	if(assemblyUnitId=="select"){
		alert("Please select Assembly Unit");
		return;
	}
}
	else{
		var dcNumber="null";
		var dcDate="null";
		var courierId='null';
		var assignedDate="null";
		var completionDate="null";
		var assemblyUnitId='null';;
	}
	
var outData="flag=update_job_card&selectedJcId="+selectedJcId+"&jcStatusId="+jcStatusId+"&statusDate="+statusDate+
"&comments="+comments+"&dcNumber="+dcNumber+"&dcDate="+dcDate+"&courierId="+courierId+
"&assignedDate="+assignedDate+"&completionDate="+completionDate+"&assemblyUnitId="+assemblyUnitId;

//alert(outData);
$.ajax( {
	type : 'POST',
	url : 'JobCardStatusController.jsp',
	data : outData,
	success : function(data) {
	
	alert("Job Cards Updated Successfully");
	//window.location.reload();
	document.getElementById('updateForm').reset();
	document.getElementById('jobCardSelect').reset();
	}
});



}

// Update Element 
function updateElementStatus(){

	var checkBoxArray=new Array();
	checkBoxArray=document.getElementsByName("elementSelectCheckbox");
	var elementId=new Array();
	var elementStatusId=new Array();
	var elementStatusDate=new Array();
	var elementStatusComments=new Array();
	

	var j=0;
	
	
	 for (var i = 0; i < checkBoxArray.length; i++)
	    if (checkBoxArray[i].checked){
	       //alert(checkBoxArray[i].value);
	    	elementId[j]=checkBoxArray[i].value;
	       if(elementId[j]==""){
	    	   alert("Please Select Element Types");
	    	   return false;
	       }
	    	   
	   

 

var e = document.getElementById("element_status_select"+i);
var elementStatus=e.options[e.selectedIndex].value;


if(elementStatus=="select"){
	alert("Please Select Status");
	return false;
}
elementStatusId[j]=elementStatus;


var dateStr=document.getElementById("date"+i).value;
if(dateStr==""){
    alert("Please Enter Status Date");
    document.getElementById("date"+i).focus();
    return false;	

  }

var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

var matchArray = dateStr.match(datePat); // is the format ok?
if (matchArray == null) {
 alert("Date must be in DD/MM/YY format");
 document.getElementById("date"+i).focus();
 return false;
}

day = matchArray[1]; // parse date into variables
month = matchArray[3];
year = matchArray[4];
if (month < 1 || month > 12) { // check month range
 alert("Month must be between 1 and 12");
 document.getElementById("date"+i).focus();
 return false;
}
if (day < 1 || day > 31) {
 alert("Day must be between 1 and 31");
 document.getElementById("date"+i).focus();
 return false;
}
if ((month==4 || month==6 || month==9 || month==11) && day==31) {
 alert("Month "+month+" doesn't have 31 days!");
 document.getElementById("date"+i).focus();
 return false;
}
if (month == 2) { // check for february 29th
 var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
 if (day>29 || (day==29 && !isleap)) {
  alert("February " + year + " doesn't have " + day + " days!");
  document.getElementById("date"+i).focus();
  return false;
   }
}
// date is valid


elementStatusDate[j] = dateStr;
  
  


	
var comments=document.getElementById("comments"+i).value;
if(comments==""){
	alert("Please Enter Comments");
	document.getElementById("comments"+i).focus();
	return false;
}
elementStatusComments[j] = comments;
j++;

	    
	    
}
	 
if(elementId.length<=0){
	alert("Please Select Job Card");
	return;
}	 
	// alert(elementId+"  "+elementStatusId+"  "+elementStatusDate+"  "+elementStatusComments);

	 

var outData="flag=update_element_status&elementId="+elementId+"&elementStatusId="+elementStatusId+
"&elementStatusDate="+elementStatusDate+"&elementStatusComments="+elementStatusComments;

//alert(outData);
$.ajax( {
	type : 'POST',
	url : 'JobCardStatusController.jsp',
	data : outData,
	success : function(data) {
	
	//alert("Job Cards Updated Successfully");
	window.location.reload();
	}
});



}

function getXMLHttpRequestObject() {
	var xmlHttpReq = false;
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {

			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {

				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}

function datePicker(textBoxId){
	//document.getElementById('jc_status_date_input').value='';
new JsDatePick({

useMode:2,

target:""+textBoxId+"",

dateFormat:"%d/%m/%Y"

}); 

}


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

	oTable = $('#jcUpdate').dataTable( {
		"oLanguage" : {
			"sSearch" : "Search all columns:"
		},
		"bDestroy" : true,
		"bPaginate": false,
		"bfilter":true,
		"aoColumnDefs": [
		                 { 
		                   "bSortable": false, 
		                   "aTargets": [ 0 ] // <-- gets last column and turns off sorting
		                  } 
		              ]
	
	});

	// Add a select menu for each TH element in the table footer
	$("tfoot th").each(function(i) {
		if (i >= 2 && i <= 7)
			this.innerHTML = fnCreateSelect(oTable.fnGetColumnData(i));
		$('select', this).change(function() {
			oTable.fnFilter($(this).val(), i);
		});
	});
	
		
	/*	$('#checkall').click( function() {
			
			$('input', oTable.fnGetNodes()).each( function() {
				$('input', oTable.fnGetNodes()).prop('checked','checked');
			} );
			return false; // to avoid refreshing the page
		} );
	*/
$(function(){

		// add multiple select / deselect functionality
	$("#checkall").click(function () {
			$('.case').prop('checked', this.checked);
		});

		// if all checkbox are selected, check the selectall checkbox
		// and viceversa
	$(".case").click(function(){

		if($(".case").length == $(".case:checked").length) {
			$("#checkall").prop("checked", "checked");
		} else {
			$("#checkall").prop("unchecked", "unchecked");
		}

	});
});

	$('#updateDetails').on('click', function(e) {
//		alert('inside Update click');
		var jobcardId = [];
		
		//alert('inside jc_card' + projectId);
		if (document.getElementById("filter_Project_select").selectedIndex == 0) {
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
							jobcardId[n] = $('#chkbox'+n).val();
//							alert('checked jcCard is' + jobcardId[n]);
							console.log('store_id is ' + jobcardId[n]);
					}
				}
			}
			console.log('length of array' + jobcardId.length);
			if(jobcardId.length>0){
				console.log("updateJobCard");
				var dcNumber="null";
				var dcDate="null";
				var courierId='null';
				var assignedDate="null";
				var completionDate="null";
				var assemblyUnitId='null';
				var checkBoxArray=new Array();
				

			var e = document.getElementById("jc_status_select");
			var jcStatusId=e.options[e.selectedIndex].value;


			if(jcStatusId=="select"){
				alert("Please Select Status");
				return;
			}



			var dateStr=document.updateForm.statusDate.value;
			if(dateStr==""){
			    alert("Please Enter Status Date");
			    dateStr.focus();
			    return false;	

			  }

			var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

			var matchArray = dateStr.match(datePat); // is the format ok?
			if (matchArray == null) {
			 alert("Date must be in DD/MM/YY format");
			 return false;
			}

			day = matchArray[1]; // parse date into variables
			month = matchArray[3];
			year = matchArray[4];
			if (month < 1 || month > 12) { // check month range
			 alert("Month must be between 1 and 12");
			 return false;
			}
			if (day < 1 || day > 31) {
			 alert("Day must be between 1 and 31");
			 return false;
			}
			if ((month==4 || month==6 || month==9 || month==11) && day==31) {
			 alert("Month "+month+" doesn't have 31 days!")
			 return false;
			}
			if (month == 2) { // check for february 29th
			 var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
			 if (day>29 || (day==29 && !isleap)) {
			  alert("February " + year + " doesn't have " + day + " days!");
			  return false;
			   }
			}
			// date is valid


			var statusDate = dateStr;
				
			var comments=document.updateForm.comments.value;
			/*if(comments==""){
				alert("Please Enter Comments");
				document.updateForm.comments.focus();
				return;
			}*/

			if(jcStatusId=="1"){
				 assemblyUnitId="null";
				 dcNumber=document.updateForm.dcNumber.value;
				if(dcNumber==""){
					alert("Please Enter DC Number");
					document.updateForm.dcNumber.focus();
					return;
				}
				
				 var dateStr=document.updateForm.dcDate.value;;
				 if(dateStr==""){
				     alert("Please Enter DC Date");
				     dateStr.focus();
				     return false;	
				   }

				 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

				 var matchArray = dateStr.match(datePat); // is the format ok?
				 if (matchArray == null) {
				  alert("Date must be in DD/MM/YY format");
				  return false;
				 }

				 day = matchArray[1]; // parse date into variables
				 month = matchArray[3];
				 year = matchArray[4];
				 if (month < 1 || month > 12) { // check month range
				  alert("Month must be between 1 and 12");
				  return false;
				 }
				 if (day < 1 || day > 31) {
				  alert("Day must be between 1 and 31");
				  return false;
				 }
				 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
				  alert("Month "+month+" doesn't have 31 days!")
				  return false;
				 }
				 if (month == 2) { // check for february 29th
				  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
				  if (day>29 || (day==29 && !isleap)) {
				   alert("February " + year + " doesn't have " + day + " days!");
				   return false;
				    }
				 }
				 // date is valid

				 dcDate = dateStr;
				
				var e = document.getElementById("courier_name_select");
				 courierId=e.options[e.selectedIndex].value;
				if(courierId=="select"){
					alert("Please select Courier Name");
					return;
				}
			}
				 
			else if(jcStatusId=="2"){
				courierId="null";
				  var dateStr=document.updateForm.assignedDate.value;
					 if(dateStr==""){
					     alert("Please Enter Assigned Date");
					     dateStr.focus();
					     return false;	
					   }

					 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

					 var matchArray = dateStr.match(datePat); // is the format ok?
					 if (matchArray == null) {
					  alert("Date must be in DD/MM/YY format");
					  return false;
					 }

					 day = matchArray[1]; // parse date into variables
					 month = matchArray[3];
					 year = matchArray[4];
					 if (month < 1 || month > 12) { // check month range
					  alert("Month must be between 1 and 12");
					  return false;
					 }
					 if (day < 1 || day > 31) {
					  alert("Day must be between 1 and 31");
					  return false;
					 }
					 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					  alert("Month "+month+" doesn't have 31 days!")
					  return false;
					 }
					 if (month == 2) { // check for february 29th
					  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					  if (day>29 || (day==29 && !isleap)) {
					   alert("February " + year + " doesn't have " + day + " days!");
					   return false;
					    }
					 }
					 // date is valid
					 assignedDate = dateStr;
			    var dateStr=document.updateForm.completionDate.value;
				 if(dateStr==""){
				     alert("Please Enter Completion Date");
				     dateStr.focus();
				     return false;	

				   }

				 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

				 var matchArray = dateStr.match(datePat); // is the format ok?
				 if (matchArray == null) {
				  alert("Date must be in DD/MM/YY format");
				  return false;
				 }

				 day = matchArray[1]; // parse date into variables
				 month = matchArray[3];
				 year = matchArray[4];
				 if (month < 1 || month > 12) { // check month range
				  alert("Month must be between 1 and 12");
				  return false;
				 }
				 if (day < 1 || day > 31) {
				  alert("Day must be between 1 and 31");
				  return false;
				 }
				 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
				  alert("Month "+month+" doesn't have 31 days!")
				  return false;
				 }
				 if (month == 2) { // check for february 29th
				  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
				  if (day>29 || (day==29 && !isleap)) {
				   alert("February " + year + " doesn't have " + day + " days!");
				   return false;
				    }
				 }
				 // date is valid

				 completionDate = dateStr;
				
				var e = document.getElementById("assembly_unit_select");
				 assemblyUnitId=e.options[e.selectedIndex].value;
				if(assemblyUnitId=="select"){
					alert("Please select Assembly Unit");
					return;
				}
			}
				else{
					var dcNumber="null";
					var dcDate="null";
					var courierId='null';
					var assignedDate="null";
					var completionDate="null";
					var assemblyUnitId='null';;
				}
				
			var outData="flag=update_job_card&selectedJcId="+jobcardId+"&jcStatusId="+jcStatusId+"&statusDate="+statusDate+
			"&comments="+comments+"&dcNumber="+dcNumber+"&dcDate="+dcDate+"&courierId="+courierId+
			"&assignedDate="+assignedDate+"&completionDate="+completionDate+"&assemblyUnitId="+assemblyUnitId;

			//alert(outData);
			$.ajax( {
				type : 'POST',
				url : 'JobCardStatusController.jsp',
				data : outData,
				success : function(data) {
				
				alert("Job Cards Updated Successfully");
				//window.location.reload();
				document.getElementById('updateForm').reset();
				document.getElementById('jobCardSelect').reset();
				}
			});
				console.log('jobcardId' + jobcardId);
				
			}else{
				alert('Please Select Job Card');
				return;
			}

		});

}