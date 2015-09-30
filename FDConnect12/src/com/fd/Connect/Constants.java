package com.fd.Connect;

public interface Constants {

	public static final String rowSeperator = "!&!";
	public static final String columnSeperator = "#&#";
	// consatnt

	public static final String DATA_INSUFFICIENT = "DATA_INSUFFICIENT";
	public static final String DATA_INSERTED = "DATA_INSERTED";
	public static final String DATA_NOT_INSERTED = "DATA_NOT_INSERTED";

	// Update

	public static final String DATA_UPDATED = "DATA_UPDATED";
	public static final String DATA_NOT_UPDATED = "DATA_NOT_UPDATED";
	// Select
	public static final String NO_DATA = "NO DATA";
	// Delete
	public static final String DATA_NOT_DELETED = "DATA NOT DELETED";
	public static final String DATA_DELETED = "DATA DELETED";

	public static final String FOREIGN_KEY_CONSTRANIT_FAIL = "FOREIGN KEY CONSTRANIT FAIL";

	// Search

	public static final String search_Projectby_clientname = "SELECT Project_number,Project_name,client_name, "
			+ " division_name,Project_id FROM Project, client_master, fd_division_master where Project.client_id=client_master.client_id "
			+ "AND Project.division_id=fd_division_master.fd_division_id AND client_name=? order by Project_name";

	public static final String search_Projectby_divisionname = "SELECT Project_number,Project_name,client_name,division_name,Project_id FROM "
			+ " Project, client_master, fd_division_master where Project.client_id=client_master.client_id "
			+ " AND Project.division_id=fd_division_master.fd_division_id AND division_name=? order by Project_name";

	public static final String search_Projectby_name = "SELECT Project_number,Project_name,client_name,division_name,Project_id FROM "
			+ " Project, client_master, fd_division_master where Project.client_id=client_master.client_id "
			+ " AND Project.division_id=fd_division_master.fd_division_id AND Project_name=? order by Project_name";

	public static final String search_Project = "SELECT Project_number,Project_name,client_name,division_name,Project_id FROM "
			+ " Project, client_master, fd_division_master where Project.client_id=client_master.client_id "
			+ " AND Project.division_id=fd_division_master.fd_division_id AND Project_number=? order by Project_name";

	/*
	 * 
	 * Project Related
	 */

	/*public static final String Project_header_data = "SELECT Project_name ,"
			+ " Project_number , client_id , division_id , start_date ,"
			+ " end_date , notes , crm_name , crm_phone , crm_email , estimated_budget ,"
			+ " billing_narration , status , special_instruction , total_stores ,"
			+ " client_manager_name, client_manager_phone,client_manager_email,po_reference, po_date,fd_hub_id "
			+ " FROM  fourth_dimension . Project ,fourth_dimension.Project_client_details,"
			+ " fourth_dimension.fd_division_master,fourth_dimension.fd_hub_master where  Project.Project_id =? and "
			+ " Project.Project_id=Project_client_details.Project_id and "
			+ " Project.division_id=fd_division_master.fd_division_id order by Project_name";
	*/
	
	public static final String Project_header_data = "SELECT Project_name ,Project_number , client_id , division_id , start_date ,end_date , " +
			"notes , crm_name , crm_phone , crm_email , estimated_budget ,billing_narration , status , special_instruction , total_stores ," +
			"client_manager_name, client_manager_phone,client_manager_email,po_reference, po_date,fd_hub_id,po_amount,status,project_status_date," +
			"approval_mgr_id FROM  fourth_dimension . Project ,fourth_dimension.Project_client_details,fourth_dimension.fd_division_master," +
			"fourth_dimension.fd_hub_master where  Project.Project_id =? and Project.Project_id=Project_client_details.Project_id and " +
			"Project.division_id=fd_division_master.fd_division_id order by Project_name";

	public static final String Project_insert_query = "insert into fourth_dimension.Project(Project_number,Project_name,client_id,division_id,start_date,end_date)"
			+ "values(?,?,?,?,?,?)";

