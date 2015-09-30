var columnSeperator="@!@";

function date_measured_on(){

	//document.getElementById('measured_on_u').value='';
	new JsDatePick({
		useMode:2,
		target:"measured_on_u",
		dateFormat:"%d/%m/%Y"
		}); 	
	}



function showAdd(){
	
	
	document.getElementById('measured_items').style.display="none";
	document.getElementById('add_measurment').style.display="block";
	document.getElementById('update_measurment').style.display="none";
	getElementStatus();
}

function cancel_measurement_add(){
	
	document.getElementById('measured_items').style.display="block";
	document.getElementById('add_measurment').style.display="none";
	document.getElementById('update_measurment').style.display="none";
}

function cancel_measurement_update(){
	
	document.getElementById('measured_items').style.display="block";
	document.getElementById('add_measurment').style.display="none";
	document.getElementById('update_measurment').style.display="none";
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
/*
*	Location Filtering
*/

function replaceStatus(){
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		//   alert("RESULT replaceStatus --->"+result);
		  	   
		   document.getElementById('replace_measure_status').innerHTML=result;
	  }
	};

	ajaxRequest.open("GET", "DropDowns.jsp?param=measurementStatus",true);	
	ajaxRequest.send(null); 	
}


function getRegionOnCountry(){
	
	//alert('selectCountry  in jobCardMeasurement.js');
	var Country=document.getElementById('country_select').value;
	//alert('selectCountry'+Country);
	if(Country==='SELECT'){
		alert('SELECT COUNTRY !!');
	//	return false;
	}
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT region --->"+result);		 
		   document.getElementById('select_region').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=country&data="+Country,true);	
	ajaxRequest.send(null); 
}

function getElementStatus(){
	
	currentMeasurementDate();
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT region --->"+result);		 
		   document.getElementById('filter_element_status').innerHTML=result;
		   document.getElementById('status_id_u').innerHTML=result;
		   
		   var eStatus = document.getElementById('filter_element_status');
		   for (var i = 0; i <eStatus.options.length; i++) {
			    if('NA'==(eStatus.options[i].text)){
			    	eStatus.options[i].selected= true;
			    }
		   }
		   
		   
		   
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=element_status",true);	
	ajaxRequest.send(null); 
}

function currentMeasurementDate(){
	document.getElementById('measured_on_u').value='';
	var currentDate = new Date();
	var day = currentDate.getDate();
	var month = currentDate.getMonth()+1;

	var year = currentDate.getFullYear();
	if(month<10){
		month=('0'+month);
		
	}
	
	if(day<10){
		day=('0'+day);
		
	}
document.getElementById('measured_on_u').value = day +"/"+ month +"/" + year.toString().substring(2) ;
}

function updateStoreDetail(){
	
	//alert('update store detail');
	
	
	var e=document.getElementById('visit_store_name');
    var storeId = e.options[e.selectedIndex].value;
    
	if(storeId=="SELECT")
	{
		alert("Please select the store");
		return;
	}

	var f=	document.getElementById('fd_hub_select');
	var hubName = f.options[f.selectedIndex].value; 
	
	if(hubName=="SELECT")
	{
		alert("Please select the hub");
		return;
	}
	
	var address=document.getElementById('address').value;
	var contactName=document.getElementById('contact_name').value;
	var contactPhone=document.getElementById('contact_phone').value;
	var storeCode=document.getElementById('store_code').value;
	var tsiName=document.getElementById('tsi_name').value;
	var tsiPhone=document.getElementById('tsi_phone').value;
	
	if(address==""){
		address=" ";
	}
	if(contactName==""){
		contactName=" ";
	}
	if(contactPhone==""){
		contactPhone=" ";
	}
	if(storeCode==""){
		storeCode=" ";
	}
	
	if(tsiName==""){
		tsiName=" ";
	}
	if(tsiPhone==""){
		tsiPhone=" ";
	}

	
		

	
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		 if(result==0){
			 alert("store detail is updated successfully");
			 getStoresDetails();
		 }
		 else{
			 alert("store detail is updation failed");
		 }
		  
	  }
	};
	var outData="flag=update_store_detail&storeId="+storeId+"&hubName="+hubName+"&address="+address+
	"&contactName="+contactName+"&contactPhone="+contactPhone+"&storeCode="+storeCode+
	"&tsiName="+tsiName+"&tsiPhone="+tsiPhone;
	
	//alert(outData);
	ajaxRequest.open("POST", "FilterDropDown.jsp",true);	
	ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	ajaxRequest.send(outData); 
	
}


function getStateOnRegion(){
	
	//alert('getStateOnRegion');
	var region=document.getElementById('region_select').value;
	if(region==='SELECT'){
		alert('SELECT REGION !!');
	//	return false;
	}
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT State --->"+result);		 
		   document.getElementById('select_state').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=region&data="+region,true);	
	ajaxRequest.send(null); 
}

function getCityOnState(){
		
	var state=document.getElementById('state_select').value;
	if(state==='SELECT'){
		alert('SELECT STATE !!');
	//	return false;
	}
	//alert('getCityOnState '+state);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT State --->"+result);		 
		   document.getElementById('select_city').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=state&data="+state,true);	
	ajaxRequest.send(null); 
}


function getTownOnCity(){
	
	var city=document.getElementById('city_select').value;
	if(city==='SELECT'){
		alert('SELECT CITY !!');
	//	return false;
	}
	//alert('getTownOnCity '+city);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT City --->"+result);		 
		   document.getElementById('select_town').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=city&data="+city,true);	
	ajaxRequest.send(null); 
}

function getAreaOnTown(){
		
	var town=document.getElementById('town_select').value;
	//alert('getAreaOnTown '+town);
	if(town==='SELECT'){
		alert('SELECT TOWN !!');
	//	return false;
	}
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT Town --->"+result);		 
		   document.getElementById('select_area').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=town&data="+town,true);	
	ajaxRequest.send(null); 
}

