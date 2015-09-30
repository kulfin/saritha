<%@page import="com.fd.Connect.DropDown"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@page import="com.fd.App.*" %>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<%! String Project_elements=null;  %>

<!-- Retrieve all display Data -->
<%
String session1=(String)session.getAttribute("employee_Id");
if(session1==null){
	response.sendRedirect("../Login.jsp");
}
String quantityVal="";
String id = request.getParameter("id");
session.setAttribute("project_id",id);
System.out.println("Project ID in ProjectScopeOfWork.jsp---------->"+id);
ProjectServices services = new ProjectServices();
String header_details = services.Project_header_details(Integer.parseInt(id));
String[] row_data = header_details.split(Constants.columnSeperator);
System.out.println("row_data is****** \t" + row_data[2]);
String clientName=row_data[2];

DropDown drop=new DropDown();
/* String row_region=drop.regionDrop(); */
String country_list=drop.drop_down_country();
//String state_list=drop.dropdown_state_withID();
//String city_list=drop.dropdown_city_withID();

String chain_list=drop.dropdown_chain_withID();
String trade_list=drop.dropdown_trade_withID();
String scope_list_list=drop.drop_down_scope_master();


String project_element_scopes= services.retrieve_Project_scope_list(Integer.parseInt(id));
String[] project_element_scopes_list=project_element_scopes.split(Constants.rowSeperator);
System.out.println("Project_element_scopes --- >"+project_element_scopes);


//Brand
String[] brand_list_data=services.drop_down_brand(clientName).split(Constants.columnSeperator);
//Element
String[] element_list_data=services.drop_down_element().split(Constants.columnSeperator);
//Component
String[] component_list_data=services.drop_down_component().split(Constants.columnSeperator);
//Material
String[] material_list_data=services.drop_down_material().split(Constants.columnSeperator);
//Depot 
String[] depot_list_data=services.drop_down_depot().split(Constants.columnSeperator);
//Print Mode
String[] print_mode_list_data=services.drop_down_print_mode_master().split(Constants.columnSeperator);
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#data {
	color: #000000;
	text-align: left;
}

.reduce_inputTag {
	width: 100px;
}


</style>
<!-- <link rel="stylesheet" type="text/css" href="../css/Connect/demo_table_old.css"/> -->
<link rel="stylesheet" type="text/css" href="test/demo_table.css"/>
<script type="text/javascript" src="../js/Connect/connet_js_entire.js">
</script>

<!-- Drop Do0wn -->
<link rel="stylesheet" type="text/css" href="../css/Common/style.css" />
<!-- <script type="text/javascript" src="../js/Common/jquery.min.js"></script> -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Common/home.css" />
<script type="text/javascript" src="../js/Connect/connect.js"></script>

<script type="text/javascript"><!--

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


</script>
<script type="text/javascript">
		
	function open_details(num) {
		//indow.location.href = 'DetailsProject.jsp?id=' + num;
		
		parent.window.document.getElementById('header_label').innerHTML = "Project Page";
		
		ajaxcheck();
		ajaxRequest.onreadystatechange = function(){
	  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			document.getElementById('main_content_element').innerHTML = ajaxRequest.responseText;
		  }
	};

		ajaxRequest.open("GET","DetailsProject.jsp?id="+ num,true);	
		ajaxRequest.send(null); 	
	}
	</script>

<!--  For new tab menu -->
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Connect/style.css" />

<!-- Side Menu -->

<link rel="stylesheet" type="text/css" 	href="../css/Connect/details_project.css" />

<!-- CheckBox Select -->
<script type="text/javascript" src="../js/Connect/jobCardMeasurement.js">
</script>

<link rel="stylesheet" type="text/css" href="../css/Connect/horizontal_menu.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css"	href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript" 	src="../css/Common/dropdowntabfiles/dropdowntabs.js"></script>


<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all"	href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript"	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="../js/Connect/jquery.min.detail.js"></script>



<script type="text/javascript"><!--

function setRegionCombo(country_id_choose,arr){
	
	//alert("setRegionCombo"+country_id_choose);
	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
			result = result.trim();
			//alert("result in region dropdown "+result);
			
			var row_arr=result.split("!&!");
			
			
			var region_div=document.getElementById('region_update');
			var region_name_u=document.getElementById('region_name_u');

			
			var region_select=document.createElement("select");
			region_select.setAttribute("id","region_name_u");
			region_select.setAttribute("name","region_name_u");
			region_select.setAttribute("onchange","getStateOnRegionUpdate()");
			region_select.setAttribute("style","width: 115px");
			
			var j=0;
			region_select.options[j]=new Option('SELECT','SELECT');
			j++;
			//region_div.replaceChild(region_select,region_name_u); 
			
			for(var i=0; i<row_arr.length-1; i++){	
			var col_arr=row_arr[i].split("#&#");
			region_select.options[j]=new Option(col_arr[0],col_arr[1]);//value,id,default_selected,selected
			j++;
			}

			region_div.replaceChild(region_select,region_name_u); 
			 
			var region_name_u = document.getElementById('region_name_u');
			var region_id_choose;
			for(var i=0;i<region_name_u.options.length;i++){
				if(region_name_u.options[i].value.trim()==arr[3].trim()){
					region_name_u.options[i].selected=true;
					region_id_choose=region_name_u.options[i].value.trim();
				}
			}  
			setStateCombo(region_id_choose,arr); 
		}
	};
	
	ajaxRequest.open("POST","DropDowns.jsp?param=regionwithID&country="+country_id_choose);
	ajaxRequest.send(null);
	
}

function setStateCombo(region_id_choose,arr){
	
 	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
			result = result.trim();
			//alert("result in state dropdown"+result);
			
			var row_arr=result.split("!&!");
			
			
			var state_div=document.getElementById('state_update');
			
			var state_name_u=document.getElementById('state_name_u');
			
			
			var state_select=document.createElement("select");
			state_select.setAttribute("id","state_name_u");
			state_select.setAttribute("name","state_name_u");
			state_select.setAttribute("onchange","getCityOnStateUpdate()");
			state_select.setAttribute("style","width: 115px");
			
			var j=0;
			state_select.options[j]=new Option('SELECT','SELECT');
			j++;
			
			for(var i=0; i<row_arr.length-1; i++){
			//alert("State row_arr[i] "+row_arr[i]);	
			
			var column_arr=row_arr[i].split("#&#");
			state_select.options[j]=new Option(column_arr[0],column_arr[1]);//value,id,default_selected,selected
			j++;
			}
			

			state_div.replaceChild(state_select,state_name_u); 
			
			var state_id;
			var state_name_u = document.getElementById('state_name_u');

			for(var i=0;i<state_name_u.options.length;i++){
				if(state_name_u.options[i].value.trim()==arr[4].trim()){
				//	alert('State Matched !!  ::'+arr[4]);
					state_name_u.options[i].selected=true;
					state_id=state_name_u.options[i].value.trim();
				}
			}  
			setCityCombo(state_id, arr);
			 
		}
	};
	
	ajaxRequest.open("POST","DropDowns.jsp?param=statewithID&region="+region_id_choose);
	ajaxRequest.send(null); 
	
}

 function setCityCombo(state_id,arr){
	
	//alert("setCityCombo"+state_id);
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
			result = result.trim();
			//alert("result in setCityCombo"+result);
			
			var row_arr=result.split("!&!");
			
			
			var city_div=document.getElementById('city_update');
			var city_name_u=document.getElementById('city_name_u');

			
			var city_select=document.createElement("select");
			city_select.setAttribute("id","city_name_u");
			city_select.setAttribute("name","city_name_u");
			city_select.setAttribute("onchange","getCityOnStateUpdate()");
			city_select.setAttribute("style","width: 115px");
			
			var j=0;
			city_select.options[j]=new Option('SELECT','SELECT');
			j++;	
			
			for(var i=0; i<row_arr.length-1; i++){
				var col_arr=row_arr[i].split("#&#");
				city_select.options[j]=new Option(col_arr[0],col_arr[1]);//value,id
				j++;
			}
			city_div.replaceChild(city_select,city_name_u); 
			
			 var city_name_u = document.getElementById('city_name_u');

			for(var i=0;i<city_name_u.options.length;i++){
				if(city_name_u.options[i].value.trim()==arr[5].trim()){
					city_name_u.options[i].selected=true;
				}
			} 
			
		}
	};
	
	ajaxRequest.open("POST","DropDowns.jsp?param=citywithID&state="+state_id);
	ajaxRequest.send(null);
	
} 

function ScopeDetails(id){

	document.getElementById('element_scope_date').style.display='block';
	document.getElementById('Project_element_scope').value=id;
	document.getElementById(id).checked=true;
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
			document.getElementById('measured_items').innerHTML=result;
			convertToDataTable();
		}
	};

	ajaxRequest.open("GET","GetScopeDetailsforElement.jsp?Project_element_scope="+id,true);
	ajaxRequest.send(null);
}
var nRows;
var oTable2;
var nEdit;
var indexID;
var aData_Scope;
var jqInputs_Scope;
var jqTds_Scope;