	public static final String Project_open_query = "SELECT p.Project_number,p.Project_name,c.client_name,fd_div.division_name,p.Project_id"
			+ " FROM fourth_dimension.Project p ,fourth_dimension.client_master c,"
			+ "fourth_dimension.fd_division_master fd_div"
			+ " where p.client_id=c.client_id and fd_div.fd_division_id=p.division_id order by p.Project_id desc";

	public static final String Project_header_details_query = "SELECT p.Project_number,p.Project_name,c.client_name,fd_div.division_name,p.start_date,"
			+ "p.end_date FROM fourth_dimension.Project p ,fourth_dimension.client_master c,fourth_dimension.fd_division_master"
			+ " fd_div where p.client_id=c.client_id and fd_div.fd_division_id=p.division_id and p.Project_id=?";

	/*
	 * 
	 * CRM RELATED QURIES
	 */

	public static final String CRM_details_update_query = "update fourth_dimension.Project set crm_name=?,"
			+ "crm_phone=?,crm_email=?,notes=? where Project_id=?";

	public static final String check_TermDays_details = "SELECT from_Project_approval_date,from_implementation_date,from_dispatch_date,from_bill_docking_date "
			+ "FROM fourth_dimension.Project_payment_term_days where Project_id=?";

	public static final String check_CRM_details_query = "select crm_name,crm_phone,crm_email,notes"
			+ " from Project where Project_id=?";

	public static final String check_Project_Approval_query = "SELECT client_manager_approval_date, fd_manager_approval_date,"
			+ "client_manager_comments,fd_manager_comments,client_Project_status,fd_Project_staus FROM fourth_dimension.Project_approval_details"
			+ " where Project_id=?";

	/*
	 * 
	 * Scope of work related
	 */

	
	//Above query changed as there will not be any relation with component_master table because element will be referred as component.go through the below query string for changes.  
	public static final String retrieve_Project_scope_list_query = "SELECT brand_name, ele.element_name ,comp.component_name,material_name ,quantity ," +
			"print_mode_name,Project_element_id,set_as_hold,project_code,Project_elements.project_name FROM fourth_dimension.Project Project," +
			"fourth_dimension.Project_elements Project_elements,fourth_dimension.brand_master brand_master,fourth_dimension.element_master ele, " +
			"fourth_dimension.material_master material_master, fourth_dimension.print_mode_master print_mode_master,fourth_dimension.component_master comp " +
			"WHERE Project_elements.Project_id=? AND Project_elements.Project_id=Project.Project_id AND Project_elements.brand_id=brand_master.brand_id " +
			"AND Project_elements.element_id=ele.element_id AND Project_elements.material_id=material_master.material_id AND Project_elements.component_id = comp.component_id AND" +
			" Project_elements.print_mode_id=print_mode_master.print_mode_id order by Project_elements.project_element_id desc";

	public static final String retrieve_element_scope_date_plans_query = "SELECT Project_element_id , scope_name , set_as_hold ,client_planned_start_date ,"
			+ " client_planned_end_date , client_actual_start_date ,	client_actual_end_date ,"
			+ " fd_planned_start_date , fd_planned_end_date , fd_actual_start_date ,	 fd_actual_end_date	"
			+ "FROM   Project_element_scope_dates ,scope_master	"
			+ "WHERE Project_element_scope_dates.scope_id=scope_master.scope_id AND"
			+ "	Project_element_scope_dates.Project_element_id=?";

	public static final String Project_scope_of_work_insert = "INSERT INTO fourth_dimension.Project_elements(Project_id,"
			+ "brand_id,element_id,component_id,material_id,quantity,print_mode_id,set_as_hold,project_name)VALUES(?,"
			+ "(select brand_id from fourth_dimension.brand_master where brand_name=? group by brand_name),"
			+ "(select element_id from fourth_dimension.element_master where element_name=? group by element_name),"
			+ "(select component_id from fourth_dimension.component_master where component_name=? group by component_name),"
		/*	+ "(select element_id from fourth_dimension.element_master where element_name=?),"*/
			+ "(select material_id from fourth_dimension.material_master where material_name=? group by material_name),"
			+ "?,"
			+ "(select print_mode_id from fourth_dimension.print_mode_master where print_mode_name=?),"
			+ " ?,?)";

