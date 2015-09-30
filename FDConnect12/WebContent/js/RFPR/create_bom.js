
function getXMLHttpRequestObject() {
	var xmlHttpReq = false;
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {

			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {

				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}

var rowCount = 0;
function addRow(tableID) {
	rowCount = rowCount + 1;
	//alert(rowCount);
	//alert("this is rowCount"+rowCount);
	var table = document.getElementById(tableID);

	var rowCount1 = table.rows.length;
	var row = table.insertRow(rowCount1);

	var cell1 = row.insertCell(0);
	var element1 = document.createElement("input");
	element1.type = "checkbox";
	cell1.appendChild(element1);

	var cell2 = row.insertCell(1);
	cell2.innerHTML = "<input id=\"element_section_input" + rowCount
			+ "\"  name=\"percentage\" type=\"text\" size=\"27\">";

	var cell3 = row.insertCell(2);
	cell3.innerHTML = "<span id=\"select_material" + rowCount
			+ "\"><select id=\"material_select" + rowCount
			+ "\" name=\"material\"  style=\"width: 200px;\" >"
			+ "<option>select</option></select></span>";

	//
	var cell4 = row.insertCell(3);
	cell4.innerHTML = "<input id=\"height_input" + rowCount
			+ "\"  name=\"height\" type=\"text\" size=\"4\">"

	var cell5 = row.insertCell(4);
	cell5.innerHTML = "<span id=\"select_height_unit" + rowCount
			+ "\"><select id=\"height_unit_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 50px;\" >"
			+ "<option>select</option></select></span>";

	//
	var cell6 = row.insertCell(5);
	cell6.innerHTML = "<input id=\"width_input" + rowCount
			+ "\"  name=\"height\" type=\"text\" size=\"4\" />"

	var cell7 = row.insertCell(6);
	cell7.innerHTML = "<span id=\"select_width_unit" + rowCount
			+ "\"><select id=\"width_unit_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 50px;\" >"
			+ "<option>select</option></select></span>";

	//

	var cell8 = row.insertCell(7);
	cell8.innerHTML = "<input id=\"thickness_input" + rowCount
			+ "\"  name=\"height\" type=\"text\" size=\"4\">"

	var cell9 = row.insertCell(8);
	cell9.innerHTML = "<span id=\"select_thickness_unit" + rowCount
			+ "\"><select id=\"thickness_unit_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 50px;\" >"
			+ "<option>select</option></select></span>";

	//

	var cell10 = row.insertCell(9);
	cell10.innerHTML = "<input id=\"capacity_input" + rowCount
			+ "\"  name=\"height\" type=\"text\" size=\"4\">"

	var cell11 = row.insertCell(10);
	cell11.innerHTML = "<span id=\"select_capacity_unit" + rowCount
			+ "\"><select id=\"capacity_unit_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 50px;\" >"
			+ "<option>select</option></select></span>";

	/*
	  var cell12 = row.insertCell(11);
	  cell12.innerHTML ="<input id=\"heightMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">"
	  
	  var cell13 = row.insertCell(12);
	  cell13.innerHTML ="<input id=\"widthMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">"
	  
	  var cell14 = row.insertCell(13);
	  cell14.innerHTML ="<input id=\"depthMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">"
	  
	  var cell15 = row.insertCell(14);
	  cell15.innerHTML ="<input id=\"capacityMM_input"+rowCount+"\"  name=\"height\" type=\"text\" size=\"4\">"
	  
	 */

	var cell12 = row.insertCell(11);
	cell12.innerHTML = "<input id=\"bom_quantity_input" + rowCount
			+ "\"  name=\"height\" type=\"text\" size=\"4\">";

	var cell13 = row.insertCell(12);
	cell13.innerHTML = "<input id=\"order_quantity_input" + rowCount
			+ "\"  name=\"height\" type=\"text\" size=\"4\">";

	var cell14 = row.insertCell(13);
	cell14.innerHTML = "<span id=\"select_order_quantity_unit" + rowCount
			+ "\"><select id=\"order_quantity_unit_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 50px;\" >"
			+ "<option>select</option></select></span>";
	
	var cell15 = row.insertCell(14);
	cell15.innerHTML = "<span id=\"select_process" + rowCount
			+ "\"><select id=\"process_select" + rowCount
			+ "\" name=\"heightUnit\"  style=\"width: 150px;\" >"
			+ "<option>select</option></select></span>";

	getMaterial(rowCount);
	getProcess(rowCount);
	getHeightUnit(rowCount);
	getWidthUnit(rowCount);
	getThicknessUnit(rowCount);
	getOrderQuantityUnit(rowCount);
	getCapacityUnit(rowCount);

}

function deleteRow(tableID) {
	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;

		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				table.deleteRow(i);
				rowCount--;
				i--;
			}

		}
	} catch (e) {
		alert(e);
	}
}

