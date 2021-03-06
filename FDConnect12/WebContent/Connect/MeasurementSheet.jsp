<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	String employee_Id=(String)session.getAttribute("employee_Id");
	if(employee_Id==null){
		response.sendRedirect("../Login.jsp");
	}
%>

<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.FilterSevices"%>
<%@page import="com.fd.Connect.DropDown"%>
<%@page import="com.fd.App.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.fd.Connect.ProjectServices"%>

<%
	DropDown drop = new DropDown();
	String[] trade_list = drop.drop_down_trade().split(Constants.columnSeperator);
	String[] chain_list = drop.drop_down_chain().split(Constants.columnSeperator);
	String[] region_list = drop.drop_down_region().split(Constants.columnSeperator);
	String[] state_list = drop.drop_down_state().split(Constants.columnSeperator);
	String[] city_list = drop.drop_down_city().split(Constants.columnSeperator);
	String[] town_list = drop.drop_down_town().split(Constants.columnSeperator);
	String[] area_list = drop.drop_down_area().split(Constants.columnSeperator);
	String[] country_list = drop.drop_down_country().split(Constants.rowSeperator);
	String[] fd_fub_list = drop.drop_down_fdhub().split(Constants.columnSeperator);
	String[] fd_client_name = drop.drop_down_client_nameByID().split(Constants.rowSeperator);
	String[] units_name = drop.dropDownUnitMaster().split(Constants.columnSeperator);
	String[] material_name=drop.dropDownMaterial_name_id().split(Constants.rowSeperator);
	String[] element_status_name_id=drop.dropDownElementStatus_name_id().split(Constants.rowSeperator); 

%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css"	href="../css/Connect/measurement_sheet.css" />
<script type="text/javascript" src="../js/Connect/measurement_sheet.js">
</script>
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>
<link rel="stylesheet" type="text/css" href="test/demo_table.css"/>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript"
	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
 <script type="text/javascript" src="../js/Connect/jquery.min.js"></script> 
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->
<style type="text/css">

