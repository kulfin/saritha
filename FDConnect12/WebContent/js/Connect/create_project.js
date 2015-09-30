/**
 * Callender
 */
	function date()
	{
		
		new JsDatePick({
			useMode:2,
			target:"fd_start_date",
			dateFormat:"%Y-%m-%d"
			/*selectedDate:{				This is an example of what the full configuration offers.
				day:5,						For full documentation about these settings please see the full version of the code.
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			cellColorScheme:"beige",
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
		new JsDatePick({
			useMode:2,
			target:"fd_end_date",
			dateFormat:"%Y-%m-%d"
			/*selectedDate:{				This is an example of what the full configuration offers.
				day:5,						For full documentation about these settings please see the full version of the code.
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			cellColorScheme:"beige",
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
		
	}

/**
 * 
 */

function create_project(){
	
	//alert("inside create_project() ");
	
	var myForm = document.forms[0];
	
	var project_id_v = myForm.project_id.value;
	project_id_v = project_id_v.trim();
	if(project_id_v===''){
		alert("Project NO CAN'T BE EMPTY");
		return false;
	}
	
		
	var project_name_v = myForm.project_name.value;
	project_name_v = project_name_v.trim();
	if(project_name_v===''){
		alert("Project NAME CAN'T BE EMPTY");
		return false;
	}
	
	
	var client_id_v = myForm.client_id.value;
	client_id_v = client_id_v.trim();
	if(client_id_v==='' || client_id_v==='SELECT'){
		alert("CLIENT NEED TO BE SELECTED");
		return false;
	}
	
	var fd_div_v = myForm.fd_div.value;
	fd_div_v = fd_div_v.trim();
	if(fd_div_v==='' || fd_div_v==='SELECT'){
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
	   if(result.trim()==="DATA_INSERTED"){
		alert('Project CREATED !!');
		myForm.project_id.value="";
		myForm.project_name.value="";
		myForm.fd_start_date.value="";
		myForm.fd_end_date.value="";
	   }
	   else if(result.trim()==="DATA_NOT_INSERTED")
	   {
		   alert('Project NOT CREATED !!');
	   }
	     	      
  }
};


var param=project_id_v+","+project_name_v+","+client_id_v+","+fd_div_v+","+fd_start_date_v+","+fd_end_date_v;

//alert("param----"+param);

ajaxRequest.open("GET", "InsertProject.jsp?queryString="+param,true);	
ajaxRequest.send(null); 
}