function getMaterial(rowCount) { //alert("get Material1");
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//alert("get Material2");

			document.getElementById("select_material" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");

		}
	}

	var outData = "flag=get_material&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getProcess(rowCount) { //alert("get Process1");
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//alert("get Process2");

			document.getElementById("select_process" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");

		}
	}

	var outData = "flag=get_process&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getHeightUnit(rowCount) { //alert("get Height1");
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			// alert("get Height2")

			document.getElementById("select_height_unit" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");

		}
	}

	var outData = "flag=get_unit&subFlag=heightUnit&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getWidthUnit(rowCount) {
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_width_unit" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");

		}
	}

	var outData = "flag=get_unit&subFlag=widthUnit&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getThicknessUnit(rowCount) {
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_thickness_unit" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");

		}
	};

	var outData = "flag=get_unit&subFlag=thicknessUnit&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getOrderQuantityUnit(rowCount) {
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_order_quantity_unit" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("get order quantity_unit "+xmlhttp.responseText);

		}
	};

	var outData = "flag=get_unit&subFlag=orderQuantityUnit&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getCapacityUnit(rowCount) {
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_capacity_unit" + rowCount).innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");

		}
	}

	var outData = "flag=get_unit&subFlag=capacityUnit&rowCount=" + rowCount;
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function getClient() {
	//alert("calling getCreateFor ");
	//var y=document.getElementById("Country_select").options;
	//alert("Index: " + y[x].index + " is " + y[x].text);
	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_client").innerHTML = xmlhttp.responseText;
			//alert("calling getCreateFor 1");
			// getBrand();

		}
	}

	var outData = "flag=get_client";
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

// Get Material Sub Group	
var selectedClientId;
function getProject() {

	var e = document.getElementById("client_select");

	selectedClientId = e.options[e.selectedIndex].value;

	if (selectedClientId == "select") {
		//alert("Please select Country");
		// document.getElementById("bom_div").style.display="none";
		document.getElementById("select_Project").innerHTML = "Project: <select style=\"width:250px;margin:0 0 0 33px;\"><option>select</option></select>";
		document.getElementById("select_project").innerHTML = "Element: <select style=\"width:250px;margin:0 0 0 13px;\"><option>select</option></select>";

		return;
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_Project").innerHTML = xmlhttp.responseText;
			document.getElementById("select_element").innerHTML = "Element: <select style=\"width:250px;margin:0 0 0 13px;\"><option>select</option></select>";

		}
	}

	var outData = "flag=get_Project&clientId=" + selectedClientId;

	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

var selectedElementId;
function getElement(flag) {

	var e = document.getElementById("Project_select");

	selectedProjectId = e.options[e.selectedIndex].value;
	// alert("project id "+selectedProjectId);
	 
	//alert("it is calling "+selectedProjectId);
	if (selectedProjectId == "select") {
		//alert("Please select Country");
		// document.getElementById("bom_div").style.display="none";
		document.getElementById("select_element").innerHTML = "Project: <select style=\"width:250px;margin:0 0 0 13px;\"><option>select</option></select>";

		return;
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
   // alert("after result");
			document.getElementById("select_element").innerHTML = xmlhttp.responseText;

		}
	};

	var outData = "flag=get_element&ProjectId=" + selectedProjectId;

	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}


function getElementNameAndQuantity() {

	var e = document.getElementById("element_select");

	selectedProjectElementId = e.options[e.selectedIndex].value;
	
	if (selectedProjectElementId == "select") {
		//alert("Please select Country");
		// document.getElementById("bom_div").style.display="none";
		document.getElementById("quantity_input").value = "";
		document.getElementById("element_name_input").value = "";

		return;
	}

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var elementNameAndQuantity =new Array();
			var elementNameAndQuantity = xmlhttp.responseText.split("#&#");
            
			if(elementNameAndQuantity.length==2){
			document.getElementById("element_name_input").value = (elementNameAndQuantity[0].replace(/^\s+|\s+$/g,""));
			document.getElementById("quantity_input").value = (elementNameAndQuantity[1].replace(/^\s+|\s+$/g,""));
			}
		}
	}

	var outData = "flag=get_element_name_and_quantity&ProjectElementId=" + selectedProjectElementId;

	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