</style>	

	<script type="text/javascript" charset="utf-8">
		var oTable;
		var nRow;
		var nEditing;
		//	$(document).ready(function() {
		function getDataTableRecord(){
			 oTable=	$('#example').dataTable({
					//'iDisplayLength': 10,
					//"aaSortingFixed": [[2,'asc']],//column number,ascending order
					"bFilter": false,
					 "bPaginate": false,
		            'bLengthChange': false,
		            "bSortClasses": false,
		            "aoColumns": [ {"bSortable": false},null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null]
		            
				});

		}	
				nEditing = null;	
				
				$('#example a.edit').live('click', function (e) {
					console.log("a.edit");
						e.preventDefault();
						
						/* Get the row as a parent of the link that was clicked on */
						var nRow = $(this).parents('tr')[0];
						
						if ( nEditing !== null && nEditing != nRow ) {
						console.log("if ( nEditing !== null && nEditing != nRow ):::::::::::::");
							/* Currently editing - but not this row - restore the old before continuing to edit mode */
							
							editRow( oTable, nRow );
							nEditing = nRow;
						}
						else if ( nEditing == nRow && this.innerHTML == "Save" ) {
						console.log("else if ( nEditing == nRow && this.innerHTML == Save )::::::::::::::::::::");
							/* Editing this row and want to save it */
						
							saveRow(oTable, nEditing);
							nEditing = null;
						}
						else {
							/* No edit in progress - let's start one */
							editRow( oTable, nRow );
							nEditing = nRow;
						}
					});
						    
	//});

			function editRow ( oTable, nRow )
			{
			//alert("edit row called");
				var aData = oTable.fnGetData(nRow);
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);
				var element_status;
				//jqTds[1].innerHTML="<a class='' href='' onclick='insertStore(\"" +oTable+ "\",\"" +nRow+ "\",\"" + proj_id+ "\");'>Save</a>/<a class='' href=''>Cancel</a>" ;
				jqTds[1].innerHTML="<a class='' href='#' onclick='update_measurement_sheet();'>Save</a>/<a class='' href='#' onclick='hideEditRow();'>Cancel</a>" ;
				jqTds[5].innerHTML = document.getElementById('element_status').innerHTML.toString().trim();
				element_status=document.getElementById('e_status_id');
				 for (i = 0; i <element_status.options.length; i++) {
					    if(aData[5].trim()==(element_status.options[i].text.trim())){
					    	element_status.options[i].selected= true;
					    }
					} 
				jqTds[8].innerHTML = '<input type="text" value="'+aData[8]+'"  id="surface_no_u" style="width:50px" >';
				jqTds[9].innerHTML = '<input type="text" value="'+aData[9]+'"  id="surface_detail_u" style="width:100px" >';
				
				jqTds[10].innerHTML = '<input type="text" value="'+aData[10]+'"  id="height_u" style="width:50px" >';
				jqTds[11].innerHTML = '<input type="text" value="'+aData[11]+'"  id="width_u" style="width:50px">';
				jqTds[12].innerHTML = "<input type=\"hidden\" id=\"element_u\" value=\""+aData[3]+"\">"+
				"<input type=\"hidden\" id=\"component_u\" value=\""+aData[6]+"\">"+
				"<div id=\"height_unit_drop\">"+
					//"<input type=\"text\" id=\"unit_h_w_u\" size=\"12\""+
					//	"name=\"unit_h_w_u\" >"+
					"</div>";
				jqTds[13].innerHTML = '<input type="text" value="'+aData[13]+'"  id="depth_u" style="width:50px">';
				jqTds[14].innerHTML = "	<div id=\"depth_unit_drop\">"+
				//"<input type=\"text\" id=\"unit_d_u\" size=\"12\""+
				//"name=\"unit_d_u\" >"+
			"</div>";
			
				jqTds[15].innerHTML = '<input type="text" value="'+aData[15]+'"  id="quantity_u" style="width:50px">';
			//	jqTds[17].innerHTML = '<input type="text" value="'+aData[17]+'"  id="store_name" >';
				

				
			}
	</script>
</head>
<body onload="getElementStatus();" style="position: relative; height: 648px; width: 1368px;
background-color: #FFFFFF;font-family: Arial, Helvetica, sans-serif;padding: 0;font-size: 12px;
margin: 0 auto;">


<!-- div to include header -->
<div>	
	<jsp:include page="../Common/Home.jsp">
	<jsp:param name="title" value="Measurement Sheet"/>
	</jsp:include>