	public static final String Project_elements_scope_of_work_query = "INSERT INTO  "
			+ "fourth_dimension . Project_element_scope_dates (  Project_element_id ,"
			+ "  scope_id ,  set_as_hold , client_planned_start_date , client_planned_end_date ,"
			+ " client_actual_start_date , client_actual_end_date , fd_planned_start_date ,"
			+ " fd_planned_end_date , fd_actual_start_date , fd_actual_end_date )"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

	String check_Project_element_query = "SELECT Project_element_id ,Project_id ,brand_id ,element_id ,component_id ,material_id ,"
			+ "quantity ,depot_id ,print_mode_id FROM  fourth_dimension.Project_elements where Project_id=?";

	public static final String scope_list_query = "SELECT scope_id,scope_name FROM fourth_dimension.scope_master order by scope_name";

	// Payment Term

	/*
	 * 
	 * ProjectPayment Term days
	 */

	public static final String check_TermDays_details_query = "SELECT from_Project_approval_date,from_implementation_date,from_dispatch_date,from_bill_docking_date 	"
			+ "FROM fourth_dimension.Project_payment_term_days where Project_id=?";

	public static final String insert_payment_term_query = "INSERT INTO fourth_dimension.Project_payment_term_days"
			+ "(Project_id,from_Project_approval_date,from_implementation_date,from_dispatch_date,"
			+ "from_bill_docking_date)VALUES(?,?,?,?,?)";

	public static final String update_payment_term_query = "UPDATE  fourth_dimension . Project_payment_term_days "
			+ "SET  from_Project_approval_date  = ?, from_implementation_date  = ?, from_dispatch_date  = ?,"
			+ "from_bill_docking_date  = ? WHERE Project_id =?";

	public static final String insert_ProjectApproval_query = "INSERT INTO  fourth_dimension . Project_approval_details "
			+ "( Project_id , client_manager_approval_date ,client_Project_status,"
			+ "client_manager_comments, fd_manager_approval_date ,fd_Project_staus ,"
			+ " fd_manager_comments ) VALUES (?,?,?,?,?,?,?)";

	// Store list Management

	/*
	 * public static final String
	 * insert_Project_store="INSERT INTO  fourth_dimension.Project_store_master"
	 * +
	 * "(Project_id , store_name , chain_name , area_name , town_name , city_name , region_name ,"
	 * + " state_name , country_name )VALUES (? ,? ,? ,? ,? ,? ,? ,? ,?)";
	 */

	/*
	 * public static final String getStoresDetails=
	 * "SELECT  store_name, address, contact_name, contact_phone, notes, store_flag,fdhub_name, handle_by,"
	 * +
	 * " measured_by, measured_on, measurement_status,Project_store_id FROM fourth_dimension.Project_stores where store_name=? "
	 * ;
	 */

	public static final String getStoresDetails = "SELECT  store_name, address, contact_name,"
			+ " contact_phone, store_flag,fdhub_name, handle_by "
			+ " ,Project_store_id,store_code,tsi_name,tsi_phone FROM fourth_dimension.Project_stores where Project_store_id=? ";

	public static final String getLastMeasurementStaus = "SELECT m.status_date ,"
			+ " m.measured_by , measurement_status_name , m.comments , m.date FROM fourth_dimension.measurement_status m ,"
			+ " fourth_dimension.measurement_status_master ms WHERE Project_store_id=? and "
			+ " ms.measurement_status_id=m.measurement_status order by  m.measurement_status_id desc ";