//var selectedProjectId;
function getBrand() {

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("select_brand").innerHTML = xmlhttp.responseText;

		}
	}

	var outData = "flag=get_brand";

	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}

function validate() {
	var selectedClientId;
	var selectedProjectId;
	var selectedProjectElementId;
	var selectedBrandId;
	var bomCode=null;
	var bomVersionNumber=null;
	var bomDate;

	if (document.getElementById("client_select").selectedIndex == 0) {
		alert("Please Select Client");
		return false;

	} else {
		var e = document.getElementById("client_select");
		selectedClientId = e.options[e.selectedIndex].value;
		//alert(selectedCreateId);
	}

	if (document.getElementById("Project_select").selectedIndex == 0) {
		alert("Please Select Project");
		return false;

	} else {
		var e = document.getElementById("Project_select");
		selectedProjectId = e.options[e.selectedIndex].value;
		//alert(selectedProjectId);
	}

	if (document.getElementById("element_select").selectedIndex == 0) {
		alert("Please Select Project");
		return false;

	} else {
		var e = document.getElementById("element_select");
		selectedProjectElementId = e.options[e.selectedIndex].value;
		//alert(selectedProjectElementId);
	}

	/* 
	 if(document.getElementById("brand_select").selectedIndex==0){
	     alert("Please Select Brand");
	     return false;	
	
	   }
	 else{
	  var e=document.getElementById("brand_select");
	  selectedBrandId=e.options[e.selectedIndex].value; 
	 // alert(selectedBrandId);
	 }*/

	if (document.getElementById("bom_code_input").value == "") {
		
		bomCode=null;

	} else {
		bomCode = document.getElementById("bom_code_input").value;
		//alert(bomCode);
	}

	if (document.getElementById("bom_version_input").value == "") {
		bomVersionNumber=null;

	} else {
		bomVersionNumber = document.getElementById("bom_version_input").value;
		//alert(bomVersionNumber);
	}

	/*  var dateStr=document.getElementById("bom_date_input").value;
	   
	if(dateStr==""){
	    alert("Please Enter Bom Date");
	    dateStr.focus();
	    return false;	
	
	  }
	
	var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{4}|\d{4})$/;
	
	var matchArray = dateStr.match(datePat); // is the format ok?
	if (matchArray == null) {
	 alert("Date must be in DD/MM/YYYY format");
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
	 alert("Month "+month+" doesn't have 31 days!")
	 return false;
	}
	if (month == 2) { // check for february 29th
	 var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	 if (day>29 || (day==29 && !isleap)) {
	  alert("February " + year + " doesn't have " + day + " days!");
	  return false;
	   }
	}
	// date is valid
	
	
	 bomDate = dateStr;
	  //alert(bomDate);
	  
	 */

	//bom elements validation

	var materialId = new Array();
	var heightUnitId = new Array();
	var widthUnitId = new Array();
	var thicknessUnitId = new Array();
	var orderQuantityUnitId = new Array();
	var capacityUnitId = new Array();
	var processId = new Array();

	var elementSection = new Array();
	var height = new Array();
	var width = new Array();
	var thickness = new Array();
	var capacity = new Array();
	var bomQuantity = new Array();
	var orderQuantity = new Array();

	var j = 0;

	for ( var i = 1; i <= rowCount; i++) {

		if (document.getElementById("element_section_input" + i) != null) {
			var dimensionFlag = 0;

			if (document.getElementById("element_section_input" + i).value == "") {
				alert("Please Enter Element Section");
				document.getElementById("element_section_input" + i).focus();
				return false;
			}

			else {
				elementSection[j] = document
						.getElementById("element_section_input" + i).value;

				//alert("element section is "+elementSection[j]);

			}

			if (document.getElementById("material_select" + i).selectedIndex == 0) {
				alert("Please Select Material");
				return false;

			} else {
				var e = document.getElementById("material_select" + i);
				materialId[j] = Number(e.options[e.selectedIndex].value);

				// alert("material Id is "+materialId[j]);

			}

			if (document.getElementById("height_input" + i).value == "") {

				dimensionFlag++;
				// alert("Please Enter Height");
				// document.getElementById("height_input"+i).focus();
				//return false;	
			}

			else if (isNaN(document.getElementById("height_input" + i).value)) {
				alert("Please Enter Numeric Value For Height");
				document.getElementById("height_input" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("height_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Height");
				document.getElementById("height_input" + i).focus();
				return false;
			}
		/*	else if((Number(document.getElementById("height_input" + i).value))== 0){
				alert("Value Should be more Than Zero");
				document.getElementById("height_input" + i).focus();
				
			}*/

			// else{
			height[j] = Number(document.getElementById("height_input" + i).value);

		   

			// }

			if ((document.getElementById("height_unit_select" + i).selectedIndex == 0)
					&& (document.getElementById("height_input" + i).value != "")) {
				alert("Please Select Height Unit");
				return false;

			}

			else {
				if (document.getElementById("height_unit_select" + i).selectedIndex != 0) {
					var e = document.getElementById("height_unit_select" + i);
					heightUnitId[j] = e.options[e.selectedIndex].value;
				} else {
					heightUnitId[j] = " ";

				}

				//alert("height Id is "+heightUnitId[j]);

			}

			if (document.getElementById("width_input" + i).value == "") {

				dimensionFlag++;
				// alert("Please Enter Width");
				//document.getElementById("width_input"+i).focus();
				//return false;	
			}

			else if (isNaN(document.getElementById("width_input" + i).value)) {
				alert("Please Enter Numeric Value For Width");
				document.getElementById("width_input" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("width_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Width");
				document.getElementById("width_input" + i).focus();
				return false;
			}

			// else{
			width[j] = Number(document.getElementById("width_input" + i).value);

			// alert("width  is "+width[j]);

			//}

			if ((document.getElementById("width_unit_select" + i).selectedIndex == 0)
					&& (document.getElementById("width_input" + i).value != "")) {
				alert("Please Select width Unit");
				return false;

			}

			else {
				if (document.getElementById("width_unit_select" + i).selectedIndex != 0) {
					var e = document.getElementById("width_unit_select" + i);
					widthUnitId[j] = e.options[e.selectedIndex].value;
				} else {
					widthUnitId[j] = " ";

				}
				//alert("width Id is "+widthUnitId[j]);

			}

			if (document.getElementById("thickness_input" + i).value == "") {

				dimensionFlag++;
				//alert("Please Enter Thickness");
				//document.getElementById("thickness_input"+i).focus();
				//return false;	
			}

			else if (isNaN(document.getElementById("thickness_input" + i).value)) {
				alert("Please Enter Numeric Value For Thickness");
				document.getElementById("thickness_input" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("thickness_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Thickness");
				document.getElementById("thickness_input" + i).focus();
				return false;
			}

			// else{
			thickness[j] = Number(document
					.getElementById("thickness_input" + i).value);

			// alert("thickness  is "+thickness[j]);

			//}

			if ((document.getElementById("thickness_unit_select" + i).selectedIndex == 0)
					&& (document.getElementById("thickness_input" + i).value != "")) {
				alert("Please Select Thickness Unit");
				return false;

			}

			else {
				if (document.getElementById("thickness_unit_select" + i).selectedIndex != 0) {
					var e = document
							.getElementById("thickness_unit_select" + i);
					thicknessUnitId[j] = e.options[e.selectedIndex].value;
				} else {
					thicknessUnitId[j] = " ";

				}

			}

			if (document.getElementById("capacity_input" + i).value == "") {

				dimensionFlag++;
				//alert("Please Enter Capacity");
				// document.getElementById("capacity_input"+i).focus();
				//return false;	
			}

			else if (isNaN(document.getElementById("capacity_input" + i).value)) {
				alert("Please Enter Numeric Value For Capacity");
				document.getElementById("capacity_input" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("capacity_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Capacity");
				document.getElementById("capacity_input" + i).focus();
				return false;
			}

			// else{
			capacity[j] = Number(document.getElementById("capacity_input" + i).value);

			// alert("capacity  is "+capacity[j]);

			// }

			if ((document.getElementById("capacity_unit_select" + i).selectedIndex == 0)
					&& (document.getElementById("capacity_input" + i).value != "")) {
				alert("Please Select Capacity Unit");
				return false;

			}

			else {
				if (document.getElementById("capacity_unit_select" + i).selectedIndex != 0) {
					var e = document.getElementById("capacity_unit_select" + i);
					capacityUnitId[j] = e.options[e.selectedIndex].value;
				} else {
					capacityUnitId[j] = " ";

				}

				//alert("capacity Id is "+capacityUnitId[j]);

			}

			if (dimensionFlag == 4) {
				alert("Atleast Enter One Of Height , Width ,Thickness or Capacity");
				return false;
			}

			if (document.getElementById("bom_quantity_input" + i).value == "") {

				alert("Please Enter Bom Quantity");
				document.getElementById("bom_quantity_input" + i).focus();
				return false;
			}

			else if (isNaN(document.getElementById("bom_quantity_input" + i).value)) {
				alert("Please Enter Numeric Value For Bom Quantity");
				document.getElementById("bom_quantity_input" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("bom_quantity_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Bom Quantity");
				document.getElementById("bom_quantity_input" + i).focus();
				return false;
			}

			else if ((Number(document.getElementById("bom_quantity_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Bom Quantity");
				document.getElementById("bom_quantity_input" + i).focus();
				return false;
			}

			else {
				bomQuantity[j] = Number(document
						.getElementById("bom_quantity_input" + i).value);

				// alert("bom_quantity  is "+bom_quantity[j]);

			}

			if (document.getElementById("order_quantity_input" + i).value == "") {

				alert("Please Enter Order Quantity");
				document.getElementById("order_quantity_input" + i).focus();
				return false;
			}

			else if (isNaN(document.getElementById("order_quantity_input" + i).value)) {
				alert("Please Enter Numeric Value For Order Quantity");
				document.getElementById("order_quantity_input" + i).focus();
				return false;
			}

			else if ((document.getElementById("order_quantity_input" + i).value)
					.indexOf(".") != -1) {
				alert("Decimal Value Not Allowed For Order Quantity");
				document.getElementById("order_quantity_input" + i).focus();
				return false;
			}

			else if ((Number(document
					.getElementById("order_quantity_input" + i).value)) < 0) {
				alert("Negative Value Not Allowed For Order Quantity");
				document.getElementById("order_quantity_input" + i).focus();
				return false;
			}

			else {
				orderQuantity[j] = Number(document
						.getElementById("order_quantity_input" + i).value);

				// alert("order_quantity  is "+order_quantity[j]);

			}

			
			if ((document.getElementById("order_quantity_unit_select" + i).selectedIndex == 0)
					&& (document.getElementById("order_quantity_input" + i).value != "")) {
				alert("Please Select order quantity Unit");
				return false;

			}

			else {
				if (document.getElementById("order_quantity_unit_select" + i).selectedIndex != 0) {
					var e = document.getElementById("order_quantity_unit_select" + i);
					orderQuantityUnitId[j] = e.options[e.selectedIndex].value;
				} else {
					orderQuantityUnitId[j] = " ";

				}
				
			}
			if (document.getElementById("process_select" + i).selectedIndex == 0) {
				processId[j]=" ";

			}

			else {
				var e = document.getElementById("process_select" + i);
				processId[j] = Number(e.options[e.selectedIndex].value);

				//alert("process Id is "+processId[j]);

			}

			j = j + 1;
		}

	}

	createBom(selectedClientId, selectedProjectId, selectedProjectElementId,
			selectedBrandId, bomCode, bomVersionNumber, bomDate, materialId,
			heightUnitId, widthUnitId, thicknessUnitId, orderQuantityUnitId, capacityUnitId,
			processId, elementSection, height, width, thickness, capacity,
			bomQuantity, orderQuantity);

}

function createBom(selectedClientId, selectedProjectId,
		selectedProjectElementId, selectedBrandId, bomCode, bomVersionNumber,
		bomDate, materialId, heightUnitId, widthUnitId, thicknessUnitId,orderQuantityUnitId,
		capacityUnitId, processId, elementSection, height, width, thickness,
		capacity, bomQuantity, orderQuantity) {

	var xmlhttp = getXMLHttpRequestObject();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			alert("BOM is Created successfully");
			//window.location.reload();

		}
	};

	var outData = "flag=create_bom&selectedClientId=" + selectedClientId
			+ "&selectedProjectId=" + selectedProjectId
			+ "&selectedProjectElementId=" + selectedProjectElementId
			+ "&selectedBrandId=" + selectedBrandId + "&bomCode=" + bomCode
			+ "&bomVersionNumber=" + bomVersionNumber + "&bomDate=" + bomDate
			+ "&materialId=" + materialId + "&heightUnitId=" + heightUnitId
			+ "&widthUnitId=" + widthUnitId + "&thicknessUnitId="
			+ thicknessUnitId + "&orderQuantityUnitId="+orderQuantityUnitId+"&capacityUnitId=" + capacityUnitId
			+ "&processId=" + processId + "&elementSection=" + elementSection
			+ "&height=" + height + "&width=" + width + "&thickness="
			+ thickness + "&capacity=" + capacity + "&bomQuantity="
			+ bomQuantity + "&orderQuantity=" + orderQuantity;

	//alert(outData);
	xmlhttp.open("POST", "CreateBomController.jsp", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(outData);

}


/*function datePicker(){

 new JsDatePick({

 useMode:2,

 target:"bom_date_input",//Id specified -àOnclick

 dateFormat:"%d/%m/%y"

 });  

 }*/