function checkJobCard(){
	
	//var Project_select=document.getElementById('Project_select').value;
	var visit_store_name=document.getElementById('visit_store_name').value;
/*	if(Project_select==''){
		alert("Project Can't Empty !!");
		return false;
	}*/
	
	if(visit_store_name==''){
		alert("Store Can't Empty !!");
		return false;
	}
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			//  alert("RESULT checkJobCard --->"+result);
			   
			   if(result!=='NO DATA'){   
			   var arr=result.split("#&#");
			   
			   document.getElementById('job_card_date').value=arr[1].substr(0,10);
			   document.getElementById('job_card').value=arr[0];
			   document.getElementById('job_card_date').readOnly=true;
			   document.getElementById('job_card').readOnly=true;
			   
			   document.getElementById('job_card_submit_button_td').innerHTML="";
			   
			   }
			   else if(result=='NO DATA'){
				   document.getElementById('job_card_date').value='';
				   document.getElementById('job_card').value='';
				   document.getElementById('job_card_date').readOnly=false;
				   document.getElementById('job_card').readOnly=false;
				   
				   document.getElementById('job_card_submit_button_td').innerHTML="<input type=\"submit\"  value=\"Submit\" onclick=\"submitJobCard();\"/>";
				   
			   }
		  }
		};
	
	var	data=visit_store_name;
//	alert('data'+data);
	
	ajaxRequest.open("GET", "InsertJobCard.jsp?flag=checkJobCard&data="+data,true);	
	ajaxRequest.send(null); 
	
	
}
function submitJobCard(){
	//alert('SubmitJobCArd');
	var jobCardDate=document.getElementById('job_card_date').value;
	var jobCard=document.getElementById('job_card').value;
	//var Project_select=document.getElementById('Project_select').value;
	var visit_store_name=document.getElementById('visit_store_name').value;
	
	if(jobCardDate==''){
		alert("JobCardDate Can't Empty !!");
		return false;
	}
/*	if(Project_select==''){
		alert("Project Can't Empty !!");
		return false;
	}*/
	if(jobCard==''){
		alert("Job Card Can't Empty !!");
		return false;
	}
	if(visit_store_name==''){
		alert("Store Can't Empty !!");
		return false;
	}
	
	ajaxCheck();
	
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			   //alert("RESULT submitJobCard --->"+result);
			   if(result=='DATA INSERTED'){
				   alert('JOB CARD ASSIGNED !!');
			   }
			   else if(result=='DUPLICATE JOB CARD'){
				   alert('DUPLICATE JOB CARD !!');
			   } 
			   
			   else if(result=='DATA NOT INSERTED'){
				   alert('JOB CARD NOT ASSIGNED !!');
			   }
			   else if(result=='DATA INSUFFICIENT'){
				   alert('DATA INSUFFICIENT\n PLEASE ENTER ALL THE DETAILS');
			   }
			   
			   else if(result=='NO MEASUREMENT DATA FOR STORE'){
				   alert('NO MEASUREMENT DATA FOR STORE');
			   }
			 
			   
		  }
		};
	
	var	data=jobCardDate+columnSeperator+jobCard+columnSeperator+
	visit_store_name;
	//alert(data);
	
	ajaxRequest.open("GET", "InsertJobCard.jsp?flag=submitJobCard&data="+data,true);	
	ajaxRequest.send(null); 	
		
}

/*
 * Filtering Project on Client basis 
 */

/*function dropPROJECTOnClient(){
	
	alert('dropPROJECTOnClient');
	var client=document.getElementById('client_select').value;
	//alert('dropPROJECTOnClient'+client);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT region --->"+result);		 
		   document.getElementById('select_Project').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=clientFromProject&data="+client,true);	
	ajaxRequest.send(null); 
}*/
function dropPROJECTOnClient(){
	
	//alert('dropPROJECTOnClient');
	
	var client=document.getElementById('client_select').value;
	//alert('dropPROJECTOnClient'+client);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT region --->"+result);		 
		   document.getElementById('select_Project').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=clientFromProject&data="+client,true);	
	ajaxRequest.send(null); 
}



/*function getBrandsOnProjects(){
	
	alert('getBrandsOnProjects in JC');
	var client=document.getElementById('client_select').value;
	//var area_select=document.getElementById('area_select').value;
	//var flag=0;
	if(area_select==='NODATA'|| area_select==='SELECT'){
	}else
	{		flag=1;
		//getStoresOnArea();
	}
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		// alert("RESULT getBrandsOnProjects --->"+result);		 
		   document.getElementById('select_brand').innerHTML=ajaxRequest.responseText;
		  
		   getInstructionOnProject();
		  //alert('flag -->'+flag);
		  if(flag==1){
			  getStoresOnArea();  
		  }
		 
		
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getBrandsOnProjects&data="+client,true);	
	ajaxRequest.send(null); 	
}*/

/*function getStroreOnProjectlist(){
	//alert('getStroreOnProjectlist');
	var Project=document.getElementById('Project_select').value;
	//alert(Project);
	if(document.getElementById('Project_select').value==='NODATA' || document.getElementById('Project_select').value==='SELECT'){
		
		alert('Please Select Project !!');
		return false;
	}
	
	//var area=document.getElementById('area_select').value;
	//var Project=document.getElementById('Project_select').value;
	//alert('getStoresOnArea'+area +'Project  '+Project);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT region --->"+result);		 
		   document.getElementById('select_store').innerHTML=ajaxRequest.responseText;
		  
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=storesFromProject&Project="+Project,true);	
	ajaxRequest.send(null); 

}*/


function getFdHub(){
 
	//alert('calling fd hub');
	//var area=document.getElementById('area_select').value;
	//var Project=document.getElementById('Project_select').value;
	//alert('getStoresOnArea'+area +'Project  '+Project);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert(result);
		   document.getElementById('fd_hub_select_td').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=get_fd_hub",true);	
	ajaxRequest.send(null); 
	
	
}

function getInstructionOnProject(){
	var project=document.getElementById('Project_select').value;
	if(project=='SELECT'||project=='NODATA'){
		alert("SELECT project !!");
		return false;
	}
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   document.getElementById('measurement_instruction').value=result;
		   
		   getStroreOnProjectlist();  
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getInstructionOnProject&project="+project,true);	
	ajaxRequest.send(null); 
	
}

/*function getElementOnBrands(){
	//alert('getElementOnBrands');
	var brand=document.getElementById('brand_select').value;
	var Project=document.getElementById('Project_select').value; 
	//alert('getBrandsOnProjects :: '+ brand + '   '+Project);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT getBrandsOnProjects --->"+result);		 
		   document.getElementById('select_element').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getElementOnBrands&brand="+brand+"&Project="+Project,true);	
	ajaxRequest.send(null); 	
	
}*/

