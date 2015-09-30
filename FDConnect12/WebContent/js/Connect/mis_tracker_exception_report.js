
//Get Client	
var getClient = function() {
	$.ajax( {
		type : 'POST',
		url : 'MisTracker_Exception_Report_Controller.jsp',
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
		url : 'MisTracker_Exception_Report_Controller.jsp',
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
		url : 'MisTracker_Exception_Report_Controller.jsp',
		data : {
			flag : 'get_job_card_by_Project_for_report',
			ProjectId : ProjectId
		},
		success : function(data) {
			
//			 alert(data);
			$('#jc_div').css('display', 'block');
			$('#jc_div').html(data);
			$('#loading-image').hide();
			
			//getDataTable();
			var oTable = $('#misReport').dataTable({
					
					'sSearch': "Search all columns",
					//'iDisplayLength': 10,
					//"aaSortingFixed": [[2,'asc']],//column number,ascending order
					//"bFilter": false,
					 "bPaginate": false,
		            'bLengthChange': false,
		            'bSort':false,
		            "bSortClasses": false
		            //"bProcessing" : true,
			          //  "bDestroy" : true,
			          //  "bAutoWidth" : true,
			       //     "sScrollY" : "400px",
			         //   "sScrollX" : "100%"
				});
			 
			var asInitVals = new Array();
			 
			     
			    $("thead input").keyup( function () {
			        /* Filter on the column (the index) of this element */
			        oTable.fnFilter( this.value, $("thead input").index(this) );
			    } );
			     
			     
			     
			    /*
			     * Support functions to provide a little bit of 'user friendlyness' to the textboxes in
			     * the footer
			     */
			    $("thead input").each( function (i) {
			        asInitVals[i] = this.value;
			    } );
			     
			    $("thead input").focus( function () {
			        if ( this.className == "search_init" )
			        {
			            this.className = "";
			            this.value = "";
			        }
			    } );
			     
			    $("thead input").blur( function (i) {
			        if ( this.value == "" )
			        {
			            this.className = "search_init";
			            this.value = asInitVals[$("thead input").index(this)];
			        }
			    } );
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
