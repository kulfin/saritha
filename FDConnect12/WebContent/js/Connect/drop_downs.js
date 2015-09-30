
	var ajaxRequest = null;
	function ajaxCheck() {
		// The variable that makes Ajax possible!		
		try {
			// Opera 8.0+, Firefox, Safari
			ajaxRequest = new XMLHttpRequest();
		} catch (e) {
			alert('Internet Explorer Browsers');
			// Internet Explorer Browsers
			try {
				ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				alert('Internet Explorer Browsers2');
				try {
					ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert('Internet Explorer Browsers3');
					// Something went wrong
					// // alert("Your browser broke!");
					return false;
				}
			}
		}
	}

function project_search_name(){
	
	var availableTags;
	ajaxCheck();
	
/*	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
			 	availableTags =result.trim();
				//alert(availableTags);   
				   $(function() {
					   var args=availableTags.split("#&#");
					//   alert("args"+args);
					$("#search_project_id").autocomplete({
				            source: args
				        });
				    });
		 }
		};*/
			
		ajaxRequest.open("GET", "ComboDropDown.jsp?param=projectname", true);	
		ajaxRequest.send(null);
}	

function project_search_by_client(){
	
	var availableTags;
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
			 	availableTags =result.trim();
				   
				   $(function() {
					   var args=availableTags.split("#&#");
					//   alert("args"+args);
					$( "#search_client" ).autocomplete({
				            source: args
				        });
				    });
		 }
		};
			ajaxRequest.open("GET", "ComboDropDown.jsp?param=client", true);	
			ajaxRequest.send(null);
}

function project_search_by_division(){
	var availableTags;
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
			 	availableTags =result.trim();
				   
				   $(function() {
					   var args=availableTags.split("#&#");
					//   alert("args"+args);
					$( "#search_division" ).autocomplete({
				            source: args
				        });
				    });
		 }
		};
			ajaxRequest.open("GET", "ComboDropDown.jsp?param=division", true);	
			ajaxRequest.send(null);
}
	
function drop_region(){
	//// // alert('drop region');
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
		  	// alert('response drop_down' +result);
				document.getElementById('Region_id').innerHTML=ajaxRequest.responseText;
		   
		 }
		};
			ajaxRequest.open("GET", "DropDowns.jsp?param=region", true);	
			ajaxRequest.send(null);
}



function drop_state(){
	//('drop_state');
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
		  // alert('response drop_down' +result);
				document.getElementById('State_id').innerHTML=ajaxRequest.responseText;
		   
		 }
		};

		ajaxRequest.open("GET", "DropDowns.jsp?param=state", true);	
		ajaxRequest.send(null);
	
	
}
function drop_trade(){
	//// alert('drop_trade');
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		var result = ajaxRequest.responseText;
		  		//// alert('response drop_down' +result);
		document.getElementById('Trade_id').innerHTML=ajaxRequest.responseText;
		   
		 }
		};
		ajaxRequest.open("GET", "DropDowns.jsp?param=trade", true);	
		ajaxRequest.send(null);
	
	
}

function drop_chain(){
	//// alert('drop_chain');
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
		  		//// alert('response drop_down' +result);
				document.getElementById('Chain_id').innerHTML=ajaxRequest.responseText;
		   
		 }
		};

		ajaxRequest.open("GET", "DropDowns.jsp?param=chain", true);	
		ajaxRequest.send(null);
}

function drop_city(){
	//// alert('drop_city');
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
		  	//	// alert('response drop_down' +result);
				document.getElementById('City_id').innerHTML=ajaxRequest.responseText;
		   
		 }
		};

		ajaxRequest.open("GET", "DropDowns.jsp?param=city", true);	
		ajaxRequest.send(null);
}

function drop_down_fdhub(id){
	//// alert('drop_down_fdhub');
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
		  
				document.getElementById('td_fd_hub_list'+id).innerHTML=ajaxRequest.responseText;
		   
		 }
		};

		ajaxRequest.open("GET", "DropDowns.jsp?param=fdhub", true);	
		ajaxRequest.send(null);
	
}

function drop_down_fdemployee(id){
	// // alert('drop_down_fdemployee');
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
		  		// // alert('response drop_down---->' +'td_resopnsible_list'+id);
				document.getElementById('td_resopnsible_list'+id).innerHTML=ajaxRequest.responseText;
		   
		 }
		};
		
		ajaxRequest.open("GET", "DropDowns.jsp?param=fdemp", true);	
		ajaxRequest.send(null);
	
}