	/*public static final String insert_Project_store = "INSERT INTO  fourth_dimension.Project_stores"
			+ "(Project_id , store_name ,address, store_flag ,fdhub_name ,handle_by,contact_name,contact_phone, chain_name ,trade_name, country_name,region_name ,"
			+ "state_name ,city_name , town_name , area_name  )VALUES (? ,? ,? ,? ,? ,? ,? ,? ,?,?,"
			+ " (select upper(country_name) from country_master where country_id=?),(select upper(region_name) from region_master where region_id=?),"
			+ " (select upper(state_name) from state_master where state_id=?),(select upper(city_name) from city_master where city_id=?),"
			+ " (select upper(town_name) from town_master where town_id=?),(select upper(area_name) from area_master where area_id=?))";*/
	public static final String insert_Project_store ="INSERT INTO  fourth_dimension.Project_stores(Project_id , store_name ,address, store_flag ,fdhub_name ," +
			"handle_by,tsi_name,tsi_phone,chain_name ,trade_name,state_name ,city_name , town_name , area_name ,store_status_id )" +
			" VALUES (? ,? ,? ,? ,? ,? ,? ,? ,?,?,?,?,?,?,?);";
	
	public static final String query_for_already_exist_store ="select project_store_id from  fourth_dimension.Project_stores where Project_id=? and store_name=? and address=? and " +
			"state_name=? and city_name=? ";
	
	public static final String query_for_getting_latest_inserted_store ="select project_store_id from  fourth_dimension.Project_stores where Project_id=? and store_name=? and address=? and " +
	"state_name=? and city_name=? ";

	/*public static final String update_Project_store = "UPDATE  fourth_dimension . Project_stores "
			+ "SET   store_name  = ?,address=?, store_flag=? ,fdhub_name=? ,handle_by=?,contact_name=?,"
			+ "contact_phone=? , chain_name  = ?, trade_name=?, country_name  =(select upper(country_name) from country_master where country_id=?),region_name=(select upper(region_name) from region_master where region_id=?),"
			+ " state_name  = (select upper(state_name) from state_master where state_id=?), city_name  = (select upper(city_name) from city_master where city_id=?), town_name  =(select upper(town_name) from town_master where town_id=?),area_name  = (select upper(area_name) from area_master where area_id=?)"
			+ "   WHERE  Project_store_id  = ?";*/
	
	public static final String update_Project_store = "UPDATE  fourth_dimension . Project_stores " +
			"SET   store_name  = ?,address=?, store_flag=? ,fdhub_name=? ,handle_by=?,tsi_name=?,tsi_phone=? , chain_name  = ?," +
			" trade_name=?,state_name  = ?,city_name  = ?,town_name  =?,area_name  = ? , store_status_id=? WHERE  Project_store_id  = ?";
	
	
	public static final String update_store_measuremnt_details = "UPDATE fourth_dimension.Project_stores SET notes = ?,measurement_status = ?,"
			+ "measured_on = ?,measured_by = ? WHERE Project_store_id = ?";

	public static final String insertMeasuremntTrailforStore_query = "INSERT INTO fourth_dimension.measurement_status "
			+ " ( comments ,measurement_status ,status_date ,measured_by ,Project_store_id , date ) "
			+ " VALUES (?,?,?,?,?,?) ";
	
	public static final String updateMeasuremntTrailforStore_query = "update fourth_dimension.measurement_status "
		+ "set comments=? ,measurement_status=? ,status_date=? ,measured_by=? , date=?  "
		+ " where Project_store_id=?";

	/*
	 * public static final String
	 * update_Project_store="UPDATE  fourth_dimension . Project_store_master " +
	 * "SET   store_name  = ?, chain_name  = ?, trade_name=?, country_name  =?,region_name=?,"
	 * + " state_name  = ?, city_name  = ?, town_name  = ?,area_name  = ? " +
	 * "   WHERE  Project_store_id  = ?";
	 */

	/*
	 * public static final String list_Project_store=
	 * "SELECT Project_id , store_name , chain_name , area_name ," +
	 * " town_name , city_name , region_name , state_name , country_name,Project_store_id  "
	 * + "FROM  fourth_dimension . Project_store_master where Project_id=?";
	 */