var convertToDataTable=function(){
	
	oTable2=$('#scope_example').dataTable({
		 // 'iDisplayLength': 5,
		"aaSortingFixed": [[21,'asc']],//column number,ascending order
		//"bFilter": false,
		 "bPaginate": false,
		 'bLengthChange': false,
         "bSortClasses": false,
         "bProcessing" : true,
         "bDestroy" : true,
         "bAutoWidth" : true,
         "aoColumns": [ {"bSortable": false}, {"bSortable": false},
                       null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,{"bVisible":false}]
 		});


	//new added for edit		
	$('#scope_example a.edit').live('click', function (e) {
			//e.preventDefault();
			
			/* Get the row as a parent of the link that was clicked on */
			 nRows = $(this).parents('tr')[0];
			
			if ( nEdit !== null && nEdit != nRows ) {
				/* Currently editing - but not this row - restore the old before continuing to edit mode */
				restoreRow( oTable2, nEdit );
				editScopeRow( oTable2, nRows );
				nEdit = nRows;
			}
			else if ( nEdit == nRows && this.innerHTML == "Save" ) {
				/* Editing this row and want to save it */
			return updateScopeforElement(oTable2, nEdit);
				nEdit = null;
			}
			else if ( nEdit == nRows && this.innerHTML == "Cancel" ) {
				restoreRow( oTable2, nEdit );
				nEdit = null;
				}
			else {
				/* No edit in progress - let's start one */
				editScopeRow( oTable2, nRows );
				nEdit = nRows;
			}
		});


	
};
function restoreRow ( oTable2, nRows )
{
	var aData = oTable2.fnGetData(nRows);
	console.log("aData=="+aData);
	var jqTds = $('>td', nRows);
	console.log("restoreRow------------jqTds=="+jqTds);
	for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
		oTable2.fnUpdate( aData[i], nRows, i, false );
	}
	
	oTable2.fnDraw();
}

	
function editScopeRow( oTable2, nRows ){
//alert("edit scope==========");
	aData_Scope = oTable2.fnGetData(nRows);
	 jqInputs_Scope = $('input', nRows);
	 jqTds_Scope = $('>td', nRows);

	 
	jqTds_Scope[1].innerHTML ="<a class='edit' href=''>Save</a>/<a class='edit' href=''>Cancel</a>";

	//country
	 jqTds_Scope[2].innerHTML=document.getElementById('country_update').innerHTML;
	 var set_name_u = document.getElementById('country_name_u');
	 for (i = 0; i <set_name_u.options.length; i++) {
	    if(aData_Scope[2].trim().toUpperCase()==(set_name_u.options[i].text.trim())){
	    	set_name_u.options[i].selected= true;
	    }
	 }						
	

	//region 
	 jqTds_Scope[3].innerHTML='<div id="region_update"><select id="region_name_u" name="region_name_u" style="width: 115px;"><option value='+s_regionid_update+' >'+aData_Scope[3]+'</option></select></div>';

	//state 
	 jqTds_Scope[4].innerHTML='<div id="state_update"><select style="width: 115px;" id="state_name_u" name="state_name_u"><option value='+s_stateid_update+'>'+aData_Scope[4]+'</select></div>';

	//trade
	 jqTds_Scope[5].innerHTML=document.getElementById('select_trade_u').innerHTML;
	 var select_trade_u = document.getElementById('trade_id_u');
	
	 for (i = 0; i <select_trade_u.options.length; i++) {
	    if(aData_Scope[5].trim().toUpperCase()==(select_trade_u.options[i].text.trim())){
	    	select_trade_u.options[i].selected= true;
	    }
	 }	
	 
	//chain
	 jqTds_Scope[6].innerHTML=document.getElementById('select_chain_u').innerHTML;
	 var select_chain_u = document.getElementById('chain_id_u');
		for (i = 0; i <select_chain_u.options.length; i++) {
	    if(aData_Scope[6].trim().toUpperCase()==(select_chain_u.options[i].text.trim())){
	    	select_chain_u.options[i].selected= true;
	    }
	 }	

	 
	//city
	 jqTds_Scope[7].innerHTML='<div id="city_update"><select style="width: 115px;" id="city_name_u" name="city_name_u"><option value='+s_cityid_update+'>'+aData_Scope[7]+'</select></div>';

	//no of stores
	 jqTds_Scope[8].innerHTML='<input type="text" id="no_of_store_u" name="no_of_store_u"  value="'+aData_Scope[8]+'" class="reduce_inputTag" >';
	 
	 //scope of work
	 jqTds_Scope[9].innerHTML=document.getElementById('scope_of_work_u_div').innerHTML;
	 var scope_of_work_u = document.getElementById('scope_of_work_u');
		for (i = 0; i <scope_of_work_u.options.length; i++) {
	    if(aData_Scope[9].trim()==(scope_of_work_u.options[i].text)){
	    	scope_of_work_u.options[i].selected= true;
	    }
	 }	

		//client approval  
	 jqTds_Scope[10].innerHTML=document.getElementById('client_approval_req_u_div').innerHTML;
	 var client_approval_req_u = document.getElementById('client_approval_req_u');
		for (i = 0; i <client_approval_req_u.options.length; i++) {
	    if(aData_Scope[10].trim().toUpperCase()==(client_approval_req_u.options[i].text.trim())){
	    	client_approval_req_u.options[i].selected= true;
	    }
	 }	
		
	 jqTds_Scope[11].innerHTML=document.getElementById('fd_hub_u_div').innerHTML;
	 var fd_hub_u = document.getElementById('fd_hub_u');
		for (i = 0; i <fd_hub_u.options.length; i++) {
	    if(aData_Scope[11].trim().toUpperCase()==(fd_hub_u.options[i].text.trim())){
	    	fd_hub_u.options[i].selected= true;
	    }
	 }	
	 

	//responsible persion
	 jqTds_Scope[12].innerHTML='<input type="text" id="responsible_person_u" name="responsible_person_u" value="'+aData_Scope[12]+'" class="reduce_inputTag" >';

	 //client national target date
	 jqTds_Scope[13].innerHTML='<input type="text" id="cl_np_tar_u" value="'+aData_Scope[13]+'" class="reduce_inputTag" onmouseover="dateplan(cl_np_tar_u);">';

	//client national actual date
	 jqTds_Scope[14].innerHTML='<input type="text" id="cl_np_act_u" value="'+aData_Scope[14]+'" class="reduce_inputTag" onmouseover="dateplan(cl_np_act_u);">';

	//clent regional target date
	 jqTds_Scope[15].innerHTML='<input type="text" id="cl_reg_tar_u" value="'+aData_Scope[15]+'" class="reduce_inputTag" onmouseover="dateplan(cl_reg_tar_u);">';

	 //clent regional actual date
	 jqTds_Scope[16].innerHTML='<input type="text" id="cl_reg_act_u" value="'+aData_Scope[16]+'" class="reduce_inputTag" onmouseover="dateplan(cl_reg_act_u);">';

	//fd_national_target_date
	 jqTds_Scope[17].innerHTML='<input type="text" id="fd_np_tar_u" value="'+aData_Scope[17]+'" class="reduce_inputTag" onmouseover="dateplan(fd_np_tar_u);">';

	//fd_national_actual_date
	 jqTds_Scope[18].innerHTML='<input type="text" id="fd_np_act_u" value="'+aData_Scope[18]+'" class="reduce_inputTag" onmouseover="dateplan(fd_np_act_u);">';

	//fd_regional_target_date
	 jqTds_Scope[19].innerHTML='<input type="text" id="fd_reg_tar_u" value="'+aData_Scope[19]+'" class="reduce_inputTag" onmouseover="dateplan(fd_reg_tar_u);">';

	 //fd_regional_actual_date
	 jqTds_Scope[20].innerHTML='<input type="text" id="fd_reg_act_u" value="'+aData_Scope[20]+'" class="reduce_inputTag" onmouseover="dateplan(fd_reg_act_u);">';
	
		
}

	
var s_regionid_update;
var s_stateid_update;
var s_cityid_update;	
	
function editScope(indexID,region_id,state_id,city_id ){
 	
 	//document.getElementById('add_scope_elements').style.display='none';
 	//document.getElementById('measured_items').style.display='none';
 	//document.getElementById('update_scope_element').style.display='block';
 	s_regionid_update=region_id;
 	s_stateid_update=state_id;
 	s_cityid_update=city_id;
	ajaxCheck();
 	ajaxRequest.onreadystatechange = function() {
 		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
 			var result = ajaxRequest.responseText;
 			result = result.trim();
 		//	alert(result);
 		
 			
 		var arr=result.split("#&#");

 		var country_id_choose;
 		document.getElementById('scope_id_u').value=arr[0];//scope_id
 
 		for(var i=0;i<country_name_u.options.length;i++){
 			if(country_name_u.options[i].value.trim()==arr[2].trim()){
 				country_name_u.options[i].selected=true;
 				country_id_choose=country_name_u.options[i].value.trim();
 			}
 		} 
 		
 		setRegionCombo(country_id_choose,arr);	
 		
 		if(arr[6]==0)//
 			document.getElementById('trade_id_u').value='SELECT';	
 		else
 			document.getElementById('trade_id_u').value=arr[6];
 		
 		
 		if(arr[7]==0)
 			document.getElementById('chain_id_u').value='SELECT';	
 		else
 			document.getElementById('chain_id_u').value=arr[7];
 		
 		
 		document.getElementById('client_approval_req_u').value=arr[8];
 		
 		if(arr[9]==0)
 			document.getElementById('fd_hub_u').value='SELECT';	
 		else
 			document.getElementById('fd_hub_u').value=arr[9];
 		//document.getElementById('fd_hub_u').value=arr[9];	
 		
 		
 		document.getElementById('responsible_person_u').value=arr[10];
 		if(arr[10]=='NONE')
 			document.getElementById('responsible_person_u').value='';	
 		else
 			document.getElementById('responsible_person_u').value=arr[10];
 		
 		//document.getElementById('no_of_store_u').value=arr[11];	
 		if(arr[11]=='0')
 			document.getElementById('no_of_store_u').value='';	
 		else
 			document.getElementById('no_of_store_u').value=arr[11];	
 		
 		
 		document.getElementById('scope_of_work_u').value=arr[12];
 		
 		//document.getElementById('cl_np_tar_u').value=arr[13];
 		if(arr[13]=='1970-01-01')
 			document.getElementById('cl_np_tar_u').value='';	
 		else
 			document.getElementById('cl_np_tar_u').value=arr[13];
 		
 		//document.getElementById('cl_np_act_u').value=arr[14];
 		if(arr[14]=='1970-01-01')
 			document.getElementById('cl_np_act_u').value='';	
 		else
 			document.getElementById('cl_np_act_u').value=arr[14];
 		
 		
 		//document.getElementById('cl_reg_tar_u').value=arr[15];
 		if(arr[15]=='1970-01-01')
 			document.getElementById('cl_reg_tar_u').value='';	
 		else
 			document.getElementById('cl_reg_tar_u').value=arr[15];
 		
 		//document.getElementById('cl_reg_act_u').value=arr[16];
 		if(arr[16]=='1970-01-01')
 			document.getElementById('cl_reg_act_u').value='';	
 		else
 			document.getElementById('cl_reg_act_u').value=arr[16];
 		
 		//document.getElementById('fd_np_tar_u').value=arr[17];
 		if(arr[17]=='1970-01-01')
 			document.getElementById('fd_np_tar_u').value='';	
 		else
 			document.getElementById('fd_np_tar_u').value=arr[17];
 		
 		//document.getElementById('fd_np_act_u').value=arr[18];
 		if(arr[18]=='1970-01-01')
 			document.getElementById('fd_np_act_u').value='';	
 		else
 			document.getElementById('fd_np_act_u').value=arr[18];
 		
 		//document.getElementById('fd_reg_tar_u').value=arr[19];
 		if(arr[19]=='1970-01-01')
 			document.getElementById('fd_reg_tar_u').value='';	
 		else
 			document.getElementById('fd_reg_tar_u').value=arr[19];
 		
 		
 		//document.getElementById('fd_reg_act_u').value=arr[20];
 		if(arr[20]=='1970-01-01')
 			document.getElementById('fd_reg_act_u').value='';	
 		else
 			document.getElementById('fd_reg_act_u').value=arr[20];

 		}
 	};
		
 	ajaxRequest.open("GET","GetParticularScopeDetails.jsp?id="+indexID,true);
 	ajaxRequest.send(null);
 	
 
 }




function ScopeDetailsAfterChoose(){
	//document.getElementById('add_scope_elements').style.display='none';
	document.getElementById('measured_items').style.display='block';
	//document.getElementById('update_scope_element').style.display='none';

	var id=document.getElementById('Project_element_scope').value;
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
		
			document.getElementById('measured_items').innerHTML=result;
			convertToDataTable();
		}
	};

	ajaxRequest.open("GET","GetScopeDetailsforElement.jsp?Project_element_scope="+id,true);
	ajaxRequest.send(null);
}