function getBrandsOnProjects(){
	
	////alert('getBrandsOnProjects');
	var project=document.getElementById('Project_select').value;
	//var area_select=document.getElementById('area_select').value;
	//var flag=0;
	/*if(area_select==='NODATA'|| area_select==='SELECT'){
	}else
	{		flag=1;
		//getStoresOnArea();
	}*/
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		 // alert("RESULT getBrandsOnProjects --->"+result);		 
		   document.getElementById('select_brand').innerHTML=ajaxRequest.responseText;
		   getInstructionOnProject();
		  //alert('flag -->'+flag);
		 /* if(flag==1){
			  getStoresOnArea();  
		  }*/
		   
		  // getStroreOnProjectlist();
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getBrandsOnProjects&data="+project,true);	
	ajaxRequest.send(null); 	
}


function getStroreOnProjectlist(){
	var project=document.getElementById('Project_select').value;
	//alert("getStroreOnProjectlist");
	if(document.getElementById('Project_select').value==='NODATA' || document.getElementById('Project_select').value==='SELECT'){
		
		alert('Please Select Project !!');
		return false;
	}
	
	//var area=document.getElementById('area_select').value;
	//var Project=document.getElementById('Project_select').value;
	//alert('getStoresOnArea'+area +'Project  '+Project);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT region --->"+result);		 
		   document.getElementById('select_store').innerHTML=ajaxRequest.responseText;
		   getFdHub();
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=storesFromProject&project="+project,true);	
	ajaxRequest.send(null); 
	
	
}

function getElementOnBrands(){
	//alert('getElementOnBrands');
	var brand=document.getElementById('brand_select').value;
	var project=document.getElementById('Project_select').value; 
	//alert('getBrandsOnProjects :: '+ brand + '   '+Project);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT getBrandsOnProjects --->"+result);		 
		   document.getElementById('select_element').innerHTML=ajaxRequest.responseText;
		   getComponentOnBrand();
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getElementOnBrands&brand="+brand+"&project="+project,true);	
	ajaxRequest.send(null); 	
	
}

function getComponentOnBrand(){
	var brand=document.getElementById('brand_select').value;
	var project=document.getElementById('Project_select').value; 
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		 		 
		   document.getElementById('component_id_div').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getElementComponent&brand="+brand+"&project="+project,true);	
	ajaxRequest.send(null); 	
}

function getComponentMaterialOnElement(){
	//alert('getComponentMaterialOnElement');
	
	var brand=document.getElementById('brand_select').value;
	var Project=document.getElementById('Project_select').value; 
	var elementID=document.getElementById('element_select').value;
	if(elementID==='SELECT' || elementID==='NODATA'){
		alert('PLEASE SELECT Element Type!! ');
		
		 document.getElementById('component_id').value='';
		 document.getElementById('material_id').value='';
//		 document.getElementById('e_status_id').value='';
		 document.getElementById('filter_element_status').value='';
		 
		 
		return false;
	}
	//alert('getComponentMaterialOnElement :: '+ elementID);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   
		   
		   if(result==='NO_DATA'){
			   alert('NO DETAILS FOUND !!');
			   document.getElementById('component_id').value='';
			   document.getElementById('material_id').value='';
//			   document.getElementById('e_status_id').value='';
			   document.getElementById('filter_element_status').value=''; 
		   }
		   else{
		   
			   var com_mat=result.split("#&#");
		
		   document.getElementById('component_id').value=com_mat[0];
		   document.getElementById('material_id').value=com_mat[1];
//		   document.getElementById('e_status_id').value=com_mat[2];
//		   document.getElementById('filter_element_status').value=com_mat[2];
		   document.getElementById('filter_element_status').selectedIndex=com_mat[2];
		  
		   
		   }
		  // document.getElementById('select_component').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=getComponentMaterialOnElement&element="+elementID+"&brand="+brand+"&Project="+Project,true);	
	ajaxRequest.send(null); 	
	
}


function getProjectElementIDforInsert(){
	//alert('getProjectElementIDforInsert');
	//var Project=document.getElementById('Project_select').value;//will get iD
	var elementID=document.getElementById('element_select').value;//will get iD
	

	
	Project_element_id(elementID);
}

function Project_element_id(elementID){
	//	alert('Project_element_id');
	//alert('Project---->'+Project);	
	//alert('elementID----->'+elementID);
	
	var visit_store_name=document.getElementById('visit_store_name').value;
	if(visit_store_name===''){
		alert('VISIT STORE NAME CAN NOT BE EMPTY !!');
		return false; 
	}
	
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   insertMeaurementSheet(result);
	  }
	};
	ajaxRequest.open("GET", "MeasurementInsert.jsp?flag=projectElementID&Project_store_id="+visit_store_name+"&elementID="+elementID,true);	
	ajaxRequest.send(null); 
}