	public static final String list_Project_store = "SELECT Project_id , store_name , address, fdhub_name ,"
			+ "handle_by ,contact_name,  contact_phone ,store_flag ,Project_store_id  "
			+ "FROM  fourth_dimension . Project_stores where Project_id=? and area_name= "
			+ "(select area_name from area_master where area_id=?)";

	public static final String delete_Project_store = "DELETE FROM fourth_dimension.Project_stores WHERE Project_store_id in(?)";

	/*
	 * public static final String getProjectStore_query =
	 * "SELECT Project_id , store_name , address, fdhub_name ," +
	 * " handle_by ,contact_name,  contact_phone ,store_flag ,Project_store_id,country_name,state_name,region_name,city_name,town_name,area_name,trade_name,chain_name  "
	 * + "FROM  fourth_dimension . Project_stores where Project_id=?";
	 */

	/*public static final String getProjectStore_query = "SELECT distinct ps.Project_id , ps.store_name , ps.address, ps.fdhub_name ," +
			"ps.handle_by ,ps.contact_name,  ps.contact_phone ,ps.store_flag ,ps.Project_store_id,ps.country_name,ps.state_name," +
			"ps.region_name,ps.city_name,ps.town_name,ps.area_name,ps.trade_name,ps.chain_name,c.country_id,r.region_id,s.state_id," +
			"cit.city_id,tm.town_id,area.area_id ,job.job_card_number,job.job_card_date FROM fourth_dimension.area_master area," +
			"fourth_dimension.Project_stores  ps,fourth_dimension.country_master c,fourth_dimension.region_master r," +
			"fourth_dimension.state_master s,fourth_dimension.city_master cit,fourth_dimension.job_cards job," +
			"fourth_dimension.town_master tm where ps.Project_store_id=? and c.country_name=ps.country_name and ps.region_name=r.region_name " +
			"and ps.state_name=s.state_name and ps.city_name=cit.city_name and ps.town_name=tm.town_name and ps.area_name=area.area_name " +
			" and ps.Project_store_id = job.Project_store_id ;";*/
	
	public static final String getProjectStore_query = "SELECT distinct ps.Project_id , ps.store_name , ps.address, ps.fdhub_name ," +
							"ps.handle_by ,ps.tsi_name,  ps.tsi_phone ,ps.store_flag ,ps.Project_store_id,ps.country_name,ps.state_name," +
							"ps.region_name,ps.city_name,ps.town_name,ps.area_name,ps.trade_name,ps.chain_name,job.job_card_number,job.job_card_date," +
							"(select project_name from fourth_dimension.project_elements pe where  pe.project_element_id=psem.project_element_id " +
							"and pe.project_id=ps.project_id) project_name,psem.quantity,psem.project_element_mapping_id FROM " +
							"fourth_dimension.project_store_element_mapping psem,fourth_dimension.Project_stores  ps," +
							"fourth_dimension.job_cards job,fourth_dimension.project_elements pe,fourth_dimension.store_status_master ssm " +
							"where  ps.Project_store_id = job.Project_store_id and ps.Project_store_id=? " +
							"and ps.store_status_id=ssm.store_status_id and ps.project_store_id=psem.project_store_id;";

	
	public static final String getProjectStore_no_job_card="SELECT distinct ps.Project_id , ps.store_name , ps.address, ps.fdhub_name ," +
			"ps.handle_by ,ps.tsi_name,  ps.tsi_phone ,ps.store_flag ,ps.Project_store_id,ps.country_name,ps.state_name,ps.region_name," +
			"ps.city_name,ps.town_name,ps.area_name,ps.trade_name,ps.chain_name, (select project_name from fourth_dimension.project_elements pe " +
			"where pe.project_element_id=psem.project_element_id and pe.project_id=ps.project_id)project_name,psem.quantity,psem.project_element_mapping_id FROM fourth_dimension.Project_stores  ps ," +
			" fourth_dimension.store_status_master ssm, fourth_dimension.project_store_element_mapping psem where ps.Project_store_id=?" +
			" and psem.project_store_id=ps.Project_store_id and ps.store_status_id=ssm.store_status_id and ps.project_store_id=psem.project_store_id;";
	
	
	// Measurement Sheet

