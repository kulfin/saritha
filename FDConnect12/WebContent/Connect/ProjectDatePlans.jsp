<%@page import="com.fd.Connect.DropDown"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.App.*" %>

<%@page import="com.fd.Connect.ProjectServices"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>

<%
	System.out.println("PROJECT DATE PLANS");
	String id = request.getParameter("id");
	session.setAttribute("project_id", id);
	System.out.println("Project ID" + id);
	ProjectServices services = new ProjectServices();
	String header_details = services.Project_header_details(Integer
			.parseInt(id));
	String[] row_data = header_details.split(Constants.columnSeperator);
	DropDown drop = new DropDown();
	String[] country_list = drop.drop_down_country().split(
			Constants.rowSeperator);
%>
<!DOCTYPE html>
<html>
<head>
<!--  For new tab menu -->
<script type="text/javascript" src="../js/Common/script.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Connect/style.css" />

<!-- Side Menu -->
<link rel="stylesheet" type="text/css"
	href="../css/Connect/horizontal_menu.css" />

<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="../css/Connect/details_project.css" />
<link rel="icon" href=<%=ApplicationConstants.IMAGEPATH %> type="image/x-icon" />
<title><%=ApplicationConstants.TITLE %></title>

<!-- CSS for Drop Down Tabs Menu #2 -->
<link rel="stylesheet" type="text/css"
	href="../css/Common/dropdowntabfiles/bluetabs.css" />
<script type="text/javascript"
	src="../css/Common/dropdowntabfiles/dropdowntabs.js"></script>

<script type="text/javascript" src="../js/Connect/jobCardMeasurement.js"></script>
<script type="text/javascript" src="../js/Connect/connet_js_entire.js"></script>

<!-- Calendar -->
<link rel="stylesheet" type="text/css" media="all"
	href="../css/Connect/jsDatePick_ltr.min.css" />
<script type="text/javascript"
	src="../js/Connect/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="../js/Connect/jquery.min.detail.js"></script>

</head>