</div>

		<!-- body /main content-->
		<div id="maincontent">
			<table style="width: 100%; text-align: center;" align="center">
				<!-- Client Details -->
				<tr>
					<td style="width: 100%; height: 100px;">
						
						<div id="Client" style="overflow: auto;
							background-color: #F2F2F2;
							-webkit-border-radius: 6px;
							-moz-box-shadow:    3px 3px 5px 6px #ccc;
  							-webkit-box-shadow: 3px 3px 5px 6px #ccc;
  							box-shadow: 2px 2px 2px #ccc;
  							margin-top: -10px">
							<table style="width: 65%;text-align: left;" align="center">

								<tr>
									<td style="color: #A90B0B;">Client Name :</td>
									<td>
									<select style="width: 150px;" id="client_select"
										onchange="return dropPROJECTOnClient();">
											<option value="SELECT">SELECT</option>
											<%for (int i = 0; i < fd_client_name.length; i++) {
											String[] fd_client_name_data=fd_client_name[i].split(Constants.columnSeperator);
											%>
											<option value="<%=fd_client_name_data[1]%>"><%=fd_client_name_data[0]%></option>
											<%}%>
									</select>
									</td>
									<!-- <td><input type="text" id="client_name" name="client_name"></td> -->
									<td style="color: #A90B0B;">Measurement InCharge :</td>
									<td><input type="text" id="measurement_incharge"
										name="measurement_incharge"></td>
										
										<td style="color: #A90B0B;">JC Number :</td>
									<td><input type="text" id="jc_number" 
										name="jc_number" size="15" readonly></td>
										
								</tr>

								<tr>
									<td style="color: #A90B0B;">Project Name :</td>
									<td>
										<div id="select_Project">

											<select style="width: 150px;" id="Project_select">
												<option value="NODATA">NO DATA</option>
											</select><!--
											 <input type="text" id="Project_name" name="Project_name"> 
										--></div></td>
									<td style="color: #A90B0B;">Measurement Instruction :</td>
									<td><input type="text" id="measurement_instruction"
										name="measurement_instruction"></td>
										
													<td style="color: #A90B0B;">JC Date :</td>
									<td><input type="text" id="jc_date"
										name="jc_date" size="15" readonly></td>
								</tr>
							</table>
						</div>
						
					</td>
				</tr>
								<!-- Display Store -->
				<tr>
					<td style="width: 100%; height: 200px;">
						

						<div id="store_details" style="margin-top: -30px;">
						
						<!-- <div id="searched_title"
							style="color: #A90B0B; font: 13px; padding-top: 5px; padding-bottom: 3px; padding-right: 21%">Store Details</div>
						 -->
						
						
							<table>
								<tr>
									<td>
			<div id="store_data" style="width: 990px;text-align: left;
			background-color: #e0e0e0;
			-webkit-border-radius: 3px;
			-moz-box-shadow:    3px 3px 5px 6px #ccc;
			-webkit-box-shadow: 3px 3px 5px 6px #ccc;
			box-shadow: 2px 2px 2px #ccc;
			height:170px;">
					
					<font color="white" style="font-size: 19px;margin-left: 19px;">STORE DETAILS</font>
			  							
						<table style="color: #A90B0B; width: 70%; font-size: 11px;margin-left: 11px;">

							<tr>
								<td>Store Name</td>

								<td>
									<div id="select_store">
										<input type="text" id="visit_store_name"
											name="visit_store_name">
									</div></td>
								<td>Contact Person</td>
								<td><input type="text" id="contact_name"
									name="contact_name" >
								</td>

								<td>TSI Name</td>
								<td><input type="text" id="tsi_name" name="tsi_name" >
								</td>
								
                               <td>Measurement Status</td>
													<td><input type="text" id="measurement_status"
													readonly	name="measurement_status">
													</td>
								
							</tr>

							<tr>
								<td>Address</td>
								<td rowspan="2"><textarea rows="4" cols="19" 
										id="address"></textarea></td>

													<!-- <td>Contact Phone :<input type="text" id="contact_phone" name="contact_phone"></br>
		Store/Dealer Code :<input type="text" id="contact_phone" name="contact_phone"></td> -->

													<td>Contact Phone</td>
													<td><input type="text" id="contact_phone"
														name="contact_phone" maxlength="12" >
													</td>
                                                      
                                                      <td>TSI Phone</td>
								<td><input type="text" id="tsi_phone" name="tsi_phone"  maxlength="12">
								</td>
													
													<td>Status Date</td>
													<td><input type="text" id="measured_on"
													readonly	name="measured_on" >
													</td>
													

													<!-- <td colspan="2">Below Column are Editable :</td> 
													<td colspan="2"><a href="#"
														onclick="editMeasurementDetails();">Click here to Edit</a>
													</td>
													-->

												</tr>

												<tr>
													<td></td>
													<td>Store/Dealer Code</td>
													<td><input type="text" id="store_code"
														name="store_code" >
													</td>

													
													
													<td >FD HUB</td>
													<td id="fd_hub_select_td"><input type="text" id="fd_hub" name="fd_hub" >
													</td>

													<td>Measured By</td>
													<td><input type="text" id="measured_by"
													readonly	name="measured_by" >
													</td>

												</tr>
												<tr>
													<td></td>
													<td><input type="button" value="Update Store Detail" onclick="updateStoreDetail();"></td>
													
													<td>Comments</td>
													<td colspan="5"><input type="text" id="comments" name="comments" 
													 readonly size="102" maxlength="100">
													</td>


													

													

												</tr>
											</table>

										</div></td>
									<td>
										<!-- Store Datas  --> 
										
										<!-- Edit Store Datalist  -->
		<div id="Edit Store Datalist" style="height: 168px;
		background-color: #e0e0e0;
		-webkit-border-radius: 3px;
		-moz-box-shadow:    3px 3px 5px 6px #ccc;
		-webkit-box-shadow: 3px 3px 5px 6px #ccc;
		box-shadow: 2px 2px 2px #ccc;width:350px;">

			<font style="color: white; text-align: right;font-size:18px;">Update Measurement Status</font> <br>
			<table style="color: #A90B0B; width: 335px; text-align: left;font-size:11px;margin-left: 12px;">
			
				<tr>

					<td>Measurement Status</td>
					<td>
						<div id="replace_measure_status">
							<!-- <input type="text" id="measure_status_u"
								name="measure_status_u" onclick="replaceStatus();"> -->
							<select id="measure_status_u" name="measure_status_u" style="width:150px;">
								<option value="Select">Select</option>
							</select>
						</div></td>
				</tr>
				<tr>
					<td>Status Date</td>
					<td><input type="text" id="measured_on_u"
						name="measured_on_u" onmouseover="date_measured_on(measured_on_u);" size="12" >
					</td>
				</tr>
				<tr>

					<td>Measured By</td>
					<td><input type="text" id="measured_by_u"
						name="measured_by_u" size="20" maxlength="30"></td>
				</tr>
					<tr>
					<td>Comments</td>
					<td><input type="text" id="comments_u"
						name="comments_u" maxlength="100" ></td>

				</tr>


			</table>
			<span
				style="position: relative; display: block; margin: 0px 0 0 20px;">
				<input style="width: 110px;" type="button"
				onclick="return updateMeasurementDetails();" value="Update" />
				<span style="display: inline; margin: 0px 0 0 10px;">
					<input style="width: 110px;" type="button" onclick="blank_update_details();"
					value="Cancel" /> </span> </span>
										</div></td>
								</tr>
							</table>
						</div></td>
				</tr>

				<tr>
					<td style="width: 100%;">
					<div style="margin-top: 0px;">
					
			<div id="measured_title" style="color: white; font-weight:bold; 
				height: 20px;background-color: #e0e0e0;font-size: 17px;">
			Measurement	Sheet</div>

		<div id="measured_details" style=" overflow: auto; z-index:0 ; margin-top: 30px;">
					<table style="color: #39939C; border: 1px; width: 100%" class="display"> 
								<tr>
									<td>
					<div id="measured_items" style=" font-size: 12px;" >
					<table style="width: 100%" id="example">
											
								<thead>
									<tr>
										<th>Select</th>
										<th>Edit</th>
										<th>Brand</th>
										<th>Element Name</th>
										<th>Element Type</th>
										<th>Element Status</th>
										<th>Component</th>
										<th>Material</th>
										<th>Surface No</th>
										<th>Surface Detail</th>
										<th>Height</th>
										<th>Width</th>
										<th>Unit</th>
										<th>Depth</th>
										<th>Unit</th>
										<th>Quantity</th>
										<th style="width:50px;">SFT</th>
										
										<!-- <td>Upload</td> -->
									</tr>
								</thead>

					</table>
					</div>

	<div id="add_measurment" style="display: none;
		border: #778 2px solid;
		background: #e0e0e0;
		margin: 5px 30px 0px 0px;
		-webkit-border-radius: 3px;
		-moz-box-shadow: 3px 3px 5px 6px #ccc;
		-webkit-box-shadow: 1px 1px 1px 1px #ccc">
	
	<div style="color: #ffffff; font-size: 17px; padding-bottom: 12px">Add Measurements</div> 
	
	<form id="myAddForm">

					
					<table style="width: 73%;text-align:left ;margin-left: 155px;">
						<tr>
							<td>Brand</td>
							<td>
								<div id="select_brand">
									<input type="text" id="brand_id" name="brand_id">
								</div></td>

							<td>Element Name</td>
							<td>
								<div id="select_element">
									<input type="text" id="element_id" name="element_id">
								</div></td>

							<td>Element Status</td>
							<td>
								<div id="element_status">
								
									<select id="e_status_id" style="width: 110px;">
												<option value="SELECT">SELECT</option>
												<%for (int i = 0; i < element_status_name_id.length; i++) {
												String[] element_status_name_id_data=element_status_name_id[i].split(Constants.columnSeperator);
												%>
												<option value="<%=element_status_name_id_data[0]%>"><%=element_status_name_id_data[1]%></option>
												<%
												}%>
									</select>
								</div>
							</td>
								
							<td>Component</td>
							<td>
								<div id="select_component">
									<input type="text" id="component_id" name="component_id" readonly="readonly">
								</div></td>
						</tr>
						 </table>
						<table style="width:73%;text-align:left ;margin-left: 155px;" > 
													<tr>
														

														<td>Material</td>
														<td>
															<div id="select_material">
																<!--<input type="text" id="material_id" name="material_id" readonly="readonly">-->
																<select id="material_id" style="width:180px">
																	<option value="SELECT">SELECT</option>
																	<%for (int i = 0; i < material_name.length; i++) {
																	String[] material_name_data=material_name[i].split(Constants.columnSeperator);
																	%>
																	<option value="<%=material_name_data[0]%>"><%=material_name_data[1]%></option>
																	<%}%>
																</select>
															</div></td>

														<td>Surface No</td>
														<td><input type="text" id="surface_no"
															name="surface_no" size="22" maxlength="16">
														</td>

														<td>Surface Detail</td>
														<td><input type="text" id="surface_detail"
															name="surface_detail" size="25" maxlength="25" style="width:340px">
														</td>
													</tr>
							 </table>
						<table style="width:72%;text-align:left ;margin-left: 155px;" >
													
													<tr>
														<td>Height</td>
														<td><input type="text" id="height" name="height" maxlength="9" size="10" >
														</td>

														<td>Width</td>
														<td><input type="text" id="width" name="width" maxlength="9" size="10">
														</td>


														<td>Unit</td>
														<td>
															<!-- <input type="text" id="unit_h_w" name="unit_h_w"> -->
															<select style="width: 110px;" id="unit_h_w"
															name="unit_h_w">
																<option value="SELECT">SELECT</option>
																<%for (int i = 0; i < units_name.length; i++) {%>
																<option value="<%=units_name[i]%>"><%=units_name[i]%></option>
																<%}%>
														</select></td>

														<td>Depth</td>
														<td><input type="text" id="depth" name="depth" maxlength="9" size="10">
														</td>

														<td>Unit</td>
														<td>
															<!-- <input type="text" id="unit_d" name="unit_d"> --> <select
															style="width: 110px;" id="unit_d" name="unit_d">
																<option value="SELECT">SELECT</option>
																<%for (int i = 0; i < units_name.length; i++) {%>
																<option value="<%=units_name[i]%>"><%=units_name[i]%></option>
																<%}%>
														</select></td>
														
														<td>Quantity</td>
														<td><input type="text" id="quantity" name="quantity" maxlength="10" size="12" >
														</td>
														
														
													</tr>
													 </table>
				<!-- 			<table style="width:80%;text-align:left ;">
						
						<tr>
							
						<td>Upload</td>
							<td><input type="text" id="upload_image"															name="upload_image">
							</td>


						</tr>
					</table>-->
			</form>
				<span
					style="position: relative; display: block; margin: 5px 0 0 20px;">
					<input style="width: 140px;" type="button"
					onclick="getProjectElementIDforInsert()"
					value="Insert Measurement" /> <span
					style="display: inline; margin: 0px 0 0 140px;"> <input
						style="width: 140px;" type="button"
						onclick="cancel_measurement_add();" value="Cancel" /> </span> </span>

			</div>


				<div id="update_measurment" style="display: none;
				border: #778 2px solid;
					background: #e0e0e0;
					margin: 5px 30px 0px 0px;
					-webkit-border-radius: 3px;
					-moz-box-shadow: 3px 3px 5px 6px #ccc;
					-webkit-box-shadow: 1px 1px 1px 1px #ccc">
				<div style="color: #ffffff; font-size: 17px; padding-bottom: 12px">
				Update Measurements</div>
					<table style="width: 73%;text-align:left ;margin-left: 155px;">
								<tr>
								
									<td>Brand</td>
									<td>
									 <input type="text" id="brand_id_u" name="brand_id_u" readonly >
									<!-- <select id="brand_id_u" name="brand_id_u">
										<option value="SELECT">SELECT</option>
									</select> -->
									</td>

									<td>Element</td>
									<td><input type="text" id="element_id_u"
										name="element_id_u" readonly>
									</td>

									<td>Element Status</td>
									<td>
									<!-- <input type="text" id="status_id_u" size="10" name="status_id_u" readonly="readonly"> -->
									<select id="status_id_u" style="width: 130px;">
										<option value="SELECT">SELECT</option>
										
									</select>
									
									</td>
									<td>Component</td>
													<td><input type="text" id="component_id_u"
														name="component_id_u" readonly>
									</td>
								</tr>
					</table>
					<table style="width: 73%;text-align:left ;margin-left: 155px;"> 
												<tr>
													

													<td>Material</td>
													<td><input type="text" id="material_id_u"
														name="material_id_u" readonly>
													</td>

													<td>Surface No</td>
													<td><input type="text" id="surface_no_u"
														name="surface_no_u" size="22" maxlength="16">
													</td>

													<td>Surface Detail</td>
													<td><input type="text" id="surface_detail_u"
														name="surface_detail_u"  size="25" maxlength="25"
														style="width:340px">
													</td>
												</tr>
					</table>
						<table style="width:72%;text-align:left ;margin-left: 155px;"> 
												<tr>
													<td>Height</td>
													<td><input type="text" id="height_u" name="height_u"  maxlength="9" size="10">
													</td>

													<td>Width</td>
													<td><input type="text" id="width_u" name="width_u"  maxlength="9" size="10">
													</td>


													<td>Unit</td>
													<td>
													<div id="height_unit_drop1">
													<input type="text" id="unit_h_w_u" size="12"
														name="unit_h_w_u" onmouseover="heightDropDown();">
													</div>
													</td>

													<td>Depth</td>
													<td><input type="text" id="depth_u" name="depth_u"  maxlength="9" size="10" >
													</td>

													<td>Unit</td>
													<td>
													<div id="depth_unit_drop1">
													<input type="text" id="unit_d_u" size="12" 
													name="unit_d_u" onmouseover="depthDropDown();">
													</div>
													</td>
													
													<td>Quantity</td>
													<td><input type="text" id="quantity_u"
														name="quantity_u" maxlength="10" size="12" >
														<input type="hidden" id="measure_id_u" name="measure_id_u">
													</td>
													
												</tr>
					</table>
				
					
			<span
					style="position: relative; display: block; margin: 5px 0 0 20px;">
					<input style="width: 140px;" type="button"
					onclick="return update_measurement_sheet();" value="Update Measurement" />
					<span style="display: inline; margin: 0px 0 0 140px;">
						<input style="width: 140px;" type="button"
						onclick="cancel_measurement_update();" value="Cancel" /> </span>
				</span>

				</div></td>

								</tr>
							</table>
						</div>
						</div>
						</td>
				</tr>
			</table>
		</div>
		<!-- End body /main content-->
		<!-- Footer -->
<!-- 		<div id="footer">
			<font style="font-size: 12px" color="#000">Powered by</font>&nbsp;&nbsp;&nbsp;
			<img alt="Aseema Softnet Technologies" src="../images/a_logo.png"
				height="20px" width="40px" draggable="false" /> <a
				href="http://www.aseema.in"><font color="#A0A0A0"
				style="font-size: 13px; font-weight: bold;"> Aseema Softnet
					Technologies Private Limited</font> </a>
		</div> -->






</body>
</html>