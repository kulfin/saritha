 //CONSTANTS
	
	
	var DATA_INSUFFICIENT="DATA_INSUFFICIENT";
	var DATA_INSERTED="DATA_INSERTED";
	var DATA_NOT_INSERTED="DATA_NOT_INSERTED";
	
	//Update
	
	var DATA_UPDATED="DATA_UPDATED";
	var DATA_NOT_UPDATED="DATA_NOT_UPDATED";
	//Select
	var NO_DATA="NO DATA";
	//Delete
	var DATA_NOT_DELETED = "DATA NOT DELETED";
	var DATA_DELETED = "DATA DELETED";


//Dates start here	

function date(id)
	{
	//document.getElementById(id.id).value="";
		new JsDatePick({
		useMode:2,
		target:""+id.id+"",
			dateFormat:"%d/%m/%Y"
	});
		
		
		
	}

function date_client_approval_date(){
	new JsDatePick({
		useMode:2,
		target:"client_approval_date",
		dateFormat:"%Y-%m-%d"
	});	
}

function  date_fd_approval_date(){
	new JsDatePick({
		useMode:2,
		target:"fd_approval_date",
		dateFormat:"%Y-%m-%d"
	});	
}



function date_prjct_approval(){
		new JsDatePick({
			useMode:2,
			target:"prjct_approval",
			dateFormat:"%Y-%m-%d"
		});	
}

function date_prjct_implt(){
	new JsDatePick({
		useMode:2,
		target:"prjct_implt",
		dateFormat:"%Y-%m-%d"
		
	});
	
}

function date_prjct_dispatch(){
	new JsDatePick({
		useMode:2,
		target:"prjct_dispatch",
		dateFormat:"%Y-%m-%d"
		
	});
}

function date_prjct_docking(){
	new JsDatePick({
		useMode:2,
		target:"prjct_docking",
		dateFormat:"%Y-%m-%d"
		
	});
}


//Project Indent
function dateindent()
{
	
	new JsDatePick({
		useMode:2,
		target:'indent_create_date',
		dateFormat:"%Y-%m-%d"
		
	});
		
}

function date_cl_pl_st(id){
	//alert('date ----- >'+id);
	id = "client_planned_start_date"+id;
	//alert("id:: "+id);
	new JsDatePick({
		useMode:2,
		target:id,
		dateFormat:"%Y-%m-%d"
	});
	
}

function date_cl_pl_en(id){
//alert('date ----- >'+id);
id = "client_planned_end_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"

});

}

function date_cl_ac_st(id){
//alert('date ----- >'+id);
id = "client_actual_start_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"
	
});

}


function date_cl_ac_en(id){
//alert('date ----- >'+id);
id = "client_actual_end_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"
	
});

}


function date_fd_pl_st(id){
//alert('date ----- >'+id);
id = "fd_planned_start_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"
	
});

}


function date_fd_pl_en(id){
//alert('date ----- >'+id);
id = "fd_planned_end_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"
	
});

}


function date_fd_ac_st(id){
//alert('date ----- >'+id);
id = "fd_actual_start_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"
	
});

}


function date_fd_ac_en(id){
//alert('date ----- >'+id);
id = "fd_actual_end_date"+id;
//alert("id:: "+id);
new JsDatePick({
	useMode:2,
	target:id,
	dateFormat:"%Y-%m-%d"
	
});

}

//Dates ENd here
	
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
	