function updateScopeforElement(oTable2, nEdit){
//alert("updateScopeforElement------");
	
	var columnSeperator="@!@";
	
	var country=document.getElementById('country_name_u').value;
	if(country==''||country=='SELECT'){
		alert('SELECT COUNTRY');
		return false;
	}
	
	var region=document.getElementById('region_name_u').value;
	if(region==''||region=='SELECT'){
		alert('SELECT REGION');
		return false;
	}
	
	var state=document.getElementById('state_name_u').value;
	if(state=='SELECT'||state==''||state=='NODATA'){
		state='empty';
	}
	
	var city=document.getElementById('city_name_u').value;
	if(city=='SELECT'||city==''||city=='NODATA'){
		city='empty';
	}
	
	var trade=document.getElementById('trade_id_u').value;
	if(trade=='SELECT'||trade==''||trade=='NODATA'){
		trade='empty';
	}
	
	var chain=document.getElementById('chain_id_u').value;
	if(chain=='SELECT'||chain==''||chain=='NODATA'){
		chain='empty';
	}
	
//alert(country+region+state+city+trade+chain);
	
	var no_of_store=document.getElementById('no_of_store_u').value;
	//alert("no_of_store--"+no_of_store);
	if(no_of_store==''){
		no_of_store=0;
	}
	
	
	var scope_of_work=document.getElementById('scope_of_work_u').value;
	if(scope_of_work==''||scope_of_work=='SELECT'){
		alert('SELECT SCOPE');
		return false;
	}
	
	var client_aprvl_req=document.getElementById('client_approval_req_u').value;
//	alert(client_aprvl_req);
	
	var scope_id_u=document.getElementById('scope_id_u').value;//Project_element_id
	//alert(scope_id_u);
	
	var fd_hub=document.getElementById('fd_hub_u').value;
	if(fd_hub=='SELECT'||fd_hub==''){
		fd_hub='empty';
	}
//	alert(fd_hub);
	
	var responsible_person=document.getElementById('responsible_person_u').value;
	//alert("responsible_person=="+responsible_person);
	if(responsible_person==''){
		responsible_person='NONE';
	}
//	alert(responsible_person+"-----responsible_person");
	
//alert(no_of_store+scope_of_work+client_aprvl_req+prgm_element_scope+responsible_person+fd_hub);

	var cl_np_tar=document.getElementById('cl_np_tar_u').value;
	if(cl_np_tar==''){
		cl_np_tar='empty';
	}
	var cl_np_act=document.getElementById('cl_np_act_u').value;
	if(cl_np_act==''){
		cl_np_act='empty';
	}
	var cl_reg_tar=document.getElementById('cl_reg_tar_u').value;
	if(cl_reg_tar==''){
		cl_reg_tar='empty';
	}
	var cl_reg_act=document.getElementById('cl_reg_act_u').value;
	if(cl_reg_act==''){
		cl_reg_act='empty';
	}
//alert(cl_reg_act+cl_reg_tar+cl_np_act+cl_np_tar);
	
	var fd_np_tar=document.getElementById('fd_np_tar_u').value;
	if(fd_np_tar==''){
		fd_np_tar='empty';
	}
	var fd_np_act=document.getElementById('fd_np_act_u').value;
	if(fd_np_act==''){
		fd_np_act='empty';
	}
	var fd_reg_tar=document.getElementById('fd_reg_tar_u').value;
	if(fd_reg_tar==''){
		fd_reg_tar='empty';
	}
	var fd_reg_act=document.getElementById('fd_reg_act_u').value;
	if(fd_reg_act==''){
		fd_reg_act='empty';
	}
 //alert("dates====="+fd_np_tar+fd_np_act+fd_reg_tar+fd_reg_act);
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
			result = result.trim();
		// alert(result);
			 if(result=="DATA_NOT_UPDATED"){
				 alert("SCOPE DETAILS NOT UPDATED");	 
			 }else if(result=="DATA_INSUFFICIENT"){
				 alert("SCOPE DETAILS INSUFFICIENT");
			 }else if(result=="DATA_UPDATED"){		    	
			    	alert("SCOPE DETAILS UPDATED");
			    	
		    }
			 ScopeDetailsAfterChoose();
		}
	};

	ajaxRequest.open("GET","UpdatePrgramScopes.jsp?scope_id="+scope_id_u
			+ "&data=" + country + columnSeperator + region + columnSeperator + state + columnSeperator
			+ city + columnSeperator + trade + columnSeperator + chain + columnSeperator +
			no_of_store + columnSeperator + scope_of_work + columnSeperator +
			client_aprvl_req + columnSeperator + fd_hub + columnSeperator + responsible_person + columnSeperator+
			cl_reg_act + columnSeperator + cl_reg_tar + columnSeperator + cl_np_act +
			columnSeperator + cl_np_tar + columnSeperator + fd_np_tar + columnSeperator +
			fd_np_act + columnSeperator + fd_reg_tar + columnSeperator + fd_reg_act,false);
	ajaxRequest.send(null);
	
}



function insertScopeforElement(){

		//alert("insertScopeforElement()===================called");
	var columnSeperator="@!@";
	
	var country=document.getElementById('country_select').value;
	if(country==''||country=='SELECT'){
		alert('SELECT COUNTRY');
		return false;
	}
	
	
	var region=document.getElementById('region_select').value;
	if(region==''||region=='SELECT'){
		alert('SELECT REGION');
		return false;
	}
	
	var state=document.getElementById('state_select').value;
	if(state=='SELECT'||state==''||state=='NODATA'){
		state='empty';
	}
		
	var city=document.getElementById('city_select').value;
	if(city=='SELECT'||city==''||city=='NODATA'){
		city='empty';
	}
	
	var trade=document.getElementById('trade_id').value;
	if(trade=='SELECT'||trade==''||trade=='NODATA'){
		trade='empty';
	}
	
	var chain=document.getElementById('chain_id').value;
	if(chain=='SELECT'||chain==''||chain=='NODATA'){
		chain='empty';
	}
	
	//alert(country+region+state+city+trade+chain);
	
	var no_of_store=document.getElementById('no_of_store').value;
	//alert(no_of_store+"----no of store");
	if(no_of_store==''){
		no_of_store=0;
	}
	
	var scope_of_work=document.getElementById('scope_of_work').value;

	if(scope_of_work==''||scope_of_work=='SELECT'){
		alert('SELECT SCOPE');
		return false;
	}
	
	var client_aprvl_req=document.getElementById('client_approval_req').value;
	
	var prgm_element_scope=document.getElementById('Project_element_scope').value;//Project_element_id
	

//alert("prgm_element_scope!!!!!!!!!"+prgm_element_scope+"!!!!!!!!!!!!!");	
	var fd_hub=document.getElementById('fd_hub').value;
	if(fd_hub=='SELECT'||fd_hub==''){
		fd_hub='empty';
	}
	
	var responsible_person=document.getElementById('responsible_person').value;	
	//alert("responsible_person=="+responsible_person);
	if(responsible_person==''){
		responsible_person='NONE';
	}
	

	//alert(no_of_store+scope_of_work+client_aprvl_req+prgm_element_scope+'res person:::'+responsible_person+fd_hub);
	
	var cl_np_tar=document.getElementById('cl_np_tar').value;
	if(cl_np_tar==''){
		//cl_np_tar='1970-01-01';
		cl_np_tar='empty';
	}
	var cl_np_act=document.getElementById('cl_np_act').value;
	if(cl_np_act==''){
//		cl_np_act='1970-01-01';
		cl_np_act='empty';
	}
	var cl_reg_tar=document.getElementById('cl_reg_tar').value;
	if(cl_reg_tar==''){
	//	cl_reg_tar='1970-01-01';
		cl_reg_tar='empty';
	}
	
	var cl_reg_act=document.getElementById('cl_reg_act').value;
	if(cl_reg_act==''){
	//	cl_reg_act='1970-01-01';
		cl_reg_act='empty';
	}
//alert(cl_reg_act+cl_reg_tar+cl_np_act+cl_np_tar);
	
	var fd_np_tar=document.getElementById('fd_np_tar').value;
	if(fd_np_tar==''){
	//	fd_np_tar='1970-01-01';
		fd_np_tar='empty';
	}
	
	var fd_np_act=document.getElementById('fd_np_act').value;
	if(fd_np_act==''){
	//	fd_np_act='1970-01-01';
		fd_np_act='empty';
	}
	
	var fd_reg_tar=document.getElementById('fd_reg_tar').value;
	if(fd_reg_tar==''){
		//fd_reg_tar='1970-01-01';
		fd_reg_tar='empty';
	}
	
	var fd_reg_act=document.getElementById('fd_reg_act').value;
	if(fd_reg_act==''){
		//fd_reg_act='1970-01-01';
		fd_reg_act='empty';
	}
	
//alert("dates==========="+fd_np_tar+fd_np_act+fd_reg_tar+fd_reg_act);
	
	ajaxCheck();
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var result = ajaxRequest.responseText;
			result = result.trim();
			// alert(result);
			 if(result=="DATA_NOT_INSERTED"){
				 alert("SCOPE DETAILS NOT INSERTED");
			 }else if(result=="DATA_INSUFFICIENT"){
				 alert("SCOPE DETAILS INSUFFICIENT");
			 }else if(result=="DATA_INSERTED"){		    	
			    	alert("SCOPE DETAILS INSERTED");
			    	ScopeDetailsAfterChoose();
		    }
		}
	};

	ajaxRequest.open("GET","InsertPrgramScopes.jsp?prgm_element_scope="+prgm_element_scope
			+"&data="+country+columnSeperator+region+columnSeperator+state+columnSeperator
			+city+columnSeperator+trade+columnSeperator+chain+columnSeperator+
			no_of_store+columnSeperator+scope_of_work+columnSeperator+
			client_aprvl_req+columnSeperator+fd_hub+columnSeperator+responsible_person+columnSeperator+
			cl_np_tar +columnSeperator+cl_np_act+columnSeperator+cl_reg_tar+
			columnSeperator+cl_reg_act +columnSeperator+fd_np_tar+columnSeperator+
			fd_np_act+columnSeperator+fd_reg_tar+columnSeperator+fd_reg_act,false);
	ajaxRequest.send(null);
	
}



function DeleteScopeOfWork(){

	var checked_position='';
	//var date_plan='';
	//var set_as_hold=''; 
	var count=0;
	if(confirm("Are You Want Delete")) 
	{	
		var check_box=document.getElementsByName('checkbox_scope');
			for(var i=0; i<check_box.length ; i++)
			{
				if( check_box[i].checked)
				{
					checked_position=checked_position+check_box[i].value+',';
					count++;
				}
			}
		
			ajaxCheck();	
			ajaxRequest.onreadystatechange = function(){
				  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
					   var result = ajaxRequest.responseText;
					   result = result.trim();
					  // alert("RESULT -"+result);
					   if(result==='DATA DELETED'){
						   alert("ELEMENT TYPE SCOPE DELETED !!");
						   
					   }else if(result==='FOREIGN_KEY_CONSTRANIT_FAIL'){
						
						   alert("FOREIGN KEY CONSTRANIT FAIL !\ELEMENT SCOPE NOT DELETED");
					   }						   
					   else{
						   alert("ELEMENT TYPE SCOPE NOT DELETED !!");
					   }
					   var Project_element_id=document.getElementById('Project_element_scope').value;
					   ScopeDetails(Project_element_id);
			}
		};

				ajaxRequest.open("GET", "DeleteScopesforElement.jsp?checked_position="+checked_position,true);	
				ajaxRequest.send(null); 			
	}
}