function insertMeaurementSheet(Project_elementID){
	//brand_select element_select status_id component_id 
	//material_id surface_no surface_detail 
	//height width unit_h_w depth unit_d quantity upload_image

	// alert('insertMeaurementSheet ::  Project_elementID'+Project_elementID);
	var visit_store_name=document.getElementById('visit_store_name').value;
	//var surface_no=document.getElementById('surface_no').value;
	//var surface_detail=document.getElementById('surface_detail').value;
	
	var surface_no=document.getElementById('surface_no').value;
	if(surface_no===''){
		
		surface_no='empty';
	}
	
	var surface_detail=document.getElementById('surface_detail').value;
	if(surface_detail===''){
		surface_detail=='empty';
	
	}
	
	var height=document.getElementById('height').value;
	var width=document.getElementById('width').value;
	var depth=document.getElementById('depth').value;
	var unit_d=document.getElementById('unit_d').value;
	var unit_h_w=document.getElementById('unit_h_w').value;
	
	if( height!='' && width!='' && depth==''){
		
		//alert('depth blank!!');
		if(height===''){
			alert('HEIGHT CAN NOT BE EMPTY !!');
			return false; 
		}
		
		if(isNaN(height)||height.indexOf(" ")!=-1)
	    {
	       alert("HEIGHT NEED TO BE NUMERIC VALUE !!");
	       return false; 
	    }
		
		
		if(width===''){
			alert('WIDTH CAN NOT BE EMPTY !!');
			return false; 
		}
		
		if(isNaN(width)||width.indexOf(" ")!=-1)
	    {
	       alert("WIDTH NEED TO BE NUMERIC VALUE !!");
	       return false; 
	    }
		
		var unit_h_w=document.getElementById('unit_h_w').value;
		if(unit_h_w===''|| unit_h_w=='SELECT'){
			alert('SELECT HEIGHT WIDTH UNIT !!');
			return false; 
		}
		
		depth=0;
		unit_d='null';
		
	}else 
		if( height=='' && width=='' && depth!=''){
			
			//alert('depth not blank !!');
			
			height=0;
			width=0;
			unit_h_w='null';
			
			if(depth===''){
				alert('DEPTH CAN NOT BE EMPTY !!');
				return false; 
			}
			
			if(isNaN(depth)||depth.indexOf(" ")!=-1)
		    {
		       alert("DEPTH NEED TO BE NUMERIC VALUE !!");
		       return false; 
		    }
			
			var unit_d=document.getElementById('unit_d').value;
			if(unit_d===''|| unit_d=='SELECT'){
				alert('SELECT DEPTH UNIT !!');
				return false; 
			}
			
		}
		else
			if( height!='' && width=='' && depth==''){
			alert('Please Enter Width !!');
			return false;
		}
		else 
			if(height=='' && width!='' && depth==''){
				alert('Please Enter Height !!');
				return false;
		}
		else 
			if(height=='' && width=='' && depth==''){
					alert('Height-Width Or Depth is Mandatory !!');
					return false;
		}
	
	
	/*var unit_h_w=document.getElementById('unit_h_w').value;
	if(unit_h_w===''|| unit_h_w=='SELECT'){
		alert('SELECT UNIT');
		return false; 
	}
	
	var unit_d=document.getElementById('unit_d').value;
	if(unit_d===''|| unit_d=='SELECT'){
		alert('SELECT UNIT');
		return false; 
	}*/
	
	var quantity=document.getElementById('quantity').value;
	if(quantity===''){
		alert('QUANTITY CAN NOT BE EMPTY !!');
		return false; 
	}
	
	if(isNaN(quantity)||quantity.indexOf(" ")!=-1)
    {
       alert("QUANTITY NEED TO BE NUMERIC VALUE !!");
       return false; 
    }
	
	//var height=document.getElementById('height').value;
	//var width=document.getElementById('width').value;
	//var unit_h_w=document.getElementById('unit_h_w').value;
	//var depth=document.getElementById('depth').value;
	//var unit_d=document.getElementById('unit_d').value;
	var quantity=document.getElementById('quantity').value;
	var surface_no=document.getElementById('surface_no').value;
	var element_status=document.getElementById('filter_element_status').value;
	var component_id=document.getElementById('component_id').value;
	if(surface_no==''){
	
		surface_no='empty';
	}
	
	var surface_detail=document.getElementById('surface_detail').value;
	if(surface_detail==''){
	
		surface_detail='empty';
	
	}
	
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert(result);
		   if(result==="DATA_INSERTED"){
			  alert('MEASURE DATA INSERTED SUCCEFULLY !!');
			   measurementSheetDisplay();
			  
				document.getElementById('surface_no').value='';
				document.getElementById('surface_detail').value='';
				document.getElementById('height').value='';
				document.getElementById('width').value='';
				//document.getElementById('unit_h_w').value='';
				document.getElementById('depth').value='';
				//document.getElementById('unit_d').value='';
				document.getElementById('quantity').value='';
				//document.getElementById('e_status_id').value='';
				document.getElementById('element_id').value='';
				document.getElementById('component_id').value='';
				document.getElementById('material_id').value='';
		   }
		   else if(result==="DATA_NOT_INSERTED"){
			   alert('MEASURE DATA NOT INSERTED!!');
			   
		   }else if(result==="DATA_INSUFFICIENT"){
			   alert('MEASURE DATA INSUFFICEIENT!!\nPLEASE ENTER ALL MANDATORY FIELDS !! ');
		   } 
	  }
	};

	var data = surface_no + columnSeperator + surface_detail + columnSeperator + height + columnSeperator + width +
	columnSeperator + unit_h_w + columnSeperator + depth + columnSeperator + unit_d + columnSeperator + quantity+columnSeperator+element_status+columnSeperator+component_id;
	
	//alert('data for insert measurement	---->'+data +
	//		'visit_store_name'+visit_store_name+'Project_elementID'+Project_elementID);
	
	//alert("store id "+visit_store_name);
	ajaxRequest.open("GET", "MeasurementInsert.jsp?flag=insertStore&data="+data+"&Project_elementID="+Project_elementID+"&visit_store_name="+visit_store_name,true);	
	ajaxRequest.send(null); 
	
	
}

function getStoresOnArea(){
	//alert('getStoresOnArea');
	//var Project=document.getElementById('Project_select').value;
	//alert('Project'+Project);

	if(document.getElementById('Project_select').value==='NODATA' || document.getElementById('Project_select').value==='SELECT'){
		
		alert('Please Select Project !!');
		return false;
	}
	
	var area=document.getElementById('area_select').value;
	var Project=document.getElementById('Project_select').value;
	//alert('getStoresOnArea'+area +'Project  '+Project);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT region --->"+result);		 
		   document.getElementById('select_store').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=storesFromArea&area="+area+"&Project="+Project,true);	
	ajaxRequest.send(null); 
}