/*
	function create_project(){
		
		//alert("inside create_project() ");
		
		var myForm = document.forms[0];
		
		var project_id_v = myForm.project_id.value;
		project_id_v = project_id_v.trim();
		if(project_id_v==''){
			alert("Project NO CAN'T BE EMPTY");
			return false;
		}
		
			
		var project_name_v = myForm.project_name.value;
		project_name_v = project_name_v.trim();
		if(project_name_v==''){
			alert("Project NAME CAN'T BE EMPTY");
			return false;
		}
		
		
		var client_id_v = myForm.client_id.value;
		client_id_v = client_id_v.trim();
		if(client_id_v=='' || client_id_v=='SELECT'){
			alert("CLIENT NEED TO BE SELECTED");
			return false;
		}
		
		var fd_div_v = myForm.fd_div.value;
		fd_div_v = fd_div_v.trim();
		if(fd_div_v=='' || fd_div_v=='SELECT'){
			alert("FD DIVISION NEED TO BE SELECTED");
			return false;
		}

		var fd_start_date_v = myForm.fd_start_date.value;
		fd_start_date_v = fd_start_date_v.trim();
		if(fd_start_date_v.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('START DATE NOT ACCEPTABLE');
			return false;
		}

		var fd_end_date_v = myForm.fd_end_date.value;
		fd_end_date_v = fd_end_date_v.trim();
		if(fd_end_date_v.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		 // alert('DATE ACCEPTABLE');
		}
		else{
			alert('END DATE NOT ACCEPTABLE');
			return false;
		}
		
		ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   //alert("under if");
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT --->"+result);	   
		   if(result.trim()=="DATA_INSERTED"){
			alert('Project CREATED !!');
			myForm.project_id.value="";
			myForm.project_name.value="";
			myForm.fd_start_date.value="";
			myForm.fd_end_date.value="";
		   }
		   else if(result.trim()=="DATA_NOT_INSERTED")
		   {
			   alert('Project NOT CREATED !!');
		   }
		     	      
	  }
	};*/

	function getDivisionOnHub(){
		var hub_id=document.getElementById('fd_hub').value;
		/*if(hub_id=='SELECT' || hub_id==''){
			alert('SELECT HUB !!');
		}*/
		
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				   var result = ajaxRequest.responseText;
				   document.getElementById('fd_div_replace').innerHTML=result.trim();
				   getFDMgr_Name(); 	      
			  }
			};
		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getDivisionOnHub&hub_id="+hub_id,true);	
		ajaxRequest.send(null); 		
	}
	
	function  getFDMgr_Name(){
	//alert("fd manager name");
	//var FD_app_mgr=document.getElementById('fd_FDMgr').value;
	ajaxCheck();
	ajaxRequest.onreadystatechange=function(){
		if(ajaxRequest.readyState==4 && ajaxRequest.status==200){
			 var result = ajaxRequest.responseText;
			   document.getElementById('fd_div_FDMgr').innerHTML=result.trim();
		}
	};
	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getFDApprovalMgr",true);	
	ajaxRequest.send(null); 	
	}
	
	
	function create_Project(){
		
		//alert("inside create_project() ::: connect_js_entire.js");
		var columnSeperator="!#!";
		var myForm = document.forms[0];
		
		// Necessary Details
		var client_id_v = myForm.client_id.value;
		client_id_v = client_id_v.trim();
		if(client_id_v=='' || client_id_v=='SELECT'){
			alert("Please select Client Name");
			return false;
		}
		
		var Project_code_v = myForm.Project_id.value;
		Project_code_v = Project_code_v.trim();
		if(Project_code_v==''){
			alert("Please enter Project Code");
			return false;
		}
		
		var Project_name_v = myForm.Project_name.value;
		Project_name_v = Project_name_v.trim();
		if(Project_name_v==''){
			alert("Please enter Project Name");
			return false;
		}
		
		var fd_hub_v = myForm.fd_hub.value;
		if(fd_hub_v==''||fd_hub_v=='SELECT'){
			alert("Please Select Hub");
			return false;
		}
		
		var fd_div_v = myForm.fd_div.value;
		fd_div_v = fd_div_v.trim();
		if(fd_div_v=='' || fd_div_v=='SELECT'){
			alert("Please Select Division");
			return false;
		}
		
		var fd_FDMgr_v=document.getElementById('fd_FDMgr').value;
		if(document.getElementById("fd_FDMgr").selectedIndex==0){
			alert('Please Select FD Approval Manager');
			return false;
		}

		//rest checking
		var client_manager_name_v = myForm.client_manager_name.value;
		if(client_manager_name_v.length==0 || client_manager_name_v==null){
			client_manager_name_v='empty';
		}
		
		var client_manager_phone_v = myForm.client_manager_phone.value;
		if(client_manager_phone_v==''||client_manager_phone_v==null){
			client_manager_phone_v='empty';
		}
		else{
			
			if(isNaN(client_manager_phone_v)||client_manager_phone_v.indexOf(" ")!=-1)
	        {
	           alert("PHONE NUMBER IS INVALID !!");
	           return false; 
	        }
			
	        if (client_manager_phone_v.length<10)
	        {
	             alert("PLEASE ENTER 10 DIGIT NUMBER ATLEAST !!");
	             return false;
	        }
		}
		
		var client_manager_email_v = myForm.client_manager_email.value;
		if(client_manager_email_v==''||client_manager_email_v==null){
			client_manager_email_v='empty';
		}
		else{
			
		var atpos=client_manager_email_v.indexOf("@");
		var dotpos=client_manager_email_v.lastIndexOf(".");
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>=client_manager_email_v.length)
		  {
		  alert("NOT A VALID E-MAIL ADDRESS !!");
		  return false;
		  }
		}
	
		
		var client_manager_po_v = myForm.client_manager_po.value;
		if(client_manager_po_v==''||client_manager_po_v==null){
			client_manager_po_v='empty';
		}
		
		/*var po_date_v = myForm.po_date.value;
		if(po_date_v==''||po_date_v==null){
			po_date_v='empty';
		}
		else{
		if(po_date_v.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PO DATE IS NOT VALID DATE !!');
			return false;
		}
		}*/
		
		var po_date_ = myForm.po_date.value;
		if(po_date_==''||po_date_==null){
			po_date_='empty';
		}
		// FOR DATE VALIDATION
		
		 /*var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

		 var matchArray = po_date_.match(datePat); // is the format ok?
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
		  alert("Month "+month+" doesn't have 31 days!");
		  return false;
		 }
		 if (month == 2) { // check for february 29th
		  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		  if (day>29 || (day==29 && !isleap)) {
		   alert("February " + year + " doesn't have " + day + " days!");
		   return false;
		    }
		 }*/
		 
		 po_date_v=po_date_;

		
		
		var estimated_budget_v = myForm.estimated_budget.value;
		if(estimated_budget_v==''||estimated_budget_v==null){
			estimated_budget_v='empty';
		}
		else{
			if(isNaN(parseFloat(estimated_budget_v))){
				alert("Please enter numeric Value for Estimated Budget");
	             return false;
			}		
	        if (estimated_budget_v.length>20)
	        {
	             alert("ESTIMATED BUDGET CAN'T BE GREATER THAN 20 DIGITS !!");
	             return false;
	        }
			
		}
		
		
		var total_stores_v = myForm.total_stores.value;
		if(total_stores_v==''||total_stores_v==null){
			total_stores_v='empty';
		}
		else
		{
			if(isNaN(parseInt(total_stores_v))){
				alert("PLEASE ENTER NUMERIC VALUE IN ESTIMATED BUDGET IN TOTAL STORE !!");
				return false;		
			}
		}

		var Project_id_v = myForm.Project_id.value;
		if(Project_id_v==''||Project_id_v==null){
			Project_id_v='empty';
		}
		
		
		
		var crm_name_v = myForm.crm_name.value;
		if(crm_name_v==''||crm_name_v==null){
			crm_name_v='empty';
		}
		
		var fd_start_date_v = myForm.fd_start_date.value;
		if(fd_start_date_v==''||fd_start_date_v==null){
			fd_start_date_v='empty';
		}
		
		var crm_phone_v = myForm.crm_phone.value;
		if(crm_phone_v==''||crm_phone_v==null){
			crm_phone_v='empty';
		}
		
		var fd_end_date_v = myForm.fd_end_date.value;
		if(fd_end_date_v==''||fd_end_date_v==null){
			fd_end_date_v='empty';
		}
		
		var crm_email_v = myForm.crm_email.value;
		if(crm_email_v==''||crm_email_v==null){
			crm_email_v='empty';
		}
		
		var bill_naration_v = myForm.bill_naration.value;
		if(bill_naration_v==''||bill_naration_v==null){
			bill_naration_v='empty';
		}
		
		var special_instruction_v = myForm.special_instruction.value;
		if(special_instruction_v==''||special_instruction_v==null){
			special_instruction_v='empty';
		}
		
		//var notes_v = myForm.notes.value;
		
		var notes_v = document.getElementById('notes').value;
		if(notes_v==''){
			notes_v='empty';
		}	
		
		var po_amount_v=myForm.po_amount.value;
		if(po_amount_v=='' || po_amount_v==null){
			po_amount_v='0';
		}
		
		var pro_status_v=myForm.pro_status.value;
		if(pro_status_v=='' || pro_status_v==null){
			pro_status_v='empty';
		}
		
		if(pro_status_v == "SELECT"){
			alert("PLEASE SELECT PROJECT STATUS");
			pro_status.focus();
			return false;
		}
		var pro_status_date_=myForm.pro_status_date.value;
		if(pro_status_date_==''||pro_status_date_==null){
			pro_status_date_='empty';
		}
			/* var matchArray = pro_status_date_.match(datePat); // is the format ok?
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
			  alert("Month "+month+" doesn't have 31 days!");
			  return false;
			 }
			 if (month == 2) { // check for february 29th
			  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
			  if (day>29 || (day==29 && !isleap)) {
			   alert("February " + year + " doesn't have " + day + " days!");
			   return false;
			    }
			 }*/
			 pro_status_date_v=pro_status_date_;
			 
			 
	/*Project_name_v		client_id_v		fd_div_v

	client_manager_phone
	client_manager_email
	client_manager_po
	po_date
	estimated_budget
	total_stores

	Project_id
	fd_hub
	crm_name
	fd_start_date
	crm_phone
	fd_end_date
	crm_email
	bill_naration
	special_instruction
	notes*/
	
	/*Project_name ,  Project_number , client_id , division_id , start_date ,
	 end_date , notes , crm_name , crm_phone , crm_email , estimated_budget ,
	 billing_narration , special_instruction , total_stores*/
	
	
	
	var data=Project_name_v+columnSeperator+Project_id_v+columnSeperator+client_id_v+columnSeperator+
	fd_div_v+columnSeperator+fd_start_date_v+columnSeperator+fd_end_date_v+columnSeperator+notes_v+
	columnSeperator+crm_name_v+columnSeperator+crm_phone_v+columnSeperator+crm_email_v+
	columnSeperator+estimated_budget_v+columnSeperator+bill_naration_v+columnSeperator+special_instruction_v
	+columnSeperator+total_stores_v+columnSeperator+fd_FDMgr_v+columnSeperator+pro_status_v+columnSeperator+pro_status_date_v;
	
	/*Project_id , client_manager_name , client_manager_phone , client_manager_email ,
	 po_reference , po_date */
	
	var client_data=client_manager_name_v+columnSeperator+client_manager_phone_v+columnSeperator+client_manager_email_v
	+columnSeperator+client_manager_po_v+columnSeperator+po_date_v+columnSeperator+po_amount_v;
	
	
	//alert('data--->  '+data);
	//alert('client_data--->  '+client_data);
	
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			//   alert(result);
			   var arr_result=result.split(columnSeperator);
			   if(arr_result[0].trim()=="DATA_INSERTED"){
				alert('Successfully Created Project');
				
				var num=arr_result[1];
				//alert(num);
				window.location.href = 'DetailsProject.jsp?id='+num+"&flag=true";
				parent.window.document.getElementById('header_label').innerHTML="Project Page";
			
			   }
			   else if(arr_result[0].trim()=="DATA_NOT_INSERTED")
			   {
				   alert('Project NOT CREATED !!');
			   }
			     	      
		  }
		};
	
		/*ajaxRequest.open("GET", "InsertProject.jsp?data="+data+"&client_data="+client_data,true);
		//ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajaxRequest.send(null);*/
		
		ajaxRequest.open("POST", "InsertProject.jsp",true);
		ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajaxRequest.send("data="+data+"&client_data="+client_data);
}

	function update_Project_header(num){
		
		//alert('update_Project_header');
		
		var columnSeperator="!#!";
		
		// Necceary Details	
		var Project_name_v = myForm.Project_name.value;
		Project_name_v = Project_name_v.trim();
		if(Project_name_v==''){
			alert("PROJECT NAME CAN'T BE EMPTY !!");
			return false;
		}
			
		
		var client_id_v = myForm.client_id.value;
		client_id_v = client_id_v.trim();
		if(client_id_v=='' || client_id_v=='SELECT'){
			alert("PLEASE SELECT CLIENT !!");
			return false;
		}
		
		var fd_div_v = myForm.fd_div.value;
		fd_div_v = fd_div_v.trim();
		if(fd_div_v=='' || fd_div_v=='SELECT'){
			alert("FD DIVISION NEED TO BE SELECTED !!");
			return false;
		}
		
		//alert(Project_name_v+client_id_v+ fd_div_v);
		
		//Rest Data
		
		
		var client_manager_name_v = myForm.client_manager_name.value;
		if(client_manager_name_v==''||client_manager_name_v==null){
			client_manager_name_v='empty';
		}
		
		var client_manager_phone_v = myForm.client_manager_phone.value;
		if(client_manager_phone_v==''||client_manager_phone_v==null){
			client_manager_phone_v='empty';
		}
		else{
			
			if(isNaN(client_manager_phone_v)||client_manager_phone_v.indexOf(" ")!=-1)
	        {
	           alert("PHONE  NEED TO BE NUMERIC VALUE !!");
	           return false; 
	        }
			
	        if (client_manager_phone_v.length<10)
	        {
	             alert("PHONE  NEED TO HAVE 10 NUMBER ATLEAST !!");
	             return false;
	        }
		}
			
		var client_manager_email_v = myForm.client_manager_email.value;
		if(client_manager_email_v==''||client_manager_email_v==null){
			client_manager_email_v='empty';
		}
		else{
			
			var atpos=client_manager_email_v.indexOf("@");
			var dotpos=client_manager_email_v.lastIndexOf(".");
			if (atpos<1 || dotpos<atpos+2 || dotpos+2>=client_manager_email_v.length)
			  {
			  alert("NOT A VALID E-MAIL ADDRESS !!");
			  return false;
			  }
		}
		
		
		
		var client_manager_po_v = myForm.client_manager_po.value;
		if(client_manager_po_v==''||client_manager_po_v==null){
			client_manager_po_v='empty';
		}
		
		
		
		var po_date_v = (myForm.po_date.value).trim();
		if(po_date_v==''||po_date_v==null){
			po_date_v='empty';
		}
		/*
		else{
			if(po_date_v.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
			{
			  //alert('DATE ACCEPTABLE');
			}
			else{
				alert('PO DATE IS NOT VALID DATE !!');
				return false;
			}
			}
		*/
		
		var estimated_budget_v = myForm.estimated_budget.value;
		if(estimated_budget_v==''||estimated_budget_v==null){
			estimated_budget_v='empty';
		}else{
//			if(isNaN(parseFloat(estimated_budget_v))){
			if(isNaN(estimated_budget_v)){
				alert("ESTIMATED BUDGET NEED TO BE NUMERIC VALUE!!");
	             return false;
			}		
	        if (estimated_budget_v.length>20)
	        {
	             alert("ESTIMATED BUDGET CAN'T BE GREATER THAN 20 DIGITS !!");
	             return false;
	        }
			
		}
		
		//alert('estimated_budget_v'+estimated_budget_v);
		
		var total_stores_v = myForm.total_stores.value;
		if(total_stores_v==''||total_stores_v==null){
			total_stores_v='empty';
		}
		else
		{
			if(isNaN(parseInt(total_stores_v))){
				alert("TOTAL STORES NEED TO BE NUMERIC VALUE !!");
				return false;		
			}
		}
		
		//alert('total_stores_v'+total_stores_v);
		
		var Project_id_v = myForm.Project_id.value;
		if(Project_id_v==''||Project_id_v==null){
			Project_id_v='empty';
		}
		//alert('Project_id_v'+Project_id_v);
		
		var fd_hub_v = myForm.fd_hub.value;
		if(fd_hub_v==''||fd_hub_v==null){
			fd_hub_v='empty';
		}
		
		var crm_name_v = myForm.crm_name.value;
		if(crm_name_v==''||crm_name_v==null){
			crm_name_v='empty';
		}
		
		var fd_start_date_v = (myForm.fd_start_date.value).trim();
		if(fd_start_date_v==''||fd_start_date_v==null){
			fd_start_date_v='empty';
		}
		
		var crm_phone_v = myForm.crm_phone.value;
		if(crm_phone_v==''||crm_phone_v==null){
			crm_phone_v='empty';
		}else{
			
			if(isNaN(crm_phone_v)||crm_phone_v.indexOf(" ")!=-1)
	        {
	           alert("CRM PHONE NEED TO BE NUMERIC VALUE !!");
	           return false; 
	        }
			
	        if (crm_phone_v.length<10)
	        {
	             alert("CRM PHONE  NEED TO HAVE 10 NUMBER ATLEAST !!");
	             return false;
	        }
		}
		
		var fd_end_date_v = (myForm.fd_end_date.value).trim();
		if(fd_end_date_v==''||fd_end_date_v==null){
			fd_end_date_v='empty';
		}
		
		var crm_email_v = myForm.crm_email.value;
		if(crm_email_v==''||crm_email_v==null){
			crm_email_v='empty';
		}else{
			
			var atpos=crm_email_v.indexOf("@");
			var dotpos=crm_email_v.lastIndexOf(".");
			if (atpos<1 || dotpos<atpos+2 || dotpos+2>=crm_email_v.length)
			  {
			  alert("Not a valid e-mail address");
			  return false;
			  }
		}
		
		var bill_naration_v = myForm.bill_naration.value;
		if(bill_naration_v==''||bill_naration_v==null){
			bill_naration_v='empty';
		}
		
		var special_instruction_v = myForm.special_instruction.value;
		if(special_instruction_v==''||special_instruction_v==null){
			special_instruction_v='empty';
		}
		
		//var notes_v = myForm.notes.value;
		
		var notes_v = document.getElementById('notes').value;
		if(notes_v==''){
			notes_v='empty';
		}	

	
		var po_amount_v=document.getElementById('po_amount').value;
		if(po_amount_v=="" || po_amount_v==null){
			po_amount_v='0';
		}
		
	
		var pro_status_v=document.getElementById('pro_status').value;
		
		
		
		var pro_status_date=(document.getElementById('pro_status_date').value).trim();
		if(pro_status_date=="" || pro_status_date==null){
		pro_status_date="empty";
		}
		
		var fd_FDMgr_v=document.getElementById('fd_FDMgr').value;
		
		if(fd_FDMgr_v=="NODATA" || fd_FDMgr=="SELECT" || fd_FDMgr=="Select"){
			alert("Select FD Approval Manager");
			return false;
		}
		
	var data =	Project_name_v 			+ columnSeperator +
				Project_id_v 			+ columnSeperator +
				client_id_v				+ columnSeperator +
				fd_div_v 				+ columnSeperator +
				fd_start_date_v			+ columnSeperator +
				fd_end_date_v 			+ columnSeperator +
				notes_v					+ columnSeperator +
				crm_name_v				+ columnSeperator +
				crm_phone_v				+ columnSeperator +
				crm_email_v				+ columnSeperator +
				estimated_budget_v		+ columnSeperator + 
				bill_naration_v			+ columnSeperator +
				special_instruction_v	+ columnSeperator + 
				total_stores_v			+ columnSeperator +
				pro_status_v			+ columnSeperator + 
				pro_status_date 		+ columnSeperator +
				fd_FDMgr_v;
	
	var client_data	= 	
				client_manager_name_v	+ columnSeperator +
				client_manager_phone_v	+ columnSeperator + 
				client_manager_email_v	+ columnSeperator + 
				client_manager_po_v 	+ columnSeperator +
				po_date_v				+ columnSeperator +						
				po_amount_v;
			
	ajaxCheck();
//	alert("data is "+data+" client data is "+client_data);
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			   alert("PROJECT HEADER UPDATED");	
			
			   open_details(num);
		  }
		};
		
		//alert('test');
		ajaxRequest.open("POST","UpdateProjectHeader.jsp",true);
		ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		ajaxRequest.send("data="+data+"&client_data="+client_data+"&Project_id="+num);
		
	}
	
	function open_details(num) {
		//alert(num+"===num");
		parent.window.document.getElementById('header_label').innerHTML = "Project Page";
		
		ajaxcheck();
		ajaxRequest.onreadystatechange = function(){
	  		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				document.getElementById('main_content_element').innerHTML = ajaxRequest.responseText;
			}
		};

		ajaxRequest.open("GET","DetailsProject.jsp?id="+ num+"&flag=false",true);	
		ajaxRequest.send(null);		
	}

	
	
	
	//Edit Button Available Disable
	function editCRM(){
		//alert('edit CRM');
		document.getElementById('edit_buttons').style.display='none';
		document.getElementById('crm_name').removeAttribute('readonly');
		document.getElementById('crm_phone').removeAttribute('readonly');
		document.getElementById('crm_email').removeAttribute('readonly');
		document.getElementById('crm_notes').removeAttribute('readonly');
		
		document.getElementById('add_buttons').style.visibility='visible'; 
		 
	}
	
	
	function editPayment(){
		document.getElementById('edit_buttons').style.display='none';
		document.getElementById('prjct_approval').removeAttribute('disabled');
		document.getElementById('prjct_implt').removeAttribute('disabled');
		document.getElementById('prjct_dispatch').removeAttribute('disabled');
		document.getElementById('prjct_docking').removeAttribute('disabled');
		document.getElementById("update_buttons").style.visibility="visible";
	}

	//Search and filter Project
	function searchProject(){
		//alert('searchProject');
		var prjct_id=document.getElementById('search_project_id').value;
	//	alert('project_id'+prjct_id);
		
		ajaxcheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   //alert("under if");
			   var result = ajaxRequest.responseText;
			//   result = result.trim();
			//   alert("RESULT --->"+result);	   
			document.getElementById('search_project').innerHTML=ajaxRequest.responseText;
			     	      
		  }
		};


	//	alert("param----"+prjct_id);

		ajaxRequest.open("GET", "SearchProject.jsp?flag=searchProject&project="+prjct_id,true);	
		ajaxRequest.send(null); 
		
		
	}

	function filterProject(){
	//	alert('filterProject');
		
		var fd_client=document.getElementById('fd_client').value;
	//	alert('fd_client'+fd_client);
		
		var fd_div=document.getElementById('fd_div').value;
	//	alert('fd_div'+fd_div);
		
		ajaxcheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   //alert("under if");
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			  // alert("RESULT --->"+result);	   
			document.getElementById('filter_result').innerHTML=ajaxRequest.responseText;
			     	      
		  }
		};
		ajaxRequest.open("GET", "SearchProject.jsp?flag=filterProject&client="+fd_client+"&division="+fd_div,true);	
		ajaxRequest.send(null); 
	
	}
	
	function save_crm_details(num){
		
//		alert("Project save_crm_details --- >  "+num);
		
		var myForm=document.forms[0];
		
		var crm_name=myForm.crm_name.value;
		crm_name=crm_name.trim();
		
		if(crm_name==''){
			alert('NAME CAN NOT BE EMPTY !!');
			return false;
		}
		
		var letters = /^[A-Za-z]+$/;  
		if(crm_name.match(letters)|| crm_name.match('') )  
		{  	
			//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  
			alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN NAME. !!"); 
			  
			return false;  
		}  
		
		var crm_phone=myForm.crm_phone.value;
		crm_phone=crm_phone.trim();
		
		if(crm_phone==''){
			alert('PHONE NO CAN NOT BE EMPTY !!');
			return false;
		}
		
		
		if(isNaN(crm_phone)||crm_phone.indexOf(" ")!=-1)
        {
           alert("PHONE NO NEED TO BE NUMERIC VALUE !!");
           return false; 
        }
		
        if (crm_phone.length<10)
        {
             alert("PHONE NO NEED TO HAVE 10 NUMBER ATLEAST !!");
             return false;
        }
       

		
		
		var crm_email=myForm.crm_email.value;
		crm_email=crm_email.trim();
		
		if(crm_email==''){
			alert('EMAIL CAN NOT BE EMPTY !!');
			return false;
		}
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
		if(crm_email.match(mailformat))  
		{  
		//document.form1.text1.focus();  
		//return true;  
		}  
		else  
		{  
			alert("YOU HAVE ENTEREDE AN INVALID EMAIL ADDRESS !!");  
		//document.form1.text1.focus();  
			return false;  
		}  
		
		
		
		var crm_notes=myForm.crm_notes.value;
		crm_notes=crm_notes.trim();
		if(crm_notes==''){
			alert('CRM NOTES CAN NOT BE EMPTY !!');
			return false;
		}
		
		ajaxCheck();

	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   //alert("under if");
		   var result = ajaxRequest.responseText;
		   result = result.trim();
	//	   alert("RESULT save_crm_details--->"+result);	   
		   //window.location.reload(true);
		//document.getElementById('maincontent_element').innerHTML=
		   
	//	   alert('Calling project deatilas'+num);
		   project_details(num);
		     	      
	  }
	};

	var param=crm_name+"@,@"+crm_phone +"@,@"+crm_email +"@,@"+crm_notes+"@,@"+num;
	//alert("param --- >"+param);
	ajaxRequest.open("GET", "UpdateCrm.jsp?param="+param,true);	
	ajaxRequest.send(null); 
		
	}
	
	
	function project_approval(id){
	//	alert("Project Approval --- >  "+id);
	
		ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		
		  parent.window.document.getElementById('header_label').innerHTML="Project Approval";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		  /* var result = ajaxRequest.responseText;
		   result = result.trim();
		   alert("RESULT --->"+result);	   */
		//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		     	      
	  }
	};
	
	ajaxRequest.open("GET", "ProjectApproval.jsp?id="+id,true);	
	ajaxRequest.send(null); 
}
	
		
	function project_term_days(id){
	//	alert("call_term_days --- >  "+id);
		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		  
		  parent.window.document.getElementById('header_label').innerHTML="Project PaymentTerm";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		  /* var result = ajaxRequest.responseText;
		   result = result.trim();
		   alert("RESULT --->"+result);	   */
		//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		     	      
	  }
	};

	
	ajaxRequest.open("GET", "ProjectPaymentTerm.jsp?id="+id,true);	
	ajaxRequest.send(null); 
		
	}

	function ProjectScopeDisplay(id){
		//alert(id);
		window.location.href="ProjectScopeDisplay.jsp?id="+id;
		
	}
	
	
	
	