function add_scope(id){
	//alert('ID--'+id);
	var ajaxRequest = null;
	
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

		ajaxRequest.open("GET", "project_scope_display.jsp?id="+id,true);	
		ajaxRequest.send(null);

	
}


function scope_date_plan(id){
	
	//alert('ELMENT ID'+id);
	document.getElementById('element_scope_date').style.display='block';
	var ajaxRequest = null;
	
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
		ajaxRequest.onreadystatechange = function(){
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				 
				   var result = ajaxRequest.responseText;
				  // alert("RESULT -"+result);	   
				document.getElementById('element_scope_date').innerHTML=ajaxRequest.responseText;
				     	      
			  }
			};

			ajaxRequest.open("POST", "ScopeSupport.jsp?id="+id,true);	
			ajaxRequest.send(null);
	
}

function CancelElements(){
	document.getElementById('edit_scope_of_work').style.display='none';
	document.getElementById('qty_u').value='';
	document.getElementById('set_as_hold_u').value='';
}


function AddScopeOfWork(){
	//measured_items
	//add_scope_elements
	//update_scope_element
	//document.getElementById('add_scope_elements').style.display='block';
	//document.getElementById('measured_items').style.display='none';
	//document.getElementById('update_scope_element').style.display='none'; 

	var aiNew = oTable2.fnAddData( [ '<input type="checkbox" name="checkbox_scope[]" id="checkbox_scope">',
		                                "<a class='' onclick='return insertScopeforElement();' href=''>Save</a>/<a class='' href=''>Cancel</a>" ,
		                                 '','','','','','','','','','','','','','','','','','','',''] );
	//	alert("********aiNew*******"+aiNew[0]);
		
		var nRows = oTable2.fnGetNodes( aiNew[0] );
		
		AddRowToDatatable_Scope(oTable2, nRows);

	
}

	function AddRowToDatatable_Scope(oTable2, nRows){
		
		var jqTds_Scope = $('>td', nRows);
		
		var jqInputs = $('input', nRows);
		
		jqTds_Scope[2].innerHTML =document.getElementById('country_div').innerHTML;
	
		//region 
		 jqTds_Scope[3].innerHTML='<div id="select_region"><input type="text" id="region_select" style="width: 100px;"></div>';

		//state 
		 jqTds_Scope[4].innerHTML='<div id="select_state"><input type="text" id="state_select" name="state_select" style="width: 100px;"></div>';

		//trade
		 jqTds_Scope[5].innerHTML=document.getElementById('select_trade').innerHTML;

		//chain
		 jqTds_Scope[6].innerHTML=document.getElementById('select_chain').innerHTML;
		//city
		 jqTds_Scope[7].innerHTML='<div id="select_city"><input type="text" id="city_select" name="city_select" style="width: 100px;"></div>';
		//no of stores
		 jqTds_Scope[8].innerHTML='<input type="text" id="no_of_store" name="no_of_store" class="reduce_inputTag">';
		 
		 jqTds_Scope[9].innerHTML=document.getElementById('ScopeOfWork_div').innerHTML;

		 jqTds_Scope[10].innerHTML='<td><select id="client_approval_req" name="client_approval_req"><option value="NO">NO</option><option value="YES">YES</option></select></td>';

		jqTds_Scope[11].innerHTML=document.getElementById('fd_add_div').innerHTML;

		//responsible persion
		 jqTds_Scope[12].innerHTML='<input type="text" id="responsible_person" style="width: 100px;">';

		 //client national target date
		 jqTds_Scope[13].innerHTML='<input type="text" id="cl_np_tar" class="reduce_inputTag" onmouseover="dateplan(cl_np_tar);">';

		//client national actual date
		 jqTds_Scope[14].innerHTML='<input type="text" id="cl_np_act"  class="reduce_inputTag" onmouseover="dateplan(cl_np_act);">';

		//clent regional target date
		 jqTds_Scope[15].innerHTML='<input type="text" id="cl_reg_tar"  class="reduce_inputTag" onmouseover="dateplan(cl_reg_tar);">';

		 //clent regional actual date
		 jqTds_Scope[16].innerHTML='<input type="text" id="cl_reg_act" class="reduce_inputTag" onmouseover="dateplan(cl_reg_act);">';

		//fd_national_target_date
		 jqTds_Scope[17].innerHTML='<input type="text" id="fd_np_tar"  class="reduce_inputTag" onmouseover="dateplan(fd_np_tar);">';

		//fd_national_actual_date
		 jqTds_Scope[18].innerHTML='<input type="text" id="fd_np_act"  class="reduce_inputTag" onmouseover="dateplan(fd_np_act);">';

		//fd_regional_target_date
		 jqTds_Scope[19].innerHTML='<input type="text" id="fd_reg_tar"  class="reduce_inputTag" onmouseover="dateplan(fd_reg_tar);">';

		 //fd_regional_actual_date
		 jqTds_Scope[20].innerHTML='<input type="text" id="fd_reg_act"  class="reduce_inputTag" onmouseover="dateplan(fd_reg_act);">';
		
			
		
	}

function CancelAddScopeOfWork(){
	
	document.getElementById('add_scope_elements').style.display='none';
	document.getElementById('measured_items').style.display='block';
	document.getElementById('update_scope_element').style.display='none'; 
}

 
function editElement(Project_element_id){

	/* console.log("editElement called..");
	
	/*var Row = document.getElementById("client_name");
	var Cells = Row.getElementsByTagName("td");
	var client_name = Cells[2].innerText;
	alert('client name' + client_name);*/
	
	
	 var ajaxRequest = null;
	
	// The variable that makes Ajax possible!		
	try {
		// Opera 8.0+, Firefox, Safari0
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
	
	ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			 
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			  // alert("RESULT - "+result);
			   var arr=result.split("#&#");
			   	
			   var brands_u = document.getElementById('brand_u');
				 for (i = 0; i <brands_u.options.length; i++) {
				    if(arr[0].trim().toUpperCase()==(brands_u.options[i].value.trim())){
				    	brands_u.options[i].selected= true;
				    }
				 }
			   
				var element_u = document.getElementById('element_u');
				 for (i = 0; i <element_u.options.length; i++) {
					 //alert(element_u.options[i].value.trim()+"=="+arr[1]);
				    if(arr[1].trim().toUpperCase()==(element_u.options[i].value.trim())){
				    	//alert("true");
				    	element_u.options[i].selected= true;
				    }
				 }
			   
				 var component_u = document.getElementById('component_u');
				 for (i = 0; i <component_u.options.length; i++) {
				    if(arr[2].trim().toUpperCase()==(component_u.options[i].value.trim())){
				    	component_u.options[i].selected= true;
				    }
				 }
			   
				 var material_u = document.getElementById('material_u');
				 for (i = 0; i <material_u.options.length; i++) {
				    if(arr[3].trim().toUpperCase()==(material_u.options[i].value.trim())){
				    	material_u.options[i].selected= true;
				    }
				 }
				 
				 var print_u = document.getElementById('print_u');
				 for (i = 0; i <print_u.options.length; i++) {
				    if(arr[5].trim().toUpperCase()==(print_u.options[i].value.trim())){
				    	print_u.options[i].selected= true;
				    }
				 }
				 
				 var set_as_hold_u = document.getElementById('set_as_hold_u');
				 for (i = 0; i <set_as_hold_u.options.length; i++) {
				    if(arr[6].trim().toUpperCase()==(set_as_hold_u.options[i].value.trim())){
				    	set_as_hold_u.options[i].selected= true;
				    }
				 }
				
			
				document.getElementById('qty_u').value=arr[4];
				document.getElementById('Project_element_id').value=Project_element_id;
				document.getElementById('project_id').value=arr[7];
				document.getElementById('project_name').value=arr[8];
			  	document.getElementById('edit_scope_of_work').style.display='block';   
		  }
		};

	ajaxRequest.open("POST", "UpdateElementsScopes.jsp?flag=getElementData&Project_element="+Project_element_id,true);	
	ajaxRequest.send(null); 
	
	
}


function dateplan(id){
	new JsDatePick({

		useMode:2,

		target:""+id.id+"",

		dateFormat:"%d/%m/%Y"

		}); 
	}
	
	


//lOCATION FILTERING FOR UPDATE


function getRegionOnCountryUpdate(){
		//alert('getRegionOnCountryUpdate');
		var Country=document.getElementById('country_name_u').value;
		//alert('getRegionOnCountryUpdate-'+Country);
		ajaxCheck();
		// Create a function that will receive data 
		// sent from the server and will update
		// div section in the same page.
		ajaxRequest.onreadystatechange = function(){
		  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			   var result = ajaxRequest.responseText;
			   result = result.trim();
			 //  alert("RESULT getRegionOnCountryUpdate -"+result);		 
			   document.getElementById('region_update').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=countryUpdate&data="+Country,true);	
		ajaxRequest.send(null);
		
}


function getStateOnRegionUpdate(){
	
	//	alert('getStateOnRegionUpdate');
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
		//	   alert("RESULT getStateOnRegionUpdate -"+result);		 
			   document.getElementById('state_update').innerHTML=ajaxRequest.responseText;
		  }
		};

		ajaxRequest.open("GET", "FilterDropDown.jsp?flag=regionUpdate&data="+region,true);	
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
//		   alert("RESULT getCityOnStateAdd -"+result);		 
		   document.getElementById('city_update').innerHTML=ajaxRequest.responseText;
	  }
	};

	ajaxRequest.open("GET", "FilterDropDown.jsp?flag=stateUpdate&data="+state,true);	
	ajaxRequest.send(null);
}
	
	
</script>
</head>

<!-----   Body   ----->

<!-- div to include header -->
<div><jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Project Elements" />
</jsp:include></div>