	public static final String insert_Measurement_Data_For_Element = "INSERT INTO fourth_dimension . measurement_data "
			+ "( Project_element_id ,  Project_store_id ,  surface_number , surface_detail , height ,"
			+ " width , width_unit , thickness , thickness_unit , quantity,element_status,component_id,material_id ) VALUES "
			+ "(?,?,"
			+ "?,?,?,?,(SELECT id FROM fourth_dimension.unit_master where unit_name=?),?,"
			+ "(SELECT id FROM fourth_dimension.unit_master where unit_name=?),?,?,?,?)";

	public static final String select_Measurement_display = "SELECT  measurement_id ,Project_element_id ,Project_store_id ,"
			+ " surface_number,surface_detail ,height ,width , width_unit,thickness, thickness_unit , quantity FROM  "
			+ "fourth_dimension . measurement_data where Project_element_id= ? and Project_store_id=?";

	public static final String updateMeasurementData_query = "UPDATE  fourth_dimension . measurement_data "
			+ " set surface_number=?,surface_detail=?,height  = ?,"
			+ " width= ?,width_unit=(Select id from unit_master where unit_name=?) ,thickness= ?,thickness_unit  = " +
					"(Select id from unit_master where unit_name=?), quantity  = ?,element_status=? WHERE measurement_id  = ?";

	// Relational Drop Down
	public static final String drop_down_elements_of_Project = "SELECT upper(element_name) FROM Project_elements p,element_master e"
			+ " WHERE p.element_id=e.element_id AND p.Project_id=? order by element_name";
	public static final String drop_down_store_of_Project = "SELECT upper(store_name) FROM Project_stores prj_str,Project prj "
			+ "WHERE prj_str.Project_id=prj.Project_id and prj_str.Project_id=? order by store_name";

	// Filter DropDown

	/*
	 * public static final String drop_down_brand_on_ProjectId=
	 * "SELECT upper(brand_name) from fourth_dimension.brand_master where brand_id in (select distinct Project_elements.brand_id "
	 * +
	 * " FROM fourth_dimension.Project_elements where Project_elements.Project_id=(select Project_id from fourth_dimension.Project where Project_name=?))"
	 * ;
	 */

	public static final String drop_down_brand_on_ProjectId = "SELECT upper(brand_name),brand_id from fourth_dimension.brand_master where brand_id in (select distinct Project_elements.brand_id "
			+ " FROM fourth_dimension.Project_elements where Project_elements.Project_id=?) order by brand_name";

	/*
	 * public static final String drop_down_element_on_brandId =
	 * "SELECT (SELECT upper(element_name) from fourth_dimension.element_master where element_id=e.element_id)"
	 * +
	 * " ,e.element_id,e.set_as_hold FROM fourth_dimension.Project_elements e where e.brand_id=? and e.Project_id=?"
	 * ;
	 */
	public static final String drop_down_element_on_brandId = "SELECT distinct (SELECT upper(element_name) from fourth_dimension.element_master "
			+ "where element_id=e.element_id order by element_name),e.element_id FROM fourth_dimension.Project_elements e where e.brand_id=? and e.Project_id" +
					" =? ";
	
	
	public static final String drop_down_component_on_brandId = "SELECT distinct (SELECT upper(component_name) from fourth_dimension.component_master "
		+ "where component_id=e.component_id order by component_name),e.component_id FROM fourth_dimension.Project_elements e where e.brand_id=? and e.Project_id" +
			" =? ";