function project_details(id){
		
	ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		 // alert("response project_details"+ajaxRequest.responseText);
		 parent.window.document.getElementById('header_label').innerHTML="Project CRM Details";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		  /* var result = ajaxRequest.responseText;
		   result = result.trim();
		   alert("RESULT --->"+result);	   */
		//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
	  }
	};
	//alert("project_details calling --- >  "+id);
	ajaxRequest.open("GET", "ProjectDetails.jsp?id="+id,true);	
	ajaxRequest.send(null); 	
}
	
	function project_document(id){
		
	//	alert("project_document calling --- >  "+id);		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	//	  alert("response project_document");
		  parent.window.document.getElementById('header_label').innerHTML="Project Document";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		 
		     	      
	  }
	};
	
	ajaxRequest.open("GET", "ProjectDocument.jsp?id="+id,true);	
	ajaxRequest.send(null); 
	}
	
	function project_indent(id){
	//	alert("project_indent calling --- >  "+id);		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	//	  alert("response project_indent");
		  parent.window.document.getElementById('header_label').innerHTML="Project Indent";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		  /* var result = ajaxRequest.responseText;
		   result = result.trim();
		   alert("RESULT --->"+result);	   */
		//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		     	      
	  }
	};
	
	ajaxRequest.open("GET", "ProjectIndent.jsp?id="+id,true);	
	ajaxRequest.send(null); 
		
	}
	


	function project_accountablity(id){
	//	alert("project_accountablity calling --- >  "+id);		
			ajaxCheck();
		
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		//	  alert("response project_accountablity");
			  parent.window.document.getElementById('header_label').innerHTML="Project Accountability";
			  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
			  /* var result = ajaxRequest.responseText;
			   result = result.trim();
			   alert("RESULT --->"+result);	   */
			//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
			     	      
		  }
		};
		
		ajaxRequest.open("GET", "ProjectAccountablity.jsp?id="+id,true);	
		ajaxRequest.send(null); 
	}

	function project_national_date_plan(id){
		
	//	alert("project_national_date_plan calling --- >  "+id);		
		ajaxCheck();

	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		//  alert("response project_national_date_plan");
		  parent.window.document.getElementById('header_label').innerHTML="Project Natioanal Date Plan";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		  /* var result = ajaxRequest.responseText;
		   result = result.trim();
		   alert("RESULT --->"+result);	   */
		//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		     	      
	  }
	};

	ajaxRequest.open("GET", "ProjectNationalDatePlan.jsp?id="+id,true);	
	ajaxRequest.send(null); 
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
			alert('PROJECT APPROVAL DATE IS NOT VALID DATE !!');
			return false;
		}
		
		
		var date_prjct_implt=mytermForm.prjct_implt.value;
		date_prjct_implt=date_prjct_implt.trim();
		if(date_prjct_implt.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT IMPLEMENTATION DATE IS NOT VALID DATE !!');
			return false;
		}

		var date_prjct_dispatch=mytermForm.prjct_dispatch.value;
		date_prjct_dispatch=date_prjct_dispatch.trim();
		
		if(date_prjct_dispatch.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT DISPATCH DATE IS NOT VALID DATE !!');
			return false;
		}

		
		var date_prjct_docking=mytermForm.prjct_docking.value;
		date_prjct_docking=date_prjct_docking.trim();
		
		if(date_prjct_docking.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT APPROVAL DATE IS NOT VALID DATE !!');
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
			alert('PROJECT APPROVAL DATE IS NOT VALID DATE !!');
			return false;
		}
		
		var date_prjct_implt=mytermForm.prjct_implt.value;
		date_prjct_implt=date_prjct_implt.trim();
		
		if(date_prjct_implt.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT IMPLEMENTATION DATE IS NOT VALID DATE !!');
			return false;
		}

		var date_prjct_dispatch=mytermForm.prjct_dispatch.value;
		date_prjct_dispatch=date_prjct_dispatch.trim();
		
		if(date_prjct_dispatch.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT DISPATCH DATE IS NOT VALID DATE !!');
			return false;
		}
		
		var date_prjct_docking=mytermForm.prjct_docking.value;
		date_prjct_docking=date_prjct_docking.trim();
		
		if(date_prjct_docking.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
		{
		  //alert('DATE ACCEPTABLE');
		}
		else{
			alert('PROJECT APPROVAL DATE IS NOT VALID DATE !!');
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

	
	function cancelElement(num){
		//alert('Cancel Element');
		window.location.href='ProjectScopeOfWork.jsp?id='+num;
	}
	
	
	function save_Elementdetails(num){
		
	//	alert ("Save-DetailsCalled : Project ID--->"+num);
		
		
		var set_as_hold=document.getElementById('set_as_hold_u').value;
		/**var prjct_id= document.getElementById('projectNo').value;
		prjct_id=prjct_id.trim();
		console.log("projectno"+prjct_id);
		if(prjct_id==''){
			alert('Please Enter Project Number !!');
			document.getElementById('projectNo').focus;
			return false;
		}
		if(prjct_id==null){
			alert('Project Number can\'t be null !!');
			return false;
		}**/
		
		var prjct_name=document.getElementById('projectName').value;
		prjct_name=prjct_name.trim();
		if(prjct_name==''){
			alert('Please Enter Project Name !!');
			document.getElementById('projectName').focus;
			return false;
		}
		
	
	
		var brand_name=document.getElementById('brand_u').value;;
		brand_id=brand_name.trim();
		console.log("brand_id=="+brand_id);
		
		
		var element_name=document.getElementById('element_u').value;;
		element_name=element_name.trim();
		console.log(element_name+"==element_name");
		
		var component_name=document.getElementById('component_u').value;;
		component_name=component_name.trim();
		console.log("component_name=="+component_name);
			
		var material_name=document.getElementById('material_u').value;;
		material_name=material_name.trim();
		console.log("material_name=="+material_name);
		
		var quantity=document.getElementById('quan').value;
		 quantity=quantity.trim();
		console.log("quantity==="+quantity);
		if(quantity==""){
			//alert("QUANTITY CAN'T BE EMPTY OR ZERO !!");
			quantity=0;
			//return false;
		}
		if(quantity<0){
			alert("QUANTITY CAN'T BE NEGATIVE !!");
			
			return false;
		}
		
//		if(isNaN(quantity)||quantity.indexOf(" ")!=-1)
		/*if(isNaN(quantity)||quantity.indexOf(" ")!=-1)
        {
           alert("QUANTITY NEED TO BE NUMERIC VALUE !!");
          
          return false; 
        }*/
		var print_mode_name=document.getElementById('print_u').value;
		print_mode_name=print_mode_name.trim();
	//alert("print_mode_name=="+print_mode_name);
		
		
	
		ajaxCheck();
	
		ajaxRequest.onreadystatechange = function(){
			console.log("ready state="+ajaxRequest.readyState);
			console.log("responseText="+ajaxRequest.responseText);
			console.log("responseXML="+ajaxRequest.responseXML);
			console.log("status="+ajaxRequest.status);
			console.log("statue="+ajaxRequest.statusText);
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				 
				   var result = ajaxRequest.responseText;
				   result = result.trim();
				   
				   if(result.trim()=="DATA_INSERTED"){
						alert('ELEMENT INSERTED FOR PROJECT !!');
						window.location.href='ProjectScopeOfWork.jsp?id='+num;
						
				   }else if(result.trim()=="DATA_INSUFFICIENT"){
					   
					   alert("DATA INSUFFICIENT");
				   }
					  else if(result.trim()=="DATA_NOT_INSERTED")
				 {
						   alert('ELEMENT NOT INSERTED FOR Project !!');
						   if(confirm("Do YOU WANT TO GO BACK ")){
								  window.location.href='ProjectScopeOfWork.jsp?id='+num;
							  }
						   else{
							   return false;
						   }
						   
				  }
					  else if(result.trim()=="PROJECT CODE ALREADY EXIST !!")
						 {
						   alert('PROJECT CODE ALREADY EXIST !!\n PLEASE ENTER ANOTHER PROJECT CODE');
						   /*if(confirm("Do YOU WANT TO GO BACK ")){
								  window.location.href='ProjectScopeOfWork.jsp?id='+num;
							  }*/
				  }
				 
			
				     	      
			  }
			};
//alert('Component name' + component_name);
			
			
			ajaxRequest.open("POST", "InsertScopeOfWork.jsp?id="+num+"&brand_name="+brand_name+"&element_name="+element_name
					+"&component_name="+component_name+"&material_name="+material_name+"&quantity="+quantity
					+"&print_mode_name="+print_mode_name+"&set_as_hold="+set_as_hold+
					"&prjct_name="+prjct_name,false);	
			
			ajaxRequest.send(null);
		
	}
	
	
	/*function save_details(num){
		
		alert ("Save-DetailsCalled : Project ID--->"+num);
		
		var myForm=document.forms[0];
		
		
		
		var brand_name=myForm.brand_id.value;
		brand_id=brand_name.trim();
		
		
		var element_name=myForm.element_id.value;
		element_name=element_name.trim();
		
		
		var component_name=myForm.component_id.value;
		component_name=component_name.trim();
			
		var material_name=myForm.material_id.value;
		material_name=material_name.trim();
		
		var quantity=myForm.qty.value;
		quantity=quantity.trim();
		
		var depot_name=myForm.depot_id.value;
		depot_name=depot_name.trim();
		
		var print_mode_name=myForm.print_mode_id.value;
		print_mode_name=print_mode_name.trim();
		
		
		ajaxCheck();
		//Checking Check BOX
		var checked_position='';
		var date_plan='';
		var set_as_hold=''; 
		var count=0;
		if(confirm("Are You SURE OF SCOPE OF WORK")) 
		{	
				for(var i=0; i<myForm.chckbox.length ; i++)
				{
					if(myForm.chckbox[i].checked)
					{
						checked_position=checked_position+myForm.chckbox[i].value+",";
						if(myForm.chck_hold[i].checked)
						{
						set_as_hold=set_as_hold+myForm.chck_hold[i].value+",";
						}
						date_plan=date_plan+
						document.getElementById('client_planned_start_date'+(i+1)).value+","
						+document.getElementById('client_planned_end_date'+(i+1)).value+","
						+document.getElementById('client_actual_start_date'+(i+1)).value+","
						+document.getElementById('client_actual_end_date'+(i+1)).value+","
						+document.getElementById('fd_planned_start_date'+(i+1)).value+","
						+document.getElementById('fd_planned_end_date'+(i+1)).value+","
						+document.getElementById('fd_actual_start_date'+(i+1)).value+","
						+document.getElementById('fd_actual_end_date'+(i+1)).value+"@!@"
						;
						//alert("date_plan for"+(i+1)+"----> "+date_plan);
						alert("Scope which are set as hold"+set_as_hold);
						
						count++;
						
					}
				}
				
				alert("count Check Box--->"+count);
				alert("checked_position--->"+checked_position);
				alert("date_plan ----> "+date_plan);
		}
		else
		{
			alert("count Check Box in false--->"+count);
			return false;
		}
		
		//alert("Brand Name"+brand_name+ "  element name"+element_name+" component name"+component_name);
		//alert("\n Material_name"+material_name+quantity+depot_name+print_mode_name+"\nDate Plan"+date_plan);
		
		ajaxRequest.onreadystatechange = function(){
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				 
				   var result = ajaxRequest.responseText;
				   result = result.trim();
				   alert("RESULT --->"+result);
				   window.location.href='ProjectScopeOfWork.jsp?id='+num;
				//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
				     	      
			  }
			};

			ajaxRequest.open("POST", "InsertScopeWork.jsp?id="+num+"&brand_name="+brand_name+"&element_name="+element_name
					+"&component_name="+component_name+"&material_name="+material_name+"&quantity="+quantity
					+"&depot_name="+depot_name+"&print_mode_name="+print_mode_name+"&checkeditem="+checked_position
					+"&date_plan="+date_plan+"&scope_set_as_hold="+set_as_hold,true);	
			ajaxRequest.send(null);
		
	}*/
	
	

	
	
/*function project_scope_work(id){
		alert('yyyyyyyy');
		alert("project_scope_work calling --- >  "+id);		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
		
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		  alert("resp project_scope_work");
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		 
		     	      
	  }
	};
	
	ajaxRequest.open("POST","project_scope_work.jsp?id="+id,true);	
	ajaxRequest.send(null); 
}*/



function store_project_manage_list(id){
	
	//alert("store_project_date_list calling --- >  "+id);		
	ajaxCheck();

// Create a function that will receive data 
// sent from the server and will update
// div section in the same page.
ajaxRequest.onreadystatechange = function(){
  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	//  alert("response store_project_date_list");
	  parent.window.document.getElementById('header_label').innerHTML="Store Management";
	  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
	  /* var result = ajaxRequest.responseText;
	   result = result.trim();
	   alert("RESULT --->"+result);	   */
	//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
	     	      
  }
};

ajaxRequest.open("GET", "StoreProjectList.jsp?id="+id,true);	
ajaxRequest.send(null); 
}