<!-----   Body   ----->
<body style="position: absolute; height: 110px;">
	<!-- Header -->
	<div id="admindiv" style="height: 97%; width: 97%;">
		<!-- body /main content-->
		<div id="maincontent">
			<table style="width: 100%; text-align: center;">
				<tr>
					<td width="80%">

						<div id="maincontent_element"
							style="overflow: auto; height: 600px">

							<h3 style="color: #39939C;">Project Details</h3>

							<table id="table_id" align="center"
								style="width: 90%; text-align: left; table-layout: auto;">
								<tr>
									<td width="16.5%" id="td_id">Project No.</td>
									<td width="16.5%"><font size="2"><%=row_data[0]%></font></td>

									<td width="16.5%" id="td_id">Project Name</td>
									<td width="16.5%"><font size="2"><%=row_data[1]%></font></td>

									<td width="16.5%" id="td_id">Client Name</td>
									<td width="16.5%"><font size="2"><%=row_data[2]%></font></td>
								</tr>
								<tr>
									<td width="16.5%" id="td_id">FD Division</td>
									<td width="16.5%"><font size="2"><%=row_data[3]%></font></td>

									<td width="16.5%" id="td_id">Start Date</td>
									<td width="16.5%"><font size="2"><%=row_data[4]%></font></td>

									<td width="16.5%" id="td_id">End Date</td>
									<td width="16.5%"><font size="2"><%=row_data[5]%></font></td>
								</tr>

							</table>

							<div id="subMenu" style="margin-top: 20px; font-size: 9px;">

								<ul class="menu" id="menu">

									<li><a href="#" onclick="project_details(<%=id%>);"
										class="submenulink">Project Header</a></li>


									<li><a href="#" onclick="project_document(<%=id%>);"
										class="submenulink">Document Library</a></li>
									<li><a href="#" onclick="project_accountablity(<%=id%>);"
										class="submenulink">Accountability</a></li>

									<li><a href="ProjectScopeOfWork.jsp?id=<%=id%>"
										class="submenulink">Element & Scope</a></li>
									<li><a href="ProjectDatePlans.jsp?id=<%=id%>"
										class="submenulink">Date Plans</a></li>
									<li><a href="#" class="submenulink">Rate Card</a></li>
									<li><a href="StoreProjectList.jsp?id=<%=id%>"
										class="submenulink">Location</a></li>
									<li><a href="#" class="submenulink">Quantities</a></li>
									
									<li><a href="ProjectPaymentTerm.jsp?id=<%=id%>"
										class="submenulink">Financial</a></li>
									<li><a href="#" onclick="project_approval(<%=id%>);"
										class="submenulink">Approval</a></li>

									<li><a href="#" class="submenulink" onclick="#">Closure</a>
									</li>
								</ul>


								<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>
							</div>



							<div id="sub_menu"
								style="font-size: 10px; margin-left: 484px; margin-top: 30px;">

								<ul class="menu" id="menu">
									<li><a href="#" onclick="Project_Date_Plans();"
										class="submenulink" style="width: 112px;">ProjectDatePlans</a>
									</li>
									<li><a href="#" onclick="Production_Plan();"
										class="submenulink" style="width: 103px;">ProductionPlan</a></li>
									<li><a href="#" onclick="Implementation_Plan();"
										class="submenulink" style="width: 130px;">ImplementationPlan</a>
									</li>
								</ul>
							</div>


							<div id="datePlan_page"
								style="width: 100%; height: 300px; overflow: auto; margin-top: 10px; font-size: 12px;">

								<div id="project_date_plans" style="position: absloute;">
									Project Date Plans

									<div id="filter_div" style="margin-top: 20px;" align="center">
										<table>
											<tr>

												<td><span id="filter_select_country"
													style="color: #A90B0B; font-size: 12px;"> Country </span></td>
												<td><input type="hidden" value="<%=id%>"
													id="prjctId_hidden" name="prjctId_hidden"> <select
													style="width: 115px;"
													onchange="return getRegionOnCountry();" id="country_select">
														<option value="SELECT">SELECT</option>
														<%
															for (int i = 0; i < country_list.length; i++) {
																String[] countryid = country_list[i]
																		.split(Constants.columnSeperator);
														%>
														<option value="<%=countryid[1]%>"><%=countryid[0]%></option>
														<%
															}
														%>
												</select></td>



												<td><span id="filter_select_region"
													style="color: #A90B0B; font-size: 12px;"> Region </span></td>
												<td>
													<div id="select_region">
														<select style="width: 115px;" id="region_select">

															<option value="NODATA">NO DATA</option>

														</select>
													</div></td>


												<td><span id="filter_select_state"
													style="color: #A90B0B; font-size: 12px;"> State </span></td>
												<td>
													<div id="select_state">
														<select style="width: 115px;" id="state_select">

															<option value="NODATA">NO DATA</option>

														</select>
													</div></td>



												<td><span id="filter_select_city"
													style="color: #A90B0B; font-size: 12px;"> City </span></td>
												<td>
													<div id="select_city">
														<select style="width: 115px;" id="city_select">

															<option value="NODATA">NO DATA</option>

														</select>
													</div></td>
												
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;"> No of
														Stores </span></td>

												<td><input type="text" id="NoofStores"
													name="NoofStores" style="width: 110px;">
												</td>

												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;"> Activity
														Type </span></td>
												<td><input type="text" id="ActivityType"
													name="ActivityType" style="width: 110px;">
												</td>
											</tr>
										</table>

										<table style="text-align: left;">


											<tr>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;"> Client : </span>
												</td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														TargetNationalDate </span></td>
												<td><input type="text" id="TargetNationalDate"
													name="TargetNationalDate" style="width: 110px;"></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														ActualNationalDate </span></td>
												<td><input type="text" id="ActualNationalDate"
													name="ActualNationalDate" style="width: 110px;"></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														TargetRegionalDate </span></td>
												<td><input type="text" id="TargetRegionalDate"
													name="TargetRegionalDate" style="width: 110px;"></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														ActualRegionalDate </span></td>
												<td><input type="text" id="ActualRegionalDate"
													name="ActualRegionalDate" style="width: 110px;"></td>
											</tr>



											<tr>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;"> FD : </span></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														PlannedStartDate </span></td>
												<td><input type="text" id="PlannedStartDate"
													name="PlannedStartDate" style="width: 110px;"></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														PlannedEndDate </span></td>
												<td><input type="text" id="PlannedEndDate"
													name="PlannedEndDate" style="width: 110px;"></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														ActualStartDate </span></td>
												<td><input type="text" id="ActualStartDate"
													name="ActualStartDate" style="width: 110px;"></td>
												<td><span id="filter_no_of_store"
													style="color: #A90B0B; font-size: 12px;">
														ActualEndDate </span></td>
												<td><input type="text" id="ActualEndDate"
													name="ActualEndDate" style="width: 110px;"></td>
											</tr>

											<tr>
												<td colspan="7"></td>
												<td>
													<button onclick="">Insert Date Plan</button></td>
												<td>
													<button onclick="">Cancel</button></td>
											</tr>
										</table>
									</div>
									<!-- filter div -->
								</div>

								<div id="project_plans" style="position: absloute;">
									Project Plans</div>

								<div id="implementation_plan" style="position: absloute;">
									Implementation Plan</div>

							</div>
							<script type="text/javascript">
	document.getElementById('project_date_plans').style.display='none';
	document.getElementById('project_plans').style.display='none';
	document.getElementById('implementation_plan').style.display='none';
</script>


						</div>
					</td>

				</tr>
			</table>
		</div>
		<!-- End body /main content-->
	</div>
	<!-- admindiv -->
</body>
<script type="text/javascript">

function Project_Date_Plans(){
	
	document.getElementById('datePlan_page').style.display='block';
	document.getElementById('project_date_plans').style.display='block';
	document.getElementById('project_plans').style.display='none';
	document.getElementById('implementation_plan').style.display='none';
	
}

function Production_Plan(){
	//alert('production_plan');
	document.getElementById('datePlan_page').style.display='block';
	document.getElementById('project_date_plans').style.display='none';
	document.getElementById('project_plans').style.display='block';
	document.getElementById('implementation_plan').style.display='none';
	
}
function Implementation_Plan(){
	//alert('Implementation_Plan');
	document.getElementById('datePlan_page').style.display='block';
	document.getElementById('project_date_plans').style.display='none';
	document.getElementById('project_plans').style.display='none';
	document.getElementById('implementation_plan').style.display='block';
	 
}

</script>

</html>