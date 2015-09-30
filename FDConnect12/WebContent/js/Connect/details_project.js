	function date()
	{
		//alert('Date');
		
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
	
	
	function dateid()
	{
		
		new JsDatePick({
			useMode:2,
			target:'indent_create_date',
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

	function editCRM(){
		//alert('edit CRM');
		document.getElementById('edit_buttons').style.display='none';
		document.getElementById('crm_name').removeAttribute('readonly');
		document.getElementById('crm_phone').removeAttribute('readonly');
		document.getElementById('crm_email').removeAttribute('readonly');
		document.getElementById('crm_notes').removeAttribute('readonly');
		//document.getElementById('edit_buttons').style.visiblity='true';
		document.getElementById('add_buttons').style.visibility='visible'; 
		 
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
	
	
	
	function save_crm_details(num){
		
var myForm=document.forms[0];
		
		var crm_name=myForm.crm_name.value;
		crm_name=crm_name.trim();
		
		if(crm_name==''){
			alert('NAME CAN NOT BE EMPTY');
			return false;
		}
		
		var letters = /^[A-Za-z]+$/;  
		if(crm_name.match(letters) || crm_name.match('') )  
		{  	
			//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  
			alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN NAME."); 
			  
			return false;  
		}  
		
	  /*   
	    var spclChars = "!@#$%^&*()_-./\+"; // specify special characters 
	  
	  
	    for (var i = 0; i < crm_name.length; i++) 
	    { 
	    	if (spclChars.indexOf(crm_name.charAt(i)) != -1) 
	    	{ 
	    		alert ("SPECIAL CHARACTERS ARE NOT ALLOWED."); 
	    		return false; 
	    	} 
	    } */
	    
		
		var crm_phone=myForm.crm_phone.value;
		crm_phone=crm_phone.trim();
		
		if(crm_phone==''){
			alert('PHONE NO CAN NOT BE EMPTY');
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
			alert('EMAIL CAN NOT BE EMPTY');
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
			alert("YOU HAVE ENTEREDE AN INVALID EMAIL ADDRESS!");  
		//document.form1.text1.focus();  
			return false;  
		}  
		
		
		
		var crm_notes=myForm.crm_notes.value;
		crm_notes=crm_notes.trim();
		if(crm_notes==''){
			alert('CRM NOTES CAN NOT BE EMPTY');
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
		//alert("Project Approval --- >  "+id);
	
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
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
	
	function project_details(id){
		
		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		// alert("response project_details");
		 parent.window.document.getElementById('header_label').innerHTML="Project CRM Details";
		 document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;     	      
	  }
	};
	//alert("project_details calling --- >  "+id);
	ajaxRequest.open("GET", "ProjectDetails.jsp?id="+id,true);	
	ajaxRequest.send(null); 	
}
	
	function project_document(id){
		
		//alert("project_document calling --- >  "+id);		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		  //alert("response project_document");
		  parent.window.document.getElementById('header_label').innerHTML="Project Document";
		  document.getElementById('maincontent_element').innerHTML = ajaxRequest.responseText;
		  /* var result = ajaxRequest.responseText;
		   result = result.trim();
		   alert("RESULT --->"+result);	   */
		//document.getElementById('map_data').innerHTML=ajaxRequest.responseText;
		     	      
	  }
	};
	
	ajaxRequest.open("GET", "ProjectDocument.jsp?id="+id,true);	
	ajaxRequest.send(null); 
	}
	
	function project_indent(id){
		//alert("project_indent calling --- >  "+id);		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		 // alert("response project_indent");
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


function project_accountablity(id){
		//alert("project_accountablity calling --- >  "+id);		
		ajaxCheck();
	
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		//  alert("response project_accountablity");
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
