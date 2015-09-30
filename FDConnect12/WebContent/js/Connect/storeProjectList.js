/**
 * 
 */
	var project_element_mapping_id=0;
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
	
	function getStoreDetails(){
			//alert("datatable");
		}
	

	function loadStore(id){
		
		//alert("loadStore "+id);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			  // alert("RESULT loadStore --->"+result);		 
			 document.getElementById('area_detail').innerHTML=ajaxRequest.responseText;
			// alert('before calling');
			 getTableData();
		  }
		 
		};

		ajaxRequest.open("GET", "StoreProjectLoad.jsp?id="+id,true);	
		ajaxRequest.send(null);
		
	}
	

	
	
	function getRegionOnCountryUpdate(){
	//	alert('getRegionOnCountryUpdate');
		var Country=document.getElementById('country_name_u').value;
	//	alert('getRegionOnCountryUpdate--->'+Country);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			  // alert("RESULT getRegionOnCountryUpdate --->"+result);		 
			   document.getElementById('region_update').innerHTML=ajaxRequest.responseText;
			  // document.getElementById('regionName_u').innerHTML=result;
			  
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=countryUpdate&data="+Country,true);	
		ajaxRequest.send(null);
		
	}
	function getRegionOnCountryAdd(){
		
		//alert('getRegionOnCountryAdd');
		var Country=document.getElementById('country_name').value;
		if(Country=='SELECT'){
			alert('SELECT COUNTRY !!');
			return false;
		}
	//	alert('getRegionOnCountryAdd--->'+Country);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			//   alert("RESULT getRegionOnCountryAdd --->"+result);		 
			   document.getElementById('region_add').innerHTML=ajaxRequest.responseText;
			   
			   
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=countryAdd&data="+Country,true);	
		ajaxRequest.send(null); 
	}

	function getStateOnRegionAdd(){
		
		//alert('getStateOnRegionAdd');
		var region=document.getElementById('region_name').value;
		//alert('getStateOnRegionAdd'+region);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			//   alert("RESULT getStateOnRegionAdd --->"+result);		 
			  document.getElementById('state_add').innerHTML=ajaxRequest.responseText;
			   
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=regionAdd&data="+region,true);	
		ajaxRequest.send(null);	
	}
	