/*function getStoresDetails(){
	
	//alert('getStoresDetails');
	
	var store=document.getElementById('visit_store_name').value;
	//alert('store--->'+store);

	if(store==='NODATA' || store==='SELECT'){
		
		alert('Please Select Store !!');
		document.getElementById('address').innerHTML='';
		document.getElementById('tsi_name').value='';
		document.getElementById('tsi_phone').value='';
		document.getElementById('comments').value='';
		 //document.getElementById('measurement_status').value=arr[5];
		document.getElementById('fd_hub').value='';
		 //document.getElementById('fd_hub').value=arr[7];
		document.getElementById('measured_by').value='';
		document.getElementById('measured_on').value='';
		document.getElementById('measurement_status').value='';
		document.getElementById('store_code').value='';
		return false;
	}
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT store --->"+result);
		   var arr = result.split("#&#");
		
		   document.getElementById('address').innerHTML=arr[1];
		   document.getElementById('tsi_name').value=arr[2];
		   document.getElementById('tsi_phone').value=arr[3];		   
		   //document.getElementById('contact_name').value=arr[2];
		   //document.getElementById('contact_phone').value=arr[3];
		   
		  //  document.getElementById('comments').value=arr[4];
		   if(arr[4]=='null'||arr[4]=='empty'){
			   document.getElementById('comments').value='';
		   }else{
			   	document.getElementById('comments').value=arr[4];
		   }
		   
		   //document.getElementById('measurement_status').value=arr[5];
		   document.getElementById('fd_hub').value=arr[6];
		   //document.getElementById('fd_hub').value=arr[7];
		   
		   //  document.getElementById('measured_by').value=arr[8];
		   if(arr[8]=='null'||arr[8]=='empty'){
			   document.getElementById('measured_by').value='';
		   }else{
			   	document.getElementById('measured_by').value=arr[8];
		   }
		   
		   
		   if(arr[9]=='null'||arr[9].substr(0,10)=='1970-01-01'){
			   document.getElementById('measured_on').value='';
		   }else{
			   	document.getElementById('measured_on').value=arr[9].substr(0,10);
		   }
		   
		   if(arr[10]=='null'||arr[10]=='empty'){
			   document.getElementById('measurement_status').value='';
		   }else{
			   document.getElementById('measurement_status').value=arr[10];
		   }
		   //document.getElementById('measurement_status').value=arr[10];
		   document.getElementById('store_code').value=arr[11];
		   
		   editMeasurementDetails();
		   
		   measurementSheetDisplay();
		   //document.getElementById('select_store').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=storesDetails&store="+store,true);	
	ajaxRequest.send(null); 
	
}*/

/*function getStoresDetails(){
	
	//alert('getStoresDetails');
	
	var store=document.getElementById('visit_store_name').value;
	//alert('store--->'+store);

	if(store==='NODATA' || store==='SELECT'){
		
		alert('Please Select Store !!');
		document.getElementById('address').innerHTML='';
		document.getElementById('tsi_name').value='';
		document.getElementById('tsi_phone').value='';
		document.getElementById('comments').value='';
		 //document.getElementById('measurement_status').value=arr[5];
		document.getElementById('fd_hub').value='';
		 //document.getElementById('fd_hub').value=arr[7];
		document.getElementById('measured_by').value='';
		document.getElementById('measured_on').value='';
		document.getElementById('measurement_status').value='';
		document.getElementById('store_code').value='';
		document.getElementById('comments_u').value='';
		document.getElementById('measured_by_u').value='';
		document.getElementById('measured_on_u').value='';
		document.getElementById('measurement_status_u').value='';
		
		return false;
	}
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT store --->"+result);
		   var arr = result.split("#&#");
	   
		   document.getElementById('address').innerHTML=arr[1];
		   document.getElementById('tsi_name').value=arr[2];
		   
		   if(arr[3]=="0000000000"){
			   document.getElementById('tsi_phone').value="";
		   }
		   else{
		   document.getElementById('tsi_phone').value=arr[3];	
		   }
		  
		   document.getElementById('fd_hub').value=arr[5];
		   document.getElementById('store_code').value=arr[7];
		   
		   
		   

		   if(arr[8]=='null'||arr[8].substr(0,10)=='1970-01-01'){
			   document.getElementById('measured_on').value='';
			   document.getElementById('measured_on_u').value='';
		   }else{
			   	document.getElementById('measured_on').value=arr[8].substr(0,10);
				document.getElementById('measured_on_u').value=arr[8].substr(0,10);
		   }
		   
		   if(arr[9]=='null'||arr[9]=='empty'){
			   document.getElementById('measured_by').value='';
			   document.getElementById('measured_by_u').value='';
		   }else{
			   	document.getElementById('measured_by').value=arr[9];
			   	document.getElementById('measured_by_u').value=arr[9];
		   }
		   
		   
		   
		   if(arr[10]=='null'||arr[10]=='empty'){
			   document.getElementById('measure_status_u').value='';
			   document.getElementById('measure_status_u').value='';
		   }else{
			   document.getElementById('measure_status_u').value=arr[10];
			  // document.getElementById('measurement_status_u').value=arr[10];
		   }
		   
		   if( arr[11]=='null' || arr[11]=='empty' ){
			  // alert('blank comment');
			   document.getElementById('comments').value='';
			   document.getElementById('comments_u').value='';
		   }else{
			  // alert('blank not comment');
			   	document.getElementById('comments').value=arr[11];
			   	document.getElementById('comments_u').value=arr[11];
		   }
		  // editMeasurementDetails();
  
		   measurementSheetDisplay();
		   //document.getElementById('select_store').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=storesDetails&store="+store,true);	
	ajaxRequest.send(null); 
	
}*/

