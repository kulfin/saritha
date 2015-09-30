/**
 * date_cl_pl_st(<%=scope_list_row[0]%>) date_cl_pl_en(<%=scope_list_row[0]%>)
 * date_cl_ac_st(<%=scope_list_row[0]%>) date_cl_ac_en(<%=scope_list_row[0]%>)
 * date_fd_pl_st(<%=scope_list_row[0]%>) date_fd_pl_en(<%=scope_list_row[0]%>)
 * date_fd_ac_st(<%=scope_list_row[0]%>) date_fd_ac_en(<%=scope_list_row[0]%>)
 */

function date_cl_pl_st(id) {
	// alert('date ----- >'+id);
	id = "client_planned_start_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"
	/*
	 * selectedDate:{ This is an example of what the full configuration offers.
	 * day:5, For full documentation about these settings please see the full
	 * version of the code. month:9, year:2006 }, yearsRange:[1978,2020],
	 * limitToToday:false, cellColorScheme:"beige", dateFormat:"%m-%d-%Y",
	 * imgPath:"img/", weekStartDay:1
	 */
	});
	// alert("end");
}

function date_cl_pl_en(id) {
	// alert('date ----- >'+id);
	id = "client_planned_end_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

function date_cl_ac_st(id) {
	// alert('date ----- >'+id);
	id = "client_actual_start_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

function date_cl_ac_en(id) {
	// alert('date ----- >'+id);
	id = "client_actual_end_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

function date_fd_pl_st(id) {
	// alert('date ----- >'+id);
	id = "fd_planned_start_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

function date_fd_pl_en(id) {
	// alert('date ----- >'+id);
	id = "fd_planned_end_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

function date_fd_ac_st(id) {
	// alert('date ----- >'+id);
	id = "fd_actual_start_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

function date_fd_ac_en(id) {
	// alert('date ----- >'+id);
	id = "fd_actual_end_date" + id;
	// alert("id:: "+id);
	new JsDatePick({
		useMode : 2,
		target : id,
		dateFormat : "%Y-%m-%d"

	});
	// alert("end");
}

// Project Indent
function dateid() {

	new JsDatePick({
		useMode : 2,
		target : 'indent_create_date',
		dateFormat : "%Y-%m-%d"
	/*
	 * selectedDate:{ This is an example of what the full configuration offers.
	 * day:5, For full documentation about these settings please see the full
	 * version of the code. month:9, year:2006 }, yearsRange:[1978,2020],
	 * limitToToday:false, cellColorScheme:"beige", dateFormat:"%m-%d-%Y",
	 * imgPath:"img/", weekStartDay:1
	 */
	});

}

var ajaxRequest = null;
function ajaxCheck() {
	// The variable that makes Ajax possible!
	try {
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer Browsers
		try {
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}
	}
}

/*function insertScopeforElement(){
	
	alert('insertScopeforElement');
	
	var country=document.getElementById('country_select').value;
	var region=document.getElementById('region_select').value;
	var state=document.getElementById('state_select').value;
	var city=document.getElementById('city_select').value;
	var trade=document.getElementById('trade_id').value;
	var chain=document.getElementById('chain_id').value;
	
	alert(country+region+state+city+trade+chain);
	
	var no_of_store=document.getElementById('no_of_store').value;
	var scope_of_work=document.getElementById('scope_of_work').value;
	var client_aprvl_req=document.getElementById('client_approval_req').value;
	var prgm_element_scope=document.getElementById('Project_element_scope').value;//Project_element_id
	
	alert(no_of_store+scope_of_work+client_aprvl_req+prgm_element_scope);
	
	var cl_np_tar=document.getElementById('cl_np_tar').value;
	var cl_np_act=document.getElementById('cl_np_act').value;
	var cl_reg_tar=document.getElementById('cl_reg_tar').value;
	var cl_reg_act=document.getElementById('cl_reg_act').value;
	
	alert(cl_reg_act+cl_reg_tar+cl_np_act+cl_np_tar);
	
	var fd_np_tar=document.getElementById('fd_np_tar').value;
	var fd_np_act=document.getElementById('fd_np_act').value;
	var fd_reg_tar=document.getElementById('fd_reg_tar').value;
	var fd_reg_act=document.getElementById('fd_reg_act').value;
	
	alert(fd_np_tar+fd_np_act+fd_reg_tar+fd_reg_act);
	
	//ajaxCheck();
	
	
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

			var result = ajaxRequest.responseText;
			result = result.trim();
		}
	};

	ajaxRequest.open("POST", "InsertScopeWork.jsp?id=" + num + "&brand_name="
			+ brand_name + "&element_name=" + element_name + "&component_name="
			+ component_name + "&material_name=" + material_name + "&quantity="
			+ quantity + "&depot_name=" + depot_name + "&print_mode_name="
			+ print_mode_name + "&checkeditem=" + checked_position
			+ "&date_plan=" + date_plan + "&scope_set_as_hold=" + set_as_hold,
			true);
	ajaxRequest.send(null);
	
}*/


/*
function save_details(num) {

	// alert ("Save-DetailsCalled : Project ID--->"+num);

	var myForm = document.forms[0];

	var brand_name = myForm.brand_id.value;
	brand_id = brand_name.trim();

	var element_name = myForm.element_id.value;
	element_name = element_name.trim();

	var component_name = myForm.component_id.value;
	component_name = component_name.trim();

	var material_name = myForm.material_id.value;
	material_name = material_name.trim();

	var quantity = myForm.qty.value;
	quantity = quantity.trim();

	var depot_name = myForm.depot_id.value;
	depot_name = depot_name.trim();

	var print_mode_name = myForm.print_mode_id.value;
	print_mode_name = print_mode_name.trim();

	ajaxCheck();
	// Checking Check BOX
	var checked_position = '';
	var date_plan = '';
	var set_as_hold = '';
	var count = 0;
	if (confirm("Are You SURE OF SCOPE OF WORK")) {
		for ( var i = 0; i < myForm.chckbox.length; i++) {
			if (myForm.chckbox[i].checked) {
				checked_position = checked_position + myForm.chckbox[i].value
						+ ",";
				if (myForm.chck_hold[i].checked) {
					set_as_hold = set_as_hold + myForm.chck_hold[i].value + ",";
				}
				date_plan = date_plan
						+ document.getElementById('client_planned_start_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('client_planned_end_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('client_actual_start_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('client_actual_end_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('fd_planned_start_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('fd_planned_end_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('fd_actual_start_date'
								+ (i + 1)).value
						+ ","
						+ document.getElementById('fd_actual_end_date'
								+ (i + 1)).value + "@!@";
				// alert("date_plan for"+(i+1)+"----> "+date_plan);
				// alert("Scope which are set as hold"+set_as_hold);

				count++;

			}
		}

		// alert("count Check Box--->"+count);
		// alert("checked_position--->"+checked_position);
		// alert("date_plan ----> "+date_plan);
	} else {
		// alert("count Check Box in false--->"+count);
		return false;
	}

	// alert("Brand Name"+brand_name+ " element name"+element_name+" component
	// name"+component_name);
	// alert("\n
	// Material_name"+material_name+quantity+depot_name+print_mode_name+"\nDate
	// Plan"+date_plan);

	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

			var result = ajaxRequest.responseText;
			result = result.trim();
			alert("RESULT --->" + result);
			window.location.href = 'ProjectScopeOfWork.jsp?id=' + num;
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;

		}
	};

	ajaxRequest.open("POST", "InsertScopeWork.jsp?id=" + num + "&brand_name="
			+ brand_name + "&element_name=" + element_name + "&component_name="
			+ component_name + "&material_name=" + material_name + "&quantity="
			+ quantity + "&depot_name=" + depot_name + "&print_mode_name="
			+ print_mode_name + "&checkeditem=" + checked_position
			+ "&date_plan=" + date_plan + "&scope_set_as_hold=" + set_as_hold,
			true);
	ajaxRequest.send(null);

}*/

function save_crm_details(num) {
	var myForm = document.forms[0];

	var crm_name = myForm.crm_name.value;
	crm_name = crm_name.trim();

	if (crm_name == '') {
		alert('NAME CAN NOT BE EMPTY');
		return false;
	}

	var letters = /^[A-Za-z]+$/;
	if (crm_name.match(letters) || crm_name.match('')) {
		//alert("STRING CONTAINS A-Za-z");
	} else {
		alert("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN NAME.");

		return false;
	}

	var crm_phone = myForm.crm_phone.value;
	crm_phone = crm_phone.trim();

	if (crm_phone == '') {
		alert('PHONE NO CAN NOT BE EMPTY');
		return false;
	}

	if (isNaN(crm_phone) || crm_phone.indexOf(" ") != -1) {
		alert("PHONE NO NEED TO BE NUMERIC VALUE !!");
		return false;
	}

	if (crm_phone.length < 10) {
		alert("PHONE NO NEED TO HAVE 10 NUMBER ATLEAST !!");
		return false;
	}

	var crm_email = myForm.crm_email.value;
	crm_email = crm_email.trim();

	if (crm_email == '') {
		alert('EMAIL CAN NOT BE EMPTY');
		return false;
	}
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (crm_email.match(mailformat)) {
		//document.form1.text1.focus();  
		//return true;  
	} else {
		alert("YOU HAVE ENTEREDE AN INVALID EMAIL ADDRESS!");
		//document.form1.text1.focus();  
		return false;
	}

	var crm_notes = myForm.crm_notes.value;
	crm_notes = crm_notes.trim();
	if (crm_notes == '') {
		alert('CRM NOTES CAN NOT BE EMPTY');
		return false;
	}

	ajaxCheck();

	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			//alert("under if");
			var result = ajaxRequest.responseText;
			result = result.trim();
			if (result.trim().match('DATA_INSERTED')) {
				alert('PAYMENT TERM DATES STORED');
			} else {
				alert('PAYMENT TERM DATES NOT STORED');
			}
			//	   alert("RESULT save_crm_details--->"+result);	   
			//window.location.reload(true);
			//document.getElementById('maincontent_element').innerHTML=

			//	   alert('Calling project deatilas'+num);
			project_details(num);

		}
	};

	var param = crm_name + "@,@" + crm_phone + "@,@" + crm_email + "@,@"
			+ crm_notes + "@,@" + num;
	//alert("param --- >"+param);
	ajaxRequest.open("GET", "UpdateCrm.jsp?param=" + param, true);
	ajaxRequest.send(null);
}

function project_approval(id) {
	// alert("Project Approval --- > "+id);

	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			parent.window.document.getElementById('header_label').innerHTML = "Project Approval";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectApproval.jsp?id=" + id, true);
	ajaxRequest.send(null);

}

function project_term_days(id) {
	// alert("call_term_days --- > "+id);

	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			parent.window.document.getElementById('header_label').innerHTML = "Project PaymentTerm";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectPaymentTerm.jsp?id=" + id, true);
	ajaxRequest.send(null);

}

function project_details(id) {
	// alert("project_details calling --- > "+id);

	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			parent.window.document.getElementById('header_label').innerHTML = "Project CRM Details";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectDetails.jsp?id=" + id, true);
	ajaxRequest.send(null);
}

function project_document(id) {

	// alert("project_document calling --- > "+id);
	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			parent.window.document.getElementById('header_label').innerHTML = "Project Document";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectDocument.jsp?id=" + id, true);
	ajaxRequest.send(null);
}

function project_indent(id) {
	// alert("project_indent calling --- > "+id);
	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			parent.window.document.getElementById('header_label').innerHTML = "Project Indent";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectIndent.jsp?id=" + id, true);
	ajaxRequest.send(null);

}

/*
 * function project_scope_work(id){ alert('yyyyyyyy'); alert("project_scope_work
 * calling --- > "+id); ajaxCheck();
 *  // Create a function that will receive data // sent from the server and will
 * update // div section in the same page.
 * 
 * ajaxRequest.onreadystatechange = function(){ if(ajaxRequest.readyState == 4 &&
 * ajaxRequest.status==200){ alert("resp project_scope_work");
 * document.getElementById('maincontent_element').innerHTML =
 * ajaxRequest.responseText;
 * 
 *  } };
 * 
 * ajaxRequest.open("POST","project_scope_work.jsp?id="+id,true);
 * ajaxRequest.send(null); }
 */

function project_accountablity(id) {
	// alert("project_accountablity calling --- > "+id);
	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			// alert("response project_accountablity");
			parent.window.document.getElementById('header_label').innerHTML = "Project Accountability";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectAccountablity.jsp?id=" + id, true);
	ajaxRequest.send(null);
}

function project_national_date_plan(id) {

	// alert("project_national_date_plan calling --- > "+id);
	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			// alert("response project_national_date_plan");
			parent.window.document.getElementById('header_label').innerHTML = "Project Natioanal Date Plan";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectNationalDatePlan.jsp?id=" + id, true);
	ajaxRequest.send(null);
}