function getStateOnRegionUpdate(){
		
		//alert('getStateOnRegionUpdate');
		var region=document.getElementById('region_name_u').value;
	//	alert('getStateOnRegionUpdate'+region);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
		//	   alert("RESULT getStateOnRegionUpdate --->"+result);		 
		//	   document.getElementById('state_update').innerHTML=ajaxRequest.responseText;
			   document.getElementById('state_update').innerHTML=result;
		
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=regionUpdate&data="+region,true);	
		ajaxRequest.send(null);
		
	}
	
	function getCityOnStateAdd(){
		var state=document.getElementById('state_name').value;
	//	alert('getCityOnStateAdd '+state);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
		//	   alert("RESULT getCityOnStateAdd --->"+result);		 
		//	   document.getElementById('city_add').innerHTML=ajaxRequest.responseText;
			   document.getElementById('city_add').innerHTML=result;
			  // document.getElementById('city_update').innerHTML=result;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=stateAdd&data="+state,true);	
		ajaxRequest.send(null);
	}
	
	function getCityOnStateUpdate(){
		var state=document.getElementById('state_name_u').value;
	//	alert('getCityOnStateAdd '+state);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
	//		   alert("RESULT getCityOnStateAdd --->"+result);		 
	//		   document.getElementById('city_update').innerHTML=ajaxRequest.responseText;
			   document.getElementById('city_update').innerHTML=result;
			  
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=stateUpdate&data="+state,true);	
		ajaxRequest.send(null);
	}
	
	function getTownOnCityAdd(){
		
		var city=document.getElementById('city_name').value;
		//alert('getTownOnCity '+city);
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			  // alert("RESULT City --->"+result);		 
			  // document.getElementById('town_add').innerHTML=ajaxRequest.responseText;
			   document.getElementById('town_add').innerHTML=result;
			   
			   var townName = document.getElementById('town_name');
			   for (var i = 0; i <townName.options.length; i++) {
				    if('NA'==(townName.options[i].text)){
				    	townName.options[i].selected= true;
				    }
			   }
			  var townNameVal=townName.options[townName.selectedIndex].text;
			 // alert(townNameVal+"==townNameVal");
			  if(townNameVal=="NA"){
				  getAreaOnTownAdd_NA();
			
			  }
			   
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=cityAdd&data="+city,true);	
		ajaxRequest.send(null); 
		
	}
	
	function getTownOnCityUpdate(){
		
		var city=document.getElementById('city_name_u').value;
	//	alert('getTownOnCityUpdate '+city);
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
		//	   alert("RESULT getTownOnCityUpdate --->"+result);		 
			   document.getElementById('town_update').innerHTML=ajaxRequest.responseText;
			   
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=cityUpdate&data="+city,true);	
		ajaxRequest.send(null); 
		
	}
	
	
	
	function getAreaOnTownAdd_NA(){
		var town=document.getElementById('town_name').value;
		//alert('getAreaOnTownAdd '+town);
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();		 
			   document.getElementById('area_add').innerHTML=result;
			   
			   var AreaName = document.getElementById('area_name');
			   for (var i = 0; i <AreaName.options.length; i++) {
				    if('NA'==(AreaName.options[i].text)){
				    	AreaName.options[i].selected= true;
				    }
			   }
		  }};
		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=townAdd&data="+town,true);	
		ajaxRequest.send(null); 
		
	}
	
	function getAreaOnTownAdd(){
		var town=document.getElementById('town_name').value;
	//	alert('getAreaOnTownAdd '+town);
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();		 
			   document.getElementById('area_add').innerHTML=result;
			   
			   var AreaName = document.getElementById('area_name');
			   for (var i = 0; i <AreaName.options.length; i++) {
				    if('NA'==(AreaName.options[i].text)){
				    	AreaName.options[i].selected= true;
				    }
			   }
		  }};
		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=townAdd&data="+town,true);	
		ajaxRequest.send(null); 
		
	}
	
	function getAreaOnTownUpdate(){
		var town=document.getElementById('town_name_u').value;
	//	alert('getAreaOnTownAdd '+town);
		ajaxCheck();
		
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
		//	   alert("RESULT Town --->"+result);		 
			   document.getElementById('area_updatee').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=townUpdate&data="+town,true);	
		ajaxRequest.send(null); 
		
	}
	
	
	function getStoresOnArea(){
		
		var areaSelect=area_name_v;
		var ProjectID=proj_id;
	
		//	alert('getStrores on area  -->'+ProjectID +'   '+areaSelect);
		
 		 ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			  // alert("RESULT --->"+result);	   
			document.getElementById('area_detail').innerHTML=ajaxRequest.responseText;	     	      
		  }
		};
		
		ajaxRequest.open("GET", "GetProjectStoreDetails.jsp?project_id="+ProjectID+"&areaSelect="+areaSelect,true);	
		ajaxRequest.send(null); 
		
	}
	
	function getRegionOnCountry(){
		
		//alert('selectCountry  in store projectList.js');
		var Country=document.getElementById('country_select').value;
		//alert('selectCountry--->'+Country);
		if(Country.trim().match('SELECT') || Country.trim().match('NODATA')){
			alert('SELECT COUNTRY');
			return false;
		}
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			 //  alert("RESULT region --->"+result);		 
			   document.getElementById('select_region').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=country&data="+Country,true);	
		ajaxRequest.send(null); 
	}
	
	function getStateOnRegion(){
		
	//	alert('getStateOnRegion');
		var region=document.getElementById('region_select').value;
		if(region.trim().match('SELECT')|| region.trim().match('NODATA')){
			alert('SELECT REGION');
			return false;
		}
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
	//		   alert("RESULT State --->"+result);		 
			   document.getElementById('select_state').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=region&data="+region,true);	
		ajaxRequest.send(null); 
	}
	
	function getCityOnState(){
			
		var state=document.getElementById('state_select').value;
	//	alert('getCityOnState '+state);
		if(state.trim().match('SELECT')|| state.trim().match('NODATA')){
			alert('SELECT STATE');
			return false;
		}
		
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
		//	   alert("RESULT State --->"+result);		 
			   document.getElementById('select_city').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=state&data="+state,true);	
		ajaxRequest.send(null); 
	}


	function getTownOnCity(){
		
		var city=document.getElementById('city_select').value;
	//	alert('getTownOnCity '+city);
		if(city.trim().match('SELECT')|| city.trim().match('NODATA')){
			alert('SELECT CITY');
			return false;
		}
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
	//		   alert("RESULT City --->"+result);		 
			   document.getElementById('select_town').innerHTML=ajaxRequest.responseText;
			   
			   
			   
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=city&data="+city,true);	
		ajaxRequest.send(null); 
	}

	function getAreaOnTown(){
		
		var town=document.getElementById('town_select').value;
	//	alert('getAreaOnTown '+town);
		if(town.trim().match('SELECT')|| town.trim().match('NODATA')){
			alert('SELECT TOWN');
			return false;
		}
		
		ajaxCheck();
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
		//	   alert("RESULT Town --->"+result);		 
			   document.getElementById('select_area').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=town&data="+town,true);	
		ajaxRequest.send(null); 
	}
	

		
	

	
	function editRecord(project_id,store_name,address_name,hub_name,
			handle_by_name,tsi_name,tsi_phone,status,project_store_id){
		
		
		myUpdateForm.project_store_hidden.value=project_store_id;
		
		myUpdateForm.store_name_u.value=store_name;
		myUpdateForm.store_addr_u.value=address_name;
		myUpdateForm.store_status_u.value=status;
		myUpdateForm.handle_by_u.value=handle_by_name;
		myUpdateForm.tsi_name_u.value=tsi_name;
		myUpdateForm.tsi_phone_u.value=tsi_phone;
		
	}
	
	function editRecordNew(project_id,store_name,address_name,hub_name,
			handle_by_name,tsi_name,tsi_phone,status,project_store_id,country_name
			,region_name,state_name,city_name,town_name,area_name,trade_name,chain_name,jobcard_no,jobcardDate,project_element_mapping_id){
		
		//alert('edit Record '+project_element_mapping_id+"=====project_element_mapping_id");
		
		var project_store_id=project_store_id.toString().replace('"+','');
		project_store_id=project_store_id.toString().replace('+"','');
		
		var store_name=store_name.toString().replace('"+','');
		store_name=store_name.toString().replace('+"','');
		
		var address_name=address_name.toString().replace('"+','');
		address_name=address_name.toString().replace('+"','');
		
		var hub_name=hub_name.toString().replace('"+','');
		hub_name=hub_name.toString().replace('+"','');
		
		var handle_by_name=handle_by_name.toString().replace('"+','');
		handle_by_name=handle_by_name.toString().replace('+"','');
		
		
		
		var tsi_name=tsi_name.toString().replace('"+','');
		tsi_name=tsi_name.toString().replace('+"','');
		
		var tsi_phone=tsi_phone.toString().replace('"+','');
		tsi_phone=tsi_phone.toString().replace('+"','');
		
		var status=status.toString().replace('"+','');
		status=status.toString().replace('+"','');
			
		var project_store_id=project_store_id.toString().replace('"+','');
		project_store_id=project_store_id.toString().replace('+"','');
		
		var country_name=country_name.toString().replace('"+','');
		country_name=country_name.toString().replace('+"','');
		
		//area_name,trade_name,chain_name
		
		var region_name=region_name.toString().replace('"+','');
		region_name=region_name.toString().replace('+"','');
		
		var state_name=state_name.toString().replace('"+','');
		state_name=state_name.toString().replace('+"','');
		
		var city_name=city_name.toString().replace('"+','');
		city_name=city_name.toString().replace('+"','');
		
		var town_name=town_name.toString().replace('"+','');
		town_name=town_name.toString().replace('+"','');
		
		
		var area_name=area_name.toString().replace('"+','');
		area_name=area_name.toString().replace('+"','');
		
		var trade_name=trade_name.toString().replace('"+','');
		trade_name=trade_name.toString().replace('+"','');
		
		var chain_name=chain_name.toString().replace('"+','');
		chain_name=chain_name.toString().replace('+"','');
		
		var project_element_mapping_id=project_element_mapping_id.toString().replace('"+','');
		project_element_mapping_id=project_element_mapping_id.toString().replace('+"','');
		document.getElementById('project_element_mapping_id').value=project_element_mapping_id.trim();
		 
		//alert(project_element_mapping_id+"-get value of pemid");
		myUpdateForm.project_store_hidden.value=project_store_id;
		
		myUpdateForm.store_name_u.value=store_name;
		myUpdateForm.store_addr_u.value=address_name;
		myUpdateForm.store_status_u.value=status;
		myUpdateForm.handle_by_u.value=handle_by_name;
		myUpdateForm.tsi_name_u.value=tsi_name;
		myUpdateForm.tsi_phone_u.value=tsi_phone;
		
	}
	
	
	
	
	//Insert Store
	function insertStore(oTable,nRow,proj_id){
	
		var aData = oTable.fnGetData(nRow);
		
		var jqInputs = $('input', nRow);
		var jqSelect = $('select', nRow);
		
		for(var i=0;i<jqInputs.length;i++){
			console.log("i ::"+i+" jqInputs   "+jqInputs[i].value);
		}
		for(var j=0;j<jqSelect.length;j++){
			console.log("j ::"+j+" jqSelect   "+jqSelect[j].value);
		}
	
		var letters = /^[A-Za-z]+$/;  
		
		var columnSeperator="@!@";
			
		var store_name_v = document.getElementById('store_name').value;
		//alert(store_name_v+"===store_name_v");
		if(store_name_v===""){
			alert("Store Name Can't be empty");
			return false;
		}
		store_name_v = store_name_v.trim();
		if(store_name_v.match(letters)|| store_name_v.match('') )  
		{ 
			
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN STORE NAME."); 
			return false;  
		}  
		
		//var store_addr_v = jqInputs[2].value;
		var store_addr_v = document.getElementById('store_addr').value;
		if(store_addr_v===""){
			store_addr_v="NA";
			//alert("Store Address Can't be empty");
			//return false;
		}
		store_addr_v = store_addr_v.trim();		
	
	//	var store_status_v= jqSelect[1].value;
		var store_status= document.getElementById('store_status');
		var store_status_v=store_status.options[store_status.selectedIndex].text;
		
		if(store_status_v=="SELECT" || store_status_v==""){
			alert("PLEASE SELECT THE STATUS");
			return false;
		}
		store_status_v = store_status_v.trim();
		//alert(store_status_v);
		
		var store_status_id=store_status.options[store_status.selectedIndex].value;
		
		var fd_hub=document.getElementById('fd_hub');
		var fd_hub_v = fd_hub.options[fd_hub.selectedIndex].value;
		//alert(fd_hub_v);
		
		
		//var handle_by_v= jqInputs[3].value;
		var handle_by_v=  document.getElementById('handle_by').value;
		if(handle_by_v===""){
			handle_by_v="empty"	;
		}
		handle_by_v = handle_by_v.trim();
		if(handle_by_v.match(letters)|| handle_by_v.match('') )  
		{  	//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN HANDLE BY."); 
			return false;  
		}  

		var tsi_name_v= document.getElementById('tsi_name').value;
		if(tsi_name_v===""){
			tsi_name_v="empty";
		}
		tsi_name_v = tsi_name_v.trim();
		if(tsi_name_v.match(letters)|| tsi_name_v.match('') )  
		{  	//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN TSI NAME."); 
			return false;  
		}  

		var tsi_phone_v=  document.getElementById('tsi_phone').value;
		console.log(tsi_phone_v.substring(1, 3)+"\t"+"==tsi_phone_v.substring(1, 3)");
		console.log(tsi_phone_v.substring(0, 3)+"\t"+"==tsi_phone_v.substring(0, 3)");
		
		if(tsi_phone_v.substring(0, 3)=="000"){
			alert("INVALID NUMBER");
			return false;
		}
		
		if(tsi_phone_v===""){
			tsi_phone_v="0000000000";
		}
		tsi_phone_v = tsi_phone_v.trim();
		/*if(isNaN(tsi_phone_v)||tsi_phone_v.indexOf(" ")!=-1)
        {
           alert("INVALID NUMBER !!");
           return false; 
        }*/
		
        if (tsi_phone_v.length<10)
        {
             alert("INVALID NUMBER !!");
             return false;
        }
       
        var chain_name_v="NA";
        var trade_name_v="NA";
    
		var state_name =document.getElementById('state_name');
		var state_name_v=state_name.options[state_name.selectedIndex].text;
		if(state_name_v===""){
			alert("State Name Can't be empty");
			//state_name_v="NA";
			return false;
		}
		state_name_v = state_name_v.trim();
		if(state_name_v.trim().match('SELECT') || state_name_v.trim().match('NODATA')){
			alert('SELECT STATE');
			return false;
			//state_name_v="NA";
		}
		
		var city_name =document.getElementById('city_name');
		var city_name_v=city_name.options[city_name.selectedIndex].text;
		if(city_name_v===""){
			alert("City Name Can't be empty");
		//	city_name_v="NA";
			return false;
		}
		city_name_v = city_name_v.trim();
		
		if(city_name_v.trim().match('SELECT') || city_name_v.trim().match('NODATA')){
			alert('SELECT CITY');
			return false;
			//city_name_v="NA";
		}
		var town_name_v="NA";
		var area_name_v="NA";
		
	
		var project_name_v=document.getElementById('projName').value;
		if(project_name_v=="SELECT"){
			alert("PLEASE SELECT THE ELEMENT NAME");
			return false;
		}
	
		var project_QTY=document.getElementById('qty').value;
		if(project_QTY===""){
			project_QTY=0;	
		}
		
		
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			  
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			 //alert(result);
			   if(result==="DATA INSERTED"){
				   alert("STORE ADDED TO Project !!");
				   loadStore(proj_id);
					     
				 
			   }
			   else {
				   alert("STORE NOT ADDED!!"); 
				  
			   }
			  
		 }
		  
		};

		var args="store_name_v="+store_name_v+"&project_id="+proj_id+
		"&store_addr_v="+store_addr_v+"&store_status_v="+store_status_v+"&fd_hub_v="+fd_hub_v
		+"&handle_by_v="+handle_by_v+"&tsi_name_v="+tsi_name_v+"&tsi_phone_v="+tsi_phone_v+"&chain_name_v="+chain_name_v
		+"&trade_name_v="+trade_name_v+"&city_name_v="+city_name_v+"&town_name_v="+town_name_v+"&area_name_v="+area_name_v
		+"&state_name_v="+state_name_v+"&project_name_v="+project_name_v+"&project_QTY="+project_QTY+"&store_status_id="+store_status_id;
		//alert("store id "+store_name_v+" element_id "+project_name_v);
		console.log(args);
		
		ajaxRequest.open("POST","InsertStoreDetails.jsp?",false);	
		ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		ajaxRequest.send(args); 
	}
	
	
	
	function insertStoreforEmptyTable(oTable_new,newRow,proj_id){

		var aData = oTable_new.fnGetData(newRow);
		
		var jqInputs = $('input', newRow);
		var jqSelect = $('select', newRow);
	
		var letters = /^[A-Za-z]+$/;  
		
		var columnSeperator="@!@";
			
		var store_name_v = document.getElementById('store_name').value;
		//alert(store_name_v+"===store_name_v");
		if(store_name_v===""){
			alert("Store Name Can't be empty");
			return false;
		}
		store_name_v = store_name_v.trim();
		if(store_name_v.match(letters)|| store_name_v.match('') )  
		{ 
			
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN STORE NAME."); 
			return false;  
		}  
		
		//var store_addr_v = jqInputs[2].value;
		var store_addr_v = document.getElementById('store_addr').value;
		if(store_addr_v===""){
			store_addr_v="NA";
			//alert("Store Address Can't be empty");
			//return false;
		}
		store_addr_v = store_addr_v.trim();		
	
	//	var store_status_v= jqSelect[1].value;
		var store_status= document.getElementById('store_status');
		var store_status_v=store_status.options[store_status.selectedIndex].text;
		
		if(store_name_v=="SELECT" || store_status_v==""){
			alert("PLEASE SELECT THE STATUS");
			return false;
		}
		store_status_v = store_status_v.trim();
		//alert(store_status_v);
		
		var store_status_id=store_status.options[store_status.selectedIndex].value;
		
		var fd_hub=document.getElementById('fd_hub');
		var fd_hub_v = fd_hub.options[fd_hub.selectedIndex].value;
		//alert(fd_hub_v);
		
		
		//var handle_by_v= jqInputs[3].value;
		var handle_by_v=  document.getElementById('handle_by').value;
		if(handle_by_v===""){
			handle_by_v="empty"	;
		}
		handle_by_v = handle_by_v.trim();
		if(handle_by_v.match(letters)|| handle_by_v.match('') )  
		{  	//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN HANDLE BY."); 
			return false;  
		}  

		var tsi_name_v= document.getElementById('tsi_name').value;
		if(tsi_name_v===""){
			tsi_name_v="empty";
		}
		tsi_name_v = tsi_name_v.trim();
		if(tsi_name_v.match(letters)|| tsi_name_v.match('') )  
		{  	//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN TSI NAME."); 
			return false;  
		}  

		var tsi_phone_v=  document.getElementById('tsi_phone').value;
		console.log(tsi_phone_v.substring(1, 3)+"\t"+"==tsi_phone_v.substring(1, 3)");
		console.log(tsi_phone_v.substring(0, 3)+"\t"+"==tsi_phone_v.substring(0, 3)");
		
		if(tsi_phone_v.substring(0, 3)=="000"){
			alert("INVALID NUMBER");
			return false;
		}
		
		if(tsi_phone_v===""){
			tsi_phone_v="0000000000";
		}
		tsi_phone_v = tsi_phone_v.trim();
		/*if(isNaN(tsi_phone_v)||tsi_phone_v.indexOf(" ")!=-1)
        {
           alert("INVALID NUMBER !!");
           return false; 
        }*/
		
        if (tsi_phone_v.length<10)
        {
        	   alert("INVALID NUMBER !!");
             return false;
        }
       
        var chain_name_v="NA";
        var trade_name_v="NA";
    
		var state_name =document.getElementById('state_name');
		var state_name_v=state_name.options[state_name.selectedIndex].text;
		if(state_name_v===""){
			alert("State Name Can't be empty");
			//state_name_v="NA";
			return false;
		}
		state_name_v = state_name_v.trim();
		if(state_name_v.trim().match('SELECT') || state_name_v.trim().match('NODATA')){
			alert('SELECT STATE');
			return false;
			//state_name_v="NA";
		}
		
		var city_name =document.getElementById('city_name');
		var city_name_v=city_name.options[city_name.selectedIndex].text;
		if(city_name_v===""){
			alert("City Name Can't be empty");
		//	city_name_v="NA";
			return false;
		}
		city_name_v = city_name_v.trim();
		
		if(city_name_v.trim().match('SELECT') || city_name_v.trim().match('NODATA')){
			alert('SELECT CITY');
			return false;
			//city_name_v="NA";
		}
		var town_name_v="NA";
		var area_name_v="NA";
		
	
		var project_name_v=document.getElementById('projName').value;
		if(project_name_v=="SELECT"){
			alert("PLEASE SELECT THE ELEMENT NAME");
			return false;
		}
	
		var project_QTY=document.getElementById('qty').value;
		if(project_QTY===""){
			project_QTY=0;	
		}
			
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			  
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			//  alert(result);
			   if(result==="DATA INSERTED"){
				   alert("STORE ADDED TO PROJECT !!");
				  
				 // window.location.reload();
			   }
			   else{
				   alert("STORE NOT ADDED!!"); 
			   }
			   loadStore(proj_id);
		  }
		};

		var args="store_name_v="+store_name_v+"&project_id="+proj_id+
		"&store_addr_v="+store_addr_v+"&store_status_v="+store_status_v+"&fd_hub_v="+fd_hub_v
		+"&handle_by_v="+handle_by_v+"&tsi_name_v="+tsi_name_v+"&tsi_phone_v="+tsi_phone_v+"&chain_name_v="+chain_name_v
		+"&trade_name_v="+trade_name_v+"&city_name_v="+city_name_v+"&town_name_v="+town_name_v+"&area_name_v="+area_name_v
		+"&state_name_v="+state_name_v+"&project_name_v="+project_name_v
		+"&project_QTY="+project_QTY+"&store_status_id="+store_status_id;
		
		console.log(args);
		
		ajaxRequest.open("POST","InsertStoreDetails.jsp?",false);	
		ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		ajaxRequest.send(args); 
	}
	
	
	
	
	//Update Store details 
	function saveRow ( oTable, nRow,project_id )
	{
		var jqInputs = $('input', nRow);
		
		var jqSelect = $('select', nRow);
		
		for(var i=0;i<jqInputs.length;i++){
			console.log("i ::"+i+" jqInputs   "+jqInputs[i].value);
		}
		for(var j=0;j<jqSelect.length;j++){
			console.log("j ::"+j+" jqSelect   "+jqSelect[j].value);
		}
		//alert("for submit");
		
		
		var letters = /^[A-Za-z]+$/; 
		
		var columnSeperator="!@!";
		
		var project_store_id=jqInputs[0].value;
		
	//	alert("project_store_id hidden  "+project_store_id);
		
		//var store_name_v = myUpdateForm.store_name_u.value;
		var store_name_v = document.getElementById('store_name_u').value;
		if(store_name_v===""){
			alert("STORE NAME CAN'T BE EMPTY");
			return false;
		}
		store_name_v = store_name_v.trim();		
		if(store_name_v.match(letters)|| store_name_v.match('') )  
		{  }  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN STORE NAME."); 
			return false;  
		}  
		

		//var store_addr_v = myUpdateForm.store_addr_u.value;
		var store_addr_v = document.getElementById('store_addr_u').value;
		if(store_addr_v===""){
			store_addr_v="NA";
			//alert("Store Address Can't be empty");
			//return false;
		}
		store_addr_v = store_addr_v.trim();
		//alert(store_addr_v);
	
		var store_status= document.getElementById('store_status_u');
		var store_status_v=store_status.options[store_status.selectedIndex].text;
		if(store_status_v==="" || store_status_v=="SELECT"){
			alert("STORE STATUS CAN'T BE EMPTY");
			return false;
		}
		store_status_v = store_status_v.trim();
		
		var store_status_id=store_status.options[store_status.selectedIndex].value;
		
		
		
		var fd_hub=document.getElementById('fd_hub_u');
		var fd_hub_v=fd_hub.options[fd_hub.selectedIndex].value;
		
