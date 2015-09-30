
//Get Client	
var getClient = function() {
	$.ajax( {
		type : 'POST',
		url : 'MisTracker_Edge_Regional_Co_Ordinator_Report_Controller.jsp',
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
	if (clientId == "select") {
		alert("Select Client");
		$('#filter_Project_select').html(
				'<option value="select">select</option>');
		return;
	}
	$.ajax( {
		type : 'POST',
		url : 'MisTracker_Edge_Regional_Co_Ordinator_Report_Controller.jsp',
		data : {
			flag : 'get_Project',
			clientId : clientId
		},
		success : function(data) {
			$('#filter_Project_select').html(data);
		}
	});
};

// Get Job Card By Project
var getJobCardByProjectForReport = function() {
	$('#jc_div').html("");
	var ProjectId = $('#filter_Project_select').val();
	if (ProjectId == "select") {
		return false;
	}
	$('#loading-image').show();
	$.ajax( {
		type : 'POST',
		url : 'MisTracker_Edge_Regional_Co_Ordinator_Report_Controller.jsp',
		data : {
			flag : 'get_job_card_by_Project_for_report',
			ProjectId : ProjectId
		},
		success : function(data) {
			// alert(data);
		$('#jc_div').css('display', 'block');
		$('#jc_div').html(data);
		$('#loading-image').hide();
		// getJCStatus();
	}
	});
};

function datePicker(textBoxId) {

	new JsDatePick( {

		useMode : 2,

		target : "" + textBoxId + "",

		dateFormat : "%d/%m/%y"

	});

}

/*$("input.filter").keyup(function() {
	var rex = new RegExp($(this).val(), "i");
	$(".jobCardStatusTable tbody tr").hide();
	$(".jobCardStatusTable tbody tr").filter(function() {
	    return rex.test($(this).text());
	}).show();
});*/
