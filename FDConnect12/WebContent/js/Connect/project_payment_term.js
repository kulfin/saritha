/**
 * 
 */
function date_prjct_approval() {
	new JsDatePick({
		useMode : 2,
		target : "prjct_approval",
		dateFormat : "%Y-%m-%d"
	});
}

function date_prjct_implt() {
	new JsDatePick({
		useMode : 2,
		target : "prjct_implt",
		dateFormat : "%Y-%m-%d"

	});

}

function date_prjct_dispatch() {
	new JsDatePick({
		useMode : 2,
		target : "prjct_dispatch",
		dateFormat : "%Y-%m-%d"

	});
}

function date_prjct_docking() {
	new JsDatePick({
		useMode : 2,
		target : "prjct_docking",
		dateFormat : "%Y-%m-%d"

	});
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

function editPayment() {
	//alert("inside editPayment---->");
	ajaxCheck();

}

function save_payment_term(id){
	
	//	alert("inside save_payment_term---->"+id);
		
		var myForm=document.forms[0];
		
		var date_prjct_approval=mytermForm.prjct_approval.value;
		date_prjct_approval=date_prjct_approval.trim();
		
		if(date_prjct_approval.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT APPROVAL DATE IS NOT VALID DATE');
			return false;
		}
		
		
		var date_prjct_implt=mytermForm.prjct_implt.value;
		date_prjct_implt=date_prjct_implt.trim();
		if(date_prjct_implt.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT IMPLEMENTATION DATE IS NOT VALID DATE');
			return false;
		}

		var date_prjct_dispatch=mytermForm.prjct_dispatch.value;
		date_prjct_dispatch=date_prjct_dispatch.trim();
		
		if(date_prjct_dispatch.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT DISPATCH DATE IS NOT VALID DATE');
			return false;
		}

		
		var date_prjct_docking=mytermForm.prjct_docking.value;
		date_prjct_docking=date_prjct_docking.trim();
		
		if(date_prjct_docking.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT APPROVAL DATE IS NOT VALID DATE');
			return false;
		}
		
		//alert('dates-->'+date_prjct_approval+'  '+date_prjct_implt+'  '+date_prjct_dispatch+'   '+date_prjct_docking+'   ');
		
		ajaxCheck();

		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			  // alert(result.trim());
			   if(result.trim().match(DATA_INSERTED)){
				   alert('PAYMENT TERM DATES STORED !!');
			   }
			   else{
				   alert('PAYMENT TERM DATES NOT STORED !!');
			   }
			   project_term_days(id);
			   //document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
			     	      
		  }
		};
		
		var param=date_prjct_approval+','+date_prjct_implt+','+date_prjct_dispatch+','+date_prjct_docking;
	//	alert("param --- >"+param);
		ajaxRequest.open("GET", "InsertPaymentTerm.jsp?param="+param+"&pid="+id,true);	
		ajaxRequest.send(null); 
		
	}

function update_payment_term(id){
	
	//alert("inside update_payment_term---->"+id);

	var myForm=document.forms[0];
	
	var date_prjct_approval=mytermForm.prjct_approval.value;
	date_prjct_approval=date_prjct_approval.trim();

	if(date_prjct_approval.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
	{
	  //alert('DATE ACCEPTABLE');
	}
	else{
		alert('PROJECT APPROVAL DATE IS NOT VALID DATE');
		return false;
	}
	
	var date_prjct_implt=mytermForm.prjct_implt.value;
	date_prjct_implt=date_prjct_implt.trim();
	
	if(date_prjct_implt.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
	{
	  //alert('DATE ACCEPTABLE');
	}
	else{
		alert('PROJECT IMPLEMENTATION DATE IS NOT VALID DATE');
		return false;
	}

	var date_prjct_dispatch=mytermForm.prjct_dispatch.value;
	date_prjct_dispatch=date_prjct_dispatch.trim();
	
	if(date_prjct_dispatch.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
	{
	  //alert('DATE ACCEPTABLE');
	}
	else{
		alert('PROJECT DISPATCH DATE IS NOT VALID DATE');
		return false;
	}
	
	var date_prjct_docking=mytermForm.prjct_docking.value;
	date_prjct_docking=date_prjct_docking.trim();
	
	if(date_prjct_docking.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
	{
	  //alert('DATE ACCEPTABLE');
	}
	else{
		alert('PROJECT APPROVAL DATE IS NOT VALID DATE');
		return false;
	}
	
	//alert('dates-->'+date_prjct_approval+'  '+date_prjct_implt+'  '+date_prjct_dispatch+'   '+date_prjct_docking+'   ');
	
	ajaxCheck();

	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   if(result.trim().match(DATA_UPDATED)){
			   alert('PAYMENT TERM DATES ARE UPDATED !!');
		   }
		   else if(result.trim().match(DATA_NOT_UPDATED)){
			   alert('PAYMENT TERM DATES ARE NOT UPDATED !!');
		   }
		// alert(result.trim());
		// window.location.reload(true);
		//document.getElementById('maincontent_element').innerHTML=ajaxRequest.responseText;
		project_term_days(id);   	      
	  }
	};
	
	var param=date_prjct_approval+','+date_prjct_implt+','+date_prjct_dispatch+','+date_prjct_docking;
//	alert("param --- >"+param);
	
	ajaxRequest.open("GET", "UpdatePaymentTerm.jsp?dates="+param+"&id="+id,true);	
	ajaxRequest.send(null); 	
}

function project_approval(id) {
	// alert("Project Approval --- >  " + id);

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
	//alert("call_term_days --- >  " + id);

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
	//alert("project_details calling --- >  " + id);

	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
		//	alert("response project_details");
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

	// alert("project_document calling --- >  " + id);
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
	// alert("project_indent calling --- >  " + id);
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
//	alert("project_accountablity calling --- >  " + id);
	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
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

	//alert("project_national_date_plan calling --- >  " + id);
	ajaxCheck();

	// Create a function that will receive data
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			parent.window.document.getElementById('header_label').innerHTML = "Project Natioanal Date Plan";
			document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			/*
			 * var result = ajaxRequest.responseText; result = result.trim();
			 * alert("RESULT --->"+result);
			 */
			// document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		}
	};

	ajaxRequest.open("GET", "ProjectNationalDatePlan.jsp?id=" + id, true);
	ajaxRequest.send(null);
}