function getStoresDetails(){
	
	//alert('getStoresDetails');
	
	document.getElementById('address').value='';
	document.getElementById('contact_name').value='';
	document.getElementById('contact_phone').value='';
	document.getElementById('comments').value='';
    document.getElementById('measured_by').value='';
	document.getElementById('measured_on').value='';
	document.getElementById('fd_hub_select').selectedIndex=0;
	document.getElementById('measurement_status').value='';

	document.getElementById('store_code').value='';
	document.getElementById('comments_u').value='';
	document.getElementById('measured_by_u').value='';
	document.getElementById('measured_on_u').value='';
	document.getElementById('measure_status_u').selectedIndex=0;

	currentMeasurementDate();
	
	var store=document.getElementById('visit_store_name').value;
	//alert('store--->'+store);

	if(store==='NODATA' || store==='SELECT'){
		
		alert('Please Select Store !!');

		
		return false;
	}
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		   //alert("RESULT store --->"+result);
		   var arr = result.split("#&#");
		   var dd;
		   var mm;
		   var yy;
		   
		   if(arr[1]=='null'||arr[1]=='empty'){
			   document.getElementById('address').value='';
		   }else{
			   document.getElementById('address').value=arr[1];
		   }
		 
		   if(arr[2]=='null'||arr[2]=='empty'){
			    document.getElementById('contact_name').value='';
		   }else{
			   document.getElementById('contact_name').value=arr[2];
		   }
		   
		   
		   if(arr[3]=="0000000000"){
			   document.getElementById('contact_phone').value="";
		   }
		   else{
		   document.getElementById('contact_phone').value=arr[3];	
		   }
		   
		   var typeDdl=document.getElementById('fd_hub_select');
		   
	
			for (i = 0; i < typeDdl.options.length; i++) {

				if (arr[5].trim() == (typeDdl.options[i].value)) {
					typeDdl.options[i].selected = true;
				}
			}
			
		   //document.getElementById('store_code').value=arr[7];
		   
		   
		   console.log(arr[8]+":::::::get the date val ");

		   if(arr[8]=='null'||arr[8].substr(0,10)=='1970-01-01' || arr[8]==""){			//for the MeasurementSheet date
			   document.getElementById('measured_on').value='';
			   //document.getElementById('measured_on_u').value='';
		   }else{
//			   document.getElementById('measured_on').value=arr[8].substr(0,10);
//			   document.getElementById('measured_on_u').value=arr[8].substr(0,10);
			  // alert("year ::"+arr[8].substring(2,4));
			 //  alert("month ::"+arr[8].substring(5,7));
			 //  alert("day ::"+arr[8].substring(8,10));
			  
			   yy=arr[8].substring(2,4);
			   console.log(yy);
			   mm=arr[8].substring(5,7);
			   console.log(mm);
			   dd=arr[8].substring(8,10);
			   console.log(dd);
			   document.getElementById('measured_on').value=dd+"/"+mm+"/"+yy;
			   //document.getElementById('measured_on_u').value=dd+"/"+mm+"/"+yy;
		   }
		   
		   if(arr[9]=='null'||arr[9]=='empty'){
			   document.getElementById('measured_by').value='';
			   //document.getElementById('measured_by_u').value='';
		   }else{
			   	document.getElementById('measured_by').value=arr[9];
			   	//document.getElementById('measured_by_u').value=arr[9];
		   }
		 //  alert("arr[10]   "+arr[10]);
		  
			   if(arr[10]=='null'||arr[10]=='empty'){
			   document.getElementById('measurement_status').value='';
			  // document.getElementById('measure_status_u').value='';
			 //  alert("if 10");
		   }else{
			   document.getElementById('measurement_status').value=arr[10];
			   
			/*   var typeDdl=document.getElementById('measure_status_u');
			   
				
				for (i = 0; i < typeDdl.options.length; i++) {
                    
					//alert("array value is "+arr[10].trim()+" text value is "+typeDdl.options[i].text);
					if (arr[10].trim() == (typeDdl.options[i].text)) {
						typeDdl.options[i].selected = true;
					}
				}*/
			   //document.getElementById('measure_status_u').value=arr[10];
			//   alert("else 10");
		   }
			//   alert("document.getElementById('measurement_status').value   "+document.getElementById('measurement_status').value);
		   
		   if( arr[11]=='null' || arr[11]=='empty' ){
			  // alert('blank comment');
			   document.getElementById('comments').value='';
			  // document.getElementById('comments_u').value='';
		   }else{
			  // alert('blank not comment');
			   	document.getElementById('comments').value=arr[11];
			   //	document.getElementById('comments_u').value=arr[11];
		   }
		   
		   if( arr[12]=='null' || arr[12]=='empty' ){
		   
		   document.getElementById('store_code').value="";
		   }
		   else{
			 document.getElementById('store_code').value=arr[12];
		   }
		   
		   if( arr[13]=='null' || arr[13]=='empty' ){
			   
			   document.getElementById('tsi_name').value="";
			   }
			   else{
				 document.getElementById('tsi_name').value=arr[13];
			   }
		   
		   if( arr[14]=='null' || arr[14]=='empty' ){
			   
			   document.getElementById('tsi_phone').value="";
			   }
			   else{
			   document.getElementById('tsi_phone').value=arr[14];
			   }
		   
	

		   
		   measurementSheetDisplay();
		   //document.getElementById('select_store').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=storesDetails&store="+store,true);	
	ajaxRequest.send(null); 
	
}


function editMeasurementDetails(){
	
	var measurement_status=document.getElementById('measurement_status').value;
	var measured_by=document.getElementById('measured_by').value;
	var measured_on=document.getElementById('measured_on').value;
	var comments=document.getElementById('comments').value;
	
	
	document.getElementById('comments_u').value=comments;
	document.getElementById('measure_status_u').value=measurement_status;
	document.getElementById('measured_on_u').value=measured_on;
	document.getElementById('measured_by_u').value=measured_by;
	
	document.getElementById('comments_u').value=document.getElementById('comments').value;
	//document.getElementById('measure_status_u').value=document.getElementById('measurement_status').value;
	//document.getElementById('measured_on_u').value=document.getElementById('measured_on').value;
	document.getElementById('measured_by_u').value=document.getElementById('measured_by').value;

}



function depthDropDown(){
	
	//alert('replacestatus');
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		//   alert("RESULT replaceStatus --->"+result);
		  	   
		   document.getElementById('depth_unit_drop').innerHTML=result;

	  }
	};

	ajaxRequest.open("GET", "DropDowns.jsp?param=depthDropDown",true);	
	ajaxRequest.send(null); 
	
}

function heightDropDown(){
	
	//alert('replacestatus');
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		//   alert("RESULT replaceStatus --->"+result);
		  	   
		   document.getElementById('height_unit_drop').innerHTML=result;

	  }
	};

	ajaxRequest.open("GET", "DropDowns.jsp?param=heightDropDown",true);	
	ajaxRequest.send(null); 
	
}

function blank_update_details(){
	  document.getElementById('comments_u').value="";
	  document.getElementById('measure_status_u').value="";
	  document.getElementById('measured_on_u').value="";
	  document.getElementById('measured_by_u').value="";
}