function deleteElements(id){
	
	
	var checked_position='';
	var count=0;
	if(confirm("Do You Want Delete")) 
	{	
		var chks = document.getElementsByName('chkbox[]');
		var hasChecked = false;
		for (var i = 0; i < chks.length; i++)
		{
			//alert('inside for'+i);
			if (chks[i].checked)
			{			
				checked_position=checked_position+chks[i].value.trim()+',';
				//alert(chks[i].value);
				hasChecked = true;		
			}
		}
		
	if (hasChecked == false)
	{
		alert("PLEASE SELECT AT LEAST ONE FOR DELETE !!");
		return false;
	}
				
		
			/*for(var i=1; i<=myElementDisplayForm.chkbox.length ; i++)
			{
				alert('i='+i);
				if(myElementDisplayForm.chkbox[i].checked)
				{
					alert(myElementDisplayForm.chkbox[i]);
					
					checked_position=checked_position+myElementDisplayForm.chkbox[i].value.trim()+',';
					count++;
				}
			}*/
			//alert("checked_position---> "+checked_position);
		//	alert("count Check Box--->"+count);
		
	}
	
	ajaxCheck();	
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			 //  alert("RESULT --->"+result);
			   if(result.match('DATA DELETED')){
				  // alert('ELEMENT DELETED !!');
			   }
			   if(result.match('DATA NOT DELETED')){
				   alert('ELEMENT NOT DELETED !!');
			   }
			   
			   if (result.match('FOREIGN_KEY')) {
//				   alert('ELEMENT RELATED TO BOM CANNOT BE DELETED !!');
				   alert('ELEMENT CANNOT BE DELETED !!');
				
			}
			   window.location.href="ProjectScopeOfWork.jsp?id="+id;
			  
	}
};

		ajaxRequest.open("GET", "DeleteElementOFProject.jsp?checked_position="+checked_position,true);	
		ajaxRequest.send(null); 	
	
	
}