<body style="width: 1370px; background-color: #FFFFFF;padding-left: 25px;">
	<div id="maincontent" style="width: 100%;">
		<div id="maincontent_element" style="font: calibri;">
			<div id="Projectdata">				
				<div style="background-color: #CCCCCC; color: darkgray; width: 33%; height: 50px; float: left; margin-top: 3px; margin-bottom: 3px; 
							-webkit-border-top-left-radius: 6px; -webkit-border-bottom-left-radius: 6px; -moz-border-radius-topleft: 6px; -moz-border-radius-bottomleft: 6px; border-top-left-radius: 6px; border-bottom-left-radius: 6px;">
					<table style="font-size: 15px;">
						<tr>
							<td width="110px">Project No</td>
							<td>:</td>
							<td id="data"><%=row_data[0]%></td>
						</tr>
						<tr>
							<td>Project Name</td>
							<td>:</td>
							<td id="data"><%=row_data[1]%></td>
						</tr>	
					</table>
				</div>
				<div style="background-color: #CCCCCC; color: darkgray;text-align: left; height: 50px; width: 33%; float: left; margin-top: 3px; margin-bottom: 3px;">
					<table style="font-size: 15px;">
						<tr>
							<td width="110px">FD Division</td>
							<td>:</td>
							<td id="data"><%=row_data[3]%></td>
						</tr>
						<tr>
							<td>Start Date</td>
							<td>:</td>
							<td id="data"><%=row_data[4]%></td>
						</tr>
		
					</table>
				</div>
				<div style="background-color: #CCCCCC; color: darkgray; width: 34%; text-align: left; height: 50px; float: left; margin-top: 3px; margin-bottom: 3px; 
							-webkit-border-top-right-radius: 6px; -webkit-border-bottom-right-radius: 6px; -moz-border-radius-topright: 6px; -moz-border-radius-bottomright: 6px; border-top-right-radius: 6px; border-bottom-right-radius: 6px;" >
					<table style="font-size: 15px;">
						<tr id="client_name">
							<td>Client Name</td>
							<td>:</td>
							<td><%=row_data[2]%></td>
						</tr>
						<tr>
							<td>End Date</td>
							<td>:</td>
							<td id="data"><%=row_data[5]%></td>
						</tr>
					</table>
				</div>
				<div style="clear:left"> </div>
			</div>
			<div id="subMenu" style="margin: 5px 0px 5px 0px; font-size: 11px;">
				<ul class="menu" id="menu">
					<li><a href="#" onclick="project_details(<%=id%>);" class="submenulink">Project Header</a></li>			
					      <li><a href="DocumentLibrary.jsp?projectId=<%=id%>" 
		class="submenulink">Document Library</a></li>		
					<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>" class="submenulink">Element And Scope</a></li>					
					<li><a href="StoreProjectList.jsp?id=<%=id%>" class="submenulink">Location</a> </li>
					<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>" class="submenulink">Financial</a></li>
				</ul>
			</div>
			<div style="clear:left"> </div>
			<div id="cover_ele_previous">
				<h3 style=" margin-top: 5px; background-color: #e0e0e0; font-size: 16px;color: white;" >Element Type Details</h3>
				<form name="myElementDisplayForm" id="myElementDisplayForm" action="">
					<div style=" float: left;z-index:1;" > 
						<% 
							if(project_element_scopes.equals(Constants.NO_DATA)){
						%> 		<input style="width: 110px;" type="button" value="Add Element"	onClick="$(this).ProSDisplayNew(<%=id %>);"> 
						<%	
							}else{
						%> <input style="width: 110px;" type="button" value="Add Element" onClick="$(this).ProSDisplay(<%=id %>);"> <%
							}
						%>
						<input style="width: 110px;" type="button" value="Delete Element" onclick="return deleteElements(<%=id%>)">										
					</div>

			<div id="display_scope_table" style=" font-size: 12px; clear: left;">
				<!-- Display Data Elements for a Project -->
				<div id="view_elements">				
				<%
					if(project_element_scopes.equals(Constants.NO_DATA)) {
				%>
						<table align="center" style=" width: 100%; text-align: left;margin-top: 0px;" id="example1" class="display">
							<thead>
								<tr>
									<th width="1%">SELECT</th>
									<th width="1%">EDIT</th>
									<th width="1%">SCOPE</th>									
									<th width="10%">ELEMENT NAME</th>
									<th width="3%">SET AS HOLD</th>
									<th width="5%">BRAND</th>
									<th width="10%">ELEMENT TYPE</th>
									<th width="10%">COMPONENT</th>
									<th width="10%">MATERIAL</th>
									<th width="3%">QTY</th>									
									<th width="7%">PRINT MODE</th>
									<th >Element NO:</th>
									
								</tr>
							</thead>
						</table>
				<%
					}
					else{					
				%>
						<table align="center" style="text-align:left; margin-top: 0px;" id="example" class="display datatables">
							<thead>
								<tr>
									<th style="width: 20x; padding: 2px;">SELECT</th>
									<th style="width: 20x; padding: 2px;">EDIT</th>
									<th style="width: 20x; padding: 2px;">SCOPE</th>									
									<th>ELEMENT NAME</th>
									<th style="width: 60x; padding: 2px;">SET AS HOLD</th>
									<th>BRAND</th>
									<th>ELEMENT TYPE</th>
									<th>COMPONENT</th>
									<th>MATERIAL</th>
									<th>QTY</th>									
									<th>PRINT MODE</th>
									<th>Element No:</th>
								</tr>
							</thead>
							<tbody>
				<%
								for(int i=0;i<project_element_scopes_list.length;i++) {		
									String[] element_scope= project_element_scopes_list[i].split(Constants.columnSeperator);	
									System.out.println("getting value of Project id from list-------===>"+element_scope[6]);
									Project_elements=element_scope[6];
									quantityVal=element_scope[4];
									if(quantityVal.trim().equalsIgnoreCase("0")){
										quantityVal="";
								}
				%>		
								<tr align="left" class="gradeA">
									<td style="width: 20px; padding: 2px; text-align: center;"><input type="checkbox" value="<%=Project_elements%>" name="chkbox[]" id="chkbox"></td>
									<td style="width: 20px; padding: 2px; text-align: center;"><a class="edit" href=""><img alt="edit" src="../images/edit.png"></img></a></td>
									<td style="width: 20px; padding: 2px; text-align: center;"><input  type="radio" id="<%=Project_elements %>" name="scopeid" onclick="ScopeDetails(<%=Project_elements %>)" ></td>
									<td><%= element_scope[9]%></td>
									<td style="width: 60px; padding: 2px; text-align: center;"><%= element_scope[7]%></td>
									<td><%= element_scope[0]%></td>
									<td><%= element_scope[1]%></td>
									<td><%= element_scope[2]%></td>
									<td><%= element_scope[3]%></td>
									<td><%= quantityVal%></td>			
									<td><%= element_scope[5]%></td>
									<td>ElementNo  <%= i%></td>
								</tr>
				<%
						}
					}
								%>

							</tbody>
						</table>
					</div>
					
			<!-- Edit Element-->
<div id="edit_scope_of_work"
	style="margin-top: 10px; margin-left: 910px; width: 450px; display: none;">
<h3 style="color: #39939C;">Update Element Type Details</h3>

<table style="width: 99%; margin-top: 10px; text-align: left;"
	align="center">
	<tr>
		<td>Project Code</td>
		<td><input type="text" id="project_id" name="project_id" style="width: 110px;"></td>
		<td>Element Name</td>
		<td><input type="text" id="project_name" name="project_name" style="width: 110px;"></td>
	</tr>
	<tr>
		<td>BRAND</td>
		<td><input type="hidden" value="<%=Project_elements%>" id="Project_element_id">
		<div id="read_brand">
			<select name="brand_u" id="brand_u" style="width: 110px;">
			<%
			for(int i=0;i<brand_list_data.length;i++){
			%>
			<option value="<%=brand_list_data[i]%>"><%=brand_list_data[i]%></option>
			<%
					}
					%>
		</select></div>
		<!-- <input type="text" id="brand_u" style="width: 110px;"> --></td>
		<td>Element Type</td>
		<td>
		<div id="read_element">
			<select name="element_u" id="element_u"
				style="width: 150px;">
				<%
					for(int i=0;i<element_list_data.length;i++){
				%>
				<option value="<%=element_list_data[i]%>"><%=element_list_data[i]%></option>
				<%
				}
				%>
			</select>
		</div>
		<!-- <input type="text" id="element_u" style="width: 110px;"> --></td>
	</tr>
	<tr>
		<td>COMPONENT</td>
		<td>
		<div id="read_component"><select name="component_u"
			id="component_u" style="width: 150px;">
			<%
				for(int i=0;i<component_list_data.length;i++){
				%>
			<option value="<%=component_list_data[i]%>"><%=component_list_data[i]%></option>
			<%
								}
				%>
		</select></div>
		<!-- <input type="text" id="component_u" style="width: 110px;"> --></td>
		<td>MATERIAL</td>
		<td>
		<div id="read_material"><select name="material_u"
			id="material_u" style="width: 200px;">
			<%
							for(int i=0;i<material_list_data.length;i++){
					%>
			<option value="<%=material_list_data[i]%>"><%=material_list_data[i]%></option>
			<%
										}
					%>
		</select></div>
		<!-- <input type="text" id="material_u" style="width: 110px;"> --></td>
	</tr>
	<tr>
		<td>QUANTITY</td>
		<td><input type="text" id="qty_u" style="width: 50px;">
		</td>

		<td>PRINT MODE</td>
		<td>
		<div id="read_print"><select name="print_u" id="print_u"
			style="width: 110px;">
			<%
							for(int i=0;i<print_mode_list_data.length;i++){
					%>
			<option value="<%=print_mode_list_data[i]%>"><%=print_mode_list_data[i]%></option>
			<%
							}
					%>
		</select></div>
		
	</tr>
	<tr>


		<td>SET AS HOLD</td>
		<td><!-- <input type="text" id="set_as_hold_u" style="width: 110px;"> -->
		<div id="read_set"><select name="set_as_hold_u"
			id="set_as_hold_u" style="width: 70px;">
			<option value="NO">NO</option>
			<option value="YES">YES</option>
		</select></div>
		</td>
	</tr>
	
	<tr>
		<td><input type="hidden" value="<%=id%>" id="hidden_Project_id">


		</td>
		<td></td>
		<td><input type="button" value="Update" style="width: 80px;"
			onclick="return UpdateElements(<%=id%>);"></td>
		<td><input type="button" value="Cancel" style="width: 80px;"
			onclick="CancelElements();"></td>
	</tr>
</table>

</div>

</div>

</form>
</div>

<div id="element_scope_date" style="width: 99%; display: none;">


<div id="measured_title"
	style="color: white; font-weight: bold; height: 20px; background-color: #e0e0e0; font-size: 16px;">
Scope Of Work</div>

<!-- <div id="measured_title" style="color: #A90B0B; font: 13px;">Measurement
							Sheet</div> -->

<div id="add_delete_button" align="left" style="width: 235px; z-index: 1">

	<input type="button" value="Add Scope" onclick="AddScopeOfWork();" style="width: 110px;">
	
<input	type="button" value="Delete Scope" onclick="DeleteScopeOfWork();" style="width: 110px;"> 
	
</div>