	public static final String drop_down_component_material_on_element = "SELECT (select upper(component_name) from fourth_dimension.component_master"
			+ " where component_id=e.component_id), (select upper(material_name) from fourth_dimension.material_master "
			+ " where material_id=e.material_id),set_as_hold FROM fourth_dimension.Project_elements e where element_id=? and e.brand_id=? and e.Project_id=?";
	/*
	 * Filter location *
	 */
	public static final String filter_region_master = "select upper(region_name),region_id from fourth_dimension.region_master where country_id=? order by region_name";
	public static final String filter_state_master = "select upper(state_name),state_id from fourth_dimension.state_master where region_id=? order by state_name";
	public static final String filter_city_master = "select upper(city_name),city_id from fourth_dimension.city_master where state_id=? order by city_name";
//	public static final String filter_town_master = "select upper(town_name),town_id from fourth_dimension.town_master where city_id=?";
	
	public static final String filter_town_master = "select upper(town_name),town_id from fourth_dimension.town_master where city_id=? or town_name='NA' order by town_name";
	
//	public static final String filter_area_master = "select upper(area_name),area_id from fourth_dimension.area_master where town_id=?";

	public static final String filter_area_master = "select upper(area_name),area_id from fourth_dimension.area_master where town_id=? or area_name='NA' order by area_name";
	
	public static final String filter_element_status = "SELECT element_status_id,element_status_name FROM fourth_dimension.element_status_master "; // New
																																					// Requirement
																																					// for
																																					// element
																																					// status
																																					// drop
																																					// down

	/*
	 * Filtering
	 */

	// public static final String
	// filter_stores_on_Project="select upper(store_name),Project_store_id from fourth_dimension.Project_stores where  Project_id=?";

	public static final String filter_stores_on_Project = "select upper(store_name),Project_store_id from fourth_dimension.Project_stores where  Project_id " +
			"=? "
			+ "  order by store_name";

//	public static final String filter_division_master = "SELECT upper(division_name),fd_division_id FROM  fourth_dimension . fd_division_master where hub_id=? ";
	public static final String filter_division_master = "SELECT upper(division_name),fd_division_id FROM  fourth_dimension . fd_division_master order by division_name";
	public static final String InstructionOnProject = "SELECT special_instruction FROM fourth_dimension.Project where Project_id=?";
	/*
	 * Project Client *
	 */
	/*
	 * public static final String filter_Project_name_on_Client=
	 * "select upper(Project_name) from fourth_dimension.Project where client_id="
	 * +
	 * "(select client_id from fourth_dimension.client_master where client_name=?)"
	 * ;
	 */


	public static final String filter_Project_name_on_Client = "select upper(Project_name),Project_id from fourth_dimension.Project where client_id=?";


	/*
	 * public static final String filter_Stores_On_Area=
	 * "select upper(store_name) from fourth_dimension.Project_stores where area_name="
	 * +
	 * "(select area_name from fourth_dimension.area_master where area_name=?) and Project_id=(select Project_id from fourth_dimension.Project where Project_name=?)"
	 * ;
	 */
	public static final String filter_Stores_On_Area = "select upper(store_name),Project_store_id from fourth_dimension.Project_stores where area_name="
			+ "(select area_name from fourth_dimension.area_master where area_id=?) and Project_id=? order by store_name";