function updateMeasurementDetails(){
	
	//alert('measure_status_u');
/*	if(comments_u==='' &&  measure_status_u==='' && measured_by_u==='' && measured_on==''){
		alert('Enter Atleast One Data to Update!!');
		return false;
	}*/
	
	var sel = document.getElementById('measure_status_u');
	var measure_status_u = sel.options[sel.selectedIndex].value;
	if(document.getElementById('measure_status_u').selectedIndex==0){
	
		alert("please select status");
		return false;
	}
    
	var comments_u=document.getElementById('comments_u').value;
	if(comments_u==='null'){
		alert ("NULL VALUE IS NOT ALLOWED IN COMMENTS!!\n Please Enter Comments");   
		return false;
	}
	if(comments_u===''){
		comments_u='empty';
	}

	var measured_by=document.getElementById('measured_by_u').value;
	//alert(measured_by);
	if(measured_by==='null'){
		alert ("NULL VALUE IS NOT ALLOWED IN MEASUREMENT BY !!");   
		return false;
	}
	else if(measured_by==='')
	{  measured_by='empty';
	}
	

	var measured_on=document.getElementById('measured_on_u').value;
	
	
	if(measured_on==""){
	    alert("Please Enter measured Date");
	    document.getElementById("measured_on").focus();
	    return false;	

	  }

	var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{2}|\d{2})$/;

	var matchArray = measured_on.match(datePat); // is the format ok?
	if (matchArray == null) {
	 alert("Date must be in DD/MM/YY format");
	 document.getElementById("measured_on").focus();
	 return false;
	}

	day = matchArray[1]; // parse date into variables
	month = matchArray[3];
	year = matchArray[4];
	if (month < 1 || month > 12) { // check month range
	 alert("Month must be between 1 and 12");
	 document.getElementById("measured_on").focus();
	 return false;
	}
	if (day < 1 || day > 31) {
	 alert("Day must be between 1 and 31");
	 document.getElementById("measured_on").focus();
	 return false;
	}
	if ((month==4 || month==6 || month==9 || month==11) && day==31) {
	 alert("Month "+month+" doesn't have 31 days!");
	 document.getElementById("measured_on").focus();
	 return false;
	}
	if (month == 2) { // check for february 29th
	 var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	 if (day>29 || (day==29 && !isleap)) {
	  alert("February " + year + " doesn't have " + day + " days!");
	  document.getElementById("measured_on").focus();
	  return false;
	   }
	}
	
	
	
	
	
	
	var e = document.getElementById("visit_store_name");

	var store_code = e.options[e.selectedIndex].value;
	//alert (comments_u+" "+measurement_status_u+" "+measured_on+" "+measured_by+" "+store_code);
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  //alert(result);
		  if(result==="DATA_INSERTED"){
			  
			  alert('MEASUREMENT STATUS AGAINST THE STORE UPDATED !!');
			  
			/*  document.getElementById('comments').value=document.getElementById('comments_u').value;
			 // alert(document.getElementById('measurement_status_u').value);
			  document.getElementById('measurement_status').value=document.getElementById('measurement_status_u').value;
			  
			 
			  //alert(document.getElementById('measured_on_u').value);
			
			  document.getElementById('measured_on').value=document.getElementById('measured_on_u').value;  
			  if(document.getElementById('measured_on_u').value=='1970-01-01'){  
			  document.getElementById('measured_on').value='';
			  }
			
			 
			  
			  document.getElementById('measured_by').value=document.getElementById('measured_by_u').value;
			  
			  */
			  getStoresDetails();
			 // document.getElementById('comments_u').value="";
			 // document.getElementById('measure_status_u').value="";
			//  document.getElementById('measured_on_u').value="";
			//  document.getElementById('measured_by_u').value="";
			  }
			  else if(result==="DATA_NOT_INSERTED"){
				  alert('MEASUREMENT STATUS AGAINST THE STORE NOT UPDATED !!');
				  
			  } 
			  else if(result===" DATA_INSUFFICIENT"){
				  alert('MEASUREMENT STATUS AGAINST THE STORE UPDATED NOT UPDATED !!\n PLEASE INSERT NECCESARY DETAILS'); 
			}
	  }
	};

	var data=comments_u+"@!@"+measure_status_u+"@!@"+measured_on+"@!@"+measured_by+"@!@"+store_code;
	//alert(data);
	ajaxRequest.open("GET", "UpdateMeasurementStatus.jsp?param=storemeasurement&data="+data,true);	
	ajaxRequest.send(null); 
}

function measurementSheetDisplay(){
	
//	alert('measurementSheetDisplay');
	
	document.getElementById('measured_items').style.display="block";
	document.getElementById('add_measurment').style.display="none";
	document.getElementById('update_measurment').style.display="none";
	
	var visit_store_name=document.getElementById('visit_store_name').value;
	//var Project_select=document.getElementById('Project_select').value;
	
//	alert(visit_store_name +' '+Project_select);
	
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		  var result = ajaxRequest.responseText;
		  //alert("result  :: "+result);
		  document.getElementById('measured_items').innerHTML=result;
		 
		  checkJobCard();
		  
		  getDataTableRecord();
	  }
	};

	ajaxRequest.open("GET", 'SelectMeasurementData.jsp?visit_store_name='+visit_store_name,true);	
	ajaxRequest.send(null);
	
}

function deleteMeasurementData(){


	//Checking Check BOX
	var checked_position='';
	//var date_plan='';
	//var set_as_hold=''; 
	var count=0;
	if(confirm("Are You Want Delete")) 
	{	
		var chks = document.getElementsByName('checkbx[]');
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
		alert("PLEASE SELECT AT LEAST ONE FOR DELETE!!");
		return false;
	}
		
		
		
		/*
			for(var i=0; i<myMeasureSheet.checkbx.length;i++)
			{
				
				alert(myMeasureSheet.checkbx.length+"    "+i);
				if(myMeasureSheet.checkbx[i].checked)
				{
					alert("inside check selection"+myMeasureSheet.checkbx[i].value);
					checked_position=checked_position+myMeasureSheet.checkbx[i].value+',';
					count++;
									
				}
			}
			alert("checked_position---> "+checked_position);
			alert("count Check Box--->"+count);*/
	}	
	
	ajaxCheck();	
	ajaxRequest.onreadystatechange = function(){
				  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
					   var result = ajaxRequest.responseText;
					   result = result.trim();
					   alert(result);
					   
					   measurementSheetDisplay();		     	      
			}
		};

	ajaxRequest.open("GET", "DeleteMeasureData.jsp?checked_position="+checked_position,true);	
	ajaxRequest.send(null); 	
	
}