<div id="scope_elements_details" style="margin-top:0px;">
<table style="color: #39939C; border: 1px; width: 100%; text-align: left;">
	<tr>
		<td>
		<form name="myDisplayForm" id="myDisplayForm" style="width: 1337px;">
		<div id="measured_items" style="font-size: 11px;">

		<table style="color: #39939C; border: 1px; width: 100%;text-align: left;" id="scope_example">
			<thead>
				<tr>
					<th colspan="12"></th>
					<th colspan="4">Client Date</th>
					<th colspan="4">FD Date</th>
				</tr>

				<tr>
					<th colspan="12"></th>
					
					<th colspan="2">TARGET</th>
					<th colspan="2">ACTUAL</th>
					<th colspan="2">TARGET</th>
					<th colspan="2">ACTUAL</th>

				</tr>

				<tr>

					<th>Edit</th>
					<th>Region</th>
					<th>State</th>
					<th>Trade</th>
					<th>Chain</th>
					<th>City</th>
					<th>#Store/Qty</th>
					<th>Scope Of Work</th>
					<th>Client Approval Req</th>
					<th>FD Hub</th>
					<th>Responsible Person</th>

					<th>Start Date</th>
					<th>End Date</th>

					<th>Start Date</th>
					<th>End Date</th>

					<th>Start Date</th>
					<th>End Date</th>

					<th>Start Date</th>
					<th>End Date</th>



				</tr>
			</thead>
		</table>
		</div>
		</form>
		<div id="add_scope_elements"
			style="display: none; border: #778 2px solid; background: #e0e0e0; margin: 20px 0px 50px 10px; border-radius: 10px; -webkit-border-radius: 10px; -moz-border-radius: 10px;">

		<div
			style="color: #39939C; font-size: 17px; margin-top: 1px; font-weight: bold;">Add Scope</div>

		<form id="myAddForm">
		<table style="width: 83%; text-align: left; margin-left: 10px;">
			<tr>
				<td>Country</td>
				<td>
				<div id="country_div">
				<select style="width: 115px;"
					onchange="return getRegionOnCountry();" id="country_select">
					<option value="SELECT">SELECT</option>
					<%
					if(country_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_contry=country_list.split(Constants.rowSeperator);
						//System.out.println("country length :: "+result_contry.length);
					for(int i=0;i<result_contry.length;i++){
						//System.out.println("country list :: "+result_contry[i]);
					String[] country_data=result_contry[i].split(Constants.columnSeperator);
				%>
					<option value="<%=country_data[1]%>"><%=country_data[0]%></option>
					<%
					}
				
				}
				%>
				</select>
				</div>
				
				</td>

				<td>Region</td>
				<td>

				<div id="select_region"><input type="text" id="region_select"
					name="region_select" style="width: 115px;"></div>
				</td>

				<td>State</td>
				<td>
				<div id="select_state"><input type="text" id="state_select"
					name="state_select" style="width: 115px;"></div>
				</td>

				<td>City</td>
				<td>
				<div id="select_city"><input type="text" id="city_select"
					name="city_select" style="width: 100px;"></div>
				</td>

				<td>Trade</td>
				<td>
				<div id="select_trade"><select style="width: 100px;"
					id="trade_id">
					<option value="SELECT">SELECT</option>
					<%
					if(trade_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_trade=trade_list.split(Constants.rowSeperator);
						
					for(int i=0;i<result_trade.length;i++){
						//System.out.println("result_trade list :: "+result_trade[i]);
					String[] trade_data=result_trade[i].split(Constants.columnSeperator);
				%>
					<option value="<%=trade_data[1]%>"><%=trade_data[0]%></option>
					<%
						}
					
					}
				%>
				</select> <!-- <input type="text" id="trade_id" name="trade_id" style="width: 115px;"> -->
				</div>
				</td>

				<td>Chain</td>
				<td>
				<div id="select_chain"><select style="width: 100px;"
					id="chain_id">
					<option value="SELECT">SELECT</option>
					<%
					if(chain_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_chain=chain_list.split(Constants.rowSeperator);
						
					for(int i=0;i<result_chain.length;i++){
						//System.out.println("result_trade list :: "+result_chain[i]);
					String[] chain_data=result_chain[i].split(Constants.columnSeperator);
				%>
					<option value="<%=chain_data[1]%>"><%=chain_data[0]%></option>
					<%
						}
					
					}
				%>
				</select></div>
				</td>
			</tr>
		</table>
		<table style="width: 83%; text-align: left; margin-left: 10px;">
			<tr>
				<td>No Of Store</td>
				
				<td><input type="text" id="no_of_store" name="no_of_store"></td>

				<td>Scope Of Work</td>
				<td>
				<div id="ScopeOfWork_div">
				<select style="width: 115px;" id="scope_of_work">
					<option value="SELECT">SELECT</option>
					<%
					if(scope_list_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_scope=scope_list_list.split(Constants.rowSeperator);
					//	System.out.println("country length :: "+result_scope.length);
					for(int i=0;i<result_scope.length;i++){
					//	System.out.println("country list :: "+result_scope[i]);
					String[] scope_data=result_scope[i].split(Constants.columnSeperator);
				%>
					<option value="<%=scope_data[1]%>"><%=scope_data[0]%></option>
					<%
						}
					
					}
				%>
				</select>
				</div>
				</td>
				<td>Client-Approval Required</td>
				<td><select id="client_approval_req" name="client_approval_req">

					<option value="NO">NO</option>
					<option value="YES">YES</option>
				</select> 
				</td>
				<td><input type="hidden" id="Project_element_scope"></td>

				<td>FD HUB</td>
				<td>
				<div id="fd_add_div">
				<select name="fd_hub" id="fd_hub" style="width: 100px;">
					<option value="SELECT">SELECT</option>
					<%		
			String dropdownhub=services.drop_down_fdhubID();
			String[] resulthub=dropdownhub.split(Constants.rowSeperator);
			for(int i=0;i<resulthub.length;i++)
			{	
				String columndata[]=resulthub[i].split(Constants.columnSeperator);
				%>
			<option value="<%=columndata[1]%>"><%=columndata[0]%></option>
					<%	
			}
	 	%>
				</select>
				</div>
				</td>
				<td>Responsible Person</td>
				<td><input type="text" id="responsible_person"
					style="width: 110px;"></td>

			</tr>
		</table>
		<table style="width: 80%; margin-left: 10px;" border="1px">
			<tr>
				<td colspan="4">Client Date</td>
				<td colspan="4">FD Date</td>
			</tr>

			<tr>
				<!--<td colspan="2">National Plan</td>
					<td colspan="2">Regional Plan</td>
					<td colspan="2">National Plan</td>
					<td colspan="2">Regional Plan</td>-->
				<td colspan="2">TARGET</td>
				<td colspan="2">ACTUAL</td>
				<td colspan="2">TARGET</td>
				<td colspan="2">ACTUAL</td>

			</tr>
			<tr>
				<td>Target</td>
				<td>Actual</td>

				<td>Target</td>
				<td>Actual</td>

				<td>Target</td>
				<td>Actual</td>

				<td>Target</td>
				<td>Actual</td>
			</tr>

			<tr>
				<td><input type="text" id="cl_np_tar" onclick="cl_np_tar();">
				</td>
				<td><input type="text" id="cl_np_act" onclick="cl_np_act();">
				</td>

				<td><input type="text" id="cl_reg_tar" onclick="cl_reg_tar();">
				</td>
				<td><input type="text" id="cl_reg_act" onclick="cl_reg_act();">
				</td>

				<td><input type="text" id="fd_np_tar" onclick="fd_np_tar();">
				</td>
				<td><input type="text" id="fd_np_act" onclick="fd_np_act();">
				</td>

				<td><input type="text" id="fd_reg_tar" onclick="fd_reg_tar();">
				</td>
				<td><input type="text" id="fd_reg_act" onclick="fd_reg_act();">
				</td>
			</tr>
		</table>

		</form>
		<span
			style="position: relative; display: block; margin: 5px 0 0 20px;">
		<input style="width: 140px;" type="button"
			onclick="insertScopeforElement();" value="Insert Scope" /> <span
			style="display: inline; margin: 0px 0 0 140px;"> <input
			style="width: 140px;" type="button" onclick="CancelAddScopeOfWork();"
			value="Cancel" /> </span> </span></div>



		<div id="update_scope_element"
			style="display: none; border: 2px solid rgb(119, 119, 136); background-color: rgb(224, 224, 224); margin: 20px 0px 50px 10px; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border-bottom-left-radius: 10px;">
		<div
			style="color: #39939C; font-size: 17px; margin-top: 1px; font-weight: bold;">
		Update Scope Details</div>

		<form id="scopeUpdateForm" name="scopeUpdateForm">
		<table style="width: 86%; text-align: left; margin-left: 10px;">
			<tr>
				<td>Country</td>
				<td>
				<div id="country_update"><select style="width: 115px;"
					id="country_name_u" onchange="return getRegionOnCountryUpdate();">
					<option value="SELECT">SELECT</option>
					<%
						if(country_list.equals(Constants.NO_DATA)){
						%>
					<option value="NODATA">NO DATA</option>
					<%	
						}
						else{
						String[] result_contry=country_list.split(Constants.rowSeperator);
						//	System.out.println("country length :: "+result_contry.length);
						for(int i=0;i<result_contry.length;i++){
							//System.out.println("country list :: "+result_contry[i]);
						String[] country_data=result_contry[i].split(Constants.columnSeperator);
						%>
					<option value="<%=country_data[1]%>"><%=country_data[0]%></option>
					<%
							}
						
						}
						%>
				</select></div>
				</td>

				<td>Region</td>
				<td>
				<div id="region_update"><select id="region_name_u"
					name="region_name_u" style="width: 115px;">
				</select></div>
				</td>

				<td>State</td>
				<td>
				<div id="state_update"><select style="width: 115px;"
					id="state_name_u" name="state_name_u">
				</select></div>
				</td>

				<td>City</td>
				<td>
				<div id="city_update"><select style="width: 115px;"
					id="city_name_u" name="city_name_u">
				</select></div>
				</td>

				<td>Trade</td>
				<td>
				<div id="select_trade_u"><select style="width: 115px;"
					id="trade_id_u">
					<option value="SELECT">SELECT</option>
					<%
					if(trade_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_trade=trade_list.split(Constants.rowSeperator);
						
					for(int i=0;i<result_trade.length;i++){
					//	System.out.println("result_trade list :: "+result_trade[i]);
					String[] trade_data=result_trade[i].split(Constants.columnSeperator);
				%>
					<option value="<%=trade_data[1]%>"><%=trade_data[0]%></option>
					<%
						}
					
					}
				%>
				</select> <!-- <input type="text" id="trade_id" name="trade_id" style="width: 115px;"> -->
				</div>
				</td>

				<td>Chain</td>
				<td>
				<div id="select_chain_u"><select style="width: 115px;"
					id="chain_id_u">
					<option value="SELECT">SELECT</option>
					<%
					if(chain_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_chain=chain_list.split(Constants.rowSeperator);
						
					for(int i=0;i<result_chain.length;i++){
					//	System.out.println("result_trade list :: "+result_chain[i]);
					String[] chain_data=result_chain[i].split(Constants.columnSeperator);
				%>
					<option value="<%=chain_data[1]%>"><%=chain_data[0]%></option>
					<%
						}
					
					}
				%>
				</select></div>
				</td>
			</tr>
		</table>

		<table style="width: 86%; text-align: left; margin-left: 10px;">
			<tr>
				<td>No Of Store</td>
				
				<td>
					<div id="no_of_store_u_div">
						<input type="text" id="no_of_store_u" name="no_of_store_u">
					</div>
				</td>
				
				<td>Scope Of Work</td>
				<td>
				<div id="scope_of_work_u_div">
				<select style="width: 115px;" id="scope_of_work_u">
					<option value="SELECT">SELECT</option>
					<%
					if(scope_list_list.equals(Constants.NO_DATA)){
					%>
					<option value="NODATA">NO DATA</option>
					<%	
					}
					else{
					String[] result_scope=scope_list_list.split(Constants.rowSeperator);
						//System.out.println("country length :: "+result_scope.length);
					for(int i=0;i<result_scope.length;i++){
						//System.out.println("country list :: "+result_scope[i]);
					String[] scope_data=result_scope[i].split(Constants.columnSeperator);
				%>
					<option value="<%=scope_data[1]%>"><%=scope_data[0]%></option>
					<%
						}
					
					}
				%>
				</select>
				</div>
				</td>
				<td>Client-Approval Required</td>
				<td><div id="client_approval_req_u_div">
				<select id="client_approval_req_u"
					name="client_approval_req_u" style="width: 110px;">

					<option value="NO">NO</option>
					<option value="YES">YES</option>
				</select> <!-- <input type="text" id="client_approval_req_u" name="client_approval_req_u"> -->
				</div>
				</td>
				<td>
				<div id="scope_id_u_div">
				<input type="hidden" id="scope_id_u" name="scope_id_u">
				</div>
				</td>

				<td>FD HUB</td>
				<td>
				<div id="fd_hub_u_div">
				<select name="fd_hub_u" id="fd_hub_u" style="width: 110px;">
					<option value="SELECT">SELECT</option>
					<%		
			dropdownhub=services.drop_down_fdhubID();
			resulthub=dropdownhub.split(Constants.rowSeperator);
			for(int i=0;i<resulthub.length;i++)
			{	
				String columndata[]=resulthub[i].split(Constants.columnSeperator);
				%>
					<option value="<%=columndata[1]%>"><%=columndata[0]%></option>
					<%	
			}
	 	%>
				</select>
				</div>
				</td>
				<td>Responsible Person</td>
				<td>
				<div id="responsible_person_u_div">
				<input type="text" id="responsible_person_u" name="responsible_person_u"
					style="width: 110px;">
				</div>	
				</td>
				
			</tr>
		</table>
		<table style="width: 80%; margin-left: 10px;" border="1px">
			<tr>
				<td colspan="4">Client Date</td>
				<td colspan="4">FD Date</td>
			</tr>

			<tr>
				<!--<td colspan="2">National Plan</td>
									<td colspan="2">Regional Plan</td>
									<td colspan="2">National Plan</td>
									<td colspan="2">Regional Plan</td>-->
				<td colspan="2">TARGET</td>
				<td colspan="2">ACTUAL</td>
				<td colspan="2">TARGET</td>
				<td colspan="2">ACTUAL</td>
			</tr>
			<tr>
				<td>Target</td>
				<td>Actual</td>

				<td>Target</td>
				<td>Actual</td>

				<td>Target</td>
				<td>Actual</td>

				<td>Target</td>
				<td>Actual</td>
			</tr>

			<tr>
				<td>
				<div id="cl_np_tar_u_div">
				<input type="text" id="cl_np_tar_u" onclick="cl_np_tar();">
				</div>
				</td>
				
				<td>
				<div id="cl_np_act_u_div">
				<input type="text" id="cl_np_act_u" onclick="cl_np_act();">
				</div>
				</td>

				<td>
				<div id="cl_reg_tar_u_div">
				<input type="text" id="cl_reg_tar_u" onclick="cl_reg_tar();">
				</div>	
				</td>
				
				<td>
				<div id="cl_reg_act_u_div">
				<input type="text" id="cl_reg_act_u" onclick="cl_reg_act();">
				</div>
				</td>

				<td>
				<div id="fd_np_tar_u_div">
				<input type="text" id="fd_np_tar_u" onclick="fd_np_tar();">
				</div>
				</td>
				<td>
				<div id="fd_np_act_u_div">
				<input type="text" id="fd_np_act_u" onclick="fd_np_act();">
				</div>
				</td>

				<td>
				<div id="fd_reg_tar_u_div">
				<input type="text" id="fd_reg_tar_u" onclick="fd_reg_tar();">
				</div>	
				</td>
				
				<td>
				<div id="fd_reg_act_u_div">
				<input type="text" id="fd_reg_act_u" onclick="fd_reg_act();">
				</div>
				</td>
			</tr>
		</table>
		</form>
		<span
			style="position: relative; display: block; margin: 5px 0 0 20px;">
		<input style="width: 140px;" type="button"
			onclick="return updateScopeforElement();" value="Update Scope" /> <span
			style="display: inline; margin: 0px 0 0 140px;"> <input
			style="width: 140px;" type="button" onclick="CancelAddScopeOfWork();"
			value="Cancel" /> </span> </span></div>
		</td>

	</tr>