//		var handle_by_v= myUpdateForm.handle_by_u.value;
		var handle_by_v= document.getElementById('handle_by_u').value;
		if(handle_by_v===""){
			handle_by_v="empty";
		}
		handle_by_v = handle_by_v.trim();
		if(handle_by_v.match(letters)|| handle_by_v.match('') )  
		{  	//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN HANDLE BY."); 
			return false;  
		}  
		
		//var tsi_name_v= myUpdateForm.tsi_name_u.value;
		var tsi_name_v= document.getElementById('tsi_name_u').value;
		if(tsi_name_v===""){
			tsi_name_v="empty";
		}
		tsi_name_v = tsi_name_v.trim();
		if(tsi_name_v.match(letters)|| tsi_name_v.match('') )  
		{  	//alert("STRING CONTAINS A-Za-z");
		}  
		else  
		{  	alert ("SPECIAL CHARACTERS AND NUMBERS ARE NOT ALLOWED IN TSI NAME."); 
			return false;  
		}  
		
		//var tsi_phone_v= myUpdateForm.tsi_phone_u.value;
		var tsi_phone_v= document.getElementById('tsi_phone_u').value;
		
		if(tsi_phone_v.substring(0, 3)=="000"){
			alert("INVALID NUMBER");
			return false;
		}
		
		if(tsi_phone_v===""){
			tsi_phone_v="0000000000";
		}
		tsi_phone_v = tsi_phone_v.trim();
		/*if(isNaN(tsi_phone_v)||tsi_phone_v.indexOf(" ")!=-1)
        {
           alert("INVALID NUMBER !!");
           
           return false; 
        }*/
		
      
		var chain_name_v="NA";
		var trade_name_v="NA";
		