var measurementId;
function editMeasurementData(SurfaceNo,SurfaceDetail,Component,Height,Width,unit_w,
		Depth,unit_d,Quantity,ProjectElementID,measurementID,element_status,element_id){
	//alert("calling");
	measurementId=measurementID;
heightDropDown(unit_w,unit_d);


}

function depthDropDown(unit_d){
	
	//alert('drop down'+flag);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		  // alert("RESULT width--->"+result);
		  	   
		   document.getElementById('depth_unit_drop').innerHTML=result;
		   var elemetStatus=document.getElementById('unit_d_u');
			//alert("elemetStatus=="+elemetStatus);
			 for (i = 0; i <elemetStatus.options.length; i++) {
				 //alert("unit is=="+unit_d+" drop down value is=="+elemetStatus.options[i].value.trim());
			    if(unit_d==(elemetStatus.options[i].value.trim())){
			    	elemetStatus.options[i].selected= true;
			    }
			 }
		   
	  }
	};

	ajaxRequest.open("GET", "DropDowns.jsp?param=depthDropDown",true);	
	ajaxRequest.send(null); 

}


function heightDropDown(unit_w,unit_d){
	
	//alert('height drop down width unit is '+unit_w+" depth unit is "+unit_d);
	ajaxCheck();
	// Create a function that will receive data 
	// sent from the server and will update
	// div section in the same page.
	ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		   var result = ajaxRequest.responseText;
		   result = result.trim();
		//  
		  	   
		   document.getElementById('height_unit_drop').innerHTML=result;
		   
		  // alert("RESULT height--->"+unit_w);
		   
			var elemetStatus=document.getElementById('unit_h_w_u');
			//alert("elemetStatus=="+elemetStatus);
			 for (var i = 0; i <elemetStatus.options.length; i++) {
				 
				//alert("unit is=="+unit_d+" drop down value is=="+elemetStatus.options[i].value.trim());
				 
			    if(unit_w==(elemetStatus.options[i].value.trim())){
			    	elemetStatus.options[i].selected= true;
			    }
			 }
		   depthDropDown(unit_d);

	  }
	};

	ajaxRequest.open("GET", "DropDowns.jsp?param=heightDropDown",true);	
	ajaxRequest.send(null); 
	
}

	
function update_measurement_sheet(){
	//	alert('update_measurement_sheet');
		var visit_store_name=document.getElementById('visit_store_name').value;
		if(visit_store_name===''){
			alert('VISIT STORE NAME CAN NOT BE EMPTY !!');
			return false; 
		}
		
		//var element=document.getElementById('element_u').value;
		//var component=document.getElementById('component_u').value;
		var surfaceNo=document.getElementById('surface_no_u').value;
		if(surfaceNo==""){
			alert("PLEASE ENTER SURFACE NO.");
			return false;
		}
		
		var surfaceDetail=document.getElementById('surface_detail_u').value;
		if(surfaceDetail==""){
			surfaceDetail="empty";
		}
		
		var height=document.getElementById('height_u').value;
		var width=document.getElementById('width_u').value;
		var depth=document.getElementById('depth_u').value;
		var unit_d=document.getElementById('unit_d_u').value;
		var unit_h_w=document.getElementById('unit_h_w_u').value;
		
			
			if(isNaN(height)||height.indexOf(" ")!=-1)
		    {
		       alert("HEIGHT NEED TO BE NUMERIC VALUE !!");
		       return false; 
		    }
			
			
			
			
			if(isNaN(width)||width.indexOf(" ")!=-1)
		    {
		       alert("WIDTH NEED TO BE NUMERIC VALUE !!");
		       return false; 
		    }
			
			var unit_h_w=document.getElementById('unit_h_w_u').value;
			if(unit_h_w===''|| unit_h_w=='SELECT'||unit_h_w=='NO DATA'){
				alert('SELECT HEIGHT-WIDTH UNIT !!');
				return false; 
			}
			
		
			if(depth=="" || depth==null){
			depth=0;
			}
	
				/*if(isNaN(depth)||depth.indexOf(" ")!=-1)
			    {
			       alert("DEPTH NEED TO BE NUMERIC VALUE !!");
			       return false; 
			    }*/
				
				var unit_d=document.getElementById('unit_d_u').value;
				if(unit_d===''|| unit_d=='SELECT'||unit_d=='NO DATA'){
					alert('SELECT DEPTH UNIT !!');
					return false; 
				}
				
			
			
		var quantity=document.getElementById('quantity_u').value;

		var element_status=document.getElementById('e_status_id');
		var element_status_v=element_status.options[element_status.selectedIndex].value;
		
		
		ajaxCheck();	
		ajaxRequest.onreadystatechange = function(){
					  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
						   var result = ajaxRequest.responseText;
						   result = result.trim();
						  // alert("RESULT --->"+result);
						   if(result=='DATA_UPDATED'){
							   alert("MEASUREMENT-SHEET DATA UPDATED SUCCESSFULLY !!");
							   document.getElementById('measured_items').style.display="block";
							   document.getElementById('add_measurment').style.display="none";
							   document.getElementById('update_measurment').style.display="none";
							   measurementSheetDisplay();
						     
						   } else if(result=='DATA_NOT_UPDATED'){
							   alert("MEASUREMENT-SHEET NOT UPDATED !!");
						   }
						   else if(result=='NO_DATA'){
							   alert("DATA INSUFFCIENT !!\n PLEASE ENTER NECCESARY DETAILS");   
						   }
						   
				}
			};
		
		var param=surfaceNo+columnSeperator+surfaceDetail+columnSeperator+height+columnSeperator+width+columnSeperator+unit_h_w+columnSeperator+depth+
		columnSeperator+unit_d+columnSeperator+quantity+columnSeperator+element_status_v;
		
		//alert(param);
		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=updateMeasurementData&param="+param+"&measureId="+measurementId,true);	
		ajaxRequest.send(null); 
		
	}
function hideEditRow(){
	measurementSheetDisplay();
}
function datePicker(textBoxId){
	document.getElementById(textBoxId).value='';
new JsDatePick({

useMode:2,

target:""+textBoxId+"",

dateFormat:"%d/%m/%y"

}); 

}
