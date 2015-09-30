/**
 * 
 */
var ajaxRequest;  
function ajaxcheck(){
	 try
	 {
	   // Opera 8.0+, Firefox, Safari
	   ajaxRequest = new XMLHttpRequest();
	 }
	 catch(e)
	 {
	   // Internet Explorer Browsers
	   try
	   {
	      ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
	   }
	   catch(e)
	   {
	      try
	      {
	         ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
	      }
	      catch(e)
	      {
	         // Something went wrong
	         alert("Your browser broke!");
	         return false;
	      }
	   }
	 }
}


function  getFDMgr_Name(){
	//alert("get fd manage name----connect");
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

function project_search_name(){
	
	//alert('project_search_name');
	/*var x = document.createElement('script');
	x.src = 'jquery-1.8.3.js';
	document.getElementsByTagName("head")[0].appendChild(x);*/
	
	var availableTags;
	try
	 {
	   // Opera 8.0+, Firefox, Safari
	   ajaxRequest = new XMLHttpRequest();
	 }
	 catch(e)
	 {
	   // Internet Explorer Browsers
	   try
	   {
	      ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
	   }
	   catch(e)
	   {
	      try
	      {
	         ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
	      }
	      catch(e)
	      {
	         // Something went wrong
	         alert("Your browser broke!");
	         return false;
	      }
	   }
	 } 
	 
 	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 	var result = ajaxRequest.responseText;
			 	availableTags =result.trim();
				//alert(availableTags);   
				   $(function() {
					   var args=availableTags.split("#&#");
					  // alert("args"+args);
					$("#search_project_name").autocomplete({
				            source: args
				        });
				    });
				   //document.getElementsByTagName("head")[0].removeChild(x);
				  
		 }
};
		
	//alert('Before Response');
	ajaxRequest.open("GET","ComboDropDown.jsp?param=projectname", true);	
	ajaxRequest.send(null); 
}

function setTable(){
	//alert('setTable');
	$(document).ready(function() {
		$('#example').dataTable();
	} );
}



function searchProject(){
	//alert('searchProject');
	var prjct_id=document.getElementById('search_project_name').value;
	//alert('project_id'+prjct_id);
	
	ajaxcheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   //alert("under if");
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		//   alert("RESULT --->"+result);	   
		document.getElementById('search_project').innerHTML=ajaxRequest.responseText;
		     	      
	  }
	};


	//alert("param----"+prjct_id);

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


function search_projects(){
	var myForm = document.forms[0];
	var txt_1 ;
	ajaxcheck();

	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   //alert("under if");
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			//  alert("RESULT --->"+result);	   
			document.getElementById('searched_result').innerHTML=ajaxRequest.responseText;
			 setTable();     	      
		  }
		};
	
	
	if(document.forms[0].id_1.checked==true){
		//alert('radio 1');
		txt_1 = myForm.search_project_name.value;
		//alert('radio 1 ---'+txt_1);
		
		if(txt_1===""){
			alert("Enter Project Name");
			return false;
		}
		ajaxRequest.open("GET", "SearchProject.jsp?flag=search_project_id&search_project_id="+txt_1,true);	
		ajaxRequest.send(null); 
	}
	else if(document.forms[0].id_2.checked==true){
		//alert('radio 2');
		txt_1 = myForm.search_client.value;
		//alert('radio 2'+txt_1);
		if(txt_1===""){
			alert("Enter Client Name");
			return false;
		}
		ajaxRequest.open("GET", "SearchProject.jsp?flag=search_client&search_client="+txt_1,true);	
		ajaxRequest.send(null);
		
	} else if(document.forms[0].id_3.checked==true){
	//	alert('radio 3');
		
		txt_1 = myForm.search_division.value;
		//alert('radio 3'+txt_1);
		if(txt_1===""){
			alert("Enter Division Name");
			return false;
		}
		
		ajaxRequest.open("GET", "SearchProject.jsp?flag=search_division&search_division="+txt_1,true);	
		ajaxRequest.send(null);
		
	}
}

function disable_radio(){
	//search_project_id
	document.getElementById("search_client").disabled=true;
	document.getElementById("search_division").disabled=true;
	
}

function radio_test(){
	//alert('radio_test');
	//var myForm = document.forms[0];
	
	//var txt_1 = myForm.search_project_id.value;
	//var txt_2 = myForm.search_client.value;
	//var txt_3 = myForm.search_division.value;
	
	// alert(txt_1 +''+txt_2+' '+txt_3);
	
	//var id_1 = myForm.btn1.value;
	//var id_2 = myForm.btn1.value;
	//var id_3 = myForm.btn1.value;
	
	//alert(id_1 +''+id_2+' '+id_3);

	if(document.forms[0].id_1.checked==true){
		
		//search_project_id
		//document.getElementById("search_client").disabled=true;
		//document.getElementById("search_division").disabled=true;
		//alert('testttt');
		//document.getElementById("search_client").value='';
		//document.getElementById("search_division").value='';
		document.getElementById("search_project_name").disabled=false;
		document.getElementById("search_client").disabled=true;
		document.getElementById("search_division").disabled=true;
		document.forms[0].id_2.disabled=false;
		document.forms[0].id_3.disabled=false;
		
		}
	else	
	if(document.forms[0].id_2.checked==true){
		//alert('tet');
			document.getElementById("search_project_name").value='';
			//document.getElementById("search_division").value='';
			document.getElementById("search_client").disabled=false;
		    document.getElementById("search_project_name").disabled=true;
		    document.getElementById("search_division").disabled=true;
			document.forms[0].id_1.disabled=false;
			document.forms[0].id_3.disabled=false;
		}
	else
	if(document.forms[0].id_3.checked==true){
		//alert('tetsssss');
			document.getElementById("search_project_name").value='';
			//document.getElementById("search_client").value='';
			document.getElementById("search_project_name").disabled=true;
		    document.getElementById("search_client").disabled=true;
		    document.getElementById("search_division").disabled=false;
			document.forms[0].id_1.disabled=false;
			document.forms[0].id_2.disabled=false; 	
		}
	
}