//		var state_name_v = jqSelect[4].value;
		var state_name = document.getElementById('state_name_u');
		var state_name_v=state_name.options[state_name.selectedIndex].text;
		
		if(state_name_v===""){
			//alert("State Name Can't be empty");
			state_name_v="NA";
		//	return false;
		}
		state_name_v = state_name_v.trim();
		if(state_name_v.trim().match('SELECT') || state_name_v.trim().match('NODATA')){
			alert('SELECT STATE');
			return false;
		}
		
		//var city_name_v = jqSelect[5].value;
		var city_name =  document.getElementById('city_name_u');
		var city_name_v =  city_name.options[city_name.selectedIndex].text;
		
		if(city_name_v===""){
			alert("City Name Can't be empty");
		//	city_name_v="NA";
			return false;
		}
		city_name_v = city_name_v.trim();
		if(city_name_v.trim().match('SELECT') || city_name_v.trim().match('NODATA')){
			alert('SELECT CITY');
			return false;
		}
				
	
		
		var town_name_v ="NA";
		var area_name_v="NA";
		var project_name=document.getElementById('projName_u');
		var project_name_v=project_name.options[project_name.selectedIndex].text;
		if(project_name_v=="SELECT"){
			alert("PLEASE SELECT THE ELEMENT NAME");
			return false;
		}
		var Element_id=document.getElementById('projName_u').value;
		
		var project_QTY=document.getElementById('qty_u').value;
		if(project_QTY===""){
			project_QTY=0;	
		}
		
		var project_element_mapping_id= document.getElementById('project_element_mapping_id').value;
		//document.getElementById('update_div').style.visibility="hidden";
		//document.getElementById('insert_div').style.visibility="visible"; 
		
		ajaxCheck();
		
		ajaxRequest.onreadystatechange = function(){
			
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				   var result = ajaxRequest.responseText;
				   result = result.trim();
				  // alert(result);
				   if(result==='DATA UPDATED'){
					  alert("STORE DETAILS UPDATED"); 
					  oTable.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 1, false );
					  
					  //window.location.href="StoreProjectList.jsp?id="+project_id;
				   }else if(result==='DATA NOT UPDATED'){
					   alert("STORE DETAILS NOT UPDATED"); 
				   }
				   //getStoresOnArea();
				   loadStore(project_id);
				  
				
			  }
			};
			/*var param=store_name_v+columnSeperator+chain_name_v+columnSeperator+trade_name_v+columnSeperator+
			country_name_v+columnSeperator+region_name_v+columnSeperator+state_name_v+columnSeperator
			+city_name_v+columnSeperator+town_name_v+columnSeperator+area_name_v;*/
		
			var param=store_name_v+columnSeperator+store_addr_v+columnSeperator+store_status_v +columnSeperator+ fd_hub_v+columnSeperator+
			handle_by_v +columnSeperator+tsi_name_v+columnSeperator+ tsi_phone_v+columnSeperator+chain_name_v+columnSeperator+trade_name_v+columnSeperator+
			state_name_v+columnSeperator+city_name_v+columnSeperator+town_name_v+columnSeperator+area_name_v+columnSeperator+project_name_v+columnSeperator+
			project_QTY+columnSeperator+Element_id+columnSeperator+store_status_id;
			
			console.log(param);
			
			var param="store_name_v="+store_name_v+"&project_id="+project_id+
			"&store_addr_v="+store_addr_v+"&store_status_v="+store_status_v+"&fd_hub_v="+fd_hub_v
			+"&handle_by_v="+handle_by_v+"&tsi_name_v="+tsi_name_v+"&tsi_phone_v="+tsi_phone_v+"" +
			"&chain_name_v="+chain_name_v+"&trade_name_v="+trade_name_v+"&city_name_v="+city_name_v+"" +
			"&town_name_v="+town_name_v+"&area_name_v="+area_name_v
			+"&state_name_v="+state_name_v+"&project_name_v="+project_name_v+
			"&projectStoreid="+project_store_id+"&project_QTY="+project_QTY+"&Element_id="+Element_id+
			"&store_status_id="+store_status_id+"&project_element_mapping_id="+project_element_mapping_id;

			
			
			//?param="+param+"&projectStoreid="+project_store_id
			ajaxRequest.open("POST", "UpdateStoreDetails.jsp",false);
			ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			ajaxRequest.send(param); 
		
	}
	
	function deleteStore(project_id){
		//alert('deleteStore');
		//var myForm=document.forms[0];
		//Checking Check BOX
		var checked_position='';
		//var date_plan='';
		//var set_as_hold=''; 
		var count=0;
		if(confirm("Are You Want Delete")) 
		{	
			
			if(myDisplayForm.chkbox.length==undefined && myDisplayForm.chkbox.checked){
				checked_position=document.getElementById('chkbox').value;
				
			}else{
				for(var i=0; i<myDisplayForm.chkbox.length ; i++)
				{
					if(myDisplayForm.chkbox[i].checked)
					{
						//alert("inside check selection"+myDisplayForm.chkbox[i].value);
						checked_position=checked_position+myDisplayForm.chkbox[i].value+',';
						count++;
					}
				}
			}
				//alert("checked_position---> "+checked_position);
			//	alert("count Check Box--->"+count);
			
				ajaxCheck();	
				ajaxRequest.onreadystatechange = function(){
					  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
						   var result = ajaxRequest.responseText;
						   result = result.trim();
						 //  alert("RESULT --->"+result);
						   if(result==='DATA DELETED'){
							   alert("STORE DELETED FROM PROJECT");
							   
							   
							   
						   }else if(result==='FOREIGN_KEY_CONSTRANIT_FAIL'){
							
							   alert("FOREIGN KEY CONSTRANIT FAIL !\nSTORE NOT DELETED");
						   }						   
						   else{
							   alert("STORE NOT DELETED FROM PROJECT");
						   }
						   //listStore(project_id);
							  loadStore(project_id);
				}
			};

					ajaxRequest.open("GET", "DeleteStoreForProject.jsp?checked_position="+checked_position,true);	
					ajaxRequest.send(null); 			
		}
	}