	// For ScopOF WORk
	public static final String drop_down_region_against_country = "select upper(region_name),region_id from fourth_dimension.region_master where country_id=? order by region_name";
	public static final String drop_down_state_against_region = "select upper(state_name),state_id from fourth_dimension.state_master where region_id=? order by state_name";
	public static final String drop_down_city_against_state = "select upper(city_name),city_id from fourth_dimension.city_master where state_id=? order by city_name";
	// Drop Down List
	public static final String drop_down_scope_master = " select scope_name , scope_id from fourth_dimension.scope_master order by scope_name";
	public static final String drop_down_brand_master_onClient = "select upper(brand_name) from fourth_dimension.brand_master "
			+ " where client_id=(select client_id from client_master where client_name=?) order by brand_name";
	public static final String drop_down_fddiv_for_search = "select upper(division_name) from fourth_dimension.fd_division_master order by division_name";
	public static final String drop_down_unit_master = "select upper(unit_name) from fourth_dimension.unit_master order by unit_name";
	public static final String drop_down_measurementStatus_master = "select measurement_status_id,measurement_status_name from fourth_dimension.measurement_status_master order by measurement_status_name";
	public static final String drop_down_country__master = "select upper(country_name),country_id from fourth_dimension.country_master order by country_name";
	public static final String drop_down_area__master = "select upper(area_name) from fourth_dimension.area_master order by area_name ";
	public static final String drop_down_town__master = "select upper(town_name) from fourth_dimension.town_master order by town_name";
	public static final String drop_down_depot_master = "select upper(depot_name) from fourth_dimension.depot_master order by depot_name";
	public static final String drop_down_print_mode_master = "select upper(print_mode_name) from fourth_dimension.print_mode_master order by print_mode_name";
	public static final String drop_down_material_master = "select upper(material_name) from fourth_dimension.material_master order by material_name";
	public static final String drop_down_material_master_id_and_name="select material_id,upper(material_name) from fourth_dimension.material_master order by material_name";
//	public static final String drop_down_component_master = "select upper(component_name) from fourth_dimension.component_master order by component_name"; //Element will be referred as component.so for the component data will be taken from element_master table for the same query changed as below.
	public static final String drop_down_component_master = "select upper(component_name) from fourth_dimension.component_master order by component_name";
	public static final String drop_down_element_master = "select upper(element_name) from fourth_dimension.element_master order by element_name";

	/*
	 * public static final String drop_down_brand_master =
	 * "select upper(brand_name) from fourth_dimension.brand_master"; need to
	 * modify
	 */

	public static final String drop_down_brand_master = "SELECT distinct bm.brand_name FROM fourth_dimension.brand_master bm,fourth_dimension.client_master cm "
			+ "where bm.client_id=cm.client_id and cm.client_name=? order by brand_name";

	public static final String drop_down_client_master = "SELECT upper(client_name),client_id FROM fourth_dimension.client_master order by client_name";
	public static final String drop_down_fddiv_master = "select upper(division_name),fd_division_id from fourth_dimension.fd_division_master order by division_name";
	public static final String drop_down_region_master = "select upper(region_name),region_id from fourth_dimension.region_master order by region_name";
	public static final String drop_down_state_master = "select distinct upper(state_name),state_id from fourth_dimension.state_master order by state_name";
	public static final String drop_down_trade_master = "select upper(trade_name),trade_id from fourth_dimension.trade_master order by trade_name";
	public static final String drop_down_chain_master = "select upper(chain_name),chain_id from fourth_dimension.chain_master order by chain_name";
	public static final String drop_down_city_master = "select upper(city_name),city_id from fourth_dimension.city_master order by city_name";
	public static final String drop_down_fdhub_master = "select upper(hub_name) from fourth_dimension.fd_hub_master order by hub_name";
	public static final String drop_down_fdemp_master = "select upper(employee_name) from fourth_dimension.fd_employee_master order by employee_name";
	public static final String drop_down_fdemp_name = "SELECT upper(Project_name) FROM fourth_dimension.Project order by Project_name";
	public static final String drop_down_fdhub_master_id = "select upper(hub_name),fd_hub_id from fourth_dimension.fd_hub_master order by hub_name";
	public static final String filter_FDManager_Name = "select EmployeeID, upper(employee_name) from fourth_dimension.fd_employee_master";
	//public static final String drop_down_project_List = "SELECT project_name,project_element_id FROM fourth_dimension.project_elements";
	public static final String drop_down_project_List = "SELECT project_name,project_element_id FROM fourth_dimension.project_elements " +
															"where project_id=? group by project_name ";
	public static final String drop_down_store_status_List = "SELECT store_status_id,store_status_name FROM fourth_dimension.store_status_master group by store_status_name";
	

	
}