/*
 * 			Save Approval Details
 * 
 */

function editAppprovalRecord(){
	
	
}


function save_approvalDetails(Project_id){
	
	
	//alert('save_approvalDetails  -->'+Project_id);
	
	var client_approval_date=myApprovalForm.client_approval_date.value;
	client_approval_date=client_approval_date.trim();
	
	var status_client=myApprovalForm.status_client.value;
	status_client=status_client.trim();
	
	var client_approval_comment=myApprovalForm.client_approval_comment.value;
	client_approval_comment=client_approval_comment.trim();
	
	var fd_approval_date=myApprovalForm.fd_approval_date.value;
	fd_approval_date=fd_approval_date.trim();
	
	var status_fd=myApprovalForm.status_fd.value;
	status_fd=status_fd.trim();
	
	var fd_approval_comment=myApprovalForm.fd_approval_comment.value;
	fd_approval_comment=fd_approval_comment.trim();

	
	ajaxCheck();	
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			   alert(result);
		  }
	};

	ajaxRequest.open("POST","InsertApprovalProject.jsp",true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.send("client_approval_date="+client_approval_date+"&status_client="+status_client
		+"&client_approval_comment="+client_approval_comment+"&fd_approval_date="+fd_approval_date
		+"&status_fd="+status_fd+"&fd_approval_comment="+fd_approval_comment+
		"&Project_id="+Project_id); 	
		
}


function getElementDataTable(id){
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result=result.trim();
			   document.getElementById('cover_ele_previous').innerHTML=result;
			   	      getElementTableproperty();
		  }
		};
	ajaxRequest.open("GET", "ProjectElementTable.jsp?id="+id,true);	
	ajaxRequest.send(null); 	
	
}