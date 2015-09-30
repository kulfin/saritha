
function validateFile()
{
	//alert('inside validate');
	
/*	 if(document.getElementById("project_select").selectedIndex==0){
		 alert("Please Select Project");  
		 return false;
	   }
	 */
	 if(document.getElementById("brand_select").selectedIndex==0){
		 alert("Please Select Brand");  
		 return false;
	   }
	 
	 if(document.getElementById("element_select").selectedIndex==0){
		 alert("Please Select Element");  
		 return false;
	   }
	 
	 if(document.getElementById("hub_select").selectedIndex==0){
		 alert("Please Select Hub");  
		 return false;
	   }
	 
	 if(document.getElementById("sow_select").selectedIndex==0){
		 alert("Please Select Scope Of Work");  
		 return false;
	   }
	 
	 if(document.getElementById("dt_select").selectedIndex==0){
		 alert("Please Select Document Type");  
		 return false;
	   }
	 
	/* if(document.getElementById("comments_input").value==""){
		 alert("Please Enter Comments");  
		 return false;
	   }*/
	 
	var fileName = document.getElementById("file").value;
	//alert("file is" + fileName);
	if(fileName == "")
	{
		alert("Please Select File to Upload");
		file.focus();
		return false;
	}
}

function isChar(c){
    return( (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z')|| c == ' ');
}
function isdigit(c){
	return(c >= '0' && c<='9');
	}



//Get Project
var getProject = function(projectId) {
 
	//alert("client name is"+clientName);
	$.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_project",
                    projectId :projectId
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#project_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var projectId = data[i].projectId;
						var projectName = data[i].projectName;
						//alert(projectId+projectName);
						$('#project_select').
					    append("<option value=\""+projectId+"\">"+projectName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};



//Get Brand
var globalProjectId;
var getBrand = function(projectId) {
     globalProjectId=projectId;
/*   if(document.getElementById("project_select").selectedIndex==0){
	   return false;
   }
   var projectId=$("#project_select").val();
   
   getDivision(projectId);*/
     $.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_brand",
                    projectId :projectId
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#brand_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var brandId = data[i].brandId;
						var brandName = data[i].brandName;
						//alert(brandId+brandName);
						$('#brand_select').
					    append("<option value=\""+brandId+"\">"+brandName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};

//Get Division
var getDivision = function(projectId) {
 
	//alert("client name is"+clientName);
	$.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_division",
                    projectId :projectId
				},
				success : function(data) {

				$('#division_input').val(data.divisionName);
		
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};

//Get Element
var getElement = function() {
/* 
   if(document.getElementById("project_select").selectedIndex==0){
	   return false;
   }
   var projectId=$("#project_select").val();*/
	
	 $("#element_select").html('<option>select</option>');
   if(document.getElementById("brand_select").selectedIndex==0){
	  
	 return false;
   }
   var brandId=$("#brand_select").val();
  
     $.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_element",
                    projectId :globalProjectId,
                    brandId:brandId
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#element_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var elementId = data[i].elementId;
						var elementName = data[i].elementName;
						
						$('#element_select').
					    append("<option value=\""+elementId+"\">"+elementName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};

//Get Hub
var getHub = function() {

     $.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_hub"
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#hub_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var hubId = data[i].hubId;
						var hubName = data[i].hubName;
						//alert(hubId+hubName);
						$('#hub_select').
					    append("<option value=\""+hubId+"\">"+hubName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};
//Get Scope Of Work
var getSow = function() {

     $.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_sow"
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#sow_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var sowId = data[i].sowId;
						var sowName = data[i].sowName;
						//alert(sowId+sowName);
						$('#sow_select').
					    append("<option value=\""+sowId+"\">"+sowName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};

//Get Document Type
var getDt = function() {

     $.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_dt"
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#dt_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var dtId = data[i].dtId;
						var dtName = data[i].dtName;
						//alert(dtId+dtName);
						$('#dt_select').
					    append("<option value=\""+dtId+"\">"+dtName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};
//Get Client
var getDocumentLibrary = function(projectId) {
 //alert("calling");
	$.ajax( {
				type : "POST",
				url : "../DocumentLibrary",
				data : {
					userAction : "get_document_library",
					projectId:projectId
                  
				},
				success : function(data) {
				var len = data.length;
					$('#content_view_table').html(
					'<tr><th align=\"left\">Edit</th>'+
					/*'<th>Project</th>'+*/
					'<th align=\"left\">Brand</th>'+
					'<th align=\"left\">Element</th>'+
				/*	'<th>Division</th>'+*/
					'<th align=\"left\">Hub</th>'+
					'<th align=\"left\">Scope Of Work</th>'+
					'<th align=\"left\">File Name</th>'+
					'<th align=\"left\">Document Type</th>'+
					'<th align=\"left\">Comments</th>'+
					'<th align=\"left\">Date Time</th>'+
					'</tr>');
					for ( var i = 0; i < len; ++i) {
						var documentId = data[i].documentId;
						var projectName = data[i].projectName;
						var brandName = data[i].brandName;
						var elementName = data[i].elementName;
						var divisionName = data[i].divisionName;
						var hubName = data[i].hubName;
						var sowName = data[i].sowName;
						var documentName = data[i].documentName;
						var dtName = data[i].dtName;
						var comments = data[i].comments;
						var dateTime = data[i].dateTime;
						
						
						
						$('#content_view_table').append("<tr>"+
								"<td style=\"padding-left:2px;\" id=\"button_td"+i+"\"><input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showEditBox('"+documentId+"','"+i+"','"+comments+"')\"></td>"+
							/*	"<td>"+ projectName + "</td>"+*/
								"<td style=\"padding-left:2px;\">"+ brandName + "</td>"+
								"<td style=\"padding-left:2px;\">"+ elementName + "</td>"+
								/*"<td>"+ divisionName + "</td>"+*/
								"<td style=\"padding-left:2px;\">"+ hubName + "</td>"+
								"<td style=\"padding-left:2px;\">"+ sowName + "</td>"+
								"<td style=\"padding-left:2px;\"><form id=\"downloadForm"+i+"\" action=\"../DownloadDocument\" method=\"post\">" +
								"<input name=\"documentId\" type=\"hidden\" value=\""+documentId+"\">" +
								"<span onclick=\"getDocument("+i+")\" style=\"cursor:pointer;text-decoration:underline;color:blue;\">"+ documentName + "</span></form></td>"+
								"<td style=\"padding-left:2px;\">"+ dtName + "</td>"+
								"<td style=\"padding-left:2px;\" id=\"comments_td"+i+"\">"+ comments + "</td>"+
								"<td style=\"padding-left:2px;\">"+ dateTime + "</td>"+
								
							    "</tr>");
					}
				},
				error : function(error) {
					alert("Error");
				}
			});
};


//Get Document Type
var getDocument = function(documentId) {
	
var form=$("#downloadForm"+documentId);
form.submit();
//alert("documentId"+documentId);
   /*  $.ajax( {
				type : "POST",
				url : "../DownloadDocument",
				data : {
					userAction : "get_document",
					documentId:documentId
				},
				success : function(data) {
					
				var len = data.length;
			
				$('#dt_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var dtId = data[i].dtId;
						var dtName = data[i].dtName;
						//alert(dtId+dtName);
						$('#dt_select').
					    append("<option value=\""+dtId+"\">"+dtName+"</option>");
					}
				 
				
			
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});*/
};

var showEditBox=function(documentId,rowId,comments){
	$("#button_td"+rowId).html("<a onclick=\"updateDocument('"+documentId+"','"+rowId+"')\" href=\"#\">Save/</a>" +
			"<a onclick=\"cancelUpdate('"+documentId+"','"+rowId+"','"+comments+"')\" href=\"#\">Cancel</a>");
	
	$("#comments_td"+rowId).html("<input type=\"text\" value=\""+comments+"\" id=\"comments_input"+rowId+"\">");
	
};


// Set Client
var updateDocument = function(documentId,rowId) {
//alert("update document"+documentId+rowId);
	var comments=$("#comments_input"+rowId).val();
	//alert("update document"+documentId+comments);
	$.ajax( {
		type : 'POST',
		url : "../DocumentLibrary",
		data : {
			userAction : 'update_document',
			documentId:documentId,
			comments:comments
		
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Document is Updated Successfully');
				getDocumentLibrary(globalProjectId);
				
				
			}else {
				alert('Document Updation is Failed');
				
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};


var cancelUpdate=function(documentId,rowId,comments){
	//alert("cancel update");
	$("#button_td"+rowId).html("<input type=\"image\" src=\"../images/edit.png\" " +
								" onclick=\"showEditBox('"+documentId+"','"+rowId+"','"+comments+"')\">");
	
	$("#comments_td"+rowId).html(comments);
	
};

// Delete Client
var deleteClient = function(tableID) {

	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var clientId = new Array();
		var j = 0;
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				clientId[j] = chkbox.value;
				j++;
			}

		}
	} catch (e) {
		alert(e);
	}
 
	$.ajax( {
		type : 'POST',
		url : '../ManageClient',
		data : {
			userAction : "delete_client",
			clientId : clientId
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Client is Deleted Succesfully');
				getClient();
			} else {
				alert('Client Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};

// Update Client
var updateClient = function(countryId,stateId,cityId,clientId,
		clientName,localCurrency,baseCurrency,tinNumber,
		 cstNumber,pinCode,address) {

	$.ajax( {
		type : 'POST',
		url : '../ManageClient',
		data : {
			userAction : 'update_client',
			countryId:countryId,
			stateId:stateId,
			cityId:cityId,
			clientId:clientId,
			clientName:clientName,
			localCurrency:localCurrency,
			baseCurrency:baseCurrency,
			tinNumber:tinNumber,
			cstNumber:cstNumber,
			pinCode:pinCode,
			address:address
		},
		success : function(data) {
			var status = data.status;

			if (status == 0) {
				alert('Client is Updated Succesfully');
				getClient();
				showAddDiv();
			} else {
				alert('Client Updation Failed');

			}
		},
		error : function(error) {
			alert("Error");
		}

	});
};

//Get Country
var getCountry = function(operation) {
 
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_country"
                  
				},
				success : function(data) {
					
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_country_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var countryId = data[i].countryId;
						var countryName = data[i].countryName;
						$('#add_operation_country_select').
					    append("<option value=\""+countryId+"\">"+countryName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_country_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var countryId = data[i].countryId;
							var countryName = data[i].countryName;
						    $('#update_operation_country_select').
						    append("<option value=\""+countryId+"\">"+countryName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			
			});
};

//Get State
var getState = function(operation) {
	var countryId;
	if(operation=="add"){
     countryId=$('#add_operation_country_select').val();
 	 $('#add_operation_state_select').html('<option>select</option>');
	 $('#add_operation_city_select').html('<option>select</option>');
     if(document.getElementById("add_operation_country_select").selectedIndex == 0){
   
    	 return;
     }
	}
	
	if(operation=="update"){
	     countryId=$('#update_operation_country_select').val();
		 $('#update_operation_state_select').html('<option>select</option>');
    	 $('#update_operation_city_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_country_select").selectedIndex == 0){
	    
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_state",
					countryId:countryId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_state_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var stateId = data[i].stateId;
						var stateName = data[i].stateName;
					    $('#add_operation_state_select').
					    append("<option value=\""+stateId+"\">"+stateName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_state_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var stateId = data[i].stateId;
							var stateName = data[i].stateName;
						    $('#update_operation_state_select').
						    append("<option value=\""+stateId+"\">"+stateName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			});
};

//Get State
var getCity = function(operation) {
	var stateId;
	if(operation=="add"){
      stateId=$('#add_operation_state_select').val();
      $('#add_operation_city_select').html('<option>select</option>');
     if(document.getElementById("add_operation_state_select").selectedIndex == 0){
    	
    	 return;
     }
    
	}
	
	if(operation=="update"){
	     stateId=$('#update_operation_state_select').val();
	     $('#update_operation_city_select').html('<option>select</option>');
	     if(document.getElementById("update_operation_state_select").selectedIndex == 0){
	    	
	    	 return;
	     }
		}
	$.ajax( {
				type : "POST",
				url : "../ManageClient",
				data : {
					userAction : "get_city",
					stateId:stateId
                  
				},
				success : function(data) {
				var len = data.length;
				if(operation=="add"){
				$('#add_operation_city_select').html('<option>select</option>');
					for ( var i = 0; i < len; ++i) {
						var cityId = data[i].cityId;
						var cityName = data[i].cityName;
					    $('#add_operation_city_select').
					    append("<option value=\""+cityId+"\">"+cityName+"</option>");
					}
				 }
				
				else if(operation=="update"){
					$('#update_operation_city_select').html('<option>select</option>');
						for ( var i = 0; i < len; ++i) {
							var cityId = data[i].cityId;
							var cityName = data[i].cityName;
						    $('#update_operation_city_select').
						    append("<option value=\""+cityId+"\">"+cityName+"</option>");
						}
					 }
				},
				error : function(error) {
					alert("Error");
				},
				async: false
			});
};

   