</table>
</div>




</div>

</div>
<!-- </td>

			</tr>
		</table> --></div>
<script type="text/javascript" src="../js/Connect/jquery.js"></script>
<script type="text/javascript"
	src="../js/Connect/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			
			var oTable = $('#example').dataTable({
					//'iDisplayLength': 5,
					"aaSortingFixed": [[11,'asc']],//column number,ascending order					
					"bPaginate": false,
					"bInfo": false, 
		            "bLengthChange": false,		            
		            "bProcessing" : true,
		            "sScrollY" : "200",
		            "sScrollX" : "100%",
		            "aoColumns": [ {"bSortable": false}, {"bSortable": false},
		                           {"bSortable": false},null,null,null,null,null,
				              		null,null,null,{"bVisible": false} ]
			            
			});
			
			var oTable1=$('#example1').dataTable({
			//	'iDisplayLength': 5,	
				"aaSortingFixed": [[11,'asc']],//column number,ascending order
				"bFilter": false,
				 "bPaginate": false,
	            'bLengthChange': false,
	            "bSortClasses": false,
	            "bProcessing" : true,
	            "sScrollY" : "200",
	            "sScrollX" : "100%",   
	            	 "aoColumns": [ {"bSortable": false}, {"bSortable": false},
			                           {"bSortable": false},null,null,null,null,null,
					              		null,null,null,{"bVisible": false} ]
			});
					
	//	alert(oTable.fnGetData().length);
		
				
		//$('#scope_example').dataTable();
			
			
			//INLINE EDITING
			var nEditing = null;
			
			$.fn.ProSDisplayNew = function(e) {

			
		        //  e.preventDefault();
		    	var aiNew = oTable1.fnAddData( [ '<input type="checkbox" name="chkbox[]" id="chkbox">',
				                                "<a class='' onclick='return save_Elementdetails("+e+");' href=''>Save</a>/<br><a class='' href=''>Cancel</a>" ,
				                                "<input type='radio'  name='scopeid'>",'','','','','','','','','',''] );
		//		alert("********aiNew*******"+aiNew[0]);
				
				var nRow = oTable1.fnGetNodes( aiNew[0] );
			
				displayInputBox(oTable1, nRow);
				
		    };
		    
		    
		    $.fn.ProSDisplay = function(e) {
		     //  e.preventDefault();
		     
		     		
		    	var aiNew = oTable.fnAddData( [ '<input type="checkbox" name="chkbox[]" id="chkbox">',
				                                "<a class='' onclick='return save_Elementdetails("+e+");' href=''>Save</a>/<br><a class='' href=''>Cancel</a>" ,
				                                "<input type='radio'  name='scopeid'>",'','','','','','','','',''] );
				
				var nRow = oTable.fnGetNodes( aiNew[0] );
				
				displayInputBox(oTable, nRow);
				  
		    };

		    function restoreRow ( oTable, nRow )
			{
			console.log("restoreRow");
				var aData = oTable.fnGetData(nRow);
				console.log("aData=="+aData);
				var jqTds = $('>td', nRow);
				console.log("restoreRow------------jqTds=="+jqTds);
				for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
					oTable.fnUpdate( aData[i], nRow, i, false );
				}
				
				oTable.fnDraw();
			}
			  
				function displayInputBox(oTable, nRow){

					var aData = oTable.fnGetData(nRow);
					var jqTds = $('>td', nRow);
					
					var jqInputs = $('input', nRow);
					
					//Project Name
					jqTds[3].innerHTML = '<input type="text" value="'+aData[3]+'"  id=projectName class="reduce_inputTag" >';
					
					
					//Set As Hold: previous value=aData[5] ;
					jqTds[4].innerHTML = document.getElementById("read_set").innerHTML.toString().trim();
					
					 var set_as_hold_u = document.getElementById('set_as_hold_u');
					 for (i = 0; i <set_as_hold_u.options.length; i++) {
					    if(aData[4].trim().toUpperCase()==(set_as_hold_u.options[i].value.trim())){
					    	set_as_hold_u.options[i].selected= true;
					    }
					 }
					 
					
					//brand : previous value=aData[6];
					 
					jqTds[5].innerHTML =document.getElementById("read_brand").innerHTML.toString().trim();
					
					
					var brands_u = document.getElementById('brand_u');
					 for (i = 0; i <brands_u.options.length; i++) {
					    if(aData[5].trim().toUpperCase()==(brands_u.options[i].value.trim())){
					    	brands_u.options[i].selected= true;
					    }
					 }
					
					
					//Element : previous value=aData[7];
					jqTds[6].innerHTML =document.getElementById("read_element").innerHTML ;
					
					var element_u = document.getElementById('element_u');
					 for (i = 0; i <element_u.options.length; i++) {
					    if(aData[6].trim().toUpperCase()==(element_u.options[i].value.trim())){
					    	element_u.options[i].selected= true;
					    }
					 }
					
					
					//Component: previous value=aData[8];
					 
					jqTds[7].innerHTML =document.getElementById("read_component").innerHTML ;
					
					var component_u = document.getElementById('component_u');
					 for (i = 0; i <component_u.options.length; i++) {
					    if(aData[7].trim().toUpperCase()==(component_u.options[i].value.trim())){
					    	component_u.options[i].selected= true;
					    }
					 }
					
					//Material: previous value= aData[9];
					jqTds[8].innerHTML =document.getElementById("read_material").innerHTML ;
					
					var material_u = document.getElementById('material_u');
					 for (i = 0; i <material_u.options.length; i++) {
					    if(aData[8].trim().toUpperCase()==(material_u.options[i].value.trim())){
					    	material_u.options[i].selected= true;
					    }
					 }
					 
					//quantity
					
					//if(aData[10]!=''){
					jqTds[9].innerHTML = '<input type="text" value="'+aData[9]+'" id=quan style="width:40px;">';
					
					//print mode
					jqTds[10].innerHTML = document.getElementById("read_print").innerHTML;
					   
					var print_u = document.getElementById('print_u');
					 for (i = 0; i <print_u.options.length; i++) {
					    if("NA"==(print_u.options[i].value.trim())){
					    	print_u.options[i].selected= true;
					    }
					} 
					 document.getElementById('projectName').focus();		
				}
			
			 
			$('#example a.edit').live('click', function (e) {
				e.preventDefault();
				 
				/* Get the row as a parent of the link that was clicked on */
				var nRow = $(this).parents('tr')[0];
				 
				if ( nEditing !== null && nEditing != nRow ) {
					/* A different row is being edited - the edit should be cancelled and this row edited */
					restoreRow( oTable, nEditing );
					editRow( oTable, nRow );
					nEditing = nRow;
				}
				else if ( nEditing == nRow && this.innerHTML == "Save" ) {
					/* This row is being edited and should be saved */
					saveRow( oTable, nEditing );
				//	alert("saveRow11111");
					nEditing = null;
				}
				else if(nEditing==nRow && this.innerHTML=="Cancel"){
					restoreRow( oTable, nEditing );
					nEditing = null;
					}
				else {
					/* No row currently being edited */
					editRow( oTable, nRow );
					nEditing = nRow;
				}
			} );
			
			
			
			
	function editRow(oTable, nRow )
	{	
		
		// alert(nRow.toString()+"nRow");
		var aData = oTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);
	
		var jqInputs = $('input', nRow);
		//value(Element_id)=jqInputs[0].value;
		//alert("\n jqInputs[0].value="+jqInputs[0].value);

		
		 var ajaxRequest = null;
		
		// The variable that makes Ajax possible!		
		try {
			// Opera 8.0+, Firefox, Safari0
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
		
		ajaxRequest.onreadystatechange = function(){
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				 
				   var result = ajaxRequest.responseText;
				   result = result.trim();
				  // alert("RESULT ---> "+result);
				   var arr=result.split("#&#");
				   
				   
				   
				   
				//jqTds[0].innerHTML = "<input type='checkbox' value='"+aData[0]+"' name='chkbox[]' id='chkbox'>";
				jqTds[1].innerHTML = "<a class='edit' href=''>Save</a>/<br><a class='edit' href=''>Cancel</a>";
				//jqTds[2].innerHTML =aData[2];
				
				//Project No
				//jqTds[3].innerHTML = '<input type="text" id="project_id" value="'+aData[3]+'" class="reduce_inputTag"> ';
				//Project Name
				jqTds[3].innerHTML = '<input type="text" id="project_name" value="'+aData[3]+'" class="reduce_inputTag" >';
				
				//Set As Hold: previous value=aData[5] ;
				
				
				jqTds[4].innerHTML = document.getElementById("read_set").innerHTML.toString().trim();
				/* jqTds[5].innerHTML = '<select name="set_as_hold_u" id="set_as_hold_u" style="width: 110px;">'+
										'<option value="YES">YES</option>'+
										'<option value="NO">NO</option>'+
									'</select>'; */
			
				 var set_as_hold_u = document.getElementById('set_as_hold_u');
				 for (i = 0; i <set_as_hold_u.options.length; i++) {
				    if(aData[4].trim().toUpperCase()==(set_as_hold_u.options[i].value.trim())){
				    	set_as_hold_u.options[i].selected= true;
				    }
				 }						
				
				//brand : previous value=aData[6];
				 
				//jqTds[6].innerHTML = '<input type="text" value="'+aData[6]+'" class="reduce_inputTag">';
				
			//	console.log("read brand=="+document.getElementById("read_brand").innerHTML.toString().trim());
				jqTds[5].innerHTML =document.getElementById("read_brand").innerHTML.toString().trim();
				
				var brands_u = document.getElementById('brand_u');
				 for (i = 0; i <brands_u.options.length; i++) {
				    if(aData[5].trim().toUpperCase()==(brands_u.options[i].value.trim())){
				    	brands_u.options[i].selected= true;
				    }
				 }
				
				
				//Element : previous value=aData[7];
				
				//jqTds[7].innerHTML = '<input type="text" value="'+aData[7]+'" class="reduce_inputTag">';
				
				jqTds[6].innerHTML =document.getElementById("read_element").innerHTML ;
				
				var element_u = document.getElementById('element_u');
				 for (i = 0; i <element_u.options.length; i++) {
				    if(aData[6].trim().toUpperCase()==(element_u.options[i].value.trim())){
				    	element_u.options[i].selected= true;
				    }
				 }
				
				
				//Component: previous value=aData[8];
				
				 //jqTds[8].innerHTML = '<input type="text" value="'+aData[8]+'" class="reduce_inputTag">';
				 
				jqTds[7].innerHTML =document.getElementById("read_component").innerHTML ;
				
				var component_u = document.getElementById('component_u');
				 for (i = 0; i <component_u.options.length; i++) {
				    if(aData[7].trim().toUpperCase()==(component_u.options[i].value.trim())){
				    	component_u.options[i].selected= true;
				    }
				 }
				
				//Material: previous value= aData[9];
				
				//jqTds[9].innerHTML = '<input type="text" value="'+aData[9]+'" style="width: 200px;">';
				
				jqTds[8].innerHTML =document.getElementById("read_material").innerHTML ;
				
				var material_u = document.getElementById('material_u');
				 for (i = 0; i <material_u.options.length; i++) {
				    if(aData[8].trim().toUpperCase()==(material_u.options[i].value.trim())){
				    	material_u.options[i].selected= true;
				    }
				 }
				 
				//quantity
				jqTds[9].innerHTML = '<input type="text" id="qty_u" value="'+aData[9]+'" style="width:40px;">';
				
				
				//PrintMode previous valiue=aData[11];
				//jqTds[11].innerHTML = '<input type="text" value="'+aData[11]+'" class="reduce_inputTag">';
				jqTds[10].innerHTML = document.getElementById("read_print").innerHTML;
				   
				var print_u = document.getElementById('print_u');
				 for (i = 0; i <print_u.options.length; i++) {
				    if(aData[10].trim().toUpperCase()==(print_u.options[i].value.trim())){
				    	print_u.options[i].selected= true;
				    }
				} 
				   
				 
				/* 
					document.getElementById('qty_u').value=arr[4];
					document.getElementById('Project_element_id').value=Project_element_id;
					document.getElementById('project_id').value=arr[7];
					document.getElementById('project_name').value=arr[8];
				  	document.getElementById('edit_scope_of_work').style.display='block';  */  
			  }
			};

		ajaxRequest.open("POST", "UpdateElementsScopes.jsp?flag=getElementData&Project_element="+'<%=Project_elements%>',true);	
		ajaxRequest.send(null);  
		
		
	}
	
	
	function saveRow( oTable, nRow )
	{
		//alert('SaveRow called..');
		var jqInputs = $('input', nRow);
		var jqSelect = $('select', nRow);
		
		
		
		for(var i=0;i<jqInputs.length;i++){
			console.log("i ::"+i+" jqInputs   "+jqInputs[i].value);
		}

		for(var j=0;j<jqSelect.length;j++){
			console.log("j ::"+j+" jqSelect   "+jqSelect[j].value);
		}

		
	//	alert("for submit");
	//	var project_id=jqInputs[1].value;
	/** var project_id=document.getElementById('project_id').value.trim();
	//	 alert("project_id==="+project_id);
		 if(project_id==''){
			alert('PROJECT ID CAN NOT BE EMPTY ');
			return false;
	}**/
		
		//project Name
	//	var project_name=jqInputs[2].value;
		var project_name=document.getElementById('project_name').value.trim();
	//	alert("project_name=="+project_name);
		if(project_name==''){
			alert('PROJECT NAME CAN NOT BE EMPTY ');
			return false;
		}
		
		//Set As Hold
	//	var set_as_hold_u=jqSelect[0].value;
		var set_as_hold_u=document.getElementById('set_as_hold_u');
		set_as_hold_u=set_as_hold_u.options[set_as_hold_u.selectedIndex].value;
	//	alert("set_as_hold_u=="+set_as_hold_u);
		if(set_as_hold_u==''){
			alert('SET AS HOLD CAN NOT BE EMPTY ');
			return false;
		}
		
		//Brand
		//var brand_u=jqSelect[1].value;
		var brand_u=document.getElementById('brand_u');
		brand_u=brand_u.options[brand_u.selectedIndex].value;
	//	alert("brand_u=="+brand_u);
		if(brand_u==''){
			alert('BRAND CAN NOT BE EMPTY ');
			return false;
		} 
		
		
		//Element
		 //var element_u=jqSelect[2].value;
		 var element_u=document.getElementById('element_u');
		 element_u=element_u.options[element_u.selectedIndex].value;
		// alert("Element_u=="+element_u);
		if(element_u==''){
			alert('ELEMENT CAN NOT BE EMPTY ');
			return false;
		} 
		
		
		
		//component
		//var component_u=jqSelect[3].value; 
		var component_u=document.getElementById('component_u'); 
		component_u=component_u.options[component_u.selectedIndex].value;
	//	alert("component_u=="+component_u);
		if(component_u==''){
			alert('COMPONENT CAN NOT BE EMPTY ');
			return false;
		}
		
		
		//Material
		//var material_u=jqSelect[4].value;
		var material_u=document.getElementById('material_u');
		material_u=material_u.options[material_u.selectedIndex].value;
	//	alert("material_u=="+material_u);
		if(material_u==''){
			alert('MATERIAL CAN NOT BE EMPTY ');
			return false;
		}
		
		
		//quantity
		//var qty_u=jqInputs[3].value;
		 var qty_u=document.getElementById('qty_u').value.trim();
		 if(qty_u===""){
			//alert("QUANTITY CAN'T BE EMPTY OR ZERO");
			qty_u=0;
			//return false;
		} 
		if(qty_u<0){
			alert("QUANTITY CAN'T BE NEGATIVE");
			return false;
		}
		
		/* if(isNaN(qty_u)||qty_u.indexOf(" ")!=-1)
	    {
	       alert("QUANTITY NEED TO BE NUMERIC VALUE !!");
	       return false; 
	    }
		 */
		
		//Print mode
		//oTable.fnUpdate( jqInputs[9].value, nRow, 11, false );
		//var print_u= jqSelect[5].value;
		var print_u=document.getElementById('print_u');
		print_u=print_u.options[print_u.selectedIndex].value;
		if(print_u==''){
			alert('PRINTMODE CAN NOT BE EMPTY ');
			return false;
		}
		
		//oTable.fnDraw();
		
		
		var columnSeperator="@!@";

		
		var ajaxRequest = null;
		
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
		
		
		var data=brand_u+columnSeperator+element_u+columnSeperator+component_u+columnSeperator+
		material_u+columnSeperator+qty_u+columnSeperator+print_u+columnSeperator+
		set_as_hold_u+columnSeperator+project_name;
		

		ajaxRequest.onreadystatechange = function(){
			  if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
				 
				   var result = ajaxRequest.responseText;
				   result = result.trim();

			if(result=="DATA_UPDATED"){
				oTable.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 1, false );
						var Project_id=document.getElementById("hidden_Project_id").value;
						alert('ELEMENT DETAILS UPDATED !!');
				   		window.location.href="ProjectScopeOfWork.jsp?id="+Project_id;   
			  }else
			  {
				 		 alert('ELEMENT DETAILS NOT UPDATED !!');
			  }

		}
		};
			console.log(jqInputs[0].value+"=======jqInputs[0].value");
			ajaxRequest.open("POST", "UpdateElementsScopes.jsp?flag=UpdateElement&data="+data+"&id="+jqInputs[0].value,true);	
			ajaxRequest.send(null);
		
		
	}
			
	
});//end of document.ready
</script>
</body>
</html>